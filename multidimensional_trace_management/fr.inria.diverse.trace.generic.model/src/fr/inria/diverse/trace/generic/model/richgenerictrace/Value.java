/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getExecutionStates <em>Execution States</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue <em>Next Value</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue <em>Previous Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getValue()
 * @model
 * @generated
 */
public interface Value extends EObject {
	/**
	 * Returns the value of the '<em><b>Execution States</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState}.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution States</em>' reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getValue_ExecutionStates()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getValues
	 * @model opposite="values"
	 * @generated
	 */
	EList<ExecutionState> getExecutionStates();

	/**
	 * Returns the value of the '<em><b>Next Value</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue <em>Previous Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Value</em>' reference.
	 * @see #setNextValue(Value)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getValue_NextValue()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue
	 * @model opposite="previousValue" derived="true"
	 * @generated
	 */
	Value getNextValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue <em>Next Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Value</em>' reference.
	 * @see #getNextValue()
	 * @generated
	 */
	void setNextValue(Value value);

	/**
	 * Returns the value of the '<em><b>Previous Value</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue <em>Next Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Value</em>' reference.
	 * @see #setPreviousValue(Value)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getValue_PreviousValue()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getNextValue
	 * @model opposite="nextValue" derived="true"
	 * @generated
	 */
	Value getPreviousValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Value#getPreviousValue <em>Previous Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous Value</em>' reference.
	 * @see #getPreviousValue()
	 * @generated
	 */
	void setPreviousValue(Value value);

} // Value
