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
import java.io.File

//import org.modelexecution.xmof.
class Xmof2tracematerialTest {

	static val File INPUTS_FOLDER = new File("model_inputs")
	static val File EXPECTED_FOLDER = new File("model_expected")

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
		genericTestOperation("model1")
	}

	@Test
	def testModel2() {
		genericTestOperation("model2")
	}
	
		@Test
	def testAD() {
		genericTestOperation("activitydiagram")
	}

	def genericTestOperation(String name) {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		val Resource ecore = loadModel(new File(INPUTS_FOLDER, name + ".ecore").absolutePath)
		val Resource xmof = loadModel(new File(INPUTS_FOLDER, name + ".xmof").absolutePath)

		// Method call: fabriquer l'extension
		val stuff = new Xmof2tracematerial(ecore, xmof)
		stuff.computeAllMaterial

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/" + name + "ext.xmi"))
			val Resource r2 = rs.createResource(EMFUtil.createFileURI("tmp/" + name + "events.ecore"))
			r1.contents.add(stuff.mmextensionResult)
			r2.contents.add(stuff.eventsmmResult)
			r1.save(null)
			r2.save(null)
		}

		// Oracle: comparison with expected outputs
		val Resource expectedExtResource = loadModel(new File(EXPECTED_FOLDER, name + "ext.xmi").absolutePath)
		val Resource expectedEventsResource = loadModel(new File(EXPECTED_FOLDER, name + "events.ecore").absolutePath)
		val expectedExt = expectedExtResource.contents.get(0)
		val expectedEvents = expectedEventsResource.contents.get(0)
		EMFCompareUtil.assertEqualsEMF("Generated ecorext does not match expected", stuff.mmextensionResult, expectedExt)
		EMFCompareUtil.assertEqualsEMF("Generated events mm does not match expected", stuff.eventsmmResult,
			expectedEvents)
	}

}
