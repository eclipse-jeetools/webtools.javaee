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



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveURIConverterImpl;


/**
 * Implementer that knows how to save an archives contents to a directory on the local file system
 */
public class ConnectorDirectorySaveStrategyImpl extends SaveStrategyImpl implements SaveStrategy {
	protected String directoryName;
	protected URIConverter uriConverter;
	/** Indicates which type of archives should be expanded */
	protected int expansionFlags;

	/**
	 * DirectoryDumpStrategy constructor comment.
	 */
	public ConnectorDirectorySaveStrategyImpl() {
		super();
	}

	/**
	 * DirectoryDumpStrategy constructor comment.
	 */
	public ConnectorDirectorySaveStrategyImpl(String dirName, int flags) {
		super();
		setDirectoryName(dirName);
		setExpansionFlags(flags);
		initialize();
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	protected SaveStrategy createNestedDirectoryStrategy(Archive anArchive) {
		String uri = org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.getOSUri(getDirectoryName(), "");//$NON-NLS-1$
		return new ConnectorDirectorySaveStrategyImpl(uri, getExpansionFlags());
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	protected SaveStrategy createNestedSaveStrategy(Archive anArchive) throws IOException {
		if (shouldExpand(anArchive))
			return createNestedDirectoryStrategy(anArchive);
		return createNestedZipStrategy(anArchive);
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	protected SaveStrategy createNestedZipStrategy(Archive anArchive) throws IOException {
		OutputStream out = getUriConverter().createOutputStream(URI.createURI(anArchive.getURI()));
		return new ZipStreamSaveStrategyImpl(out);
	}

	/**
	 * Insert the method's description here. Creation date: (11/15/00 2:26:37 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDirectoryName() {
		return directoryName;
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 10:18:21 AM)
	 * 
	 * @return int
	 */
	public int getExpansionFlags() {
		return expansionFlags;
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	protected java.io.OutputStream getOutputStreamForResource(Resource aResource) throws java.io.IOException {
		return getUriConverter().createOutputStream(aResource.getURI());
	}

	/**
	 * Insert the method's description here. Creation date: (12/08/00 4:50:32 PM)
	 * 
	 * @return org.eclipse.emf.ecore.resource.URIConverter
	 */
	public org.eclipse.emf.ecore.resource.URIConverter getUriConverter() {
		return uriConverter;
	}

	public void initialize() {
		ArchiveURIConverterImpl converter = new ArchiveURIConverterImpl(getArchive(), getDirectoryName());
		converter.setOutputFilepath(getDirectoryName());
		setUriConverter(converter);
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public boolean isDirectory() {
		return true;
	}

	protected void mkdirs(String directoryPath) {
		File aDirectory = new File(getDirectoryName() + File.separatorChar + directoryPath);
		aDirectory.mkdirs();
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void save(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest aManifest) throws SaveFailureException {
		try {
			OutputStream out = getUriConverter().createOutputStream(URI.createURI(ArchiveConstants.MANIFEST_URI));
			aManifest.write(out);
			out.close();
		} catch (IOException iox) {
			throw new SaveFailureException(ArchiveConstants.MANIFEST_URI, iox);
		}
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	public void save(org.eclipse.jst.j2ee.commonarchivecore.internal.File aFile, InputStream in) throws SaveFailureException {
		String entryName = aFile.getURI();
		if (aFile.isDirectoryEntry())
			mkdirs(entryName);
		else {
			try {
				OutputStream out = getUriConverter().createOutputStream(URI.createURI(entryName));
				org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil.copy(in, out);
			} catch (IOException iox) {
				throw new SaveFailureException(aFile.getURI(), iox);
			}
		}
	}

	protected void saveMofResource(org.eclipse.emf.ecore.resource.Resource aResource, OutputStream os) throws IOException {
		super.saveMofResource(aResource, os);
		os.close();
	}

	/**
	 * Insert the method's description here. Creation date: (11/15/00 2:26:37 PM)
	 * 
	 * @param newDirectoryName
	 *            java.lang.String
	 */
	public void setDirectoryName(java.lang.String newDirectoryName) {
		directoryName = newDirectoryName;
	}

	/**
	 * Insert the method's description here. Creation date: (12/19/00 10:18:21 AM)
	 * 
	 * @param newExpansionFlags
	 *            int
	 */
	public void setExpansionFlags(int newExpansionFlags) {
		expansionFlags = newExpansionFlags;
	}

	/**
	 * Insert the method's description here. Creation date: (12/08/00 4:50:32 PM)
	 * 
	 * @param newUriConverter
	 *            org.eclipse.emf.ecore.resource.URIConverter
	 */
	public void setUriConverter(org.eclipse.emf.ecore.resource.URIConverter newUriConverter) {
		uriConverter = newUriConverter;
	}

	protected boolean shouldExpand(Archive anArchive) {

		int flag = 0;

		if (anArchive.isWARFile())
			flag = Archive.EXPAND_WAR_FILES;
		else if (anArchive.isEARFile())
			flag = Archive.EXPAND_EAR_FILES;
		else if (anArchive.isEJBJarFile())
			flag = Archive.EXPAND_EJBJAR_FILES;
		else if (anArchive.isRARFile())
			flag = Archive.EXPAND_RAR_FILES;
		else if (anArchive.isApplicationClientFile())
			flag = Archive.EXPAND_APPCLIENT_FILES;
		else
			flag = Archive.EXPAND_ARCHIVES;

		return (getExpansionFlags() & flag) != 0;

	}
}
