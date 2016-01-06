package fr.inria.diverse.tracemm.xmof.statesbuilder.test.admaterial;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ADHiringFactory extends ADInputFactory {

	public static final String HIRING_V1 = "model_inputs/ad/hireV1.xmi";
	public static final String HIRING_V2 = "model/ad/hiring/hireV2.xmi";
	public static final String HIRING_V3 = "model/ad/hiring/hireV3.xmi";
	public static final String HIRING_V4 = "model/ad/hiring/hireV4.xmi";
	
	public ADHiringFactory(ResourceSet resourceSet) {
		super(resourceSet);
	}

	public EObject createHiringV1ParameterValueDefintion(boolean internal) {
		EObject internalInputValue = createHiringV1InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createHiringV1InternalValue(boolean value) {
		EObject internalVariable = getVariable(HIRING_V1, "internal");
		return createBooleanInputValueObject(internalVariable, value);
	}

	public EObject createHiringV2ParameterValueDefintion(boolean internal) {
		EObject internalInputValue = createHiringV2InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(
				internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createHiringV2InternalValue(boolean value) {
		EObject internalVariable = getVariable(HIRING_V2, "internal");
		return createBooleanInputValueObject(internalVariable, value);
	}

	public EObject createHiringV3ParameterValueDefintion(boolean internal) {
		EObject internalInputValue = createHiringV3InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(
				internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createHiringV3InternalValue(boolean value) {
		EObject internalVariable = getVariable(HIRING_V3, "internal");
		return createBooleanInputValueObject(internalVariable, value);
	}

	public EObject createHiringV4ParameterValueDefintion(boolean internal) {
		EObject internalInputValue = createHiringV4InternalValue(internal);
		EObject parameterValueDefinition = createParameterValueDefinition(
				internalInputValue);
		return parameterValueDefinition;
	}

	private EObject createHiringV4InternalValue(boolean value) {
		EObject internalVariable = getVariable(HIRING_V4, "internal");
		return createBooleanInputValueObject(internalVariable, value);
	}

}
