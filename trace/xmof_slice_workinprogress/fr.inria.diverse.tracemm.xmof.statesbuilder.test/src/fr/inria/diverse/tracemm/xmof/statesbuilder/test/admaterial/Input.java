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
package fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

public class Input {
	
	private List<Resource> inputResourcesLeft = new ArrayList<>();
	private List<Resource> inputResourcesRight = new ArrayList<>();

	public void addInputResources(Resource inputResourceLeft, Resource inputResourceRight) {
		inputResourcesLeft.add(inputResourceLeft);
		inputResourcesRight.add(inputResourceRight);
	}
	
	public List<Resource> getInputResourcesLeft() {
		return inputResourcesLeft;
	}
	
	public List<Resource> getInputResourcesRight() {
		return inputResourcesRight;
	}
	
}
