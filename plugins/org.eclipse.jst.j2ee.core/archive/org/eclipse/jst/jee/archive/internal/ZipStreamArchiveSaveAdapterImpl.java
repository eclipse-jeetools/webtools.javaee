/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;



import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.jee.archive.AbstractArchiveSaveAdapter;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.IArchiveSaveAdapter;



/**
 * This is a concrete implentation of IArchiveSaveAdapter. All the contents of the archive, including xmi
 * resources, will be output to a ZipOutputStream. The output stream should be passed in by the
 * client.
 */
public class ZipStreamArchiveSaveAdapterImpl extends AbstractArchiveSaveAdapter {
	protected OutputStream destinationStream;
	/** Used internally */
	protected ZipOutputStream zipOutputStream;

	/**
	 * Wraps a new zip output stream around the parameter
	 */
	public ZipStreamArchiveSaveAdapterImpl(OutputStream out) {
		setDestinationStream(out);
		setZipOutputStream(new ZipOutputStream(out));
	}

	@Override
	public void close() throws IOException {
		getDestinationStream().close();
	}

	protected IArchiveSaveAdapter createNestedSaveAdapter(IArchive anArchive) {
		return new ZipStreamArchiveSaveAdapterImpl(getZipOutputStream());
	}

	@Override
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

	protected java.io.OutputStream getOutputStreamForResource(Resource aResource) throws java.io.IOException {
		return getZipOutputStream();
	}

	/**
	 * @return java.util.zip.ZipOutputStream
	 */
	protected java.util.zip.ZipOutputStream getZipOutputStream() {
		return zipOutputStream;
	}

	@Override
	protected void save(IArchiveResource resource) throws ArchiveSaveFailureException {
		try {
			IPath path = resource.getPath();
			if(resource.getType() == IArchiveResource.DIRECTORY_TYPE && !path.hasTrailingSeparator()){
				path = path.addTrailingSeparator();
			}
			
			ZipEntry entry = new ZipEntry(path.toString());
			if (resource.getLastModified() > 0)
				entry.setTime(resource.getLastModified());
			getZipOutputStream().putNextEntry(entry);
			if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
				ArchiveUtil.copy(resource.getInputStream(), getZipOutputStream());
			}
			getZipOutputStream().closeEntry();
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
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
