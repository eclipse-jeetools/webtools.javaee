/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.util.Set;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;


/**
 * Insert the type's description here. Creation date: (02/28/01 1:20:09 PM)
 * 
 * @author: Administrator
 */
public class FileExtensionsFilterImpl extends SaveFilterImpl implements org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter {
	protected Set excludedExtensions;
	protected boolean isCaseSensitive = false;
	protected Set excludedExtensionsAsUppercase;

	/**
	 * FileExtensionsFilterImpl constructor comment.
	 */
	public FileExtensionsFilterImpl(String[] extensionsToExclude, boolean caseSensitive) {
		super();
		Set extensions = new java.util.HashSet();
		for (int i = 0; i < extensionsToExclude.length; i++) {
			extensions.add(extensionsToExclude[i]);
		}
		setExcludedExtensions(extensions);
		setIsCaseSensitive(caseSensitive);
		initialize();
	}

	/**
	 * FileExtensionsFilterImpl constructor comment.
	 */
	public FileExtensionsFilterImpl(String extension, boolean caseSensitive) {
		super();
		Set extensions = new java.util.HashSet(1);
		extensions.add(extension);
		setExcludedExtensions(extensions);
		setIsCaseSensitive(caseSensitive);
		initialize();
	}

	/**
	 * FileExtensionsFilterImpl constructor comment.
	 */
	public FileExtensionsFilterImpl(Set extensionsToExclude, boolean caseSensitive) {
		super();
		setExcludedExtensions(extensionsToExclude);
		setIsCaseSensitive(caseSensitive);
		initialize();
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 1:24:28 PM)
	 * 
	 * @return java.util.Set
	 */
	public java.util.Set getExcludedExtensions() {
		return excludedExtensions;
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 2:42:20 PM)
	 * 
	 * @return java.util.Set
	 */
	protected java.util.Set getExcludedExtensionsAsUppercase() {
		return excludedExtensionsAsUppercase;
	}

	protected void initialize() {
		if (isCaseSensitive())
			return;

		java.util.HashSet aSet = new java.util.HashSet();
		java.util.Iterator it = getExcludedExtensions().iterator();
		while (it.hasNext()) {
			aSet.add(((String) it.next()).toUpperCase());
		}
		setExcludedExtensionsAsUppercase(aSet);
	}

	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 1:24:28 PM)
	 * 
	 * @param newExcludedExtensions
	 *            java.util.Set
	 */
	protected void setExcludedExtensions(java.util.Set newExcludedExtensions) {
		excludedExtensions = newExcludedExtensions;
	}

	/**
	 * Insert the method's description here. Creation date: (02/28/01 2:42:20 PM)
	 * 
	 * @param newExcludedExtensionsAsUppercase
	 *            java.util.Set
	 */
	protected void setExcludedExtensionsAsUppercase(java.util.Set newExcludedExtensionsAsUppercase) {
		excludedExtensionsAsUppercase = newExcludedExtensionsAsUppercase;
	}

	protected void setIsCaseSensitive(boolean value) {
		isCaseSensitive = value;
	}

	/**
	 * @see com.ibm.etools.archive.SaveFilter
	 */
	public boolean shouldSave(String uri, Archive anArchive) {
		String extension = org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.getFileNameExtension(uri);
		if (extension.equals(""))//$NON-NLS-1$
			return true;

		Set excluded;
		if (isCaseSensitive()) {
			excluded = getExcludedExtensions();
		} else {
			excluded = getExcludedExtensionsAsUppercase();
			extension = extension.toUpperCase();
		}

		return !excluded.contains(extension);

	}
}
