package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import fr.inria.diverse.trace.gemoc.traceaddon.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineProvider = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}

	override public stepInto(String threadName) {
		if (inThePast) {
			jump(lastJumpIndex + 1)
		} else {
			super.stepInto(threadName)
		}
	}

	override public stepOver(String threadName) {
		if (inThePast) {
			jump(lastJumpIndex + 1)
		} else {
			super.stepOver(threadName)
		}
	}

	override public stepReturn(String threadName) {
		if (inThePast) {
			jump(lastJumpIndex + 1)
		} else {
			super.stepReturn(threadName)
		}
	}

	//TODO use a method from the trace manager mapping an eStructuralFeature to a GenericValueTrace
	//then value = t.getValue(t.getCurrentIndex(currentStateIndex))
//	override protected void updateVariables(List<MutableData> mutableDatas) {
//		val manager = traceAddon.traceManager
//		/*
//		 * for(MutableData m : mutableDatas) {
//		 * 	   val valueTraces = manager.getGenericValueTraces(m.geteObject)
//		 * 	   val value = valueTrace.getValue(valueTrace.getCurrentIndex(currentStateIndex))
//		 * 	   if(value != null)
//		 *		   m.value = manager.getValue(value)
//	     * }
//		 */
//		val values = manager.getAllCurrentValues(currentStateIndex)
//		for(MutableData m : mutableDatas) {
//			val traced = manager.getExeToTraced(m.geteObject)
//			val value = values.stream.filter(v|v.eContainer == traced).findFirst.get
//			if(value != null)
//				m.value = manager.getValue(value)
//		}
//		/*
//		 * 
//		 */
//		super.updateVariables(mutableDatas)
//	}
	
	/**
	 * -1 means we are in the present.
	 * Otherwise value of the last jump index.
	 */
	private int lastJumpIndex;

	/**
	 * To be used to decide whether to jump somewhere or to continue the execution.
	 */
	private def boolean inThePast() {
		return lastJumpIndex != -1
	}

	private def int getLastIndex() {
		traceAddon.traceManager.traceSize - 1
	}

	private IMultiDimensionalTraceAddon traceAddon ;

	/**
	 * If we are in the past, we read the last jump index.
	 * Otherwise, we read the trace size.
	 */
	public def int getCurrentStateIndex() {
		if (inThePast)
			return lastJumpIndex
		else
			return getLastIndex
	}

	/**
	 * To be used by the timeline
	 */
	public def void jump(int i) {

		// If we jump at the last index of the trace, then we are back in the present (ie -1).
		if (i == lastIndex) {
			lastJumpIndex = -1;
		} else {
			lastJumpIndex = i
		}
		traceAddon.goTo(i)
		updateData("Model debugging", null)
	}

	/**
	 * To be used by the timeline
	 */
	public def void jump(EObject o) {
		jump(traceAddon.traceManager.getStateIndex(o))
	}

}
