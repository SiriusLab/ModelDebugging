/**
 */
package tfsm.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tfsm.EvaluateGuard;
import tfsm.EventGuard;
import tfsm.Guard;
import tfsm.NamedElement;
import tfsm.State;
import tfsm.TemporalGuard;
import tfsm.Tfsm;
import tfsm.TfsmClock;
import tfsm.TfsmEvent;
import tfsm.TfsmPackage;
import tfsm.Transition;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tfsm.TfsmPackage
 * @generated
 */
public class TfsmAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TfsmPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TfsmAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TfsmPackage.eINSTANCE;
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
    protected TfsmSwitch<Adapter> modelSwitch =
        new TfsmSwitch<Adapter>() {
            @Override
            public Adapter caseTfsm(Tfsm object) {
                return createTfsmAdapter();
            }
            @Override
            public Adapter caseState(State object) {
                return createStateAdapter();
            }
            @Override
            public Adapter caseTransition(Transition object) {
                return createTransitionAdapter();
            }
            @Override
            public Adapter caseNamedElement(NamedElement object) {
                return createNamedElementAdapter();
            }
            @Override
            public Adapter caseGuard(Guard object) {
                return createGuardAdapter();
            }
            @Override
            public Adapter caseTemporalGuard(TemporalGuard object) {
                return createTemporalGuardAdapter();
            }
            @Override
            public Adapter caseEventGuard(EventGuard object) {
                return createEventGuardAdapter();
            }
            @Override
            public Adapter caseTfsmEvent(TfsmEvent object) {
                return createTfsmEventAdapter();
            }
            @Override
            public Adapter caseTfsmClock(TfsmClock object) {
                return createTfsmClockAdapter();
            }
            @Override
            public Adapter caseSystem(tfsm.System object) {
                return createSystemAdapter();
            }
            @Override
            public Adapter caseEvaluateGuard(EvaluateGuard object) {
                return createEvaluateGuardAdapter();
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
     * Creates a new adapter for an object of class '{@link tfsm.Tfsm <em>Tfsm</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.Tfsm
     * @generated
     */
    public Adapter createTfsmAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.State <em>State</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.State
     * @generated
     */
    public Adapter createStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.Transition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.Transition
     * @generated
     */
    public Adapter createTransitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.NamedElement
     * @generated
     */
    public Adapter createNamedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.Guard <em>Guard</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.Guard
     * @generated
     */
    public Adapter createGuardAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.TemporalGuard <em>Temporal Guard</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.TemporalGuard
     * @generated
     */
    public Adapter createTemporalGuardAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.EventGuard <em>Event Guard</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.EventGuard
     * @generated
     */
    public Adapter createEventGuardAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.TfsmEvent <em>Event</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.TfsmEvent
     * @generated
     */
    public Adapter createTfsmEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.TfsmClock <em>Clock</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.TfsmClock
     * @generated
     */
    public Adapter createTfsmClockAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.System <em>System</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.System
     * @generated
     */
    public Adapter createSystemAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link tfsm.EvaluateGuard <em>Evaluate Guard</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see tfsm.EvaluateGuard
     * @generated
     */
    public Adapter createEvaluateGuardAdapter() {
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

} //TfsmAdapterFactory
