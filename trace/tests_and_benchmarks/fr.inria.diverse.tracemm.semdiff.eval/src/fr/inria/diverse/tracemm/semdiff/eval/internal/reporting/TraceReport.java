package fr.inria.diverse.tracemm.semdiff.eval.internal.reporting;

public class TraceReport extends Report {

	private static final String TRACE_REPORT_FOLDER = "/trace/";
	private static final String GENERIC_TRACE_REPORT_FOLDER = TRACE_REPORT_FOLDER
			+ "/trace_generic/";
	private static final String DOMAINSPECIFIC_TRACE_REPORT_FOLDER = TRACE_REPORT_FOLDER
			+ "/trace_domainspecific/";

	private boolean domainSpecificMatching = false;

	public void setDomainSpecific() {
		this.domainSpecificMatching = true;
	}

	@Override
	protected String getReportFolder() {
		return REPORT_FOLDER
				+ (domainSpecificMatching ? DOMAINSPECIFIC_TRACE_REPORT_FOLDER
						: GENERIC_TRACE_REPORT_FOLDER);
	}

	@Override
	protected String printHeader() {
		return "trace" + ENTRY_SEPARATOR + "#states";
	}

}
