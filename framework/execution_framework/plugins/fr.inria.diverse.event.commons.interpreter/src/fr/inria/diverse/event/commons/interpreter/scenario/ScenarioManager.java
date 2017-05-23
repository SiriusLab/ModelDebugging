package fr.inria.diverse.event.commons.interpreter.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
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

import fr.inria.diverse.event.commons.interpreter.property.ClassPropertyAspect;
import fr.inria.diverse.event.commons.interpreter.property.IPropertyListener;
import fr.inria.diverse.event.commons.interpreter.property.IPropertyMonitor;
import fr.inria.diverse.event.commons.model.EventInstance;
import fr.inria.diverse.event.commons.model.IEventManager;
import fr.inria.diverse.event.commons.model.property.Property;
import fr.inria.diverse.event.commons.model.property.StateProperty;
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
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;

public class ScenarioManager implements IScenarioManager {

	private final Scenario<?> scenario;
	private final Resource executedModel;
	private final IEventManager eventManager;
	private final IPropertyMonitor propertyMonitor;
	private final List<ScenarioElement<?>> currentElements = new ArrayList<>();
	private final Map<ScenarioFSM<?, ?, ?, ?>, ScenarioFSMState<?, ?>> currentFSMStates = new HashMap<>();
	private final Report report;

	public ScenarioManager(Scenario<?> scenario, Resource executedModel, IEventManager eventManager,
			IPropertyMonitor propertyMonitor) {
		this.scenario = scenario;
		this.executedModel = executedModel;
		this.eventManager = eventManager;
		this.propertyMonitor = propertyMonitor;
		scenario.getElements().forEach(element -> {
			final Property property = element.getGuard();
			propertyMonitor.addListener(property, new ScenarioGuardListener(element, property));
		});
		this.report = ReportFactory.eINSTANCE.createReport();
	}

	@Override
	public List<EventInstance> getEvents() {
		if (currentElements.isEmpty()) {
			return Collections.emptyList();
		} else {
			final List<EventInstance> events = new ArrayList<>();
			while (events.addAll(gatherEventInstances()));
			return events;
		}
	}

	private List<EventInstance> gatherEventInstances() {
		final List<EventInstance> result = new ArrayList<>();
		final Stream<ScenarioElement<?>> reachedSet = currentElements.stream().filter(e -> {
			if (e.getGuard() == null || evaluateStateProperty(e.getGuard())) {
				if (e instanceof ScenarioFSM<?, ?, ?, ?>) {
					return getEventInstances((ScenarioFSM<?, ?, ?, ?>) e, result);
				} else {
					final EventOccurrence<?, ?> eventOccurrence = (EventOccurrence<?, ?>) e;
					final EventInstance eventInstance = createEvent(eventOccurrence.getEvent());
					if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
						result.add(eventInstance);
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		});
		reachedSet.forEach(e -> {
			currentElements.remove(e);
			currentElements.addAll(e.getNextElements());
		});
		return result;
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

	private boolean getEventInstances(ScenarioFSM<?, ?, ?, ?> fsm, List<EventInstance> result) {
		ScenarioFSMState<?, ?> currentState = currentFSMStates.get(fsm);
		boolean canProgress = true;
		if (currentState == null) {
			currentState = fsm.getInitialState();
			if (currentState.getEvent() != null) {
				final EventInstance eventInstance = createEvent(currentState.getEvent());
				if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
					result.add(eventInstance);
					currentFSMStates.put(fsm, currentState);
				} else {
					// The FSM is stuck as it is supposed to send an event is
					// invalid or cannot be received,
					// thus we 'rollback' to the previous state by not saving
					// the current state.
					canProgress = false;
				}
			}
		}
		if (canProgress) {
			ScenarioFSMTransition<?, ?> fireableTransition = currentState.getOutgoingTransitions().stream()
					.filter(t -> t.getGuard() == null || evaluateStateProperty(t.getGuard())).findFirst().orElse(null);
			while (fireableTransition != null && canProgress) {
				currentState = fireableTransition.getTarget();
				final EventInstance eventInstance = createEvent(currentState.getEvent());
				if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
					result.add(eventInstance);
					currentFSMStates.put(fsm, currentState);
					fireableTransition = currentState.getOutgoingTransitions().stream()
							.filter(t -> t.getGuard() == null || evaluateStateProperty(t.getGuard())).findFirst()
							.orElse(null);
				} else {
					// The FSM is stuck as it is supposed to send an event is
					// invalid or cannot be received,
					// thus we 'rollback' to the previous state by not saving
					// the current state.
					canProgress = false;
				}
			}
		}
		if (fsm.getAcceptingStates().contains(currentState)) {
			// The FSM reached an accepting state, it will not be used again for
			// this execution of the scenario
			currentFSMStates.remove(fsm);
			return true;
		}
		return false;
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

	private boolean evaluateStateProperty(StateProperty<?> property) {
		final List<EObject> eventReceivers = new ArrayList<>();
		final Object target = property.getTarget();
		if (target == null) {
			Set<EClassifier> eClasses = new HashSet<>();
			property.eClass().getEAllGenericSuperTypes().stream()
					.forEach(t -> t.getETypeArguments().stream().forEach(a -> eClasses.add(a.getEClassifier())));
			final Iterator<EObject> it = executedModel.getAllContents();
			while (it.hasNext()) {
				EObject o = it.next();
				if (eClasses.contains(o.eClass()) && ClassPropertyAspect.evaluate(property, o)) {
					eventReceivers.add(o);
				}
			}
		} else {
			final EObject target_cast = (EObject) target;
			if (ClassPropertyAspect.evaluate(property, target_cast)) {
				eventReceivers.add(target_cast);
			}
		}
		return !eventReceivers.isEmpty();
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
			if (element instanceof ScenarioFSM<?, ?, ?, ?>) {
				// FSM elements 
				handleFSM((ScenarioFSM<?, ?, ?, ?>) element);
			} else {
				final EventOccurrence<?, ?> eventOccurrence = (EventOccurrence<?, ?>) element;
				final EventInstance eventInstance = createEvent(eventOccurrence.getEvent());
				if (eventInstance != null && eventManager.canSendEvent(eventInstance)) {
					eventManager.sendEvent(eventInstance);
				}
			}
			currentElements.remove(element);
			currentElements.addAll(element.getNextElements());
			propertyMonitor.removeListener(property, this);
			element.getNextElements().stream().filter(e -> e instanceof EventOccurrence<?, ?>).forEach(e -> {
				final Property property = e.getGuard();
				propertyMonitor.addListener(property, new ScenarioGuardListener(e, property));
			});
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
