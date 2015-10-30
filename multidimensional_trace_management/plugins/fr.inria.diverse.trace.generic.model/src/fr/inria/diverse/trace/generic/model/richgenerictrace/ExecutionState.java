/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getValues <em>Values</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getEndingSteps <em>Ending Steps</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState <em>Next State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState <em>Previous State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getStartingSteps <em>Starting Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState()
 * @model
 * @generated
 */
public interface ExecutionState extends EObject {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getExecutionStates <em>Execution States</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState_Values()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getExecutionStates
	 * @model opposite="executionStates"
	 * @generated
	 */
	EList<Value> getValues();

	/**
	 * Returns the value of the '<em><b>Ending Steps</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState <em>Ending State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending Steps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending Steps</em>' reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState_EndingSteps()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState
	 * @model opposite="endingState"
	 * @generated
	 */
	EList<Step> getEndingSteps();

	/**
	 * Returns the value of the '<em><b>Next State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState <em>Previous State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next State</em>' reference.
	 * @see #setNextState(ExecutionState)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState_NextState()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState
	 * @model opposite="previousState" derived="true"
	 * @generated
	 */
	ExecutionState getNextState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState <em>Next State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next State</em>' reference.
	 * @see #getNextState()
	 * @generated
	 */
	void setNextState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Previous State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState <em>Next State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous State</em>' reference.
	 * @see #setPreviousState(ExecutionState)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState_PreviousState()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getNextState
	 * @model opposite="nextState" derived="true"
	 * @generated
	 */
	ExecutionState getPreviousState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getPreviousState <em>Previous State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous State</em>' reference.
	 * @see #getPreviousState()
	 * @generated
	 */
	void setPreviousState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Starting Steps</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState <em>Starting State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting Steps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting Steps</em>' containment reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getExecutionState_StartingSteps()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState
	 * @model opposite="startingState" containment="true"
	 * @generated
	 */
	EList<Step> getStartingSteps();

} // ExecutionState
