package fr.inria.diverse.trace.plaink3.tracematerialextractor

import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.xtend.core.xtend.XtendClass
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.common.types.JvmIdentifiableElement
import org.eclipse.xtext.common.types.impl.JvmAnnotationTypeImpl
import org.eclipse.xtext.common.types.impl.JvmEnumerationTypeImplCustom
import org.eclipse.xtext.xbase.XMemberFeatureCall
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom
import org.eclipse.xtext.common.types.JvmMember
import org.eclipse.xtext.common.types.JvmEnumerationType
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmTypeReference
import fr.inria.diverse.trace.metamodel.generator.TraceMMStrings

class EventsMetamodelGenerator {

	// Input
	private val IJavaProject javaProject
	private val EPackage extendedMetamodel

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var EPackage eventsMM

	// Transient
	private val Map<XtendClass, JvmIdentifiableElement> transactionAspectsClassToAspectedClasses = new HashMap
	private val Set<XtendFunction> allFunctions = new HashSet
	private val Set<XtendClass> allClasses = new HashSet
	private val Set<XtendFunction> transactionFunctions = new HashSet
	private val Set<XtendFunction> microFunctions = new HashSet
	private val Map<XtendFunction, Set<XtendFunction>> macroFunctions = new HashMap
	private val Map<XtendFunction, EClass> functionToClass = new HashMap
	private val Map<XtendClass, Set<XtendClass>> classToSubTypes = new HashMap
	private val Set<XtendClass> inspectedClasses = new HashSet
	private val EPackage macroEventsPackage
	private var JvmMember className
	private var JvmMember transactionEMF
	private var JvmAnnotationType aspectAnnotation
	private var JvmEnumerationType transactionType
	private var JvmMember transactionSupport

	new(IJavaProject p, String languageName, EPackage extendedMetamodel) {
		this.javaProject = p
		this.eventsMM = EcoreFactory.eINSTANCE.createEPackage
		val mmname = languageName + TraceMMStrings.package_Steps
		this.eventsMM.name = mmname
		this.eventsMM.nsURI = mmname //TODO
		this.eventsMM.nsPrefix = mmname //TODO
		this.extendedMetamodel = extendedMetamodel
		this.macroEventsPackage = EcoreFactory.eINSTANCE.createEPackage
		this.macroEventsPackage.name = Plaink3MaterialStrings.package_BigSteps
		this.macroEventsPackage.nsURI = this.eventsMM.nsURI + "/"+Plaink3MaterialStrings.package_BigSteps.toFirstLower
		this.eventsMM.nsPrefix = this.eventsMM.nsPrefix + Plaink3MaterialStrings.package_BigSteps
		this.eventsMM.ESubpackages.add(this.macroEventsPackage)

	}

	public def void generate() {
		val loader = new XtendLoader(javaProject)
		loader.loadXtendModel

		// We gather a lot of stuff
		aspectAnnotation = loader.aspectAnnotation
		transactionType = loader.transactionSupport
		className = aspectAnnotation.members.findFirst[m|m.simpleName.equals("className")]
		transactionSupport = aspectAnnotation.members.findFirst[m|m.simpleName.equals("transactionSupport")]
		transactionEMF = transactionType.literals.findFirst[l|l.simpleName.equals("EMF")]

		// Then we generate
		generateEventsFromXtend(loader.xtendModel)
	}

