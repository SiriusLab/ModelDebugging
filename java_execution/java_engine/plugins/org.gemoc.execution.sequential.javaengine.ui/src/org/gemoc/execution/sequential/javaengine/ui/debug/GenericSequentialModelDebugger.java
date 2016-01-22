package org.gemoc.execution.sequential.javaengine.ui.debug;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.gemoc.executionframework.engine.core.EngineStoppedException;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.ui.debug.AbstractGemocDebugger;
import org.gemoc.executionframework.engine.ui.debug.breakpoint.GemocBreakpoint;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.ISequentialExecutionEngine;

import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public class GenericSequentialModelDebugger extends AbstractGemocDebugger {

	/**
	 * A fake instruction to prevent the stepping return to stop on each event.
	 */
	private static final EObject FAKE_INSTRUCTION = EcorePackage.eINSTANCE;

	protected final String threadName = "Model debugging";

	protected int nbStackFrames = 0;
	
	protected boolean executionTerminated = false;

	public GenericSequentialModelDebugger(IDSLDebugEventProcessor target, ISequentialExecutionEngine engine) {
		super(target, engine);
	}

	@Override
	public boolean control(String threadName, EObject instruction) {
		if (!isTerminated() && instruction instanceof LogicalStep) {
			return true;
		} else {
			return super.control(threadName, instruction);
		}
	}

	@Override
	/*
	 * This method is eventually called within a new engine thread.
	 * (non-Javadoc)
	 * 
	 * @see fr.obeo.dsl.debug.ide.IDSLDebugger#start()
	 */
	public void start() {
		engine.start();
	}

	@Override
	public void disconnect() {
		return;
	}

	protected void setupStepReturnPredicateBreak() {
		final ISequentialExecutionEngine seqEngine = (ISequentialExecutionEngine) engine;
		final Deque<MSEOccurrence> stack = seqEngine.getCurrentStack();
		if (stack.size() > 1) {
			final Iterator<MSEOccurrence> it = stack.iterator();
			it.next();
			addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
				// The operation we want to step return
				private MSEOccurrence steppedReturn = it.next();

				@Override
				public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
					// We finished stepping over once the mseoccurrence is not
					// there anymore
					return !seqEngine.getCurrentStack().contains(steppedReturn);
				}
			});
		}
	}

	@Override
	public void steppingReturn(String threadName) {
		// To send notifications
		super.steppingReturn(threadName);
		// We add a future break as soon as the step is returned
		setupStepReturnPredicateBreak();
	}

	protected void setupStepOverPredicateBreak() {
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
			final ISequentialExecutionEngine seqEngine = (ISequentialExecutionEngine) engine;
			// The operation we want to step over
			private MSEOccurrence steppedOver = seqEngine.getCurrentMSEOccurrence();

			@Override
			public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
				// We finished stepping over once the mseoccurrence is not there
				// anymore
				return !seqEngine.getCurrentStack().contains(steppedOver);
			}
		});
	}

	@Override
	public void steppingOver(String threadName) {
		// To send notifications
		super.steppingOver(threadName);
		// We add a future break as soon as the step is over
		setupStepOverPredicateBreak();
	}

	@Override
	public boolean canStepInto(String threadName, EObject instruction) {
		// TODO generate code to test small/big step
		return currentInstructions.get(threadName) == instruction;
	}

	@Override
	public void steppingInto(String threadName) {
		// To send notifications, but probably useless
		super.steppingInto(threadName);
		// We add a future break asap
		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
			@Override
			public boolean test(IBasicExecutionEngine t, MSEOccurrence u) {
				// We finished stepping as soon as we encounter a new step
				return true;
			}
		});
	}

	Deque<String> stackFrameNames = new ArrayDeque<>();
	
	@Override
	public void pushStackFrame(String threadName, String frameName, EObject context, EObject instruction) {
		super.pushStackFrame(threadName, frameName, context, instruction);
		stackFrameNames.push(frameName);
		nbStackFrames++;
	}

	@Override
	public void popStackFrame(String threadName) {
		super.popStackFrame(threadName);
		stackFrameNames.pop();
		nbStackFrames--;
	}
	
