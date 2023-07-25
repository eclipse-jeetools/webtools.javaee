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
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.osgi.util.NLS;

public class ArchiveFactoryImpl implements IArchiveFactory {

	@Override
	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException {
		java.io.File file = new java.io.File(archivePath.toOSString());
		ZipFile zipFile;
		try {
			zipFile = ArchiveUtil.newZipFile(file);
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
	@Override
	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		ArchiveImpl archive = new ArchiveImpl(archiveOptions);
		archive.setArchiveFactory(this);
		return archive;
	}

	@Override
	public void closeArchive(IArchive archive) {
		((ArchiveImpl) archive).close();
		// TODO add tracing support
	}
	
	@Override
	public void saveArchive(IArchive archive, IPath outputPath, IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SAVE_TICKS = 198;
		final int CLEANUP_TICKS = 1;
		final int TOTAL_TICKS = SAVE_TICKS + CLEANUP_TICKS;
		try {
			monitor.beginTask(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_Saving_archive_to_0_, outputPath.toOSString()), TOTAL_TICKS);
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
				throw new ArchiveSaveFailureException(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_Error_saving_archive_0_to_output_, new Object[] { archive, outputPath }), e);
			} catch (ArchiveSaveFailureException failure) {
				try {
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
			throw new IOException(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_The_specified_file_0_exists_and_, aFile.getAbsolutePath()));
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

	@Override
	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions, IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SAVE_TICKS = 198;
		final int CLOSE_TICKS = 2;
		final int TOTAL_TICKS = SAVE_TICKS + CLOSE_TICKS;
		try {
			monitor.beginTask(CommonArchiveResourceHandler.ArchiveFactoryImpl_Saving_archiv_, TOTAL_TICKS);
			IArchiveSaveAdapter aSaveAdapter = (IArchiveSaveAdapter) archiveOptions.getOption(ArchiveOptions.SAVE_ADAPTER);
			try {
				aSaveAdapter.setArchive(archive);
				save(aSaveAdapter, new SubProgressMonitor(monitor, SAVE_TICKS));

				aSaveAdapter.close();
				monitor.worked(CLOSE_TICKS);
			} catch (ArchiveSaveFailureException failure) {
				try {
					aSaveAdapter.close();
				} catch (IOException weTried) {
					// Ignore
				}
				throw failure;
			} catch (java.io.IOException ex) {
				throw new ArchiveSaveFailureException(NLS.bind(CommonArchiveResourceHandler.ArchiveFactoryImpl_Error_saving_archive_0_, archive));
			}
		} finally {
			monitor.done();
		}
	}

}
