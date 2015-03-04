package fr.inria.diverse.tracemm.semdiff.eval;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import fr.inria.diverse.tracemm.semdiff.eval.internal.MatchResult;
import fr.inria.diverse.tracemm.semdiff.eval.internal.MatchingReport;
import fr.inria.diverse.tracemm.semdiff.eval.internal.MatchingReportEntry;
import fr.inria.diverse.tracemm.semdiff.eval.internal.TraceMatcher;
import fr.inria.diverse.tracemm.semdiff.eval.internal.TraceMatchingEvent;
import fr.inria.diverse.tracemm.semdiff.eval.internal.TraceMatchingEvent.EventType;
import fr.inria.diverse.tracemm.semdiff.eval.internal.TraceMatchingListener;

public abstract class TraceMatching extends Evaluation implements
		TraceMatchingListener {

	private static final String ITERATIONS_PROGRAM_ARGUMENT = "iterations";

	private static MatchingReport report;
	
	private TraceMatchingEvent previousStartEvent = null;
	private String currentLeftPath = null;
	private String currentRightPath = null;
	
	@BeforeClass
	public static void initializeReport() {
		report = new MatchingReport();
	}

	@AfterClass
	public static void printReport() {
		report.printReportToFile();
	}
	
	@Override
	public void notify(TraceMatchingEvent event) {
		if (event.getType() == EventType.MATCHING_START) {
			previousStartEvent = event;
		} else if (event.getType() == EventType.MATCHING_END) {
			long start = previousStartEvent.getTimepoint();
			long end = event.getTimepoint();
			report.addMatchingReportEntry(new MatchingReportEntry(currentLeftPath, currentRightPath, end-start));
			previousStartEvent = null;
		}
	}

	protected MatchResult matchFumlTestmodel(int modelNumber1,
			int modelNumber2, boolean domainSpecific) {
		String leftTracemodelPath = deriveFumlTestmodelTracemodelPath(
				modelNumber1, domainSpecific);
		String rightTracemodelPath = deriveFumlTestmodelTracemodelPath(
				modelNumber2, domainSpecific);
		MatchResult matchResult = matchFumlTraces(leftTracemodelPath,
				rightTracemodelPath, domainSpecific);
		return matchResult;
	}

	protected MatchResult matchAnonExampleB(int version1, int version2,
			boolean exists, boolean found, boolean acc, boolean domainSpecific) {
		String leftTracemodePath = deriveAnonExampleTracemodelPath(version1,
				exists, found, acc, domainSpecific);
		String rightTracemodelPath = deriveAnonExampleTracemodelPath(version2,
				exists, found, acc, domainSpecific);
		MatchResult matchResult = matchFumlTraces(leftTracemodePath,
				rightTracemodelPath, domainSpecific);
		return matchResult;
	}

	private MatchResult matchFumlTraces(String leftTracemodelPath,
			String rightTracemodelPath, boolean domainSpecific) {
		setTracemodelPaths(leftTracemodelPath, rightTracemodelPath);
		MatchResult matchResult = null;
		for (int i = 0; i < getIterationNumber(); ++i) {
			TraceMatcher matcher = setupTraceMatcher();
			boolean match = matcher.match(leftTracemodelPath,
					rightTracemodelPath, FUML_METMODEL_PATH,
					FUML_CONFIGURATION_PATH,
					getFumlTracemetamodelPath(domainSpecific),
					getFumlMatchrules(domainSpecific));
			matchResult = updateMatchResult(matchResult, match,
					matcher.matchedWithoutErrors());
		}
		unsetTracemodelPaths();
		return matchResult;
	}

	private int getIterationNumber() {
		int interations = 1;
		String loopProgramArgument = System.getProperty(ITERATIONS_PROGRAM_ARGUMENT);
		if(loopProgramArgument != null) {
			interations = Integer.parseInt(loopProgramArgument);
		}
		return interations;
	}

	private void setTracemodelPaths(String leftTracemodelPath, String rightTracemodelPath) {
		currentLeftPath = leftTracemodelPath;
		currentRightPath = rightTracemodelPath;
	}
	
	private void unsetTracemodelPaths() {
		currentLeftPath = null;
		currentRightPath = null;
	}

	private TraceMatcher setupTraceMatcher() {
		TraceMatcher matcher = new TraceMatcher();
		matcher.registerListener(this);
		return matcher;
	}

	private MatchResult updateMatchResult(MatchResult matchResult,
			boolean matches, boolean matchedWithoutErrors) {
		MatchResult result = null;
		if (matchResult == null) {
			result = new MatchResult(matches, matchedWithoutErrors);
		} else {
			result = matchResult;
			if (result.matches() != matches) {
				result.setMatches(false);
				result.setMatchingInconclusive();
			}
			if (result.matchedWithoutErrors() != matchedWithoutErrors) {
				result.setMatchedWithoutErrors(false);
			}
		}
		return result;
	}
}
