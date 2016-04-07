package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import org.eclipse.core.resources.IProject
import java.util.Set
import org.eclipse.emf.ecore.EPackage
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.emf.ecore.resource.ResourceSet

interface TraceAddonGeneratorIntegrationConfiguration {
	
	def Ecorext getExecutionExtension(Language melangeLanguage, String languageName, IProject melangeProject, Set<EPackage> abstractSyntax, ResourceSet rs)
	
}