/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Value#getState <em>State</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getValue()
 * @model abstract="true"
 * @generated
 */
public interface Value extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.State}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.State#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getValue_State()
	 * @see fr.inria.diverse.trace.commons.model.trace.State#getValue
	 * @model opposite="value"
	 * @generated
	 */
	EList<State> getState();

} // Value
