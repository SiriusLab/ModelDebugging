/**
 */
package fr.inria.diverse.trace.commons.model.trace.util;

import fr.inria.diverse.trace.commons.model.trace.*;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage
 * @generated
 */
public class TraceSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceSwitch() {
		if (modelPackage == null) {
			modelPackage = TracePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TracePackage.MSE_OCCURRENCE: {
				MSEOccurrence mseOccurrence = (MSEOccurrence)theEObject;
				T1 result = caseMSEOccurrence(mseOccurrence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MSE: {
				MSE mse = (MSE)theEObject;
				T1 result = caseMSE(mse);
				if (result == null) result = caseENamedElement(mse);
				if (result == null) result = caseEModelElement(mse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MSE_MODEL: {
				MSEModel mseModel = (MSEModel)theEObject;
				T1 result = caseMSEModel(mseModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_MSE: {
				GenericMSE genericMSE = (GenericMSE)theEObject;
				T1 result = caseGenericMSE(genericMSE);
				if (result == null) result = caseMSE(genericMSE);
				if (result == null) result = caseENamedElement(genericMSE);
				if (result == null) result = caseEModelElement(genericMSE);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.STEP: {
				Step<?> step = (Step<?>)theEObject;
				T1 result = caseStep(step);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.BIG_STEP: {
				BigStep<?, ?> bigStep = (BigStep<?, ?>)theEObject;
				T1 result = caseBigStep(bigStep);
				if (result == null) result = caseStep(bigStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.SMALL_STEP: {
				SmallStep<?> smallStep = (SmallStep<?>)theEObject;
				T1 result = caseSmallStep(smallStep);
				if (result == null) result = caseStep(smallStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.SEQUENTIAL_STEP: {
				SequentialStep<?, ?> sequentialStep = (SequentialStep<?, ?>)theEObject;
				T1 result = caseSequentialStep(sequentialStep);
				if (result == null) result = caseBigStep(sequentialStep);
				if (result == null) result = caseStep(sequentialStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.PARALLEL_STEP: {
				ParallelStep<?, ?> parallelStep = (ParallelStep<?, ?>)theEObject;
				T1 result = caseParallelStep(parallelStep);
				if (result == null) result = caseBigStep(parallelStep);
				if (result == null) result = caseStep(parallelStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_SEQUENTIAL_STEP: {
				GenericSequentialStep genericSequentialStep = (GenericSequentialStep)theEObject;
				T1 result = caseGenericSequentialStep(genericSequentialStep);
				if (result == null) result = caseSequentialStep(genericSequentialStep);
				if (result == null) result = caseGenericStep(genericSequentialStep);
				if (result == null) result = caseBigStep(genericSequentialStep);
				if (result == null) result = caseStep(genericSequentialStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_PARALLEL_STEP: {
				GenericParallelStep genericParallelStep = (GenericParallelStep)theEObject;
				T1 result = caseGenericParallelStep(genericParallelStep);
				if (result == null) result = caseParallelStep(genericParallelStep);
				if (result == null) result = caseGenericStep(genericParallelStep);
				if (result == null) result = caseBigStep(genericParallelStep);
				if (result == null) result = caseStep(genericParallelStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_SMALL_STEP: {
				GenericSmallStep genericSmallStep = (GenericSmallStep)theEObject;
				T1 result = caseGenericSmallStep(genericSmallStep);
				if (result == null) result = caseSmallStep(genericSmallStep);
				if (result == null) result = caseGenericStep(genericSmallStep);
				if (result == null) result = caseStep(genericSmallStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.TRACE: {
				Trace<?, ?, ?> trace = (Trace<?, ?, ?>)theEObject;
				T1 result = caseTrace(trace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.TRACED_OBJECT: {
				TracedObject<?> tracedObject = (TracedObject<?>)theEObject;
				T1 result = caseTracedObject(tracedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.DIMENSION: {
				Dimension<?> dimension = (Dimension<?>)theEObject;
				T1 result = caseDimension(dimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.VALUE: {
				Value<?> value = (Value<?>)theEObject;
				T1 result = caseValue(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.STATE: {
				State<?, ?> state = (State<?, ?>)theEObject;
				T1 result = caseState(state);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_REFERENCE_VALUE: {
				GenericReferenceValue genericReferenceValue = (GenericReferenceValue)theEObject;
				T1 result = caseGenericReferenceValue(genericReferenceValue);
				if (result == null) result = caseGenericValue(genericReferenceValue);
				if (result == null) result = caseValue(genericReferenceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_DIMENSION: {
				GenericDimension genericDimension = (GenericDimension)theEObject;
				T1 result = caseGenericDimension(genericDimension);
				if (result == null) result = caseDimension(genericDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_TRACED_OBJECT: {
				GenericTracedObject<?> genericTracedObject = (GenericTracedObject<?>)theEObject;
				T1 result = caseGenericTracedObject(genericTracedObject);
				if (result == null) result = caseTracedObject(genericTracedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_STATE: {
				GenericState genericState = (GenericState)theEObject;
				T1 result = caseGenericState(genericState);
				if (result == null) result = caseState(genericState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_TRACE: {
				GenericTrace<?> genericTrace = (GenericTrace<?>)theEObject;
				T1 result = caseGenericTrace(genericTrace);
				if (result == null) result = caseTrace(genericTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_ATTRIBUTE_VALUE: {
				GenericAttributeValue genericAttributeValue = (GenericAttributeValue)theEObject;
				T1 result = caseGenericAttributeValue(genericAttributeValue);
				if (result == null) result = caseGenericValue(genericAttributeValue);
				if (result == null) result = caseValue(genericAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.BOOLEAN_ATTRIBUTE_VALUE: {
				BooleanAttributeValue booleanAttributeValue = (BooleanAttributeValue)theEObject;
				T1 result = caseBooleanAttributeValue(booleanAttributeValue);
				if (result == null) result = caseGenericAttributeValue(booleanAttributeValue);
				if (result == null) result = caseGenericValue(booleanAttributeValue);
				if (result == null) result = caseValue(booleanAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.INTEGER_ATTRIBUTEVALUE: {
				IntegerAttributevalue integerAttributevalue = (IntegerAttributevalue)theEObject;
				T1 result = caseIntegerAttributevalue(integerAttributevalue);
				if (result == null) result = caseGenericAttributeValue(integerAttributevalue);
				if (result == null) result = caseGenericValue(integerAttributevalue);
				if (result == null) result = caseValue(integerAttributevalue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.STRING_ATTRIBUTE_VALUE: {
				StringAttributeValue stringAttributeValue = (StringAttributeValue)theEObject;
				T1 result = caseStringAttributeValue(stringAttributeValue);
				if (result == null) result = caseGenericAttributeValue(stringAttributeValue);
				if (result == null) result = caseGenericValue(stringAttributeValue);
				if (result == null) result = caseValue(stringAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LAUNCH_CONFIGURATION: {
				LaunchConfiguration launchConfiguration = (LaunchConfiguration)theEObject;
				T1 result = caseLaunchConfiguration(launchConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LAUNCH_CONFIGURATION_PARAMETER: {
				LaunchConfigurationParameter launchConfigurationParameter = (LaunchConfigurationParameter)theEObject;
				T1 result = caseLaunchConfigurationParameter(launchConfigurationParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LANGUAGE_NAME_PARAMETER: {
				LanguageNameParameter languageNameParameter = (LanguageNameParameter)theEObject;
				T1 result = caseLanguageNameParameter(languageNameParameter);
				if (result == null) result = caseLaunchConfigurationParameter(languageNameParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ADDON_EXTENSION_PARAMETER: {
				AddonExtensionParameter addonExtensionParameter = (AddonExtensionParameter)theEObject;
				T1 result = caseAddonExtensionParameter(addonExtensionParameter);
				if (result == null) result = caseLaunchConfigurationParameter(addonExtensionParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MODEL_URI_PARAMETER: {
				ModelURIParameter modelURIParameter = (ModelURIParameter)theEObject;
				T1 result = caseModelURIParameter(modelURIParameter);
				if (result == null) result = caseLaunchConfigurationParameter(modelURIParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ANIMATOR_URI_PARAMETER: {
				AnimatorURIParameter animatorURIParameter = (AnimatorURIParameter)theEObject;
				T1 result = caseAnimatorURIParameter(animatorURIParameter);
				if (result == null) result = caseLaunchConfigurationParameter(animatorURIParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ENTRY_POINT_PARAMETER: {
				EntryPointParameter entryPointParameter = (EntryPointParameter)theEObject;
				T1 result = caseEntryPointParameter(entryPointParameter);
				if (result == null) result = caseLaunchConfigurationParameter(entryPointParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.INITIALIZATION_ARGUMENTS_PARAMETER: {
				InitializationArgumentsParameter initializationArgumentsParameter = (InitializationArgumentsParameter)theEObject;
				T1 result = caseInitializationArgumentsParameter(initializationArgumentsParameter);
				if (result == null) result = caseLaunchConfigurationParameter(initializationArgumentsParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MODEL_ROOT_PARAMETER: {
				ModelRootParameter modelRootParameter = (ModelRootParameter)theEObject;
				T1 result = caseModelRootParameter(modelRootParameter);
				if (result == null) result = caseLaunchConfigurationParameter(modelRootParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.INITIALIZATION_METHOD_PARAMETER: {
				InitializationMethodParameter initializationMethodParameter = (InitializationMethodParameter)theEObject;
				T1 result = caseInitializationMethodParameter(initializationMethodParameter);
				if (result == null) result = caseLaunchConfigurationParameter(initializationMethodParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_STEP: {
				GenericStep genericStep = (GenericStep)theEObject;
				T1 result = caseGenericStep(genericStep);
				if (result == null) result = caseStep(genericStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_VALUE: {
				GenericValue genericValue = (GenericValue)theEObject;
				T1 result = caseGenericValue(genericValue);
				if (result == null) result = caseValue(genericValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MSE Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MSE Occurrence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMSEOccurrence(MSEOccurrence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MSE</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MSE</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMSE(MSE object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MSE Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MSE Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMSEModel(MSEModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic MSE</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic MSE</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericMSE(GenericMSE object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StateSubType extends State<?, ?>> T1 caseStep(Step<StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Big Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Big Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubtype extends Step<StateSubType>, StateSubType extends State<?, ?>> T1 caseBigStep(BigStep<StepSubtype, StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Small Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Small Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StateSubType extends State<?, ?>> T1 caseSmallStep(SmallStep<StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequential Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequential Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubtype extends Step<StateSubType>, StateSubType extends State<?, ?>> T1 caseSequentialStep(SequentialStep<StepSubtype, StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parallel Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parallel Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubtype extends Step<StateSubType>, StateSubType extends State<StepSubtype, ?>> T1 caseParallelStep(ParallelStep<StepSubtype, StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Sequential Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Sequential Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericSequentialStep(GenericSequentialStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Parallel Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Parallel Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericParallelStep(GenericParallelStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Small Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Small Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericSmallStep(GenericSmallStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubType extends Step<?>, TracedObjectSubtype extends TracedObject<?>, StateSubType extends State<?, ?>> T1 caseTrace(Trace<StepSubType, TracedObjectSubtype, StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Traced Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Traced Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <DimensionSubType extends Dimension<?>> T1 caseTracedObject(TracedObject<DimensionSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <ValueSubType extends Value<?>> T1 caseDimension(Dimension<ValueSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StateSubType extends State<?, ?>> T1 caseValue(Value<StateSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubType extends Step<?>, ValueSubType extends Value<?>> T1 caseState(State<StepSubType, ValueSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Reference Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Reference Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericReferenceValue(GenericReferenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericDimension(GenericDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Traced Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Traced Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends EObject> T1 caseGenericTracedObject(GenericTracedObject<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericState(GenericState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <StepSubType extends GenericStep> T1 caseGenericTrace(GenericTrace<StepSubType> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericAttributeValue(GenericAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBooleanAttributeValue(BooleanAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Attributevalue</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Attributevalue</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIntegerAttributevalue(IntegerAttributevalue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringAttributeValue(StringAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Launch Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Launch Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLaunchConfiguration(LaunchConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Launch Configuration Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Launch Configuration Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLaunchConfigurationParameter(LaunchConfigurationParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Language Name Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Language Name Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLanguageNameParameter(LanguageNameParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Addon Extension Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Addon Extension Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAddonExtensionParameter(AddonExtensionParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model URI Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model URI Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelURIParameter(ModelURIParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Animator URI Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Animator URI Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAnimatorURIParameter(AnimatorURIParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry Point Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry Point Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEntryPointParameter(EntryPointParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initialization Arguments Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initialization Arguments Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInitializationArgumentsParameter(InitializationArgumentsParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Root Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Root Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelRootParameter(ModelRootParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initialization Method Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initialization Method Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInitializationMethodParameter(InitializationMethodParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericStep(GenericStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGenericValue(GenericValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseENamedElement(ENamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //TraceSwitch
