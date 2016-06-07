package fr.inria.diverse.trace.gemoc.generator.k3

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.trace.plaink3.tracematerialextractor.K3ExecutionExtensionGenerator
import fr.inria.diverse.trace.plaink3.tracematerialextractor.K3StepExtractor
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.core.IType
import fr.inria.diverse.trace.gemoc.generator.TraceAddonGeneratorIntegrationConfiguration
import ecorext.Ecorext

/**
 * Plenty of ways to call the generator in an eclipse context
 */
class K3TraceAddonGeneratorIntegrationConfiguration implements TraceAddonGeneratorIntegrationConfiguration {

	private var Ecorext mmextension
	private var Set<EPackage> executionMetamodel

	private static def Set<IType> findAspects(Language language, IProject melangeProject) {
		val aspectNames = language.semantics.map[aspectTypeRef.type.qualifiedName].toList
		val IJavaProject javaProject = JavaCore.create(melangeProject);
		val aspectClasses = aspectNames.map[it|javaProject.findType(it)].toSet
		return aspectClasses

	}

	override compute(Language language, String selectedLanguage, IProject melangeProject, Set<EPackage> abstractSyntax,
		ResourceSet rs) {

		this.executionMetamodel = abstractSyntax

		val aspectClasses = findAspects(language, melangeProject)

		val K3ExecutionExtensionGenerator extgen = new K3ExecutionExtensionGenerator(abstractSyntax.head);
		extgen.generate();

		val mmextension = extgen.mmextensionResult

		val K3StepExtractor eventsgen = new K3StepExtractor(aspectClasses, selectedLanguage, abstractSyntax.head,
			mmextension);
		eventsgen.generate();

		this.mmextension = mmextension

	}

	override canWorkWith(Language language, IProject melangeProject) {
		val aspectClasses = findAspects(language, melangeProject)
		return !aspectClasses.empty
	}

	override getExecutionExtension() {
		return mmextension
	}

	override getExecutionMetamodel() {
		return executionMetamodel
	}

}
