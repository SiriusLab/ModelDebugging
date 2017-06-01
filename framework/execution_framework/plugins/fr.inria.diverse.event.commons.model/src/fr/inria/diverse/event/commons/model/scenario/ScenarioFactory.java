/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import fr.inria.diverse.event.commons.model.property.Property;
import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage
 * @generated
 */
public interface ScenarioFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScenarioFactory eINSTANCE = fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Arbiter State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arbiter State</em>'.
	 * @generated
	 */
	<P extends Property, T extends ArbiterTransition<P, ?>> ArbiterState<P, T> createArbiterState();

	/**
	 * Returns a new object of class '<em>Arbiter Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arbiter Transition</em>'.
	 * @generated
	 */
	<P extends Property, S extends ArbiterState<P, ?>> ArbiterTransition<P, S> createArbiterTransition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ScenarioPackage getScenarioPackage();

} //ScenarioFactory
