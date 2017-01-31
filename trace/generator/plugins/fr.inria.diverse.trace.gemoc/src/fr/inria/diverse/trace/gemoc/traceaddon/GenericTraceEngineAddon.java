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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.BatchModelChangeListener;

import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.gemoc.api.IStateManager;
import fr.inria.diverse.trace.gemoc.api.IStepFactory;
import fr.inria.diverse.trace.gemoc.api.ITraceConstructor;
import fr.inria.diverse.trace.gemoc.api.ITraceNotifier;

public class GenericTraceEngineAddon extends AbstractTraceAddon {

//	private GenericTraceStepFactory factory = null;
	
	@Override
	public IStepFactory getFactory() {
		throw new UnsupportedOperationException();
//		if (factory == null) {
//			factory = new GenericTraceStepFactory();
//		}
//		return factory;
	}

	@Override
	public ITraceConstructor constructTraceConstructor(Resource exeModel,
			Resource traceResource, Map<EObject, EObject> exeToTraced) {
		throw new UnsupportedOperationException();
//		return new GenericTraceConstructor(traceResource);
	}

	@Override
	public boolean isAddonForTrace(EObject traceRoot) {
		throw new UnsupportedOperationException();
//		return true;
	}

	@Override
	public ITraceNotifier constructTraceNotifier(BatchModelChangeListener traceListener) {
		throw new UnsupportedOperationException();
//		return null;
	}

	@Override
	public IStateManager<State<?, ?>> constructStateManager(Resource modelResource, Map<EObject, EObject> tracedToExe) {
		throw new UnsupportedOperationException();
//		return null;
	}

}
