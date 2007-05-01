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
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadStrategy;
import org.eclipse.jst.jee.archive.IArchiveResource;


public class ArchiveImpl extends ArchiveResourceImpl implements IArchive {

	private ArchiveOptions archiveOptions;

	private IArchiveLoadStrategy loadStrategy;

	private class ArchiveFileIndex {
		private Map index = new HashMap();

		private List fullIndex = null;

		private boolean fullyIndexed = false;

		public ArchiveFileIndex() {
		}

		public synchronized boolean containsFile(IPath archiveRelativePath) {
			return index.containsKey(archiveRelativePath);
		}

		public synchronized IArchiveResource getFile(IPath archiveRelativePath) {
			IArchiveResource aFile = (IArchiveResource) index.get(archiveRelativePath);
			return aFile;
		}

		public synchronized void noteEmptyFile(IPath archiveRelativePath) {
			if (isFullyIndexed()) {
				throw new RuntimeException("Attempting to modify a fully indexed file list"); //$NON-NLS-1$
			}
			index.put(archiveRelativePath, null);
		}

		public synchronized void addFile(IArchiveResource aFile) {
			if (isFullyIndexed()) {
				throw new RuntimeException("Attempting to modify a fully indexed file list"); //$NON-NLS-1$
			}
			index.put(aFile.getPath(), aFile);
		}

		public synchronized boolean isFullyIndexed() {
			return fullyIndexed;
		}

		public void fullyIndex(List files) {
			synchronized (this) {
				if (fullyIndexed) {
					if (isFullyIndexed()) {
						throw new RuntimeException("Attempting to modify a fully indexed file list"); //$NON-NLS-1$
					}
				}
				fullyIndexed = true;
			}

			for (int i = 0; i < files.size(); i++) {
				IArchiveResource aFile = (IArchiveResource) files.get(i);
				synchronized (this) {
					if (!index.containsKey(aFile.getPath())) {
						index.put(aFile.getPath(), aFile);
					}
				}
			}
		}

		public synchronized List getFullIndex() {
			if (!isFullyIndexed()) {
				throw new RuntimeException("File list has not been fully indexed"); //$NON-NLS-1$
			}
			if (fullIndex == null) {
				List list = new ArrayList();
				list.addAll(index.values());
				fullIndex = Collections.unmodifiableList(list);
			}
			return fullIndex;
		}
	};

	private ArchiveFileIndex archiveFileIndex = new ArchiveFileIndex();

	private FailedToCloseException openendBy = null;

	public ArchiveImpl(ArchiveOptions archiveOptions) {
		setArchiveOptions(archiveOptions);
		loadStrategy = (IArchiveLoadStrategy) getArchiveOptions().getOption(ArchiveOptions.LOAD_STRATEGY);
		loadStrategy.setArchive(this);
		openendBy = new FailedToCloseException();
	}

	public boolean isOpen() {
		return openendBy != null;
	}

	public void close() {
		openendBy = null;
		loadStrategy.close();
	}

	public IArchiveResource getArchiveResource(IPath archiveRelativePath) throws FileNotFoundException {
		IArchiveResource aFile = null;
		if (archiveFileIndex.containsFile(archiveRelativePath)) {
			aFile = archiveFileIndex.getFile(archiveRelativePath);
		} else if (!archiveFileIndex.isFullyIndexed()) {
			aFile = loadStrategy.getArchiveResource(archiveRelativePath);
			if (aFile == null) {
				archiveFileIndex.noteEmptyFile(archiveRelativePath);
			} else {
				archiveFileIndex.addFile(aFile);
			}
		}
		return aFile;
	}

	public List getArchiveResources() {
		synchronized (this) {
			if (!archiveFileIndex.isFullyIndexed()) {
				archiveFileIndex.fullyIndex(loadStrategy.getArchiveResource());
			}
		}
		return archiveFileIndex.getFullIndex();
	}

	public IArchiveLoadStrategy getLoadStrategy() {
		return loadStrategy;
	}

	protected void setArchiveOptions(ArchiveOptions archiveOptions) {
		this.archiveOptions = archiveOptions;
	}

	public ArchiveOptions getArchiveOptions() {
		return archiveOptions;
	}

	public String toString() {
		return loadStrategy.toString();
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
		return containsModelObject(new Path("/")); //$NON-NLS-1$
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		return getLoadStrategy().containsModelObject(modelObjectPath);
	}

	public Object getModelObject() throws ArchiveModelLoadException {
		return getModelObject(new Path("/")); //$NON-NLS-1$
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return getLoadStrategy().getModelObject(modelObjectPath);
	}

	public boolean containsArchiveResource(IPath archiveRelativePath) {
		try {
			return null != getLoadStrategy().getArchiveResource(archiveRelativePath);
		} catch (FileNotFoundException e) {
			// catch this exception
		}
		return false;
	}

}