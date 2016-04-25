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
package org.gemoc.executionframework.engine.mse.impl;

import org.eclipse.emf.ecore.EClass;

import org.gemoc.executionframework.engine.mse.MsePackage;
import org.gemoc.executionframework.engine.mse.SmallStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Small Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SmallStepImpl extends StepImpl implements SmallStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SmallStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.SMALL_STEP;
	}

} //SmallStepImpl
