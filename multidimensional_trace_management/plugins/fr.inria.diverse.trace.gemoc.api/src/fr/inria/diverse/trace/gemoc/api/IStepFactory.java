package fr.inria.diverse.trace.gemoc.api;

import java.util.List;

public interface IStepFactory {

	public org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence createMSEOccurrence(
			org.gemoc.execution.engine.mse.engine_mse.MSE mse, List<Object> parameters, List<Object> result);
	
}
