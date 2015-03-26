package fr.inria.diverse.trace.plugin.generator

import ecorext.ClassExtension
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMStringsCreator
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference

class TraceManagerGenerator {

	// Inputs
	private val String className
	private val String packageQN
	private val EPackage traceMM
	private val EPackage executionMM
	private val TraceMMGenerationTraceability traceability

	public def String getClassName() {
		return className
	}

	new(String languageName, String packageQN, EPackage traceMM, EPackage executionMM,
		TraceMMGenerationTraceability traceability) {
		this.traceMM = traceMM
		this.className = languageName.replaceAll(" ", "") + "TraceManager"
		this.packageQN = packageQN
		this.executionMM = executionMM
		this.traceability = traceability
	}

	private def String getEClassFQN(EClass c) {
		val EPackage p = c.getEPackage
		if (p != null) {
			return getEPackageFQN(p) + "." + c.name
		} else {
			return c.name
		}
	}

	private def String getEPackageFQN(EPackage p) {
		val EPackage superP = p.getESuperPackage
		if (superP != null) {
			return getEPackageFQN(superP) + "." + p.name
		} else {
			return p.name
		}
	}

	private def String stringCreate(EClass c) {
		val EPackage p = c.EPackage
		return getEPackageFQN(p)+"."+p.name.toFirstUpper + "Factory.eINSTANCE.create" + c.name
	}

	public def String generateCode() {
		return '''package «packageQN»

import fr.inria.diverse.trace.api.ITraceManager
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource

«««// We import all classes from the trace metamodel (just to be sure)
««««FOR c : traceMM.eAllContents.filter(EClass).toSet»
«««import «getEClassFQN(c)»
««««ENDFOR»


«««// We import all classes from the exe metamodel (just to be sure)
««««FOR c : executionMM.eAllContents.filter(EClass).toSet»
«««import «getEClassFQN(c)»
««««ENDFOR»



class «className» implements ITraceManager {

	private var «getEClassFQN(traceability.rootClass)» traceRoot
	private var «getEClassFQN(traceability.tracedObjectsClass)» tracedObjects
	private var «getEClassFQN(traceability.eventsClass)» events
	private var Resource executedModel
	private var Map<EObject, EObject> exeToTraced
	private var «getEClassFQN(traceability.globalStateClass)» lastState

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
			 * Storing the state of a «getEClassFQN(c)» object
			 */
			if (o instanceof «getEClassFQN(c)») {

				// First we find the traced object, and we create it of required
				var «getEClassFQN(traced)» tracedObject
				if (!exeToTraced.containsKey(o)) {
					val «getEClassFQN(c)» o_cast = o
					tracedObject = «stringCreate(traced)»
					«FOR origRef : traceability.getRefs_originalObject(traced)»
					tracedObject.«origRef.name» = o_cast
					«ENDFOR»
					exeToTraced.put(o, tracedObject)
					tracedObjects.«TraceMMStringsCreator.ref_createTracedObjectsToTrace(traced)».add(tracedObject)
				} else {
					tracedObject = exeToTraced.get(o) as «getEClassFQN(traced)»
				}
				
				«FOR p : traceability.mutableProperties»
				«val EReference ptrace = traceability.getTraceOf(p)»
				«val EClass stateClass = ptrace.getEType as EClass»
				«val EReference refGlobalToState = traceability.getGlobalToState(p)»

				// Then we compare the value of the field with the last stored value
				// If same value, we create no local state and we refer to the previous
				// TODO to change if we switch from refering the exeMM to refering the AS (as in the paper) -> need to compare to refs to origobjs/tracedobj
				val List<«getEClassFQN(stateClass)»> localTrace = tracedObject.«ptrace.name»
				var «getEClassFQN(stateClass)» previousValue = null
				if (!localTrace.isEmpty)
					previousValue = localTrace.get(localTrace.size - 1)
				if (previousValue != null && previousValue.«p.name» == o.«p.name») {
					
					lastState.«refGlobalToState.name».add(previousValue)

				} // Else we create one
				else {
					val «getEClassFQN(stateClass)» newValue = «stringCreate(stateClass)»
					newValue.«p.name» = o.«p.name»
					tracedObject.«ptrace.name».add(newValue)
					lastState.«refGlobalToState.name».add(newValue)
				}
				}
				«ENDFOR»
			«ENDFOR»
			}
			}
			
			

	/**
	 * For now very simple since only one model (ie the exe one), and no new classes introduced in the extension
	 * TRACE MM DEPENDENT
	 */
	def void goTo(int stepNumber) {
		val «getEClassFQN(traceability.globalStateClass)» stateToGo = traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».get(stepNumber)

		«FOR p : traceability.mutableProperties»
		«val EReference ptrace = traceability.getTraceOf(p)»
		«val EClass stateClass = ptrace.getEType as EClass»

		for (value : stateToGo.«TraceMMStringsCreator.ref_createGlobalToState(stateClass)») {
			«val EReference origRef = traceability.getRefs_originalObject(ptrace.getEContainingClass).get(0)»
			(value.parent.«origRef.name» as «getEClassFQN((p.eContainer as ClassExtension).extendedExistingClass)»).«p.name» = value.«p.name»
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
		val «getEClassFQN(traceability.globalStateClass)» gs = traceRoot.«TraceMMStringsCreator.ref_SystemToGlobal».get(index);
		
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
