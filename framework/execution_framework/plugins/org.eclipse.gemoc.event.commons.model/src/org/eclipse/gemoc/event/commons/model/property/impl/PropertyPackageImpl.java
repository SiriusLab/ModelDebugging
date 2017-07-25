/**
 */
package org.eclipse.gemoc.event.commons.model.property.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.event.commons.model.property.AbstractProperty;
import org.eclipse.gemoc.event.commons.model.property.BinaryProperty;
import org.eclipse.gemoc.event.commons.model.property.BooleanAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.BooleanOperator;
import org.eclipse.gemoc.event.commons.model.property.ComparisonOperator;
import org.eclipse.gemoc.event.commons.model.property.CompositeProperty;
import org.eclipse.gemoc.event.commons.model.property.ContainerReferenceProperty;
import org.eclipse.gemoc.event.commons.model.property.EventPrecondition;
import org.eclipse.gemoc.event.commons.model.property.IntegerAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.ManyBooleanAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.ManyIntegerAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.ManyReferenceProperty;
import org.eclipse.gemoc.event.commons.model.property.ManyStringAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.NegationTemporalProperty;
import org.eclipse.gemoc.event.commons.model.property.NextProperty;
import org.eclipse.gemoc.event.commons.model.property.Property;
import org.eclipse.gemoc.event.commons.model.property.PropertyFactory;
import org.eclipse.gemoc.event.commons.model.property.PropertyPackage;
import org.eclipse.gemoc.event.commons.model.property.PropertyReference;
import org.eclipse.gemoc.event.commons.model.property.Quantifier;
import org.eclipse.gemoc.event.commons.model.property.ReleaseProperty;
import org.eclipse.gemoc.event.commons.model.property.SingleReferenceProperty;
import org.eclipse.gemoc.event.commons.model.property.StateProperty;
import org.eclipse.gemoc.event.commons.model.property.StepProperty;
import org.eclipse.gemoc.event.commons.model.property.Stepping;
import org.eclipse.gemoc.event.commons.model.property.StringAttributeProperty;
import org.eclipse.gemoc.event.commons.model.property.TemporalProperty;
import org.eclipse.gemoc.event.commons.model.property.UnaryOperator;
import org.eclipse.gemoc.event.commons.model.property.UnaryProperty;
import org.eclipse.gemoc.event.commons.model.property.UntilProperty;

import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;

