/**
 */
package org.gemoc.executionframework.engine.mse.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.gemoc.executionframework.engine.mse.Engine_msePackage;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MSE Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.MSEModelImpl#getOwnedMSEs <em>Owned MS Es</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSEModelImpl extends MinimalEObjectImpl.Container implements MSEModel {
	/**
	 * The cached value of the '{@link #getOwnedMSEs() <em>Owned MS Es</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMSEs()
	 * @generated
	 * @ordered
	 */
	protected EList<MSE> ownedMSEs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MSEModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Engine_msePackage.Literals.MSE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MSE> getOwnedMSEs() {
		if (ownedMSEs == null) {
			ownedMSEs = new EObjectContainmentEList<MSE>(MSE.class, this, Engine_msePackage.MSE_MODEL__OWNED_MS_ES);
		}
		return ownedMSEs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Engine_msePackage.MSE_MODEL__OWNED_MS_ES:
				return ((InternalEList<?>)getOwnedMSEs()).basicRemove(otherEnd, msgs);
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
			case Engine_msePackage.MSE_MODEL__OWNED_MS_ES:
				return getOwnedMSEs();
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
			case Engine_msePackage.MSE_MODEL__OWNED_MS_ES:
				getOwnedMSEs().clear();
				getOwnedMSEs().addAll((Collection<? extends MSE>)newValue);
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
			case Engine_msePackage.MSE_MODEL__OWNED_MS_ES:
				getOwnedMSEs().clear();
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
			case Engine_msePackage.MSE_MODEL__OWNED_MS_ES:
				return ownedMSEs != null && !ownedMSEs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MSEModelImpl
