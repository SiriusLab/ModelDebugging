package fr.inria.diverse.trace.plaink3.tracematerialextractor

import ecorext.Ecorext
import ecorext.EcorextFactory
import ecorext.Rule
import fr.inria.diverse.commons.eclipse.xtendparser.XtendParser
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtend.core.xtend.XtendClass
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.common.types.JvmMember
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.impl.JvmAnnotationTypeImpl
import org.eclipse.xtext.xbase.XMemberFeatureCall
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom
import org.eclipse.emf.ecore.EClass

class K3StepExtractor {

	// Input
	private val IJavaProject javaProject
	private val EPackage extendedMetamodel

	// Input / Output
	private val Ecorext ecoreExtension

	// Transient
	private val Map<XtendClass, JvmIdentifiableElement> stepAspectsClassToAspectedClasses = new HashMap
	private val Set<XtendFunction> allFunctions = new HashSet
	private val Set<XtendClass> allClasses = new HashSet
	private val Set<XtendFunction> stepFunctions = new HashSet
	private val Map<XtendFunction, Rule> functionToRule = new HashMap
	private val Map<XtendClass, Set<XtendClass>> classToSubTypes = new HashMap
	private val Set<XtendClass> inspectedClasses = new HashSet
	private var JvmMember className

	private var JvmAnnotationType aspectAnnotation
	private var JvmAnnotationType stepAnnotation

	new(IJavaProject p, String languageName, EPackage extendedMetamodel, Ecorext inConstructionEcorext) {
		this.javaProject = p
		this.extendedMetamodel = extendedMetamodel
		this.ecoreExtension = inConstructionEcorext

	}

	public def void generate() {
		val loader = new XtendParser
		loader.loadXtendModel(javaProject)

		// We find the annotation types from what the parser found
		for (jvmTypeResource : loader.jvmTypeResources) {
			if (jvmTypeResource.URI.toString.equals("java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Aspect"))
				aspectAnnotation = jvmTypeResource.contents.
					findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl
			else if (jvmTypeResource.URI.toString.equals(
				"java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Step"))
				stepAnnotation = jvmTypeResource.contents.
					findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl
		}

		// And we also isolate the "className" field of @Aspect		
		className = aspectAnnotation.members.findFirst[m|m.simpleName.equals("className")]

		// Then we generate
		generateStepFromXtend(loader.xtendModel)
	}

	private static def String getXtendFunctionFQN(XtendFunction f) {
		val XtendTypeDeclaration type = f.declaringType
		if (type instanceof XtendClass) {
			return getXtendClassFQN(type) + "." + f.name
		} else {
			throw new Exception("Function not in a class!")
		}
	}

	private static def String getXtendClassFQN(XtendClass type) {
		val file = type.eContainer
		if (file instanceof XtendFile) {
			return file.package + "." + type.name
		} else {
			throw new Exception("Class not in a file!")
		}

	}

	private def XtendFunction callToFunction(XMemberFeatureCall call) {
		if (stepAspectsClassToAspectedClasses != null) {
			val String jvmfqn = call.feature.qualifiedName
			for (f : allFunctions) {
				val String fqn = getXtendFunctionFQN(f)
				if (fqn.equals(jvmfqn)) {
					return f
				}
			}

		} else
			return null
	}

	private def XtendClass typeRefToClass(JvmTypeReference ref) {
		val String jvmfqn = ref.type.qualifiedName
		for (c : allClasses) {
			val String fqn = getXtendClassFQN(c)
			if (fqn.equals(jvmfqn)) {
				return c
			}
		}
	}

