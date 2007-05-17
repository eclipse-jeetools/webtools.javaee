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
		return openArchive(archiveOptions);
	}

	// TODO add tracing support
	// info in spec page 154
	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		IArchive archive = new ArchiveImpl(archiveOptions);
		return archive;
	}

	public void closeArchive(IArchive archive) {
		((ArchiveImpl) archive).close();
		// TODO add tracing support
	}

	public void saveArchive(IArchive archive, IPath outputPath) throws ArchiveSaveFailureException {
		String aUri = outputPath.toOSString();
		java.io.File aFile = new java.io.File(aUri);
		ArchiveUtil.checkWriteable(aFile);
		boolean fileExisted = aFile.exists();
		IArchiveSaveAdapter aSaveAdapter = null;
		try {
			try {
				java.io.File destinationFile = fileExisted ? ArchiveUtil.createTempFile(aUri, aFile.getCanonicalFile().getParentFile()) : aFile;
				aSaveAdapter = createSaveAdapterForJar(destinationFile);
				aSaveAdapter.setArchive(archive);
				save(aSaveAdapter);

				aSaveAdapter.close();
				closeArchive(archive);
				if (fileExisted) {
					ArchiveUtil.cleanupAfterTempSave(aUri, aFile, destinationFile);
				}
			} catch (java.io.IOException ex) {
				// TODO throw new
				// SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_,
				// (new Object[]{aUri})), ex); // = "Error saving "
			}
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
	}

	protected IArchiveSaveAdapter createSaveAdapterForJar(java.io.File aFile) throws java.io.IOException {
		if (aFile.exists() && aFile.isDirectory()) {
			// TODO throw new
			// IOException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_exist_as_dir_EXC_,
			// (new Object[]{aFile.getAbsolutePath()})));// = "A file named {0}
			// exists and is a directory"
		}
		java.io.File parent = aFile.getParentFile();
		if (parent != null)
			parent.mkdirs();
		java.io.OutputStream out = new java.io.FileOutputStream(aFile);
		return new ZipStreamArchiveSaveAdapterImpl(out);
	}

	public void save(IArchiveSaveAdapter anAdapter) throws ArchiveSaveFailureException {
		try {
			anAdapter.save();
		} finally {
			try {
				anAdapter.close();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			}
		}
	}

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions) throws ArchiveSaveFailureException {
		IArchiveSaveAdapter aSaveAdapter = (IArchiveSaveAdapter)archiveOptions.getOption(ArchiveOptions.SAVE_ADAPTER);
		try {
			aSaveAdapter.setArchive(archive);
			save(aSaveAdapter);

			aSaveAdapter.close();
			closeArchive(archive);
		} catch (ArchiveSaveFailureException failure) {
			try {
				if (aSaveAdapter != null)
					aSaveAdapter.close();
			} catch (IOException weTried) {
				// Ignore
			}
			throw failure;
		} catch (java.io.IOException ex) {
			// TODO throw new
			// SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_,
			// (new Object[]{aUri})), ex); // = "Error saving "
		}
		
	}

}
