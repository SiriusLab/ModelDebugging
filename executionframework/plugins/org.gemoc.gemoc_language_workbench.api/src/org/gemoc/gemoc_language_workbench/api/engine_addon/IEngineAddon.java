package org.gemoc.gemoc_language_workbench.api.engine_addon;

import java.util.Collection;

import org.gemoc.execution.engine.mse.engine_mse.LogicalStep;
import org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

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
	
}
