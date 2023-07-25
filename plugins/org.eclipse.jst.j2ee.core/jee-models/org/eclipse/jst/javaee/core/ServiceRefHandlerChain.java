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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Ref Handler Chain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The handler-chain element defines the handlerchain. 
 *         Handlerchain can be defined such that the handlers in the
 *         handlerchain operate,all ports of a service, on a specific
 *         port or on a list of protocol-bindings. The choice of elements
 *         service-name-pattern, port-name-pattern and protocol-bindings
 *         are used to specify whether the handlers in handler-chain are
 *         for a service, port or protocol binding. If none of these 
 *         choices are specified with the handler-chain element then the
 *         handlers specified in the handler-chain will be applied on 
 *         everything.
 *         
 *         @since Java EE 5, Web Services Client 1.2
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getServiceNamePattern <em>Service Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getPortNamePattern <em>Port Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getProtocolBindings <em>Protocol Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain()
 * @extends JavaEEObject
 * @generated
 */
public interface ServiceRefHandlerChain extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Service Name Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Name Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Name Pattern</em>' attribute.
	 * @see #setServiceNamePattern(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain_ServiceNamePattern()
	 * @generated
	 */
	String getServiceNamePattern();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getServiceNamePattern <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Name Pattern</em>' attribute.
	 * @see #getServiceNamePattern()
	 * @generated
	 */
	void setServiceNamePattern(String value);

	/**
	 * Returns the value of the '<em><b>Port Name Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Name Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Name Pattern</em>' attribute.
	 * @see #setPortNamePattern(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain_PortNamePattern()
	 * @generated
	 */
	String getPortNamePattern();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getPortNamePattern <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Name Pattern</em>' attribute.
	 * @see #getPortNamePattern()
	 * @generated
	 */
	void setPortNamePattern(String value);

	/**
	 * Returns the value of the '<em><b>Protocol Bindings</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protocol Bindings</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protocol Bindings</em>' attribute.
	 * @see #setProtocolBindings(List)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain_ProtocolBindings()
	 * @generated
	 */
	List<String> getProtocolBindings();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getProtocolBindings <em>Protocol Bindings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Protocol Bindings</em>' attribute.
	 * @see #getProtocolBindings()
	 * @generated
	 */
	void setProtocolBindings(List<String> value);

	/**
	 * Returns the value of the '<em><b>Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ServiceRefHandler}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handlers</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain_Handlers()
	 * @generated
	 */
	List<ServiceRefHandler> getHandlers();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChain_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ServiceRefHandlerChain