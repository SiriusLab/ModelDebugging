package fr.inria.diverse.tracemm.common;

import fUML.Semantics.Classes.Kernel.Object_
import fr.inria.diverse.tracemm.generator.TraceMMExplorer
import fr.inria.diverse.tracemm.generator.TraceMMStringsCreator
import fr.inria.diverse.tracemm.xmof2tracematerial.ExtractorStringsCreator
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.Iterator
import java.util.Map
import java.util.Set
import java.util.Stack
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import org.eclipse.emf.ecore.resource.Resource
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent
import org.modelexecution.fumldebug.core.event.ActivityEvent
import org.modelexecution.fumldebug.core.event.ActivityExitEvent
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.configuration.ConfigurationObjectMap
import org.eclipse.xtend.lib.annotations.Accessors
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterValue
import java.util.List

public class GenericStatesBuilderConfigurationDynamicEObj {

	private ConfigurationObjectMap configurationObjectMap;

	private EPackage tracePackage;
	private EFactory traceFactory;

	private EObject trace;
	private EObject tracedObjects
	private EObject eventsTraces
	private EObject pools

	private EPackage originalPackage;
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) private EFactory originalFactory;

	private EPackage confPackage

	private ConfigurableStatesBuilder statesBuilder;
	private var boolean initDone = false;

	// References to state classes from the global state class
	private val Set<EReference> stateRefsFromGS

	private val Map<EClass, Set<EStructuralFeature>> tracedConfClassToTracedFeatures

	// Link from some conf object to its traced version (or normal version, but stored in the trace)
	// Yet not sure, maybe this leads to issues when doing "copyReferences", which may screw some objects... ?
	private ConfToTracedConverter confToTraceObj

	// Call stack of called operations, to link exit events to entry events
	private Stack<EObject> callStack

	private val TraceMMExplorer traceMMExplorer

	public def void init(ConfigurableStatesBuilder s) {
		if (!initDone) {
			this.statesBuilder = s;
			initDone = true;
		}
	}

	new(Resource traceMMResource, Resource originalMMResource, Resource confMMResource,
		ConfigurationObjectMap configurationObjectMap) {
		this(traceMMResource, originalMMResource.getContents.get(0) as EPackage, confMMResource, configurationObjectMap)
	}

	new(Resource traceMMResource, String originalMMnsURI, Resource confMMResource,
		ConfigurationObjectMap configurationObjectMap) {
		this(traceMMResource, EPackageRegistryImpl.INSTANCE.getEPackage(originalMMnsURI), confMMResource,
			configurationObjectMap)
	}

	new(Resource traceMMResource, EPackage originalMM, Resource confMMResource,
		ConfigurationObjectMap configurationObjectMap) {
		this.originalPackage = originalMM;
		this.originalFactory = originalPackage.getEFactoryInstance
		this.confPackage = confMMResource.getContents.get(0) as EPackage

		this.tracePackage = traceMMResource.getContents.get(0) as EPackage

		this.traceFactory = tracePackage.getEFactoryInstance
		this.configurationObjectMap = configurationObjectMap

		// Initializing collections
		stateRefsFromGS = new HashSet<EReference>

		callStack = new Stack()
		tracedConfClassToTracedFeatures = new HashMap<EClass, Set<EStructuralFeature>>

		this.traceMMExplorer = new TraceMMExplorer(traceMMResource.contents.get(0) as EPackage)
		confToTraceObj = new ConfToTracedConverter(traceMMExplorer, this)

		// We find the conf classes that are traced (ie the ones with features), and their traced features
		for (eclass : this.confPackage.eAllContents.toSet.filter(EClass).filter[c|!(c instanceof Activity)]) {
			val featuresToTrace = eclass.getEAllStructuralFeatures.filter[f|isConfClass(f.eContainer as EClass)].toSet
			val origClass = confClassToOrigClass(eclass)
			var String tracedName = null
			if (origClass != null)
				tracedName = TraceMMStringsCreator.class_createTraceClassName(origClass)
			else
				tracedName = TraceMMStringsCreator.class_createTraceClassName(eclass)
			val foundTracedName = tracedName
			val isInTraceMm = tracePackage.eAllContents.filter(EClass).exists[c|c.name.equals(foundTracedName)]
			if (isInTraceMm && featuresToTrace.size > 0)
				tracedConfClassToTracedFeatures.put(eclass, featuresToTrace)
		}
	}

	/**
	 * Creates an empty trace
	 */
	public def createEmptyTrace() {
		this.trace = traceFactory.create(traceMMExplorer.getTraceClass)
		this.tracedObjects = traceMMExplorer.createTracedObject(traceMMExplorer.tracedObjectsClass)
		this.eventsTraces = traceMMExplorer.createEventOccurrence(traceMMExplorer.getEventsClass)
		this.pools = traceFactory.create(traceMMExplorer.staticObjectsPoolsClass)

		this.trace.eSet(traceMMExplorer.ref_traceSystemToTracedObjects, tracedObjects)
		this.trace.eSet(traceMMExplorer.ref_traceSystemToEventsTrace, eventsTraces)
		this.trace.eSet(traceMMExplorer.ref_traceSystemToPools, pools)

		createNewState();
	}

	/**
	 * Adding to EObjects the possibility to get a field per name
	 */
	private def Object get(EObject o, String featureName) {
		return o.eGet(o.eClass.getEStructuralFeature(featureName))
	}

	/**
	 * Adding to EObjects the possibility to set a field per name
	 */
	private def set(EObject o, String featureName, Object value) {
		o.eSet(o.eClass.getEStructuralFeature(featureName), value)
	}

	public def EObject confToOriginal(EObject confObject) {
		val res = configurationObjectMap.getOriginalObject(confObject)
		if (res == null || findRootPackage(res.eClass.getEPackage) != originalPackage)
			return null
		else
			return res
	}

	public def EClass confClassToOrigClass(EClass confClass) {
		if (findRootPackage(confClass.getEPackage) == this.originalPackage)
			return confClass
		else
			return confClass.getESuperTypes.findFirst[t|findRootPackage(t.getEPackage) == originalPackage]
	}

	private def EObject fumlToConf(Object_ fumlObject) {
		return this.statesBuilder.getVM.instanceMap.getEObject(fumlObject)
	}

	private def EPackage findRootPackage(EPackage p) {
		if (p.getESuperPackage == null)
			return p
		else
			return findRootPackage(p.getESuperPackage)
	}

	private def EObject getLastState() {
		val int stateNumber = (trace.get("globalTrace") as Collection<EObject>).size();
		val lastState = (trace.get("globalTrace") as Collection<EObject>).get(stateNumber - 1);
		return lastState;
	}

	def EObject getTrace() {
		return trace
	}

	def createNewState() {
		val EObject gs = traceFactory.create(traceMMExplorer.globalStateClass);
		(trace.get("globalTrace") as Collection<EObject>).add(gs)
		createAllStates(gs);
	}

	private def boolean isConfClass(EClass c) {
		confPackage.eAllContents.filter(EClass).toSet.contains(c)
	}

	private def Object valueSnapshotToObject(ValueSnapshot contextValueSnapshot) {
		val res = contextValueSnapshot.runtimeValue
		return res
	}

	private def void addEvent(ActivityEvent event, boolean entry) {

		// First we find the "this" (or "caller") object, from the trace perspective
		val activityExecution = this.statesBuilder.getActivityExecutionOf(event);

		if (activityExecution.contextValueSnapshot == null)
			return

		val fumlCaller = valueSnapshotToObject(activityExecution.contextValueSnapshot)
		val confCaller = fumlToConf(fumlCaller as Object_)
		var EObject traceCaller = null

		// Case traced class, thus need the corresponding traced object
		if (confToTraceObj.containsKey(confCaller)) {
			traceCaller = confToTraceObj.get(confCaller)
		}
		// Otherwise, we must point to the (static) object directly
		else {
			val origCaller = confToOriginal(confCaller)
			traceCaller = origCaller
		}

		// Then we find the matching event occ class in the trace mm
		val String activityName = event.getActivity().name
		val eventClasses = traceMMExplorer.eventClasses()
		var String eventClassName = null

		// The class containing the activity may be original, or new
		val origClass = confClassToOrigClass(confCaller.eClass)
		var EClass behavioredClass = null
		if (origClass == null)
			behavioredClass = confCaller.eClass
		else
			behavioredClass = origClass

		if (entry) {
			eventClassName = ExtractorStringsCreator.class_createEntryEventClassName(behavioredClass, activityName)
		} else {
			eventClassName = ExtractorStringsCreator.class_createExitEventClassName(behavioredClass, activityName)
		}

		val valEventClassName = eventClassName
		var EClass eventClass = eventClasses.findFirst[c|c.name.equals(valEventClassName)]

		// If we must take this event into account (ie we do have an event occurrence class)
		if (eventClass != null) {

			if (entry) {

				// Create event occurrence, with param and global step
				val traceEvent = traceMMExplorer.createEventOccurrence(eventClass);
				traceEvent.set(ExtractorStringsCreator.ref_EventToThis, traceCaller);

				// Finally we configure remaining links 
				traceEvent.eSet(traceMMExplorer.eventToGlobal, getLastState());
				val ref = traceMMExplorer.eventTraceRefOf(eventClass)
				val eventsTrace = (eventsTraces.eGet(ref) as Collection<EObject>)
				eventsTrace.add(traceEvent);

				// And we put the event in the callstack to be found again when handling some exit event
				callStack.push(traceEvent)

			} else {

				// Create event occurrence, with param and global step
				val exitEvent = traceMMExplorer.createEventOccurrence(eventClass);
				val EObject entryEventOcc = callStack.pop
				exitEvent.set(ExtractorStringsCreator.ref_ExitToEntry, entryEventOcc)
				exitEvent.set(TraceMMStringsCreator.ref_EventToGlobal, getLastState());
				(eventsTraces.eGet(traceMMExplorer.eventTraceRefOf(eventClass)) as Collection<EObject>).add(exitEvent);

				// If the entry event had input parameters, we handle it here
				// (because when we receive the entry event the inputs are not here yet)
				for (input : activityExecution.activityInputs) {
					val String refName = ExtractorStringsCreator.ref_createEntryToParam(input.parameter.name)
					fillEventOccurrenceFeature(input.parameterValues, entryEventOcc, refName)
				}

				// Then same thing for outputs
				for (output : activityExecution.activityOutputs) {
					val String refName = ExtractorStringsCreator.ref_createExitToReturn(output.parameter.name)
					fillEventOccurrenceFeature(output.parameterValues, exitEvent, refName)
				}
			}
		}
	}

	def fillEventOccurrenceFeature(List<? extends ParameterValue> values, EObject eventOcc, String featureName) {

		val EStructuralFeature eventOccParamFeature = eventOcc.eClass.EStructuralFeatures.findFirst[r|
			r.name.equals(featureName)]
		for (value : values) {

			if (value.valueSnapshot != null) {

				// TODO here we should also sometimes get primitive values, but not sure yet of what it would look like
				// how does fUML store them? within Object_s or within some other structure?
				// in any case we _will_ have an exception here when it will happen :)
				val fumlParamValue = valueSnapshotToObject(value.valueSnapshot)
				val confParamValue = fumlToConf(fumlParamValue as Object_)

				if (!eventOccParamFeature.many) {
					val EObject referencedObject = obtainTraceObjectFromConfObject(confParamValue)
					eventOcc.eSet(eventOccParamFeature, referencedObject)
				} else {

					if (confParamValue instanceof Collection<?>) {
						val Collection<EObject> confParamValues = confParamValue as Collection<EObject>
						for (confParamValueInColl : confParamValues) {
							val EObject referencedObject = obtainTraceObjectFromConfObject(confParamValueInColl)
							(eventOcc.eGet(eventOccParamFeature) as Collection<EObject>).add(referencedObject)
						}
					} else {
						val EObject referencedObject = obtainTraceObjectFromConfObject(confParamValue)
						(eventOcc.eGet(eventOccParamFeature) as Collection<EObject>).add(referencedObject)
					}

				}

			} else {

				// TODO should that happen?
				println("###############")
				println("WARNING: null value snapshot!")
				println("###############")
			}
		}
	}

	def addEntryEvent(ActivityEntryEvent event) {
		addEvent(event, true)
	}

	def addExitEvent(ActivityExitEvent event) {
		addEvent(event, false)
	}

	def updateState() {

		// We go through all the out refs of GlobalState, except the one to the events
		for (stateRef : stateRefsFromGS) {

			// We go through all states linked to this global step
			val someStateCollection = lastState.eGet(stateRef) as Collection<EObject>
			for (someState : someStateCollection) {

				// We will need to navigate through the "parent" link and its opposite as well
				val refParent = someState.eClass.getEAllReferences.findFirst[r|r.name.equals("parent")]
				val oppositeToParent = refParent.getEOpposite

				// If the state was created for this global state, we destroy it
				if ((someState.get("globalStates") as Collection<EObject>).size < 2)
					((someState.eGet(refParent) as EObject).eGet(oppositeToParent) as Collection<EObject>).remove(
						someState);
			}

			// And we remove the global state references, after browsing them
			someStateCollection.clear();
		}

		// After undo-ing a step, we recreate all states
		createAllStates(lastState);
	}

	def EObject obtainTraceObjectFromConfObject(EObject confObject) {

		if (confObject instanceof EObject) {

			val origObject = confToOriginal(confObject)

			// Case the corresponding object SHOULD be in the trace eventually
			// Maybe we know it already
			if (confToTraceObj.containsKey(confObject)) {
				return confToTraceObj.get(confObject)
			}

			// Case we refer to some object in the original model
			else if (origObject != null) {
				return origObject
			}
			// Last case: we have a transient static object (that we don't know yet, because first case). We must create it and store it in the correct pool. 
			else {
				val transientObject = confToTraceObj.convertWithIncSolving(confObject)
				val String refName = TraceMMStringsCreator.ref_createStaticObjectsToStatic(transientObject.eClass)
				(pools.get(refName) as Collection<EObject>).add(transientObject);
				return transientObject
			}
		}

	}

	def createAllStates(EObject gs) {

		// First we should go once through all objects, and convert them to traced versions
		val EList<EObject> contents = statesBuilder.getModelResource().getContents();
		for (EObject confObject : contents) {

			val EClass confEClass = confObject.eClass

			// If the class is traced (ie has features)
			if (tracedConfClassToTracedFeatures.containsKey(confEClass)) {

				// We search for a traced version
				var EObject tracedVariable = null
				if (confToTraceObj.containsKey(confObject)) {
					tracedVariable = confToTraceObj.get(confObject)
				}
				// If we don't have a traced version, we create it
				else {
					tracedVariable = confToTraceObj.convertWithLaterSolving(confObject)

					// If there is a matching value in the original model, we refer to it
					val originalVariable = confToOriginal(confObject)
					if (originalVariable != null) {

						// There might be multiple references to set, in case of multiple inheritance
						for (r : traceMMExplorer.refs_originalObject(tracedVariable.eClass))
							tracedVariable.eSet(r, originalVariable);
					}

					// Finally, we add the traced object to the corresponding collection
					val String refName = TraceMMStringsCreator.ref_createTracedObjectsToTrace(tracedVariable.eClass)
					(tracedObjects.get(refName) as Collection<EObject>).add(tracedVariable);
				}
			}
		}

		// Then a single "copyreferences"
		confToTraceObj.copyReferences

		// And finally we look for value changes
		// We go through the content of the complete (synchronized) conf model
		for (EObject confObject : contents) {

			val EClass confEClass = confObject.eClass

			// If the object has a traced version (created in the first step of the method)
			if (confToTraceObj.containsKey(confObject)) {

				val tracedVariable = confToTraceObj.get(confObject)

				// We go through all the features of the class (if any to trace)
				if (tracedConfClassToTracedFeatures.containsKey(confEClass)) {
					for (tracedConfFeature : tracedConfClassToTracedFeatures.get(confEClass)) {

						// We get the current value of this feature (may be null)
						var Object confObjectCurrentValue = confObject.eGet(tracedConfFeature);

						// We also get the trace of this feature
						val featureTraceRefName = tracedConfFeature.name + "Trace"
						val refToStateClass = tracedVariable.eClass.getEAllReferences.findFirst[r|
							r.name.equals(featureTraceRefName)]

						val EList<EObject> featureTrace = (tracedVariable.eGet(refToStateClass) as EList <EObject>);

						// We create a new state for the trace, that we will use or not
						// We can change the stored value in it using
						val potentialNewState = traceMMExplorer.createState(refToStateClass.getEType as EClass)

						// And we will try to now if the value of the last state changed or not
						var boolean changed;

						// If there are any states in the trace, we get the last registered value for this feature
						var EObject previousState = null
						var Object previousValue = null

						if (featureTrace.size() > 0) {
							previousState = featureTrace.get(featureTrace.size() - 1);

							// There should be a single feature in the state class, excluding the "globalStates" one
							// But the value can be anything: a collection, a java primitive (Integer, etc.), an EObject
							previousValue = previousState.eGet(
								previousState.eClass.getEStructuralFeatures.findFirst[f|!f.name.equals("globalStates")]);
						}

						// Case both null: no change
						if (previousValue == null && confObjectCurrentValue == null) {
							changed = false
						} 
						
						// Case previous not null and new null -> set to null
						else if (previousValue != null && confObjectCurrentValue == null) {
							changed = true
							potentialNewState.set(tracedConfFeature.name, null) // I think it cannot be a collection here, since null
						} 
					
						// Case previous null or not, and new value not null -> set to current
						else {

							// If the feature is a single value
							if (!tracedConfFeature.many) {

								// If the single value is an EObject (thus probably element of the model)
								// There we must make sure to compare references consistently
								// current is a conf object previous is either an original or a traced object
								if (confObjectCurrentValue instanceof EObject) {
									val traceObject = obtainTraceObjectFromConfObject(confObjectCurrentValue)
									if (traceObject != previousValue) {
										changed = true
										potentialNewState.set(tracedConfFeature.name, traceObject)
									}
								}
							
								// Else, probably some Java primitive (e.g. Integer)
								else {
									changed = (previousValue != confObjectCurrentValue)
									if (changed)
										potentialNewState.set(tracedConfFeature.name, confObjectCurrentValue)
								}

							}
							// If the feature is a collection of values
							else {

								val confObjectCurrentValues = confObjectCurrentValue as Collection<Object>
								val previousValues = previousValue as Collection<Object>
								val Collection<Object> newStateValues = potentialNewState.get(tracedConfFeature.name) as Collection<Object>
								var Iterator<Object> previousIterator
								if (previousValues != null && previousValues.empty && confObjectCurrentValues.empty) {
									changed = false
								} else if (previousValues != null && !previousValues.empty &&
									confObjectCurrentValues.empty) {
									changed = true
								} else {
									var boolean sameSize
									if (previousValues != null) {
										sameSize = confObjectCurrentValues.size == previousValues.size
										previousIterator = previousValues.iterator
									} else
										sameSize = false
									changed = !sameSize

									for (confValueInColl : confObjectCurrentValues) {
										var Object previousNext = null
										if (previousIterator != null && previousIterator.hasNext)
											previousNext = previousIterator.next

										// First we want to obtain the trace version of the value
										var Object traceObjectInColl = confValueInColl
										if (confValueInColl instanceof EObject) {
											traceObjectInColl = obtainTraceObjectFromConfObject(confValueInColl)
										}
										newStateValues.add(traceObjectInColl)

										if (sameSize) {

											// Then if ordered, we compare it with the other value
											if (tracedConfFeature.ordered) {
												val previousValueInColl = previousNext
												changed = (previousValueInColl != traceObjectInColl)
											}
									
											// Otherwise, we look for it in the other collection
											else {
												changed = (previousValues.contains(traceObjectInColl))
											}
										}
									}
								}
							}
						}

						// If change or if this is the first state of the trace
						if (changed || previousState == null) {

							// If changed, we add a step to its trace
							(potentialNewState.get("globalStates") as Collection<EObject>).add(gs);
							featureTrace.add(potentialNewState)

						} else {

							// If no change, we simply add the step to the current gs
							(previousState.get("globalStates") as Collection<EObject>).add(gs);
						}
					}
				}
			}
		}
	}
}
