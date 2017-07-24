/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider;
import org.eclipse.gemoc.event.commons.model.scenario.Event;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.EventImpl#getTargetProvider <em>Target Provider</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EventImpl<T> extends MinimalEObjectImpl.Container implements Event<T> {
	/**
	 * The cached value of the '{@link #getTargetProvider() <em>Target Provider</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProvider()
	 * @generated
	 * @ordered
	 */
	protected ElementProvider<T> targetProvider;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementProvider<T> getTargetProvider() {
		return targetProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetProvider(ElementProvider<T> newTargetProvider, NotificationChain msgs) {
		ElementProvider<T> oldTargetProvider = targetProvider;
		targetProvider = newTargetProvider;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.EVENT__TARGET_PROVIDER, oldTargetProvider, newTargetProvider);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetProvider(ElementProvider<T> newTargetProvider) {
		if (newTargetProvider != targetProvider) {
			NotificationChain msgs = null;
			if (targetProvider != null)
				msgs = ((InternalEObject)targetProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.EVENT__TARGET_PROVIDER, null, msgs);
			if (newTargetProvider != null)
				msgs = ((InternalEObject)newTargetProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.EVENT__TARGET_PROVIDER, null, msgs);
			msgs = basicSetTargetProvider(newTargetProvider, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.EVENT__TARGET_PROVIDER, newTargetProvider, newTargetProvider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.EVENT__TARGET_PROVIDER:
				return basicSetTargetProvider(null, msgs);
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
			case ScenarioPackage.EVENT__TARGET_PROVIDER:
				return getTargetProvider();
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
			case ScenarioPackage.EVENT__TARGET_PROVIDER:
				setTargetProvider((ElementProvider<T>)newValue);
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
			case ScenarioPackage.EVENT__TARGET_PROVIDER:
				setTargetProvider((ElementProvider<T>)null);
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
			case ScenarioPackage.EVENT__TARGET_PROVIDER:
				return targetProvider != null;
		}
		return super.eIsSet(featureID);
	}

} //EventImpl
