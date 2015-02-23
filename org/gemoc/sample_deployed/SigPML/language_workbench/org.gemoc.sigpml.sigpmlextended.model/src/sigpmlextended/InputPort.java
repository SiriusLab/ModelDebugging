/**
 */
package sigpmlextended;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link sigpmlextended.InputPort#getSizeToread <em>Size Toread</em>}</li>
 * </ul>
 * </p>
 *
 * @see sigpmlextended.SigpmlextendedPackage#getInputPort()
 * @model
 * @generated
 */
public interface InputPort extends Port {
	/**
	 * Returns the value of the '<em><b>Size Toread</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Toread</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Toread</em>' attribute.
	 * @see #setSizeToread(int)
	 * @see sigpmlextended.SigpmlextendedPackage#getInputPort_SizeToread()
	 * @model
	 * @generated
	 */
	int getSizeToread();

	/**
	 * Sets the value of the '{@link sigpmlextended.InputPort#getSizeToread <em>Size Toread</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size Toread</em>' attribute.
	 * @see #getSizeToread()
	 * @generated
	 */
	void setSizeToread(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int read();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void logSizeToReadWhenZero();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void logSizeToReadWhenNotZero();

} // InputPort
