/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.impl;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;

/**
 * Insert the type's description here.
 * Creation date: (10/15/2001 11:37:19 AM)
 * @author: Administrator
 */
public abstract class AbstractRequiredRelationshipRoleFilter extends ContainerManagedEntityFilter {
/**
 * AbstractRequiredRelationshipRoleFilter constructor comment.
 */
public AbstractRequiredRelationshipRoleFilter() {
	super();
}
/**
 * filter method comment.
 */
public List filter(ContainerManagedEntity cmp) {
	List roles = new ArrayList();
	List allRoles = getRolesToFilter(cmp);
	CommonRelationshipRole role;
	for (int i = 0; i < allRoles.size(); i++) {
		role = (CommonRelationshipRole) allRoles.get(i);
		if (shouldAddFilteredResult(role))
			roles.add(role);
	}
	return roles;
}
protected abstract java.util.List getRolesToFilter(ContainerManagedEntity cmp) ;
/**
 * Return true if the multiplicity for @aRole is required.
 */
protected boolean shouldAddFilteredResult(CommonRelationshipRole aRole) {
	boolean required = aRole.isRequired();
	if (required) {
		CommonRelationshipRole opposite = aRole.getOppositeAsCommonRole();
		if (opposite != null) {
			if (opposite.isRequired() && !aRole.isKey()) {
				if (opposite.isKey() || aRole.isMany())
					return false;
				if (!opposite.isMany())
					//both are single and not key
					return aRole.isForward();
			}
		}
	}
	return required;
}
}



















































