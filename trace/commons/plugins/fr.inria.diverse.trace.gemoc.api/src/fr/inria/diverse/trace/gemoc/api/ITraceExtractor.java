package fr.inria.diverse.trace.gemoc.api;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceExtractor {
	
	boolean compareStates(EObject e1, EObject e2, List<Integer> l);
	
	LaunchConfiguration getLaunchConfiguration();
	
	int getNumberOfTraces();
	
	String getStateDescription(int stateIndex);
	
	int getStatesTraceLength();

	StateWrapper getStateWrapper(int stateIndex);
	
	List<StateWrapper> getStateWrappers(int start, int end);

	List<? extends Step> getStepsForStates(int startingState, int endingState);
	
	StepWrapper getStepWrapper(Step step);
	
	String getValueDescription(int traceIndex, int stateIndex);
	
	String getValueLabel(int traceIndex);
	
	int getValuesTraceLength(int traceIndex);
	
	ValueWrapper getValueWrapper(int traceIndex, int stateIndex);
	
	List<ValueWrapper> getValueWrappers(int valueTraceIndex, int start, int end);
	
	void update();
	
	class ValueWrapper {

		public EObject value;
		public int firstStateIndex;
		public int traceIndex;
		public int lastStateIndex;

		public ValueWrapper() {
			value = null;
			firstStateIndex = -1;
			lastStateIndex = -1;
			traceIndex = -1;
		}

		public ValueWrapper(EObject value, int firstStateIndex, int lastStateIndex, int traceIndex) {
			this.value = value;
			this.firstStateIndex = firstStateIndex;
			this.lastStateIndex = lastStateIndex;
			this.traceIndex = traceIndex;
		}
	}
	
	class StateWrapper {
		public EObject state;
		public int stateIndex;
		public boolean breakable;
		
		public StateWrapper() {
			state = null;
			stateIndex = -1;
			breakable = false;
		}

		public StateWrapper(EObject value, int stateIndex, boolean breakable) {
			this.state = value;
			this.stateIndex = stateIndex;
			this.breakable = breakable;
		}
	}
	
	class StepWrapper {
		public Step step = null;
		public int startingIndex = -1;
		public int endingIndex = -1;
		public List<Step> subSteps = new ArrayList<>();

		public StepWrapper(Step value, int startingIndex, int endingIndex, List<Step> subSteps) {
			this.step = value;
			this.startingIndex = startingIndex;
			this.endingIndex = endingIndex;
			this.subSteps.addAll(subSteps);
		}
	}
}
