package org.eclipse.gemoc.event.commons.interpreter.scenario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.gemoc.event.commons.interpreter.property.IPropertyListener;
import org.eclipse.gemoc.event.commons.interpreter.property.IPropertyMonitor;
import org.eclipse.gemoc.event.commons.model.scenario.Arbiter;
import org.eclipse.gemoc.event.commons.model.scenario.ArbiterState;
import org.eclipse.gemoc.event.commons.model.scenario.TruthValue;
import org.eclipse.gemoc.event.commons.model.property.Property;

public class ArbiterManager {

	private ArbiterState<?, ?> currentState;
	private final IPropertyMonitor propertyMonitor;

	public ArbiterManager(IPropertyMonitor propertyMonitor) {
		this.propertyMonitor = propertyMonitor;
	}

	public TruthValue getTruthValue() {
		return currentState.getTruthValue();
	}
	
	private void setupArbiterStatePropertyListeners(Arbiter<?, ?, ?> arbiter, ArbiterState<?, ?> state) {
		final Map<Property, IPropertyListener> guards = new HashMap<>();
		state.getOutgoingTransitions().forEach(t -> {
			final Property property = t.getGuard();
				IPropertyListener listener = new ArbiterTransitionGuardListener(arbiter, t.getTarget(), property, guards);
				guards.put(property, listener);
		});
		new HashSet<>(guards.keySet()).forEach(p -> {
			IPropertyListener l = guards.get(p);
			if (l != null) {
				propertyMonitor.monitorProperty(p, l);
			}
		});
	}
	
	public void loadArbiter(Arbiter<?, ?, ?> arbiter) {
		currentState = arbiter.getInitialState();
		setupArbiterStatePropertyListeners(arbiter, currentState);
	}
	
	private class ArbiterTransitionGuardListener implements IPropertyListener {

		private final Arbiter<?, ?, ?> arbiter;
		private final ArbiterState<?, ?> state;
		private final Property property;
		private Map<Property, IPropertyListener> guards = new HashMap<>();

		public ArbiterTransitionGuardListener(Arbiter<?, ?, ?> arbiter, ArbiterState<?, ?> state, Property property, Map<Property, IPropertyListener> guards) {
			this.arbiter = arbiter;
			this.state = state;
			this.property = property;
			this.guards = guards;
		}

		@Override
		public void update(boolean propertyValue) {
			if (propertyValue) {
				// We stop monitoring the current guard as well as
				// the guards of the other outgoing transitions of
				// the previous state of the FSM.
				propertyMonitor.unmonitorProperty(property, this);
				currentState = state;
				guards.forEach((p, l) -> propertyMonitor.unmonitorProperty(p, l));
				guards.clear();
				if (arbiter.getAcceptingStates().contains(currentState)) {
					// If the current state is an accepting state,
					// we print the final verdict.
					System.out.println("Arbiter has reached a verdict: " + currentState.getTruthValue());
				} else {
					// Otherwise we start monitoring the guards of the outgoing
					// transitions.
					setupArbiterStatePropertyListeners(arbiter, state);
				}
			}
		}
	}
}
