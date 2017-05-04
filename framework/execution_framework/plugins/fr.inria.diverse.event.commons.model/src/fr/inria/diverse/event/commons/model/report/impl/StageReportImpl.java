/**
 */
package fr.inria.diverse.event.commons.model.report.impl;

import fr.inria.diverse.event.commons.model.report.EventReport;
import fr.inria.diverse.event.commons.model.report.ReportPackage;
import fr.inria.diverse.event.commons.model.report.StageReport;

import fr.inria.diverse.event.commons.model.scenario.Stage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stage Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.impl.StageReportImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.impl.StageReportImpl#getMatches <em>Matches</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.report.impl.StageReportImpl#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StageReportImpl extends MinimalEObjectImpl.Container implements StageReport {
	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected Stage<?, ?> stage;

	/**
	 * The cached value of the '{@link #getMatches() <em>Matches</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatches()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> matches;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<EventReport> events;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReportPackage.Literals.STAGE_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stage<?, ?> getStage() {
		if (stage != null && stage.eIsProxy()) {
			InternalEObject oldStage = (InternalEObject)stage;
			stage = (Stage<?, ?>)eResolveProxy(oldStage);
			if (stage != oldStage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReportPackage.STAGE_REPORT__STAGE, oldStage, stage));
			}
		}
		return stage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stage<?, ?> basicGetStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStage(Stage<?, ?> newStage) {
		Stage<?, ?> oldStage = stage;
		stage = newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReportPackage.STAGE_REPORT__STAGE, oldStage, stage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getMatches() {
		if (matches == null) {
			matches = new EObjectResolvingEList<EObject>(EObject.class, this, ReportPackage.STAGE_REPORT__MATCHES);
		}
		return matches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventReport> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<EventReport>(EventReport.class, this, ReportPackage.STAGE_REPORT__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReportPackage.STAGE_REPORT__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReportPackage.STAGE_REPORT__STAGE:
				if (resolve) return getStage();
				return basicGetStage();
			case ReportPackage.STAGE_REPORT__MATCHES:
				return getMatches();
			case ReportPackage.STAGE_REPORT__EVENTS:
				return getEvents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReportPackage.STAGE_REPORT__STAGE:
				setStage((Stage<?, ?>)newValue);
				return;
			case ReportPackage.STAGE_REPORT__MATCHES:
				getMatches().clear();
				getMatches().addAll((Collection<? extends EObject>)newValue);
				return;
			case ReportPackage.STAGE_REPORT__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends EventReport>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ReportPackage.STAGE_REPORT__STAGE:
				setStage((Stage<?, ?>)null);
				return;
			case ReportPackage.STAGE_REPORT__MATCHES:
				getMatches().clear();
				return;
			case ReportPackage.STAGE_REPORT__EVENTS:
				getEvents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ReportPackage.STAGE_REPORT__STAGE:
				return stage != null;
			case ReportPackage.STAGE_REPORT__MATCHES:
				return matches != null && !matches.isEmpty();
			case ReportPackage.STAGE_REPORT__EVENTS:
				return events != null && !events.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StageReportImpl
