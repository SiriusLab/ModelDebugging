/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Reference Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.ManyReferenceProperty#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.ManyReferenceProperty#getQuantifier <em>Quantifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getManyReferenceProperty()
 * @model abstract="true"
 * @generated
 */
public interface ManyReferenceProperty<P extends StateProperty<?>, T> extends StateProperty<T> {
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
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getManyReferenceProperty_Property()
	 * @model containment="true" required="true"
	 * @generated
	 */
	P getProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.ManyReferenceProperty#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(P value);

	/**
	 * Returns the value of the '<em><b>Quantifier</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gemoc.event.commons.model.property.Quantifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantifier</em>' attribute.
	 * @see org.eclipse.gemoc.event.commons.model.property.Quantifier
	 * @see #setQuantifier(Quantifier)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getManyReferenceProperty_Quantifier()
	 * @model
	 * @generated
	 */
	Quantifier getQuantifier();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.ManyReferenceProperty#getQuantifier <em>Quantifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantifier</em>' attribute.
	 * @see org.eclipse.gemoc.event.commons.model.property.Quantifier
	 * @see #getQuantifier()
	 * @generated
	 */
	void setQuantifier(Quantifier value);

} // ManyReferenceProperty
