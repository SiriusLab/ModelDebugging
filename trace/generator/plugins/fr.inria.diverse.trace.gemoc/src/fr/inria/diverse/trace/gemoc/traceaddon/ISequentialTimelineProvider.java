package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.List;

import fr.inria.diverse.trace.api.IStep.StepEvent;
import fr.obeo.timeline.view.ITimelineProvider;

public interface ISequentialTimelineProvider extends ITimelineProvider {
	
	List<StateWrapper> getStatesOrValues(int branch);
	
	List<List<StepEvent>> getStepEvents();
	
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
