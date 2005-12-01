/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Servlet Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The servlet-link element is used in the service-impl-bean element to specify that a Service Implementation Bean is defined as a JAX-RPC Service Endpoint.
 * 
 * The value of the servlet-link element must be the servlet-name of a JAX-RPC Service Endpoint in the same WAR file.
 * 
 * Used in: service-impl-bean
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.ServletLink#getServletLink <em>Servlet Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getServletLink()
 * @model
 * @generated
 * @since 1.0 */
public interface ServletLink extends BeanLink{
	/**
	 * Returns the value of the '<em><b>Servlet Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Servlet Link</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlet Link</em>' attribute.
	 * @see #setServletLink(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getServletLink_ServletLink()
	 * @model
	 * @generated
	 */
  String getServletLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.ServletLink#getServletLink <em>Servlet Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servlet Link</em>' attribute.
	 * @see #getServletLink()
	 * @generated
	 */
  void setServletLink(String value);

} // ServletLink
