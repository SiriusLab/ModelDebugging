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
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStates <em>States</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericValue()
 * @model abstract="true"
 * @generated
 */
public interface GenericValue extends Value {

	/**
	 * Returns the value of the '<em><b>States</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericState}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericValue_States()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericState#getValues
	 * @model opposite="values"
	 * @generated
	 */
	EList<GenericState> getStates();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final EList<State> result = new org.eclipse.emf.common.util.BasicEList<State>();\nresult.addAll(getStates());\nreturn result;'"
	 * @generated
	 */
	EList<State> getStatesView();
} // GenericValue
