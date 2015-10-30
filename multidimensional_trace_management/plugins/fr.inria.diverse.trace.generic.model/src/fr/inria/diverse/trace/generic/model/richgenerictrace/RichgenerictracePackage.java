/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.ecore.EClass;
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
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictraceFactory
 * @model kind="package"
 * @generated
 */
public interface RichgenerictracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "richgenerictrace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "richgenerictrace";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "richgenerictrace";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RichgenerictracePackage eINSTANCE = fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 0;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__STATES = 0;

	/**
	 * The feature id for the '<em><b>Traced Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__TRACED_OBJECTS = 1;

	/**
	 * The feature id for the '<em><b>Current State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__CURRENT_STATE = 2;

	/**
	 * The feature id for the '<em><b>Current Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__CURRENT_STEP = 3;

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
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl <em>Value Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getValueSequence()
	 * @generated
	 */
	int VALUE_SEQUENCE = 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SEQUENCE__VALUES = 0;

	/**
	 * The feature id for the '<em><b>Current</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SEQUENCE__CURRENT = 1;

	/**
	 * The feature id for the '<em><b>Traced Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SEQUENCE__TRACED_PROPERTY = 2;

	/**
	 * The number of structural features of the '<em>Value Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SEQUENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Value Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SEQUENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 2;

	/**
	 * The feature id for the '<em><b>Execution States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__EXECUTION_STATES = 0;

	/**
	 * The feature id for the '<em><b>Next Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__NEXT_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Previous Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__PREVIOUS_VALUE = 2;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl <em>Execution State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getExecutionState()
	 * @generated
	 */
	int EXECUTION_STATE = 3;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__VALUES = 0;

	/**
	 * The feature id for the '<em><b>Ending Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__ENDING_STEPS = 1;

	/**
	 * The feature id for the '<em><b>Next State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__NEXT_STATE = 2;

	/**
	 * The feature id for the '<em><b>Previous State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__PREVIOUS_STATE = 3;

	/**
	 * The feature id for the '<em><b>Starting Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE__STARTING_STEPS = 4;

	/**
	 * The number of structural features of the '<em>Execution State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Execution State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getStep()
	 * @generated
	 */
	int STEP = 4;

	/**
	 * The feature id for the '<em><b>Next Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__NEXT_STEP = 0;

	/**
	 * The feature id for the '<em><b>Previous Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__PREVIOUS_STEP = 1;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ENDING_STATE = 2;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__STARTING_STATE = 3;

	/**
	 * The feature id for the '<em><b>Parent Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__PARENT_STEP = 4;

	/**
	 * The feature id for the '<em><b>Applied Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__APPLIED_RULE = 5;

	/**
	 * The feature id for the '<em><b>Children Steps</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__CHILDREN_STEPS = 6;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl <em>Traced Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getTracedObject()
	 * @generated
	 */
	int TRACED_OBJECT = 5;

	/**
	 * The feature id for the '<em><b>Value Sequences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT__VALUE_SEQUENCES = 0;

	/**
	 * The feature id for the '<em><b>Original Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT__ORIGINAL_OBJECT = 1;

	/**
	 * The number of structural features of the '<em>Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACED_OBJECT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getStates()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_States();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getTracedObjects <em>Traced Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traced Objects</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getTracedObjects()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_TracedObjects();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentState <em>Current State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentState()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_CurrentState();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentStep <em>Current Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current Step</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentStep()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_CurrentStep();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence <em>Value Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Sequence</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence
	 * @generated
	 */
	EClass getValueSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getValues()
	 * @see #getValueSequence()
	 * @generated
	 */
	EReference getValueSequence_Values();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getCurrent <em>Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getCurrent()
	 * @see #getValueSequence()
	 * @generated
	 */
	EReference getValueSequence_Current();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getTracedProperty <em>Traced Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Traced Property</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence#getTracedProperty()
	 * @see #getValueSequence()
	 * @generated
	 */
	EReference getValueSequence_TracedProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getExecutionStates <em>Execution States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution States</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getExecutionStates()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_ExecutionStates();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue <em>Next Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next Value</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_NextValue();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue <em>Previous Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Previous Value</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_PreviousValue();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState
	 * @generated
	 */
	EClass getExecutionState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Values</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getValues()
	 * @see #getExecutionState()
	 * @generated
	 */
	EReference getExecutionState_Values();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getEndingSteps <em>Ending Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ending Steps</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getEndingSteps()
	 * @see #getExecutionState()
	 * @generated
	 */
	EReference getExecutionState_EndingSteps();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState <em>Next State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState()
	 * @see #getExecutionState()
	 * @generated
	 */
	EReference getExecutionState_NextState();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState <em>Previous State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Previous State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState()
	 * @see #getExecutionState()
	 * @generated
	 */
	EReference getExecutionState_PreviousState();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getStartingSteps <em>Starting Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Starting Steps</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getStartingSteps()
	 * @see #getExecutionState()
	 * @generated
	 */
	EReference getExecutionState_StartingSteps();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep <em>Next Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next Step</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_NextStep();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep <em>Previous Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Previous Step</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_PreviousStep();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState <em>Ending State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ending State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_EndingState();

	/**
	 * Returns the meta object for the container reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState <em>Starting State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Starting State</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_StartingState();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep <em>Parent Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Step</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_ParentStep();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getAppliedRule <em>Applied Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Applied Rule</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getAppliedRule()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_AppliedRule();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps <em>Children Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Children Steps</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_ChildrenSteps();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject <em>Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Traced Object</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject
	 * @generated
	 */
	EClass getTracedObject();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getValueSequences <em>Value Sequences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value Sequences</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getValueSequences()
	 * @see #getTracedObject()
	 * @generated
	 */
	EReference getTracedObject_ValueSequences();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getOriginalObject <em>Original Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Original Object</em>'.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getOriginalObject()
	 * @see #getTracedObject()
	 * @generated
	 */
	EReference getTracedObject_OriginalObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RichgenerictraceFactory getRichgenerictraceFactory();

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
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__STATES = eINSTANCE.getTrace_States();

		/**
		 * The meta object literal for the '<em><b>Traced Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__TRACED_OBJECTS = eINSTANCE.getTrace_TracedObjects();

		/**
		 * The meta object literal for the '<em><b>Current State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__CURRENT_STATE = eINSTANCE.getTrace_CurrentState();

		/**
		 * The meta object literal for the '<em><b>Current Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__CURRENT_STEP = eINSTANCE.getTrace_CurrentStep();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl <em>Value Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getValueSequence()
		 * @generated
		 */
		EClass VALUE_SEQUENCE = eINSTANCE.getValueSequence();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_SEQUENCE__VALUES = eINSTANCE.getValueSequence_Values();

		/**
		 * The meta object literal for the '<em><b>Current</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_SEQUENCE__CURRENT = eINSTANCE.getValueSequence_Current();

		/**
		 * The meta object literal for the '<em><b>Traced Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_SEQUENCE__TRACED_PROPERTY = eINSTANCE.getValueSequence_TracedProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>Execution States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE__EXECUTION_STATES = eINSTANCE.getValue_ExecutionStates();

		/**
		 * The meta object literal for the '<em><b>Next Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE__NEXT_VALUE = eINSTANCE.getValue_NextValue();

		/**
		 * The meta object literal for the '<em><b>Previous Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE__PREVIOUS_VALUE = eINSTANCE.getValue_PreviousValue();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl <em>Execution State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getExecutionState()
		 * @generated
		 */
		EClass EXECUTION_STATE = eINSTANCE.getExecutionState();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_STATE__VALUES = eINSTANCE.getExecutionState_Values();

		/**
		 * The meta object literal for the '<em><b>Ending Steps</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_STATE__ENDING_STEPS = eINSTANCE.getExecutionState_EndingSteps();

		/**
		 * The meta object literal for the '<em><b>Next State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_STATE__NEXT_STATE = eINSTANCE.getExecutionState_NextState();

		/**
		 * The meta object literal for the '<em><b>Previous State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_STATE__PREVIOUS_STATE = eINSTANCE.getExecutionState_PreviousState();

		/**
		 * The meta object literal for the '<em><b>Starting Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_STATE__STARTING_STEPS = eINSTANCE.getExecutionState_StartingSteps();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '<em><b>Next Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__NEXT_STEP = eINSTANCE.getStep_NextStep();

		/**
		 * The meta object literal for the '<em><b>Previous Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__PREVIOUS_STEP = eINSTANCE.getStep_PreviousStep();

		/**
		 * The meta object literal for the '<em><b>Ending State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__ENDING_STATE = eINSTANCE.getStep_EndingState();

		/**
		 * The meta object literal for the '<em><b>Starting State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__STARTING_STATE = eINSTANCE.getStep_StartingState();

		/**
		 * The meta object literal for the '<em><b>Parent Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__PARENT_STEP = eINSTANCE.getStep_ParentStep();

		/**
		 * The meta object literal for the '<em><b>Applied Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__APPLIED_RULE = eINSTANCE.getStep_AppliedRule();

		/**
		 * The meta object literal for the '<em><b>Children Steps</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__CHILDREN_STEPS = eINSTANCE.getStep_ChildrenSteps();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl <em>Traced Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl
		 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.impl.RichgenerictracePackageImpl#getTracedObject()
		 * @generated
		 */
		EClass TRACED_OBJECT = eINSTANCE.getTracedObject();

		/**
		 * The meta object literal for the '<em><b>Value Sequences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACED_OBJECT__VALUE_SEQUENCES = eINSTANCE.getTracedObject_ValueSequences();

		/**
		 * The meta object literal for the '<em><b>Original Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACED_OBJECT__ORIGINAL_OBJECT = eINSTANCE.getTracedObject_OriginalObject();

	}

} //RichgenerictracePackage
