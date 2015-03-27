package fr.inria.diverse.trace.gemoc.generator.test

import org.junit.Test
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil
import fr.inria.diverse.trace.gemoc.generator.GenericEngineTraceAddonGenerator
import org.eclipse.emf.common.util.URI
import java.util.Random

class AddonGeneratorTest {

	static val String root = "platform:/plugin/fr.inria.diverse.trace.plugin.generator.test/model_inputs/"

	@Test
	def void test() {

		new GenericEngineTraceAddonGenerator(URI.createURI(root + "petrinet.ecore"),
			URI.createURI(root + "petrinetext.xmi"), URI.createURI(root + "petrinetevents.ecore"),
			"awesomeProject" + new Random().nextInt(100)).generateCompleteAddon

		EclipseTestUtil.waitForJobsThenWindowClosed

	}
}
