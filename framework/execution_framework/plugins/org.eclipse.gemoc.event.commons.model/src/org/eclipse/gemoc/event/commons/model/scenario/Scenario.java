/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.Scenario#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenario()
 * @model abstract="true"
 * @generated
 */
public interface Scenario<T extends ScenarioElement<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getScenario_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<T> getElements();

} // Scenario
