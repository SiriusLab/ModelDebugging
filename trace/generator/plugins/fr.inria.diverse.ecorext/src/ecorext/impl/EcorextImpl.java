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
import ecorext.Ecorext;
import ecorext.EcorextPackage;
import ecorext.Rule;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ecorext</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ecorext.impl.EcorextImpl#getNewPackages <em>New Packages</em>}</li>
 *   <li>{@link ecorext.impl.EcorextImpl#getClassesExtensions <em>Classes Extensions</em>}</li>
 *   <li>{@link ecorext.impl.EcorextImpl#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EcorextImpl extends MinimalEObjectImpl.Container implements Ecorext {
	/**
	 * The cached value of the '{@link #getNewPackages() <em>New Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<EPackage> newPackages;

	/**
	 * The cached value of the '{@link #getClassesExtensions() <em>Classes Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassesExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassExtension> classesExtensions;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> rules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EcorextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcorextPackage.Literals.ECOREXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EPackage> getNewPackages() {
		if (newPackages == null) {
			newPackages = new EObjectContainmentEList<EPackage>(EPackage.class, this, EcorextPackage.ECOREXT__NEW_PACKAGES);
		}
		return newPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassExtension> getClassesExtensions() {
		if (classesExtensions == null) {
			classesExtensions = new EObjectContainmentEList<ClassExtension>(ClassExtension.class, this, EcorextPackage.ECOREXT__CLASSES_EXTENSIONS);
		}
		return classesExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<Rule>(Rule.class, this, EcorextPackage.ECOREXT__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcorextPackage.ECOREXT__NEW_PACKAGES:
				return ((InternalEList<?>)getNewPackages()).basicRemove(otherEnd, msgs);
			case EcorextPackage.ECOREXT__CLASSES_EXTENSIONS:
				return ((InternalEList<?>)getClassesExtensions()).basicRemove(otherEnd, msgs);
			case EcorextPackage.ECOREXT__RULES:
				return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
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
			case EcorextPackage.ECOREXT__NEW_PACKAGES:
				return getNewPackages();
			case EcorextPackage.ECOREXT__CLASSES_EXTENSIONS:
				return getClassesExtensions();
			case EcorextPackage.ECOREXT__RULES:
				return getRules();
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
			case EcorextPackage.ECOREXT__NEW_PACKAGES:
				getNewPackages().clear();
				getNewPackages().addAll((Collection<? extends EPackage>)newValue);
				return;
			case EcorextPackage.ECOREXT__CLASSES_EXTENSIONS:
				getClassesExtensions().clear();
				getClassesExtensions().addAll((Collection<? extends ClassExtension>)newValue);
				return;
			case EcorextPackage.ECOREXT__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends Rule>)newValue);
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
			case EcorextPackage.ECOREXT__NEW_PACKAGES:
				getNewPackages().clear();
				return;
			case EcorextPackage.ECOREXT__CLASSES_EXTENSIONS:
				getClassesExtensions().clear();
				return;
			case EcorextPackage.ECOREXT__RULES:
				getRules().clear();
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
			case EcorextPackage.ECOREXT__NEW_PACKAGES:
				return newPackages != null && !newPackages.isEmpty();
			case EcorextPackage.ECOREXT__CLASSES_EXTENSIONS:
				return classesExtensions != null && !classesExtensions.isEmpty();
			case EcorextPackage.ECOREXT__RULES:
				return rules != null && !rules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EcorextImpl
