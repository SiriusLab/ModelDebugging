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

import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.trace.commons.model.trace.Step;

public interface IExecutionEngine extends IBasicExecutionEngine{

	public abstract IExecutionContext getExecutionContext();

	public abstract EngineStatus getEngineStatus();
	
	public abstract RunStatus getRunningStatus();

	public abstract void notifyEngineAboutToStart();

	public abstract void notifyEngineStarted();

	public abstract void notifyAboutToStop();

	public abstract void notifyEngineStopped();

	public abstract void notifyEngineAboutToDispose();

	public abstract void notifyAboutToExecuteLogicalStep(Step l);

	public abstract void notifyLogicalStepExecuted(Step l);

	public abstract <T extends IEngineAddon> boolean hasAddon(Class<T> type);

	public abstract <T extends IEngineAddon> T getAddon(Class<T> type);

	public abstract void setEngineStatus(RunStatus stopped);


}