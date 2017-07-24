/**
 */
package fr.inria.diverse.event.commons.model.property.impl;

import fr.inria.diverse.event.commons.model.property.*;

import fr.inria.diverse.event.commons.model.scenario.Event;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertyFactoryImpl extends EFactoryImpl implements PropertyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertyFactory init() {
		try {
			PropertyFactory thePropertyFactory = (PropertyFactory)EPackage.Registry.INSTANCE.getEFactory(PropertyPackage.eNS_URI);
			if (thePropertyFactory != null) {
				return thePropertyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PropertyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PropertyPackage.ABSTRACT_PROPERTY: return createAbstractProperty();
			case PropertyPackage.PROPERTY_REFERENCE: return createPropertyReference();
			case PropertyPackage.COMPOSITE_PROPERTY: return createCompositeProperty();
			case PropertyPackage.EVENT_PRECONDITION: return createEventPrecondition();
			case PropertyPackage.CONTAINER_REFERENCE_PROPERTY: return createContainerReferenceProperty();
			case PropertyPackage.MANY_BOOLEAN_ATTRIBUTE_PROPERTY: return createManyBooleanAttributeProperty();
			case PropertyPackage.MANY_INTEGER_ATTRIBUTE_PROPERTY: return createManyIntegerAttributeProperty();
			case PropertyPackage.MANY_STRING_ATTRIBUTE_PROPERTY: return createManyStringAttributeProperty();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PropertyPackage.UNARY_OPERATOR:
				return createUnaryOperatorFromString(eDataType, initialValue);
			case PropertyPackage.COMPARISON_OPERATOR:
				return createComparisonOperatorFromString(eDataType, initialValue);
			case PropertyPackage.BOOLEAN_OPERATOR:
				return createBooleanOperatorFromString(eDataType, initialValue);
			case PropertyPackage.QUANTIFIER:
				return createQuantifierFromString(eDataType, initialValue);
			case PropertyPackage.STEPPING:
				return createSteppingFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PropertyPackage.UNARY_OPERATOR:
				return convertUnaryOperatorToString(eDataType, instanceValue);
			case PropertyPackage.COMPARISON_OPERATOR:
				return convertComparisonOperatorToString(eDataType, instanceValue);
			case PropertyPackage.BOOLEAN_OPERATOR:
				return convertBooleanOperatorToString(eDataType, instanceValue);
			case PropertyPackage.QUANTIFIER:
				return convertQuantifierToString(eDataType, instanceValue);
			case PropertyPackage.STEPPING:
				return convertSteppingToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractProperty createAbstractProperty() {
		AbstractPropertyImpl abstractProperty = new AbstractPropertyImpl();
		return abstractProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <P extends StateProperty<?>, T> ContainerReferenceProperty<P, T> createContainerReferenceProperty() {
		ContainerReferencePropertyImpl<P, T> containerReferenceProperty = new ContainerReferencePropertyImpl<P, T>();
		return containerReferenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <T> ManyBooleanAttributeProperty<T> createManyBooleanAttributeProperty() {
		ManyBooleanAttributePropertyImpl<T> manyBooleanAttributeProperty = new ManyBooleanAttributePropertyImpl<T>();
		return manyBooleanAttributeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <T> ManyIntegerAttributeProperty<T> createManyIntegerAttributeProperty() {
		ManyIntegerAttributePropertyImpl<T> manyIntegerAttributeProperty = new ManyIntegerAttributePropertyImpl<T>();
		return manyIntegerAttributeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <T> ManyStringAttributeProperty<T> createManyStringAttributeProperty() {
		ManyStringAttributePropertyImpl<T> manyStringAttributeProperty = new ManyStringAttributePropertyImpl<T>();
		return manyStringAttributeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryOperator createUnaryOperatorFromString(EDataType eDataType, String initialValue) {
		UnaryOperator result = UnaryOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComparisonOperator createComparisonOperatorFromString(EDataType eDataType, String initialValue) {
		ComparisonOperator result = ComparisonOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <P extends Property> PropertyReference<P> createPropertyReference() {
		PropertyReferenceImpl<P> propertyReference = new PropertyReferenceImpl<P>();
		return propertyReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <P extends Property> CompositeProperty<P> createCompositeProperty() {
		CompositePropertyImpl<P> compositeProperty = new CompositePropertyImpl<P>();
		return compositeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <E extends Event<?>> EventPrecondition<E> createEventPrecondition() {
		EventPreconditionImpl<E> eventPrecondition = new EventPreconditionImpl<E>();
		return eventPrecondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanOperator createBooleanOperatorFromString(EDataType eDataType, String initialValue) {
		BooleanOperator result = BooleanOperator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanOperatorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quantifier createQuantifierFromString(EDataType eDataType, String initialValue) {
		Quantifier result = Quantifier.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQuantifierToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stepping createSteppingFromString(EDataType eDataType, String initialValue) {
		Stepping result = Stepping.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSteppingToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyPackage getPropertyPackage() {
		return (PropertyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PropertyPackage getPackage() {
		return PropertyPackage.eINSTANCE;
	}

} //PropertyFactoryImpl
