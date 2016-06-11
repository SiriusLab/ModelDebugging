package fr.inria.diverse.trace.commons.testutil

import org.eclipse.emf.ecore.EObject
import java.util.Set
import java.util.HashSet
import java.util.Collection
import java.util.function.Predicate
import org.eclipse.emf.ecore.resource.Resource

class Investigation {

	public static def Set<EObject> findObjectsThatPointTo(EObject root, Predicate<EObject> predicateReferencedObject,
		Set<EObject> pointedObjects) {
		val Set<EObject> result = new HashSet
		for (o : root.eAllContents.toSet) {
			for (f : o.eClass.EAllReferences) {
				val Set<EObject> allObjectsRefed = new HashSet
				val Object stuffRefed = o.eGet(f)
				if (f.many) {
					allObjectsRefed.addAll(stuffRefed as Collection<EObject>)
				} else {
					allObjectsRefed.add(stuffRefed as EObject)
				}
				val filtered = allObjectsRefed.filter[y|predicateReferencedObject.test(y)]
				if (!filtered.empty) {
					result.add(o)
					pointedObjects.addAll(filtered)
				}
			}
		}
		return result
	}

	public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
		Set<EObject> pointedObjects) {
		return findObjectsThatPointTo(root, [o|o != null && o.eResource == null], pointedObjects)
	}

	public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(Resource resource,
		Set<EObject> pointedObjects) {
		val result = new HashSet
		for (c : resource.contents) {
			result.addAll(Investigation::findObjectsThatPointToObjectsWithoutResource(c, pointedObjects))
		}
		return result
	}

	public static def EObject findRoot(EObject o) {
		if (o.eContainer == null)
			return o
		else
			return findRoot(o.eContainer)
	}

}
