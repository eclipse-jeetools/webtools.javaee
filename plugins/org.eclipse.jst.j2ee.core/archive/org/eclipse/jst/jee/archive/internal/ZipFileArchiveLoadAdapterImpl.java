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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities;
import org.eclipse.jst.jee.archive.AbstractArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchiveResource;



public class ZipFileArchiveLoadAdapterImpl extends AbstractArchiveLoadAdapter {
	protected ZipFile zipFile;

	public ZipFileArchiveLoadAdapterImpl() {
		super();
	}

	public ZipFileArchiveLoadAdapterImpl(ZipFile zipFile) {
		super();
		this.zipFile = zipFile;
	}

	@Override
	public void close() {
		super.close();
		try {
			getZipFile().close();
		} catch (IOException e) {
			// Ignore
		}
	}

	@Override
	public boolean containsArchiveResource(IPath resourcePath) {
		ZipEntry entry = getZipFile().getEntry(resourcePath.toString());
		return entry != null;
	}
	
	@Override
	public IArchiveResource getArchiveResource(IPath filePath) {
		ZipEntry entry = getZipFile().getEntry(filePath.toString());
		IArchiveResource aFile = null;
		if (entry != null) {
			aFile = createFile(entry);
		}
		return aFile;
	}

	@Override
	public java.util.List getArchiveResources() {
		List list = new ArrayList();
		Enumeration entries = getZipFile().entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			IArchiveResource aFile = createFile(entry);
			list.add(aFile);
		}
		return list;
	}

	protected IArchiveResource createFile(ZipEntry entry) {
		IPath path = new Path(entry.getName()).makeRelative();
		IArchiveResource aFile = null;
		if (!entry.isDirectory()) {
			aFile = createFile(path);
			aFile.setSize(entry.getSize());
			aFile.setLastModified(entry.getTime());
		} else { // always include directories
			aFile = createDirectory(path);
			aFile.setSize(entry.getSize());
			aFile.setLastModified(entry.getTime());
		}
		return aFile;
	}

	@Override
	public java.io.InputStream getInputStream(IArchiveResource aFile) throws IOException, FileNotFoundException {
		IPath path = aFile.getPath();
		String uri = path.toString();
		return ManifestUtilities.getInputstreamForZipEntry(getZipFile(), uri);
	}

	public java.util.zip.ZipFile getZipFile() {
		return zipFile;
	}

	public void setZipFile(java.util.zip.ZipFile newZipFile) {
		zipFile = newZipFile;
	}
	
	@Override
	public String toString() {
		return zipFile.getName();
	}

	@Override
	public boolean containsModelObject(IPath modelObjectPath) {
		return false;
	}

	@Override
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		throw new ArchiveModelLoadException("Simple Zip Archives have no model objects."); //$NON-NLS-1$
	}

}
