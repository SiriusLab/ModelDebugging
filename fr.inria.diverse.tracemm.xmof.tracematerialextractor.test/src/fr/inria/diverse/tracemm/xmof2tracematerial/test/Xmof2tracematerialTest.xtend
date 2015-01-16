package fr.inria.diverse.tracemm.xmof2tracematerial.test

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil


import fr.inria.diverse.tracemm.xmof2tracematerial.Xmof2tracematerial
import fr.inria.diverse.tracemm.test.util.EMFCompareUtil

//import org.modelexecution.xmof.
class Xmof2tracematerialTest {

	static val String MODEL1_ECORE_PATH = "model_inputs/model1.ecore";
	static val String MODEL1_XMOF_PATH = "model_inputs/model1.xmof";
	static val String MODEL1_EXT_EXPECTED_PATH = "model_expected/model1ext.xmi";
	static val String MODEL1_EVENTS_EXPECTED_PATH = "model_expected/model1events.ecore";

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
	def testModel1() {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		val Resource ecore = loadModel(MODEL1_ECORE_PATH)
		val Resource xmof = loadModel(MODEL1_XMOF_PATH)
		val Resource expectedExtResource = loadModel(MODEL1_EXT_EXPECTED_PATH)
		val Resource expectedEventsResource = loadModel(MODEL1_EVENTS_EXPECTED_PATH)
		val expectedExt = expectedExtResource.contents.get(0)
		val expectedEvents = expectedEventsResource.contents.get(0)

		// Method call: fabriquer l'extension
		val stuff = new Xmof2tracematerial(ecore, xmof)
		stuff.computeAllMaterial

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/model1ext.xmi"))
			val Resource r2 = rs.createResource(EMFUtil.createFileURI("tmp/model1events.ecore"))
			r1.contents.add(stuff.mmextensionResult)
			r2.contents.add(stuff.eventsmmResult)
			r1.save(null)
			r2.save(null)
		}

		// Oracle: comparison with expected outputs
		EMFCompareUtil.assertEqualsEMF("Generated ecorext does not match expected",stuff.mmextensionResult, expectedExt)
		EMFCompareUtil.assertEqualsEMF("Generated events mm does not match expected",stuff.eventsmmResult, expectedEvents)
	}

}
