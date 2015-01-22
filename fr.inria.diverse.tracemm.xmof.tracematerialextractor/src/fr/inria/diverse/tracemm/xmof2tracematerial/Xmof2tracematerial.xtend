package fr.inria.diverse.tracemm.xmof2tracematerial

import ecorext.Ecorext
import ecorext.EcorextFactory
import java.util.HashMap
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass
import java.util.HashSet
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EObject
import java.util.ArrayList

class Xmof2tracematerial {

	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) Ecorext mmextensionResult
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) EPackage eventsmmResult

	protected val Resource ecoreModel
	protected val Resource xmofModel
	protected boolean done = false
	protected Copier copier

	protected val Map<EClass, EClass> xmof2othermap
	protected val Map<EClass, Integer> classesScores
	protected var EClass guessedRootType

	// All the eclasses of the original mm
	var Set<EClass> ecoreClasses

	new(Resource ecoreModel, Resource xmofModel) {
		this.ecoreModel = ecoreModel
		this.xmofModel = xmofModel
		this.xmof2othermap = new HashMap<EClass, EClass>
		this.classesScores = new HashMap<EClass, Integer>
		this.ecoreClasses = ecoreModel.allContents.filter(EClass).toSet

	}

	public def void computeAllMaterial() {
		if (!done) {
			copier = new Copier
			computeMMExtension()
			computeEventMM()
			copier.copyReferences
		} else {
			println("ERROR: already computed.")
		}
	}

	protected def EClass guessRootType() {
		var EClass bestSoFar = null
		var int bestScoreSoFar = -1
		for (someClass : classesScores.keySet) {
			val someScore = classesScores.get(someClass)
			if (someScore > bestScoreSoFar) {
				bestSoFar = someClass
				bestScoreSoFar = someScore
			}
		}
		return bestSoFar
	}

	protected def EClass findExtendedClass(EClass confClass) {
		var EClass res = null
		val otherResultsFar = new HashSet<EClass>
		for (superType : confClass.ESuperTypes) {
			if (ecoreClasses.contains(superType))
				res = superType
			else {
				var indirectSuperType = findExtendedClass(superType)
				if (indirectSuperType != null)
					otherResultsFar.add(indirectSuperType)
			}
		}
		if (res != null)
			return res
		else if (otherResultsFar.size > 0) {
			return otherResultsFar.get(0)
		} else {
			return null
		}
	}

	protected def void computeMMExtension() {

		// What we want to create
		mmextensionResult = EcorextFactory.eINSTANCE.createEcorext

		// We create a "runtimedata" package in which are stored the new classes
		val runtimeDataPackage = EcoreFactory.eINSTANCE.createEPackage
		runtimeDataPackage.name = "runtimeData"
		runtimeDataPackage.nsPrefix = "runtimeData"
		runtimeDataPackage.nsURI = "http://runtimeData/1.0"
		val newPackageElement = EcorextFactory.eINSTANCE.createNewPackage
		newPackageElement.newPackage = runtimeDataPackage
		mmextensionResult.newPackages.add(newPackageElement)

		// For each class of the xmof model
		for (EClass xmofClass : xmofModel.allContents.filter(EClass).filter[c|!(c instanceof Activity)].toSet) {

			// First we increment the scores of each  super class
			for (superClass : xmofClass.EAllSuperTypes) {
				if (!classesScores.containsKey(superClass))
					classesScores.put(superClass, 0)
				classesScores.put(superClass, classesScores.get(superClass) + 1)
			}

			// We find the extended classes by looking in the supertypes for some class with the same name
			// but without the "Configuration" suffix
			val extendedClass = findExtendedClass(xmofClass)

			// Either we found extended classes, in which case this is a class extension
			if (xmofClass instanceof BehavioredEClass && extendedClass != null) {

				println("Found a class inheriting a class of the ecore model! " + xmofClass)

				// Store for later the mapping xmof -> ecore
				xmof2othermap.put(xmofClass, extendedClass)

				val allProperties = new HashSet<EStructuralFeature>
				allProperties.addAll(xmofClass.EReferences)
				allProperties.addAll(xmofClass.EAttributes)

				if (allProperties.size > 0) {

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
				if (xmofClass instanceof BehavioredEClass) {

					// We remove the behaviors from the BehavioredEClass, remporarily
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
				runtimeDataPackage.EClassifiers.add(copiedClass)

				// Store for later the mapping xmof -> ecorext
				xmof2othermap.put(xmofClass, copiedClass)
			}
		}

		this.guessedRootType = guessRootType()

	}

	protected def void computeEventMM() {

		// Init ecore model
		eventsmmResult = EcoreFactory.eINSTANCE.createEPackage
		eventsmmResult.name = "eventsMM"
		eventsmmResult.nsPrefix = "eventsMM"
		eventsmmResult.nsURI = "http://eventsMM/1.0"

		// find all xmof activities, 
		for (confClass : xmofModel.allContents.filter(BehavioredEClass).toSet) {

			for (Activity activity : confClass.ownedBehavior.filter(Activity)) {

				println("Found xmof activity!" + activity)

				// create an entry event class
				val entryEventClass = EcoreFactory.eINSTANCE.createEClass
				entryEventClass.name = ExtractorStringsCreator.class_createEntryEventClassName(confClass, activity)
				eventsmmResult.EClassifiers.add(entryEventClass)

				// create an exit event class
				val exitEventClass = EcoreFactory.eINSTANCE.createEClass
				exitEventClass.name = ExtractorStringsCreator.class_createExitEventClassName(confClass, activity)
				eventsmmResult.EClassifiers.add(entryEventClass)
				addReferenceToClass(exitEventClass, ExtractorStringsCreator.ref_ExitToEntry, entryEventClass)
				eventsmmResult.EClassifiers.add(exitEventClass)

				// we add a param property for the caller element ("this"), thus typed by the original class
				addReferenceToClass(entryEventClass, ExtractorStringsCreator.ref_EventToThis,
					xmof2othermap.get(confClass))

				// For each activity param, create a property in the class
				for (param : activity.ownedParameter) {

					// Either this is a known class of the metamodel, in which case we take the copy/original
					var EClassifier paramType = null
					if (xmof2othermap.keySet.contains(param.EType))
						paramType = xmof2othermap.get(param.EType)
					// Or not (eg EInt), and we use the type directly
					else
						paramType = param.EType

					if (paramType == null)
						paramType = guessedRootType

					if (param.direction == ParameterDirectionKind.IN) {
						addReferenceToClass(entryEventClass, ExtractorStringsCreator.ref_createEntryToParam(param),
							paramType)
					} else if (param.direction == ParameterDirectionKind.OUT) {
						addReferenceToClass(exitEventClass, ExtractorStringsCreator.ref_createExitToReturn(param),
							paramType)
					}
				}
			}
		}
	}

	protected static def EClass behavioredToNormal(BehavioredEClass b, Copier c) {
		val res = EcoreFactory.eINSTANCE.createEClass

		for (prop : res.eClass.EAllStructuralFeatures) {
			val value = b.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)c
			try {
				if (value instanceof EObject)
					res.eSet(prop, c.copy(value))
				else
					res.eSet(prop, value)
			} catch (Exception e) {
			}
		}

		return res
	}

	/*
	 * Note: we consider the xmof model to be immutable (we reuse its objects without copying them)
	 */
	protected static def EClass behavioredToNormal(BehavioredEClass b) {
		val res = EcoreFactory.eINSTANCE.createEClass

		for (prop : res.eClass.EAllStructuralFeatures) {
			val value = b.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)c
			try {
				res.eSet(prop, value)
			} catch (Exception e) {
			}
		}

		return res
	}

	protected static def void addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
	}

}
