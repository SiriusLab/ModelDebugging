/**
 */
package org.gemoc.executionengine.java.sequential_xdsml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage
 * @generated
 */
public interface Sequential_xdsmlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Sequential_xdsmlFactory eINSTANCE = org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sequential Language Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequential Language Definition</em>'.
	 * @generated
	 */
	SequentialLanguageDefinition createSequentialLanguageDefinition();

	/**
	 * Returns a new object of class '<em>DSA Project</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DSA Project</em>'.
	 * @generated
	 */
	DSAProject createDSAProject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Sequential_xdsmlPackage getSequential_xdsmlPackage();

} //Sequential_xdsmlFactory
