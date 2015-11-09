package fr.inria.diverse.trace.api.impl;

import java.util.HashMap;
import java.util.Map;

import fr.inria.diverse.trace.api.IStep;

public class GenericStep implements IStep {

	private final Map<String, Object> parameters;
	private final String operationName;
	private final String containingClassName;

	public GenericStep(String containingClassName, String operationName) {
		this.parameters = new HashMap<String, Object>();
		this.operationName = operationName;
		this.containingClassName = containingClassName;
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

}
