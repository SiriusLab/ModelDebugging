/**
 */
package org.gemoc.execution.engine.mse.engine_mse;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.execution.engine.mse.engine_mse.LogicalStep#getMseOccurrences <em>Mse Occurrences</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.execution.engine.mse.engine_mse.Engine_msePackage#getLogicalStep()
 * @model
 * @generated
 */
public interface LogicalStep extends EObject {
	/**
	 * Returns the value of the '<em><b>Mse Occurrences</b></em>' reference list.
	 * The list contents are of type {@link org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence}.
	 * It is bidirectional and its opposite is '{@link org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence#getLogicalStep <em>Logical Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mse Occurrences</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mse Occurrences</em>' reference list.
	 * @see org.gemoc.execution.engine.mse.engine_mse.Engine_msePackage#getLogicalStep_MseOccurrences()
	 * @see org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence#getLogicalStep
	 * @model opposite="logicalStep" required="true"
	 * @generated
	 */
	EList<MSEOccurrence> getMseOccurrences();

} // LogicalStep
