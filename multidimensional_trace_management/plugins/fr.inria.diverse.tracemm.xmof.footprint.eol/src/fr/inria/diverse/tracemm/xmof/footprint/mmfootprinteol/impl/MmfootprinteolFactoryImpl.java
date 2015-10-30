/**
 */
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.impl;

import fr.inria.diverse.tracemm.xmof.footprint.mmfootprinteol.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MmfootprinteolFactoryImpl extends EFactoryImpl implements MmfootprinteolFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static MmfootprinteolFactory init() {
		try {
			MmfootprinteolFactory theMmfootprinteolFactory = (MmfootprinteolFactory) EPackage.Registry.INSTANCE
					.getEFactory(MmfootprinteolPackage.eNS_URI);
			if (theMmfootprinteolFactory != null) {
				return theMmfootprinteolFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MmfootprinteolFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprinteolFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case MmfootprinteolPackage.EOL_LOCATION:
			return createEOLLocation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EOLLocation createEOLLocation() {
		EOLLocationImpl eolLocation = new EOLLocationImpl();
		return eolLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MmfootprinteolPackage getMmfootprinteolPackage() {
		return (MmfootprinteolPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MmfootprinteolPackage getPackage() {
		return MmfootprinteolPackage.eINSTANCE;
	}

} // MmfootprinteolFactoryImpl
