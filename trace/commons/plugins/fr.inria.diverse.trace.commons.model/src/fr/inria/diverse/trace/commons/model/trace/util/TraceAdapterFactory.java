/**
 */
package fr.inria.diverse.trace.commons.model.trace.util;

import fr.inria.diverse.trace.commons.model.trace.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage
 * @generated
 */
public class TraceAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TracePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TracePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceSwitch<Adapter> modelSwitch =
		new TraceSwitch<Adapter>() {
			@Override
			public Adapter caseMSEOccurrence(MSEOccurrence object) {
				return createMSEOccurrenceAdapter();
			}
			@Override
			public Adapter caseMSE(MSE object) {
				return createMSEAdapter();
			}
			@Override
			public Adapter caseMSEModel(MSEModel object) {
				return createMSEModelAdapter();
			}
			@Override
			public Adapter caseGenericMSE(GenericMSE object) {
				return createGenericMSEAdapter();
			}
			@Override
			public <StateSubType extends State<?, ?>> Adapter caseStep(Step<StateSubType> object) {
				return createStepAdapter();
			}
			@Override
			public <StepSubtype extends Step<StateSubType>, StateSubType extends State<?, ?>> Adapter caseBigStep(BigStep<StepSubtype, StateSubType> object) {
				return createBigStepAdapter();
			}
			@Override
			public <StateSubType extends State<?, ?>> Adapter caseSmallStep(SmallStep<StateSubType> object) {
				return createSmallStepAdapter();
			}
			@Override
			public <StepSubtype extends Step<StateSubType>, StateSubType extends State<?, ?>> Adapter caseSequentialStep(SequentialStep<StepSubtype, StateSubType> object) {
				return createSequentialStepAdapter();
			}
			@Override
			public <StepSubtype extends Step<StateSubType>, StateSubType extends State<StepSubtype, ?>> Adapter caseParallelStep(ParallelStep<StepSubtype, StateSubType> object) {
				return createParallelStepAdapter();
			}
			@Override
			public Adapter caseGenericSequentialStep(GenericSequentialStep object) {
				return createGenericSequentialStepAdapter();
			}
			@Override
			public Adapter caseGenericParallelStep(GenericParallelStep object) {
				return createGenericParallelStepAdapter();
			}
			@Override
			public Adapter caseGenericSmallStep(GenericSmallStep object) {
				return createGenericSmallStepAdapter();
			}
			@Override
			public <StepSubType extends Step<?>, TracedObjectSubtype extends TracedObject<?>, StateSubType extends State<?, ?>> Adapter caseTrace(Trace<StepSubType, TracedObjectSubtype, StateSubType> object) {
				return createTraceAdapter();
			}
			@Override
			public <DimensionSubType extends Dimension<?>> Adapter caseTracedObject(TracedObject<DimensionSubType> object) {
				return createTracedObjectAdapter();
			}
			@Override
			public <ValueSubType extends Value<?>> Adapter caseDimension(Dimension<ValueSubType> object) {
				return createDimensionAdapter();
			}
			@Override
			public <StateSubType extends State<?, ?>> Adapter caseValue(Value<StateSubType> object) {
				return createValueAdapter();
			}
			@Override
			public <StepSubType extends Step<?>, ValueSubType extends Value<?>> Adapter caseState(State<StepSubType, ValueSubType> object) {
				return createStateAdapter();
			}
			@Override
			public Adapter caseGenericReferenceValue(GenericReferenceValue object) {
				return createGenericReferenceValueAdapter();
			}
			@Override
			public Adapter caseGenericDimension(GenericDimension object) {
				return createGenericDimensionAdapter();
			}
			@Override
			public <T extends EObject> Adapter caseGenericTracedObject(GenericTracedObject<T> object) {
				return createGenericTracedObjectAdapter();
			}
			@Override
			public Adapter caseGenericState(GenericState object) {
				return createGenericStateAdapter();
			}
			@Override
			public <StepSubType extends GenericStep> Adapter caseGenericTrace(GenericTrace<StepSubType> object) {
				return createGenericTraceAdapter();
			}
			@Override
			public Adapter caseGenericAttributeValue(GenericAttributeValue object) {
				return createGenericAttributeValueAdapter();
			}
			@Override
			public Adapter caseBooleanAttributeValue(BooleanAttributeValue object) {
				return createBooleanAttributeValueAdapter();
			}
			@Override
			public Adapter caseIntegerAttributevalue(IntegerAttributevalue object) {
				return createIntegerAttributevalueAdapter();
			}
			@Override
			public Adapter caseStringAttributeValue(StringAttributeValue object) {
				return createStringAttributeValueAdapter();
			}
			@Override
			public Adapter caseLaunchConfiguration(LaunchConfiguration object) {
				return createLaunchConfigurationAdapter();
			}
			@Override
			public Adapter caseLaunchConfigurationParameter(LaunchConfigurationParameter object) {
				return createLaunchConfigurationParameterAdapter();
			}
			@Override
			public Adapter caseLanguageNameParameter(LanguageNameParameter object) {
				return createLanguageNameParameterAdapter();
			}
			@Override
			public Adapter caseAddonExtensionParameter(AddonExtensionParameter object) {
				return createAddonExtensionParameterAdapter();
			}
			@Override
			public Adapter caseModelURIParameter(ModelURIParameter object) {
				return createModelURIParameterAdapter();
			}
			@Override
			public Adapter caseAnimatorURIParameter(AnimatorURIParameter object) {
				return createAnimatorURIParameterAdapter();
			}
			@Override
			public Adapter caseEntryPointParameter(EntryPointParameter object) {
				return createEntryPointParameterAdapter();
			}
			@Override
			public Adapter caseInitializationArgumentsParameter(InitializationArgumentsParameter object) {
				return createInitializationArgumentsParameterAdapter();
			}
			@Override
			public Adapter caseModelRootParameter(ModelRootParameter object) {
				return createModelRootParameterAdapter();
			}
			@Override
			public Adapter caseInitializationMethodParameter(InitializationMethodParameter object) {
				return createInitializationMethodParameterAdapter();
			}
			@Override
			public Adapter caseGenericStep(GenericStep object) {
				return createGenericStepAdapter();
			}
			@Override
			public Adapter caseGenericValue(GenericValue object) {
				return createGenericValueAdapter();
			}
			@Override
			public Adapter caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			@Override
			public Adapter caseENamedElement(ENamedElement object) {
				return createENamedElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.MSEOccurrence <em>MSE Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEOccurrence
	 * @generated
	 */
	public Adapter createMSEOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.MSE <em>MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSE
	 * @generated
	 */
	public Adapter createMSEAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.MSEModel <em>MSE Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEModel
	 * @generated
	 */
	public Adapter createMSEModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE <em>Generic MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE
	 * @generated
	 */
	public Adapter createGenericMSEAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.Step
	 * @generated
	 */
	public Adapter createStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.BigStep <em>Big Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.BigStep
	 * @generated
	 */
	public Adapter createBigStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.SmallStep <em>Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.SmallStep
	 * @generated
	 */
	public Adapter createSmallStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.SequentialStep <em>Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.SequentialStep
	 * @generated
	 */
	public Adapter createSequentialStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.ParallelStep <em>Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.ParallelStep
	 * @generated
	 */
	public Adapter createParallelStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep <em>Generic Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep
	 * @generated
	 */
	public Adapter createGenericSequentialStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericParallelStep <em>Generic Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericParallelStep
	 * @generated
	 */
	public Adapter createGenericParallelStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericSmallStep <em>Generic Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericSmallStep
	 * @generated
	 */
	public Adapter createGenericSmallStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace
	 * @generated
	 */
	public Adapter createTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.TracedObject <em>Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracedObject
	 * @generated
	 */
	public Adapter createTracedObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.Dimension
	 * @generated
	 */
	public Adapter createDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue <em>Generic Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue
	 * @generated
	 */
	public Adapter createGenericReferenceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericDimension <em>Generic Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericDimension
	 * @generated
	 */
	public Adapter createGenericDimensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericTracedObject <em>Generic Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericTracedObject
	 * @generated
	 */
	public Adapter createGenericTracedObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericState <em>Generic State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState
	 * @generated
	 */
	public Adapter createGenericStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericTrace <em>Generic Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericTrace
	 * @generated
	 */
	public Adapter createGenericTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericAttributeValue <em>Generic Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericAttributeValue
	 * @generated
	 */
	public Adapter createGenericAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue
	 * @generated
	 */
	public Adapter createBooleanAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue <em>Integer Attributevalue</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue
	 * @generated
	 */
	public Adapter createIntegerAttributevalueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.StringAttributeValue
	 * @generated
	 */
	public Adapter createStringAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration <em>Launch Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration
	 * @generated
	 */
	public Adapter createLaunchConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter <em>Launch Configuration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter
	 * @generated
	 */
	public Adapter createLaunchConfigurationParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.LanguageNameParameter <em>Language Name Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.LanguageNameParameter
	 * @generated
	 */
	public Adapter createLanguageNameParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.AddonExtensionParameter <em>Addon Extension Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.AddonExtensionParameter
	 * @generated
	 */
	public Adapter createAddonExtensionParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.ModelURIParameter <em>Model URI Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.ModelURIParameter
	 * @generated
	 */
	public Adapter createModelURIParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.AnimatorURIParameter <em>Animator URI Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.AnimatorURIParameter
	 * @generated
	 */
	public Adapter createAnimatorURIParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.EntryPointParameter <em>Entry Point Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.EntryPointParameter
	 * @generated
	 */
	public Adapter createEntryPointParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.InitializationArgumentsParameter <em>Initialization Arguments Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.InitializationArgumentsParameter
	 * @generated
	 */
	public Adapter createInitializationArgumentsParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.ModelRootParameter <em>Model Root Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.ModelRootParameter
	 * @generated
	 */
	public Adapter createModelRootParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.InitializationMethodParameter <em>Initialization Method Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.InitializationMethodParameter
	 * @generated
	 */
	public Adapter createInitializationMethodParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep <em>Generic Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep
	 * @generated
	 */
	public Adapter createGenericStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue <em>Generic Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue
	 * @generated
	 */
	public Adapter createGenericValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createENamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TraceAdapterFactory
