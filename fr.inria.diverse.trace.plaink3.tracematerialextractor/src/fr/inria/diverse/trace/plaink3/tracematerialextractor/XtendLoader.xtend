package fr.inria.diverse.trace.plaink3.tracematerialextractor

import com.google.common.base.Joiner
import com.google.inject.Injector
import fr.inria.diverse.trace.commons.EclipseUtil
import java.io.File
import java.util.ArrayList
import java.util.HashSet
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
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.xbase.resource.BatchLinkableResource

/**
 * Lots of hacks in order to properly compule a java/xtend project and obtain an Xtend model
 * With code copied (and changed) from org.eclipse.xtend.ide.macro.JdtBasedProcessorProvider
 * TODO turns out once it failed, xtend compilation is screwed until eclipse restart...
 */
class XtendLoader {

	/**
	 * Output: the set of XtendFile objects
	 */
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var Set<XtendFile> xtendModel = new HashSet

	/**
	 * Output: the set of TypeResource containing the Jvm types (including annotation types)
	 * The URI of such resource uses the "java:/" prefix.
	 * Example to find a particular annotation among the types:
	 * <pre>
 	 * if (jvmTypeResource.URI.toString.equals("java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Aspect"))
	 *		aspectAnnotation = jvmTypeResource.contents.findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl
	 * </pre>
	 */
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	var Set<TypeResource> jvmTypeResources = new HashSet

	/**
	 * We create a fake XtendBatchCompiler to reuse all its clean standalone initialization procedure (with injections),
	 * and we override some operations to disable code generation and access to the information we need (ie the AST).
	 */
	private static class FakeXtendBatchCompiler extends XtendBatchCompiler {

		/**
		 * To make accessible the ResourceSet produced by the parser.
		 */
		@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
		ResourceSet resourceSet;

		/**
		 * To make accessible the list of issues found by the parser (warning, errors, etc.).
		 */
		@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
		List<Issue> issues;

		/*
		 * (non-Javadoc)
		 * @see XtendBatchCompiler#validate(org.eclipse.emf.ecore.resource.ResourceSet)
		 * 
		 * Hack allowing to store the resourceset to access it later
		 * And to access issues as well.
		 */
		override List<Issue> validate(ResourceSet resourceSet) {
			this.resourceSet = resourceSet;
			issues = super.validate(resourceSet);
			return issues;
		}

		/*
	 * (non-Javadoc)
	 * @see XtendBatchCompiler#generateJavaFiles(org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 * Disable the java generation.
	 */
		override generateJavaFiles(ResourceSet resourceSet) {
			// We do nothing
		}
	}

	/**
	 * Main operation to parse the xtend code and produce the model.
	 * @param classPath  The complete Java classpath, as one would give "javac -cp" in command line.
	 * @param srcFolders The list of folders containing the xtend files.
	 */
	public def void loadXtendModel(String classPath, List<String> srcFolders) {

		// The XtendBatchCompiler initialization will remove the Xtend factory from the registry, which breaks xtend completely in the current Eclipse!
		// Hence we store the factory, and we restore it whatever happens (in the finally at the end)
		val toRestore = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get("xtend")

		try {

			// We create the fake xtend compiler
			val Injector injector = new XtendStandaloneSetup().createInjectorAndDoEMFRegistration(); //XtendInjectorSingleton.INJECTOR;
			var FakeXtendBatchCompiler xtendBatchCompiler = injector.getInstance(FakeXtendBatchCompiler);

			// Limiting the output from the compiler (especially because of warning...)
			val test = LogManager.getLogger(XtendBatchCompiler)
			test.level = Level.FATAL

			// Setting the classpath
			xtendBatchCompiler.setClassPath(classPath);
			xtendBatchCompiler.setUseCurrentClassLoaderAsParent(true);

			// Setting the output folder (will not really be used, nothing will be generated)
			val File out = File.createTempFile("xtendLoaderOutput", "")
			out.delete
			out.mkdir
			out.deleteOnExit
			xtendBatchCompiler.setOutputPath(out.absolutePath);

			// Setting the src folders
			val String pathes = Joiner.on(File.pathSeparator).join(srcFolders);
			xtendBatchCompiler.setSourcePath(pathes);

			if (!xtendBatchCompiler.compile()) {

				// Gathering errors
				val StringBuilder issues = new StringBuilder();
				if (xtendBatchCompiler.getIssues != null && ! xtendBatchCompiler.getIssues.empty)
					for (i : xtendBatchCompiler.getIssues.filter[i|i.severity.equals(Severity.ERROR)])
						issues.append("\n" + i.toString)

				// Cleaning up
				xtendBatchCompiler.resourceSet.resources.clear
				xtendBatchCompiler.resourceSet = null
				xtendBatchCompiler.issues.clear
				xtendBatchCompiler.issues = null

				// Throwing error
				throw new Exception("Couldn't compile:\n " + issues.toString)
			}

			// Lastly, we gather all the information we wanted, from the ResourceSet
			val ResourceSet rs = xtendBatchCompiler.resourceSet
			for (Resource resource : rs.getResources()) {
				if (resource instanceof BatchLinkableResource) {
					for (EObject o : resource.allContents.toSet) {
						if (o instanceof XtendFile) {
							xtendModel.add(o);
						}
					}
				} else if (resource instanceof TypeResource) {
					jvmTypeResources.add(resource)
				}
			}
		} finally {
			if (toRestore != null)
				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xtend", toRestore)
		}
	}

	/**
	 * Main operation to parse the xtend code and produce the model.
	 * @param javaProject The Eclipse Java project containing the xtend code.
	 */
	public def void loadXtendModel(IJavaProject javaProject) {

		// Computing complete classpath of the java project
		val String classPath = computeClassPath(javaProject)

		// Getting all the src folders of the java project
		val List<String> existingDirs = new ArrayList<String>();
		for (IFolder f : EclipseUtil.findSrcFoldersOf(javaProject)) {
			existingDirs.add(f.location.toString)
		}

		// Calling the compiler
		loadXtendModel(classPath, existingDirs);
	}

	/**
	 * Computes a String with the complete Java classpath of an Eclipse Java project, ie bin folders of referenced projects, and paths to .jar files
	 * Probably not  
	 * 
	 * Code partly taken from http://www.programcreek.com/java-api-examples/org.eclipse.jdt.core.IClasspathEntry
	 * So indirectly from Acceleo, it seems
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
					s = if (library != null) {
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
			if (s != null) {
				urls.add(s);
			}
		}

		return Joiner.on(System.getProperty("path.separator")).join(urls)
	}

	/**
	 * Code partly taken from http://www.programcreek.com/java-api-examples/org.eclipse.jdt.core.IClasspathEntry
	 * So indirectly from Acceleo, it seems
	 */
	def static private List<String> getOutputFolders(IJavaProject javaProject) {
		val List<String> result = newArrayList;
		val IFolder outputFolderResource = javaProject.workspaceRoot.getFolder(javaProject.outputLocation)
		val File outputFolderFile = URIUtil.toFile(outputFolderResource.locationURI)
		result.add(outputFolderFile.absolutePath);
		for (IClasspathEntry entry : javaProject.getRawClasspath()) {
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_SOURCE: {
					val path = entry.getOutputLocation();
					if (path != null) {
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
