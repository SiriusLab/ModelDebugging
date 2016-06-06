/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.ParallelStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parallel Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ParallelStepImpl<StepSubtype extends Step> extends BigStepImpl<StepSubtype> implements ParallelStep<StepSubtype> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParallelStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.PARALLEL_STEP;
	}

} //ParallelStepImpl
