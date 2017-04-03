/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.opsemanticsview.gen.xmof

import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import opsemanticsview.OperationalSemanticsView
import opsemanticsview.OpsemanticsviewFactory
import opsemanticsview.Rule
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EParameter
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.Diagnostician
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallBehaviorAction
import org.modelexecution.xmof.Syntax.Actions.BasicActions.CallOperationAction
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEOperation
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter
import org.modelexecution.xmof.Syntax.Classes.Kernel.ParameterDirectionKind

class XMOFAnalyzer {

	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER]) OperationalSemanticsView mmextensionResult

	// Input
	protected val EPackage xmofModel
	protected val EPackage abstractSyntaxModel

	// Transient
	protected boolean done = false
	protected val Map<Activity, Rule> activityToRule = new HashMap
	protected val Map<BehavioredEOperation, Rule> operationToRule = new HashMap
	val Set<EClass> abstractSyntaxClasses = new HashSet

	private static def void debug(Object toPrint) {
		// println(toPrint)
	}

	new(EPackage abstractSyntax, EPackage xmofModel, OperationalSemanticsView view) {
		this.xmofModel = xmofModel
		this.abstractSyntaxModel = abstractSyntax
		this.mmextensionResult = view
	}

	public def void generate() {

		abstractSyntaxClasses.addAll(abstractSyntaxModel.eAllContents.filter(EClass).toSet)

		// We process each class of the model
		xmofModel.eAllContents.filter(EClass).filter[c|!(c instanceof Activity)].toSet.forEach[inspectClass]

		// Validation
		val results = Diagnostician.INSTANCE.validate(this.getMmextensionResult);
		val error = results.children.findFirst[r|r.severity == Diagnostic.ERROR]
		if (error != null)
			throw new IllegalStateException(
				"The extracted execution extension from the xmof model is invalid for at least one reason: " + error)
	}

	/*
	 * Called exactly once per class.
	 */
	private def void inspectClass(EClass xmofClass) {

		// We create the basic class or extension
		findDynamicProperties(xmofClass)

		// Then we look at the activities of the class to transform them into rules
		if (xmofClass instanceof BehavioredEClass) {
			for (activity : xmofClass.ownedBehavior.filter(Activity))
				inspectActivity(xmofClass, activity)
			for (operation : xmofClass.getEOperations.filter(BehavioredEOperation))
				inspectBehavioredEOperation(xmofClass, operation)
		}

//		addTraceabilityAnnotations(xmofClass);
	}

	/*
	 * Called exactly once per Activity.
	 */
	private def void inspectActivity(BehavioredEClass xmofClass, Activity activity) {

		// We create the corresponding rule object
		val inspectedActivityRule = getRuleOf(activity)

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
		inspectedOperationRule.containingClass = xmofClass
	}

	private def void findDynamicProperties(EClass xmofClass) {

		// We find the extended class of the abstract syntax
		val extendedClass = findExtendedClass(xmofClass)

		// Either we found extended classes, in which case this is a class extension
		if (xmofClass instanceof BehavioredEClass && extendedClass != null) {
			debug("Found a class inheriting a class of the ecore model! " + xmofClass)
			mmextensionResult.dynamicProperties.addAll(xmofClass.EStructuralFeatures)
			val entry = OpsemanticsviewFactory.eINSTANCE.createExecutionToASEntry => [
				ASclass = extendedClass
				executionClass = xmofClass
			]
			mmextensionResult.executionToASmapping.add(entry)

		} // Or not, in which case this is a new class, if it does comes from the xmof model
		else {

			debug("Found new class! " + xmofClass)
			mmextensionResult.dynamicClasses.add(xmofClass)
			mmextensionResult.dynamicProperties.addAll(xmofClass.EStructuralFeatures)
		}

	}

	protected def EClass findExtendedClass(EClass confClass) {

		var EClass res = null
		val otherResultsFar = new HashSet<EClass>
		for (superType : confClass.getESuperTypes.filter[c|c != confClass]) {

			if (abstractSyntaxClasses.contains(superType))
				res = superType
			else {
				var indirectSuperType = findExtendedClass(superType)
				if (indirectSuperType != null)
					otherResultsFar.add(indirectSuperType)
			}
		}
		if (res != null) {
			return res
		} else if (otherResultsFar.size > 0) {
			return otherResultsFar.get(0)
		} else {
			return null
		}

	}

