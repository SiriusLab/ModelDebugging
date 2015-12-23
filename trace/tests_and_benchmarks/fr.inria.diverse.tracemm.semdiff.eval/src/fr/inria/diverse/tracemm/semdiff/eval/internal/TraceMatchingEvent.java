package fr.inria.diverse.tracemm.semdiff.eval.internal;

public class TraceMatchingEvent {

	private long timepoint = -1;
	private EventType type;
	
	public TraceMatchingEvent(long timepoint, EventType type) {
		this.timepoint = timepoint;
		this.type = type;
	}
	
	public long getTimepoint() {
		return timepoint;
	}
	
	public EventType getType() {
		return type;
	}
	
	public enum EventType {
		INITIALIZATION_START,
		INITIALIZATION_END,
		MATCHING_START,
		MATCHING_END
	}
}
