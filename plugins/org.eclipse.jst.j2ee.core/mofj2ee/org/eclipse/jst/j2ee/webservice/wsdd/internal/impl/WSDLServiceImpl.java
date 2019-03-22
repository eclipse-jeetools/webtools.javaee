/*******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.jst.j2ee.common.internal.impl.QNameImpl;

import org.eclipse.jst.j2ee.webservice.wsdd.WSDLService;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WSDL Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class WSDLServiceImpl extends QNameImpl implements WSDLService {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WSDLServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WsddPackage.Literals.WSDL_SERVICE;
	}

} //WSDLServiceImpl
