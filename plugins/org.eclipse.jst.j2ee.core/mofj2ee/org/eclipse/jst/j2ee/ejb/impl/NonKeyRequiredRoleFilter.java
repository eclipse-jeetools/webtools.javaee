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
/*
 * Created on May 5, 2003
 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.ejb.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NonKeyRequiredRoleFilter extends ContainerManagedEntityFilter {

    private static NonKeyRequiredRoleFilter singleton;

    /**
     * filter method comment.
     */
    public List filter(ContainerManagedEntity cmp) {
        List roles = new ArrayList();
        Iterator it = getRelationshipRoles(cmp).iterator();
        CommonRelationshipRole role;
        while (it.hasNext()) {
            role = (CommonRelationshipRole) it.next();
            if (!role.isKey() && role.isRequired()) roles.add(role);
        }
        return roles;
    }

    /**
     * Insert the method's description here. Creation date: (11/28/2000 7:15:37
     * PM)
     * 
     * @return com.ibm.ejs.models.base.extensions.ejbext.impl.KeyRelationshipRoleFilter
     */
    public static NonKeyRequiredRoleFilter singleton() {
        if (singleton == null) singleton = new NonKeyRequiredRoleFilter();
        return singleton;
    }
}
