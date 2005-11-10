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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.NestedJarException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.NestedArchiveIterator;


/**
 * Load strategy used as a last resort to read entries from jars inside of jars. It is a last resort
 * because of the poor perfomance involved, as the contents of the zip entries are not cached, and
 * sequential scan searches will cause the zip stream to be inflated on each search. Whenever
 * possible,
 * 
 * @link {TempFileLoadStrategy} should be used.
 */
public class NestedArchiveLoadStrategyImpl extends LoadStrategyImpl {
	/**
	 * Index to improve lookup performance - not used if in Runtime to reduce footprint
	 */
	protected Map urisToPositions;
	protected LoadStrategy parent;

	/**
	 * NestedArchiveLoadStrategyImpl constructor comment.
	 */
	public NestedArchiveLoadStrategyImpl(LoadStrategy parentStrategy) {
		super();
		setParent(parentStrategy);
	}

	protected void buildIndex() {
		Map map = new HashMap();
		try {
			ZipInputStream zis = getZipInputStream();
			ZipEntry entry = null;
			long position = 0;
			while ((entry = zis.getNextEntry()) != null) {
				position++;
				map.put(entry.getName(), new Long(position));
			}
		} catch (IOException iox) {
			throwNestedJarException(iox);
		}
		setUrisToPositions(map);
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	protected boolean primContains(String uri) {
		if (usingIndex())
			return containsUsingIndex(uri);
		return containsSequentially(uri);
	}


	protected boolean containsSequentially(String uri) {
		ZipInputStream zis = null;
		try {
			zis = getZipInputStreamSkippedTo(uri);
			return zis != null;
		} catch (IOException ex) {
			return false;
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (IOException ioe) {
					//Ignore
				}
			}
		}
	}


	protected boolean containsUsingIndex(String uri) {
		return getUrisToPositions().containsKey(uri);
	}


	/**
	 * Used internally; clients should not need to call
	 */
	public FileIterator getFileIterator() throws IOException {
		if (((Archive) getContainer()).getOptions().isReadOnly())
			return new NestedArchiveIterator(getContainer().getFiles(), getZipInputStream());
		return super.getFileIterator();
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.util.List getFiles() {
		List list = new ArrayList(500);
		ZipInputStream zis = null;
		try {
			zis = getZipInputStream();
			ZipEntry entry = zis.getNextEntry();
			while (entry != null) {
				if (!entry.isDirectory()) {
					File aFile = createFile(entry.getName());
					aFile.setSize(entry.getSize());
					aFile.setLastModified(entry.getTime());
					list.add(aFile);
				}
				entry = zis.getNextEntry();
			}
		} catch (IOException iox) {
			throwNestedJarException(iox);
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (IOException iox) {
					//Ignore
				}
			}
		}
		return list;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl
	 */
	public java.io.InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		if (usingIndex())
			return getInputStreamUsingIndex(uri);
		return getInputStreamSequentially(uri);
	}

	protected java.io.InputStream getInputStreamSequentially(String uri) throws IOException, FileNotFoundException {
		return new java.io.BufferedInputStream(getZipInputStreamSkippedTo(uri));
	}

	protected java.io.InputStream getInputStreamUsingIndex(String uri) throws IOException, FileNotFoundException {
		Long position = (Long) getUrisToPositions().get(uri);
		if (position == null)
			throw new FileNotFoundException();
		ZipInputStream zis = getZipInputStream();
		long pos = position.longValue();
		for (long i = 0; i < pos; i++) {
			zis.getNextEntry();
		}
		return new java.io.BufferedInputStream(zis);
	}

	/**
	 * Insert the method's description here. Creation date: (12/07/00 6:12:36 PM)
	 * 
	 * @return com.ibm.etools.archive.LoadStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy getParent() {
		return parent;
	}

	/**
	 * Insert the method's description here. Creation date: (12/07/00 4:38:21 PM)
	 * 
	 * @return java.util.Map
	 */
	public java.util.Map getUrisToPositions() {
		if (usingIndex() && urisToPositions == null)
			buildIndex();
		return urisToPositions;
	}

	protected ZipInputStream getZipInputStream() throws IOException, FileNotFoundException {
		String uri = getContainer().getURI();
		InputStream in = getParent().getInputStream(uri);
		return new ZipInputStream(in);
	}

	protected ZipInputStream getZipInputStreamSkippedTo(String uri) throws IOException, FileNotFoundException {
		if (uri == null)
			throw new NullPointerException(CommonArchiveResourceHandler.Null_uri_EXC_); //$NON-NLS-1$ = "Null uri"
		ZipInputStream zis = null;
		ZipEntry entry = null;
		try {
			zis = getZipInputStream();
			entry = zis.getNextEntry();
			while (entry != null) {
				if (uri.equals(entry.getName()))
					return zis;
				entry = zis.getNextEntry();
			}
		} finally {
			// entry was not found so close the stream
			if (entry == null && zis != null) {
				try {
					zis.close();
				} catch (IOException ioe) {
					//Ignore
				}
			}
		}
		throw new FileNotFoundException(uri);
	}

	/**
	 * Insert the method's description here. Creation date: (12/07/00 6:12:36 PM)
	 * 
	 * @param newParent
	 *            com.ibm.etools.archive.LoadStrategy
	 */
	public void setParent(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy newParent) {
		parent = newParent;
	}

	/**
	 * Insert the method's description here. Creation date: (12/07/00 4:38:21 PM)
	 * 
	 * @param newUrisToPositions
	 *            java.util.Map
	 */
	public void setUrisToPositions(java.util.Map newUrisToPositions) {
		urisToPositions = newUrisToPositions;
	}

	/**
	 * An exception was caught reading the nested jar; throws a runtime exception
	 */
	protected void throwNestedJarException(Exception caughtException) {
		throw new NestedJarException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.nested_jar_EXC_, (new Object[]{getContainer().getURI(), getParent().getContainer().getURI()})), caughtException);//$NON-NLS-1$ = "An error occurred reading {0} from {1}"
	}

	/**
	 * Don't use the index in the runtime environment; reduce footprint
	 */
	public boolean usingIndex() {
		return false;
		//return !com.ibm.etools.archive.util.ArchiveUtil.isRuntime();
	}


}
