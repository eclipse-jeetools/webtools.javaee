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
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Declares the handler for a port-component. Handlers can access the init-param name/value pairs using the HandlerInfo interface.
 * 
 * Used in: port-component
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getHandlerName <em>Handler Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getSoapHeaders <em>Soap Headers</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getSoapRoles <em>Soap Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler()
 * @model 
 * @generated
 */
public interface Handler extends CompatibilityDescriptionGroup {
	/**
	 * Returns the value of the '<em><b>Handler Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Handler Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Name</em>' attribute.
	 * @see #setHandlerName(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler_HandlerName()
	 * @model 
	 * @generated
	 */
  String getHandlerName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getHandlerName <em>Handler Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Name</em>' attribute.
	 * @see #getHandlerName()
	 * @generated
	 */
  void setHandlerName(String value);

	/**
	 * Returns the value of the '<em><b>Handler Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Handler Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Class</em>' attribute.
	 * @see #setHandlerClass(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler_HandlerClass()
	 * @model 
	 * @generated
	 */
  String getHandlerClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.Handler#getHandlerClass <em>Handler Class</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Class</em>' attribute.
	 * @see #getHandlerClass()
	 * @generated
	 */
  void setHandlerClass(String value);

	/**
	 * Returns the value of the '<em><b>Init Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wscommon.InitParam}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Init Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Params</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler_InitParams()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wscommon.InitParam" containment="true"
	 * @generated
	 */
  EList getInitParams();

	/**
	 * Returns the value of the '<em><b>Soap Headers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wscommon.SOAPHeader}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Soap Headers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Headers</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler_SoapHeaders()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wscommon.SOAPHeader" containment="true"
	 * @generated
	 */
  EList getSoapHeaders();

	/**
	 * Returns the value of the '<em><b>Soap Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wscommon.SOAPRole}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Soap Roles</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Roles</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getHandler_SoapRoles()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wscommon.SOAPRole" containment="true"
	 * @generated
	 */
  EList getSoapRoles();

} // Handler
