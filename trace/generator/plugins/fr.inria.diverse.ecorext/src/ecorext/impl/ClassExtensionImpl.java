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
package ecorext.impl;

import ecorext.ClassExtension;
import ecorext.EcorextPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ecorext.impl.ClassExtensionImpl#getExtendedExistingClass <em>Extended Existing Class</em>}</li>
 *   <li>{@link ecorext.impl.ClassExtensionImpl#getNewProperties <em>New Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassExtensionImpl extends MinimalEObjectImpl.Container implements ClassExtension {
	/**
	 * The cached value of the '{@link #getExtendedExistingClass() <em>Extended Existing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedExistingClass()
	 * @generated
	 * @ordered
	 */
	protected EClass extendedExistingClass;

	/**
	 * The cached value of the '{@link #getNewProperties() <em>New Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<EStructuralFeature> newProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcorextPackage.Literals.CLASS_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedExistingClass() {
		if (extendedExistingClass != null && extendedExistingClass.eIsProxy()) {
			InternalEObject oldExtendedExistingClass = (InternalEObject)extendedExistingClass;
			extendedExistingClass = (EClass)eResolveProxy(oldExtendedExistingClass);
			if (extendedExistingClass != oldExtendedExistingClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS, oldExtendedExistingClass, extendedExistingClass));
			}
		}
		return extendedExistingClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetExtendedExistingClass() {
		return extendedExistingClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedExistingClass(EClass newExtendedExistingClass) {
		EClass oldExtendedExistingClass = extendedExistingClass;
		extendedExistingClass = newExtendedExistingClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS, oldExtendedExistingClass, extendedExistingClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EStructuralFeature> getNewProperties() {
		if (newProperties == null) {
			newProperties = new EObjectContainmentEList<EStructuralFeature>(EStructuralFeature.class, this, EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES);
		}
		return newProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES:
				return ((InternalEList<?>)getNewProperties()).basicRemove(otherEnd, msgs);
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
			case EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS:
				if (resolve) return getExtendedExistingClass();
				return basicGetExtendedExistingClass();
			case EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES:
				return getNewProperties();
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
			case EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS:
				setExtendedExistingClass((EClass)newValue);
				return;
			case EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES:
				getNewProperties().clear();
				getNewProperties().addAll((Collection<? extends EStructuralFeature>)newValue);
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
			case EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS:
				setExtendedExistingClass((EClass)null);
				return;
			case EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES:
				getNewProperties().clear();
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
			case EcorextPackage.CLASS_EXTENSION__EXTENDED_EXISTING_CLASS:
				return extendedExistingClass != null;
			case EcorextPackage.CLASS_EXTENSION__NEW_PROPERTIES:
				return newProperties != null && !newProperties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ClassExtensionImpl
