/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericState;
import fr.inria.diverse.trace.commons.model.trace.GenericTrace;
import fr.inria.diverse.trace.commons.model.trace.GenericTracedObject;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GenericTraceImpl<StepSubType> extends TraceImpl<StepSubType, GenericTracedObject<?>, GenericState> implements GenericTrace<StepSubType> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public NotificationChain basicSetRootStep(StepSubType newRootStep, NotificationChain msgs) {
		return super.basicSetRootStep(newRootStep, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<GenericTracedObject<?>> getTracedObjects() {
		if (tracedObjects == null) {
			tracedObjects = new EObjectContainmentEList<GenericTracedObject<?>>(GenericTracedObject.class, this, TracePackage.GENERIC_TRACE__TRACED_OBJECTS);
		}
		return tracedObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<GenericState> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<GenericState>(GenericState.class, this, TracePackage.GENERIC_TRACE__STATES);
		}
		return states;
	}

} //GenericTraceImpl
