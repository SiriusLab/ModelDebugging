/**
 */
package ecorext;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ecorext.EcorextPackage
 * @generated
 */
public interface EcorextFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcorextFactory eINSTANCE = ecorext.impl.EcorextFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Ecorext</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ecorext</em>'.
	 * @generated
	 */
	Ecorext createEcorext();

	/**
	 * Returns a new object of class '<em>Class Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Extension</em>'.
	 * @generated
	 */
	ClassExtension createClassExtension();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EcorextPackage getEcorextPackage();

} //EcorextFactory
