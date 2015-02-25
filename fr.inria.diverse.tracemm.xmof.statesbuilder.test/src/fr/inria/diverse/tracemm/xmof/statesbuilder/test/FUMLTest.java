package fr.inria.diverse.tracemm.xmof.statesbuilder.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import fr.inria.diverse.tracemm.common.ConfigurableStatesBuilder;
import fr.inria.diverse.tracemm.common.GenericStatesBuilderConfigurationDynamicEObj;
import static org.junit.Assert.*;

public class FUMLTest {

	private static final String MODELS_INPUTS_FOLDER = "model_inputs";
	private static final String FUML_FOLDER = "fuml";
	private static final String FUML_MOLIZ_FOLDER = "org.modelexecution.xmof.examples/fuml/";
	private static final String FUML_MOLIZ_TEST_FOLDER = "org.modelexecution.xmof.examples/test/fuml/";
	private static final String CONFIGURATIONMODEL_FILE = "configurationmodel.xmi";
	private static final String FUML_METAMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";
	private static final String FUML_CONFIGURATION_PATH = FUML_MOLIZ_FOLDER + "fuml.xmof";
	private static final String FUML_BEHAVIOR_LIBRARY_FILENAME = FUML_MOLIZ_TEST_FOLDER
			+ "primitiveBehaviorLibrary.uml";
	private static final String FUML_TYPE_LIBRARY_FILENAME = FUML_MOLIZ_TEST_FOLDER + "primitiveTypeLibrary.uml";
	private static final String FUML_TRACEMM_FILENAME = "fumltracemm.ecore";

