package fr.inria.diverse.tracemm.generator

import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtend.lib.annotations.Accessors

class TraceMMExplorer {

	EPackage tracemm

	// Base classes
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass globalStateClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass traceSystemClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventOccClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventsTracesClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass tracedObjectsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass staticObjectsPoolsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage eventsPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage tracedPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage statesPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EReference eventToGlobal

	protected EFactory rootFactory;
	protected EFactory eventFactory;

	//protected EFactory tracedFactory;
	protected EFactory stateFactory

	/**
	 * Here we focus on the part of the base trace mm, because TraceMMExplorer is
	 * used in the TraceMMGenerator as well.
	 */
	new(EPackage traceMetamodel) {
		this.tracemm = traceMetamodel

		// Find the TraceSystem class
		traceSystemClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_TraceSystem)] as EClass

		// Find the GlobalState class
		globalStateClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_GlobalState)] as EClass

		// Find the EventOcc class and EventsTraces class and Events package
		eventOccClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_EventOccurrence)] as EClass
		eventsTracesClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_EventsTraces)] as EClass
		eventsPackage = eventOccClass.EPackage

		// Find the TracedObjects class and Traced package
		tracedObjectsClass = tracemm.eAllContents.filter(EClass).findFirst[p|
			p.name.equals(TraceMMStringsCreator.class_TracedObjects)] as EClass
		tracedPackage = tracedObjectsClass.EPackage

		// Find the States package
		statesPackage = tracemm.eAllContents.filter(EPackage).findFirst[p|
			p.name.equals(TraceMMStringsCreator.package_States)] as EPackage

		// Find the StaticObjectsPools class
		staticObjectsPoolsClass = tracemm.eAllContents.filter(EClass).findFirst[p|
			p.name.equals(TraceMMStringsCreator.class_StaticObjectsPools)] as EClass

		eventToGlobal = eventOccClass.EReferences.get(0)

		rootFactory = tracemm.EFactoryInstance
		eventFactory = eventsPackage.EFactoryInstance

		//tracedFactory = tracedPackage.EFactoryInstance
		this.stateFactory = statesPackage.EFactoryInstance

	}

	private Set<EClass> eventClassesCache = null

	def Set<EClass> eventClasses() {
		if (eventClassesCache == null) {
			eventClassesCache = new HashSet
			eventClassesCache.addAll(
				eventsPackage.eAllContents.filter(EClass).filter[c|c != eventOccClass && c != eventsTracesClass].toSet)
		}
		return eventClassesCache
	}

	private Map<EClass, EReference> eventTraceRefOfCache = new HashMap

	def EReference eventTraceRefOf(EClass eventClass) {
		if (!eventTraceRefOfCache.containsKey(eventClass)) {
			eventTraceRefOfCache.put(eventClass,
				eventsTracesClass.EReferences.findFirst[r|
					r.name.equals(TraceMMStringsCreator.ref_createEventsTracesToEvent(eventClass))])
		}
		return eventTraceRefOfCache.get(eventClass)
	}

	def EObject createEventOccurrence(EClass eventClass) {
		return eventFactory.create(eventClass)
	}

	def EObject createTracedObject(EClass tracedClass) {

		// TODO provide somewhere a generic create method? not related to trace mm explorer
		return tracedClass.EPackage.EFactoryInstance.create(tracedClass)
	}

	def EObject createState(EClass stateClass) {
		return stateFactory.create(stateClass)
	}

	private EReference ref_traceSystemToTracedObjectsCache

	def EReference ref_traceSystemToTracedObjects() {
		if (ref_traceSystemToTracedObjectsCache == null)
			ref_traceSystemToTracedObjectsCache = this.traceSystemClass.EReferences.findFirst[r|
				r.name.equals(TraceMMStringsCreator.ref_SystemToTracedObjects)]
		return ref_traceSystemToTracedObjectsCache
	}

	private EReference ref_traceSystemToEventsTraceCache

	def EReference ref_traceSystemToEventsTrace() {
		if (ref_traceSystemToEventsTraceCache == null)
			ref_traceSystemToEventsTraceCache = this.traceSystemClass.EReferences.findFirst[r|
				r.name.equals(TraceMMStringsCreator.ref_SystemToEvents)]
		return ref_traceSystemToEventsTraceCache
	}

	private EReference ref_traceSystemToPoolsCache

	def EReference ref_traceSystemToPools() {
		if (ref_traceSystemToPoolsCache == null)
			ref_traceSystemToPoolsCache = this.traceSystemClass.EReferences.findFirst[r|
				r.name.equals(TraceMMStringsCreator.ref_SystemToPools)]
		return ref_traceSystemToPoolsCache
	}

	// References to state classes from the global state class
	private var Set<EReference> refs_stateRefsFromGSCache

	def Set<EReference> refs_stateRefsFromGS() {
		if (refs_stateRefsFromGSCache == null)
			refs_stateRefsFromGSCache = globalStateClass.getEAllReferences.filter[r|
				!r.name.equals("eventsTriggeredDuringGlobalState")].toSet
		return refs_stateRefsFromGSCache
	}

	def Set<EReference> refs_originalObject(EClass traceClass) {
		return traceClass.EAllReferences.filter[r|r.name.startsWith(TraceMMStringsCreator.ref_OriginalObject)].toSet
	}

}
