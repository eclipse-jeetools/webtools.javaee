/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 18, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.codegen;

import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class SessionServiceEndpointInterfaceCU extends EnterpriseBeanRemoteInterfaceCU {
	/**
	 *  
	 */
	public SessionServiceEndpointInterfaceCU() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.EnterpriseBeanRemoteInterfaceCU#getTypeGeneratorName()
	 */
	protected String getTypeGeneratorName() {
		return IEJBGenConstants.SESSION_SERVICE_ENDPOINT_INTERFACE;
	}

	protected String getClientInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClientClassReferenceHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		return ((Session) getSourceElement()).getServiceEndpoint().getQualifiedName();
	}

	protected EJBClassReferenceHelper getClientClassReferenceHelper() {
		return ((EnterpriseBeanHelper) getTopLevelHelper()).getServiceEndpointHelper();
	}
}