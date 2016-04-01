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

import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;


public class DefaultEngineAddon implements IEngineAddon {

	@Override
	public void engineAboutToStart(IBasicExecutionEngine engine) 
	{
	}

	@Override
	public void engineStarted(IBasicExecutionEngine executionEngine) 
	{
	}

	@Override
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) 
	{
	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine, LogicalStep selectedLogicalStep) 
	{
	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) 
	{
	}


	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine executionEngine, LogicalStep logicalStepToApply) 
	{
	}


	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) 
	{
	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engineRunnable, RunStatus newStatus) 
	{
	}

	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) 
	{
	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine, LogicalStep logicalStepExecuted) 
	{
	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) 
	{
	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine,
			Collection<LogicalStep> logicalSteps) {
	}

	@Override
	public void engineAboutToDispose(IBasicExecutionEngine engine) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return new ArrayList<String>();
	}
}
