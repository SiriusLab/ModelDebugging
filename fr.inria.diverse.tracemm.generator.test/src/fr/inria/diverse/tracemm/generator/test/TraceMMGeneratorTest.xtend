package fr.inria.diverse.tracemm.generator.test

import ecorext.Ecorext
import fr.inria.diverse.tracemm.generator.TraceMMGenerator
import fr.inria.diverse.tracemm.test.util.EMFCompareUtil
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil

class TraceMMGeneratorTest {

	static val String MODEL2_ECORE_PATH = "model_inputs/model2.ecore";
	static val String MODEL2_ECOREXT_PATH = "model_inputs/model2ext.xmi";
	static val String MODEL2_EVENTS_PATH = "model_inputs/model2events.ecore";

	static val String MODEL2_TMM_EXPECTED_PATH = "model_expected/model2tracemm.ecore";

	static var boolean saveInFiles = true;

	var ResourceSet rs
 
	@Before
	def init() {
		this.rs = new ResourceSetImpl
		EMFUtil.registerEcoreFactory(rs)
		EMFUtil.registerXMIFactory(rs)
	}

	def Resource loadModel(String path) {
		val res = rs.createResource(EMFUtil.createFileURI(path))
		res.load(null)
		EcoreUtil.resolveAll(rs) // IMPORTANT
		return res
	}

	@Test
	def testModel2ExtensionTMMGeneration() {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		loadModel(MODEL2_ECORE_PATH)
		val Resource ecorextResource = loadModel(MODEL2_ECOREXT_PATH)
		val Resource eventsResource = loadModel(MODEL2_EVENTS_PATH)

		val Resource expectedTraceMMResource = loadModel(MODEL2_TMM_EXPECTED_PATH)
		val ecorext = ecorextResource.contents.get(0) as Ecorext
		val events = eventsResource.contents.get(0) as EPackage

		val expectedTraceMM = expectedTraceMMResource.contents.get(0)

		// Method call: fabriquer l'extension
		val stuff = new TraceMMGenerator(ecorext, events)
		stuff.computeAllMaterial

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/tracemmResult.ecore"))
			r1.contents.add(stuff.tracemmresult)
			r1.save(null)
		}

		// Oracle: comparison with expected outputs
		EMFCompareUtil.assertEqualsEMF("Generated trace mm does not match expected", stuff.tracemmresult, expectedTraceMM)
	}

}
