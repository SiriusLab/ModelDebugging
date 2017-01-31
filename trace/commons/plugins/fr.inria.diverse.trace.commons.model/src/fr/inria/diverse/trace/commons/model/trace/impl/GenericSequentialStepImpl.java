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

import fr.inria.diverse.trace.commons.model.trace.GenericSequentialStep;
import fr.inria.diverse.trace.commons.model.trace.GenericState;
import fr.inria.diverse.trace.commons.model.trace.GenericStep;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Sequential Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl#getStartingStateRef <em>Starting State Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericSequentialStepImpl#getEndingStateRef <em>Ending State Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericSequentialStepImpl extends SequentialStepImpl<GenericStep, GenericState> implements GenericSequentialStep {
	/**
	 * The cached value of the '{@link #getStartingStateRef() <em>Starting State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartingStateRef()
	 * @generated
	 * @ordered
	 */
	protected GenericState startingStateRef;
	/**
	 * The cached value of the '{@link #getEndingStateRef() <em>Ending State Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndingStateRef()
	 * @generated
	 * @ordered
	 */
	protected GenericState endingStateRef;

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
		return TracePackage.Literals.GENERIC_SEQUENTIAL_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericState getStartingStateRef() {
		if (startingStateRef != null && startingStateRef.eIsProxy()) {
			InternalEObject oldStartingStateRef = (InternalEObject)startingStateRef;
			startingStateRef = (GenericState)eResolveProxy(oldStartingStateRef);
			if (startingStateRef != oldStartingStateRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF, oldStartingStateRef, startingStateRef));
			}
		}
		return startingStateRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericState basicGetStartingStateRef() {
		return startingStateRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartingStateRef(GenericState newStartingStateRef, NotificationChain msgs) {
		GenericState oldStartingStateRef = startingStateRef;
		startingStateRef = newStartingStateRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF, oldStartingStateRef, newStartingStateRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartingStateRef(GenericState newStartingStateRef) {
		if (newStartingStateRef != startingStateRef) {
			NotificationChain msgs = null;
			if (startingStateRef != null)
				msgs = ((InternalEObject)startingStateRef).eInverseRemove(this, TracePackage.GENERIC_STATE__STARTED_STEPS_REF, GenericState.class, msgs);
			if (newStartingStateRef != null)
				msgs = ((InternalEObject)newStartingStateRef).eInverseAdd(this, TracePackage.GENERIC_STATE__STARTED_STEPS_REF, GenericState.class, msgs);
			msgs = basicSetStartingStateRef(newStartingStateRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF, newStartingStateRef, newStartingStateRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericState getEndingStateRef() {
		if (endingStateRef != null && endingStateRef.eIsProxy()) {
			InternalEObject oldEndingStateRef = (InternalEObject)endingStateRef;
			endingStateRef = (GenericState)eResolveProxy(oldEndingStateRef);
			if (endingStateRef != oldEndingStateRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF, oldEndingStateRef, endingStateRef));
			}
		}
		return endingStateRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericState basicGetEndingStateRef() {
		return endingStateRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndingStateRef(GenericState newEndingStateRef, NotificationChain msgs) {
		GenericState oldEndingStateRef = endingStateRef;
		endingStateRef = newEndingStateRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF, oldEndingStateRef, newEndingStateRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndingStateRef(GenericState newEndingStateRef) {
		if (newEndingStateRef != endingStateRef) {
			NotificationChain msgs = null;
			if (endingStateRef != null)
				msgs = ((InternalEObject)endingStateRef).eInverseRemove(this, TracePackage.GENERIC_STATE__ENDED_STEPS_REF, GenericState.class, msgs);
			if (newEndingStateRef != null)
				msgs = ((InternalEObject)newEndingStateRef).eInverseAdd(this, TracePackage.GENERIC_STATE__ENDED_STEPS_REF, GenericState.class, msgs);
			msgs = basicSetEndingStateRef(newEndingStateRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF, newEndingStateRef, newEndingStateRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				if (startingStateRef != null)
					msgs = ((InternalEObject)startingStateRef).eInverseRemove(this, TracePackage.GENERIC_STATE__STARTED_STEPS_REF, GenericState.class, msgs);
				return basicSetStartingStateRef((GenericState)otherEnd, msgs);
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				if (endingStateRef != null)
					msgs = ((InternalEObject)endingStateRef).eInverseRemove(this, TracePackage.GENERIC_STATE__ENDED_STEPS_REF, GenericState.class, msgs);
				return basicSetEndingStateRef((GenericState)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				return basicSetStartingStateRef(null, msgs);
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				return basicSetEndingStateRef(null, msgs);
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
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				if (resolve) return getStartingStateRef();
				return basicGetStartingStateRef();
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				if (resolve) return getEndingStateRef();
				return basicGetEndingStateRef();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				setStartingStateRef((GenericState)newValue);
				return;
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				setEndingStateRef((GenericState)newValue);
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
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				setStartingStateRef((GenericState)null);
				return;
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				setEndingStateRef((GenericState)null);
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
			case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF:
				return startingStateRef != null;
			case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF:
				return endingStateRef != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == GenericStep.class) {
			switch (derivedFeatureID) {
				case TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF: return TracePackage.GENERIC_STEP__STARTING_STATE_REF;
				case TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF: return TracePackage.GENERIC_STEP__ENDING_STATE_REF;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == GenericStep.class) {
			switch (baseFeatureID) {
				case TracePackage.GENERIC_STEP__STARTING_STATE_REF: return TracePackage.GENERIC_SEQUENTIAL_STEP__STARTING_STATE_REF;
				case TracePackage.GENERIC_STEP__ENDING_STATE_REF: return TracePackage.GENERIC_SEQUENTIAL_STEP__ENDING_STATE_REF;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //GenericSequentialStepImpl
