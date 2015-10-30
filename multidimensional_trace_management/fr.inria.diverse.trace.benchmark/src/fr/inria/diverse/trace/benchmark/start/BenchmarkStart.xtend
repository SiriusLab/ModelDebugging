package fr.inria.diverse.trace.benchmark.start

import fr.inria.diverse.trace.benchmark.Benchmark
import fr.inria.diverse.trace.benchmark.Language
import fr.inria.diverse.trace.benchmark.Results
import fr.inria.diverse.trace.benchmark.debuggers.DSTraceDebuggerHelper
import fr.inria.diverse.trace.benchmark.debuggers.NoTraceDebuggerHelper
import fr.inria.diverse.trace.benchmark.debuggers.SnapshotDebugger
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.List
import java.util.Map
import org.eclipse.core.resources.IWorkspace
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.junit.Test

class BenchmarkStart {
	
	
	private val String heapDumpFolder = "/home/ebousse/tmp/bench-debugging"
	private val String outputFolder = "/home/ebousse/Documents/Thèse/2015-02 Omniscient debugging of xDSMLs/results/"

	private def URI createURI(String s) {
		return URI.createPlatformResourceURI(s, true);
	}

	@Test
	/**
	 * Needs to be run in a workspace with the model!
	 */
	def void test() {

		EclipseTestUtil.waitForJobs

		val Job j = new Job("Running the benchmark") {

			override protected run(IProgressMonitor monitor) {

				val IWorkspace workspace = ResourcesPlugin.getWorkspace();
				val project = workspace.root.getProjects().get(0);
				val folder = project.getFolder("model")
				val List<URI> modelsList = new ArrayList();
				for (m : folder.members) {
					val String fullPath = m.fullPath.toString
					if(fullPath.endsWith("xmi"))
						modelsList.add(createURI(fullPath))
				}

				val Map<Language, List<URI>> languagesAndModels = newLinkedHashMap(
					Language.AD -> modelsList
				)

				val debuggers = #[
					new SnapshotDebugger(),
					new DSTraceDebuggerHelper(),
					new NoTraceDebuggerHelper()
				]

				val int nbRetries = 4

				val Benchmark bench = new Benchmark(languagesAndModels, debuggers, nbRetries,
					new File(heapDumpFolder));
				try {

					// Executing the benchmark
					val Results results = bench.computeAll

					// Printing results
					println("\n\n Final Results:\n")
					println(results)

					// Writing them in a file
					val DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
					val Date date = new Date();
					val String dateString = dateFormat.format(date);
					val String fileName = dateString + "_benchmarkResults.csv"
					val File output = new File(outputFolder,fileName)
					val FileWriter fstream = new FileWriter(output);
					val BufferedWriter out = new BufferedWriter(fstream);
					try {
						out.write(results.toString)
					} finally {
						out.close
					}

				} catch(Exception exc) {
					exc.printStackTrace
					//println("Major,error, SLEEEPING")
					//Thread.sleep(100000000)
					return new Status(Status.ERROR, "benchmark", "something went wrong :'(");
				}
				return Status.OK_STATUS

			}

		}
		j.schedule();
		EclipseTestUtil.waitForJobsThenWindowClosed

	}

}
