package fr.inria.diverse.trace.benchmark.debuggers;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.gemoc.execution.engine.commons.trace.ModelExecutionTracingAddon;
import org.gemoc.execution.engine.commons.trace.ModelExecutionTracingException;
import org.gemoc.execution.engine.core.PlainK3ExecutionEngine;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Branch;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.Choice;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.ModelState;

import fr.inria.diverse.trace.benchmark.Language;
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer.QueryResult;

public class SnapshotDebugger extends AbstractTraceDebugger implements IDebuggerHelper {

	private final String javaPackageName;
	private final String javaTraceRootName;

	public SnapshotDebugger() {
		super();
		javaPackageName = "org.gemoc.execution.engine.trace";
		javaTraceRootName = "org.gemoc.execution.engine.trace.gemoc_execution_trace.impl.ExecutionTraceModelImpl";
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
		
		ModelState state = traceAddon.getExecutionTrace().getReachedStates().get(i);
		traceAddon.jump(state);

		

//		Choice c = state.getContextState().get(0).getChoice();// traceAddon.getExecutionTrace().getBranches().get(0).getChoices().get(i);
//		Choice choice = c.getSelectedNextChoice();
//
//		try {
//			Choice previousChoice = choice.getPreviousChoice();
//			Branch previousBranch = previousChoice.getBranch();
//			// if the choice is the last before last one, then branch
//			if (previousBranch.getChoices().indexOf(previousChoice) == (previousBranch.getChoices().size() - 2)) {
//				traceAddon.reintegrateBranch(choice);
//			} else {
//				traceAddon.branch(choice);
//			}
//		} catch (ModelExecutionTracingException e) {
//			e.printStackTrace();
//		}

	}

	@Override
	public int getTraceSize() {
		return traceAddon.getExecutionTrace().getReachedStates().size();
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

	@Override
	public int computeTraceMemoryFootprint(Language l, File dumpFile, int traceSize) throws Exception {
		
		MemoryAnalyzer analyzer = new MemoryAnalyzer(dumpFile);

		// First we make sure that there is only one trace
		String queryCheck = "SELECT * FROM " + javaTraceRootName;
		QueryResult resCheck = analyzer.computeRetainedSizeWithOQLQuery(queryCheck, dumpFile);
		
		if (resCheck.nbElements != 1) {
			throw new Exception("Wrong number of traces: "+resCheck.nbElements);
		}

		// Then we prepare the queries
		String queryClones = "SELECT a.@retainedHeapSize FROM "
				+ l.languageRootClassName
				+ " a WHERE ((a.eStorage.toString() LIKE \".*Object.*\") and (a.eStorage[1].toString() LIKE \".*XMI.*\"))";
		String queryAll = "select a.@retainedHeapSize from \".*" + javaPackageName + ".*\" a";
		String queryRemove = "select a.@retainedHeapSize from \".*" + javaPackageName
				+ ".*(PackageImpl|FactoryImpl|AdapterFactory|Switch)$\" a";

		// Memory used by trace structure
		QueryResult resAll = analyzer.computeRetainedSizeWithOQLQuery(queryAll, dumpFile);
		QueryResult resRemove = analyzer.computeRetainedSizeWithOQLQuery(queryRemove, dumpFile);

		// Memory used by clones
		QueryResult resClones = analyzer.computeRetainedSizeWithOQLQuery(queryClones, dumpFile);

		// To be sure that our weird clone query works
		if (resClones.nbElements != traceSize) {
			throw new Exception("Wrong trace size: "+resClones.nbElements + "instead of "+traceSize);
		}


		// Debug prints
		System.out.println("Memory all package: " + resAll.memorySum);
		System.out.println("Memory to remove: " + resRemove.memorySum);
		System.out.println("Memory clones: " + resClones.memorySum);

		return resAll.memorySum - resRemove.memorySum + resClones.memorySum;
	}

}
