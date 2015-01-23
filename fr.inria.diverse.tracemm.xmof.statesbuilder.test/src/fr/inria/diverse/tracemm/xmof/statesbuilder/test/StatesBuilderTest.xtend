package fr.inria.diverse.tracemm.xmof.statesbuilder.test

import fr.inria.diverse.tracemm.common.ConfigurableStatesBuilder
import fr.inria.diverse.tracemm.common.GenericStatesBuilderConfigurationDynamicEObj
import fr.inria.diverse.tracemm.common.SpecificStatesBuilderConfiguration
import fr.inria.diverse.tracemm.xmof.statesbuilder.test.copies.ADHiringFactory
import fr.inria.diverse.tracemm.xmof.statesbuilder.test.copies.GatheredHelpers
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

class StatesBuilderTest {

	private var ResourceSet resourceSet;
	private var EditingDomain editingDomain;

	protected def Resource createParameterDefinitionResource(String resourceFilePath, EObject parameterValueDefinition) {
		return EMFUtil.createResource(resourceSet, editingDomain, EMFUtil.createFileURI(resourceFilePath),
			parameterValueDefinition);
	}

	public def static ConfigurableStatesBuilder createStatesBuilder(XMOFVirtualMachine vm,
		Resource configurationModelResource, Resource traceMMResource, Resource originalMMResource,
		Resource confMMResource, ConfigurationObjectMap configurationObjectMap) {

		val SpecificStatesBuilderConfiguration conf = new GenericStatesBuilderConfigurationDynamicEObj(traceMMResource,
			originalMMResource, confMMResource, configurationObjectMap)
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

	@Test
	def void test() {
		System.out.println("Loading resources.");

		val Resource originalMMResource = EMFUtil.loadMetamodel(resourceSet, EMFUtil.createFileURI("model_inputs/activitydiagram.ecore"));
		val Resource traceMMResource = EMFUtil.loadMetamodel(resourceSet, EMFUtil.createFileURI("model_inputs/activitydiagramtracemm.ecore"));
		val Resource configurationMetamodelResource = EMFUtil.loadResource(resourceSet,
			EMFUtil.createFileURI("model_inputs/activitydiagram.xmof"));
			
		val Resource modelResource = EMFUtil.loadResource(resourceSet,
			EMFUtil.createFileURI("model_inputs/hireV1.xmi"));

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
		val ConfigurableStatesBuilder statesBuilder = createStatesBuilder(vm, configurationModelResource,traceMMResource,
			originalMMResource,configurationMetamodelResource,configurationMap);

		System.out.println("Running VM."); 


		vm.run();
 
		val stateSystem = statesBuilder.getConf().getStateSystem();
		assertNotNull(stateSystem);
	}

}
