package fr.inria.diverse.tracemm.semdiff.eval;


public abstract class Evaluation {
	
	private static final String FUML_DOMAINSPECIFIC_TRACEMETAMODEL_PATH = "platform:/plugin/fr.inria.diverse.tracemm.xmof.statesbuilder.test/model_inputs/fuml/fumltracemm.ecore";

	protected static final String FUML_CONFIGURATION_PATH = "platform:/plugin/org.modelexecution.xmof.examples/fuml/fuml.xmof";
	protected static final String FUML_METMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";

	private static final String FUML_TYPE_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveTypeLibrary.uml";
	private static final String FUML_BEHAVIOR_LIBRARY_PATH = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/primitiveBehaviorLibrary.uml";

	private static final String FUML_GENERIC_MATCH_RULES_PATH = "matchrules/trace_generic/actionExecutionOrder.ecl";
	private static final String FUML_DOMAINSPECIFIC_MATCH_RULES_PATH = "matchrules/trace_domainspecific/actionExecutionOrder.ecl";

	private static final String GENERIC_TRACE_FOLDER = "trace_generic/";
	private static final String DOMAIN_SPECIFIC_TRACE_FOLDER = "trace_domainspecific/";

	// Models
	private static final String FUML_MODEL_FOLDER = "platform:/plugin/org.modelexecution.xmof.examples/test/fuml/";

	private static final String FUML_ANON_EXAMPLEB_MODEL_FOLDER = "anonCompany/ExampleB/";
	private static final String FUML_ANON_EXAMPLEB_MODEL_FILENAME = "ExampleBV";
	
	private static final String FUML_TEST_MODEL_FILENAME = "testmodel";

	protected String deriveAnonExampleTracemodelPath(int version,
			boolean exists, boolean found, boolean acc, boolean domainSpecific) {
		String parameterDefinitionFileName = deriveAnonExampleParameterDefinitionFileName(
				version, exists, found, acc);
		return getTracemodelFolder(domainSpecific)
				+ FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ parameterDefinitionFileName;
	}
	
	private String deriveAnonExampleParameterDefinitionFileName(
			int version, boolean exists, boolean found, boolean acc) {
		String parameterDefinitionFilename = FUML_ANON_EXAMPLEB_MODEL_FILENAME
				+ version + "_parameter_" + exists + "_" + found
				+ (version == 3 ? ("_" + acc) : "") + ".xmi";
		return parameterDefinitionFilename;
	}
	
	protected String deriveFumlTestmodelTracemodelPath(int modelNumber, boolean domainSpecific) {
		return getTracemodelFolder(domainSpecific) +  "test" + modelNumber + ".xmi";
	}
	
	private String getTracemodelFolder(boolean domainSpecific) {
		return domainSpecific ? DOMAIN_SPECIFIC_TRACE_FOLDER
				: GENERIC_TRACE_FOLDER;
	}
	
	protected String getFumlTracemetamodelPath(boolean domainSpecific) {
		return domainSpecific ? FUML_DOMAINSPECIFIC_TRACEMETAMODEL_PATH : null;
	}
	
	protected String getFumlMatchrules(boolean domainSpecific) {
		return domainSpecific ? FUML_DOMAINSPECIFIC_MATCH_RULES_PATH
				: FUML_GENERIC_MATCH_RULES_PATH;
	}
	
	protected String getFumlTestmodelModelPath() {
		return FUML_MODEL_FOLDER + FUML_TEST_MODEL_FILENAME + ".uml";
	}
	
	protected String deriveFumlTestmodelParameterDefinitionPath(int modelNumber) {
		return FUML_MODEL_FOLDER + "test" + modelNumber + "parameter.xmi";
	}

	protected String deriveAnonExampleModelPath(int version) {
		String modelFilename = FUML_ANON_EXAMPLEB_MODEL_FILENAME + version
				+ ".uml";
		return FUML_MODEL_FOLDER + FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ modelFilename;
	}

	protected String deriveAnonExampleParameterDefinitionPath(int version,
			boolean exists, boolean found, boolean acc) {
		String parameterDefinitionFilename = deriveAnonExampleParameterDefinitionFileName(
				version, exists, found, acc);
		return FUML_MODEL_FOLDER + FUML_ANON_EXAMPLEB_MODEL_FOLDER
				+ parameterDefinitionFilename;
	}
	
	protected String[] getFumlAdditionalModelInputPaths() {
		return new String[]{FUML_TYPE_LIBRARY_PATH, FUML_BEHAVIOR_LIBRARY_PATH};
	}
	
}
