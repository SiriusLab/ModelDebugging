/**
 */
package fr.inria.diverse.event.commons.model.report;

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
 *   <li>{@link fr.inria.diverse.event.commons.model.report.Report#getStages <em>Stages</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getReport()
 * @model
 * @generated
 */
public interface Report extends EObject {
	/**
	 * Returns the value of the '<em><b>Stages</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.event.commons.model.report.StageReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stages</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.report.ReportPackage#getReport_Stages()
	 * @model containment="true"
	 * @generated
	 */
	EList<StageReport> getStages();

} // Report
