/**
 */
package tfsmextended;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timed System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tfsmextended.TimedSystem#getTfsms <em>Tfsms</em>}</li>
 *   <li>{@link tfsmextended.TimedSystem#getGlobalClocks <em>Global Clocks</em>}</li>
 *   <li>{@link tfsmextended.TimedSystem#getGlobalEvents <em>Global Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see tfsmextended.TfsmextendedPackage#getTimedSystem()
 * @model
 * @generated
 */
public interface TimedSystem extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Tfsms</b></em>' containment reference list.
	 * The list contents are of type {@link tfsmextended.TFSM}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tfsms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tfsms</em>' containment reference list.
	 * @see tfsmextended.TfsmextendedPackage#getTimedSystem_Tfsms()
	 * @model containment="true"
	 * @generated
	 */
	EList<TFSM> getTfsms();

	/**
	 * Returns the value of the '<em><b>Global Clocks</b></em>' containment reference list.
	 * The list contents are of type {@link tfsmextended.FSMClock}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Clocks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Clocks</em>' containment reference list.
	 * @see tfsmextended.TfsmextendedPackage#getTimedSystem_GlobalClocks()
	 * @model containment="true"
	 * @generated
	 */
	EList<FSMClock> getGlobalClocks();

	/**
	 * Returns the value of the '<em><b>Global Events</b></em>' containment reference list.
	 * The list contents are of type {@link tfsmextended.FSMEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Events</em>' containment reference list.
	 * @see tfsmextended.TfsmextendedPackage#getTimedSystem_GlobalEvents()
	 * @model containment="true"
	 * @generated
	 */
	EList<FSMEvent> getGlobalEvents();

} // TimedSystem
