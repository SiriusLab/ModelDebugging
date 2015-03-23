package fr.inria.diverse.tracemm.generator

import org.eclipse.emf.ecore.EClass
import java.util.Set
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.HashSet
import java.util.Map
import java.util.HashMap

/**
 * Second output of the transformation: a class both to access to parts
 * of the trace metamodel, and with links between the original metamodels
 * and the trace metamodel.
 */
class TraceMMGenerationTraceability {

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	EClass globalStateClass

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	EClass rootClass

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	EClass tracedObjectsClass

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	EClass eventsClass

	private Set<EClass> runtimeClasses = new HashSet<EClass>

	package def void addRuntimeClass(EClass c) {
		runtimeClasses.add(c)
	}

	public def Set<EClass> getRuntimeClasses() {
		return runtimeClasses.immutableCopy
	}

	private Set<EReference> mutableProperties = new HashSet<EReference>

	package def void addMutableProperty(EReference r) {
		mutableProperties.add(r)
	}

	public def Set<EReference> getMutableProperties() {
		return mutableProperties.immutableCopy
	}

	private Map<EClass, EClass> tracedClasses = new HashMap<EClass, EClass>

	package def void putTracedClasses(EClass c1, EClass c2) {
		tracedClasses.put(c1, c2)
	}

	public def EClass getTracedClass(org.eclipse.emf.ecore.EClass class1) {
		return tracedClasses.get(class1)
	}

	private Map<EClass, Set<EReference>> refs_originalObject = new HashMap<EClass, Set<EReference>>

	package def void addRefs_originalObject(EClass c1, EReference r) {
		if (!refs_originalObject.containsKey(c1))
			refs_originalObject.put(c1, new HashSet)
		refs_originalObject.get(c1).add(r)
	}

	public def Set<EReference> getRefs_originalObject(org.eclipse.emf.ecore.EClass class1) {
		return refs_originalObject.get(class1)
	}

	private Map<EReference, EReference> traceOf = new HashMap<EReference, EReference>

	package def void putTraceOf(EReference r1, EReference r2) {
		traceOf.put(r1, r2)
	}

	public def EReference getTraceOf(org.eclipse.emf.ecore.EReference reference) {
		return traceOf.get(reference)
	}

	private Map<EReference, EReference> globalToState = new HashMap<EReference, EReference>

	package def void putGlobalToState(EReference r1, EReference r2) {
		globalToState.put(r1, r2)
	}

	public def EReference getGlobalToState(org.eclipse.emf.ecore.EReference reference) {
		return globalToState.get(reference)
	}

}
