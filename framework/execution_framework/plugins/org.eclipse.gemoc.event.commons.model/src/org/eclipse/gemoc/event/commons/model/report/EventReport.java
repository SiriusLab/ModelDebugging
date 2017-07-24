/**
 */
package org.eclipse.gemoc.event.commons.model.report;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.event.commons.model.scenario.Event;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getEvent <em>Event</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getMatches <em>Matches</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getEventReport()
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
	 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getEventReport_Event()
	 * @model
	 * @generated
	 */
	Event<?> getEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getEvent <em>Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event<?> value);

	/**
	 * Returns the value of the '<em><b>Matches</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matches</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matches</em>' reference list.
	 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getEventReport_Matches()
	 * @model
	 * @generated
	 */
	EList<EObject> getMatches();

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
	 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getEventReport_Target()
	 * @model
	 * @generated
	 */
	EObject getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(EObject value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(int)
	 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getEventReport_Time()
	 * @model
	 * @generated
	 */
	int getTime();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.event.commons.model.report.EventReport#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(int value);

} // EventReport
