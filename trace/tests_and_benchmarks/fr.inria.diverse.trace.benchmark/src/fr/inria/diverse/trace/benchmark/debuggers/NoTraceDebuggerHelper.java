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

import org.gemoc.gemoc_language_workbench.extensions.k3.PlainK3ExecutionEngine;

import fr.inria.diverse.trace.benchmark.Language;
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper;

public class NoTraceDebuggerHelper implements IDebuggerHelper {

	@Override
	public boolean canJump() {
		return false;
	}

	@Override
	public void jump(int i) {

	}

	@Override
	public int getTraceSize() {
		return 0;
	}

	@Override
	public int getTraceMemoryFootprint(Language l, File dumpFolder, int traceSize) {
		return 0;
	}

	@Override
	public String getDebuggerName() {
		return "NoTraceDebuggerHelper";
	}

	@Override
	public Collection<? extends String> getAddons() {
		return Collections.emptySet();
	}

	@Override
	public void setExecutionEngine(PlainK3ExecutionEngine _executionEngine) {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public int computeTraceMemoryFootprint(Language l, File dumpFile, int traceSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unloadTraceResource() {
		// TODO Auto-generated method stub
		
	}

}
