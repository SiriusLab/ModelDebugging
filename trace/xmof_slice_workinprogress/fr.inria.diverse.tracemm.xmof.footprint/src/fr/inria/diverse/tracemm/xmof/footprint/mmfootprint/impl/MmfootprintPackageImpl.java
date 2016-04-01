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
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintFactory;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MmfootprintPackageImpl extends EPackageImpl implements MmfootprintPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass footprintEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eModelElementAccessEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass locationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MmfootprintPackageImpl() {
		super(eNS_URI, MmfootprintFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link MmfootprintPackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MmfootprintPackage init() {
		if (isInited)
			return (MmfootprintPackage) EPackage.Registry.INSTANCE.getEPackage(MmfootprintPackage.eNS_URI);

		// Obtain or create and register package
		MmfootprintPackageImpl theMmfootprintPackage = (MmfootprintPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof MmfootprintPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new MmfootprintPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMmfootprintPackage.createPackageContents();

		// Initialize created meta-data
		theMmfootprintPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMmfootprintPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MmfootprintPackage.eNS_URI, theMmfootprintPackage);
		return theMmfootprintPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFootprint() {
		return footprintEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFootprint_MetamodelEPackage() {
		return (EReference) footprintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFootprint_AnalyzedObjectURI() {
		return (EAttribute) footprintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFootprint_EModelElementAccesses() {
		return (EReference) footprintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getEModelElementAccess() {
		return eModelElementAccessEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getEModelElementAccess_EModelElement() {
		return (EReference) eModelElementAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getEModelElementAccess_AccessLocations() {
		return (EReference) eModelElementAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getLocation() {
		return locationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLocation_LocationID() {
		return (EAttribute) locationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprintFactory getMmfootprintFactory() {
		return (MmfootprintFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		footprintEClass = createEClass(FOOTPRINT);
		createEReference(footprintEClass, FOOTPRINT__METAMODEL_EPACKAGE);
		createEAttribute(footprintEClass, FOOTPRINT__ANALYZED_OBJECT_URI);
		createEReference(footprintEClass, FOOTPRINT__EMODEL_ELEMENT_ACCESSES);

		eModelElementAccessEClass = createEClass(EMODEL_ELEMENT_ACCESS);
		createEReference(eModelElementAccessEClass, EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT);
		createEReference(eModelElementAccessEClass, EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS);

		locationEClass = createEClass(LOCATION);
		createEAttribute(locationEClass, LOCATION__LOCATION_ID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(footprintEClass, Footprint.class, "Footprint", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFootprint_MetamodelEPackage(), theEcorePackage.getEPackage(), null, "metamodelEPackage", null,
				1, 1, Footprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFootprint_AnalyzedObjectURI(), theEcorePackage.getEString(), "analyzedObjectURI", null, 1, 1,
				Footprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getFootprint_EModelElementAccesses(), this.getEModelElementAccess(), null,
				"eModelElementAccesses", null, 0, -1, Footprint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eModelElementAccessEClass, EModelElementAccess.class, "EModelElementAccess", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEModelElementAccess_EModelElement(), theEcorePackage.getEModelElement(), null,
				"eModelElement", null, 1, 1, EModelElementAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEModelElementAccess_AccessLocations(), this.getLocation(), null, "accessLocations", null, 0,
				-1, EModelElementAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(locationEClass, Location.class, "Location", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocation_LocationID(), theEcorePackage.getEString(), "locationID", null, 0, 1, Location.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // MmfootprintPackageImpl
