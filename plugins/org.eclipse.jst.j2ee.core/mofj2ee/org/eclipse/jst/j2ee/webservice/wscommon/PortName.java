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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.WscommonPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.PortName#getPortName <em>Port Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wscommon.WscommonPackage#getPortName()
 * @model 
 * @generated
 */
public interface PortName extends EObject{
	/**
	 * Returns the value of the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Name</em>' attribute.
	 * @see #setPortName(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wscommon.WscommonPackage#getPortName_PortName()
	 * @model 
	 * @generated
	 */
  String getPortName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wscommon.PortName#getPortName <em>Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Name</em>' attribute.
	 * @see #getPortName()
	 * @generated
	 */
  void setPortName(String value);

} // PortName
