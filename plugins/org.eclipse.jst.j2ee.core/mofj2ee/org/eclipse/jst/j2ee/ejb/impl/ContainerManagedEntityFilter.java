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
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBExtensionFilter;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;


/**
 * Insert the type's description here. Creation date: (11/28/2000 6:28:39 PM)
 * @author: Administrator
 */
public abstract class ContainerManagedEntityFilter implements EJBExtensionFilter {
 

    /**
     * AttributeFilter constructor comment.
     */
    public ContainerManagedEntityFilter() {
        super();
    }

    /**
     * filter method comment.
     */
    public abstract List filter(ContainerManagedEntity cmp);

    /**
     * All CMPAttributeFilters only operate on ContainerManagedEntityExtension
     * objects.
     */
    public List filter(EnterpriseBean ejb) {
        if (ejb.isEntity() && ((Entity) ejb).isContainerManagedEntity()) return filter((ContainerManagedEntity) ejb);
        return new ArrayList();
    }

    protected void filterRoleAttributesByName(List allAttributes, List roleAttributes) {
        if (!roleAttributes.isEmpty()) {
            int allSize, roleSize;
            roleSize = roleAttributes.size();
            CMPAttribute roleAtt, allAtt;
            for (int i = 0; i < roleSize; i++) {
                roleAtt = (CMPAttribute) roleAttributes.get(i);
                allSize = allAttributes.size();
                for (int j = allSize - 1; j != -1; j--) {
                    allAtt = (CMPAttribute) allAttributes.get(j);
                    if (roleAtt == allAtt || roleAtt.getName().equals(allAtt.getName())) {
                        allAttributes.remove(j);
                        break;
                    }
                }
            }
        }
    }

    protected EjbModuleExtensionHelper getEjbModuleExtHelper(Object context) {
        return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(context);
    }

    protected List getLocalRelationshipRoles(ContainerManagedEntity cmp) {
        EjbModuleExtensionHelper modelExtender = null;
        if (cmp.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)
            return cmp.getRoles();
        else if ( (modelExtender = getEjbModuleExtHelper(cmp)) != null ){
            return modelExtender.getLocalRelationshipRoles_cmp11(cmp);
        } 
        return Collections.EMPTY_LIST;
    }
    
    protected List getRelationshipRoles(ContainerManagedEntity cmp) { 
        List roles = new ArrayList(); 
        collectRelationshipRoles(cmp, getEjbModuleExtHelper(cmp), roles);
        return Collections.unmodifiableList(roles);
    }
    
    public void collectRelationshipRoles(ContainerManagedEntity cmp, EjbModuleExtensionHelper extensionHelper, List containerList) {
        if(cmp == null)
            return;
        containerList.addAll(getLocalRelationshipRoles(cmp));
        if(extensionHelper != null)
            collectRelationshipRoles((ContainerManagedEntity) extensionHelper.getSuperType(cmp), extensionHelper, containerList);        
    }
}
