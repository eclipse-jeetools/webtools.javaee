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
import java.util.List;

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;

/**
 * This filter will return a subset of the keyAttributes from the 
 * ContainerManagedEntityExtension that is passed as an argument to the filter method.
 * It will remove any keyAttributes that are held by EjbRelationshipRole objects (including
 * inherited EjbRelationshipRole attributes).
 * That means the returned list of CMPAttributes will be key attributes that were defined locally
 * and all inherited key attributes minus any key attributes pointed to by EjbRelationshipRoles, thus the
 * key attributes will only be key attributes that were defined by the user.
 * Creation date: (11/28/2000 8:09:10 PM)
 * @author: Administrator
 */
public class ModelledKeyAttributeFilter extends ContainerManagedEntityFilter {
	private static ModelledKeyAttributeFilter singleton;
/**
 * filter method comment.
 */
public List filter(ContainerManagedEntity cmp) {
	return getSourceAttributes(cmp);
}
/**
 * Return the proper list of attributes from cmpExt.
 * Return all attributes minus those added due to Relationship Roles.
 */
protected java.util.List getSourceAttributes(ContainerManagedEntity cmp) {
	java.util.List all, localRoleKeyAttributes;
	all = new java.util.ArrayList(cmp.getKeyAttributes());
	localRoleKeyAttributes = cmp.getFilteredFeatures(KeyRelationshipRoleAttributeFilter.singleton());
	filterRoleAttributesByName(all, localRoleKeyAttributes);
	return all;
}
/**
 * Insert the method's description here.
 * Creation date: (11/28/2000 5:36:00 PM)
 * @return com.ibm.ejs.models.base.extensions.ejbext.impl.ModelledKeyAttributeFilter
 */
public static ModelledKeyAttributeFilter singleton() {
	if (singleton == null)
		singleton = new ModelledKeyAttributeFilter();
	return singleton;
}
}

















































