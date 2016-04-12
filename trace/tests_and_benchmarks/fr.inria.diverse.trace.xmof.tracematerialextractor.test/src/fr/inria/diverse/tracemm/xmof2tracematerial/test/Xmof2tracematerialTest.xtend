package fr.inria.diverse.tracemm.xmof2tracematerial.test

import java.io.File
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.vm.util.EMFUtil
import static org.junit.Assert.*;
import org.eclipse.emf.ecore.util.Diagnostician
import org.eclipse.emf.common.util.Diagnostic
import fr.inria.diverse.tracemm.xmof2tracematerial.XmofExecutionExtensionExtractor

class Xmof2tracematerialTest {

	private static val File INPUTS_FOLDER = new File("model_inputs")

	static var boolean saveInFiles = true;

	private var ResourceSet rs

	@Before
	def void init() {
		this.rs = new ResourceSetImpl
		EMFUtil.registerEcoreFactory(rs)
		EMFUtil.registerXMIFactory(rs)
	}

	def Resource loadModel(URI path) {
		val res = rs.createResource(path)
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

		genericTestOperation("fuml", EMFUtil.createPlatformPluginURI("org.modelexecution.xmof.examples/fuml/fuml.xmof"),
			"http://www.eclipse.org/uml2/5.0.0/UML")
	}

	@Test
	def void testPetriNet() {
		genericTestOperation("petrinet")
	}

	private def void genericTestOperation(String name) {
		genericTestOperation(name, null, null)
	}

	private def void genericTestOperation(String name, URI xmofURI, String ecore_nsURI) {

		// Contexte: charger petit ecore et charger petit xmof qui étend le ecore (et charger expected)
		var Set<EPackage> ecore
		if (ecore_nsURI == null)
			ecore = loadModel(EMFUtil.createFileURI(new File(INPUTS_FOLDER, name + ".ecore").absolutePath)).contents.
				filter(EPackage).toSet
		else {
			ecore = new HashSet
			ecore.add(EPackageRegistryImpl.INSTANCE.getEPackage(ecore_nsURI))
		}

		var Resource xmof
		if (xmofURI != null)
			xmof = loadModel(xmofURI)
		else
			xmof = loadModel(EMFUtil.createFileURI(new File(INPUTS_FOLDER, name + ".xmof").absolutePath))

		val xmofPackages = xmof.contents.filter(EPackage).toSet

		genericTestOperation2(name, xmofPackages, ecore)

	}

	private def void genericTestOperation2(String name, Set<EPackage> xmof, Set<EPackage> ecore) {

		// Method call: fabriquer l'extension
		val stuff = new XmofExecutionExtensionExtractor(ecore, xmof, false)
		stuff.computeMMExtension

		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/" + name + "ext.xmi"))
			r1.contents.add(stuff.mmextensionResult)
			r1.save(null)
		}

		// Basic oracle: non empty models && validation
		assertTrue(!stuff.mmextensionResult.classesExtensions.empty || !stuff.mmextensionResult.newPackages.empty ||
			!stuff.mmextensionResult.rules.empty)
		val results = Diagnostician.INSTANCE.validate(stuff.mmextensionResult);
		val error = results.children.findFirst[r|r.severity == Diagnostic.ERROR]
		assertFalse("There is at least one error in the generated ecore model: " + error, error != null)

	}

}
