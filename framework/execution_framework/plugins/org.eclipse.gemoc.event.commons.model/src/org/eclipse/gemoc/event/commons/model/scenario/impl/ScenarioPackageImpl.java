/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.event.commons.model.property.PropertyPackage;

import org.eclipse.gemoc.event.commons.model.property.impl.PropertyPackageImpl;

import org.eclipse.gemoc.event.commons.model.scenario.Arbiter;
import org.eclipse.gemoc.event.commons.model.scenario.ArbiterState;
import org.eclipse.gemoc.event.commons.model.scenario.ArbiterTransition;
import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider;
import org.eclipse.gemoc.event.commons.model.scenario.ElementQuery;
import org.eclipse.gemoc.event.commons.model.scenario.ElementReference;
import org.eclipse.gemoc.event.commons.model.scenario.Event;
import org.eclipse.gemoc.event.commons.model.scenario.EventOccurrence;
import org.eclipse.gemoc.event.commons.model.scenario.Scenario;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioFactory;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;
import org.eclipse.gemoc.event.commons.model.scenario.TruthValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioPackageImpl extends EPackageImpl implements ScenarioPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementProviderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementQueryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioFSMEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioFSMStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioFSMTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arbiterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arbiterStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arbiterTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum truthValueEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ScenarioPackageImpl() {
		super(eNS_URI, ScenarioFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ScenarioPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ScenarioPackage init() {
		if (isInited) return (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);

		// Obtain or create and register package
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ScenarioPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		PropertyPackageImpl thePropertyPackage = (PropertyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PropertyPackage.eNS_URI) instanceof PropertyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PropertyPackage.eNS_URI) : PropertyPackage.eINSTANCE);

		// Create package meta-data objects
		theScenarioPackage.createPackageContents();
		thePropertyPackage.createPackageContents();

		// Initialize created meta-data
		theScenarioPackage.initializePackageContents();
		thePropertyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theScenarioPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ScenarioPackage.eNS_URI, theScenarioPackage);
		return theScenarioPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenario() {
		return scenarioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenario_Elements() {
		return (EReference)scenarioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvent() {
		return eventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementProvider() {
		return elementProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementReference() {
		return elementReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementReference_Element() {
		return (EReference)elementReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementQuery() {
		return elementQueryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementQuery_Query() {
		return (EReference)elementQueryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventOccurrence() {
		return eventOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventOccurrence_Event() {
		return (EReference)eventOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventOccurrence_Time() {
		return (EAttribute)eventOccurrenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenarioElement() {
		return scenarioElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioElement_PreviousElements() {
		return (EReference)scenarioElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioElement_NextElements() {
		return (EReference)scenarioElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioElement_Guard() {
		return (EReference)scenarioElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenarioFSM() {
		return scenarioFSMEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSM_States() {
		return (EReference)scenarioFSMEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSM_InitialState() {
		return (EReference)scenarioFSMEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSM_AcceptingStates() {
		return (EReference)scenarioFSMEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSM_Transitions() {
		return (EReference)scenarioFSMEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScenarioFSM_Name() {
		return (EAttribute)scenarioFSMEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenarioFSMState() {
		return scenarioFSMStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMState_Event() {
		return (EReference)scenarioFSMStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMState_OutgoingTransitions() {
		return (EReference)scenarioFSMStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMState_IncomingTransitions() {
		return (EReference)scenarioFSMStateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScenarioFSMState_Name() {
		return (EAttribute)scenarioFSMStateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenarioFSMTransition() {
		return scenarioFSMTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMTransition_Guard() {
		return (EReference)scenarioFSMTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMTransition_Source() {
		return (EReference)scenarioFSMTransitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioFSMTransition_Target() {
		return (EReference)scenarioFSMTransitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScenarioFSMTransition_Name() {
		return (EAttribute)scenarioFSMTransitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArbiter() {
		return arbiterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiter_States() {
		return (EReference)arbiterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiter_Transitions() {
		return (EReference)arbiterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiter_InitialState() {
		return (EReference)arbiterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiter_AcceptingStates() {
		return (EReference)arbiterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArbiter_Name() {
		return (EAttribute)arbiterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArbiterState() {
		return arbiterStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiterState_IncomingTransitions() {
		return (EReference)arbiterStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiterState_OutgoingTransitions() {
		return (EReference)arbiterStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArbiterState_TruthValue() {
		return (EAttribute)arbiterStateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArbiterState_Name() {
		return (EAttribute)arbiterStateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArbiterTransition() {
		return arbiterTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiterTransition_Guard() {
		return (EReference)arbiterTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiterTransition_Source() {
		return (EReference)arbiterTransitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArbiterTransition_Target() {
		return (EReference)arbiterTransitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArbiterTransition_Name() {
		return (EAttribute)arbiterTransitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTruthValue() {
		return truthValueEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioFactory getScenarioFactory() {
		return (ScenarioFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		scenarioEClass = createEClass(SCENARIO);
		createEReference(scenarioEClass, SCENARIO__ELEMENTS);

		eventEClass = createEClass(EVENT);

		elementProviderEClass = createEClass(ELEMENT_PROVIDER);

		elementReferenceEClass = createEClass(ELEMENT_REFERENCE);
		createEReference(elementReferenceEClass, ELEMENT_REFERENCE__ELEMENT);

		elementQueryEClass = createEClass(ELEMENT_QUERY);
		createEReference(elementQueryEClass, ELEMENT_QUERY__QUERY);

		eventOccurrenceEClass = createEClass(EVENT_OCCURRENCE);
		createEReference(eventOccurrenceEClass, EVENT_OCCURRENCE__EVENT);
		createEAttribute(eventOccurrenceEClass, EVENT_OCCURRENCE__TIME);

		scenarioElementEClass = createEClass(SCENARIO_ELEMENT);
		createEReference(scenarioElementEClass, SCENARIO_ELEMENT__PREVIOUS_ELEMENTS);
		createEReference(scenarioElementEClass, SCENARIO_ELEMENT__NEXT_ELEMENTS);
		createEReference(scenarioElementEClass, SCENARIO_ELEMENT__GUARD);

		scenarioFSMEClass = createEClass(SCENARIO_FSM);
		createEReference(scenarioFSMEClass, SCENARIO_FSM__STATES);
		createEReference(scenarioFSMEClass, SCENARIO_FSM__INITIAL_STATE);
		createEReference(scenarioFSMEClass, SCENARIO_FSM__ACCEPTING_STATES);
		createEReference(scenarioFSMEClass, SCENARIO_FSM__TRANSITIONS);
		createEAttribute(scenarioFSMEClass, SCENARIO_FSM__NAME);

		scenarioFSMStateEClass = createEClass(SCENARIO_FSM_STATE);
		createEReference(scenarioFSMStateEClass, SCENARIO_FSM_STATE__EVENT);
		createEReference(scenarioFSMStateEClass, SCENARIO_FSM_STATE__OUTGOING_TRANSITIONS);
		createEReference(scenarioFSMStateEClass, SCENARIO_FSM_STATE__INCOMING_TRANSITIONS);
		createEAttribute(scenarioFSMStateEClass, SCENARIO_FSM_STATE__NAME);

		scenarioFSMTransitionEClass = createEClass(SCENARIO_FSM_TRANSITION);
		createEReference(scenarioFSMTransitionEClass, SCENARIO_FSM_TRANSITION__GUARD);
		createEReference(scenarioFSMTransitionEClass, SCENARIO_FSM_TRANSITION__SOURCE);
		createEReference(scenarioFSMTransitionEClass, SCENARIO_FSM_TRANSITION__TARGET);
		createEAttribute(scenarioFSMTransitionEClass, SCENARIO_FSM_TRANSITION__NAME);

		arbiterEClass = createEClass(ARBITER);
		createEReference(arbiterEClass, ARBITER__STATES);
		createEReference(arbiterEClass, ARBITER__TRANSITIONS);
		createEReference(arbiterEClass, ARBITER__INITIAL_STATE);
		createEReference(arbiterEClass, ARBITER__ACCEPTING_STATES);
		createEAttribute(arbiterEClass, ARBITER__NAME);

		arbiterStateEClass = createEClass(ARBITER_STATE);
		createEReference(arbiterStateEClass, ARBITER_STATE__INCOMING_TRANSITIONS);
		createEReference(arbiterStateEClass, ARBITER_STATE__OUTGOING_TRANSITIONS);
		createEAttribute(arbiterStateEClass, ARBITER_STATE__TRUTH_VALUE);
		createEAttribute(arbiterStateEClass, ARBITER_STATE__NAME);

		arbiterTransitionEClass = createEClass(ARBITER_TRANSITION);
		createEReference(arbiterTransitionEClass, ARBITER_TRANSITION__GUARD);
		createEReference(arbiterTransitionEClass, ARBITER_TRANSITION__SOURCE);
		createEReference(arbiterTransitionEClass, ARBITER_TRANSITION__TARGET);
		createEAttribute(arbiterTransitionEClass, ARBITER_TRANSITION__NAME);

		// Create enums
		truthValueEEnum = createEEnum(TRUTH_VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PropertyPackage thePropertyPackage = (PropertyPackage)EPackage.Registry.INSTANCE.getEPackage(PropertyPackage.eNS_URI);

		// Create type parameters
		ETypeParameter scenarioEClass_T = addETypeParameter(scenarioEClass, "T");
		addETypeParameter(elementProviderEClass, "T");
		ETypeParameter elementReferenceEClass_T = addETypeParameter(elementReferenceEClass, "T");
		ETypeParameter elementQueryEClass_T = addETypeParameter(elementQueryEClass, "T");
		ETypeParameter elementQueryEClass_P = addETypeParameter(elementQueryEClass, "P");
		ETypeParameter eventOccurrenceEClass_E = addETypeParameter(eventOccurrenceEClass, "E");
		ETypeParameter eventOccurrenceEClass_P = addETypeParameter(eventOccurrenceEClass, "P");
		ETypeParameter scenarioElementEClass_P = addETypeParameter(scenarioElementEClass, "P");
		ETypeParameter scenarioFSMEClass_P = addETypeParameter(scenarioFSMEClass, "P");
		ETypeParameter scenarioFSMEClass_E = addETypeParameter(scenarioFSMEClass, "E");
		ETypeParameter scenarioFSMEClass_S = addETypeParameter(scenarioFSMEClass, "S");
		ETypeParameter scenarioFSMEClass_T = addETypeParameter(scenarioFSMEClass, "T");
		ETypeParameter scenarioFSMStateEClass_E = addETypeParameter(scenarioFSMStateEClass, "E");
		ETypeParameter scenarioFSMStateEClass_T = addETypeParameter(scenarioFSMStateEClass, "T");
		ETypeParameter scenarioFSMTransitionEClass_P = addETypeParameter(scenarioFSMTransitionEClass, "P");
		ETypeParameter scenarioFSMTransitionEClass_S = addETypeParameter(scenarioFSMTransitionEClass, "S");
		ETypeParameter arbiterEClass_P = addETypeParameter(arbiterEClass, "P");
		ETypeParameter arbiterEClass_S = addETypeParameter(arbiterEClass, "S");
		ETypeParameter arbiterEClass_T = addETypeParameter(arbiterEClass, "T");
		ETypeParameter arbiterStateEClass_P = addETypeParameter(arbiterStateEClass, "P");
		ETypeParameter arbiterStateEClass_T = addETypeParameter(arbiterStateEClass, "T");
		ETypeParameter arbiterTransitionEClass_P = addETypeParameter(arbiterTransitionEClass, "P");
		ETypeParameter arbiterTransitionEClass_S = addETypeParameter(arbiterTransitionEClass, "S");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getScenarioElement());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		scenarioEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getStateProperty());
		g2 = createEGenericType(elementQueryEClass_T);
		g1.getETypeArguments().add(g2);
		elementQueryEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getEvent());
		eventOccurrenceEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		eventOccurrenceEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		scenarioElementEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		scenarioFSMEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getEvent());
		scenarioFSMEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getScenarioFSMState());
		g2 = createEGenericType(scenarioFSMEClass_E);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(scenarioFSMEClass_T);
		g1.getETypeArguments().add(g2);
		scenarioFSMEClass_S.getEBounds().add(g1);
		g1 = createEGenericType(this.getScenarioFSMTransition());
		g2 = createEGenericType(scenarioFSMEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(scenarioFSMEClass_S);
		g1.getETypeArguments().add(g2);
		scenarioFSMEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getEvent());
		scenarioFSMStateEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getScenarioFSMTransition());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		scenarioFSMStateEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		scenarioFSMTransitionEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getScenarioFSMState());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		scenarioFSMTransitionEClass_S.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		arbiterEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getArbiterState());
		g2 = createEGenericType(arbiterEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(arbiterEClass_T);
		g1.getETypeArguments().add(g2);
		arbiterEClass_S.getEBounds().add(g1);
		g1 = createEGenericType(this.getArbiterTransition());
		g2 = createEGenericType(arbiterEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(arbiterEClass_S);
		g1.getETypeArguments().add(g2);
		arbiterEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		arbiterStateEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getArbiterTransition());
		g2 = createEGenericType(arbiterStateEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		arbiterStateEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		arbiterTransitionEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getArbiterState());
		g2 = createEGenericType(arbiterTransitionEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		arbiterTransitionEClass_S.getEBounds().add(g1);

		// Add supertypes to classes
		g1 = createEGenericType(this.getElementProvider());
		g2 = createEGenericType(elementReferenceEClass_T);
		g1.getETypeArguments().add(g2);
		elementReferenceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getElementProvider());
		g2 = createEGenericType(elementQueryEClass_T);
		g1.getETypeArguments().add(g2);
		elementQueryEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScenarioElement());
		g2 = createEGenericType(eventOccurrenceEClass_P);
		g1.getETypeArguments().add(g2);
		eventOccurrenceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScenarioElement());
		g2 = createEGenericType(scenarioFSMEClass_P);
		g1.getETypeArguments().add(g2);
		scenarioFSMEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(scenarioEClass, Scenario.class, "Scenario", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(scenarioEClass_T);
		initEReference(getScenario_Elements(), g1, null, "elements", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventEClass, Event.class, "Event", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementProviderEClass, ElementProvider.class, "ElementProvider", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementReferenceEClass, ElementReference.class, "ElementReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(elementReferenceEClass_T);
		initEReference(getElementReference_Element(), g1, null, "element", null, 0, 1, ElementReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementQueryEClass, ElementQuery.class, "ElementQuery", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(elementQueryEClass_P);
		initEReference(getElementQuery_Query(), g1, null, "query", null, 0, 1, ElementQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventOccurrenceEClass, EventOccurrence.class, "EventOccurrence", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(eventOccurrenceEClass_E);
		initEReference(getEventOccurrence_Event(), g1, null, "event", null, 0, 1, EventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventOccurrence_Time(), ecorePackage.getEInt(), "time", null, 0, 1, EventOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioElementEClass, ScenarioElement.class, "ScenarioElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getScenarioElement());
		g2 = createEGenericType(scenarioElementEClass_P);
		g1.getETypeArguments().add(g2);
		initEReference(getScenarioElement_PreviousElements(), g1, this.getScenarioElement_NextElements(), "previousElements", null, 0, -1, ScenarioElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getScenarioElement());
		g2 = createEGenericType(scenarioElementEClass_P);
		g1.getETypeArguments().add(g2);
		initEReference(getScenarioElement_NextElements(), g1, this.getScenarioElement_PreviousElements(), "nextElements", null, 0, -1, ScenarioElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioElementEClass_P);
		initEReference(getScenarioElement_Guard(), g1, null, "guard", null, 0, 1, ScenarioElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioFSMEClass, ScenarioFSM.class, "ScenarioFSM", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(scenarioFSMEClass_S);
		initEReference(getScenarioFSM_States(), g1, null, "states", null, 0, -1, ScenarioFSM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMEClass_S);
		initEReference(getScenarioFSM_InitialState(), g1, null, "initialState", null, 1, 1, ScenarioFSM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMEClass_S);
		initEReference(getScenarioFSM_AcceptingStates(), g1, null, "acceptingStates", null, 0, -1, ScenarioFSM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMEClass_T);
		initEReference(getScenarioFSM_Transitions(), g1, null, "transitions", null, 0, -1, ScenarioFSM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScenarioFSM_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScenarioFSM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioFSMStateEClass, ScenarioFSMState.class, "ScenarioFSMState", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(scenarioFSMStateEClass_E);
		initEReference(getScenarioFSMState_Event(), g1, null, "event", null, 0, 1, ScenarioFSMState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMStateEClass_T);
		initEReference(getScenarioFSMState_OutgoingTransitions(), g1, this.getScenarioFSMTransition_Source(), "outgoingTransitions", null, 0, -1, ScenarioFSMState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMStateEClass_T);
		initEReference(getScenarioFSMState_IncomingTransitions(), g1, this.getScenarioFSMTransition_Target(), "incomingTransitions", null, 0, -1, ScenarioFSMState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScenarioFSMState_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScenarioFSMState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioFSMTransitionEClass, ScenarioFSMTransition.class, "ScenarioFSMTransition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(scenarioFSMTransitionEClass_P);
		initEReference(getScenarioFSMTransition_Guard(), g1, null, "guard", null, 0, 1, ScenarioFSMTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMTransitionEClass_S);
		initEReference(getScenarioFSMTransition_Source(), g1, this.getScenarioFSMState_OutgoingTransitions(), "source", null, 1, 1, ScenarioFSMTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scenarioFSMTransitionEClass_S);
		initEReference(getScenarioFSMTransition_Target(), g1, this.getScenarioFSMState_IncomingTransitions(), "target", null, 1, 1, ScenarioFSMTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScenarioFSMTransition_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScenarioFSMTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arbiterEClass, Arbiter.class, "Arbiter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(arbiterEClass_S);
		initEReference(getArbiter_States(), g1, null, "states", null, 0, -1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterEClass_T);
		initEReference(getArbiter_Transitions(), g1, null, "transitions", null, 0, -1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterEClass_S);
		initEReference(getArbiter_InitialState(), g1, null, "initialState", null, 0, 1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterEClass_S);
		initEReference(getArbiter_AcceptingStates(), g1, null, "acceptingStates", null, 0, -1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArbiter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arbiterStateEClass, ArbiterState.class, "ArbiterState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(arbiterStateEClass_T);
		initEReference(getArbiterState_IncomingTransitions(), g1, this.getArbiterTransition_Target(), "incomingTransitions", null, 0, -1, ArbiterState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterStateEClass_T);
		initEReference(getArbiterState_OutgoingTransitions(), g1, this.getArbiterTransition_Source(), "outgoingTransitions", null, 0, -1, ArbiterState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArbiterState_TruthValue(), this.getTruthValue(), "truthValue", null, 1, 1, ArbiterState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArbiterState_Name(), ecorePackage.getEString(), "name", null, 0, 1, ArbiterState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arbiterTransitionEClass, ArbiterTransition.class, "ArbiterTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(arbiterTransitionEClass_P);
		initEReference(getArbiterTransition_Guard(), g1, null, "guard", null, 0, 1, ArbiterTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterTransitionEClass_S);
		initEReference(getArbiterTransition_Source(), g1, this.getArbiterState_OutgoingTransitions(), "source", null, 1, 1, ArbiterTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterTransitionEClass_S);
		initEReference(getArbiterTransition_Target(), g1, this.getArbiterState_IncomingTransitions(), "target", null, 1, 1, ArbiterTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArbiterTransition_Name(), ecorePackage.getEString(), "name", null, 0, 1, ArbiterTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(truthValueEEnum, TruthValue.class, "TruthValue");
		addEEnumLiteral(truthValueEEnum, TruthValue.INCONCLUSIVE);
		addEEnumLiteral(truthValueEEnum, TruthValue.TRUE);
		addEEnumLiteral(truthValueEEnum, TruthValue.FALSE);

		// Create resource
		createResource(eNS_URI);
	}

} //ScenarioPackageImpl
