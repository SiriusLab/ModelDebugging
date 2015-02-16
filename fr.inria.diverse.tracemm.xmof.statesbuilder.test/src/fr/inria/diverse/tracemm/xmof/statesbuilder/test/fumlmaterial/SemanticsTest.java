package fr.inria.diverse.tracemm.xmof.statesbuilder.test.fumlmaterial;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.vm.IXMOFVirtualMachineListener;
import org.modelexecution.xmof.vm.XMOFInstanceMap;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent;
import org.modelexecution.xmof.vm.XMOFVirtualMachineEvent.Type;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import fUML.Semantics.Loci.LociL1.Locus;

public abstract class SemanticsTest implements ExecutionEventListener, IXMOFVirtualMachineListener {

	private static final String CONFIGURATIONMODEL_PATH = "configurationmodel.xmi";

	private ResourceSet resourceSet;
	private EditingDomain editingDomain;
	private XMOFVirtualMachine vm;
	private ConfigurationObjectMap configurationObjectMap;

	Trace execute(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath) {
		return execute(modelPath, parameterDefinitionPath, metamodelPath,
				configurationPath, true);
	}

	Trace execute(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath, boolean cleanup) {
		setupVM(modelPath, parameterDefinitionPath, metamodelPath,
				configurationPath);
		vm.addRawExecutionEventListener(this);
		vm.addVirtualMachineListener(this);
		vm.run();
		vm.removeRawExecutionEventListener(this);
		vm.removeVirtualMachineListener(this);

		Trace trace = vm.getRawExecutionContext().getTrace(activityExecutionID);
		if (cleanup)
			cleanup();
		return trace;
	}

	XMOFVirtualMachine setupVM(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath) {
		loadMetamodel(metamodelPath);

		Resource modelResource = loadResource(modelPath);

		Resource configurationResource = loadResource(configurationPath);

		Resource parameterDefintionResource = loadResource(parameterDefinitionPath);

		vm = createVM(modelResource, configurationResource,
				parameterDefintionResource);
		return vm;
	}

	void cleanup() {
		vm.getRawExecutionContext().reset();
	}

	XMOFVirtualMachine getXMOFVirtualMachine() {
		return vm;
	}

	XMOFInstanceMap getInstanceMap() {
		XMOFVirtualMachine vm = getXMOFVirtualMachine();
		return vm.getInstanceMap();
	}

	Locus getLocus() {
		XMOFVirtualMachine vm = getXMOFVirtualMachine();
		Locus locus = vm.getRawExecutionContext().getLocus();
		return locus;
	}

	@Before
	public void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	ResourceSet getResourceSet() {
		return resourceSet;
	}

	private XMOFVirtualMachine createVM(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		configurationObjectMap = createConfigurationObjectMap(modelResource,
				configurationResource, parameterDefintionResource);

		Resource configurationModelResource = EMFUtil.createResource(
				resourceSet, editingDomain,
				EMFUtil.createFileURI(CONFIGURATIONMODEL_PATH),
				configurationObjectMap.getConfigurationObjects());

		List<ParameterValue> parameterValueConfiguration = XMOFUtil
				.getParameterValueConfiguration(parameterDefintionResource,
						configurationObjectMap);

		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet,
				editingDomain, configurationModelResource,
				parameterValueConfiguration);
		return vm;
	}

	ConfigurationObjectMap getConfigurationObjectMap() {
		return configurationObjectMap;
	}

	ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationResource,
						modelResource, parameterDefintionResource);
		return configurationObjectMap;
	}

	void loadMetamodel(String metamodelPath) {
		EMFUtil.loadMetamodel(resourceSet, EMFUtil.createFileURI(metamodelPath));
	}

	Resource loadResource(String filePath) {
		return EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI(filePath));
	}

	@SuppressWarnings("unused")
	private int nodeCounter = 0;
	private int activityExecutionID = -1;
	private static Set<String> executedActivities = new HashSet<String>();

	@Override
	public void notify(Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			++nodeCounter;
		}
		if (activityExecutionID == -1 && event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			activityExecutionID = activityEntryEvent.getActivityExecutionID();
		}

		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			executedActivities.add(activityEntryEvent.getActivity().name);
		}
		// if (event instanceof ActivityEntryEvent) {
		// ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
		// debugPrint(activityEntryEvent);
		// }
	}
	
	@Override
	public void notify(XMOFVirtualMachineEvent event) {
		if(event.getType() == Type.ERROR) {
			event.getException().printStackTrace();
			Assert.fail("XMOF VM threw exception");
		}
	}

	@After
	public void reset() {
		activityExecutionID = -1;
		// System.out.println("executed nodes: " + nodeCounter);
		nodeCounter = 0;
	}

	protected ActivityExecution getActivityExecution(Trace trace, String activityName) {
		Set<ActivityExecution> activityExecutions = getActivityExecutions(trace,
				activityName);
		if(activityExecutions.size() != 1)
			return null;
		return activityExecutions.iterator().next();
	}

	protected Set<ActivityExecution> getActivityExecutions(Trace trace, String activityName) {
		Set<ActivityExecution> activityExecutions = new HashSet<ActivityExecution>(); 
		for(ActivityExecution activityExecution : trace.getActivityExecutions()) {
			if(activityExecution.getActivity().name.equals(activityName)) {
				activityExecutions.add(activityExecution);
			}
		}
		return activityExecutions;
	}

	@AfterClass
	public static void printConfigurationActivityCoverage() {
		System.out.println(executedActivities.size() + "/"
				+ allActivities.size() + " executed");
		System.out.println("not executed:");
		Set<String> notExecutedActivities = new HashSet<String>(allActivities);
		notExecutedActivities.removeAll(executedActivities);
		for (String notExecutedActivity : notExecutedActivities) {
			System.out.println(notExecutedActivity.toString());
		}
	}

	static Set<String> allActivities = new HashSet<String>();

	@BeforeClass
	public static void turnOffLogging() {
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	public static void collectAllActivities(String configurationPath) {
		ResourceSet resourceSet = EMFUtil.createResourceSet();
		Resource configuration = EMFUtil.loadResource(resourceSet,
				EMFUtil.createFileURI(configurationPath));
		for (TreeIterator<EObject> treeIterator = configuration
				.getAllContents(); treeIterator.hasNext();) {
			EObject eObject = treeIterator.next();
			if (eObject instanceof org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) {
				org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity activity = (org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity) eObject;
				allActivities.add(activity.getName());
			}
		}
	}
}
