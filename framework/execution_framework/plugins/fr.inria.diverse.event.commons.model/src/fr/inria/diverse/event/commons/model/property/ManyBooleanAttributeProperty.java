/**
 */
package fr.inria.diverse.event.commons.model.property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Boolean Attribute Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getQuantifier <em>Quantifier</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#isValue <em>Value</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyBooleanAttributeProperty()
 * @model
 * @generated
 */
public interface ManyBooleanAttributeProperty<T> extends ClassProperty<T> {
	/**
	 * Returns the value of the '<em><b>Quantifier</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.property.Quantifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantifier</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Quantifier
	 * @see #setQuantifier(Quantifier)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyBooleanAttributeProperty_Quantifier()
	 * @model
	 * @generated
	 */
	Quantifier getQuantifier();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getQuantifier <em>Quantifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantifier</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Quantifier
	 * @see #getQuantifier()
	 * @generated
	 */
	void setQuantifier(Quantifier value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(boolean)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyBooleanAttributeProperty_Value()
	 * @model
	 * @generated
	 */
	boolean isValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#isValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isValue()
	 * @generated
	 */
	void setValue(boolean value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.property.Operator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Operator
	 * @see #setOperator(Operator)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyBooleanAttributeProperty_Operator()
	 * @model
	 * @generated
	 */
	Operator getOperator();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Operator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(Operator value);

} // ManyBooleanAttributeProperty
