/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.StateProperty;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getNextElements <em>Next Elements</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioElement()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioElement<P extends StateProperty<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Next Elements</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement}&lt;P>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Elements</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioElement_NextElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ScenarioElement<P>> getNextElements();

	/**
	 * Returns the value of the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guard</em>' containment reference.
	 * @see #setGuard(StateProperty)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getScenarioElement_Guard()
	 * @model containment="true"
	 * @generated
	 */
	P getGuard();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(P value);

} // ScenarioElement