	private def Rule getRuleOfFunction(XtendFunction function) {
		if (functionToRule.containsKey(function))
			return functionToRule.get(function)
		else {
			val Rule rule = EcorextFactory.eINSTANCE.createRule;
			this.ecoreExtension.rules.add(rule)

			// We find the ecore class matching the aspected java class 
			val containingClass = function.declaringType as XtendClass
			val aspectedJVMClass = stepAspectsClassToAspectedClasses.get(containingClass)

			rule.containingClass = if (aspectedJVMClass != null) {
				val String aspectedClassName = aspectedJVMClass.simpleName
				// TODO here we would need traceability links between non extended/extended to be more precise....
				// And we would need to know the java packages matching the core packages
				extendedMetamodel.eAllContents.filter(EClass).findFirst [ c1 |
					aspectedClassName.equals(c1.name)
				]
			}

			rule.operation = xtendFunctionToEOperation(function)
			rule.stepRule = stepFunctions.contains(function)
			functionToRule.put(function, rule)
			return rule
		}

	}

	private def void inspectForBigStep(XtendFunction function) {

		// We consider that each Kermeta function is a transformation rule (even through we cannot know if it modifies anything)		
		val Rule rule = getRuleOfFunction(function)

		// We retrieve which xtend functions are called by the function
		val calls = function.eAllContents.toSet.filter(XMemberFeatureCall)
		val calledFunctions = calls.map[call|callToFunction(call)].filter[f|f != null]

		// We look at all called functions, and add them as called functions
		for (calledFunction : calledFunctions) {
			val Rule calledRule = getRuleOfFunction(calledFunction)
			rule.calledRules.add(calledRule)
		}

		// Finally we look if this function was overriden/implemented by subtypes
		// TODO use annotation?
		val xclass = function.declaringType as XtendClass
		if (classToSubTypes.containsKey(xclass)) {
			val subtypes = classToSubTypes.get(xclass)
			for (t : subtypes) {
				for (f : t.members.filter(XtendFunction)) {
					if (f.name.equals(function.name)) {
						val Rule overridingRule = getRuleOfFunction(f)
						rule.overridenBy.add(overridingRule)
					}
				}
			}

		}

	}

	private def EOperation xtendFunctionToEOperation(XtendFunction function) {
		val result = EcoreFactory.eINSTANCE.createEOperation
		result.name = function.name
		// TODO finish the translation and/or ask Thomas
		// TODO or consider it is already in the ecore?
		return result
	}

	private def void inspectClass(XtendClass type) {

		if (!inspectedClasses.contains(type)) {

			// We find the subtypes of all super classes
			allFunctions.addAll(type.members.filter(XtendFunction))
			if (type.extends != null) {
				val xclass = typeRefToClass(type.extends)
				if (xclass != null) {
					if (!classToSubTypes.containsKey(xclass)) {
						classToSubTypes.put(xclass, new HashSet)
					}
					classToSubTypes.get(xclass).add(type)
				}
			}

			// For each aspect annotation of the class 
			for (a : type.annotations.filter[a1|a1 != null && a1.annotationType == aspectAnnotation]) {

				// We find the JVM aspected class
				val aspectedJVMClass = (a.elementValuePairs.findFirst[p|p.element == className].
					value as XFeatureCallImplCustom).feature

				// We store the aspect class and the aspected class
				stepAspectsClassToAspectedClasses.put(type, aspectedJVMClass)

				// And we store all the functions with @Step
				stepFunctions.addAll(
					type.members.filter(XtendFunction).filter [ function |
					function.annotations.exists[a1|a1 != null && a1.annotationType == stepAnnotation]
				])
			}
			inspectedClasses.add(type)
		}
	}

	private def generateStepFromXtend(Set<XtendFile> files) {

		// Will fill "allClasses"
		for (f : files) {
			for (type : f.xtendTypes.filter(XtendClass)) {
				allClasses.add(type)
			}
		}

		// First we look for functions, step aspects and step functions
		// Will fill the variables stepAspectsClassToAspectedClasses, allFunctions and stepFunctions		
		for (c : allClasses) {
			inspectClass(c)
		}

		// Next we create the Rule objects with all that
		for (function : allFunctions) {
			inspectForBigStep(function)
		}

	}

}
