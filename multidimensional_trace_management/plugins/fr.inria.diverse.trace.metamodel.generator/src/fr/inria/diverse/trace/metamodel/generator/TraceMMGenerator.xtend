package fr.inria.diverse.trace.metamodel.generator

import fr.inria.diverse.trace.commons.EMFUtil
import java.io.IOException
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.xtend.lib.annotations.Accessors
import ecorext.Ecorext

class TraceMMGenerator {

	// Inputs
	private val Ecorext mmext
	private val EPackage mm
	private val ResourceSet rs
	private val String languageName

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) val EPackage tracemmresult
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) val TraceMMGenerationTraceability traceability

	// Base classes
	private val TraceMMExplorer traceMMExplorer

	private var boolean done = false

	new(Ecorext mmext, EPackage mm) {

		// Storing inputs
		this.mmext = mmext
		this.mm = mm
		
		
		// Create name of the trace metamodel 
		languageName = mm.name.replaceAll(" ", "") + "Trace"

		// Creating resource set to work with
		this.rs = new ResourceSetImpl()
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

		// Create the root package by loading the base ecore and changing its name and stuff
		val Resource base = EMFUtil.loadModelURI(
			URI.createPlatformPluginURI("fr.inria.diverse.trace.metamodel.generator/model/base.ecore", true), rs)
		tracemmresult = base.contents.get(0) as EPackage
		base.contents.remove(tracemmresult)
		tracemmresult.name = languageName
		tracemmresult.nsURI = languageName // TODO
		tracemmresult.nsPrefix = languageName

		// Create an explorer to generically manipulate the generated trace metamodel
		// (mostly base classes)
		this.traceMMExplorer = new TraceMMExplorer(tracemmresult)

		// Changing packages names 
		// TODO use strings classes to name the languages
		traceMMExplorer.stepsPackage.nsURI = languageName + "_Steps"
		traceMMExplorer.statesPackage.nsURI = languageName + "_States"

		// Finally, initializing traceability class 
		this.traceability = new TraceMMGenerationTraceability(traceMMExplorer)

	}

	public def void computeAllMaterial() throws IOException {
		if (!done) {

			val statesGen = new TraceMMGeneratorStates(mmext, mm, traceability, traceMMExplorer, languageName,
				tracemmresult)
			statesGen.process

			val stepsGen = new TraceMMGeneratorSteps(mmext, tracemmresult, traceability, traceMMExplorer)
			stepsGen.process

			done = true
		} else {
			println("ERROR: already computed.")
		}
	}

}
