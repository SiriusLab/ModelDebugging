package fr.inria.diverse.trace.plaink3.tracematerialextractor

import com.google.common.base.Joiner
import com.google.inject.Injector
import java.io.File
import java.util.ArrayList
import java.util.HashSet
import java.util.Iterator
import java.util.List
import java.util.Set
import org.apache.log4j.BasicConfigurator
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtend.core.XtendInjectorSingleton
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtext.xbase.resource.BatchLinkableResource
import fr.inria.diverse.trace.commons.EclipseUtil
import org.eclipse.core.resources.IFolder
import org.eclipse.xtend.core.compiler.batch.XtendBatchCompiler
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.common.types.impl.JvmAnnotationTypeImpl
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.common.types.impl.JvmEnumerationTypeImplCustom

/**
 * Lots of hacks in order to properly compule a java/xtend project and obtain an Xtend model
 * With code copied (and changed) from org.eclipse.xtend.ide.macro.JdtBasedProcessorProvider
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
	var JvmEnumerationTypeImplCustom transactionSupport
	
	new(IJavaProject javaProject) {
		this.javaProject = javaProject
		xtendModel = new HashSet
	}

	private static class FakeXtendBatchCompiler extends XtendBatchCompiler {

		public def ResourceSet getResourceSet() {
			return rs;
		}

		private ResourceSet rs;

		/*
	 * (non-Javadoc)
	 * @see org.eclipse.xtend.core.compiler.batch.XtendBatchCompiler#validate(org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 * Hack allowing to store the resourceset to access it later
	 */
		override List<Issue> validate(ResourceSet resourceSet) {
			rs = resourceSet;
			return super.validate(resourceSet);
		}

		/*
	 * (non-Javadoc)
	 * @see org.eclipse.xtend.core.compiler.batch.XtendBatchCompiler#generateJavaFiles(org.eclipse.emf.ecore.resource.ResourceSet)
	 * 
	 * We disable the java generation, since we only want to obtain the resourceset
	 * 
	 */
		override generateJavaFiles(ResourceSet resourceSet) {
			// We do nothing
		}
	}

	public def void loadXtendModel() {

		// Log4j configuration
		BasicConfigurator.configure();

		// We create the xtend compile
		val Injector injector = XtendInjectorSingleton.INJECTOR;
		val FakeXtendBatchCompiler xtendBatchCompiler = injector.getInstance(FakeXtendBatchCompiler);

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

		if (!xtendBatchCompiler.compile()) {
			throw new Exception("Couldnt compile")
		}
		val ResourceSet rs = xtendBatchCompiler.getResourceSet();


		var Set other = new HashSet
		for (Resource resource : rs.getResources()) {
			if (resource instanceof BatchLinkableResource) {
				for (val Iterator<EObject> i = resource.getAllContents(); i.hasNext();) {
					val EObject o = i.next();
					if (o instanceof XtendFile) {
						xtendModel.add(o); 
					}
				}
			} else if (resource instanceof org.eclipse.xtext.common.types.access.TypeResource){
				if (resource.URI.toString.equals("java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.Aspect"))
					aspectAnnotation = resource.contents.findFirst[c|c instanceof JvmAnnotationTypeImpl] as JvmAnnotationTypeImpl
				else if (resource.URI.toString.equals("java:/Objects/fr.inria.diverse.k3.al.annotationprocessor.TransactionSupport"))
					transactionSupport = resource.contents.findFirst[c|c instanceof JvmEnumerationTypeImplCustom] as JvmEnumerationTypeImplCustom
				
			} 
		}

	}

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
					var IPath path = entry.getPath() //tostring de ça

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

		return Joiner.on(":").join(urls)
	}

	def static private List<String> getOutputFolders(IJavaProject javaProject) {
		val List<String> result = newArrayList;

		//var IPath path = javaProject.getOutputLocation().addTrailingSeparator(); // not enough: need to find the real location of the java project
		//var URL url = new URL(URI.createPlatformResourceURI(path.toString(), true).toString());
		var IPath outputFolder = javaProject.workspaceRoot.location.append(javaProject.outputLocation)
		result.add(outputFolder.toString);
		for (IClasspathEntry entry : javaProject.getRawClasspath()) {
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_SOURCE: {
					val path = entry.getOutputLocation();
					if (path != null) {
						outputFolder = javaProject.workspaceRoot.location.append(path)
						result.add(outputFolder.toString);
					}
				}
			}
		}
		return result;
	}

	//	static private def getParentClassLoader() {
	//		val bundleClassLoader = TransformationContext.classLoader
	//		return bundleClassLoader
	//	}
	def static private getWorkspaceRoot(IJavaProject javaProject) {
		return javaProject.project.workspace.root
	}
}
