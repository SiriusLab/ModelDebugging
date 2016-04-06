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
 * A representation of the model object '<em><b>Sequential Logical Step</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getSequentialLogicalStep()
 * @model
 * @generated
 */
public interface SequentialLogicalStep extends MSEOccurrence, LogicalStep {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList<MSEOccurrence> result = new org.eclipse.emf.common.util.BasicEList<MSEOccurrence>();\nresult.add(this);\nreturn result;'"
	 * @generated
	 */
	EList<MSEOccurrence> getMseOccurrences();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this;'"
	 * @generated
	 */
	LogicalStep getLogicalStep();
} // SequentialLogicalStep
