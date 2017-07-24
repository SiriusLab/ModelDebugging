/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.gemoc.event.commons.model.property.Property;
import org.eclipse.gemoc.event.commons.model.property.StateProperty;

import org.eclipse.gemoc.event.commons.model.scenario.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage
 * @generated
 */
public class ScenarioSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ScenarioPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioSwitch() {
		if (modelPackage == null) {
			modelPackage = ScenarioPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ScenarioPackage.SCENARIO: {
				Scenario<?> scenario = (Scenario<?>)theEObject;
				T1 result = caseScenario(scenario);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.EVENT: {
				Event<?> event = (Event<?>)theEObject;
				T1 result = caseEvent(event);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ELEMENT_PROVIDER: {
				ElementProvider<?> elementProvider = (ElementProvider<?>)theEObject;
				T1 result = caseElementProvider(elementProvider);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ELEMENT_REFERENCE: {
				ElementReference<?> elementReference = (ElementReference<?>)theEObject;
				T1 result = caseElementReference(elementReference);
				if (result == null) result = caseElementProvider(elementReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ELEMENT_QUERY: {
				ElementQuery<?, ?> elementQuery = (ElementQuery<?, ?>)theEObject;
				T1 result = caseElementQuery(elementQuery);
				if (result == null) result = caseElementProvider(elementQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.EVENT_OCCURRENCE: {
				EventOccurrence<?, ?> eventOccurrence = (EventOccurrence<?, ?>)theEObject;
				T1 result = caseEventOccurrence(eventOccurrence);
				if (result == null) result = caseScenarioElement(eventOccurrence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_ELEMENT: {
				ScenarioElement<?> scenarioElement = (ScenarioElement<?>)theEObject;
				T1 result = caseScenarioElement(scenarioElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_FSM: {
				ScenarioFSM<?, ?, ?, ?> scenarioFSM = (ScenarioFSM<?, ?, ?, ?>)theEObject;
				T1 result = caseScenarioFSM(scenarioFSM);
				if (result == null) result = caseScenarioElement(scenarioFSM);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_FSM_STATE: {
				ScenarioFSMState<?, ?> scenarioFSMState = (ScenarioFSMState<?, ?>)theEObject;
				T1 result = caseScenarioFSMState(scenarioFSMState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_FSM_TRANSITION: {
				ScenarioFSMTransition<?, ?> scenarioFSMTransition = (ScenarioFSMTransition<?, ?>)theEObject;
				T1 result = caseScenarioFSMTransition(scenarioFSMTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ARBITER: {
				Arbiter<?, ?, ?> arbiter = (Arbiter<?, ?, ?>)theEObject;
				T1 result = caseArbiter(arbiter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ARBITER_STATE: {
				ArbiterState<?, ?> arbiterState = (ArbiterState<?, ?>)theEObject;
				T1 result = caseArbiterState(arbiterState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ARBITER_TRANSITION: {
				ArbiterTransition<?, ?> arbiterTransition = (ArbiterTransition<?, ?>)theEObject;
				T1 result = caseArbiterTransition(arbiterTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scenario</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends ScenarioElement<?>> T1 caseScenario(Scenario<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseEvent(Event<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseElementProvider(ElementProvider<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseElementReference(ElementReference<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T, P extends StateProperty<T>> T1 caseElementQuery(ElementQuery<T, P> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Occurrence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends Event<?>, P extends Property> T1 caseEventOccurrence(EventOccurrence<E, P> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property> T1 caseScenarioElement(ScenarioElement<P> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FSM</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FSM</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property, E extends Event<?>, S extends ScenarioFSMState<E, T>, T extends ScenarioFSMTransition<P, S>> T1 caseScenarioFSM(ScenarioFSM<P, E, S, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FSM State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FSM State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends Event<?>, T extends ScenarioFSMTransition<?, ?>> T1 caseScenarioFSMState(ScenarioFSMState<E, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FSM Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FSM Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property, S extends ScenarioFSMState<?, ?>> T1 caseScenarioFSMTransition(ScenarioFSMTransition<P, S> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arbiter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arbiter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property, S extends ArbiterState<P, T>, T extends ArbiterTransition<P, S>> T1 caseArbiter(Arbiter<P, S, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arbiter State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arbiter State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property, T extends ArbiterTransition<P, ?>> T1 caseArbiterState(ArbiterState<P, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arbiter Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arbiter Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends Property, S extends ArbiterState<P, ?>> T1 caseArbiterTransition(ArbiterTransition<P, S> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //ScenarioSwitch
