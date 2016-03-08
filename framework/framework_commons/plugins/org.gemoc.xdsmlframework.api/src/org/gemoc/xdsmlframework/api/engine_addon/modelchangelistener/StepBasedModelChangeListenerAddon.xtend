package org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.DefaultEngineAddon;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.emf.ecore.EReference
import java.util.Collection

public class StepBasedModelChangeListenerAddon extends DefaultEngineAddon {

	private EContentAdapter adapter;
	private IBasicExecutionEngine engine;
	private Map<Object, List<ModelChange>> changes = new HashMap
	private Set<Object> registeredObservers = new HashSet

	private Map<EObject, Map<EStructuralFeature, List<Notification>>> gatheredNotifications = new HashMap

	public new(IBasicExecutionEngine engine) {
		this.engine = engine;
		engine.executionContext.executionPlatform.addEngineAddon(this)
		/*
		 * We create an adapter that stores and sort all the notifications for each object and field.
		 * This avoids us to sort everything afterwards.
		 */
		this.adapter = new EContentAdapter() {
			override void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				val int eventType = notification.getEventType();
				if (eventType < Notification.EVENT_TYPE_COUNT && notification.getNotifier() instanceof EObject &&
					!notification.isTouch() && notification.getFeature() instanceof EStructuralFeature) {
					val EStructuralFeature feature = notification.getFeature() as EStructuralFeature;
					val EObject changedObject = notification.getNotifier() as EObject;
					if (!gatheredNotifications.containsKey(changedObject)) {
						gatheredNotifications.put(changedObject, new HashMap)
					}
					val Map<EStructuralFeature, List<Notification>> objectsNotifications = gatheredNotifications.get(
						changedObject);
					if (!objectsNotifications.containsKey(feature)) {
						objectsNotifications.put(feature, new ArrayList)
					}
					val List<Notification> fieldsNotifications = objectsNotifications.get(feature);
					fieldsNotifications.add(notification);
				}
			}
		};
		val Set<Resource> allResources = org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(
			this.engine.getExecutionContext().getResourceModel());
		allResources.forEach [ r |
			if (r != null) {
				r.eAdapters().add(adapter);
			}
		]

	}

	def List<ModelChange> getChanges(Object addon) {
		val List<ModelChange> result = changes.get(addon);
		if (registeredObservers.contains(addon)) {
			changes.put(addon, new ArrayList<ModelChange>());
		}
		return result;
	}

	def boolean registerObserver(Object observer) {
		val boolean res = registeredObservers.add(observer);
		if (res) {
			changes.put(observer, new ArrayList<ModelChange>());
		}
		return res;
	}

	/*
	 * At the begining of an MSE, we clear the notification cache
	 */
	override void aboutToExecuteMSEOccurrence(IBasicExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
		gatheredNotifications = new HashMap
	}

	/*
	 * At the end of an MSE, we process all the notifications, and find our what were the changes
	 * at the granularity of a step.
	 * 
	 * This is a lot of lines of code, but should be quite efficient since few notifications should have to be processed.
	 */
	override void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {

		val newObjects = new HashSet<EObject>
		val removedObjects = new HashSet<EObject>

		for (object : gatheredNotifications.keySet) {
			val featureMap = gatheredNotifications.get(object)
			for (feature : featureMap.keySet) {
				val notifs = featureMap.get(feature)

				// Case multiplicity 0..1: we compare the original value and the new one at the end of the step
				if (!feature.isMany) {

					val previousValue = notifs.head.oldValue
					val newValue = notifs.last.newValue

					// Case objects: we compare references
					if (feature instanceof EReference) {
						if (previousValue != newValue) {

							// Register model change
							addModelChange(new ChangeElementFieldModelChange(object, feature, newValue))

							// Register potentially new or removed object
							if ((feature as EReference).containment) {
								if (previousValue != null && previousValue instanceof EObject)
									addToRemovedObjects(removedObjects, newObjects, previousValue as EObject)
								if (newValue != null && newValue instanceof EObject)
									addToNewObjects(removedObjects, newObjects, newValue as EObject)
							}
						}
					} // Case data types: we compare values
					else if (!previousValue.equals(newValue)) {

						// Register model change
						addModelChange(new ChangeElementFieldModelChange(object, feature, newValue))
					}

				} // Case multiplicity 0..*: we consider that there was a potential change, but maybe following
				// all the adds ands remove, the collection went back to its state before the step
				else {

					// Very hard to decide if a collection has changed or not based on the notifications,
					// so for now we simply state a "potential change"
					addModelChange(new PotentialCollectionFieldModelChange(object, feature, notifs))

					// Yet we must still find new/removed objects
					for (notif : notifs) {

						if (feature instanceof EReference && (feature as EReference).containment) {
							switch (notif.eventType) {
								case Notification.ADD:
									addToNewObjects(removedObjects, newObjects, notif.newValue as EObject)
								case Notification.ADD_MANY:
									for (add : notif.newValue as List<EObject>)
										addToNewObjects(removedObjects, newObjects, add)
								case Notification.REMOVE:
									addToRemovedObjects(removedObjects, newObjects, notif.oldValue as EObject)
								case Notification.REMOVE_MANY:
									for (remove : notif.oldValue as List<EObject>)
										addToNewObjects(removedObjects, newObjects, remove)
							}
						}
					}
				}
			}
		}

		// Finally we register the new and removed objects from the model
		for (newObject : newObjects) {
			addModelChange(new NewObjectModelChange(newObject))
		}
		for (removedObject : removedObjects) {
			addModelChange(new RemovedObjectModelChange(removedObject))
		}

	}

	private def void addToNewObjects(Collection<EObject> removedObjects, Collection<EObject> newObjects,
		EObject object) {
		if (object != null) {
			val hasMoved = removedObjects.remove(object)
			if (!hasMoved)
				newObjects.add(object)
		}
	}

	private def void addToRemovedObjects(Collection<EObject> removedObjects, Collection<EObject> newObjects,
		EObject object) {
		if (object != null) {
			val hasMoved = newObjects.remove(object)
			if (!hasMoved)
				removedObjects.add(object)

		}
	}

	override void engineAboutToStop(IBasicExecutionEngine engine) {
		val Set<Resource> allResources = org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(
			this.engine.getExecutionContext().getResourceModel());
		allResources.forEach [ r |
			r.eAdapters().remove(adapter);
		]
	}

	private def void addModelChange(ModelChange modelChange) {
		registeredObservers.forEach [ addon |
			changes.get(addon).add(modelChange);
		]

	}

}