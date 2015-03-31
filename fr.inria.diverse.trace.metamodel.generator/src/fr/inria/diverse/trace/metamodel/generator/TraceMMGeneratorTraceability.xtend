package fr.inria.diverse.trace.metamodel.generator

import org.eclipse.emf.ecore.EClass
import java.util.Set
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.HashSet
import java.util.Map
import java.util.HashMap
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Second output of the transformation: a class both to access to parts
 * of the trace metamodel, and with links between the original metamodels
 * and the trace metamodel.
 */
class TraceMMGenerationTraceability {

	new() {
		// Everything is handled below
	}

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

	private Map<EClass, Set<EStructuralFeature>> mutableProperties = new HashMap<EClass, Set<EStructuralFeature>>

	package def void addMutableProperty(EClass c, EStructuralFeature r) {
		if (!mutableProperties.containsKey(c))
			mutableProperties.put(c, new HashSet)
		mutableProperties.get(c).add(r)
	}

	//	public def Set<EStructuralFeature> getMutableProperties() {
	//		return mutableProperties.immutableCopy
	//	}
	public def Set<EStructuralFeature> getMutablePropertiesOf(EClass c) {
		if (mutableProperties.containsKey(c)) {
			return mutableProperties.get(c).immutableCopy
		} else {
			return null
		}
	}
	
	public def Set<EStructuralFeature> getAllMutableProperties() {
		return mutableProperties.values.flatten.toSet
	}
	
	val tracedClasses = new HashMap<EClass, EClass>

	package def void putTracedClasses(EClass runtimeClass, EClass tracedClass) {
		tracedClasses.put(runtimeClass, tracedClass)
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
		val Set<EReference> res = new HashSet<EReference>
		val existingRefs = class1.EAllSuperTypes.map[c|getRefs_originalObject(c)].flatten.toSet
		res.addAll(existingRefs)
		val refsForThisClass = refs_originalObject.get(class1)
		if (refsForThisClass != null && !refsForThisClass.isEmpty)
			res.addAll(refsForThisClass)
		return res
	}

	private Map<EStructuralFeature, EReference> traceOf = new HashMap<EStructuralFeature, EReference>

	package def void putTraceOf(EStructuralFeature r1, EReference r2) {
		traceOf.put(r1, r2)
	}

	public def EReference getTraceOf(EStructuralFeature s) {
		return traceOf.get(s)
	}

	private Map<EStructuralFeature, EReference> globalToState = new HashMap<EStructuralFeature, EReference>

	package def void putGlobalToState(EStructuralFeature r1, EReference r2) {
		globalToState.put(r1, r2)
	}

	public def EReference getGlobalToState(EStructuralFeature s) {
		return globalToState.get(s)
	}

}
