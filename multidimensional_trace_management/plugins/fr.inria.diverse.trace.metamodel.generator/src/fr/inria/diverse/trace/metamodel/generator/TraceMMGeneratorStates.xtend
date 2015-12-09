package fr.inria.diverse.trace.metamodel.generator

import ecorext.ClassExtension
import ecorext.Ecorext
import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability
import java.util.HashMap
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.util.EcoreUtil.Copier

import static fr.inria.diverse.trace.commons.EcoreCraftingUtil.*

class TraceMMGeneratorStates {

	// Inputs
	private val Ecorext mmext
	private val EPackage mm
	private val TraceMMExplorer traceMMExplorer
	private val String languageName
	private val boolean gemoc
	
	// Input/Output (already accessible because created before)
	private val EPackage tracemmresult
	private val TraceMMGenerationTraceability traceability

	// Transient stuff 
	private val Copier runtimeClassescopier
	private val Set<EClass> allRuntimeClasses
	private val Set<EClass> allStaticClasses
	private val Set<EClass> allNewEClasses
	
	

	new(Ecorext mmext, EPackage mm, TraceMMGenerationTraceability traceability, TraceMMExplorer traceMMExplorer, String languageName, EPackage tracemmresult, boolean gemoc) {
		this.mm = mm
		this.mmext = mmext
		this.allRuntimeClasses = new HashSet<EClass>
		this.allStaticClasses = new HashSet<EClass>
		this.allNewEClasses = mmext.eAllContents.toSet.filter(EClass).toSet
		this.traceability = traceability
		this. traceMMExplorer = traceMMExplorer
		this.languageName = languageName
		this.tracemmresult = tracemmresult
		this.runtimeClassescopier = new Copier
		this.gemoc = gemoc
		
		// If we are generating for gemoc, then we must load the ecore containing the MSEOccurrence class
		if (gemoc) {
			//TODO
		}
	}

	private def void cleanup() {
		for (c : this.tracemmresult.eAllContents.filter(EClass).toSet) {
			cleanupAnnotations(c);
			c.EOperations.clear
		}

		for (r : runtimeClassescopier.values.filter(EReference)) {
			r.EOpposite = null
		}
	}

	public def void process() {
		handleTraceClasses()
		runtimeClassescopier.copyReferences
		cleanup()
	}

	private def void cleanupAnnotations(EClass eClass) {
		val traceabilityAnnotation = ExecutionMetamodelTraceability.getTraceabilityAnnotation(eClass);
		eClass.EAnnotations.clear
		if (traceabilityAnnotation != null) {
			eClass.EAnnotations.add(traceabilityAnnotation);
		}
	}

