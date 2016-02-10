package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.List;

import fr.inria.diverse.trace.api.IStep.StepEvent;
import fr.obeo.timeline.view.ITimelineProvider;

public interface ISequentialTimelineProvider extends ITimelineProvider {

	/**
	 * 
	 * @param line
	 * @param startStateIndex
	 * @param endStateIndex
	 * @return
	 */
	List<StateWrapper> getStatesOrValues(int line, int startStateIndex, int endStateIndex);
	
	/**
	 * 
	 * @param stateIndex
	 * @return
	 */
	List<StepEvent> getStepEventsForState(int stateIndex);
	
	class StateWrapper {
		
		public Object value;
		public int stateIndex;
		public int traceIndex;
		public int length;
		
		public StateWrapper() {
			value = null;
			stateIndex = -1;
			traceIndex = -1;
			length = -1;
		}
		
		public StateWrapper(Object value, int stateIndex, int traceIndex, int length) {
			this.value = value;
			this.stateIndex = stateIndex;
			this.traceIndex = traceIndex;
			this.length = length;
		}
		
	}

}
