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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage
 * @generated
 */
public interface JaxrpcmapFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  JaxrpcmapFactory eINSTANCE = new org.eclipse.jst.j2ee.webservice.jaxrpcmap.impl.JaxrpcmapFactoryImpl();

  /**
   * Returns a new object of class '<em>Java WSDL Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java WSDL Mapping</em>'.
   * @generated
   */
  JavaWSDLMapping createJavaWSDLMapping();

  /**
   * Returns a new object of class '<em>Package Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Package Mapping</em>'.
   * @generated
   */
  PackageMapping createPackageMapping();

  /**
   * Returns a new object of class '<em>Java XML Type Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java XML Type Mapping</em>'.
   * @generated
   */
  JavaXMLTypeMapping createJavaXMLTypeMapping();

  /**
   * Returns a new object of class '<em>Exception Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Exception Mapping</em>'.
   * @generated
   */
  ExceptionMapping createExceptionMapping();

  /**
   * Returns a new object of class '<em>Service Interface Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Service Interface Mapping</em>'.
   * @generated
   */
  ServiceInterfaceMapping createServiceInterfaceMapping();

  /**
   * Returns a new object of class '<em>Service Endpoint Interface Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Service Endpoint Interface Mapping</em>'.
   * @generated
   */
  ServiceEndpointInterfaceMapping createServiceEndpointInterfaceMapping();

  /**
   * Returns a new object of class '<em>Package Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Package Type</em>'.
   * @generated
   */
  PackageType createPackageType();

  /**
   * Returns a new object of class '<em>Class Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Type</em>'.
   * @generated
   */
  ClassType createClassType();

  /**
   * Returns a new object of class '<em>Root Type Qname</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Root Type Qname</em>'.
   * @generated
   */
  RootTypeQname createRootTypeQname();

  /**
   * Returns a new object of class '<em>Qname Scope</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Qname Scope</em>'.
   * @generated
   */
  QnameScope createQnameScope();

  /**
   * Returns a new object of class '<em>Variable Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable Mapping</em>'.
   * @generated
   */
  VariableMapping createVariableMapping();

  /**
   * Returns a new object of class '<em>Exception Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Exception Type</em>'.
   * @generated
   */
  ExceptionType createExceptionType();

  /**
   * Returns a new object of class '<em>WSDL Message</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Message</em>'.
   * @generated
   */
  WSDLMessage createWSDLMessage();

  /**
   * Returns a new object of class '<em>Constructor Parameter Order</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constructor Parameter Order</em>'.
   * @generated
   */
  ConstructorParameterOrder createConstructorParameterOrder();

  /**
   * Returns a new object of class '<em>Java Variable Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java Variable Name</em>'.
   * @generated
   */
  JavaVariableName createJavaVariableName();

  /**
   * Returns a new object of class '<em>XML Element Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XML Element Name</em>'.
   * @generated
   */
  XMLElementName createXMLElementName();

  /**
   * Returns a new object of class '<em>Element Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Name</em>'.
   * @generated
   */
  ElementName createElementName();

  /**
   * Returns a new object of class '<em>WSDL Service Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Service Name</em>'.
   * @generated
   */
  WSDLServiceName createWSDLServiceName();

  /**
   * Returns a new object of class '<em>Port Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Port Mapping</em>'.
   * @generated
   */
  PortMapping createPortMapping();

  /**
   * Returns a new object of class '<em>Java Port Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java Port Name</em>'.
   * @generated
   */
  JavaPortName createJavaPortName();

  /**
   * Returns a new object of class '<em>WSDL Port Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Port Type</em>'.
   * @generated
   */
  WSDLPortType createWSDLPortType();

  /**
   * Returns a new object of class '<em>WSDL Binding</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Binding</em>'.
   * @generated
   */
  WSDLBinding createWSDLBinding();

  /**
   * Returns a new object of class '<em>Service Endpoint Method Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Service Endpoint Method Mapping</em>'.
   * @generated
   */
  ServiceEndpointMethodMapping createServiceEndpointMethodMapping();

  /**
   * Returns a new object of class '<em>Java Method Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java Method Name</em>'.
   * @generated
   */
  JavaMethodName createJavaMethodName();

  /**
   * Returns a new object of class '<em>WSDL Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Operation</em>'.
   * @generated
   */
  WSDLOperation createWSDLOperation();

  /**
   * Returns a new object of class '<em>Method Param Parts Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Method Param Parts Mapping</em>'.
   * @generated
   */
  MethodParamPartsMapping createMethodParamPartsMapping();

  /**
   * Returns a new object of class '<em>WSDL Return Value Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Return Value Mapping</em>'.
   * @generated
   */
  WSDLReturnValueMapping createWSDLReturnValueMapping();

  /**
   * Returns a new object of class '<em>Param Position</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Param Position</em>'.
   * @generated
   */
  ParamPosition createParamPosition();

  /**
   * Returns a new object of class '<em>Param Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Param Type</em>'.
   * @generated
   */
  ParamType createParamType();

  /**
   * Returns a new object of class '<em>WSDL Message Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Message Mapping</em>'.
   * @generated
   */
  WSDLMessageMapping createWSDLMessageMapping();

  /**
   * Returns a new object of class '<em>WSDL Message Part Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>WSDL Message Part Name</em>'.
   * @generated
   */
  WSDLMessagePartName createWSDLMessagePartName();

  /**
   * Returns a new object of class '<em>Parameter Mode</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter Mode</em>'.
   * @generated
   */
  ParameterMode createParameterMode();

  /**
   * Returns a new object of class '<em>Method Return Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Method Return Value</em>'.
   * @generated
   */
  MethodReturnValue createMethodReturnValue();

  /**
   * Returns a new object of class '<em>Interface Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Interface Mapping</em>'.
   * @generated
   */
  InterfaceMapping createInterfaceMapping();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  JaxrpcmapPackage getJaxrpcmapPackage();

} //JaxrpcmapFactory
