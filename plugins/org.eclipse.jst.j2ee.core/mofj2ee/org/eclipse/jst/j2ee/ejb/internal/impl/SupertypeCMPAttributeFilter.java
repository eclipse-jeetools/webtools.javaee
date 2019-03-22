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

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;


public abstract class SupertypeCMPAttributeFilter extends ContainerManagedEntityFilter {
/**
 * SupertypeCMPAttributeFilter constructor comment.
 */
public SupertypeCMPAttributeFilter() {
	super();
}

@Override
protected List filterNotcached(ContainerManagedEntity cmp) {
	ContainerManagedEntity supertype = null;
	EjbModuleExtensionHelper extensionHelper = getEjbModuleExtHelper(cmp);
	if(extensionHelper != null)
	   supertype = (ContainerManagedEntity)extensionHelper.getSuperType(cmp);
	if (supertype == null)
		return getSourceAttributes(cmp);
	return filterUsingSupertype(cmp, supertype);
}
/**
 * filter method comment.
 */
protected List filterUsingSupertype(ContainerManagedEntity cmp, ContainerManagedEntity supertype) {
	ContainerManagedEntity superEntity = supertype;
	List result = new ArrayList();
	Iterator it = getSourceAttributes(cmp).iterator();
	CMPAttribute attribute;
	while (it.hasNext()) {
		attribute = (CMPAttribute) it.next();
		if (!isSupertypeAttribute(superEntity, attribute))
			result.add(attribute);
	}
	return result;
}
/**
 * Return the proper list of attributes from cmpExt.
 */
protected abstract List getSourceAttributes(ContainerManagedEntity cmp) ;
/**
 * Return a boolean indicating whether anAttribute also exists in the superEntity.
 */
protected abstract boolean isSupertypeAttribute(ContainerManagedEntity superEntity, CMPAttribute anAttribute) ;
}

















































