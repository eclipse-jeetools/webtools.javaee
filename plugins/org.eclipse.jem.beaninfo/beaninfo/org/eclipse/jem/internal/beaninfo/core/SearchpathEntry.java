/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: SearchpathEntry.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.w3c.dom.*;

import org.eclipse.jem.internal.beaninfo.adapters.*;

/**
 * Searchpath entry. Beaninfo searchpath entry (i.e. package name). Can include
 * kind/path/exported if not a child of the BeaninfoEntry.
 * 
 * Note: if not a child of a BeanInfoEntry, then the path refers to a package
 * in the classpath, so container is supported here.
 * 
 * @version 	1.0
 * @author
 */
public class SearchpathEntry implements IBeaninfosDocEntry {

	final static String sSearchpath = "searchpath"; // Searchpath entry, shared with BeaninfosDoc. //$NON-NLS-1$
	final static String sPackage = "package"; // Package name. //$NON-NLS-1$

	/**
	 * Read the entry in from the element.
	 */
	public static SearchpathEntry readEntry(IReader reader, Object element, IProject project, boolean beaninfoChild) {
		String packageName = reader.getAttribute(element, sPackage);
		if (beaninfoChild)
			return new SearchpathEntry(packageName); // Kind/path aren't valid on beaninfo children.

		String elementKind = reader.getAttribute(element, BeaninfosDoc.sKind);
		String pathStr = reader.getAttribute(element, BeaninfosDoc.sPath);
		int kind = BeaninfoEntry.kindFromString(elementKind);		
		IPath path = null;
		if (pathStr != null) {
			// ensure path is absolute
			path = new Path(pathStr);
			if (kind != IClasspathEntry.CPE_VARIABLE && kind != IClasspathEntry.CPE_CONTAINER && !path.isAbsolute()) {
				path = project != null ? project.getFullPath().append(path) : path.makeAbsolute(); // Some folder/jar within this project
			}
		}

		// create the appropriate entry
		boolean valid = true;
		switch (kind) {

			case IClasspathEntry.CPE_LIBRARY :
				valid = path != null && path.isAbsolute();
				break;

			case IClasspathEntry.CPE_SOURCE :
				if (path == null)
					valid = false;
				else if (path.isAbsolute()) {
					// must be an entry in this project or specify another project
					String projSegment = path.segment(0);
					if (project == null || projSegment == null || !projSegment.equals(project.getName())) {
						// another project
						kind = IClasspathEntry.CPE_PROJECT;
					}
				}
				break;

			case IClasspathEntry.CPE_VARIABLE :
			case IClasspathEntry.CPE_CONTAINER:				
				break;

			default :
				valid = false;
				break;
		}

		if (valid)
			return new SearchpathEntry(kind, path, packageName);
		else
			return null;

	}

	protected int kind = -1; // Under Beaninfo entry, these don't have kind/path's.
	protected IPath path;	// Path can be null if under beaninfo entry
	protected String packageName;	// Packagename can be null if kind/path refer to a project or registered variable.
	protected boolean isExported;

	public SearchpathEntry(int kind, IPath path, String packageName) {
		this.kind = kind;
		this.path = path;
		this.packageName = packageName;
	}

	public SearchpathEntry(String packageName) {
		this.packageName = packageName;
	}

	public int getKind() {
		return kind;
	}
	
	public IPath getPath() {
		return path;
	}

	public String getPackage() {
		return packageName;
	}

	public Node writeEntry(Document doc, IProject project) {

		Element element = doc.createElement(sSearchpath);
		if (kind != -1) {
			// A non-beaninfo child
			element.setAttribute(BeaninfosDoc.sKind, BeaninfoEntry.kindToString(kind));
			IPath tPath = path;
			if (kind != IClasspathEntry.CPE_VARIABLE && kind != IClasspathEntry.CPE_CONTAINER) {
				// translate to project relative from absolute (unless a device path)
				if (tPath.isAbsolute()) {
					if (tPath.segment(0).equals(project.getFullPath().segment(0))) {
						tPath = tPath.removeFirstSegments(1);
						tPath = tPath.makeRelative();
					} else {
						tPath = tPath.makeAbsolute();
					}
				}
			}

			element.setAttribute(BeaninfosDoc.sPath, tPath.toString());
		}
		if (packageName != null)
			element.setAttribute(sPackage, packageName);
		return element;
	}
	
	public boolean equals(Object other) {
		if (this == other)
			return true;
			
		if (!(other instanceof SearchpathEntry))
			return false;
	
		SearchpathEntry otherEntry = (SearchpathEntry) other;
		if (kind != otherEntry.kind)
			return false;
			
		if (path == null)
			if (otherEntry.path != null)
				return false;
			else
				;
		else if (!path.equals(otherEntry.path))
			return false;
			
		if (packageName == null)
			if (otherEntry.packageName != null)
				return false;
			else
				;
		else if (!packageName.equals(otherEntry.packageName))
			return false;
			
		return true;
	}
	
	public int hashCode() {
		int hashCode = kind;
		if (path != null)
			hashCode ^= path.hashCode();
		if (packageName != null)
			hashCode ^= packageName.hashCode();
		return hashCode;
	}

}
