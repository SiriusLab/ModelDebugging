package fr.inria.diverse.tracemm.semdiff.eval;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.GenericTraceMatcher;
import fr.inria.diverse.tracemm.semdiff.eval.internal.Util;

public class GenericTraceMatching {

	@Test
	public void test() {
		GenericTraceMatcher matcher = new GenericTraceMatcher();
		boolean match = matcher.match(Util.FUML_GENERIC_TRACE_PATH + "trace1.xmi",
				Util.FUML_GENERIC_TRACE_PATH + "trace1.xmi", null,
				Util.FUML_CONFIGURATION_PATH,
				Util.FUML_MATCH_RULES_ACTION_EXE_ORDERING_PATH);
		assertTrue(match);
	}

}
