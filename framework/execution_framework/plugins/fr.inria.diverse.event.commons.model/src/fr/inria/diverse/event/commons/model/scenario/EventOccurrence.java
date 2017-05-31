/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Occurrence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getEvent <em>Event</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getEventOccurrence()
 * @model abstract="true"
 * @generated
 */
public interface EventOccurrence<E extends Event<?>, P extends Property> extends ScenarioElement<P> {
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
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getEventOccurrence_Event()
	 * @model containment="true"
	 * @generated
	 */
	E getEvent();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(E value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(int)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getEventOccurrence_Time()
	 * @model
	 * @generated
	 */
	int getTime();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(int value);

} // EventOccurrence
