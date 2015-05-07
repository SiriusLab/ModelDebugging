package fr.inria.diverse.trace.plaink3.tracematerialextractor

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import ecorext.Ecorext
import ecorext.EcorextFactory
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.emf.ecore.EcoreFactory

class ExecutionExtensionGenerator {

	// Input
	private val EPackage extendedMetamodel

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var Ecorext mmextensionResult

	new(EPackage extendedMetamodel) {
		this.extendedMetamodel = extendedMetamodel
		this.mmextensionResult = EcorextFactory.eINSTANCE.createEcorext
	}

	protected def EPackage obtainExtensionPackage(EPackage runtimePackage) {

		// Null means that the root is the Ecorext object
		var EPackage result = null

		if(runtimePackage != null) {

			val tracedSuperPackage = obtainExtensionPackage(runtimePackage.ESuperPackage)

			if(tracedSuperPackage == null)
				result = mmextensionResult.newPackages.findFirst[p|p.name.equals(runtimePackage.name)]
			else
				result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(runtimePackage.name)]

			if(result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = runtimePackage.name
				result.nsURI = result.name // TODO
				result.nsPrefix = "" // TODO

				if(tracedSuperPackage == null) {
					mmextensionResult.newPackages.add(result)
				} else
					tracedSuperPackage.ESubpackages.add(result)
			}

		}
		return result
	}

	def void generate() {

		val Copier copier = new Copier()

		for (c : extendedMetamodel.eAllContents.filter(EClass).toSet) {

			// Either partially mutable or not mutable at all
			if(c.EAnnotations.isEmpty) {

				val mutableProperties = c.EStructuralFeatures.filter[p|!p.EAnnotations.empty]
				if(mutableProperties != null && !mutableProperties.empty) {
					val classExt = EcorextFactory.eINSTANCE.createClassExtension

					// TODO for now we refer to the extended metamodel, not the original one!
					classExt.extendedExistingClass = c
					mmextensionResult.classesExtensions.add(classExt)
					for (p : mutableProperties) {
						val copiedProp = copier.copy(p) as EStructuralFeature
						classExt.newProperties.add(copiedProp)
					}
				}
			}
			// Or completely mutable
			else {

				val EClass copiedClass = copier.copy(c) as EClass
				val EPackage containingPackage = obtainExtensionPackage(c.EPackage)
				containingPackage.EClassifiers.add(copiedClass)

			}
		}
		copier.copyReferences

	}
}
