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
 *  $RCSfile: BPBeaninfoListElement.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoEntry;
import org.eclipse.jem.internal.beaninfo.adapters.SearchpathEntry;

/**
 * @version 	1.0
 * @author
 */
public class BPBeaninfoListElement extends BPListElement {

	BPSearchListElement[] searchpaths;
	
	/**
	 * Constructor for BPBeaninfoListElement.
	 * The searchPaths is the initial list, it is assumed that
	 * the beaninfoEntry has these searchpaths in them too.
	 */
	public BPBeaninfoListElement(BeaninfoEntry entry, BPSearchListElement[] searchpaths, boolean missing) {
		super(entry, missing);
		this.searchpaths = searchpaths != null ? searchpaths : new BPSearchListElement[0];
	}

	/**
	 * Mark the entry as exported.
	 */
	public void setExported(boolean exported) {
		((BeaninfoEntry) entry).setIsExported(exported);
	}

	/*
	 * @see BPListElement#canExportBeChanged()
	 */
	public boolean canExportBeChanged() {
		return true;
	}

	/*
	 * @see BPListElement#isExported()
	 */
	public boolean isExported() {
		return ((BeaninfoEntry) entry).isExported();
	}
	
	/**
	 * Return the current list of Searchpaths for this Beaninfo Element.
	 */
	public BPSearchListElement[] getSearchpaths() {
		return searchpaths;
	}
	
	/**
	 * Set the new list. This will update the beaninfo entry too.
	 */
	public void setSearchpaths(BPSearchListElement[] searchpaths) {
		this.searchpaths = searchpaths;
		
		SearchpathEntry[] spEntries = new SearchpathEntry[searchpaths.length];
		for (int i = 0; i < searchpaths.length; i++) {
			BPSearchListElement bps = searchpaths[i];
			spEntries[i] = (SearchpathEntry) bps.getEntry();
		}
		
		((BeaninfoEntry) entry).setSearchPaths(spEntries);
	}
}