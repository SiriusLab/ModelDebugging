package org.gemoc.gemoc_language_workbench.api.core;

import org.gemoc.execution.engine.mse.engine_mse.LogicalStep;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

public interface IExecutionEngine extends IBasicExecutionEngine{

	public abstract IExecutionContext getExecutionContext();

	public abstract EngineStatus getEngineStatus();
	
	public abstract RunStatus getRunningStatus();

	public abstract void notifyEngineAboutToStart();

	public abstract void notifyEngineStarted();

	public abstract void notifyAboutToStop();

	public abstract void notifyEngineStopped();

	public abstract void notifyEngineAboutToDispose();

	public abstract void notifyAboutToExecuteLogicalStep(LogicalStep l);

	public abstract void notifyLogicalStepExecuted(LogicalStep l);

	public abstract <T extends IEngineAddon> boolean hasAddon(Class<T> type);

	public abstract <T extends IEngineAddon> T getAddon(Class<T> type);

	public abstract void setEngineStatus(RunStatus stopped);


}