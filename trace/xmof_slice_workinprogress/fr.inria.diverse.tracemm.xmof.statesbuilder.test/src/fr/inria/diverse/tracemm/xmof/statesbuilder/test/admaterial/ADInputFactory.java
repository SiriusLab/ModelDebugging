package fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelexecution.xmof.vm.util.EMFUtil;

public abstract class ADInputFactory extends InputFactory {

	private static final String ACTIVITYCONFIGURATION = "ActivityConfiguration";
	private static final String ACTIVITYCONFIGURATION_MAIN = "main";
	private static final String ACTIVITYCONFIGURATION_MAIN_INPUTVALUES = "inputValues";
	
	private static final String BOOLEANVALUECONFIGURATION = "BooleanValueConfiguration";
	private static final String BOOLEANVALUE_VALUE = "value";
	
	private static final String INTEGERVALUECONFIGURATION = "IntegerValueConfiguration";
	private static final String INTEGERVALUE_VALUE = "value";
	
	private static final String INPUTVALUE = "InputValue";
	private static final String INPUTVALUE_VARIABLE = "variable";
	private static final String INPUTVALUE_VALUE = "value";
	
	private static final String INPUT = "Input";
	private static final String INPUT_INPUTVALUES = ACTIVITYCONFIGURATION_MAIN_INPUTVALUES;
	
	private static final String VARIABLE = "Variable";
	private static final String VARIABLE_NAME = "name";
	
	public static final String ACTIVITYDIAGRAM_XMOF_PATH = "model_inputs/ad/activitydiagram.xmof";
	public static final String ACTIVITYDIAGRAM_METAMODEL_PATH = "model_inputs/ad/activitydiagram.ecore";
	
	public ADInputFactory(ResourceSet resourceSet) {
		super(resourceSet);
	}

	protected EObject createBooleanValueConfEObject(boolean value) {
		EClass booleanValueConfEClass = getConfigurationClass(ACTIVITYDIAGRAM_XMOF_PATH, BOOLEANVALUECONFIGURATION);
		EObject booleanValueConfEObject = EcoreUtil.create(booleanValueConfEClass);
		booleanValueConfEObject.eSet(booleanValueConfEClass.getEStructuralFeature(BOOLEANVALUE_VALUE), value);
		return booleanValueConfEObject;
	}
	
	protected EObject createIntegerValueConfEObject(int value) {
		EClass integerValueConfEClass = getConfigurationClass(ACTIVITYDIAGRAM_XMOF_PATH, INTEGERVALUECONFIGURATION);
		EObject integerValueConfEObject = EcoreUtil.create(integerValueConfEClass);
		integerValueConfEObject.eSet(integerValueConfEClass.getEStructuralFeature(INTEGERVALUE_VALUE), value);
		return integerValueConfEObject;
	}
	
	protected EObject createInputObject(EObject... inputValues) {
		EClass inputEClass = getConfigurationClass(ACTIVITYDIAGRAM_XMOF_PATH, INPUT);
		EObject inputEObject = EcoreUtil.create(inputEClass);
		
		EList<EObject> inputValueEObjects = new BasicEList<EObject>(Arrays.asList(inputValues));
		inputEObject.eSet(inputEClass.getEStructuralFeature(INPUT_INPUTVALUES), inputValueEObjects);
		return inputEObject;
	}
	
	protected EObject createInputValueObject(EObject variable, EObject value) {
		EClass inputValueEClass = getConfigurationClass(ACTIVITYDIAGRAM_XMOF_PATH, INPUTVALUE);
		EObject inputValueEObject = EcoreUtil.create(inputValueEClass);
		inputValueEObject.eSet(inputValueEClass.getEStructuralFeature(INPUTVALUE_VALUE), value);
		inputValueEObject.eSet(inputValueEClass.getEStructuralFeature(INPUTVALUE_VARIABLE), variable);
		return inputValueEObject;
	}

	protected EObject getVariable(String modelPath, String name) {
		EClass variableEClass = getMetamodelClass(ACTIVITYDIAGRAM_METAMODEL_PATH, VARIABLE);
		Resource modelResource = EMFUtil.loadResource(resourceSet, EMFUtil.createFileURI(modelPath));
		for(TreeIterator<EObject> modelContents = modelResource.getAllContents();modelContents.hasNext();) {
			EObject eObject = modelContents.next();
			if(variableEClass.isInstance(eObject)) {
				if(eObject.eGet(variableEClass.getEStructuralFeature(VARIABLE_NAME)).equals(name))
					return eObject;
			}
		}
		return null;
	}
	
	protected EObject createParameterValueDefinition(EObject... inputValues) {
		List<EObject> objectValues = new ArrayList<EObject>();
		for (EObject inputValue : inputValues) {
			EObject objectValue = createObjectValue(inputValue);
			objectValues.add(objectValue);
		}		
		
		EObject [] objectValuesAsArray = objectValues.toArray(new EObject[objectValues.size()]);				
		EObject parameterValue = createParameterValue(getParameter(ACTIVITYDIAGRAM_XMOF_PATH, ACTIVITYCONFIGURATION, ACTIVITYCONFIGURATION_MAIN, ACTIVITYCONFIGURATION_MAIN_INPUTVALUES), objectValuesAsArray);
		EObject parameterValueDefinition = super.createParameterValueDefinition(parameterValue);
		return parameterValueDefinition;
	}
	
	protected EObject createBooleanInputValueObject(EObject variable,
			boolean value) {
		EObject booleanValue = createBooleanValueConfEObject(value);
		EObject inputValue = createInputValueObject(variable, booleanValue);
		return inputValue;
	}
	
	private EObject createIntegerInputValueObject(EObject variable,
			int value) {
		EObject booleanValue = createIntegerValueConfEObject(value);
		EObject inputValue = createInputValueObject(variable, booleanValue);
		return inputValue;
	}
}
