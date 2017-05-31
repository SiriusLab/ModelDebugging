/**
 */
package opsemanticsview;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution To AS Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.ExecutionToASEntry#getExecutionClass <em>Execution Class</em>}</li>
 *   <li>{@link opsemanticsview.ExecutionToASEntry#getASclass <em>ASclass</em>}</li>
 * </ul>
 *
 * @see opsemanticsview.OpsemanticsviewPackage#getExecutionToASEntry()
 * @model
 * @generated
 */
public interface ExecutionToASEntry extends EObject {
	/**
	 * Returns the value of the '<em><b>Execution Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Class</em>' reference.
	 * @see #setExecutionClass(EClass)
	 * @see opsemanticsview.OpsemanticsviewPackage#getExecutionToASEntry_ExecutionClass()
	 * @model required="true"
	 * @generated
	 */
	EClass getExecutionClass();

	/**
	 * Sets the value of the '{@link opsemanticsview.ExecutionToASEntry#getExecutionClass <em>Execution Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Class</em>' reference.
	 * @see #getExecutionClass()
	 * @generated
	 */
	void setExecutionClass(EClass value);

	/**
	 * Returns the value of the '<em><b>ASclass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ASclass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ASclass</em>' reference.
	 * @see #setASclass(EClass)
	 * @see opsemanticsview.OpsemanticsviewPackage#getExecutionToASEntry_ASclass()
	 * @model required="true"
	 * @generated
	 */
	EClass getASclass();

	/**
	 * Sets the value of the '{@link opsemanticsview.ExecutionToASEntry#getASclass <em>ASclass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ASclass</em>' reference.
	 * @see #getASclass()
	 * @generated
	 */
	void setASclass(EClass value);

} // ExecutionToASEntry
