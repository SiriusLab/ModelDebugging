/**
 *  Copyright (c) 2016 Inria and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Inria - initial API and implementation
 * 
 */
package org.gemoc.executionframework.engine.mse;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parrallel Logical Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.ParrallelLogicalStep#getMseOccurrences <em>Mse Occurrences</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getParrallelLogicalStep()
 * @model
 * @generated
 */
public interface ParrallelLogicalStep extends LogicalStep {
	/**
	 * Returns the value of the '<em><b>Mse Occurrences</b></em>' containment reference list.
	 * The list contents are of type {@link org.gemoc.executionframework.engine.mse.MSEOccurrence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mse Occurrences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mse Occurrences</em>' containment reference list.
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getParrallelLogicalStep_MseOccurrences()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<MSEOccurrence> getMseOccurrences();

} // ParrallelLogicalStep
