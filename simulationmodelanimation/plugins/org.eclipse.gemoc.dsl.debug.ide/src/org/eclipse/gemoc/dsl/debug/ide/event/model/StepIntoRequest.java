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

import org.eclipse.emf.ecore.EObject;

/**
 * Request sent to step into the current instruction of a thread.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class StepIntoRequest extends AbstractStepRequest {

	/**
	 * Constructor.
	 * 
	 * @param threadName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Thread#getName() thread name}
	 * @param instruction
	 *            the {@link EObject} representing the current instruction
	 */
	public StepIntoRequest(String threadName, EObject instruction) {
		super(threadName, instruction);
	}

}
