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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * @author jlanuti
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LocalRelationshipRoleKeyAttributeFilter extends LocalRelationshipRoleAttributeFilter {

	private static LocalRelationshipRoleKeyAttributeFilter singleton;

public static LocalRelationshipRoleAttributeFilter singleton() {
	if (singleton == null)
		singleton = new LocalRelationshipRoleKeyAttributeFilter();
	return singleton;
}

/**
 * filter method comment.
 */
public List filter(ContainerManagedEntity cmp) {
	List attributes = new ArrayList();
	Iterator it = getSourceRoles(cmp).iterator();
	CommonRelationshipRole role;
	while (it.hasNext()) {
		role = (CommonRelationshipRole) it.next();
		if (role.isKey())
			attributes.addAll(role.getAttributes());
	}
	return attributes;
}
}
