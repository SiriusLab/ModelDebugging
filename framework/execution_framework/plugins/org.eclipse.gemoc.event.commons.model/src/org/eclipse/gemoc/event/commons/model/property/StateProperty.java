/**
 */
package org.eclipse.gemoc.event.commons.model.property;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.StateProperty#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getStateProperty()
 * @model abstract="true"
 * @generated
 */
public interface StateProperty<T> extends Property {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Object)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getStateProperty_Target()
	 * @model kind="reference"
	 * @generated
	 */
	T getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.StateProperty#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(T value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EStructuralFeature getFeature();

} // StateProperty
