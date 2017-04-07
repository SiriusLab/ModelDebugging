/**
 */
package fr.inria.diverse.event.commons.model.property.impl;

import fr.inria.diverse.event.commons.model.property.BinaryProperty;
import fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty;
import fr.inria.diverse.event.commons.model.property.BooleanOperator;
import fr.inria.diverse.event.commons.model.property.ClassProperty;
import fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty;
import fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty;
import fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty;
import fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty;
import fr.inria.diverse.event.commons.model.property.ManyReferenceProperty;
import fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty;
import fr.inria.diverse.event.commons.model.property.Operator;
import fr.inria.diverse.event.commons.model.property.PropertyFactory;
import fr.inria.diverse.event.commons.model.property.PropertyPackage;
import fr.inria.diverse.event.commons.model.property.Quantifier;
import fr.inria.diverse.event.commons.model.property.SingleReferenceProperty;
import fr.inria.diverse.event.commons.model.property.StringAttributeProperty;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

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
	private EClass classPropertyEClass = null;

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
	private EEnum operatorEEnum = null;

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
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#eNS_URI
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

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePropertyPackage.createPackageContents();

		// Initialize created meta-data
		thePropertyPackage.initializePackageContents();

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
	public EClass getClassProperty() {
		return classPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassProperty_Target() {
		return (EReference)classPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassProperty_Feature() {
		return (EReference)classPropertyEClass.getEStructuralFeatures().get(1);
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
	public EEnum getOperator() {
		return operatorEEnum;
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
		classPropertyEClass = createEClass(CLASS_PROPERTY);
		createEReference(classPropertyEClass, CLASS_PROPERTY__TARGET);
		createEReference(classPropertyEClass, CLASS_PROPERTY__FEATURE);

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
		operatorEEnum = createEEnum(OPERATOR);
		booleanOperatorEEnum = createEEnum(BOOLEAN_OPERATOR);
		quantifierEEnum = createEEnum(QUANTIFIER);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters
		ETypeParameter classPropertyEClass_T = addETypeParameter(classPropertyEClass, "T");
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
		EGenericType g1 = createEGenericType(this.getClassProperty());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		binaryPropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		manyReferencePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		singleReferencePropertyEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		containerReferencePropertyEClass_P.getEBounds().add(g1);

		// Add supertypes to classes
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(binaryPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		binaryPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(manyReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(singleReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		singleReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(containerReferencePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		containerReferencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(manyBooleanAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyBooleanAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(manyIntegerAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyIntegerAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(manyStringAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		manyStringAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(booleanAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		booleanAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(integerAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		integerAttributePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getClassProperty());
		g2 = createEGenericType(stringAttributePropertyEClass_T);
		g1.getETypeArguments().add(g2);
		stringAttributePropertyEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(classPropertyEClass, ClassProperty.class, "ClassProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(classPropertyEClass_T);
		initEReference(getClassProperty_Target(), g1, null, "target", null, 0, 1, ClassProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassProperty_Feature(), theEcorePackage.getEStructuralFeature(), null, "feature", null, 0, 1, ClassProperty.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

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
		initEAttribute(getManyBooleanAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, ManyBooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyIntegerAttributePropertyEClass, ManyIntegerAttributeProperty.class, "ManyIntegerAttributeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyIntegerAttributeProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyIntegerAttributeProperty_Value(), theEcorePackage.getEInt(), "value", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyIntegerAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, ManyIntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyStringAttributePropertyEClass, ManyStringAttributeProperty.class, "ManyStringAttributeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyStringAttributeProperty_Quantifier(), this.getQuantifier(), "quantifier", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyStringAttributeProperty_Value(), theEcorePackage.getEString(), "value", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getManyStringAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, ManyStringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanAttributePropertyEClass, BooleanAttributeProperty.class, "BooleanAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanAttributeProperty_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, BooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBooleanAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, BooleanAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerAttributePropertyEClass, IntegerAttributeProperty.class, "IntegerAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerAttributeProperty_Value(), theEcorePackage.getEInt(), "value", null, 0, 1, IntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntegerAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, IntegerAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringAttributePropertyEClass, StringAttributeProperty.class, "StringAttributeProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringAttributeProperty_Value(), theEcorePackage.getEString(), "value", null, 0, 1, StringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringAttributeProperty_Operator(), this.getOperator(), "operator", null, 0, 1, StringAttributeProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(operatorEEnum, Operator.class, "Operator");
		addEEnumLiteral(operatorEEnum, Operator.EQUAL);

		initEEnum(booleanOperatorEEnum, BooleanOperator.class, "BooleanOperator");
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.AND);
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.OR);
		addEEnumLiteral(booleanOperatorEEnum, BooleanOperator.IMPLIES);

		initEEnum(quantifierEEnum, Quantifier.class, "Quantifier");
		addEEnumLiteral(quantifierEEnum, Quantifier.EXISTS);
		addEEnumLiteral(quantifierEEnum, Quantifier.FORALL);

		// Create resource
		createResource(eNS_URI);
	}

} //PropertyPackageImpl
