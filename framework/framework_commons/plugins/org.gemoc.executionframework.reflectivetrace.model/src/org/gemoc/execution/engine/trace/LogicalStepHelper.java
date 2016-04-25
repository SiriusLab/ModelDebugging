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
package org.gemoc.execution.engine.trace;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.Step;


public class LogicalStepHelper {

	public static String getLogicalStepName(Step step) {
		//returns the emf id or the java id if the emf id is null
		return "LogicalStep ["+(EcoreUtil.getID(step) !=  null ? EcoreUtil.getID(step):System.identityHashCode(step)) +"]";
	}

	public static List<MSE> getMSEs(Step instruction) {
		ArrayList<MSE> l = new ArrayList<MSE>();
		l.add(instruction.getMseoccurrence().getMse());
		return l;
	}
	
}
