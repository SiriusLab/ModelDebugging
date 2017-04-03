/**
 */
package opsemanticsview;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see opsemanticsview.OpsemanticsviewPackage
 * @generated
 */
public interface OpsemanticsviewFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OpsemanticsviewFactory eINSTANCE = opsemanticsview.impl.OpsemanticsviewFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Operational Semantics View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operational Semantics View</em>'.
	 * @generated
	 */
	OperationalSemanticsView createOperationalSemanticsView();

	/**
	 * Returns a new object of class '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule</em>'.
	 * @generated
	 */
	Rule createRule();

	/**
	 * Returns a new object of class '<em>Execution To AS Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution To AS Entry</em>'.
	 * @generated
	 */
	ExecutionToASEntry createExecutionToASEntry();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OpsemanticsviewPackage getOpsemanticsviewPackage();

} //OpsemanticsviewFactory
