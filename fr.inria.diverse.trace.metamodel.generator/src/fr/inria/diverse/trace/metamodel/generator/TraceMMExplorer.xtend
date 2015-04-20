package fr.inria.diverse.trace.metamodel.generator

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
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass traceClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventOccClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass macroEventClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass tracedObjectsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage eventsPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage tracedPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage statesPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EReference eventToGlobal

	protected EFactory rootFactory;
	protected EFactory eventFactory;

	// protected EFactory tracedFactory;
	protected EFactory stateFactory

	/**
	 * Here we focus on the part of the base trace mm, because TraceMMExplorer is
	 * used in the TraceMMGenerator as well.
	 */
	new(EPackage traceMetamodel) {
		this.tracemm = traceMetamodel

		// Find the TraceSystem class
		traceClass = tracemm.eAllContents.filter(EClass).findFirst [ c |
			c.name.equals(TraceMMStringsCreator.class_TraceSystem)
		] as EClass

		// Find the GlobalState class
		globalStateClass = tracemm.eAllContents.filter(EClass).findFirst [ c |
			c.name.equals(TraceMMStringsCreator.class_GlobalState)
		] as EClass

		// Find the EventOcc class and EventsTraces class and Events package
		eventOccClass = tracemm.eAllContents.filter(EClass).findFirst [ c |
			c.name.equals(TraceMMStringsCreator.class_EventOccurrence)
		] as EClass
		
		macroEventClass = tracemm.eAllContents.filter(EClass).findFirst [ c |
			c.name.equals(TraceMMStringsCreator.class_MacroEvent)
		] as EClass
		
		eventsClass = tracemm.eAllContents.filter(EClass).findFirst [ c |
			c.name.equals(TraceMMStringsCreator.class_EventsTraces)
		] as EClass
		
		eventsPackage = eventOccClass.EPackage

		// Find the TracedObjects class and Traced package
		tracedObjectsClass = tracemm.eAllContents.filter(EClass).findFirst [ p |
			p.name.equals(TraceMMStringsCreator.class_TracedObjects)
		] as EClass
		tracedPackage = tracedObjectsClass.EPackage

		// Find the States package
		statesPackage = tracemm.eAllContents.filter(EPackage).findFirst [ p |
			p.name.equals(TraceMMStringsCreator.package_States)
		] as EPackage

		eventToGlobal = eventOccClass.EReferences.get(0)

		rootFactory = tracemm.EFactoryInstance
		eventFactory = eventsPackage.EFactoryInstance

		// tracedFactory = tracedPackage.EFactoryInstance
		this.stateFactory = statesPackage.EFactoryInstance

	}

	private var initDone = false

	def void init() {
		if (!initDone) {

			eventClassesCache = new HashSet
			eventClassesCache.addAll(
				eventsPackage.eAllContents.filter(EClass).filter [ c |
					c != eventOccClass && c != eventsClass
				].toSet)

			ref_traceSystemToTracedObjectsCache = this.traceClass.EReferences.findFirst [ r |
				r.name.equals(TraceMMStringsCreator.ref_SystemToTracedObjects)
			]

			ref_traceSystemToEventsTraceCache = this.traceClass.EReferences.findFirst [ r |
				r.name.equals(TraceMMStringsCreator.ref_SystemToEvents)
			]

			refs_stateRefsFromGSCache = globalStateClass.getEAllReferences.filter [ r |
				!r.name.equals(TraceMMStringsCreator.ref_GlobalToEvent)
			].toSet

			initDone = true
		}

	}

	private Set<EClass> eventClassesCache = null

	public def Set<EClass> eventClasses() {
		init()
		return eventClassesCache
	}

	private val Map<EClass, EReference> eventTraceRefOfCache = new HashMap

	public def EReference eventTraceRefOf(EClass eventClass) {

		if (!eventTraceRefOfCache.containsKey(eventClass)) {
			eventTraceRefOfCache.put(eventClass,
				eventsClass.EReferences.findFirst[r|
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
		init()
		return ref_traceSystemToTracedObjectsCache
	}

	private EReference ref_traceSystemToEventsTraceCache

	def EReference ref_traceSystemToEventsTrace() {
		init()
		return ref_traceSystemToEventsTraceCache
	}

	private EReference ref_traceSystemToPoolsCache

	def EReference ref_traceSystemToPools() {
		init()
		return ref_traceSystemToPoolsCache
	}

	// References to state classes from the global state class
	private var Set<EReference> refs_stateRefsFromGSCache

	def Set<EReference> refs_stateRefsFromGS() {
		init()
		return refs_stateRefsFromGSCache
	}

	private val Map<EClass, Set<EReference>> refs_originalObjectCache = new HashMap

	def Set<EReference> refs_originalObject(EClass traceClass) {

		if (!refs_originalObjectCache.containsKey(traceClass)) {
			refs_originalObjectCache.put(traceClass,
				traceClass.EAllReferences.filter[r|r.name.startsWith(TraceMMStringsCreator.ref_OriginalObject)].toSet)
		}

		return refs_originalObjectCache.get(traceClass)
	}

}
