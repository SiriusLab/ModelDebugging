/**
 */
package fr.inria.diverse.event.commons.model.arbiter;

import fr.inria.diverse.event.commons.model.property.Property;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.State#getTruthValue <em>Truth Value</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.State#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.State#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getState()
 * @model abstract="true"
 * @generated
 */
public interface State<P extends Property, T extends Transition<P, ?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Truth Value</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.arbiter.TruthValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Truth Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Truth Value</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.arbiter.TruthValue
	 * @see #setTruthValue(TruthValue)
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getState_TruthValue()
	 * @model required="true"
	 * @generated
	 */
	TruthValue getTruthValue();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.arbiter.State#getTruthValue <em>Truth Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Truth Value</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.arbiter.TruthValue
	 * @see #getTruthValue()
	 * @generated
	 */
	void setTruthValue(TruthValue value);

	/**
	 * Returns the value of the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Transitions</em>' reference list.
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getState_OutgoingTransitions()
	 * @see fr.inria.diverse.event.commons.model.arbiter.Transition#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<T> getOutgoingTransitions();

	/**
	 * Returns the value of the '<em><b>Incoming Transitions</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link fr.inria.diverse.event.commons.model.arbiter.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Transitions</em>' reference list.
	 * @see fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage#getState_IncomingTransitions()
	 * @see fr.inria.diverse.event.commons.model.arbiter.Transition#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<T> getIncomingTransitions();

} // State
