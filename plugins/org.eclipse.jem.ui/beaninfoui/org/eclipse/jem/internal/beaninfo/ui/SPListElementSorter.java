package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SPListElementSorter.java,v $
 *  $Revision: 1.2 $  $Date: 2004/03/08 00:48:07 $ 
 */

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.eclipse.jem.internal.beaninfo.adapters.SearchpathEntry;

/**
 * Sorter for sorting BPSearchListElements within the main list.
 * It is not to be used on elements within a specific beaninfo entry.
 * 
 * @version 	1.0
 * @author
 */
public class SPListElementSorter extends ViewerSorter {
	
	/*
	 * @see ViewerSorter#category(Object)
	 */
	public int category(Object obj) {
		if (obj instanceof BPSearchListElement) {
			BPSearchListElement element = (BPSearchListElement) obj;
			
			switch (((SearchpathEntry) element.getEntry()).getKind()) {
			case IClasspathEntry.CPE_LIBRARY:
				return 4;
			case IClasspathEntry.CPE_PROJECT:
				return 1;
			case IClasspathEntry.CPE_SOURCE:
				return 0;
			case IClasspathEntry.CPE_VARIABLE:
				return 3;
			case IClasspathEntry.CPE_CONTAINER:
				return 2;
			}
		}
		return super.category(obj);
	}

	/*
	 * @see ViewerSorter#compare()
	 */	
	public int compare(Viewer viewer, Object e1, Object e2) {
		int cat1 = category(e1);
		int cat2 = category(e2);
	
		if (cat1 != cat2)
			return cat1 - cat2;
			
		if ((e1 instanceof BPSearchListElement) && (e2 instanceof BPSearchListElement)) {
			SearchpathEntry p1 = (SearchpathEntry) ((BPSearchListElement) e1).getEntry();
			SearchpathEntry p2 = (SearchpathEntry) ((BPSearchListElement) e2).getEntry();
			
			// Compare first within path, then packages within each path.
			int c = p1.getPath().toString().compareTo(p2.getPath().toString());
			if (c == 0) {
				// Within same path, so now sort by package, if there are any.
				String pkg1 = p1.getPackage();
				String pkg2 = p2.getPackage();
				if (pkg1 == null)
					pkg1 = "";	// For null, use an empty string //$NON-NLS-1$
				if (pkg2 == null)
					pkg2 = ""; //$NON-NLS-1$
				return pkg1.compareTo(pkg2);
			} else
				return c;	// Paths are not equal, so sort via path.
		}
		return super.compare(viewer, e1, e2);
	}	



}
