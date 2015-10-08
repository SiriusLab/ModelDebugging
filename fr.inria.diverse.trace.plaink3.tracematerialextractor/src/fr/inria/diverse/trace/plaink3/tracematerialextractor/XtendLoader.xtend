package fr.inria.diverse.trace.plaink3.tracematerialextractor

import com.google.common.base.Joiner
import com.google.inject.Injector
import fr.inria.diverse.trace.commons.EclipseUtil
import java.io.File
import java.util.ArrayList
import java.util.HashSet
import java.util.Iterator
import java.util.List
import java.util.Set
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.URIUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtend.core.XtendStandaloneSetup
import org.eclipse.xtend.core.compiler.batch.XtendBatchCompiler
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.common.types.access.TypeResource
import org.eclipse.xtext.common.types.impl.JvmAnnotationTypeImpl
import org.eclipse.xtext.common.types.impl.JvmEnumerationTypeImplCustom
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.xbase.resource.BatchLinkableResource

/**
 * Lots of hacks in order to properly compule a java/xtend project and obtain an Xtend model
 * With code copied (and changed) from org.eclipse.xtend.ide.macro.JdtBasedProcessorProvider
 * TODO turns out once it failed, xtend compilation is screwed until eclipse restart...
 */
class XtendLoader {

	// Input
	private val IJavaProject javaProject

	// Outputs
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var Set<XtendFile> xtendModel
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var JvmAnnotationTypeImpl aspectAnnotation
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var JvmAnnotationTypeImpl stepAnnotation
	//@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	//var JvmEnumerationTypeImplCustom transactionSupport

	new(IJavaProject javaProject) {
		this.javaProject = javaProject
		xtendModel = new HashSet
	}

	private static class FakeXtendBatchCompiler extends XtendBatchCompiler {

		public def ResourceSet getResourceSet() {
			return rs;
		}

		public def List<Issue> getIssues() {
			return issues;
		}

		private ResourceSet rs;
		private List<Issue> issues;

		/*
	 * (non-Javadoc)
	 * @see XtendBatchCompiler#validate(org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 * Hack allowing to store the resourceset to access it later
	 * And to access issues as well.
	 */
		override List<Issue> validate(ResourceSet resourceSet) {
			rs = resourceSet;
			issues = super.validate(resourceSet);
			return issues;
		}

		/*
	 * (non-Javadoc)
	 * @see XtendBatchCompiler#generateJavaFiles(org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 * We disable the java generation, since we only want to obtain the resourceset
	 * 
	 */
		override generateJavaFiles(ResourceSet resourceSet) {
			// We do nothing
		}
	}

