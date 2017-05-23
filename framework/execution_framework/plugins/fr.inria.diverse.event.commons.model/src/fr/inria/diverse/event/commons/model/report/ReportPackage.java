/**
 */
package fr.inria.diverse.event.commons.model.report;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.event.commons.model.report.ReportFactory
 * @model kind="package"
 * @generated
 */
public interface ReportPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "report";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/report";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "report";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReportPackage eINSTANCE = fr.inria.diverse.event.commons.model.report.impl.ReportPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.report.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.report.impl.ReportImpl
	 * @see fr.inria.diverse.event.commons.model.report.impl.ReportPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 0;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__EVENTS = 0;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.inria.diverse.event.commons.model.report.impl.EventReportImpl <em>Event Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.inria.diverse.event.commons.model.report.impl.EventReportImpl
	 * @see fr.inria.diverse.event.commons.model.report.impl.ReportPackageImpl#getEventReport()
	 * @generated
	 */
	int EVENT_REPORT = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT__EVENT = 0;

	/**
	 * The feature id for the '<em><b>Matches</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT__MATCHES = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT__TARGET = 2;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT__TIME = 3;

	/**
	 * The number of structural features of the '<em>Event Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Event Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_REPORT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.report.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.inria.diverse.event.commons.model.report.Report#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.Report#getEvents()
	 * @see #getReport()
	 * @generated
	 */
	EReference getReport_Events();

	/**
	 * Returns the meta object for class '{@link fr.inria.diverse.event.commons.model.report.EventReport <em>Event Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Report</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.EventReport
	 * @generated
	 */
	EClass getEventReport();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.report.EventReport#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.EventReport#getEvent()
	 * @see #getEventReport()
	 * @generated
	 */
	EReference getEventReport_Event();

	/**
	 * Returns the meta object for the reference list '{@link fr.inria.diverse.event.commons.model.report.EventReport#getMatches <em>Matches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Matches</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.EventReport#getMatches()
	 * @see #getEventReport()
	 * @generated
	 */
	EReference getEventReport_Matches();

	/**
	 * Returns the meta object for the reference '{@link fr.inria.diverse.event.commons.model.report.EventReport#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.EventReport#getTarget()
	 * @see #getEventReport()
	 * @generated
	 */
	EReference getEventReport_Target();

	/**
	 * Returns the meta object for the attribute '{@link fr.inria.diverse.event.commons.model.report.EventReport#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see fr.inria.diverse.event.commons.model.report.EventReport#getTime()
	 * @see #getEventReport()
	 * @generated
	 */
	EAttribute getEventReport_Time();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReportFactory getReportFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.report.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.report.impl.ReportImpl
		 * @see fr.inria.diverse.event.commons.model.report.impl.ReportPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPORT__EVENTS = eINSTANCE.getReport_Events();

		/**
		 * The meta object literal for the '{@link fr.inria.diverse.event.commons.model.report.impl.EventReportImpl <em>Event Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.inria.diverse.event.commons.model.report.impl.EventReportImpl
		 * @see fr.inria.diverse.event.commons.model.report.impl.ReportPackageImpl#getEventReport()
		 * @generated
		 */
		EClass EVENT_REPORT = eINSTANCE.getEventReport();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_REPORT__EVENT = eINSTANCE.getEventReport_Event();

		/**
		 * The meta object literal for the '<em><b>Matches</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_REPORT__MATCHES = eINSTANCE.getEventReport_Matches();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_REPORT__TARGET = eINSTANCE.getEventReport_Target();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_REPORT__TIME = eINSTANCE.getEventReport_Time();

	}

} //ReportPackage
