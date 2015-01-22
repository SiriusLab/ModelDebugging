package fr.inria.diverse.tracemm.generator

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature

class TraceMMStringsCreator {

	public static val String class_TraceSystem = "TraceSystem"

	public static val String class_GlobalState = "GlobalState"

	public static val String class_EventOccurrence = "EventOccurrence"

	public static val String class_EventsTraces = "EventsTraces"

	public static val String class_TracedObjects = "TracedObjects"

	public static val String class_StaticObjectsPools = "StaticObjectsPools"
	
	public static val String package_States = "States"

	public static val String ref_OriginalObject = "originalObject"

	public static val String ref_StateToTrace = "parent"

	public static val String ref_StateToGlobal = "globalStates"

	static def String class_createTraceClassName(EClass runtimeClass) { "Traced" + runtimeClass.name }

	static def String ref_createTracedObjectsToTrace(EClass traceClass) { traceClass.name.toFirstLower + "s" }

	static def String class_createStateClassName(EClass runtimeClass, EStructuralFeature runtimeProperty) {
		runtimeClass.name + "_" + runtimeProperty.name + "_State"
	}

	static def String ref_createTraceToState(EStructuralFeature runtimeProperty) { runtimeProperty.name + "Trace" }

	static def String ref_createGlobalToState(EClass stateClass) { stateClass.name.toFirstLower + "s" }

	static def String ref_createEventsTracesToEvent(EClass eventClass) { eventClass.name + "_Trace" }

	static def String ref_createStaticObjectsToStatic(EClass staticClass) {"pool_" + staticClass.name + "s"}

}
