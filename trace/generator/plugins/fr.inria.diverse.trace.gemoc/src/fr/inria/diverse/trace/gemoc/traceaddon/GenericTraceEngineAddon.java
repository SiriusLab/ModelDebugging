package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.gemoc.api.IStepFactory;
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;

public class GenericTraceEngineAddon extends AbstractTraceAddon {

	private GenericTraceStepFactory factory = null;
	
	@Override
	public IStepFactory getFactory() {
		if (factory == null) {
			factory = new GenericTraceStepFactory();
		}
		return factory;
	}

	@Override
	public ITraceConstructor constructTraceConstructor(Resource exeModel,
			Resource traceResource, Map<EObject, EObject> exeToTraced) {
		return new GenericTraceConstructor(traceResource);
	}

	@Override
	public boolean isAddonForTrace(EObject traceRoot) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ITraceExplorer constructTraceExplorer(Resource traceResource) {
		GenericTraceExplorer explorer = new GenericTraceExplorer();
		EObject root = traceResource.getContents().get(0);
		if (root instanceof Trace<?>) {
			explorer.loadTrace((Trace<SequentialStep<Step>>)root);
			return explorer;
		}
		return null;
	}

	@Override
	public ITraceExplorer constructTraceExplorer(Resource modelResource,
			Resource traceResource, Map<EObject, EObject> tracedToExe) {
		return constructTraceExplorer(traceResource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ITraceExtractor constructTraceExtractor(Resource traceResource) {
		GenericTraceExtractor extractor = new GenericTraceExtractor();
		EObject root = traceResource.getContents().get(0);
		if (root instanceof Trace<?>) {
			extractor.loadTrace((Trace<SequentialStep<Step>>)root);
			return extractor;
		}
		return null;
	}

}
