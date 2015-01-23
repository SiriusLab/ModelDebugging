package fr.inria.diverse.tracemm.common

import fUML.Semantics.Classes.Kernel.Object_
import java.util.Collection
import java.util.HashMap
import java.util.Map
import java.util.Stack
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent
import org.modelexecution.fumldebug.core.event.ActivityExitEvent
import org.modelexecution.fumldebug.core.trace.tracemodel.ValueSnapshot
import org.modelexecution.xmof.configuration.ConfigurationObjectMap
import java.util.HashSet
import org.eclipse.emf.ecore.EReference
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.EStructuralFeature
import fr.inria.diverse.tracemm.generator.TraceMMExplorer
import org.eclipse.emf.ecore.EPackage
import fr.inria.diverse.tracemm.xmof2tracematerial.ExtractorStringsCreator
import fr.inria.diverse.tracemm.generator.TraceMMStringsCreator

class GenericStatesBuilderConfigurationDynamicEObj extends SpecificStatesBuilderConfiguration {

	// References to state classes from the global state class
	private val Set<EReference> stateRefsFromGS

	private val Map<EClass, Set<EStructuralFeature>> tracedConfClassToTracedFeatures

	// Link from some conf object to its traced version (or normal version, but stored in the trace)
	// Yet not sure, maybe this leads to issues when doing "copyReferences", which may screw some objects... ?
	private EcoreUtil.Copier confToTraceObj

	// Call stack of called operations, to link exit events to entry events
	private Stack<EObject> callStack

	protected val TraceMMExplorer traceMMExplorer

	def Object_ valueSnapshotToObject(ValueSnapshot contextValueSnapshot) {
		return contextValueSnapshot.runtimeValue as Object_
	}

	def boolean shouldHaveATracedVersion(EObject o) {
		tracedConfClassToTracedFeatures.containsKey(o.eClass)
	}

	new(Resource traceMMResource, Resource originalMMResource, Resource confMMResource,
		ConfigurationObjectMap configurationObjectMap) {

		super(traceMMResource, originalMMResource, confMMResource, configurationObjectMap);

		// Initializing collections
		//eventNames = new HashSet<String>
		stateRefsFromGS = new HashSet<EReference>
		confToTraceObj = new EcoreUtil.Copier
		callStack = new Stack()
		tracedConfClassToTracedFeatures = new HashMap<EClass, Set<EStructuralFeature>>

		this.traceMMExplorer = new TraceMMExplorer(traceMMResource.contents.get(0) as EPackage)

		// We parse the metamodel and find the interesting event names
		//eventNames.addAll(
		//	traceMMExplorer.findEventClasses.filter[c|c.name.endsWith("EntryEventOccurrence")].map[c|
		//		c.name.replace("EntryEventOccurrence", "")])
		// And also the references to state classes from the global state class
		stateRefsFromGS.addAll(
			globalStateClass.getEAllReferences.filter[r|!r.name.equals("eventsTriggeredDuringGlobalState")])

		// And we find the conf classes that are traced (ie the ones with features)
		// FAIL: we must also consider the features that are in the super classes, but not the orig super class
		for (eclass : this.confPackage.eAllContents.toSet.filter(EClass)) {
			val featuresToTrace = eclass.getEAllStructuralFeatures.filter[f|!isOrigClass(f.eContainer as EClass)].toSet
			val tracedName = TraceMMStringsCreator.class_createTraceClassName(eclass)
			val isInTraceMm = tracePackage.eAllContents.filter(EClass).exists[c|c.name.equals(tracedName)]
			if (isInTraceMm && featuresToTrace.size > 0)
				tracedConfClassToTracedFeatures.put(eclass, featuresToTrace)
		}

	}

	override addEntryEvent(ActivityEntryEvent event) {

		// First we find the "this" (or "caller") object, from the trace perspective
		val activityExecution = this.statesBuilder.getActivityExecutionOf(event);

		if (activityExecution.context == null)
			return

		val fumlCaller = valueSnapshotToObject(activityExecution.context)
		val confCaller = fumlToConf(fumlCaller)
		var EObject traceCaller = null

		// Case traced class, thus need a "TracedX" proxy
		if (confToTraceObj.containsKey(confCaller)) {
			traceCaller = confToTraceObj.get(confCaller)
		}
			// Otherwise, we must point to the (static) object directly
		else {
			val origCaller = confToOriginal(confCaller)
			traceCaller = origCaller
		}

		// Then we find the matching event occ class in the trace mm
		val callerEClass = traceCaller.eClass
		val String activityName = event.getActivity().name
		val eventClasses = traceMMExplorer.eventClasses()
		val eventClassName = ExtractorStringsCreator.class_createEntryEventClassName(callerEClass, activityName)
		val EClass eventClass = eventClasses.findFirst[c|c.name.equals(eventClassName)]

		// If we must take this event into account (ie we do have an event occurrence class)
		if (eventClass != null) {

			// Create event occurrence, with param and global step
			val traceEvent = traceMMExplorer.createEventOccurrence(eventClass);
			traceEvent.set(ExtractorStringsCreator.ref_EventToThis, traceCaller);

			// Finally we configure remaining links 
			traceEvent.eSet(traceMMExplorer.eventToGlobal, getLastState());
			(traceMMExplorer.eventsTracesClass.eGet(traceMMExplorer.eventTraceRefOf(eventClass)) as Collection<EObject>).add(traceEvent);

			// And we put the event in the callstack to be found again when handling some exit event
			callStack.push(traceEvent)
		}

	}

