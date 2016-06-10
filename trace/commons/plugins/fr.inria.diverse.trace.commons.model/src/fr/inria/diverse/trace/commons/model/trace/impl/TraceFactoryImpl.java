/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraceFactoryImpl extends EFactoryImpl implements TraceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TraceFactory init() {
		try {
			TraceFactory theTraceFactory = (TraceFactory)EPackage.Registry.INSTANCE.getEFactory(TracePackage.eNS_URI);
			if (theTraceFactory != null) {
				return theTraceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TraceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TracePackage.MSE_OCCURRENCE: return createMSEOccurrence();
			case TracePackage.MSE_MODEL: return createMSEModel();
			case TracePackage.GENERIC_MSE: return createGenericMSE();
			case TracePackage.SEQUENTIAL_STEP: return createSequentialStep();
			case TracePackage.PARALLEL_STEP: return createParallelStep();
			case TracePackage.GENERIC_SEQUENTIAL_STEP: return createGenericSequentialStep();
			case TracePackage.GENERIC_PARALLEL_STEP: return createGenericParallelStep();
			case TracePackage.GENERIC_SMALL_STEP: return createGenericSmallStep();
			case TracePackage.TRACE: return createTrace();
			case TracePackage.LAUNCH_CONFIGURATION: return createLaunchConfiguration();
			case TracePackage.LANGUAGE_NAME_PARAMETER: return createLanguageNameParameter();
			case TracePackage.MODEL_URI_PARAMETER: return createModelURIParameter();
			case TracePackage.ANIMATOR_URI_PARAMETER: return createAnimatorURIParameter();
			case TracePackage.ENTRY_POINT_PARAMETER: return createEntryPointParameter();
			case TracePackage.MODEL_ROOT_PARAMETER: return createModelRootParameter();
			case TracePackage.INITIALIZATION_METHOD_PARAMETER: return createInitializationMethodParameter();
			case TracePackage.INITIALIZATION_ARGUMENTS_PARAMETER: return createInitializationArgumentsParameter();
			case TracePackage.ADDON_EXTENSION_PARAMETER: return createAddonExtensionParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TracePackage.ISERIALIZABLE:
				return createISerializableFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TracePackage.ISERIALIZABLE:
				return convertISerializableToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSEOccurrence createMSEOccurrence() {
		MSEOccurrenceImpl mseOccurrence = new MSEOccurrenceImpl();
		return mseOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSEModel createMSEModel() {
		MSEModelImpl mseModel = new MSEModelImpl();
		return mseModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericMSE createGenericMSE() {
		GenericMSEImpl genericMSE = new GenericMSEImpl();
		return genericMSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <StepSubtype extends Step> SequentialStep<StepSubtype> createSequentialStep() {
		SequentialStepImpl<StepSubtype> sequentialStep = new SequentialStepImpl<StepSubtype>();
		return sequentialStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <StepSubtype extends Step> ParallelStep<StepSubtype> createParallelStep() {
		ParallelStepImpl<StepSubtype> parallelStep = new ParallelStepImpl<StepSubtype>();
		return parallelStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericSequentialStep createGenericSequentialStep() {
		GenericSequentialStepImpl genericSequentialStep = new GenericSequentialStepImpl();
		return genericSequentialStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericParallelStep createGenericParallelStep() {
		GenericParallelStepImpl genericParallelStep = new GenericParallelStepImpl();
		return genericParallelStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericSmallStep createGenericSmallStep() {
		GenericSmallStepImpl genericSmallStep = new GenericSmallStepImpl();
		return genericSmallStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <StepSubType> Trace<StepSubType> createTrace() {
		TraceImpl<StepSubType> trace = new TraceImpl<StepSubType>();
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaunchConfiguration createLaunchConfiguration() {
		LaunchConfigurationImpl launchConfiguration = new LaunchConfigurationImpl();
		return launchConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LanguageNameParameter createLanguageNameParameter() {
		LanguageNameParameterImpl languageNameParameter = new LanguageNameParameterImpl();
		return languageNameParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelURIParameter createModelURIParameter() {
		ModelURIParameterImpl modelURIParameter = new ModelURIParameterImpl();
		return modelURIParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnimatorURIParameter createAnimatorURIParameter() {
		AnimatorURIParameterImpl animatorURIParameter = new AnimatorURIParameterImpl();
		return animatorURIParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryPointParameter createEntryPointParameter() {
		EntryPointParameterImpl entryPointParameter = new EntryPointParameterImpl();
		return entryPointParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelRootParameter createModelRootParameter() {
		ModelRootParameterImpl modelRootParameter = new ModelRootParameterImpl();
		return modelRootParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitializationMethodParameter createInitializationMethodParameter() {
		InitializationMethodParameterImpl initializationMethodParameter = new InitializationMethodParameterImpl();
		return initializationMethodParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitializationArgumentsParameter createInitializationArgumentsParameter() {
		InitializationArgumentsParameterImpl initializationArgumentsParameter = new InitializationArgumentsParameterImpl();
		return initializationArgumentsParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddonExtensionParameter createAddonExtensionParameter() {
		AddonExtensionParameterImpl addonExtensionParameter = new AddonExtensionParameterImpl();
		return addonExtensionParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] createISerializableFromString(EDataType eDataType, String initialValue) {
		return (byte[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertISerializableToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracePackage getTracePackage() {
		return (TracePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TracePackage getPackage() {
		return TracePackage.eINSTANCE;
	}

} //TraceFactoryImpl
