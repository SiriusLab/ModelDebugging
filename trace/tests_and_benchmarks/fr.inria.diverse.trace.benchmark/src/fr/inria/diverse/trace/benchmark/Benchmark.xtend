package fr.inria.diverse.trace.benchmark

import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper
import java.io.File
import java.io.IOException
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.core.runtime.CoreException
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.gemoc.execution.engine.commons.EngineContextException

/**
 * We want one line per tuple <language,model,debugger>
 * Each line has:
 * language,model,debugger,model_size,nbMut,timeStep,traceSize,traceMemoryFootprint,meanJumpTime,allJumpTimes
 */
class Benchmark {

	private val Map<Language, List<URI>> languagesAndModels
	private val List<? extends IDebuggerHelper> debuggers
	private val int nbRetries
	private val File dumpFolder
	private val waitTime = 500

	/**
	 * TODO un set de models par language!
	 */
	new(Map<Language, List<URI>> languagesAndModels, List<? extends IDebuggerHelper> debuggers, int nbRetries,
		File dumpFolder) {
		this.languagesAndModels = languagesAndModels
		this.debuggers = debuggers
		this.nbRetries = nbRetries
		this.dumpFolder = dumpFolder
	}

	def Results computeAll() throws CoreException, EngineContextException , IOException {

		println("Starting the benchmark...")

		// First variable here to be filled and thrown away
		var Results results = new Results()

		val nbWarmup = 1

		for (int w : 0 .. nbWarmup) {

			if(w == nbWarmup) {

				// This one is the real being used
				results = new Results()
			}
			for (l : languagesAndModels.keySet) {
				println("Language " + l.languageName)
				for (debugger : debuggers) {
					println("Debugger " + debugger.debuggerName)
					for (model : languagesAndModels.get(l)) {
						debugger.init();
						println("Model " + model.lastSegment)
						val Set<Result> toMerge = new HashSet<Result>
						for (int i : 1 .. nbRetries) {
							println("Try " + i)

							val Result result = new Result()

							// General information
							result.languageName = l.languageName
							result.debuggerName = debugger.debuggerName
							result.modelName = model.lastSegment
							result.nbRetries = nbRetries

							// Preparing engine parameters
							val EngineHelper engine = new EngineHelper();
							engine.removeStoppedEngines();

							// Creating gemoc engine for the execution (up until first model state created...?)
							println("Preparing engine...")
							Thread.sleep(waitTime)
							val long setUpBegin = System.nanoTime
							engine.prepareEngine(model, debugger, l);
							val long setUpEnd = System.nanoTime
							result.timeInit = setUpEnd - setUpBegin

							// Information about the executed model
							println("Getting information about model...")
							result.modelNbElements = computeModelSize(engine.model) // number of eobjects
							result.modelNbMutFields = computeNumberMutableElements(engine.model) // sum of : (number of instances) times (number of mutable properties in class)

							// Executing the model entirely
							println("Execution the model...")
							Thread.sleep(waitTime)
							val long completeExeBegin = System.nanoTime
							engine.execute()
							val long completeExeEnd = System.nanoTime
							result.timeExe = completeExeEnd - completeExeBegin

							// Retrieving information about the created trace
							println("Getting information about the trace...")
							val int traceSize = debugger.getTraceSize();
							result.traceNbStates = traceSize

							// We don't capture memory during warmups
							if(w == nbWarmup)
								result.traceMemoryFootprint = debugger.getTraceMemoryFootprint(l, dumpFolder, traceSize)

							println("... done")

							// Doing the jumps (or simply retrieving times that correspond)
							println("Doing the jumps...")
							if(debugger.canJump()) {

								// Mean jump time (hopping into all states, in a random order)
								println("Mean jump time (hopping into all states, in a random order)")
								val allJumps = (1 .. traceSize - 1).toList
								Collections.shuffle(allJumps)
								debugger.jump(0);
								Thread.sleep(waitTime)
								val long jumpAllBegin = System.nanoTime
								for (toJump : allJumps) {
									println("\tJumping to " + toJump)
									debugger.jump(toJump);
								}
								val long jumpAllEnd = System.nanoTime
								result.timeJumpMean = (jumpAllEnd - jumpAllBegin) / (allJumps.size)

							}

							println("Done! Result:")
							println(result)

							// We do the warmup once per measure
							toMerge.add(result)

							engine.clearCommandStackAndAdapters
							debugger.unloadTraceResource

						}
						results.addResult(Result.merge(toMerge))

					}
				}

			}
		}

		return results
	}

	def int computeModelSize(Resource resource) {
		return resource.allContents.filter(EObject).size
	}

	def int computeNumberMutableElements(Resource r) {
		val allProperties = r.allContents.filter(EObject).map[o|o.eClass.EAllStructuralFeatures].toList.flatten
		// TODO replace by usage of DynamicAnnotationHelper
		val annotatedProps = allProperties.filter[f|f.EAnnotations.size > 0]
		return annotatedProps.size()
	}

}
