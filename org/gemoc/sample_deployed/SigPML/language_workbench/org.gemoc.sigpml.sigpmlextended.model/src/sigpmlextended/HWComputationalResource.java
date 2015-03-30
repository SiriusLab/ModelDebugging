/**
 */
package sigpmlextended;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>HW Computational Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sigpmlextended.HWComputationalResource#isIsUnderPreemptiveManagement <em>Is Under Preemptive Management</em>}</li>
 *   <li>{@link sigpmlextended.HWComputationalResource#getAllocatedAgents <em>Allocated Agents</em>}</li>
 * </ul>
 * </p>
 *
 * @see sigpmlextended.SigpmlextendedPackage#getHWComputationalResource()
 * @model
 * @generated
 */
public interface HWComputationalResource extends HWRessource {
	/**
	 * Returns the value of the '<em><b>Is Under Preemptive Management</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Under Preemptive Management</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Under Preemptive Management</em>' attribute.
	 * @see #setIsUnderPreemptiveManagement(boolean)
	 * @see sigpmlextended.SigpmlextendedPackage#getHWComputationalResource_IsUnderPreemptiveManagement()
	 * @model
	 * @generated
	 */
	boolean isIsUnderPreemptiveManagement();

	/**
	 * Sets the value of the '{@link sigpmlextended.HWComputationalResource#isIsUnderPreemptiveManagement <em>Is Under Preemptive Management</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Under Preemptive Management</em>' attribute.
	 * @see #isIsUnderPreemptiveManagement()
	 * @generated
	 */
	void setIsUnderPreemptiveManagement(boolean value);

	/**
	 * Returns the value of the '<em><b>Allocated Agents</b></em>' reference list.
	 * The list contents are of type {@link sigpmlextended.Agent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Agents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Agents</em>' reference list.
	 * @see sigpmlextended.SigpmlextendedPackage#getHWComputationalResource_AllocatedAgents()
	 * @model
	 * @generated
	 */
	EList<Agent> getAllocatedAgents();

} // HWComputationalResource
