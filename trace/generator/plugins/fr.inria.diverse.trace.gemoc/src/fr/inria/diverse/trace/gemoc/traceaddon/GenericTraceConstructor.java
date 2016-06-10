package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.commons.model.trace.TraceFactory;
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor;

public class GenericTraceConstructor implements ITraceConstructor {

	private Trace<SequentialStep<Step>> traceRoot;
	private Resource traceResource;
	private final Deque<Step> context = new LinkedList<Step>();
	
	public GenericTraceConstructor(Resource traceResource) {
		this.traceResource = traceResource;
	}
	
	@Override
	public void addState(List<ModelChange> modelChanges) {
		// Empty implementation, states are not yet supported in the generic trace.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addStep(Step step) {
		if (context.isEmpty()) {
			traceRoot.getRootStep().getSubSteps().add(step);
			context.push(step);
		} else {
			final Step topStep = context.getFirst();
			if (topStep != null && topStep instanceof SequentialStep<?>) {
				((SequentialStep<Step>) topStep).getSubSteps().add(step);
			}
		}
	}

	@Override
	public void endStep(Step step) {
		context.pop();
	}

	@Override
	public EObject initTrace(LaunchConfiguration launchConfiguration) {
		
		// Create root
		traceRoot = TraceFactory.eINSTANCE.createTrace();
		traceRoot.setLaunchconfiguration(launchConfiguration);

		// Create root sequential step
		SequentialStep<Step> rootStep = TraceFactory.eINSTANCE.createSequentialStep();
		traceRoot.setRootStep(rootStep);

		// Put in the resource
		traceResource.getContents().add(traceRoot);

		return traceRoot;
	}

	@Override
	public void save() {
		try {
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(URI uri) {
		try {
			traceResource.setURI(uri);
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isPartialTraceConstructor() {
		return false;
	}

}
