package org.gemoc.execution.sequential.javaengine.ui.debug

import fr.inria.diverse.trace.gemoc.traceaddon.WrapperSimpleTimeLine
import fr.inria.diverse.trace.api.ITraceManager
import fr.inria.diverse.trace.api.IValueTrace

class WrapperOmniscientDebugTimeLine extends WrapperSimpleTimeLine {

	OmniscientGenericSequentialModelDebugger debugger
	
	private var int currentTrace

	new(ITraceManager manager) {
		super(manager)
	}
	
	new (OmniscientGenericSequentialModelDebugger debugger) {
		super (null)
		this.debugger = debugger
	}
	
//	def private void stepValue() {
//		val valueTrace = allValueTraces.get(currentTrace)
//		val i = valueTrace.getCurrentIndex(debugger.currentStateIndex)
//		debugger.jump(valueTrace.getValue(i+1))
//	}
	
//	def private void backValue() {
//		val valueTrace = allValueTraces.get(currentTrace)
//		val i = valueTrace.getCurrentIndex(debugger.currentStateIndex)
//		debugger.jump(valueTrace.getValue(i-1))
//	}

	override getSelectedPossibleStep(int branch, int index) {

		if (branch == 0) {
			if (debugger.getCurrentStateIndex() == index) {
				return -1;
			} else if (debugger.getCurrentStateIndex() > index) {
				return 0;
			} else {
				return 1;
			}

		} else {
			val IValueTrace trace = getAllValueTraces().get(branch - 1);
			val int traceCurrentIndex = trace.getCurrentIndex(debugger.currentStateIndex);
			if (traceCurrentIndex == index) {
				return -1;
			} else {
				return 0;

			}
		}

	}

}