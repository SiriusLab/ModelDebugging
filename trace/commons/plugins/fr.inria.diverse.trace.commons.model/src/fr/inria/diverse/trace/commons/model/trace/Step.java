/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence <em>Mseoccurrence</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep()
 * @model abstract="true"
 * @generated
 */
public interface Step extends EObject {
	/**
	 * Returns the value of the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mseoccurrence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mseoccurrence</em>' containment reference.
	 * @see #setMseoccurrence(MSEOccurrence)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep_Mseoccurrence()
	 * @model containment="true"
	 * @generated
	 */
	MSEOccurrence getMseoccurrence();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence <em>Mseoccurrence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mseoccurrence</em>' containment reference.
	 * @see #getMseoccurrence()
	 * @generated
	 */
	void setMseoccurrence(MSEOccurrence value);

} // Step
