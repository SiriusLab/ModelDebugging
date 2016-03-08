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

public class StepBasedModelChangeListenerAddon extends DefaultEngineAddon {

	private EContentAdapter adapter;
	private IBasicExecutionEngine engine;
	private Map<IEngineAddon, List<ModelChange>> changes;
	private Set<IEngineAddon> registeredAddons;

	private Map<EObject, Map<EStructuralFeature, List<Notification>>> gatheredNotifications = new HashMap

	public new(IBasicExecutionEngine engine) {
		this.engine = engine;
		changes = new HashMap
		registeredAddons = new HashSet

		/*
		 * We create an adapter that stores and sort all the notifications for each object and field.
		 * This avoids us to sort everything afterwards.
		 */
		adapter = new EContentAdapter() {
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

	def List<ModelChange> getChanges(IEngineAddon addon) {
		val List<ModelChange> result = changes.get(addon);
		if (registeredAddons.contains(addon)) {
			changes.put(addon, new ArrayList<ModelChange>());
		}
		return result;
	}

	def boolean registerAddon(IEngineAddon addon) {
		val boolean res = registeredAddons.add(addon);
		if (res) {
			changes.put(addon, new ArrayList<ModelChange>());
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

				// Case multiplicity 0..1
				if (!feature.isMany) {

					val previousValue = notifs.head.oldValue
					val newValue = notifs.last.newValue

					// Case objects: we compare references
					if (feature instanceof EReference && previousValue != newValue) {
						// Register model change
						addModelChange(new ChangeElementFieldModelChange(object, feature, newValue))
						// Register potentially new or removed object
						if ((feature as EReference).containment) {
							val EObject newValue_cast = newValue as EObject
							if (newValue != null) {
								val hasMoved = removedObjects.remove(newValue_cast)
								if (!hasMoved)
									newObjects.add(newValue_cast)
							} else {
								val hasMoved = newObjects.remove(newValue_cast)
								if (!hasMoved)
									removedObjects.add(newValue_cast)
							}
						}

					} // Case data types: we compare values
					else if (!previousValue.equals(newValue)) {
						// Register model change
						addModelChange(new ChangeElementFieldModelChange(object, feature, newValue))
					}

				} // Case multiplicity 0..*
				else {

					for (notif : notifs) {

						// Case ordered: we store all the model changes, to be sure to reflect the potential order changes in the collection
						if (feature.ordered) {
							
							switch (notif.eventType) {
								case Notification.ADD: println("a")
								case Notification.ADD_MANY: println("a")
								case Notification.REMOVE: println("a")
								case Notification.REMOVE_MANY: println("a")
							}
							if (notif.eventType == Notification.ADD) {
								
							}

						} // Case unordered: we focus on finding which elements were added/removed, 
						else {
						}

						// Case objects
						if (feature instanceof EReference) {
							// We must find if and how the collection really changed
						} // Case data types
						else {
						}
					}
				}
			}
		}

	// Now we have to check if there are new or removed objects
	}

	override void engineAboutToStop(IBasicExecutionEngine engine) {
		val Set<Resource> allResources = org.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(
			this.engine.getExecutionContext().getResourceModel());
		allResources.forEach [ r |
			r.eAdapters().remove(adapter);
		]
	}

	private def void addModelChange(ModelChange modelChange) {
		registeredAddons.forEach [ addon |
			changes.get(addon).add(modelChange);
		]

	}
}



















