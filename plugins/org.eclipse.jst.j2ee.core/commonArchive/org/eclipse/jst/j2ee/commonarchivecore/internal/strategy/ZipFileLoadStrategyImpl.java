/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.jst.j2ee.commonarchivecore.internal.File;


/**
 * Implementer that knows how to read files from a zip/jar file on the local file system
 * 
 * @see LoadStrategy
 */

public class ZipFileLoadStrategyImpl extends LoadStrategyImpl {
	protected java.io.File file;
	protected ZipFile zipFile;

	/**
	 * ZipFileLoadStrategy constructor comment.
	 */
	public ZipFileLoadStrategyImpl() {
		super();
	}

	/**
	 * ZipFileLoadStrategy constructor comment.
	 */
	public ZipFileLoadStrategyImpl(java.io.File file) throws IOException {
		super();
		setFile(file);
		setZipFile(new ZipFile(file));
	}

	public void close() {
		super.close();
		try {
			getZipFile().close();
		} catch (Throwable t) {
			//Ignore
		}
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	protected boolean primContains(java.lang.String uri) {
		return getZipFile().getEntry(uri) != null;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public java.lang.String getAbsolutePath() throws java.io.FileNotFoundException {
		return getFile().getAbsolutePath();
	}

	/**
	 * Insert the method's description here. Creation date: (12/20/00 4:40:14 PM)
	 * 
	 * @return java.io.File
	 */
	public java.io.File getFile() {
		return file;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.util.List getFiles() {
		List list = new ArrayList();
		Enumeration entries = getZipFile().entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (!entry.isDirectory()){
				if(!collectedLooseArchiveFiles.containsKey(entry.getName())) {
					File aFile = createFile(entry.getName());
					aFile.setSize(entry.getSize());
					aFile.setLastModified(entry.getTime());
					list.add(aFile);
				}
			} else {
				File aFile = createDirectory(entry.getName());
				aFile.setDirectoryEntry(true);
				aFile.setSize(entry.getSize());
				aFile.setLastModified(entry.getTime());
				list.add(aFile);
			}
		}
		return list;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.io.InputStream getInputStream(java.lang.String uri) throws IOException, FileNotFoundException {
		try {
			ZipEntry entry = getZipFile().getEntry(uri);
			if (entry == null)
				throw new FileNotFoundException(uri);

			return new java.io.BufferedInputStream(getZipFile().getInputStream(entry));
		} catch (IllegalStateException zipClosed) {
			throw new IOException(zipClosed.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (11/01/00 11:28:12 AM)
	 * 
	 * @return java.util.zip.ZipFile
	 */
	public java.util.zip.ZipFile getZipFile() {
		return zipFile;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public boolean isUsing(java.io.File aSystemFile) {
		return getFile() != null && getFile().equals(aSystemFile);
	}

	/**
	 * Insert the method's description here. Creation date: (12/20/00 4:40:14 PM)
	 * 
	 * @param newFile
	 *            java.io.File
	 */
	public void setFile(java.io.File newFile) {
		file = newFile;
	}


	/**
	 * Insert the method's description here. Creation date: (11/01/00 11:28:12 AM)
	 * 
	 * @param newZipFile
	 *            java.util.zip.ZipFile
	 */
	public void setZipFile(java.util.zip.ZipFile newZipFile) {
		zipFile = newZipFile;
	}
}
