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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fr.inria.diverse.trace.api.IStep;

public class PartiallyLazyGenericStep implements IStep {
	
	private Map<String, Object> parameters;
	private String operationName;
	private String containingClassName;
	private int endingIndex;
	private int startingIndex;
	private IStep parentStep;
	private List<IStep> subSteps;
	private Function<IStep,List<IStep>> supplier;
	private Predicate<IStep> predicate;
	private boolean resolved = false;

	public PartiallyLazyGenericStep(String containingClassName, String operationName, int startingIndex,
			int endingState, IStep parentStep, Function<IStep,List<IStep>> supplier) {
		this.parameters = new HashMap<String, Object>();
		this.operationName = operationName;
		this.containingClassName = containingClassName;
		this.startingIndex = startingIndex;
		this.endingIndex = endingState;
		this.parentStep = parentStep;
		this.supplier = supplier;
		this.predicate = null;
	}
	
	public PartiallyLazyGenericStep(String containingClassName, String operationName, int startingIndex,
			int endingState, IStep parentStep, Function<IStep,List<IStep>> supplier, Predicate<IStep> predicate) {
		this.parameters = new HashMap<String, Object>();
		this.operationName = operationName;
		this.containingClassName = containingClassName;
		this.startingIndex = startingIndex;
		this.endingIndex = endingState;
		this.parentStep = parentStep;
		this.supplier = supplier;
		this.predicate = predicate;
	}
	
	private void resolve() {
		if (predicate != null) {
			subSteps = supplier.apply(this).stream().filter(predicate).collect(Collectors.toList());
		} else {
			subSteps = supplier.apply(this);
		}
		resolved = true;
		supplier = null;
		predicate = null;
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
		if (!resolved) {
			resolve();
		}
		return subSteps;
	}

	@Override
	public void addParameter(String name, Object value) {
		parameters.put(name, value);
	}
}
