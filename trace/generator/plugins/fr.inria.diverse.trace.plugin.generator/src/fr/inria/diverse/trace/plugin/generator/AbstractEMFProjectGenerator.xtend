package fr.inria.diverse.trace.plugin.generator

import org.eclipse.emf.common.util.URI
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.core.resources.IProject
import java.util.List
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EPackage
import java.util.HashSet
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.core.runtime.IProgressMonitor

abstract class AbstractEMFProjectGenerator {

	// Inputs
	protected val URI ecoreURI
	protected val String projectName

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	protected var IProject project
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	protected var List<GenPackage> referencedGenPackages
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	protected val Set<EPackage> rootPackages = new HashSet

	// Transient
	protected var GenModel genModel

	new(String projectName, URI ecoreURI) {
		this.projectName = projectName
		this.ecoreURI = ecoreURI
	}

	/**
	 * Creates a new EMF project with the ecore file and the genmodel in the "model" folder
	 * also mages project, referencedGenPackages and rootPackages available.
	 */
	def void generateBaseEMFProject()

	/**
	 * Generates the code using the genmodel.
	 */
	def void generateModelCode()

	/**
	 * Generates the code using the genmodel (within a Job).
	 */
	def void generateModelCode(IProgressMonitor m)

}
