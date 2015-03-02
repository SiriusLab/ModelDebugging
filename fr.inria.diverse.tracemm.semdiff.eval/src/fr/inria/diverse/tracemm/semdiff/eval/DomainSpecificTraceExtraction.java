package fr.inria.diverse.tracemm.semdiff.eval;

import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.Util;

public class DomainSpecificTraceExtraction {

	@Test
	public void anonCompany_ExampleB_V1_false_false() {
		Util.executeAnonExampleB(1, false, false, false, true);
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_false() {
		Util.executeAnonExampleB(1, true, false, false, true);
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_false() {
		Util.executeAnonExampleB(2, false, false, false, true);
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_false() {
		Util.executeAnonExampleB(2, true, false, false, true);
	}

}
