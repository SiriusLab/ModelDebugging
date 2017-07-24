/**
 */
package fr.inria.diverse.event.commons.model.property.util;

import fr.inria.diverse.event.commons.model.property.*;

import fr.inria.diverse.event.commons.model.scenario.Event;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage
 * @generated
 */
public class PropertyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PropertyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PropertyPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertySwitch<Adapter> modelSwitch =
		new PropertySwitch<Adapter>() {
			@Override
			public Adapter caseAbstractProperty(AbstractProperty object) {
				return createAbstractPropertyAdapter();
			}
			@Override
			public Adapter caseTemporalProperty(TemporalProperty object) {
				return createTemporalPropertyAdapter();
			}
			@Override
			public <P extends AbstractProperty> Adapter caseNextProperty(NextProperty<P> object) {
				return createNextPropertyAdapter();
			}
			@Override
			public <P extends AbstractProperty> Adapter caseUntilProperty(UntilProperty<P> object) {
				return createUntilPropertyAdapter();
			}
			@Override
			public <P extends AbstractProperty> Adapter caseReleaseProperty(ReleaseProperty<P> object) {
				return createReleasePropertyAdapter();
			}
			@Override
			public <P extends TemporalProperty> Adapter caseNegationTemporalProperty(NegationTemporalProperty<P> object) {
				return createNegationTemporalPropertyAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public <P extends Property> Adapter casePropertyReference(PropertyReference<P> object) {
				return createPropertyReferenceAdapter();
			}
			@Override
			public <P extends Property> Adapter caseCompositeProperty(CompositeProperty<P> object) {
				return createCompositePropertyAdapter();
			}
			@Override
			public <E extends Event<?>> Adapter caseEventPrecondition(EventPrecondition<E> object) {
				return createEventPreconditionAdapter();
			}
			@Override
			public <T> Adapter caseStepProperty(StepProperty<T> object) {
				return createStepPropertyAdapter();
			}
			@Override
			public <T> Adapter caseStateProperty(StateProperty<T> object) {
				return createStatePropertyAdapter();
			}
			@Override
			public <P extends StateProperty<?>, T> Adapter caseUnaryProperty(UnaryProperty<P, T> object) {
				return createUnaryPropertyAdapter();
			}
			@Override
			public <P extends StateProperty<?>, T> Adapter caseBinaryProperty(BinaryProperty<P, T> object) {
				return createBinaryPropertyAdapter();
			}
			@Override
			public <P extends StateProperty<?>, T> Adapter caseManyReferenceProperty(ManyReferenceProperty<P, T> object) {
				return createManyReferencePropertyAdapter();
			}
			@Override
			public <P extends StateProperty<?>, T> Adapter caseSingleReferenceProperty(SingleReferenceProperty<P, T> object) {
				return createSingleReferencePropertyAdapter();
			}
			@Override
			public <P extends StateProperty<?>, T> Adapter caseContainerReferenceProperty(ContainerReferenceProperty<P, T> object) {
				return createContainerReferencePropertyAdapter();
			}
			@Override
			public <T> Adapter caseManyBooleanAttributeProperty(ManyBooleanAttributeProperty<T> object) {
				return createManyBooleanAttributePropertyAdapter();
			}
			@Override
			public <T> Adapter caseManyIntegerAttributeProperty(ManyIntegerAttributeProperty<T> object) {
				return createManyIntegerAttributePropertyAdapter();
			}
			@Override
			public <T> Adapter caseManyStringAttributeProperty(ManyStringAttributeProperty<T> object) {
				return createManyStringAttributePropertyAdapter();
			}
			@Override
			public <T> Adapter caseBooleanAttributeProperty(BooleanAttributeProperty<T> object) {
				return createBooleanAttributePropertyAdapter();
			}
			@Override
			public <T> Adapter caseIntegerAttributeProperty(IntegerAttributeProperty<T> object) {
				return createIntegerAttributePropertyAdapter();
			}
			@Override
			public <T> Adapter caseStringAttributeProperty(StringAttributeProperty<T> object) {
				return createStringAttributePropertyAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.AbstractProperty <em>Abstract Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.AbstractProperty
	 * @generated
	 */
	public Adapter createAbstractPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.TemporalProperty <em>Temporal Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.TemporalProperty
	 * @generated
	 */
	public Adapter createTemporalPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.NextProperty <em>Next Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.NextProperty
	 * @generated
	 */
	public Adapter createNextPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.UntilProperty <em>Until Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.UntilProperty
	 * @generated
	 */
	public Adapter createUntilPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ReleaseProperty <em>Release Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ReleaseProperty
	 * @generated
	 */
	public Adapter createReleasePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.NegationTemporalProperty <em>Negation Temporal Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.NegationTemporalProperty
	 * @generated
	 */
	public Adapter createNegationTemporalPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.StateProperty <em>State Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.StateProperty
	 * @generated
	 */
	public Adapter createStatePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.UnaryProperty <em>Unary Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.UnaryProperty
	 * @generated
	 */
	public Adapter createUnaryPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.BinaryProperty <em>Binary Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.BinaryProperty
	 * @generated
	 */
	public Adapter createBinaryPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ManyReferenceProperty <em>Many Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ManyReferenceProperty
	 * @generated
	 */
	public Adapter createManyReferencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.SingleReferenceProperty <em>Single Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.SingleReferenceProperty
	 * @generated
	 */
	public Adapter createSingleReferencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty <em>Container Reference Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ContainerReferenceProperty
	 * @generated
	 */
	public Adapter createContainerReferencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty <em>Many Boolean Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ManyBooleanAttributeProperty
	 * @generated
	 */
	public Adapter createManyBooleanAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty <em>Many Integer Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ManyIntegerAttributeProperty
	 * @generated
	 */
	public Adapter createManyIntegerAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty <em>Many String Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.ManyStringAttributeProperty
	 * @generated
	 */
	public Adapter createManyStringAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty <em>Boolean Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.BooleanAttributeProperty
	 * @generated
	 */
	public Adapter createBooleanAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty <em>Integer Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.IntegerAttributeProperty
	 * @generated
	 */
	public Adapter createIntegerAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.StringAttributeProperty <em>String Attribute Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.StringAttributeProperty
	 * @generated
	 */
	public Adapter createStringAttributePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.StepProperty <em>Step Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.StepProperty
	 * @generated
	 */
	public Adapter createStepPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.PropertyReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.PropertyReference
	 * @generated
	 */
	public Adapter createPropertyReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.CompositeProperty <em>Composite Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.CompositeProperty
	 * @generated
	 */
	public Adapter createCompositePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.inria.diverse.event.commons.model.property.EventPrecondition <em>Event Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.inria.diverse.event.commons.model.property.EventPrecondition
	 * @generated
	 */
	public Adapter createEventPreconditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PropertyAdapterFactory
