/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.event.commons.model.property.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FSM Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSMTransition()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioFSMTransition<P extends Property, S extends ScenarioFSMState<?, ?>> extends EObject {
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
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSMTransition_Guard()
	 * @model containment="true"
	 * @generated
	 */
	P getGuard();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(P value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(ScenarioFSMState)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSMTransition_Source()
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState#getOutgoingTransitions
	 * @model opposite="outgoingTransitions" required="true"
	 * @generated
	 */
	S getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(S value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(ScenarioFSMState)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSMTransition_Target()
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState#getIncomingTransitions
	 * @model opposite="incomingTransitions" required="true"
	 * @generated
	 */
	S getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(S value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSMTransition_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ScenarioFSMTransition
