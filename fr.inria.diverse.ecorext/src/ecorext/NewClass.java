/**
 */
package ecorext;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecorext.NewClass#getNewClass <em>New Class</em>}</li>
 *   <li>{@link ecorext.NewClass#getContainingExistingPackage <em>Containing Existing Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecorext.EcorextPackage#getNewClass()
 * @model
 * @generated
 */
public interface NewClass extends EObject {
	/**
	 * Returns the value of the '<em><b>New Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Class</em>' containment reference.
	 * @see #setNewClass(EClass)
	 * @see ecorext.EcorextPackage#getNewClass_NewClass()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EClass getNewClass();

	/**
	 * Sets the value of the '{@link ecorext.NewClass#getNewClass <em>New Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Class</em>' containment reference.
	 * @see #getNewClass()
	 * @generated
	 */
	void setNewClass(EClass value);

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
	 * @see ecorext.EcorextPackage#getNewClass_ContainingExistingPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getContainingExistingPackage();

	/**
	 * Sets the value of the '{@link ecorext.NewClass#getContainingExistingPackage <em>Containing Existing Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Existing Package</em>' reference.
	 * @see #getContainingExistingPackage()
	 * @generated
	 */
	void setContainingExistingPackage(EPackage value);

} // NewClass
