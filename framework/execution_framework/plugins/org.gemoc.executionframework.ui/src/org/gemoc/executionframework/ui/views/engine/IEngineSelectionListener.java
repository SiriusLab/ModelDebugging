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
package org.gemoc.executionframework.ui.views.engine;

import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;

public interface IEngineSelectionListener {

	/***
	 * Notify when engine is selected by user.
	 * @param engine The selected engine.
	 */
	public void engineSelectionChanged(IBasicExecutionEngine engine);
	
}
