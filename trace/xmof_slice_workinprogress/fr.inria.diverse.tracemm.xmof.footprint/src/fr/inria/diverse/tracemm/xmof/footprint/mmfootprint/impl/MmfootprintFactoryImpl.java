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

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MmfootprintFactoryImpl extends EFactoryImpl implements MmfootprintFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static MmfootprintFactory init() {
		try {
			MmfootprintFactory theMmfootprintFactory = (MmfootprintFactory) EPackage.Registry.INSTANCE
					.getEFactory(MmfootprintPackage.eNS_URI);
			if (theMmfootprintFactory != null) {
				return theMmfootprintFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MmfootprintFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprintFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case MmfootprintPackage.FOOTPRINT:
			return createFootprint();
		case MmfootprintPackage.EMODEL_ELEMENT_ACCESS:
			return createEModelElementAccess();
		case MmfootprintPackage.LOCATION:
			return createLocation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Footprint createFootprint() {
		FootprintImpl footprint = new FootprintImpl();
		return footprint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EModelElementAccess createEModelElementAccess() {
		EModelElementAccessImpl eModelElementAccess = new EModelElementAccessImpl();
		return eModelElementAccess;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Location createLocation() {
		LocationImpl location = new LocationImpl();
		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprintPackage getMmfootprintPackage() {
		return (MmfootprintPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MmfootprintPackage getPackage() {
		return MmfootprintPackage.eINSTANCE;
	}

} // MmfootprintFactoryImpl
