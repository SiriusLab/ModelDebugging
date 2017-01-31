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
package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor;

public class GenericTraceConstructor implements ITraceConstructor {

//	private Trace<Step<?>, TracedObject<?>, State<?,?>> traceRoot;
//	private Resource traceResource;
//	private final Deque<Step<?>> context = new LinkedList<Step<?>>();
	
	private GenericTraceConstructor() {
		
	}
	
	@Override
	public void addState(List<ModelChange> modelChanges) {
		throw new UnsupportedOperationException();
		// Empty implementation, states are not yet supported in the generic trace.
	}

//	private List<? extends Step<?>> getSubSteps(Step<?> step) {
//		if (step instanceof BigStep<?,?>) {
//			return ((BigStep<?,?>) step).getSubSteps();
//		} else {
//			return Collections.emptyList();
//		}
//	}
	
	@Override
	public void addStep(Step<?> step) {
		throw new UnsupportedOperationException();
//		if (context.isEmpty()) {
//			final List<Step<?>> tmp = new ArrayList<>(getSubSteps(traceRoot.getRootStep()));
//			tmp.add(step);
//			context.push(step);
//		} else {
//			final Step<?> topStep = context.getFirst();
//			if (topStep != null && topStep instanceof BigStep<?,?>) {
//				((BigStep<?,?>) topStep).getSubSteps().add(step);
//			}
//		}
	}

	@Override
	public void endStep(Step<?> step) {
		throw new UnsupportedOperationException();
//		context.pop();
	}

	@Override
	public EObject initTrace(LaunchConfiguration launchConfiguration) {
		throw new UnsupportedOperationException();
//		// Create root
//		traceRoot = TraceFactory.eINSTANCE.createGenericTrace();
//		traceRoot.setLaunchconfiguration(launchConfiguration);
//
//		// Create root sequential step
//		GenericSequentialStep rootStep = TraceFactory.eINSTANCE.createGenericSequentialStep();
//		traceRoot.setRootStep(rootStep);
//
//		// Put in the resource
//		traceResource.getContents().add(traceRoot);
//
//		return traceRoot;
	}

	@Override
	public void save() {
		throw new UnsupportedOperationException();
//		try {
//			traceResource.save(null);
//		} catch (java.io.IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void save(URI uri) {
		throw new UnsupportedOperationException();
//		try {
//			traceResource.setURI(uri);
//			traceResource.save(null);
//		} catch (java.io.IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public boolean isPartialTraceConstructor() {
		return false;
	}

}
