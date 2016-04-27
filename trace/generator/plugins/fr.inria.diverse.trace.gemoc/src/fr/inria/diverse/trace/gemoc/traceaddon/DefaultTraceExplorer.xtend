package fr.inria.diverse.trace.gemoc.traceaddon

import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.api.ITraceManager
import fr.inria.diverse.trace.api.IValueTrace
import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer
import fr.inria.diverse.trace.gemoc.api.ITraceListener
import java.util.ArrayList
import java.util.Collections
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.gemoc.executionframework.engine.mse.MSEOccurrence

class DefaultTraceExplorer implements ITraceExplorer {

	private val nameprovider = new DefaultDeclarativeQualifiedNameProvider()
	private var ITraceManager traceManager
	private val IMultiDimensionalTraceAddon traceAddon
	private var int lastJumpIndex = -1
	private val List<IStep> callStack = new ArrayList

	new(IMultiDimensionalTraceAddon addon) {
		this.traceAddon = addon
	}

	private val List<ITraceListener> listeners = new ArrayList

	override addListener(ITraceListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener)
		}
	}

	override removeListener(ITraceListener listener) {
		listeners.remove(listener)
	}

	override notifyListeners() {
		listeners.forEach[l|l.update]
	}

	override getAt(int traceIndex, int indexInTrace) {
		if (traceIndex == 0) {
			if (traceManager.traceSize > indexInTrace) {
				return traceManager.getExecutionState(indexInTrace)
			}
		} else {
			if (traceManager.allValueTraces.size > indexInTrace) {
				return traceManager.allValueTraces.get(traceIndex - 1).getValue(indexInTrace)
			}
		}
		return null;
	}

	override getCurrentStateIndex() {
		if (lastJumpIndex != -1)
			return lastJumpIndex
		else
			return getLastIndex
	}

	override getNumberOfTraces() {
		if (traceManager == null)
			return 1;
		return traceManager.numberOfValueTraces + 1
	}

	override getStatesOrValues(int line, int start, int end) {
		if (traceManager == null)
			return Collections.EMPTY_LIST

		val List<StateWrapper> result = new ArrayList
		val startStateIndex = Math.max(0, start)
		val endStateIndex = Math.min(traceManager.traceSize, end)

		if (line == 0) {
			for (var i = startStateIndex; i < endStateIndex; i++) {
				result.add(new StateWrapper(traceManager.getExecutionState(i), i, i, i))
			}
		} else if (line - 1 < traceManager.getNumberOfValueTraces()) {
			// Getting the trace we want to gather values from
			val valueTrace = traceManager.getAllValueTraces().get(line - 1)
			// Initializing the index of the current value
			var valueStartIndex = -1
			for (var i = startStateIndex; i < endStateIndex; i++) {
				// We get the starting index of the current value in the value trace.
				val startIndex = valueTrace.getActiveValueStartingState(i)
				if (startIndex != valueStartIndex) {
					// If it points to a new value
					if (valueStartIndex != -1) {
						// If we have a current value
						result.add(
							new StateWrapper(valueTrace.getActiveValue(valueStartIndex), valueStartIndex,
								valueTrace.getActiveValueIndex(valueStartIndex), i - 1))
					}
					valueStartIndex = startIndex
				}
			}
			// If the last value does not end before the endStateIndex parameter,
			// we iterate until we find the actual end of the value.
			if (valueStartIndex != -1) {
				var i = endStateIndex
				var endIndex = traceManager.getTraceSize() - 1
				var found = false
				while (i < traceManager.getTraceSize() && !found) {
					val startIndex = valueTrace.getActiveValueStartingState(i)
					if (startIndex != valueStartIndex) {
						endIndex = i - 1
						found = true
					}
					i++
				}
				result.add(
					new StateWrapper(valueTrace.getActiveValue(valueStartIndex), valueStartIndex,
						valueTrace.getActiveValueIndex(valueStartIndex), endIndex))
			}
		}
		return result
	}

	override getStepsForStates(int startingState, int endingState) {
		if (traceManager == null)
			return Collections.EMPTY_LIST
		return traceManager.getStepsForStates(startingState, endingState)
	}

	override getTextAt(int traceIndex) {
		if (traceIndex == 0) {
			return "All execution states (" + traceManager.getTraceSize() + ")"

		} else {

			val trace = traceManager.allValueTraces.get(traceIndex - 1)
			val value = trace.getValue(0)
			if (value == null) {
				return ""
			}
			val container = value.eContainer()
			val List<String> attributes = container.eClass().getEAllReferences().filter [ r |
				r.getName().endsWith("Sequence")
			].map[r|r.getName().substring(0, r.getName().length() - 8)].toList
			var attributeName = ""
			if (!attributes.isEmpty()) {
				attributeName = attributes.filter[s|value.class.name.contains("_" + s + "_")].get(0)
			}
			val originalObject = container.eClass.EAllReferences.findFirst[r|r.name.equals("originalObject")]
			if (originalObject != null) {
				val o = container.eGet(originalObject)
				if (o instanceof EObject) {
					val eObject = o as EObject
					val qname = nameprovider.getFullyQualifiedName(eObject)
					if (qname == null) {
						return attributeName + " (" + eObject.toString() + ")"
					} else {
						return attributeName + " (" + qname.toString() + " :" + eObject.eClass.name + ")"
					}
				}
			}

			return attributeName
		}
	}

	override getTextAt(int traceIndex, int indexInTrace) {
		if (traceIndex == 0) {
			return traceManager.getDescriptionOfExecutionState(indexInTrace)
		} else {
			var result = ""
			try {
				result +=
					traceManager.getContainedValue(
						traceManager.allValueTraces.get(traceIndex - 1).getValue(indexInTrace))
			} catch (IllegalStateException e) {
				e.printStackTrace()
				result = traceManager.getDescriptionOfValue(
					traceManager.allValueTraces.get(traceIndex - 1).getValue(indexInTrace))
			}
			return result
		}
	}

	override getTraceLength(int traceIndex) {
		if (traceManager == null) {
			return 0
		}
		if (traceIndex == 0)
			return traceManager.traceSize
		else
			return traceManager.allValueTraces.get(traceIndex - 1).size
	}

	override isInReplayMode() {
		return stepIntoResult != null
	}

	override jump(EObject o) {
		if (traceManager == null)
			return;
		jump(traceManager.getStateOrValueIndex(o))
	}

	override jump(int i) {
		if (traceManager == null)
			return;
		if (i < traceManager.traceSize) {
			val List<IStep> rootSteps = traceManager.getStepsForStates(i, i)
			val List<IStep> searchPath = new ArrayList
			var IStep firstStateStep = null
			if (!rootSteps.empty) {
				var IStep currentStep = rootSteps.get(0)
				var List<IStep> siblingSteps = rootSteps
				while (firstStateStep == null) {
					if (currentStep.startingIndex < i &&
						(currentStep.endingIndex > i || currentStep.endingIndex == -1)) {
						if (currentStep.subSteps.empty) {
							throw new IllegalStateException("Unreachable state")
						} else {
							searchPath.add(0, currentStep)
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
							searchPath.add(0, currentStep)
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
	}

	override setTraceManager(ITraceManager traceManager) {
		this.traceManager = traceManager
	}

	private var IStep stepIntoResult
	private var IStep stepOverResult
	private var IStep stepReturnResult

	private var IStep stepBackIntoResult
	private var IStep stepBackOverResult
	private var IStep stepBackOutResult

	private var IStep stepInto
	private var IStep stepOver
	private var IStep stepReturn

//	private var IStep stepBackInto
//	private var IStep stepBackOver
//	private var IStep stepBackOut
	def private void computeExplorerState(List<Object> path) {
		val List<IStep> rootSteps = traceManager.getStepsForStates(0, traceManager.traceSize)
		val List<IStep> stepPath = new ArrayList;
		val List<IStep> currentSteps = new ArrayList;
		currentSteps.addAll(rootSteps);
		path.forEach [ o |
			val IStep step = currentSteps.findFirst[s|s.getParameters().get("this") == o]
			currentSteps.clear();
			if (step != null) {
				stepPath.add(step);
				currentSteps.addAll(step.subSteps);
			}
		]

		val stepPathUnmodifiable = stepPath.unmodifiableView

		stepIntoResult = computeStepInto(stepPathUnmodifiable, rootSteps)
		stepOverResult = computeStepOver(stepPathUnmodifiable, rootSteps)
		stepReturnResult = computeStepReturn(stepPathUnmodifiable, rootSteps)

		stepBackIntoResult = computeBackInto(stepPathUnmodifiable, rootSteps)
		stepBackOverResult = computeBackOver(stepPathUnmodifiable, rootSteps)
		stepBackOutResult = computeBackOut(stepPathUnmodifiable, rootSteps)

		callStack.clear
		callStack.addAll(stepPathUnmodifiable)

		stepOver = callStack.last

		if (!stepOver.subSteps.empty) {
			stepInto = stepOver.subSteps.get(0)
		} else {
			stepInto = null
		}

		if (callStack.size > 1) {
			stepReturn = callStack.get(callStack.size - 2)
		} else {
			stepReturn = null
		}

		notifyListeners
	}

	override public loadLastState() {
		var steps = getStepsForStates(lastIndex, lastIndex)
		var IStep lastStep = null
		while (!steps.empty) {
			lastStep = steps.last
			steps = lastStep.subSteps
		}
		if (lastStep.endingIndex == -1)
			jumpBeforeStep(lastStep)
	}

	def private computeBackInto(List<IStep> stepPath, List<IStep> rootSteps) {
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
				val previousStep = parentSubSteps.get(idx - 1)
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
				val previousStep = rootSteps.get(idx - 1)
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

	def private computeBackOver(List<IStep> stepPath, List<IStep> rootSteps) {
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
				// Otherwise, return the previous sibling step
				result = parentSubSteps.get(idx - 1)
			}
		} else if (stepPath.size == 1) {
			val currentStep = stepPath.get(0)
			val idx = rootSteps.indexOf(currentStep)
			if (idx > 0) {
				result = rootSteps.get(idx - 1)
			}
		}
		return result
	}

	def private computeBackOut(List<IStep> stepPath, List<IStep> rootSteps) {
		if (stepPath.size > 1) {
			return stepPath.get(stepPath.size - 2)
		}
		return null
	}

	def private findNextStep(List<IStep> stepPath, List<IStep> rootSteps, IStep previousStep, int start) {
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

	def private computeStepInto(List<IStep> stepPath, List<IStep> rootSteps) {
		return findNextStep(stepPath.reverseView, rootSteps, null, 0)
	}

	def private computeStepOver(List<IStep> stepPath, List<IStep> rootSteps) {
		if (!stepPath.empty) {
			val reversedPath = stepPath.reverseView
			return findNextStep(reversedPath, rootSteps, reversedPath.get(0), 1)
		}
		return null
	}

	def private computeStepReturn(List<IStep> stepPath, List<IStep> rootSteps) {
		if (stepPath.size > 1) {
			val reversedPath = stepPath.reverseView
			return findNextStep(reversedPath, rootSteps, reversedPath.get(1), 2)
		}
		return null
	}

	override public boolean canStepBackInto() {
		return stepBackIntoResult != null
	}

	override public boolean canStepBackOver() {
		return stepBackOverResult != null
	}

	override public boolean canStepBackOut() {
		return stepBackOutResult != null
	}

	override public boolean stepBackInto() {
		if (stepBackIntoResult != null) {
			jumpBeforeStep(stepBackIntoResult)
			return true
		}
		return false
	}

	override public boolean stepBackOver() {
		if (stepBackOverResult != null) {
			jumpBeforeStep(stepBackOverResult)
			return true
		}
		return false
	}

	override public boolean stepBackOut() {
		if (stepBackOutResult != null) {
			jumpBeforeStep(stepBackOutResult)
			return true
		}
		return false
	}

	override public boolean stepInto() {
		if (stepIntoResult != null) {
			jumpBeforeStep(stepIntoResult)
			return true
		}
		return false
	}

	override public boolean stepOver() {
		if (stepOverResult != null) {
			jumpBeforeStep(stepOverResult)
			return true
		}
		return false
	}

	override public boolean stepReturn() {
		if (stepReturnResult != null) {
			jumpBeforeStep(stepReturnResult)
			return true
		}
		return false
	}

	def private void jumpBeforeStep(IStep step) {
		if (step != null) {
			// Jumping to the correct state
			val i = step.startingIndex
			if (i == lastIndex) {
				lastJumpIndex = -1
			} else {
				lastJumpIndex = i
			}
			traceAddon.goTo(i)
			// Computing the new callstack
			val newPath = new ArrayList
			newPath.add(step.parameters.get("this"))
			var parent = step.parentStep
			while (parent != null) {
				newPath.add(0, parent.parameters.get("this"))
				parent = parent.parentStep
			}
			computeExplorerState(newPath)
		}
	}

	def private getPreviousValueIndex(IValueTrace valueTrace) {
		val currentValueIndex = valueTrace.getActiveValueIndex(currentStateIndex)
		var stateIndex = currentStateIndex - 1
		var valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		while (stateIndex > 0 && (valueIndex == currentValueIndex || valueIndex == -1)) {
			stateIndex--
			valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		}
		return valueIndex
	}

	override public canBackValue(int traceIndex) {
		val index = traceIndex - 1
		val allValueTraces = traceManager.allValueTraces
		if (index < allValueTraces.size && index > -1) {
			val valueTrace = allValueTraces.get(index)
			val currentValueIndex = valueTrace.getActiveValueIndex(currentStateIndex)
			var stateIndex = currentStateIndex
			var valueIndex = valueTrace.getActiveValueIndex(stateIndex)
			while (stateIndex > 0 && (valueIndex == currentValueIndex || valueIndex == -1)) {
				stateIndex--
				valueIndex = valueTrace.getActiveValueIndex(stateIndex)
			}
			return valueIndex != currentValueIndex && valueIndex != -1
		}
		return false
	}

	override public backValue(int traceIndex) {
		val valueTrace = traceManager.allValueTraces.get(traceIndex - 1)
		jump(valueTrace.getValue(getPreviousValueIndex(valueTrace)))
	}

	def private getNextValueIndex(IValueTrace valueTrace) {
		var stateIndex = currentStateIndex
		val currentValueIndex = valueTrace.getActiveValueIndex(stateIndex)
		var valueIndex = currentValueIndex
		while (stateIndex < lastIndex && (valueIndex == currentValueIndex || valueIndex == -1)) {
			stateIndex++
			valueIndex = valueTrace.getActiveValueIndex(stateIndex)
		}
		if (valueIndex == currentValueIndex || valueIndex == -1) {
			return valueTrace.size
		}
		return valueIndex
	}

	override public canStepValue(int traceIndex) {
		return true
	}

	override public stepValue(int traceIndex) {
		val valueTrace = traceManager.allValueTraces.get(traceIndex - 1)
		val i = getNextValueIndex(valueTrace)
		if (i < valueTrace.size) {
			jump(valueTrace.getValue(i))
		} else {
			// TODO
		}
	}

	def private getLastIndex() {
		return traceManager.traceSize - 1
	}

	override getCallStack() {
		return callStack
	}

	override updateCallStack(MSEOccurrence mseOccurrence) {
		val newPath = new ArrayList
		newPath.add(mseOccurrence)
		var container = mseOccurrence.eContainer
		while (container != null && container instanceof MSEOccurrence) {
			newPath.add(0, container)
			container = container.eContainer
		}
		computeExplorerState(newPath)
	}

	override update() {
		notifyListeners
	}
	
	override getCurrentBackwardStep() {
		return stepBackOverResult
	}
	
	override getCurrentBigStep() {
		return stepBackOutResult
	}
	
	override getCurrentForwardStep() {
		return callStack.last
	}
	
}