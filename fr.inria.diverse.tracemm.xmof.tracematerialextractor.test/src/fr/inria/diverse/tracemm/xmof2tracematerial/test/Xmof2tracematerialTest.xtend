package fr.inria.diverse.tracemm.xmof2tracematerial.test

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil


import static fr.inria.diverse.tracemm.xmof2tracematerial.test.util.EMFCompareUtil.*
import fr.inria.diverse.tracemm.xmof2tracematerial.Xmof2tracematerial

//import org.modelexecution.xmof.
class Xmof2tracematerialTest {

	static val String SIMPLEST_ECORE_PATH = "model/simplestmm.ecore";
	static val String SIMPLEST_XMOF_PATH = "model/simplestmm.xmof";
	static val String SIMPLEST_EXT_EXPECTED_PATH = "model/simplestmmext_expected.xmi";
	static val String SIMPLEST_EVENTS_EXPECTED_PATH = "model/simplestmmevents_expected.ecore";

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
	def testSimplestExtensionMMExt() {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		val Resource ecore = loadModel(SIMPLEST_ECORE_PATH)
		val Resource xmof = loadModel(SIMPLEST_XMOF_PATH)
		val Resource expectedExtResource = loadModel(SIMPLEST_EXT_EXPECTED_PATH)
		val Resource expectedEventsResource = loadModel(SIMPLEST_EVENTS_EXPECTED_PATH)
		val expectedExt = expectedExtResource.contents.get(0)
		val expectedEvents = expectedEventsResource.contents.get(0)

		// Method call: fabriquer l'extension
		val stuff = new Xmof2tracematerial(ecore, xmof)
		stuff.computeAllMaterial

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/mmextensionResult.xmi"))
			val Resource r2 = rs.createResource(EMFUtil.createFileURI("tmp/eventsmmResult.ecore"))
			r1.contents.add(stuff.mmextensionResult)
			r2.contents.add(stuff.eventsmmResult)
			r1.save(null)
			r2.save(null)
		}

		// Oracle: comparison with expected outputs
		assertEqualsEMF("Generated ecorext does not match expected",stuff.mmextensionResult, expectedExt)
		assertEqualsEMF("Generated events mm does not match expected",stuff.eventsmmResult, expectedEvents)
	}

}
