/**
 */
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprint;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintFactory
 * @model kind="package"
 * @generated
 */
public interface MmfootprintPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "mmfootprint";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://xmof/mmfootprint/1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "mmfootprint";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MmfootprintPackage eINSTANCE = fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl
			.init();

	/**
	 * The meta object id for the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.FootprintImpl
	 * <em>Footprint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
	 *      FootprintImpl
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getFootprint()
	 * @generated
	 */
	int FOOTPRINT = 0;

	/**
	 * The feature id for the '<em><b>Metamodel EPackage</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FOOTPRINT__METAMODEL_EPACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Analyzed Object URI</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FOOTPRINT__ANALYZED_OBJECT_URI = 1;

	/**
	 * The feature id for the '<em><b>EModel Element Accesses</b></em>'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FOOTPRINT__EMODEL_ELEMENT_ACCESSES = 2;

	/**
	 * The number of structural features of the '<em>Footprint</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FOOTPRINT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Footprint</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FOOTPRINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.EModelElementAccessImpl
	 * <em>EModel Element Access</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
	 *      EModelElementAccessImpl
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getEModelElementAccess()
	 * @generated
	 */
	int EMODEL_ELEMENT_ACCESS = 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Access Locations</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS = 1;

	/**
	 * The number of structural features of the '<em>EModel Element Access</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMODEL_ELEMENT_ACCESS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>EModel Element Access</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMODEL_ELEMENT_ACCESS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.LocationImpl
	 * <em>Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
	 *      LocationImpl
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Location ID</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION__LOCATION_ID = 0;

	/**
	 * The number of structural features of the '<em>Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATION_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint
	 * <em>Footprint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Footprint</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint
	 * @generated
	 */
	EClass getFootprint();

	/**
	 * Returns the meta object for the reference '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getMetamodelEPackage
	 * <em>Metamodel EPackage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the reference '<em>Metamodel EPackage</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getMetamodelEPackage()
	 * @see #getFootprint()
	 * @generated
	 */
	EReference getFootprint_MetamodelEPackage();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getAnalyzedObjectURI
	 * <em>Analyzed Object URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Analyzed Object URI</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getAnalyzedObjectURI()
	 * @see #getFootprint()
	 * @generated
	 */
	EAttribute getFootprint_AnalyzedObjectURI();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getEModelElementAccesses
	 * <em>EModel Element Accesses</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>EModel Element Accesses</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getEModelElementAccesses()
	 * @see #getFootprint()
	 * @generated
	 */
	EReference getFootprint_EModelElementAccesses();

	/**
	 * Returns the meta object for class '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess
	 * <em>EModel Element Access</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EModel Element Access</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.
	 *      EModelElementAccess
	 * @generated
	 */
	EClass getEModelElementAccess();

	/**
	 * Returns the meta object for the reference '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getEModelElement
	 * <em>EModel Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>EModel Element</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getEModelElement()
	 * @see #getEModelElementAccess()
	 * @generated
	 */
	EReference getEModelElementAccess_EModelElement();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getAccessLocations
	 * <em>Access Locations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Access Locations</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getAccessLocations()
	 * @see #getEModelElementAccess()
	 * @generated
	 */
	EReference getEModelElementAccess_AccessLocations();

	/**
	 * Returns the meta object for class '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location
	 * <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Location</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location#getLocationID
	 * <em>Location ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location ID</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location#getLocationID()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_LocationID();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MmfootprintFactory getMmfootprintFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.FootprintImpl
		 * <em>Footprint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
		 *      FootprintImpl
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getFootprint()
		 * @generated
		 */
		EClass FOOTPRINT = eINSTANCE.getFootprint();

		/**
		 * The meta object literal for the '<em><b>Metamodel EPackage</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FOOTPRINT__METAMODEL_EPACKAGE = eINSTANCE.getFootprint_MetamodelEPackage();

		/**
		 * The meta object literal for the '<em><b>Analyzed Object URI</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute FOOTPRINT__ANALYZED_OBJECT_URI = eINSTANCE.getFootprint_AnalyzedObjectURI();

		/**
		 * The meta object literal for the '
		 * <em><b>EModel Element Accesses</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FOOTPRINT__EMODEL_ELEMENT_ACCESSES = eINSTANCE.getFootprint_EModelElementAccesses();

		/**
		 * The meta object literal for the '
		 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.EModelElementAccessImpl
		 * <em>EModel Element Access</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
		 *      EModelElementAccessImpl
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getEModelElementAccess()
		 * @generated
		 */
		EClass EMODEL_ELEMENT_ACCESS = eINSTANCE.getEModelElementAccess();

		/**
		 * The meta object literal for the '<em><b>EModel Element</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EMODEL_ELEMENT_ACCESS__EMODEL_ELEMENT = eINSTANCE.getEModelElementAccess_EModelElement();

		/**
		 * The meta object literal for the '<em><b>Access Locations</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EMODEL_ELEMENT_ACCESS__ACCESS_LOCATIONS = eINSTANCE.getEModelElementAccess_AccessLocations();

		/**
		 * The meta object literal for the '
		 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.LocationImpl
		 * <em>Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.
		 *      LocationImpl
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '<em><b>Location ID</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOCATION__LOCATION_ID = eINSTANCE.getLocation_LocationID();

	}

} // MmfootprintPackage