	private def String getFQN(EClassifier c, String separator) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p, separator) + separator + c.name
		} else {
			return c.name
		}
	}

	private def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name
		}
	}

	private def String getXtendFunctionFQN(XtendFunction f) {
		val XtendTypeDeclaration type = f.declaringType
		if (type instanceof XtendClass) {
			return getXtendClassFQN(type) + "." + f.name
		} else {
			throw new Exception("Function not in a class!")
		}
	}

	private def String getXtendClassFQN(XtendClass type) {
		val file = type.eContainer
		if (file instanceof XtendFile) {
			return file.package + "." + type.name
		} else {
			throw new Exception("Class not in a file!")
		}

	}

	private def XtendFunction callToFunction(XMemberFeatureCall call) {
		if (transactionAspectsClassToAspectedClasses != null) {
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

	private def void inspectForMacro(XtendFunction function) {

		// If we haven't taken care of this function yet
		if (!(microFunctions.contains(function) || macroFunctions.containsKey(function))) {

			var boolean isMacro = false

			val calls = function.eAllContents.toSet.filter(XMemberFeatureCall)
			val calledFunctions = calls.map[call|callToFunction(call)].filter[f|f != null]

			// We look at all called functions
			for (calledFunction : calledFunctions) {

				// Recursive call, so that we are sure to know about the called function
				inspectForMacro(calledFunction)

				// The called function can be macro / transaction
				val boolean calledMacro = macroFunctions.containsKey(calledFunction)
				val boolean calledTransaction = transactionFunctions.contains(calledFunction)

				// If it is either, we have found a macro function
				if (calledMacro || calledTransaction) {
					isMacro = true
					if (!macroFunctions.containsKey(function))
						macroFunctions.put(function, new HashSet)
					val containedFunctions = macroFunctions.get(function)

					// If the called function is a transaction, we add it
					if (calledTransaction) {
						containedFunctions.add(calledFunction)
					}
					
					// If it isn't but still contains indirect calls to transaction functions, we add these calls
					else if (!calledTransaction && calledMacro) {
						containedFunctions.addAll(macroFunctions.get(calledFunction))
					}

				}
			}

			// Finally we look if this function was overriden/implemented by subtypes
			val xclass = function.declaringType as XtendClass
			if (classToSubTypes.containsKey(xclass)) {
				val subtypes = classToSubTypes.get(xclass)
				for (t : subtypes) {
					for (f : t.members.filter(XtendFunction)) {
						if (f.name.equals(function.name)) {
							inspectForMacro(f);
							if (macroFunctions.containsKey(f)) {
								isMacro = true
								if (!macroFunctions.containsKey(function))
									macroFunctions.put(function, new HashSet)
								macroFunctions.get(function).addAll(macroFunctions.get(f))
							}
						}

					}

				}
			}

			// If it never calls a transaction function, it is a micro function
			if (!isMacro) {
				microFunctions.add(function)
			}
		}

	}

	private def void generateEventClassFor(XtendFunction function) {

		if (!functionToClass.containsKey(function)) {

			// We find the ecore class matching the aspected java class 
			val aspect = function.declaringType as XtendClass
			val aspectedJVMClass = transactionAspectsClassToAspectedClasses.get(aspect)
			val String aspectedClassName = aspectedJVMClass.simpleName

			// TODO here we would need traceability links between non extended/extended to be more precise....
			// And we would need to know the java packages matching the core packages
			val EClass aspectedClass = extendedMetamodel.eAllContents.filter(EClass).findFirst[c1|
				aspectedClassName.equals(c1.name)]
			val String prefix = getFQN(aspectedClass, "_").toFirstUpper + "_"

			// For each operation, we create an event class
			val EClass eventClass = EcoreFactory.eINSTANCE.createEClass
			eventClass.name = prefix + function.name.toFirstUpper
			functionToClass.put(function, eventClass)

			// With a single "this" parameter
			EcoreCraftingUtil.addReferenceToClass(eventClass, "this", aspectedClass)

			// If this is a macro event, we have to handle sub events
			if (macroFunctions.containsKey(function)) {

				this.macroEventsPackage.EClassifiers.add(eventClass)

				// SubEventSuperClass
				val EClass subEventSuperClass = EcoreFactory.eINSTANCE.createEClass
				this.eventsMM.EClassifiers.add(subEventSuperClass)
				subEventSuperClass.name = prefix + function.name.toFirstUpper + Plaink3MaterialStrings.abstractSubStepSuffix
				subEventSuperClass.abstract = true

				// Link EventClass -> SubEventSuperClass
				val ref = EcoreCraftingUtil.addReferenceToClass(eventClass, Plaink3MaterialStrings.ref_BigStepToSub, subEventSuperClass)
				ref.ordered = true
				ref.containment = false
				ref.lowerBound = 0
				ref.upperBound = -1

				// Fill event class
				val EClass fillEventClass = EcoreFactory.eINSTANCE.createEClass
				this.eventsMM.EClassifiers.add(fillEventClass)
				fillEventClass.name = eventClass.name + Plaink3MaterialStrings.fillEventSuffix

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

				// If the class has transaction support
				if (a.elementValuePairs.exists[p|
					p.element == transactionSupport && (p.value as XMemberFeatureCall).feature == transactionEMF]) {

					// We find the JVM aspected class
					val aspectedJVMClass = (a.elementValuePairs.findFirst[p|p.element == className].value as XFeatureCallImplCustom).
						feature

					// We store the aspect class and the aspected class
					transactionAspectsClassToAspectedClasses.put(type, aspectedJVMClass)

					// And we store all the transaction functions that have a public visibility
					transactionFunctions.addAll(
						type.members.filter(XtendFunction).filter[function|function.modifiers.contains("public")])

				}
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

		// First we look for functions, transaction aspects and transaction functions
		// Will fill the variables transactionAspectsClassToAspectedClasses, allFunctions and transactionFunctions		
		for (c : allClasses) {
			inspectClass(c)
		}

		// Next we find all the macro functions
		// Will fill the variable microFunctions and mactoFunctions 
		for (function : transactionFunctions) {
			inspectForMacro(function)
		}

		// And finally we generate event classes
		// Will fill the variable functionToClass
		for (function : transactionFunctions) {
			generateEventClassFor(function)
		}
		
		// Also we generate a fill event, in case things happen between states not tracked by events
		val EClass fillEventClass = EcoreFactory.eINSTANCE.createEClass
		this.eventsMM.EClassifiers.add(fillEventClass)
		fillEventClass.name = Plaink3MaterialStrings.globalFillEventName

	}

}
