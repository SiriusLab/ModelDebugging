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
 * A representation of the model object '<em><b>Big Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.BigStep#getSubSteps <em>Sub Steps</em>}</li>
 * </ul>
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getBigStep()
 * @model abstract="true"
 * @generated
 */
public interface BigStep<StepSubtype extends Step> extends Step {
	/**
	 * Returns the value of the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Steps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Steps</em>' containment reference list.
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getBigStep_SubSteps()
	 * @model containment="true"
	 * @generated
	 */
	EList<StepSubtype> getSubSteps();

} // BigStep
