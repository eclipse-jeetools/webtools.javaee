/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapFactory
 * @generated
 */
public interface JaxrpcmapPackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "jaxrpcmap";//$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ibm.com/websphere/appserver/schemas/5.0.2/jaxrpcmap.xmi";//$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap";//$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  JaxrpcmapPackage eINSTANCE = org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.impl.JaxrpcmapPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaWSDLMappingImpl <em>Java WSDL Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaWSDLMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getJavaWSDLMapping()
   * @generated
   */
  int JAVA_WSDL_MAPPING = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Package Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING__PACKAGE_MAPPINGS = 1;

  /**
   * The feature id for the '<em><b>Java XML Type Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING__JAVA_XML_TYPE_MAPPINGS = 2;

  /**
   * The feature id for the '<em><b>Exception Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING__EXCEPTION_MAPPINGS = 3;

  /**
   * The feature id for the '<em><b>Interface Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING__INTERFACE_MAPPINGS = 4;

  /**
   * The number of structural features of the the '<em>Java WSDL Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_WSDL_MAPPING_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PackageMappingImpl <em>Package Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PackageMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getPackageMapping()
   * @generated
   */
  int PACKAGE_MAPPING = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Package Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_MAPPING__PACKAGE_TYPE = 1;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_MAPPING__NAMESPACE_URI = 2;

  /**
   * The number of structural features of the the '<em>Package Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_MAPPING_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl <em>Java XML Type Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getJavaXMLTypeMapping()
   * @generated
   */
  int JAVA_XML_TYPE_MAPPING = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__CLASS_TYPE = 1;

  /**
   * The feature id for the '<em><b>Qname Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__QNAME_SCOPE = 2;

  /**
   * The feature id for the '<em><b>Anonymous Type Qname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME = 3;

  /**
   * The feature id for the '<em><b>Root Type Qname</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME = 4;

  /**
   * The feature id for the '<em><b>Variable Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS = 5;

  /**
   * The number of structural features of the the '<em>Java XML Type Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_XML_TYPE_MAPPING_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ExceptionMappingImpl <em>Exception Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ExceptionMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getExceptionMapping()
   * @generated
   */
  int EXCEPTION_MAPPING = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Exception Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING__EXCEPTION_TYPE = 1;

  /**
   * The feature id for the '<em><b>Wsdl Message Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME = 2;

  /**
   * The feature id for the '<em><b>Wsdl Message</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING__WSDL_MESSAGE = 3;

  /**
   * The feature id for the '<em><b>Constructor Parameter Order</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER = 4;

  /**
   * The number of structural features of the the '<em>Exception Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_MAPPING_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.InterfaceMappingImpl <em>Interface Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.InterfaceMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getInterfaceMapping()
   * @generated
   */
  int INTERFACE_MAPPING = 33;

  /**
   * The number of structural features of the the '<em>Interface Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERFACE_MAPPING_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceInterfaceMappingImpl <em>Service Interface Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceInterfaceMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getServiceInterfaceMapping()
   * @generated
   */
  int SERVICE_INTERFACE_MAPPING = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_INTERFACE_MAPPING__ID = INTERFACE_MAPPING_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Service Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE = INTERFACE_MAPPING_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Wsdl Service Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME = INTERFACE_MAPPING_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Port Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS = INTERFACE_MAPPING_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the the '<em>Service Interface Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_INTERFACE_MAPPING_FEATURE_COUNT = INTERFACE_MAPPING_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointInterfaceMappingImpl <em>Service Endpoint Interface Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointInterfaceMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getServiceEndpointInterfaceMapping()
   * @generated
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING__ID = INTERFACE_MAPPING_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Service Endpoint Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE = INTERFACE_MAPPING_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Wsdl Port Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE = INTERFACE_MAPPING_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Wsdl Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING = INTERFACE_MAPPING_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Service Endpoint Method Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS = INTERFACE_MAPPING_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the the '<em>Service Endpoint Interface Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_INTERFACE_MAPPING_FEATURE_COUNT = INTERFACE_MAPPING_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PackageTypeImpl <em>Package Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PackageTypeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getPackageType()
   * @generated
   */
  int PACKAGE_TYPE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Package Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_TYPE__PACKAGE_TYPE = 1;

  /**
   * The number of structural features of the the '<em>Package Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ClassTypeImpl <em>Class Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ClassTypeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getClassType()
   * @generated
   */
  int CLASS_TYPE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_TYPE__CLASS_TYPE = 1;

  /**
   * The number of structural features of the the '<em>Class Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.RootTypeQnameImpl <em>Root Type Qname</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.RootTypeQnameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getRootTypeQname()
   * @generated
   */
  int ROOT_TYPE_QNAME = 8;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_TYPE_QNAME__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

  /**
   * The feature id for the '<em><b>Local Part</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_TYPE_QNAME__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

  /**
   * The feature id for the '<em><b>Combined QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_TYPE_QNAME__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

  /**
   * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_TYPE_QNAME__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

  /**
   * The number of structural features of the the '<em>Root Type Qname</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROOT_TYPE_QNAME_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.QnameScopeImpl <em>Qname Scope</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.QnameScopeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getQnameScope()
   * @generated
   */
  int QNAME_SCOPE = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QNAME_SCOPE__ID = 0;

  /**
   * The feature id for the '<em><b>Qname Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QNAME_SCOPE__QNAME_SCOPE = 1;

  /**
   * The number of structural features of the the '<em>Qname Scope</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QNAME_SCOPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.VariableMappingImpl <em>Variable Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.VariableMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getVariableMapping()
   * @generated
   */
  int VARIABLE_MAPPING = 10;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Java Variable Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__JAVA_VARIABLE_NAME = 1;

  /**
   * The feature id for the '<em><b>Xml Element Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__XML_ELEMENT_NAME = 2;

  /**
   * The feature id for the '<em><b>Data Member</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__DATA_MEMBER = 3;

  /**
   * The feature id for the '<em><b>Xml Attribute Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__XML_ATTRIBUTE_NAME = 4;

  /**
   * The feature id for the '<em><b>Xml Wildcard</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING__XML_WILDCARD = 5;

  /**
   * The number of structural features of the the '<em>Variable Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_MAPPING_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ExceptionTypeImpl <em>Exception Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ExceptionTypeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getExceptionType()
   * @generated
   */
  int EXCEPTION_TYPE = 11;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_TYPE__NAME = 1;

  /**
   * The number of structural features of the the '<em>Exception Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPTION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessageImpl <em>WSDL Message</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessageImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLMessage()
   * @generated
   */
  int WSDL_MESSAGE = 12;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

  /**
   * The feature id for the '<em><b>Local Part</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

  /**
   * The feature id for the '<em><b>Combined QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

  /**
   * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

  /**
   * The number of structural features of the the '<em>WSDL Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ConstructorParameterOrderImpl <em>Constructor Parameter Order</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ConstructorParameterOrderImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getConstructorParameterOrder()
   * @generated
   */
  int CONSTRUCTOR_PARAMETER_ORDER = 13;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRUCTOR_PARAMETER_ORDER__ID = 0;

  /**
   * The feature id for the '<em><b>Element Names</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES = 1;

  /**
   * The number of structural features of the the '<em>Constructor Parameter Order</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRUCTOR_PARAMETER_ORDER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaVariableNameImpl <em>Java Variable Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaVariableNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getJavaVariableName()
   * @generated
   */
  int JAVA_VARIABLE_NAME = 14;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_VARIABLE_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Java Variable Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_VARIABLE_NAME__JAVA_VARIABLE_NAME = 1;

  /**
   * The number of structural features of the the '<em>Java Variable Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_VARIABLE_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.XMLElementNameImpl <em>XML Element Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.XMLElementNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getXMLElementName()
   * @generated
   */
  int XML_ELEMENT_NAME = 15;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_ELEMENT_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Xml Element Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_ELEMENT_NAME__XML_ELEMENT_NAME = 1;

  /**
   * The number of structural features of the the '<em>XML Element Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XML_ELEMENT_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ElementNameImpl <em>Element Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ElementNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getElementName()
   * @generated
   */
  int ELEMENT_NAME = 16;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_NAME__TEXT = 1;

  /**
   * The number of structural features of the the '<em>Element Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLServiceNameImpl <em>WSDL Service Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLServiceNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLServiceName()
   * @generated
   */
  int WSDL_SERVICE_NAME = 17;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_SERVICE_NAME__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

  /**
   * The feature id for the '<em><b>Local Part</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_SERVICE_NAME__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

  /**
   * The feature id for the '<em><b>Combined QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_SERVICE_NAME__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

  /**
   * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_SERVICE_NAME__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

  /**
   * The number of structural features of the the '<em>WSDL Service Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_SERVICE_NAME_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PortMappingImpl <em>Port Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.PortMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getPortMapping()
   * @generated
   */
  int PORT_MAPPING = 18;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Port Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__PORT_NAME = 1;

  /**
   * The feature id for the '<em><b>Java Port Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__JAVA_PORT_NAME = 2;

  /**
   * The number of structural features of the the '<em>Port Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaPortNameImpl <em>Java Port Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaPortNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getJavaPortName()
   * @generated
   */
  int JAVA_PORT_NAME = 19;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_PORT_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Java Port Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_PORT_NAME__JAVA_PORT_NAME = 1;

  /**
   * The number of structural features of the the '<em>Java Port Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_PORT_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLPortTypeImpl <em>WSDL Port Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLPortTypeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLPortType()
   * @generated
   */
  int WSDL_PORT_TYPE = 20;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_PORT_TYPE__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

  /**
   * The feature id for the '<em><b>Local Part</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_PORT_TYPE__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

  /**
   * The feature id for the '<em><b>Combined QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_PORT_TYPE__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

  /**
   * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_PORT_TYPE__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

  /**
   * The number of structural features of the the '<em>WSDL Port Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_PORT_TYPE_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLBindingImpl <em>WSDL Binding</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLBindingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLBinding()
   * @generated
   */
  int WSDL_BINDING = 21;

  /**
   * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_BINDING__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

  /**
   * The feature id for the '<em><b>Local Part</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_BINDING__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

  /**
   * The feature id for the '<em><b>Combined QName</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_BINDING__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

  /**
   * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_BINDING__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

  /**
   * The number of structural features of the the '<em>WSDL Binding</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_BINDING_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl <em>Service Endpoint Method Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getServiceEndpointMethodMapping()
   * @generated
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING = 22;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Java Method Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME = 1;

  /**
   * The feature id for the '<em><b>Wsdl Operation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION = 2;

  /**
   * The feature id for the '<em><b>Wrapped Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT = 3;

  /**
   * The feature id for the '<em><b>Method Param Parts Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS = 4;

  /**
   * The feature id for the '<em><b>Wsdl Return Value Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING = 5;

  /**
   * The number of structural features of the the '<em>Service Endpoint Method Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SERVICE_ENDPOINT_METHOD_MAPPING_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaMethodNameImpl <em>Java Method Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaMethodNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getJavaMethodName()
   * @generated
   */
  int JAVA_METHOD_NAME = 23;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_METHOD_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Java Method Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_METHOD_NAME__JAVA_METHOD_NAME = 1;

  /**
   * The number of structural features of the the '<em>Java Method Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_METHOD_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLOperationImpl <em>WSDL Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLOperationImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLOperation()
   * @generated
   */
  int WSDL_OPERATION = 24;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_OPERATION__ID = 0;

  /**
   * The feature id for the '<em><b>Wsdl Operation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_OPERATION__WSDL_OPERATION = 1;

  /**
   * The number of structural features of the the '<em>WSDL Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_OPERATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl <em>Method Param Parts Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getMethodParamPartsMapping()
   * @generated
   */
  int METHOD_PARAM_PARTS_MAPPING = 25;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_PARAM_PARTS_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Param Position</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION = 1;

  /**
   * The feature id for the '<em><b>Param Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE = 2;

  /**
   * The feature id for the '<em><b>Wsdl Message Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING = 3;

  /**
   * The number of structural features of the the '<em>Method Param Parts Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_PARAM_PARTS_MAPPING_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLReturnValueMappingImpl <em>WSDL Return Value Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLReturnValueMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLReturnValueMapping()
   * @generated
   */
  int WSDL_RETURN_VALUE_MAPPING = 26;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_RETURN_VALUE_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Method Return Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE = 1;

  /**
   * The feature id for the '<em><b>Wsdl Message Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME = 2;

  /**
   * The feature id for the '<em><b>Wsdl Message</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE = 3;

  /**
   * The number of structural features of the the '<em>WSDL Return Value Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_RETURN_VALUE_MAPPING_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamPositionImpl <em>Param Position</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamPositionImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getParamPosition()
   * @generated
   */
  int PARAM_POSITION = 27;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_POSITION__ID = 0;

  /**
   * The feature id for the '<em><b>Param Position</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_POSITION__PARAM_POSITION = 1;

  /**
   * The number of structural features of the the '<em>Param Position</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_POSITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamTypeImpl <em>Param Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamTypeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getParamType()
   * @generated
   */
  int PARAM_TYPE = 28;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Param Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_TYPE__PARAM_TYPE = 1;

  /**
   * The number of structural features of the the '<em>Param Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessageMappingImpl <em>WSDL Message Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessageMappingImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLMessageMapping()
   * @generated
   */
  int WSDL_MESSAGE_MAPPING = 29;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING__ID = 0;

  /**
   * The feature id for the '<em><b>Wsdl Message Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING__WSDL_MESSAGE_PART_NAME = 1;

  /**
   * The feature id for the '<em><b>Parameter Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING__PARAMETER_MODE = 2;

  /**
   * The feature id for the '<em><b>Soap Header</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING__SOAP_HEADER = 3;

  /**
   * The feature id for the '<em><b>Wsdl Message</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING__WSDL_MESSAGE = 4;

  /**
   * The number of structural features of the the '<em>WSDL Message Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_MAPPING_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessagePartNameImpl <em>WSDL Message Part Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.WSDLMessagePartNameImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getWSDLMessagePartName()
   * @generated
   */
  int WSDL_MESSAGE_PART_NAME = 30;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_PART_NAME__ID = 0;

  /**
   * The feature id for the '<em><b>Wsdl Message Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME = 1;

  /**
   * The number of structural features of the the '<em>WSDL Message Part Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WSDL_MESSAGE_PART_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParameterModeImpl <em>Parameter Mode</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParameterModeImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getParameterMode()
   * @generated
   */
  int PARAMETER_MODE = 31;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_MODE__ID = 0;

  /**
   * The feature id for the '<em><b>Parameter Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_MODE__PARAMETER_MODE = 1;

  /**
   * The number of structural features of the the '<em>Parameter Mode</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_MODE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodReturnValueImpl <em>Method Return Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodReturnValueImpl
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JaxrpcmapPackageImpl#getMethodReturnValue()
   * @generated
   */
  int METHOD_RETURN_VALUE = 32;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_RETURN_VALUE__ID = 0;

  /**
   * The feature id for the '<em><b>Method Return Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_RETURN_VALUE__METHOD_RETURN_VALUE = 1;

  /**
   * The number of structural features of the the '<em>Method Return Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_RETURN_VALUE_FEATURE_COUNT = 2;


  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping <em>Java WSDL Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java WSDL Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping
   * @generated
   */
  EClass getJavaWSDLMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getId()
   * @see #getJavaWSDLMapping()
   * @generated
   */
  EAttribute getJavaWSDLMapping_Id();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getPackageMappings <em>Package Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Package Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getPackageMappings()
   * @see #getJavaWSDLMapping()
   * @generated
   */
  EReference getJavaWSDLMapping_PackageMappings();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getJavaXMLTypeMappings <em>Java XML Type Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Java XML Type Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getJavaXMLTypeMappings()
   * @see #getJavaWSDLMapping()
   * @generated
   */
  EReference getJavaWSDLMapping_JavaXMLTypeMappings();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getExceptionMappings <em>Exception Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exception Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getExceptionMappings()
   * @see #getJavaWSDLMapping()
   * @generated
   */
  EReference getJavaWSDLMapping_ExceptionMappings();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getInterfaceMappings <em>Interface Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Interface Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaWSDLMapping#getInterfaceMappings()
   * @see #getJavaWSDLMapping()
   * @generated
   */
  EReference getJavaWSDLMapping_InterfaceMappings();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping <em>Package Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping
   * @generated
   */
  EClass getPackageMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getId()
   * @see #getPackageMapping()
   * @generated
   */
  EAttribute getPackageMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getPackageType <em>Package Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Package Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getPackageType()
   * @see #getPackageMapping()
   * @generated
   */
  EAttribute getPackageMapping_PackageType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getNamespaceURI <em>Namespace URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Namespace URI</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageMapping#getNamespaceURI()
   * @see #getPackageMapping()
   * @generated
   */
  EAttribute getPackageMapping_NamespaceURI();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping <em>Java XML Type Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java XML Type Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping
   * @generated
   */
  EClass getJavaXMLTypeMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getId()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EAttribute getJavaXMLTypeMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getClassType()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EAttribute getJavaXMLTypeMapping_ClassType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getQnameScope <em>Qname Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Qname Scope</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getQnameScope()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EAttribute getJavaXMLTypeMapping_QnameScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getAnonymousTypeQname <em>Anonymous Type Qname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Anonymous Type Qname</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getAnonymousTypeQname()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EAttribute getJavaXMLTypeMapping_AnonymousTypeQname();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getRootTypeQname <em>Root Type Qname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Root Type Qname</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getRootTypeQname()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EReference getJavaXMLTypeMapping_RootTypeQname();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getVariableMappings <em>Variable Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaXMLTypeMapping#getVariableMappings()
   * @see #getJavaXMLTypeMapping()
   * @generated
   */
  EReference getJavaXMLTypeMapping_VariableMappings();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping <em>Exception Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exception Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping
   * @generated
   */
  EClass getExceptionMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getId()
   * @see #getExceptionMapping()
   * @generated
   */
  EAttribute getExceptionMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getExceptionType <em>Exception Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exception Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getExceptionType()
   * @see #getExceptionMapping()
   * @generated
   */
  EAttribute getExceptionMapping_ExceptionType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Message Part Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessagePartName()
   * @see #getExceptionMapping()
   * @generated
   */
  EAttribute getExceptionMapping_WsdlMessagePartName();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessage <em>Wsdl Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Message</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessage()
   * @see #getExceptionMapping()
   * @generated
   */
  EReference getExceptionMapping_WsdlMessage();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getConstructorParameterOrder <em>Constructor Parameter Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constructor Parameter Order</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionMapping#getConstructorParameterOrder()
   * @see #getExceptionMapping()
   * @generated
   */
  EReference getExceptionMapping_ConstructorParameterOrder();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping <em>Service Interface Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Service Interface Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping
   * @generated
   */
  EClass getServiceInterfaceMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getId()
   * @see #getServiceInterfaceMapping()
   * @generated
   */
  EAttribute getServiceInterfaceMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getServiceInterface <em>Service Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Service Interface</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getServiceInterface()
   * @see #getServiceInterfaceMapping()
   * @generated
   */
  EAttribute getServiceInterfaceMapping_ServiceInterface();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getWsdlServiceName <em>Wsdl Service Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Service Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getWsdlServiceName()
   * @see #getServiceInterfaceMapping()
   * @generated
   */
  EReference getServiceInterfaceMapping_WsdlServiceName();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getPortMappings <em>Port Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Port Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getPortMappings()
   * @see #getServiceInterfaceMapping()
   * @generated
   */
  EReference getServiceInterfaceMapping_PortMappings();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping <em>Service Endpoint Interface Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Service Endpoint Interface Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping
   * @generated
   */
  EClass getServiceEndpointInterfaceMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getId()
   * @see #getServiceEndpointInterfaceMapping()
   * @generated
   */
  EAttribute getServiceEndpointInterfaceMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointInterface <em>Service Endpoint Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Service Endpoint Interface</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointInterface()
   * @see #getServiceEndpointInterfaceMapping()
   * @generated
   */
  EAttribute getServiceEndpointInterfaceMapping_ServiceEndpointInterface();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlPortType <em>Wsdl Port Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Port Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlPortType()
   * @see #getServiceEndpointInterfaceMapping()
   * @generated
   */
  EReference getServiceEndpointInterfaceMapping_WsdlPortType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlBinding <em>Wsdl Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Binding</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlBinding()
   * @see #getServiceEndpointInterfaceMapping()
   * @generated
   */
  EReference getServiceEndpointInterfaceMapping_WsdlBinding();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointMethodMappings <em>Service Endpoint Method Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Service Endpoint Method Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointMethodMappings()
   * @see #getServiceEndpointInterfaceMapping()
   * @generated
   */
  EReference getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType <em>Package Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType
   * @generated
   */
  EClass getPackageType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType#getId()
   * @see #getPackageType()
   * @generated
   */
  EAttribute getPackageType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType#getPackageType <em>Package Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Package Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PackageType#getPackageType()
   * @see #getPackageType()
   * @generated
   */
  EAttribute getPackageType_PackageType();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType
   * @generated
   */
  EClass getClassType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType#getId()
   * @see #getClassType()
   * @generated
   */
  EAttribute getClassType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ClassType#getClassType()
   * @see #getClassType()
   * @generated
   */
  EAttribute getClassType_ClassType();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.RootTypeQname <em>Root Type Qname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Root Type Qname</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.RootTypeQname
   * @generated
   */
  EClass getRootTypeQname();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope <em>Qname Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Qname Scope</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope
   * @generated
   */
  EClass getQnameScope();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope#getId()
   * @see #getQnameScope()
   * @generated
   */
  EAttribute getQnameScope_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope#getQnameScope <em>Qname Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Qname Scope</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.QnameScope#getQnameScope()
   * @see #getQnameScope()
   * @generated
   */
  EAttribute getQnameScope_QnameScope();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping <em>Variable Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping
   * @generated
   */
  EClass getVariableMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getId()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getJavaVariableName <em>Java Variable Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Variable Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getJavaVariableName()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_JavaVariableName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getXmlElementName <em>Xml Element Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xml Element Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getXmlElementName()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_XmlElementName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#isDataMember <em>Data Member</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Member</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#isDataMember()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_DataMember();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getXmlAttributeName <em>Xml Attribute Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xml Attribute Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#getXmlAttributeName()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_XmlAttributeName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#isXmlWildcard <em>Xml Wildcard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xml Wildcard</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.VariableMapping#isXmlWildcard()
   * @see #getVariableMapping()
   * @generated
   */
  EAttribute getVariableMapping_XmlWildcard();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType <em>Exception Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exception Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType
   * @generated
   */
  EClass getExceptionType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType#getId()
   * @see #getExceptionType()
   * @generated
   */
  EAttribute getExceptionType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ExceptionType#getName()
   * @see #getExceptionType()
   * @generated
   */
  EAttribute getExceptionType_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessage <em>WSDL Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Message</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessage
   * @generated
   */
  EClass getWSDLMessage();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder <em>Constructor Parameter Order</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constructor Parameter Order</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder
   * @generated
   */
  EClass getConstructorParameterOrder();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder#getId()
   * @see #getConstructorParameterOrder()
   * @generated
   */
  EAttribute getConstructorParameterOrder_Id();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder#getElementNames <em>Element Names</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Element Names</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ConstructorParameterOrder#getElementNames()
   * @see #getConstructorParameterOrder()
   * @generated
   */
  EReference getConstructorParameterOrder_ElementNames();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName <em>Java Variable Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java Variable Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName
   * @generated
   */
  EClass getJavaVariableName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName#getId()
   * @see #getJavaVariableName()
   * @generated
   */
  EAttribute getJavaVariableName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName#getJavaVariableName <em>Java Variable Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Variable Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaVariableName#getJavaVariableName()
   * @see #getJavaVariableName()
   * @generated
   */
  EAttribute getJavaVariableName_JavaVariableName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName <em>XML Element Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XML Element Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName
   * @generated
   */
  EClass getXMLElementName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getId()
   * @see #getXMLElementName()
   * @generated
   */
  EAttribute getXMLElementName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getXmlElementName <em>Xml Element Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xml Element Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getXmlElementName()
   * @see #getXMLElementName()
   * @generated
   */
  EAttribute getXMLElementName_XmlElementName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName <em>Element Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName
   * @generated
   */
  EClass getElementName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName#getId()
   * @see #getElementName()
   * @generated
   */
  EAttribute getElementName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName#getText <em>Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ElementName#getText()
   * @see #getElementName()
   * @generated
   */
  EAttribute getElementName_Text();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLServiceName <em>WSDL Service Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Service Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLServiceName
   * @generated
   */
  EClass getWSDLServiceName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping <em>Port Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping
   * @generated
   */
  EClass getPortMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getId()
   * @see #getPortMapping()
   * @generated
   */
  EAttribute getPortMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getPortName <em>Port Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Port Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getPortName()
   * @see #getPortMapping()
   * @generated
   */
  EAttribute getPortMapping_PortName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getJavaPortName <em>Java Port Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Port Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping#getJavaPortName()
   * @see #getPortMapping()
   * @generated
   */
  EAttribute getPortMapping_JavaPortName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName <em>Java Port Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java Port Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName
   * @generated
   */
  EClass getJavaPortName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName#getId()
   * @see #getJavaPortName()
   * @generated
   */
  EAttribute getJavaPortName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName#getJavaPortName <em>Java Port Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Port Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaPortName#getJavaPortName()
   * @see #getJavaPortName()
   * @generated
   */
  EAttribute getJavaPortName_JavaPortName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLPortType <em>WSDL Port Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Port Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLPortType
   * @generated
   */
  EClass getWSDLPortType();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLBinding <em>WSDL Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Binding</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLBinding
   * @generated
   */
  EClass getWSDLBinding();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping <em>Service Endpoint Method Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Service Endpoint Method Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping
   * @generated
   */
  EClass getServiceEndpointMethodMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getId()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EAttribute getServiceEndpointMethodMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getJavaMethodName <em>Java Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Method Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getJavaMethodName()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EAttribute getServiceEndpointMethodMapping_JavaMethodName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlOperation <em>Wsdl Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Operation</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlOperation()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EAttribute getServiceEndpointMethodMapping_WsdlOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement <em>Wrapped Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wrapped Element</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EAttribute getServiceEndpointMethodMapping_WrappedElement();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getMethodParamPartsMappings <em>Method Param Parts Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Method Param Parts Mappings</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getMethodParamPartsMappings()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EReference getServiceEndpointMethodMapping_MethodParamPartsMappings();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlReturnValueMapping <em>Wsdl Return Value Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Return Value Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlReturnValueMapping()
   * @see #getServiceEndpointMethodMapping()
   * @generated
   */
  EReference getServiceEndpointMethodMapping_WsdlReturnValueMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName <em>Java Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java Method Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName
   * @generated
   */
  EClass getJavaMethodName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName#getId()
   * @see #getJavaMethodName()
   * @generated
   */
  EAttribute getJavaMethodName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName#getJavaMethodName <em>Java Method Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Java Method Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JavaMethodName#getJavaMethodName()
   * @see #getJavaMethodName()
   * @generated
   */
  EAttribute getJavaMethodName_JavaMethodName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation <em>WSDL Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Operation</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation
   * @generated
   */
  EClass getWSDLOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation#getId()
   * @see #getWSDLOperation()
   * @generated
   */
  EAttribute getWSDLOperation_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation#getWsdlOperation <em>Wsdl Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Operation</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLOperation#getWsdlOperation()
   * @see #getWSDLOperation()
   * @generated
   */
  EAttribute getWSDLOperation_WsdlOperation();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping <em>Method Param Parts Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Method Param Parts Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping
   * @generated
   */
  EClass getMethodParamPartsMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getId()
   * @see #getMethodParamPartsMapping()
   * @generated
   */
  EAttribute getMethodParamPartsMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getParamPosition <em>Param Position</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param Position</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getParamPosition()
   * @see #getMethodParamPartsMapping()
   * @generated
   */
  EAttribute getMethodParamPartsMapping_ParamPosition();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getParamType <em>Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getParamType()
   * @see #getMethodParamPartsMapping()
   * @generated
   */
  EAttribute getMethodParamPartsMapping_ParamType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getWsdlMessageMapping <em>Wsdl Message Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Message Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodParamPartsMapping#getWsdlMessageMapping()
   * @see #getMethodParamPartsMapping()
   * @generated
   */
  EReference getMethodParamPartsMapping_WsdlMessageMapping();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping <em>WSDL Return Value Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Return Value Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping
   * @generated
   */
  EClass getWSDLReturnValueMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getId()
   * @see #getWSDLReturnValueMapping()
   * @generated
   */
  EAttribute getWSDLReturnValueMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getMethodReturnValue <em>Method Return Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Method Return Value</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getMethodReturnValue()
   * @see #getWSDLReturnValueMapping()
   * @generated
   */
  EAttribute getWSDLReturnValueMapping_MethodReturnValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Message Part Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessagePartName()
   * @see #getWSDLReturnValueMapping()
   * @generated
   */
  EAttribute getWSDLReturnValueMapping_WsdlMessagePartName();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessage <em>Wsdl Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Message</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessage()
   * @see #getWSDLReturnValueMapping()
   * @generated
   */
  EReference getWSDLReturnValueMapping_WsdlMessage();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition <em>Param Position</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Param Position</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition
   * @generated
   */
  EClass getParamPosition();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition#getId()
   * @see #getParamPosition()
   * @generated
   */
  EAttribute getParamPosition_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition#getParamPosition <em>Param Position</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param Position</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamPosition#getParamPosition()
   * @see #getParamPosition()
   * @generated
   */
  EAttribute getParamPosition_ParamPosition();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType <em>Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Param Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType
   * @generated
   */
  EClass getParamType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getId()
   * @see #getParamType()
   * @generated
   */
  EAttribute getParamType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getParamType <em>Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Param Type</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getParamType()
   * @see #getParamType()
   * @generated
   */
  EAttribute getParamType_ParamType();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping <em>WSDL Message Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Message Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping
   * @generated
   */
  EClass getWSDLMessageMapping();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getId()
   * @see #getWSDLMessageMapping()
   * @generated
   */
  EAttribute getWSDLMessageMapping_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Message Part Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getWsdlMessagePartName()
   * @see #getWSDLMessageMapping()
   * @generated
   */
  EAttribute getWSDLMessageMapping_WsdlMessagePartName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getParameterMode <em>Parameter Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Parameter Mode</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getParameterMode()
   * @see #getWSDLMessageMapping()
   * @generated
   */
  EAttribute getWSDLMessageMapping_ParameterMode();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getWsdlMessage <em>Wsdl Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Wsdl Message</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#getWsdlMessage()
   * @see #getWSDLMessageMapping()
   * @generated
   */
  EReference getWSDLMessageMapping_WsdlMessage();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#isSoapHeader <em>Soap Header</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Soap Header</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessageMapping#isSoapHeader()
   * @see #getWSDLMessageMapping()
   * @generated
   */
  EAttribute getWSDLMessageMapping_SoapHeader();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName <em>WSDL Message Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>WSDL Message Part Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName
   * @generated
   */
  EClass getWSDLMessagePartName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName#getId()
   * @see #getWSDLMessagePartName()
   * @generated
   */
  EAttribute getWSDLMessagePartName_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wsdl Message Part Name</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.WSDLMessagePartName#getWsdlMessagePartName()
   * @see #getWSDLMessagePartName()
   * @generated
   */
  EAttribute getWSDLMessagePartName_WsdlMessagePartName();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode <em>Parameter Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Mode</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode
   * @generated
   */
  EClass getParameterMode();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getId()
   * @see #getParameterMode()
   * @generated
   */
  EAttribute getParameterMode_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getParameterMode <em>Parameter Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Parameter Mode</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getParameterMode()
   * @see #getParameterMode()
   * @generated
   */
  EAttribute getParameterMode_ParameterMode();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue <em>Method Return Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Method Return Value</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue
   * @generated
   */
  EClass getMethodReturnValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue#getId()
   * @see #getMethodReturnValue()
   * @generated
   */
  EAttribute getMethodReturnValue_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue#getMethodReturnValue <em>Method Return Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Method Return Value</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.MethodReturnValue#getMethodReturnValue()
   * @see #getMethodReturnValue()
   * @generated
   */
  EAttribute getMethodReturnValue_MethodReturnValue();

  /**
   * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.InterfaceMapping <em>Interface Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interface Mapping</em>'.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.InterfaceMapping
   * @generated
   */
  EClass getInterfaceMapping();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  JaxrpcmapFactory getJaxrpcmapFactory();

} //JaxrpcmapPackage
