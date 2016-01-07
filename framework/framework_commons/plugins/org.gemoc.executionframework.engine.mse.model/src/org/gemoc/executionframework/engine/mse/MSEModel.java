/**
 */
package org.gemoc.executionframework.engine.mse;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MSE Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.MSEModel#getOwnedMSEs <em>Owned MS Es</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getMSEModel()
 * @model
 * @generated
 */
public interface MSEModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Owned MS Es</b></em>' containment reference list.
	 * The list contents are of type {@link org.gemoc.executionframework.engine.mse.MSE}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned MS Es</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned MS Es</em>' containment reference list.
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getMSEModel_OwnedMSEs()
	 * @model containment="true"
	 * @generated
	 */
	EList<MSE> getOwnedMSEs();

} // MSEModel
