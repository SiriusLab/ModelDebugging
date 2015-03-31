package fr.inria.diverse.trace.plaink3.tracematerialextractor

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EStructuralFeature
import ecorext.Ecorext
import ecorext.EcorextFactory
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.xtend.lib.annotations.Accessors

class ExecutionExtensionGenerator {

	// Input
	private val EPackage extendedMetamodel

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var Ecorext result

	new(EPackage extendedMetamodel) {
		this.extendedMetamodel = extendedMetamodel
		this.result = EcorextFactory.eINSTANCE.createEcorext
	}

	def void generate() {

		val Copier copier = new Copier()

		for (c : extendedMetamodel.eAllContents.filter(EClass).toSet) {
			val mutableProperties = c.EStructuralFeatures.filter[p|!p.EAnnotations.empty]
			if (mutableProperties != null && !mutableProperties.empty) {
				val classExt = EcorextFactory.eINSTANCE.createClassExtension

				// TODO for now we refer to the extended metamodel, not the original one!
				classExt.extendedExistingClass = c
				result.classesExtensions.add(classExt)
				for (p : mutableProperties) {
					val copiedProp = copier.copy(p) as EStructuralFeature
					classExt.newProperties.add(copiedProp)
				}
			}
		}
		copier.copyReferences

	}
}
