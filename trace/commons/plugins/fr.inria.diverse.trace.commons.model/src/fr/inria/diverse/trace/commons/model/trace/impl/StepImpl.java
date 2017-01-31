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
/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;
import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.StepImpl#getMseoccurrence <em>Mseoccurrence</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.StepImpl#getStartingState <em>Starting State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.StepImpl#getEndingState <em>Ending State</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StepImpl<StateSubType extends State<?, ?>> extends MinimalEObjectImpl.Container implements Step<StateSubType> {
	/**
	 * The cached value of the '{@link #getMseoccurrence() <em>Mseoccurrence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMseoccurrence()
	 * @generated
	 * @ordered
	 */
	protected MSEOccurrence mseoccurrence;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSEOccurrence getMseoccurrence() {
		return mseoccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMseoccurrence(MSEOccurrence newMseoccurrence, NotificationChain msgs) {
		MSEOccurrence oldMseoccurrence = mseoccurrence;
		mseoccurrence = newMseoccurrence;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.STEP__MSEOCCURRENCE, oldMseoccurrence, newMseoccurrence);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMseoccurrence(MSEOccurrence newMseoccurrence) {
		if (newMseoccurrence != mseoccurrence) {
			NotificationChain msgs = null;
			if (mseoccurrence != null)
				msgs = ((InternalEObject)mseoccurrence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TracePackage.STEP__MSEOCCURRENCE, null, msgs);
			if (newMseoccurrence != null)
				msgs = ((InternalEObject)newMseoccurrence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TracePackage.STEP__MSEOCCURRENCE, null, msgs);
			msgs = basicSetMseoccurrence(newMseoccurrence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.STEP__MSEOCCURRENCE, newMseoccurrence, newMseoccurrence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public StateSubType getStartingState() {
		StateSubType startingState = basicGetStartingState();
		return startingState != null && startingState.eIsProxy() ? (StateSubType)eResolveProxy((InternalEObject)startingState) : startingState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateSubType basicGetStartingState() {
		// TODO: implement this method to return the 'Starting State' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartingState(StateSubType newStartingState) {
		// TODO: implement this method to set the 'Starting State' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public StateSubType getEndingState() {
		StateSubType endingState = basicGetEndingState();
		return endingState != null && endingState.eIsProxy() ? (StateSubType)eResolveProxy((InternalEObject)endingState) : endingState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateSubType basicGetEndingState() {
		// TODO: implement this method to return the 'Ending State' reference
		// -> do not perform proxy resolution
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndingState(StateSubType newEndingState) {
		// TODO: implement this method to set the 'Ending State' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.STEP__MSEOCCURRENCE:
				return basicSetMseoccurrence(null, msgs);
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
			case TracePackage.STEP__MSEOCCURRENCE:
				return getMseoccurrence();
			case TracePackage.STEP__STARTING_STATE:
				if (resolve) return getStartingState();
				return basicGetStartingState();
			case TracePackage.STEP__ENDING_STATE:
				if (resolve) return getEndingState();
				return basicGetEndingState();
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
			case TracePackage.STEP__MSEOCCURRENCE:
				setMseoccurrence((MSEOccurrence)newValue);
				return;
			case TracePackage.STEP__STARTING_STATE:
				setStartingState((StateSubType)newValue);
				return;
			case TracePackage.STEP__ENDING_STATE:
				setEndingState((StateSubType)newValue);
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
			case TracePackage.STEP__MSEOCCURRENCE:
				setMseoccurrence((MSEOccurrence)null);
				return;
			case TracePackage.STEP__STARTING_STATE:
				setStartingState((StateSubType)null);
				return;
			case TracePackage.STEP__ENDING_STATE:
				setEndingState((StateSubType)null);
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
			case TracePackage.STEP__MSEOCCURRENCE:
				return mseoccurrence != null;
			case TracePackage.STEP__STARTING_STATE:
				return basicGetStartingState() != null;
			case TracePackage.STEP__ENDING_STATE:
				return basicGetEndingState() != null;
		}
		return super.eIsSet(featureID);
	}

} //StepImpl
