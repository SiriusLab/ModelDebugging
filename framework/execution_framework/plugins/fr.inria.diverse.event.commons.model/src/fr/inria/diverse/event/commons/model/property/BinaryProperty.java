/**
 */
package fr.inria.diverse.event.commons.model.property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getOperator <em>Operator</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getLeft <em>Left</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getBinaryProperty()
 * @model abstract="true"
 * @generated
 */
public interface BinaryProperty<P extends ClassProperty<?>, T> extends ClassProperty<T> {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.property.BooleanOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanOperator
	 * @see #setOperator(BooleanOperator)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getBinaryProperty_Operator()
	 * @model required="true"
	 * @generated
	 */
	BooleanOperator getOperator();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(BooleanOperator value);

	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(ClassProperty)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getBinaryProperty_Left()
	 * @model containment="true" required="true"
	 * @generated
	 */
	P getLeft();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(P value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(ClassProperty)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getBinaryProperty_Right()
	 * @model containment="true" required="true"
	 * @generated
	 */
	P getRight();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(P value);

} // BinaryProperty
