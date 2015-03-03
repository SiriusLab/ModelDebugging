package fr.inria.diverse.tracemm.semdiff.eval.internal;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.xmof.vm.util.EMFUtil;

public abstract class Util {

	private static final String FUML_DOMAINSPECIFIC_TRACEMETAMODEL_PATH = "platform:/plugin/fr.inria.diverse.tracemm.xmof.statesbuilder.test/model_inputs/fuml/fumltracemm.ecore";

	public static final String FUML_CONFIGURATION_PATH = "platform:/plugin/org.modelexecution.xmof.examples/fuml/fuml.xmof";
	private static final String FUML_METMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";

	private static final String FUML_TYPE_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveTypeLibrary.uml";
	private static final String FUML_BEHAVIOR_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveBehaviorLibrary.uml";

	public static final String FUML_GENERIC_MATCH_RULES_PATH = "matchrules/trace_generic/actionExecutionOrder.ecl";
	public static final String FUML_DOMAINSPECIFIC_MATCH_RULES_PATH = "matchrules/trace_domainspecific/actionExecutionOrder.ecl";

	public static final String GENERIC_TRACE_FOLDER = "trace_generic/";
	public static final String DOMAIN_SPECIFIC_TRACE_FOLDER = "trace_domainspecific/";

	// Models
	private static final String FUML_MODEL_FOLDER = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/";

	public static final String FUML_ANON_EXAMPLEB_MODEL_FOLDER = "anonCompany/ExampleB/";
	private static final String FUML_ANON_EXAMPLEB_MODEL_FILENAME = "ExampleBV";
	
	private static final String FUML_TEST_MODEL_FILENAME = "testmodel";

	public static Resource loadResource(ResourceSet resourceSet, String filePath) {
		URI uri = null;
		if (filePath.contains("platform:/plugin/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll(
					"platform:/plugin", ""));
		} else if (filePath.contains("platform:/resource/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll(
					"platform:/resource", ""));
		} else {
			uri = EMFUtil.createFileURI(filePath);
		}
		return EMFUtil.loadResource(resourceSet, uri);
	}
	
	public static EObject executeTestmodel(int modelNumber, boolean domainSpecific) {
		ModelExecutor executor = new ModelExecutor();
		return executor.execute(getFumlTestmodelModelPath(), 
				deriveFumlTestmodelParameterDefinitionPath(modelNumber), 
				FUML_METMODEL_PATH, FUML_CONFIGURATION_PATH, 
				getFumlTracemetamodelPath(domainSpecific), 
				deriveFumlTestmodelTracemodelPath(modelNumber, domainSpecific), 
				getFumlAdditionalModelInputPaths());
	}

	private static String getFumlTestmodelModelPath() {
		return FUML_MODEL_FOLDER + FUML_TEST_MODEL_FILENAME + ".uml";
	}
	
	private static String deriveFumlTestmodelParameterDefinitionPath(int modelNumber) {
		return FUML_MODEL_FOLDER + "test" + modelNumber + "parameter.xmi";
	}
	
	private static String deriveFumlTestmodelTracemodelPath(int modelNumber, boolean domainSpecific) {
		return getTracemodelFolder(domainSpecific) +  "test" + modelNumber + ".xmi";
	}

	public static EObject executeAnonExampleB(int version, boolean exists,
			boolean found, boolean acc, boolean domainSpecific) {
		String modelPath = deriveAnonExampleModelPath(version);
		String parameterDefinitionPath = deriveAnonExampleParameterDefinitionPath(
				version, exists, found, acc);
		String tracemodelPath = deriveAnonExampleTracemodelPath(version,
				exists, found, acc, domainSpecific);

		ModelExecutor executor = new ModelExecutor();
		return executor.execute(modelPath, parameterDefinitionPath,
				Util.FUML_METMODEL_PATH, Util.FUML_CONFIGURATION_PATH,
				getFumlTracemetamodelPath(domainSpecific),
				tracemodelPath, getFumlAdditionalModelInputPaths());
	}

	private static String deriveAnonExampleModelPath(int version) {
		String modelFilename = FUML_ANON_EXAMPLEB_MODEL_FILENAME + version
				+ ".uml";
		return FUML_MODEL_FOLDER + FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ modelFilename;
	}

	private static String deriveAnonExampleParameterDefinitionPath(int version,
			boolean exists, boolean found, boolean acc) {
		String parameterDefinitionFilename = deriveAnonExampleParameterDefinitionFileName(
				version, exists, found, acc);
		return FUML_MODEL_FOLDER + FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ parameterDefinitionFilename;
	}

	private static String deriveAnonExampleParameterDefinitionFileName(
			int version, boolean exists, boolean found, boolean acc) {
		String parameterDefinitionFilename = FUML_ANON_EXAMPLEB_MODEL_FILENAME
				+ version + "_parameter_" + exists + "_" + found
				+ (version == 3 ? ("_" + acc) : "") + ".xmi";
		return parameterDefinitionFilename;
	}
	
	private static String getFumlTracemetamodelPath(boolean domainSpecific) {
		return domainSpecific ? FUML_DOMAINSPECIFIC_TRACEMETAMODEL_PATH : null;
	}

	private static String deriveAnonExampleTracemodelPath(int version,
			boolean exists, boolean found, boolean acc, boolean domainSpecific) {
		String parameterDefinitionFileName = deriveAnonExampleParameterDefinitionFileName(
				version, exists, found, acc);
		return getTracemodelFolder(domainSpecific)
				+ FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ parameterDefinitionFileName;
	}

	private static String getTracemodelFolder(boolean domainSpecific) {
		return domainSpecific ? Util.DOMAIN_SPECIFIC_TRACE_FOLDER
				: GENERIC_TRACE_FOLDER;
	}
	
	private static String[] getFumlAdditionalModelInputPaths() {
		return new String[]{FUML_TYPE_LIBRARY_PATH, FUML_BEHAVIOR_LIBRARY_PATH};
	}

	public static MatchResult matchAnonExampleB(int version1, int version2,
			boolean exists, boolean found, boolean acc, boolean domainSpecific) {
		String tracemodelPath1 = deriveAnonExampleTracemodelPath(version1,
				exists, found, acc, domainSpecific);
		String tracemodelPath2 = deriveAnonExampleTracemodelPath(version2,
				exists, found, acc, domainSpecific);
		GenericTraceMatcher matcher = new GenericTraceMatcher();
		boolean match = matcher.match(tracemodelPath1, tracemodelPath2, null,
				FUML_CONFIGURATION_PATH,
				domainSpecific ? FUML_DOMAINSPECIFIC_TRACEMETAMODEL_PATH : null,
				getFumlMatchrules(domainSpecific));
		return new MatchResult(match, matcher.matchedWithoutErrors());
	}

	public static MatchResult matchTestmodel(int modelNumber1, int modelNumber2, boolean domainSpecific) {
		GenericTraceMatcher matcher = new GenericTraceMatcher();
		boolean match = matcher.match(deriveFumlTestmodelTracemodelPath(modelNumber1, domainSpecific), 
				deriveFumlTestmodelTracemodelPath(modelNumber2, domainSpecific), 
				FUML_METMODEL_PATH, FUML_CONFIGURATION_PATH, getFumlTracemetamodelPath(domainSpecific), getFumlMatchrules(domainSpecific));
		return new MatchResult(match, matcher.matchedWithoutErrors());
	}
	
	private static String getFumlMatchrules(boolean domainSpecific) {
		return domainSpecific ? FUML_DOMAINSPECIFIC_MATCH_RULES_PATH
				: FUML_GENERIC_MATCH_RULES_PATH;
	}
}
