package org.gemoc.gemoc_language_workbench.api.core;

import java.util.Set;

import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;



/**
 * The interface of the GEMOC Execution Engine. The Execution Engine is an
 * entity able to execute models conforming to an xDSML as defined in the GEMOC
 * ANR INS project. This API allows the caller to initialize the engine for a
 * given model, and to run the engine in different ways. It also allows the
 * caller to influence the constraints of the MoC at runtime.
 * 
 * @author didier.vojtisek@inria.fr
 * 
 */
public interface IBasicExecutionEngine extends IDisposable {

	/**
	 * Starts the {@link IBasicExecutionEngine}.
	 */
	public void start();
	
	/**
	 * Asks the engine to stop
	 */
	void stop();
	
	EngineStatus getEngineStatus();
	
	/**
	 * 
	 * @param type
	 * @return true if the engine has the capability, false otherwise.
	 */
	<T extends IEngineAddon> boolean hasAddon(Class<T> type);
	/**
	 * 
	 * @param type
	 * @return The capability of the given type if it exists.
	 */
	<T extends IEngineAddon> T getAddon(Class<T> type);

	IExecutionContext getExecutionContext();


	
	RunStatus getRunningStatus();
	
	<T extends IEngineAddon> Set<T> getAddonsTypedBy(Class<T> type);
	
	void initialize (IExecutionContext executionContext);
	
	/**
	 * 
	 * @return a user display name for the engine kind (will be used to compute the full name of the engine instance) 
	 */
	String engineKindName();
	
	/** 
	 * 
	 * @return a displayable name to identify this engine
	 */
	public abstract String getName();
}
