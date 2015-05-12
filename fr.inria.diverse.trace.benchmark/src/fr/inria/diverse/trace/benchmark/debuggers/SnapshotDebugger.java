package fr.inria.diverse.trace.benchmark.debuggers;

import java.util.Collection;
import java.util.Collections;

import org.gemoc.execution.engine.commons.trace.ModelExecutionTracingAddon;
import org.gemoc.execution.engine.commons.trace.ModelExecutionTracingException;
import org.gemoc.execution.engine.core.PlainK3ExecutionEngine;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Branch;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Choice;

import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;

public class SnapshotDebugger extends AbstractTraceDebugger implements IDebuggerHelper {

	public SnapshotDebugger() {
		super("org.gemoc.execution.engine.trace.gemoc_execution_trace.impl.ExecutionTraceModelImpl");
		
	}

	ModelExecutionTracingAddon traceAddon;


	@Override
	public boolean canJump() {
		return true;
	}

	@Override
	/*
	 * (non-Javadoc) Code taken from branchIfPossible of TimeLineView
	 * 
	 * @see fr.inria.diverse.trace.benchmark.api.IDebuggerHelper#jump(int)
	 */
	public void jump(int i) {

		Choice c = traceAddon.getExecutionTrace().getBranches().get(0).getChoices().get(i);
		Choice choice = c.getSelectedNextChoice();

		try {
			Choice previousChoice = choice.getPreviousChoice();
			Branch previousBranch = previousChoice.getBranch();
			// if the choice is the last before last one, then branch
			if (previousBranch.getChoices().indexOf(previousChoice) == (previousBranch.getChoices().size() - 2)) {
				traceAddon.reintegrateBranch(choice);
			} else {
				traceAddon.branch(choice);
			}
		} catch (ModelExecutionTracingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getTraceSize() {
		return traceAddon.getExecutionTrace().getBranches().get(0).getChoices().size();
	}

	@Override
	public String getDebuggerName() {
		return "SnapshotDebugger";
	}

	@Override
	public void setExecutionEngine(PlainK3ExecutionEngine executionEngine) {
		traceAddon = executionEngine.getAddon(ModelExecutionTracingAddon.class);
		traceAddon.disableTraceSaving();

	}

	@Override
	public Collection<? extends String> getAddons() {
		return Collections.singleton("Execution tracing");
	}

}
