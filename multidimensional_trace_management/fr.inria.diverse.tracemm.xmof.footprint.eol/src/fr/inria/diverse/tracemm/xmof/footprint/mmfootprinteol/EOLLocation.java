/**
 */
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>EOL Location</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getLine
 * <em>Line</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getColumn
 * <em>Column</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStartIndex
 * <em>Token Start Index</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStopIndex
 * <em>Token Stop Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#getEOLLocation()
 * @model
 * @generated
 */
public interface EOLLocation extends Location {
	/**
	 * Returns the value of the '<em><b>Line</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Line</em>' attribute.
	 * @see #setLine(int)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#getEOLLocation_Line()
	 * @model required="true"
	 * @generated
	 */
	int getLine();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getLine
	 * <em>Line</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Line</em>' attribute.
	 * @see #getLine()
	 * @generated
	 */
	void setLine(int value);

	/**
	 * Returns the value of the '<em><b>Column</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Column</em>' attribute.
	 * @see #setColumn(int)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#getEOLLocation_Column()
	 * @model required="true"
	 * @generated
	 */
	int getColumn();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getColumn
	 * <em>Column</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Column</em>' attribute.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(int value);

	/**
	 * Returns the value of the '<em><b>Token Start Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Token Start Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Token Start Index</em>' attribute.
	 * @see #setTokenStartIndex(int)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#getEOLLocation_TokenStartIndex()
	 * @model required="true"
	 * @generated
	 */
	int getTokenStartIndex();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStartIndex
	 * <em>Token Start Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Token Start Index</em>' attribute.
	 * @see #getTokenStartIndex()
	 * @generated
	 */
	void setTokenStartIndex(int value);

	/**
	 * Returns the value of the '<em><b>Token Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Token Stop Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Token Stop Index</em>' attribute.
	 * @see #setTokenStopIndex(int)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.MmfootprinteolPackage#getEOLLocation_TokenStopIndex()
	 * @model required="true"
	 * @generated
	 */
	int getTokenStopIndex();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.EOLLocation#getTokenStopIndex
	 * <em>Token Stop Index</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Token Stop Index</em>' attribute.
	 * @see #getTokenStopIndex()
	 * @generated
	 */
	void setTokenStopIndex(int value);

} // EOLLocation
