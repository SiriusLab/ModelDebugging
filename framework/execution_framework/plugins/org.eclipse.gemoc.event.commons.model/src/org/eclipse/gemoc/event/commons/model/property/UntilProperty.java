/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Until Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.UntilProperty#getLeftFormula <em>Left Formula</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.UntilProperty#getRightFormula <em>Right Formula</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUntilProperty()
 * @model abstract="true"
 * @generated
 */
public interface UntilProperty<P extends AbstractProperty> extends TemporalProperty {
	/**
	 * Returns the value of the '<em><b>Left Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Formula</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Formula</em>' containment reference.
	 * @see #setLeftFormula(AbstractProperty)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUntilProperty_LeftFormula()
	 * @model containment="true"
	 * @generated
	 */
	P getLeftFormula();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.UntilProperty#getLeftFormula <em>Left Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Formula</em>' containment reference.
	 * @see #getLeftFormula()
	 * @generated
	 */
	void setLeftFormula(P value);

	/**
	 * Returns the value of the '<em><b>Right Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Formula</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Formula</em>' containment reference.
	 * @see #setRightFormula(AbstractProperty)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getUntilProperty_RightFormula()
	 * @model containment="true"
	 * @generated
	 */
	P getRightFormula();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.UntilProperty#getRightFormula <em>Right Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Formula</em>' containment reference.
	 * @see #getRightFormula()
	 * @generated
	 */
	void setRightFormula(P value);

} // UntilProperty
