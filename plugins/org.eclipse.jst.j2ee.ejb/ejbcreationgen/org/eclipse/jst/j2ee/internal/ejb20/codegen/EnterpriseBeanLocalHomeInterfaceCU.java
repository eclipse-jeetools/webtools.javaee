/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanHomeInterfaceCU;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


public abstract class EnterpriseBeanLocalHomeInterfaceCU extends EnterpriseBeanHomeInterfaceCU {
	/**
	 * Constructor for EnterpriseBeanLocalHomeInterfaceCU.
	 */
	public EnterpriseBeanLocalHomeInterfaceCU() {
		super();
	}

	/*
	 * @see EnterpriseBeanHomeInterfaceCU#getHomeInterfaceQualifiedName()
	 */
	protected String getHomeInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClientClassReferenceHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		return ((EnterpriseBean) getSourceElement()).getLocalHomeInterfaceName();
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.codegen.EnterpriseBeanRemoteInterfaceCU#getClientClassReferenceHelper()
	 */
	protected EJBClassReferenceHelper getClientClassReferenceHelper() {
		return ((EnterpriseBeanHelper) getTopLevelHelper()).getLocalHomeHelper();
	}

}