package fr.inria.diverse.trace.commons;

import static com.google.common.collect.Sets.newHashSet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtend.ide.buildpath.XtendLibClasspathAdder;
import org.eclipse.xtext.util.MergeableManifest;

/**
 * Code taken from XtendLibClasspathAdder
 * 
 * @author ebousse
 *
 */
@SuppressWarnings("restriction")
public class ManifestUtil {

	private static MergeableManifest createMergableManifest(
			IResource manifestFile) throws IOException, CoreException {
		InputStream originalManifest = ((IFile) manifestFile).getContents();
		try {
			return new MergeableManifest(originalManifest);
		} finally {
			originalManifest.close();
		}
	}

	public static boolean addToPluginManifest(IProject project,
			IProgressMonitor monitor, String pluginToAdd) throws IOException,
			CoreException {
		IResource manifestFile = project.findMember("META-INF/MANIFEST.MF");
		if (manifestFile != null && manifestFile.isAccessible()
				&& !manifestFile.getResourceAttributes().isReadOnly()
				&& manifestFile instanceof IFile) {
			OutputStream output = null;
			InputStream input = null;
			try {
				MergeableManifest manifest = createMergableManifest(manifestFile);
				List<String> plunginsToAdd = new ArrayList<String>();
				plunginsToAdd.add(pluginToAdd);
				manifest.addRequiredBundles(newHashSet(plunginsToAdd));
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				output = new BufferedOutputStream(out);
				manifest.write(output);
				ByteArrayInputStream in = new ByteArrayInputStream(
						out.toByteArray());
				input = new BufferedInputStream(in);
				((IFile) manifestFile).setContents(input, true, true, monitor);
				return true;
			} finally {
				if (output != null)
					output.close();
				if (input != null)
					input.close();
			}
		}
		return false;
	}

	public static void addXtendLibs(IJavaProject project, IProgressMonitor monitor) {
		XtendLibClasspathAdder xtendLibs = new XtendLibClasspathAdder();
		xtendLibs.addLibsToClasspath(project,monitor);
	}

}
