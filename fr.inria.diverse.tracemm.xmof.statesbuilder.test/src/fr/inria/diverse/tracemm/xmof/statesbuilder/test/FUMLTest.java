package fr.inria.diverse.tracemm.xmof.statesbuilder.test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.AfterClass;
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

public class FUMLTest {

	private static final String MODELS_INPUTS_FOLDER = "model_inputs";
	private static final String FUML_FOLDER = "fuml";
	private static final String CONFIGURATIONMODEL_FILE = "configurationmodel.xmi";
	private static final String FUML_METAMODEL_PATH = "http://www.eclipse.org/uml2/5.0.0/UML";
	private static final String FUML_CONFIGURATION_NAME = "fuml.xmof";
	private static final String FUML_BEHAVIOR_LIBRARY_FILENAME = "primitiveBehaviorLibrary.uml";
	private static final String FUML_TYPE_LIBRARY_FILENAME = "primitiveTypeLibrary.uml";
	private static final String FUML_TRACEMM_FILENAME = "fumltracemm.ecore";

	private static final File modelsInputFolder = new File(MODELS_INPUTS_FOLDER);
	private static final File fumlFolder = new File(modelsInputFolder, FUML_FOLDER);
	private static final File fumlConfigurationFile = new File(fumlFolder, FUML_CONFIGURATION_NAME);
	private static final File fumlBehaviorLibraryFile = new File(fumlFolder, FUML_BEHAVIOR_LIBRARY_FILENAME);
	private static final File fumlTypeLibraryFile = new File(fumlFolder, FUML_TYPE_LIBRARY_FILENAME);
	private static final File fumlTraceMMFile = new File(fumlFolder, FUML_TRACEMM_FILENAME);

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;
	private XMOFVirtualMachine vm;
	private ConfigurationObjectMap configurationObjectMap;

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

	@BeforeClass
	public static void collectAllActivities() {
		collectAllActivities(fumlConfigurationFile.getAbsolutePath());
	}

	@Test
	public void test() {
		execute(new File(fumlFolder, "testmodel.uml").getAbsolutePath(),
				new File(fumlFolder, "test1parameter.xmi").getAbsolutePath(), true);

	}

	@SuppressWarnings("unused")
	private int nodeCounter = 0;
	private int activityExecutionID = -1;
	private static Set<String> executedActivities = new HashSet<String>();

	@After
	public void reset() {
		activityExecutionID = -1;
		// System.out.println("executed nodes: " + nodeCounter);
		nodeCounter = 0;
	}
	
	@AfterClass
	public static void printConfigurationActivityCoverage() {
		System.out.println(executedActivities.size() + "/" + allActivities.size() + " executed");
		System.out.println("not executed:");
		Set<String> notExecutedActivities = new HashSet<String>(allActivities);
		notExecutedActivities.removeAll(executedActivities);
		for (String notExecutedActivity : notExecutedActivities) {
			System.out.println(notExecutedActivity.toString());
		}
	}

	private ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource, Resource configurationResource,
			Resource parameterDefintionResource) {
		Resource primitiveTypeLibraryPath = loadResource(fumlTypeLibraryFile.getAbsolutePath());
		Resource primitiveBehaviorLibraryPath = loadResource(fumlBehaviorLibraryFile.getAbsolutePath());
		ConfigurationObjectMap configurationObjectMap = XMOFUtil.createConfigurationObjectMap(configurationResource,
				modelResource, parameterDefintionResource, primitiveTypeLibraryPath, primitiveBehaviorLibraryPath);
		return configurationObjectMap;
	}

	private Trace execute(String modelPath, String parameterDefinitionPath, boolean cleanup) {
		return execute(modelPath, parameterDefinitionPath, FUML_METAMODEL_PATH,
				fumlConfigurationFile.getAbsolutePath(), cleanup);
	}

	private Trace execute(String modelPath, String parameterDefinitionPath, String metamodelPath,
			String configurationPath, boolean cleanup) {
		setupVM(modelPath, parameterDefinitionPath, metamodelPath, configurationPath);

		vm.run();

		Trace trace = vm.getRawExecutionContext().getTrace(activityExecutionID);
		if (cleanup)
			cleanup();
		return trace;
	}

	private XMOFVirtualMachine setupVM(String modelPath, String parameterDefinitionPath, String metamodelPath,
			String configurationPath) {

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
		ConfigurableStatesBuilder statesBuilder = new ConfigurableStatesBuilder(configurationModelResource, conf);
		statesBuilder.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);

		return vm;
	}

	private Resource loadResource(String filePath) {
		return EMFUtil.loadResource(resourceSet, EMFUtil.createFileURI(filePath));
	}



	static Set<String> allActivities = new HashSet<String>();

	private static void collectAllActivities(String configurationPath) {
		ResourceSet resourceSet = EMFUtil.createResourceSet();
		Resource configuration = EMFUtil.loadResource(resourceSet, EMFUtil.createFileURI(configurationPath));
		for (TreeIterator<EObject> treeIterator = configuration.getAllContents(); treeIterator.hasNext();) {
			EObject eObject = treeIterator.next();
			if (eObject instanceof org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) {
				org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity activity = (org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) eObject;
				allActivities.add(activity.getName());
			}
		}
	}
}
