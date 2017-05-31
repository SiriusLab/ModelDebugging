/**
 */
package fr.inria.diverse.event.commons.model.scenario.impl;

import fr.inria.diverse.event.commons.model.property.PropertyPackage;

import fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl;
import fr.inria.diverse.event.commons.model.scenario.ElementProvider;
import fr.inria.diverse.event.commons.model.scenario.ElementQuery;
import fr.inria.diverse.event.commons.model.scenario.ElementReference;
import fr.inria.diverse.event.commons.model.scenario.Event;
import fr.inria.diverse.event.commons.model.scenario.EventOccurrence;
import fr.inria.diverse.event.commons.model.scenario.Scenario;
import fr.inria.diverse.event.commons.model.scenario.ScenarioElement;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSM;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSMState;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFSMTransition;
import fr.inria.diverse.event.commons.model.scenario.ScenarioFactory;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
	 * @see fr.inria.diverse.event.commons.model.scenario.ScenarioPackage#eNS_URI
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
	public EReference getEvent_TargetProvider() {
		return (EReference)eventEClass.getEStructuralFeatures().get(0);
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
	public EReference getScenarioElement_NextElements() {
		return (EReference)scenarioElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScenarioElement_Guard() {
		return (EReference)scenarioElementEClass.getEStructuralFeatures().get(1);
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
		createEReference(eventEClass, EVENT__TARGET_PROVIDER);

		elementProviderEClass = createEClass(ELEMENT_PROVIDER);

		elementReferenceEClass = createEClass(ELEMENT_REFERENCE);
		createEReference(elementReferenceEClass, ELEMENT_REFERENCE__ELEMENT);

		elementQueryEClass = createEClass(ELEMENT_QUERY);
		createEReference(elementQueryEClass, ELEMENT_QUERY__QUERY);

		eventOccurrenceEClass = createEClass(EVENT_OCCURRENCE);
		createEReference(eventOccurrenceEClass, EVENT_OCCURRENCE__EVENT);
		createEAttribute(eventOccurrenceEClass, EVENT_OCCURRENCE__TIME);

		scenarioElementEClass = createEClass(SCENARIO_ELEMENT);
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
		ETypeParameter eventEClass_T = addETypeParameter(eventEClass, "T");
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
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		eventOccurrenceEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		eventOccurrenceEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		scenarioElementEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		scenarioFSMEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getEvent());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
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
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
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
		g1 = createEGenericType(this.getElementProvider());
		g2 = createEGenericType(eventEClass_T);
		g1.getETypeArguments().add(g2);
		initEReference(getEvent_TargetProvider(), g1, null, "targetProvider", null, 1, 1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEReference(getScenarioElement_NextElements(), g1, null, "nextElements", null, 0, -1, ScenarioElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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

		// Create resource
		createResource(eNS_URI);
	}

} //ScenarioPackageImpl