//	protected def EPackage obtainExtensionPackage(EPackage runtimePackage) {
//
//		// Null means that the root is the Ecorext object
//		var EPackage result = null
//
//		if (runtimePackage != null) {
//
//			val tracedSuperPackage = obtainExtensionPackage(runtimePackage.getESuperPackage)
//
//			if (tracedSuperPackage == null)
//				result = mmextensionResult.newPackages.findFirst[p|p.name.equals(runtimePackage.name)]
//			else
//				result = tracedSuperPackage.getESubpackages.findFirst[p|p.name.equals(runtimePackage.name)]
//
//			if (result == null) {
//				result = EcoreFactory.eINSTANCE.createEPackage
//				result.name = runtimePackage.name
//				result.nsURI = result.name + "_trace"
//				result.nsPrefix = runtimePackage.nsPrefix + "_trace"
//				if (tracedSuperPackage == null) {
//					mmextensionResult.newPackages.add(result)
//				} else
//					tracedSuperPackage.getESubpackages.add(result)
//			}
//
//		}
//		return result
//	}
	private static def Rule createRule(boolean isAbstract, boolean isStep) {
		val rule = OpsemanticsviewFactory.eINSTANCE.createRule
		rule.stepRule = isStep
		rule.abstract = isAbstract
		return rule
	}

	private def hasOperationInClass(Activity activity) {
		activity.specification != null && activity.eContainer == activity.specification.EContainingClass
	}

	private def findMethodInClass(BehavioredEOperation xmofOperation) {
		return xmofOperation.method.filter(Activity).findFirst [ activity |
			activity.eContainer == xmofOperation.EContainingClass
		]

	}

	def List<EParameter> createInputParameters(List<EParameter> directedParameters) {
		val List<EParameter> result = new ArrayList

		val List<EParameter> inputParams = directedParameters.filter [ p |
			(p instanceof DirectedParameter && (p as DirectedParameter).direction == ParameterDirectionKind.IN ||
				(p as DirectedParameter).direction == ParameterDirectionKind.INOUT
			) || (! (p instanceof DirectedParameter))
		].toList

		for (xmofParam : inputParams) {
			val newParameter = EcoreFactory.eINSTANCE.createEParameter
			copyAttributes(xmofParam, newParameter)
			if (xmofParam.getEType != null)
				newParameter.EType = xmofParam.getEType
			else
				newParameter.EType = EcorePackage.eINSTANCE.getEObject
			result.add(newParameter)
		}

		if (result.filter[p|p.name.equals("tokens")].size > 1)
			debug(result)

		return result
	}

	def void findAndUseOutputParameter(EOperation operation, List<EParameter> directedParameters) {
		var returnParam = directedParameters.filter(DirectedParameter).findFirst [ p |
			p.direction == ParameterDirectionKind.RETURN
		]
		if (returnParam == null)
			returnParam = directedParameters.filter(DirectedParameter).findFirst [ p |
				p.direction == ParameterDirectionKind.OUT
			]
		if (returnParam != null) {
			operation.ordered = returnParam.ordered
			operation.unique = returnParam.unique
			operation.lowerBound = returnParam.lowerBound
			operation.upperBound = returnParam.upperBound
			if (returnParam.getEType != null)
				operation.EType = returnParam.getEType
			else
				operation.EType = EcorePackage.eINSTANCE.getEObject

		}
	}

	private def Rule getRuleOf(Activity activity) {
		if (activityToRule.containsKey(activity)) {
			return activityToRule.get(activity)
		} else {
			val isStep = hasStepAnnotation(activity) ||
				(activity.specification != null && hasStepAnnotation(activity.specification))
			var Rule rule = createRule(false, isStep)
			mmextensionResult.rules.add(rule)
			activityToRule.put(activity, rule)

			rule.containingClass = activity.eContainer as EClass

			val EOperation operation = EcoreFactory.eINSTANCE.createEOperation
			operation.name = activity.name

			// If our specification is in the same class of the activity, we use its list of methods to discover overriding activities
			if (hasOperationInClass(activity)) {
				for (activityMethod : activity.specification.method.filter(Activity).filter[a|a != activity]) {
					val Rule overrideRule = getRuleOf(activityMethod)
					rule.overridenBy.add(overrideRule)
				}
			}

			// First inputs
			operation.getEParameters.addAll(createInputParameters(activity.ownedParameter.filter(EParameter).toList))

			// Then output param. There can be only one, so we look for the first RETURN, or the first OUT
			findAndUseOutputParameter(operation, activity.ownedParameter.filter(EParameter).toList)

			// An EOperation with void return type *must* have an upper bound of 1
			if (operation.getEType == null && operation.upperBound != 1)
				operation.upperBound = 1

			rule.operation = operation
			return rule
		}

	}

	private def Rule getRuleOf(BehavioredEOperation xmofOperation) {
		val method = findMethodInClass(xmofOperation)
		if (operationToRule.containsKey(xmofOperation)) {
			return operationToRule.get(xmofOperation)
		} else // If the operation has one method in the same class with the same name, we ignore the operation
		if (method != null) {
			return getRuleOf(method)
		} else {
			val isStep = hasStepAnnotation(xmofOperation)
			val Rule rule = createRule(true, isStep)
			mmextensionResult.rules.add(rule)
			operationToRule.put(xmofOperation, rule)
			rule.containingClass = xmofOperation.EContainingClass

			val EOperation newEOperation = EcoreFactory.eINSTANCE.createEOperation
			copyAttributes(xmofOperation, newEOperation)
			rule.operation = newEOperation

			if (xmofOperation.EType != null)
				newEOperation.EType = xmofOperation.EType
			else
				findAndUseOutputParameter(newEOperation, xmofOperation.EParameters)

			// Input parameters
			newEOperation.getEParameters.addAll(createInputParameters(xmofOperation.EParameters))

			// An EOperation with void return type *must* have an upper bound of 1, while a param 
			if (newEOperation.getEType == null && newEOperation.upperBound != 1)
				newEOperation.upperBound = 1

			for (activityMethod : xmofOperation.method.filter(Activity)) {
				val Rule overrideRule = getRuleOf(activityMethod)
				rule.overridenBy.add(overrideRule)
			}

			return rule
		}

	}

