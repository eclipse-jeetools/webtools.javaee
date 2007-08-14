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

import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveSaveAdapter;

public class ArchiveFactoryImpl implements IArchiveFactory {

	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException {
		java.io.File file = new java.io.File(archivePath.toOSString());
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(file);
		} catch (ZipException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		} catch (IOException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		}
		ZipFileArchiveLoadAdapterImpl loadAdapter = new ZipFileArchiveLoadAdapterImpl(zipFile);
		ArchiveOptions archiveOptions = new ArchiveOptions();
		archiveOptions.setOption(ArchiveOptions.LOAD_ADAPTER, loadAdapter);
		archiveOptions.setOption(ArchiveOptions.ARCHIVE_PATH, archivePath);
		return openArchive(archiveOptions);
	}

	// TODO add tracing support
	// info in spec page 154
	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		ArchiveImpl archive = new ArchiveImpl(archiveOptions);
		archive.setArchiveFactory(this);
		return archive;
	}

	public void closeArchive(IArchive archive) {
		((ArchiveImpl) archive).close();
		// TODO add tracing support
	}
	
	public void saveArchive(IArchive archive, IPath outputPath, IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SAVE_TICKS = 198;
		final int CLEANUP_TICKS = 1;
		final int TOTAL_TICKS = SAVE_TICKS + CLEANUP_TICKS;
		try {
			monitor.beginTask("Saving archive to: " + outputPath.toOSString(), TOTAL_TICKS);
			String aUri = outputPath.toOSString();
			java.io.File aFile = new java.io.File(aUri);
			ArchiveUtil.checkWriteable(aFile);
			boolean fileExisted = aFile.exists();
			IArchiveSaveAdapter aSaveAdapter = null;
			try {
				java.io.File destinationFile = fileExisted ? ArchiveUtil.createTempFile(aUri, aFile.getCanonicalFile().getParentFile()) : aFile;
				aSaveAdapter = createSaveAdapterForJar(destinationFile);
				aSaveAdapter.setArchive(archive);
				save(aSaveAdapter, new SubProgressMonitor(monitor, SAVE_TICKS));

				aSaveAdapter.close();
				if (fileExisted) {
					ArchiveUtil.cleanupAfterTempSave(aUri, aFile, destinationFile);
				}
				monitor.worked(CLEANUP_TICKS);
			} catch (java.io.IOException e) {
				throw new ArchiveSaveFailureException("Error saving archive: " + archive + " to output path: " + outputPath, e);
			} catch (ArchiveSaveFailureException failure) {
				try {
					if (aSaveAdapter != null)
						aSaveAdapter.close();
				} catch (IOException weTried) {
					// Ignore
				}
				if (!fileExisted)
					aFile.delete();
				throw failure;
			}
		} finally {
			monitor.done();
		}
	}

	protected IArchiveSaveAdapter createSaveAdapterForJar(java.io.File aFile) throws java.io.IOException {
		if (aFile.exists() && aFile.isDirectory()) {
			throw new IOException("The specified file: " + aFile.getAbsolutePath() + " exists and is a directory");
		}
		java.io.File parent = aFile.getParentFile();
		if (parent != null)
			parent.mkdirs();
		java.io.OutputStream out = new java.io.FileOutputStream(aFile);
		return new ZipStreamArchiveSaveAdapterImpl(out);
	}

	protected void save(IArchiveSaveAdapter anAdapter, IProgressMonitor monitor) throws ArchiveSaveFailureException {
		try {
			anAdapter.save(monitor);
		} finally {
			try {
				anAdapter.close();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			}
		}
	}

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions, IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SAVE_TICKS = 198;
		final int CLOSE_TICKS = 2;
		final int TOTAL_TICKS = SAVE_TICKS + CLOSE_TICKS;
		try {
			monitor.beginTask("Saving archive", TOTAL_TICKS);
			IArchiveSaveAdapter aSaveAdapter = (IArchiveSaveAdapter) archiveOptions.getOption(ArchiveOptions.SAVE_ADAPTER);
			try {
				aSaveAdapter.setArchive(archive);
				save(aSaveAdapter, new SubProgressMonitor(monitor, SAVE_TICKS));

				aSaveAdapter.close();
				monitor.worked(CLOSE_TICKS);
			} catch (ArchiveSaveFailureException failure) {
				try {
					if (aSaveAdapter != null)
						aSaveAdapter.close();
				} catch (IOException weTried) {
					// Ignore
				}
				throw failure;
			} catch (java.io.IOException ex) {
				throw new ArchiveSaveFailureException("Error saving archive: " + archive);
			}
		} finally {
			monitor.done();
		}
	}

}
