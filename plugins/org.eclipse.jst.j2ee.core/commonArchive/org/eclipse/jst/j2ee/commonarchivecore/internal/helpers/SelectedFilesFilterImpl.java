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



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;


/**
 * Insert the type's description here. Creation date: (03/23/01 11:04:52 AM)
 * 
 * @author: Administrator
 */
public class SelectedFilesFilterImpl extends SaveFilterImpl {
	/** Set of file uris to be saved */
	protected Set selectedFileNames;

	/**
	 * SelectedFilesFilterImpl constructor comment.
	 */
	public SelectedFilesFilterImpl() {
		super();
	}

	/**
	 * Constructor with a preselected subset of the files in the archive to be saved; each element
	 * in the list must be an instance of {@link com.ibm.etools.commonarchive.File}
	 */
	public SelectedFilesFilterImpl(List archiveFiles) {
		super();
		Set fileNames = new HashSet();
		for (int i = 0; i < archiveFiles.size(); i++) {
			File aFile = (File) archiveFiles.get(i);
			fileNames.add(aFile.getURI());
		}
		setSelectedFileNames(fileNames);
	}

	/**
	 * Parameter must be a set of valid uris in the archive
	 */
	public SelectedFilesFilterImpl(Set fileNames) {
		super();
		setSelectedFileNames(fileNames);
	}

	/**
	 * Insert the method's description here. Creation date: (03/23/01 11:19:01 AM)
	 * 
	 * @return java.util.Set
	 */
	public java.util.Set getSelectedFileNames() {
		return selectedFileNames;
	}

	/**
	 * Insert the method's description here. Creation date: (03/23/01 11:19:01 AM)
	 * 
	 * @param newSelectedFileNames
	 *            java.util.Set
	 */
	public void setSelectedFileNames(java.util.Set newSelectedFileNames) {
		selectedFileNames = newSelectedFileNames;
	}

	public boolean shouldSave(java.lang.String uri, Archive anArchive) {
		return getSelectedFileNames().contains(uri);
	}
}
