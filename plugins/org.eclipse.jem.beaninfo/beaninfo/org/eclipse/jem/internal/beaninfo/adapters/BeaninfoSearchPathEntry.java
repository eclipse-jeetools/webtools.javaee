package org.eclipse.jem.internal.beaninfo.adapters;
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
 *  $RCSfile: BeaninfoSearchPathEntry.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */


import org.w3c.dom.*;
/**
 * Entry of search path for beaninfo.
 * *package* because it is for old format and should not be used outside of this package.
 */

class BeaninfoSearchPathEntry {
	protected String fPackageName;	// Name of package that this entry references	

	/**
	 * packageName.
	 */	
	public BeaninfoSearchPathEntry(String packageName) {
		fPackageName = packageName;
	}
	
	public String getPackageName() {
		return fPackageName;
	}
		
	/**
	 * package protected so that only BeaninfoNature can call it. This is the
	 * old format which is no longer being used. It is here to allow conversion
	 * from Beta to GA.
	 */		 
	private static final String sSearchPathEntryElementName = "pathEntry"; //$NON-NLS-1$
	private static final String sPackageElementName = "package";	 //$NON-NLS-1$
	static BeaninfoSearchPathEntry readOldEntry(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element beElement= (Element) node;
			if (beElement.getNodeName().equalsIgnoreCase(sSearchPathEntryElementName)) {
				String pkgName = beElement.getAttribute(sPackageElementName);
				return new BeaninfoSearchPathEntry(pkgName);
			}
		}
		return null;		
	}
	
	public static BeaninfoSearchPathEntry readEntry(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element beElement= (Element) node;
			if (beElement.getNodeName().equalsIgnoreCase(sSearchPathEntryElementName)) {
				String pkgName = beElement.getAttribute(sPackageElementName);
				return new BeaninfoSearchPathEntry(pkgName);
			}
		}
		return null;
	}
	
	public Element writeEntry(Document doc) {
		Element entry = doc.createElement(sSearchPathEntryElementName);	// Create entry
		entry.setAttribute(sPackageElementName, getPackageName());	// Set the package name
		return entry;
	}	
		
	public boolean equals(Object other) {
		if (this == other)
			return true;
			
		if (!(other instanceof BeaninfoSearchPathEntry))
			return false;
			
		BeaninfoSearchPathEntry otherEntry = (BeaninfoSearchPathEntry) other;
		return (fPackageName.equals(otherEntry.fPackageName));
	}
}