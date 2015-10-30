package fr.inria.diverse.tracemm.xmof.statesbuilder.test

import fr.inria.diverse.tracemm.common.ConfigurableStatesBuilder
import fr.inria.diverse.tracemm.common.GenericStatesBuilderConfigurationDynamicEObj
import fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial.ADHiringFactory
import fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial.GatheredHelpers
import java.util.Collection
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.edit.domain.EditingDomain
import org.junit.Before
import org.junit.Test
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue
import org.modelexecution.xmof.configuration.ConfigurationObjectMap
import org.modelexecution.xmof.vm.XMOFVirtualMachine
import org.modelexecution.xmof.vm.util.EMFUtil
import org.modelexecution.xmof.vm.util.XMOFUtil

import static org.junit.Assert.assertNotNull
import org.eclipse.emf.ecore.util.EcoreUtil
import java.io.File
import fr.inria.diverse.tracemm.test.util.EMFCompareUtil

class ADTest {

	private static val File INPUTS_FOLDER = new File("model_inputs/ad")
	private static val File EXPECTED_FOLDER = new File("model_expected")

	private var ResourceSet resourceSet;
	private var EditingDomain editingDomain;

	private def Resource createParameterDefinitionResource(String resourceFilePath, EObject parameterValueDefinition) {
		return EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI(resourceFilePath),
			parameterValueDefinition);
	}

	private def static ConfigurableStatesBuilder createStatesBuilder(XMOFVirtualMachine vm,
		Resource configurationModelResource, Resource traceMMResource, Resource originalMMResource,
		Resource confMMResource, ConfigurationObjectMap configurationObjectMap) {

		val GenericStatesBuilderConfigurationDynamicEObj conf = new GenericStatesBuilderConfigurationDynamicEObj(
			traceMMResource, originalMMResource, confMMResource, configurationObjectMap)
		val ConfigurableStatesBuilder statesBuilder = new ConfigurableStatesBuilder(configurationModelResource, conf);
		statesBuilder.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilder);
		vm.setSynchronizeModel(true);
		return statesBuilder;
	}

	@Before
	public def void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = GatheredHelpers.createTransactionalEditingDomain(resourceSet);
	}

	private def Resource loadModel(String path) {
		val res = resourceSet.createResource(EMFUtil.createFileURI(path))
		res.load(null)
		EcoreUtil.resolveAll(resourceSet) // IMPORTANT
		return res
	}

	@Test
	def void testAD() {
		System.out.println("Loading resources.");

		val Resource originalMMResource = EMFUtil.loadMetamodel(resourceSet,
			EMFUtil.createFileURI(INPUTS_FOLDER + "/activitydiagram.ecore"));
		val Resource traceMMResource = EMFUtil.loadMetamodel(resourceSet,
			EMFUtil.createFileURI(INPUTS_FOLDER + "/activitydiagramtracemm.ecore"));
		val Resource configurationMetamodelResource = EMFUtil.loadResource(resourceSet,
			EMFUtil.createFileURI(INPUTS_FOLDER + "/activitydiagram.xmof"));

		val Resource modelResource = EMFUtil.loadResource(resourceSet,
			EMFUtil.createFileURI(INPUTS_FOLDER + "/hireV1.xmi"));

		val ADHiringFactory factory = new ADHiringFactory(resourceSet);
		val EObject hiringV1pvd_true = factory.createHiringV1ParameterValueDefintion(true);
		val Resource hiringV1res_true = createParameterDefinitionResource("hiringV1_input" + "1", hiringV1pvd_true);

		val Resource paramResource = hiringV1res_true;

		System.out.println("Creating configuration model.");

		val ConfigurationObjectMap configurationMap = GatheredHelpers.
			createConfigurationObjectMap(configurationMetamodelResource, modelResource, paramResource);
		val Collection<EObject> configurationObjects = configurationMap.getConfigurationObjects();
		val Resource configurationModelResource = EMFUtil.createResource(resourceSet, editingDomain,
			EMFUtil.createFileURI("configurationmodel.xmi"), configurationObjects);

		val List<ParameterValue> parameterValueConfiguration = GatheredHelpers.
			getParameterValueConfiguration(paramResource, configurationMap);

		System.out.println("Creating VM.");

		val XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet, editingDomain,
			configurationModelResource, parameterValueConfiguration);
		val ConfigurableStatesBuilder statesBuilder = createStatesBuilder(vm, configurationModelResource,
			traceMMResource, originalMMResource, configurationMetamodelResource, configurationMap);

		System.out.println("Running VM.");

		vm.run();

		val stateSystem = statesBuilder.getConf().getTrace();

		val Resource traceResource = EMFUtil.createResource(resourceSet, editingDomain,
			EMFUtil.createFileURI("tmp/hireV1trace.xmi"), stateSystem)

		traceResource.save(null)

		assertNotNull(stateSystem);

		// Oracle: comparison with expected outputs
		val Resource expectedTraceResource = loadModel(new File(EXPECTED_FOLDER, "hireV1trace.xmi").absolutePath)
		val expectedTrace = expectedTraceResource.contents.get(0)
		EMFCompareUtil.assertEqualsEMF("Generated trace does not match expected", stateSystem, expectedTrace)

	}

}
