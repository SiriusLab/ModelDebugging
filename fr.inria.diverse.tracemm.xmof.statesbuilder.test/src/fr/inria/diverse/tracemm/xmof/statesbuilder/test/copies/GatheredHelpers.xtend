package fr.inria.diverse.tracemm.xmof.statesbuilder.test.copies

import java.util.ArrayList
import java.util.Collection
import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil.Copier
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.modelexecution.xmof.Semantics.Classes.Kernel.ObjectValue
import org.modelexecution.xmof.Semantics.Classes.Kernel.Value
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueDefinition
import org.modelexecution.xmof.configuration.ConfigurationObjectMap
import org.modelexecution.xmof.vm.util.EMFUtil

class GatheredHelpers {
	
	
	private def static Collection<ParameterValue> getParameterValues(Resource parameterResource) {
		val EList<ParameterValue> parameterValues = new BasicEList<ParameterValue>();
		if (parameterResource != null) {
			for (EObject eObject : parameterResource.getContents()) {
				if (eObject instanceof ParameterValueDefinition) {
					val ParameterValueDefinition parameterValueDefinition = eObject as ParameterValueDefinition;
					parameterValues.addAll(parameterValueDefinition.getParameterValues());
				}
			}
		}
		return parameterValues;
	}
	
		public def static TransactionalEditingDomain createTransactionalEditingDomain(ResourceSet resourceSet) {
		return TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
	}

	private def static Collection<EObject> getParameterValueObjects(Resource parameterResource) {
		val Collection<ParameterValue> parameterValues = getParameterValues(parameterResource);
		val Collection<EObject> parameterValueObjects = new BasicEList<EObject>();
		for (ParameterValue parameterValue : parameterValues) {
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					val ObjectValue objectValue = value as ObjectValue;
					val EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						parameterValueObjects.add(referencedEObject);
					}
				}
			}
		}
		return parameterValueObjects;
	}

	public def static ConfigurationObjectMap createConfigurationObjectMap(Resource configurationMetamodelResource,
		Resource modelResource, Resource parameterResource) {
		val Collection<EObject> parameterValueObjects = getParameterValueObjects(parameterResource);

		val Collection<EObject> inputElements = new ArrayList<EObject>();
		inputElements.addAll(modelResource.getContents());
		inputElements.addAll(parameterValueObjects);

		val Collection<EPackage> configurationPackages = EMFUtil.getEPackages(configurationMetamodelResource);
		val ConfigurationObjectMap configurationMap = new ConfigurationObjectMap(inputElements, configurationPackages);
		return configurationMap;
	}

	public static def List<ParameterValue> getParameterValueConfiguration(Resource parameterResource,
		ConfigurationObjectMap configurationMap) {
		val Collection<ParameterValue> parameterValues = getParameterValues(parameterResource);
		val Copier copier = new Copier(true, false);
		copier.copyAll(parameterValues);
		copier.copyReferences();

		val List<ParameterValue> parameterValueConfiguration = new ArrayList<ParameterValue>();
		for (ParameterValue parameterValue : parameterValues) {
			val ParameterValue parameterValueConf = copier.get(parameterValue) as ParameterValue;
			parameterValueConf.setParameter(parameterValue.getParameter());
			for (Value value : parameterValue.getValues()) {
				if (value instanceof ObjectValue) {
					val ObjectValue objectValue = value as ObjectValue;
					val EObject referencedEObject = objectValue.getEObject();
					if (referencedEObject != null) {
						val EObject referencedEObjectConf = configurationMap.getConfigurationObject(referencedEObject);
						val ObjectValue objectValueConf = copier.get(value) as ObjectValue
						objectValueConf.setEObject(referencedEObjectConf);
					}
				}
			}
			parameterValueConfiguration.add(parameterValueConf);
		}
		return parameterValueConfiguration;
	}
}