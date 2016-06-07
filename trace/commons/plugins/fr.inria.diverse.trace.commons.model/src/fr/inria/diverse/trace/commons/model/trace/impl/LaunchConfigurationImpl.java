/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Launch Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getLanguageName <em>Language Name</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getAirdResourceURI <em>Aird Resource URI</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getMethodEntryPoint <em>Method Entry Point</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getModelEntryPoint <em>Model Entry Point</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getInitializationMethod <em>Initialization Method</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getInitializationArguments <em>Initialization Arguments</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getAddonExtensions <em>Addon Extensions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LaunchConfigurationImpl extends MinimalEObjectImpl.Container implements LaunchConfiguration {
	/**
	 * The default value of the '{@link #getLanguageName() <em>Language Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguageName()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguageName() <em>Language Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguageName()
	 * @generated
	 * @ordered
	 */
	protected String languageName = LANGUAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected String resourceURI = RESOURCE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getAirdResourceURI() <em>Aird Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAirdResourceURI()
	 * @generated
	 * @ordered
	 */
	protected static final String AIRD_RESOURCE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAirdResourceURI() <em>Aird Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAirdResourceURI()
	 * @generated
	 * @ordered
	 */
	protected String airdResourceURI = AIRD_RESOURCE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodEntryPoint() <em>Method Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodEntryPoint()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_ENTRY_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodEntryPoint() <em>Method Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodEntryPoint()
	 * @generated
	 * @ordered
	 */
	protected String methodEntryPoint = METHOD_ENTRY_POINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelEntryPoint() <em>Model Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelEntryPoint()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_ENTRY_POINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModelEntryPoint() <em>Model Entry Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelEntryPoint()
	 * @generated
	 * @ordered
	 */
	protected String modelEntryPoint = MODEL_ENTRY_POINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitializationMethod() <em>Initialization Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializationMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIALIZATION_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitializationMethod() <em>Initialization Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializationMethod()
	 * @generated
	 * @ordered
	 */
	protected String initializationMethod = INITIALIZATION_METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitializationArguments() <em>Initialization Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializationArguments()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIALIZATION_ARGUMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitializationArguments() <em>Initialization Arguments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializationArguments()
	 * @generated
	 * @ordered
	 */
	protected String initializationArguments = INITIALIZATION_ARGUMENTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAddonExtensions() <em>Addon Extensions</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddonExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<String> addonExtensions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LaunchConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.LAUNCH_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguageName(String newLanguageName) {
		String oldLanguageName = languageName;
		languageName = newLanguageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__LANGUAGE_NAME, oldLanguageName, languageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceURI(String newResourceURI) {
		String oldResourceURI = resourceURI;
		resourceURI = newResourceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__RESOURCE_URI, oldResourceURI, resourceURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAirdResourceURI() {
		return airdResourceURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAirdResourceURI(String newAirdResourceURI) {
		String oldAirdResourceURI = airdResourceURI;
		airdResourceURI = newAirdResourceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__AIRD_RESOURCE_URI, oldAirdResourceURI, airdResourceURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMethodEntryPoint() {
		return methodEntryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodEntryPoint(String newMethodEntryPoint) {
		String oldMethodEntryPoint = methodEntryPoint;
		methodEntryPoint = newMethodEntryPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__METHOD_ENTRY_POINT, oldMethodEntryPoint, methodEntryPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModelEntryPoint() {
		return modelEntryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelEntryPoint(String newModelEntryPoint) {
		String oldModelEntryPoint = modelEntryPoint;
		modelEntryPoint = newModelEntryPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__MODEL_ENTRY_POINT, oldModelEntryPoint, modelEntryPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitializationMethod() {
		return initializationMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializationMethod(String newInitializationMethod) {
		String oldInitializationMethod = initializationMethod;
		initializationMethod = newInitializationMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_METHOD, oldInitializationMethod, initializationMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitializationArguments() {
		return initializationArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializationArguments(String newInitializationArguments) {
		String oldInitializationArguments = initializationArguments;
		initializationArguments = newInitializationArguments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_ARGUMENTS, oldInitializationArguments, initializationArguments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAddonExtensions() {
		if (addonExtensions == null) {
			addonExtensions = new EDataTypeUniqueEList<String>(String.class, this, TracePackage.LAUNCH_CONFIGURATION__ADDON_EXTENSIONS);
		}
		return addonExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.LAUNCH_CONFIGURATION__LANGUAGE_NAME:
				return getLanguageName();
			case TracePackage.LAUNCH_CONFIGURATION__RESOURCE_URI:
				return getResourceURI();
			case TracePackage.LAUNCH_CONFIGURATION__AIRD_RESOURCE_URI:
				return getAirdResourceURI();
			case TracePackage.LAUNCH_CONFIGURATION__METHOD_ENTRY_POINT:
				return getMethodEntryPoint();
			case TracePackage.LAUNCH_CONFIGURATION__MODEL_ENTRY_POINT:
				return getModelEntryPoint();
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_METHOD:
				return getInitializationMethod();
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_ARGUMENTS:
				return getInitializationArguments();
			case TracePackage.LAUNCH_CONFIGURATION__ADDON_EXTENSIONS:
				return getAddonExtensions();
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
			case TracePackage.LAUNCH_CONFIGURATION__LANGUAGE_NAME:
				setLanguageName((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__RESOURCE_URI:
				setResourceURI((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__AIRD_RESOURCE_URI:
				setAirdResourceURI((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__METHOD_ENTRY_POINT:
				setMethodEntryPoint((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__MODEL_ENTRY_POINT:
				setModelEntryPoint((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_METHOD:
				setInitializationMethod((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_ARGUMENTS:
				setInitializationArguments((String)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__ADDON_EXTENSIONS:
				getAddonExtensions().clear();
				getAddonExtensions().addAll((Collection<? extends String>)newValue);
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
			case TracePackage.LAUNCH_CONFIGURATION__LANGUAGE_NAME:
				setLanguageName(LANGUAGE_NAME_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__RESOURCE_URI:
				setResourceURI(RESOURCE_URI_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__AIRD_RESOURCE_URI:
				setAirdResourceURI(AIRD_RESOURCE_URI_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__METHOD_ENTRY_POINT:
				setMethodEntryPoint(METHOD_ENTRY_POINT_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__MODEL_ENTRY_POINT:
				setModelEntryPoint(MODEL_ENTRY_POINT_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_METHOD:
				setInitializationMethod(INITIALIZATION_METHOD_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_ARGUMENTS:
				setInitializationArguments(INITIALIZATION_ARGUMENTS_EDEFAULT);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__ADDON_EXTENSIONS:
				getAddonExtensions().clear();
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
			case TracePackage.LAUNCH_CONFIGURATION__LANGUAGE_NAME:
				return LANGUAGE_NAME_EDEFAULT == null ? languageName != null : !LANGUAGE_NAME_EDEFAULT.equals(languageName);
			case TracePackage.LAUNCH_CONFIGURATION__RESOURCE_URI:
				return RESOURCE_URI_EDEFAULT == null ? resourceURI != null : !RESOURCE_URI_EDEFAULT.equals(resourceURI);
			case TracePackage.LAUNCH_CONFIGURATION__AIRD_RESOURCE_URI:
				return AIRD_RESOURCE_URI_EDEFAULT == null ? airdResourceURI != null : !AIRD_RESOURCE_URI_EDEFAULT.equals(airdResourceURI);
			case TracePackage.LAUNCH_CONFIGURATION__METHOD_ENTRY_POINT:
				return METHOD_ENTRY_POINT_EDEFAULT == null ? methodEntryPoint != null : !METHOD_ENTRY_POINT_EDEFAULT.equals(methodEntryPoint);
			case TracePackage.LAUNCH_CONFIGURATION__MODEL_ENTRY_POINT:
				return MODEL_ENTRY_POINT_EDEFAULT == null ? modelEntryPoint != null : !MODEL_ENTRY_POINT_EDEFAULT.equals(modelEntryPoint);
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_METHOD:
				return INITIALIZATION_METHOD_EDEFAULT == null ? initializationMethod != null : !INITIALIZATION_METHOD_EDEFAULT.equals(initializationMethod);
			case TracePackage.LAUNCH_CONFIGURATION__INITIALIZATION_ARGUMENTS:
				return INITIALIZATION_ARGUMENTS_EDEFAULT == null ? initializationArguments != null : !INITIALIZATION_ARGUMENTS_EDEFAULT.equals(initializationArguments);
			case TracePackage.LAUNCH_CONFIGURATION__ADDON_EXTENSIONS:
				return addonExtensions != null && !addonExtensions.isEmpty();
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
		result.append(" (languageName: ");
		result.append(languageName);
		result.append(", resourceURI: ");
		result.append(resourceURI);
		result.append(", airdResourceURI: ");
		result.append(airdResourceURI);
		result.append(", methodEntryPoint: ");
		result.append(methodEntryPoint);
		result.append(", modelEntryPoint: ");
		result.append(modelEntryPoint);
		result.append(", initializationMethod: ");
		result.append(initializationMethod);
		result.append(", initializationArguments: ");
		result.append(initializationArguments);
		result.append(", addonExtensions: ");
		result.append(addonExtensions);
		result.append(')');
		return result.toString();
	}

} //LaunchConfigurationImpl
