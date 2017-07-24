/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;

import org.eclipse.gemoc.event.commons.model.property.StateProperty;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ElementQuery#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getElementQuery()
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
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getElementQuery_Query()
	 * @model containment="true"
	 * @generated
	 */
	P getQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ElementQuery#getQuery <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' containment reference.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(P value);

} // ElementQuery
