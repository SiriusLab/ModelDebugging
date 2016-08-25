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
package org.gemoc.xdsmlframework.api.core;

import java.util.Deque;
import java.util.Set;

import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;

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
public interface IExecutionEngine extends IDisposable {
	
	Deque<MSEOccurrence> getCurrentStack();

	MSEOccurrence getCurrentMSEOccurrence();

	/**
	 * Starts the {@link IExecutionEngine}.
	 */
	void start();

	/**
	 * Asks the engine to stop
	 */
	void stop();

	EngineStatus getEngineStatus();
	
	void setEngineStatus(RunStatus status);

	/**
	 * 
	 * @param type
	 * @return true if the engine has the addon, false otherwise.
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

	<T> Set<T> getAddonsTypedBy(Class<T> type);

	void initialize(IExecutionContext executionContext);
	
	default LaunchConfiguration extractLaunchConfiguration() {
		return null;
	}

	/**
	 * 
	 * @return a user display name for the engine kind (will be used to compute
	 *         the full name of the engine instance)
	 */
	String engineKindName();

	/**
	 * 
	 * @return a displayable name to identify this engine
	 */
	String getName();
}
