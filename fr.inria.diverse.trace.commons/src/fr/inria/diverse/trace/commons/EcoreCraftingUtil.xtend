package fr.inria.diverse.trace.commons

import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier

class EcoreCraftingUtil {
	public static def EReference addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
		return res
	}
}
