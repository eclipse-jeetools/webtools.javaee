/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.util.List;

import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.DefaultClasspathProvider;

/**
 * @author naci
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ClasspathProvider extends DefaultClasspathProvider {
	public ClasspathProvider() {
		// Default constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.model.internal.emitter.DefaultClasspathProvider#getClasspathItems()
	 */
	public List getClasspathItems() {
		List list  = super.getClasspathItems();
		list.add("org.eclipse.jst.j2ee.ejb.annotations.xdoclet");	
		list.add("org.eclipse.jst.common.frameworks");
		list.add("org.eclipse.wst.common.frameworks");
		list.add("com.eteration.test");
		return list;
	}
}
