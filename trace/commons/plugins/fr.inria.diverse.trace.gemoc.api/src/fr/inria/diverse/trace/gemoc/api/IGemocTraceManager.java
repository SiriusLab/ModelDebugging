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

import org.eclipse.emf.ecore.EObject;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;

public interface IGemocTraceManager extends ITraceManager {

	boolean addStep(MSEOccurrence mseOccurrence);

	default void addState() {
		throw new IllegalStateException(
				"The old addState method should not be used with new trace managers! Regenerate a trace manager.");
	}

	default boolean addStateIfChanged() {
		throw new IllegalStateException(
				"The old addState method should not be used with new trace managers! Regenerate a trace manager.");
	}

	default void addState(Set<ModelChange> modelChanges) {
		// For now we do nothing by default, so that former trace managers can keep compiling
		throw new IllegalStateException(
				"The new addState method should not be used with old trace managers! Regenerate a trace manager.");
	}

	public abstract void setTraceRoot(EObject object);
}
