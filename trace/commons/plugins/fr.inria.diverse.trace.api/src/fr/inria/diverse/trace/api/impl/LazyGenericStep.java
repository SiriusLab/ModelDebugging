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

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import fr.inria.diverse.trace.api.IStep;

public class LazyGenericStep implements IStep {
	
	private Map<String, Object> parameters;
	private String operationName;
	private String containingClassName;
	private int endingIndex;
	private int startingIndex;
	private IStep parentStep;
	private List<IStep> subSteps;
	private Supplier<IStep> supplier;
	private boolean resolved = false;
	
	public LazyGenericStep(Supplier<IStep> stepSupplier) {
		supplier = stepSupplier;
	}
	
	private void resolve() {
		IStep internalStep = supplier.get();
		parameters = internalStep.getParameters();
		operationName = internalStep.getOperationName();
		containingClassName = internalStep.getContainingClassName();
		endingIndex = internalStep.getEndingIndex();
		startingIndex = internalStep.getStartingIndex();
		parentStep = internalStep.getParentStep();
		subSteps = internalStep.getSubSteps();
		resolved = true;
		supplier = null;
	}

	@Override
	public Map<String, Object> getParameters() {
		if (!resolved) {
			resolve();
		}
		return parameters;
	}

	@Override
	public String getOperationName() {
		if (!resolved) {
			resolve();
		}
		return operationName;
	}

	@Override
	public String getContainingClassName() {
		if (!resolved) {
			resolve();
		}
		return containingClassName;
	}

	@Override
	public int getEndingIndex() {
		if (!resolved) {
			resolve();
		}
		return endingIndex;
	}

	@Override
	public int getStartingIndex() {
		if (!resolved) {
			resolve();
		}
		return startingIndex;
	}

	@Override
	public IStep getParentStep() {
		if (!resolved) {
			resolve();
		}
		return parentStep;
	}

	@Override
	public List<IStep> getSubSteps() {
		if (!resolved) {
			resolve();
		}
		return subSteps;
	}

	@Override
	public void addParameter(String name, Object value) {
		if (!resolved) {
			resolve();
		}
		parameters.put(name, value);
	}
}
