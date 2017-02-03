/**
 */
package base.States;

import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see base.States.StatesFactory
 * @model kind="package"
 * @generated
 */
public interface StatesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "States";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://tracedclasses/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracedclasses";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StatesPackage eINSTANCE = base.States.impl.StatesPackageImpl.init();

	/**
	 * The meta object id for the '{@link base.States.impl.SpecificStateImpl <em>Specific State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.impl.SpecificStateImpl
	 * @see base.States.impl.StatesPackageImpl#getSpecificState()
	 * @generated
	 */
	int SPECIFIC_STATE = 0;

	/**
	 * The feature id for the '<em><b>Started Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE__STARTED_STEPS = TracePackage.STATE__STARTED_STEPS;

	/**
	 * The feature id for the '<em><b>Ended Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE__ENDED_STEPS = TracePackage.STATE__ENDED_STEPS;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE__VALUES = TracePackage.STATE__VALUES;

	/**
	 * The feature id for the '<em><b>Started Steps Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE__STARTED_STEPS_REF = TracePackage.STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ended Steps Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE__ENDED_STEPS_REF = TracePackage.STATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Specific State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE_FEATURE_COUNT = TracePackage.STATE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Values</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE___GET_VALUES = TracePackage.STATE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Started Steps</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE___GET_STARTED_STEPS = TracePackage.STATE_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Ended Steps</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE___GET_ENDED_STEPS = TracePackage.STATE_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Specific State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_STATE_OPERATION_COUNT = TracePackage.STATE_OPERATION_COUNT + 3;

	/**
	 * The meta object id for the '{@link base.States.impl.SpecificValueImpl <em>Specific Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.impl.SpecificValueImpl
	 * @see base.States.impl.StatesPackageImpl#getSpecificValue()
	 * @generated
	 */
	int SPECIFIC_VALUE = 5;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_VALUE__STATES = TracePackage.VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Specific Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_VALUE_FEATURE_COUNT = TracePackage.VALUE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_VALUE___GET_STATES = TracePackage.VALUE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Specific Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_VALUE_OPERATION_COUNT = TracePackage.VALUE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link base.States.SpecificAttributeValue <em>Specific Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.SpecificAttributeValue
	 * @see base.States.impl.StatesPackageImpl#getSpecificAttributeValue()
	 * @generated
	 */
	int SPECIFIC_ATTRIBUTE_VALUE = 1;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_ATTRIBUTE_VALUE__STATES = SPECIFIC_VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Specific Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_ATTRIBUTE_VALUE_FEATURE_COUNT = SPECIFIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_ATTRIBUTE_VALUE___GET_STATES = SPECIFIC_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Specific Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_ATTRIBUTE_VALUE_OPERATION_COUNT = SPECIFIC_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link base.States.impl.SpecificDimensionImpl <em>Specific Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.impl.SpecificDimensionImpl
	 * @see base.States.impl.StatesPackageImpl#getSpecificDimension()
	 * @generated
	 */
	int SPECIFIC_DIMENSION = 2;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIMENSION__VALUES = TracePackage.DIMENSION__VALUES;

	/**
	 * The number of structural features of the '<em>Specific Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIMENSION_FEATURE_COUNT = TracePackage.DIMENSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Specific Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_DIMENSION_OPERATION_COUNT = TracePackage.DIMENSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link base.States.impl.SpecificTracedObjectImpl <em>Specific Traced Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.impl.SpecificTracedObjectImpl
	 * @see base.States.impl.StatesPackageImpl#getSpecificTracedObject()
	 * @generated
	 */
	int SPECIFIC_TRACED_OBJECT = 3;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_TRACED_OBJECT__DIMENSIONS = TracePackage.TRACED_OBJECT__DIMENSIONS;

	/**
	 * The number of structural features of the '<em>Specific Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_TRACED_OBJECT_FEATURE_COUNT = TracePackage.TRACED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Dimensions Internal</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_TRACED_OBJECT___GET_DIMENSIONS_INTERNAL = TracePackage.TRACED_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Specific Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_TRACED_OBJECT_OPERATION_COUNT = TracePackage.TRACED_OBJECT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link base.States.SpecificReferenceValue <em>Specific Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see base.States.SpecificReferenceValue
	 * @see base.States.impl.StatesPackageImpl#getSpecificReferenceValue()
	 * @generated
	 */
	int SPECIFIC_REFERENCE_VALUE = 4;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_REFERENCE_VALUE__STATES = SPECIFIC_VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Specific Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_REFERENCE_VALUE_FEATURE_COUNT = SPECIFIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_REFERENCE_VALUE___GET_STATES = SPECIFIC_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Specific Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIC_REFERENCE_VALUE_OPERATION_COUNT = SPECIFIC_VALUE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link base.States.SpecificState <em>Specific State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific State</em>'.
	 * @see base.States.SpecificState
	 * @generated
	 */
	EClass getSpecificState();

	/**
	 * Returns the meta object for the reference list '{@link base.States.SpecificState#getStartedStepsRef <em>Started Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Started Steps Ref</em>'.
	 * @see base.States.SpecificState#getStartedStepsRef()
	 * @see #getSpecificState()
	 * @generated
	 */
	EReference getSpecificState_StartedStepsRef();

	/**
	 * Returns the meta object for the reference list '{@link base.States.SpecificState#getEndedStepsRef <em>Ended Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ended Steps Ref</em>'.
	 * @see base.States.SpecificState#getEndedStepsRef()
	 * @see #getSpecificState()
	 * @generated
	 */
	EReference getSpecificState_EndedStepsRef();

	/**
	 * Returns the meta object for the '{@link base.States.SpecificState#getValues() <em>Get Values</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Values</em>' operation.
	 * @see base.States.SpecificState#getValues()
	 * @generated
	 */
	EOperation getSpecificState__GetValues();

	/**
	 * Returns the meta object for the '{@link base.States.SpecificState#getStartedSteps() <em>Get Started Steps</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Started Steps</em>' operation.
	 * @see base.States.SpecificState#getStartedSteps()
	 * @generated
	 */
	EOperation getSpecificState__GetStartedSteps();

	/**
	 * Returns the meta object for the '{@link base.States.SpecificState#getEndedSteps() <em>Get Ended Steps</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Ended Steps</em>' operation.
	 * @see base.States.SpecificState#getEndedSteps()
	 * @generated
	 */
	EOperation getSpecificState__GetEndedSteps();

	/**
	 * Returns the meta object for class '{@link base.States.SpecificAttributeValue <em>Specific Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Attribute Value</em>'.
	 * @see base.States.SpecificAttributeValue
	 * @generated
	 */
	EClass getSpecificAttributeValue();

	/**
	 * Returns the meta object for class '{@link base.States.SpecificDimension <em>Specific Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Dimension</em>'.
	 * @see base.States.SpecificDimension
	 * @generated
	 */
	EClass getSpecificDimension();

	/**
	 * Returns the meta object for class '{@link base.States.SpecificTracedObject <em>Specific Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Traced Object</em>'.
	 * @see base.States.SpecificTracedObject
	 * @generated
	 */
	EClass getSpecificTracedObject();

	/**
	 * Returns the meta object for the '{@link base.States.SpecificTracedObject#getDimensionsInternal() <em>Get Dimensions Internal</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Dimensions Internal</em>' operation.
	 * @see base.States.SpecificTracedObject#getDimensionsInternal()
	 * @generated
	 */
	EOperation getSpecificTracedObject__GetDimensionsInternal();

	/**
	 * Returns the meta object for class '{@link base.States.SpecificReferenceValue <em>Specific Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Reference Value</em>'.
	 * @see base.States.SpecificReferenceValue
	 * @generated
	 */
	EClass getSpecificReferenceValue();

	/**
	 * Returns the meta object for class '{@link base.States.SpecificValue <em>Specific Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specific Value</em>'.
	 * @see base.States.SpecificValue
	 * @generated
	 */
	EClass getSpecificValue();

	/**
	 * Returns the meta object for the '{@link base.States.SpecificValue#getStates() <em>Get States</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get States</em>' operation.
	 * @see base.States.SpecificValue#getStates()
	 * @generated
	 */
	EOperation getSpecificValue__GetStates();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StatesFactory getStatesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link base.States.impl.SpecificStateImpl <em>Specific State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.impl.SpecificStateImpl
		 * @see base.States.impl.StatesPackageImpl#getSpecificState()
		 * @generated
		 */
		EClass SPECIFIC_STATE = eINSTANCE.getSpecificState();

		/**
		 * The meta object literal for the '<em><b>Started Steps Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_STATE__STARTED_STEPS_REF = eINSTANCE.getSpecificState_StartedStepsRef();

		/**
		 * The meta object literal for the '<em><b>Ended Steps Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIC_STATE__ENDED_STEPS_REF = eINSTANCE.getSpecificState_EndedStepsRef();

		/**
		 * The meta object literal for the '<em><b>Get Values</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPECIFIC_STATE___GET_VALUES = eINSTANCE.getSpecificState__GetValues();

		/**
		 * The meta object literal for the '<em><b>Get Started Steps</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPECIFIC_STATE___GET_STARTED_STEPS = eINSTANCE.getSpecificState__GetStartedSteps();

		/**
		 * The meta object literal for the '<em><b>Get Ended Steps</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPECIFIC_STATE___GET_ENDED_STEPS = eINSTANCE.getSpecificState__GetEndedSteps();

		/**
		 * The meta object literal for the '{@link base.States.SpecificAttributeValue <em>Specific Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.SpecificAttributeValue
		 * @see base.States.impl.StatesPackageImpl#getSpecificAttributeValue()
		 * @generated
		 */
		EClass SPECIFIC_ATTRIBUTE_VALUE = eINSTANCE.getSpecificAttributeValue();

		/**
		 * The meta object literal for the '{@link base.States.impl.SpecificDimensionImpl <em>Specific Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.impl.SpecificDimensionImpl
		 * @see base.States.impl.StatesPackageImpl#getSpecificDimension()
		 * @generated
		 */
		EClass SPECIFIC_DIMENSION = eINSTANCE.getSpecificDimension();

		/**
		 * The meta object literal for the '{@link base.States.impl.SpecificTracedObjectImpl <em>Specific Traced Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.impl.SpecificTracedObjectImpl
		 * @see base.States.impl.StatesPackageImpl#getSpecificTracedObject()
		 * @generated
		 */
		EClass SPECIFIC_TRACED_OBJECT = eINSTANCE.getSpecificTracedObject();

		/**
		 * The meta object literal for the '<em><b>Get Dimensions Internal</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPECIFIC_TRACED_OBJECT___GET_DIMENSIONS_INTERNAL = eINSTANCE.getSpecificTracedObject__GetDimensionsInternal();

		/**
		 * The meta object literal for the '{@link base.States.SpecificReferenceValue <em>Specific Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.SpecificReferenceValue
		 * @see base.States.impl.StatesPackageImpl#getSpecificReferenceValue()
		 * @generated
		 */
		EClass SPECIFIC_REFERENCE_VALUE = eINSTANCE.getSpecificReferenceValue();

		/**
		 * The meta object literal for the '{@link base.States.impl.SpecificValueImpl <em>Specific Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see base.States.impl.SpecificValueImpl
		 * @see base.States.impl.StatesPackageImpl#getSpecificValue()
		 * @generated
		 */
		EClass SPECIFIC_VALUE = eINSTANCE.getSpecificValue();

		/**
		 * The meta object literal for the '<em><b>Get States</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPECIFIC_VALUE___GET_STATES = eINSTANCE.getSpecificValue__GetStates();

	}

} //StatesPackage
