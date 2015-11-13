package fr.inria.diverse.trace.api;

import java.util.Map;

public interface IStep {

	Map<String, Object> getParameters();

	String getContainingClassName();
	
	String getOperationName();

	void addParameter(String name, Object value);

	int getEndingIndex();
	
	int getStartingIndex();
}
