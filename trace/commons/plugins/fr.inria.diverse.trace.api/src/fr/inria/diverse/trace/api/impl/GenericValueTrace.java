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
	public int getCurrentIndex(int stateIndex) {
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
	public EObject getValue(int index) {
		return values.get(index);
	}

	private static List<EObject> intersect(Collection<? extends EObject> s1, Collection<? extends EObject> s2) {
		List<EObject> copy = new ArrayList<EObject>(s1);
		copy.retainAll(s2);
		return copy;
	}

}
