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

class EclipseUtil {
	def static List<IFolder> findSrcFoldersOf(IJavaProject p) {

		// Finding the "src folder" in which to generate code
		val IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		val List<IFolder> sourceFolders = new ArrayList();
		val IClasspathEntry[] entries = p.getResolvedClasspath(true);
		for (var int i = 0; i < entries.length; i++) {
			val IClasspathEntry entry = entries.get(i);
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				val IPath path = entry.getPath();
				val IFolder sourceFolder = root.getFolder(path);
				sourceFolders.add(sourceFolder);
			}
		}
		return sourceFolders
	}

	/**
	 * Taken from http://www.mkyong.com/java/how-to-copy-directory-in-java/
	 */
	def static void copyFolder(File src, File dest){

		if (src.isDirectory()) {

			//if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
				//System.out.println("Directory copied from " + src + "  to " + dest);
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
			//System.out.println("File copied from " + src + " to " + dest);
		}
	}

	def static Set<IResource> findAllFilesOf(IFolder f) {
		val result = new HashSet<IResource>
		for (r : f.members) {
			if (r instanceof IFile)
				result.add(r)
			else if (r instanceof IFolder) {
				result.addAll(findAllFilesOf(r))
			}
		}
		return result
	}

}
