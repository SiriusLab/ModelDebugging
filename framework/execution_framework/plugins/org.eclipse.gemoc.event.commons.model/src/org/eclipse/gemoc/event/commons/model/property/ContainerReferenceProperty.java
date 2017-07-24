/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Reference Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.ContainerReferenceProperty#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getContainerReferenceProperty()
 * @model
 * @generated
 */
public interface ContainerReferenceProperty<P extends StateProperty<?>, T> extends StateProperty<T> {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference.
	 * @see #setProperty(StateProperty)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getContainerReferenceProperty_Property()
	 * @model containment="true"
	 * @generated
	 */
	P getProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.ContainerReferenceProperty#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(P value);

} // ContainerReferenceProperty
