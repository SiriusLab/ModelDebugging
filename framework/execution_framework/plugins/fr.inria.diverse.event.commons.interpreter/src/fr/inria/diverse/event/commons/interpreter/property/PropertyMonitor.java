package fr.inria.diverse.event.commons.interpreter.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.event.commons.interpreter.scenario.ElementProviderAspect;
import fr.inria.diverse.event.commons.model.EventInstance;
import fr.inria.diverse.event.commons.model.IEventManager;
import fr.inria.diverse.event.commons.model.property.CompositeProperty;
import fr.inria.diverse.event.commons.model.property.EventPrecondition;
import fr.inria.diverse.event.commons.model.property.Property;
import fr.inria.diverse.event.commons.model.property.PropertyReference;
import fr.inria.diverse.event.commons.model.property.StateProperty;
import fr.inria.diverse.event.commons.model.property.StepProperty;
import fr.inria.diverse.event.commons.model.property.Stepping;
import fr.inria.diverse.event.commons.model.scenario.ElementProvider;
import fr.inria.diverse.event.commons.model.scenario.Event;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;
import fr.inria.diverse.trace.commons.model.trace.MSE;
import fr.inria.diverse.trace.commons.model.trace.Step;

public class PropertyMonitor implements IEngineAddon, IPropertyMonitor {

	private Resource executedModel;

	private final Map<Property, Boolean> monitoredProperties = new HashMap<>();

	private final Map<Property, List<IPropertyListener>> propertyListeners = new HashMap<>();

	private final Set<MSE> ongoingSteps = new HashSet<>();

	private final Set<MSE> endedSteps = new HashSet<>();

	private final Set<MSE> endingSteps = new HashSet<>();
	
	private IEventManager eventManager;
	
	private boolean eventManagerAvailable = false;
	
	private boolean monitor(Property property) {
		if (property == null) {
			return true;
		}
		boolean result = evaluateProperty(property);
		monitoredProperties.put(property, result);
		return result;
	}
	
	private boolean evaluateProperty(Property property) {
		boolean result = false;
		if (property instanceof StepProperty<?>) {
			result = evaluateStepProperty((StepProperty<?>) property);
		} else if (property instanceof StateProperty) {
			result = evaluateStateProperty((StateProperty<?>) property);
		} else if (property instanceof CompositeProperty<?>) {
			result = evaluateCompositeProperty((CompositeProperty<?>) property);
		} else if (property instanceof EventPrecondition<?>) {
			result = evaluateEventPrecondition((EventPrecondition<?>) property);
		} else {
			result = evaluateProperty(((PropertyReference<?>) property).getReferencedProperty());
		}
		return result;
	}

	private void updateProperties() {
		new HashSet<>(propertyListeners.entrySet()).stream().forEach(e -> {
			final Property p = e.getKey();
			if (monitoredProperties.get(p) != monitor(p)) {
				final boolean newValue = monitoredProperties.get(p);
				new ArrayList<>(e.getValue()).forEach(l -> l.update(newValue));
			}
		});
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine engine, Step<?> stepToExecute) {
		ongoingSteps.add(stepToExecute.getMseoccurrence().getMse());
		endedSteps.addAll(endingSteps);
		endingSteps.clear();
		updateProperties();
	}

	@Override
	public void stepExecuted(IExecutionEngine engine, Step<?> stepExecuted) {
		endedSteps.addAll(endingSteps);
		endingSteps.clear();
		endingSteps.add(stepExecuted.getMseoccurrence().getMse());
		updateProperties();
	}
	
	private boolean evaluateEventPrecondition(EventPrecondition<?> property) {
		if (eventManagerAvailable) {
			final EventInstance eventInstance = createEvent(property.getEvent());
			return eventInstance != null && eventManager.canSendEvent(eventInstance);
		}
		return false;
	}

	private boolean evaluateCompositeProperty(CompositeProperty<?> property) {
		final List<Boolean> list = property.getProperties().stream().map(p -> evaluateProperty(p)).collect(Collectors.toList());
		return list.stream().allMatch(b -> b);
	}

	private boolean evaluateStepProperty(StepProperty<?> property) {
		final EOperation operation = property.getOperation();
		final EObject caller = ElementProviderAspect.resolve(property.getTargetProvider(), executedModel);
		final Stepping stepping = property.getStepping();
		final Predicate<MSE> predicate = mse -> mse.getAction() == operation && mse.getCaller() == caller;
		return stepping == Stepping.ONGOING && ongoingSteps.stream().anyMatch(predicate)
				|| stepping == Stepping.ENDED && endedSteps.stream().anyMatch(predicate)
				|| stepping == Stepping.ENDING && endingSteps.stream().anyMatch(predicate);
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
				if (eClasses.contains(o.eClass()) && StatePropertyAspect.evaluate(property, o)) {
					eventReceivers.add(o);
				}
			}
		} else {
			final EObject target_cast = (EObject) target;
			if (StatePropertyAspect.evaluate(property, target_cast)) {
				eventReceivers.add(target_cast);
			}
		}
		return !eventReceivers.isEmpty();
	}

	@Override
	public void monitorProperty(Property property, IPropertyListener listener) {
		List<IPropertyListener> listeners = propertyListeners.get(property);
		if (listeners == null) {
			listeners = new ArrayList<>();
			propertyListeners.put(property, listeners);
		}
		listeners.add(listener);
		monitoredProperties.put(property, false);
		// Should the property be evaluated immediately?
		if (monitor(property)) {
			listener.update(true);
		}
	}

	@Override
	public void unmonitorProperty(Property property, IPropertyListener listener) {
		List<IPropertyListener> listeners = propertyListeners.get(property);
		if (listeners != null) {
			listeners.remove(listener);
			if (listeners.isEmpty()) {
				propertyListeners.remove(property);
			}
		}
		monitoredProperties.remove(property);
		if (property instanceof CompositeProperty<?>) {
			((CompositeProperty<?>) property).getProperties().forEach(p -> monitoredProperties.remove(p));
		}
	}

	@Override
	public void engineAboutToStart(IExecutionEngine engine) {
		executedModel = engine.getExecutionContext().getResourceModel();
		eventManager = engine.getAddonsTypedBy(IEventManager.class).stream().findFirst().orElse(null);
		eventManagerAvailable = eventManager != null;
	}

	@Override
	public void engineInitialized(IExecutionEngine engine) {
	}

	@Override
	public void engineStarted(IExecutionEngine engine) {
	}

	@Override
	public void engineAboutToStop(IExecutionEngine engine) {
	}

	@Override
	public void engineStopped(IExecutionEngine engine) {
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine engine) {
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine engine, Collection<Step<?>> steps) {
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine engine, Collection<Step<?>> steps) {
	}

	@Override
	public void stepSelected(IExecutionEngine engine, Step<?> selectedStep) {
	}

	@Override
	public void engineStatusChanged(IExecutionEngine engine, RunStatus newStatus) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return Collections.emptyList();
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
				return new EventInstance(originalEvent, parameters);
			}
		}
		return null;
	}
}
