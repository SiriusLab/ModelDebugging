/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.event.commons.model.property.Property;
import org.eclipse.gemoc.event.commons.model.property.StateProperty;

import org.eclipse.gemoc.event.commons.model.scenario.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage
 * @generated
 */
public class ScenarioAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ScenarioPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ScenarioPackage.eINSTANCE;
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
	protected ScenarioSwitch<Adapter> modelSwitch =
		new ScenarioSwitch<Adapter>() {
			@Override
			public <T extends ScenarioElement<?>> Adapter caseScenario(Scenario<T> object) {
				return createScenarioAdapter();
			}
			@Override
			public <T> Adapter caseEvent(Event<T> object) {
				return createEventAdapter();
			}
			@Override
			public <T> Adapter caseElementProvider(ElementProvider<T> object) {
				return createElementProviderAdapter();
			}
			@Override
			public <T> Adapter caseElementReference(ElementReference<T> object) {
				return createElementReferenceAdapter();
			}
			@Override
			public <T, P extends StateProperty<T>> Adapter caseElementQuery(ElementQuery<T, P> object) {
				return createElementQueryAdapter();
			}
			@Override
			public <E extends Event<?>, P extends Property> Adapter caseEventOccurrence(EventOccurrence<E, P> object) {
				return createEventOccurrenceAdapter();
			}
			@Override
			public <P extends Property> Adapter caseScenarioElement(ScenarioElement<P> object) {
				return createScenarioElementAdapter();
			}
			@Override
			public <P extends Property, E extends Event<?>, S extends ScenarioFSMState<E, T>, T extends ScenarioFSMTransition<P, S>> Adapter caseScenarioFSM(ScenarioFSM<P, E, S, T> object) {
				return createScenarioFSMAdapter();
			}
			@Override
			public <E extends Event<?>, T extends ScenarioFSMTransition<?, ?>> Adapter caseScenarioFSMState(ScenarioFSMState<E, T> object) {
				return createScenarioFSMStateAdapter();
			}
			@Override
			public <P extends Property, S extends ScenarioFSMState<?, ?>> Adapter caseScenarioFSMTransition(ScenarioFSMTransition<P, S> object) {
				return createScenarioFSMTransitionAdapter();
			}
			@Override
			public <P extends Property, S extends ArbiterState<P, T>, T extends ArbiterTransition<P, S>> Adapter caseArbiter(Arbiter<P, S, T> object) {
				return createArbiterAdapter();
			}
			@Override
			public <P extends Property, T extends ArbiterTransition<P, ?>> Adapter caseArbiterState(ArbiterState<P, T> object) {
				return createArbiterStateAdapter();
			}
			@Override
			public <P extends Property, S extends ArbiterState<P, ?>> Adapter caseArbiterTransition(ArbiterTransition<P, S> object) {
				return createArbiterTransitionAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.Scenario
	 * @generated
	 */
	public Adapter createScenarioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ElementProvider <em>Element Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ElementProvider
	 * @generated
	 */
	public Adapter createElementProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ElementReference <em>Element Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ElementReference
	 * @generated
	 */
	public Adapter createElementReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ElementQuery <em>Element Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ElementQuery
	 * @generated
	 */
	public Adapter createElementQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.EventOccurrence <em>Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.EventOccurrence
	 * @generated
	 */
	public Adapter createEventOccurrenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement
	 * @generated
	 */
	public Adapter createScenarioElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM <em>FSM</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSM
	 * @generated
	 */
	public Adapter createScenarioFSMAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState <em>FSM State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMState
	 * @generated
	 */
	public Adapter createScenarioFSMStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition <em>FSM Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioFSMTransition
	 * @generated
	 */
	public Adapter createScenarioFSMTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.Arbiter <em>Arbiter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.Arbiter
	 * @generated
	 */
	public Adapter createArbiterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ArbiterState <em>Arbiter State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ArbiterState
	 * @generated
	 */
	public Adapter createArbiterStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.event.commons.model.scenario.ArbiterTransition <em>Arbiter Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.event.commons.model.scenario.ArbiterTransition
	 * @generated
	 */
	public Adapter createArbiterTransitionAdapter() {
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

} //ScenarioAdapterFactory
