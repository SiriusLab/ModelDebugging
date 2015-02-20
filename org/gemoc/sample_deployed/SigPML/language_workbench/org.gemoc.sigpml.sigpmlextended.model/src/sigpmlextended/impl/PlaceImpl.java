/**
 */
package sigpmlextended.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import sigpmlextended.Application;
import sigpmlextended.InputPort;
import sigpmlextended.OutputPort;
import sigpmlextended.Place;
import sigpmlextended.SigpmlextendedPackage;
import sigpmlextended.sizeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getItsOutputPort <em>Its Output Port</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getItsInputPort <em>Its Input Port</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getSize <em>Size</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getType <em>Type</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getByteSize <em>Byte Size</em>}</li>
 *   <li>{@link sigpmlextended.impl.PlaceImpl#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlaceImpl extends NamedElementImpl implements Place {
	/**
	 * The cached value of the '{@link #getItsOutputPort() <em>Its Output Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItsOutputPort()
	 * @generated
	 * @ordered
	 */
	protected OutputPort itsOutputPort;

	/**
	 * The cached value of the '{@link #getItsInputPort() <em>Its Input Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItsInputPort()
	 * @generated
	 * @ordered
	 */
	protected InputPort itsInputPort;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final sizeType TYPE_EDEFAULT = sizeType.B;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected sizeType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getByteSize() <em>Byte Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getByteSize()
	 * @generated
	 * @ordered
	 */
	protected static final int BYTE_SIZE_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final int DELAY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected int delay = DELAY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SigpmlextendedPackage.Literals.PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPort getItsOutputPort() {
		if (itsOutputPort != null && itsOutputPort.eIsProxy()) {
			InternalEObject oldItsOutputPort = (InternalEObject)itsOutputPort;
			itsOutputPort = (OutputPort)eResolveProxy(oldItsOutputPort);
			if (itsOutputPort != oldItsOutputPort) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT, oldItsOutputPort, itsOutputPort));
			}
		}
		return itsOutputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPort basicGetItsOutputPort() {
		return itsOutputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItsOutputPort(OutputPort newItsOutputPort) {
		OutputPort oldItsOutputPort = itsOutputPort;
		itsOutputPort = newItsOutputPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT, oldItsOutputPort, itsOutputPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPort getItsInputPort() {
		if (itsInputPort != null && itsInputPort.eIsProxy()) {
			InternalEObject oldItsInputPort = (InternalEObject)itsInputPort;
			itsInputPort = (InputPort)eResolveProxy(oldItsInputPort);
			if (itsInputPort != oldItsInputPort) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SigpmlextendedPackage.PLACE__ITS_INPUT_PORT, oldItsInputPort, itsInputPort));
			}
		}
		return itsInputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPort basicGetItsInputPort() {
		return itsInputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItsInputPort(InputPort newItsInputPort) {
		InputPort oldItsInputPort = itsInputPort;
		itsInputPort = newItsInputPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__ITS_INPUT_PORT, oldItsInputPort, itsInputPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Application getOwner() {
		if (eContainerFeatureID() != SigpmlextendedPackage.PLACE__OWNER) return null;
		return (Application)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(Application newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, SigpmlextendedPackage.PLACE__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(Application newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != SigpmlextendedPackage.PLACE__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, SigpmlextendedPackage.APPLICATION__OWNED_PLACES, Application.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public sizeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(sizeType newType) {
		sizeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getByteSize() {
		// TODO: implement this method to return the 'Byte Size' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByteSize(int newByteSize) {
		// TODO: implement this method to set the 'Byte Size' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelay(int newDelay) {
		int oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SigpmlextendedPackage.PLACE__DELAY, oldDelay, delay));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SigpmlextendedPackage.PLACE__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((Application)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SigpmlextendedPackage.PLACE__OWNER:
				return basicSetOwner(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SigpmlextendedPackage.PLACE__OWNER:
				return eInternalContainer().eInverseRemove(this, SigpmlextendedPackage.APPLICATION__OWNED_PLACES, Application.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT:
				if (resolve) return getItsOutputPort();
				return basicGetItsOutputPort();
			case SigpmlextendedPackage.PLACE__ITS_INPUT_PORT:
				if (resolve) return getItsInputPort();
				return basicGetItsInputPort();
			case SigpmlextendedPackage.PLACE__SIZE:
				return getSize();
			case SigpmlextendedPackage.PLACE__OWNER:
				return getOwner();
			case SigpmlextendedPackage.PLACE__TYPE:
				return getType();
			case SigpmlextendedPackage.PLACE__BYTE_SIZE:
				return getByteSize();
			case SigpmlextendedPackage.PLACE__DELAY:
				return getDelay();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT:
				setItsOutputPort((OutputPort)newValue);
				return;
			case SigpmlextendedPackage.PLACE__ITS_INPUT_PORT:
				setItsInputPort((InputPort)newValue);
				return;
			case SigpmlextendedPackage.PLACE__SIZE:
				setSize((Integer)newValue);
				return;
			case SigpmlextendedPackage.PLACE__OWNER:
				setOwner((Application)newValue);
				return;
			case SigpmlextendedPackage.PLACE__TYPE:
				setType((sizeType)newValue);
				return;
			case SigpmlextendedPackage.PLACE__BYTE_SIZE:
				setByteSize((Integer)newValue);
				return;
			case SigpmlextendedPackage.PLACE__DELAY:
				setDelay((Integer)newValue);
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
			case SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT:
				setItsOutputPort((OutputPort)null);
				return;
			case SigpmlextendedPackage.PLACE__ITS_INPUT_PORT:
				setItsInputPort((InputPort)null);
				return;
			case SigpmlextendedPackage.PLACE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case SigpmlextendedPackage.PLACE__OWNER:
				setOwner((Application)null);
				return;
			case SigpmlextendedPackage.PLACE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case SigpmlextendedPackage.PLACE__BYTE_SIZE:
				setByteSize(BYTE_SIZE_EDEFAULT);
				return;
			case SigpmlextendedPackage.PLACE__DELAY:
				setDelay(DELAY_EDEFAULT);
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
			case SigpmlextendedPackage.PLACE__ITS_OUTPUT_PORT:
				return itsOutputPort != null;
			case SigpmlextendedPackage.PLACE__ITS_INPUT_PORT:
				return itsInputPort != null;
			case SigpmlextendedPackage.PLACE__SIZE:
				return size != SIZE_EDEFAULT;
			case SigpmlextendedPackage.PLACE__OWNER:
				return getOwner() != null;
			case SigpmlextendedPackage.PLACE__TYPE:
				return type != TYPE_EDEFAULT;
			case SigpmlextendedPackage.PLACE__BYTE_SIZE:
				return getByteSize() != BYTE_SIZE_EDEFAULT;
			case SigpmlextendedPackage.PLACE__DELAY:
				return delay != DELAY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (size: ");
		result.append(size);
		result.append(", type: ");
		result.append(type);
		result.append(", delay: ");
		result.append(delay);
		result.append(')');
		return result.toString();
	}

} //PlaceImpl
