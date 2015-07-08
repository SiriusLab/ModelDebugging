package fr.inria.diverse.tracemm.xmof2tracematerial

import ecorext.Ecorext
import ecorext.EcorextFactory
import java.util.ArrayList
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass

class XmofExecutionExtensionGenerator {

	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) Ecorext mmextensionResult

	protected val Resource xmofModel
	protected boolean done = false
	protected Copier copier

	// All the eclasses of the original mm
	var Set<EClass> ecoreClasses

	new(Set<EPackage> ecore, Resource xmofModel, Copier copier) {
		this.xmofModel = xmofModel
		this.ecoreClasses = ecore.map[p|p.eAllContents.toSet].flatten.filter(EClass).toSet
		this.copier = copier
	}

	new(Resource ecoreModel, Resource xmofModel, Copier copier) {
		this(ecoreModel.contents.filter(EPackage).toSet, xmofModel, copier)
	}

	protected def EClass findExtendedClass(EClass confClass) {
		var EClass res = null
		val otherResultsFar = new HashSet<EClass>
		for (superType : confClass.ESuperTypes.filter[c|c != confClass]) {

			if(ecoreClasses.contains(superType))
				res = superType
			else {
				var indirectSuperType = findExtendedClass(superType)
				if(indirectSuperType != null)
					otherResultsFar.add(indirectSuperType)
			}
		}
		if(res != null)
			return res
		else if(otherResultsFar.size > 0) {
			return otherResultsFar.get(0)
		} else {
			return null
		}
	}

	protected def EPackage obtainExtensionPackage(EPackage runtimePackage) {

		// Null means that the root is the Ecorext object
		var EPackage result = null

		if(runtimePackage != null) {

			val tracedSuperPackage = obtainExtensionPackage(runtimePackage.ESuperPackage)

			if(tracedSuperPackage == null)
				result = mmextensionResult.newPackages.findFirst[p|p.name.equals(runtimePackage.name)]
			else
				result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(runtimePackage.name)]

			if(result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = runtimePackage.name
				result.nsURI = result.name // TODO
				result.nsPrefix = "" // TODO

				if(tracedSuperPackage == null) {
					mmextensionResult.newPackages.add(result)
				} else
					tracedSuperPackage.ESubpackages.add(result)
			}

		}
		return result
	}

	protected def void computeMMExtension() {

		// What we want to create
		mmextensionResult = EcorextFactory.eINSTANCE.createEcorext

		// For each class of the xmof model
		for (EClass xmofClass : xmofModel.allContents.filter(EClass).filter[c|!(c instanceof Activity)].toSet) {

			// We find the extended class of the abstract syntax
			val extendedClass = findExtendedClass(xmofClass)

			// Either we found extended classes, in which case this is a class extension
			if(xmofClass instanceof BehavioredEClass && extendedClass != null) {

				println("Found a class inheriting a class of the ecore model! " + xmofClass)

				// Store for later the mapping xmof -> ecore
				copier.put(xmofClass, extendedClass)

				val allProperties = new HashSet<EStructuralFeature>
				allProperties.addAll(xmofClass.EReferences)
				allProperties.addAll(xmofClass.EAttributes)

				// But we truly have class extensions only if there are new properties
				if(allProperties.size > 0) {

					// Create class extension
					val classExt = EcorextFactory.eINSTANCE.createClassExtension
					classExt.extendedExistingClass = extendedClass
					mmextensionResult.classesExtensions.add(classExt)

					// In the class extension, we copy structural features of the conf class
					val copied = copier.copyAll(allProperties)
					classExt.newProperties.addAll(copied)

				}

			}
			// Or not, in which case this is a new class
			else {

				println("Found new class! " + xmofClass)

				var EClass copiedClass = null;

				// Either this is a behaviored EClass
				if(xmofClass instanceof BehavioredEClass) {

					// We remove the behaviors from the BehavioredEClass, temporarily
					val temporaryRemovedClassifierBehavior = xmofClass.classifierBehavior
					val temporaryRemovedOwnedBehavior = new ArrayList
					temporaryRemovedOwnedBehavior.addAll(xmofClass.ownedBehavior)
					xmofClass.classifierBehavior = null
					xmofClass.ownedBehavior.clear

					// Then we copy the BehavioredEClass into a new one (that has no behaviors)
					val BehavioredEClass temporaryCopy = copier.copy(xmofClass) as BehavioredEClass

					// And we restore the behaviors in the copied BehavioredEClass, in order to find activities later 
					xmofClass.classifierBehavior = temporaryRemovedClassifierBehavior
					xmofClass.ownedBehavior.addAll(temporaryRemovedOwnedBehavior)

					// Finally we convert the copy into a standard EClass
					copiedClass = behavioredToNormal(temporaryCopy)

					// And we replace the copy/copied pair in the copier
					copier.put(xmofClass, copiedClass)
				} 

				// Or a normal one
				else {
					copiedClass = copier.copy(xmofClass) as EClass
				}

				copiedClass.EOperations.clear
				val EPackage containingPackage = obtainExtensionPackage(xmofClass.EPackage)
				containingPackage.EClassifiers.add(copiedClass)

				// Store for later the mapping xmof -> ecorext
				copier.put(xmofClass, copiedClass)
			}
		}

	}

	/*
	 * Note: we consider the xmof model to be immutable (we reuse its objects without copying them)
	 */
	protected static def EClass behavioredToNormal(BehavioredEClass b) {
		val res = EcoreFactory.eINSTANCE.createEClass

		for (prop : res.eClass.EAllStructuralFeatures) {
			val value = b.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)
			try {
				res.eSet(prop, value)
			} catch(Exception e) {
			}
		}

		return res
	}

}
