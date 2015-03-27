package fr.inria.diverse.trace.plugin.generator.test

import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil

/**
 * Taken from http://www.informit.com/articles/article.aspx?p=1315271&seqNum=8
 */
class TestTracePluginGenerator {

	static val String root = "platform:/plugin/fr.inria.diverse.trace.plugin.generator.test/model_inputs/"

	@Test
	def void testTracePlugin() {

		new GenericTracePluginGenerator(URI.createURI(root + "petrinet.ecore"), URI.createURI(root + "petrinetext.xmi"),
			URI.createURI(root + "petrinetevents.ecore"), "awesomeProject" + new Random().nextInt(100)).generate

		EclipseTestUtil.waitForJobsThenWindowClosed

	}

}
