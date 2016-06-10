package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.List;

import fr.inria.diverse.trace.commons.model.trace.MSE;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TraceFactory;
import fr.inria.diverse.trace.gemoc.api.IStepFactory;

public class GenericTraceStepFactory implements IStepFactory {

	@Override
	public Step createStep(MSE mse, List<Object> parameters, List<Object> result) {
		return TraceFactory.eINSTANCE.createSequentialStep();
	}

}
