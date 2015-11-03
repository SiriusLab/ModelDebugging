package fr.inria.diverse.trace.commons

import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EPackage

class EcoreCraftingUtil {
	public static def EReference addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def EReference addReferenceToClass(EClass clazz, String refName, EClass refType) {
		if (clazz != null && refName != null && refName != "" && refType != null) {
			val res = EcoreFactory.eINSTANCE.createEReference
			res.name = refName
			res.EType = refType
			clazz.EStructuralFeatures.add(res)
			return res
		} else {
			return null
		}

	}

	public static def EAttribute addAttributeToClass(EClass clazz, String attName, EDataType attType) {
		val res = EcoreFactory.eINSTANCE.createEAttribute
		res.name = attName
		res.EType = attType
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def EStructuralFeature addFeatureToClass(EClass clazz, String name, EClassifier type) {
		var EStructuralFeature res = null
		if (type instanceof EDataType)
			res = EcoreFactory.eINSTANCE.createEAttribute
		else if (type instanceof EClass)
			res = EcoreFactory.eINSTANCE.createEReference
		res.name = name
		res.EType = type
		clazz.EStructuralFeatures.add(res)
		return res
	}

	public static def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	public static def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name
		}
	}

}
