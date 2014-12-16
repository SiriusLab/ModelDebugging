/**
 */
package ecorext;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ecorext</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecorext.Ecorext#getNewPackages <em>New Packages</em>}</li>
 *   <li>{@link ecorext.Ecorext#getClassesExtensions <em>Classes Extensions</em>}</li>
 *   <li>{@link ecorext.Ecorext#getNewClasses <em>New Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecorext.EcorextPackage#getEcorext()
 * @model
 * @generated
 */
public interface Ecorext extends EObject {
	/**
	 * Returns the value of the '<em><b>New Packages</b></em>' containment reference list.
	 * The list contents are of type {@link ecorext.NewPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Packages</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_NewPackages()
	 * @model containment="true"
	 * @generated
	 */
	EList<NewPackage> getNewPackages();

	/**
	 * Returns the value of the '<em><b>Classes Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link ecorext.ClassExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes Extensions</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_ClassesExtensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassExtension> getClassesExtensions();

	/**
	 * Returns the value of the '<em><b>New Classes</b></em>' containment reference list.
	 * The list contents are of type {@link ecorext.NewClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Classes</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_NewClasses()
	 * @model containment="true"
	 * @generated
	 */
	EList<NewClass> getNewClasses();

} // Ecorext
