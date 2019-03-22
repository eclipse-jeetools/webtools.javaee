/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;

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
		((ArchiveImpl)archive).addArchiveResourceInternal(this);
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
		case IArchiveResource.ARCHIVE_TYPE:
			IArchiveLoadAdapter loadAdapter = null;
			loadAdapter = getArchive().getLoadAdapter();
			return loadAdapter.getInputStream(this);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		switch (getType()) {
		case FILE_TYPE:
			buffer.append("File: "); //$NON-NLS-1$
			break;
		case DIRECTORY_TYPE:
			buffer.append("Directory: "); //$NON-NLS-1$
			break;
		case ARCHIVE_TYPE:
			buffer.append("Archive: "); //$NON-NLS-1$
			break;
		case UNKNOWN_TYPE:
			buffer.append("Unknown: "); //$NON-NLS-1$
		}
		buffer.append(getPath());
		return buffer.toString();
	}

	protected void dispose() {
		archive = null;
	}

}
