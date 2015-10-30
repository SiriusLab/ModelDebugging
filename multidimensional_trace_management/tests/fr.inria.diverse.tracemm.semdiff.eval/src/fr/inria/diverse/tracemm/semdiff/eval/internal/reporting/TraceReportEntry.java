package fr.inria.diverse.tracemm.semdiff.eval.internal.reporting;

public class TraceReportEntry extends ReportEntry {

	private String tracemodel;
	private int statenumber;
	
	public TraceReportEntry(String tracemodel, int statenumber) {
		super();
		this.tracemodel = tracemodel;
		this.statenumber = statenumber;
	}

	public int getStatenumber() {
		return statenumber;
	}

	public String getTracemodel() {
		return tracemodel;
	}
	
	@Override
	public String print() {
		StringBuffer str = new StringBuffer();
			str.append(tracemodel);
			str.append(Report.ENTRY_SEPARATOR);
			str.append(statenumber);
		return str.toString();
	}
}
