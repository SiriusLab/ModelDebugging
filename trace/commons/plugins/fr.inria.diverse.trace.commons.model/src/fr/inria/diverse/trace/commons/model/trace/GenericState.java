/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.GenericState#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState()
 * @model
 * @generated
 */
public interface GenericState extends State {

	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.GenericValue}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.commons.model.trace.GenericValue#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getGenericState_Values()
	 * @see fr.inria.diverse.trace.commons.model.trace.GenericValue#getStates
	 * @model opposite="states"
	 * @generated
	 */
	EList<GenericValue> getValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final EList<Value> result = new org.eclipse.emf.common.util.BasicEList<Value>();\nresult.addAll(getValues());\nreturn result;'"
	 * @generated
	 */
	EList<Value> getValuesView();
} // GenericState
