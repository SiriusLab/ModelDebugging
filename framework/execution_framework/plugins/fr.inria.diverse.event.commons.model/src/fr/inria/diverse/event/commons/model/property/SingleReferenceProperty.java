/**
 */
package fr.inria.diverse.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Reference Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.SingleReferenceProperty#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getSingleReferenceProperty()
 * @model abstract="true"
 * @generated
 */
public interface SingleReferenceProperty<P extends ClassProperty<?>, T> extends ClassProperty<T> {
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
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getSingleReferenceProperty_Property()
	 * @model containment="true"
	 * @generated
	 */
	P getProperty();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.SingleReferenceProperty#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(P value);

} // SingleReferenceProperty
