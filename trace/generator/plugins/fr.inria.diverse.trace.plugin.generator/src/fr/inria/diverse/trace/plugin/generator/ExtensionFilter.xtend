package fr.inria.diverse.trace.plugin.generator

import java.util.Set
import ecorext.Ecorext
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import ecorext.ClassExtension
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EPackage
import java.util.HashSet
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Given a set of references to classes and properties from the execution metamodel,
 * will filter out all other elements from the execution extension transient model.
 * 
 * For now it is implemented through fully qualified name comparisons.
 */
class ExtensionFilter {

	// Input
	val Set<EClass> chosenClasses
	val Set<? extends EStructuralFeature> chosenProperties

	// Input / Output
	val Ecorext executionExtension
	
	// Output
	@Accessors(PUBLIC_GETTER,PRIVATE_SETTER) 
	var boolean didFilterSomething = false

	// Transient
	val Set<EClass> retainedClasses = new HashSet
	val Set<EStructuralFeature> retainedProperties = new HashSet

	new(Ecorext executionExtension, Set<EClass> chosenClasses, Set<? extends EStructuralFeature> chosenProperties) {
		if (chosenClasses != null)
			this.chosenClasses = chosenClasses
		else
			this.chosenClasses = #{}
		if (chosenProperties != null)
			this.chosenProperties = chosenProperties
		else
			this.chosenProperties = #{}
		this.executionExtension = executionExtension
	}

	public def void execute() {

		if (!chosenClasses.empty || !chosenProperties.empty) {

			val Set<String> chosenClassesFQNs = chosenClasses.map[c|EcoreCraftingUtil.getFQN(c, ".")].toSet
			val Set<String> chosenPropertiesFQNs = chosenProperties.map [ p |
				EcoreCraftingUtil.getFQN(p.EContainingClass, ".") + "." + p.name
			].toSet

			// First pass, we find everything to retain
			for (element : executionExtension.eAllContents.toSet) {
				if (element instanceof EClass) {
					val fqn = EcoreCraftingUtil.getFQN(element, ".")
					if (chosenClassesFQNs.contains(fqn)) {
						retainedClasses.add(element)
						retainedClasses.addAll(getAllSubtypesOf(element))
						retainedProperties.addAll(element.EAllStructuralFeatures)
					}

				} else if (element instanceof EStructuralFeature) {
					val container = element.eContainer
					val EClass class = if (container instanceof ClassExtension) {
							container.extendedExistingClass
						} else if (container instanceof EClass) {
							container
						}
					val fqn = EcoreCraftingUtil.getFQN(class, ".") + "." + element.name
					if (chosenPropertiesFQNs.contains(fqn)) {
						retainedProperties.add(element)
						retainedClasses.add(class)
						retainedClasses.addAll(getAllSubtypesOf(class))
						if (element instanceof EReference) {
							retainedClasses.add(element.EReferenceType)
							retainedClasses.addAll(getAllSubtypesOf(element.EReferenceType))
						}
					}
				}
			}

			// Second pass, we remove stuff, and their empty containers
			for (element : executionExtension.eAllContents.toSet) {
				if (element instanceof EClass) {
					if (!retainedClasses.contains(element)) {
						didFilterSomething = true
						element.EPackage.EClassifiers.remove(element)
						cleanEPackage(element.EPackage)
					}
				} else if (element instanceof EStructuralFeature) {
					if (!retainedProperties.contains(element)) {
						didFilterSomething = true
						val container = element.eContainer
						if (container instanceof ClassExtension) {
							container.newProperties.remove(element)
							if (container.newProperties.empty)
								executionExtension.classesExtensions.remove(container)
						} else if (container instanceof EClass) {
							container.EStructuralFeatures.remove(element)
						}
					}
				}
			}
		}
	}

	private def void cleanEPackage(EPackage p) {
		if (p.EClassifiers.empty && p.ESubpackages.empty) {
			val container = p.eContainer
			if (container instanceof EPackage) {
				container.ESubpackages.remove(p)
				cleanEPackage(container)
			} else if (container instanceof Ecorext) {
				container.newPackages.remove(p)
			}
		}
	}

	private def Set<EClass> getAllSubtypesOf(EClass cl) {
		return executionExtension.eAllContents.toSet.filter(EClass).filter[c|c.EAllSuperTypes.contains(cl)].toSet
	}

}