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

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * This filter will return a subset of the keyAttributes (this includes inherited 
 * key attributes) from the ContainerManagedEntityExtension that is passed as an
 * argument to the filter method.
 * It will remove any key attribute that is not pointed to by an EjbRelationshipRole.
 * Thus, the returned list will contain key attributes (including inherited key attributes) that only
 * belong to roles.
 * Creation date: (11/28/2000 7:23:22 PM)
 * @author: Administrator
 */
public class KeyRelationshipRoleAttributeFilter extends AbstractRelationshipRoleAttributeFilter {
	private static KeyRelationshipRoleAttributeFilter singleton;
/**
 * Return the proper list of attributes from cmpExt.
 */
protected java.util.List getSourceRoles(ContainerManagedEntity cmp) {
	return cmp.getFilteredFeatures(KeyRelationshipRoleFilter.singleton());
}
/**
 * Insert the method's description here.
 * Creation date: (11/28/2000 5:36:00 PM)
 * @return com.ibm.ejs.models.base.extensions.ejbext.impl.KeyRelationshipRoleAttributeFilter
 */
public static KeyRelationshipRoleAttributeFilter singleton() {
	if (singleton == null)
		singleton = new KeyRelationshipRoleAttributeFilter();
	return singleton;
}
}

















