//	private def void addTraceabilityAnnotations(EClass executionClass) {
//		addTraceabilityAnnotations(executionClass, executionClass as EClass);
//		addTraceabilityAnnotations(executionClass.getEAttributes);
//		addTraceabilityAnnotations(executionClass.getEReferences);
//	}
//	private def void addTraceabilityAnnotations(Collection<? extends EStructuralFeature> executionClassProperties) {
//		executionClassProperties.forEach [ property |
//			addTraceabilityAnnotations(property, xmofPropertyToNewProperty.get(property))
//		];
//	}
//	private def void addTraceabilityAnnotations(EModelElement originalExecutionMetamodelElement,
//		EModelElement extensionModelElement) {
//		val executionMetamodelElementURI = originalExecutionMetamodelElement.eResource.getURIFragment(
//			originalExecutionMetamodelElement);
//		ExecutionMetamodelTraceability.createTraceabilityAnnotation(extensionModelElement,
//			executionMetamodelElementURI);
//	}
	private static def copyAttributes(EObject from, EObject to) {
		for (prop : to.eClass.getEAllAttributes) {
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

	private static def hasStepAnnotation(EModelElement element) {
		val annotation = element.getEAnnotation("http://www.modelexecution.org/xmof")
		return annotation != null && annotation.details.containsKey("Step")
	}

}
