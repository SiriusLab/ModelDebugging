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

class TraceMMGenerator {

	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) EPackage tracemmresult

	protected boolean done = false
	protected Copier copier
	protected Ecorext mmext
	protected EPackage eventsmm
	protected EClass globalStateClass
	protected EClass traceSystemClass

	new(Ecorext mmext, EPackage eventsmm) {
		this.mmext = mmext
		this.eventsmm = eventsmm
	}

	public def void computeAllMaterial() {
		if (!done) {
			copier = new Copier
			createBase()
			handleVariables()
			handleEvents()
			copier.copyReferences
		} else {
			println("ERROR: already computed.")
		}
	}

	protected def createBase() {

		// Create the root package
		tracemmresult = EcoreFactory.eINSTANCE.createEPackage
		tracemmresult.name = "traceSystem"
		tracemmresult.nsURI = "traceSystem" // TODO
		tracemmresult.nsPrefix = "" // TODO

		// Creating the TraceSystem class
		traceSystemClass = EcoreFactory.eINSTANCE.createEClass
		traceSystemClass.name = "TraceSystem"
		tracemmresult.EClassifiers.add(traceSystemClass)

		// Creating the GlobalState class
		globalStateClass = EcoreFactory.eINSTANCE.createEClass
		globalStateClass.name = "GlobalState"
		tracemmresult.EClassifiers.add(globalStateClass)
		val refTraceToGlobal = addReferenceToClass(traceSystemClass, "globalTrace", globalStateClass)
		refTraceToGlobal.containment = true
		refTraceToGlobal.ordered = true
		refTraceToGlobal.unique = true
		refTraceToGlobal.upperBound = -1
		refTraceToGlobal.lowerBound = 0

	}


	protected def handleVariables() {

		for (o : mmext.eAllContents.toSet) {

			// For each new class, we copy it in the trace MM
			if (o instanceof EClass) {
			}

			// For each new class property, we create trace class + state class
			if (o instanceof EStructuralFeature) {
			}

		}

	}

	protected def handleEvents() {

		// For each event class, we copy the class, add a reference to the state, and and create ref from the root 
		for (e : eventsmm.eAllContents.filter(EClass).toSet) {
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
