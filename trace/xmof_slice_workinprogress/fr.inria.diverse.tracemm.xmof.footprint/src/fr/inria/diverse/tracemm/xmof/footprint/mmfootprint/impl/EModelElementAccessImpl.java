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
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EModel Element Access</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.EModelElementAccessImpl#getEModelElement
 * <em>EModel Element</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.EModelElementAccessImpl#getAccessLocations
 * <em>Access Locations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EModelElementAccessImpl extends MinimalEObjectImpl.Container implements EModelElementAccess {
	/**
	 * The cached value of the '{@link #getEModelElement()
	 * <em>EModel Element</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEModelElement()
	 * @generated
	 * @ordered
	 */
	protected EModelElement eModelElement;

	/**
	 * The cached value of the '{@link #getAccessLocations()
	 * <em>Access Locations</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAccessLocations()
	 * @generated
	 * @ordered
	 */
	protected EList<Location> accessLocations;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EModelElementAccessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MmfootprintPackage.Literals.EMODEL_ELEMENT_ACCESS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EModelElement getEModelElement() {
		if (eModelElement != null && eModelElement.eIsProxy()) {
			InternalEObject oldEModelElement = (InternalEObject) eModelElement;
			eModelElement = (EModelElement) eResolveProxy(oldEModelElement);
			if (eModelElement != oldEModelElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT, oldEModelElement, eModelElement));
			}
		}
		return eModelElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EModelElement basicGetEModelElement() {
		return eModelElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEModelElement(EModelElement newEModelElement) {
		EModelElement oldEModelElement = eModelElement;
		eModelElement = newEModelElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT, oldEModelElement, eModelElement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Location> getAccessLocations() {
		if (accessLocations == null) {
			accessLocations = new EObjectContainmentEList<Location>(Location.class, this,
					MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS);
		}
		return accessLocations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS:
			return ((InternalEList<?>) getAccessLocations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT:
			if (resolve)
				return getEModelElement();
			return basicGetEModelElement();
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS:
			return getAccessLocations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT:
			setEModelElement((EModelElement) newValue);
			return;
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS:
			getAccessLocations().clear();
			getAccessLocations().addAll((Collection<? extends Location>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT:
			setEModelElement((EModelElement) null);
			return;
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS:
			getAccessLocations().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT:
			return eModelElement != null;
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS:
			return accessLocations != null && !accessLocations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // EModelElementAccessImpl
