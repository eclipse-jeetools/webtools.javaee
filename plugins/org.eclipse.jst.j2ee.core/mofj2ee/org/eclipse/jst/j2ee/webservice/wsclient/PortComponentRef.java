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
package org.eclipse.jst.j2ee.webservice.wsclient;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The port-component-ref element declares a client dependency
 *     on the container for resolving a Service Endpoint Interface
 *     to a WSDL port. It optionally associates the Service Endpoint
 *     Interface with a particular port-component. This is only used
 *     by the container for a Service.getPort(Class) method call.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getPortComponentRef()
 * @model 
 * @generated
 */
public interface PortComponentRef extends EObject{
	/**
	 * Returns the value of the '<em><b>Port Component Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Component Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The port-component-link element links a port-component-ref to a
	 *         specific port-component required to be made available by a service
	 *         reference.
	 * 
	 *         The value of a port-component-link must be the port-component-name
	 *         of a port-component in the same module or another module in the same
	 *         application unit. The syntax for specification follows the syntax
	 *         defined for ejb-link in the EJB 2.0 specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Link</em>' attribute.
	 * @see #setPortComponentLink(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getPortComponentRef_PortComponentLink()
	 * @model 
	 * @generated
	 */
	String getPortComponentLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Component Link</em>' attribute.
	 * @see #getPortComponentLink()
	 * @generated
	 */
	void setPortComponentLink(String value);

	/**
	 * Returns the value of the '<em><b>Service Endpoint Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Endpoint Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The service-endpoint-interface element defines a fully qualified
	 *         Java class that represents the Service Endpoint Interface of a
	 *         WSDL port.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint Interface</em>' reference.
	 * @see #setServiceEndpointInterface(JavaClass)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getPortComponentRef_ServiceEndpointInterface()
	 * @model required="true"
	 * @generated
	 */
	JavaClass getServiceEndpointInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint Interface</em>' reference.
	 * @see #getServiceEndpointInterface()
	 * @generated
	 */
	void setServiceEndpointInterface(JavaClass value);

} // PortComponentRef
