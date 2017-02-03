/**
 */
package fr.inria.diverse.trace.commons.model.trace;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef <em>Starting State Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef <em>Ending State Ref</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericStep()
 * @model abstract="true"
 * @generated
 */
public interface GenericStep extends Step<GenericState> {
	/**
	 * Returns the value of the '<em><b>Starting State Ref</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedStepsRef <em>Started Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting State Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting State Ref</em>' reference.
	 * @see #setStartingStateRef(GenericState)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericStep_StartingStateRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedStepsRef
	 * @model opposite="startedStepsRef" required="true"
	 * @generated
	 */
	GenericState getStartingStateRef();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef <em>Starting State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting State Ref</em>' reference.
	 * @see #getStartingStateRef()
	 * @generated
	 */
	void setStartingStateRef(GenericState value);

	/**
	 * Returns the value of the '<em><b>Ending State Ref</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedStepsRef <em>Ended Steps Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending State Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending State Ref</em>' reference.
	 * @see #setEndingStateRef(GenericState)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericStep_EndingStateRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedStepsRef
	 * @model opposite="endedStepsRef"
	 * @generated
	 */
	GenericState getEndingStateRef();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef <em>Ending State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending State Ref</em>' reference.
	 * @see #getEndingStateRef()
	 * @generated
	 */
	void setEndingStateRef(GenericState value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	GenericState getStartingState();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	GenericState getEndingState();

} // GenericStep
