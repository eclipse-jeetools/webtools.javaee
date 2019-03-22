/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*


 */
import java.util.ArrayList;
import java.util.List;

public class URL {
	public String namespaceName;
	public String ID;
	List attributeNames;
/**
 * URL constructor comment.
 */
public URL(String urlString) {
	initializeFromString(urlString);
}
/**
 * URL constructor comment.
 */
public URL(String nameSpaceName, String anID) {
	this.namespaceName = nameSpaceName;
	this.ID = anID;
}
public List getAttributeNames() {
	return attributeNames;
}
public void initializeFromString(String urlString) {
	int poundInx = urlString.lastIndexOf('#');
	if (poundInx > -1) {
		namespaceName = urlString.substring(0, poundInx);
		urlString = urlString.substring(poundInx + 1);
		int dotIndex = urlString.indexOf("->"); //$NON-NLS-1$
		if (dotIndex > -1) {
			ID = urlString.substring(0, dotIndex);
			String attributeNameString = urlString.substring(dotIndex + 2);
			attributeNames = new ArrayList();
			do {
				dotIndex = attributeNameString.indexOf("->"); //$NON-NLS-1$
				if (dotIndex > -1) {
					attributeNames.add(attributeNameString.substring(0, dotIndex));
					attributeNameString = attributeNameString.substring(dotIndex + 2);
				} else
					attributeNames.add(attributeNameString);
			} while (dotIndex > -1);
		} else {
			ID = urlString;
		}
	} else {
		// assume that any URL string that ends with .xmi is a namespace.
		if (urlString.endsWith(".xmi")){ //$NON-NLS-1$
			namespaceName = urlString;
			ID = null;
		} else {
			namespaceName = "?defaultURL?"; //$NON-NLS-1$
			ID = urlString;
		}
	}
}
public String toString() {
	return "URL(" + ((namespaceName == null) ? "" : namespaceName + "#") + ((ID == null) ? "" : ID) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
}
}


