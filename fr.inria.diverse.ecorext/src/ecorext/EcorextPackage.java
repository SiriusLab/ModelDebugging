/**
 */
package ecorext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ecorext.EcorextFactory
 * @model kind="package"
 * @generated
 */
public interface EcorextPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ecorext";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ecorext/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ecorext";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcorextPackage eINSTANCE = ecorext.impl.EcorextPackageImpl.init();

	/**
	 * The meta object id for the '{@link ecorext.impl.EcorextImpl <em>Ecorext</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecorext.impl.EcorextImpl
	 * @see ecorext.impl.EcorextPackageImpl#getEcorext()
	 * @generated
	 */
	int ECOREXT = 0;

	/**
	 * The feature id for the '<em><b>New Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT__NEW_PACKAGES = 0;

	/**
	 * The feature id for the '<em><b>Classes Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT__CLASSES_EXTENSIONS = 1;

	/**
	 * The number of structural features of the '<em>Ecorext</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Ecorext</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecorext.impl.ClassExtensionImpl <em>Class Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecorext.impl.ClassExtensionImpl
	 * @see ecorext.impl.EcorextPackageImpl#getClassExtension()
	 * @generated
	 */
	int CLASS_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Extended Existing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION__EXTENDED_EXISTING_CLASS = 0;

	/**
	 * The feature id for the '<em><b>New Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION__NEW_PROPERTIES = 1;

	/**
	 * The number of structural features of the '<em>Class Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Class Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link ecorext.Ecorext <em>Ecorext</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecorext</em>'.
	 * @see ecorext.Ecorext
	 * @generated
	 */
	EClass getEcorext();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.Ecorext#getNewPackages <em>New Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>New Packages</em>'.
	 * @see ecorext.Ecorext#getNewPackages()
	 * @see #getEcorext()
	 * @generated
	 */
	EReference getEcorext_NewPackages();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.Ecorext#getClassesExtensions <em>Classes Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes Extensions</em>'.
	 * @see ecorext.Ecorext#getClassesExtensions()
	 * @see #getEcorext()
	 * @generated
	 */
	EReference getEcorext_ClassesExtensions();

	/**
	 * Returns the meta object for class '{@link ecorext.ClassExtension <em>Class Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Extension</em>'.
	 * @see ecorext.ClassExtension
	 * @generated
	 */
	EClass getClassExtension();

	/**
	 * Returns the meta object for the reference '{@link ecorext.ClassExtension#getExtendedExistingClass <em>Extended Existing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extended Existing Class</em>'.
	 * @see ecorext.ClassExtension#getExtendedExistingClass()
	 * @see #getClassExtension()
	 * @generated
	 */
	EReference getClassExtension_ExtendedExistingClass();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.ClassExtension#getNewProperties <em>New Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>New Properties</em>'.
	 * @see ecorext.ClassExtension#getNewProperties()
	 * @see #getClassExtension()
	 * @generated
	 */
	EReference getClassExtension_NewProperties();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EcorextFactory getEcorextFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link ecorext.impl.EcorextImpl <em>Ecorext</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecorext.impl.EcorextImpl
		 * @see ecorext.impl.EcorextPackageImpl#getEcorext()
		 * @generated
		 */
		EClass ECOREXT = eINSTANCE.getEcorext();

		/**
		 * The meta object literal for the '<em><b>New Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECOREXT__NEW_PACKAGES = eINSTANCE.getEcorext_NewPackages();

		/**
		 * The meta object literal for the '<em><b>Classes Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECOREXT__CLASSES_EXTENSIONS = eINSTANCE.getEcorext_ClassesExtensions();

		/**
		 * The meta object literal for the '{@link ecorext.impl.ClassExtensionImpl <em>Class Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecorext.impl.ClassExtensionImpl
		 * @see ecorext.impl.EcorextPackageImpl#getClassExtension()
		 * @generated
		 */
		EClass CLASS_EXTENSION = eINSTANCE.getClassExtension();

		/**
		 * The meta object literal for the '<em><b>Extended Existing Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_EXTENSION__EXTENDED_EXISTING_CLASS = eINSTANCE.getClassExtension_ExtendedExistingClass();

		/**
		 * The meta object literal for the '<em><b>New Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_EXTENSION__NEW_PROPERTIES = eINSTANCE.getClassExtension_NewProperties();

	}

} //EcorextPackage
