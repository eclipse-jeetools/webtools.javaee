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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Impl Bean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The service-impl-bean element defines the Web service implementation.A service implementation can be an EJB bean class or JAX-RPC web component. Existing EJB implementations are exposed as a Web service using an ejb-link.
 * 
 * Used in: port-component
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getEEJBLink <em>EEJB Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getEServletLink <em>EServlet Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getBeanLink <em>Bean Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getServiceImplBean()
 * @model 
 * @generated
 */
public interface ServiceImplBean extends EObject{
	/**
	 * Returns the value of the '<em><b>EEJB Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EEJB Link</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>EEJB Link</em>' containment reference.
	 * @see #setEEJBLink(EJBLink)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getServiceImplBean_EEJBLink()
	 * @model containment="true"
	 * @generated
	 */
  EJBLink getEEJBLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getEEJBLink <em>EEJB Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EEJB Link</em>' containment reference.
	 * @see #getEEJBLink()
	 * @generated
	 */
  void setEEJBLink(EJBLink value);

	/**
	 * Returns the value of the '<em><b>EServlet Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EServlet Link</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>EServlet Link</em>' containment reference.
	 * @see #setEServletLink(ServletLink)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getServiceImplBean_EServletLink()
	 * @model containment="true"
	 * @generated
	 */
  ServletLink getEServletLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getEServletLink <em>EServlet Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EServlet Link</em>' containment reference.
	 * @see #getEServletLink()
	 * @generated
	 */
  void setEServletLink(ServletLink value);

	/**
	 * Returns the value of the '<em><b>Bean Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bean Link</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Bean Link</em>' containment reference.
	 * @see #setBeanLink(BeanLink)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage#getServiceImplBean_BeanLink()
	 * @model containment="true"
	 * @generated
	 */
  BeanLink getBeanLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.ServiceImplBean#getBeanLink <em>Bean Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bean Link</em>' containment reference.
	 * @see #getBeanLink()
	 * @generated
	 */
  void setBeanLink(BeanLink value);

} // ServiceImplBean