import org.eclipse.gemoc.event.commons.model.scenario.impl.ScenarioPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyPackageImpl extends EPackageImpl implements PropertyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass temporalPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nextPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass untilPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass releasePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass negationTemporalPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventPreconditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyReferencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleReferencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerReferencePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyBooleanAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyIntegerAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyStringAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringAttributePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum comparisonOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum booleanOperatorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum quantifierEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum steppingEEnum = null;

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
	 * @see org.eclipse.gemoc.event.commons.model.property.PropertyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PropertyPackageImpl() {
		super(eNS_URI, PropertyFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PropertyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PropertyPackage init() {
		if (isInited) return (PropertyPackage)EPackage.Registry.INSTANCE.getEPackage(PropertyPackage.eNS_URI);

		// Obtain or create and register package
		PropertyPackageImpl thePropertyPackage = (PropertyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PropertyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PropertyPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

		// Create package meta-data objects
		thePropertyPackage.createPackageContents();
		theScenarioPackage.createPackageContents();

		// Initialize created meta-data
		thePropertyPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePropertyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PropertyPackage.eNS_URI, thePropertyPackage);
		return thePropertyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractProperty() {
		return abstractPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemporalProperty() {
		return temporalPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNextProperty() {
		return nextPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNextProperty_Formula() {
		return (EReference)nextPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUntilProperty() {
		return untilPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUntilProperty_LeftFormula() {
		return (EReference)untilPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUntilProperty_RightFormula() {
		return (EReference)untilPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReleaseProperty() {
		return releasePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReleaseProperty_LeftFormula() {
		return (EReference)releasePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReleaseProperty_RightFormula() {
		return (EReference)releasePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNegationTemporalProperty() {
		return negationTemporalPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNegationTemporalProperty_Formula() {
		return (EReference)negationTemporalPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyReference() {
		return propertyReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyReference_ReferencedProperty() {
		return (EReference)propertyReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeProperty() {
		return compositePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeProperty_Properties() {
		return (EReference)compositePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventPrecondition() {
		return eventPreconditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventPrecondition_Event() {
		return (EReference)eventPreconditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStepProperty() {
		return stepPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStepProperty_Stepping() {
		return (EAttribute)stepPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStepProperty_TargetProvider() {
		return (EReference)stepPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStepProperty__GetOperation() {
		return stepPropertyEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateProperty() {
		return statePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateProperty_Target() {
		return (EReference)statePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStateProperty__GetFeature() {
		return statePropertyEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryProperty() {
		return unaryPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryProperty_Operator() {
		return (EAttribute)unaryPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryProperty_Property() {
		return (EReference)unaryPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryProperty() {
		return binaryPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinaryProperty_Operator() {
		return (EAttribute)binaryPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryProperty_Left() {
		return (EReference)binaryPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryProperty_Right() {
		return (EReference)binaryPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManyReferenceProperty() {
		return manyReferencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManyReferenceProperty_Property() {
		return (EReference)manyReferencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyReferenceProperty_Quantifier() {
		return (EAttribute)manyReferencePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleReferenceProperty() {
		return singleReferencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleReferenceProperty_Property() {
		return (EReference)singleReferencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainerReferenceProperty() {
		return containerReferencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerReferenceProperty_Property() {
		return (EReference)containerReferencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManyBooleanAttributeProperty() {
		return manyBooleanAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyBooleanAttributeProperty_Quantifier() {
		return (EAttribute)manyBooleanAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyBooleanAttributeProperty_Value() {
		return (EAttribute)manyBooleanAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyBooleanAttributeProperty_Operator() {
		return (EAttribute)manyBooleanAttributePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManyIntegerAttributeProperty() {
		return manyIntegerAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyIntegerAttributeProperty_Quantifier() {
		return (EAttribute)manyIntegerAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyIntegerAttributeProperty_Value() {
		return (EAttribute)manyIntegerAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyIntegerAttributeProperty_Operator() {
		return (EAttribute)manyIntegerAttributePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManyStringAttributeProperty() {
		return manyStringAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyStringAttributeProperty_Quantifier() {
		return (EAttribute)manyStringAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyStringAttributeProperty_Value() {
		return (EAttribute)manyStringAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManyStringAttributeProperty_Operator() {
		return (EAttribute)manyStringAttributePropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanAttributeProperty() {
		return booleanAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanAttributeProperty_Value() {
		return (EAttribute)booleanAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanAttributeProperty_Operator() {
		return (EAttribute)booleanAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerAttributeProperty() {
		return integerAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerAttributeProperty_Value() {
		return (EAttribute)integerAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerAttributeProperty_Operator() {
		return (EAttribute)integerAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringAttributeProperty() {
		return stringAttributePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringAttributeProperty_Value() {
		return (EAttribute)stringAttributePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringAttributeProperty_Operator() {
		return (EAttribute)stringAttributePropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryOperator() {
		return unaryOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getComparisonOperator() {
		return comparisonOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBooleanOperator() {
		return booleanOperatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getQuantifier() {
		return quantifierEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getStepping() {
		return steppingEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyFactory getPropertyFactory() {
		return (PropertyFactory)getEFactoryInstance();
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
		abstractPropertyEClass = createEClass(ABSTRACT_PROPERTY);

		temporalPropertyEClass = createEClass(TEMPORAL_PROPERTY);

		nextPropertyEClass = createEClass(NEXT_PROPERTY);
		createEReference(nextPropertyEClass, NEXT_PROPERTY__FORMULA);

		untilPropertyEClass = createEClass(UNTIL_PROPERTY);
		createEReference(untilPropertyEClass, UNTIL_PROPERTY__LEFT_FORMULA);
		createEReference(untilPropertyEClass, UNTIL_PROPERTY__RIGHT_FORMULA);

		releasePropertyEClass = createEClass(RELEASE_PROPERTY);
		createEReference(releasePropertyEClass, RELEASE_PROPERTY__LEFT_FORMULA);
		createEReference(releasePropertyEClass, RELEASE_PROPERTY__RIGHT_FORMULA);

		negationTemporalPropertyEClass = createEClass(NEGATION_TEMPORAL_PROPERTY);
		createEReference(negationTemporalPropertyEClass, NEGATION_TEMPORAL_PROPERTY__FORMULA);

		propertyEClass = createEClass(PROPERTY);

		propertyReferenceEClass = createEClass(PROPERTY_REFERENCE);
		createEReference(propertyReferenceEClass, PROPERTY_REFERENCE__REFERENCED_PROPERTY);

		compositePropertyEClass = createEClass(COMPOSITE_PROPERTY);
		createEReference(compositePropertyEClass, COMPOSITE_PROPERTY__PROPERTIES);

		eventPreconditionEClass = createEClass(EVENT_PRECONDITION);
		createEReference(eventPreconditionEClass, EVENT_PRECONDITION__EVENT);

		stepPropertyEClass = createEClass(STEP_PROPERTY);
		createEAttribute(stepPropertyEClass, STEP_PROPERTY__STEPPING);
		createEReference(stepPropertyEClass, STEP_PROPERTY__TARGET_PROVIDER);
		createEOperation(stepPropertyEClass, STEP_PROPERTY___GET_OPERATION);

		statePropertyEClass = createEClass(STATE_PROPERTY);
		createEReference(statePropertyEClass, STATE_PROPERTY__TARGET);
		createEOperation(statePropertyEClass, STATE_PROPERTY___GET_FEATURE);

		unaryPropertyEClass = createEClass(UNARY_PROPERTY);
		createEAttribute(unaryPropertyEClass, UNARY_PROPERTY__OPERATOR);
		createEReference(unaryPropertyEClass, UNARY_PROPERTY__PROPERTY);

		binaryPropertyEClass = createEClass(BINARY_PROPERTY);
		createEAttribute(binaryPropertyEClass, BINARY_PROPERTY__OPERATOR);
		createEReference(binaryPropertyEClass, BINARY_PROPERTY__LEFT);
		createEReference(binaryPropertyEClass, BINARY_PROPERTY__RIGHT);

		manyReferencePropertyEClass = createEClass(MANY_REFERENCE_PROPERTY);
		createEReference(manyReferencePropertyEClass, MANY_REFERENCE_PROPERTY__PROPERTY);
		createEAttribute(manyReferencePropertyEClass, MANY_REFERENCE_PROPERTY__QUANTIFIER);

		singleReferencePropertyEClass = createEClass(SINGLE_REFERENCE_PROPERTY);
		createEReference(singleReferencePropertyEClass, SINGLE_REFERENCE_PROPERTY__PROPERTY);

		containerReferencePropertyEClass = createEClass(CONTAINER_REFERENCE_PROPERTY);
		createEReference(containerReferencePropertyEClass, CONTAINER_REFERENCE_PROPERTY__PROPERTY);

		manyBooleanAttributePropertyEClass = createEClass(MANY_BOOLEAN_ATTRIBUTE_PROPERTY);
		createEAttribute(manyBooleanAttributePropertyEClass, MANY_BOOLEAN_ATTRIBUTE_PROPERTY__QUANTIFIER);
		createEAttribute(manyBooleanAttributePropertyEClass, MANY_BOOLEAN_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(manyBooleanAttributePropertyEClass, MANY_BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR);

		manyIntegerAttributePropertyEClass = createEClass(MANY_INTEGER_ATTRIBUTE_PROPERTY);
		createEAttribute(manyIntegerAttributePropertyEClass, MANY_INTEGER_ATTRIBUTE_PROPERTY__QUANTIFIER);
		createEAttribute(manyIntegerAttributePropertyEClass, MANY_INTEGER_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(manyIntegerAttributePropertyEClass, MANY_INTEGER_ATTRIBUTE_PROPERTY__OPERATOR);

		manyStringAttributePropertyEClass = createEClass(MANY_STRING_ATTRIBUTE_PROPERTY);
		createEAttribute(manyStringAttributePropertyEClass, MANY_STRING_ATTRIBUTE_PROPERTY__QUANTIFIER);
		createEAttribute(manyStringAttributePropertyEClass, MANY_STRING_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(manyStringAttributePropertyEClass, MANY_STRING_ATTRIBUTE_PROPERTY__OPERATOR);

		booleanAttributePropertyEClass = createEClass(BOOLEAN_ATTRIBUTE_PROPERTY);
		createEAttribute(booleanAttributePropertyEClass, BOOLEAN_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(booleanAttributePropertyEClass, BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR);

		integerAttributePropertyEClass = createEClass(INTEGER_ATTRIBUTE_PROPERTY);
		createEAttribute(integerAttributePropertyEClass, INTEGER_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(integerAttributePropertyEClass, INTEGER_ATTRIBUTE_PROPERTY__OPERATOR);

		stringAttributePropertyEClass = createEClass(STRING_ATTRIBUTE_PROPERTY);
		createEAttribute(stringAttributePropertyEClass, STRING_ATTRIBUTE_PROPERTY__VALUE);
		createEAttribute(stringAttributePropertyEClass, STRING_ATTRIBUTE_PROPERTY__OPERATOR);

		// Create enums
		unaryOperatorEEnum = createEEnum(UNARY_OPERATOR);
		comparisonOperatorEEnum = createEEnum(COMPARISON_OPERATOR);
		booleanOperatorEEnum = createEEnum(BOOLEAN_OPERATOR);
		quantifierEEnum = createEEnum(QUANTIFIER);
		steppingEEnum = createEEnum(STEPPING);
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
		ScenarioPackage theScenarioPackage = (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters
		ETypeParameter nextPropertyEClass_P = addETypeParameter(nextPropertyEClass, "P");
		ETypeParameter untilPropertyEClass_P = addETypeParameter(untilPropertyEClass, "P");
		ETypeParameter releasePropertyEClass_P = addETypeParameter(releasePropertyEClass, "P");
		ETypeParameter negationTemporalPropertyEClass_P = addETypeParameter(negationTemporalPropertyEClass, "P");
		ETypeParameter propertyReferenceEClass_P = addETypeParameter(propertyReferenceEClass, "P");
		ETypeParameter compositePropertyEClass_P = addETypeParameter(compositePropertyEClass, "P");
		ETypeParameter eventPreconditionEClass_E = addETypeParameter(eventPreconditionEClass, "E");
		ETypeParameter stepPropertyEClass_T = addETypeParameter(stepPropertyEClass, "T");
		ETypeParameter statePropertyEClass_T = addETypeParameter(statePropertyEClass, "T");
		ETypeParameter unaryPropertyEClass_P = addETypeParameter(unaryPropertyEClass, "P");
		ETypeParameter unaryPropertyEClass_T = addETypeParameter(unaryPropertyEClass, "T");
		ETypeParameter binaryPropertyEClass_P = addETypeParameter(binaryPropertyEClass, "P");
		ETypeParameter binaryPropertyEClass_T = addETypeParameter(binaryPropertyEClass, "T");
		ETypeParameter manyReferencePropertyEClass_P = addETypeParameter(manyReferencePropertyEClass, "P");
		ETypeParameter manyReferencePropertyEClass_T = addETypeParameter(manyReferencePropertyEClass, "T");
		ETypeParameter singleReferencePropertyEClass_P = addETypeParameter(singleReferencePropertyEClass, "P");
		ETypeParameter singleReferencePropertyEClass_T = addETypeParameter(singleReferencePropertyEClass, "T");
		ETypeParameter containerReferencePropertyEClass_P = addETypeParameter(containerReferencePropertyEClass, "P");
		ETypeParameter containerReferencePropertyEClass_T = addETypeParameter(containerReferencePropertyEClass, "T");
		ETypeParameter manyBooleanAttributePropertyEClass_T = addETypeParameter(manyBooleanAttributePropertyEClass, "T");
		ETypeParameter manyIntegerAttributePropertyEClass_T = addETypeParameter(manyIntegerAttributePropertyEClass, "T");
		ETypeParameter manyStringAttributePropertyEClass_T = addETypeParameter(manyStringAttributePropertyEClass, "T");
		ETypeParameter booleanAttributePropertyEClass_T = addETypeParameter(booleanAttributePropertyEClass, "T");
		ETypeParameter integerAttributePropertyEClass_T = addETypeParameter(integerAttributePropertyEClass, "T");
		ETypeParameter stringAttributePropertyEClass_T = addETypeParameter(stringAttributePropertyEClass, "T");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getAbstractProperty());
		nextPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getAbstractProperty());
		untilPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getAbstractProperty());
		releasePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getTemporalProperty());
		negationTemporalPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getProperty());
		propertyReferenceEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getProperty());
		compositePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(theScenarioPackage.getEvent());
		eventPreconditionEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		unaryPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		binaryPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		manyReferencePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		singleReferencePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		containerReferencePropertyEClass_P.getEBounds().add(g1);

		// Add supertypes to classes
		temporalPropertyEClass.getESuperTypes().add(this.getAbstractProperty());
		nextPropertyEClass.getESuperTypes().add(this.getTemporalProperty());
		untilPropertyEClass.getESuperTypes().add(this.getTemporalProperty());
		releasePropertyEClass.getESuperTypes().add(this.getTemporalProperty());
		negationTemporalPropertyEClass.getESuperTypes().add(this.getTemporalProperty());
		propertyEClass.getESuperTypes().add(this.getAbstractProperty());
		propertyReferenceEClass.getESuperTypes().add(this.getProperty());
		compositePropertyEClass.getESuperTypes().add(this.getProperty());
		eventPreconditionEClass.getESuperTypes().add(this.getProperty());
		stepPropertyEClass.getESuperTypes().add(this.getProperty());
		statePropertyEClass.getESuperTypes().add(this.getProperty());
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(unaryPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		unaryPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(binaryPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		binaryPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(manyReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(singleReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		singleReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(containerReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		containerReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(manyBooleanAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyBooleanAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(manyIntegerAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyIntegerAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(manyStringAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyStringAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(booleanAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		booleanAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(integerAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		integerAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getStateProperty());
		g2 = createEGenericType(stringAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		stringAttributePropertyEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(abstractPropertyEClass, AbstractProperty.class, "AbstractProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(temporalPropertyEClass, TemporalProperty.class, "TemporalProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nextPropertyEClass, NextProperty.class, "NextProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(nextPropertyEClass_P);
		initEReference(getNextProperty_Formula(), g1, null, "formula", null, 0, 1, NextProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(untilPropertyEClass, UntilProperty.class, "UntilProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(untilPropertyEClass_P);
		initEReference(getUntilProperty_LeftFormula(), g1, null, "leftFormula", null, 0, 1, UntilProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(untilPropertyEClass_P);
		initEReference(getUntilProperty_RightFormula(), g1, null, "rightFormula", null, 0, 1, UntilProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(releasePropertyEClass, ReleaseProperty.class, "ReleaseProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(releasePropertyEClass_P);
		initEReference(getReleaseProperty_LeftFormula(), g1, null, "leftFormula", null, 0, 1, ReleaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(releasePropertyEClass_P);
		initEReference(getReleaseProperty_RightFormula(), g1, null, "rightFormula", null, 0, 1, ReleaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(negationTemporalPropertyEClass, NegationTemporalProperty.class, "NegationTemporalProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(negationTemporalPropertyEClass_P);
		initEReference(getNegationTemporalProperty_Formula(), g1, null, "formula", null, 0, 1, NegationTemporalProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyReferenceEClass, PropertyReference.class, "PropertyReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(propertyReferenceEClass_P);
		initEReference(getPropertyReference_ReferencedProperty(), g1, null, "referencedProperty", null, 0, 1, PropertyReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositePropertyEClass, CompositeProperty.class, "CompositeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(compositePropertyEClass_P);
		initEReference(getCompositeProperty_Properties(), g1, null, "properties", null, 0, -1, CompositeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventPreconditionEClass, EventPrecondition.class, "EventPrecondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(eventPreconditionEClass_E);
		initEReference(getEventPrecondition_Event(), g1, null, "event", null, 0, 1, EventPrecondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stepPropertyEClass, StepProperty.class, "StepProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStepProperty_Stepping(), this.getStepping(), "stepping", null, 0, 1, StepProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(theScenarioPackage.getElementProvider());
		g2 = createEGenericType(stepPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		initEReference(getStepProperty_TargetProvider(), g1, null, "targetProvider", null, 0, 1, StepProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getStepProperty__GetOperation(), theEcorePackage.getEOperation(), "getOperation", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(statePropertyEClass, StateProperty.class, "StateProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(statePropertyEClass_T);
		initEReference(getStateProperty_Target(), g1, null, "target", null, 0, 1, StateProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getStateProperty__GetFeature(), theEcorePackage.getEStructuralFeature(), "getFeature", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(unaryPropertyEClass, UnaryProperty.class, "UnaryProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnaryProperty_Operator(), this.getUnaryOperator(), "operator", null, 1, 1, UnaryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(unaryPropertyEClass_P);
		initEReference(getUnaryProperty_Property(), g1, null, "property", null, 1, 1, UnaryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryPropertyEClass, BinaryProperty.class, "BinaryProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBinaryProperty_Operator(), this.getBooleanOperator(), "operator", null, 1, 1, BinaryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(binaryPropertyEClass_P);
		initEReference(getBinaryProperty_Left(), g1, null, "left", null, 1, 1, BinaryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(binaryPropertyEClass_P);
		initEReference(getBinaryProperty_Right(), g1, null, "right", null, 1, 1, BinaryProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyReferencePropertyEClass, ManyReferenceProperty.class, "ManyReferenceProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(manyReferencePropertyEClass_P);
		initEReference(getManyReferenceProperty_Property(), g1, null, "property", null, 1, 1, ManyReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyReferenceProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleReferencePropertyEClass, SingleReferenceProperty.class, "SingleReferenceProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(singleReferencePropertyEClass_P);
		initEReference(getSingleReferenceProperty_Property(), g1, null, "property", null, 0, 1, SingleReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerReferencePropertyEClass, ContainerReferenceProperty.class, "ContainerReferenceProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(containerReferencePropertyEClass_P);
		initEReference(getContainerReferenceProperty_Property(), g1, null, "property", null, 0, 1, ContainerReferenceProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyBooleanAttributePropertyEClass, ManyBooleanAttributeProperty.class, "ManyBooleanAttributeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyBooleanAttributeProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyBooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyBooleanAttributeProperty_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, ManyBooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyBooleanAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, ManyBooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyIntegerAttributePropertyEClass, ManyIntegerAttributeProperty.class, "ManyIntegerAttributeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyIntegerAttributeProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyIntegerAttributeProperty_Value(), theEcorePackage.getEInt(), "value", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyIntegerAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyStringAttributePropertyEClass, ManyStringAttributeProperty.class, "ManyStringAttributeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyStringAttributeProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyStringAttributeProperty_Value(), theEcorePackage.getEString(), "value", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyStringAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanAttributePropertyEClass, BooleanAttributeProperty.class, "BooleanAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanAttributeProperty_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, BooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBooleanAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, BooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerAttributePropertyEClass, IntegerAttributeProperty.class, "IntegerAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerAttributeProperty_Value(), theEcorePackage.getEInt(), "value", null, 0, 1, IntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntegerAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, IntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringAttributePropertyEClass, StringAttributeProperty.class, "StringAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringAttributeProperty_Value(), theEcorePackage.getEString(), "value", null, 0, 1, StringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringAttributeProperty_Operator(), this.getComparisonOperator(), "operator", null, 0, 1, StringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(unaryOperatorEEnum, UnaryOperator.class, "UnaryOperator");
		addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.NOT);

		initEEnum(comparisonOperatorEEnum, ComparisonOperator.class, "ComparisonOperator");
		addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.EQUAL);

		initEEnum(booleanOperatorEEnum, BooleanOperator.class, "BooleanOperator");
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.AND);
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.OR);
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.IMPLIES);

		initEEnum(quantifierEEnum, Quantifier.class, "Quantifier");
		addEEnumLiteral(quantifierEEnum, Quantifier.EXISTS);
		addEEnumLiteral(quantifierEEnum, Quantifier.FORALL);

		initEEnum(steppingEEnum, Stepping.class, "Stepping");
		addEEnumLiteral(steppingEEnum, Stepping.ONGOING);
		addEEnumLiteral(steppingEEnum, Stepping.ENDED);
		addEEnumLiteral(steppingEEnum, Stepping.ENDING);

		// Create resource
		createResource(eNS_URI);
	}

} //PropertyPackageImpl
