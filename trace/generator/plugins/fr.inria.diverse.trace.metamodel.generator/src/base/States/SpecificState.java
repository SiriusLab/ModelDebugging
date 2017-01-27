/**
 */
package base.States;

import base.Steps.SpecificStep;

import fr.inria.diverse.trace.commons.model.trace.State;

import fr.inria.diverse.trace.commons.model.trace.Value;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link base.States.SpecificState#getStartedSteps <em>Started Steps</em>}</li>
 *   <li>{@link base.States.SpecificState#getEndedSteps <em>Ended Steps</em>}</li>
 * </ul>
 *
 * @see base.States.StatesPackage#getSpecificState()
 * @model
 * @generated
 */
public interface SpecificState extends State {
	/**
	 * Returns the value of the '<em><b>Started Steps</b></em>' reference list.
	 * The list contents are of type {@link base.Steps.SpecificStep}.
	 * It is bidirectional and its opposite is '{@link base.Steps.SpecificStep#getStartingState <em>Starting State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Started Steps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Started Steps</em>' reference list.
	 * @see base.States.StatesPackage#getSpecificState_StartedSteps()
	 * @see base.Steps.SpecificStep#getStartingState
	 * @model opposite="startingState"
	 * @generated
	 */
	EList<SpecificStep> getStartedSteps();

	/**
	 * Returns the value of the '<em><b>Ended Steps</b></em>' reference list.
	 * The list contents are of type {@link base.Steps.SpecificStep}.
	 * It is bidirectional and its opposite is '{@link base.Steps.SpecificStep#getEndingState <em>Ending State</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ended Steps</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ended Steps</em>' reference list.
	 * @see base.States.StatesPackage#getSpecificState_EndedSteps()
	 * @see base.Steps.SpecificStep#getEndingState
	 * @model opposite="endingState"
	 * @generated
	 */
	EList<SpecificStep> getEndedSteps();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Value> getValuesView();

} // SpecificState
