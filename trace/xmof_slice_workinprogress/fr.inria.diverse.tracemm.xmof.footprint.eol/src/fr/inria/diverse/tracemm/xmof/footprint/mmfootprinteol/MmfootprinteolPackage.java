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
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.
 *      MmfootprinteolFactory
 * @model kind="package"
 * @generated
 */
public interface MmfootprinteolPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "mmfootprinteol";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://xmof/mmfootprint/eol/1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "mmfootprinteol";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MmfootprinteolPackage eINSTANCE = fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.MmfootprinteolPackageImpl
			.init();

	/**
	 * The meta object id for the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl
	 * <em>EOL Location</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.
	 *      EOLLocationImpl
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.MmfootprinteolPackageImpl#getEOLLocation()
	 * @generated
	 */
	int EOL_LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION__LINE = MmfootprintPackage.LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Column</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION__COLUMN = MmfootprintPackage.LOCATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Token Start Index</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION__TOKEN_START_INDEX = MmfootprintPackage.LOCATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Token Stop Index</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION__TOKEN_STOP_INDEX = MmfootprintPackage.LOCATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>EOL Location</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION_FEATURE_COUNT = MmfootprintPackage.LOCATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>EOL Location</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EOL_LOCATION_OPERATION_COUNT = MmfootprintPackage.LOCATION_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation
	 * <em>EOL Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EOL Location</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation
	 * @generated
	 */
	EClass getEOLLocation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getLine
	 * <em>Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getLine()
	 * @see #getEOLLocation()
	 * @generated
	 */
	EAttribute getEOLLocation_Line();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getColumn
	 * <em>Column</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Column</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getColumn()
	 * @see #getEOLLocation()
	 * @generated
	 */
	EAttribute getEOLLocation_Column();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStartIndex
	 * <em>Token Start Index</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Token Start Index</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStartIndex()
	 * @see #getEOLLocation()
	 * @generated
	 */
	EAttribute getEOLLocation_TokenStartIndex();

	/**
	 * Returns the meta object for the attribute '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStopIndex
	 * <em>Token Stop Index</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Token Stop Index</em>'.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStopIndex()
	 * @see #getEOLLocation()
	 * @generated
	 */
	EAttribute getEOLLocation_TokenStopIndex();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MmfootprinteolFactory getMmfootprinteolFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.EOLLocationImpl
		 * <em>EOL Location</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.
		 *      EOLLocationImpl
		 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl.MmfootprinteolPackageImpl#getEOLLocation()
		 * @generated
		 */
		EClass EOL_LOCATION = eINSTANCE.getEOLLocation();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOL_LOCATION__LINE = eINSTANCE.getEOLLocation_Line();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOL_LOCATION__COLUMN = eINSTANCE.getEOLLocation_Column();

		/**
		 * The meta object literal for the '<em><b>Token Start Index</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOL_LOCATION__TOKEN_START_INDEX = eINSTANCE.getEOLLocation_TokenStartIndex();

		/**
		 * The meta object literal for the '<em><b>Token Stop Index</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EOL_LOCATION__TOKEN_STOP_INDEX = eINSTANCE.getEOLLocation_TokenStopIndex();

	}

} // MmfootprinteolPackage
