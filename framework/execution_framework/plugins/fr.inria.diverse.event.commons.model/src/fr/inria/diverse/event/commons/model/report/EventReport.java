/**
 */
package fr.inria.diverse.event.commons.model.report;

import fr.inria.diverse.event.commons.model.scenario.Event;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.EventReport#getEvent <em>Event</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.EventReport#getMatches <em>Matches</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.EventReport#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getEventReport()
 * @model
 * @generated
 */
public interface EventReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' reference.
	 * @see #setEvent(Event)
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getEventReport_Event()
	 * @model
	 * @generated
	 */
	Event<?> getEvent();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.report.EventReport#getEvent <em>Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event<?> value);

	/**
	 * Returns the value of the '<em><b>Matches</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.diverse.event.commons.model.report.EventParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matches</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matches</em>' reference list.
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getEventReport_Matches()
	 * @model
	 * @generated
	 */
	EList<EventParameter> getMatches();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(EObject)
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getEventReport_Target()
	 * @model
	 * @generated
	 */
	EObject getTarget();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.report.EventReport#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(EObject value);

} // EventReport
