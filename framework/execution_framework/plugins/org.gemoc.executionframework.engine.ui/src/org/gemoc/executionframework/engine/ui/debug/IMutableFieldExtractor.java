package org.gemoc.executionframework.engine.ui.debug;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface IMutableFieldExtractor {

	public List<MutableField> extractMutableField(EObject eObject);
	
}
