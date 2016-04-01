/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.benchmark.debuggers;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.gemoc.execution.engine.commons.trace.ModelExecutionTracingAddon;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.ModelState;
import org.gemoc.gemoc_language_workbench.extensions.k3.PlainK3ExecutionEngine;

import fr.inria.diverse.trace.benchmark.Language;
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer;
import fr.inria.diverse.trace.benchmark.memory.MemoryAnalyzer.QueryResult;

public class SnapshotDebugger extends AbstractTraceDebugger implements IDebuggerHelper {

	private static final String javaPackageName = "org.gemoc.execution.engine.trace";
	private static final String javaTraceRootName = "org.gemoc.execution.engine.trace.gemoc_execution_trace.impl.ExecutionTraceModelImpl";

	ModelExecutionTracingAddon traceAddon;

	@Override
	public boolean canJump() {
		return true;
	}

	@Override
	public void jump(int i) {

		ModelState state = traceAddon.getExecutionTrace().getReachedStates().get(i);
		traceAddon.jump(state);
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

		System.out.println("Parsing the dump...");
		MemoryAnalyzer analyzer = new MemoryAnalyzer(dumpFile);

		// First we make sure that there is only one trace
		String queryCheck = "SELECT * FROM " + javaTraceRootName;
		
		System.out.println("Query check...");
		QueryResult resCheck = analyzer.computeRetainedSizeWithOQLQuery(queryCheck, dumpFile);

		if (resCheck.nbElements != 1) {
			//System.out.println("SLEEPING WHILE TOO MANY TRACES");
			//Thread.sleep(100000000);
			throw new Exception("Wrong number of traces: " + resCheck.nbElements);
		}

		// Then we prepare the queries
		String queryClones = "SELECT a.@retainedHeapSize FROM "
				+ l.languageRootClassName + " a "
				+ " WHERE ("
				+ " (a.eStorage.toString() LIKE \".*Object.*\")"
				+ " and (a.eStorage[1].toString() LIKE \".*XMI.*\")"
				+ " and (a.eStorage[1].uri.segments[2].toString() LIKE \"gemoc-gen\")"
				+ ")";		
		String queryAll = "select a.@retainedHeapSize from \".*" + javaPackageName + ".*\" a";
		String queryRemove = "select a.@retainedHeapSize from \".*" + javaPackageName
				+ ".*(PackageImpl|FactoryImpl|AdapterFactory|Switch)$\" a";

		// Memory used by trace structure
		System.out.println("Query all...");
		QueryResult resAll = analyzer.computeRetainedSizeWithOQLQuery(queryAll, dumpFile);
		
		System.out.println("Query to remove...");
		QueryResult resRemove = analyzer.computeRetainedSizeWithOQLQuery(queryRemove, dumpFile);

		// Memory used by clones
		System.out.println("Query clones...");
		QueryResult resClones = analyzer.computeRetainedSizeWithOQLQuery(queryClones, dumpFile);

		// To be sure that our weird clone query works
		if (resClones.nbElements != traceSize) {
			//System.out.println("SLEEPING WHILE: "+"Wrong trace size: " + resClones.nbElements + " instead of " + traceSize);
			//Thread.sleep(100000000);
			throw new Exception("Wrong trace size: " + resClones.nbElements + " instead of " + traceSize);
		}

		// Debug prints
		System.out.println("Memory all package: " + resAll.memorySum);
		System.out.println("Memory to remove: " + resRemove.memorySum);
		System.out.println("Memory clones: " + resClones.memorySum);

		return resAll.memorySum - resRemove.memorySum + resClones.memorySum;
	}

	@Override
	public void unloadTraceResource() {
		traceAddon.getExecutionTrace().eResource().unload();
	}

}
