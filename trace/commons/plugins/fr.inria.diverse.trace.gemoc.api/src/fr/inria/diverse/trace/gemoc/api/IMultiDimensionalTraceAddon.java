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
package fr.inria.diverse.trace.gemoc.api;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.obeo.timeline.view.ITimelineProvider;

public interface IMultiDimensionalTraceAddon extends IEngineAddon {

	void goTo(EObject state);

	void goToNoTimelineNotification(int i);

	void goTo(int i);

	ITimelineProvider getTimeLineProvider();

	void setTimeLineNotifier(ISimpleTimeLineNotifier prov);

	fr.inria.diverse.trace.gemoc.api.IGemocTraceManager getTraceManager();

	IStepFactory getFactory();

}
