package fr.inria.diverse.trace.plugin.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.EMFUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import java.io.File
import java.util.ArrayList
import java.util.List
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI

/**
 * Glues the generators : trace metamodel, emf project and trace manager
 */
class GenericTracePluginGenerator {

	//Inputs
	private val URI abstractSyntaxEcoreURI
	private val URI executionEcorExtURI
	private val URI eventsMetamodelURI
	private val String pluginName

	new(URI abstractSyntaxEcoreURI, URI executionEcorExtURI, URI eventsMetamodelURI, String pluginName) {
		this.abstractSyntaxEcoreURI = abstractSyntaxEcoreURI
		this.executionEcorExtURI = executionEcorExtURI
		this.eventsMetamodelURI = eventsMetamodelURI
		this.pluginName = pluginName
	}

	def void generate() {

		val ResourceSet rs = new ResourceSetImpl()

		// Load the three models
		val Resource abstractSyntaxResource = EMFUtil.loadModelURI(abstractSyntaxEcoreURI, rs)
		val EPackage abstractSyntax = abstractSyntaxResource.contents.filter(EPackage).get(0)
		val Resource executionEcorExtResource = EMFUtil.loadModelURI(executionEcorExtURI, rs)
		val Ecorext executionEcorExt = executionEcorExtResource.contents.filter(Ecorext).get(0)
		val Resource eventsMetamodelResource = EMFUtil.loadModelURI(eventsMetamodelURI, rs)
		val EPackage eventsMetamodel = eventsMetamodelResource.contents.filter(EPackage).get(0)
		val String languageName = abstractSyntax.name.replaceAll(" ", "") + "Trace"

		// Generate trace metamodel
		val TraceMMGenerator tmmgenerator = new TraceMMGenerator(executionEcorExt, eventsMetamodel, abstractSyntax)
		tmmgenerator.computeAllMaterial
		val EPackage tracemm = tmmgenerator.tracemmresult

		// Serializing the tracemm temporarily
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
		emfGen.generateModelCode

		// Finding the "src folder" in which to generate code
		val IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		val IJavaProject javaProject = JavaCore.create(emfGen.project)
		val List<IFolder> sourceFolders = new ArrayList();
		val IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
		for (var int i = 0; i < entries.length; i++) {
			val IClasspathEntry entry = entries.get(i);
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				val IPath path = entry.getPath();
				val IFolder sourceFolder = root.getFolder(path);
				sourceFolders.add(sourceFolder);
			}
		}

		// Now we need lots of things that require a monitor, so we do that in a dedicated action
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true,
			[ m |
				// We use JDT to create the package folders from a string "xxx.yyy.zzz"
				val IPackageFragmentRoot srcFolderFragment = javaProject.getPackageFragmentRoot(sourceFolders.get(0));
				val IPackageFragment fragment = srcFolderFragment.createPackageFragment(pluginName + ".tracemanager",
					true, m)
				// Adding plugin dependency to our trace api
				ManifestUtil.addToPluginManifest(emfGen.project, m, "fr.inria.diverse.trace.api")
				// Generate trace manager
				val TraceManagerGeneratorJava tmanagergen = new TraceManagerGeneratorJava(languageName,
					pluginName + ".tracemanager", tracemm, abstractSyntax, tmmgenerator.traceability)
				fragment.createCompilationUnit(tmanagergen.className + ".java", tmanagergen.generateCode, true, m)
			])

	}

}
