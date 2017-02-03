/**
 */
package base.States;

import fr.inria.diverse.trace.commons.model.trace.Value;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Value</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see base.States.StatesPackage#getSpecificValue()
 * @model abstract="true"
 * @generated
 */
public interface SpecificValue extends Value<SpecificState> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final EList<SpecificState> result = new org.eclipse.emf.common.util.BasicEList<SpecificState>();\nresult.addAll(getStates());\nreturn result;'"
	 * @generated
	 */
	EList<SpecificState> getStates();

} // SpecificValue
