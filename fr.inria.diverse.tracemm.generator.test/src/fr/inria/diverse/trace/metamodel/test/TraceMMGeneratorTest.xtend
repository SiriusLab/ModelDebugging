package fr.inria.diverse.trace.metamodel.test

import ecorext.Ecorext
import java.io.File
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import fr.inria.diverse.tracemm.test.util.EMFUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import fr.inria.diverse.tracemm.test.util.EMFCompareUtil

class TraceMMGeneratorTest {

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

	def  Resource loadModel(String path) {
		val res = rs.createResource(EMFUtil.createFileURI(path))
		res.load(null)
		EcoreUtil.resolveAll(rs) // IMPORTANT
		return res
	}

	@Test
	def void testModel2ExtensionTMMGeneration() {
		genericTest("model2")
	}

	@Test
	def void testAD() {
		genericTest("activitydiagram")
	}
	
	@Test
	def void testFuml() {
		genericTest("fuml","http://www.eclipse.org/uml2/5.0.0/UML")
	}
	
	@Test
	def void testPetriNet(){
		genericTest("petrinet")
	}
	
	def void genericTest(String name) {
		genericTest(name,null)
	}

	def void genericTest(String name, String nsURI) {
		
		println("Testing with input: "+name)

		var EPackage ecore

		if (nsURI == null)
			ecore = loadModel(new File(INPUTS_FOLDER, name + ".ecore").absolutePath).contents.filter(EPackage).get(0)
		else {
			ecore = EPackageRegistryImpl.INSTANCE.getEPackage(nsURI)
		}

		//val Resource mmResource = loadModel(new File(INPUTS_FOLDER, name + ".ecore").absolutePath)
		val Resource ecorextResource = loadModel(new File(INPUTS_FOLDER, name + "ext.xmi").absolutePath)
		val Resource eventsResource = loadModel(new File(INPUTS_FOLDER, name + "events.ecore").absolutePath)

		val ecorext = ecorextResource.contents.get(0) as Ecorext
		val events = eventsResource.contents.get(0) as EPackage
		//val mm = mmResource.contents.get(0) as EPackage

		// Method call: fabriquer l'extension
		val stuff = new TraceMMGenerator(ecorext, events, ecore)
		stuff.computeAllMaterial

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/" + name + "tracemm.ecore"))
			r1.contents.add(stuff.tracemmresult)
			r1.save(null)
		}

		// Oracle: comparison with expected outputs
		val Resource expectedTraceMMResource = loadModel(new File(EXPECTED_FOLDER, name + "tracemm.ecore").absolutePath)
		val expectedTraceMM = expectedTraceMMResource.contents.get(0)
		EMFCompareUtil.assertEqualsEMF("Generated trace mm does not match expected", stuff.tracemmresult,
			expectedTraceMM)
	}

}
