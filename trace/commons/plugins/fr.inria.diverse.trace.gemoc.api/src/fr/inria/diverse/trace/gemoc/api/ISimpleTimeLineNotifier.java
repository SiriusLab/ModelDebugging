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

import fr.inria.diverse.trace.api.ITraceManager;
import fr.obeo.timeline.view.ITimelineProvider;

public interface ISimpleTimeLineNotifier extends ITimelineProvider {

	void notifyTimeLine();

	void setTraceManager(ITraceManager m);

}
