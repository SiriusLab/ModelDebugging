package fr.inria.diverse.tracemm.test.util;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFUtil {

	public static void registerXMIFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
	}
	
	public static void registerEcoreFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());
	}
	
	public static URI createFileURI(String path) {
		return URI.createFileURI(createFile(path).getAbsolutePath());
	}
	
	public static File createFile(String path) {
		return new File(path);
	}


}
