/**
 */
package org.eclipse.gemoc.event.commons.model.report;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.report.Report#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getReport()
 * @model
 * @generated
 */
public interface Report extends EObject {
	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.event.commons.model.report.EventReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see org.eclipse.gemoc.event.commons.model.report.ReportPackage#getReport_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventReport> getEvents();

} // Report
