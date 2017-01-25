/**
 */
package base.Steps;

import base.States.SpecificState;

import fr.inria.diverse.trace.commons.model.trace.Step;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link base.Steps.SpecificStep#getStartingState <em>Starting State</em>}</li>
 *   <li>{@link base.Steps.SpecificStep#getEndingState <em>Ending State</em>}</li>
 * </ul>
 *
 * @see base.Steps.StepsPackage#getSpecificStep()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificStep extends Step {
	/**
	 * Returns the value of the '<em><b>Starting State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link base.States.SpecificState#getStartedSteps <em>Started Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting State</em>' reference.
	 * @see #setStartingState(SpecificState)
	 * @see base.Steps.StepsPackage#getSpecificStep_StartingState()
	 * @see base.States.SpecificState#getStartedSteps
	 * @model opposite="startedSteps" required="true"
	 * @generated
	 */
	SpecificState getStartingState();

	/**
	 * Sets the value of the '{@link base.Steps.SpecificStep#getStartingState <em>Starting State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting State</em>' reference.
	 * @see #getStartingState()
	 * @generated
	 */
	void setStartingState(SpecificState value);

	/**
	 * Returns the value of the '<em><b>Ending State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link base.States.SpecificState#getEndedSteps <em>Ended Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending State</em>' reference.
	 * @see #setEndingState(SpecificState)
	 * @see base.Steps.StepsPackage#getSpecificStep_EndingState()
	 * @see base.States.SpecificState#getEndedSteps
	 * @model opposite="endedSteps"
	 * @generated
	 */
	SpecificState getEndingState();

	/**
	 * Sets the value of the '{@link base.Steps.SpecificStep#getEndingState <em>Ending State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending State</em>' reference.
	 * @see #getEndingState()
	 * @generated
	 */
	void setEndingState(SpecificState value);

} // SpecificStep
