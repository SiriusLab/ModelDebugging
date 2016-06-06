/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Trace#getRootStep <em>Root Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getTrace()
 * @model
 * @generated
 */
public interface Trace<StepSubType> extends EObject {
	/**
	 * Returns the value of the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Step</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Step</em>' containment reference.
	 * @see #setRootStep(Object)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getTrace_RootStep()
	 * @model kind="reference" containment="true" required="true"
	 * @generated
	 */
	StepSubType getRootStep();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getRootStep <em>Root Step</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Step</em>' containment reference.
	 * @see #getRootStep()
	 * @generated
	 */
	void setRootStep(StepSubType value);

	/**
	 * Returns the value of the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Launchconfiguration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Launchconfiguration</em>' containment reference.
	 * @see #setLaunchconfiguration(LaunchConfiguration)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getTrace_Launchconfiguration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	LaunchConfiguration getLaunchconfiguration();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Launchconfiguration</em>' containment reference.
	 * @see #getLaunchconfiguration()
	 * @generated
	 */
	void setLaunchconfiguration(LaunchConfiguration value);

} // Trace
