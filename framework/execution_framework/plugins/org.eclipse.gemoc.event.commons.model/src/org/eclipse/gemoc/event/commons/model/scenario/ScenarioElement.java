/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.event.commons.model.property.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getPreviousElements <em>Previous Elements</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getNextElements <em>Next Elements</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioElement()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioElement<P extends Property> extends EObject {
	/**
	 * Returns the value of the '<em><b>Previous Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement}&lt;P>.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getNextElements <em>Next Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Elements</em>' reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioElement_PreviousElements()
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getNextElements
	 * @model opposite="nextElements"
	 * @generated
	 */
	EList<ScenarioElement<P>> getPreviousElements();

	/**
	 * Returns the value of the '<em><b>Next Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement}&lt;P>.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getPreviousElements <em>Previous Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Elements</em>' reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioElement_NextElements()
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getPreviousElements
	 * @model opposite="previousElements"
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
	 * @see #setGuard(Property)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenarioElement_Guard()
	 * @model containment="true"
	 * @generated
	 */
	P getGuard();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(P value);

} // ScenarioElement
