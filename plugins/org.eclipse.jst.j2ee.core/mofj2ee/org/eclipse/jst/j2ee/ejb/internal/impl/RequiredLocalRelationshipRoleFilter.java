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

import java.util.Collections;

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;


/**
 * Insert the type's description here.
 * Creation date: (10/15/2001 11:35:00 AM)
 * @author: Administrator
 */
public class RequiredLocalRelationshipRoleFilter extends AbstractRequiredRelationshipRoleFilter {
	private static RequiredLocalRelationshipRoleFilter singleton;
@Override
protected java.util.List getRolesToFilter(ContainerManagedEntity cmp) {
    EjbModuleExtensionHelper modHelper = getEjbModuleExtHelper(cmp);
	if(modHelper != null)
	  return modHelper.getLocalRelationshipRoles_cmp11(cmp);
	return Collections.EMPTY_LIST;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2001 11:27:25 AM)
 * @return com.ibm.ejs.models.base.extensions.ejbext.impl.RequiredLocalRelationshipRoleFilter
 */
public static RequiredLocalRelationshipRoleFilter singleton() {
	if (singleton == null)
		singleton = new RequiredLocalRelationshipRoleFilter();
	return singleton;
}
}

















































