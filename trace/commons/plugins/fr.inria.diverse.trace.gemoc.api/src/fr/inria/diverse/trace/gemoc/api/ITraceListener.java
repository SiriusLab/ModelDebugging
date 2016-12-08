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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface ITraceListener {
	
	void statesAdded(List<EObject> states);
	
	void stepsStarted(List<EObject> steps);
	
	void stepsEnded(List<EObject> steps);
	
	void valuesAdded(List<EObject> values);
	
	void dimensionsAdded(List<List<? extends EObject>> dimensions);
}
