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
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: BeaninfosDoc.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:44:20 $ 
 */
import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.*;

import org.eclipse.jem.internal.beaninfo.adapters.*;
/**
 * Beaninfos document in-memory format.
 */

public class BeaninfosDoc implements IBeaninfosDocEntry {

	protected IBeaninfosDocEntry[] searchpath;

	static final String sExported = "exported"; //$NON-NLS-1$

	static final String sKind = "kind"; //$NON-NLS-1$

	static final String sPath = "path"; //$NON-NLS-1$

	public BeaninfosDoc() {
	}

	public BeaninfosDoc(IBeaninfosDocEntry[] searchpath) {
		this.searchpath = searchpath;
	}

	public int getKind() {
		return -1;	// N.A.
	}
	
	public IPath getPath() {
		return null;	// N.A.
	}
	
	public static BeaninfosDoc readEntry(IReader reader, Object root, IProject project) {
		ArrayList paths = new ArrayList();
		Object children = reader.getChildren(root);
		int childrenLength = reader.getLength(children);
		for (int i = 0; i < childrenLength; i++) {
			Object child = reader.getItem(children, i);
			if (reader.isNodeTypeElement(child)) {
				Object entry = null;
				if (reader.getNodeName(child).equalsIgnoreCase(BeaninfoEntry.sBeaninfo)) {
					entry = BeaninfoEntry.readEntry(reader, child, project);
				} else if (reader.getNodeName(child).equalsIgnoreCase(SearchpathEntry.sSearchpath)) {
					entry = SearchpathEntry.readEntry(reader, child, project, false);
				}
				if (entry != null)
					paths.add(entry);
			}
		}
		return new BeaninfosDoc((IBeaninfosDocEntry[]) paths.toArray(new IBeaninfosDocEntry[paths.size()]));
	}

	public IBeaninfosDocEntry[] getSearchpath() {
		return searchpath;
	}

	public void setSearchpath(IBeaninfosDocEntry[] searchpath) {
		this.searchpath = searchpath;
	}

	public Node writeEntry(Document doc, IProject project) {
		Element root = doc.createElement(SearchpathEntry.sSearchpath); // Create Root Element
		for (int i = 0; i < searchpath.length; i++) {
			root.appendChild(searchpath[i].writeEntry(doc, project)); // Add to the document
		}
		return root;
	}
}
