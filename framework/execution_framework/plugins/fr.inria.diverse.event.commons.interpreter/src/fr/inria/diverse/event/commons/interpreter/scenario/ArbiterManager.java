package fr.inria.diverse.event.commons.interpreter.scenario;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import fr.inria.diverse.event.commons.interpreter.property.IPropertyListener;
import fr.inria.diverse.event.commons.interpreter.property.IPropertyMonitor;
import fr.inria.diverse.event.commons.model.arbiter.Arbiter;
import fr.inria.diverse.event.commons.model.arbiter.State;
import fr.inria.diverse.event.commons.model.arbiter.TruthValue;
import fr.inria.diverse.event.commons.model.property.Property;

public class ArbiterManager {

	private State<?, ?> currentState;
	private final IPropertyMonitor propertyMonitor;

	public ArbiterManager(IPropertyMonitor propertyMonitor) {
		this.propertyMonitor = propertyMonitor;
	}

	public TruthValue getTruthValue() {
		return currentState.getTruthValue();
	}
	
	private void setupArbiterStatePropertyListeners(Arbiter<?, ?, ?> arbiter, State<?, ?> state) {
		final Map<Property, IPropertyListener> guards = new HashMap<>();
		state.getOutgoingTransitions().forEach(t -> {
			final Property property = t.getGuard();
				IPropertyListener listener = new ArbiterTransitionGuardListener(arbiter, t.getTarget(), property, guards);
				guards.put(property, listener);
		});
		new HashSet<>(guards.keySet()).forEach(p -> {
			IPropertyListener l = guards.get(p);
			if (l != null) {
				propertyMonitor.addListener(p, l);
			}
		});
	}
	
	public void loadArbiter(Arbiter<?, ?, ?> arbiter) {
		currentState = arbiter.getInitialState();
	}
	
	private class ArbiterTransitionGuardListener implements IPropertyListener {

		private final Arbiter<?, ?, ?> arbiter;
		private final State<?, ?> state;
		private final Property property;
		private Map<Property, IPropertyListener> guards = new HashMap<>();

		public ArbiterTransitionGuardListener(Arbiter<?, ?, ?> arbiter, State<?, ?> state, Property property, Map<Property, IPropertyListener> guards) {
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
				propertyMonitor.removeListener(property, this);
				currentState = state;
				//-----------------------
				System.out.println(currentState.getTruthValue());
				//-----------------------
				guards.forEach((p, l) -> propertyMonitor.removeListener(p, l));
				guards.clear();
				// Otherwise we start monitoring the guards of the outgoing
				// transitions.
				setupArbiterStatePropertyListeners(arbiter, state);
			}
		}
	}
}
