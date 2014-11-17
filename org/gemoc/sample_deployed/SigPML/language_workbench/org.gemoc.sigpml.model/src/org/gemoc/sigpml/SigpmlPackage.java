/**
 */
package org.gemoc.sigpml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.gemoc.sigpml.SigpmlFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface SigpmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sigpml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.gemoc.sigpml.model/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sigpml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SigpmlPackage eINSTANCE = org.gemoc.sigpml.impl.SigpmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.NamedElementImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.ApplicationImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owned Agents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__OWNED_AGENTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned Places</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__OWNED_PLACES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.AgentImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owned Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__OWNED_PORTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cycles</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__CYCLES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__OWNER = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__CODE = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Allocated To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ALLOCATED_TO = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.PortImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__OWNER = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__RATE = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Byte Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__BYTE_RATE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.InputPortImpl <em>Input Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.InputPortImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getInputPort()
	 * @generated
	 */
	int INPUT_PORT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT__NAME = PORT__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT__OWNER = PORT__OWNER;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT__RATE = PORT__RATE;

	/**
	 * The feature id for the '<em><b>Byte Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT__BYTE_RATE = PORT__BYTE_RATE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT__TYPE = PORT__TYPE;

	/**
	 * The number of structural features of the '<em>Input Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Input Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PORT_OPERATION_COUNT = PORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.OutputPortImpl <em>Output Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.OutputPortImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getOutputPort()
	 * @generated
	 */
	int OUTPUT_PORT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT__NAME = PORT__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT__OWNER = PORT__OWNER;

	/**
	 * The feature id for the '<em><b>Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT__RATE = PORT__RATE;

	/**
	 * The feature id for the '<em><b>Byte Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT__BYTE_RATE = PORT__BYTE_RATE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT__TYPE = PORT__TYPE;

	/**
	 * The number of structural features of the '<em>Output Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Output Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PORT_OPERATION_COUNT = PORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.PlaceImpl <em>Place</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.PlaceImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getPlace()
	 * @generated
	 */
	int PLACE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Its Output Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__ITS_OUTPUT_PORT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Its Input Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__ITS_INPUT_PORT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__SIZE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__OWNER = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Byte Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__BYTE_SIZE = NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE__DELAY = NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Place</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Place</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.HWRessourceImpl <em>HW Ressource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.HWRessourceImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWRessource()
	 * @generated
	 */
	int HW_RESSOURCE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_RESSOURCE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_RESSOURCE__OWNER = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connected To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_RESSOURCE__CONNECTED_TO = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>HW Ressource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_RESSOURCE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>HW Ressource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_RESSOURCE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.HWComputationalResourceImpl <em>HW Computational Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.HWComputationalResourceImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWComputationalResource()
	 * @generated
	 */
	int HW_COMPUTATIONAL_RESOURCE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE__NAME = HW_RESSOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE__OWNER = HW_RESSOURCE__OWNER;

	/**
	 * The feature id for the '<em><b>Connected To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE__CONNECTED_TO = HW_RESSOURCE__CONNECTED_TO;

	/**
	 * The feature id for the '<em><b>Is Under Preemptive Management</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT = HW_RESSOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Allocated Agents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS = HW_RESSOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>HW Computational Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE_FEATURE_COUNT = HW_RESSOURCE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>HW Computational Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMPUTATIONAL_RESOURCE_OPERATION_COUNT = HW_RESSOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.HWStorageResourceImpl <em>HW Storage Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.HWStorageResourceImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWStorageResource()
	 * @generated
	 */
	int HW_STORAGE_RESOURCE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE__NAME = HW_RESSOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE__OWNER = HW_RESSOURCE__OWNER;

	/**
	 * The feature id for the '<em><b>Connected To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE__CONNECTED_TO = HW_RESSOURCE__CONNECTED_TO;

	/**
	 * The feature id for the '<em><b>Allocated Places</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE__ALLOCATED_PLACES = HW_RESSOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>HW Storage Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE_FEATURE_COUNT = HW_RESSOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>HW Storage Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_STORAGE_RESOURCE_OPERATION_COUNT = HW_RESSOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.HWCommunicationResourceImpl <em>HW Communication Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.HWCommunicationResourceImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWCommunicationResource()
	 * @generated
	 */
	int HW_COMMUNICATION_RESOURCE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMMUNICATION_RESOURCE__NAME = HW_RESSOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMMUNICATION_RESOURCE__OWNER = HW_RESSOURCE__OWNER;

	/**
	 * The feature id for the '<em><b>Connected To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMMUNICATION_RESOURCE__CONNECTED_TO = HW_RESSOURCE__CONNECTED_TO;

	/**
	 * The number of structural features of the '<em>HW Communication Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMMUNICATION_RESOURCE_FEATURE_COUNT = HW_RESSOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>HW Communication Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_COMMUNICATION_RESOURCE_OPERATION_COUNT = HW_RESSOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.HWPlatformImpl <em>HW Platform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.HWPlatformImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWPlatform()
	 * @generated
	 */
	int HW_PLATFORM = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_PLATFORM__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owned HW Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_PLATFORM__OWNED_HW_RESOURCES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>HW Platform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_PLATFORM_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>HW Platform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HW_PLATFORM_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.impl.SystemImpl <em>System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.impl.SystemImpl
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getSystem()
	 * @generated
	 */
	int SYSTEM = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Owned Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_APPLICATION = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owned HW Platform</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM__OWNED_HW_PLATFORM = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.sigpml.sizeType <em>size Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.sigpml.sizeType
	 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getsizeType()
	 * @generated
	 */
	int SIZE_TYPE = 13;


	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see org.gemoc.sigpml.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.sigpml.Application#getOwnedAgents <em>Owned Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Agents</em>'.
	 * @see org.gemoc.sigpml.Application#getOwnedAgents()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_OwnedAgents();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.sigpml.Application#getOwnedPlaces <em>Owned Places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Places</em>'.
	 * @see org.gemoc.sigpml.Application#getOwnedPlaces()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_OwnedPlaces();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see org.gemoc.sigpml.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.sigpml.Agent#getOwnedPorts <em>Owned Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Ports</em>'.
	 * @see org.gemoc.sigpml.Agent#getOwnedPorts()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_OwnedPorts();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Agent#getCycles <em>Cycles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cycles</em>'.
	 * @see org.gemoc.sigpml.Agent#getCycles()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Cycles();

	/**
	 * Returns the meta object for the container reference '{@link org.gemoc.sigpml.Agent#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.gemoc.sigpml.Agent#getOwner()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Owner();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Agent#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.gemoc.sigpml.Agent#getCode()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Code();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.sigpml.Agent#getAllocatedTo <em>Allocated To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Allocated To</em>'.
	 * @see org.gemoc.sigpml.Agent#getAllocatedTo()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_AllocatedTo();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see org.gemoc.sigpml.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the container reference '{@link org.gemoc.sigpml.Port#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.gemoc.sigpml.Port#getOwner()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Owner();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Port#getRate <em>Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rate</em>'.
	 * @see org.gemoc.sigpml.Port#getRate()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Rate();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Port#getByteRate <em>Byte Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Byte Rate</em>'.
	 * @see org.gemoc.sigpml.Port#getByteRate()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_ByteRate();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Port#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.gemoc.sigpml.Port#getType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Type();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.InputPort <em>Input Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Port</em>'.
	 * @see org.gemoc.sigpml.InputPort
	 * @generated
	 */
	EClass getInputPort();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.OutputPort <em>Output Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Port</em>'.
	 * @see org.gemoc.sigpml.OutputPort
	 * @generated
	 */
	EClass getOutputPort();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.Place <em>Place</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Place</em>'.
	 * @see org.gemoc.sigpml.Place
	 * @generated
	 */
	EClass getPlace();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.sigpml.Place#getItsOutputPort <em>Its Output Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Its Output Port</em>'.
	 * @see org.gemoc.sigpml.Place#getItsOutputPort()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_ItsOutputPort();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.sigpml.Place#getItsInputPort <em>Its Input Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Its Input Port</em>'.
	 * @see org.gemoc.sigpml.Place#getItsInputPort()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_ItsInputPort();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Place#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.gemoc.sigpml.Place#getSize()
	 * @see #getPlace()
	 * @generated
	 */
	EAttribute getPlace_Size();

	/**
	 * Returns the meta object for the container reference '{@link org.gemoc.sigpml.Place#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.gemoc.sigpml.Place#getOwner()
	 * @see #getPlace()
	 * @generated
	 */
	EReference getPlace_Owner();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Place#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.gemoc.sigpml.Place#getType()
	 * @see #getPlace()
	 * @generated
	 */
	EAttribute getPlace_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Place#getByteSize <em>Byte Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Byte Size</em>'.
	 * @see org.gemoc.sigpml.Place#getByteSize()
	 * @see #getPlace()
	 * @generated
	 */
	EAttribute getPlace_ByteSize();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.Place#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see org.gemoc.sigpml.Place#getDelay()
	 * @see #getPlace()
	 * @generated
	 */
	EAttribute getPlace_Delay();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.gemoc.sigpml.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gemoc.sigpml.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.HWRessource <em>HW Ressource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HW Ressource</em>'.
	 * @see org.gemoc.sigpml.HWRessource
	 * @generated
	 */
	EClass getHWRessource();

	/**
	 * Returns the meta object for the container reference '{@link org.gemoc.sigpml.HWRessource#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.gemoc.sigpml.HWRessource#getOwner()
	 * @see #getHWRessource()
	 * @generated
	 */
	EReference getHWRessource_Owner();

	/**
	 * Returns the meta object for the reference list '{@link org.gemoc.sigpml.HWRessource#getConnectedTo <em>Connected To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connected To</em>'.
	 * @see org.gemoc.sigpml.HWRessource#getConnectedTo()
	 * @see #getHWRessource()
	 * @generated
	 */
	EReference getHWRessource_ConnectedTo();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.HWComputationalResource <em>HW Computational Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HW Computational Resource</em>'.
	 * @see org.gemoc.sigpml.HWComputationalResource
	 * @generated
	 */
	EClass getHWComputationalResource();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.sigpml.HWComputationalResource#isIsUnderPreemptiveManagement <em>Is Under Preemptive Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Under Preemptive Management</em>'.
	 * @see org.gemoc.sigpml.HWComputationalResource#isIsUnderPreemptiveManagement()
	 * @see #getHWComputationalResource()
	 * @generated
	 */
	EAttribute getHWComputationalResource_IsUnderPreemptiveManagement();

	/**
	 * Returns the meta object for the reference list '{@link org.gemoc.sigpml.HWComputationalResource#getAllocatedAgents <em>Allocated Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Agents</em>'.
	 * @see org.gemoc.sigpml.HWComputationalResource#getAllocatedAgents()
	 * @see #getHWComputationalResource()
	 * @generated
	 */
	EReference getHWComputationalResource_AllocatedAgents();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.HWStorageResource <em>HW Storage Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HW Storage Resource</em>'.
	 * @see org.gemoc.sigpml.HWStorageResource
	 * @generated
	 */
	EClass getHWStorageResource();

	/**
	 * Returns the meta object for the reference list '{@link org.gemoc.sigpml.HWStorageResource#getAllocatedPlaces <em>Allocated Places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Places</em>'.
	 * @see org.gemoc.sigpml.HWStorageResource#getAllocatedPlaces()
	 * @see #getHWStorageResource()
	 * @generated
	 */
	EReference getHWStorageResource_AllocatedPlaces();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.HWCommunicationResource <em>HW Communication Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HW Communication Resource</em>'.
	 * @see org.gemoc.sigpml.HWCommunicationResource
	 * @generated
	 */
	EClass getHWCommunicationResource();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.HWPlatform <em>HW Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HW Platform</em>'.
	 * @see org.gemoc.sigpml.HWPlatform
	 * @generated
	 */
	EClass getHWPlatform();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.sigpml.HWPlatform#getOwnedHWResources <em>Owned HW Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned HW Resources</em>'.
	 * @see org.gemoc.sigpml.HWPlatform#getOwnedHWResources()
	 * @see #getHWPlatform()
	 * @generated
	 */
	EReference getHWPlatform_OwnedHWResources();

	/**
	 * Returns the meta object for class '{@link org.gemoc.sigpml.System <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System</em>'.
	 * @see org.gemoc.sigpml.System
	 * @generated
	 */
	EClass getSystem();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.sigpml.System#getOwnedApplication <em>Owned Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Application</em>'.
	 * @see org.gemoc.sigpml.System#getOwnedApplication()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_OwnedApplication();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.sigpml.System#getOwnedHWPlatform <em>Owned HW Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned HW Platform</em>'.
	 * @see org.gemoc.sigpml.System#getOwnedHWPlatform()
	 * @see #getSystem()
	 * @generated
	 */
	EReference getSystem_OwnedHWPlatform();

	/**
	 * Returns the meta object for enum '{@link org.gemoc.sigpml.sizeType <em>size Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>size Type</em>'.
	 * @see org.gemoc.sigpml.sizeType
	 * @generated
	 */
	EEnum getsizeType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SigpmlFactory getSigpmlFactory();

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
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.ApplicationImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Owned Agents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__OWNED_AGENTS = eINSTANCE.getApplication_OwnedAgents();

		/**
		 * The meta object literal for the '<em><b>Owned Places</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__OWNED_PLACES = eINSTANCE.getApplication_OwnedPlaces();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.AgentImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Owned Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__OWNED_PORTS = eINSTANCE.getAgent_OwnedPorts();

		/**
		 * The meta object literal for the '<em><b>Cycles</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__CYCLES = eINSTANCE.getAgent_Cycles();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__OWNER = eINSTANCE.getAgent_Owner();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__CODE = eINSTANCE.getAgent_Code();

		/**
		 * The meta object literal for the '<em><b>Allocated To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__ALLOCATED_TO = eINSTANCE.getAgent_AllocatedTo();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.PortImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__OWNER = eINSTANCE.getPort_Owner();

		/**
		 * The meta object literal for the '<em><b>Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__RATE = eINSTANCE.getPort_Rate();

		/**
		 * The meta object literal for the '<em><b>Byte Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__BYTE_RATE = eINSTANCE.getPort_ByteRate();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__TYPE = eINSTANCE.getPort_Type();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.InputPortImpl <em>Input Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.InputPortImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getInputPort()
		 * @generated
		 */
		EClass INPUT_PORT = eINSTANCE.getInputPort();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.OutputPortImpl <em>Output Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.OutputPortImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getOutputPort()
		 * @generated
		 */
		EClass OUTPUT_PORT = eINSTANCE.getOutputPort();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.PlaceImpl <em>Place</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.PlaceImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getPlace()
		 * @generated
		 */
		EClass PLACE = eINSTANCE.getPlace();

		/**
		 * The meta object literal for the '<em><b>Its Output Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__ITS_OUTPUT_PORT = eINSTANCE.getPlace_ItsOutputPort();

		/**
		 * The meta object literal for the '<em><b>Its Input Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__ITS_INPUT_PORT = eINSTANCE.getPlace_ItsInputPort();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE__SIZE = eINSTANCE.getPlace_Size();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLACE__OWNER = eINSTANCE.getPlace_Owner();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE__TYPE = eINSTANCE.getPlace_Type();

		/**
		 * The meta object literal for the '<em><b>Byte Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE__BYTE_SIZE = eINSTANCE.getPlace_ByteSize();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE__DELAY = eINSTANCE.getPlace_Delay();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.NamedElementImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.HWRessourceImpl <em>HW Ressource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.HWRessourceImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWRessource()
		 * @generated
		 */
		EClass HW_RESSOURCE = eINSTANCE.getHWRessource();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HW_RESSOURCE__OWNER = eINSTANCE.getHWRessource_Owner();

		/**
		 * The meta object literal for the '<em><b>Connected To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HW_RESSOURCE__CONNECTED_TO = eINSTANCE.getHWRessource_ConnectedTo();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.HWComputationalResourceImpl <em>HW Computational Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.HWComputationalResourceImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWComputationalResource()
		 * @generated
		 */
		EClass HW_COMPUTATIONAL_RESOURCE = eINSTANCE.getHWComputationalResource();

		/**
		 * The meta object literal for the '<em><b>Is Under Preemptive Management</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HW_COMPUTATIONAL_RESOURCE__IS_UNDER_PREEMPTIVE_MANAGEMENT = eINSTANCE.getHWComputationalResource_IsUnderPreemptiveManagement();

		/**
		 * The meta object literal for the '<em><b>Allocated Agents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HW_COMPUTATIONAL_RESOURCE__ALLOCATED_AGENTS = eINSTANCE.getHWComputationalResource_AllocatedAgents();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.HWStorageResourceImpl <em>HW Storage Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.HWStorageResourceImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWStorageResource()
		 * @generated
		 */
		EClass HW_STORAGE_RESOURCE = eINSTANCE.getHWStorageResource();

		/**
		 * The meta object literal for the '<em><b>Allocated Places</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HW_STORAGE_RESOURCE__ALLOCATED_PLACES = eINSTANCE.getHWStorageResource_AllocatedPlaces();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.HWCommunicationResourceImpl <em>HW Communication Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.HWCommunicationResourceImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWCommunicationResource()
		 * @generated
		 */
		EClass HW_COMMUNICATION_RESOURCE = eINSTANCE.getHWCommunicationResource();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.HWPlatformImpl <em>HW Platform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.HWPlatformImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getHWPlatform()
		 * @generated
		 */
		EClass HW_PLATFORM = eINSTANCE.getHWPlatform();

		/**
		 * The meta object literal for the '<em><b>Owned HW Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HW_PLATFORM__OWNED_HW_RESOURCES = eINSTANCE.getHWPlatform_OwnedHWResources();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.impl.SystemImpl <em>System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.impl.SystemImpl
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getSystem()
		 * @generated
		 */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
		 * The meta object literal for the '<em><b>Owned Application</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__OWNED_APPLICATION = eINSTANCE.getSystem_OwnedApplication();

		/**
		 * The meta object literal for the '<em><b>Owned HW Platform</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM__OWNED_HW_PLATFORM = eINSTANCE.getSystem_OwnedHWPlatform();

		/**
		 * The meta object literal for the '{@link org.gemoc.sigpml.sizeType <em>size Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.sigpml.sizeType
		 * @see org.gemoc.sigpml.impl.SigpmlPackageImpl#getsizeType()
		 * @generated
		 */
		EEnum SIZE_TYPE = eINSTANCE.getsizeType();

	}

} //SigpmlPackage
