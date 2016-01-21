package org.gemoc.execution.sequential.javaengine.ui.debug

import fr.inria.diverse.trace.api.ITraceManager
import fr.inria.diverse.trace.api.IValueTrace
import fr.inria.diverse.trace.gemoc.traceaddon.WrapperSimpleTimeLine

class WrapperOmniscientDebugTimeLine extends WrapperSimpleTimeLine {

	OmniscientGenericSequentialModelDebugger debugger

	new(ITraceManager manager) {
		super(manager)
	}
	
	new (OmniscientGenericSequentialModelDebugger debugger) {
		super (null)
		this.debugger = debugger
	}
	
	override notifyTimeLine() {
		// This method is overriden to refresh only once the fx timeline
		if (traceManager != null) {
			notifyEndChanged(debugger.currentStateIndex, traceManager.traceSize);
		}
	}

	override getSelectedPossibleStep(int branch, int index) {
		if (branch == 0) {
			return debugger.getCurrentStateIndex();
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