	override addExitEvent(ActivityExitEvent event) {

		// First we find the "this" (or "caller") object, from the trace perspective
		val activityExecution = this.statesBuilder.getActivityExecutionOf(event);

		if (activityExecution.context == null)
			return

		val activityName = event.getActivity().name

		//val EClass eventClass = traceMMExplorer.findEventClasses.findFirst[c|c.name.equals(ExtractorStringsCreator.class_createExitEventClassName(activity.))]
		val eventName = ""

		// If we must take this event into account (ie we do have an event occurrence class)
		if (eventName != null) {

			// Create event occurrence, with param and global step
			val traceEvent = newTraceObject(eventName + "ExitEventOccurrence");
			val EObject entryEventOcc = callStack.pop
			traceEvent.set("correspondingEntryEvent", entryEventOcc)
			traceEvent.set("stateDuringWhichTriggered", getLastState());
			(stateSystem.get(eventName + "ExitEventTrace") as Collection<EObject>).add(traceEvent);

			// If the entry event had parameters, we handle it here
			// (because when we receive the entry event the inputs are not here yet)
			//val activityExecution = this.statesBuilder.getActivityExecutionOf(event);
			for (input : activityExecution.activityInputs) {
				val paramName = input.parameter.name
				for (value : input.parameterValues) {
					val fumlParamValue = valueSnapshotToObject(value.valueSnapshot)
					val confParamValue = fumlToConf(fumlParamValue)
					val origParamValue = confToOriginal(confParamValue)

					// Either we have a traced version of the value
					if (confToTraceObj.containsKey(confParamValue)) {
						entryEventOcc.set("param_" + paramName, confToTraceObj.get(confParamValue))
					}
					// Otherwise, it means the object is in the model
					else if (origParamValue != null) {
						entryEventOcc.set("param_" + paramName, origParamValue)
					}
					// Last case: we have a transient object, and we must create a copy of it
					else {
						val transientObject = convertConfToOriginalAttOnly(confParamValue)
						(stateSystem.get("pool" + transientObject.eClass.name + "s") as Collection<EObject>).add(
							transientObject);
						entryEventOcc.set("param_" + paramName, transientObject)
						confToTraceObj.put(confParamValue, transientObject)
						// confToTraceObj.copyReferences TODO fail because of collections
					}
				}
			}
		}
	}

	override updateState() {

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
			// Else we don't, in which case we must wait for its traced version
			// by refering to its conf version for now (resolved later by the copier... hopefully...)
			else if (shouldHaveATracedVersion(confObject)) {
				return confObject
			}

			// Case we refer to some object in the original model
			else if (origObject != null) {
				return origObject
			}
			// Last case: we have a transient static object (that we don't know yet, because first case). We must create it and store it in the correct pool. 
			else {
				val transientObject = convertConfToOriginalAttOnly(confObject)
				confToTraceObj.put(confObject, transientObject)
				// confToTraceObj.copyReferences() TODO FAIL because of collections
				(stateSystem.get("pool" + transientObject.eClass.name + "s") as Collection<EObject>).add(transientObject);
				return transientObject
			}
		}

	}

	override createAllStates(EObject gs) {

		// We go through the content of the complete (synchronized) conf model
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
					tracedVariable = traceMMExplorer.createTracedObject(confEClass)
					confToTraceObj.put(confObject, tracedVariable)
					copyAttributes(confObject, tracedVariable) // TODO problem here, not copied
					// confToTraceObj.copyReferences // TODO FAIL WITH COLLECTIONS: DUPLICATE REFS

					// If there is a matching value in the original model, we refer to it
					val originalVariable = confToOriginal(confObject)
					if (originalVariable != null) {
						tracedVariable.set("originalObject", originalVariable);
					}

					// Finally, we add the traced object to the corresponding collection
					(stateSystem.get("traced" + origClass.name + "s") as Collection<EObject>).add(tracedVariable);
				}

				// We go through all the features of the class 
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
					val potentialNewState = traceFactory.create(refToStateClass.getEType as EClass)

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
							previousState.eClass.getEStructuralFeatures.findFirst[f|!f.name.equals("globalStates")]) as EObject;
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
						// If the feature is a collection of values (NOT TESTED)
						else {

							val confObjectCurrentValues = confObjectCurrentValue as Collection<Object>
							val previousValues = previousValue as Collection<Object>
							val Collection<Object> newStateValues = potentialNewState.get(tracedConfFeature.name) as Collection<Object>

							if (previousValues.empty && confObjectCurrentValues.empty) {
								changed = false
							} else if (!previousValues.empty && confObjectCurrentValues.empty) {
								changed = true
							} else {
								val sameSize = confObjectCurrentValues.size == previousValues.size
								changed = !sameSize
								var i = 0;
								for (confValueInColl : confObjectCurrentValues) {

									// First we want to obtain the trace version of the value
									var Object traceObjectInColl = confValueInColl
									if (confValueInColl instanceof EObject) {
										traceObjectInColl = obtainTraceObjectFromConfObject(confValueInColl)
									}
									newStateValues.add(traceObjectInColl)

									if (sameSize) {

										// Then if ordered, we compare it with the other value
										if (tracedConfFeature.ordered) {
											val previousValueInColl = previousValues.get(i)
											changed = (previousValueInColl != traceObjectInColl)
										}
									
										// Otherwise, we look for it in the other collection
										else {
											changed = (previousValues.contains(traceObjectInColl))
										}
									}

									// End of the loop																	
									i++
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