//	@Override
//	public void variable(String threadName, String stackName, String declarationTypeName,
//			String variableName, Object value, boolean supportModifications) {
//		for (String stackFrameName : stackFrameNames) {
//			super.variable(threadName, stackFrameName, declarationTypeName,
//					variableName, value, supportModifications);
//		}
//	}

	protected void updateStack(String threadName, EObject instruction) {
		// Catching the stack up with events that occurred since last suspension
		// We use a virtual stack to replay the last events to avoid pushing
		// stackframes that would be popped right after.
		Deque<MSEOccurrence> virtualStack = new ArrayDeque<>();
		for (ToPushPop m : toPushPop) {
			if (m.push) {
				// We push the mse onto the virtual stack.
				virtualStack.push(m.mseOccurrence);
			} else {
				if (virtualStack.isEmpty()) {
					// The virtual stack is empty, we pop the top stackframe off
					// of the real stack.
					popStackFrame(threadName);
				} else {
					// The virtual stack is not empty, we pop the top stackframe
					// off of it.
					virtualStack.pop();
				}
			}
		}

		// We then push the missing stackframes onto the real stack.
		Iterator<MSEOccurrence> iterator = virtualStack.descendingIterator();
		while (iterator.hasNext()) {
			MSEOccurrence mseOccurrence = iterator.next();
			EObject caller = mseOccurrence.getMse().getCaller();
			String name = caller.eClass().getName() + " (" + mseOccurrence.getMse().getName() + ") ["
					+ caller.toString() + "]";
			pushStackFrame(threadName, name, caller, caller);
		}

		setCurrentInstruction(threadName, instruction);

		toPushPop.clear();
	}

	@Override
	public void updateData(String threadName, EObject instruction) {
		if (instruction == null) {
			updateVariables(threadName);
			updateStack(threadName, null);
			return;
		}

		// We don't want to deal with logical steps since we are in sequential mode
		if (instruction instanceof LogicalStep) {
			instruction = ((LogicalStep) instruction).getMseOccurrences().get(0).getMse().getCaller();
		} else if (instruction instanceof MSEOccurrence) {
			instruction = ((MSEOccurrence) instruction).getMse().getCaller();
		}

		super.updateData(threadName, instruction);
	}

	@Override
	public boolean shouldBreak(EObject instruction) {
		if (instruction instanceof MSEOccurrence) {
			return shouldBreakMSEOccurence((MSEOccurrence) instruction);
		} else if (instruction == FAKE_INSTRUCTION) {
			// Part of the breakpoint simulation to suspend the execution once the end has been reached. 
			return true;
		}
		return false;
	}

	private boolean hasRegularBreakpointTrue(EObject o) {
		return super.shouldBreak(o)
				&& (Boolean.valueOf((String) getBreakpointAttributes(o, GemocBreakpoint.BREAK_ON_LOGICAL_STEP)) || Boolean
						.valueOf((String) getBreakpointAttributes(o, GemocBreakpoint.BREAK_ON_MSE_OCCURRENCE)));
	}

	private boolean shouldBreakMSEOccurence(MSEOccurrence mseOccurrence) {
		if (shouldBreakPredicates(engine, mseOccurrence))
			return true;
		// If still no break yet, we look at regular breakpoints on MSE
		MSE mse = mseOccurrence.getMse();
		if (hasRegularBreakpointTrue(mse)) {
			return true;
		}
		// If still no break yet, we look at regular breakpoints on MSE's caller
		EObject caller = mseOccurrence.getMse().getCaller();
		if (hasRegularBreakpointTrue(caller)) {
			return true;
		}
		return false;
	}

	@Override
	public EObject getNextInstruction(String threadName, EObject currentInstruction, Stepping stepping) {
		// We always return fakeinstruction to make sure to not stop and to
		// handle everything with shouldBreak
		return FAKE_INSTRUCTION;
	}

	@Override
	public void engineStarted(IBasicExecutionEngine executionEngine) {
		spawnRunningThread(threadName, engine.getExecutionContext().getResourceModel().getContents().get(0));
	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) {
		if (!isTerminated(threadName)) {
			terminated(threadName);
		}
	}

	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine executionEngine, LogicalStep logicalStepToApply) {
		// if (!control(Thread.currentThread().getName(), logicalStepToApply)) {
		// throw new EngineStoppedException("Debug thread has stopped.");
		// }
	}

	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		ToPushPop stackModification = new ToPushPop(mseOccurrence, true);
		toPushPop.add(stackModification);
		if (!control(threadName, mseOccurrence)) {
			throw new EngineStoppedException("Debug thread has stopped.");
		}
	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		ToPushPop stackModification = new ToPushPop(mseOccurrence, false);
		toPushPop.add(stackModification);
	}
	
	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) {
		// Simulating breakpoint
		// TODO maybe display a warning informing the user the execution has ended,
		// as resuming execution will prevent further interactions with the trace and the
		// debugging facilities, which might not be desirable.
		executionTerminated = true;
		control(threadName, FAKE_INSTRUCTION);
	}

	@Override
	public void terminate() {
		super.terminate();
		engine.stop();
	}

	private static class ToPushPop {
		public MSEOccurrence mseOccurrence;
		public boolean push;

		ToPushPop(MSEOccurrence mseOccurrence, boolean push) {
			this.mseOccurrence = mseOccurrence;
			this.push = push;
		}
	}

	List<ToPushPop> toPushPop = new ArrayList<>();

}
