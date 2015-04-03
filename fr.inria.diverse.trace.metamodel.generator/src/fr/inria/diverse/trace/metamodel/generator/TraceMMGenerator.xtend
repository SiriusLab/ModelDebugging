package fr.inria.diverse.trace.metamodel.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.EMFUtil
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.xtend.lib.annotations.Accessors

import static fr.inria.diverse.trace.commons.EcoreCraftingUtil.*
import java.io.IOException

class TraceMMGenerator {

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) var EPackage tracemmresult
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) val TraceMMGenerationTraceability traceability

	// Other 
	private boolean done = false
	private Copier runtimeClassescopier

	private ResourceSet rs
	private Set<EClass> referencedStaticClasses
	private Set<EClass> allRuntimeClasses
	private Set<EClass> allStaticClasses
	private Set<EClass> allNewEClasses
	private Set<EPackage> allNewEPackages

	// Inputs
	private Ecorext mmext
	private EPackage eventsmm
	private EPackage mm

	// Base classes
	private TraceMMExplorer traceMMExplorer

	new(Ecorext mmext, EPackage eventsmm, EPackage mm) {
		this.mm = mm
		this.mmext = mmext
		this.eventsmm = eventsmm
		this.rs = new ResourceSetImpl()
		this.referencedStaticClasses = new HashSet<EClass>
		this.allRuntimeClasses = new HashSet<EClass>
		this.allStaticClasses = new HashSet<EClass>
		this.allNewEClasses = mmext.eAllContents.toSet.filter(EClass).toSet
		this.allNewEPackages = mmext.eAllContents.toSet.filter(EPackage).toSet
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		this.traceability = new TraceMMGenerationTraceability
	}

	public def void computeAllMaterial() throws IOException {
		if (!done) {
			runtimeClassescopier = new Copier
			loadBase()
			handleTraceClasses()
			handleEvents()
			runtimeClassescopier.copyReferences
			cleanup()
			done = true
		} else {
			println("ERROR: already computed.")
		}
	}

	private def void cleanup() {
		for (c : this.tracemmresult.eAllContents.filter(EClass).toSet) {
			c.EAnnotations.clear
			c.EOperations.clear
		}

		for (r : runtimeClassescopier.values.filter(EReference)) {
			r.EOpposite = null
		}
	}

	private def void loadBase() throws IOException {

		// Create the root package by loading the base ecore and changing its name and stuff
		val Resource base = EMFUtil.loadModelURI(
			URI.createPlatformPluginURI("fr.inria.diverse.trace.metamodel.generator/model/base.ecore", true), rs)
		val String languageName = mm.name.replaceAll(" ", "") + "Trace"
		tracemmresult = base.contents.get(0) as EPackage
		base.contents.remove(tracemmresult)
		tracemmresult.name = languageName
		tracemmresult.nsURI = languageName //TODO
		tracemmresult.nsPrefix = languageName

		this.traceMMExplorer = new TraceMMExplorer(tracemmresult)

		// Filling the traceability object
		traceability.globalStateClass = traceMMExplorer.globalStateClass
		traceability.eventsClass = traceMMExplorer.eventsClass
		traceability.rootClass = traceMMExplorer.traceClass
		traceability.tracedObjectsClass = traceMMExplorer.tracedObjectsClass
		traceability.eventOccurrenceClass = traceMMExplorer.eventOccClass
	}

	//private val eclass2Trace = new HashMap<EClass, EClass>
	private def EPackage obtainTracedPackage(EPackage runtimePackage) {
		var EPackage result = traceMMExplorer.tracedPackage

		if (runtimePackage != null) {
			val tracedSuperPackage = obtainTracedPackage(runtimePackage.ESuperPackage)
			val String tracedPackageName = TraceMMStringsCreator.package_createTracedPackage(runtimePackage)
			result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(tracedPackageName)]
			if (result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = tracedPackageName
				result.nsURI = result.name // TODO
				result.nsPrefix = "" // TODO
				tracedSuperPackage.ESubpackages.add(result)
			}
		}
		return result
	}

	private def handleTraceClasses() {

		// First we find ALL classes linked to runtime properties
		for (c : mmext.classesExtensions) {
			allRuntimeClasses.add(c.extendedExistingClass)
			allRuntimeClasses.addAll(c.extendedExistingClass.EAllSuperTypes)
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
			val traceClass = runtimeClassescopier.copy(runtimeClass) as EClass
			traceClass.name = TraceMMStringsCreator.class_createTraceClassName(runtimeClass)

			// We recreate the same package organization
			val tracedPackage = obtainTracedPackage(runtimeClass.EPackage)
			tracedPackage.EClassifiers.add(traceClass)

			// Removing all containments in the references obtained
			for (prop : traceClass.EReferences) {
				prop.containment = false
				prop.EOpposite = null
			}

			// We remove all attributes from the class (since accessible from "originalObject"): 
			traceClass.EStructuralFeatures.removeAll(traceClass.EStructuralFeatures.filter(EAttribute))

			// If this is a class extension, then we add a reference, to be able to refer to the element of the original model (if originally static element of the model)
			if (!allNewEClasses.contains(runtimeClass) && !traceClass.abstract &&
				// Also we must check that there isn't already a concrete class in the super classes, which would have its own origObj ref
				// TODO this is not enough ! it is possible to have a concrete class with no originalObject link! (eg new class in the extension)
				runtimeClass.EAllSuperTypes.forall[c|c.abstract]) {
				var refName = ""
				if (multipleOrig.contains(runtimeClass)) {
					refName = TraceMMStringsCreator.ref_OriginalObject_MultipleInheritance(runtimeClass)
				} else {
					refName = TraceMMStringsCreator.ref_OriginalObject
				}
				val EReference ref = addReferenceToClass(traceClass, refName, runtimeClass)
				traceability.addRefs_originalObject(traceClass, ref)
			}

			// Link TracedObjects -> Trace class
			if (!traceClass.abstract) {
				val refTraceSystem2Trace = addReferenceToClass(traceMMExplorer.tracedObjectsClass,
					TraceMMStringsCreator.ref_createTracedObjectsToTrace(traceClass), traceClass)
				refTraceSystem2Trace.containment = true
				refTraceSystem2Trace.ordered = false
				refTraceSystem2Trace.unique = true
				refTraceSystem2Trace.upperBound = -1
				refTraceSystem2Trace.lowerBound = 0
			}

			// Then going through all properties for the remaining generation
			var Set<EStructuralFeature> runtimeProperties = new HashSet<EStructuralFeature>
			if (allNewEClasses.contains(runtimeClass))
				runtimeProperties.addAll(runtimeClass.EStructuralFeatures)
			else {
				for (c2 : mmext.classesExtensions) {
					if (c2.extendedExistingClass == runtimeClass) {
						runtimeProperties.addAll(c2.newProperties)
					}
				}
			}

			// We remove the copied properties that will become traces
			for (prop : runtimeProperties)
				traceClass.EStructuralFeatures.remove(runtimeClassescopier.get(prop))

			// Storing traceability stuff
			traceability.putTracedClasses(runtimeClass, traceClass)
			if (!runtimeProperties.isEmpty)
				traceability.addRuntimeClass(runtimeClass)

			// We go through the runtime properties of this class
			for (runtimeProperty : runtimeProperties) {

				// Storing traceability stuff
				traceability.addMutableProperty(runtimeClass,runtimeProperty)

				// State class
				val stateClass = EcoreFactory.eINSTANCE.createEClass
				stateClass.name = TraceMMStringsCreator.class_createStateClassName(runtimeClass, runtimeProperty)

				// We copy the property inside the state class
				val copiedProperty = runtimeClassescopier.copy(runtimeProperty) as EStructuralFeature
				if (copiedProperty instanceof EReference) {
					copiedProperty.containment = false
					copiedProperty.EOpposite = null // useful ? copy references later...
				}
				stateClass.EStructuralFeatures.add(copiedProperty)
				traceMMExplorer.statesPackage.EClassifiers.add(stateClass)

				// Link Trace class -> State class
				val refTrace2State = addReferenceToClass(traceClass,
					TraceMMStringsCreator.ref_createTraceToState(runtimeProperty), stateClass);
				refTrace2State.containment = true
				refTrace2State.ordered = true
				refTrace2State.unique = true
				refTrace2State.lowerBound = 0
				refTrace2State.upperBound = -1

				traceability.putTraceOf(runtimeProperty, refTrace2State)

				// Link State class -> Trace class (bidirectional)
				val refState2Trace = addReferenceToClass(stateClass, TraceMMStringsCreator.ref_StateToTrace, traceClass);
				refState2Trace.upperBound = 1
				refState2Trace.lowerBound = 1
				refState2Trace.EOpposite = refTrace2State
				refTrace2State.EOpposite = refState2Trace

				// Link GlobalState -> State class
				val refGlobal2State = addReferenceToClass(traceMMExplorer.globalStateClass,
					TraceMMStringsCreator.ref_createGlobalToState(stateClass), stateClass);
				refGlobal2State.ordered = false
				refGlobal2State.unique = true
				refGlobal2State.upperBound = -1
				refGlobal2State.lowerBound = 0

				traceability.putGlobalToState(runtimeProperty, refGlobal2State)

				// Link State class -> GlobalState (bidirectional)
				val refState2Global = addReferenceToClass(stateClass, TraceMMStringsCreator.ref_StateToGlobal,
					traceMMExplorer.globalStateClass);
				refState2Global.upperBound = -1
				refState2Global.lowerBound = 1
				refState2Global.EOpposite = refGlobal2State
				refGlobal2State.EOpposite = refState2Global
			}
		}

	}

	private def handleEvents() {

		// For each event class, we copy the class, add a reference to the state, and and create ref from the root 
		for (eventClass : eventsmm.eAllContents.filter(EClass).toSet) {

			// Copying event occurrence class from events mm
			val EClass newClass = runtimeClassescopier.copy(eventClass) as EClass
			traceMMExplorer.eventsPackage.EClassifiers.add(newClass)
			traceability.addEventClass(newClass)

			// Adding inheritance to EventOccurence abstract class
			eventClass.ESuperTypes.add(traceMMExplorer.eventOccClass)

			// Link EventsTraces -> Event class
			val ref = addReferenceToClass(traceMMExplorer.eventsClass,
				TraceMMStringsCreator.ref_createEventsTracesToEvent(eventClass), newClass)
			ref.lowerBound = 0
			ref.upperBound = -1
			ref.containment = true
			traceability.addEventTrace(newClass,ref)

		}
	}

}
