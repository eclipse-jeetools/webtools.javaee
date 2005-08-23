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
package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The port-component element associates a WSDL port with a Web service interface and implementation.  It defines the name of the port as a component, optional description, optional display name, optional iconic representations, WSDL port QName, Service Endpoint Interface, Service Implementation Bean.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getPortComponentName <em>Port Component Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getWsdlPort <em>Wsdl Port</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getServiceImplBean <em>Service Impl Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDescriptionType <em>Description Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDisplayNameType <em>Display Name Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getIconType <em>Icon Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent()
 * @model
 * @generated
 */
public interface PortComponent extends EObject{
	/**
	 * Returns the value of the '<em><b>Port Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Component Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The port-component-name element specifies a port component's name. This name is assigned by the module producer to name the service implementation bean in the module's deployment descriptor. The name must be unique among the port component names defined in the same module.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Name</em>' attribute.
	 * @see #setPortComponentName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_PortComponentName()
	 * @model
	 * @generated
	 */
  String getPortComponentName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getPortComponentName <em>Port Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Component Name</em>' attribute.
	 * @see #getPortComponentName()
	 * @generated
	 */
  void setPortComponentName(String value);

	/**
	 * Returns the value of the '<em><b>Service Endpoint Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Endpoint Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The service-endpoint-interface element contains the fully-qualified name of the port component's Service Endpoint Interface.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #setServiceEndpointInterface(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_ServiceEndpointInterface()
	 * @model
	 * @generated
	 */
  String getServiceEndpointInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getServiceEndpointInterface <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #getServiceEndpointInterface()
	 * @generated
	 */
  void setServiceEndpointInterface(String value);

	/**
	 * Returns the value of the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Small Icon</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Small Icon</em>' attribute.
	 * @see #setSmallIcon(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_SmallIcon()
	 * @model
	 * @generated
	 */
  String getSmallIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getSmallIcon <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small Icon</em>' attribute.
	 * @see #getSmallIcon()
	 * @generated
	 */
  void setSmallIcon(String value);

	/**
	 * Returns the value of the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Large Icon</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Large Icon</em>' attribute.
	 * @see #setLargeIcon(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_LargeIcon()
	 * @model
	 * @generated
	 */
  String getLargeIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getLargeIcon <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Large Icon</em>' attribute.
	 * @see #getLargeIcon()
	 * @generated
	 */
  void setLargeIcon(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_Description()
	 * @model
	 * @generated
	 */
  String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
  void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_DisplayName()
	 * @model
	 * @generated
	 */
  String getDisplayName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
  void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Port</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl Port</em>' containment reference.
	 * @see #setWsdlPort(WSDLPort)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_WsdlPort()
	 * @model containment="true" required="true"
	 * @generated
	 */
  WSDLPort getWsdlPort();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getWsdlPort <em>Wsdl Port</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Port</em>' containment reference.
	 * @see #getWsdlPort()
	 * @generated
	 */
  void setWsdlPort(WSDLPort value);

	/**
	 * Returns the value of the '<em><b>Service Impl Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Service Impl Bean</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Impl Bean</em>' containment reference.
	 * @see #setServiceImplBean(ServiceImplBean)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_ServiceImplBean()
	 * @model containment="true" required="true"
	 * @generated
	 */
  ServiceImplBean getServiceImplBean();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getServiceImplBean <em>Service Impl Bean</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Impl Bean</em>' containment reference.
	 * @see #getServiceImplBean()
	 * @generated
	 */
  void setServiceImplBean(ServiceImplBean value);

	/**
	 * Returns the value of the '<em><b>Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsdd.Handler}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Handlers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Handlers</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_Handlers()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsdd.Handler" containment="true"
	 * @generated
	 */
  EList getHandlers();

	/**
	 * Returns the value of the '<em><b>Description Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description Type</em>' containment reference.
	 * @see #setDescriptionType(DescriptionType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_DescriptionType()
	 * @model containment="true"
	 * @generated
	 */
  DescriptionType getDescriptionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDescriptionType <em>Description Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description Type</em>' containment reference.
	 * @see #getDescriptionType()
	 * @generated
	 */
  void setDescriptionType(DescriptionType value);

	/**
	 * Returns the value of the '<em><b>Display Name Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Display Name Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name Type</em>' containment reference.
	 * @see #setDisplayNameType(DisplayNameType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_DisplayNameType()
	 * @model containment="true"
	 * @generated
	 */
  DisplayNameType getDisplayNameType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getDisplayNameType <em>Display Name Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name Type</em>' containment reference.
	 * @see #getDisplayNameType()
	 * @generated
	 */
  void setDisplayNameType(DisplayNameType value);

	/**
	 * Returns the value of the '<em><b>Icon Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Icon Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon Type</em>' containment reference.
	 * @see #setIconType(IconType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_IconType()
	 * @model containment="true"
	 * @generated
	 */
  IconType getIconType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getIconType <em>Icon Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon Type</em>' containment reference.
	 * @see #getIconType()
	 * @generated
	 */
  void setIconType(IconType value);

} // PortComponent
