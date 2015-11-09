package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import fr.inria.diverse.trace.gemoc.traceaddon.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import org.eclipse.emf.ecore.EObject
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine
import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent
import org.gemoc.execution.engine.core.AbstractDeterministicExecutionEngine
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence
import fr.inria.diverse.trace.api.IStep

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {


	private val IMultiDimensionalTraceAddon traceAddon ;
	
	/**
	 * -1 means we are in the present.
	 * Otherwise value of the last jump index.
	 */
	private var int lastJumpIndex;

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineProvider = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}

	override public stepInto(String threadName) {
		if (inThePast) {
			
			//----------- UNTESTED CODE THAT CURRENTLY DOES NOTHING -----------
			
			// Retrieving the first part of the stack containing big steps that had started before the current state 
			val beforeStateStack = traceAddon.traceManager.getStackForwardBeforeState(currentStateIndex)
			for (IStep step : beforeStateStack) {
				val EObject caller = step.parameters.entrySet.findFirst[es|es.key.equals("this")].value as EObject // TODO here not enough checks
				val ModelSpecificEvent mse = (engine as AbstractDeterministicExecutionEngine).findOrCreateMSE(caller,step.containingClassName,step.operationName)
				val MSEOccurrence mseocc = org.gemoc.execution.engine.trace.gemoc_execution_trace.Gemoc_execution_traceFactory.eINSTANCE.createMSEOccurrence
				mseocc.mse = mse
				// etc.
			}
			
			// Retrieving the second part of the stack containing steps that had started at the current state
			val afterStateStack = traceAddon.traceManager.getStackForwardBeforeState(currentStateIndex)
			for (IStep step : afterStateStack) {
				// can do the same here
			}
			// -------------------------------------------------------------------
			
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
	


	/**
	 * To be used to decide whether to jump somewhere or to continue the execution.
	 */
	private def boolean inThePast() {
		return lastJumpIndex != -1
	}

	private def int getLastIndex() {
		traceAddon.traceManager.traceSize - 1
	}


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
