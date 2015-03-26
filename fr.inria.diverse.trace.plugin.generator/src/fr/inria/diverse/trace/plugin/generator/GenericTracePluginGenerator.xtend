package fr.inria.diverse.trace.plugin.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.EMFUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerator
import java.io.File
import java.util.ArrayList
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.ICompilationUnit
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.IWorkbenchPage
import java.util.HashMap
import org.eclipse.core.resources.IMarker
import org.eclipse.ui.ide.IDE

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
		val IPackageFragmentRoot srcFolder = javaProject.getPackageFragmentRoot(sourceFolders.get(0));
		val folder = srcFolder.correspondingResource //TODO use this !
		
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true,
			[ m |
				val IPackageFragment fragment = srcFolder.createPackageFragment(pluginName + ".tracemanager", true, m)
				// Setting the project xtend friendly
				ManifestUtil.addXtendLibs(javaProject, m)
				// Adding plugin dependency to our trace api
				ManifestUtil.addToPluginManifest(emfGen.project, m, "fr.inria.diverse.trace.api")
				// Generate trace manager
				// TODO don't rely on JDT?
				val TraceManagerGenerator tmanagergen = new TraceManagerGenerator(languageName,
					pluginName + ".tracemanager", tracemm, abstractSyntax, tmmgenerator.traceability)
				val ICompilationUnit cu = fragment.createCompilationUnit(tmanagergen.className + ".java",
					tmanagergen.generateCode, true, m)
				val IResource r = cu.getResource
				val IFile f = r as IFile
				val IPath newPath = javaProject.path.append(f.projectRelativePath).removeLastSegments(1).append(
					new Path(tmanagergen.className + ".xtend"))
				f.move(newPath, IFile.FORCE, m)
				val IResource newFile = emfGen.project.findMember(
					f.projectRelativePath.removeLastSegments(1).append(new Path(tmanagergen.className + ".xtend")))
				val editor = IDE.getDefaultEditor(newFile as IFile)
				
				IDE.openEditor(PlatformUI.workbench.activeWorkbenchWindow.activePage, f,editor.id);
			//			   val IWorkbenchPage page = PlatformUI.workbench.activeWorkbenchWindow.activePage
			//			   val HashMap map = new HashMap();
			//			   map.put(IMarker.LINE_NUMBER, new Integer(5));
			//			   map.put(IWorkbenchPage.EDITOR_ID_ATTR, 
			//			      "org.eclipse.ui.DefaultTextEditor");
			//			   val IMarker marker = f.createMarker(IMarker.TEXT);
			//			   marker.setAttributes(map);
			//			   //page.openEditor(marker); //2.1 API
			//			   IDE.openEditor(marker); //3.0 API
			//			   marker.delete();
			//				
			])

	}

}
