package fr.inria.diverse.trace.gemoc.api;

import fr.inria.diverse.trace.api.ITraceManager;

public interface IGemocTraceManager extends ITraceManager{

	public boolean addStep(org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence mseOccurrence);
	
}
