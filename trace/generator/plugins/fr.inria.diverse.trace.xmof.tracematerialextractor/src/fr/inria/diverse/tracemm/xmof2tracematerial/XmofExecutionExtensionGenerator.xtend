package fr.inria.diverse.tracemm.xmof2tracematerial

import ecorext.Ecorext
import ecorext.EcorextFactory
import ecorext.Rule
import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation

class XmofExecutionExtensionGenerator {

	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) Ecorext mmextensionResult

	protected val Resource xmofModel
	protected boolean done = false

	/*
	 * This copier is a bit tricky. Since we create Ecore objects from Ecore (or xmof) objects, we want to benefit from the
	 * EMF Copier "copyReferences" facility to automatically obtain something nice. 
	 * 
	 * Case class extension:
	 * - we put in it couples <xmof class, extended original class>
	 * - and use it to copy new properties from the xmof class into the class extension (the latter being not in the copier)
	 * 
	 * Case new class 
	 * - we copy a conf class into a regular class (in fact by copying then replacing the copy in the map)
	 * 
	 * Then "copyReferences" of the copier will update all references in the "copied" (or right side) elements
	 * so that they all refer to "copied" elements as well. 
	 */
	protected Copier hackyCopierXmof2ExtensionOrOriginal

	// All the eclasses of the original mm
	var Set<EClass> ecoreClasses

	new(Set<EPackage> ecore, Resource xmofModel, Copier copier) {
		this.xmofModel = xmofModel
		this.ecoreClasses = ecore.map[p|p.eAllContents.toSet].flatten.filter(EClass).toSet
		this.hackyCopierXmof2ExtensionOrOriginal = copier
	}

	new(Resource ecoreModel, Resource xmofModel, Copier copier) {
		this(ecoreModel.contents.filter(EPackage).toSet, xmofModel, copier)
	}

	protected def EClass findExtendedClass(EClass confClass) {
		var EClass res = null
		val otherResultsFar = new HashSet<EClass>
		for (superType : confClass.ESuperTypes.filter[c|c != confClass]) {

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

	protected def EPackage obtainExtensionPackage(EPackage runtimePackage) {

		// Null means that the root is the Ecorext object
		var EPackage result = null

		if (runtimePackage != null) {

			val tracedSuperPackage = obtainExtensionPackage(runtimePackage.ESuperPackage)

			if (tracedSuperPackage == null)
				result = mmextensionResult.newPackages.findFirst[p|p.name.equals(runtimePackage.name)]
			else
				result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(runtimePackage.name)]

			if (result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = runtimePackage.name
				result.nsURI = result.name // TODO
				result.nsPrefix = "" // TODO
				if (tracedSuperPackage == null) {
					mmextensionResult.newPackages.add(result)
				} else
					tracedSuperPackage.ESubpackages.add(result)
			}

		}
		return result
	}

	public def void computeMMExtension() {
		// We create some empty result
		mmextensionResult = EcorextFactory.eINSTANCE.createEcorext

		// We process each class of the model
		xmofModel.allContents.filter(EClass).filter[c|!(c instanceof Activity)].toSet.forEach[inspectClass]
	}

	private def void inspectClass(EClass xmofClass) {

		// We find the extended class of the abstract syntax
		val extendedClass = findExtendedClass(xmofClass)

		// Either we found extended classes, in which case this is a class extension
		if (xmofClass instanceof BehavioredEClass && extendedClass != null) {

			println("Found a class inheriting a class of the ecore model! " + xmofClass)

			// Store for later the mapping xmof -> ecore
			hackyCopierXmof2ExtensionOrOriginal.put(xmofClass, extendedClass)

			val allProperties = new HashSet<EStructuralFeature>
			allProperties.addAll(xmofClass.EReferences)
			allProperties.addAll(xmofClass.EAttributes)

			// But we truly have class extensions only if there are new properties
			if (allProperties.size > 0) {

				// Create class extension
				val classExt = EcorextFactory.eINSTANCE.createClassExtension
				classExt.extendedExistingClass = extendedClass
				mmextensionResult.classesExtensions.add(classExt)

				// In the class extension, we copy structural features of the conf class
				val copied = hackyCopierXmof2ExtensionOrOriginal.copyAll(allProperties)
				classExt.newProperties.addAll(copied)

				addTraceabilityAnnotations(allProperties);
			}

		} // Or not, in which case this is a new class
		else {

			println("Found new class! " + xmofClass)

			var EClass copiedClass = null;

			// Either this is a behaviored EClass
			if (xmofClass instanceof BehavioredEClass) {

				// We remove the behaviors from the BehavioredEClass, temporarily
				val temporaryRemovedClassifierBehavior = xmofClass.classifierBehavior
				val temporaryRemovedOwnedBehavior = new ArrayList
				temporaryRemovedOwnedBehavior.addAll(xmofClass.ownedBehavior)
				xmofClass.classifierBehavior = null
				xmofClass.ownedBehavior.clear

				// Then we copy the BehavioredEClass into a new one (that has no behaviors)
				val BehavioredEClass temporaryCopy = hackyCopierXmof2ExtensionOrOriginal.copy(
					xmofClass) as BehavioredEClass

				// And we restore the behaviors in the copied BehavioredEClass, in order to find activities later 
				xmofClass.classifierBehavior = temporaryRemovedClassifierBehavior
				xmofClass.ownedBehavior.addAll(temporaryRemovedOwnedBehavior)

				// Finally we convert the copy into a standard EClass
				copiedClass = behavioredToNormal(temporaryCopy)

				// And we replace the copy/copied pair in the copier
				hackyCopierXmof2ExtensionOrOriginal.put(xmofClass, copiedClass)
			} // Or a normal one
			else {
				copiedClass = hackyCopierXmof2ExtensionOrOriginal.copy(xmofClass) as EClass
			}

			copiedClass.EOperations.clear
			val EPackage containingPackage = obtainExtensionPackage(xmofClass.EPackage)

			containingPackage.EClassifiers.add(copiedClass)

			// Store for later the mapping xmof -> ecorext
			hackyCopierXmof2ExtensionOrOriginal.put(xmofClass, copiedClass)

			addTraceabilityAnnotations(xmofClass);

		}

		// Finally we look at the activities of the class to transform them into rules
		if (xmofClass instanceof BehavioredEClass)
			for (activity : xmofClass.ownedBehavior.filter(Activity))
				inspectActivity(xmofClass, activity)
	}

	val Map<Activity, Rule> activityToRule = new HashMap
	val Map<BehavioredEOperation, Rule> operationToRule = new HashMap
	
	
	private def Rule createRule(boolean isAbstract, EClass containingClass, BehavioredEOperation corresponingOperation) {
		
		// create rule
		val rule = EcorextFactory.eINSTANCE.createRule
		mmextensionResult.rules.add(rule)

		// For now we consider that everything is a step rule
		rule.stepRule = true

		rule.abstract = isAbstract
		rule.containingClass = hackyCopierXmof2ExtensionOrOriginal.get(containingClass) as EClass

		// We transform the behavioredeoperation into a regular operation
		val BehavioredEOperation behavioredEOperation = hackyCopierXmof2ExtensionOrOriginal.copy(
				corresponingOperation) as BehavioredEOperation
		val EOperation normalEOperation = behavioredToNormal(behavioredEOperation)
		hackyCopierXmof2ExtensionOrOriginal.put(behavioredEOperation, normalEOperation)
		rule.operation = normalEOperation

		return rule
	}

	private def Rule getRuleOf(Activity activity) {
		if (activityToRule.containsKey(activity)) {
			return activityToRule.get(activity)
		} else {
			return createRule(false, activity.context as EClass,activity.specification)
		}
	}

	private def Rule getRuleOf(BehavioredEOperation operation) {
		if (operationToRule.containsKey(operation)) {
			return operationToRule.get(operation)
		} else {
			val Rule rule = createRule(true,operation.EContainingClass,operation)
			
			for (activityMethod : operation.method.filter(Activity)) {
				val Rule overrideRule = getRuleOf(activityMethod)
				rule.overridenBy.add(overrideRule)
			}
			
			return rule
		}
	}

	private def void inspectActivity(BehavioredEClass confClass, Activity activity) {

		val inspectedActivityRule = getRuleOf(activity)

		for (callAction : activity.eAllContents.filter(CallAction).toSet) {	

			var Rule calledRule = null

			if (callAction instanceof CallOperationAction) {
				calledRule = getRuleOf(callAction.operation)
			} else if (callAction instanceof CallBehaviorAction) {
				if (callAction.behavior instanceof Activity)
					calledRule = getRuleOf(callAction.behavior as Activity)
			}

			if (calledRule != null)
				inspectedActivityRule.calledRules.add(calledRule)

		}
	}

	private def void addTraceabilityAnnotations(EClass executionClass) {
		addTraceabilityAnnotations(executionClass, hackyCopierXmof2ExtensionOrOriginal.get(executionClass) as EClass);
		addTraceabilityAnnotations(executionClass.EAttributes);
		addTraceabilityAnnotations(executionClass.EReferences);
	}

	private def void addTraceabilityAnnotations(Collection<? extends EStructuralFeature> executionClassProperties) {
		executionClassProperties.forEach [ property |
			addTraceabilityAnnotations(property,
				hackyCopierXmof2ExtensionOrOriginal.get(property) as EStructuralFeature)
		];
	}

	private def void addTraceabilityAnnotations(EModelElement originalExecutionMetamodelElement,
		EModelElement extensionModelElement) {
		val executionMetamodelElementURI = originalExecutionMetamodelElement.eResource.getURIFragment(
			originalExecutionMetamodelElement);
		ExecutionMetamodelTraceability.createTraceabilityAnnotation(extensionModelElement,
			executionMetamodelElementURI);
	}

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

	protected static def EOperation behavioredToNormal(BehavioredEOperation o) {
		val res = EcoreFactory.eINSTANCE.createEOperation

		for (prop : res.eClass.EAllStructuralFeatures) {
			val value = o.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)
			try {
				res.eSet(prop, value)
			} catch (Exception e) {
			}
		}

		return res
	}

}
