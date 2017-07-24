/**
 */
package fr.inria.diverse.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Release Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getLeftFormula <em>Left Formula</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getRightFormula <em>Right Formula</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getReleaseProperty()
 * @model abstract="true"
 * @generated
 */
public interface ReleaseProperty<P extends AbstractProperty> extends TemporalProperty {
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
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getReleaseProperty_LeftFormula()
	 * @model containment="true"
	 * @generated
	 */
	P getLeftFormula();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getLeftFormula <em>Left Formula</em>}' containment reference.
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
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getReleaseProperty_RightFormula()
	 * @model containment="true"
	 * @generated
	 */
	P getRightFormula();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getRightFormula <em>Right Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Formula</em>' containment reference.
	 * @see #getRightFormula()
	 * @generated
	 */
	void setRightFormula(P value);

} // ReleaseProperty
