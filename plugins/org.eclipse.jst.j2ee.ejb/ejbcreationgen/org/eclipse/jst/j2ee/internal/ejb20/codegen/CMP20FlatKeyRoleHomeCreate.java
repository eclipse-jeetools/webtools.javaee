/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.List;

import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanHomeInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityHomeCreate;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;


public class CMP20FlatKeyRoleHomeCreate extends EntityHomeCreate {
	/**
	 * Constructor for Entity20HomeCreate.
	 */
	public CMP20FlatKeyRoleHomeCreate() {
		super();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberGenerator#setHistoryDescriptor(JavaMemberHistoryDescriptor)
	 */
	public void setHistoryDescriptor(JavaMemberHistoryDescriptor newHistoryDescriptor) {
		super.setHistoryDescriptor(newHistoryDescriptor);
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		List keyRoles = null;
		try {
			keyRoles = EJBGenHelpers.getEntityKeyRoles(helper.getEntity(), helper, getSourceContext().getNavigator());
			if (keyRoles.isEmpty())
				newHistoryDescriptor.setDeleteOnly(true);
		} catch (GenerationException e) {
		}
	}

	protected EnterpriseBeanHomeInterface getDeclaringHomeInterfaceGenerator() throws GenerationException {
		return (EnterpriseBeanHomeInterface) getDeclaringTypeGenerator();
	}

	protected boolean isForRemoteHome() throws GenerationException {
		return getDeclaringHomeInterfaceGenerator().isRemote();
	}

	protected String[] getExceptions() throws GenerationException {
		if (isForRemoteHome())
			return super.getExceptions();
		return new String[]{IEJBGenConstants.CREATE_EXCEPTION_NAME};
	}

	protected String getReturnType() throws GenerationException {
		if (isForRemoteHome())
			return super.getReturnType();
		return ((Entity) getSourceElement()).getLocalInterfaceName();
	}
}