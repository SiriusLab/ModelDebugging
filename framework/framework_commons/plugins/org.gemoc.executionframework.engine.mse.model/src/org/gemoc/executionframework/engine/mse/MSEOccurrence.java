/**
 */
package org.gemoc.executionframework.engine.mse;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MSE Occurrence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getMse <em>Mse</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getResult <em>Result</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getLogicalStep <em>Logical Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.engine.mse.Engine_msePackage#getMSEOccurrence()
 * @model
 * @generated
 */
public interface MSEOccurrence extends EObject {
	/**
	 * Returns the value of the '<em><b>Mse</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mse</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mse</em>' reference.
	 * @see #setMse(MSE)
	 * @see org.gemoc.executionframework.engine.mse.Engine_msePackage#getMSEOccurrence_Mse()
	 * @model required="true"
	 * @generated
	 */
	MSE getMse();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getMse <em>Mse</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mse</em>' reference.
	 * @see #getMse()
	 * @generated
	 */
	void setMse(MSE value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Object}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' attribute list.
	 * @see org.gemoc.executionframework.engine.mse.Engine_msePackage#getMSEOccurrence_Parameters()
	 * @model
	 * @generated
	 */
	EList<Object> getParameters();

	/**
	 * Returns the value of the '<em><b>Result</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Object}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' attribute list.
	 * @see org.gemoc.executionframework.engine.mse.Engine_msePackage#getMSEOccurrence_Result()
	 * @model
	 * @generated
	 */
	EList<Object> getResult();

	/**
	 * Returns the value of the '<em><b>Logical Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.gemoc.executionframework.engine.mse.LogicalStep#getMseOccurrences <em>Mse Occurrences</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Step</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Step</em>' reference.
	 * @see #setLogicalStep(LogicalStep)
	 * @see org.gemoc.executionframework.engine.mse.Engine_msePackage#getMSEOccurrence_LogicalStep()
	 * @see org.gemoc.executionframework.engine.mse.LogicalStep#getMseOccurrences
	 * @model opposite="mseOccurrences"
	 * @generated
	 */
	LogicalStep getLogicalStep();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getLogicalStep <em>Logical Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Logical Step</em>' reference.
	 * @see #getLogicalStep()
	 * @generated
	 */
	void setLogicalStep(LogicalStep value);

} // MSEOccurrence
