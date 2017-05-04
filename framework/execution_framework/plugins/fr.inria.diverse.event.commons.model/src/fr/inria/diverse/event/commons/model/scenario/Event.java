/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.Event#getTargetProvider <em>Target Provider</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getEvent()
 * @model abstract="true"
 * @generated
 */
public interface Event<T> extends EObject {
	/**
	 * Returns the value of the '<em><b>Target Provider</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Provider</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Provider</em>' containment reference.
	 * @see #setTargetProvider(ElementProvider)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getEvent_TargetProvider()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ElementProvider<T> getTargetProvider();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.Event#getTargetProvider <em>Target Provider</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Provider</em>' containment reference.
	 * @see #getTargetProvider()
	 * @generated
	 */
	void setTargetProvider(ElementProvider<T> value);

} // Event
