/**
 */
package newTfsm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see newTfsm.NewTfsmPackage
 * @generated
 */
public interface NewTfsmFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NewTfsmFactory eINSTANCE = newTfsm.impl.NewTfsmFactoryImpl.init();

    /**
     * Returns a new object of class '<em>TFSM</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>TFSM</em>'.
     * @generated
     */
    TFSM createTFSM();

    /**
     * Returns a new object of class '<em>State</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>State</em>'.
     * @generated
     */
    State createState();

    /**
     * Returns a new object of class '<em>Transition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Transition</em>'.
     * @generated
     */
    Transition createTransition();

    /**
     * Returns a new object of class '<em>Named Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Named Element</em>'.
     * @generated
     */
    NamedElement createNamedElement();

    /**
     * Returns a new object of class '<em>Temporal Guard</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Temporal Guard</em>'.
     * @generated
     */
    TemporalGuard createTemporalGuard();

    /**
     * Returns a new object of class '<em>Event Guard</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Event Guard</em>'.
     * @generated
     */
    EventGuard createEventGuard();

    /**
     * Returns a new object of class '<em>FSM Event</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>FSM Event</em>'.
     * @generated
     */
    FSMEvent createFSMEvent();

    /**
     * Returns a new object of class '<em>FSM Clock</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>FSM Clock</em>'.
     * @generated
     */
    FSMClock createFSMClock();

    /**
     * Returns a new object of class '<em>System</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>System</em>'.
     * @generated
     */
    System createSystem();

    /**
     * Returns a new object of class '<em>Evaluate Guard</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Evaluate Guard</em>'.
     * @generated
     */
    EvaluateGuard createEvaluateGuard();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    NewTfsmPackage getNewTfsmPackage();

} //NewTfsmFactory
