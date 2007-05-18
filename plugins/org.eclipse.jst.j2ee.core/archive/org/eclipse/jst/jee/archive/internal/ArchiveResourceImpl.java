/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.IArchiveSaveAdapter;



public class ArchiveResourceImpl implements IArchiveResource {

	private IArchive archive = null;

	private int type = UNKNOWN_TYPE;

	private long size = -1;

	private long lastModified = -1;

	private IPath path = null;

	public IArchive getArchive() {
		return archive;
	}

	public int getType() {
		return type;
	}

	public long getLastModified() {
		return lastModified;
	}

	public IPath getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

	public void setArchive(IArchive archive) {
		this.archive = archive;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setPath(IPath path) {
		this.path = path;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public InputStream getInputStream() throws FileNotFoundException, IOException {
		switch (getType()) {
		case IArchiveResource.DIRECTORY_TYPE:
		case IArchiveResource.UNKNOWN_TYPE:
			return null;
		case IArchiveResource.FILE_TYPE:
			IArchiveLoadAdapter loadAdapter = null;
			loadAdapter = getArchive().getLoadAdapter();
			return loadAdapter.getInputStream(this);
		case IArchiveResource.ARCHIVE_TYPE:
			IArchive thisArchive = (IArchive) this;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			IArchiveSaveAdapter saveAdapter = new ZipStreamArchiveSaveAdapterImpl(byteOut);
			ArchiveOptions archiveOptions = new ArchiveOptions();
			archiveOptions.setOption(ArchiveOptions.SAVE_ADAPTER, saveAdapter);
			try {
				IArchiveFactory.INSTANCE.saveArchive(thisArchive, archiveOptions);
			} catch (ArchiveSaveFailureException e) {
				throw new IOException("Unable to save nested Archive " + getPath() + " nested exception = " + e.getMessage());  //$NON-NLS-1$//$NON-NLS-2$
			}
			return new ByteArrayInputStream(byteOut.toByteArray());

		}
		return null;
	}

}
