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
 * Insert the type's description here. Creation date: (8/6/2001 11:26:10 AM)
 * @author: Administrator
 */
public class RequiredRelationshipRoleFilter extends ContainerManagedEntityFilter {

    private static RequiredRelationshipRoleFilter singleton;

    /**
     * filter method comment.
     */
    public List filter(ContainerManagedEntity cmp) {
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
