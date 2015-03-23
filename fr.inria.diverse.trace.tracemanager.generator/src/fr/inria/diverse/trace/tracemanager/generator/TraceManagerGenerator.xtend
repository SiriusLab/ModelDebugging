package fr.inria.diverse.trace.tracemanager.generator

import fr.inria.diverse.tracemm.generator.TraceMMGenerationTraceability
import fr.inria.diverse.tracemm.generator.TraceMMStringsCreator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference

class TraceManagerGenerator {

	val String languageName
	val String packageQN
	val EPackage traceMM
	val EPackage executionMM
	val TraceMMGenerationTraceability traceability

	new(String languageName, String packageQN, EPackage traceMM, EPackage executionMM,
		TraceMMGenerationTraceability traceability) {
		this.traceMM = traceMM
		this.languageName = languageName.replaceAll(" ", "")
		this.packageQN = packageQN
		this.executionMM = executionMM
		this.traceability = traceability
	}

	def String getEClassFQN(EClass c) {
		val EPackage p = c.EPackage
		if (p != null) {
			return getEPackageFQN(p) + "." + c.name
		} else {
			return c.name
		}
	}

	def String getEPackageFQN(EPackage p) {
		val EPackage superP = p.ESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP) + "." + p.name
		} else {
			return p.name
		}
	}

	def String stringCreate(EClass c) {
		return c.EPackage.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name.toFirstLower
	}

	def String generate() {
		return '''package «packageQN»

import fr.inria.diverse.trace.api.ITraceManager
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
// TODO imports from extended mm

// We import all classes from the trace metamodel (just to be sure)
«FOR c : traceMM.eAllContents.filter(EClass).toSet»
imports «getEClassFQN(c)»
«ENDFOR»

// We import all classes from the exe metamodel (just to be sure)
«FOR c : executionMM.eAllContents.filter(EClass).toSet»
imports «getEClassFQN(c)»
«ENDFOR»


class «languageName»TraceManager implements ITraceManager {

	private var «TraceMMStringsCreator.class_TraceSystem» traceRoot
	private var «TraceMMStringsCreator.class_TracedObjects» tracedObjects
	private var «TraceMMStringsCreator.class_EventsTraces» events
	private var Resource executedModel
	private var Map<EObject, EObject> exeToTraced
	private var «TraceMMStringsCreator.class_GlobalState» lastState

	private var Resource traceResource

	new(Resource exeModel, Resource traceResource) {
		this.traceResource = traceResource
		this.executedModel = exeModel
	}

	/**
	 * Note: does NOT check that the model has indeed been modified!! Might be useless
	 * TRACE MM DEPENDENT
	 */
	override void addState() {

		lastState = «stringCreate(traceability.globalStateClass)»
		traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».add(lastState)

		// We look at each object instance of a class with mutable properties 
		// Each of these objects should eventually become a traced object
		for (EObject o : executedModel.allContents.toSet) {

			«FOR c : traceability.runtimeClasses»
			«val traced = traceability.getTracedClass(c)»

			/**
			 * Storing the state of a «c.name» object
			 */
			if (o instanceof «c.name») {

				// First we find the traced object, and we create it of required
				var «traced.name» tracedObject
				if (!exeToTraced.containsKey(o)) {
					val «c.name» o_cast = o
					tracedObject = «stringCreate(traced)»
					«FOR origRef : traceability.refs_originalObject(traced)»
					tracedObject.«origRef.name» = o_cast
					«ENDFOR»
					exeToTraced.put(o, tracedObject)
					tracedObjects.«TraceMMStringsCreator.ref_createTracedObjectsToTrace(traced)».add(tracedObject)
				} else {
					tracedObject = exeToTraced.get(o) as «traced.name»
				}
				
				«FOR p : traceability.mutableProperties»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass stateClass = ptrace.EType as EClass»
				«val EReference refGlobalToState = traceability.getGlobalToState(p)»

				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				// TODO to change if we switch from refering the exeMM to refering the AS (as in the paper) -> need to compare to refs to origobjs/tracedobj
				val List<«stateClass.name»> localTrace = tracedObject.«ptrace.name»
				var «stateClass.name» previousValue = null
				if (!localTrace.isEmpty)
					previousValue = localTrace.get(localTrace.size - 1)
				if (previousValue != null && previousValue.«p.name» == o.«p.name») {
					
					lastState.«refGlobalToState.name».add(previousValue)

				} // Else we create one
				else {
					val «stateClass.name» newValue = «stringCreate(stateClass)»
					newValue.«p.name» = o.«p.name»
					tracedObject.«ptrace.name».add(newValue)
					lastState.«refGlobalToState.name».add(newValue)
				}
				}
				«ENDFOR»
			«ENDFOR»
			
			

	/**
	 * For now very simple since only one model (ie the exe one), and no new classes introduced in the extension
	 * TRACE MM DEPENDENT
	 */
	def void goTo(int stepNumber) {
		val «traceability.globalStateClass» stateToGo = traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».get(stepNumber)

		«FOR p : traceability.mutableProperties»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.EType as EClass»

		for (value : stateToGo.«TraceMMStringsCreator.ref_createTracedObjectsToTrace(stateClass)») {
			«val EReference origRef = traceability.refs_originalObject(ptrace.EContainingClass).get(0)»
			(value.parent.«origRef.name» as «p.EContainingClass.name»).«p.name» = value.«p.name»
		}
		
		«ENDFOR»

	}

	/**
	 * TODO how to get the parameters of the operation call?
	 * TRACE MM DEPENDENT
	 */
	override void addEvent() {
	}

	override initTrace() {
		// Create root
		this.traceRoot = «stringCreate(traceability.rootClass)»
		
		// Put in the resource
		traceResource.contents.add(traceRoot)

		// Create objects storage
		this.tracedObjects = «stringCreate(traceability.getTracedObjectsClass)»
		this.traceRoot.«TraceMMStringsCreator.ref_SystemToTracedObjects» = tracedObjects

		// Create events storage
		this.events = «stringCreate(traceability.eventsClass)»
		this.traceRoot.«TraceMMStringsCreator.ref_SystemToEvents» = events

		// Initializing the map exeobject -> tracedobject
		this.exeToTraced = new HashMap<EObject, EObject>
	}

	override save() {
		traceResource.save(null)
	}

	override getTraceRoot() {
		return traceRoot
	}

	override getExecutionState(int index) {
		return traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».get(index);
	}

	override getDescriptionOfExecutionState(int index) {
		var String result = ""
		val «TraceMMStringsCreator.class_GlobalState» gs = traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».get(index);
		
		«FOR p : traceability.mutableProperties»
		«val EReference refGlobalToState = traceability.getGlobalToState(p)»
		
		result += "«p.name.toFirstUpper»s:"
		for (currenState : gs.«refGlobalToState.name») {
			result += "\n\t" + currenState.«p.name»
		}
		«ENDFOR»
		return result
	}

	override getTraceSize() {
		return traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».size
	}

}
		'''
	}

}