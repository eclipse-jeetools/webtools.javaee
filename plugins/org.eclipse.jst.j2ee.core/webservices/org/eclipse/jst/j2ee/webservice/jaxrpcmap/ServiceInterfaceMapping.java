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
 * A representation of the model object '<em><b>Service Interface Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getWsdlServiceName <em>Wsdl Service Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getPortMappings <em>Port Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceInterfaceMapping()
 * @model 
 * @generated
 */
public interface ServiceInterfaceMapping extends InterfaceMapping{
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
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceInterfaceMapping_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Service Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Service Interface</em>' attribute.
   * @see #setServiceInterface(String)
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceInterfaceMapping_ServiceInterface()
   * @model 
   * @generated
   */
  String getServiceInterface();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getServiceInterface <em>Service Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Service Interface</em>' attribute.
   * @see #getServiceInterface()
   * @generated
   */
  void setServiceInterface(String value);

  /**
   * Returns the value of the '<em><b>Wsdl Service Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Service Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wsdl Service Name</em>' containment reference.
   * @see #setWsdlServiceName(WSDLServiceName)
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceInterfaceMapping_WsdlServiceName()
   * @model containment="true" required="true"
   * @generated
   */
  WSDLServiceName getWsdlServiceName();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ServiceInterfaceMapping#getWsdlServiceName <em>Wsdl Service Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wsdl Service Name</em>' containment reference.
   * @see #getWsdlServiceName()
   * @generated
   */
  void setWsdlServiceName(WSDLServiceName value);

  /**
   * Returns the value of the '<em><b>Port Mappings</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Port Mappings</em>' containment reference list.
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceInterfaceMapping_PortMappings()
   * @model type="org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.PortMapping" containment="true"
   * @generated
   */
  EList getPortMappings();

} // ServiceInterfaceMapping
