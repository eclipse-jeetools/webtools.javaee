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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class SessionServiceEndpointInterface extends EnterpriseBeanClientInterface {
	/**
	 *  
	 */
	public SessionServiceEndpointInterface() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getCommentTypeName() {
		return "ServiceEndpoint"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected void initilializeClassRefHelper(EnterpriseBeanHelper ejbHelper) {
		setClassRefHelper(ejbHelper.getServiceEndpointHelper());
	}

	/**
	 * For a remote it is either the inherited remote or EJBObject.
	 */
	protected List getInheritedSuperInterfaceNames() {
		// Get the inherited interface or EJBObject.
		List names = new ArrayList();
		EnterpriseBean superEJB = getSourceSupertype();
		if (superEJB == null)
			names.add(getBaseSuperInterfaceName());
		else {
			names.add(((Session) superEJB).getServiceEndpoint().getName());
		}
		return names;
	}

	protected String[] getInterfaceNamesToRemove() {
		if (!hasSuptertypeInterfacesToRemove())
			return null;
		EnterpriseBean oldSuper = ((EnterpriseBeanHelper) getTopLevelHelper()).getOldSupertype();
		if (oldSuper != null) {
			String oldSuperName = ((Session) oldSuper).getServiceEndpoint().getName();
			return new String[]{oldSuperName};
		}
		return new String[]{getBaseSuperInterfaceName()};
	}

	protected String getClientInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClassRefHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		Session ejb = (Session) getSourceElement();
		return ejb.getServiceEndpoint().getName();
	}

	protected String getBaseSuperInterfaceName() {
		return IEJB20GenConstants.SEI_INTERFACE_NAME;
	}
}