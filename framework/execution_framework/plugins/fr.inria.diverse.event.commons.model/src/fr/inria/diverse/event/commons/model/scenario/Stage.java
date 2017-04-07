/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.ClassProperty;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.Stage#getProperty <em>Property</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.Stage#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getStage()
 * @model abstract="true"
 * @generated
 */
public interface Stage<E extends Event<?>, P extends ClassProperty<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference.
	 * @see #setProperty(ClassProperty)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getStage_Property()
	 * @model containment="true"
	 * @generated
	 */
	P getProperty();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.Stage#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(P value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(Event)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getStage_Event()
	 * @model containment="true"
	 * @generated
	 */
	E getEvent();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.Stage#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(E value);

} // Stage
