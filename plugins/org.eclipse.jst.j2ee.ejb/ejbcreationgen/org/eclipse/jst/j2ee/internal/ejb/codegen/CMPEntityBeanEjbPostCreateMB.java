/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.codegen;


import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.impl.RequiredLocalRelationshipRoleFilter;
import org.eclipse.jst.j2ee.internal.codegen.DependentGenerator;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


public class CMPEntityBeanEjbPostCreateMB extends DependentGenerator {
	static final String BODY_1 = "try {\n"; //$NON-NLS-1$
	static final String BODY_PATTERN = "%0(%1);\n"; //$NON-NLS-1$
	static final String BODY_2 = "} catch (java.rmi.RemoteException remoteEx) {\n"; //$NON-NLS-1$
	static final String BODY_3 = "throw new javax.ejb.CreateException(remoteEx.getMessage());\n"; //$NON-NLS-1$
	static final String BODY_4 = "}\n"; //$NON-NLS-1$

	protected void addRoleSetters(IGenerationBuffer buffer) throws GenerationException {
		List roles = getRoles();
		CommonRelationshipRole role;
		String setterName, roleArgName;
		for (int i = 0; i < roles.size(); i++) {
			role = (CommonRelationshipRole) roles.get(i);
			if (RoleHelper.isMany(role))
				setterName = RoleHelper.getAddName(role);
			else
				setterName = RoleHelper.getSetterName(role);
			roleArgName = RoleHelper.getParameterName(role);
			buffer.formatWithMargin(BODY_PATTERN, new String[]{setterName, roleArgName});
		}
	}

	/**
	 * Return a List of roles from the ContainerManagedEntityExtension.
	 */
	protected List getRoles() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		if (entity == null || !entity.isContainerManagedEntity())
			return Collections.EMPTY_LIST;
		return primGetRoles((ContainerManagedEntity) entity);
	}

	/**
	 * Return only the required local roles.
	 */
	protected List primGetRoles(ContainerManagedEntity entity) {
		return entity.getFilteredFeatures(RequiredLocalRelationshipRoleFilter.singleton());
	}

	public void run(IGenerationBuffer buffer) throws GenerationException {
		if (getRoles().isEmpty())
			return;
		buffer.appendWithMargin(BODY_1);
		buffer.indent();
		addRoleSetters(buffer);
		buffer.unindent();
		buffer.appendWithMargin(BODY_2);
		buffer.indent();
		buffer.appendWithMargin(BODY_3);
		buffer.unindent();
		buffer.appendWithMargin(BODY_4);
	}
}