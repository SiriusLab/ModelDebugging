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

class Tests {

	@Test
	/**
	 * Needs to be run in a workspace with the model!
	 */
	def void test() {
		
		EclipseTestUtil.waitForJobs

		val Job j = new Job("Running the benchmark") {

			override protected run(IProgressMonitor monitor) {

				val URI tfsmmodel = URI.createPlatformResourceURI(
					"/org.gemoc.sample.tfsm.plaink3.single_traffic_light_sample/single_traffic_light.tfsm", true);

				val URI admodel = URI.createPlatformResourceURI("/ad_sequential_test1/test1.ad", true);
				val URI admodel2 = URI.createPlatformResourceURI("/ad_test/test1_big.ad", true);

				val Map<Language, List<URI>> languagesAndModels = newLinkedHashMap(
					Language.TFSM -> #[
						tfsmmodel
					],
					Language.AD -> #[
						admodel, 
						admodel2
					]
				)

				val debuggers = #[
					new SnapshotDebugger(), 
					new DSTraceDebuggerHelper(), 
					new NoTraceDebuggerHelper()
				];

				val Benchmark bench = new Benchmark(languagesAndModels, debuggers, 3,
					new File("/home/ebousse/tmp/bench-debugging"));
				try {
					val Results results = bench.computeAll
					println("\n\n Final Results:\n")
					println(results)

				} catch(Exception exc) {
					exc.printStackTrace
					return new Status(Status.ERROR, "benchmark", "something went wrong");
				}
				return Status.OK_STATUS

			}

		}
		j.schedule();
		EclipseTestUtil.waitForJobsThenWindowClosed

	}

}
