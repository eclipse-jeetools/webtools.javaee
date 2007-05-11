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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.AbstractArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;

public class ArchiveImpl extends ArchiveResourceImpl implements IArchive {

	private ArchiveOptions archiveOptions;

	private IArchiveLoadAdapter loadAdapter;

	private class ArchiveFileIndex {
		private Map<IPath, IArchiveResource> index = new HashMap<IPath, IArchiveResource>();

		private List<IArchiveResource> fullIndex = null;

		private boolean fullyIndexed = false;

		public ArchiveFileIndex() {
		}

		public synchronized boolean containsFile(IPath archiveRelativePath) {
			AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
			return index.containsKey(archiveRelativePath);
		}

		public synchronized IArchiveResource getFile(IPath archiveRelativePath) {
			AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
			IArchiveResource aFile = index.get(archiveRelativePath);
			return aFile;
		}

		public synchronized void noteEmptyFile(IPath archiveRelativePath) {
			verifyNotFullyIndexed();
			AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
			index.put(archiveRelativePath, null);
		}

		public synchronized void addFile(IArchiveResource aFile) {
			verifyNotFullyIndexed();
			AbstractArchiveLoadAdapter.verifyRelative(aFile.getPath());
			index.put(aFile.getPath(), aFile);
		}

		public synchronized boolean isFullyIndexed() {
			return fullyIndexed;
		}

		public void fullyIndex(List files) {
			synchronized (this) {
				if (fullyIndexed) {
					verifyNotFullyIndexed();
				}
				fullyIndexed = true;
			}

			for (int i = 0; i < files.size(); i++) {
				IArchiveResource aFile = (IArchiveResource) files.get(i);
				AbstractArchiveLoadAdapter.verifyRelative(aFile.getPath());
				synchronized (this) {
					if (!index.containsKey(aFile.getPath())) {
						index.put(aFile.getPath(), aFile);
					}
				}
			}
		}

		public synchronized List<IArchiveResource> getFullIndex() {
			if (!isFullyIndexed()) {
				throw new RuntimeException("File list has not been fully indexed"); //$NON-NLS-1$
			}
			if (fullIndex == null) {
				List<IArchiveResource> list = new ArrayList<IArchiveResource>();
				list.addAll(index.values());
				fullIndex = Collections.unmodifiableList(list);
			}
			return fullIndex;
		}

		private void verifyNotFullyIndexed() {
			if (isFullyIndexed()) {
				throw new RuntimeException("Attempting to modify a fully indexed file list"); //$NON-NLS-1$
			}
		}
	};

	private ArchiveFileIndex archiveFileIndex = new ArchiveFileIndex();

	private FailedToCloseException openendBy = null;

	public ArchiveImpl(ArchiveOptions archiveOptions) {
		setArchiveOptions(archiveOptions);
		loadAdapter = (IArchiveLoadAdapter) getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
		loadAdapter.setArchive(this);
		openendBy = new FailedToCloseException();
	}

	public boolean isOpen() {
		return openendBy != null;
	}

	public void close() {
		openendBy = null;
		loadAdapter.close();
	}

	public IArchiveResource getArchiveResource(IPath archiveRelativePath) throws FileNotFoundException {
		AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
		IArchiveResource aFile = null;
		if (archiveFileIndex.containsFile(archiveRelativePath)) {
			aFile = archiveFileIndex.getFile(archiveRelativePath);
		} else if (!archiveFileIndex.isFullyIndexed()) {
			aFile = loadAdapter.getArchiveResource(archiveRelativePath);
			if (aFile == null) {
				archiveFileIndex.noteEmptyFile(archiveRelativePath);
			} else {
				archiveFileIndex.addFile(aFile);
			}
		}
		return aFile;
	}

	public List<IArchiveResource> getArchiveResources() {
		synchronized (this) {
			if (!archiveFileIndex.isFullyIndexed()) {
				archiveFileIndex.fullyIndex(loadAdapter.getArchiveResources());
			}
		}
		return archiveFileIndex.getFullIndex();
	}

	public IArchiveLoadAdapter getLoadAdapter() {
		return loadAdapter;
	}

	protected void setArchiveOptions(ArchiveOptions archiveOptions) {
		this.archiveOptions = archiveOptions;
	}

	public ArchiveOptions getArchiveOptions() {
		return archiveOptions;
	}

	public String toString() {
		return loadAdapter.toString();
	}

	protected void finalize() throws Throwable {
		super.finalize();
		if (isOpen()) {
			System.err.println("Archive opener did not close archive: " + this); //$NON-NLS-1$
			System.err.println("Archive was opened here:"); //$NON-NLS-1$
			openendBy.printStackTrace(System.err);
			close();
		}
	}

	public boolean containsModelObject() {
		return containsModelObject(IArchive.EMPTY_MODEL_PATH);
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		AbstractArchiveLoadAdapter.verifyRelative(modelObjectPath);
		return getLoadAdapter().containsModelObject(modelObjectPath);
	}

	public Object getModelObject() throws ArchiveModelLoadException {
		return getModelObject(IArchive.EMPTY_MODEL_PATH);
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		AbstractArchiveLoadAdapter.verifyRelative(modelObjectPath);
		return getLoadAdapter().getModelObject(modelObjectPath);
	}

	public boolean containsArchiveResource(IPath archiveRelativePath) {
		AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
		if (archiveFileIndex.containsFile(archiveRelativePath)) {
			return true;
		} else if (!archiveFileIndex.isFullyIndexed()) {
			return loadAdapter.containsArchiveResource(archiveRelativePath);
		}
		return false;
	}

}