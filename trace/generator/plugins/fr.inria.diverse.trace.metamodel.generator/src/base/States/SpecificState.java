/**
 */
package base.States;

import base.Steps.SpecificStep;

import fr.inria.diverse.trace.commons.model.trace.State;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link base.States.SpecificState#getStartedStepsRef <em>Started Steps Ref</em>}</li>
 *   <li>{@link base.States.SpecificState#getEndedStepsRef <em>Ended Steps Ref</em>}</li>
 * </ul>
 *
 * @see base.States.StatesPackage#getSpecificState()
 * @model
 * @generated
 */
public interface SpecificState extends State<SpecificStep, SpecificValue> {
	/**
	 * Returns the value of the '<em><b>Started Steps Ref</b></em>' reference list.
	 * The list contents are of type {@link base.Steps.SpecificStep}.
	 * It is bidirectional and its opposite is '{@link base.Steps.SpecificStep#getStartingStateRef <em>Starting State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Started Steps Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Started Steps Ref</em>' reference list.
	 * @see base.States.StatesPackage#getSpecificState_StartedStepsRef()
	 * @see base.Steps.SpecificStep#getStartingStateRef
	 * @model opposite="startingStateRef"
	 * @generated
	 */
	EList<SpecificStep> getStartedStepsRef();

	/**
	 * Returns the value of the '<em><b>Ended Steps Ref</b></em>' reference list.
	 * The list contents are of type {@link base.Steps.SpecificStep}.
	 * It is bidirectional and its opposite is '{@link base.Steps.SpecificStep#getEndingStateRef <em>Ending State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ended Steps Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ended Steps Ref</em>' reference list.
	 * @see base.States.StatesPackage#getSpecificState_EndedStepsRef()
	 * @see base.Steps.SpecificStep#getEndingStateRef
	 * @model opposite="endingStateRef"
	 * @generated
	 */
	EList<SpecificStep> getEndedStepsRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<SpecificValue> getValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getStartedStepsRef();'"
	 * @generated
	 */
	EList<SpecificStep> getStartedSteps();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getEndedStepsRef();'"
	 * @generated
	 */
	EList<SpecificStep> getEndedSteps();

} // SpecificState
