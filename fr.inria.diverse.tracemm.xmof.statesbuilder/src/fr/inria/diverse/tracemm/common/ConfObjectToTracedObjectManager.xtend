package fr.inria.diverse.tracemm.common

import org.eclipse.emf.ecore.EObject
import java.util.Map
import java.util.HashMap
import java.util.Collection
import fr.inria.diverse.tracemm.generator.TraceMMExplorer

/**
 * TODO use and test !
 */
class ConfObjectToTracedObjectManager {

	protected Map<EObject, EObject> converts = new HashMap

	protected SpecificStatesBuilderConfiguration conf

	protected TraceMMExplorer traceMMExplorer

	new(TraceMMExplorer traceMMExplorer, SpecificStatesBuilderConfiguration conf) {
		this.traceMMExplorer = traceMMExplorer
		this.conf = conf
	}

	def EObject convertWithLaterSolving(EObject confObject) {

		// here we should create an instance
		val tracedObject = traceMMExplorer.createTracedObject(confObject.eClass)

		// put in map
		converts.put(confObject, confObject)

		// copy attributes
		for (confAtt : confObject.eClass.EAllAttributes) {
			val traceAtt = tracedObject.eClass.EAllAttributes.findFirst[a|a.name.equals(confAtt.name)]

			if (traceAtt != null) {

				if (!confAtt.many) {
					tracedObject.eSet(traceAtt, confObject.eGet(confAtt))
				} else {
					val confValues = confObject.eGet(confAtt) as Collection<Object>
					val toFill = tracedObject.eGet(traceAtt) as Collection<Object>
					for (confValue : confValues) {
						toFill.add(confValue)
					}
				}
			}
		}

		return confObject
	}

	protected def EObject findTraceVersionOfConfObject(EObject objectRef) {

		// If it points to an object that was converted to a traced object, we set to this traced object
		if (converts.containsKey(objectRef)) {
			converts.get(objectRef)
		}
		// Otherwise to set to the originally pointed object (which should be a static object ? 
		else {

			// Else if the object has an original counterpart, we use it
			val EObject origRef = conf.confToOriginal(objectRef)
			if (origRef != null) {
				origRef
			}
						
			// Else... two transient objects cross ref, and.... :'(
			else {
				println("TWO TRANSIENT OBJECTS CROSS REF? NOT HANDLED WITH INC SOLVING")
				null
			}
		}

	}

	protected def copyReferencesSingle(EObject confObject) {

		val tracedObject = converts.get(confObject) // puts in the map

		// For each ref of the class of the conf object, we look for an equivalent ref in the traced object's class
		for (confRef : confObject.eClass.EAllReferences) {
			val traceRef = tracedObject.eClass.EAllReferences.findFirst[r|r.name.equals(confRef.name)]

			// If we have a matching ref, we copy refs
			if (traceRef != null) {

				// Case single reference
				if (!confRef.many) {

					// We find the object ref to "copy"
					val objectRef = confObject.eGet(confRef)  as EObject
					tracedObject.eSet(traceRef, findTraceVersionOfConfObject(objectRef))
				} 
				
				// Case collection of references
				else {

					// We find the object refs to "copy"
					val objectRefs = confObject.eGet(confRef) as Collection<EObject>
					val Collection<EObject> toFill = tracedObject.eGet(traceRef) as Collection<EObject>
					toFill.clear() // so that we don't have the same issue as EcoreUtil.Copier!
					for (objectRef : objectRefs) {
						toFill.add(findTraceVersionOfConfObject(objectRef))
					}

				}

			}
		}

	}

	def EObject convertWithIncSolving(EObject confObject) {
		val tracedObject = convertWithLaterSolving(confObject) // puts in the map
		copyReferencesSingle(confObject)
		return tracedObject

	}

	def copyReferences() {
		for (confObject : converts.keySet) {
			copyReferencesSingle(confObject)
		}
	}

}
