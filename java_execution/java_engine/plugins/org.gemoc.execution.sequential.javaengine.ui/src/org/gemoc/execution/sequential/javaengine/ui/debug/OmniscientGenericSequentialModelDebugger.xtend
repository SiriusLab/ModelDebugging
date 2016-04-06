package org.gemoc.execution.sequential.javaengine.ui.debug;

import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.api.IValueTrace
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.function.BiPredicate
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.dialogs.ErrorDialog
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.gemoc.execution.sequential.javaengine.ui.Activator
import org.gemoc.executionframework.engine.core.AbstractSequentialExecutionEngine
import org.gemoc.executionframework.engine.core.EngineStoppedException
import org.gemoc.executionframework.engine.mse.MSE
import org.gemoc.executionframework.engine.mse.MSEOccurrence
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger {

	private val IMultiDimensionalTraceAddon traceAddon

	/**
	 * -1 means we are on the last recorded state.
	 * Otherwise value of the last jump index.
	 */
	private var int lastJumpIndex

	private var boolean inThePast = false
	
	private var steppingOverStackFrameIndex = -1
	
	private var steppingReturnStackFrameIndex = -1

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, IMultiDimensionalTraceAddon addon) {
		super(target, engine)
		this.traceAddon = addon
		traceAddon.timeLineNotifier = new WrapperOmniscientDebugTimeLine(this);
		this.lastJumpIndex = -1
		callStack = Collections.EMPTY_LIST
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

	def private void loadLastState(String threadName) {
		if (lastJumpIndex != -1) {
			jumpToState(lastIndex)
		}
	}
	
	def private List<Object> computeNewStack(MSEOccurrence mseOccurrence) {
		val newPath = new ArrayList
		newPath.add(mseOccurrence)
		var container = mseOccurrence.eContainer
		while (container != null && container instanceof MSEOccurrence) {
			newPath.add(0,container)
			container = container.eContainer
		}
		return newPath
	}
	
	override void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		newStack = computeNewStack(mseOccurrence)
		if (!control(threadName, mseOccurrence)) {
			throw new EngineStoppedException("Debug thread has stopped.");
		}
	}
	
	override void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		newStack = computeNewStack(mseOccurrence)
	}
	
	override protected void updateStack(String threadName, EObject instruction) {
		if (!inThePast) {
			doStuff(newStack,true)
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
	
	private var IStep stepIntoResult
	private var IStep stepOverResult
	private var IStep stepReturnResult
	
	private var IStep backIntoResult
	private var IStep backOverResult
	private var IStep backOutResult
	
	private var List<IStep> callStack
	private var List<Object> newStack
	
	def private void doStuff(List<Object> path, boolean updateStack) {
		val List<IStep> rootSteps = traceAddon.traceManager.getStepsForStates(0,traceAddon.traceManager.traceSize)
		
		val stepPath = new ArrayList
		val currentSteps = new ArrayList
		currentSteps.addAll(rootSteps)
		path.forEach[o|
			val step = currentSteps.findFirst[s|s.parameters.get("this") == o]
			currentSteps.clear
			if (step != null) {
				stepPath.add(step)
				currentSteps.addAll(step.subSteps)
			}
		]
		
		val stepPathUnmodifiable = stepPath.unmodifiableView
		
		stepIntoResult = computeStepInto(stepPathUnmodifiable,rootSteps)
		stepOverResult = computeStepOver(stepPathUnmodifiable,rootSteps)
		stepReturnResult = computeStepReturn(stepPathUnmodifiable,rootSteps)
		
		backIntoResult = computeBackInto(stepPathUnmodifiable,rootSteps)
		backOverResult = computeBackOver(stepPathUnmodifiable,rootSteps)
		backOutResult = computeBackOut(stepPathUnmodifiable,rootSteps)
		
		var i = 0
		while (i < path.size && i < callStack.size && path.get(i) == callStack.get(i)) i++
		if (updateStack) {
			for (var j=i;j<callStack.size;j++) popStackFrame(threadName)
			for (var j=i;j<path.size;j++) pushStackFrame(threadName,stepPath.get(j))
		}
		callStack = stepPathUnmodifiable
	}
	
	def computeBackInto(List<IStep> stepPath, List<IStep> rootSteps) {
		var IStep result = null
		if (stepPath.size > 1) {
			val reversedPath = stepPath.reverseView
			val currentStep = reversedPath.get(0)
			val parentStep = reversedPath.get(1)
			val parentSubSteps = parentStep.subSteps
			val idx = parentSubSteps.indexOf(currentStep)
			if (idx == 0) {
				// If the current step is the first in its parents substeps, return parent step
				result = parentStep
			} else {
				// Otherwise, return the deepest substep in the previous sibling step
				val previousStep = parentSubSteps.get(idx-1)
				var tmpStep = previousStep
				var tmpSubSteps = tmpStep.subSteps
				while (!tmpSubSteps.empty) {
					tmpStep = tmpSubSteps.last
					tmpSubSteps = tmpStep.subSteps
				}
				result = tmpStep
			}
		} else if (stepPath.size == 1) {
			val currentStep = stepPath.get(0)
			val idx = rootSteps.indexOf(currentStep)
			if (idx > 0) {
				val previousStep = rootSteps.get(idx-1)
				var tmpStep = previousStep
				var tmpSubSteps = tmpStep.subSteps
				while (!tmpSubSteps.empty) {
					tmpStep = tmpSubSteps.last
					tmpSubSteps = tmpStep.subSteps
				}
				result = tmpStep
			}
		}
		return result
	}
	
	def computeBackOver(List<IStep> stepPath, List<IStep> rootSteps) {
		if (!stepPath.empty) {
			val reversedPath = stepPath.reverseView
			return findPreviousStep(reversedPath,rootSteps,reversedPath.get(0),1)
		}
		return null
	}
	
	def computeBackOut(List<IStep> stepPath, List<IStep> rootSteps) {
		if (stepPath.size > 1) {
			val reversedPath = stepPath.reverseView
			return findPreviousStep(reversedPath,rootSteps,reversedPath.get(1),2)
		}
		return null
	}
	
	def findPreviousStep(List<IStep> stepPath,
			List<IStep> rootSteps, IStep previousStep, int start) {
		var IStep result = null
		var i = start
		var previous = previousStep
		while (result == null && i < stepPath.size) {
			val currentStep = stepPath.get(i)
			val currentSubSteps = currentStep.subSteps
			var idx = currentSubSteps.indexOf(previous) - 1
			if (idx > 0) {
				result = currentSubSteps.get(idx)
			} else {
				previous = currentStep
			}
			i++
		}
		if (result == null) {
			val idx = rootSteps.indexOf(previous) - 1
			if (idx > 0) {
				result = rootSteps.get(idx)
			}
		}
		return result
	}
	
	def findNextStep(List<IStep> stepPath,
			List<IStep> rootSteps, IStep previousStep, int start) {
		var IStep result = null
		var i = start
		var previous = previousStep
		while (result == null && i < stepPath.size) {
			val currentStep = stepPath.get(i)
			val currentSubSteps = currentStep.subSteps
			if (currentSubSteps.empty) {
				// No substep to step into, we thus have to explore the substeps of the parent step
				previous = currentStep
			} else {
				if (previous == null) {
					// First time we step into 
					result = currentSubSteps.get(0)
				} else {
					val idx = currentSubSteps.indexOf(previous) + 1
					if (idx < currentSubSteps.size) {
						// We step into the next step
						result = currentSubSteps.get(idx)
					} else {
						previous = currentStep
					}
				}
			}
			i++
		}
		if (result == null) {
			val idx = rootSteps.indexOf(previous) + 1
			if (idx < rootSteps.size) {
				result = rootSteps.get(idx)
			}
		}
		return result
	}
	
	def computeStepInto(List<IStep> stepPath, List<IStep> rootSteps) {
		return findNextStep(stepPath.reverseView,rootSteps,null,0)
	}
	
	def computeStepOver(List<IStep> stepPath, List<IStep> rootSteps) {
		if (!stepPath.empty) {
			val reversedPath = stepPath.reverseView
			return findNextStep(reversedPath,rootSteps,reversedPath.get(0),1)
		}
		return null
	}
	
	def computeStepReturn(List<IStep> stepPath, List<IStep> rootSteps) {
		if (stepPath.size > 1) {
			val reversedPath = stepPath.reverseView
			return findNextStep(reversedPath,rootSteps,reversedPath.get(1),2)
		}
		return null
	}
	
	override public void stepInto(String threadName) {
		if (inThePast) {
			if (stepIntoResult == null) {
				if (lastJumpIndex == -1) {
					inThePast = false
					super.stepInto(threadName)
				} else {
					throw new IllegalStateException("Next step was not found")
				}
			} else {
				jumpBeforeStep(stepIntoResult)
			}
		} else {
			super.stepInto(threadName)
		}
	}
	
	override public void stepOver(String threadName) {
		if (inThePast) {
			if (stepOverResult == null) {
				if (callStack.last.endingIndex == -1) {
					// This ensures we place an appropriate predicate break on the next call
					// to the steppingOver method.
					steppingOverStackFrameIndex = nbStackFrames - 1
					// The event corresponding to the end of the step has not yet
					// been recorded : we jump to the last state and resume execution
					loadLastState(threadName)
					inThePast = false
					// Resuming the execution
					super.stepOver(threadName)
				} else {
					throw new IllegalStateException("Next step was not found")
				}
			} else {
				jumpBeforeStep(stepOverResult)
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
			if (stepReturnResult == null) {
				if (callStack.size > 1 && callStack.get(callStack.size - 2).endingIndex == -1) {
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
					throw new IllegalStateException("Next step was not found")
				}
			} else {
				jumpBeforeStep(stepReturnResult)
			}
		} else {
			super.stepReturn(threadName)
		}
	}
	
	def public boolean canStepBackInto(String threadName) {
		return backIntoResult != null
	}
	
	def public boolean canStepBackOver(String threadName) {
		return backOverResult != null
	}
	
	def public boolean canStepBackOut(String threadName) {
		return backOutResult != null
	}
	
	def public void stepBackInto(String threadName) {
		if (backIntoResult != null) {
			inThePast = true
			jumpBeforeStep(backIntoResult)
		}
	}
	
	def public void stepBackOver(String threadName) {
		if (backOverResult != null) {
			inThePast = true
			jumpBeforeStep(backOverResult)
		}
	}
	
	def public void stepBackOut(String threadName) {
		if (backOutResult != null) {
			inThePast = true
			jumpBeforeStep(backOutResult)
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
	
	def private void jumpBeforeStep(IStep step) {
		if (step != null) {
			jumpToState(step.startingIndex)
			val newPath = new ArrayList
			newPath.add(step.parameters.get("this"))
			var parent = step.parentStep
			while (parent != null) {
				newPath.add(0,parent.parameters.get("this"))
				parent = parent.parentStep
			}
			doStuff(newPath,true)
		}
	}
	
	def private void jumpToState(int i) {
		// We are now in replay mode
		inThePast = true
		if (i == lastIndex) {
			lastJumpIndex = -1
		} else {
			lastJumpIndex = i
		}
		
		traceAddon.goTo(i)
		// Updating the variables view
		updateData(threadName, null)
		scheduleSelectLastStackframe(500)
	}
	
	/**
	 * To be used by the timeline
	 */
	def public void jump(int i) {
		if (i < traceAddon.traceManager.traceSize) {
			val List<IStep> rootSteps = traceAddon.traceManager.getStepsForStates(i,i)
			val List<IStep> searchPath = new ArrayList
			var IStep firstStateStep = null
 			if (!rootSteps.empty) {
				var IStep currentStep = rootSteps.get(0)
				var List<IStep> siblingSteps = rootSteps
				while (firstStateStep == null) {
					if (currentStep.startingIndex < i && (currentStep.endingIndex > i || currentStep.endingIndex == -1)) {
						if (currentStep.subSteps.empty) {
							throw new IllegalStateException("Unreachable state")
						} else {
							searchPath.add(0,currentStep)
							siblingSteps = currentStep.subSteps
							currentStep = siblingSteps.get(0)
						}
					} else if (currentStep.endingIndex == i && currentStep.startingIndex != i) {
						if (currentStep.subSteps.empty) {
							// We need to explore the next sibling step
							var tmp = currentStep
							currentStep = null
							while (currentStep == null) {
								val idx = siblingSteps.indexOf(tmp) + 1
								if (idx < siblingSteps.size) {
									currentStep = siblingSteps.get(idx)
								} else {
									if (searchPath.empty) {
										throw new IllegalStateException("Unreachable state")
									} else {
										tmp = searchPath.remove(0)
										if (searchPath.empty) {
											siblingSteps = rootSteps
										} else {
											siblingSteps = searchPath.get(0).subSteps
										}
									}
								}
							}
						} else {
							// We need to explore the substeps in case one of them starts on i
							searchPath.add(0,currentStep)
							siblingSteps = currentStep.subSteps
							currentStep = siblingSteps.get(0)
						}
					} else if (currentStep.startingIndex == i) {
						firstStateStep = currentStep
					}
				}
			}			
			jumpBeforeStep(firstStateStep)
		}
		scheduleSelectLastStackframe(500)
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
		//TODO fin the correct step to jump before
		val IStep step = null
		jumpBeforeStep(step)
	}
	
	override void engineStopped(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = null
		super.engineStopped(executionEngine)
	}
}
