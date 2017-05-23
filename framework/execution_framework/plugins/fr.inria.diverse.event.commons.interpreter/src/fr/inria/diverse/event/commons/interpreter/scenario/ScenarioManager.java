package fr.inria.diverse.event.commons.interpreter.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.EMFCommandTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;

import fr.inria.diverse.event.commons.interpreter.property.IPropertyListener;
import fr.inria.diverse.event.commons.interpreter.property.IPropertyMonitor;
import fr.inria.diverse.event.commons.model.EventInstance;
import fr.inria.diverse.event.commons.model.IEventManager;
import fr.inria.diverse.event.commons.model.property.Property;
import fr.inria.diverse.event.commons.model.report.EventReport;
import fr.inria.diverse.event.commons.model.report.Report;
import fr.inria.diverse.event.commons.model.report.ReportFactory;
import fr.inria.diverse.event.commons.model.scenario.ElementProvider;
import fr.inria.diverse.event.commons.model.scenario.Event;
import fr.inria.diverse.event.commons.model.scenario.EventOccurrence;
import fr.inria.diverse.event.commons.model.scenario.Scenario;
import fr.inria.diverse.event.commons.model.scenario.ScenarioElement;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSM;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;

public class ScenarioManager implements IScenarioManager {

	private Scenario<?> scenario;
	private final Resource executedModel;
	private final IEventManager eventManager;
	private final IPropertyMonitor propertyMonitor;
	private final List<ScenarioElement<?>> currentElements = new ArrayList<>();
	private final Report report = ReportFactory.eINSTANCE.createReport();

	public ScenarioManager(Resource executedModel, IEventManager eventManager, IPropertyMonitor propertyMonitor) {
		this.executedModel = executedModel;
		this.eventManager = eventManager;
		this.propertyMonitor = propertyMonitor;
	}
	
	public void loadScenario(Scenario<?> scenario) {
		this.scenario = scenario;
		this.scenario.getElements().forEach(element -> {
			currentElements.add(element);
			final Property property = element.getGuard();
			propertyMonitor.addListener(property, new ScenarioGuardListener(element, property));
		});
	}

