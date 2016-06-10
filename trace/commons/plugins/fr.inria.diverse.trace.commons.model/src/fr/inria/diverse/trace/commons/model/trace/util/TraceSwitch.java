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
public class TraceSwitch<T> extends Switch<T> {
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TracePackage.MSE_OCCURRENCE: {
				MSEOccurrence mseOccurrence = (MSEOccurrence)theEObject;
				T result = caseMSEOccurrence(mseOccurrence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MSE: {
				MSE mse = (MSE)theEObject;
				T result = caseMSE(mse);
				if (result == null) result = caseENamedElement(mse);
				if (result == null) result = caseEModelElement(mse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MSE_MODEL: {
				MSEModel mseModel = (MSEModel)theEObject;
				T result = caseMSEModel(mseModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_MSE: {
				GenericMSE genericMSE = (GenericMSE)theEObject;
				T result = caseGenericMSE(genericMSE);
				if (result == null) result = caseMSE(genericMSE);
				if (result == null) result = caseENamedElement(genericMSE);
				if (result == null) result = caseEModelElement(genericMSE);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.STEP: {
				Step step = (Step)theEObject;
				T result = caseStep(step);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.BIG_STEP: {
				BigStep<?> bigStep = (BigStep<?>)theEObject;
				T result = caseBigStep(bigStep);
				if (result == null) result = caseStep(bigStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.SMALL_STEP: {
				SmallStep smallStep = (SmallStep)theEObject;
				T result = caseSmallStep(smallStep);
				if (result == null) result = caseStep(smallStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.SEQUENTIAL_STEP: {
				SequentialStep<?> sequentialStep = (SequentialStep<?>)theEObject;
				T result = caseSequentialStep(sequentialStep);
				if (result == null) result = caseBigStep(sequentialStep);
				if (result == null) result = caseStep(sequentialStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.PARALLEL_STEP: {
				ParallelStep<?> parallelStep = (ParallelStep<?>)theEObject;
				T result = caseParallelStep(parallelStep);
				if (result == null) result = caseBigStep(parallelStep);
				if (result == null) result = caseStep(parallelStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_SEQUENTIAL_STEP: {
				GenericSequentialStep genericSequentialStep = (GenericSequentialStep)theEObject;
				T result = caseGenericSequentialStep(genericSequentialStep);
				if (result == null) result = caseSequentialStep(genericSequentialStep);
				if (result == null) result = caseBigStep(genericSequentialStep);
				if (result == null) result = caseStep(genericSequentialStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_PARALLEL_STEP: {
				GenericParallelStep genericParallelStep = (GenericParallelStep)theEObject;
				T result = caseGenericParallelStep(genericParallelStep);
				if (result == null) result = caseParallelStep(genericParallelStep);
				if (result == null) result = caseBigStep(genericParallelStep);
				if (result == null) result = caseStep(genericParallelStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.GENERIC_SMALL_STEP: {
				GenericSmallStep genericSmallStep = (GenericSmallStep)theEObject;
				T result = caseGenericSmallStep(genericSmallStep);
				if (result == null) result = caseSmallStep(genericSmallStep);
				if (result == null) result = caseStep(genericSmallStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.TRACE: {
				Trace<?> trace = (Trace<?>)theEObject;
				T result = caseTrace(trace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LAUNCH_CONFIGURATION: {
				LaunchConfiguration launchConfiguration = (LaunchConfiguration)theEObject;
				T result = caseLaunchConfiguration(launchConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LAUNCH_CONFIGURATION_PARAMETER: {
				LaunchConfigurationParameter launchConfigurationParameter = (LaunchConfigurationParameter)theEObject;
				T result = caseLaunchConfigurationParameter(launchConfigurationParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.LANGUAGE_NAME_PARAMETER: {
				LanguageNameParameter languageNameParameter = (LanguageNameParameter)theEObject;
				T result = caseLanguageNameParameter(languageNameParameter);
				if (result == null) result = caseLaunchConfigurationParameter(languageNameParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MODEL_URI_PARAMETER: {
				ModelURIParameter modelURIParameter = (ModelURIParameter)theEObject;
				T result = caseModelURIParameter(modelURIParameter);
				if (result == null) result = caseLaunchConfigurationParameter(modelURIParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ANIMATOR_URI_PARAMETER: {
				AnimatorURIParameter animatorURIParameter = (AnimatorURIParameter)theEObject;
				T result = caseAnimatorURIParameter(animatorURIParameter);
				if (result == null) result = caseLaunchConfigurationParameter(animatorURIParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ENTRY_POINT_PARAMETER: {
				EntryPointParameter entryPointParameter = (EntryPointParameter)theEObject;
				T result = caseEntryPointParameter(entryPointParameter);
				if (result == null) result = caseLaunchConfigurationParameter(entryPointParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.MODEL_ROOT_PARAMETER: {
				ModelRootParameter modelRootParameter = (ModelRootParameter)theEObject;
				T result = caseModelRootParameter(modelRootParameter);
				if (result == null) result = caseLaunchConfigurationParameter(modelRootParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.INITIALIZATION_METHOD_PARAMETER: {
				InitializationMethodParameter initializationMethodParameter = (InitializationMethodParameter)theEObject;
				T result = caseInitializationMethodParameter(initializationMethodParameter);
				if (result == null) result = caseLaunchConfigurationParameter(initializationMethodParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.INITIALIZATION_ARGUMENTS_PARAMETER: {
				InitializationArgumentsParameter initializationArgumentsParameter = (InitializationArgumentsParameter)theEObject;
				T result = caseInitializationArgumentsParameter(initializationArgumentsParameter);
				if (result == null) result = caseLaunchConfigurationParameter(initializationArgumentsParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TracePackage.ADDON_EXTENSION_PARAMETER: {
				AddonExtensionParameter addonExtensionParameter = (AddonExtensionParameter)theEObject;
				T result = caseAddonExtensionParameter(addonExtensionParameter);
				if (result == null) result = caseLaunchConfigurationParameter(addonExtensionParameter);
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
	public T caseMSEOccurrence(MSEOccurrence object) {
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
	public T caseMSE(MSE object) {
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
	public T caseMSEModel(MSEModel object) {
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
	public T caseGenericMSE(GenericMSE object) {
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
	public T caseStep(Step object) {
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
	public <StepSubtype extends Step> T caseBigStep(BigStep<StepSubtype> object) {
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
	public T caseSmallStep(SmallStep object) {
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
	public <StepSubtype extends Step> T caseSequentialStep(SequentialStep<StepSubtype> object) {
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
	public <StepSubtype extends Step> T caseParallelStep(ParallelStep<StepSubtype> object) {
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
	public T caseGenericSequentialStep(GenericSequentialStep object) {
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
	public T caseGenericParallelStep(GenericParallelStep object) {
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
	public T caseGenericSmallStep(GenericSmallStep object) {
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
	public <StepSubType> T caseTrace(Trace<StepSubType> object) {
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
	public T caseLaunchConfiguration(LaunchConfiguration object) {
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
	public T caseLaunchConfigurationParameter(LaunchConfigurationParameter object) {
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
	public T caseLanguageNameParameter(LanguageNameParameter object) {
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
	public T caseModelURIParameter(ModelURIParameter object) {
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
	public T caseAnimatorURIParameter(AnimatorURIParameter object) {
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
	public T caseEntryPointParameter(EntryPointParameter object) {
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
	public T caseModelRootParameter(ModelRootParameter object) {
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
	public T caseInitializationMethodParameter(InitializationMethodParameter object) {
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
	public T caseInitializationArgumentsParameter(InitializationArgumentsParameter object) {
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
	public T caseAddonExtensionParameter(AddonExtensionParameter object) {
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
	public T caseEModelElement(EModelElement object) {
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
	public T caseENamedElement(ENamedElement object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //TraceSwitch
