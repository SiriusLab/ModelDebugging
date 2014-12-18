package fr.inria.diverse.tracemm.generator.test

import ecorext.Ecorext
import fr.inria.diverse.tracemm.generator.TraceMMGenerator
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil

//import org.modelexecution.xmof.
class TraceMMGeneratorTest {

	static val String SIMPLEST_ECORE_PATH = "model_inputs/simplestmm.ecore";
	static val String SIMPLEST_ECOREXT_PATH = "model_inputs/simplestmmext.xmi";
	static val String SIMPLEST_EVENTS_PATH = "model_inputs/simplestmmevents.ecore";

	static val String CURRENT_BUNDLE = "fr.inria.diverse.tracemm.generator.test"
	static val String SIMPLEST_TMM_EXPECTED_PATH = "expected/yay.xmi"; //TODO

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
	def testSimplestExtensionTMMGeneration() {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		loadModel(SIMPLEST_ECORE_PATH)
		val Resource ecorextResource = loadModel(SIMPLEST_ECOREXT_PATH)
		val Resource eventsResource = loadModel(SIMPLEST_EVENTS_PATH)
 
		//val Resource expectedExtResource = loadModel(SIMPLEST_EXT_EXPECTED_PATH)
		//val Resource expectedEventsResource = loadModel(SIMPLEST_EVENTS_EXPECTED_PATH)
		val ecorext = ecorextResource.contents.get(0) as Ecorext
		val events = eventsResource.contents.get(0) as EPackage

		//val expectedExt = expectedExtResource.contents.get(0)
		//val expectedEvents = expectedEventsResource.contents.get(0)
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
	//assertEqualsEMF("Generated ecorext does not match expected", stuff.mmextensionResult, expectedExt)
	//assertEqualsEMF("Generated events mm does not match expected", stuff.eventsmmResult, expectedEvents)
	}

}
