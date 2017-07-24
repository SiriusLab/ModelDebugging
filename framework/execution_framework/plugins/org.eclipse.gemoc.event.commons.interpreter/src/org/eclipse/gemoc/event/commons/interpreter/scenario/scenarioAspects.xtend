package org.eclipse.gemoc.event.commons.interpreter.scenario

import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider
import org.eclipse.gemoc.event.commons.model.scenario.ElementQuery
import org.eclipse.gemoc.event.commons.model.scenario.ElementReference
import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.EObject
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod
import org.eclipse.gemoc.event.commons.interpreter.property.StatePropertyAspect

@Aspect(className=ElementProvider)
class ElementProviderAspect {
	public def EObject resolve(Resource r) {
		return null
	}
}

@Aspect(className=ElementReference)
class ElementReferenceAspect extends ElementProviderAspect {
	@OverrideAspectMethod
	public def EObject resolve(Resource r) {
		return _self.element as EObject
	}
}

@Aspect(className=ElementQuery)
class ElementQueryAspect extends ElementProviderAspect {
	@OverrideAspectMethod
	public def EObject resolve(Resource r) {
		val queriedType = _self.eClass.EGenericSuperTypes.head.ETypeArguments.head.EClassifier
		return r.allContents.filter[o|(o.eClass == queriedType || o.eClass.EAllSuperTypes.contains(queriedType))
			&& StatePropertyAspect.evaluate(_self.query, o)
		].head
	}
}
