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



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityURIConverter;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;


/**
 * This is a concrete implentation of SaveStrategy. All the contents of the archive, including xmi
 * resources, will be output to a ZipOutputStream. The output stream should be passed in by the
 * client.
 */
public class ZipStreamSaveStrategyImpl extends SaveStrategyImpl implements SaveStrategy {
	protected OutputStream destinationStream;
	/** Used internally */
	protected ZipOutputStream zipOutputStream;

	/**
	 * Wraps a new zip output stream around the parameter
	 */
	public ZipStreamSaveStrategyImpl(OutputStream out) {
		setDestinationStream(out);
		setZipOutputStream(new ZipOutputStream(out));
	}

	public void close() throws IOException {
		getDestinationStream().close();
	}

	protected SaveStrategy createNestedSaveStrategy(Archive anArchive) {
		return new ZipStreamSaveStrategyImpl(getZipOutputStream());
	}

	public void finish() throws IOException {
		getZipOutputStream().finish();
		//If this is not nested, close the stream to free up the resource
		//otherwise, don't close it because the parent may not be done
		if (!(getDestinationStream() instanceof ZipOutputStream))
			getDestinationStream().close();
	}

	/**
	 * @return java.io.OutputStream
	 */
	public java.io.OutputStream getDestinationStream() {
		return destinationStream;
	}

	/**
	 * @see com.ibm.etools.archive.impl.SaveStrategyImpl
	 */
	protected java.io.OutputStream getOutputStreamForResource(Resource aResource) throws java.io.IOException {
		return getZipOutputStream();
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy#saveMofResource(Resource)
	 */
	public void saveMofResource(Resource aResource, OutputStream out) throws IOException {
		Revisit.revisit();
		URI uri = aResource.getURI();
		//Ensure container relative URI
		URIConverter conv = getArchive().getResourceSet().getURIConverter();
		if (conv instanceof CompatibilityURIConverter)
			uri = ((CompatibilityURIConverter) conv).deNormalize(uri);
		ZipEntry entry = new ZipEntry(uri.toString());
		zipOutputStream.putNextEntry(entry);
		super.saveMofResource(aResource, out);
		zipOutputStream.closeEntry();
	}

	/**
	 * @return java.util.zip.ZipOutputStream
	 */
	protected java.util.zip.ZipOutputStream getZipOutputStream() {
		return zipOutputStream;
	}

	/**
	 * @see com.ibm.etools.archive.SaveStrategy
	 */
	public void save(org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveManifest aManifest) throws SaveFailureException {
		try {
			ZipEntry entry = new ZipEntry(ArchiveConstants.MANIFEST_URI);
			getZipOutputStream().putNextEntry(entry);
			aManifest.write(getZipOutputStream());
			getZipOutputStream().closeEntry();
		} catch (IOException iox) {
			throw new SaveFailureException(ArchiveConstants.MANIFEST_URI, iox);
		}
	}

	public void save(Archive anArchive) throws SaveFailureException {
		try {
			ZipEntry entry = new ZipEntry(anArchive.getURI());
			getZipOutputStream().putNextEntry(entry);
			super.save(anArchive);
			getZipOutputStream().closeEntry();
		} catch (IOException iox) {
			throw new SaveFailureException(anArchive.getURI(), iox);
		}
	}

	public void save(File aFile, InputStream in) throws SaveFailureException {
		try {
			String entryName = aFile.getURI();
			if (aFile.isDirectoryEntry() && !entryName.endsWith("/"))//$NON-NLS-1$
				entryName = entryName + '/';
			ZipEntry entry = new ZipEntry(entryName);
			if (aFile.getLastModified() > 0)
				entry.setTime(aFile.getLastModified());
			getZipOutputStream().putNextEntry(entry);
			if (!aFile.isDirectoryEntry()) {
				org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil.copy(in, getZipOutputStream());
			}
			getZipOutputStream().closeEntry();
		} catch (IOException iox) {
			throw new SaveFailureException(aFile.getURI(), iox);
		}
	}

	/**
	 * @param newDestinationStream
	 *            java.io.OutputStream
	 */
	public void setDestinationStream(java.io.OutputStream newDestinationStream) {
		destinationStream = newDestinationStream;
	}

	/**
	 * @param newZipOutputStream
	 *            java.util.zip.ZipOutputStream
	 */
	protected void setZipOutputStream(java.util.zip.ZipOutputStream newZipOutputStream) {
		zipOutputStream = newZipOutputStream;
	}
}
