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
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityBeanEjbPostCreate;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


public class CMP20EntityBeanFlatKeyRoleEjbPostCreate extends EntityBeanEjbPostCreate {
	/**
	 * Constructor for CMP20EntityBeanFlatKeyRoleEjbPostCreate.
	 */
	public CMP20EntityBeanFlatKeyRoleEjbPostCreate() {
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

	/**
	 * @see org.eclipse.jst.j2ee.ejb.codegen.EntityBeanEjbCreate#getParameterDescriptors(Entity)
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors(Entity entity) throws GenerationException {
		return EJBGenHelpers.getEntityKeyFieldsAsFlatParms(entity, (EntityHelper) getTopLevelHelper(), getSourceContext().getNavigator());
	}
}