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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.CMPEntityBeanEjbPostCreateMB;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (5/4/2001 12:05:49 PM)
 * 
 * @author: Administrator
 */
public class CMP20EntityBeanEjbPostCreateMB extends CMPEntityBeanEjbPostCreateMB {
	static final String BODY_PATTERN = "%0(%1);\n"; //$NON-NLS-1$

	public void run(IGenerationBuffer buffer) throws GenerationException {
		List roles = getRoles();
		CommonRelationshipRole role;
		String setterName, roleArgName;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			setterName = RoleHelper.getSetterName(role);
			roleArgName = RoleHelper.getParameterName(role);
			buffer.formatWithMargin(BODY_PATTERN, new String[]{setterName, roleArgName});
		}
	}

	/**
	 * Since there is not requiredness in EJB 2.0, we only want the key roles.
	 */
	protected List getRoles() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		List keyRoles = EJBGenHelpers.getEntityKeyRoles(entity, (EntityHelper) getTopLevelHelper(), getSourceContext().getNavigator());

		if (keyRoles.isEmpty())
			return keyRoles;
		//Remove all non navigable roles
		List toRemove = null;
		EJBRelationshipRole role;
		for (int i = 0; i < keyRoles.size(); i++) {
			role = (EJBRelationshipRole) keyRoles.get(i);
			if (!role.isNavigable()) {
				if (toRemove == null)
					toRemove = new ArrayList();
				toRemove.add(role);
			}
		}
		if (toRemove != null) {
			List result = new ArrayList(keyRoles);
			result.removeAll(toRemove);
			return result;
		}
		return keyRoles;
	}
}