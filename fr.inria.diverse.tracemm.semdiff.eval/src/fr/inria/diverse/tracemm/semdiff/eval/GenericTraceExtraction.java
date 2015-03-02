package fr.inria.diverse.tracemm.semdiff.eval;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.Util;

public class GenericTraceExtraction {

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}
	
	@Test
	public void anonCompany_ExampleB_V1_false_false() {
		Util.executeAnonExampleB(1, false, false, false, false);
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_false() {
		Util.executeAnonExampleB(1, true, false, false, false);
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_false() {
		Util.executeAnonExampleB(2, false, false, false, false);
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_false() {
		Util.executeAnonExampleB(2, true, false, false, false);
	}
	
}
