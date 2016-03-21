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
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Footprint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.FootprintImpl#getMetamodelEPackage
 * <em>Metamodel EPackage</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.FootprintImpl#getAnalyzedObjectURI
 * <em>Analyzed Object URI</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.FootprintImpl#getEModelElementAccesses
 * <em>EModel Element Accesses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FootprintImpl extends MinimalEObjectImpl.Container implements Footprint {
	/**
	 * The cached value of the '{@link #getMetamodelEPackage()
	 * <em>Metamodel EPackage</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMetamodelEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage metamodelEPackage;

	/**
	 * The default value of the '{@link #getAnalyzedObjectURI()
	 * <em>Analyzed Object URI</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAnalyzedObjectURI()
	 * @generated
	 * @ordered
	 */
	protected static final String ANALYZED_OBJECT_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAnalyzedObjectURI()
	 * <em>Analyzed Object URI</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAnalyzedObjectURI()
	 * @generated
	 * @ordered
	 */
	protected String analyzedObjectURI = ANALYZED_OBJECT_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEModelElementAccesses()
	 * <em>EModel Element Accesses</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEModelElementAccesses()
	 * @generated
	 * @ordered
	 */
	protected EList<EModelElementAccess> eModelElementAccesses;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FootprintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MmfootprintPackage.Literals.FOOTPRINT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage getMetamodelEPackage() {
		if (metamodelEPackage != null && metamodelEPackage.eIsProxy()) {
			InternalEObject oldMetamodelEPackage = (InternalEObject) metamodelEPackage;
			metamodelEPackage = (EPackage) eResolveProxy(oldMetamodelEPackage);
			if (metamodelEPackage != oldMetamodelEPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE, oldMetamodelEPackage, metamodelEPackage));
			}
		}
		return metamodelEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EPackage basicGetMetamodelEPackage() {
		return metamodelEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMetamodelEPackage(EPackage newMetamodelEPackage) {
		EPackage oldMetamodelEPackage = metamodelEPackage;
		metamodelEPackage = newMetamodelEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE,
					oldMetamodelEPackage, metamodelEPackage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAnalyzedObjectURI() {
		return analyzedObjectURI;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAnalyzedObjectURI(String newAnalyzedObjectURI) {
		String oldAnalyzedObjectURI = analyzedObjectURI;
		analyzedObjectURI = newAnalyzedObjectURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprintPackage.FOOTPRINT__ANALYZED_OBJECT_URI,
					oldAnalyzedObjectURI, analyzedObjectURI));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<EModelElementAccess> getEModelElementAccesses() {
		if (eModelElementAccesses == null) {
			eModelElementAccesses = new EObjectContainmentEList<EModelElementAccess>(EModelElementAccess.class, this,
					MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES);
		}
		return eModelElementAccesses;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES:
			return ((InternalEList<?>) getEModelElementAccesses()).basicRemove(otherEnd, msgs);
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
		case MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE:
			if (resolve)
				return getMetamodelEPackage();
			return basicGetMetamodelEPackage();
		case MmfootprintPackage.FOOTPRINT__ANALYZED_OBJECT_URI:
			return getAnalyzedObjectURI();
		case MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES:
			return getEModelElementAccesses();
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
		case MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE:
			setMetamodelEPackage((EPackage) newValue);
			return;
		case MmfootprintPackage.FOOTPRINT__ANALYZED_OBJECT_URI:
			setAnalyzedObjectURI((String) newValue);
			return;
		case MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES:
			getEModelElementAccesses().clear();
			getEModelElementAccesses().addAll((Collection<? extends EModelElementAccess>) newValue);
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
		case MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE:
			setMetamodelEPackage((EPackage) null);
			return;
		case MmfootprintPackage.FOOTPRINT__ANALYZED_OBJECT_URI:
			setAnalyzedObjectURI(ANALYZED_OBJECT_URI_EDEFAULT);
			return;
		case MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES:
			getEModelElementAccesses().clear();
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
		case MmfootprintPackage.FOOTPRINT__METAMODEL_EPACKAGE:
			return metamodelEPackage != null;
		case MmfootprintPackage.FOOTPRINT__ANALYZED_OBJECT_URI:
			return ANALYZED_OBJECT_URI_EDEFAULT == null ? analyzedObjectURI != null
					: !ANALYZED_OBJECT_URI_EDEFAULT.equals(analyzedObjectURI);
		case MmfootprintPackage.FOOTPRINT__EMODEL_ELEMENT_ACCESSES:
			return eModelElementAccesses != null && !eModelElementAccesses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (analyzedObjectURI: ");
		result.append(analyzedObjectURI);
		result.append(')');
		return result.toString();
	}

} // FootprintImpl
