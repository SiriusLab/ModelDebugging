package fr.inria.diverse.tracemm.generator

import ecorext.Ecorext
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.xtend.lib.annotations.Accessors

class TraceMMGenerator {

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) EPackage tracemmresult

	// Other 
	protected boolean done = false
	protected Copier runtimeClassescopier
	protected Copier propertiesForTracescopier
	protected ResourceSet rs
	protected HashSet<EClass> referencedStaticClasses
	protected HashSet<EClass> allRuntimeClasses

	// Inputs
	protected Ecorext mmext
	protected EPackage eventsmm

	// Base classes
	protected EClass globalStateClass
	protected EClass traceSystemClass
	protected EClass eventOccClass

	new(Ecorext mmext, EPackage eventsmm) {
		this.mmext = mmext
		this.eventsmm = eventsmm
		this.rs = new ResourceSetImpl()
		this.referencedStaticClasses = new HashSet<EClass>
		this.allRuntimeClasses = new HashSet<EClass>
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	}

	public def void computeAllMaterial() {
		if (!done) {
			runtimeClassescopier = new Copier
			propertiesForTracescopier = new Copier
			loadBase()
			handleTraceClasses()
			handleEvents()
			handlePools()
			runtimeClassescopier.copyReferences
			propertiesForTracescopier.copyReferences
		} else {
			println("ERROR: already computed.")
		}
	}

	def Resource loadModel(URI uri) {
		val res = rs.createResource(uri)
		res.load(null)
		EcoreUtil.resolveAll(rs) // IMPORTANT
		return res
	}

	protected def loadBase() {

		// Create the root package by loading the base ecore and changing its name and stuff
		val Resource base = loadModel(
			URI.createPlatformPluginURI("fr.inria.diverse.tracemm.generator/model/base.ecore", true))
		tracemmresult = base.contents.get(0) as EPackage
		base.contents.remove(tracemmresult)
		tracemmresult.name = "traceSystem"
		tracemmresult.nsURI = "traceSystem" // TODO
		tracemmresult.nsPrefix = "" // TODO

		// Find the TraceSystem class
		traceSystemClass = tracemmresult.EClassifiers.filter[c|c.name.equals("TraceSystem")].get(0) as EClass

		// Find the GlobalState class
		globalStateClass = tracemmresult.EClassifiers.filter[c|c.name.equals("GlobalState")].get(0) as EClass

		// Find the EventOcc class
		eventOccClass = tracemmresult.EClassifiers.filter[c|c.name.equals("EventOccurrence")].get(0) as EClass

	}

	protected val eclass2Trace = new HashMap<EClass, EClass>

	protected def handleTraceClasses() {

		// We go through ALL classes linked to runtime properties, and we create traced versions of them
		// We also store links from eclasses to their trace version
		//val allRuntimeClasses = new HashSet<EClass>
		val Set<EClass> allNewEClasses = mmext.eAllContents.toSet.filter(EClass).toSet
		for (c : mmext.classesExtensions)
			allRuntimeClasses.add(c.extendedExistingClass)
		allRuntimeClasses.addAll(allNewEClasses)
		for (runtimeClass : allRuntimeClasses) {

			// Creating the traced version of the class
			val traceClass = EcoreFactory.eINSTANCE.createEClass
			traceClass.name = "Traced" + runtimeClass.name
			tracemmresult.EClassifiers.add(traceClass)
			runtimeClassescopier.put(runtimeClass, traceClass)

			// If this is a class extension, then we add some links:
			// - inheritance to be able to store the static properties (if pure runtime object)
			// - reference, to be able to refer to the element of the original model (if originally static element of the model)
			if (!allNewEClasses.contains(runtimeClass)) {
				traceClass.ESuperTypes.add(runtimeClass)
				addReferenceToClass(traceClass, "originalObject", runtimeClass)
			}

			// Link TraceSystem -> Trace class
			val refTraceSystem2Trace = addReferenceToClass(traceSystemClass, traceClass.name.toFirstLower + "s",
				traceClass)
			refTraceSystem2Trace.containment = true
			refTraceSystem2Trace.ordered = false
			refTraceSystem2Trace.unique = true
			refTraceSystem2Trace.upperBound = -1
			refTraceSystem2Trace.lowerBound = 0

			// Then going through all properties for the remaining generation
			var Collection<EStructuralFeature> runtimeProperties
			if (allNewEClasses.contains(runtimeClass))
				runtimeProperties = runtimeClass.EAllStructuralFeatures
			else
				runtimeProperties = mmext.classesExtensions.findFirst[c2|c2.extendedExistingClass == runtimeClass].
					newProperties

			// We go through the runtime properties of this class
			for (runtimeProperty : runtimeProperties) {

				// If the property refers to a part of the original metamodel, we store it to create a pool later
				if (runtimeProperty instanceof EReference) {
					val propertyType = runtimeProperty.EType
					if (propertyType instanceof EClass)
						if (!allRuntimeClasses.contains(propertyType))
							referencedStaticClasses.add(propertyType)
				}

				// State class
				val stateClass = EcoreFactory.eINSTANCE.createEClass
				stateClass.name = runtimeClass.name + "_" + runtimeProperty.name + "_State"
				val copiedProperty = propertiesForTracescopier.copy(runtimeProperty) as EStructuralFeature
				stateClass.EStructuralFeatures.add(copiedProperty) // this is where the property is copied
				tracemmresult.EClassifiers.add(stateClass)

				// Link Trace class -> State class
				val refTrace2State = addReferenceToClass(traceClass, runtimeProperty.name + "Trace", stateClass);
				refTrace2State.containment = true
				refTrace2State.ordered = true
				refTrace2State.unique = true
				refTrace2State.lowerBound = 0
				refTrace2State.upperBound = -1

				// Link State class -> Trace class (bidirectional)
				val refState2Trace = addReferenceToClass(stateClass, "parent", traceClass);
				refState2Trace.upperBound = 1
				refState2Trace.lowerBound = 1
				refState2Trace.EOpposite = refTrace2State
				refTrace2State.EOpposite = refState2Trace

				// Link GlobalState -> State class
				val refGlobal2State = addReferenceToClass(globalStateClass, stateClass.name.toFirstLower + "s",
					stateClass);
				refGlobal2State.ordered = false
				refGlobal2State.unique = true
				refGlobal2State.upperBound = -1
				refGlobal2State.lowerBound = 0

				// Link State class -> GlobalState (bidirectional)
				val refState2Global = addReferenceToClass(stateClass, "globalStates", globalStateClass);
				refState2Global.upperBound = -1
				refState2Global.lowerBound = 1
				refState2Global.EOpposite = refGlobal2State
				refGlobal2State.EOpposite = refState2Global
			}
		}

	}

	protected def handleEvents() {

		// For each event class, we copy the class, add a reference to the state, and and create ref from the root 
		for (eventClass : eventsmm.eAllContents.filter(EClass).toSet) {

			// We go through the properties of the eventClass, to look for refs to static classes
			for (prop : eventClass.EAllReferences) {
				val propertyType = prop.EType
				if (propertyType instanceof EClass)
					// If we find one we add it to the collection of used static classes, to create pools later
					if (!allRuntimeClasses.contains(propertyType))
						referencedStaticClasses.add(propertyType)
			}

			// Copying event occurrence class from events mm
			val EClass newClass = runtimeClassescopier.copy(eventClass) as EClass
			tracemmresult.EClassifiers.add(newClass)

			// Adding inheritance to EventOccurence abstract class
			eventClass.ESuperTypes.add(eventOccClass)

			// Link TraceSystem -> Event class
			val ref = addReferenceToClass(traceSystemClass, eventClass.name + "_Trace", newClass)
			ref.lowerBound = 0
			ref.upperBound = -1
			ref.containment = true

		}
	}

	def handlePools() {
		for (eClass : referencedStaticClasses) {

			// Link TraceSystem -> Static class
			val ref = addReferenceToClass(traceSystemClass, "pool_" + eClass.name + "s", eClass)
			ref.containment = true
			ref.upperBound = -1
		}
	}

	protected static def EReference addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
		return res
	}
}
