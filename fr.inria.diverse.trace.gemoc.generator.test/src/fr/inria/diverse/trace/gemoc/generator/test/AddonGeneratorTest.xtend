package fr.inria.diverse.trace.gemoc.generator.test

import org.junit.Test
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil
import fr.inria.diverse.trace.gemoc.generator.GenericEngineTraceAddonGenerator
import org.eclipse.emf.common.util.URI
import java.util.Random
import org.junit.After
import org.junit.AfterClass
import org.eclipse.core.resources.IProject

class AddonGeneratorTest {

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
		val gen = new GenericEngineTraceAddonGenerator(URI.createURI(root + name + ".ecore"),
			URI.createURI(root + name + "ext.xmi"), URI.createURI(root + name + "events.ecore"),
			"awesomeProject" + new Random().nextInt(100))
		gen.generateCompleteAddon
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
