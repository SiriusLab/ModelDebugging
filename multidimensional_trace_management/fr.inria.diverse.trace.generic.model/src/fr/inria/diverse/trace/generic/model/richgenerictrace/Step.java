/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep <em>Next Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep <em>Previous Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState <em>Ending State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState <em>Starting State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep <em>Parent Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getAppliedRule <em>Applied Rule</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps <em>Children Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep()
 * @model abstract="true"
 * @generated
 */
public interface Step extends EObject {
	/**
	 * Returns the value of the '<em><b>Next Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep <em>Previous Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Step</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Step</em>' reference.
	 * @see #setNextStep(Step)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_NextStep()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep
	 * @model opposite="previousStep" derived="true"
	 * @generated
	 */
	Step getNextStep();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep <em>Next Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Step</em>' reference.
	 * @see #getNextStep()
	 * @generated
	 */
	void setNextStep(Step value);

	/**
	 * Returns the value of the '<em><b>Previous Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep <em>Next Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Step</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Step</em>' reference.
	 * @see #setPreviousStep(Step)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_PreviousStep()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getNextStep
	 * @model opposite="nextStep" derived="true"
	 * @generated
	 */
	Step getPreviousStep();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getPreviousStep <em>Previous Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous Step</em>' reference.
	 * @see #getPreviousStep()
	 * @generated
	 */
	void setPreviousStep(Step value);

	/**
	 * Returns the value of the '<em><b>Ending State</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getEndingSteps <em>Ending Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending State</em>' reference.
	 * @see #setEndingState(ExecutionState)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_EndingState()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getEndingSteps
	 * @model opposite="endingSteps"
	 * @generated
	 */
	ExecutionState getEndingState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getEndingState <em>Ending State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending State</em>' reference.
	 * @see #getEndingState()
	 * @generated
	 */
	void setEndingState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Starting State</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getStartingSteps <em>Starting Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting State</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting State</em>' container reference.
	 * @see #setStartingState(ExecutionState)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_StartingState()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState#getStartingSteps
	 * @model opposite="startingSteps" required="true" transient="false"
	 * @generated
	 */
	ExecutionState getStartingState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getStartingState <em>Starting State</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting State</em>' container reference.
	 * @see #getStartingState()
	 * @generated
	 */
	void setStartingState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Parent Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps <em>Children Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Step</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Step</em>' reference.
	 * @see #setParentStep(Step)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_ParentStep()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps
	 * @model opposite="childrenSteps" derived="true"
	 * @generated
	 */
	Step getParentStep();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep <em>Parent Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Step</em>' reference.
	 * @see #getParentStep()
	 * @generated
	 */
	void setParentStep(Step value);

	/**
	 * Returns the value of the '<em><b>Applied Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applied Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applied Rule</em>' reference.
	 * @see #setAppliedRule(EOperation)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_AppliedRule()
	 * @model required="true"
	 * @generated
	 */
	EOperation getAppliedRule();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getAppliedRule <em>Applied Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied Rule</em>' reference.
	 * @see #getAppliedRule()
	 * @generated
	 */
	void setAppliedRule(EOperation value);

	/**
	 * Returns the value of the '<em><b>Children Steps</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep <em>Parent Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children Steps</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children Steps</em>' reference.
	 * @see #setChildrenSteps(Step)
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage#getStep_ChildrenSteps()
	 * @see fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getParentStep
	 * @model opposite="parentStep"
	 * @generated
	 */
	Step getChildrenSteps();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.generic.model.richgenerictrace.Step#getChildrenSteps <em>Children Steps</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Children Steps</em>' reference.
	 * @see #getChildrenSteps()
	 * @generated
	 */
	void setChildrenSteps(Step value);

} // Step
