package fr.inria.diverse.trace.plugin.generator.test

import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil
import org.junit.After
import org.eclipse.core.resources.IProject
import org.junit.AfterClass

/**
 * Taken from http://www.informit.com/articles/article.aspx?p=1315271&seqNum=8
 */
class TestTracePluginGenerator {

	static val String root = "platform:/plugin/fr.inria.diverse.trace.plugin.generator.test/inputs/"

	@Test
	def void testPetrinet() {
		genericTest("petrinet")
	}

	@Test
	def void testTFSM() {
		genericTest("tfsm")
	}

	var IProject currentProject

	def void genericTest(String name) {
		val gen = new GenericTracePluginGenerator(URI.createURI(root + name + ".ecore"),
			URI.createURI(root + name + "ext.xmi"), URI.createURI(root + name + "events.ecore"),
			"awesomeProject" + new Random().nextInt(100))
		gen.generate
		currentProject = gen.project

	}

	@After
	def void waitOne() {
		EclipseTestUtil.waitForJobs
	}

	@AfterClass
	def static void waitAll() {
		EclipseTestUtil.waitForJobsThenWindowClosed
	}

}
