/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getTracedObjects <em>Traced Objects</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentState <em>Current State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentStep <em>Current Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTrace()
 * @model
 * @generated
 */
public interface Trace extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTrace_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionState> getStates();

	/**
	 * Returns the value of the '<em><b>Traced Objects</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traced Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traced Objects</em>' containment reference list.
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTrace_TracedObjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<TracedObject> getTracedObjects();

	/**
	 * Returns the value of the '<em><b>Current State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current State</em>' reference.
	 * @see #setCurrentState(ExecutionState)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTrace_CurrentState()
	 * @model derived="true"
	 * @generated
	 */
	ExecutionState getCurrentState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentState <em>Current State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current State</em>' reference.
	 * @see #getCurrentState()
	 * @generated
	 */
	void setCurrentState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Current Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Step</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Step</em>' reference.
	 * @see #setCurrentStep(Step)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getTrace_CurrentStep()
	 * @model
	 * @generated
	 */
	Step getCurrentStep();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Trace#getCurrentStep <em>Current Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Step</em>' reference.
	 * @see #getCurrentStep()
	 * @generated
	 */
	void setCurrentStep(Step value);

} // Trace
