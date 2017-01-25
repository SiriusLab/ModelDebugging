/**
 */
package base.States;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Reference Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link base.States.SpecificReferenceValue#getStatesNoOpposite <em>States No Opposite</em>}</li>
 * </ul>
 *
 * @see base.States.StatesPackage#getSpecificReferenceValue()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificReferenceValue<T> extends SpecificValue {
	/**
	 * Returns the value of the '<em><b>States No Opposite</b></em>' reference list.
	 * The list contents are of type {@link base.States.SpecificState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States No Opposite</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States No Opposite</em>' reference list.
	 * @see base.States.StatesPackage#getSpecificReferenceValue_StatesNoOpposite()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<SpecificState> getStatesNoOpposite();

} // SpecificReferenceValue
