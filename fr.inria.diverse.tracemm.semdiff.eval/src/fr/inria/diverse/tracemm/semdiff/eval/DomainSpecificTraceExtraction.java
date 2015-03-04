package fr.inria.diverse.tracemm.semdiff.eval;

import org.junit.BeforeClass;
import org.junit.Test;

public class DomainSpecificTraceExtraction extends TraceExtraction {

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	@Test
	public void testmodel_2() {
		executeFumlTestmodel(2, true);
	}

	@Test
	public void anonCompany_ExampleB_V1_false_false() {
		executeAnonExampleB(1, false, false, false, true);
	}

	@Test
	public void anonCompany_ExampleB_V1_true_false() {
		executeAnonExampleB(1, true, false, false, true);
	}

	@Test
	public void anonCompany_ExampleB_V2_false_false() {
		executeAnonExampleB(2, false, false, false, true);
	}

	@Test
	public void anonCompany_ExampleB_V2_true_false() {
		executeAnonExampleB(2, true, false, false, true);
	}

}
