package fr.inria.diverse.event.commons.interpreter.scenario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.diverse.event.commons.interpreter.property.StatePropertyAspect;
import fr.inria.diverse.event.commons.model.arbiter.Arbiter;
import fr.inria.diverse.event.commons.model.arbiter.State;
import fr.inria.diverse.event.commons.model.arbiter.TruthValue;
import fr.inria.diverse.event.commons.model.property.StateProperty;

public class ArbiterManager {

	private final Resource executedModel;
	private State currentState;

	public ArbiterManager(Arbiter arbiter, Resource executedModel) {
		this.executedModel = executedModel;
		currentState = arbiter.getInitialState();
	}

	public TruthValue getTruthValue() {
		currentState = currentState.getOutgoingTransitions().stream()
				.filter(t -> evaluateStateProperty(t.getGuard()))
				.findFirst().map(t -> t.getTarget())
				.orElse(currentState);
		return currentState.getTruthValue();
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
}
