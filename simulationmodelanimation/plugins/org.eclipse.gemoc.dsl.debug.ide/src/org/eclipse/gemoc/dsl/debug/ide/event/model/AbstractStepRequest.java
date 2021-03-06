/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
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
 * Request sent to step the execution of the debugger.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractStepRequest extends AbstractThreadRequest {

	/**
	 * The {@link EObject} representing the current instruction. NOTE: this instruction should only be used
	 * for synchronization checks.
	 */
	private final EObject instruction;

	/**
	 * Constructor.
	 * 
	 * @param threadName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Thread#getName() thread name}
	 * @param instruction
	 *            the {@link EObject} representing the current instruction
	 */
	public AbstractStepRequest(String threadName, EObject instruction) {
		super(threadName);
		this.instruction = instruction;
	}

	/**
	 * Gets the {@link EObject} representing the current instruction.
	 * 
	 * @return the {@link EObject} representing the current instruction
	 */
	public EObject getInstrcution() {
		return instruction;
	}

}
