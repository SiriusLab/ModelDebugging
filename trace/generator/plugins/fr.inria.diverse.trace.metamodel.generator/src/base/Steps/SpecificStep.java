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
 *   <li>{@link base.Steps.SpecificStep#getStartingStateRef <em>Starting State Ref</em>}</li>
 *   <li>{@link base.Steps.SpecificStep#getEndingStateRef <em>Ending State Ref</em>}</li>
 * </ul>
 *
 * @see base.Steps.StepsPackage#getSpecificStep()
 * @model abstract="true"
 * @generated
 */
public interface SpecificStep extends Step<SpecificState> {
	/**
	 * Returns the value of the '<em><b>Starting State Ref</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link base.States.SpecificState#getStartedStepsRef <em>Started Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting State Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting State Ref</em>' reference.
	 * @see #setStartingStateRef(SpecificState)
	 * @see base.Steps.StepsPackage#getSpecificStep_StartingStateRef()
	 * @see base.States.SpecificState#getStartedStepsRef
	 * @model opposite="startedStepsRef" required="true"
	 * @generated
	 */
	SpecificState getStartingStateRef();

	/**
	 * Sets the value of the '{@link base.Steps.SpecificStep#getStartingStateRef <em>Starting State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting State Ref</em>' reference.
	 * @see #getStartingStateRef()
	 * @generated
	 */
	void setStartingStateRef(SpecificState value);

	/**
	 * Returns the value of the '<em><b>Ending State Ref</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link base.States.SpecificState#getEndedStepsRef <em>Ended Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending State Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending State Ref</em>' reference.
	 * @see #setEndingStateRef(SpecificState)
	 * @see base.Steps.StepsPackage#getSpecificStep_EndingStateRef()
	 * @see base.States.SpecificState#getEndedStepsRef
	 * @model opposite="endedStepsRef"
	 * @generated
	 */
	SpecificState getEndingStateRef();

	/**
	 * Sets the value of the '{@link base.Steps.SpecificStep#getEndingStateRef <em>Ending State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending State Ref</em>' reference.
	 * @see #getEndingStateRef()
	 * @generated
	 */
	void setEndingStateRef(SpecificState value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getStartingStateRef();'"
	 * @generated
	 */
	SpecificState getStartingState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getEndingStateRef();'"
	 * @generated
	 */
	SpecificState getEndingState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return basicGetStartingStateRef();'"
	 * @generated
	 */
	SpecificState basicGetStartingState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return basicGetEndingStateRef();'"
	 * @generated
	 */
	SpecificState basicGetEndingState();

} // SpecificStep
