package org.eclipse.gemoc.event.commons.interpreter.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

import org.eclipse.gemoc.event.commons.interpreter.property.IPropertyListener;
import org.eclipse.gemoc.event.commons.interpreter.property.IPropertyMonitor;
import org.eclipse.gemoc.event.commons.model.EventInstance;
import org.eclipse.gemoc.event.commons.model.IEventManager;
import org.eclipse.gemoc.event.commons.model.property.CompositeProperty;
import org.eclipse.gemoc.event.commons.model.property.EventPrecondition;
import org.eclipse.gemoc.event.commons.model.property.Property;
import org.eclipse.gemoc.event.commons.model.property.PropertyFactory;
import org.eclipse.gemoc.event.commons.model.property.PropertyReference;
import org.eclipse.gemoc.event.commons.model.report.EventReport;
import org.eclipse.gemoc.event.commons.model.report.Report;
import org.eclipse.gemoc.event.commons.model.report.ReportFactory;
import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider;
import org.eclipse.gemoc.event.commons.model.scenario.Event;
import org.eclipse.gemoc.event.commons.model.scenario.EventOccurrence;
import org.eclipse.gemoc.event.commons.model.scenario.Scenario;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;

public class ScenarioManager implements IScenarioManager {

	private Scenario<?> scenario;
	private final Resource executedModel;
	private final IEventManager eventManager;
	private final IPropertyMonitor propertyMonitor;
	private final List<ScenarioElement<?>> currentElements = new ArrayList<>();
	private final Report report = ReportFactory.eINSTANCE.createReport();
	private final PropertyFactory propertyFactory = PropertyFactory.eINSTANCE;

	public ScenarioManager(Resource executedModel, IEventManager eventManager, IPropertyMonitor propertyMonitor) {
		this.executedModel = executedModel;
		this.eventManager = eventManager;
		this.propertyMonitor = propertyMonitor;
	}

	public void loadScenario(Scenario<?> scenario) {
		this.scenario = scenario;
		this.scenario.getElements().forEach(element -> {
			setupScenarioElementPropertyListeners(element);
		});
	}

	private void setupScenarioElementPropertyListeners(ScenarioElement<?> element) {
		currentElements.add(element);
		final boolean isEvent = element instanceof EventOccurrence<?, ?>;
		if (isEvent) {
			handleEventOccurrence((EventOccurrence<?, ?>) element);
		} else {
			handleFSM((ScenarioFSM<?, ?, ?, ?>) element);
		}
	}

	private void setupFSMStatePropertyListeners(ScenarioFSM<?, ?, ?, ?> fsm, ScenarioFSMState<?, ?> state) {
		final Map<Property, IPropertyListener> fsmGuards = new HashMap<>();
		state.getOutgoingTransitions().forEach(t -> {
			final Property property = t.getGuard();
			final Event<?> event = t.getTarget().getEvent();
			if (property != null && event != null) {
				// If the FSM state sends an event, we add the event
				// precondition to the guard of the transition by
				// using a composite property.
				final CompositeProperty<Property> compositeProperty = propertyFactory.createCompositeProperty();
				final PropertyReference<Property> propertyReference = propertyFactory.createPropertyReference();
				final EventPrecondition<Event<?>> precondition = propertyFactory.createEventPrecondition();
				propertyReference.setReferencedProperty(property);
				precondition.setEvent(event);
				compositeProperty.getProperties().add(propertyReference);
				compositeProperty.getProperties().add(precondition);
				IPropertyListener listener = new FSMGuardListener(fsm, t.getTarget(), compositeProperty, fsmGuards);
				fsmGuards.put(compositeProperty, listener);
			} else if (property != null) {
				IPropertyListener listener = new FSMGuardListener(fsm, t.getTarget(), property, fsmGuards);
				fsmGuards.put(property, listener);
			} else if (event != null) {
				final EventPrecondition<Event<?>> precondition = propertyFactory.createEventPrecondition();
				precondition.setEvent(event);
				IPropertyListener listener = new FSMGuardListener(fsm, t.getTarget(), precondition, fsmGuards);
				fsmGuards.put(precondition, listener);
			} else {
				// Degenerate case: no guard, no event precondition.
				// Go directly to the target state?
				fsmGuards.clear();
				setupFSMStatePropertyListeners(fsm, t.getTarget());
			}
		});
		new HashSet<>(fsmGuards.keySet()).forEach(p -> {
			IPropertyListener l = fsmGuards.get(p);
			if (l != null) {
				propertyMonitor.monitorProperty(p, l);
			}
		});
	}

