package fr.inria.diverse.trace.plugin.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.EMFUtil
import fr.inria.diverse.trace.commons.EclipseUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import java.io.File
import org.eclipse.core.resources.IProject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.ui.PlatformUI
import org.eclipse.core.runtime.IProgressMonitor

/**
 * Glues the generators : trace metamodel, emf project and trace manager
 */
class GenericTracePluginGenerator {

	//Inputs
	private val EPackage abstractSyntax //EcoreURI
	private val Ecorext executionEcorExt //URI
	private val EPackage eventsMetamodel //URI
	private val String pluginName

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String languageName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String packageQN
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var String traceManagerClassName
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IPackageFragment packageFragment
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var IProject project

	new(EPackage abstractSyntax, Ecorext executionEcorExt, EPackage eventsMetamodel, String pluginName) {
		this.abstractSyntax = abstractSyntax
		this.executionEcorExt = executionEcorExt
		this.eventsMetamodel = eventsMetamodel
		this.pluginName = pluginName
		this.packageQN = pluginName + ".tracemanager"
	}

	def void generate() {
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true,
			[ m |
				generate(m)
			])
	}

	def void generate(IProgressMonitor m) {

		languageName = abstractSyntax.name.replaceAll(" ", "") + "Trace"

		// Generate trace metamodel
		val TraceMMGenerator tmmgenerator = new TraceMMGenerator(executionEcorExt, eventsMetamodel, abstractSyntax)
		tmmgenerator.computeAllMaterial
		val EPackage tracemm = tmmgenerator.tracemmresult

		// Serializing the tracemm temporarily
		val ResourceSet rs = new ResourceSetImpl()
		val File tmpFolder = File.createTempFile("diverse", "tracemmgeneration")
		tmpFolder.delete
		tmpFolder.mkdir
		tmpFolder.deleteOnExit
		val File tmmFile = new File(tmpFolder, languageName + ".ecore")
		val Resource tmmResource = rs.createResource(URI.createFileURI(tmmFile.absolutePath))
		tmmResource.contents.add(tracemm)
		tmmResource.save(null)

		// Generate EMF project + code
		val EMFProjectGenerator emfGen = new EMFProjectGenerator(pluginName, tmmResource.URI)
		emfGen.generateBaseEMFProject
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

		// Generate trace manager
		val TraceManagerGeneratorJava tmanagergen = new TraceManagerGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, abstractSyntax, tmmgenerator.traceability, emfGen.referencedGenPackages)
		this.traceManagerClassName = tmanagergen.className
		packageFragment.createCompilationUnit(traceManagerClassName + ".java", tmanagergen.generateCode, true, m)

	}

}
