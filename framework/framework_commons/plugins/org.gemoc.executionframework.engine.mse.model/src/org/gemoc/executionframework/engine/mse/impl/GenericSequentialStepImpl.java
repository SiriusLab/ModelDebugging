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

import org.gemoc.executionframework.engine.mse.GenericSequentialStep;
import org.gemoc.executionframework.engine.mse.MsePackage;
import org.gemoc.executionframework.engine.mse.Step;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Sequential Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GenericSequentialStepImpl extends SequentialStepImpl<Step> implements GenericSequentialStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericSequentialStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.GENERIC_SEQUENTIAL_STEP;
	}

} //GenericSequentialStepImpl
