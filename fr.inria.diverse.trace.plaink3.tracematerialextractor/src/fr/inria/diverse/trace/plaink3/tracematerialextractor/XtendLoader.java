package fr.inria.diverse.trace.plaink3.tracematerialextractor;

import java.io.IOException;
import java.net.URLClassLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend.core.XtendStandaloneSetup;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

@SuppressWarnings("restriction")
public class XtendLoader {

	private static XtextResourceSet rs;

	private static void init() {
		injector = new XtendStandaloneSetup()
				.createInjectorAndDoEMFRegistration();
		rs = (XtextResourceSet) injector.getInstance(ResourceSet.class);
		rs.setClasspathURIContext(URLClassLoader.getSystemClassLoader());
		rs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}

	private static Injector injector;

	public static XtendFile load(URI uri) throws IOException {

		if (rs == null) {
			init();
		}
		Resource res = rs.getResource(uri, true);
		EcoreUtil.resolveAll(res);
		XtendFile xtendFile = (XtendFile) res.getContents().get(0);
		return xtendFile;
	}

	public static XtendFile load(String filePath) throws IOException {

		return load(org.eclipse.emf.common.util.URI.createFileURI(filePath));

	}

	public static XtendFile load(IFile file) throws IOException {

		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(),
				true);
		return load(uri);

	}

}
