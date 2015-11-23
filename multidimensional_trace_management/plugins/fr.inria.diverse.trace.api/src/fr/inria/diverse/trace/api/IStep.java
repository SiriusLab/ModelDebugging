package fr.inria.diverse.trace.api;

import java.util.Map;

public interface IStep {

	public static class StepEvent {
		public final IStep step;
		public final boolean start;
	
		public StepEvent(fr.inria.diverse.trace.api.IStep step, boolean start) {
			this.step = step;
			this.start = start;
		}
	}

	Map<String, Object> getParameters();

	String getContainingClassName();
	
	String getOperationName();

	void addParameter(String name, Object value);

	int getEndingIndex();
	
	int getStartingIndex();
	
	IStep getParentStep();
	
}
