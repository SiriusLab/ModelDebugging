/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.State#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getState()
 * @model abstract="true"
 * @generated
 */
public interface State extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.Value}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.Value#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getState_Value()
	 * @see fr.inria.diverse.trace.commons.model.trace.Value#getState
	 * @model opposite="state"
	 * @generated
	 */
	EList<Value> getValue();

} // State
