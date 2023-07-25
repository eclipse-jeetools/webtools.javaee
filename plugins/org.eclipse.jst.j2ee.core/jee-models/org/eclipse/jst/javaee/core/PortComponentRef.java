/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import java.math.BigInteger;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The port-component-ref element declares a client dependency
 *         on the container for resolving a Service Endpoint Interface
 *         to a WSDL port. It optionally associates the Service Endpoint
 *         Interface with a particular port-component. This is only used
 *         by the container for a Service.getPort(Class) method call.
 *         
 *         @since Java EE 5, Web Services Client 1.2
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getMtomThreshold <em>Mtom Threshold</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getAddressing <em>Addressing</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getRespectBinding <em>Respect Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef()
 * @extends JavaEEObject
 * @generated
 */
public interface PortComponentRef extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Service Endpoint Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-endpoint-interface element defines a fully qualified
	 *             Java class that represents the Service Endpoint Interface of a
	 *             WSDL port.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #setServiceEndpointInterface(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_ServiceEndpointInterface()
	 * @generated
	 */
	String getServiceEndpointInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #getServiceEndpointInterface()
	 * @generated
	 */
	void setServiceEndpointInterface(String value);

	/**
	 * Returns the value of the '<em><b>Enable Mtom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Used to enable or disable SOAP MTOM/XOP mechanism on the client
	 *             side for a port-component. 
	 *             
	 *             Not to be specified for JAX-RPC runtime
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enable Mtom</em>' attribute.
	 * @see #isSetEnableMtom()
	 * @see #unsetEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_EnableMtom()
	 * @generated
	 */
	boolean isEnableMtom();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Mtom</em>' attribute.
	 * @see #isSetEnableMtom()
	 * @see #unsetEnableMtom()
	 * @see #isEnableMtom()
	 * @generated
	 */
	void setEnableMtom(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnableMtom()
	 * @see #isEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @generated
	 */
	void unsetEnableMtom();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enable Mtom</em>' attribute is set.
	 * @see #unsetEnableMtom()
	 * @see #isEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @generated
	 */
	boolean isSetEnableMtom();

	/**
	 * Returns the value of the '<em><b>Mtom Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             When MTOM is enabled, binary data above this size in bytes
	 *             should be XOP encoded or sent as attachment. Default value is 0.
	 *             
	 *             Not to be specified for JAX-RPC runtime
	 *             
	 *             @since Java EE 6, Web Services Client 1.3
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mtom Threshold</em>' attribute.
	 * @see #setMtomThreshold(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_MtomThreshold()
	 * @generated
	 */
	BigInteger getMtomThreshold();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getMtomThreshold <em>Mtom Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mtom Threshold</em>' attribute.
	 * @see #getMtomThreshold()
	 * @generated
	 */
	void setMtomThreshold(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Addressing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             This specifies the WS-Addressing requirements for a JAX-WS
	 *             web service. It corresponds to javax.xml.ws.soap.Addressing
	 *             annotation or its feature javax.xml.ws.soap.AddressingFeature.
	 *             
	 *             See the addressingType for more information.
	 *             
	 *             Not to be specified for JAX-RPC runtime
	 *             
	 *             @since Java EE 6, Web Services Client 1.3
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Addressing</em>' containment reference.
	 * @see #setAddressing(AddressingType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_Addressing()
	 * @generated
	 */
	AddressingType getAddressing();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getAddressing <em>Addressing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Addressing</em>' containment reference.
	 * @see #getAddressing()
	 * @generated
	 */
	void setAddressing(AddressingType value);

	/**
	 * Returns the value of the '<em><b>Respect Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Corresponds to the javax.xml.ws.RespectBinding annotation
	 *             or its corresponding javax.xml.ws.RespectBindingFeature web
	 *             service feature. This is used to control whether a JAX-WS
	 *             implementation must respect/honor the contents of the
	 *             wsdl:binding in the WSDL that is associated with the service.
	 *             
	 *             Not to be specified for JAX-RPC runtime
	 *             
	 *             @since Java EE 6, Web Services Client 1.3
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Respect Binding</em>' containment reference.
	 * @see #setRespectBinding(RespectBindingType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_RespectBinding()
	 * @generated
	 */
	RespectBindingType getRespectBinding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getRespectBinding <em>Respect Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Respect Binding</em>' containment reference.
	 * @see #getRespectBinding()
	 * @generated
	 */
	void setRespectBinding(RespectBindingType value);

	/**
	 * Returns the value of the '<em><b>Port Component Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The port-component-link element links a port-component-ref
	 *             to a specific port-component required to be made available
	 *             by a service reference.
	 *             
	 *             The value of a port-component-link must be the
	 *             port-component-name of a port-component in the same module
	 *             or another module in the same application unit. The syntax
	 *             for specification follows the syntax defined for ejb-link
	 *             in the EJB 2.0 specification.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Link</em>' attribute.
	 * @see #setPortComponentLink(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_PortComponentLink()
	 * @generated
	 */
	String getPortComponentLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Component Link</em>' attribute.
	 * @see #getPortComponentLink()
	 * @generated
	 */
	void setPortComponentLink(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // PortComponentRef