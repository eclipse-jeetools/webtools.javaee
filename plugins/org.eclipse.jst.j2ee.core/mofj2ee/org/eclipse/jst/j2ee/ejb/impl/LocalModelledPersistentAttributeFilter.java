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
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
/**
 * This filter will return a subset of the persistentAttributes from the
 * ContainerManagedEntityExtension that is passed as an argument to the filter
 * method. It will remove any inherited persistentAttributes and any
 * persistentAttributes that are held by EjbRelationshipRole objects (including
 * inherited EjbRelationshipRole attributes). That means the returned list of
 * CMPAttributes will be attributes that were defined locally minus all
 * inherited attributes and minus any attributes pointed to by
 * EjbRelationshipRoles, thus the attributes will only be attributes that were
 * defined locally by the user. Creation date: (11/28/2000 7:31:42 PM) @author:
 * Administrator
 */
public class LocalModelledPersistentAttributeFilter extends SupertypeCMPAttributeFilter {
	private static LocalModelledPersistentAttributeFilter singleton;
	/**
	 * Return the proper list of attributes from cmpExt. Return local
	 * attributes minus those added due to Relationship Roles.
	 */
	protected java.util.List getSourceAttributes(ContainerManagedEntity cmp) {
		java.util.List local = null, localRoleAttributes; 
		local = cmp.getFilteredFeatures(LocalPersistentAttributeFilter.singleton());
		localRoleAttributes = cmp.getFilteredFeatures(LocalRelationshipRoleAttributeFilter.singleton());
		if(local != null)
		  local.removeAll(localRoleAttributes);
		return local;
	}
	/**
	 * Return a boolean indicating whether anAttribute also exists in the
	 * superEntity. For this filter, only the name has to be the same.
	 */
	protected boolean isSupertypeAttribute(ContainerManagedEntity superEntity, CMPAttribute anAttribute) {
		CMPAttribute found = superEntity.getPersistentAttribute(anAttribute.getName());
		return found != null;
	}
	/**
	 * Insert the method's description here. Creation date: (11/28/2000 5:36:00
	 * PM)
	 * 
	 * @return com.ibm.ejs.models.base.extensions.ejbext.impl.LocalModelledPersistentAttributeFilter
	 */
	public static LocalModelledPersistentAttributeFilter singleton() {
		if (singleton == null)
			singleton = new LocalModelledPersistentAttributeFilter();
		return singleton;
	}
}
