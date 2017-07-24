/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.PropertyReference#getReferencedProperty <em>Referenced Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getPropertyReference()
 * @model
 * @generated
 */
public interface PropertyReference<P extends Property> extends Property {
	/**
	 * Returns the value of the '<em><b>Referenced Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Property</em>' reference.
	 * @see #setReferencedProperty(Property)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getPropertyReference_ReferencedProperty()
	 * @model
	 * @generated
	 */
	P getReferencedProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.PropertyReference#getReferencedProperty <em>Referenced Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Property</em>' reference.
	 * @see #getReferencedProperty()
	 * @generated
	 */
	void setReferencedProperty(P value);

} // PropertyReference
