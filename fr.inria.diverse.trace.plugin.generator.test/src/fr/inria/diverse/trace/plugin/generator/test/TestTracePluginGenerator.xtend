package fr.inria.diverse.trace.plugin.generator.test

import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test

class TestTracePluginGenerator {

	static val String root = "platform:/plugin/fr.inria.diverse.trace.plugin.generator.test/model_inputs/"

	@Test
	def void testTracePlugin() {
		new GenericTracePluginGenerator(URI.createURI(root + "petrinet.ecore"), URI.createURI(root + "petrinetext.xmi"),
			URI.createURI(root + "petrinetevents.ecore"), "awesomeProject" + new Random().nextInt(100)).generate

		// We wait for the eclipse threads to finish
		Thread.sleep(5000)
	}

}
