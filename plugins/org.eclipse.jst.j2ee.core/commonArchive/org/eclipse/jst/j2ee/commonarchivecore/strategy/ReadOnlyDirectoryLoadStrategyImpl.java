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
package org.eclipse.jst.j2ee.commonarchivecore.strategy;



import org.eclipse.jst.j2ee.commonarchivecore.ReadOnlyDirectory;

public class ReadOnlyDirectoryLoadStrategyImpl extends DirectoryLoadStrategyImpl {
	protected java.io.File subdirectory;

	/**
	 * ReadOnlyDirectoryLoadStrategy constructor comment.
	 */
	public ReadOnlyDirectoryLoadStrategyImpl(String aDirectoryUri) {
		super(aDirectoryUri);
	}

	/**
	 * ReadOnlyDirectoryLoadStrategy constructor comment.
	 */
	public ReadOnlyDirectoryLoadStrategyImpl(String rootDirectoryUri, java.io.File subdir) {
		super(rootDirectoryUri);
		setSubdirectory(subdir);
	}

	/**
	 * addDirectory method comment.
	 */
	protected void addDirectory(java.io.File aFile, java.util.List aList) {
		ReadOnlyDirectory dir = getArchiveFactory().createReadOnlyDirectory();
		dir.setLastModified(aFile.lastModified());
		dir.setURI(getURIFrom(aFile));
		dir.setLoadingContainer(getContainer());
		dir.setLoadStrategy(new ReadOnlyDirectoryLoadStrategyImpl(getDirectoryUri(), aFile));
		aList.add(dir);

	}

	public boolean contains(java.lang.String uri) {
		return super.contains(uri) && (getSubdirectory() == null || subdirectoryContains(uri));
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.lang.String getAbsolutePath() throws java.io.FileNotFoundException {
		java.io.File subdir = getSubdirectory();
		if (subdir == null) {
			return super.getAbsolutePath();
		}
		return subdir.getAbsolutePath();
	}

	/**
	 * getDirectoryForList method comment.
	 */
	protected java.io.File getDirectoryForList() {
		if (getSubdirectory() != null)
			return getSubdirectory();
		return new java.io.File(getDirectoryUri());
	}

	/**
	 * Insert the method's description here. Creation date: (01/08/01 1:10:47 PM)
	 * 
	 * @return java.io.File
	 */
	protected java.io.File getSubdirectory() {
		return subdirectory;
	}

	/**
	 * Always return false, because we want to treat the entries in a file system directory as basic
	 * files
	 */
	protected boolean isArchive(String uri) {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (01/08/01 1:10:47 PM)
	 * 
	 * @param newSubdirectory
	 *            java.io.File
	 */
	protected void setSubdirectory(java.io.File newSubdirectory) {
		subdirectory = newSubdirectory;
	}

	protected boolean subdirectoryContains(String uri) {
		if (getSubdirectory() == null)
			return false;
		java.io.File aFile = new java.io.File(getFileNameFrom(uri)).getParentFile();
		boolean contains = false;
		while (aFile != null && !contains) {
			if (getSubdirectory().equals(aFile)) {
				contains = true;
			} else {
				aFile = aFile.getParentFile();
			}
		}
		return contains;
	}
}
