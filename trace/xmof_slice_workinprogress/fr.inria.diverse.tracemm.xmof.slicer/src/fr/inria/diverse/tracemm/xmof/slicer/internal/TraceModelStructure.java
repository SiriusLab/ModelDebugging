package fr.inria.diverse.tracemm.xmof.slicer.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

public class TraceModelStructure {

	private Set<EClass> tracedObjectClasses = new HashSet<EClass>();
	private Set<EClass> valueClasses = new HashSet<EClass>();
	private Map<EClass, EReference> valueClass2ValueTraceContainmentMap = new HashMap<EClass, EReference>();

	private EClass baseClassTrace;
	private EClass baseClassState;
	private EClass baseClassTracedObjects;

	public void addTracedObjectClass(EClass tracedObjectClass) {
		tracedObjectClasses.add(tracedObjectClass);
		Set<EReference> valueTraceContainments = tracedObjectClass
				.getEReferences().stream()
				.filter(eReference -> eReference.isContainment())
				.collect(Collectors.toSet());
		valueTraceContainments.stream().forEach(
				valueTraceContainment -> addValueClass(
						(EClass) valueTraceContainment.getEType(),
						tracedObjectClass, valueTraceContainment));
	}

	private void addValueClass(EClass valueClass, EClass tracedObjectClass,
			EReference valueTraceContainment) {
		valueClasses.add(valueClass);
		valueClass2ValueTraceContainmentMap.put(valueClass,
				valueTraceContainment);
	}

	public Collection<EClass> getTracedObjectClasses() {
		return Collections.unmodifiableCollection(tracedObjectClasses);
	}

	public Collection<EClass> getValueClasses() {
		return valueClasses;
	}

	public EReference getValueTraceContainment(EClass valueClass) {
		return valueClass2ValueTraceContainmentMap.get(valueClass);
	}

	public Collection<EClass> getAllSuperclassesAndSubclassesOfTracedObjectClass(
			EClass tracedObjectClass) {
		Set<EClass> result = new HashSet<EClass>();
		result.addAll(tracedObjectClasses
				.stream()
				.filter(tracedObjectClassIt -> tracedObjectClassIt
						.getEAllSuperTypes().contains(tracedObjectClass))
				.collect(Collectors.toSet()));
		result.addAll(tracedObjectClass.getEAllSuperTypes());
		return result;
	}

	public EClass getBaseClassTrace() {
		return baseClassTrace;
	}

	public void setBaseClassTrace(EClass baseClassTrace) {
		this.baseClassTrace = baseClassTrace;
	}

	public EClass getBaseClassState() {
		return baseClassState;
	}

	public void setBaseClassState(EClass baseClassState) {
		this.baseClassState = baseClassState;
	}

	public EClass getBaseClassTracedObjects() {
		return baseClassTracedObjects;
	}

	public void setBaseClassTracedObjects(EClass baseClassTracedObjects) {
		this.baseClassTracedObjects = baseClassTracedObjects;
	}

}
