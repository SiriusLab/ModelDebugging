package fr.inria.diverse.trace.gemoc.api;

import java.util.List;

import fr.inria.diverse.trace.commons.model.trace.MSE;
import fr.inria.diverse.trace.commons.model.trace.Step;

public interface IStepFactory {

	public Step createStep(
			MSE mse, List<Object> parameters, List<Object> result);
	
}
