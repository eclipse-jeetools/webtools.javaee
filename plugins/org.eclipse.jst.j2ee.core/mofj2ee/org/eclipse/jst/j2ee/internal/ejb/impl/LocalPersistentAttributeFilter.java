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

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * This filter will return a subset of the persistentAttributes from the 
 * ContainerManagedEntityExtension that is passed as an argument to the filter method.
 * It will remove any persistentAttributes that are inherited by any supertypes.
 * That means the returned list of CMPAttributes will be attributes that were defined locally
 * for the ContainerManagedEntity held by the ContainerManagedEntityExtension plus attributes
 * that were added from EjbRelationshipRoles that are held by the ContainerManagedEntityExtension.
 * Creation date: (11/28/2000 5:35:03 PM)
 * @author: Administrator
 */
public class LocalPersistentAttributeFilter extends SupertypeCMPAttributeFilter {
	private static LocalPersistentAttributeFilter singleton;
/**
 * Return the proper list of attributes from cmpExt.
 */
protected java.util.List getSourceAttributes(ContainerManagedEntity cmp) {
	return new ArrayList(cmp.getPersistentAttributes());
}
/**
 * Return a boolean indicating whether anAttribute also exists in the superEntity.
 * For this filter, only the name has to be the same.
 */
protected boolean isSupertypeAttribute(ContainerManagedEntity superEntity, CMPAttribute anAttribute) {
	CMPAttribute found = superEntity.getPersistentAttribute(anAttribute.getName());
	return found != null;
}
/**
 * Insert the method's description here.
 * Creation date: (11/28/2000 5:36:00 PM)
 * @return com.ibm.ejs.models.base.extensions.ejbext.impl.LocalPersistentAttributeFilter
 */
public static LocalPersistentAttributeFilter singleton() {
	if (singleton == null)
		singleton = new LocalPersistentAttributeFilter();
	return singleton;
}
}

















































