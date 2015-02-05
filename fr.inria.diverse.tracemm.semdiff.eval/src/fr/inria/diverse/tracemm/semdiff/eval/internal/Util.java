package fr.inria.diverse.tracemm.semdiff.eval.internal;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.xmof.vm.util.EMFUtil;

public abstract class Util {

	public static final String FUML_CONFIGURATION_PATH = "platform:/plugin/org.modelexecution.xmof.examples/fuml/fuml.xmof";
	public static final String FUML_METMODEL_PATH = null;
	
	public static final String FUML_TESTMODEL_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/testmodel.uml";
	public static final String FUML_TESTMODEL_PARAMETERDEFINITION_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/";
	
	public static final String FUML_TYPE_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveTypeLibrary.uml";
	public static final String FUML_BEHAVIOR_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveBehaviorLibrary.uml";
	
	public static final String FUML_GENERIC_TRACE_PATH = "trace_generic/";
	public static final String FUML_MATCH_RULES_ACTION_EXE_ORDERING_PATH = "matchrules/trace_generic/actionExecutionOrder.ecl";
	
	public static Resource loadResource(ResourceSet resourceSet, String filePath) {
		URI uri = null;
		if(filePath.contains("platform:/plugin/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll("platform:/plugin", ""));
		} else if(filePath.contains("platform:/resource/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll("platform:/resource", ""));
		} else {
			uri = EMFUtil.createFileURI(filePath); 
		}
		return EMFUtil.loadResource(resourceSet, uri);
	}
}
