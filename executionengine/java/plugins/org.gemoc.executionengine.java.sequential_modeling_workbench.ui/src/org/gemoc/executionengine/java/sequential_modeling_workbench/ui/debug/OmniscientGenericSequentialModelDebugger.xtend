package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent
import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.gemoc.traceaddon.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import java.util.ListIterator
import java.util.function.BiPredicate
import org.eclipse.emf.ecore.EObject
import org.gemoc.execution.engine.core.AbstractDeterministicExecutionEngine
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Gemoc_execution_traceFactory
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {

	private val IMultiDimensionalTraceAddon traceAddon

	/**
	 * -1 means we are in the present.
	 * Otherwise value of the last jump index.
	 */
	private var int lastJumpIndex

	private var boolean inThePast = false

	private val List<IStep.StepEvent> stepEvents = new ArrayList

	private var ListIterator<IStep.StepEvent> stepEventsIterator
	
	private var steppingOverStackFrameIndex = -1
	
	private var steppingReturnStackFrameIndex = -1

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineProvider = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}

	def private void pushStackFrame(String threadName, MSEOccurrence mseOccurrence) {
		val caller = mseOccurrence.getMse().getCaller()
		val name = caller.eClass().getName() + " (" + mseOccurrence.getMse().getName() + ") [" + caller.toString() + "]"
		pushStackFrame(threadName, name, caller, caller)
	}

	def private MSEOccurrence computeStackFrame(IStep step) {
		val EObject caller = step.parameters.entrySet.findFirst[es|es.key.equals("this")].value as EObject
		val ModelSpecificEvent mse = (engine as AbstractDeterministicExecutionEngine).findOrCreateMSE(caller,
			step.containingClassName, step.operationName)
		val MSEOccurrence mseOccurrence = Gemoc_execution_traceFactory.eINSTANCE.createMSEOccurrence
		mseOccurrence.mse = mse
		return mseOccurrence
	}

	def private void computeStateStack(int state) {
		stepEvents.clear
		stepEvents.addAll(traceAddon.traceManager.getEventsForState(state))
		stepEventsIterator = stepEvents.listIterator
	}

	def private void loadLastState(String threadName) {
		if (lastJumpIndex != -1) {
			jumpToState(lastIndex)
		}
		val virtualStack = new LinkedList<IStep>
		while (stepEventsIterator.hasNext) {
			val event = stepEventsIterator.next
			if (event.start) {
				virtualStack.addLast(event.step)
			} else if (virtualStack.empty) {
				popStackFrame(threadName)
			} else {
				virtualStack.removeFirst
			}
		}
		virtualStack.forEach[s|pushStackFrame(threadName,computeStackFrame(s))]
		inThePast = false
	}
	
	def private void findNextStartedStep(String threadName) {
		if (stepEventsIterator.hasNext) {
			var event = stepEventsIterator.next
			while (!event.start && stepEventsIterator.hasNext) {
				popStackFrame(threadName)
				event = stepEventsIterator.next
			}
			if (event.start) {
				pushStackFrame(threadName,computeStackFrame(event.step))
			} else {
				// Should not happen as we always have a started step at
				// the end of events (needs to be verified).
				throw new IllegalStateException("State events ended before a start event was found")
			}
		}
	}
	
	def private void findStepEndEvent(String threadName, Object step) {
		if (stepEventsIterator.hasNext) {
			// We iterate through the recorded events until we find
			// the one corresponding to the end of the step.
			var event = stepEventsIterator.next
			var obj = event.step.parameters.get("this")
			// We need to pop at least one stackframe since we want to
			// pop the one corresponding to the step that ended.
			var nbToPop = 1
			while (obj != step && stepEventsIterator.hasNext) {
				if (event.start) {
					nbToPop--
				} else {
					nbToPop++
				}
				event = stepEventsIterator.next
				obj = event.step.parameters.get("this")
			}
			if (obj == step) {
				// We found the "step ended" event we were looking for.
				// We pop as much stackframes as we have to to pop the
				// one corresponding to the step that ended.
				for(var i=0;i<nbToPop;i++) {
					popStackFrame(threadName)
				}
				// And push the next "step started" event we find, popping
				// all "stop ended" events we find on the way.
				findNextStartedStep(threadName)
			} else {
				throw new IllegalStateException("State events ended before the desired event was found")
			}
		}
	}
	
	override protected void updateStack(String threadName, EObject instruction) {
		if (!inThePast) {
			super.updateStack(threadName,instruction)
		}
	}

	override public void resume() {
		if (inThePast) {
			loadLastState(threadName)
		}		
		super.resume
	}

	override public void resume(String threadName) {
		if (inThePast) {
			loadLastState(threadName)
		}
		super.resume(threadName)
	}

	override public void stepInto(String threadName) {
		if (inThePast) {
			if (stepEventsIterator.hasNext) {
				// Events remain to be treated for that state
				var event = stepEventsIterator.next
				if (event.start) {
					pushStackFrame(threadName,computeStackFrame(event.step))
				} else {
					popStackFrame(threadName)
					findNextStartedStep(threadName)
				}
			} else {
				// No more event for that state
				if (lastJumpIndex == -1) {
					// We are at the last recorded state, resuming execution
					inThePast = false
					super.stepInto(threadName)
				} else {
					jumpToState(lastJumpIndex+1)
					findNextStartedStep(threadName)
				}
			}
		} else {
			super.stepInto(threadName)
		}
	}
	
	override protected void setupStepOverPredicateBreak() {
		if (steppingOverStackFrameIndex != -1) {
			// To send notifications
			val stack = engine.currentStack
			val idx = stack.size - steppingOverStackFrameIndex
			// We add a future break as soon as the step is over
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				// The operation we want to step over
				private MSEOccurrence steppedOver = stack.get(idx)
				override test(IBasicExecutionEngine t, MSEOccurrence u) {
					return !engine.getCurrentStack().contains(steppedOver)
				}
			})
			steppingOverStackFrameIndex = -1
		} else {
			super.setupStepOverPredicateBreak
		}
	}

	override public void stepOver(String threadName) {
		if (inThePast) {
			val steppedOver = stepEvents.get(stepEventsIterator.nextIndex-1).step
			val endingStateIdx = steppedOver.endingIndex 
			if (endingStateIdx == -1) {
				// This ensures we place an appropriate predicate break on the next call
				// to the steppingOver method.
				steppingOverStackFrameIndex = nbStackFrames - 1
				// The event corresponding to the end of the step has not yet
				// been recorded : we jump to the last state and resume execution
				loadLastState(threadName)
				// Resuming the execution
				super.stepOver(threadName)
			} else {
				if (endingStateIdx > currentStateIndex) {
					// The event corresponding to the end of the step happens at a
					// future state : we jump to that state.
					jumpToState(endingStateIdx)
				}
				findStepEndEvent(threadName,steppedOver.parameters.get("this"))
			}
		} else {
			super.stepOver(threadName)
		}
	}
	
	override protected void setupStepReturnPredicateBreak() {
		if (steppingReturnStackFrameIndex != -1) {
			// To send notifications
			val stack = engine.currentStack
			val idx = stack.size - steppingReturnStackFrameIndex
			// We add a future break as soon as the step is returned
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				// The operation we want to step return
				private MSEOccurrence steppedReturn = stack.get(idx)
				override test(IBasicExecutionEngine t, MSEOccurrence u) {
					return !engine.getCurrentStack().contains(steppedReturn)
				}
			})
			steppingReturnStackFrameIndex = -1
		} else {
			super.setupStepReturnPredicateBreak
		}
	}

	override public void stepReturn(String threadName) {
		if (inThePast) {
			val currentStep = stepEvents.get(stepEventsIterator.nextIndex-1).step
			val parentStep = currentStep.parentStep
			if (parentStep != null) {
				val endIndex = parentStep.endingIndex
				val steppedReturnObject = parentStep.parameters.get("this")
				if (endIndex == -1) {
					// This ensures we place an appropriate predicate break on the next call
					// to the steppingReturn method.
					steppingReturnStackFrameIndex = nbStackFrames - 2
					// The event corresponding to the end of the step has not yet
					// been recorded : we jump to the last state and resume execution
					loadLastState(threadName)
					inThePast = false
					// Resuming the execution
					super.stepReturn(threadName)
				} else {
					if (endIndex != currentStateIndex) {
						jumpToState(endIndex)
						findStepEndEvent(threadName,steppedReturnObject)
					}
				}
			}
		} else {
			super.stepReturn(threadName)
		}
	}
	
	def private int getLastIndex() {
		return traceAddon.traceManager.traceSize - 1
	}

	/**
	 * If we are in the past, we read the last jump index.
	 * Otherwise, we read the trace size.
	 */
	def public int getCurrentStateIndex() {
		if (lastJumpIndex != -1)
			return lastJumpIndex
		else
			return getLastIndex
	}

	def private void jumpToState(int i) {
		// We empty the stack to replace it by a recomputed one
		while (nbStackFrames > 1) {
			popStackFrame(threadName)
		}
		setCurrentInstruction(threadName, executedModelRoot)

		// We are now in replay mode
		inThePast = true
		if (i == lastIndex) {
			lastJumpIndex = -1
		} else {
			lastJumpIndex = i
		}
		
		traceAddon.goTo(i)
		computeStateStack(i)
		
		// We retrieve the stack we are supposed to have upon entering the state
		val beforeStack = traceAddon.traceManager.getStackForwardBeforeState(currentStateIndex)
		beforeStack.forEach[s|pushStackFrame(threadName,computeStackFrame(s))]
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(int i) {
		jumpToState(i)
		updateData(threadName, null)
		findNextStartedStep(threadName)
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(EObject o) {
		jump(traceAddon.traceManager.getStateIndex(o))
	}

}
