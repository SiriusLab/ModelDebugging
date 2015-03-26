package fr.inria.diverse.trace.plugin.generator.test

import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.swt.widgets.Display

class TestTracePluginGenerator {

	static val String root = "platform:/plugin/fr.inria.diverse.trace.plugin.generator.test/model_inputs/"

	@Test
	def void testTracePlugin() {
			
		new GenericTracePluginGenerator(URI.createURI(root + "petrinet.ecore"), URI.createURI(root + "petrinetext.xmi"),
			URI.createURI(root + "petrinetevents.ecore"), "awesomeProject" + new Random().nextInt(100)).generate

		waitForJobs
		
		
	}
	
	 public def void waitForJobs() {
      while (!Job.getJobManager().isIdle())
         delay(1000000);
   }
   
    private def void delay(long waitTimeMillis) {
      val Display display = Display.getCurrent();

      // If this is the UI thread,
      // then process input.

      if (display != null) {
         val long endTimeMillis =
            System.currentTimeMillis() + waitTimeMillis;
         while (System.currentTimeMillis() < endTimeMillis)
         {
            if (!display.readAndDispatch())
               display.sleep();
         }
         display.update();
      }
      // Otherwise, perform a simple sleep.
      else {
         try {
            Thread.sleep(waitTimeMillis);
         }
         catch (InterruptedException e) {
            // Ignored.
         }
      }
   }

}
