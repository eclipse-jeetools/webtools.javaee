/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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


public class RequiredRelationshipRoleFilter extends ContainerManagedEntityFilter {

    private static RequiredRelationshipRoleFilter singleton;

    @Override
	protected List filterNotcached(ContainerManagedEntity cmp) {
        List roles = new ArrayList();
        Iterator it = getRelationshipRoles(cmp).iterator();
        CommonRelationshipRole role;
        while (it.hasNext()) {
            role = (CommonRelationshipRole) it.next();
            if (shouldAddFilteredResult(role)) roles.add(role);
        }
        return roles;
    }

    /**
     * Return true if the multiplicity for @aRole is required.
     */
    protected boolean shouldAddFilteredResult(CommonRelationshipRole aRole) {
        return aRole.isRequired();
    }

    /**
     * Insert the method's description here. Creation date: (8/6/2001 11:27:25
     * AM)
     * 
     * @return com.ibm.ejs.models.base.extensions.ejbext.impl.RequiredRelationshipRoleFilter
     */
    public static RequiredRelationshipRoleFilter singleton() {
        if (singleton == null) singleton = new RequiredRelationshipRoleFilter();
        return singleton;
    }
}
