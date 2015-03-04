package fr.inria.diverse.tracemm.semdiff.eval.internal;

public class MatchingReportEntry {

	private String leftTracemodel;
	private String rightTracemodel;
	private long matchingTime = -1;
	
	public MatchingReportEntry(String leftTracemodel, String rightTracemodel,
			long executionTime) {
		super();
		this.leftTracemodel = leftTracemodel;
		this.rightTracemodel = rightTracemodel;
		this.matchingTime = executionTime;
	}
	
	public String getLeftTracemodel() {
		return leftTracemodel;
	}
	
	public String getRightTracemodel() {
		return rightTracemodel;
	}
	
	public long getMatchingTime() {
		return matchingTime;
	}
}
