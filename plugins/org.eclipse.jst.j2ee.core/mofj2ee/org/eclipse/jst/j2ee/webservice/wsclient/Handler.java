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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Declares the handler for a port-component. Handlers can access the
 *     init-param name/value pairs using the HandlerInfo interface. If
 *     port-name is not specified, the handler is assumed to be associated
 *     with all ports of the service.
 * 
 *     Used in: service-ref
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getSoapRoles <em>Soap Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getPortNames <em>Port Names</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getSoapHeaders <em>Soap Headers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler()
 * @model
 * @generated
 */
public interface Handler extends CompatibilityDescriptionGroup{
	/**
	 * Returns the value of the '<em><b>Handler Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the name of the handler. The name must be unique within the module.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Handler Name</em>' attribute.
	 * @see #setHandlerName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_HandlerName()
	 * @model
	 * @generated
	 */
	String getHandlerName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getHandlerName <em>Handler Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Name</em>' attribute.
	 * @see #getHandlerName()
	 * @generated
	 */
	void setHandlerName(String value);

	/**
	 * Returns the value of the '<em><b>Soap Headers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Soap Headers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Headers</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_SoapHeaders()
	 * @model type="org.eclipse.jst.j2ee.common.QName" containment="true"
	 * @generated
	 */
	EList getSoapHeaders();

	/**
	 * Returns the value of the '<em><b>Soap Roles</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Soap Roles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The soap-role element contains a SOAP actor definition that the Handler will play as a role.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Soap Roles</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_SoapRoles()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getSoapRoles();

	/**
	 * Returns the value of the '<em><b>Port Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The port-name element defines the WSDL port-name that a handler should be associated with.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Names</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_PortNames()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getPortNames();

	/**
	 * Returns the value of the '<em><b>Handler Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines a fully qualified class name for the handler implementation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Handler Class</em>' reference.
	 * @see #setHandlerClass(JavaClass)
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_HandlerClass()
	 * @model required="true"
	 * @generated
	 */
	JavaClass getHandlerClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsclient.Handler#getHandlerClass <em>Handler Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Class</em>' reference.
	 * @see #getHandlerClass()
	 * @generated
	 */
	void setHandlerClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Init Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Params</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Params</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getHandler_InitParams()
	 * @model type="org.eclipse.jst.j2ee.common.ParamValue" containment="true"
	 * @generated
	 */
	EList getInitParams();

} // Handler
