/**
 */
package fr.inria.diverse.event.commons.model.arbiter;

import fr.inria.diverse.event.commons.model.property.Property;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arbiter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Arbiter#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Arbiter#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Arbiter#getInitialState <em>Initial State</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getArbiter()
 * @model abstract="true"
 * @generated
 */
public interface Arbiter<P extends Property, S extends State<P, T>, T extends Transition<P, S>> extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getArbiter_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<S> getStates();

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getArbiter_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<T> getTransitions();

	/**
	 * Returns the value of the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial State</em>' reference.
	 * @see #setInitialState(State)
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getArbiter_InitialState()
	 * @model
	 * @generated
	 */
	S getInitialState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.arbiter.Arbiter#getInitialState <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial State</em>' reference.
	 * @see #getInitialState()
	 * @generated
	 */
	void setInitialState(S value);

} // Arbiter
