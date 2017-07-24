/**
 */
package fr.inria.diverse.event.commons.model.property;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
 * @see fr.inria.diverse.event.commons.model.property.PropertyFactory
 * @model kind="package"
 * @generated
 */
public interface PropertyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "property";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/property";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "property";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PropertyPackage eINSTANCE = fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.AbstractPropertyImpl <em>Abstract Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.AbstractPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getAbstractProperty()
	 * @generated
	 */
	int ABSTRACT_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Abstract Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PROPERTY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Abstract Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.TemporalPropertyImpl <em>Temporal Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.TemporalPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getTemporalProperty()
	 * @generated
	 */
	int TEMPORAL_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Temporal Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL_PROPERTY_FEATURE_COUNT = ABSTRACT_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Temporal Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL_PROPERTY_OPERATION_COUNT = ABSTRACT_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.NextPropertyImpl <em>Next Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.NextPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getNextProperty()
	 * @generated
	 */
	int NEXT_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEXT_PROPERTY__FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Next Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEXT_PROPERTY_FEATURE_COUNT = TEMPORAL_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Next Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEXT_PROPERTY_OPERATION_COUNT = TEMPORAL_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.UntilPropertyImpl <em>Until Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.UntilPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUntilProperty()
	 * @generated
	 */
	int UNTIL_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Left Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_PROPERTY__LEFT_FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_PROPERTY__RIGHT_FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Until Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_PROPERTY_FEATURE_COUNT = TEMPORAL_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Until Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_PROPERTY_OPERATION_COUNT = TEMPORAL_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl <em>Release Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getReleaseProperty()
	 * @generated
	 */
	int RELEASE_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Left Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_PROPERTY__LEFT_FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_PROPERTY__RIGHT_FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Release Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_PROPERTY_FEATURE_COUNT = TEMPORAL_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Release Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_PROPERTY_OPERATION_COUNT = TEMPORAL_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.NegationTemporalPropertyImpl <em>Negation Temporal Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.NegationTemporalPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getNegationTemporalProperty()
	 * @generated
	 */
	int NEGATION_TEMPORAL_PROPERTY = 5;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_TEMPORAL_PROPERTY__FORMULA = TEMPORAL_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Negation Temporal Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_TEMPORAL_PROPERTY_FEATURE_COUNT = TEMPORAL_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Negation Temporal Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_TEMPORAL_PROPERTY_OPERATION_COUNT = TEMPORAL_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 6;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = ABSTRACT_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = ABSTRACT_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.CompositePropertyImpl <em>Composite Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.CompositePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getCompositeProperty()
	 * @generated
	 */
	int COMPOSITE_PROPERTY = 8;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.EventPreconditionImpl <em>Event Precondition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.EventPreconditionImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getEventPrecondition()
	 * @generated
	 */
	int EVENT_PRECONDITION = 9;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.StatePropertyImpl <em>State Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.StatePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStateProperty()
	 * @generated
	 */
	int STATE_PROPERTY = 11;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.BinaryPropertyImpl <em>Binary Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.BinaryPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBinaryProperty()
	 * @generated
	 */
	int BINARY_PROPERTY = 13;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyReferencePropertyImpl <em>Many Reference Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ManyReferencePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyReferenceProperty()
	 * @generated
	 */
	int MANY_REFERENCE_PROPERTY = 14;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.SingleReferencePropertyImpl <em>Single Reference Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.SingleReferencePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getSingleReferenceProperty()
	 * @generated
	 */
	int SINGLE_REFERENCE_PROPERTY = 15;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ContainerReferencePropertyImpl <em>Container Reference Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ContainerReferencePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getContainerReferenceProperty()
	 * @generated
	 */
	int CONTAINER_REFERENCE_PROPERTY = 16;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyBooleanAttributePropertyImpl <em>Many Boolean Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ManyBooleanAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyBooleanAttributeProperty()
	 * @generated
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY = 17;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyIntegerAttributePropertyImpl <em>Many Integer Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ManyIntegerAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyIntegerAttributeProperty()
	 * @generated
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY = 18;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyStringAttributePropertyImpl <em>Many String Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.ManyStringAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyStringAttributeProperty()
	 * @generated
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY = 19;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.BooleanAttributePropertyImpl <em>Boolean Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.BooleanAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBooleanAttributeProperty()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY = 20;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.IntegerAttributePropertyImpl <em>Integer Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.IntegerAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getIntegerAttributeProperty()
	 * @generated
	 */
	int INTEGER_ATTRIBUTE_PROPERTY = 21;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.StringAttributePropertyImpl <em>String Attribute Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.StringAttributePropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStringAttributeProperty()
	 * @generated
	 */
	int STRING_ATTRIBUTE_PROPERTY = 22;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.StepPropertyImpl <em>Step Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.StepPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStepProperty()
	 * @generated
	 */
	int STEP_PROPERTY = 10;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.PropertyReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyReferenceImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getPropertyReference()
	 * @generated
	 */
	int PROPERTY_REFERENCE = 7;

	/**
	 * The feature id for the '<em><b>Referenced Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFERENCE__REFERENCED_PROPERTY = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFERENCE_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REFERENCE_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PROPERTY__PROPERTIES = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Composite Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_PRECONDITION__EVENT = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event Precondition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_PRECONDITION_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Event Precondition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_PRECONDITION_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stepping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PROPERTY__STEPPING = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Provider</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PROPERTY__TARGET_PROVIDER = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Step Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Operation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PROPERTY___GET_OPERATION = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Step Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PROPERTY__TARGET = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>State Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PROPERTY___GET_FEATURE = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>State Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.impl.UnaryPropertyImpl <em>Unary Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.impl.UnaryPropertyImpl
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUnaryProperty()
	 * @generated
	 */
	int UNARY_PROPERTY = 12;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY__PROPERTY = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Unary Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY__LEFT = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY__RIGHT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Binary Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY__PROPERTY = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Quantifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY__QUANTIFIER = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Many Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Many Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_PROPERTY__PROPERTY = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Single Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_PROPERTY__PROPERTY = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Container Reference Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Quantifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY__QUANTIFIER = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Many Boolean Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Many Boolean Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Quantifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY__QUANTIFIER = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Many Integer Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Many Integer Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Quantifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY__QUANTIFIER = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Many String Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Many String Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Boolean Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Boolean Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Integer Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>Integer Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY__TARGET = STATE_PROPERTY__TARGET;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY__VALUE = STATE_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY__OPERATOR = STATE_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY_FEATURE_COUNT = STATE_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Feature</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY___GET_FEATURE = STATE_PROPERTY___GET_FEATURE;

	/**
	 * The number of operations of the '<em>String Attribute Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_PROPERTY_OPERATION_COUNT = STATE_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.UnaryOperator <em>Unary Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.UnaryOperator
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUnaryOperator()
	 * @generated
	 */
	int UNARY_OPERATOR = 23;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.ComparisonOperator <em>Comparison Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.ComparisonOperator
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getComparisonOperator()
	 * @generated
	 */
	int COMPARISON_OPERATOR = 24;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.BooleanOperator <em>Boolean Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.BooleanOperator
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBooleanOperator()
	 * @generated
	 */
	int BOOLEAN_OPERATOR = 25;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.Quantifier <em>Quantifier</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.Quantifier
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getQuantifier()
	 * @generated
	 */
	int QUANTIFIER = 26;


	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.property.Stepping <em>Stepping</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.property.Stepping
	 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStepping()
	 * @generated
	 */
	int STEPPING = 27;


	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.AbstractProperty <em>Abstract Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.AbstractProperty
	 * @generated
	 */
	EClass getAbstractProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.TemporalProperty <em>Temporal Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporal Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.TemporalProperty
	 * @generated
	 */
	EClass getTemporalProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.NextProperty <em>Next Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Next Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.NextProperty
	 * @generated
	 */
	EClass getNextProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.NextProperty#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.NextProperty#getFormula()
	 * @see #getNextProperty()
	 * @generated
	 */
	EReference getNextProperty_Formula();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.UntilProperty <em>Until Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Until Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UntilProperty
	 * @generated
	 */
	EClass getUntilProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.UntilProperty#getLeftFormula <em>Left Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UntilProperty#getLeftFormula()
	 * @see #getUntilProperty()
	 * @generated
	 */
	EReference getUntilProperty_LeftFormula();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.UntilProperty#getRightFormula <em>Right Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UntilProperty#getRightFormula()
	 * @see #getUntilProperty()
	 * @generated
	 */
	EReference getUntilProperty_RightFormula();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty <em>Release Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Release Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ReleaseProperty
	 * @generated
	 */
	EClass getReleaseProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getLeftFormula <em>Left Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ReleaseProperty#getLeftFormula()
	 * @see #getReleaseProperty()
	 * @generated
	 */
	EReference getReleaseProperty_LeftFormula();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty#getRightFormula <em>Right Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ReleaseProperty#getRightFormula()
	 * @see #getReleaseProperty()
	 * @generated
	 */
	EReference getReleaseProperty_RightFormula();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.NegationTemporalProperty <em>Negation Temporal Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Negation Temporal Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.NegationTemporalProperty
	 * @generated
	 */
	EClass getNegationTemporalProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.NegationTemporalProperty#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formula</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.NegationTemporalProperty#getFormula()
	 * @see #getNegationTemporalProperty()
	 * @generated
	 */
	EReference getNegationTemporalProperty_Formula();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.StateProperty <em>State Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StateProperty
	 * @generated
	 */
	EClass getStateProperty();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.property.StateProperty#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StateProperty#getTarget()
	 * @see #getStateProperty()
	 * @generated
	 */
	EReference getStateProperty_Target();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.event.commons.model.property.StateProperty#getFeature() <em>Get Feature</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Feature</em>' operation.
	 * @see fr.inria.diverse.event.commons.model.property.StateProperty#getFeature()
	 * @generated
	 */
	EOperation getStateProperty__GetFeature();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.UnaryProperty <em>Unary Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UnaryProperty
	 * @generated
	 */
	EClass getUnaryProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.UnaryProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UnaryProperty#getOperator()
	 * @see #getUnaryProperty()
	 * @generated
	 */
	EAttribute getUnaryProperty_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.UnaryProperty#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UnaryProperty#getProperty()
	 * @see #getUnaryProperty()
	 * @generated
	 */
	EReference getUnaryProperty_Property();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty <em>Binary Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BinaryProperty
	 * @generated
	 */
	EClass getBinaryProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BinaryProperty#getOperator()
	 * @see #getBinaryProperty()
	 * @generated
	 */
	EAttribute getBinaryProperty_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BinaryProperty#getLeft()
	 * @see #getBinaryProperty()
	 * @generated
	 */
	EReference getBinaryProperty_Left();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BinaryProperty#getRight()
	 * @see #getBinaryProperty()
	 * @generated
	 */
	EReference getBinaryProperty_Right();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ManyReferenceProperty <em>Many Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Reference Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyReferenceProperty
	 * @generated
	 */
	EClass getManyReferenceProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.ManyReferenceProperty#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyReferenceProperty#getProperty()
	 * @see #getManyReferenceProperty()
	 * @generated
	 */
	EReference getManyReferenceProperty_Property();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyReferenceProperty#getQuantifier <em>Quantifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantifier</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyReferenceProperty#getQuantifier()
	 * @see #getManyReferenceProperty()
	 * @generated
	 */
	EAttribute getManyReferenceProperty_Quantifier();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.SingleReferenceProperty <em>Single Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Reference Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.SingleReferenceProperty
	 * @generated
	 */
	EClass getSingleReferenceProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.SingleReferenceProperty#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.SingleReferenceProperty#getProperty()
	 * @see #getSingleReferenceProperty()
	 * @generated
	 */
	EReference getSingleReferenceProperty_Property();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty <em>Container Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Reference Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty
	 * @generated
	 */
	EClass getContainerReferenceProperty();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty#getProperty()
	 * @see #getContainerReferenceProperty()
	 * @generated
	 */
	EReference getContainerReferenceProperty_Property();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty <em>Many Boolean Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Boolean Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty
	 * @generated
	 */
	EClass getManyBooleanAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getQuantifier <em>Quantifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantifier</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getQuantifier()
	 * @see #getManyBooleanAttributeProperty()
	 * @generated
	 */
	EAttribute getManyBooleanAttributeProperty_Quantifier();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#isValue()
	 * @see #getManyBooleanAttributeProperty()
	 * @generated
	 */
	EAttribute getManyBooleanAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty#getOperator()
	 * @see #getManyBooleanAttributeProperty()
	 * @generated
	 */
	EAttribute getManyBooleanAttributeProperty_Operator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty <em>Many Integer Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Integer Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty
	 * @generated
	 */
	EClass getManyIntegerAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getQuantifier <em>Quantifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantifier</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getQuantifier()
	 * @see #getManyIntegerAttributeProperty()
	 * @generated
	 */
	EAttribute getManyIntegerAttributeProperty_Quantifier();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getValue()
	 * @see #getManyIntegerAttributeProperty()
	 * @generated
	 */
	EAttribute getManyIntegerAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty#getOperator()
	 * @see #getManyIntegerAttributeProperty()
	 * @generated
	 */
	EAttribute getManyIntegerAttributeProperty_Operator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty <em>Many String Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many String Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty
	 * @generated
	 */
	EClass getManyStringAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getQuantifier <em>Quantifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantifier</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getQuantifier()
	 * @see #getManyStringAttributeProperty()
	 * @generated
	 */
	EAttribute getManyStringAttributeProperty_Quantifier();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getValue()
	 * @see #getManyStringAttributeProperty()
	 * @generated
	 */
	EAttribute getManyStringAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty#getOperator()
	 * @see #getManyStringAttributeProperty()
	 * @generated
	 */
	EAttribute getManyStringAttributeProperty_Operator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty <em>Boolean Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty
	 * @generated
	 */
	EClass getBooleanAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty#isValue()
	 * @see #getBooleanAttributeProperty()
	 * @generated
	 */
	EAttribute getBooleanAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty#getOperator()
	 * @see #getBooleanAttributeProperty()
	 * @generated
	 */
	EAttribute getBooleanAttributeProperty_Operator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty <em>Integer Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty
	 * @generated
	 */
	EClass getIntegerAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty#getValue()
	 * @see #getIntegerAttributeProperty()
	 * @generated
	 */
	EAttribute getIntegerAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty#getOperator()
	 * @see #getIntegerAttributeProperty()
	 * @generated
	 */
	EAttribute getIntegerAttributeProperty_Operator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.StringAttributeProperty <em>String Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StringAttributeProperty
	 * @generated
	 */
	EClass getStringAttributeProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.StringAttributeProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StringAttributeProperty#getValue()
	 * @see #getStringAttributeProperty()
	 * @generated
	 */
	EAttribute getStringAttributeProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.StringAttributeProperty#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StringAttributeProperty#getOperator()
	 * @see #getStringAttributeProperty()
	 * @generated
	 */
	EAttribute getStringAttributeProperty_Operator();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.property.UnaryOperator <em>Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.UnaryOperator
	 * @generated
	 */
	EEnum getUnaryOperator();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.property.ComparisonOperator <em>Comparison Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Comparison Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.ComparisonOperator
	 * @generated
	 */
	EEnum getComparisonOperator();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.StepProperty <em>Step Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StepProperty
	 * @generated
	 */
	EClass getStepProperty();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.property.StepProperty#getStepping <em>Stepping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stepping</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StepProperty#getStepping()
	 * @see #getStepProperty()
	 * @generated
	 */
	EAttribute getStepProperty_Stepping();

	/**
	 * Returns the meta object for the containment reference '{@link fr.inria.diverse.event.commons.model.property.StepProperty#getTargetProvider <em>Target Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target Provider</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.StepProperty#getTargetProvider()
	 * @see #getStepProperty()
	 * @generated
	 */
	EReference getStepProperty_TargetProvider();

	/**
	 * Returns the meta object for the '{@link fr.inria.diverse.event.commons.model.property.StepProperty#getOperation() <em>Get Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Operation</em>' operation.
	 * @see fr.inria.diverse.event.commons.model.property.StepProperty#getOperation()
	 * @generated
	 */
	EOperation getStepProperty__GetOperation();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.PropertyReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.PropertyReference
	 * @generated
	 */
	EClass getPropertyReference();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.property.PropertyReference#getReferencedProperty <em>Referenced Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.PropertyReference#getReferencedProperty()
	 * @see #getPropertyReference()
	 * @generated
	 */
	EReference getPropertyReference_ReferencedProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.CompositeProperty <em>Composite Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Property</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.CompositeProperty
	 * @generated
	 */
	EClass getCompositeProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.property.CompositeProperty#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.CompositeProperty#getProperties()
	 * @see #getCompositeProperty()
	 * @generated
	 */
	EReference getCompositeProperty_Properties();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.property.EventPrecondition <em>Event Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Precondition</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.EventPrecondition
	 * @generated
	 */
	EClass getEventPrecondition();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.property.EventPrecondition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.EventPrecondition#getEvent()
	 * @see #getEventPrecondition()
	 * @generated
	 */
	EReference getEventPrecondition_Event();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.property.BooleanOperator <em>Boolean Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Boolean Operator</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanOperator
	 * @generated
	 */
	EEnum getBooleanOperator();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.property.Quantifier <em>Quantifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Quantifier</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.Quantifier
	 * @generated
	 */
	EEnum getQuantifier();

	/**
	 * Returns the meta object for enum '{@link fr.inria.diverse.event.commons.model.property.Stepping <em>Stepping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Stepping</em>'.
	 * @see fr.inria.diverse.event.commons.model.property.Stepping
	 * @generated
	 */
	EEnum getStepping();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PropertyFactory getPropertyFactory();

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
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.AbstractPropertyImpl <em>Abstract Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.AbstractPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getAbstractProperty()
		 * @generated
		 */
		EClass ABSTRACT_PROPERTY = eINSTANCE.getAbstractProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.TemporalPropertyImpl <em>Temporal Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.TemporalPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getTemporalProperty()
		 * @generated
		 */
		EClass TEMPORAL_PROPERTY = eINSTANCE.getTemporalProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.NextPropertyImpl <em>Next Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.NextPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getNextProperty()
		 * @generated
		 */
		EClass NEXT_PROPERTY = eINSTANCE.getNextProperty();

		/**
		 * The meta object literal for the '<em><b>Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEXT_PROPERTY__FORMULA = eINSTANCE.getNextProperty_Formula();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.UntilPropertyImpl <em>Until Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.UntilPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUntilProperty()
		 * @generated
		 */
		EClass UNTIL_PROPERTY = eINSTANCE.getUntilProperty();

		/**
		 * The meta object literal for the '<em><b>Left Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNTIL_PROPERTY__LEFT_FORMULA = eINSTANCE.getUntilProperty_LeftFormula();

		/**
		 * The meta object literal for the '<em><b>Right Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNTIL_PROPERTY__RIGHT_FORMULA = eINSTANCE.getUntilProperty_RightFormula();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl <em>Release Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getReleaseProperty()
		 * @generated
		 */
		EClass RELEASE_PROPERTY = eINSTANCE.getReleaseProperty();

		/**
		 * The meta object literal for the '<em><b>Left Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE_PROPERTY__LEFT_FORMULA = eINSTANCE.getReleaseProperty_LeftFormula();

		/**
		 * The meta object literal for the '<em><b>Right Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELEASE_PROPERTY__RIGHT_FORMULA = eINSTANCE.getReleaseProperty_RightFormula();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.NegationTemporalPropertyImpl <em>Negation Temporal Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.NegationTemporalPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getNegationTemporalProperty()
		 * @generated
		 */
		EClass NEGATION_TEMPORAL_PROPERTY = eINSTANCE.getNegationTemporalProperty();

		/**
		 * The meta object literal for the '<em><b>Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEGATION_TEMPORAL_PROPERTY__FORMULA = eINSTANCE.getNegationTemporalProperty_Formula();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.StatePropertyImpl <em>State Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.StatePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStateProperty()
		 * @generated
		 */
		EClass STATE_PROPERTY = eINSTANCE.getStateProperty();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_PROPERTY__TARGET = eINSTANCE.getStateProperty_Target();

		/**
		 * The meta object literal for the '<em><b>Get Feature</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STATE_PROPERTY___GET_FEATURE = eINSTANCE.getStateProperty__GetFeature();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.UnaryPropertyImpl <em>Unary Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.UnaryPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUnaryProperty()
		 * @generated
		 */
		EClass UNARY_PROPERTY = eINSTANCE.getUnaryProperty();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_PROPERTY__OPERATOR = eINSTANCE.getUnaryProperty_Operator();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_PROPERTY__PROPERTY = eINSTANCE.getUnaryProperty_Property();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.BinaryPropertyImpl <em>Binary Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.BinaryPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBinaryProperty()
		 * @generated
		 */
		EClass BINARY_PROPERTY = eINSTANCE.getBinaryProperty();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_PROPERTY__OPERATOR = eINSTANCE.getBinaryProperty_Operator();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_PROPERTY__LEFT = eINSTANCE.getBinaryProperty_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_PROPERTY__RIGHT = eINSTANCE.getBinaryProperty_Right();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyReferencePropertyImpl <em>Many Reference Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ManyReferencePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyReferenceProperty()
		 * @generated
		 */
		EClass MANY_REFERENCE_PROPERTY = eINSTANCE.getManyReferenceProperty();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANY_REFERENCE_PROPERTY__PROPERTY = eINSTANCE.getManyReferenceProperty_Property();

		/**
		 * The meta object literal for the '<em><b>Quantifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_REFERENCE_PROPERTY__QUANTIFIER = eINSTANCE.getManyReferenceProperty_Quantifier();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.SingleReferencePropertyImpl <em>Single Reference Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.SingleReferencePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getSingleReferenceProperty()
		 * @generated
		 */
		EClass SINGLE_REFERENCE_PROPERTY = eINSTANCE.getSingleReferenceProperty();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_REFERENCE_PROPERTY__PROPERTY = eINSTANCE.getSingleReferenceProperty_Property();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ContainerReferencePropertyImpl <em>Container Reference Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ContainerReferencePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getContainerReferenceProperty()
		 * @generated
		 */
		EClass CONTAINER_REFERENCE_PROPERTY = eINSTANCE.getContainerReferenceProperty();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_REFERENCE_PROPERTY__PROPERTY = eINSTANCE.getContainerReferenceProperty_Property();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyBooleanAttributePropertyImpl <em>Many Boolean Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ManyBooleanAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyBooleanAttributeProperty()
		 * @generated
		 */
		EClass MANY_BOOLEAN_ATTRIBUTE_PROPERTY = eINSTANCE.getManyBooleanAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Quantifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_ATTRIBUTE_PROPERTY__QUANTIFIER = eINSTANCE.getManyBooleanAttributeProperty_Quantifier();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getManyBooleanAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getManyBooleanAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyIntegerAttributePropertyImpl <em>Many Integer Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ManyIntegerAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyIntegerAttributeProperty()
		 * @generated
		 */
		EClass MANY_INTEGER_ATTRIBUTE_PROPERTY = eINSTANCE.getManyIntegerAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Quantifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_ATTRIBUTE_PROPERTY__QUANTIFIER = eINSTANCE.getManyIntegerAttributeProperty_Quantifier();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getManyIntegerAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getManyIntegerAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.ManyStringAttributePropertyImpl <em>Many String Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.ManyStringAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getManyStringAttributeProperty()
		 * @generated
		 */
		EClass MANY_STRING_ATTRIBUTE_PROPERTY = eINSTANCE.getManyStringAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Quantifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_STRING_ATTRIBUTE_PROPERTY__QUANTIFIER = eINSTANCE.getManyStringAttributeProperty_Quantifier();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_STRING_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getManyStringAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_STRING_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getManyStringAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.BooleanAttributePropertyImpl <em>Boolean Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.BooleanAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBooleanAttributeProperty()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE_PROPERTY = eINSTANCE.getBooleanAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getBooleanAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getBooleanAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.IntegerAttributePropertyImpl <em>Integer Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.IntegerAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getIntegerAttributeProperty()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTE_PROPERTY = eINSTANCE.getIntegerAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getIntegerAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getIntegerAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.StringAttributePropertyImpl <em>String Attribute Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.StringAttributePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStringAttributeProperty()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE_PROPERTY = eINSTANCE.getStringAttributeProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_PROPERTY__VALUE = eINSTANCE.getStringAttributeProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_PROPERTY__OPERATOR = eINSTANCE.getStringAttributeProperty_Operator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.UnaryOperator <em>Unary Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.UnaryOperator
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getUnaryOperator()
		 * @generated
		 */
		EEnum UNARY_OPERATOR = eINSTANCE.getUnaryOperator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.ComparisonOperator <em>Comparison Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.ComparisonOperator
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getComparisonOperator()
		 * @generated
		 */
		EEnum COMPARISON_OPERATOR = eINSTANCE.getComparisonOperator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.StepPropertyImpl <em>Step Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.StepPropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStepProperty()
		 * @generated
		 */
		EClass STEP_PROPERTY = eINSTANCE.getStepProperty();

		/**
		 * The meta object literal for the '<em><b>Stepping</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP_PROPERTY__STEPPING = eINSTANCE.getStepProperty_Stepping();

		/**
		 * The meta object literal for the '<em><b>Target Provider</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP_PROPERTY__TARGET_PROVIDER = eINSTANCE.getStepProperty_TargetProvider();

		/**
		 * The meta object literal for the '<em><b>Get Operation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation STEP_PROPERTY___GET_OPERATION = eINSTANCE.getStepProperty__GetOperation();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.PropertyReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyReferenceImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getPropertyReference()
		 * @generated
		 */
		EClass PROPERTY_REFERENCE = eINSTANCE.getPropertyReference();

		/**
		 * The meta object literal for the '<em><b>Referenced Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REFERENCE__REFERENCED_PROPERTY = eINSTANCE.getPropertyReference_ReferencedProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.CompositePropertyImpl <em>Composite Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.CompositePropertyImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getCompositeProperty()
		 * @generated
		 */
		EClass COMPOSITE_PROPERTY = eINSTANCE.getCompositeProperty();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_PROPERTY__PROPERTIES = eINSTANCE.getCompositeProperty_Properties();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.impl.EventPreconditionImpl <em>Event Precondition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.impl.EventPreconditionImpl
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getEventPrecondition()
		 * @generated
		 */
		EClass EVENT_PRECONDITION = eINSTANCE.getEventPrecondition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_PRECONDITION__EVENT = eINSTANCE.getEventPrecondition_Event();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.BooleanOperator <em>Boolean Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.BooleanOperator
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getBooleanOperator()
		 * @generated
		 */
		EEnum BOOLEAN_OPERATOR = eINSTANCE.getBooleanOperator();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.Quantifier <em>Quantifier</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.Quantifier
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getQuantifier()
		 * @generated
		 */
		EEnum QUANTIFIER = eINSTANCE.getQuantifier();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.property.Stepping <em>Stepping</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.property.Stepping
		 * @see fr.inria.diverse.event.commons.model.property.impl.PropertyPackageImpl#getStepping()
		 * @generated
		 */
		EEnum STEPPING = eINSTANCE.getStepping();

	}

} //PropertyPackage
