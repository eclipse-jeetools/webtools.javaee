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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Endpoint Interface Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlPortType <em>Wsdl Port Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlBinding <em>Wsdl Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointMethodMappings <em>Service Endpoint Method Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping()
 * @model 
 * @generated
 */
public interface ServiceEndpointInterfaceMapping extends InterfaceMapping{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Service Endpoint Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Endpoint Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Service Endpoint Interface</em>' attribute.
   * @see #setServiceEndpointInterface(String)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping_ServiceEndpointInterface()
   * @model 
   * @generated
   */
  String getServiceEndpointInterface();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getServiceEndpointInterface <em>Service Endpoint Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Service Endpoint Interface</em>' attribute.
   * @see #getServiceEndpointInterface()
   * @generated
   */
  void setServiceEndpointInterface(String value);

  /**
   * Returns the value of the '<em><b>Wsdl Port Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Port Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wsdl Port Type</em>' containment reference.
   * @see #setWsdlPortType(WSDLPortType)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping_WsdlPortType()
   * @model containment="true" required="true"
   * @generated
   */
  WSDLPortType getWsdlPortType();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlPortType <em>Wsdl Port Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wsdl Port Type</em>' containment reference.
   * @see #getWsdlPortType()
   * @generated
   */
  void setWsdlPortType(WSDLPortType value);

  /**
   * Returns the value of the '<em><b>Wsdl Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Binding</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wsdl Binding</em>' containment reference.
   * @see #setWsdlBinding(WSDLBinding)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping_WsdlBinding()
   * @model containment="true" required="true"
   * @generated
   */
  WSDLBinding getWsdlBinding();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping#getWsdlBinding <em>Wsdl Binding</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wsdl Binding</em>' containment reference.
   * @see #getWsdlBinding()
   * @generated
   */
  void setWsdlBinding(WSDLBinding value);

  /**
   * Returns the value of the '<em><b>Service Endpoint Method Mappings</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Endpoint Method Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Service Endpoint Method Mappings</em>' containment reference list.
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings()
   * @model type="org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping" containment="true"
   * @generated
   */
  EList getServiceEndpointMethodMappings();

} // ServiceEndpointInterfaceMapping
