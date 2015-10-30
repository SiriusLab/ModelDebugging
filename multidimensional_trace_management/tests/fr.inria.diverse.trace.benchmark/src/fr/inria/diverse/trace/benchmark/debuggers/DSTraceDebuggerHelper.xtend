package fr.inria.diverse.trace.benchmark.debuggers

import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon
import java.util.Collections
import java.io.File
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer
import fr.inria.diverse.trace.benchmark.Language
import org.gemoc.gemoc_language_workbench.extensions.k3.PlainK3ExecutionEngine

class DSTraceDebuggerHelper extends AbstractTraceDebugger implements IDebuggerHelper {

	var AbstractTraceAddon traceAddon

	override getAddons() {
		return Collections.singleton("TimeLineProviderProvider");
	}

	override getDebuggerName() {
		return "DomainSpecificTraceDebugger"
	}

	override canJump() {
		return true;
	}

	override getTraceSize() {
		return traceAddon.traceManager.traceSize
	}

	override jump(int i) {
		traceAddon.goToNoTimelineNotification(i)
	}

	override setExecutionEngine(PlainK3ExecutionEngine _executionEngine) {
		traceAddon = _executionEngine.getAddonsTypedBy(AbstractTraceAddon).get(0)
		traceAddon.disableTraceSaving
	}

	override computeTraceMemoryFootprint(Language l, File dumpFile, int traceSize) {

		val analyzer = new MemoryAnalyzer(dumpFile);

		// First we make sure that there is only one trace
		val String queryCheck = "SELECT * FROM " + l.javaTraceRootName;
		val resCheck = analyzer.computeRetainedSizeWithOQLQuery(queryCheck, dumpFile);
		if(resCheck.nbElements != 1) {
			//println("SLEEPING WHILE TOO MANY TRACES");
			//Thread.sleep(100000000);
			throw new Exception("Wrong number of traces: " + resCheck.nbElements);
		}

		val queryAll = "select a.@retainedHeapSize from \".*" + l.javaTracePackageName + ".*\" a";
		val queryRemove = "select a.@retainedHeapSize from \".*" + l.javaTracePackageName +
			".*(PackageImpl|FactoryImpl|AdapterFactory|Switch)$\" a";

		println("QueryAll: "+queryAll)
		println("QueryRemove: "+queryRemove)

		val resAll = analyzer.computeRetainedSizeWithOQLQuery(queryAll, dumpFile);
		val resRemove = analyzer.computeRetainedSizeWithOQLQuery(queryRemove, dumpFile);

		println("Memory all package: " + resAll.memorySum)
		println("Memory to remove: " + resRemove.memorySum)

		return resAll.memorySum - resRemove.memorySum
	}
	
	override unloadTraceResource() {
		traceAddon.traceManager.getExecutionState(0).eContainer.eResource.unload
	}
	
}
