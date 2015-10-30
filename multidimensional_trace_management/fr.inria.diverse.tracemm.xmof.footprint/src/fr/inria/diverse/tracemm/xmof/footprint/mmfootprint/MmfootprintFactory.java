/**
 */
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprint;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage
 * @generated
 */
public interface MmfootprintFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MmfootprintFactory eINSTANCE = fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.MmfootprintFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Footprint</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Footprint</em>'.
	 * @generated
	 */
	Footprint createFootprint();

	/**
	 * Returns a new object of class '<em>EModel Element Access</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EModel Element Access</em>'.
	 * @generated
	 */
	EModelElementAccess createEModelElementAccess();

	/**
	 * Returns a new object of class '<em>Location</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Location</em>'.
	 * @generated
	 */
	Location createLocation();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	MmfootprintPackage getMmfootprintPackage();

} // MmfootprintFactory
