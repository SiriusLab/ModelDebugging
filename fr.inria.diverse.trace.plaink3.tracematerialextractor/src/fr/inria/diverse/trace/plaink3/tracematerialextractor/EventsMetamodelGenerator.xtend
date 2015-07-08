package fr.inria.diverse.trace.plaink3.tracematerialextractor

import fr.inria.diverse.trace.commons.EcoreCraftingUtil

import fr.inria.diverse.trace.commons.tracemetamodel.StepStrings
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtend.core.xtend.XtendClass
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.common.types.JvmMember
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.xbase.XMemberFeatureCall
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom

class EventsMetamodelGenerator {

	// Input
	private val IJavaProject javaProject
	private val EPackage extendedMetamodel

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var EPackage eventsMM

	// Transient
	private val Map<XtendClass, JvmIdentifiableElement> stepAspectsClassToAspectedClasses = new HashMap
	private val Set<XtendFunction> allFunctions = new HashSet
	private val Set<XtendClass> allClasses = new HashSet
	private val Set<XtendFunction> stepFunctions = new HashSet
	private val Set<XtendFunction> microFunctions = new HashSet
	private val Map<XtendFunction, Set<XtendFunction>> macroFunctions = new HashMap
	private val Map<XtendFunction, EClass> functionToClass = new HashMap
	private val Map<XtendClass, Set<XtendClass>> classToSubTypes = new HashMap
	private val Set<XtendClass> inspectedClasses = new HashSet
	private val EPackage macroEventsPackage
	private var JvmMember className

	private var JvmAnnotationType aspectAnnotation
	private var JvmAnnotationType stepAnnotation

	new(IJavaProject p, String languageName, EPackage extendedMetamodel) {
		this.javaProject = p
		this.eventsMM = EcoreFactory.eINSTANCE.createEPackage
		val mmname = languageName + "Steps"
		this.eventsMM.name = mmname
		this.eventsMM.nsURI = mmname //TODO
		this.eventsMM.nsPrefix = mmname //TODO
		this.extendedMetamodel = extendedMetamodel
		this.macroEventsPackage = EcoreFactory.eINSTANCE.createEPackage
		this.macroEventsPackage.name = StepStrings.package_BigSteps
		this.macroEventsPackage.nsURI = this.eventsMM.nsURI + "/" + StepStrings.package_BigSteps.toFirstLower
		this.eventsMM.nsPrefix = this.eventsMM.nsPrefix + StepStrings.package_BigSteps
		this.eventsMM.ESubpackages.add(this.macroEventsPackage)

	}

	public def void generate() {
		val loader = new XtendLoader(javaProject)
		loader.loadXtendModel

		// We gather some stuff
		aspectAnnotation = loader.aspectAnnotation
		stepAnnotation = loader.stepAnnotation
		className = aspectAnnotation.members.findFirst[m|m.simpleName.equals("className")]

		// Then we generate
		generateEventsFromXtend(loader.xtendModel)
	}

	private static def String getXtendFunctionFQN(XtendFunction f) {
		val XtendTypeDeclaration type = f.declaringType
		if(type instanceof XtendClass) {
			return getXtendClassFQN(type) + "." + f.name
		} else {
			throw new Exception("Function not in a class!")
		}
	}

	private static def String getXtendClassFQN(XtendClass type) {
		val file = type.eContainer
		if(file instanceof XtendFile) {
			return file.package + "." + type.name
		} else {
			throw new Exception("Class not in a file!")
		}

	}

