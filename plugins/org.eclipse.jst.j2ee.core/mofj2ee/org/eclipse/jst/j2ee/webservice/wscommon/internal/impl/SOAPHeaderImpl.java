/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wscommon.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.common.internal.impl.QNameImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SOAP Header</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SOAPHeaderImpl extends QNameImpl implements SOAPHeader
{
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected SOAPHeaderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return WscommonPackage.Literals.SOAP_HEADER;
	}

} //SOAPHeaderImpl
