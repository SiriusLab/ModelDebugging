/**
 */
package fr.inria.diverse.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Integer Attribute Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getQuantifier <em>Quantifier</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getValue <em>Value</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyIntegerAttributeProperty()
 * @model
 * @generated
 */
public interface ManyIntegerAttributeProperty<T> extends StateProperty<T> {
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
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyIntegerAttributeProperty_Quantifier()
	 * @model
	 * @generated
	 */
	Quantifier getQuantifier();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getQuantifier <em>Quantifier</em>}' attribute.
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
	 * @see #setValue(int)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyIntegerAttributeProperty_Value()
	 * @model
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.property.ComparisonOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.ComparisonOperator
	 * @see #setOperator(ComparisonOperator)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getManyIntegerAttributeProperty_Operator()
	 * @model
	 * @generated
	 */
	ComparisonOperator getOperator();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.ComparisonOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(ComparisonOperator value);

} // ManyIntegerAttributeProperty
