package fr.inria.diverse.trace.plugin.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.EclipseUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import java.io.File
import java.util.HashSet
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors
import tracingannotations.TracingAnnotations

/**
 * Glues the generators : trace metamodel, emf project and trace manager
 */
class GenericTracePluginGenerator {

	// Inputs
	private val EPackage abstractSyntax // EcoreURI
	private val Ecorext executionEcorExt // URI
	private val String pluginName
	private val boolean gemoc

	// Transient
	private var TracingAnnotations tracingAnnotations

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String languageName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String tracedLanguageName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	val String packageQN
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String traceManagerClassName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String traceConstructorClassName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String traceExplorerClassName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String traceExtractorClassName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IPackageFragment packageFragment
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IProject project
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var TraceMMGenerationTraceability traceability
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var Set<GenPackage> referencedGenPackages

	new(EPackage abstractSyntax, Ecorext executionEcorExt, String pluginName, boolean gemoc) {

		this.abstractSyntax = abstractSyntax
		this.executionEcorExt = executionEcorExt
		this.pluginName = pluginName
		this.packageQN = pluginName + ".tracemanager"
		this.gemoc = gemoc

		// Given a file XXX.ecore, we try to find a model containing tracing annotations in XXX.tracingannotations 
		try {
			val rs = new ResourceSetImpl
			val uri = abstractSyntax.eResource.URI.trimFileExtension.appendFileExtension("tracingannotations")
			val resource = rs.createResource(uri)
			resource.load(null)
			this.tracingAnnotations = resource.contents.head as TracingAnnotations
		} catch (Throwable e) {
		}
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

		tracedLanguageName = abstractSyntax.name
		languageName = abstractSyntax.name.replaceAll(" ", "") + "Trace"

		var partialTraceManagement = false

		if (tracingAnnotations != null) {
			var Set<EClass> classesToTrace = new HashSet
			var Set<EStructuralFeature> propertiesToTrace = new HashSet
			classesToTrace.addAll(tracingAnnotations.classestoTrace)
			propertiesToTrace.addAll(tracingAnnotations.propertiesToTrace)
			val filter = new ExtensionFilter(executionEcorExt, classesToTrace, propertiesToTrace)
			filter.execute()
			partialTraceManagement = filter.didFilterSomething
		}

		// Generate trace metamodel
		val TraceMMGenerator tmmgenerator = new TraceMMGenerator(executionEcorExt, abstractSyntax, gemoc)
		tmmgenerator.computeAllMaterial
		tmmgenerator.sortResult
		val EPackage tracemm = tmmgenerator.tracemmresult

		// Serializing the tracemm temporarily
		val ResourceSet rs = new ResourceSetImpl()
		val File tmpFolder = File.createTempFile("diverse", "tracemmgeneration")
		tmpFolder.delete
		tmpFolder.mkdir
		tmpFolder.deleteOnExit
		val String ecoreFileName = languageName + ".ecore"
		val File tmmFile = new File(tmpFolder, ecoreFileName)
		val Resource tmmResource = rs.createResource(URI.createFileURI(tmmFile.absolutePath))
		tmmResource.contents.add(tracemm)
		tmmResource.save(null)

		// Generate EMF project
		val EMFProjectGenerator emfGen = new EMFProjectGenerator(pluginName, tmmResource.URI)
		emfGen.generateBaseEMFProject
		referencedGenPackages = emfGen.referencedGenPackages.map[findNestedGenpackages].flatten.toSet

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
		ManifestUtil.addToPluginManifest(project, m, "fr.inria.diverse.trace.api")
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
			abstractSyntax, partialTraceManagement)
		traceConstructorClassName = tconstructorgen.className
		packageFragment.createCompilationUnit(traceConstructorClassName + ".java", tconstructorgen.generateCode, true, m)
		
		// Generate trace explorer
		val TraceExplorerGeneratorJava texplorergen = new TraceExplorerGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, tmmgenerator.traceability, referencedGenPackages, gemoc,
			abstractSyntax, partialTraceManagement)
		traceExplorerClassName = texplorergen.className
		packageFragment.createCompilationUnit(traceExplorerClassName + ".java", texplorergen.generateCode, true, m)

		// Generate trace extractor
		val TraceExtractorGeneratorJava textractorgen = new TraceExtractorGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, tmmgenerator.traceability, referencedGenPackages, gemoc,
			abstractSyntax, partialTraceManagement)
		traceExtractorClassName = textractorgen.className
		packageFragment.createCompilationUnit(traceExtractorClassName + ".java", textractorgen.generateCode, true, m)
		
	}

}
