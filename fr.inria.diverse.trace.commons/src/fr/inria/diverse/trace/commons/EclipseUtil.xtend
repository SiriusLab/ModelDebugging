package fr.inria.diverse.trace.commons

import org.eclipse.core.resources.IWorkspaceRoot
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IClasspathEntry
import java.util.List
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.ResourcesPlugin
import java.util.ArrayList
import org.eclipse.core.runtime.IPath
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Set
import org.eclipse.core.resources.IResource
import java.util.HashSet
import org.eclipse.core.resources.IFile
import org.eclipse.jdt.core.JavaModelException
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.Status
import java.io.FileNotFoundException
import java.io.IOException

class EclipseUtil {

	static class NoSourceFolderException extends Exception {

		new(JavaModelException exception) {
			super(exception)
		}

		new() {
			super();
		}

	}

	def static List<IFolder> findSrcFoldersOf(IJavaProject p) throws NoSourceFolderException {

		// Finding the "src folder" in which to generate code
		val IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		val List<IFolder> sourceFolders = new ArrayList();
		try {
			val IClasspathEntry[] entries = p.getResolvedClasspath(true);
			for (var int i = 0; i < entries.length; i++) {
				val IClasspathEntry entry = entries.get(i);
				if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					val IPath path = entry.getPath();
					val IFolder sourceFolder = root.getFolder(path);
					sourceFolders.add(sourceFolder);
				}
			}

		}
		// If we cannot find source folders, we simply return an empty list
		catch (JavaModelException e) {
			throw new NoSourceFolderException(e);
		}
		if (sourceFolders.empty) {
			throw new NoSourceFolderException();
		}
		return sourceFolders
	}

	/**
	 * Taken from http://www.mkyong.com/java/how-to-copy-directory-in-java/
	 */
	def static void copyFolder(File src, File dest) throws FileNotFoundException , IOException {

		if (src.isDirectory()) {

			//if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
			}

			//list all the directory contents
			val files = src.list();

			for (String file : files) {

				//construct the src and dest file structure
				val File srcFile = new File(src, file);
				val File destFile = new File(dest, file);

				//recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {

			//if file, then copy it
			//Use bytes stream to support all file types
			val InputStream in = new FileInputStream(src);
			val OutputStream out = new FileOutputStream(dest);

			val byte[] buffer = newByteArrayOfSize(1024);

			var int length;

			//copy the file content in bytes 
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();

		}
	}

	def static Set<IResource> findAllFilesOf(IFolder f) {
		val result = new HashSet<IResource>
		try {
			for (r : f.members) {
						if (r instanceof IFile)
							result.add(r)
						else if (r instanceof IFolder) {
							result.addAll(findAllFilesOf(r))
						}
					}
		}
		// If we find no files because of an error, we do nothing
		// TODO throw a warning
		catch (CoreException exc) {}
		return result
	}

	//	def public static void warn(String msg, String pluginID,Throwable e) {
	//		throw new CoreException(new Status(Status.WARNING, pluginID, msg, e));
	//	}
	def public static void error(String msg, String pluginID, Throwable e) throws CoreException {
		throw new CoreException(new Status(Status.ERROR, pluginID, msg, e));
	}

}
