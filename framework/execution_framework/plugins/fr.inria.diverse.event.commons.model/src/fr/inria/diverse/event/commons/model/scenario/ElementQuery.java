/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.StateProperty;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.ElementQuery#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getElementQuery()
 * @model abstract="true"
 * @generated
 */
public interface ElementQuery<T, P extends StateProperty<T>> extends ElementProvider<T> {
	/**
	 * Returns the value of the '<em><b>Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query</em>' containment reference.
	 * @see #setQuery(StateProperty)
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#getElementQuery_Query()
	 * @model containment="true"
	 * @generated
	 */
	P getQuery();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.scenario.ElementQuery#getQuery <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' containment reference.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(P value);

} // ElementQuery
