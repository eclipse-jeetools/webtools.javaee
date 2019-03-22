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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;


/**
 * The use-caller-identity element specifies that the caller's security identity be used as the security identity for the execution of the enterprise bean's methods.
 * @generated
 */
public class UseCallerIdentityImpl extends SecurityIdentityImpl implements UseCallerIdentity {

	public UseCallerIdentityImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.USE_CALLER_IDENTITY;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.common.SecurityIdentity#isUseCallerIdentity()
	 */
	@Override
	public boolean isUseCallerIdentity() {
		return true;
	}

}





