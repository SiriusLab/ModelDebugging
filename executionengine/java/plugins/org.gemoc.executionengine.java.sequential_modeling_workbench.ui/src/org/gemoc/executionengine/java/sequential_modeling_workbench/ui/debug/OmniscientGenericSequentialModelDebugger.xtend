package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent
import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.gemoc.traceaddon.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.gemoc.execution.engine.core.AbstractDeterministicExecutionEngine
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Gemoc_execution_traceFactory
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {

	private val IMultiDimensionalTraceAddon traceAddon

	/**
	 * -1 means we are in the present.
	 * Otherwise value of the last jump index.
	 */
	private var int lastJumpIndex

	private var LinkedList<MSEOccurrence> beforeStateStack

	private var LinkedList<MSEOccurrence> afterStateStack

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineProvider = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}

	override protected updateStack(String threadName, EObject instruction) {
		if ((beforeStateStack != null && !beforeStateStack.empty) || (afterStateStack != null && !afterStateStack.empty)) {
			while (!beforeStateStack.empty) {
				val mseOccurrence = beforeStateStack.pollLast
				val caller = mseOccurrence.getMse().getCaller()
				val name = caller.eClass().getName() + " (" + mseOccurrence.getMse().getName() + ") [" +
					caller.toString() + "]"
				pushStackFrame(threadName, name, caller, caller)
				nbStackFrames++
			}
		} else {
			super.updateStack(threadName, instruction)
		}
	}

	def private computeStateStack(int state) {
		val beforeStack = traceAddon.traceManager.getStackForwardBeforeState(state)
		beforeStateStack = new LinkedList()
		for (IStep step : beforeStack) {
			// TODO here not enough checks
			val EObject caller = step.parameters.entrySet.findFirst[es|es.key.equals("this")].value as EObject
			val ModelSpecificEvent mse = (engine as AbstractDeterministicExecutionEngine).findOrCreateMSE(caller,
				step.containingClassName, step.operationName)
			val MSEOccurrence mseocc = Gemoc_execution_traceFactory.eINSTANCE.createMSEOccurrence
			mseocc.mse = mse
			beforeStateStack.push(mseocc)
		}
		val afterStack = traceAddon.traceManager.getStackForwardAfterState(state)
		afterStateStack = new LinkedList()
		for (IStep step : afterStack) {
			val EObject caller = step.parameters.entrySet.findFirst[es|es.key.equals("this")].value as EObject
			val ModelSpecificEvent mse = (engine as AbstractDeterministicExecutionEngine).findOrCreateMSE(caller,
				step.containingClassName, step.operationName)
			val MSEOccurrence mseocc = Gemoc_execution_traceFactory.eINSTANCE.createMSEOccurrence
			mseocc.mse = mse
			afterStateStack.push(mseocc)
		}
	}
	
	def private loadLastState() {
		if(inThePast) {
			jump(lastIndex)
			while (inThePast) {
				stepInto("Model debugging")
			}
		}
	} 
	
	override public resume() {
		loadLastState
		super.resume
	}

	override public resume(String threadName) {
		loadLastState
		super.resume(threadName)
	}

	override public stepInto(String threadName) {
		if (inThePast) {
			if (afterStateStack == null || afterStateStack.empty) {
				jump(lastJumpIndex + 1)
			} else {
				beforeStateStack.push(afterStateStack.pollLast)
				updateStack(threadName, null)
			}
		} else {
			beforeStateStack = null
			afterStateStack = null
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
		return lastJumpIndex != -1 || (afterStateStack != null && !afterStateStack.empty)
	}

	private def int getLastIndex() {
		return traceAddon.traceManager.traceSize - 1
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
		// We empty the stack to replace it by a recomputed one
		while (nbStackFrames > 1) {
			popStackFrame("Model debugging")
			nbStackFrames--
		}
		
		setCurrentInstruction("Model debugging", executedModelRoot)
		traceAddon.goTo(i)
		computeStateStack(i)
		if(!afterStateStack.empty) {
			beforeStateStack.push(afterStateStack.pollLast)
		}

		updateData("Model debugging", null)
	}

	/**
	 * To be used by the timeline
	 */
	public def void jump(EObject o) {
		jump(traceAddon.traceManager.getStateIndex(o))
	}

}
