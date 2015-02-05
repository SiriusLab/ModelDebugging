package fr.inria.diverse.tracemm.semdiff.eval.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Assert;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.builder.util.StatesBuilderUtil;
import org.modelexecution.xmof.states.states.StateSystem;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

public class ModelExecutor {

	private static final String CONFIGURATIONMODEL_PATH = "configurationmodel.xmi";
	
	private ResourceSet resourceSet;
	private TransactionalEditingDomain editingDomain;

	private Resource modelResource;
	private Resource configurationResource;
	private Resource parameterDefintionResource;
	private Resource configurationModelResource;
	
	public ModelExecutor() {
		setupResourceSet();
	} 
	
	private void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}
	
	public void execute(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath, String tracemodelPath, String... additionalModelInputPaths) {
		XMOFVirtualMachine vm = setupVM(modelPath, parameterDefinitionPath, metamodelPath, configurationPath, additionalModelInputPaths);
		StatesBuilder statesBuilder = StatesBuilderUtil.createStatesBuilder(vm,
				configurationModelResource);
		vm.execute();
		StateSystem stateSystem = statesBuilder.getStateSystem();
		persistTracemodel(tracemodelPath, stateSystem);
	}

	private XMOFVirtualMachine setupVM(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath, String... additionalModelInputPaths) {
		if(metamodelPath != null)
			EMFUtil.loadMetamodel(resourceSet, EMFUtil.createFileURI(metamodelPath));

		modelResource = Util.loadResource(resourceSet, modelPath);

		configurationResource = Util.loadResource(resourceSet, configurationPath);

		parameterDefintionResource = Util.loadResource(resourceSet, parameterDefinitionPath);

		List<Resource> additionalModelInputResources = new ArrayList<Resource>();
		for(String additionalModelInputPath : additionalModelInputPaths) {
			additionalModelInputResources.add(Util.loadResource(resourceSet, additionalModelInputPath));
		}
			
		
		EcoreUtil.resolveAll(resourceSet);
		
		XMOFVirtualMachine vm = createVM(modelResource, configurationResource,
				parameterDefintionResource, additionalModelInputResources.toArray(new Resource[additionalModelInputResources.size()]));
		
		return vm;
	}
	
	private XMOFVirtualMachine createVM(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource, Resource... additionalModelInputResources) {
		ConfigurationObjectMap configurationObjectMap = createConfigurationObjectMap(modelResource,
				configurationResource, parameterDefintionResource, additionalModelInputResources);

		configurationModelResource = EMFUtil.createResource(
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
	
	ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource,
			Resource configurationResource, Resource parameterDefintionResource, Resource... additionalModelInputResources) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationResource,
						modelResource, parameterDefintionResource, additionalModelInputResources);
		return configurationObjectMap;
	}
	
	private void persistTracemodel(String tracemodelPath, StateSystem stateSystem) {
		URI outputUri = EMFUtil.createFileURI(tracemodelPath);
		Resource traceResource = resourceSet.createResource(outputUri);
		
		Command cmd = new AddCommand(editingDomain, traceResource.getContents(), stateSystem);
		editingDomain.getCommandStack().execute(cmd);
		
//		fixMissingContainment(traceResource);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		//TODO what is the matter with hrefs?
		options.put(XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		try {
			traceResource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}

	@SuppressWarnings("unused")
	private void fixMissingContainment(Resource traceResource) {
		// TODO: Should that happen at all?
		EList<EObject> eObjectsWithoutContainer;
		do {
			eObjectsWithoutContainer = new BasicEList<EObject>();
			for (TreeIterator<EObject> eAllContents = traceResource
					.getAllContents(); eAllContents.hasNext();) {
				EObject eObject = eAllContents.next();
				for (EObject referencedEObject : eObject.eCrossReferences()) {
//					if (referencedEObject.eContainer() == null
//							|| referencedEObject.eResource() != traceResource)
					if (referencedEObject.eResource() == null)
						eObjectsWithoutContainer.add(referencedEObject);
				}
			}
			Command cmd = new AddCommand(editingDomain,
					traceResource.getContents(), eObjectsWithoutContainer);
			editingDomain.getCommandStack().execute(cmd);
		} while (eObjectsWithoutContainer.size() > 0);
	}
		
}
