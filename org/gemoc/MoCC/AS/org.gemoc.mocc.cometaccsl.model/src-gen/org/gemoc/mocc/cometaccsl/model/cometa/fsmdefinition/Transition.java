/**
 * Copyright (c) 2012-2016 GEMOC consortium.
 * 
 * http://www.gemoc.org
 * 
 * Contributors:
 *   Stephen Creff - ENSTA Bretagne [stephen.creff@ensta-bretagne.fr]
 *   
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * $Id$
 */
package org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition;

import org.eclipse.emf.common.util.EList;

import org.gemoc.mocc.cometaccsl.model.cometa.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The concept of Transition is used to describe the relationships between the various states of the control FSM. They are used to define relations of succession and precedence between the control states. Specifically, Cometa transitions define references source, target to denote the < source > states and < target > states for a given transition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getSource <em>Source</em>}</li>
 *   <li>{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getTarget <em>Target</em>}</li>
 *   <li>{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getActions <em>Actions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.State#getInputTransitions <em>Input Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(State)
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition_Source()
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.State#getInputTransitions
	 * @model opposite="inputTransitions" required="true"
	 * @generated
	 */
	State getSource();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(State value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.State#getOutputTransitions <em>Output Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(State)
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition_Target()
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.State#getOutputTransitions
	 * @model opposite="outputTransitions" required="true"
	 * @generated
	 */
	State getTarget();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(State value);

	/**
	 * Returns the value of the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The conditions on transitions are referenced as the "guard". A "guard" is placed on the transition expression and its evaluation is mandatory for the state change.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Guard</em>' containment reference.
	 * @see #setGuard(Guard)
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition_Guard()
	 * @model containment="true"
	 * @generated
	 */
	Guard getGuard();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(Guard value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transitions have "trigger" reference that is of type Event. The occurrence of an event called the "trigger" may involve state change during the control process if all additional conditions on the transition are verified.
	 * In the context of Cometa, occurrences of events that are "trigger" are MoCInterfaces (Send / Receive / Ack) and MoCEvents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(Trigger)
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition_Trigger()
	 * @model containment="true"
	 * @generated
	 */
	Trigger getTrigger();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Transition#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(Trigger value);

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action: The action function is defined for the State and Transition. In both cases, the function references the Block they integrate. In the notation of action, a stands for State or Transition. The Blocks define the sequences of instructions for internal and external communication. The internal communication is the communication between control FSM and external control is the answer provided to each requesting concurrent entity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see org.gemoc.mocc.cometaccsl.model.cometa.fsmdefinition.FsmdefinitionPackage#getTransition_Actions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getActions();

} // Transition
