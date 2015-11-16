/**
 */
package org.gemoc.execution.engine.mse.engine_mse;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MSE</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.gemoc.execution.engine.mse.engine_mse.Engine_msePackage#getMSE()
 * @model abstract="true"
 * @generated
 */
public interface MSE extends ENamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EObject getCaller();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EOperation getAction();

} // MSE
