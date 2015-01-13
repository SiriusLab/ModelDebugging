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

class Xmof2tracematerial {

	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) Ecorext mmextensionResult
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) EPackage eventsmmResult

	protected val Resource ecoreModel
	protected val Resource xmofModel
	protected boolean done = false
	protected Copier copier

	protected var Map<EClass, EClass> xmof2othermap = new HashMap

	new(Resource ecoreModel, Resource xmofModel) {
		this.ecoreModel = ecoreModel
		this.xmofModel = xmofModel
		xmof2othermap = new HashMap
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

			val Set<EClass> ecoreClasses = ecoreModel.allContents.filter(EClass).toSet

			// We find the extended classes by looking in the supertypes for some class with the same name
			// but without the "Configuration" suffix
			val extendedClasses = xmofClass.EAllSuperTypes.filter[c|
				ecoreClasses.contains(c) && c.name.equals(xmofClass.name.replace("Configuration", ""))]

			// Either we found extended classes, in which case this is a class extension
			if (xmofClass instanceof BehavioredEClass && extendedClasses.size > 0) {

				println("Found a class inheriting a class of the ecore model! " + xmofClass)

				// Store for later the mapping xmof -> ecore
				xmof2othermap.put(xmofClass, extendedClasses.get(0))

				// Create class extension 
				val classExt = EcorextFactory.eINSTANCE.createClassExtension
				classExt.extendedExistingClass = extendedClasses.get(0)
				mmextensionResult.classesExtensions.add(classExt)

				// In the class extension, we copy structural features of the conf class
				val copied = copier.copyAll(xmofClass.EStructuralFeatures)
				classExt.newProperties.addAll(copied)

			}
			// Or not, in which case this is a new class
			else {

				println("Found new class! " + xmofClass)

				var EClass copiedClass = null;

				// Either this is a behaviored EClass
				if (xmofClass instanceof BehavioredEClass) {
					copiedClass = behavioredToNormal(xmofClass)
					copier.put(xmofClass, copiedClass)
					runtimeDataPackage.EClassifiers.add(copiedClass)
				} 

				// Or a normal one
				else {
					copiedClass = copier.copy(xmofClass) as EClass
					runtimeDataPackage.EClassifiers.add(copiedClass)
				}

				// Store for later the mapping xmof -> ecorext
				xmof2othermap.put(xmofClass, copiedClass)
			}
		}

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
				entryEventClass.name = activity.name + "EntryEventOccurrence"
				eventsmmResult.EClassifiers.add(entryEventClass)

				// create an exit event class
				val exitEventClass = EcoreFactory.eINSTANCE.createEClass
				exitEventClass.name = activity.name + "ExitEventOccurrence"
				addReferenceToClass(exitEventClass, "correspondingEntryEvent", entryEventClass)
				eventsmmResult.EClassifiers.add(exitEventClass)

				// we add a param property for the caller element ("this"), thus typed by the original class
				addReferenceToClass(entryEventClass, "thisParam", xmof2othermap.get(confClass))

				// For each activity param, create a property in the class
				for (param : activity.ownedParameter) {

					// Either this is a known class of the metamodel, in which case we take the copy/original
					var EClassifier paramType = null
					if (xmof2othermap.keySet.contains(param.EType))
						paramType = xmof2othermap.get(param.EType)
					// Or not (eg EInt), and we use the type directly
					else
						paramType = param.EType

					addReferenceToClass(entryEventClass, param.name + "Param", paramType)
				}

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
