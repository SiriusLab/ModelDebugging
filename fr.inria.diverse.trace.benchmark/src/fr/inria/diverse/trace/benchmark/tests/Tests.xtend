package fr.inria.diverse.trace.benchmark.tests

import fr.inria.diverse.trace.benchmark.Benchmark
import fr.inria.diverse.trace.benchmark.Language
import fr.inria.diverse.trace.benchmark.debuggers.DSTraceDebuggerHelper
import fr.inria.diverse.trace.commons.testutil.EclipseTestUtil
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.junit.Test
import fr.inria.diverse.trace.benchmark.Results
import fr.inria.diverse.trace.benchmark.debuggers.NoTraceDebuggerHelper
import fr.inria.diverse.trace.benchmark.debuggers.SnapshotDebugger
import java.io.File
import java.util.Map
import java.util.List
import java.text.DateFormat
import java.util.Date
import java.text.SimpleDateFormat
import java.io.FileWriter
import java.io.BufferedWriter

class Tests {

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

				val Map<Language, List<URI>> languagesAndModels = newLinkedHashMap(
					// Language.TFSM -> #[
					//	createURI("/org.gemoc.sample.tfsm.plaink3.single_traffic_light_sample/single_traffic_light.tfsm")
					// ],
					Language.AD -> #[
						//createURI("/ad_sequential_test1/test1.ad"),
						//createURI("/ad_test/test1_big.ad"),
						createURI("/ad_sequential_tests/model/xmi/test1.xmi"),
						createURI("/ad_sequential_tests/model/xmi/test2.xmi"),
						createURI("/ad_sequential_tests/model/xmi/test3.xmi"),
						createURI("/ad_sequential_tests/model/xmi/test4.xmi")
						//createURI("/ad_sequential_tests/model/xmi/test5.xmi") // Issue loading the input file
					]
				)

				val debuggers = #[
					new SnapshotDebugger(),
					new DSTraceDebuggerHelper(),
					new NoTraceDebuggerHelper()
				]

				val int nbRetries = 5

				val Benchmark bench = new Benchmark(languagesAndModels, debuggers, nbRetries,
					new File("/home/ebousse/tmp/bench-debugging"));
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
					val File output = new File(
						"/home/ebousse/Documents/Thèse/2015-02 Omniscient debugging of xDSMLs/results/" + fileName)
					val FileWriter fstream = new FileWriter(output);
					val BufferedWriter out = new BufferedWriter(fstream);
					try {
						out.write(results.toString)
					} finally {
						out.close
					}

				} catch(Exception exc) {
					exc.printStackTrace
					println("Major,error, SLEEEPING")
					Thread.sleep(100000000)
					return new Status(Status.ERROR, "benchmark", "something went wrong :'(");
				}
				return Status.OK_STATUS

			}

		}
		j.schedule();
		EclipseTestUtil.waitForJobsThenWindowClosed

	}

}
