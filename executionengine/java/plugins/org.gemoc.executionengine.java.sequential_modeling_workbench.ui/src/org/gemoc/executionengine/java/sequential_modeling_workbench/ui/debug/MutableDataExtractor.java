package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface MutableDataExtractor {

	public List<MutableData> extractMutableData(EObject eObject);
	
}
