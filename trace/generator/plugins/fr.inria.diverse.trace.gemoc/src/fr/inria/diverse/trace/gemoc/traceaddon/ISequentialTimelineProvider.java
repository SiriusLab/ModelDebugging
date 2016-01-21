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
	List<StateWrapper> getStatesOrValues(int line, int firstStateIndex, int lastStateIndex);
	
	/**
	 * 
	 * @param stateIndex
	 * @return
	 */
	List<StepEvent> getStepEventsForState(int stateIndex);
	
	class StateWrapper {
		
		public final Object value;
		public final int startIndex;
		public final int endIndex;
		
		public StateWrapper(Object value, int startIndex, int endIndex) {
			this.value = value;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}
		
	}

}
