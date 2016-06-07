package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import org.eclipse.core.resources.IProject
import java.util.Set
import org.eclipse.emf.ecore.EPackage
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.emf.ecore.resource.ResourceSet

interface TraceAddonGeneratorIntegrationConfiguration {
	
	def void compute(Language melangeLanguage, String languageName, IProject melangeProject,
		Set<EPackage> abstractSyntax, ResourceSet rs)

	def Ecorext getExecutionExtension();

	def boolean canWorkWith(Language melangeLanguage, IProject melangeProject)
	
	def Set<EPackage> getExecutionMetamodel()

}