	private static final File modelsInputFolder = new File(MODELS_INPUTS_FOLDER);
	private static final File fumlFolder = new File(modelsInputFolder, FUML_FOLDER);
	private static final File fumlTraceMMFile = new File(fumlFolder, FUML_TRACEMM_FILENAME);

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;
	private XMOFVirtualMachine vm;
	private ConfigurationObjectMap configurationObjectMap;
	private ConfigurableStatesBuilder statesBuilder;

	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	}

	@Test
	public void test1() {
		testModel(1);
	}

	@Test
	public void test2() {
		testModel(2);

	}

	@Test
	public void test3() {
		testModel(3);

	}

	@Test
	public void test4() {
		testModel(4);

	}

	@Test
	public void test5() {
		testModel(5);

	}

	@Test
	public void test6() {
		testModel(6);

	}

	@Test
	public void test7() {
		testModel(7);

	}

	@Test
	public void test8() {
		testModel(8);

	}

	@Test
	public void test9() {
		testModel(9);
	}

	@Test
	public void test10() {
		testModel(10);

	}

	@Test
	public void test11() {
		testModel(11);
	}

	@Test
	public void testBV1_1() {
		testBV1(true,true);
	}
	
	@Test
	public void testBV1_2() {
		testBV1(true,false);
	}
	
	@Test
	public void testBV1_3() {
		testBV1(false,false);
	}
	
	@Test
	public void testBV1_4() {
		testBV1(false,true);
	}

	public void testModel(int paramNumber) {
		genericTest("org.modelexecution.xmof.examples/test/fuml/testmodel.uml",
				"org.modelexecution.xmof.examples/test/fuml/test" + paramNumber + "parameter.xmi", "testmodel", ""
						+ paramNumber);
	}

	public void testBV1(boolean param1, boolean param2) {
		genericTest("org.modelexecution.xmof.examples/test/fuml/anonCompany/ExampleB/ExampleBV1.uml",
				"org.modelexecution.xmof.examples/test/fuml/anonCompany/ExampleB/ExampleBV1_parameter_" + param1 + "_"
						+ param2 + ".xmi", "ExampleBV1", param1 + "_" + param2);
	}

	public void genericTest(String modelURI, String paramURI, String modelName, String paramName) {

		execute(EMFUtil.createPlatformPluginURI(modelURI), EMFUtil.createPlatformPluginURI(paramURI), true);

		EObject trace = statesBuilder.getConf().getTrace();

		Resource traceResource = EMFUtil.createResource(resourceSet, editingDomain,
				EMFUtil.createFileURI("tmp/" + modelName + "_" + paramName + "_trace.xmi"), trace);

		// Serializing the result
		try {
			traceResource.save(null);
		} catch (IOException e) {
			System.out.println("Couldn't serialize!");
			e.printStackTrace();
		}

		assertTrue(statesBuilder.getErrors().size() == 0);

	}

	@SuppressWarnings("unused")
	private int nodeCounter = 0;
	private int activityExecutionID = -1;

	private Trace execute(URI modelPath, URI parameterDefinitionPath, boolean cleanup) {
		return execute(modelPath, parameterDefinitionPath, EMFUtil.createPlatformPluginURI(FUML_CONFIGURATION_PATH),
				cleanup);
	}

	private Trace execute(URI modelPath, URI parameterDefinitionPath, URI configurationPath, boolean cleanup) {
		setupVM(modelPath, parameterDefinitionPath, configurationPath);

		vm.run();

		Trace trace = vm.getRawExecutionContext().getTrace(activityExecutionID);
		if (cleanup)
			cleanup();
		return trace;
	}

	private XMOFVirtualMachine setupVM(URI modelPath, URI parameterDefinitionPath, URI configurationPath) {

		Resource modelResource = loadResource(modelPath);
		Resource configurationResource = loadResource(configurationPath);
		Resource parameterDefintionResource = loadResource(parameterDefinitionPath);

		EcoreUtil.resolveAll(resourceSet);

		vm = createVM(modelResource, configurationResource, parameterDefintionResource);

		return vm;
	}

	private void cleanup() {
		vm.getRawExecutionContext().reset();
	}

	private XMOFVirtualMachine createVM(Resource modelResource, Resource configurationResource,
			Resource parameterDefintionResource) {
		configurationObjectMap = createConfigurationObjectMap(modelResource, configurationResource,
				parameterDefintionResource);

		Resource configurationModelResource = EMFUtil.createResource(resourceSet, editingDomain,
				EMFUtil.createFileURI(CONFIGURATIONMODEL_FILE), configurationObjectMap.getConfigurationObjects());

		List<ParameterValue> parameterValueConfiguration = XMOFUtil.getParameterValueConfiguration(
				parameterDefintionResource, configurationObjectMap);

		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet, editingDomain,
				configurationModelResource, parameterValueConfiguration);

		Resource traceMMResource = EMFUtil.loadMetamodel(resourceSet,
				EMFUtil.createFileURI(fumlTraceMMFile.getAbsolutePath()));

		GenericStatesBuilderConfigurationDynamicEObj conf = new GenericStatesBuilderConfigurationDynamicEObj(
				traceMMResource, FUML_METAMODEL_PATH, configurationResource, configurationObjectMap);
		statesBuilder = new ConfigurableStatesBuilder(configurationModelResource, conf);
		statesBuilder.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);

		return vm;
	}

	private ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource, Resource configurationResource,
			Resource parameterDefintionResource) {
		Resource primitiveTypeLibraryPath = loadResource(EMFUtil.createPlatformPluginURI(FUML_TYPE_LIBRARY_FILENAME));
		Resource primitiveBehaviorLibraryPath = loadResource(EMFUtil
				.createPlatformPluginURI(FUML_BEHAVIOR_LIBRARY_FILENAME));
		ConfigurationObjectMap configurationObjectMap = XMOFUtil.createConfigurationObjectMap(configurationResource,
				modelResource, parameterDefintionResource, primitiveTypeLibraryPath, primitiveBehaviorLibraryPath);
		return configurationObjectMap;
	}

	private Resource loadResource(URI fileURI) {
		return EMFUtil.loadResource(resourceSet, fileURI);
	}

}
