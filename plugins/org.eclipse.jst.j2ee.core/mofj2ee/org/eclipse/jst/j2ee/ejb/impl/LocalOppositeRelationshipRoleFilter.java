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
package org.eclipse.jst.j2ee.ejb.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * This filter will return a subset of the relationshipRoles (this includes
 * inherited EjbRelationshipRoles) from the ContainerManagedEntityExtension
 * that is passed as an argument to the filter method. It will remove any
 * EjbRelationshipRole that is inherited and it will return the opposite role.
 * Creation date: (11/28/2000 7:14:57 PM) @author: Administrator
 */
public class LocalOppositeRelationshipRoleFilter extends ContainerManagedEntityFilter {

    private static LocalOppositeRelationshipRoleFilter singleton;

    /**
     * Return a List with the opposite roles for the local relationship roles.
     */
    public List filter(ContainerManagedEntity cmp) {
        List oppositeRoles = new ArrayList();
        Iterator it = getLocalRelationshipRoles(cmp).iterator();
        CommonRelationshipRole role, opposite;
        while (it != null && it.hasNext()) {
            role = (CommonRelationshipRole) it.next();
            opposite = role.getOppositeAsCommonRole();
            if (opposite != null) oppositeRoles.add(opposite);
        }
        return oppositeRoles;
    }

    /**
     * Insert the method's description here. Creation date: (11/28/2000 7:15:37
     * PM)
     * 
     * @return com.ibm.ejs.models.base.extensions.ejbext.impl.LocalOppositeRelationshipRoleFilter
     */
    public static LocalOppositeRelationshipRoleFilter singleton() {
        if (singleton == null) singleton = new LocalOppositeRelationshipRoleFilter();
        return singleton;
    }
}
