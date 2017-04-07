/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.gemoc.generator

import fr.inria.diverse.trace.commons.EclipseUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.gemoc.generator.codegen.StateManagerGeneratorJava
import fr.inria.diverse.trace.gemoc.generator.codegen.TraceConstructorGeneratorJava
import fr.inria.diverse.trace.gemoc.generator.util.StandaloneEMFProjectGenerator
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import java.util.Set
import opsemanticsview.OperationalSemanticsView
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EPackage
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors
import fr.inria.diverse.trace.gemoc.generator.util.AbstractEMFProjectGenerator

//import tracingannotations.TracingAnnotations

/**
 * Glues the generators : trace metamodel, emf project and trace manager
 */
class GenericTracePluginGenerator {

	// Inputs
	private val OperationalSemanticsView opsemanticsview // URI
	private val String pluginName
	private val boolean gemoc

	// Transient
	//private var TracingAnnotations tracingAnnotations

	// Outputs
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String languageName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String tracedLanguageName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	val String packageQN
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String traceManagerClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String traceConstructorClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String traceExplorerClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String stateManagerClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String traceExtractorClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var String traceNotifierClassName
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var IPackageFragment packageFragment
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var IProject project
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var TraceMMGenerationTraceability traceability
	@Accessors(#[PUBLIC_GETTER, PROTECTED_SETTER])
	var Set<GenPackage> referencedGenPackages

	new(OperationalSemanticsView opsemanticsview, String pluginName, boolean gemoc) {

		this.opsemanticsview = opsemanticsview
		this.pluginName = pluginName
		this.packageQN = pluginName + ".tracemanager"
		this.gemoc = gemoc

		// Given a file XXX.ecore, we try to find a model containing tracing annotations in XXX.tracingannotations 
//		try {
//			val rs = new ResourceSetImpl
//			val uri = abstractSyntax.eResource.URI.trimFileExtension.appendFileExtension("tracingannotations")
//			val resource = rs.createResource(uri)
//			resource.load(null)
//			this.tracingAnnotations = resource.contents.head as TracingAnnotations
//		} catch (Throwable e) {
//		}
	}

	def void generate() {
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true, [ m |
			generate(m)
		])
	}

	private static def Set<GenPackage> findNestedGenpackages(GenPackage p) {
		val result = p.nestedGenPackages.toSet
		result.add(p)
		for (n : p.nestedGenPackages) {
			result.addAll(findNestedGenpackages(n))
		}
		return result
	}

	def void generate(IProgressMonitor m) {
		tracedLanguageName = opsemanticsview.executionMetamodel.name
		languageName = tracedLanguageName.replaceAll(" ", "") + "Trace"

		var partialTraceManagement = false
//TODO disabled for now, the whole approach must be adapted since Ecorext is not used anymore
//		if (tracingAnnotations != null) {
//			var Set<EClass> classesToTrace = new HashSet
//			var Set<EStructuralFeature> propertiesToTrace = new HashSet
//			classesToTrace.addAll(tracingAnnotations.classestoTrace)
//			propertiesToTrace.addAll(tracingAnnotations.propertiesToTrace)
//			val filter = new ExtensionFilter(opsemanticsview, classesToTrace, propertiesToTrace)
//			filter.execute()
//			partialTraceManagement = filter.didFilterSomething
//		}

		// Generate trace metamodel
		val TraceMMGenerator tmmgenerator = new TraceMMGenerator(opsemanticsview, gemoc)
		tmmgenerator.computeAllMaterial
		tmmgenerator.sortResult
		val EPackage tracemm = tmmgenerator.tracemmresult

		// Generate EMF project
		val AbstractEMFProjectGenerator emfGen = new StandaloneEMFProjectGenerator(pluginName, tracemm)
		emfGen.generateBaseEMFProject(m)
		val referencedGenPackagesRoots = emfGen.referencedGenPackages
		referencedGenPackages = referencedGenPackagesRoots.map[findNestedGenpackages].flatten.toSet

		// At this point the wizard has created and reloaded a new resource with the trace metamodel
		// We access this new metamodel/resource thanks to emfGen.rootPackages
		// And we add add the missing gemoc getCaller implementations to the trace metamodel
		if (gemoc) {
			tmmgenerator.addGetCallerEOperations(emfGen.rootPackages, referencedGenPackages)
			emfGen.rootPackages.head.eResource.save(null)
		}

		// Generate code
		emfGen.generateModelCode(m)
		this.project = emfGen.project

		// Finding the "src folder" in which to generate code
		val IJavaProject javaProject = JavaCore.create(project)
		val sourceFolders = EclipseUtil.findSrcFoldersOf(javaProject)

		// Now we need lots of things that require a monitor, so we do that in a dedicated action
		// We use JDT to create the package folders from a string "xxx.yyy.zzz"
		val IPackageFragmentRoot srcFolderFragment = javaProject.getPackageFragmentRoot(sourceFolders.get(0));
		packageFragment = srcFolderFragment.createPackageFragment(packageQN, true, m)

		// Adding plugin dependency to our trace api
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.emf.transaction")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.emf.compare")
		ManifestUtil.addToPluginManifest(project, m, "org.gemoc.executionframework.engine")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.xtext")

		if (gemoc) {
			ManifestUtil.addToPluginManifest(project, m, "org.gemoc.commons.eclipse")
		}

		this.traceability = tmmgenerator.traceability

		// Generate trace constructor
		val TraceConstructorGeneratorJava tconstructorgen = new TraceConstructorGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, tmmgenerator.traceability, referencedGenPackages, gemoc,
			opsemanticsview.executionMetamodel, partialTraceManagement)
		traceConstructorClassName = tconstructorgen.className
		packageFragment.createCompilationUnit(traceConstructorClassName + ".java", tconstructorgen.generateCode, true, m)

		// Generate state manager
		val StateManagerGeneratorJava statemanagergem = new StateManagerGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, tmmgenerator.traceability, referencedGenPackages)
		stateManagerClassName = statemanagergem.className
		packageFragment.createCompilationUnit(stateManagerClassName + ".java", statemanagergem.generateCode, true, m)
	}
}