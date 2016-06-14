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

	private static def void findObjectsThatPointTo(EObject root, Predicate<EObject> predicateReferencedObject,
		Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate, Set<EObject> pointed,
		HashSet<EObject> checkedObjects) {

		if (root != null && !ignoredEClasses.contains(root.eClass)) {
			val Set<EObject> objectsToCheck = new HashSet<EObject>
			objectsToCheck.addAll(root.eAllContents.toSet)
			objectsToCheck.add(root)
			for (potentialPointer : objectsToCheck.filter[o|!checkedObjects.contains(o)]) {
				checkedObjects.add(potentialPointer)

				if (potentialPointer.eContainer != null)
					pointed.add(potentialPointer.eContainer)
				for (f : potentialPointer.eClass.EAllReferences) {
					val Object stuffRefed = potentialPointer.eGet(f)
					if (f.many) {
						pointed.addAll(stuffRefed as Collection<EObject>)
					} else {
						pointed.add(stuffRefed as EObject)
					}
				}
				
				pointed.removeIf([o|o == null || ignoredEClasses.contains(o.eClass)])

				val localPointedObjectsSatifPredicate = pointed.filter[y|predicateReferencedObject.test(y)]
				if (!localPointedObjectsSatifPredicate.empty) {
					pointersToObjectsSatifPredicate.add(potentialPointer)
					pointedObjectsSatifPredicate.addAll(localPointedObjectsSatifPredicate)
				}
			}
		}
	}

	public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
		Set<EObject> pointedObjectsSatifPreficate, HashSet<EObject> checkedObjects) {

		val pointersToObjectsSatifPredicate = new HashSet<EObject>
		val toCheck = new HashSet<EObject>
		toCheck.add(root)
		val Predicate<EObject> predicate = [EObject o|o != null && o.eResource == null]

		var int i = 0
		val int limit = 100000;

		while (!toCheck.empty && i < limit) {
			i++
			val next = toCheck.get(0)
			toCheck.remove(next)
			val pointedObjects = new HashSet<EObject>
			findObjectsThatPointTo(next, predicate, pointersToObjectsSatifPredicate, pointedObjectsSatifPreficate,
				pointedObjects, checkedObjects)
			toCheck.addAll(pointedObjects)
			toCheck.removeAll(checkedObjects)
		}
		
		if (i == limit) {
			throw new Exception("LOOPED TOO LONG")
		}
		

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