	private def XtendFunction callToFunction(XMemberFeatureCall call) {
		if(stepAspectsClassToAspectedClasses != null) {
			val String jvmfqn = call.feature.qualifiedName
			for (f : allFunctions) {
				val String fqn = getXtendFunctionFQN(f)
				if(fqn.equals(jvmfqn)) {
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
			if(fqn.equals(jvmfqn)) {
				return c
			}
		}
	}

	private def void inspectForMacro(XtendFunction function) {

		// If we haven't taken care of this function yet
		if(!(microFunctions.contains(function) || macroFunctions.containsKey(function))) {

			var boolean isMacro = false

			val calls = function.eAllContents.toSet.filter(XMemberFeatureCall)
			val calledFunctions = calls.map[call|callToFunction(call)].filter[f|f != null]

			// We look at all called functions
			for (calledFunction : calledFunctions) {

				// Recursive call, so that we are sure to know about the called function
				inspectForMacro(calledFunction)

				// The called function can be macro / step
				val boolean calledMacro = macroFunctions.containsKey(calledFunction)
				val boolean calledStep = stepFunctions.contains(calledFunction)

				// If it is either, we have found a macro function
				if(calledMacro || calledStep) {
					isMacro = true
					if(!macroFunctions.containsKey(function))
						macroFunctions.put(function, new HashSet)
					val containedFunctions = macroFunctions.get(function)

					// If the called function is a step, we add it
					if(calledStep) {
						containedFunctions.add(calledFunction)
					}
					
					// If it isn't but still contains indirect calls to step functions, we add these calls
					else if(!calledStep && calledMacro) {
						containedFunctions.addAll(macroFunctions.get(calledFunction))
					}

				}
			}

			// Finally we look if this function was overriden/implemented by subtypes
			val xclass = function.declaringType as XtendClass
			if(classToSubTypes.containsKey(xclass)) {
				val subtypes = classToSubTypes.get(xclass)
				for (t : subtypes) {
					for (f : t.members.filter(XtendFunction)) {
						if(f.name.equals(function.name)) {
							inspectForMacro(f);
							if(macroFunctions.containsKey(f)) {
								isMacro = true
								if(!macroFunctions.containsKey(function))
									macroFunctions.put(function, new HashSet)
								macroFunctions.get(function).addAll(macroFunctions.get(f))
							}
						}

					}

				}
			}

			// If it never calls a step function, it is a micro function
			if(!isMacro) {
				microFunctions.add(function)
			}
		}

	}

	private def void generateEventClassFor(XtendFunction function) {

		if(!functionToClass.containsKey(function)) {

			// We find the ecore class matching the aspected java class 
			val aspect = function.declaringType as XtendClass
			val aspectedJVMClass = stepAspectsClassToAspectedClasses.get(aspect)
			val String aspectedClassName = aspectedJVMClass.simpleName

			// TODO here we would need traceability links between non extended/extended to be more precise....
			// And we would need to know the java packages matching the core packages
			val EClass aspectedClass = extendedMetamodel.eAllContents.filter(EClass).findFirst[c1|
				aspectedClassName.equals(c1.name)]

			// For each operation, we create an event class
			val EClass eventClass = EcoreFactory.eINSTANCE.createEClass
			eventClass.name = StepStrings.stepClassName(aspectedClass, function.name)
			functionToClass.put(function, eventClass)

			// With a single "this" parameter
			EcoreCraftingUtil.addReferenceToClass(eventClass, "this", aspectedClass)

			// If this is a macro event, we have to handle sub events
			if(macroFunctions.containsKey(function)) {

				this.macroEventsPackage.EClassifiers.add(eventClass)

				// SubEventSuperClass
				val EClass subEventSuperClass = EcoreFactory.eINSTANCE.createEClass
				this.eventsMM.EClassifiers.add(subEventSuperClass)
				subEventSuperClass.name = StepStrings.abstractSubStepClassName(aspectedClass, function.name)
				subEventSuperClass.abstract = true

				// Link EventClass -> SubEventSuperClass
				val ref = EcoreCraftingUtil.addReferenceToClass(eventClass, StepStrings.ref_BigStepToSub,
					subEventSuperClass)
				ref.ordered = true
				ref.containment = false
				ref.lowerBound = 0
				ref.upperBound = -1

				// Fill event class
				val EClass fillEventClass = EcoreFactory.eINSTANCE.createEClass
				this.eventsMM.EClassifiers.add(fillEventClass)
				fillEventClass.name = StepStrings.fillEventClassName(aspectedClass, function.name)

				// Inheritance Fill > SubEventSuper
				fillEventClass.ESuperTypes.add(subEventSuperClass)

				// Then for each subevent, we generate and add some inheritance link
				for (subFunction : macroFunctions.get(function)) {
					generateEventClassFor(subFunction)
					val subEventClass = functionToClass.get(subFunction)

					// Inheritance SubEvent -> SubEventSuper
					subEventClass.ESuperTypes.add(subEventSuperClass)

				}
			} else {
				this.eventsMM.EClassifiers.add(eventClass)
			}

		}

	}

	private def void inspectClass(XtendClass type) {

		if(!inspectedClasses.contains(type)) {

			// We find the subtypes of all super classes
			allFunctions.addAll(type.members.filter(XtendFunction))
			if(type.extends != null) {
				val xclass = typeRefToClass(type.extends)
				if(xclass != null) {
					if(!classToSubTypes.containsKey(xclass)) {
						classToSubTypes.put(xclass, new HashSet)
					}
					classToSubTypes.get(xclass).add(type)
				}
			}

			// For each aspect annotation of the class 
			for (a : type.annotations.filter[a1|a1 != null && a1.annotationType == aspectAnnotation]) {

				// We find the JVM aspected class
				val aspectedJVMClass = (a.elementValuePairs.findFirst[p|p.element == className].value as XFeatureCallImplCustom).
					feature

				// We store the aspect class and the aspected class
				stepAspectsClassToAspectedClasses.put(type, aspectedJVMClass)

				// And we store all the functions with @Step
				stepFunctions.addAll(
					type.members.filter(XtendFunction).filter[function|
						function.annotations.exists[a1|a1 != null && a1.annotationType == stepAnnotation]])
			}
			inspectedClasses.add(type)
		}
	}

	private def generateEventsFromXtend(Set<XtendFile> files) {

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

		// Next we find all the macro functions
		// Will fill the variable microFunctions and mactoFunctions 
		for (function : stepFunctions) {
			inspectForMacro(function)
		}

		// And finally we generate event classes
		// Will fill the variable functionToClass
		for (function : stepFunctions) {
			generateEventClassFor(function)
		}

		// Also we generate a fill event, in case things happen between states not tracked by events
		val EClass fillEventClass = EcoreFactory.eINSTANCE.createEClass
		this.eventsMM.EClassifiers.add(fillEventClass)
		fillEventClass.name = StepStrings.globalFillEventName

	}

}
