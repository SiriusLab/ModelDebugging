package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import fr.inria.aoste.timesquare.ecl.feedback.feedback.ModelSpecificEvent
import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.gemoc.traceaddon.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import java.util.function.BiPredicate
import org.eclipse.emf.ecore.EObject
import org.gemoc.execution.engine.core.AbstractDeterministicExecutionEngine
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Gemoc_execution_traceFactory
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence
import org.gemoc.executionengine.java.sequential_modeling_workbench.ui.Activator
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine
import org.gemoc.gemoc_language_workbench.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {

	private val IMultiDimensionalTraceAddon traceAddon

	/**
	 * -1 means we are on the last recorded state.
	 * Otherwise value of the last jump index.
	 */
	private var int lastJumpIndex

	private var boolean inThePast = false

	private val List<IStep.StepEvent> stepEvents = new ArrayList
	
	private var int currentEvent = -1
	
	private var steppingOverStackFrameIndex = -1
	
	private var steppingReturnStackFrameIndex = -1

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineProvider = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}
	
	def private MSEOccurrence computeStackFrame(IStep step) {
		val EObject caller = step.parameters.entrySet.findFirst[es|es.key.equals("this")].value as EObject
		val ModelSpecificEvent mse = (engine as AbstractDeterministicExecutionEngine).findOrCreateMSE(caller,
			step.containingClassName, step.operationName)
		val MSEOccurrence mseOccurrence = Gemoc_execution_traceFactory.eINSTANCE.createMSEOccurrence
		mseOccurrence.mse = mse
		return mseOccurrence
	}	

	def private void pushStackFrame(String threadName, MSEOccurrence mseOccurrence) {
		val caller = mseOccurrence.getMse().getCaller()
		val name = caller.eClass().getName() + " (" + mseOccurrence.getMse().getName() + ") [" + caller.toString() + "]"
		pushStackFrame(threadName, name, caller, caller)
	}

	def private void updateStateEvents(int state) {
		stepEvents.clear
		stepEvents.addAll(traceAddon.traceManager.getEventsForState(state))
		currentEvent = 0
	}

	def private void loadLastState(String threadName) {
		if (lastJumpIndex != -1) {
			jumpToState(lastIndex)
		}
		val virtualStack = new LinkedList<IStep>
		val size = stepEvents.size
		while (currentEvent < size) {
			val event = stepEvents.get(currentEvent)
			if (event.start) {
				virtualStack.addLast(event.step)
			} else if (virtualStack.empty) {
				popStackFrame(threadName)
			} else {
				virtualStack.removeFirst
			}
			currentEvent++
		}
		virtualStack.forEach[s|pushStackFrame(threadName,computeStackFrame(s))]
		inThePast = false
		currentEvent = -1
	}
	
	def private void findPreviousStartedStep(String threadName) {
		var itr = currentEvent
		if (itr > 0) {
			itr--
			var event = stepEvents.get(itr)
			while (!event.start && itr > 0) {
				itr--
				event = stepEvents.get(itr)
			}
			if (event.start) {
				val step = event.step.parameters.get("this")
				jumpToState(currentStateIndex)
				findCorrespondingStepEvent(threadName,step)
			} else if (lastJumpIndex != 0) {
				// No start event was found in the current state. Thus, the
				// previous start event is the last event of the previous state.
				val step = stepEvents.get(0).step.parameters.get("this")
				jumpToState(currentStateIndex - 1)
				findCorrespondingStepEvent(threadName,step)
			} else {
				// TODO There is no previous start event,
				// signal it to the user somehow.
			}
		} else if (lastJumpIndex != 0) {
			val step = stepEvents.get(0).step.parameters.get("this")
			jumpToState(currentStateIndex - 1)
			findCorrespondingStepEvent(threadName,step)
		} else {
			// There is no previous start event,
			// signal it to the user somehow.
		}
	}
	
	def private void findNextStartedStep(String threadName) {
		val size = stepEvents.size
		if (currentEvent < size) {
			var event = stepEvents.get(currentEvent)
			// TODO pop if !event.start ?
			while (!event.start && currentEvent < size - 1) {
				popStackFrame(threadName)
				currentEvent++
				event = stepEvents.get(currentEvent)
			}
			if (event.start) {
				pushStackFrame(threadName,computeStackFrame(event.step))
			} else {
				// Should not happen as we always have a started step at
				// the end of events.
				throw new IllegalStateException("State events ended before a start event was found")
			}
		}
	}
	
	def private IStep findPreviousEndedStep(String threadName) {
		var itr = currentEvent
		if (itr > 0) {
			itr--
			var event = stepEvents.get(itr)
			while (event.start && itr > 0) {
				itr--
				event = stepEvents.get(itr)
			}
			if (!event.start) {
				return event.step
			} else if (lastJumpIndex != 0) {
				// Should not happen as we always have an ended step at
				// the beginning of events.
				throw new IllegalStateException("State events ended before an end event was found")
			} else {
				return null
			}
		} else {
			throw new IllegalStateException("State events ended before an end event was found")
		}
	}
	
	def private void findCorrespondingStepEvent(String threadName, Object step) {
		val size = stepEvents.size
		if (currentEvent < size) {
			// We iterate through the recorded events until we find
			// the one corresponding to the desired step.
			var event = stepEvents.get(currentEvent)
			var obj = event.step.parameters.get("this")
			var virtualStack = new LinkedList<IStep>
			while (obj != step && currentEvent < size - 1) {
				if (event.start) {
					virtualStack.push(event.step)
				} else if (virtualStack.empty) {
					popStackFrame(threadName)
				} else {
					virtualStack.pop
				}
				currentEvent++
				event = stepEvents.get(currentEvent)
				obj = event.step.parameters.get("this")
			}
			if (obj == step) {
				// We found the step event we were looking for.
				val itr = virtualStack.descendingIterator
				while (itr.hasNext) {
					// We put the stack in the state it is supposed to be.
					pushStackFrame(threadName,computeStackFrame(itr.next))
				}
				if (event.start) {
					// If the step event was a "step start" event, we push it onto the stack.
					pushStackFrame(threadName,computeStackFrame(event.step))
				} else {
					// Otherwise we search for the next "step start" event.
					currentEvent++
					popStackFrame(threadName)
					findNextStartedStep(threadName)
				}
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
	
	override public void terminate() {
		super.terminate()
		Activator.^default.debuggerSupplier = [|null]		
	}

	override public void stepInto(String threadName) {
		if (inThePast) {
			val size = stepEvents.size
			if (currentEvent < size - 1) {
				// Events remain to be treated for that state
				currentEvent++
				var event = stepEvents.get(currentEvent)
				if (event.start) {
					pushStackFrame(threadName,computeStackFrame(event.step))
				} else {
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
			val seqEngine = engine as ISequentialExecutionEngine
			// To send notifications
			val stack = seqEngine.currentStack
			val idx = stack.size - steppingOverStackFrameIndex
			// We add a future break as soon as the step is over
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				// The operation we want to step over
				private MSEOccurrence steppedOver = stack.get(idx)
				override test(IBasicExecutionEngine t, MSEOccurrence u) {
					return !seqEngine.getCurrentStack().contains(steppedOver)
				}
			})
			steppingOverStackFrameIndex = -1
		} else {
			super.setupStepOverPredicateBreak
		}
	}

	override public void stepOver(String threadName) {
		if (inThePast) {
			val steppedOver = stepEvents.get(currentEvent).step
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
				findCorrespondingStepEvent(threadName,steppedOver.parameters.get("this"))
			}
		} else {
			super.stepOver(threadName)
		}
	}
	
	override protected void setupStepReturnPredicateBreak() {
		if (steppingReturnStackFrameIndex != -1) {
			val seqEngine = engine as ISequentialExecutionEngine
			val stack = seqEngine.currentStack
			val idx = stack.size - steppingReturnStackFrameIndex
			// We add a future break as soon as the step is returned
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				// The operation we want to step return
				private MSEOccurrence steppedReturn = stack.get(idx)
				override test(IBasicExecutionEngine t, MSEOccurrence u) {
					return !seqEngine.getCurrentStack().contains(steppedReturn)
				}
			})
			steppingReturnStackFrameIndex = -1
		} else {
			super.setupStepReturnPredicateBreak
		}
	}

	override public void stepReturn(String threadName) {
		if (inThePast) {
			val currentStep = stepEvents.get(currentEvent).step
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
						findCorrespondingStepEvent(threadName,steppedReturnObject)
					}
				}
			}
		} else {
			super.stepReturn(threadName)
		}
	}
	
	def public boolean canStepBackInto(String threadName) {
		return
			if (lastJumpIndex > 0 || (lastJumpIndex == -1 && lastIndex != 0)) {
				true
			} else {
				val events = new ArrayList
				if (inThePast) {
					events.addAll(stepEvents)
				} else {
					events.addAll(traceAddon.traceManager.getEventsForState(lastIndex))
				}
				var itr = events.size - 1
				var event = events.get(itr)
				while (!event.start && itr > -1) {
					itr--
					event = events.get(itr)
				}
				if (event.start) {
					true
				} else {
					false
				}
			}
	}
	
	def public boolean canStepBackOver(String threadName) {
		return
			if (lastJumpIndex > 0 || (lastJumpIndex == -1 && lastIndex != 0)) {
				true
			} else {
				val events = new ArrayList
				if (inThePast) {
					events.addAll(stepEvents)
				} else {
					events.addAll(traceAddon.traceManager.getEventsForState(lastIndex))
				}
				var itr = events.size - 1
				var event = events.get(itr)
				while (event.start && itr > -1) {
					itr--
					event = events.get(itr)
				}
				if (!event.start) {
					true
				} else {
					false
				}
			}
	}
	
	def public boolean canStepBackOut(String threadName) {
		return
			if (lastJumpIndex > 0 || (lastJumpIndex == -1 && lastIndex != 0)) {
				true
			} else {
				val events = new ArrayList
				if (inThePast) {
					events.addAll(stepEvents)
				} else {
					events.addAll(traceAddon.traceManager.getEventsForState(lastIndex))
				}
				var itr = events.size - 1
				var event = events.get(itr)
				while (event.start && itr > -1) {
					itr--
					event = events.get(itr)
				}
				if (!event.start && event.step.parentStep != null) {
					true
				} else {
					false
				}
			}
	}
	
	def public void stepBackInto(String threadName) {
		if (!inThePast) {
			inThePast = true
		}
		findPreviousStartedStep(threadName)
	}
	
	def public void stepBackOver(String threadName) {
		if (!inThePast) {
			inThePast = true
		}
		val step = findPreviousEndedStep(threadName)
		// Wether the step started in a previous state or not
		// we jump to that state.
		if (step != null) {
			jumpToState(step.startingIndex)
			// We then iterate through the state events until we
			// reach the start event we are looking for.
			findCorrespondingStepEvent(threadName,step.parameters.get("this"))
		}
	}
	
	def public void stepBackOut(String threadName) {
		if (!inThePast) {
			inThePast = true
		}
		val step = findPreviousEndedStep(threadName)
		if (step != null && step.parentStep != null) {
			val parentStep = step.parentStep
			// Whether the step started in a previous state or not
			// we jump to that state.
			jumpToState(parentStep.startingIndex)
			// We then iterate through the state events until we
			// reach the start event we are looking for.
			findCorrespondingStepEvent(threadName,parentStep.parameters.get("this"))
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
		updateStateEvents(i)
		// Updating the variables view
		updateData(threadName, null)
		
		// We retrieve the stack we are supposed to have upon entering the state
		val beforeStack = traceAddon.traceManager.getStackForwardBeforeState(currentStateIndex)
		beforeStack.forEach[s|pushStackFrame(threadName,computeStackFrame(s))]
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(int i) {
		jumpToState(i)
		findNextStartedStep(threadName)
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(EObject o) {
		jump(traceAddon.traceManager.getStateIndex(o))
	}
	
	override void engineStarted(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = [this]
		super.engineStarted(executionEngine)
	}
	
	override void engineStopped(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = null
		super.engineStopped(executionEngine)
	}
}
