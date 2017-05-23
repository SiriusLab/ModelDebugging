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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.event.commons.model.property.CompositeProperty;
import fr.inria.diverse.event.commons.model.property.Property;
import fr.inria.diverse.event.commons.model.property.StateProperty;
import fr.inria.diverse.event.commons.model.property.StepProperty;
import fr.inria.diverse.event.commons.model.property.Stepping;
import fr.inria.diverse.trace.commons.model.trace.Step;

public class PropertyMonitor implements IEngineAddon, IPropertyMonitor {

	private Resource executedModel;

	private final Map<Property, Boolean> monitoredProperties = new HashMap<>();

	private final Map<Property, List<IPropertyListener>> propertyListeners = new HashMap<>();

	private final Set<EOperation> ongoingSteps = new HashSet<>();

	private final Set<EOperation> endedSteps = new HashSet<>();

	private final Set<EOperation> endingSteps = new HashSet<>();

	private boolean monitor(Property property) {
		if (property == null) {
			return true;
		}
		boolean result = false;
		if (property instanceof StepProperty) {
			result = evaluateStepProperty((StepProperty) property);
		} else {
			result = evaluateStateProperty((StateProperty<?>) property);
		}
		monitoredProperties.put(property, result);
		return result;
	}

	private void updateProperties() {
		propertyListeners.entrySet().stream().forEach(e -> {
			final Property p = e.getKey();
			if (monitoredProperties.get(p) != monitor(p)) {
				final boolean newValue = monitoredProperties.get(p);
				e.getValue().forEach(l -> l.update(newValue));
			}
		});
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine engine, Step<?> stepToExecute) {
		final EOperation operation = stepToExecute.getMseoccurrence().getMse().getAction();
		ongoingSteps.add(operation);
		endedSteps.addAll(endingSteps);
		endingSteps.clear();
		updateProperties();
	}

	@Override
	public void stepExecuted(IExecutionEngine engine, Step<?> stepExecuted) {
		final EOperation operation = stepExecuted.getMseoccurrence().getMse().getAction();
		endedSteps.addAll(endingSteps);
		endingSteps.clear();
		endingSteps.add(operation);
		updateProperties();
	}

	private boolean evaluateCompositeProperty(CompositeProperty property) {
		return property.getProperties().stream().map(p -> monitor(p)).allMatch(b -> b);
	}

	private boolean evaluateStepProperty(StepProperty property) {
		final EOperation operation = property.getOperation();
		final Stepping stepping = property.getStepping();
		return ongoingSteps.contains(operation) && stepping == Stepping.ONGOING
				|| endedSteps.contains(operation) && stepping == Stepping.ENDED
				|| endingSteps.contains(operation) && stepping == Stepping.ENDING;
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
	public void addListener(Property property, IPropertyListener listener) {
		List<IPropertyListener> listeners = propertyListeners.get(property);
		if (listeners == null) {
			listeners = new ArrayList<>();
			propertyListeners.put(property, listeners);
		}
		listeners.add(listener);
		monitoredProperties.put(property, false);
	}

	@Override
	public void removeListener(Property property, IPropertyListener listener) {
		List<IPropertyListener> listeners = propertyListeners.get(property);
		if (listeners != null) {
			listeners.remove(listener);
			if (listeners.isEmpty()) {
				propertyListeners.remove(property);
			}
		}
		monitoredProperties.remove(property);
		if (property instanceof CompositeProperty) {
			((CompositeProperty) property).getProperties().forEach(p -> monitoredProperties.remove(p));
		}
	}

	@Override
	public void engineAboutToStart(IExecutionEngine engine) {
		executedModel = engine.getExecutionContext().getResourceModel();
	}

	@Override
	public void engineInitialized(IExecutionEngine executionEngine) {
	}

	@Override
	public void engineStarted(IExecutionEngine executionEngine) {
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

	@SuppressWarnings("rawtypes")
	@Override
	public void aboutToSelectStep(IExecutionEngine engine, Collection<Step> steps) {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void proposedStepsChanged(IExecutionEngine engine, Collection<Step> steps) {
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
}
