package fr.inria.diverse.tracemm.generator

import org.eclipse.emf.ecore.EClass
import java.util.Set
import org.eclipse.emf.ecore.EReference

/**
 * Second output of the transformation: a class both to access to parts
 * of the trace metamodel, and with links between the original metamodels
 * and the trace metamodel.
 */
class TraceMMGenerationTraceability {
	
	def EClass getGlobalStateClass() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def Set<EClass> getRuntimeClasses() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EClass getTracedClass(org.eclipse.emf.ecore.EClass class1) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def Set<EReference> refs_originalObject(org.eclipse.emf.ecore.EClass class1) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def Set<EReference> getMutableProperties() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EReference getTraceOf(org.eclipse.emf.ecore.EReference reference) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EReference getGlobalToState(org.eclipse.emf.ecore.EReference reference) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EClass getRootClass() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EClass getTracedObjectsClass() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def EClass getEventsClass() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	
	
}