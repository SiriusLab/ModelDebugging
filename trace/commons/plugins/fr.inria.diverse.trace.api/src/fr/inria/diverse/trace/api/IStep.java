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
package fr.inria.diverse.trace.api;

import java.util.List;
import java.util.Map;

public interface IStep {
	
	Map<String, Object> getParameters();
	
	String getContainingClassName();
	
	String getOperationName();
	
	void addParameter(String name, Object value);
	
	int getEndingIndex();
	
	int getStartingIndex();
	
	IStep getParentStep();
	
	List<IStep> getSubSteps();
	
}
