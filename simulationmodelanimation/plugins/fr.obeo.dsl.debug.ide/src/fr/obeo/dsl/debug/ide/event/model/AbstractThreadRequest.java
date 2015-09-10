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
package fr.obeo.dsl.debug.ide.event.model;

/**
 * A {@link fr.obeo.dsl.debug.Thread thread} contextual {@link IDSLModelRequest request}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractThreadRequest implements IDSLModelRequest {

	/**
	 * The {@link fr.obeo.dsl.debug.Thread#getName() thread name}.
	 */
	private final String threadName;

	/**
	 * Constructor.
	 * 
	 * @param threadName
	 *            the {@link fr.obeo.dsl.debug.Thread#getName() thread name}
	 */
	public AbstractThreadRequest(String threadName) {
		this.threadName = threadName;
	}

	/**
	 * Gets the {@link fr.obeo.dsl.debug.Thread#getName() thread name}.
	 * 
	 * @return the {@link fr.obeo.dsl.debug.Thread#getName() thread name}
	 */
	public String getThreadName() {
		return threadName;
	}

}
