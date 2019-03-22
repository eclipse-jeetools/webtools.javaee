/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wscommon;

import org.eclipse.jst.j2ee.common.J2EEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SOAP Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The soap-role element contains a SOAP actor definition that the Handler will play as a role.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole#getSoapRole <em>Soap Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getSOAPRole()
 * @model
 * @generated
 */
public interface SOAPRole extends J2EEEObject {
	/**
	 * Returns the value of the '<em><b>Soap Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Soap Role</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Soap Role</em>' attribute.
	 * @see #setSoapRole(String)
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getSOAPRole_SoapRole()
	 * @model
	 * @generated
	 */
  String getSoapRole();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole#getSoapRole <em>Soap Role</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Soap Role</em>' attribute.
	 * @see #getSoapRole()
	 * @generated
	 */
  void setSoapRole(String value);

} // SOAPRole
