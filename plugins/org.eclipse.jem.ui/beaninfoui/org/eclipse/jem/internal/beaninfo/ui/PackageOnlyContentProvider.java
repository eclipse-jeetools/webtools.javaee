/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: PackageOnlyContentProvider.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 23:02:54 $ 
 */


import org.eclipse.jdt.ui.StandardJavaElementContentProvider;

import org.eclipse.jdt.core.IPackageFragment;
import java.util.*;
/**
 * A content provider which will only go down to the package level,
 * it won't explore further. The advantage of this is so that plus signs ('+')
 * won't show up on packages. The Filter technique could be used to not
 * show the children, but this still shows the plus sign.
 *
 * Also, if the element is a java.util.List, then that list will be returned
 * as the children. This allows for a root to be composed instead of being
 * one of the standard Java Elements.
 */

public class PackageOnlyContentProvider extends StandardJavaElementContentProvider {
	
	/**
	 * If the element is a list, return the iterator on it.
	 * Else send it up the chain.
	 */
	public Object[] getChildren(Object element) {
		if (element instanceof List)
			return ((List) element).toArray();
		return super.getChildren(element);
	}
	
	/**
	 * If the element is a list and it is not empty, it has children,
	 * if it is a IPackageFragment it does not,
	 * else send it up the chain.
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof List)
			return !((List) element).isEmpty();
		if (element instanceof IPackageFragment)
			return false;
		return super.hasChildren(element);
	}
}
