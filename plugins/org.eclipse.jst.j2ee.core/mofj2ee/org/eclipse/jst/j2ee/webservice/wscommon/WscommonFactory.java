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
package org.eclipse.jst.j2ee.webservice.wscommon;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage
 * @generated
 */
public interface WscommonFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  WscommonFactory eINSTANCE = new org.eclipse.jst.j2ee.webservice.wscommon.impl.WscommonFactoryImpl();

	/**
	 * Returns a new object of class '<em>Init Param</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Init Param</em>'.
	 * @generated
	 */
  InitParam createInitParam();

	/**
	 * Returns a new object of class '<em>SOAP Header</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>SOAP Header</em>'.
	 * @generated
	 */
  SOAPHeader createSOAPHeader();

	/**
	 * Returns a new object of class '<em>SOAP Role</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>SOAP Role</em>'.
	 * @generated
	 */
  SOAPRole createSOAPRole();

	/**
	 * Returns a new object of class '<em>Port Name</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Name</em>'.
	 * @generated
	 */
  PortName createPortName();

	/**
	 * Returns a new object of class '<em>Description Type</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description Type</em>'.
	 * @generated
	 */
  DescriptionType createDescriptionType();

	/**
	 * Returns a new object of class '<em>Display Name Type</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Name Type</em>'.
	 * @generated
	 */
  DisplayNameType createDisplayNameType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
  WscommonPackage getWscommonPackage();

} //WscommonFactory
