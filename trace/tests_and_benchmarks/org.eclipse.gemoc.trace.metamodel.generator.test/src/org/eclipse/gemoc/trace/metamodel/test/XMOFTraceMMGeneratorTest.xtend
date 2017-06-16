package fr.inria.diverse.trace.metamodel.test

import fr.inria.diverse.trace.metamodel.test.TraceMMGeneratorTest
import java.io.File

class XMOFTraceMMGeneratorTest extends TraceMMGeneratorTest {
	
	override protected getMMExtension() {
		return "xmof"
	}
	
	override protected getInputFolder() {
		return new File("model_inputs_xmof")
	}
	
}