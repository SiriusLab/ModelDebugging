package fr.inria.diverse.tracemm.generator

import ecorext.Ecorext
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors
import ecorext.ClassExtension
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import java.io.File
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil

class TraceMMGenerator {

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) EPackage tracemmresult

	// Other 
	protected boolean done = false
	protected Copier runtimeClassescopier
	protected Copier propertiesForTracescopier
	protected ResourceSet rs = new ResourceSetImpl()

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
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	}

	public def void computeAllMaterial() {
		if (!done) {
			runtimeClassescopier = new Copier
			propertiesForTracescopier = new Copier
			loadBase()
			handleVariables()
			handleEvents()
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

	protected def handleVariables() {

		// We create a subpackage in which we store all the runtime-only classes of the extension
		val runtimeClassesPackage = EcoreFactory.eINSTANCE.createEPackage
		runtimeClassesPackage.name = "runtimeClasses"
		runtimeClassesPackage.nsURI = "runtimeClasses" // TODO
		runtimeClassesPackage.nsPrefix = "" // TODO
		tracemmresult.ESubpackages.add(runtimeClassesPackage)

		// For each new class, we copy it in the trace MM... or not ? (as compared to events classes)
		for (EClass runtimeClass : mmext.eAllContents.toSet.filter(EClass)) {
			runtimeClassesPackage.EClassifiers.add(runtimeClassescopier.copy(runtimeClass) as EClass)
		}

		for (runtimeProperty : mmext.eAllContents.toSet.filter(EStructuralFeature)) {

			// For each new class property, we create trace class + state class
			// /!\ Two cases: properties inside static classes OR inside runtime classes
			// -> should we trace each property or not? some are probably immutable?
			// Getting the class that "contains" the property
			var EClass containingClass = null
			val container = runtimeProperty.eContainer
			var containerIsRuntimeClass = false
			if (container instanceof ClassExtension) {
				containingClass = container.extendedExistingClass
			} else if (container instanceof EClass) {
				containingClass = container
				containerIsRuntimeClass = true
			}

			// Trace class
			val traceClass = EcoreFactory.eINSTANCE.createEClass
			traceClass.name = containingClass.name + "_" + runtimeProperty.name + "_Trace"
			tracemmresult.EClassifiers.add(traceClass)

			// State class
			val stateClass = EcoreFactory.eINSTANCE.createEClass
			stateClass.name = containingClass.name + "_" + runtimeProperty.name + "_State"
			stateClass.EStructuralFeatures.add(propertiesForTracescopier.copy(runtimeProperty) as EStructuralFeature) // this is where the property is copied
			tracemmresult.EClassifiers.add(stateClass)

			// Link TraceSystem -> Trace class
			val refTraceSystem2Trace = addReferenceToClass(traceSystemClass, traceClass.name.toFirstLower + "s",
				traceClass)
			refTraceSystem2Trace.containment = true
			refTraceSystem2Trace.ordered = false
			refTraceSystem2Trace.unique = true
			refTraceSystem2Trace.upperBound = -1
			refTraceSystem2Trace.lowerBound = 0

			// Link Trace class -> State class
			val refTrace2State = addReferenceToClass(traceClass, "states", stateClass);
			refTrace2State.containment = true
			refTrace2State.ordered = true
			refTrace2State.unique = true
			refTrace2State.lowerBound = 0
			refTrace2State.upperBound = -1

			// Link State class -> Trace class (bidirectional)
			val refState2Trace = addReferenceToClass(stateClass, "parentVariableTrace", traceClass);
			refState2Trace.upperBound = 1
			refState2Trace.lowerBound = 1
			refState2Trace.EOpposite = refTrace2State
			refTrace2State.EOpposite = refState2Trace

			// Link Trace class -> Traced class
			// Either runtime type or not
			var EClassifier tracedObjectType = null
			if (runtimeClassescopier.containsKey(containingClass)) {
				tracedObjectType = runtimeClassescopier.get(containingClass) as EClassifier
			} else {
				tracedObjectType = containingClass
			}

			val refTrace2Class = addReferenceToClass(
				traceClass,
				"tracedObject",
				tracedObjectType
			);
			if (containerIsRuntimeClass)
				refTrace2Class.containment = true
			else
				refTrace2Class.containment = false
			refTrace2Class.upperBound = 1
			refTrace2Class.lowerBound = 1

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

	protected def handleEvents() {

		// For each event class, we copy the class, add a reference to the state, and and create ref from the root 
		for (eventClass : eventsmm.eAllContents.filter(EClass).toSet) {

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

	protected static def EReference addReferenceToClass(EClass clazz, String refName, EClassifier refType) {
		val res = EcoreFactory.eINSTANCE.createEReference
		res.name = refName
		res.EType = refType
		clazz.EStructuralFeatures.add(res)
		return res
	}
}
