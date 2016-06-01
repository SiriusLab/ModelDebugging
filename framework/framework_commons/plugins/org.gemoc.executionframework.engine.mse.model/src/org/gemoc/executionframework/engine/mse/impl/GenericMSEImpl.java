/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.executionframework.engine.mse.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.gemoc.executionframework.engine.mse.GenericMSE;
import org.gemoc.executionframework.engine.mse.MsePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Generic MSE</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl#getCallerReference <em>Caller Reference</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl#getActionReference <em>Action Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericMSEImpl extends MSEImpl implements GenericMSE {
	/**
	 * The cached value of the '{@link #getCallerReference() <em>Caller Reference</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getCallerReference()
	 * @generated
	 * @ordered
	 */
	protected EObject callerReference;

	/**
	 * The cached value of the '{@link #getActionReference() <em>Action Reference</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getActionReference()
	 * @generated
	 * @ordered
	 */
	protected EOperation actionReference;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericMSEImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.GENERIC_MSE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getCallerReference() {
		if (callerReference != null && callerReference.eIsProxy()) {
			InternalEObject oldCallerReference = (InternalEObject)callerReference;
			callerReference = eResolveProxy(oldCallerReference);
			if (callerReference != oldCallerReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MsePackage.GENERIC_MSE__CALLER_REFERENCE, oldCallerReference, callerReference));
			}
		}
		return callerReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetCallerReference() {
		return callerReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallerReference(EObject newCallerReference) {
		EObject oldCallerReference = callerReference;
		callerReference = newCallerReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.GENERIC_MSE__CALLER_REFERENCE, oldCallerReference, callerReference));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getActionReference() {
		if (actionReference != null && actionReference.eIsProxy()) {
			InternalEObject oldActionReference = (InternalEObject)actionReference;
			actionReference = (EOperation)eResolveProxy(oldActionReference);
			if (actionReference != oldActionReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MsePackage.GENERIC_MSE__ACTION_REFERENCE, oldActionReference, actionReference));
			}
		}
		return actionReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation basicGetActionReference() {
		return actionReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionReference(EOperation newActionReference) {
		EOperation oldActionReference = actionReference;
		actionReference = newActionReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.GENERIC_MSE__ACTION_REFERENCE, oldActionReference, actionReference));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MsePackage.GENERIC_MSE__CALLER_REFERENCE:
				if (resolve) return getCallerReference();
				return basicGetCallerReference();
			case MsePackage.GENERIC_MSE__ACTION_REFERENCE:
				if (resolve) return getActionReference();
				return basicGetActionReference();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MsePackage.GENERIC_MSE__CALLER_REFERENCE:
				setCallerReference((EObject)newValue);
				return;
			case MsePackage.GENERIC_MSE__ACTION_REFERENCE:
				setActionReference((EOperation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MsePackage.GENERIC_MSE__CALLER_REFERENCE:
				setCallerReference((EObject)null);
				return;
			case MsePackage.GENERIC_MSE__ACTION_REFERENCE:
				setActionReference((EOperation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MsePackage.GENERIC_MSE__CALLER_REFERENCE:
				return callerReference != null;
			case MsePackage.GENERIC_MSE__ACTION_REFERENCE:
				return actionReference != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated not
	 */
	@Override
	public EOperation getAction() {
		return actionReference;
	}
	
	/**
	 * @generated not
	 */
	@Override
	public EObject getCaller() {
		return callerReference;
	}

} // GenericMSEImpl
