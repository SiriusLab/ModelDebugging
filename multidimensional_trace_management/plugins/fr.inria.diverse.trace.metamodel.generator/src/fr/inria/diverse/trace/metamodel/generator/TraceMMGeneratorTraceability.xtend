package fr.inria.diverse.trace.metamodel.generator

import org.eclipse.emf.ecore.EClass
import java.util.Set
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.HashSet
import java.util.Map
import java.util.HashMap
import org.eclipse.emf.ecore.EStructuralFeature
import ecorext.Ecorext
import ecorext.Rule

/**
 * Second output of the transformation: a class both to access to parts
 * of the trace metamodel, and with links between the original metamodels
 * and the trace metamodel.
 */
class TraceMMGenerationTraceability {

	new(TraceMMExplorer traceMMExplorer, Ecorext mmext) {
		this.traceMMExplorer = traceMMExplorer
		this.mmext = mmext
	}

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	private val TraceMMExplorer traceMMExplorer
	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	private val Ecorext mmext

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

	public def Set<EStructuralFeature> getMutablePropertiesOf(EClass c) {
		if (mutableProperties.containsKey(c)) {
			return mutableProperties.get(c).immutableCopy
		} else {
			return #{}
		}
	}

	public def Set<EStructuralFeature> getAllMutableProperties() {
		return mutableProperties.values.flatten.toSet
	}

	val tracedClasses = new HashMap<EClass, EClass>

	package def void putTracedClasses(EClass runtimeClass, EClass tracedClass) {
		tracedClasses.put(runtimeClass, tracedClass)
	}

	public def EClass getTracedClass(org.eclipse.emf.ecore.EClass mutableClass) {
		return tracedClasses.get(mutableClass)
	}

	public def boolean hasTracedClass(EClass mutableClass) {
		return tracedClasses.containsKey(mutableClass)
	}

	public def Set<EClass> getAllMutableClasses() {
		return tracedClasses.keySet;
	}

	public def EClass getMutableClass(org.eclipse.emf.ecore.EClass tracedClass) {
		val mutClass = tracedClasses.entrySet.findFirst[p|p.value == tracedClass]
		if (mutClass != null)
			return mutClass.key
		else
			return null
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

	private Map<EStructuralFeature, EReference> stateClassToValueClass = new HashMap<EStructuralFeature, EReference>

	package def void putStateClassToValueClass(EStructuralFeature r1, EReference r2) {
		stateClassToValueClass.put(r1, r2)
	}

	public def EReference getStateClassToValueClass(EStructuralFeature s) {
		return stateClassToValueClass.get(s)
	}

	private Set<EClass> stepClasses = new HashSet<EClass>

	package def void addStepClass(EClass c) {
		stepClasses.add(c)
	}

	public def Set<EClass> getStepClasses() {
		return stepClasses.immutableCopy
	}

	private val Map<Rule, EClass> stepRuleToStepClass = new HashMap

	package def void addStepRuleToStepClass(Rule stepRule, EClass stepClass) {
		stepRuleToStepClass.put(stepRule, stepClass)
	}

	public def EClass getStepClassFromStepRule(Rule stepRule) {
		return stepRuleToStepClass.get(stepRule)
	}

	private Set<EClass> bigStepClasses = new HashSet<EClass>

	package def void addBigStepClass(EClass c) {
		bigStepClasses.add(c)
	}

	public def Set<EClass> getBigStepClasses() {
		return bigStepClasses.immutableCopy
	}

	private val Map<EClass, EReference> stepSequences = new HashMap

	package def void addStepSequence(EClass stepClass, EReference trace) {
		stepSequences.put(stepClass, trace)
	}

	public def EReference getStepSequence(EClass stepClass) {
		return stepSequences.get(stepClass)
	}

	def boolean hasExeClass(EClass tracedClass) {
		return tracedClasses.keySet.exists[k|tracedClasses.get(k) == tracedClass];
	}

	def EClass getExeClass(EClass tracedClass) {
		return tracedClasses.keySet.findFirst[k|tracedClasses.get(k) == tracedClass];
	}

}
