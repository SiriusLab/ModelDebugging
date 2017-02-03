/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStatesRef <em>States Ref</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericValue()
 * @model abstract="true"
 * @generated
 */
public interface GenericValue extends Value<GenericState> {
	/**
	 * Returns the value of the '<em><b>States Ref</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericState}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValuesRef <em>Values Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States Ref</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericValue_StatesRef()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getValuesRef
	 * @model opposite="valuesRef"
	 * @generated
	 */
	EList<GenericState> getStatesRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getStatesRef();'"
	 * @generated
	 */
	EList<GenericState> getStates();

} // GenericValue
