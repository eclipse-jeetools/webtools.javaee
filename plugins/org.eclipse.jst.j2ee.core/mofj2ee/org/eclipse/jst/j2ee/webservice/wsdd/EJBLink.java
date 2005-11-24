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



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EJB Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ejb-link element is used in the service-impl-bean element to specify that a Service Implementation Bean is defined as a Web Service Endpoint.
 * 
 * The value of the ejb-link element must be the ejb-name of an enterprise bean in the same ejb-jar file.
 * 
 * Used in: service-impl-bean
 * 
 * Examples:
 * <ejb-link>EmployeeRecord</ejb-link>
 * 	<ejb-link>../products/product.jar#ProductEJB</ejb-link>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.EJBLink#getEjbLink <em>Ejb Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getEJBLink()
 * @model
 * @generated
 * @since 1.0 */
public interface EJBLink extends BeanLink{
	/**
	 * Returns the value of the '<em><b>Ejb Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ejb Link</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Link</em>' attribute.
	 * @see #setEjbLink(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getEJBLink_EjbLink()
	 * @model
	 * @generated
	 */
  String getEjbLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.EJBLink#getEjbLink <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Link</em>' attribute.
	 * @see #getEjbLink()
	 * @generated
	 */
  void setEjbLink(String value);

} // EJBLink
