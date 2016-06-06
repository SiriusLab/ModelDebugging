/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericParallelStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Parallel Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GenericParallelStepImpl extends ParallelStepImpl<Step> implements GenericParallelStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericParallelStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_PARALLEL_STEP;
	}

} //GenericParallelStepImpl
