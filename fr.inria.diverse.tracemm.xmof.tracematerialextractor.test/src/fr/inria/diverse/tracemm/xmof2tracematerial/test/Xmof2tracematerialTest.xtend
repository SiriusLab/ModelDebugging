package fr.inria.diverse.tracemm.xmof2tracematerial.test

import fr.inria.diverse.tracemm.test.util.EMFCompareUtil
import fr.inria.diverse.tracemm.xmof2tracematerial.Xmof2tracematerial
import java.io.File
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil
import java.util.Set
import java.util.HashSet

//import org.modelexecution.xmof.
class Xmof2tracematerialTest {

	static val File INPUTS_FOLDER = new File("model_inputs")
	static val File EXPECTED_FOLDER = new File("model_expected")

	static var boolean saveInFiles = true;

	var ResourceSet rs

	@Before
	def void init() {
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
	def void testModel1() {
		genericTestOperation("model1")
	}

	@Test
	def void testModel2() {
		genericTestOperation("model2")
	}

	@Test
	def void testAD() {
		genericTestOperation("activitydiagram")
	}

	@Test
	def void testFuml() {
		genericTestOperation("fuml", "http://www.eclipse.org/uml2/5.0.0/UML")
	}
	
	@Test
	def void testPetriNet(){
		genericTestOperation("petrinet")
	}

	def void genericTestOperation(String name) {
		genericTestOperation(name, null)
	}

	def void genericTestOperation(String name, String nsURI) {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		var Set<EPackage> ecore
		if (nsURI == null)
			ecore = loadModel(new File(INPUTS_FOLDER, name + ".ecore").absolutePath).contents.filter(EPackage).toSet
		else {
			ecore = new HashSet
			ecore.add(EPackageRegistryImpl.INSTANCE.getEPackage(nsURI))
		}
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