	public def void loadXtendModel() {

		// The XtendBatchCompiler initialization will remove the Xtend factory from the registry, which breaks xtend completely in the current Eclipse!
		// Hence we store the factory, and we restore it whatever happens (in the finally at the end)
		val toRestore = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get("xtend")

		try {

			// We create the xtend compiler
			val Injector injector = new XtendStandaloneSetup().createInjectorAndDoEMFRegistration(); //XtendInjectorSingleton.INJECTOR;
			var FakeXtendBatchCompiler xtendBatchCompiler = injector.getInstance(FakeXtendBatchCompiler);

			// Limiting the output from the compiler (especially because of warning...)
			val test = LogManager.getLogger(XtendBatchCompiler)
			test.level = Level.FATAL

			// Computing the complete classpath required by the project
			val String classPath = computeClassPath(javaProject)
			xtendBatchCompiler.setClassPath(classPath);
			xtendBatchCompiler.setUseCurrentClassLoaderAsParent(true);

			// 	Setting the input folders, eg the src folder
			val List<String> existingDirs = new ArrayList<String>();
			for (IFolder f : EclipseUtil.findSrcFoldersOf(javaProject)) {
				existingDirs.add(f.location.toString)
			}

			// Setting the output folder (will not really be used, nothing will be generated)
			val out = javaProject.project.location.append("/bin").toString
			xtendBatchCompiler.setOutputPath(out);

			val String pathes = Joiner.on(File.pathSeparator).join(existingDirs);
			xtendBatchCompiler.setSourcePath(pathes);

			if(!xtendBatchCompiler.compile()) {

				// Gathering errors
				val StringBuilder issues = new StringBuilder();
				for (i : xtendBatchCompiler.getIssues.filter[i|i.severity.equals(Severity.ERROR)])
					issues.append("\n" + i.toString)

				//Cleaning up
				xtendBatchCompiler.rs.resources.clear
				xtendBatchCompiler.rs = null
				xtendBatchCompiler.issues.clear
				xtendBatchCompiler.issues = null

				// Throwing error
				throw new Exception("Couldn't compile:\n " + issues.toString)
			}

			val ResourceSet rs = xtendBatchCompiler.getResourceSet();

			for (Resource resource : rs.getResources()) {
				if(resource instanceof BatchLinkableResource) {
					for (val Iterator<EObject> i = resource.getAllContents(); i.hasNext();) {
						val EObject o = i.next();
						if(o instanceof XtendFile) {
							xtendModel.add(o);
						}
					}
				} else if(resource instanceof TypeResource) {
					if(resource.URI.toString.equals("java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Aspect"))
						aspectAnnotation = resource.contents.findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl
					else if(resource.URI.toString.equals(
						"java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Step"))
						stepAnnotation = resource.contents.findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl

				}
			}
		} finally {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xtend", toRestore)
		}
	}

	/**
	 * Code taken from... can't remember where :(
	 */
	private static def String computeClassPath(IJavaProject projectToUse) {
		val resolvedClasspath = projectToUse.getResolvedClasspath(true)
		val List<String> urls = newArrayList()
		urls.addAll(getOutputFolders(projectToUse));
		for (entry : resolvedClasspath) {
			var String s = null
			switch entry.entryKind {
				case IClasspathEntry.CPE_SOURCE: { /* do nothing */
				}
				case IClasspathEntry.CPE_PROJECT: {
					var IPath path = entry.getPath()
					val IResource project = projectToUse.workspaceRoot.findMember(path)
					urls.addAll(getOutputFolders(JavaCore.create(project.getProject())))
				}
				case IClasspathEntry.CPE_LIBRARY: {
					var IPath path = entry.getPath()

					// if the library is in the workspace, the entry path is relative to the workspace root
					// thus we load it as a resource and take the raw path to find the location in the file system
					val IResource library = projectToUse.workspaceRoot.findMember(path)
					s = if(library != null) {
						library.rawLocationURI.toString
					} else {

						// otherwise we use the path itself
						path.toFile().absolutePath
					}
				}
				default: {
					var IPath path = entry.getPath();
					s = path.toFile().absolutePath
				}
			}
			if(s != null) {
				urls.add(s);
			}
		}

		return Joiner.on(System.getProperty("path.separator")).join(urls)		
	}

	def static private List<String> getOutputFolders(IJavaProject javaProject) {
		val List<String> result = newArrayList;
		val IFolder outputFolderResource = javaProject.workspaceRoot.getFolder(javaProject.outputLocation)
		val File outputFolderFile = URIUtil.toFile(outputFolderResource.locationURI)
		result.add(outputFolderFile.absolutePath);
		for (IClasspathEntry entry : javaProject.getRawClasspath()) {
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_SOURCE: {
					val path = entry.getOutputLocation();
					if(path != null) {
						val IFolder outputFolderResource2 = javaProject.workspaceRoot.getFolder(path)
						val File outputFolderFile2 = URIUtil.toFile(outputFolderResource2.locationURI)
						result.add(outputFolderFile2.absolutePath);
					}
				}
			}
		}
		return result;
	}

	def static private getWorkspaceRoot(IJavaProject javaProject) {
		return javaProject.project.workspace.root
	}
}
