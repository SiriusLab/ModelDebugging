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
package fr.inria.diverse.trace.api.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.api.IValueTrace;

public class GenericValueTrace implements IValueTrace {

	List<? extends EObject> values;
	ITraceManager manager;

	public GenericValueTrace(List<? extends EObject> values, ITraceManager manager) {
		this.values = values;
		this.manager = manager;
	}

	@Override
	public int getSize() {
		return values.size();
	}

	@Override
	public int getActiveValueIndex(int stateIndex) {
		int realStateIndex = stateIndex;
		if (stateIndex == -1) {
			realStateIndex = manager.getTraceSize() - 1;
		}
		List<EObject> currentValues = intersect(manager.getAllCurrentValues(realStateIndex), values);
		if (!currentValues.isEmpty()) {
			EObject currentValue = currentValues.get(0);
			return values.indexOf(currentValue);
		} else {
			return -1;
		}
	}
	
	@Override
	public EObject getActiveValue(int stateIndex) {
		int realStateIndex = stateIndex;
		if (stateIndex == -1) {
			realStateIndex = manager.getTraceSize() - 1;
		}
		List<EObject> currentValues = intersect(manager.getAllCurrentValues(realStateIndex), values);
		if (!currentValues.isEmpty()) {
			EObject currentValue = currentValues.get(0);
			return currentValue;
		} else {
			return null;
		}
	}
	
	@Override
	public int getActiveValueStartingState(int stateIndex) {
		int realStateIndex = stateIndex;
		if (stateIndex == -1) {
			realStateIndex = manager.getTraceSize() - 1;
		}
		List<EObject> currentValues = intersect(manager.getAllCurrentValues(realStateIndex), values);
		if (!currentValues.isEmpty()) {
			EObject currentValue = currentValues.get(0);
			return manager.getStateOrValueIndex(currentValue);
		} else {
			return -1;
		}
	}

	@Override
	public EObject getValue(int index) {
		if (index > -1 && index < values.size()) {
			return values.get(index);
		}
		return null;
	}

	private static List<EObject> intersect(Collection<? extends EObject> s1, Collection<? extends EObject> s2) {
		List<EObject> copy = new ArrayList<EObject>(s1);
		copy.retainAll(s2);
		return copy;
	}

}
