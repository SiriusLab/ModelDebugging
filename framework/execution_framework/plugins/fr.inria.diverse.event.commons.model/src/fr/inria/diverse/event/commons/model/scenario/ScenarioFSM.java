/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.StateProperty;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FSM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getAcceptingStates <em>Accepting States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getTransitions <em>Transitions</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioFSM()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioFSM<P extends StateProperty<?>, E extends Event<?>, S extends ScenarioFSMState<E, T>, T extends ScenarioFSMTransition<P, S>> extends ScenarioElement<P> {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_States()
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
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_InitialState()
	 * @model required="true"
	 * @generated
	 */
	S getInitialState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getInitialState <em>Initial State</em>}' reference.
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
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_AcceptingStates()
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
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioFSM_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<T> getTransitions();

} // ScenarioFSM
