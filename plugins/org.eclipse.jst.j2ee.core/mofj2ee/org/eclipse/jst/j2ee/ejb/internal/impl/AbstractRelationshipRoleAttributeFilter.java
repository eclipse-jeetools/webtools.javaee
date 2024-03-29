/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;

public abstract class AbstractRelationshipRoleAttributeFilter extends
		ContainerManagedEntityFilter {
	/**
	 * AbstractRelationshipRoleAttributeFilter constructor comment.
	 */
	public AbstractRelationshipRoleAttributeFilter() {
		super();
	}

	@Override
	protected List filterNotcached(ContainerManagedEntity cmp) {
		List attributes = new ArrayList();
		Iterator it = getSourceRoles(cmp).iterator();
		CommonRelationshipRole role;
		while (it.hasNext()) {
			role = (CommonRelationshipRole) it.next();
			attributes.addAll(role.getAttributes());
		}
		return attributes;
	}

	/**
	 * Return the proper list of roles from cmpExt.
	 */
	protected abstract java.util.List getSourceRoles(
			ContainerManagedEntity cmpExt);
}
