/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.impl.LocationImpl;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EOL Location</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl#getLine
 * <em>Line</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl#getColumn
 * <em>Column</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl#getTokenStartIndex
 * <em>Token Start Index</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl#getTokenStopIndex
 * <em>Token Stop Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EOLLocationImpl extends LocationImpl implements EOLLocation {
	/**
	 * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected int line = LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getColumn() <em>Column</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int COLUMN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected int column = COLUMN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTokenStartIndex()
	 * <em>Token Start Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTokenStartIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int TOKEN_START_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTokenStartIndex()
	 * <em>Token Start Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTokenStartIndex()
	 * @generated
	 * @ordered
	 */
	protected int tokenStartIndex = TOKEN_START_INDEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getTokenStopIndex()
	 * <em>Token Stop Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTokenStopIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int TOKEN_STOP_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTokenStopIndex()
	 * <em>Token Stop Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTokenStopIndex()
	 * @generated
	 * @ordered
	 */
	protected int tokenStopIndex = TOKEN_STOP_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EOLLocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MmfootprinteolPackage.Literals.EOL_LOCATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getLine() {
		return line;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLine(int newLine) {
		int oldLine = line;
		line = newLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprinteolPackage.EOL_LOCATION__LINE, oldLine,
					line));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setColumn(int newColumn) {
		int oldColumn = column;
		column = newColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprinteolPackage.EOL_LOCATION__COLUMN, oldColumn,
					column));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getTokenStartIndex() {
		return tokenStartIndex;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTokenStartIndex(int newTokenStartIndex) {
		int oldTokenStartIndex = tokenStartIndex;
		tokenStartIndex = newTokenStartIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprinteolPackage.EOL_LOCATION__TOKEN_START_INDEX,
					oldTokenStartIndex, tokenStartIndex));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getTokenStopIndex() {
		return tokenStopIndex;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTokenStopIndex(int newTokenStopIndex) {
		int oldTokenStopIndex = tokenStopIndex;
		tokenStopIndex = newTokenStopIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MmfootprinteolPackage.EOL_LOCATION__TOKEN_STOP_INDEX,
					oldTokenStopIndex, tokenStopIndex));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case MmfootprinteolPackage.EOL_LOCATION__LINE:
			return getLine();
		case MmfootprinteolPackage.EOL_LOCATION__COLUMN:
			return getColumn();
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_START_INDEX:
			return getTokenStartIndex();
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_STOP_INDEX:
			return getTokenStopIndex();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case MmfootprinteolPackage.EOL_LOCATION__LINE:
			setLine((Integer) newValue);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__COLUMN:
			setColumn((Integer) newValue);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_START_INDEX:
			setTokenStartIndex((Integer) newValue);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_STOP_INDEX:
			setTokenStopIndex((Integer) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case MmfootprinteolPackage.EOL_LOCATION__LINE:
			setLine(LINE_EDEFAULT);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__COLUMN:
			setColumn(COLUMN_EDEFAULT);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_START_INDEX:
			setTokenStartIndex(TOKEN_START_INDEX_EDEFAULT);
			return;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_STOP_INDEX:
			setTokenStopIndex(TOKEN_STOP_INDEX_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case MmfootprinteolPackage.EOL_LOCATION__LINE:
			return line != LINE_EDEFAULT;
		case MmfootprinteolPackage.EOL_LOCATION__COLUMN:
			return column != COLUMN_EDEFAULT;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_START_INDEX:
			return tokenStartIndex != TOKEN_START_INDEX_EDEFAULT;
		case MmfootprinteolPackage.EOL_LOCATION__TOKEN_STOP_INDEX:
			return tokenStopIndex != TOKEN_STOP_INDEX_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (line: ");
		result.append(line);
		result.append(", column: ");
		result.append(column);
		result.append(", tokenStartIndex: ");
		result.append(tokenStartIndex);
		result.append(", tokenStopIndex: ");
		result.append(tokenStopIndex);
		result.append(')');
		return result.toString();
	}

} // EOLLocationImpl
