package fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial;

import java.util.Arrays;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelexecution.xmof.Semantics.Classes.Kernel.KernelPackage;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage;
import org.modelexecution.xmof.vm.util.EMFUtil;

public abstract class InputFactory {

	private static final BasicBehaviorsPackage SEMANTICS_BASIC_BEHAVIORS_PACKAGE = BasicBehaviorsPackage.eINSTANCE;
	private static final org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage SYNTAX_BASIC_BEHAVIORS_PACKAGE = org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.BasicBehaviorsPackage.eINSTANCE;
	private static final KernelPackage SEMANTICS_KERNEL_PACKAGE = KernelPackage.eINSTANCE;
	private static final EcorePackage ECORE = EcorePackage.eINSTANCE;
	
	protected ResourceSet resourceSet;
	
	protected InputFactory(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	protected EClass getConfigurationClass(String configurationFilePath, String name) {
		EPackage configurationRootEPackage = getConfigurationRootPackage(configurationFilePath);
		EClass configurationEClass = (EClass)configurationRootEPackage.getEClassifier(name);
		return configurationEClass;
	}

	private EPackage getConfigurationRootPackage(String configurationFilePath) {
		Resource configurationResource = EMFUtil.loadResource(resourceSet, EMFUtil.createFileURI(configurationFilePath));
		EPackage configurationRootEPackage = EMFUtil.getRootEPackage(configurationResource);
		return configurationRootEPackage;
	}
	
	protected EClass getMetamodelClass(String metamodelFilePath, String name) {
		EPackage metamodelRootPackage = getMetamodelRootPackage(metamodelFilePath);
		EClass metamodelClass = (EClass)metamodelRootPackage.getEClassifier(name);
		return metamodelClass;
	}
	
	private EPackage getMetamodelRootPackage(String metamodelFilePath) {
		Resource metamodelResource = EMFUtil.loadResource(resourceSet, EMFUtil.createFileURI(metamodelFilePath));
		EPackage metamodelRootEPackage = EMFUtil.getRootEPackage(metamodelResource);
		return metamodelRootEPackage;
	}

	protected EObject getParameter(String configurationFilePath, String className, String activityName, String parameterName) {
		EClass configurationClass = getConfigurationClass(configurationFilePath, className);
		@SuppressWarnings("unchecked")
		EList<EObject> ownedBehaviors = (EList<EObject>)configurationClass.eGet(SYNTAX_BASIC_BEHAVIORS_PACKAGE.getBehavioredClassifier_OwnedBehavior());
		for(EObject ownedBehavior : ownedBehaviors) {
			if(ownedBehavior.eGet(ECORE.getENamedElement_Name()).equals(activityName)) {
				@SuppressWarnings("unchecked")
				EList<EObject> ownedParameters = (EList<EObject>)ownedBehavior.eGet(SYNTAX_BASIC_BEHAVIORS_PACKAGE.getBehavior_OwnedParameter());
				for (EObject ownedParameter : ownedParameters) {
					if(ownedParameter.eGet(ECORE.getENamedElement_Name()).equals(parameterName)) {
						return ownedParameter;
					}
				}
			}
		}
		return null;
	}
	
	protected EObject createParameterValueDefinition(EObject... parameterValues) {
		EObject parameterValueDefinition = EcoreUtil.create(SEMANTICS_BASIC_BEHAVIORS_PACKAGE.getParameterValueDefinition());		
		EList<EObject> parameterValuesAsList = new BasicEList<EObject>(Arrays.asList(parameterValues));
		parameterValueDefinition.eSet(SEMANTICS_BASIC_BEHAVIORS_PACKAGE.getParameterValueDefinition_ParameterValues(), parameterValuesAsList);
		return parameterValueDefinition;
	}
	
	protected EObject createParameterValue(EObject parameter, EObject... values) {
		EObject parameterValue = EcoreUtil.create(SEMANTICS_BASIC_BEHAVIORS_PACKAGE.getParameterValue());
		parameterValue.eSet(SEMANTICS_BASIC_BEHAVIORS_PACKAGE.getParameterValue_Parameter(), parameter);
		EList<EObject> valuesAsList = new BasicEList<EObject>(Arrays.asList(values));
		parameterValue.eSet(SEMANTICS_BASIC_BEHAVIORS_PACKAGE.getParameterValue_Values(), valuesAsList);
		return parameterValue;
	}
	
	protected EObject createObjectValue(EObject eObject) {
		EObject objectValue = EcoreUtil.create(SEMANTICS_KERNEL_PACKAGE.getObjectValue());
		objectValue.eSet(SEMANTICS_KERNEL_PACKAGE.getObjectValue_EObject(), eObject);
		return objectValue;
	}
	
}
