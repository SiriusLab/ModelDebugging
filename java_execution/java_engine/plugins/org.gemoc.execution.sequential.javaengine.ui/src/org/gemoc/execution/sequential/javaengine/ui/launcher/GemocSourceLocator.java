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
package org.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.obeo.dsl.debug.ide.DSLSourceLocator;
import fr.obeo.dsl.debug.ide.adapter.DSLStackFrameAdapter;

public class GemocSourceLocator extends DSLSourceLocator {

	@Override
	public Object getSourceElement(IStackFrame stackFrame) {
		final Object res;
		if (stackFrame instanceof DSLStackFrameAdapter) {
			final DSLStackFrameAdapter eStackFrame = (DSLStackFrameAdapter) stackFrame;
			final EObject instruction = eStackFrame.getCurrentInstruction();
			if (instruction instanceof Step) {
				res = ((Step) instruction).getMseoccurrence().getMse();
			} else if (instruction != null) {
				res = instruction;
			} else {
				res = eStackFrame.getContext();
			}
		} else {
			res = null;
		}
		return res;
	}
}
