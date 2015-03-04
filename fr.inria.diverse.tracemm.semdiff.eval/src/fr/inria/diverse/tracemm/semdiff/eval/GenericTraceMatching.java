package fr.inria.diverse.tracemm.semdiff.eval;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.MatchResult;

public class GenericTraceMatching extends TraceMatching {

	@Test
	public void testmodel_2() {
		MatchResult result = matchFumlTestmodel(2, 2, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_false_false() {
		MatchResult result = matchAnonExampleB(1, 2, false, false, false,
				false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_true_false() {
		MatchResult result = matchAnonExampleB(1, 2, true, false, false,
				false);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());
	}

}
