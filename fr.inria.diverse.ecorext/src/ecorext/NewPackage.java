/**
 */
package ecorext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecorext.NewPackage#getNewPackage <em>New Package</em>}</li>
 *   <li>{@link ecorext.NewPackage#getContainingExistingPackage <em>Containing Existing Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecorext.EcorextPackage#getNewPackage()
 * @model
 * @generated
 */
public interface NewPackage extends EObject {
	/**
	 * Returns the value of the '<em><b>New Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Package</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Package</em>' containment reference.
	 * @see #setNewPackage(EPackage)
	 * @see ecorext.EcorextPackage#getNewPackage_NewPackage()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EPackage getNewPackage();

	/**
	 * Sets the value of the '{@link ecorext.NewPackage#getNewPackage <em>New Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Package</em>' containment reference.
	 * @see #getNewPackage()
	 * @generated
	 */
	void setNewPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Containing Existing Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Existing Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Existing Package</em>' reference.
	 * @see #setContainingExistingPackage(EPackage)
	 * @see ecorext.EcorextPackage#getNewPackage_ContainingExistingPackage()
	 * @model
	 * @generated
	 */
	EPackage getContainingExistingPackage();

	/**
	 * Sets the value of the '{@link ecorext.NewPackage#getContainingExistingPackage <em>Containing Existing Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Existing Package</em>' reference.
	 * @see #getContainingExistingPackage()
	 * @generated
	 */
	void setContainingExistingPackage(EPackage value);

} // NewPackage
