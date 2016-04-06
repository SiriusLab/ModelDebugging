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

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.MsePackage;
import org.gemoc.executionframework.engine.mse.SequentialLogicalStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequential Logical Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SequentialLogicalStepImpl extends MSEOccurrenceImpl implements SequentialLogicalStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequentialLogicalStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.SEQUENTIAL_LOGICAL_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalStep getLogicalStep() {
		return this;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MSEOccurrence> getMseOccurrences() {
		EList<MSEOccurrence> result = new org.eclipse.emf.common.util.BasicEList<MSEOccurrence>();
		result.add(this);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == MSEOccurrence.class) {
			switch (baseOperationID) {
				case MsePackage.MSE_OCCURRENCE___GET_LOGICAL_STEP: return MsePackage.SEQUENTIAL_LOGICAL_STEP___GET_LOGICAL_STEP;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == LogicalStep.class) {
			switch (baseOperationID) {
				case MsePackage.LOGICAL_STEP___GET_MSE_OCCURRENCES: return MsePackage.SEQUENTIAL_LOGICAL_STEP___GET_MSE_OCCURRENCES;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case MsePackage.SEQUENTIAL_LOGICAL_STEP___GET_MSE_OCCURRENCES:
				return getMseOccurrences();
			case MsePackage.SEQUENTIAL_LOGICAL_STEP___GET_LOGICAL_STEP:
				return getLogicalStep();
		}
		return super.eInvoke(operationID, arguments);
	}

} //SequentialLogicalStepImpl
