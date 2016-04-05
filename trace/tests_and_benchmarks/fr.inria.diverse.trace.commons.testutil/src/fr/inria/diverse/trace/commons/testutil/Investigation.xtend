package fr.inria.diverse.trace.commons.testutil

import org.eclipse.emf.ecore.EObject
import java.util.Set
import java.util.HashSet
import java.util.Collection
import java.util.function.Predicate

class Investigation {

	public static def Set<EObject> findObjectThatPointTo(EObject root, Predicate<EObject> predicateReferencedObject) {
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
				if (allObjectsRefed.exists[y|predicateReferencedObject.test(y)]) {
					result.add(o)
				}
			}
		}
		return result
	}

}