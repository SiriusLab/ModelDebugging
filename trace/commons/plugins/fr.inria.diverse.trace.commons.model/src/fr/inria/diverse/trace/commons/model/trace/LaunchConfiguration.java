/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Launch Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getLaunchconfigurationparameter <em>Launchconfigurationparameter</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration()
 * @model
 * @generated
 */
public interface LaunchConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Launchconfigurationparameter</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Launchconfigurationparameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Launchconfigurationparameter</em>' containment reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_Launchconfigurationparameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<LaunchConfigurationParameter> getLaunchconfigurationparameter();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // LaunchConfiguration
