/**
 */
package tracingannotations;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tracingannotations.TracingannotationsPackage
 * @generated
 */
public interface TracingannotationsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracingannotationsFactory eINSTANCE = tracingannotations.impl.TracingannotationsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Tracing Annotations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracing Annotations</em>'.
	 * @generated
	 */
	TracingAnnotations createTracingAnnotations();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracingannotationsPackage getTracingannotationsPackage();

} //TracingannotationsFactory
