/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.UnaryProperty#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.UnaryProperty#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUnaryProperty()
 * @model abstract="true"
 * @generated
 */
public interface UnaryProperty<P extends StateProperty<?>, T> extends StateProperty<T> {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gemoc.event.commons.model.property.UnaryOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gemoc.event.commons.model.property.UnaryOperator
	 * @see #setOperator(UnaryOperator)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUnaryProperty_Operator()
	 * @model required="true"
	 * @generated
	 */
	UnaryOperator getOperator();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.UnaryProperty#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see org.eclipse.gemoc.event.commons.model.property.UnaryOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(UnaryOperator value);

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
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUnaryProperty_Property()
	 * @model containment="true" required="true"
	 * @generated
	 */
	P getProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.UnaryProperty#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(P value);

} // UnaryProperty
