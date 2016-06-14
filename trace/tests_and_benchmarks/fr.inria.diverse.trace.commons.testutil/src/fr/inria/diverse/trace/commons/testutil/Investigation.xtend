package fr.inria.diverse.trace.commons.testutil

import java.util.Collection
import java.util.HashSet
import java.util.Set
import java.util.function.Predicate
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource

class Investigation {

	static val Set<EClass> ignoredEClasses = #{EcorePackage.eINSTANCE.EFactory}

	private static def void findObjectsThatPointToNoExplore(Set<EObject> from, Predicate<EObject> predicateReferencedObject,
		Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate,
		Set<EObject> globalPointed, HashSet<EObject> checkedObjects) {

		for (potentialPointer : from.filter [ o |
			o != null && !ignoredEClasses.contains(o.eClass)
			!checkedObjects.contains(o)
		]) {
			checkedObjects.add(potentialPointer)
			val localPointed = new HashSet<EObject>

			if (potentialPointer.eContainer != null)
				localPointed.add(potentialPointer.eContainer)
			for (f : potentialPointer.eClass.EAllReferences) {
				val Object stuffRefed = potentialPointer.eGet(f)
				if (f.many) {
					localPointed.addAll(stuffRefed as Collection<EObject>)
				} else {
					localPointed.add(stuffRefed as EObject)
				}
			}

			localPointed.removeIf([o|o == null || ignoredEClasses.contains(o.eClass)])
			globalPointed.addAll(localPointed)

			val localPointedObjectsSatifPredicate = localPointed.filter[y|predicateReferencedObject.test(y)]
			if (!localPointedObjectsSatifPredicate.empty) {
				pointersToObjectsSatifPredicate.add(potentialPointer)
				pointedObjectsSatifPredicate.addAll(localPointedObjectsSatifPredicate)
			}
		}
	}

	private static def void findObjectsThatPointTo(Set<EObject> roots, Predicate<EObject> predicateReferencedObject,
		Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate,
		Set<EObject> globalPointed, HashSet<EObject> checkedObjects) {

		val toCheck = new HashSet<EObject>
		toCheck.addAll(roots)

		var int i = 0
		val int limit = 100000;

		while (!toCheck.empty && i < limit) {
			i++
			val pointedObjects = new HashSet<EObject>
			findObjectsThatPointToNoExplore(toCheck, predicateReferencedObject, pointersToObjectsSatifPredicate,
				pointedObjectsSatifPredicate, pointedObjects, checkedObjects)
			toCheck.clear
			toCheck.addAll(pointedObjects)
			toCheck.removeAll(checkedObjects)
		}

		if (i == limit) {
			throw new Exception("LOOPED TOO LONG")
		}

	}

	private static def void findObjectsThatPointTo(EObject root, Predicate<EObject> predicateReferencedObject,
		Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate,
		Set<EObject> globalPointed, HashSet<EObject> checkedObjects) {
		findObjectsThatPointTo(#{root}, predicateReferencedObject, pointersToObjectsSatifPredicate,
			pointedObjectsSatifPredicate, globalPointed, checkedObjects)
	}

	public static def Set<EObject> findAllReachableObjects(Resource resource) {
		val Predicate<EObject> predicate = [o|true]
		val checked = new HashSet
		findObjectsThatPointTo(resource.contents.toSet, predicate, new HashSet(), new HashSet(), new HashSet(), checked)
		return checked

	}

	private static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
		Set<EObject> pointedObjectsSatifPreficate, HashSet<EObject> checkedObjects) {

		val pointersToObjectsSatifPredicate = new HashSet<EObject>
		val Predicate<EObject> predicate = [EObject o|o != null && o.eResource == null]
		findObjectsThatPointTo(root, predicate, pointersToObjectsSatifPredicate, pointedObjectsSatifPreficate,
			new HashSet(), checkedObjects)
		return pointersToObjectsSatifPredicate
	}

	public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
		Set<EObject> pointedObjects) {
		val checkedObjects = new HashSet<EObject>
		return findObjectsThatPointToObjectsWithoutResource(root, pointedObjects, checkedObjects)
	}

	public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(Resource resource,
		Set<EObject> pointedObjects) {
		val result = new HashSet
		val checkedObjects = new HashSet<EObject>
		for (c : resource.contents) {
			result.addAll(findObjectsThatPointToObjectsWithoutResource(c, pointedObjects, checkedObjects))
		}
		return result
	}

	public static def EObject findRoot(EObject o) {
		if (o.eContainer == null)
			return o
		else
			return findRoot(o.eContainer)
	}

	public static def Set<EObject> findRoots(Collection<EObject> os) {
		val newRoots = new HashSet<EObject>
		for (p : os) {
			val root = Investigation::findRoot(p)
			newRoots.add(root)
		}
		return newRoots
	}

}
