package org.gemoc.xdsmlframework.commons

import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EClass

class DynamicAnnotationHelper {
	public static val DYNAMIC_ANNOTATION_URI = "aspect"

	private static def boolean isDynamic(EModelElement o) {
		return o.EAnnotations.exists[a|a.source.equals(DYNAMIC_ANNOTATION_URI)]
	}

	public static def boolean isDynamic(EClass c) {
		return isDynamic(c as EModelElement)
	}

	public static def boolean isDynamic(EStructuralFeature p) {
		return isDynamic(p as EModelElement) || isDynamic(p.EContainingClass)
	}
}