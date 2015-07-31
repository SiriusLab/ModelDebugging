/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getValueSequences <em>Value Sequences</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getOriginalObject <em>Original Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTracedObject()
 * @model
 * @generated
 */
public interface TracedObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Value Sequences</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Sequences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Sequences</em>' containment reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTracedObject_ValueSequences()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueSequence> getValueSequences();

	/**
	 * Returns the value of the '<em><b>Original Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Object</em>' reference.
	 * @see #setOriginalObject(EObject)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTracedObject_OriginalObject()
	 * @model
	 * @generated
	 */
	EObject getOriginalObject();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject#getOriginalObject <em>Original Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Object</em>' reference.
	 * @see #getOriginalObject()
	 * @generated
	 */
	void setOriginalObject(EObject value);

} // TracedObject
