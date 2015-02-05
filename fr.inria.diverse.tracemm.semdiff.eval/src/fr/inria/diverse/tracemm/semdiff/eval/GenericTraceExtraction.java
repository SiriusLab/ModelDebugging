package fr.inria.diverse.tracemm.semdiff.eval;

import org.junit.Test;

import fr.inria.diverse.tracemm.semdiff.eval.internal.Util;
import fr.inria.diverse.tracemm.semdiff.eval.internal.ModelExecutor;

public class GenericTraceExtraction {

	@Test
	public void test() {
		ModelExecutor executor = new ModelExecutor();
		executor.execute(
				Util.FUML_TESTMODEL_PATH,
				Util.FUML_TESTMODEL_PARAMETERDEFINITION_PATH + "test2parameter.xmi",
				Util.FUML_METMODEL_PATH,
				Util.FUML_CONFIGURATION_PATH,
				Util.FUML_GENERIC_TRACE_PATH + "trace1.xmi",
				Util.FUML_TYPE_LIBRARY_PATH, 
				Util.FUML_BEHAVIOR_LIBRARY_PATH);
	}

}
