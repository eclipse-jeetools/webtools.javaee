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
 *  $RCSfile: BIListElementSorter.java,v $
 *  $Revision: 1.2 $  $Date: 2004/03/08 00:48:07 $ 
 */

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoEntry;

/**
 * Sorter for sorting BPBeaninfoListElements within the main list.
 * 
 * @version 	1.0
 * @author
 */
public class BIListElementSorter extends ViewerSorter {
	
	/*
	 * @see ViewerSorter#category(Object)
	 */
	public int category(Object obj) {
		if (obj instanceof BPBeaninfoListElement) {
			BPBeaninfoListElement element = (BPBeaninfoListElement) obj;
			
			switch (((BeaninfoEntry) element.getEntry()).getKind()) {
			case IClasspathEntry.CPE_LIBRARY:
				return 4;
			case IClasspathEntry.CPE_PROJECT:
				return 1;
			case IClasspathEntry.CPE_SOURCE:
				return 0;
			case IClasspathEntry.CPE_VARIABLE:
				return 3;
			case BeaninfoEntry.BIE_PLUGIN:
				return 5;
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
			
		if ((e1 instanceof BPBeaninfoListElement) && (e2 instanceof BPBeaninfoListElement)) {
			BeaninfoEntry p1 = (BeaninfoEntry) ((BPBeaninfoListElement) e1).getEntry();
			BeaninfoEntry p2 = (BeaninfoEntry) ((BPBeaninfoListElement) e2).getEntry();
			
			// Compare paths
			return p1.getPath().toString().compareTo(p2.getPath().toString());
		}
		return super.compare(viewer, e1, e2);
	}	



}
