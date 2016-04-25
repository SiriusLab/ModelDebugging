package org.gemoc.execution.sequential.javaengine.ui.debug;

import fr.inria.diverse.trace.api.IStep
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer
import fr.inria.diverse.trace.gemoc.api.ITraceListener
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor
import java.util.ArrayList
import java.util.List
import java.util.function.BiPredicate
import java.util.function.Supplier
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
import org.gemoc.executionframework.engine.mse.Step
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine

public class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger implements ITraceListener {

	private val ITraceExplorer traceExplorer

	private var steppingOverStackFrameIndex = -1

	private var steppingReturnStackFrameIndex = -1

	private val List<EObject> callerStack = new ArrayList

	new(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine, ITraceExplorer traceExplorer) {
		super(target, engine)
		this.traceExplorer = traceExplorer
		this.traceExplorer.addListener(this)
	}

	def private MSE getMSEFromStep(EObject caller, IStep step) {
		var MSE mse
		if (caller instanceof MSEOccurrence) {
			mse = (caller as MSEOccurrence).mse
		} else {
			mse = (engine as AbstractSequentialExecutionEngine).findOrCreateMSE(caller, step.containingClassName,
				step.operationName)
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
			mse = getMSEFromStep(entryValue, step)
			name = mse.name
		} else {
			val parentStep = step.parentStep.parameters.get("this") as EObject
			mse = getMSEFromStep(parentStep, step)
			name = mse.name + "_implicitStep"
		}
		caller = mse.caller
		name = caller.eClass().getName() + " (" + name + ") [" + caller.toString() + "]"

		val DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider()
		val QualifiedName qname = nameprovider.getFullyQualifiedName(caller)
		val String objectName = if(qname !== null) qname.toString() else caller.toString()
		val String opName = mse.action?.name
		val String callerType = caller.eClass().getName()
		val String prettyName = "(" + callerType + ") " + objectName + " -> " + opName + "()"
		pushStackFrame(threadName, prettyName, caller, caller)
		callerStack.add(0, caller)
	}

	override void popStackFrame(String threadName) {
		super.popStackFrame(threadName)
		callerStack.remove(0)
	}
	
	override void aboutToExecuteStep(IBasicExecutionEngine executionEngine, Step step) {
		val mseOccurrence = step.mseoccurrence
		if (mseOccurrence != null) {
			if (!control(threadName, mseOccurrence)) {
				throw new EngineStoppedException("Debug thread has stopped.");
			}
		}
		for (Supplier<Boolean> supplier : traceExplorer.breakPredicates) {
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				override test(IBasicExecutionEngine t, MSEOccurrence u) {
					return supplier.get();
				}
			})
		}
		traceExplorer.breakPredicates.clear
	}

	override public void resume() {
		if (!executionTerminated) {
			if (traceExplorer.inReplayMode) {
				traceExplorer.loadLastState
			}
			super.resume
		}
	}

	override public void resume(String threadName) {
		if (!executionTerminated) {
			if (traceExplorer.inReplayMode) {
				traceExplorer.loadLastState
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

	private val List<Object> previousCallStack = new ArrayList
	private val List<IStep> newCallStack = new ArrayList

	override public void stepInto(String threadName) {
		if (traceExplorer.inReplayMode) {
			if (!traceExplorer.stepInto) {
				traceExplorer.loadLastState
				super.stepInto(threadName)
			}
		} else {
			super.stepInto(threadName)
		}
	}

	override public void stepOver(String threadName) {
		if (traceExplorer.inReplayMode) {
			if (!traceExplorer.stepOver) {
				steppingOverStackFrameIndex = nbStackFrames - 1
				traceExplorer.loadLastState
				super.stepOver(threadName)
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
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
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
		if (traceExplorer.inReplayMode) {
			if (!traceExplorer.stepReturn) {
				steppingReturnStackFrameIndex = nbStackFrames - 2
				traceExplorer.loadLastState
				super.stepReturn(threadName)
			}
		} else {
			super.stepReturn(threadName)
		}
	}

	def public void stepBackInto() {
		traceExplorer.stepBackInto
	}

	def public void stepBackOver() {
		traceExplorer.stepBackOver
	}

	def public void stepBackOut() {
		traceExplorer.stepBackOut
	}

	def public boolean canStepBackInto() {
		return traceExplorer.canStepBackInto
	}

	def public boolean canStepBackOver() {
		return traceExplorer.canStepBackOver
	}

	def public boolean canStepBackOut() {
		return traceExplorer.canStepBackOut
	}

//	TODO
//	def private setupStepValuePredicateBreak(IValueTrace valueTrace, int valueIndex) {
//		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
//			override test(IBasicExecutionEngine t, MSEOccurrence u) {
//				val i = valueTrace.getActiveValueIndex(currentStateIndex)
//				val j = valueIndex
//				return i == j
//			}
//		});
//	}
	override public validateVariableValue(String threadName, String variableName, String value) {
		if (traceExplorer.inReplayMode) {
			ErrorDialog.openError(null, "Illegal variable value set",
				"Cannot set the value of a variable when in replay mode",
				new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Illegal variable value set"))
			return false
		}
		return super.validateVariableValue(threadName, variableName, value)
	}

	override void engineStarted(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = [this]
		super.engineStarted(executionEngine)
	}

	override void engineAboutToStop(IBasicExecutionEngine engine) {
		super.engineAboutToStop(engine)
		traceExplorer.loadLastState
	}

	override void engineStopped(IBasicExecutionEngine executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = null
		super.engineStopped(executionEngine)
	}

	override updateStack(String threadName, EObject instruction) {
		val obj_callStack = newCallStack.map[s|s.parameters.get("this")]
		var i = 0
		while (i < previousCallStack.size && i < obj_callStack.size && previousCallStack.get(i) == obj_callStack.get(i))
			i++
		for (var j = i; j < previousCallStack.size; j++)
			popStackFrame(threadName)
		if (!callerStack.empty)
			setCurrentInstruction(threadName, callerStack.get(0))
		for (var j = i; j < newCallStack.size; j++)
			pushStackFrame(threadName, newCallStack.get(j))
		previousCallStack.clear
		previousCallStack.addAll(obj_callStack)
	}

	override update() {
		newCallStack.clear
		newCallStack.addAll(traceExplorer.callStack)
		try {
			val obj_callStack = newCallStack.map[s|s.parameters.get("this")]
			var i = 0
			while (i < previousCallStack.size && i < obj_callStack.size &&
				previousCallStack.get(i) == obj_callStack.get(i))
				i++
			for (var j = i; j < previousCallStack.size; j++)
				popStackFrame(threadName)
			if (!callerStack.empty)
				setCurrentInstruction(threadName, callerStack.get(0))
			for (var j = i; j < newCallStack.size; j++)
				pushStackFrame(threadName, newCallStack.get(j))
			previousCallStack.clear
			previousCallStack.addAll(obj_callStack)
			scheduleSelectLastStackframe(500)
		} catch (IllegalStateException e) {
			// Shhh
		}
	}
}
