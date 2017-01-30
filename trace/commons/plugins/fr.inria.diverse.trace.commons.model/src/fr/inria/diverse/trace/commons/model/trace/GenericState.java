/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValuesRef <em>Values Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getStartedStepsRef <em>Started Steps Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getEndedStepsRef <em>Ended Steps Ref</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState()
 * @model
 * @generated
 */
public interface GenericState extends State<GenericStep, GenericValue> {

	/**
	 * Returns the value of the '<em><b>Values Ref</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericValue}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStatesRef <em>States Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values Ref</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState_ValuesRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue#getStatesRef
	 * @model opposite="statesRef"
	 * @generated
	 */
	EList<GenericValue> getValuesRef();

	/**
	 * Returns the value of the '<em><b>Started Steps Ref</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericStep}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef <em>Starting State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Started Steps Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Started Steps Ref</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState_StartedStepsRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getStartingStateRef
	 * @model opposite="startingStateRef"
	 * @generated
	 */
	EList<GenericStep> getStartedStepsRef();

	/**
	 * Returns the value of the '<em><b>Ended Steps Ref</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericStep}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef <em>Ending State Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ended Steps Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ended Steps Ref</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState_EndedStepsRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericStep#getEndingStateRef
	 * @model opposite="endingStateRef"
	 * @generated
	 */
	EList<GenericStep> getEndedStepsRef();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getValuesRef();'"
	 * @generated
	 */
	EList<GenericValue> getValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getStartedStepsRef();'"
	 * @generated
	 */
	EList<GenericStep> getStartedSteps();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getEndedStepsRef();'"
	 * @generated
	 */
	EList<GenericStep> getEndedSteps();
} // GenericState
