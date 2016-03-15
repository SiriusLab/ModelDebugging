package fr.inria.diverse.tracemm.xmof2tracematerial

import ecorext.ClassExtension
import ecorext.Ecorext
import ecorext.EcorextFactory
import ecorext.Rule
import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind
import org.eclipse.emf.ecore.EcorePackage

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
	// protected Map<EObject, EObject> xmof2ExtensionOrOriginal
	protected val Map<EClass, ClassExtension> xmofClassToExtension = new HashMap
	protected val Map<EClass, EClass> xmofClassToNewClass = new HashMap
	protected val Map<EStructuralFeature, EStructuralFeature> xmofPropertyToNewProperty = new HashMap
	protected val Map<Activity, Rule> activityToRule = new HashMap
	protected val Map<BehavioredEOperation, Rule> operationToRule = new HashMap

	// All the eclasses of the original mm
	var Set<EClass> ecoreClasses

	new(Set<EPackage> ecore, Resource xmofModel) {
		this.xmofModel = xmofModel
		this.ecoreClasses = ecore.map[p|p.eAllContents.toSet].flatten.filter(EClass).toSet
	}

	new(Resource ecoreModel, Resource xmofModel) {
		this(ecoreModel.contents.filter(EPackage).toSet, xmofModel)
	}

	public def void computeMMExtension() {
		// We create some empty result
		mmextensionResult = EcorextFactory.eINSTANCE.createEcorext

		// We process each class of the model
		xmofModel.allContents.filter(EClass).filter[c|!(c instanceof Activity)].toSet.forEach[inspectClass]
	}

	/*
	 * Called exactly once per class.
	 */
	private def void inspectClass(EClass xmofClass) {

		// We create the basic class or extension
		getExtendedOrNewClass(xmofClass)

		// We find all the new properties
		val Set<EStructuralFeature> newProperties = new HashSet
		for (xmofRef : xmofClass.EReferences) {
			val newRef = EcoreFactory.eINSTANCE.createEReference
			xmofPropertyToNewProperty.put(xmofRef, newRef)
			copyAttributes(xmofRef, newRef)
			if (xmofRef.EType != null)
				newRef.EType = getExtendedOrNewClass(xmofRef.EType as EClass)
			else
				newRef.EType = EcorePackage.eINSTANCE.EObject
			newProperties.add(newRef)
		}
		for (xmofAtt : xmofClass.EAttributes) {
			val newAtt = EcoreFactory.eINSTANCE.createEAttribute
			xmofPropertyToNewProperty.put(xmofAtt, newAtt)
			copyAttributes(xmofAtt, newAtt)
			newAtt.EType = xmofAtt.EType
			newProperties.add(newAtt)
		}

		// Case extension
		if (xmofClassToExtension.containsKey(xmofClass)) {
			val ext = xmofClassToExtension.get(xmofClass)
			ext.newProperties.addAll(newProperties)

		} // Case new class
		else if (xmofClassToNewClass.containsKey(xmofClass)) {
			val newClass = xmofClassToNewClass.get(xmofClass)
			newClass.EStructuralFeatures.addAll(newProperties)
			for (xmofSuperType : xmofClass.ESuperTypes) {
				newClass.ESuperTypes.add(getExtendedOrNewClass(xmofSuperType))
			}
		}

		// Then we look at the activities of the class to transform them into rules
		if (xmofClass instanceof BehavioredEClass) {
			for (activity : xmofClass.ownedBehavior.filter(Activity))
				inspectActivity(xmofClass, activity)
			for (operation : xmofClass.EOperations.filter(BehavioredEOperation))
				inspectBehavioredEOperation(xmofClass, operation)
		}

		addTraceabilityAnnotations(xmofClass);

	}

	/*
	 * Called exactly once per Activity.
	 */
	private def void inspectActivity(BehavioredEClass xmofClass, Activity activity) {

		// We create the corresponding rule object
		val inspectedActivityRule = getRuleOf(activity)
		inspectedActivityRule.containingClass = getExtendedOrNewClass(xmofClass)

		// And we find relationships between rules
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

	/*
	 * Called exactly once per BehavioredEOperation.
	 */
	private def void inspectBehavioredEOperation(BehavioredEClass xmofClass, BehavioredEOperation operation) {

		// We create the corresponding rule object
		val inspectedOperationRule = getRuleOf(operation)
		inspectedOperationRule.containingClass = getExtendedOrNewClass(xmofClass)
	}

	private def EClass getExtendedOrNewClass(EClass xmofClass) {

		if (xmofClassToExtension.containsKey(xmofClass)) {
			return xmofClassToExtension.get(xmofClass).extendedExistingClass
		} else if (xmofClassToNewClass.containsKey(xmofClass)) {
			return xmofClassToNewClass.get(xmofClass)
		} else if (ecoreClasses.contains(xmofClass))
			return xmofClass
		// We create an extension or new class
		else {

			// We find the extended class of the abstract syntax
			var extendedOrNewClass = findExtendedClass(xmofClass)

			// Either we found extended classes, in which case this is a class extension
			if (xmofClass instanceof BehavioredEClass && extendedOrNewClass != null) {

				println("Found a class inheriting a class of the ecore model! " + xmofClass)

				val allProperties = new HashSet<EStructuralFeature>
				allProperties.addAll(xmofClass.EReferences)
				allProperties.addAll(xmofClass.EAttributes)

				// But we truly have class extensions only if there are new properties
				if (allProperties.size > 0) {

					// Create class extension
					val classExt = EcorextFactory.eINSTANCE.createClassExtension
					xmofClassToExtension.put(xmofClass, classExt)
					classExt.extendedExistingClass = extendedOrNewClass
					mmextensionResult.classesExtensions.add(classExt)
				}

			} // Or not, in which case this is a new class, if it does comes from the xmof model
			else {

				println("Found new class! " + xmofClass)

				extendedOrNewClass = EcoreFactory.eINSTANCE.createEClass
				xmofClassToNewClass.put(xmofClass, extendedOrNewClass)
				copyAttributes(xmofClass, extendedOrNewClass)
				val EPackage containingPackage = obtainExtensionPackage(xmofClass.EPackage)
				containingPackage.EClassifiers.add(extendedOrNewClass)
			}

			return extendedOrNewClass

		}
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
				result.nsURI = result.name + "_trace"
				result.nsPrefix = runtimePackage.nsPrefix + "_trace"
				if (tracedSuperPackage == null) {
					mmextensionResult.newPackages.add(result)
				} else
					tracedSuperPackage.ESubpackages.add(result)
			}

		}
		return result
	}

	private static def Rule createRule(boolean isAbstract) {
		val rule = EcorextFactory.eINSTANCE.createRule
		rule.stepRule = true
		rule.abstract = isAbstract
		return rule
	}

	private def Rule getRuleOf(Activity activity) {
		if (activityToRule.containsKey(activity)) {
			return activityToRule.get(activity)
		} else {
			var Rule rule = createRule(false)
			mmextensionResult.rules.add(rule)
			activityToRule.put(activity, rule)
			val EOperation operation = EcoreFactory.eINSTANCE.createEOperation
			operation.name = activity.name

			// If we are the unique activity matching an operation, in the same class, with the same name, then we use its overrides
			if (activity.specification != null && findMatchingActivity(activity.specification) == activity) {
				for (activityMethod : activity.specification.method.filter(Activity).filter[a|a != activity]) {
					val Rule overrideRule = getRuleOf(activityMethod)
					rule.overridenBy.add(overrideRule)
				}
			}

			// First inputs
			for (xmofParam : activity.ownedParameter.filter [ p |
				p.direction == ParameterDirectionKind.IN || p.direction == ParameterDirectionKind.INOUT
			]) {
				val newParameter = EcoreFactory.eINSTANCE.createEParameter
				copyAttributes(xmofParam, newParameter)
				if (xmofParam.EType != null && xmofParam.EType instanceof EClass)
					newParameter.EType = getExtendedOrNewClass(xmofParam.EType as EClass)
				else if (xmofParam.EType != null)
					newParameter.EType = xmofParam.EType
				else
					newParameter.EType = EcorePackage.eINSTANCE.EObject
				operation.EParameters.add(newParameter)
			}

			// Then output param. There can be only one, so we look for the first RETURN, or the first OUT
			var returnParam = activity.ownedParameter.findFirst[p|p.direction == ParameterDirectionKind.RETURN]
			if (returnParam == null)
				returnParam = activity.ownedParameter.findFirst[p|p.direction == ParameterDirectionKind.OUT]
			if (returnParam != null) {
				operation.ordered = returnParam.ordered
				operation.unique = returnParam.unique
				operation.lowerBound = returnParam.lowerBound
				operation.upperBound = returnParam.upperBound
				if (returnParam.EType != null && returnParam.EType instanceof EClass)
					operation.EType = getExtendedOrNewClass(returnParam.EType as EClass)
				else if (returnParam.EType != null)
					operation.EType = returnParam.EType
				else
					operation.EType = EcorePackage.eINSTANCE.EObject

			}

			// An EOperation with void return type *must* have an upper bound of 1
			if (operation.EType == null && operation.upperBound != 1)
				operation.upperBound = 1

			rule.operation = operation
			return rule
		}

	}

	private def Activity findMatchingActivity(BehavioredEOperation xmofOperation) {
		return xmofOperation.method.filter(Activity).findFirst [ m |
			m.name.equals(xmofOperation.name) && m.eContainer == xmofOperation.EContainingClass
		]
	}

	private def Rule getRuleOf(BehavioredEOperation xmofOperation) {
		val activityWithSameName = findMatchingActivity(xmofOperation)
		if (operationToRule.containsKey(xmofOperation)) {
			return operationToRule.get(xmofOperation)
		} else // If the operation has one method in the same class with the same name, we ignore the operation
		if (activityWithSameName != null) {
			return getRuleOf(activityWithSameName)
		} else {
			val Rule rule = createRule(true)
			mmextensionResult.rules.add(rule)
			operationToRule.put(xmofOperation, rule)
			val EOperation newEOperation = EcoreFactory.eINSTANCE.createEOperation
			copyAttributes(xmofOperation, newEOperation)
			rule.operation = newEOperation

			if (xmofOperation.EType != null && xmofOperation.EType instanceof EClass)
				newEOperation.EType = getExtendedOrNewClass(xmofOperation.EType as EClass)
			else
				newEOperation.EType = xmofOperation.EType

			// An EOperation with void return type *must* have an upper bound of 1, while a param 
			if (newEOperation.EType == null && newEOperation.upperBound != 1)
				newEOperation.upperBound = 1

			for (xmofParam : xmofOperation.EParameters) {
				val newParam = EcoreFactory.eINSTANCE.createEParameter
				copyAttributes(xmofParam, newParam)
				if (xmofParam.EType != null && xmofParam.EType instanceof EClass)
					newParam.EType = getExtendedOrNewClass(xmofParam.EType as EClass)
				else if (xmofParam.EType != null)
					newParam.EType = xmofParam.EType
				else
					newParam.EType = EcorePackage.eINSTANCE.EObject
				newEOperation.EParameters.add(newParam)
			}

			for (activityMethod : xmofOperation.method.filter(Activity)) {
				val Rule overrideRule = getRuleOf(activityMethod)
				rule.overridenBy.add(overrideRule)
			}

			return rule
		}

	}

	private def void addTraceabilityAnnotations(EClass executionClass) {
		addTraceabilityAnnotations(executionClass, getExtendedOrNewClass(executionClass as EClass));
		addTraceabilityAnnotations(executionClass.EAttributes);
		addTraceabilityAnnotations(executionClass.EReferences);
	}

	private def void addTraceabilityAnnotations(Collection<? extends EStructuralFeature> executionClassProperties) {
		executionClassProperties.forEach [ property |
			addTraceabilityAnnotations(property, xmofPropertyToNewProperty.get(property))
		];
	}

	private def void addTraceabilityAnnotations(EModelElement originalExecutionMetamodelElement,
		EModelElement extensionModelElement) {
		val executionMetamodelElementURI = originalExecutionMetamodelElement.eResource.getURIFragment(
			originalExecutionMetamodelElement);
		ExecutionMetamodelTraceability.createTraceabilityAnnotation(extensionModelElement,
			executionMetamodelElementURI);
	}

	private static def copyAttributes(EObject from, EObject to) {
		for (prop : to.eClass.EAllAttributes) {
			val value = from.eGet(prop)

			// We try to set everything, but there are many derived properties etc. thus many errors
			// (but not a problem)
			try {
				if (prop.many)
					(to.eGet(prop) as Collection<Object>).addAll(value as Collection<Object>)
				else
					to.eSet(prop, value)
			} catch (Exception e) {
			}
		}
	}

}
