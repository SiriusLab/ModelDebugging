/**
 */
package fr.inria.diverse.event.commons.model.arbiter;

import fr.inria.diverse.event.commons.model.property.Property;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getSource <em>Source</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getTarget <em>Target</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getTransition()
 * @model abstract="true"
 * @generated
 */
public interface Transition<P extends Property, S extends State<P, ?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.event.commons.model.arbiter.State#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(State)
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getTransition_Source()
	 * @see fr.inria.diverse.event.commons.model.arbiter.State#getOutgoingTransitions
	 * @model opposite="outgoingTransitions" required="true"
	 * @generated
	 */
	S getSource();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(S value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.event.commons.model.arbiter.State#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(State)
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getTransition_Target()
	 * @see fr.inria.diverse.event.commons.model.arbiter.State#getIncomingTransitions
	 * @model opposite="incomingTransitions" required="true"
	 * @generated
	 */
	S getTarget();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(S value);

	/**
	 * Returns the value of the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guard</em>' containment reference.
	 * @see #setGuard(Property)
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getTransition_Guard()
	 * @model containment="true"
	 * @generated
	 */
	P getGuard();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(P value);

} // Transition
