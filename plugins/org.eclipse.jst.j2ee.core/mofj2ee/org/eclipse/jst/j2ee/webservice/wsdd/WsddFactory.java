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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage
 * @generated
 * @since 1.0 */
public interface WsddFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  WsddFactory eINSTANCE = new org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WsddFactoryImpl();

	/**
	 * Returns a new object of class '<em>Web Services</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web Services</em>'.
	 * @generated
	 */
  WebServices createWebServices();

	/**
	 * Returns a new object of class '<em>Web Service Description</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web Service Description</em>'.
	 * @generated
	 */
  WebServiceDescription createWebServiceDescription();

	/**
	 * Returns a new object of class '<em>Port Component</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Component</em>'.
	 * @generated
	 */
  PortComponent createPortComponent();

	/**
	 * Returns a new object of class '<em>WSDL Port</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>WSDL Port</em>'.
	 * @generated
	 */
  WSDLPort createWSDLPort();

	/**
	 * Returns a new object of class '<em>Service Impl Bean</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Impl Bean</em>'.
	 * @generated
	 */
  ServiceImplBean createServiceImplBean();

	/**
	 * Returns a new object of class '<em>Servlet Link</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Servlet Link</em>'.
	 * @generated
	 */
  ServletLink createServletLink();

	/**
	 * Returns a new object of class '<em>EJB Link</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Link</em>'.
	 * @generated
	 */
  EJBLink createEJBLink();

	/**
	 * Returns a new object of class '<em>Handler</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Handler</em>'.
	 * @generated
	 */
  Handler createHandler();

	/**
	 * Returns a new object of class '<em>Bean Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bean Link</em>'.
	 * @generated
	 */
	BeanLink createBeanLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
  WsddPackage getWsddPackage();

} //WsddFactory