	private void handleFSM(ScenarioFSM<?, ?, ?, ?> fsm) {
		final ScenarioFSMState<?, ?> initialState = fsm.getInitialState();
		if (initialState.getEvent() != null) {
			final EventInstance eventInstance = createEvent(initialState.getEvent());
			if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
				eventManager.sendEvent(eventInstance);
			}
			final Map<Property, IPropertyListener> fsmGuards = new HashMap<>();
			initialState.getOutgoingTransitions().forEach(t -> {
				final Property property = t.getGuard();
				if (property != null) {
					IPropertyListener listener = new FSMGuardListener(fsm, t.getTarget(), property, fsmGuards);
					fsmGuards.put(property, listener);
				}
			});
			fsmGuards.forEach((p, l) -> {
				propertyMonitor.addListener(p, l);
			});
		}
	}

	private EventInstance createEvent(Event<?> originalEvent) {
		final ElementProvider<?> targetProvider = originalEvent.getTargetProvider();
		final List<EObject> eventParameterMatches = new ArrayList<>();
		if (targetProvider != null) {
			final EObject target = ElementProviderAspect.resolve(targetProvider, executedModel);
			if (target != null) {
				eventParameterMatches.add(target);
				final Map<EStructuralFeature, Object> parameters = new HashMap<>();
				parameters.put(ScenarioPackage.Literals.EVENT__TARGET_PROVIDER, target);
				for (EStructuralFeature f : originalEvent.eClass().getEStructuralFeatures()) {
					if (f instanceof EAttribute) {
						parameters.put(f, originalEvent.eGet(f));
					} else {
						final ElementProvider<?> paramProvider = (ElementProvider<?>) originalEvent.eGet(f);
						final EObject parameter = ElementProviderAspect.resolve(paramProvider, executedModel);
						if (parameter != null) {
							parameters.put(f, parameter);
							eventParameterMatches.add(parameter);
						}
					}
				}
				final EventReport eventReport = ReportFactory.eINSTANCE.createEventReport();
				if (eventParameterMatches.size() > 1) {
					eventReport.getMatches().addAll(eventParameterMatches.subList(1, eventParameterMatches.size() - 1));
				}
				eventReport.setEvent(originalEvent);
				report.getEvents().add(eventReport);
				return new EventInstance(originalEvent, parameters);
			}
		}
		return null;
	}

	@Override
	public boolean isScenarioComplete() {
		return currentElements.isEmpty();
	}

	@Override
	public void saveScenarioReport() {
		final ResourceSet resourceSet = scenario.eResource().getResourceSet();
		final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE
				.getEditingDomain(resourceSet);
		final URI scenarioURI = scenario.eResource().getURI();
		final URI reportURI = scenarioURI.trimFileExtension().appendFileExtension("report");
		final Resource resource = resourceSet.createResource(reportURI);
		final RecordingCommand command = new RecordingCommand(editingDomain) {
			@Override
			public void doExecute() {
				resource.getContents().add(report);
			}
		};
		EMFCommandTransaction transaction = new EMFCommandTransaction(command,
				(InternalTransactionalEditingDomain) editingDomain, null);
		try {
			transaction.start();
			command.execute();
			transaction.commit();
		} catch (InterruptedException | RollbackException e) {
			e.printStackTrace();
		}
		try {
			resource.save((new PlatformResourceURIHandlerImpl()).createOutputStream(reportURI, Collections.emptyMap()),
					Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ScenarioGuardListener implements IPropertyListener {

		private final ScenarioElement<?> element;
		private final Property property;

		public ScenarioGuardListener(ScenarioElement<?> element, Property property) {
			this.element = element;
			this.property = property;
		}

		@Override
		public void update(boolean propertyValue) {
			if (propertyValue) {
				currentElements.remove(element);
				currentElements.addAll(element.getNextElements());
				// We stop monitoring the guard of this scenario element.
				propertyMonitor.removeListener(property, this);
				if (element instanceof EventOccurrence<?, ?>) {
					final EventOccurrence<?, ?> eventOccurrence = (EventOccurrence<?, ?>) element;
					final EventInstance eventInstance = createEvent(eventOccurrence.getEvent());
					if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
						eventManager.sendEvent(eventInstance);
					}
					// We start monitoring the guards of the next elements in the scenario tree.
					eventOccurrence.getNextElements().stream().forEach(e -> {
						final Property property = e.getGuard();
						propertyMonitor.addListener(property, new ScenarioGuardListener(e, property));
					});
				} else {
					// We do not monitor the guards of the next elements in the scenario tree
					// because they are only evaluated when the FSM reaches an accepting state.
					handleFSM((ScenarioFSM<?, ?, ?, ?>) element);
				}
			}
		}
	}

	private class FSMGuardListener implements IPropertyListener {

		private final ScenarioFSM<?, ?, ?, ?> fsm;
		private final ScenarioFSMState<?, ?> state;
		private final Property property;
		private Map<Property, IPropertyListener> fsmGuards = new HashMap<>();

		public FSMGuardListener(ScenarioFSM<?, ?, ?, ?> fsm, ScenarioFSMState<?, ?> state, Property property,
				Map<Property, IPropertyListener> fsmGuards) {
			this.fsm = fsm;
			this.state = state;
			this.property = property;
			this.fsmGuards = fsmGuards;
		}

		@Override
		public void update(boolean propertyValue) {
			if (propertyValue) {
				// We send the event associated to the newly reached state of the FSM, if any.
				final EventInstance eventInstance = createEvent(state.getEvent());
				if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
					eventManager.sendEvent(eventInstance);
				}
				// We stop monitoring the current guard as well as the guards of the
				// other outgoing transitions of the previous state of the FSM.
				propertyMonitor.removeListener(property, this);
				fsmGuards.forEach((p, l) -> propertyMonitor.removeListener(p, l));
				fsmGuards.clear();
				if (fsm.getAcceptingStates().contains(state)) {
					// The FSM has reached an accepting state, thus we start monitoring
					// the guards of the next elements in the scenario tree.
					currentElements.remove(fsm);
					currentElements.addAll(fsm.getNextElements());
					propertyMonitor.removeListener(property, this);
					fsm.getNextElements().stream().filter(e -> e instanceof EventOccurrence<?, ?>).forEach(e -> {
						final Property property = e.getGuard();
						propertyMonitor.addListener(property, new ScenarioGuardListener(e, property));
					});
				} else {
					// Otherwise we start monitoring the guards of the outgoing transitions.
					final Map<Property, IPropertyListener> fsmGuards = new HashMap<>();
					state.getOutgoingTransitions().forEach(t -> {
						final Property property = t.getGuard();
						if (property != null) {
							IPropertyListener listener = new FSMGuardListener(fsm, t.getTarget(), property, fsmGuards);
							fsmGuards.put(property, listener);
						}
					});
					fsmGuards.forEach((p, l) -> {
						propertyMonitor.addListener(p, l);
					});
				}
			}
		}
	}
}
