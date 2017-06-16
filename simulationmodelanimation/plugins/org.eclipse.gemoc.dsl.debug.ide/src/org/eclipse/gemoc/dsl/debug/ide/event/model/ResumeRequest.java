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
package org.eclipse.gemoc.dsl.debug.ide.event.model;

/**
 * Request sent to resume the execution of a thread or the debugger.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ResumeRequest extends AbstractThreadRequest {

	/**
	 * Constructor for debugger.
	 */
	public ResumeRequest() {
		super(null);
	}

	/**
	 * Constructor for thread.
	 * 
	 * @param threadName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Thread#getName() thread name}
	 */
	public ResumeRequest(String threadName) {
		super(threadName);
	}

}
