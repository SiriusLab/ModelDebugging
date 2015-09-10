/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.debug.ide.tests.event;

import fr.obeo.dsl.debug.ide.event.IDSLDebugEvent;
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * A test implementation of {@link IDSLDebugEventProcessor} that log
 * {@link IDSLDebugEventProcessor#handleEvent(IDSLDebugEvent) handled} {@link IDSLDebugEvent event}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TestEventProcessor implements IDSLDebugEventProcessor {

	/**
	 * Logged {@link IDSLDebugEvent event}.
	 */
	private final List<IDSLDebugEvent> events = new ArrayList<IDSLDebugEvent>();

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor#handleEvent(fr.obeo.dsl.debug.ide.event.IDSLDebugEvent)
	 */
	public Object handleEvent(IDSLDebugEvent event) {
		events.add(event);
		return null;
	}

	public List<IDSLDebugEvent> getEvents() {
		return events;
	}

}
