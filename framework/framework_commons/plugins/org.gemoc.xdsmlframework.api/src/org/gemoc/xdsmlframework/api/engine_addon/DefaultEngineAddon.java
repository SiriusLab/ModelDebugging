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
package org.gemoc.xdsmlframework.api.engine_addon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;

import fr.inria.diverse.trace.commons.model.trace.Step;

public class DefaultEngineAddon implements IEngineAddon {

	@Override
	public void engineAboutToStart(IBasicExecutionEngine engine) {
	}

	@Override
	public void engineStarted(IBasicExecutionEngine executionEngine) {
	}

	@Override
	public void aboutToSelectStep(IBasicExecutionEngine engine, Collection<Step> steps) {
	}

	@Override
	public void stepSelected(IBasicExecutionEngine engine, Step selectedStep) {
	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) {
	}

	@Override
	public void aboutToExecuteStep(IBasicExecutionEngine executionEngine, Step stepToApply) {
	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engineRunnable, RunStatus newStatus) {
	}

	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) {
	}

	@Override
	public void stepExecuted(IBasicExecutionEngine engine, Step stepExecuted) {
	}

	@Override
	public void proposedStepsChanged(IBasicExecutionEngine engine, Collection<Step> steps) {
	}

	@Override
	public void engineAboutToDispose(IBasicExecutionEngine engine) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return new ArrayList<String>();
	}
}
