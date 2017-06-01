/**
 */
package fr.inria.diverse.event.commons.model.scenario;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFactory
 * @model kind="package"
 * @generated
 */
public interface ScenarioPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "scenario";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/scenario";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "scenario";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScenarioPackage eINSTANCE = fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.EventImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 1;

	/**
	 * The feature id for the '<em><b>Target Provider</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TARGET_PROVIDER = 0;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementProviderImpl <em>Element Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementProviderImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementProvider()
	 * @generated
	 */
	int ELEMENT_PROVIDER = 2;

	/**
	 * The number of structural features of the '<em>Element Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_PROVIDER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Element Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_PROVIDER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementReferenceImpl <em>Element Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementReferenceImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementReference()
	 * @generated
	 */
	int ELEMENT_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_REFERENCE__ELEMENT = ELEMENT_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_REFERENCE_FEATURE_COUNT = ELEMENT_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Element Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_REFERENCE_OPERATION_COUNT = ELEMENT_PROVIDER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementQueryImpl <em>Element Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementQueryImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementQuery()
	 * @generated
	 */
	int ELEMENT_QUERY = 4;

	/**
	 * The feature id for the '<em><b>Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_QUERY__QUERY = ELEMENT_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_QUERY_FEATURE_COUNT = ELEMENT_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Element Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_QUERY_OPERATION_COUNT = ELEMENT_PROVIDER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioElementImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioElement()
	 * @generated
	 */
	int SCENARIO_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Next Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_ELEMENT__NEXT_ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_ELEMENT__GUARD = 1;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.EventOccurrenceImpl <em>Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.EventOccurrenceImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getEventOccurrence()
	 * @generated
	 */
	int EVENT_OCCURRENCE = 5;

	/**
	 * The feature id for the '<em><b>Next Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__NEXT_ELEMENTS = SCENARIO_ELEMENT__NEXT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__GUARD = SCENARIO_ELEMENT__GUARD;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__EVENT = SCENARIO_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__TIME = SCENARIO_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_FEATURE_COUNT = SCENARIO_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_OPERATION_COUNT = SCENARIO_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMImpl <em>FSM</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSM()
	 * @generated
	 */
	int SCENARIO_FSM = 7;

	/**
	 * The feature id for the '<em><b>Next Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__NEXT_ELEMENTS = SCENARIO_ELEMENT__NEXT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__GUARD = SCENARIO_ELEMENT__GUARD;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__STATES = SCENARIO_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__INITIAL_STATE = SCENARIO_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Accepting States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__ACCEPTING_STATES = SCENARIO_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__TRANSITIONS = SCENARIO_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM__NAME = SCENARIO_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>FSM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_FEATURE_COUNT = SCENARIO_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>FSM</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_OPERATION_COUNT = SCENARIO_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMStateImpl <em>FSM State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMStateImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSMState()
	 * @generated
	 */
	int SCENARIO_FSM_STATE = 8;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE__EVENT = 0;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE__OUTGOING_TRANSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE__INCOMING_TRANSITIONS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE__NAME = 3;

	/**
	 * The number of structural features of the '<em>FSM State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>FSM State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMTransitionImpl <em>FSM Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMTransitionImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSMTransition()
	 * @generated
	 */
	int SCENARIO_FSM_TRANSITION = 9;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION__GUARD = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION__TARGET = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION__NAME = 3;

	/**
	 * The number of structural features of the '<em>FSM Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>FSM Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FSM_TRANSITION_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl <em>Arbiter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiter()
	 * @generated
	 */
	int ARBITER = 10;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER__STATES = 0;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER__TRANSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER__INITIAL_STATE = 2;

	/**
	 * The feature id for the '<em><b>Accepting States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER__ACCEPTING_STATES = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER__NAME = 4;

	/**
	 * The number of structural features of the '<em>Arbiter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Arbiter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterStateImpl <em>Arbiter State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterStateImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiterState()
	 * @generated
	 */
	int ARBITER_STATE = 11;

	/**
	 * The feature id for the '<em><b>Incoming Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE__INCOMING_TRANSITIONS = 0;

	/**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE__OUTGOING_TRANSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Truth Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE__TRUTH_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Arbiter State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Arbiter State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterTransitionImpl <em>Arbiter Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterTransitionImpl
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiterTransition()
	 * @generated
	 */
	int ARBITER_TRANSITION = 12;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION__GUARD = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION__TARGET = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION__NAME = 3;

	/**
	 * The number of structural features of the '<em>Arbiter Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Arbiter Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARBITER_TRANSITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.scenario.TruthValue <em>Truth Value</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.scenario.TruthValue
	 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getTruthValue()
	 * @generated
	 */
	int TRUTH_VALUE = 13;


	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.Scenario#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Scenario#getElements()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Elements();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.Event#getTargetProvider <em>Target Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target Provider</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Event#getTargetProvider()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_TargetProvider();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ElementProvider <em>Element Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Provider</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ElementProvider
	 * @generated
	 */
	EClass getElementProvider();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ElementReference <em>Element Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Reference</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ElementReference
	 * @generated
	 */
	EClass getElementReference();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ElementReference#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ElementReference#getElement()
	 * @see #getElementReference()
	 * @generated
	 */
	EReference getElementReference_Element();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ElementQuery <em>Element Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Query</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ElementQuery
	 * @generated
	 */
	EClass getElementQuery();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.ElementQuery#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Query</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ElementQuery#getQuery()
	 * @see #getElementQuery()
	 * @generated
	 */
	EReference getElementQuery_Query();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence <em>Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Occurrence</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.EventOccurrence
	 * @generated
	 */
	EClass getEventOccurrence();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getEvent()
	 * @see #getEventOccurrence()
	 * @generated
	 */
	EReference getEventOccurrence_Event();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.EventOccurrence#getTime()
	 * @see #getEventOccurrence()
	 * @generated
	 */
	EAttribute getEventOccurrence_Time();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioElement
	 * @generated
	 */
	EClass getScenarioElement();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getNextElements <em>Next Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Next Elements</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getNextElements()
	 * @see #getScenarioElement()
	 * @generated
	 */
	EReference getScenarioElement_NextElements();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioElement#getGuard()
	 * @see #getScenarioElement()
	 * @generated
	 */
	EReference getScenarioElement_Guard();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM <em>FSM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FSM</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM
	 * @generated
	 */
	EClass getScenarioFSM();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getStates()
	 * @see #getScenarioFSM()
	 * @generated
	 */
	EReference getScenarioFSM_States();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getInitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initial State</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getInitialState()
	 * @see #getScenarioFSM()
	 * @generated
	 */
	EReference getScenarioFSM_InitialState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getAcceptingStates <em>Accepting States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Accepting States</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getAcceptingStates()
	 * @see #getScenarioFSM()
	 * @generated
	 */
	EReference getScenarioFSM_AcceptingStates();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getTransitions()
	 * @see #getScenarioFSM()
	 * @generated
	 */
	EReference getScenarioFSM_Transitions();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSM#getName()
	 * @see #getScenarioFSM()
	 * @generated
	 */
	EAttribute getScenarioFSM_Name();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState <em>FSM State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FSM State</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState
	 * @generated
	 */
	EClass getScenarioFSMState();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getEvent()
	 * @see #getScenarioFSMState()
	 * @generated
	 */
	EReference getScenarioFSMState_Event();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getOutgoingTransitions()
	 * @see #getScenarioFSMState()
	 * @generated
	 */
	EReference getScenarioFSMState_OutgoingTransitions();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getIncomingTransitions()
	 * @see #getScenarioFSMState()
	 * @generated
	 */
	EReference getScenarioFSMState_IncomingTransitions();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState#getName()
	 * @see #getScenarioFSMState()
	 * @generated
	 */
	EAttribute getScenarioFSMState_Name();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition <em>FSM Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FSM Transition</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition
	 * @generated
	 */
	EClass getScenarioFSMTransition();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getGuard()
	 * @see #getScenarioFSMTransition()
	 * @generated
	 */
	EReference getScenarioFSMTransition_Guard();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getSource()
	 * @see #getScenarioFSMTransition()
	 * @generated
	 */
	EReference getScenarioFSMTransition_Source();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getTarget()
	 * @see #getScenarioFSMTransition()
	 * @generated
	 */
	EReference getScenarioFSMTransition_Target();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition#getName()
	 * @see #getScenarioFSMTransition()
	 * @generated
	 */
	EAttribute getScenarioFSMTransition_Name();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter <em>Arbiter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arbiter</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter
	 * @generated
	 */
	EClass getArbiter();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter#getStates()
	 * @see #getArbiter()
	 * @generated
	 */
	EReference getArbiter_States();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter#getTransitions()
	 * @see #getArbiter()
	 * @generated
	 */
	EReference getArbiter_Transitions();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter#getInitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initial State</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter#getInitialState()
	 * @see #getArbiter()
	 * @generated
	 */
	EReference getArbiter_InitialState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter#getAcceptingStates <em>Accepting States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Accepting States</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter#getAcceptingStates()
	 * @see #getArbiter()
	 * @generated
	 */
	EReference getArbiter_AcceptingStates();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.Arbiter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.Arbiter#getName()
	 * @see #getArbiter()
	 * @generated
	 */
	EAttribute getArbiter_Name();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterState <em>Arbiter State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arbiter State</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterState
	 * @generated
	 */
	EClass getArbiterState();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterState#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterState#getIncomingTransitions()
	 * @see #getArbiterState()
	 * @generated
	 */
	EReference getArbiterState_IncomingTransitions();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterState#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Transitions</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterState#getOutgoingTransitions()
	 * @see #getArbiterState()
	 * @generated
	 */
	EReference getArbiterState_OutgoingTransitions();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterState#getTruthValue <em>Truth Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Truth Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterState#getTruthValue()
	 * @see #getArbiterState()
	 * @generated
	 */
	EAttribute getArbiterState_TruthValue();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterState#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterState#getName()
	 * @see #getArbiterState()
	 * @generated
	 */
	EAttribute getArbiterState_Name();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterTransition <em>Arbiter Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arbiter Transition</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterTransition
	 * @generated
	 */
	EClass getArbiterTransition();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getGuard()
	 * @see #getArbiterTransition()
	 * @generated
	 */
	EReference getArbiterTransition_Guard();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getSource()
	 * @see #getArbiterTransition()
	 * @generated
	 */
	EReference getArbiterTransition_Source();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getTarget()
	 * @see #getArbiterTransition()
	 * @generated
	 */
	EReference getArbiterTransition_Target();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.ArbiterTransition#getName()
	 * @see #getArbiterTransition()
	 * @generated
	 */
	EAttribute getArbiterTransition_Name();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.scenario.TruthValue <em>Truth Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Truth Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.scenario.TruthValue
	 * @generated
	 */
	EEnum getTruthValue();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScenarioFactory getScenarioFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__ELEMENTS = eINSTANCE.getScenario_Elements();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.EventImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Target Provider</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__TARGET_PROVIDER = eINSTANCE.getEvent_TargetProvider();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementProviderImpl <em>Element Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementProviderImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementProvider()
		 * @generated
		 */
		EClass ELEMENT_PROVIDER = eINSTANCE.getElementProvider();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementReferenceImpl <em>Element Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementReferenceImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementReference()
		 * @generated
		 */
		EClass ELEMENT_REFERENCE = eINSTANCE.getElementReference();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_REFERENCE__ELEMENT = eINSTANCE.getElementReference_Element();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ElementQueryImpl <em>Element Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ElementQueryImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getElementQuery()
		 * @generated
		 */
		EClass ELEMENT_QUERY = eINSTANCE.getElementQuery();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_QUERY__QUERY = eINSTANCE.getElementQuery_Query();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.EventOccurrenceImpl <em>Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.EventOccurrenceImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getEventOccurrence()
		 * @generated
		 */
		EClass EVENT_OCCURRENCE = eINSTANCE.getEventOccurrence();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_OCCURRENCE__EVENT = eINSTANCE.getEventOccurrence_Event();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_OCCURRENCE__TIME = eINSTANCE.getEventOccurrence_Time();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioElementImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioElement()
		 * @generated
		 */
		EClass SCENARIO_ELEMENT = eINSTANCE.getScenarioElement();

		/**
		 * The meta object literal for the '<em><b>Next Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_ELEMENT__NEXT_ELEMENTS = eINSTANCE.getScenarioElement_NextElements();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_ELEMENT__GUARD = eINSTANCE.getScenarioElement_Guard();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMImpl <em>FSM</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSM()
		 * @generated
		 */
		EClass SCENARIO_FSM = eINSTANCE.getScenarioFSM();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM__STATES = eINSTANCE.getScenarioFSM_States();

		/**
		 * The meta object literal for the '<em><b>Initial State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM__INITIAL_STATE = eINSTANCE.getScenarioFSM_InitialState();

		/**
		 * The meta object literal for the '<em><b>Accepting States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM__ACCEPTING_STATES = eINSTANCE.getScenarioFSM_AcceptingStates();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM__TRANSITIONS = eINSTANCE.getScenarioFSM_Transitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_FSM__NAME = eINSTANCE.getScenarioFSM_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMStateImpl <em>FSM State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMStateImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSMState()
		 * @generated
		 */
		EClass SCENARIO_FSM_STATE = eINSTANCE.getScenarioFSMState();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_STATE__EVENT = eINSTANCE.getScenarioFSMState_Event();

		/**
		 * The meta object literal for the '<em><b>Outgoing Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_STATE__OUTGOING_TRANSITIONS = eINSTANCE.getScenarioFSMState_OutgoingTransitions();

		/**
		 * The meta object literal for the '<em><b>Incoming Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_STATE__INCOMING_TRANSITIONS = eINSTANCE.getScenarioFSMState_IncomingTransitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_FSM_STATE__NAME = eINSTANCE.getScenarioFSMState_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMTransitionImpl <em>FSM Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioFSMTransitionImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getScenarioFSMTransition()
		 * @generated
		 */
		EClass SCENARIO_FSM_TRANSITION = eINSTANCE.getScenarioFSMTransition();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_TRANSITION__GUARD = eINSTANCE.getScenarioFSMTransition_Guard();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_TRANSITION__SOURCE = eINSTANCE.getScenarioFSMTransition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_FSM_TRANSITION__TARGET = eINSTANCE.getScenarioFSMTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_FSM_TRANSITION__NAME = eINSTANCE.getScenarioFSMTransition_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl <em>Arbiter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiter()
		 * @generated
		 */
		EClass ARBITER = eINSTANCE.getArbiter();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER__STATES = eINSTANCE.getArbiter_States();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER__TRANSITIONS = eINSTANCE.getArbiter_Transitions();

		/**
		 * The meta object literal for the '<em><b>Initial State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER__INITIAL_STATE = eINSTANCE.getArbiter_InitialState();

		/**
		 * The meta object literal for the '<em><b>Accepting States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER__ACCEPTING_STATES = eINSTANCE.getArbiter_AcceptingStates();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARBITER__NAME = eINSTANCE.getArbiter_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterStateImpl <em>Arbiter State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterStateImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiterState()
		 * @generated
		 */
		EClass ARBITER_STATE = eINSTANCE.getArbiterState();

		/**
		 * The meta object literal for the '<em><b>Incoming Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER_STATE__INCOMING_TRANSITIONS = eINSTANCE.getArbiterState_IncomingTransitions();

		/**
		 * The meta object literal for the '<em><b>Outgoing Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER_STATE__OUTGOING_TRANSITIONS = eINSTANCE.getArbiterState_OutgoingTransitions();

		/**
		 * The meta object literal for the '<em><b>Truth Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARBITER_STATE__TRUTH_VALUE = eINSTANCE.getArbiterState_TruthValue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARBITER_STATE__NAME = eINSTANCE.getArbiterState_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterTransitionImpl <em>Arbiter Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ArbiterTransitionImpl
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getArbiterTransition()
		 * @generated
		 */
		EClass ARBITER_TRANSITION = eINSTANCE.getArbiterTransition();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER_TRANSITION__GUARD = eINSTANCE.getArbiterTransition_Guard();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER_TRANSITION__SOURCE = eINSTANCE.getArbiterTransition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARBITER_TRANSITION__TARGET = eINSTANCE.getArbiterTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARBITER_TRANSITION__NAME = eINSTANCE.getArbiterTransition_Name();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.scenario.TruthValue <em>Truth Value</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.scenario.TruthValue
		 * @see fr.inria.diverse.event.commons.model.scenario.impl.ScenarioPackageImpl#getTruthValue()
		 * @generated
		 */
		EEnum TRUTH_VALUE = eINSTANCE.getTruthValue();

	}

} //ScenarioPackage
