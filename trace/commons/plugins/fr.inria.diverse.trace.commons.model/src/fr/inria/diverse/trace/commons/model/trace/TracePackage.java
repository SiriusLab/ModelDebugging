/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see fr.inria.diverse.trace.commons.model.trace.TraceFactory
 * @model kind="package"
 * @generated
 */
public interface TracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "trace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/generic_trace";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "trace";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracePackage eINSTANCE = fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEOccurrenceImpl <em>MSE Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEOccurrenceImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSEOccurrence()
	 * @generated
	 */
	int MSE_OCCURRENCE = 0;

	/**
	 * The feature id for the '<em><b>Mse</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__MSE = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__PARAMETERS = 1;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__RESULT = 2;

	/**
	 * The number of structural features of the '<em>MSE Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>MSE Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEImpl <em>MSE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSE()
	 * @generated
	 */
	int MSE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE__EANNOTATIONS = EcorePackage.ENAMED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE__NAME = EcorePackage.ENAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_FEATURE_COUNT = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get EAnnotation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_EANNOTATION__STRING = EcorePackage.ENAMED_ELEMENT___GET_EANNOTATION__STRING;

	/**
	 * The operation id for the '<em>Get Caller</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_CALLER = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_ACTION = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OPERATION_COUNT = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEModelImpl <em>MSE Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEModelImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSEModel()
	 * @generated
	 */
	int MSE_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Owned MS Es</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL__OWNED_MS_ES = 0;

	/**
	 * The number of structural features of the '<em>MSE Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>MSE Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericMSEImpl <em>Generic MSE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericMSEImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericMSE()
	 * @generated
	 */
	int GENERIC_MSE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__EANNOTATIONS = MSE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__NAME = MSE__NAME;

	/**
	 * The feature id for the '<em><b>Caller Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__CALLER_REFERENCE = MSE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__ACTION_REFERENCE = MSE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE_FEATURE_COUNT = MSE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get EAnnotation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_EANNOTATION__STRING = MSE___GET_EANNOTATION__STRING;

	/**
	 * The operation id for the '<em>Get Caller</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_CALLER = MSE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_ACTION = MSE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE_OPERATION_COUNT = MSE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.StepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getStep()
	 * @generated
	 */
	int STEP = 4;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__MSEOCCURRENCE = 0;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__STARTING_STATE = 1;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ENDING_STATE = 2;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.BigStepImpl <em>Big Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.BigStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getBigStep()
	 * @generated
	 */
	int BIG_STEP = 5;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__MSEOCCURRENCE = STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__STARTING_STATE = STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__ENDING_STATE = STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__SUB_STEPS = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Big Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Big Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.SmallStepImpl <em>Small Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.SmallStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getSmallStep()
	 * @generated
	 */
	int SMALL_STEP = 6;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP__MSEOCCURRENCE = STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP__STARTING_STATE = STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP__ENDING_STATE = STEP__ENDING_STATE;

	/**
	 * The number of structural features of the '<em>Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.SequentialStepImpl <em>Sequential Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.SequentialStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getSequentialStep()
	 * @generated
	 */
	int SEQUENTIAL_STEP = 7;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__MSEOCCURRENCE = BIG_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__STARTING_STATE = BIG_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__ENDING_STATE = BIG_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__SUB_STEPS = BIG_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP_FEATURE_COUNT = BIG_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP_OPERATION_COUNT = BIG_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ParallelStepImpl <em>Parallel Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.ParallelStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getParallelStep()
	 * @generated
	 */
	int PARALLEL_STEP = 8;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__MSEOCCURRENCE = BIG_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__STARTING_STATE = BIG_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__ENDING_STATE = BIG_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__SUB_STEPS = BIG_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP_FEATURE_COUNT = BIG_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP_OPERATION_COUNT = BIG_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericSequentialStep()
	 * @generated
	 */
	int GENERIC_SEQUENTIAL_STEP = 9;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__MSEOCCURRENCE = SEQUENTIAL_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__STARTING_STATE = SEQUENTIAL_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__ENDING_STATE = SEQUENTIAL_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__SUB_STEPS = SEQUENTIAL_STEP__SUB_STEPS;

	/**
	 * The feature id for the '<em><b>Starting State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF = SEQUENTIAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ending State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF = SEQUENTIAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_FEATURE_COUNT = SEQUENTIAL_STEP_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Starting State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP___GET_STARTING_STATE = SEQUENTIAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Ending State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP___GET_ENDING_STATE = SEQUENTIAL_STEP_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_OPERATION_COUNT = SEQUENTIAL_STEP_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericParallelStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericParallelStep()
	 * @generated
	 */
	int GENERIC_PARALLEL_STEP = 10;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__MSEOCCURRENCE = PARALLEL_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__STARTING_STATE = PARALLEL_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__ENDING_STATE = PARALLEL_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__SUB_STEPS = PARALLEL_STEP__SUB_STEPS;

	/**
	 * The feature id for the '<em><b>Starting State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__STARTING_STATE_REF = PARALLEL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ending State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__ENDING_STATE_REF = PARALLEL_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_FEATURE_COUNT = PARALLEL_STEP_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Starting State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP___GET_STARTING_STATE = PARALLEL_STEP_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Ending State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP___GET_ENDING_STATE = PARALLEL_STEP_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_OPERATION_COUNT = PARALLEL_STEP_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericSmallStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericSmallStep()
	 * @generated
	 */
	int GENERIC_SMALL_STEP = 11;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__MSEOCCURRENCE = SMALL_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__STARTING_STATE = SMALL_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__ENDING_STATE = SMALL_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Starting State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__STARTING_STATE_REF = SMALL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ending State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__ENDING_STATE_REF = SMALL_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_FEATURE_COUNT = SMALL_STEP_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Starting State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP___GET_STARTING_STATE = SMALL_STEP_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Ending State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP___GET_ENDING_STATE = SMALL_STEP_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_OPERATION_COUNT = SMALL_STEP_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TraceImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 12;

	/**
	 * The feature id for the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__ROOT_STEP = 0;

	/**
	 * The feature id for the '<em><b>Traced Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__TRACED_OBJECTS = 1;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__STATES = 2;

	/**
	 * The feature id for the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__LAUNCHCONFIGURATION = 3;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.TracedObjectImpl <em>Traced Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracedObjectImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getTracedObject()
	 * @generated
	 */
	int TRACED_OBJECT = 13;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT__DIMENSIONS = 0;

	/**
	 * The number of structural features of the '<em>Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.DimensionImpl <em>Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.DimensionImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 14;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__VALUES = 0;

	/**
	 * The number of structural features of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.ValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 15;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__STATES = 0;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.StateImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getState()
	 * @generated
	 */
	int STATE = 16;

	/**
	 * The feature id for the '<em><b>Started Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STARTED_STEPS = 0;

	/**
	 * The feature id for the '<em><b>Ended Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ENDED_STEPS = 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__VALUES = 2;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericValueImpl <em>Generic Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericValue()
	 * @generated
	 */
	int GENERIC_VALUE = 37;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE__STATES = VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE__STATES_REF = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE___GET_STATES = VALUE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericReferenceValueImpl <em>Generic Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericReferenceValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericReferenceValue()
	 * @generated
	 */
	int GENERIC_REFERENCE_VALUE = 17;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE__STATES = GENERIC_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE__STATES_REF = GENERIC_VALUE__STATES_REF;

	/**
	 * The feature id for the '<em><b>Reference Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE__REFERENCE_VALUE = GENERIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE_FEATURE_COUNT = GENERIC_VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE___GET_STATES = GENERIC_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Generic Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE_OPERATION_COUNT = GENERIC_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericDimensionImpl <em>Generic Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericDimensionImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericDimension()
	 * @generated
	 */
	int GENERIC_DIMENSION = 18;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION__VALUES = DIMENSION__VALUES;

	/**
	 * The feature id for the '<em><b>Dynamic Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION__DYNAMIC_PROPERTY = DIMENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION_FEATURE_COUNT = DIMENSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION_OPERATION_COUNT = DIMENSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericTracedObjectImpl <em>Generic Traced Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericTracedObjectImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericTracedObject()
	 * @generated
	 */
	int GENERIC_TRACED_OBJECT = 19;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT__DIMENSIONS = TRACED_OBJECT__DIMENSIONS;

	/**
	 * The number of structural features of the '<em>Generic Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT_FEATURE_COUNT = TRACED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT_OPERATION_COUNT = TRACED_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl <em>Generic State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericState()
	 * @generated
	 */
	int GENERIC_STATE = 20;

	/**
	 * The feature id for the '<em><b>Started Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__STARTED_STEPS = STATE__STARTED_STEPS;

	/**
	 * The feature id for the '<em><b>Ended Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__ENDED_STEPS = STATE__ENDED_STEPS;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__VALUES = STATE__VALUES;

	/**
	 * The feature id for the '<em><b>Values Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__VALUES_REF = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Started Steps Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__STARTED_STEPS_REF = STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ended Steps Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__ENDED_STEPS_REF = STATE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Generic State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Values</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE___GET_VALUES = STATE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Started Steps</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE___GET_STARTED_STEPS = STATE_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Ended Steps</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE___GET_ENDED_STEPS = STATE_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Generic State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE_OPERATION_COUNT = STATE_OPERATION_COUNT + 3;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericTraceImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericTrace()
	 * @generated
	 */
	int GENERIC_TRACE = 21;

	/**
	 * The feature id for the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__ROOT_STEP = TRACE__ROOT_STEP;

	/**
	 * The feature id for the '<em><b>Traced Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__TRACED_OBJECTS = TRACE__TRACED_OBJECTS;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__STATES = TRACE__STATES;

	/**
	 * The feature id for the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__LAUNCHCONFIGURATION = TRACE__LAUNCHCONFIGURATION;

	/**
	 * The number of structural features of the '<em>Generic Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_FEATURE_COUNT = TRACE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_OPERATION_COUNT = TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericAttributeValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericAttributeValue()
	 * @generated
	 */
	int GENERIC_ATTRIBUTE_VALUE = 22;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE__STATES = GENERIC_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE__STATES_REF = GENERIC_VALUE__STATES_REF;

	/**
	 * The number of structural features of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE___GET_STATES = GENERIC_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.BooleanAttributeValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getBooleanAttributeValue()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE_VALUE = 23;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__STATES_REF = GENERIC_ATTRIBUTE_VALUE__STATES_REF;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE___GET_STATES = GENERIC_ATTRIBUTE_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.IntegerAttributevalueImpl <em>Integer Attributevalue</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.IntegerAttributevalueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getIntegerAttributevalue()
	 * @generated
	 */
	int INTEGER_ATTRIBUTEVALUE = 24;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE__STATES_REF = GENERIC_ATTRIBUTE_VALUE__STATES_REF;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Attributevalue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE___GET_STATES = GENERIC_ATTRIBUTE_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>Integer Attributevalue</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTEVALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.StringAttributeValueImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getStringAttributeValue()
	 * @generated
	 */
	int STRING_ATTRIBUTE_VALUE = 25;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>States Ref</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__STATES_REF = GENERIC_ATTRIBUTE_VALUE__STATES_REF;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get States</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE___GET_STATES = GENERIC_ATTRIBUTE_VALUE___GET_STATES;

	/**
	 * The number of operations of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl <em>Launch Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLaunchConfiguration()
	 * @generated
	 */
	int LAUNCH_CONFIGURATION = 26;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION__PARAMETERS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Launch Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Launch Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationParameterImpl <em>Launch Configuration Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLaunchConfigurationParameter()
	 * @generated
	 */
	int LAUNCH_CONFIGURATION_PARAMETER = 27;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_PARAMETER__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Launch Configuration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Launch Configuration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LanguageNameParameterImpl <em>Language Name Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.LanguageNameParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLanguageNameParameter()
	 * @generated
	 */
	int LANGUAGE_NAME_PARAMETER = 28;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_NAME_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Language Name Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_NAME_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Language Name Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_NAME_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.AddonExtensionParameterImpl <em>Addon Extension Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.AddonExtensionParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getAddonExtensionParameter()
	 * @generated
	 */
	int ADDON_EXTENSION_PARAMETER = 29;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDON_EXTENSION_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Addon Extension Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDON_EXTENSION_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Addon Extension Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDON_EXTENSION_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ModelURIParameterImpl <em>Model URI Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.ModelURIParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getModelURIParameter()
	 * @generated
	 */
	int MODEL_URI_PARAMETER = 30;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_URI_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Model URI Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_URI_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Model URI Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_URI_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.AnimatorURIParameterImpl <em>Animator URI Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.AnimatorURIParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getAnimatorURIParameter()
	 * @generated
	 */
	int ANIMATOR_URI_PARAMETER = 31;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_URI_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Animator URI Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_URI_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Animator URI Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_URI_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.EntryPointParameterImpl <em>Entry Point Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.EntryPointParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getEntryPointParameter()
	 * @generated
	 */
	int ENTRY_POINT_PARAMETER = 32;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Entry Point Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Entry Point Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.InitializationArgumentsParameterImpl <em>Initialization Arguments Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.InitializationArgumentsParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getInitializationArgumentsParameter()
	 * @generated
	 */
	int INITIALIZATION_ARGUMENTS_PARAMETER = 33;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_ARGUMENTS_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Initialization Arguments Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_ARGUMENTS_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Initialization Arguments Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_ARGUMENTS_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ModelRootParameterImpl <em>Model Root Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.ModelRootParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getModelRootParameter()
	 * @generated
	 */
	int MODEL_ROOT_PARAMETER = 34;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Model Root Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Model Root Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ROOT_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.InitializationMethodParameterImpl <em>Initialization Method Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.InitializationMethodParameterImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getInitializationMethodParameter()
	 * @generated
	 */
	int INITIALIZATION_METHOD_PARAMETER = 35;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_METHOD_PARAMETER__VALUE = LAUNCH_CONFIGURATION_PARAMETER__VALUE;

	/**
	 * The number of structural features of the '<em>Initialization Method Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_METHOD_PARAMETER_FEATURE_COUNT = LAUNCH_CONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Initialization Method Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_METHOD_PARAMETER_OPERATION_COUNT = LAUNCH_CONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStepImpl <em>Generic Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericStepImpl
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericStep()
	 * @generated
	 */
	int GENERIC_STEP = 36;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__MSEOCCURRENCE = STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__STARTING_STATE = STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__ENDING_STATE = STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Starting State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__STARTING_STATE_REF = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ending State Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__ENDING_STATE_REF = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Starting State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP___GET_STARTING_STATE = STEP_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Ending State</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP___GET_ENDING_STATE = STEP_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '<em>ISerializable</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getISerializable()
	 * @generated
	 */
	int ISERIALIZABLE = 38;


	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.MSEOccurrence <em>MSE Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE Occurrence</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEOccurrence
	 * @generated
	 */
	EClass getMSEOccurrence();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getMse <em>Mse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mse</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getMse()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EReference getMSEOccurrence_Mse();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameters</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getParameters()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EAttribute getMSEOccurrence_Parameters();

	/**
	 * Returns the meta object for the attribute list '{@link fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Result</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEOccurrence#getResult()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EAttribute getMSEOccurrence_Result();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.MSE <em>MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSE
	 * @generated
	 */
	EClass getMSE();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.MSE#getCaller() <em>Get Caller</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Caller</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSE#getCaller()
	 * @generated
	 */
	EOperation getMSE__GetCaller();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.MSE#getAction() <em>Get Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Action</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSE#getAction()
	 * @generated
	 */
	EOperation getMSE__GetAction();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.MSEModel <em>MSE Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE Model</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEModel
	 * @generated
	 */
	EClass getMSEModel();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.MSEModel#getOwnedMSEs <em>Owned MS Es</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned MS Es</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.MSEModel#getOwnedMSEs()
	 * @see #getMSEModel()
	 * @generated
	 */
	EReference getMSEModel_OwnedMSEs();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE <em>Generic MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic MSE</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE
	 * @generated
	 */
	EClass getGenericMSE();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE#getCallerReference <em>Caller Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Caller Reference</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE#getCallerReference()
	 * @see #getGenericMSE()
	 * @generated
	 */
	EReference getGenericMSE_CallerReference();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE#getActionReference <em>Action Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action Reference</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE#getActionReference()
	 * @see #getGenericMSE()
	 * @generated
	 */
	EReference getGenericMSE_ActionReference();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE#getCaller() <em>Get Caller</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Caller</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE#getCaller()
	 * @generated
	 */
	EOperation getGenericMSE__GetCaller();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericMSE#getAction() <em>Get Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Action</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericMSE#getAction()
	 * @generated
	 */
	EOperation getGenericMSE__GetAction();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence <em>Mseoccurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mseoccurrence</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Mseoccurrence();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.Step#getStartingState <em>Starting State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Starting State</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Step#getStartingState()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_StartingState();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.Step#getEndingState <em>Ending State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ending State</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Step#getEndingState()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_EndingState();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.BigStep <em>Big Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Big Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.BigStep
	 * @generated
	 */
	EClass getBigStep();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.BigStep#getSubSteps <em>Sub Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Steps</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.BigStep#getSubSteps()
	 * @see #getBigStep()
	 * @generated
	 */
	EReference getBigStep_SubSteps();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.SmallStep <em>Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Small Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.SmallStep
	 * @generated
	 */
	EClass getSmallStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.SequentialStep <em>Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequential Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.SequentialStep
	 * @generated
	 */
	EClass getSequentialStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.ParallelStep <em>Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parallel Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.ParallelStep
	 * @generated
	 */
	EClass getParallelStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep <em>Generic Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Sequential Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep
	 * @generated
	 */
	EClass getGenericSequentialStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericParallelStep <em>Generic Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Parallel Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericParallelStep
	 * @generated
	 */
	EClass getGenericParallelStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericSmallStep <em>Generic Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Small Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericSmallStep
	 * @generated
	 */
	EClass getGenericSmallStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getRootStep <em>Root Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace#getRootStep()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_RootStep();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getTracedObjects <em>Traced Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traced Objects</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace#getTracedObjects()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_TracedObjects();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace#getStates()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_States();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Launchconfiguration</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Trace#getLaunchconfiguration()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Launchconfiguration();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.TracedObject <em>Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traced Object</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracedObject
	 * @generated
	 */
	EClass getTracedObject();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.TracedObject#getDimensions <em>Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimensions</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracedObject#getDimensions()
	 * @see #getTracedObject()
	 * @generated
	 */
	EReference getTracedObject_Dimensions();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Dimension
	 * @generated
	 */
	EClass getDimension();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.Dimension#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Dimension#getValues()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_Values();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.Value#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>States</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.Value#getStates()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_States();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.State#getStartedSteps <em>Started Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Started Steps</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.State#getStartedSteps()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_StartedSteps();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.State#getEndedSteps <em>Ended Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ended Steps</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.State#getEndedSteps()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_EndedSteps();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.State#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Values</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.State#getValues()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Values();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue <em>Generic Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Reference Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue
	 * @generated
	 */
	EClass getGenericReferenceValue();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue#getReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue#getReferenceValue()
	 * @see #getGenericReferenceValue()
	 * @generated
	 */
	EReference getGenericReferenceValue_ReferenceValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericDimension <em>Generic Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Dimension</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericDimension
	 * @generated
	 */
	EClass getGenericDimension();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericDimension#getDynamicProperty <em>Dynamic Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dynamic Property</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericDimension#getDynamicProperty()
	 * @see #getGenericDimension()
	 * @generated
	 */
	EReference getGenericDimension_DynamicProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericTracedObject <em>Generic Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Traced Object</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericTracedObject
	 * @generated
	 */
	EClass getGenericTracedObject();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericState <em>Generic State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic State</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState
	 * @generated
	 */
	EClass getGenericState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValuesRef <em>Values Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Values Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getValuesRef()
	 * @see #getGenericState()
	 * @generated
	 */
	EReference getGenericState_ValuesRef();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedStepsRef <em>Started Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Started Steps Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedStepsRef()
	 * @see #getGenericState()
	 * @generated
	 */
	EReference getGenericState_StartedStepsRef();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedStepsRef <em>Ended Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ended Steps Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedStepsRef()
	 * @see #getGenericState()
	 * @generated
	 */
	EReference getGenericState_EndedStepsRef();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValues() <em>Get Values</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Values</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getValues()
	 * @generated
	 */
	EOperation getGenericState__GetValues();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedSteps() <em>Get Started Steps</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Started Steps</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedSteps()
	 * @generated
	 */
	EOperation getGenericState__GetStartedSteps();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedSteps() <em>Get Ended Steps</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Ended Steps</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedSteps()
	 * @generated
	 */
	EOperation getGenericState__GetEndedSteps();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericTrace <em>Generic Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Trace</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericTrace
	 * @generated
	 */
	EClass getGenericTrace();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericAttributeValue <em>Generic Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericAttributeValue
	 * @generated
	 */
	EClass getGenericAttributeValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue
	 * @generated
	 */
	EClass getBooleanAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue#isAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.BooleanAttributeValue#isAttributeValue()
	 * @see #getBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getBooleanAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue <em>Integer Attributevalue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attributevalue</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue
	 * @generated
	 */
	EClass getIntegerAttributevalue();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.IntegerAttributevalue#getAttributeValue()
	 * @see #getIntegerAttributevalue()
	 * @generated
	 */
	EAttribute getIntegerAttributevalue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.StringAttributeValue
	 * @generated
	 */
	EClass getStringAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.trace.commons.model.trace.StringAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.StringAttributeValue#getAttributeValue()
	 * @see #getStringAttributeValue()
	 * @generated
	 */
	EAttribute getStringAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration <em>Launch Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Launch Configuration</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration
	 * @generated
	 */
	EClass getLaunchConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getParameters()
	 * @see #getLaunchConfiguration()
	 * @generated
	 */
	EReference getLaunchConfiguration_Parameters();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getType()
	 * @see #getLaunchConfiguration()
	 * @generated
	 */
	EAttribute getLaunchConfiguration_Type();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter <em>Launch Configuration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Launch Configuration Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter
	 * @generated
	 */
	EClass getLaunchConfigurationParameter();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter#getValue()
	 * @see #getLaunchConfigurationParameter()
	 * @generated
	 */
	EAttribute getLaunchConfigurationParameter_Value();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.LanguageNameParameter <em>Language Name Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Language Name Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.LanguageNameParameter
	 * @generated
	 */
	EClass getLanguageNameParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.AddonExtensionParameter <em>Addon Extension Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Addon Extension Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.AddonExtensionParameter
	 * @generated
	 */
	EClass getAddonExtensionParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.ModelURIParameter <em>Model URI Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model URI Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.ModelURIParameter
	 * @generated
	 */
	EClass getModelURIParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.AnimatorURIParameter <em>Animator URI Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Animator URI Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.AnimatorURIParameter
	 * @generated
	 */
	EClass getAnimatorURIParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.EntryPointParameter <em>Entry Point Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Point Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.EntryPointParameter
	 * @generated
	 */
	EClass getEntryPointParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.InitializationArgumentsParameter <em>Initialization Arguments Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initialization Arguments Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.InitializationArgumentsParameter
	 * @generated
	 */
	EClass getInitializationArgumentsParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.ModelRootParameter <em>Model Root Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Root Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.ModelRootParameter
	 * @generated
	 */
	EClass getModelRootParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.InitializationMethodParameter <em>Initialization Method Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initialization Method Parameter</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.InitializationMethodParameter
	 * @generated
	 */
	EClass getInitializationMethodParameter();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep <em>Generic Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Step</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep
	 * @generated
	 */
	EClass getGenericStep();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef <em>Starting State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Starting State Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef()
	 * @see #getGenericStep()
	 * @generated
	 */
	EReference getGenericStep_StartingStateRef();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef <em>Ending State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ending State Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef()
	 * @see #getGenericStep()
	 * @generated
	 */
	EReference getGenericStep_EndingStateRef();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingState() <em>Get Starting State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Starting State</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingState()
	 * @generated
	 */
	EOperation getGenericStep__GetStartingState();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingState() <em>Get Ending State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Ending State</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingState()
	 * @generated
	 */
	EOperation getGenericStep__GetEndingState();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue <em>Generic Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Value</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue
	 * @generated
	 */
	EClass getGenericValue();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStatesRef <em>States Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>States Ref</em>'.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue#getStatesRef()
	 * @see #getGenericValue()
	 * @generated
	 */
	EReference getGenericValue_StatesRef();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStates() <em>Get States</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get States</em>' operation.
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue#getStates()
	 * @generated
	 */
	EOperation getGenericValue__GetStates();

	/**
	 * Returns the meta object for data type '<em>ISerializable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ISerializable</em>'.
	 * @model instanceClass="byte[]"
	 * @generated
	 */
	EDataType getISerializable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TraceFactory getTraceFactory();

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
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEOccurrenceImpl <em>MSE Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEOccurrenceImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSEOccurrence()
		 * @generated
		 */
		EClass MSE_OCCURRENCE = eINSTANCE.getMSEOccurrence();

		/**
		 * The meta object literal for the '<em><b>Mse</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MSE_OCCURRENCE__MSE = eINSTANCE.getMSEOccurrence_Mse();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MSE_OCCURRENCE__PARAMETERS = eINSTANCE.getMSEOccurrence_Parameters();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MSE_OCCURRENCE__RESULT = eINSTANCE.getMSEOccurrence_Result();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEImpl <em>MSE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSE()
		 * @generated
		 */
		EClass MSE = eINSTANCE.getMSE();

		/**
		 * The meta object literal for the '<em><b>Get Caller</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MSE___GET_CALLER = eINSTANCE.getMSE__GetCaller();

		/**
		 * The meta object literal for the '<em><b>Get Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MSE___GET_ACTION = eINSTANCE.getMSE__GetAction();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.MSEModelImpl <em>MSE Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.MSEModelImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getMSEModel()
		 * @generated
		 */
		EClass MSE_MODEL = eINSTANCE.getMSEModel();

		/**
		 * The meta object literal for the '<em><b>Owned MS Es</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MSE_MODEL__OWNED_MS_ES = eINSTANCE.getMSEModel_OwnedMSEs();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericMSEImpl <em>Generic MSE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericMSEImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericMSE()
		 * @generated
		 */
		EClass GENERIC_MSE = eINSTANCE.getGenericMSE();

		/**
		 * The meta object literal for the '<em><b>Caller Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_MSE__CALLER_REFERENCE = eINSTANCE.getGenericMSE_CallerReference();

		/**
		 * The meta object literal for the '<em><b>Action Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_MSE__ACTION_REFERENCE = eINSTANCE.getGenericMSE_ActionReference();

		/**
		 * The meta object literal for the '<em><b>Get Caller</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_MSE___GET_CALLER = eINSTANCE.getGenericMSE__GetCaller();

		/**
		 * The meta object literal for the '<em><b>Get Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_MSE___GET_ACTION = eINSTANCE.getGenericMSE__GetAction();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.StepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '<em><b>Mseoccurrence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__MSEOCCURRENCE = eINSTANCE.getStep_Mseoccurrence();

		/**
		 * The meta object literal for the '<em><b>Starting State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__STARTING_STATE = eINSTANCE.getStep_StartingState();

		/**
		 * The meta object literal for the '<em><b>Ending State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__ENDING_STATE = eINSTANCE.getStep_EndingState();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.BigStepImpl <em>Big Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.BigStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getBigStep()
		 * @generated
		 */
		EClass BIG_STEP = eINSTANCE.getBigStep();

		/**
		 * The meta object literal for the '<em><b>Sub Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BIG_STEP__SUB_STEPS = eINSTANCE.getBigStep_SubSteps();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.SmallStepImpl <em>Small Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.SmallStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getSmallStep()
		 * @generated
		 */
		EClass SMALL_STEP = eINSTANCE.getSmallStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.SequentialStepImpl <em>Sequential Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.SequentialStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getSequentialStep()
		 * @generated
		 */
		EClass SEQUENTIAL_STEP = eINSTANCE.getSequentialStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ParallelStepImpl <em>Parallel Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.ParallelStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getParallelStep()
		 * @generated
		 */
		EClass PARALLEL_STEP = eINSTANCE.getParallelStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericSequentialStep()
		 * @generated
		 */
		EClass GENERIC_SEQUENTIAL_STEP = eINSTANCE.getGenericSequentialStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericParallelStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericParallelStep()
		 * @generated
		 */
		EClass GENERIC_PARALLEL_STEP = eINSTANCE.getGenericParallelStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericSmallStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericSmallStep()
		 * @generated
		 */
		EClass GENERIC_SMALL_STEP = eINSTANCE.getGenericSmallStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TraceImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Root Step</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__ROOT_STEP = eINSTANCE.getTrace_RootStep();

		/**
		 * The meta object literal for the '<em><b>Traced Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__TRACED_OBJECTS = eINSTANCE.getTrace_TracedObjects();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__STATES = eINSTANCE.getTrace_States();

		/**
		 * The meta object literal for the '<em><b>Launchconfiguration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__LAUNCHCONFIGURATION = eINSTANCE.getTrace_Launchconfiguration();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.TracedObjectImpl <em>Traced Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracedObjectImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getTracedObject()
		 * @generated
		 */
		EClass TRACED_OBJECT = eINSTANCE.getTracedObject();

		/**
		 * The meta object literal for the '<em><b>Dimensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACED_OBJECT__DIMENSIONS = eINSTANCE.getTracedObject_Dimensions();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.DimensionImpl <em>Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.DimensionImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getDimension()
		 * @generated
		 */
		EClass DIMENSION = eINSTANCE.getDimension();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__VALUES = eINSTANCE.getDimension_Values();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.ValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE__STATES = eINSTANCE.getValue_States();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.StateImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Started Steps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__STARTED_STEPS = eINSTANCE.getState_StartedSteps();

		/**
		 * The meta object literal for the '<em><b>Ended Steps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__ENDED_STEPS = eINSTANCE.getState_EndedSteps();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__VALUES = eINSTANCE.getState_Values();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericReferenceValueImpl <em>Generic Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericReferenceValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericReferenceValue()
		 * @generated
		 */
		EClass GENERIC_REFERENCE_VALUE = eINSTANCE.getGenericReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_REFERENCE_VALUE__REFERENCE_VALUE = eINSTANCE.getGenericReferenceValue_ReferenceValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericDimensionImpl <em>Generic Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericDimensionImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericDimension()
		 * @generated
		 */
		EClass GENERIC_DIMENSION = eINSTANCE.getGenericDimension();

		/**
		 * The meta object literal for the '<em><b>Dynamic Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_DIMENSION__DYNAMIC_PROPERTY = eINSTANCE.getGenericDimension_DynamicProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericTracedObjectImpl <em>Generic Traced Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericTracedObjectImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericTracedObject()
		 * @generated
		 */
		EClass GENERIC_TRACED_OBJECT = eINSTANCE.getGenericTracedObject();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl <em>Generic State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericState()
		 * @generated
		 */
		EClass GENERIC_STATE = eINSTANCE.getGenericState();

		/**
		 * The meta object literal for the '<em><b>Values Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_STATE__VALUES_REF = eINSTANCE.getGenericState_ValuesRef();

		/**
		 * The meta object literal for the '<em><b>Started Steps Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_STATE__STARTED_STEPS_REF = eINSTANCE.getGenericState_StartedStepsRef();

		/**
		 * The meta object literal for the '<em><b>Ended Steps Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_STATE__ENDED_STEPS_REF = eINSTANCE.getGenericState_EndedStepsRef();

		/**
		 * The meta object literal for the '<em><b>Get Values</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_STATE___GET_VALUES = eINSTANCE.getGenericState__GetValues();

		/**
		 * The meta object literal for the '<em><b>Get Started Steps</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_STATE___GET_STARTED_STEPS = eINSTANCE.getGenericState__GetStartedSteps();

		/**
		 * The meta object literal for the '<em><b>Get Ended Steps</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_STATE___GET_ENDED_STEPS = eINSTANCE.getGenericState__GetEndedSteps();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericTraceImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericTrace()
		 * @generated
		 */
		EClass GENERIC_TRACE = eINSTANCE.getGenericTrace();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericAttributeValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericAttributeValue()
		 * @generated
		 */
		EClass GENERIC_ATTRIBUTE_VALUE = eINSTANCE.getGenericAttributeValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.BooleanAttributeValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getBooleanAttributeValue()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.IntegerAttributevalueImpl <em>Integer Attributevalue</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.IntegerAttributevalueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getIntegerAttributevalue()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTEVALUE = eINSTANCE.getIntegerAttributevalue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTEVALUE__ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributevalue_AttributeValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.StringAttributeValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getStringAttributeValue()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl <em>Launch Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLaunchConfiguration()
		 * @generated
		 */
		EClass LAUNCH_CONFIGURATION = eINSTANCE.getLaunchConfiguration();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAUNCH_CONFIGURATION__PARAMETERS = eINSTANCE.getLaunchConfiguration_Parameters();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAUNCH_CONFIGURATION__TYPE = eINSTANCE.getLaunchConfiguration_Type();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationParameterImpl <em>Launch Configuration Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLaunchConfigurationParameter()
		 * @generated
		 */
		EClass LAUNCH_CONFIGURATION_PARAMETER = eINSTANCE.getLaunchConfigurationParameter();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAUNCH_CONFIGURATION_PARAMETER__VALUE = eINSTANCE.getLaunchConfigurationParameter_Value();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.LanguageNameParameterImpl <em>Language Name Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.LanguageNameParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getLanguageNameParameter()
		 * @generated
		 */
		EClass LANGUAGE_NAME_PARAMETER = eINSTANCE.getLanguageNameParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.AddonExtensionParameterImpl <em>Addon Extension Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.AddonExtensionParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getAddonExtensionParameter()
		 * @generated
		 */
		EClass ADDON_EXTENSION_PARAMETER = eINSTANCE.getAddonExtensionParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ModelURIParameterImpl <em>Model URI Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.ModelURIParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getModelURIParameter()
		 * @generated
		 */
		EClass MODEL_URI_PARAMETER = eINSTANCE.getModelURIParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.AnimatorURIParameterImpl <em>Animator URI Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.AnimatorURIParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getAnimatorURIParameter()
		 * @generated
		 */
		EClass ANIMATOR_URI_PARAMETER = eINSTANCE.getAnimatorURIParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.EntryPointParameterImpl <em>Entry Point Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.EntryPointParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getEntryPointParameter()
		 * @generated
		 */
		EClass ENTRY_POINT_PARAMETER = eINSTANCE.getEntryPointParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.InitializationArgumentsParameterImpl <em>Initialization Arguments Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.InitializationArgumentsParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getInitializationArgumentsParameter()
		 * @generated
		 */
		EClass INITIALIZATION_ARGUMENTS_PARAMETER = eINSTANCE.getInitializationArgumentsParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.ModelRootParameterImpl <em>Model Root Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.ModelRootParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getModelRootParameter()
		 * @generated
		 */
		EClass MODEL_ROOT_PARAMETER = eINSTANCE.getModelRootParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.InitializationMethodParameterImpl <em>Initialization Method Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.InitializationMethodParameterImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getInitializationMethodParameter()
		 * @generated
		 */
		EClass INITIALIZATION_METHOD_PARAMETER = eINSTANCE.getInitializationMethodParameter();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStepImpl <em>Generic Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericStepImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericStep()
		 * @generated
		 */
		EClass GENERIC_STEP = eINSTANCE.getGenericStep();

		/**
		 * The meta object literal for the '<em><b>Starting State Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_STEP__STARTING_STATE_REF = eINSTANCE.getGenericStep_StartingStateRef();

		/**
		 * The meta object literal for the '<em><b>Ending State Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_STEP__ENDING_STATE_REF = eINSTANCE.getGenericStep_EndingStateRef();

		/**
		 * The meta object literal for the '<em><b>Get Starting State</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_STEP___GET_STARTING_STATE = eINSTANCE.getGenericStep__GetStartingState();

		/**
		 * The meta object literal for the '<em><b>Get Ending State</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_STEP___GET_ENDING_STATE = eINSTANCE.getGenericStep__GetEndingState();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericValueImpl <em>Generic Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.GenericValueImpl
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getGenericValue()
		 * @generated
		 */
		EClass GENERIC_VALUE = eINSTANCE.getGenericValue();

		/**
		 * The meta object literal for the '<em><b>States Ref</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_VALUE__STATES_REF = eINSTANCE.getGenericValue_StatesRef();

		/**
		 * The meta object literal for the '<em><b>Get States</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_VALUE___GET_STATES = eINSTANCE.getGenericValue__GetStates();

		/**
		 * The meta object literal for the '<em>ISerializable</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.commons.model.trace.impl.TracePackageImpl#getISerializable()
		 * @generated
		 */
		EDataType ISERIALIZABLE = eINSTANCE.getISerializable();

	}

} //TracePackage
