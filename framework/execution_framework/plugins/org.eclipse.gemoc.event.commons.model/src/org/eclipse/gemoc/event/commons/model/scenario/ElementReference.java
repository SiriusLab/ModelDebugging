/**
 */
package org.eclipse.gemoc.event.commons.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.ElementReference#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getElementReference()
 * @model abstract="true"
 * @generated
 */
public interface ElementReference<T> extends ElementProvider<T> {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(Object)
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#getElementReference_Element()
	 * @model kind="reference"
	 * @generated
	 */
	T getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.scenario.ElementReference#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(T value);

} // ElementReference
