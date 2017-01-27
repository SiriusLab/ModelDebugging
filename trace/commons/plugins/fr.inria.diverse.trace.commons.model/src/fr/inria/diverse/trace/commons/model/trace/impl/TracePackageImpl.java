/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.AddonExtensionParameter;
import fr.inria.diverse.trace.commons.model.trace.AnimatorURIParameter;
import fr.inria.diverse.trace.commons.model.trace.BigStep;
import fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue;
import fr.inria.diverse.trace.commons.model.trace.Dimension;
import fr.inria.diverse.trace.commons.model.trace.EntryPointParameter;
import fr.inria.diverse.trace.commons.model.trace.GenericAttributeValue;
import fr.inria.diverse.trace.commons.model.trace.GenericDimension;
import fr.inria.diverse.trace.commons.model.trace.GenericMSE;
import fr.inria.diverse.trace.commons.model.trace.GenericParallelStep;
import fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue;
import fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep;
import fr.inria.diverse.trace.commons.model.trace.GenericSmallStep;
import fr.inria.diverse.trace.commons.model.trace.GenericState;
import fr.inria.diverse.trace.commons.model.trace.GenericStep;
import fr.inria.diverse.trace.commons.model.trace.GenericTrace;
import fr.inria.diverse.trace.commons.model.trace.GenericTracedObject;
import fr.inria.diverse.trace.commons.model.trace.GenericValue;
import fr.inria.diverse.trace.commons.model.trace.InitializationArgumentsParameter;
import fr.inria.diverse.trace.commons.model.trace.InitializationMethodParameter;
import fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue;
import fr.inria.diverse.trace.commons.model.trace.LanguageNameParameter;
import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter;
import fr.inria.diverse.trace.commons.model.trace.MSEModel;
import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;
import fr.inria.diverse.trace.commons.model.trace.ModelRootParameter;
import fr.inria.diverse.trace.commons.model.trace.ModelURIParameter;
import fr.inria.diverse.trace.commons.model.trace.ParallelStep;
import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
import fr.inria.diverse.trace.commons.model.trace.SmallStep;
import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.StringAttributeValue;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.commons.model.trace.TraceFactory;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;
import fr.inria.diverse.trace.commons.model.trace.TracedObject;
import fr.inria.diverse.trace.commons.model.trace.Value;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TracePackageImpl extends EPackageImpl implements TracePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericMSEEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bigStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass smallStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequentialStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parallelStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericSequentialStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericParallelStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericSmallStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tracedObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericReferenceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericTracedObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerAttributevalueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass launchConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass launchConfigurationParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass languageNameParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addonExtensionParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelURIParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animatorURIParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryPointParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initializationArgumentsParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelRootParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initializationMethodParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iSerializableEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TracePackageImpl() {
		super(eNS_URI, TraceFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TracePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TracePackage init() {
		if (isInited) return (TracePackage)EPackage.Registry.INSTANCE.getEPackage(TracePackage.eNS_URI);

		// Obtain or create and register package
		TracePackageImpl theTracePackage = (TracePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTracePackage.createPackageContents();

		// Initialize created meta-data
		theTracePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTracePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TracePackage.eNS_URI, theTracePackage);
		return theTracePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSEOccurrence() {
		return mseOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMSEOccurrence_Mse() {
		return (EReference)mseOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMSEOccurrence_Parameters() {
		return (EAttribute)mseOccurrenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMSEOccurrence_Result() {
		return (EAttribute)mseOccurrenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSE() {
		return mseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMSE__GetCaller() {
		return mseEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMSE__GetAction() {
		return mseEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSEModel() {
		return mseModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMSEModel_OwnedMSEs() {
		return (EReference)mseModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericMSE() {
		return genericMSEEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericMSE_CallerReference() {
		return (EReference)genericMSEEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericMSE_ActionReference() {
		return (EReference)genericMSEEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericMSE__GetCaller() {
		return genericMSEEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericMSE__GetAction() {
		return genericMSEEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStep() {
		return stepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStep_Mseoccurrence() {
		return (EReference)stepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBigStep() {
		return bigStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBigStep_SubSteps() {
		return (EReference)bigStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSmallStep() {
		return smallStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequentialStep() {
		return sequentialStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParallelStep() {
		return parallelStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericSequentialStep() {
		return genericSequentialStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericParallelStep() {
		return genericParallelStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericSmallStep() {
		return genericSmallStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_RootStep() {
		return (EReference)traceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_TracedObjects() {
		return (EReference)traceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_States() {
		return (EReference)traceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_Launchconfiguration() {
		return (EReference)traceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTracedObject() {
		return tracedObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTracedObject_Dimensions() {
		return (EReference)tracedObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDimension() {
		return dimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDimension_Values() {
		return (EReference)dimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getValue__GetStatesView() {
		return valueEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getState__GetValuesView() {
		return stateEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericReferenceValue() {
		return genericReferenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericReferenceValue_ReferenceValue() {
		return (EReference)genericReferenceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericDimension() {
		return genericDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericDimension_DynamicProperty() {
		return (EReference)genericDimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericTracedObject() {
		return genericTracedObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericState() {
		return genericStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericState_Values() {
		return (EReference)genericStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericState__GetValuesView() {
		return genericStateEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericTrace() {
		return genericTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericAttributeValue() {
		return genericAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanAttributeValue() {
		return booleanAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanAttributeValue_AttributeValue() {
		return (EAttribute)booleanAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerAttributevalue() {
		return integerAttributevalueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerAttributevalue_AttributeValue() {
		return (EAttribute)integerAttributevalueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringAttributeValue() {
		return stringAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringAttributeValue_AttributeValue() {
		return (EAttribute)stringAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLaunchConfiguration() {
		return launchConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLaunchConfiguration_Parameters() {
		return (EReference)launchConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLaunchConfiguration_Type() {
		return (EAttribute)launchConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLaunchConfigurationParameter() {
		return launchConfigurationParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLaunchConfigurationParameter_Value() {
		return (EAttribute)launchConfigurationParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLanguageNameParameter() {
		return languageNameParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddonExtensionParameter() {
		return addonExtensionParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelURIParameter() {
		return modelURIParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnimatorURIParameter() {
		return animatorURIParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntryPointParameter() {
		return entryPointParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitializationArgumentsParameter() {
		return initializationArgumentsParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelRootParameter() {
		return modelRootParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitializationMethodParameter() {
		return initializationMethodParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericStep() {
		return genericStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericValue() {
		return genericValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericValue_States() {
		return (EReference)genericValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericValue__GetStatesView() {
		return genericValueEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getISerializable() {
		return iSerializableEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceFactory getTraceFactory() {
		return (TraceFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		mseOccurrenceEClass = createEClass(MSE_OCCURRENCE);
		createEReference(mseOccurrenceEClass, MSE_OCCURRENCE__MSE);
		createEAttribute(mseOccurrenceEClass, MSE_OCCURRENCE__PARAMETERS);
		createEAttribute(mseOccurrenceEClass, MSE_OCCURRENCE__RESULT);

		mseEClass = createEClass(MSE);
		createEOperation(mseEClass, MSE___GET_CALLER);
		createEOperation(mseEClass, MSE___GET_ACTION);

		mseModelEClass = createEClass(MSE_MODEL);
		createEReference(mseModelEClass, MSE_MODEL__OWNED_MS_ES);

		genericMSEEClass = createEClass(GENERIC_MSE);
		createEReference(genericMSEEClass, GENERIC_MSE__CALLER_REFERENCE);
		createEReference(genericMSEEClass, GENERIC_MSE__ACTION_REFERENCE);
		createEOperation(genericMSEEClass, GENERIC_MSE___GET_CALLER);
		createEOperation(genericMSEEClass, GENERIC_MSE___GET_ACTION);

		stepEClass = createEClass(STEP);
		createEReference(stepEClass, STEP__MSEOCCURRENCE);

		bigStepEClass = createEClass(BIG_STEP);
		createEReference(bigStepEClass, BIG_STEP__SUB_STEPS);

		smallStepEClass = createEClass(SMALL_STEP);

		sequentialStepEClass = createEClass(SEQUENTIAL_STEP);

		parallelStepEClass = createEClass(PARALLEL_STEP);

		genericSequentialStepEClass = createEClass(GENERIC_SEQUENTIAL_STEP);

		genericParallelStepEClass = createEClass(GENERIC_PARALLEL_STEP);

		genericSmallStepEClass = createEClass(GENERIC_SMALL_STEP);

		traceEClass = createEClass(TRACE);
		createEReference(traceEClass, TRACE__ROOT_STEP);
		createEReference(traceEClass, TRACE__TRACED_OBJECTS);
		createEReference(traceEClass, TRACE__STATES);
		createEReference(traceEClass, TRACE__LAUNCHCONFIGURATION);

		tracedObjectEClass = createEClass(TRACED_OBJECT);
		createEReference(tracedObjectEClass, TRACED_OBJECT__DIMENSIONS);

		dimensionEClass = createEClass(DIMENSION);
		createEReference(dimensionEClass, DIMENSION__VALUES);

		valueEClass = createEClass(VALUE);
		createEOperation(valueEClass, VALUE___GET_STATES_VIEW);

		stateEClass = createEClass(STATE);
		createEOperation(stateEClass, STATE___GET_VALUES_VIEW);

		genericReferenceValueEClass = createEClass(GENERIC_REFERENCE_VALUE);
		createEReference(genericReferenceValueEClass, GENERIC_REFERENCE_VALUE__REFERENCE_VALUE);

		genericDimensionEClass = createEClass(GENERIC_DIMENSION);
		createEReference(genericDimensionEClass, GENERIC_DIMENSION__DYNAMIC_PROPERTY);

		genericTracedObjectEClass = createEClass(GENERIC_TRACED_OBJECT);

		genericStateEClass = createEClass(GENERIC_STATE);
		createEReference(genericStateEClass, GENERIC_STATE__VALUES);
		createEOperation(genericStateEClass, GENERIC_STATE___GET_VALUES_VIEW);

		genericTraceEClass = createEClass(GENERIC_TRACE);

		genericAttributeValueEClass = createEClass(GENERIC_ATTRIBUTE_VALUE);

		booleanAttributeValueEClass = createEClass(BOOLEAN_ATTRIBUTE_VALUE);
		createEAttribute(booleanAttributeValueEClass, BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		integerAttributevalueEClass = createEClass(INTEGER_ATTRIBUTEVALUE);
		createEAttribute(integerAttributevalueEClass, INTEGER_ATTRIBUTEVALUE__ATTRIBUTE_VALUE);

		stringAttributeValueEClass = createEClass(STRING_ATTRIBUTE_VALUE);
		createEAttribute(stringAttributeValueEClass, STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		launchConfigurationEClass = createEClass(LAUNCH_CONFIGURATION);
		createEReference(launchConfigurationEClass, LAUNCH_CONFIGURATION__PARAMETERS);
		createEAttribute(launchConfigurationEClass, LAUNCH_CONFIGURATION__TYPE);

		launchConfigurationParameterEClass = createEClass(LAUNCH_CONFIGURATION_PARAMETER);
		createEAttribute(launchConfigurationParameterEClass, LAUNCH_CONFIGURATION_PARAMETER__VALUE);

		languageNameParameterEClass = createEClass(LANGUAGE_NAME_PARAMETER);

		addonExtensionParameterEClass = createEClass(ADDON_EXTENSION_PARAMETER);

		modelURIParameterEClass = createEClass(MODEL_URI_PARAMETER);

		animatorURIParameterEClass = createEClass(ANIMATOR_URI_PARAMETER);

		entryPointParameterEClass = createEClass(ENTRY_POINT_PARAMETER);

		initializationArgumentsParameterEClass = createEClass(INITIALIZATION_ARGUMENTS_PARAMETER);

		modelRootParameterEClass = createEClass(MODEL_ROOT_PARAMETER);

		initializationMethodParameterEClass = createEClass(INITIALIZATION_METHOD_PARAMETER);

		genericStepEClass = createEClass(GENERIC_STEP);

		genericValueEClass = createEClass(GENERIC_VALUE);
		createEReference(genericValueEClass, GENERIC_VALUE__STATES);
		createEOperation(genericValueEClass, GENERIC_VALUE___GET_STATES_VIEW);

		// Create data types
		iSerializableEDataType = createEDataType(ISERIALIZABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters
		ETypeParameter bigStepEClass_StepSubtype = addETypeParameter(bigStepEClass, "StepSubtype");
		ETypeParameter sequentialStepEClass_StepSubtype = addETypeParameter(sequentialStepEClass, "StepSubtype");
		ETypeParameter parallelStepEClass_StepSubtype = addETypeParameter(parallelStepEClass, "StepSubtype");
		ETypeParameter traceEClass_StepSubType = addETypeParameter(traceEClass, "StepSubType");
		ETypeParameter traceEClass_TracedObjectSubtype = addETypeParameter(traceEClass, "TracedObjectSubtype");
		ETypeParameter traceEClass_StateSubType = addETypeParameter(traceEClass, "StateSubType");
		ETypeParameter tracedObjectEClass_DimensionSubType = addETypeParameter(tracedObjectEClass, "DimensionSubType");
		ETypeParameter dimensionEClass_ValueSubType = addETypeParameter(dimensionEClass, "ValueSubType");
		ETypeParameter genericTracedObjectEClass_T = addETypeParameter(genericTracedObjectEClass, "T");
		ETypeParameter genericTraceEClass_StepSubType = addETypeParameter(genericTraceEClass, "StepSubType");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getStep());
		bigStepEClass_StepSubtype.getEBounds().add(g1);
		g1 = createEGenericType(this.getStep());
		sequentialStepEClass_StepSubtype.getEBounds().add(g1);
		g1 = createEGenericType(this.getStep());
		parallelStepEClass_StepSubtype.getEBounds().add(g1);
		g1 = createEGenericType(this.getValue());
		dimensionEClass_ValueSubType.getEBounds().add(g1);
		g1 = createEGenericType(ecorePackage.getEObject());
		genericTracedObjectEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getGenericStep());
		genericTraceEClass_StepSubType.getEBounds().add(g1);

		// Add supertypes to classes
		mseEClass.getESuperTypes().add(theEcorePackage.getENamedElement());
		genericMSEEClass.getESuperTypes().add(this.getMSE());
		bigStepEClass.getESuperTypes().add(this.getStep());
		smallStepEClass.getESuperTypes().add(this.getStep());
		g1 = createEGenericType(this.getBigStep());
		EGenericType g2 = createEGenericType(sequentialStepEClass_StepSubtype);
		g1.getETypeArguments().add(g2);
		sequentialStepEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getBigStep());
		g2 = createEGenericType(parallelStepEClass_StepSubtype);
		g1.getETypeArguments().add(g2);
		parallelStepEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getSequentialStep());
		g2 = createEGenericType(this.getStep());
		g1.getETypeArguments().add(g2);
		genericSequentialStepEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getGenericStep());
		genericSequentialStepEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getParallelStep());
		g2 = createEGenericType(this.getStep());
		g1.getETypeArguments().add(g2);
		genericParallelStepEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getGenericStep());
		genericParallelStepEClass.getEGenericSuperTypes().add(g1);
		genericSmallStepEClass.getESuperTypes().add(this.getSmallStep());
		genericSmallStepEClass.getESuperTypes().add(this.getGenericStep());
		genericReferenceValueEClass.getESuperTypes().add(this.getGenericValue());
		g1 = createEGenericType(this.getDimension());
		g2 = createEGenericType(this.getGenericValue());
		g1.getETypeArguments().add(g2);
		genericDimensionEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getTracedObject());
		g2 = createEGenericType(this.getGenericDimension());
		g1.getETypeArguments().add(g2);
		genericTracedObjectEClass.getEGenericSuperTypes().add(g1);
		genericStateEClass.getESuperTypes().add(this.getState());
		g1 = createEGenericType(this.getTrace());
		g2 = createEGenericType(genericTraceEClass_StepSubType);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(this.getGenericTracedObject());
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType();
		g2.getETypeArguments().add(g3);
		g2 = createEGenericType(this.getGenericState());
		g1.getETypeArguments().add(g2);
		genericTraceEClass.getEGenericSuperTypes().add(g1);
		genericAttributeValueEClass.getESuperTypes().add(this.getGenericValue());
		booleanAttributeValueEClass.getESuperTypes().add(this.getGenericAttributeValue());
		integerAttributevalueEClass.getESuperTypes().add(this.getGenericAttributeValue());
		stringAttributeValueEClass.getESuperTypes().add(this.getGenericAttributeValue());
		languageNameParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		addonExtensionParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		modelURIParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		animatorURIParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		entryPointParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		initializationArgumentsParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		modelRootParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		initializationMethodParameterEClass.getESuperTypes().add(this.getLaunchConfigurationParameter());
		genericStepEClass.getESuperTypes().add(this.getStep());
		genericValueEClass.getESuperTypes().add(this.getValue());

		// Initialize classes, features, and operations; add parameters
		initEClass(mseOccurrenceEClass, MSEOccurrence.class, "MSEOccurrence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMSEOccurrence_Mse(), this.getMSE(), null, "mse", null, 1, 1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMSEOccurrence_Parameters(), theEcorePackage.getEJavaObject(), "parameters", null, 0, -1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMSEOccurrence_Result(), ecorePackage.getEJavaObject(), "result", null, 0, -1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mseEClass, fr.inria.diverse.trace.commons.model.trace.MSE.class, "MSE", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getMSE__GetCaller(), theEcorePackage.getEObject(), "getCaller", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getMSE__GetAction(), theEcorePackage.getEOperation(), "getAction", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(mseModelEClass, MSEModel.class, "MSEModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMSEModel_OwnedMSEs(), this.getMSE(), null, "ownedMSEs", null, 0, -1, MSEModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericMSEEClass, GenericMSE.class, "GenericMSE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericMSE_CallerReference(), theEcorePackage.getEObject(), null, "callerReference", null, 0, 1, GenericMSE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenericMSE_ActionReference(), theEcorePackage.getEOperation(), null, "actionReference", null, 0, 1, GenericMSE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getGenericMSE__GetCaller(), theEcorePackage.getEObject(), "getCaller", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getGenericMSE__GetAction(), theEcorePackage.getEOperation(), "getAction", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stepEClass, Step.class, "Step", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStep_Mseoccurrence(), this.getMSEOccurrence(), null, "mseoccurrence", null, 0, 1, Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bigStepEClass, BigStep.class, "BigStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(bigStepEClass_StepSubtype);
		initEReference(getBigStep_SubSteps(), g1, null, "subSteps", null, 0, -1, BigStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(smallStepEClass, SmallStep.class, "SmallStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequentialStepEClass, SequentialStep.class, "SequentialStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parallelStepEClass, ParallelStep.class, "ParallelStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericSequentialStepEClass, GenericSequentialStep.class, "GenericSequentialStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericParallelStepEClass, GenericParallelStep.class, "GenericParallelStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericSmallStepEClass, GenericSmallStep.class, "GenericSmallStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(traceEClass, Trace.class, "Trace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(traceEClass_StepSubType);
		initEReference(getTrace_RootStep(), g1, null, "rootStep", null, 1, 1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(traceEClass_TracedObjectSubtype);
		initEReference(getTrace_TracedObjects(), g1, null, "tracedObjects", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(traceEClass_StateSubType);
		initEReference(getTrace_States(), g1, null, "states", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrace_Launchconfiguration(), this.getLaunchConfiguration(), null, "launchconfiguration", null, 1, 1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tracedObjectEClass, TracedObject.class, "TracedObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(tracedObjectEClass_DimensionSubType);
		initEReference(getTracedObject_Dimensions(), g1, null, "dimensions", null, 0, -1, TracedObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dimensionEClass, Dimension.class, "Dimension", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(dimensionEClass_ValueSubType);
		initEReference(getDimension_Values(), g1, null, "values", null, 0, -1, Dimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getValue__GetStatesView(), this.getState(), "getStatesView", 1, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getState__GetValuesView(), this.getValue(), "getValuesView", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(genericReferenceValueEClass, GenericReferenceValue.class, "GenericReferenceValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericReferenceValue_ReferenceValue(), ecorePackage.getEObject(), null, "referenceValue", null, 0, 1, GenericReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericDimensionEClass, GenericDimension.class, "GenericDimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericDimension_DynamicProperty(), theEcorePackage.getEStructuralFeature(), null, "dynamicProperty", null, 0, 1, GenericDimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericTracedObjectEClass, GenericTracedObject.class, "GenericTracedObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericStateEClass, GenericState.class, "GenericState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericState_Values(), this.getGenericValue(), this.getGenericValue_States(), "values", null, 0, -1, GenericState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getGenericState__GetValuesView(), this.getValue(), "getValuesView", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(genericTraceEClass, GenericTrace.class, "GenericTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericAttributeValueEClass, GenericAttributeValue.class, "GenericAttributeValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanAttributeValueEClass, BooleanAttributeValue.class, "BooleanAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanAttributeValue_AttributeValue(), theEcorePackage.getEBoolean(), "attributeValue", "false", 0, 1, BooleanAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerAttributevalueEClass, IntegerAttributevalue.class, "IntegerAttributevalue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerAttributevalue_AttributeValue(), theEcorePackage.getEInt(), "attributeValue", null, 0, 1, IntegerAttributevalue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringAttributeValueEClass, StringAttributeValue.class, "StringAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringAttributeValue_AttributeValue(), theEcorePackage.getEString(), "attributeValue", null, 0, 1, StringAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(launchConfigurationEClass, LaunchConfiguration.class, "LaunchConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLaunchConfiguration_Parameters(), this.getLaunchConfigurationParameter(), null, "parameters", null, 0, -1, LaunchConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLaunchConfiguration_Type(), theEcorePackage.getEString(), "type", null, 1, 1, LaunchConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(launchConfigurationParameterEClass, LaunchConfigurationParameter.class, "LaunchConfigurationParameter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLaunchConfigurationParameter_Value(), ecorePackage.getEString(), "value", "", 0, 1, LaunchConfigurationParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(languageNameParameterEClass, LanguageNameParameter.class, "LanguageNameParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(addonExtensionParameterEClass, AddonExtensionParameter.class, "AddonExtensionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelURIParameterEClass, ModelURIParameter.class, "ModelURIParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(animatorURIParameterEClass, AnimatorURIParameter.class, "AnimatorURIParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entryPointParameterEClass, EntryPointParameter.class, "EntryPointParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(initializationArgumentsParameterEClass, InitializationArgumentsParameter.class, "InitializationArgumentsParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(modelRootParameterEClass, ModelRootParameter.class, "ModelRootParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(initializationMethodParameterEClass, InitializationMethodParameter.class, "InitializationMethodParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericStepEClass, GenericStep.class, "GenericStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(genericValueEClass, GenericValue.class, "GenericValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericValue_States(), this.getGenericState(), this.getGenericState_Values(), "states", null, 0, -1, GenericValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getGenericValue__GetStatesView(), this.getState(), "getStatesView", 1, -1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(iSerializableEDataType, byte[].class, "ISerializable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TracePackageImpl
