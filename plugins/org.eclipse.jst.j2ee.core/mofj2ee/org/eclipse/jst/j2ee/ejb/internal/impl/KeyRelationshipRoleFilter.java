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
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * This filter will return a subset of the relationshipRoles (this includes
 * inherited EjbRelationshipRoles) from the ContainerManagedEntityExtension
 * that is passed as an argument to the filter method. It will remove any
 * EjbRelationshipRole that is not part of the key. Creation date: (11/28/2000
 * 7:14:57 PM) @author: Administrator
 */
public class KeyRelationshipRoleFilter extends ContainerManagedEntityFilter {

    private static KeyRelationshipRoleFilter singleton;

    /**
     * filter method comment.
     */
    public java.util.List filter(ContainerManagedEntity cmp) {
        List keyRoles = new ArrayList();
        Iterator it = getRelationshipRoles(cmp).iterator();
        CommonRelationshipRole role;
        while (it.hasNext()) {
            role = (CommonRelationshipRole) it.next();
            if (role.isKey()) keyRoles.add(role);
        }
        return keyRoles;
    }

    /**
     * Insert the method's description here. Creation date: (11/28/2000 7:15:37
     * PM)
     * 
     * @return com.ibm.ejs.models.base.extensions.ejbext.impl.KeyRelationshipRoleFilter
     */
    public static KeyRelationshipRoleFilter singleton() {
        if (singleton == null) singleton = new KeyRelationshipRoleFilter();
        return singleton;
    }
}
