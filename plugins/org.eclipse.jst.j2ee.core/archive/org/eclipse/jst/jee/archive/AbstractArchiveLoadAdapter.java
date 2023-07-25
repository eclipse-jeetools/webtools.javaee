/*******************************************************************************
 * Copyright (c) 2005, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.jee.archive.internal.ArchiveResourceImpl;
import org.eclipse.jst.jee.archive.internal.ZipStreamArchiveSaveAdapterImpl;

/**
 * Abstract implementation of {@link IArchiveLoadAdapter} intended for
 * subclassing by clients. See {@link IArchiveLoadAdapter} for details.
 */
public abstract class AbstractArchiveLoadAdapter extends AbstractArchiveAdapter implements IArchiveLoadAdapter {

	/**
	 * Subclasses may wish to override.
	 */
	@Override
	public void close() {
	}

	/**
	 * Utility method for creating an {@link IArchiveResource} representing a
	 * directory entry for the passed {@link IPath}.
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createDirectory(IPath archiveRelativePath) {
		verifyRelative(archiveRelativePath);
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(archiveRelativePath);
		aFile.setType(IArchiveResource.DIRECTORY_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}

	/**
	 * Utility method for creating an {@link IArchiveResource} representing a
	 * file entry for the passed {@link IPath}.
	 * 
	 * @param path
	 * @return
	 */
	protected IArchiveResource createFile(IPath archiveRelativePath) {
		verifyRelative(archiveRelativePath);
		IArchiveResource aFile = null;
		aFile = new ArchiveResourceImpl();
		aFile.setPath(archiveRelativePath);
		aFile.setType(IArchiveResource.FILE_TYPE);
		aFile.setArchive(getArchive());
		return aFile;
	}

	/**
	 * Utility method for creating an empty {@link IArchiveResource} manfiest
	 * file.
	 * 
	 * @param manifestPath
	 * @return
	 */
	protected IArchiveResource createManifest(IPath manifestPath) {
		verifyRelative(manifestPath);
		IArchiveResource manifest = null;
		manifest = new ArchiveResourceImpl() {
			@Override
			public InputStream getInputStream() throws FileNotFoundException, IOException {
				String manifestContents = "Manifest-Version: 1.0\r\n\r\n"; //$NON-NLS-1$
				return new BufferedInputStream(new ByteArrayInputStream(manifestContents.getBytes()));
			}
		};
		manifest.setPath(manifestPath);
		manifest.setType(IArchiveResource.FILE_TYPE);
		manifest.setArchive(getArchive());
		return manifest;
	}

	public static void verifyRelative(IPath archiveRelativePath) {
		if (archiveRelativePath.isAbsolute() && !archiveRelativePath.equals(IArchive.EMPTY_MODEL_PATH)) {
			throw new RuntimeException(archiveRelativePath + " must be relative."); //$NON-NLS-1$
		}
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException {
		if (archiveResource.getType() == IArchive.ARCHIVE_TYPE) {
			IArchive thisArchive = (IArchive) archiveResource;
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			IArchiveSaveAdapter saveAdapter = new ZipStreamArchiveSaveAdapterImpl(byteOut);
			ArchiveOptions archiveOptions = new ArchiveOptions();
			archiveOptions.setOption(ArchiveOptions.SAVE_ADAPTER, saveAdapter);
			try {
				IArchiveFactory.INSTANCE.saveArchive(thisArchive, archiveOptions, new NullProgressMonitor());
			} catch (ArchiveSaveFailureException e) {
				throw new IOException("Unable to save nested Archive " + archiveResource.getPath() + " nested exception = " + e.getMessage()); //$NON-NLS-1$//$NON-NLS-2$
			}
			return new ByteArrayInputStream(byteOut.toByteArray());
		}
		return null;
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public boolean containsArchiveResource(IPath resourcePath) {
		return false;
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public boolean containsModelObject(IPath modelObjectPath) {
		return false;
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException {
		return null;
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public List<IArchiveResource> getArchiveResources() {
		return null;
	}

	/**
	 * Default implementation; subclasses should override as necessary.
	 */
	@Override
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return null;
	}

}
