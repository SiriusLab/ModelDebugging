/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Reference Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue#getReferenceValue <em>Reference Value</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericReferenceValue()
 * @model
 * @generated
 */
public interface GenericReferenceValue extends GenericValue {
	/**
	 * Returns the value of the '<em><b>Reference Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Value</em>' reference.
	 * @see #setReferenceValue(EObject)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericReferenceValue_ReferenceValue()
	 * @model
	 * @generated
	 */
	EObject getReferenceValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue#getReferenceValue <em>Reference Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Value</em>' reference.
	 * @see #getReferenceValue()
	 * @generated
	 */
	void setReferenceValue(EObject value);

} // GenericReferenceValue
