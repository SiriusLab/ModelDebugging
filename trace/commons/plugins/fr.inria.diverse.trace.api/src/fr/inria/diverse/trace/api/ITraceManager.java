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
package fr.inria.diverse.trace.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceManager {

	void save();
	
	void save(URI uri);

	void addState();

	boolean addStateIfChanged();

	void addStep(String stepRuleFQN, Map<String, Object> params);

	void endStep(String stepNameFQN, Object returnValue);

	void initTrace();

	int getTraceSize();

	void goTo(int index);

	void goTo(EObject stateOrValue);

	EObject getExecutionState(int index);

	String getDescriptionOfExecutionState(int index);

	String currentBigStep();

	int getNumberOfValueTraces();

	List<IValueTrace> getAllValueTraces();

	String getDescriptionOfValue(EObject value);

	default Object getContainedValue(EObject value) {
		// For now we do nothing by default, so that former trace managers can
		// keep compiling
		throw new IllegalStateException(
				"The new getContainedValue method should not be used with old trace managers! Regenerate a trace manager to use it.");
	};

	Set<EObject> getAllCurrentValues(int stateIndex);

	int getStateOrValueIndex(EObject stateOrValue);

	List<? extends Step> getStepsForStates(int startingState, int endingState);
	
	default boolean isPartialTraceManager() {
		return false;
	}

}
