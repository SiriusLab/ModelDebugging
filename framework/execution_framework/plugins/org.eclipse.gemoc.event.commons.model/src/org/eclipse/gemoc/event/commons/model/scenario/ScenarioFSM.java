/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.gemoc.event.commons.model.property.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FSM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getStates <em>States</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getAcceptingStates <em>Accepting States</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioFSM<P extends Property, E extends Event, S extends ScenarioFSMState<E, T>, T extends ScenarioFSMTransition<P, S>> extends ScenarioElement<P> {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<S> getStates();

	/**
	 * Returns the value of the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial State</em>' reference.
	 * @see #setInitialState(ScenarioFSMState)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_InitialState()
	 * @model required="true"
	 * @generated
	 */
	S getInitialState();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getInitialState <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial State</em>' reference.
	 * @see #getInitialState()
	 * @generated
	 */
	void setInitialState(S value);

	/**
	 * Returns the value of the '<em><b>Accepting States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accepting States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accepting States</em>' reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_AcceptingStates()
	 * @model
	 * @generated
	 */
	EList<S> getAcceptingStates();

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<T> getTransitions();

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
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ScenarioFSM
