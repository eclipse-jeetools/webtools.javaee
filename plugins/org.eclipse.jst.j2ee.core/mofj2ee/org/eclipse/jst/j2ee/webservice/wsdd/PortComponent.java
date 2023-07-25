/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd;

import java.math.BigInteger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.common.J2EEEObject;
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
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getWsdlService <em>Wsdl Service</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#isEnableMtom <em>Enable Mtom</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getProtocolBinding <em>Protocol Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getHandlerChains <em>Handler Chains</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getMtomThreshold <em>Mtom Threshold</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getRespectBinding <em>Respect Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getAddressing <em>Addressing</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent()
 * @model
 * @generated
 */
public interface PortComponent extends J2EEEObject {
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
	 * @model containment="true"
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

	/**
	 * Returns the value of the '<em><b>Wsdl Service</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsdl Service</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl Service</em>' containment reference.
	 * @see #setWsdlService(WSDLService)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_WsdlService()
	 * @model containment="true" required="true"
	 * @generated
	 */
	WSDLService getWsdlService();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getWsdlService <em>Wsdl Service</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Service</em>' containment reference.
	 * @see #getWsdlService()
	 * @generated
	 */
	void setWsdlService(WSDLService value);

	/**
	 * Returns the value of the '<em><b>Enable Mtom</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Mtom</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enable Mtom</em>' attribute.
	 * @see #setEnableMtom(boolean)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_EnableMtom()
	 * @model default=""
	 * @generated
	 */
	boolean isEnableMtom();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#isEnableMtom <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Mtom</em>' attribute.
	 * @see #isEnableMtom()
	 * @generated
	 */
	void setEnableMtom(boolean value);

	/**
	 * Returns the value of the '<em><b>Protocol Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protocol Binding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protocol Binding</em>' attribute.
	 * @see #setProtocolBinding(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_ProtocolBinding()
	 * @model
	 * @generated
	 */
	String getProtocolBinding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getProtocolBinding <em>Protocol Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Protocol Binding</em>' attribute.
	 * @see #getProtocolBinding()
	 * @generated
	 */
	void setProtocolBinding(String value);

	/**
	 * Returns the value of the '<em><b>Handler Chains</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Chains</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Chains</em>' containment reference.
	 * @see #setHandlerChains(HandlersChains)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_HandlerChains()
	 * @model containment="true"
	 * @generated
	 */
	HandlersChains getHandlerChains();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getHandlerChains <em>Handler Chains</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Chains</em>' containment reference.
	 * @see #getHandlerChains()
	 * @generated
	 */
	void setHandlerChains(HandlersChains value);

	/**
	 * Returns the value of the '<em><b>Mtom Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mtom Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mtom Threshold</em>' attribute.
	 * @see #setMtomThreshold(BigInteger)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_MtomThreshold()
	 * @model
	 * @generated
	 */
	BigInteger getMtomThreshold();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getMtomThreshold <em>Mtom Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mtom Threshold</em>' attribute.
	 * @see #getMtomThreshold()
	 * @generated
	 */
	void setMtomThreshold(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Respect Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Respect Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Respect Binding</em>' containment reference.
	 * @see #setRespectBinding(RespectBindingType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_RespectBinding()
	 * @model containment="true"
	 * @generated
	 */
	RespectBindingType getRespectBinding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getRespectBinding <em>Respect Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Respect Binding</em>' containment reference.
	 * @see #getRespectBinding()
	 * @generated
	 */
	void setRespectBinding(RespectBindingType value);

	/**
	 * Returns the value of the '<em><b>Addressing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Addressing</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Addressing</em>' containment reference.
	 * @see #setAddressing(AddressingType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getPortComponent_Addressing()
	 * @model containment="true"
	 * @generated
	 */
	AddressingType getAddressing();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent#getAddressing <em>Addressing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Addressing</em>' containment reference.
	 * @see #getAddressing()
	 * @generated
	 */
	void setAddressing(AddressingType value);

} // PortComponent