	private def EPackage obtainTracedPackage(EPackage runtimePackage) {
		var EPackage result = traceMMExplorer.statesPackage

		if (runtimePackage != null) {
			val tracedSuperPackage = obtainTracedPackage(runtimePackage.ESuperPackage)
			val String tracedPackageName = TraceMMStrings.package_createTracedPackage(runtimePackage)
			result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(tracedPackageName)]
			if (result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = tracedPackageName
				result.nsURI = languageName + "_" + result.name // TODO
				result.nsPrefix = "" // TODO
				tracedSuperPackage.ESubpackages.add(result)
			}
		}
		return result
	}

	private def String computeTraceabilityAnnotationValue(ClassExtension classExtension) {
		var String traceabilityAnnotationValue = null;
		if (!classExtension.newProperties.empty) {
			val mutableProperty = classExtension.newProperties.get(0);
			val String mutablePropertyTraceabilityValue = ExecutionMetamodelTraceability.
				getTraceabilityAnnotationValue(mutableProperty)
			if (mutablePropertyTraceabilityValue != null) {
				val classSubstringStartIndex = mutablePropertyTraceabilityValue.lastIndexOf("/");
				traceabilityAnnotationValue = mutablePropertyTraceabilityValue.substring(0, classSubstringStartIndex);
			}
		}
		return traceabilityAnnotationValue;
	}

	private def boolean isInPackage(EPackage c, EPackage p) {
		if (c != null && p != null && c == p) {
			return true
		} else if (c.ESuperPackage != null) {
			return isInPackage(c.ESuperPackage, p)
		} else {
			return false
		}
	}

	private def handleTraceClasses() {

		// First we find ALL classes linked to runtime properties
		val runtimeClass2ClassExtension = new HashMap<EClass, ClassExtension>;
		for (c : mmext.classesExtensions) {
			allRuntimeClasses.add(c.extendedExistingClass)
			runtimeClass2ClassExtension.put(c.extendedExistingClass, c)

			// super-classes of extended class
			allRuntimeClasses.addAll(c.extendedExistingClass.EAllSuperTypes)

			// sub-classes of extended class
			for (someEClass : mm.eAllContents.toSet.filter(EClass)) {
				if (someEClass.EAllSuperTypes.contains(c.extendedExistingClass)) {
					allRuntimeClasses.add(someEClass)
				}
			}
		}
		allRuntimeClasses.addAll(allNewEClasses)

		// We also store the dual set of classes not linked to anything dynamic
		allStaticClasses.addAll(mm.eAllContents.toSet.filter(EClass).filter[c|!allRuntimeClasses.contains(c)])

		// Here we find classes that inherit from multiple concrete classes
		// This allows later to handle multiple non-conflicting "originalObject" references in such cases
		val Set<EClass> multipleOrig = new HashSet
		for (rc : allRuntimeClasses) {
			val concreteSuperTypes = rc.EAllSuperTypes.filter[c|!c.abstract].toSet
			multipleOrig.addAll(concreteSuperTypes)

		}

		// We go through all dynamic classes and we create traced versions of them
		for (runtimeClass : allRuntimeClasses) {

			// Creating the traced version of the class by copying the runtime class
			// TODO if we remove static objects creation, could we remove such properties as well? we should always have an "originalObject" ref
			val tracedClass = runtimeClassescopier.copy(runtimeClass) as EClass
			tracedClass.name = TraceMMStrings.class_createTraceClassName(runtimeClass)

			// We recreate the same package organization
			val tracedPackage = obtainTracedPackage(runtimeClass.EPackage)
			tracedPackage.EClassifiers.add(tracedClass)

			// Removing all containments in the references obtained
			for (prop : tracedClass.EReferences) {
				prop.containment = false
				prop.EOpposite = null
			}

			// We remove all attributes from the class (since accessible from "originalObject"): 
			tracedClass.EStructuralFeatures.removeAll(tracedClass.EStructuralFeatures.filter(EAttribute))

			// If this is a class extension, then we add a reference, to be able to refer to the element of the original model (if originally static element of the model)
			val boolean notNewClass = !allNewEClasses.contains(runtimeClass)
			val boolean notAbstract = !tracedClass.abstract

			if (notNewClass && runtimeClass2ClassExtension.containsKey(runtimeClass)) {
				val traceabilityAnnotationValue = computeTraceabilityAnnotationValue(
					runtimeClass2ClassExtension.get(runtimeClass));
				if (traceabilityAnnotationValue != null)
					ExecutionMetamodelTraceability.createTraceabilityAnnotation(tracedClass,
						traceabilityAnnotationValue);
			}

			// Also we must check that there isn't already a concrete class in the super classes, which would have its own origObj ref
			// TODO this is not enough ! it is possible to have a concrete class with no originalObject link! (eg new class in the extension)
			val boolean onlyAbstractSuperTypes = runtimeClass.EAllSuperTypes.forall[c|c.abstract]
			if (notNewClass && notAbstract && onlyAbstractSuperTypes) {
				var refName = ""
				if (multipleOrig.contains(runtimeClass)) {
					refName = TraceMMStrings.ref_OriginalObject_MultipleInheritance(runtimeClass)
				} else {
					refName = TraceMMStrings.ref_OriginalObject
				}
				val EReference ref = addReferenceToClass(tracedClass, refName, runtimeClass)
				traceability.addRefs_originalObject(tracedClass, ref)
			}

			// Link Trace class -> Traced class
			if (!tracedClass.abstract) {
				val refTraceClassToTracedClass = addReferenceToClass(traceMMExplorer.traceClass,
					TraceMMStrings.ref_createTraceClassToTracedClass(tracedClass), tracedClass)
				refTraceClassToTracedClass.containment = true
				refTraceClassToTracedClass.ordered = false
				refTraceClassToTracedClass.unique = true
				refTraceClassToTracedClass.upperBound = -1
				refTraceClassToTracedClass.lowerBound = 0
			}

			// Then going through all properties for the remaining generation
			var Set<EStructuralFeature> runtimeProperties = new HashSet<EStructuralFeature>
			if (allNewEClasses.contains(runtimeClass))
				runtimeProperties.addAll(runtimeClass.EStructuralFeatures)
			else {
				val classExtension = runtimeClass2ClassExtension.get(runtimeClass)
				if (classExtension != null) {
					runtimeProperties.addAll(classExtension.newProperties);
				}
//				for (c2 : mmext.classesExtensions) {
//					if(c2.extendedExistingClass == runtimeClass) {
//						runtimeProperties.addAll(c2.newProperties)
//					}
//				}
			}

			// We remove the copied properties that will become traces
			for (prop : runtimeProperties)
				tracedClass.EStructuralFeatures.remove(runtimeClassescopier.get(prop))

			// Storing traceability stuff
			traceability.putTracedClasses(runtimeClass, tracedClass)
			if (!runtimeProperties.isEmpty)
				traceability.addRuntimeClass(runtimeClass)

			// We go through the runtime properties of this class
			for (runtimeProperty : runtimeProperties) {

				// Storing traceability stuff
				traceability.addMutableProperty(runtimeClass, runtimeProperty)

				// State class
				val valueClass = EcoreFactory.eINSTANCE.createEClass
				valueClass.name = TraceMMStrings.class_createStateClassName(runtimeClass, runtimeProperty)

				// We copy the property inside the state class
				val copiedProperty = runtimeClassescopier.copy(runtimeProperty) as EStructuralFeature
				if (copiedProperty instanceof EReference) {
					copiedProperty.containment = false
					copiedProperty.EOpposite = null // useful ? copy references later...
				}
				valueClass.EStructuralFeatures.add(copiedProperty)
				traceMMExplorer.statesPackage.EClassifiers.add(valueClass)

				ExecutionMetamodelTraceability.createTraceabilityAnnotation(valueClass,
					ExecutionMetamodelTraceability.getTraceabilityAnnotationValue(runtimeProperty));

				// Link Traced class -> Value class
				val refTrace2State = addReferenceToClass(tracedClass,
					TraceMMStrings.ref_createTraceClassToValueClass(runtimeProperty), valueClass);
				refTrace2State.containment = true
				refTrace2State.ordered = true
				refTrace2State.unique = true
				refTrace2State.lowerBound = 0
				refTrace2State.upperBound = -1

				traceability.putTraceOf(runtimeProperty, refTrace2State)

				// Link Value class -> Traced class (bidirectional)
				val refValue2Traced = addReferenceToClass(valueClass, TraceMMStrings.ref_ValueToTrace, tracedClass);
				refValue2Traced.upperBound = 1
				refValue2Traced.lowerBound = 1
				refValue2Traced.EOpposite = refTrace2State
				refTrace2State.EOpposite = refValue2Traced

				// Link State -> Value class
				val refState2Value = addReferenceToClass(traceMMExplorer.stateClass,
					TraceMMStrings.ref_createGlobalToState(valueClass), valueClass);
				refState2Value.ordered = false
				refState2Value.unique = true
				refState2Value.upperBound = -1
				refState2Value.lowerBound = 0

				traceability.putStateClassToValueClass(runtimeProperty, refState2Value)

				// Link State class -> GlobalState (bidirectional)
				val refState2Global = addReferenceToClass(valueClass, TraceMMStrings.ref_ValueToStates,
					traceMMExplorer.stateClass);
				refState2Global.upperBound = -1
				refState2Global.lowerBound = 1
				refState2Global.EOpposite = refState2Value
				refState2Value.EOpposite = refState2Global
			}
		}

	}

}