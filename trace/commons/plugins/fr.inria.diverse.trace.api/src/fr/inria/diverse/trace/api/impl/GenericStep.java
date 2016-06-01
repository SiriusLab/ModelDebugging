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
package fr.inria.diverse.trace.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.diverse.trace.api.IStep;

public class GenericStep implements IStep {

	private final Map<String, Object> parameters;
	private final String operationName;
	private final String containingClassName;
	private final int endingIndex;
	private final int startingIndex;
	private final IStep parentStep;
	private final List<IStep> subSteps;

	public GenericStep(String containingClassName, String operationName, int startingIndex,
			int endingState, IStep parentStep, List<IStep> subSteps) {
		this.parameters = new HashMap<String, Object>();
		this.operationName = operationName;
		this.containingClassName = containingClassName;
		this.startingIndex = startingIndex;
		this.endingIndex = endingState;
		this.parentStep = parentStep;
		this.subSteps = subSteps;
	}

	@Override
	public Map<String, Object> getParameters() {
		return parameters;
	}

	@Override
	public String getOperationName() {
		return operationName;
	}

	@Override
	public String getContainingClassName() {
		return containingClassName;
	}

	@Override
	public void addParameter(String name, Object value) {
		parameters.put(name, value);
	}

	@Override
	public int getEndingIndex() {
		return endingIndex;
	}

	@Override
	public int getStartingIndex() {
		return startingIndex;
	}

	@Override
	public IStep getParentStep() {
		return parentStep;
	}

	@Override
	public List<IStep> getSubSteps() {
		return subSteps;
	}

}
