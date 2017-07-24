/**
 */
package org.eclipse.gemoc.event.commons.model.arbiter;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.event.commons.model.arbiter.ArbiterPackage
 * @generated
 */
public interface ArbiterFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ArbiterFactory eINSTANCE = org.eclipse.gemoc.event.commons.model.arbiter.impl.ArbiterFactoryImpl.init();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ArbiterPackage getArbiterPackage();

} //ArbiterFactory
