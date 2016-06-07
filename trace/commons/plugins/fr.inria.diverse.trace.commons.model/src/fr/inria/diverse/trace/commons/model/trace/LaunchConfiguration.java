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
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getLanguageName <em>Language Name</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getAirdResourceURI <em>Aird Resource URI</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getMethodEntryPoint <em>Method Entry Point</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getModelEntryPoint <em>Model Entry Point</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getInitializationMethod <em>Initialization Method</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getInitializationArguments <em>Initialization Arguments</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getAddonExtensions <em>Addon Extensions</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration()
 * @model
 * @generated
 */
public interface LaunchConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Language Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language Name</em>' attribute.
	 * @see #setLanguageName(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_LanguageName()
	 * @model
	 * @generated
	 */
	String getLanguageName();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getLanguageName <em>Language Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language Name</em>' attribute.
	 * @see #getLanguageName()
	 * @generated
	 */
	void setLanguageName(String value);

	/**
	 * Returns the value of the '<em><b>Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource URI</em>' attribute.
	 * @see #setResourceURI(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_ResourceURI()
	 * @model
	 * @generated
	 */
	String getResourceURI();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getResourceURI <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource URI</em>' attribute.
	 * @see #getResourceURI()
	 * @generated
	 */
	void setResourceURI(String value);

	/**
	 * Returns the value of the '<em><b>Aird Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aird Resource URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aird Resource URI</em>' attribute.
	 * @see #setAirdResourceURI(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_AirdResourceURI()
	 * @model
	 * @generated
	 */
	String getAirdResourceURI();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getAirdResourceURI <em>Aird Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aird Resource URI</em>' attribute.
	 * @see #getAirdResourceURI()
	 * @generated
	 */
	void setAirdResourceURI(String value);

	/**
	 * Returns the value of the '<em><b>Method Entry Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Entry Point</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Entry Point</em>' attribute.
	 * @see #setMethodEntryPoint(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_MethodEntryPoint()
	 * @model
	 * @generated
	 */
	String getMethodEntryPoint();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getMethodEntryPoint <em>Method Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Entry Point</em>' attribute.
	 * @see #getMethodEntryPoint()
	 * @generated
	 */
	void setMethodEntryPoint(String value);

	/**
	 * Returns the value of the '<em><b>Model Entry Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Entry Point</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Entry Point</em>' attribute.
	 * @see #setModelEntryPoint(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_ModelEntryPoint()
	 * @model
	 * @generated
	 */
	String getModelEntryPoint();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getModelEntryPoint <em>Model Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Entry Point</em>' attribute.
	 * @see #getModelEntryPoint()
	 * @generated
	 */
	void setModelEntryPoint(String value);

	/**
	 * Returns the value of the '<em><b>Initialization Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialization Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initialization Method</em>' attribute.
	 * @see #setInitializationMethod(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_InitializationMethod()
	 * @model
	 * @generated
	 */
	String getInitializationMethod();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getInitializationMethod <em>Initialization Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialization Method</em>' attribute.
	 * @see #getInitializationMethod()
	 * @generated
	 */
	void setInitializationMethod(String value);

	/**
	 * Returns the value of the '<em><b>Initialization Arguments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialization Arguments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initialization Arguments</em>' attribute.
	 * @see #setInitializationArguments(String)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_InitializationArguments()
	 * @model
	 * @generated
	 */
	String getInitializationArguments();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration#getInitializationArguments <em>Initialization Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialization Arguments</em>' attribute.
	 * @see #getInitializationArguments()
	 * @generated
	 */
	void setInitializationArguments(String value);

	/**
	 * Returns the value of the '<em><b>Addon Extensions</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Addon Extensions</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Addon Extensions</em>' attribute list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getLaunchConfiguration_AddonExtensions()
	 * @model
	 * @generated
	 */
	EList<String> getAddonExtensions();

} // LaunchConfiguration
