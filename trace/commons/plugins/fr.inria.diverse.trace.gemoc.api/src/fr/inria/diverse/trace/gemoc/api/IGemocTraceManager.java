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
package fr.inria.diverse.trace.gemoc.api;

import java.util.Set;

import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.api.ITraceManager;

public interface IGemocTraceManager extends ITraceManager {

	boolean addStep(org.gemoc.executionframework.engine.mse.MSEOccurrence mseOccurrence);
	default void addState(Set<ModelChange> modelChanges) {
		// For now we do nothing by default, so that former trace managers can keep compiling
		throw new IllegalStateException("The new addState method should not be used with old trace managers! Disable the new add state, or regenerate a trace manager.");
	};
}
