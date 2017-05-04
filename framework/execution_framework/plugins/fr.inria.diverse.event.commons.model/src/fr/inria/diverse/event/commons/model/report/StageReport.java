/**
 */
package fr.inria.diverse.event.commons.model.report;

import fr.inria.diverse.event.commons.model.scenario.Stage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.StageReport#getStage <em>Stage</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.StageReport#getMatches <em>Matches</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.StageReport#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getStageReport()
 * @model
 * @generated
 */
public interface StageReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Stage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' reference.
	 * @see #setStage(Stage)
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getStageReport_Stage()
	 * @model
	 * @generated
	 */
	Stage<?, ?> getStage();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.report.StageReport#getStage <em>Stage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' reference.
	 * @see #getStage()
	 * @generated
	 */
	void setStage(Stage<?, ?> value);

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
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getStageReport_Matches()
	 * @model
	 * @generated
	 */
	EList<EObject> getMatches();

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.event.commons.model.report.EventReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getStageReport_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventReport> getEvents();

} // StageReport