	private void handleEventOccurrence(EventOccurrence<?, ?> eventOccurrence) {
		final EventPrecondition<Event<?>> precondition = propertyFactory.createEventPrecondition();
		precondition.setEvent((eventOccurrence).getEvent());
		final Property property = eventOccurrence.getGuard();
		if (property != null) {
			// We create a composite property containing
			// both the guard of the event and the event precondition.
			final CompositeProperty<Property> compositeProperty = propertyFactory.createCompositeProperty();
			final PropertyReference<Property> propertyReference = propertyFactory.createPropertyReference();
			propertyReference.setReferencedProperty(property);
			compositeProperty.getProperties().add(propertyReference);
			compositeProperty.getProperties().add(precondition);
			propertyMonitor.monitorProperty(compositeProperty, new ScenarioGuardListener(eventOccurrence, compositeProperty));
		} else {
			propertyMonitor.monitorProperty(precondition, new ScenarioGuardListener(eventOccurrence, precondition));
		}
	}
	
	private void handleFSM(ScenarioFSM<?, ?, ?, ?> fsm) {
		final ScenarioFSMState<?, ?> initialState = fsm.getInitialState();
		final Property property = fsm.getGuard();
		final Event<?> event = initialState.getEvent();
		if (event != null && property != null) {
			// We create a composite property containing
			// both the guard of the fsm and the event precondition
			final CompositeProperty<Property> compositeProperty = propertyFactory.createCompositeProperty();
			final PropertyReference<Property> propertyReference = propertyFactory.createPropertyReference();
			final EventPrecondition<Event<?>> precondition = propertyFactory.createEventPrecondition();
			propertyReference.setReferencedProperty(property);
			precondition.setEvent(event);
			compositeProperty.getProperties().add(propertyReference);
			compositeProperty.getProperties().add(precondition);
			propertyMonitor.monitorProperty(compositeProperty, new ScenarioGuardListener(fsm, compositeProperty));
		} else if (event != null) {
			final EventPrecondition<Event<?>> precondition = propertyFactory.createEventPrecondition();
			precondition.setEvent(event);
			propertyMonitor.monitorProperty(precondition, new ScenarioGuardListener(fsm, precondition));
		} else if (property != null) {
			propertyMonitor.monitorProperty(property, new ScenarioGuardListener(fsm, property));
		} else {
			setupFSMStatePropertyListeners(fsm, initialState);
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
				// We stop monitoring the guard of this scenario element.
				propertyMonitor.unmonitorProperty(property, this);
				if (element instanceof EventOccurrence<?, ?>) {
					currentElements.remove(element);
					currentElements.addAll(element.getNextElements());
					final EventOccurrence<?, ?> eventOccurrence = (EventOccurrence<?, ?>) element;
					eventManager.sendEvent(createEvent(eventOccurrence.getEvent()));
					// We start monitoring the guards of the next elements in
					// the scenario tree.
					eventOccurrence.getNextElements().stream().forEach(e -> {
						setupScenarioElementPropertyListeners(e);
					});
				} else {
					// We do not monitor the guards of the next elements in the
					// scenario tree yet because they must only be evaluated once the
					// FSM reaches an accepting state.
					final ScenarioFSM<?, ?, ?, ?> fsm = (ScenarioFSM<?, ?, ?, ?>) element;
					final Event<?> event = fsm.getInitialState().getEvent();
					if (event != null) {
						eventManager.sendEvent(createEvent(event));
					}
					setupFSMStatePropertyListeners(fsm, fsm.getInitialState());
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
				// We send the event associated to the newly reached state of
				// the FSM, if any. We do not have to check if it can be sent
				// as this check is part of the guard of the incoming transition.
				final Event<?> event = state.getEvent();
				if (event != null) {
					eventManager.sendEvent(createEvent(event));
				}
				// We stop monitoring the current guard as well as
				// the guards of the other outgoing transitions of
				// the previous state of the FSM.
				propertyMonitor.unmonitorProperty(property, this);
				fsmGuards.forEach((p, l) -> propertyMonitor.unmonitorProperty(p, l));
				fsmGuards.clear();
				if (fsm.getAcceptingStates().contains(state)) {
					// The FSM has reached an accepting state, thus we start
					// monitoring the guards of the next elements in the
					// scenario tree.
					currentElements.remove(fsm);
					currentElements.addAll(fsm.getNextElements());
					fsm.getNextElements().stream().forEach(e -> {
						setupScenarioElementPropertyListeners(e);
					});
				} else {
					// Otherwise we start monitoring the guards of the outgoing
					// transitions.
					setupFSMStatePropertyListeners(fsm, state);
				}
			}
		}
	}
}
