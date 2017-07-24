/**
 */
package org.eclipse.gemoc.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Negation Temporal Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.NegationTemporalProperty#getFormula <em>Formula</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getNegationTemporalProperty()
 * @model abstract="true"
 * @generated
 */
public interface NegationTemporalProperty<P extends TemporalProperty> extends TemporalProperty {
	/**
	 * Returns the value of the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formula</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formula</em>' containment reference.
	 * @see #setFormula(TemporalProperty)
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#getNegationTemporalProperty_Formula()
	 * @model containment="true"
	 * @generated
	 */
	P getFormula();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.property.NegationTemporalProperty#getFormula <em>Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formula</em>' containment reference.
	 * @see #getFormula()
	 * @generated
	 */
	void setFormula(P value);

} // NegationTemporalProperty
