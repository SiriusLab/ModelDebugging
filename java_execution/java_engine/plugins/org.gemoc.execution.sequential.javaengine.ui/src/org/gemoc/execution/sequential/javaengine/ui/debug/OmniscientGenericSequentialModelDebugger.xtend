package org.gemoc.execution.sequential.javaengine.ui.debug;

import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import java.util.function.BiPredicate
import org.eclipse.emf.ecore.EObject
import org.gemoc.executionframework.engine.core.AbstractSequentialExecutionEngine
import org.gemoc.executionframework.engine.mse.MSEOccurrence
import org.gemoc.execution.sequential.javaengine.ui.Activator
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine
import org.gemoc.executionframework.engine.mse.MSE
import fr.inria.diverse.trace.api.IValueTrace
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.jface.dialogs.ErrorDialog
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status

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
		traceAddon.timeLineNotifier = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
	}
	
	def public boolean isInReplayMode() {
		return inThePast
	}
	
	def private MSE getMSEFromStep(EObject caller, IStep step) {
		var MSE mse
		if (caller instanceof MSEOccurrence) {
				mse = (caller as MSEOccurrence).mse
		} else {
			mse = (engine as AbstractSequentialExecutionEngine).findOrCreateMSE(caller,
				step.containingClassName, step.operationName)
		}
		return mse
	}

	def private void pushStackFrame(String threadName, IStep step) {
		var EObject caller
		var MSE mse
		var String name
		val callerEntry = step.parameters.entrySet.findFirst[es|es.key.equals("caller")]
		if (callerEntry != null) {
			val entryValue = callerEntry.value as EObject
			mse = getMSEFromStep(entryValue,step)
			name = mse.name
		} else {
			val parentStep = step.parentStep.parameters.get("this") as EObject
			mse = getMSEFromStep(parentStep,step)
			name = mse.name + "_implicitStep"
		}
		caller = mse.caller
		name = caller.eClass().getName() + " (" + name + ") [" + caller.toString() + "]"
		
		val DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider()
		val QualifiedName qname = nameprovider.getFullyQualifiedName(caller)
		val String objectName = if(qname !== null) qname.toString() else caller.toString()
		val String opName = mse.action?.name
		val String callerType = caller.eClass().getName()
		val String prettyName =  "(" + callerType + ") " +objectName + " -> " + opName +"()"
		pushStackFrame(threadName, prettyName, caller, caller)
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
		virtualStack.forEach[s|pushStackFrame(threadName, s)]
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
				// There is no previous start event, the user should
				// not be able to step back in the first place
			}
		} else if (lastJumpIndex != 0) {
			val step = stepEvents.get(0).step.parameters.get("this")
			jumpToState(currentStateIndex - 1)
			findCorrespondingStepEvent(threadName,step)
		} else {
			// There is no previous start event, the user should
			// not be able to step back in the first place
		}
	}
	
	def private void findNextStartedStep(String threadName) {
		val size = stepEvents.size
		if (currentEvent < size) {
			var event = stepEvents.get(currentEvent)
			while (!event.start && currentEvent < size - 1) {
				popStackFrame(threadName)
				currentEvent++
				event = stepEvents.get(currentEvent)
			}
			if (event.start) {
				pushStackFrame(threadName, event.step)
			} else {
				if (lastJumpIndex == -1) {
					// Should only happen on the last step, as this is the only time a state ends without
					// a step start event.
					popStackFrame(threadName)
				} else {
					throw new IllegalStateException("State events ended before a start event was found")
				}
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
					pushStackFrame(threadName, itr.next)
				}
				if (event.start) {
					// If the step event was a "step start" event, we push it onto the stack.
					pushStackFrame(threadName, event.step)
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
		if (!executionTerminated) {
			if (inThePast) {
				loadLastState(threadName)
			}
			super.resume
		}
	}

	override public void resume(String threadName) {
		if (!executionTerminated) {
			if (inThePast) {
				loadLastState(threadName)
			}
			super.resume(threadName)
		}
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
					pushStackFrame(threadName, event.step)
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
				} else {
					// The event corresponding to the end of the step happens at the
					// current state : we increment currentEvent to avoid treating the
					// same event twice.
					currentEvent++
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
					}
					findCorrespondingStepEvent(threadName,steppedReturnObject)
				}
			}
		} else {
			super.stepReturn(threadName)
		}
	}
	
	def public boolean canStepBackInto(String threadName) {
		return
			if (lastJumpIndex > 0 || (lastJumpIndex == -1 && lastIndex > 0)) {
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
				!event.start
			}
	}
	
	def public boolean canStepBackOver(String threadName) {
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
		return !event.start
	}
	
	def public boolean canStepBackOut(String threadName) {
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
		return !event.start && event.step.parentStep != null
	}
	
	def public void stepBackInto(String threadName) {
		if (!inThePast) {
			inThePast = true
			updateStateEvents(lastIndex)
			currentEvent = stepEvents.size - 1
		}
		findPreviousStartedStep(threadName)
	}
	
	def public void stepBackOver(String threadName) {
		if (!inThePast) {
			inThePast = true
			updateStateEvents(lastIndex)
			currentEvent = stepEvents.size - 1
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
			updateStateEvents(lastIndex)
			currentEvent = stepEvents.size - 1
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
	
	def public canStepValue(int traceIndex) {
		// TODO handle cases where the execution has completed
		return true
	}
	
	def private getNextValueIndex(IValueTrace valueTrace) {
		var stateIndex = currentStateIndex
		val currentValueIndex = valueTrace.getActiveValueIndex(stateIndex)
		var valueIndex = currentValueIndex
		while (stateIndex<lastIndex && (valueIndex == currentValueIndex || valueIndex == -1)) {
			stateIndex++
			valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		}
		if (valueIndex == currentValueIndex || valueIndex == -1) {
			return valueTrace.size
		}
		return valueIndex
	}
	
	def public stepValue(int traceIndex) {
		val valueTrace = traceAddon.traceManager.allValueTraces.get(traceIndex)
		val i = getNextValueIndex(valueTrace)
		if (i < valueTrace.size && i != -1) {
			jump(valueTrace.getValue(i))
		} else {
			setupStepValuePredicateBreak(valueTrace,i)
			resume
		}
	}
	
	def private setupStepValuePredicateBreak(IValueTrace valueTrace, int valueIndex) {
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
			override test(IBasicExecutionEngine t, MSEOccurrence u) {
				val i = valueTrace.getActiveValueIndex(currentStateIndex)
				val j = valueIndex
				return i == j
			}
		});
	}
	
	def public canBackValue(int traceIndex) {
		val allValueTraces = traceAddon.traceManager.allValueTraces
		if (traceIndex < allValueTraces.size && traceIndex > -1) {
			val valueTrace = allValueTraces.get(traceIndex)
			val currentValueIndex = valueTrace.getActiveValueIndex(currentStateIndex)
			var stateIndex = currentStateIndex
			var valueIndex = valueTrace.getActiveValueIndex(stateIndex)
			while (stateIndex>0 && (valueIndex == currentValueIndex || valueIndex == -1)) {
				stateIndex--
				valueIndex = valueTrace.getActiveValueIndex(stateIndex)
			}
			return valueIndex != currentValueIndex && valueIndex != -1
		}
		return false
	}
	
	// TODO duplicated code, but might not be worth factoring performance-wise
	def private getPreviousValueIndex(IValueTrace valueTrace) {
		val currentValueIndex = valueTrace.getActiveValueIndex(currentStateIndex)
		var stateIndex = currentStateIndex - 1
		var valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		while (stateIndex>0 && (valueIndex == currentValueIndex || valueIndex == -1)) {
			stateIndex--
			valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		}
		return valueIndex
	}
	
	def public backValue(int traceIndex) {
		val valueTrace = traceAddon.traceManager.allValueTraces.get(traceIndex)
		jump(valueTrace.getValue(getPreviousValueIndex(valueTrace)))
	}

	/**
	 * Returns the index of the last state in the trace. 
	 */
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
		beforeStack.forEach[s|pushStackFrame(threadName, s)]
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(int i) {
		if (i < traceAddon.traceManager.traceSize) {
			jumpToState(i)
			findNextStartedStep(threadName)
		}
	}

	/**
	 * To be used by the timeline
	 */
	def public void jump(EObject o) {
		jump(traceAddon.traceManager.getStateOrValueIndex(o))
	}
	
	override public validateVariableValue(String threadName, String variableName, String value) {
		if (inThePast) {
			ErrorDialog.openError(null,"Illegal variable value set", "Cannot set the value of a variable when in replay mode",
				new Status(IStatus.ERROR,Activator.PLUGIN_ID,"Illegal variable value set"))
			return false
		}
		return super.validateVariableValue(threadName,variableName,value)
	}
	
	override void engineStarted(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = [this]
		super.engineStarted(executionEngine)
	}
	
	override void engineAboutToStop(IBasicExecutionEngine engine) {
		super.engineAboutToStop(engine)
		jumpToState(traceAddon.traceManager.traceSize-1)
		currentEvent = stepEvents.size-1
	}
	
	override void engineStopped(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = null
		super.engineStopped(executionEngine)
	}
}
