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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.gemoc.executionframework.engine.mse.LaunchConfiguration;
import org.gemoc.executionframework.engine.mse.MsePackage;
import org.gemoc.executionframework.engine.mse.Trace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.TraceImpl#getRootStep <em>Root Step</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.TraceImpl#getLaunchconfiguration <em>Launchconfiguration</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TraceImpl<StepSubType> extends MinimalEObjectImpl.Container implements Trace<StepSubType> {
	/**
	 * The cached value of the '{@link #getRootStep() <em>Root Step</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootStep()
	 * @generated
	 * @ordered
	 */
	protected StepSubType rootStep;

	/**
	 * The cached value of the '{@link #getLaunchconfiguration() <em>Launchconfiguration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchconfiguration()
	 * @generated
	 * @ordered
	 */
	protected LaunchConfiguration launchconfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StepSubType getRootStep() {
		return rootStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRootStep(StepSubType newRootStep, NotificationChain msgs) {
		StepSubType oldRootStep = rootStep;
		rootStep = newRootStep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsePackage.TRACE__ROOT_STEP, oldRootStep, newRootStep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootStep(StepSubType newRootStep) {
		if (newRootStep != rootStep) {
			NotificationChain msgs = null;
			if (rootStep != null)
				msgs = ((InternalEObject)rootStep).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsePackage.TRACE__ROOT_STEP, null, msgs);
			if (newRootStep != null)
				msgs = ((InternalEObject)newRootStep).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsePackage.TRACE__ROOT_STEP, null, msgs);
			msgs = basicSetRootStep(newRootStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.TRACE__ROOT_STEP, newRootStep, newRootStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaunchConfiguration getLaunchconfiguration() {
		return launchconfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLaunchconfiguration(LaunchConfiguration newLaunchconfiguration, NotificationChain msgs) {
		LaunchConfiguration oldLaunchconfiguration = launchconfiguration;
		launchconfiguration = newLaunchconfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MsePackage.TRACE__LAUNCHCONFIGURATION, oldLaunchconfiguration, newLaunchconfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLaunchconfiguration(LaunchConfiguration newLaunchconfiguration) {
		if (newLaunchconfiguration != launchconfiguration) {
			NotificationChain msgs = null;
			if (launchconfiguration != null)
				msgs = ((InternalEObject)launchconfiguration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MsePackage.TRACE__LAUNCHCONFIGURATION, null, msgs);
			if (newLaunchconfiguration != null)
				msgs = ((InternalEObject)newLaunchconfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MsePackage.TRACE__LAUNCHCONFIGURATION, null, msgs);
			msgs = basicSetLaunchconfiguration(newLaunchconfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.TRACE__LAUNCHCONFIGURATION, newLaunchconfiguration, newLaunchconfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MsePackage.TRACE__ROOT_STEP:
				return basicSetRootStep(null, msgs);
			case MsePackage.TRACE__LAUNCHCONFIGURATION:
				return basicSetLaunchconfiguration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MsePackage.TRACE__ROOT_STEP:
				return getRootStep();
			case MsePackage.TRACE__LAUNCHCONFIGURATION:
				return getLaunchconfiguration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MsePackage.TRACE__ROOT_STEP:
				setRootStep((StepSubType)newValue);
				return;
			case MsePackage.TRACE__LAUNCHCONFIGURATION:
				setLaunchconfiguration((LaunchConfiguration)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MsePackage.TRACE__ROOT_STEP:
				setRootStep((StepSubType)null);
				return;
			case MsePackage.TRACE__LAUNCHCONFIGURATION:
				setLaunchconfiguration((LaunchConfiguration)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MsePackage.TRACE__ROOT_STEP:
				return rootStep != null;
			case MsePackage.TRACE__LAUNCHCONFIGURATION:
				return launchconfiguration != null;
		}
		return super.eIsSet(featureID);
	}

} //TraceImpl
