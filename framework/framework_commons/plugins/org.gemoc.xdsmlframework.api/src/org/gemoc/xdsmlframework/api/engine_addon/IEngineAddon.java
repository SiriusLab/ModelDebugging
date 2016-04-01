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

import java.util.Collection;
import java.util.List;

import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;

public interface IEngineAddon {

	/**
	 * Operation called before the engine starts
	 */
	public void engineAboutToStart(IBasicExecutionEngine engine);
	/**
	 * Operation called after the engine have started
	 */
	public void engineStarted(IBasicExecutionEngine executionEngine);

	
	public void engineAboutToStop(IBasicExecutionEngine engine);
	/**
	 * Operation called after the engine has been stopped
	 */
	public void engineStopped(IBasicExecutionEngine engine);
	
	/**
	 * Operation before the engine has been disposed (and after the engine has been stopped)
	 */
	public void engineAboutToDispose(IBasicExecutionEngine engine);
	
	
	/**
	 * Operation called before the LogicalStep has been chosen
	 */
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps);
	
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps);
	
	/**
	 * Operation called after the LogicalStep has been chosen
	 * It also returns the chosen LogicalStep
	 */
	public void logicalStepSelected(IBasicExecutionEngine engine, LogicalStep selectedLogicalStep);
	

	public void aboutToExecuteLogicalStep(IBasicExecutionEngine engine, LogicalStep logicalStepToExecute);
	public void logicalStepExecuted(IBasicExecutionEngine engine, LogicalStep logicalStepExecuted);


	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence);
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence);

	public void engineStatusChanged(IBasicExecutionEngine engine, RunStatus newStatus);	
	
	/**
	 * This operation check the current addon compatibility with elements in 'otherAddons'
	 * 
	 * @return A list of error messages if the check failed or an empty list otherwise.
	 */
	public List<String> validate(List<IEngineAddon> otherAddons);
	
}
