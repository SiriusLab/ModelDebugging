package fr.inria.diverse.trace.benchmark.debuggers

import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon
import java.util.Collections
import org.gemoc.execution.engine.core.PlainK3ExecutionEngine

class DSTraceDebuggerHelper extends AbstractTraceDebugger implements IDebuggerHelper {

	var AbstractTraceAddon traceAddon
	
	new() {
		super(null)
	}

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
		traceAddon.goTo(i)
	}


	override setExecutionEngine(PlainK3ExecutionEngine _executionEngine) {
		traceAddon = _executionEngine.getAddonsTypedBy(AbstractTraceAddon).get(0)
		traceAddon.disableTraceSaving
	}

}
