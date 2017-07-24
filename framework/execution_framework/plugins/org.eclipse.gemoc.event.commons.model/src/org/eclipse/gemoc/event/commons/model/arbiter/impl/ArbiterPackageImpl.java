/**
 */
package org.eclipse.gemoc.event.commons.model.arbiter.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.event.commons.model.arbiter.Arbiter;
import org.eclipse.gemoc.event.commons.model.arbiter.ArbiterFactory;
import org.eclipse.gemoc.event.commons.model.arbiter.ArbiterPackage;
import org.eclipse.gemoc.event.commons.model.arbiter.State;
import org.eclipse.gemoc.event.commons.model.arbiter.Transition;
import org.eclipse.gemoc.event.commons.model.arbiter.TruthValue;

import org.eclipse.gemoc.event.commons.model.property.PropertyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ArbiterPackageImpl extends EPackageImpl implements ArbiterPackage {
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
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

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
	 * @see org.eclipse.gemoc.event.commons.model.arbiter.ArbiterPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ArbiterPackageImpl() {
		super(eNS_URI, ArbiterFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ArbiterPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ArbiterPackage init() {
		if (isInited) return (ArbiterPackage)EPackage.Registry.INSTANCE.getEPackage(ArbiterPackage.eNS_URI);

		// Obtain or create and register package
		ArbiterPackageImpl theArbiterPackage = (ArbiterPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ArbiterPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ArbiterPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theArbiterPackage.createPackageContents();

		// Initialize created meta-data
		theArbiterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theArbiterPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ArbiterPackage.eNS_URI, theArbiterPackage);
		return theArbiterPackage;
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
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_TruthValue() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_OutgoingTransitions() {
		return (EReference)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_IncomingTransitions() {
		return (EReference)stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Source() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Target() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Guard() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
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
	public ArbiterFactory getArbiterFactory() {
		return (ArbiterFactory)getEFactoryInstance();
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
		arbiterEClass = createEClass(ARBITER);
		createEReference(arbiterEClass, ARBITER__STATES);
		createEReference(arbiterEClass, ARBITER__TRANSITIONS);
		createEReference(arbiterEClass, ARBITER__INITIAL_STATE);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__TRUTH_VALUE);
		createEReference(stateEClass, STATE__OUTGOING_TRANSITIONS);
		createEReference(stateEClass, STATE__INCOMING_TRANSITIONS);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__SOURCE);
		createEReference(transitionEClass, TRANSITION__TARGET);
		createEReference(transitionEClass, TRANSITION__GUARD);

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
		ETypeParameter arbiterEClass_P = addETypeParameter(arbiterEClass, "P");
		ETypeParameter arbiterEClass_S = addETypeParameter(arbiterEClass, "S");
		ETypeParameter arbiterEClass_T = addETypeParameter(arbiterEClass, "T");
		ETypeParameter stateEClass_P = addETypeParameter(stateEClass, "P");
		ETypeParameter stateEClass_T = addETypeParameter(stateEClass, "T");
		ETypeParameter transitionEClass_P = addETypeParameter(transitionEClass, "P");
		ETypeParameter transitionEClass_S = addETypeParameter(transitionEClass, "S");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(thePropertyPackage.getProperty());
		arbiterEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getState());
		EGenericType g2 = createEGenericType(arbiterEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(arbiterEClass_T);
		g1.getETypeArguments().add(g2);
		arbiterEClass_S.getEBounds().add(g1);
		g1 = createEGenericType(this.getTransition());
		g2 = createEGenericType(arbiterEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(arbiterEClass_S);
		g1.getETypeArguments().add(g2);
		arbiterEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		stateEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getTransition());
		g2 = createEGenericType(stateEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		stateEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(thePropertyPackage.getProperty());
		transitionEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getState());
		g2 = createEGenericType(transitionEClass_P);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		transitionEClass_S.getEBounds().add(g1);

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(arbiterEClass, Arbiter.class, "Arbiter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(arbiterEClass_S);
		initEReference(getArbiter_States(), g1, null, "states", null, 0, -1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterEClass_T);
		initEReference(getArbiter_Transitions(), g1, null, "transitions", null, 0, -1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(arbiterEClass_S);
		initEReference(getArbiter_InitialState(), g1, null, "initialState", null, 0, 1, Arbiter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_TruthValue(), this.getTruthValue(), "truthValue", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(stateEClass_T);
		initEReference(getState_OutgoingTransitions(), g1, this.getTransition_Source(), "outgoingTransitions", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(stateEClass_T);
		initEReference(getState_IncomingTransitions(), g1, this.getTransition_Target(), "incomingTransitions", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(transitionEClass_S);
		initEReference(getTransition_Source(), g1, this.getState_OutgoingTransitions(), "source", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(transitionEClass_S);
		initEReference(getTransition_Target(), g1, this.getState_IncomingTransitions(), "target", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(transitionEClass_P);
		initEReference(getTransition_Guard(), g1, null, "guard", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(truthValueEEnum, TruthValue.class, "TruthValue");
		addEEnumLiteral(truthValueEEnum, TruthValue.TRUE);
		addEEnumLiteral(truthValueEEnum, TruthValue.FALSE);
		addEEnumLiteral(truthValueEEnum, TruthValue.INCONCLUSIVE);

		// Create resource
		createResource(eNS_URI);
	}

} //ArbiterPackageImpl
