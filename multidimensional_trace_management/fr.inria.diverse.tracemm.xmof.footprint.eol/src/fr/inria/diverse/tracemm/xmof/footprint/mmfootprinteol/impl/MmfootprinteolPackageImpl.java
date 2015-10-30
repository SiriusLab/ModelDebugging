/**
 */
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolFactory;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MmfootprinteolPackageImpl extends EPackageImpl implements MmfootprinteolPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass eolLocationEClass = null;

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
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MmfootprinteolPackageImpl() {
		super(eNS_URI, MmfootprinteolFactory.eINSTANCE);
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
	 * This method is used to initialize {@link MmfootprinteolPackage#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MmfootprinteolPackage init() {
		if (isInited)
			return (MmfootprinteolPackage) EPackage.Registry.INSTANCE.getEPackage(MmfootprinteolPackage.eNS_URI);

		// Obtain or create and register package
		MmfootprinteolPackageImpl theMmfootprinteolPackage = (MmfootprinteolPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof MmfootprinteolPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new MmfootprinteolPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MmfootprintPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMmfootprinteolPackage.createPackageContents();

		// Initialize created meta-data
		theMmfootprinteolPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMmfootprinteolPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MmfootprinteolPackage.eNS_URI, theMmfootprinteolPackage);
		return theMmfootprinteolPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getEOLLocation() {
		return eolLocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEOLLocation_Line() {
		return (EAttribute) eolLocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEOLLocation_Column() {
		return (EAttribute) eolLocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEOLLocation_TokenStartIndex() {
		return (EAttribute) eolLocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEOLLocation_TokenStopIndex() {
		return (EAttribute) eolLocationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprinteolFactory getMmfootprinteolFactory() {
		return (MmfootprinteolFactory) getEFactoryInstance();
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
		eolLocationEClass = createEClass(EOL_LOCATION);
		createEAttribute(eolLocationEClass, EOL_LOCATION__LINE);
		createEAttribute(eolLocationEClass, EOL_LOCATION__COLUMN);
		createEAttribute(eolLocationEClass, EOL_LOCATION__TOKEN_START_INDEX);
		createEAttribute(eolLocationEClass, EOL_LOCATION__TOKEN_STOP_INDEX);
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
		MmfootprintPackage theMmfootprintPackage = (MmfootprintPackage) EPackage.Registry.INSTANCE
				.getEPackage(MmfootprintPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		eolLocationEClass.getESuperTypes().add(theMmfootprintPackage.getLocation());

		// Initialize classes, features, and operations; add parameters
		initEClass(eolLocationEClass, EOLLocation.class, "EOLLocation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEOLLocation_Line(), ecorePackage.getEInt(), "line", null, 1, 1, EOLLocation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEOLLocation_Column(), ecorePackage.getEInt(), "column", null, 1, 1, EOLLocation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEOLLocation_TokenStartIndex(), ecorePackage.getEInt(), "tokenStartIndex", null, 1, 1,
				EOLLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEOLLocation_TokenStopIndex(), ecorePackage.getEInt(), "tokenStopIndex", null, 1, 1,
				EOLLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // MmfootprinteolPackageImpl
