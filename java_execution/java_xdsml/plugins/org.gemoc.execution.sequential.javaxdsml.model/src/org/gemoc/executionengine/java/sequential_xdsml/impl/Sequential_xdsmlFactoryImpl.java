/**
 */
package org.gemoc.executionengine.java.sequential_xdsml.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gemoc.executionengine.java.sequential_xdsml.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sequential_xdsmlFactoryImpl extends EFactoryImpl implements Sequential_xdsmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Sequential_xdsmlFactory init() {
		try {
			Sequential_xdsmlFactory theSequential_xdsmlFactory = (Sequential_xdsmlFactory)EPackage.Registry.INSTANCE.getEFactory(Sequential_xdsmlPackage.eNS_URI);
			if (theSequential_xdsmlFactory != null) {
				return theSequential_xdsmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Sequential_xdsmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequential_xdsmlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION: return createSequentialLanguageDefinition();
			case Sequential_xdsmlPackage.DSA_PROJECT: return createDSAProject();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequentialLanguageDefinition createSequentialLanguageDefinition() {
		SequentialLanguageDefinitionImpl sequentialLanguageDefinition = new SequentialLanguageDefinitionImpl();
		return sequentialLanguageDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSAProject createDSAProject() {
		DSAProjectImpl dsaProject = new DSAProjectImpl();
		return dsaProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequential_xdsmlPackage getSequential_xdsmlPackage() {
		return (Sequential_xdsmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Sequential_xdsmlPackage getPackage() {
		return Sequential_xdsmlPackage.eINSTANCE;
	}

} //Sequential_xdsmlFactoryImpl
