package fr.inria.diverse.tracemm.semdiff.eval;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.GenericTraceMatcher;
import fr.inria.diverse.tracemm.semdiff.eval.internal.MatchResult;
import fr.inria.diverse.tracemm.semdiff.eval.internal.Util;

public class GenericTraceMatching {

	@Test
	public void test() {
		GenericTraceMatcher matcher = new GenericTraceMatcher();
		boolean match = matcher.match(Util.FUML_GENERIC_TRACE_FOLDER
				+ "trace1.xmi", Util.FUML_GENERIC_TRACE_FOLDER + "trace1.xmi",
				null, Util.FUML_CONFIGURATION_PATH,
				Util.FUML_GENERIC_MATCH_RULES_PATH);
		assertTrue(matcher.matchedWithoutErrors());
		assertTrue(match);
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_false_false() {
		MatchResult result = Util.matchAnonExampleB(1, 2, false, false, false, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());		
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_true_false() {
		MatchResult result = Util.matchAnonExampleB(1, 2, true, false, false, false);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());		
	}
	
}
