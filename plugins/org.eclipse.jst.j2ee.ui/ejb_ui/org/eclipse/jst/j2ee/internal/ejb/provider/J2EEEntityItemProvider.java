/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.Entity;


/**
 * Insert the type's description here. Creation date: (6/20/2001 11:10:06 PM)
 * 
 * @author: Administrator
 */
public class J2EEEntityItemProvider extends EntityItemProvider {
	/**
	 * J2EEEntityItemProvider constructor comment.
	 * 
	 * @param adapterFactory
	 *            org.eclipse.emf.common.notify.AdapterFactory
	 */
	public J2EEEntityItemProvider(org.eclipse.emf.common.notify.AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildren(Object object) {
		List result = new ArrayList();
		result.addAll(super.getChildrenSuper(object));
		J2EEJavaClassProviderHelper.addChildren((Entity) object, result);
		return result;
	}
}