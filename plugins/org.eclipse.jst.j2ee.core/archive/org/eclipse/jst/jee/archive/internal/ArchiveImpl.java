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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.AbstractArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.osgi.util.NLS;

public class ArchiveImpl extends ArchiveResourceImpl implements IArchive {

	private ArchiveOptions archiveOptions;

	private IArchiveLoadAdapter loadAdapter;

	private class ArchiveFileIndex {
		private Map<IPath, IArchiveResource> index = new HashMap<IPath, IArchiveResource>();

		private List<IArchive> nestedArchives = null;

		private List<IArchiveResource> fullIndex = null;

		private boolean fullyIndexed = false;

		public ArchiveFileIndex() {
		}

		public synchronized List<IArchive> getNestedArchives() {
			if (nestedArchives == null) {
				nestedArchives = new ArrayList<IArchive>();
			}
			return nestedArchives;
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

		public void fullyIndex(List<IArchiveResource> files) {
			synchronized (this) {
				if (fullyIndexed) {
					verifyNotFullyIndexed();
				}
				fullyIndexed = true;
			}

			for (IArchiveResource aFile : files) {
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
	}

	private ArchiveFileIndex archiveFileIndex = new ArchiveFileIndex();

	private FailedToCloseException openendBy = null;

	public ArchiveImpl(ArchiveOptions archiveOptions) {
		setType(IArchiveResource.ARCHIVE_TYPE);
		setArchiveOptions(archiveOptions);
		loadAdapter = (IArchiveLoadAdapter) getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
		loadAdapter.setArchive(this);
		openendBy = new FailedToCloseException();
	}

	@Override
	public boolean isOpen() {
		return openendBy != null;
	}

	public void close() {
		if(isOpen()){
			openendBy = null;
			for (IArchive nestedArchive : getNestedArchives()) {
				IArchiveFactory.INSTANCE.closeArchive(nestedArchive);
			}
			loadAdapter.close();
			dispose();
		}
	}

	@Override
	protected void dispose() {
		super.dispose();
		archiveFileIndex = null;
		loadAdapter = null;
		archiveFactory = null;
		archiveOptions = null;
	}

	@Override
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
		if(aFile == null){
			throw new FileNotFoundException(NLS.bind(CommonArchiveResourceHandler.ArchiveImpl_0_in_1_, new Object[] { archiveRelativePath.toString(), toString() }));
		}
		return aFile;
	}

	@Override
	public List<IArchiveResource> getArchiveResources() {
		synchronized (this) {
			if (!archiveFileIndex.isFullyIndexed()) {
				archiveFileIndex.fullyIndex(loadAdapter.getArchiveResources());
			}
		}
		return archiveFileIndex.getFullIndex();
	}

	public void setLoadAdapter(IArchiveLoadAdapter loadAdapter) {
		this.loadAdapter = loadAdapter;
	}
	
	@Override
	public IArchiveLoadAdapter getLoadAdapter() {
		return loadAdapter;
	}

	protected void setArchiveOptions(ArchiveOptions archiveOptions) {
		this.archiveOptions = archiveOptions;
	}

	@Override
	public ArchiveOptions getArchiveOptions() {
		return archiveOptions;
	}

	@Override
	public String toString() {
		return loadAdapter.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (isOpen()) {
			System.err.println("Archive opener did not close archive: " + this); //$NON-NLS-1$
			System.err.println("Archive was opened here:"); //$NON-NLS-1$
			openendBy.printStackTrace(System.err);
			close();
		}
	}

	@Override
	public boolean containsModelObject() {
		return containsModelObject(IArchive.EMPTY_MODEL_PATH);
	}

	@Override
	public boolean containsModelObject(IPath modelObjectPath) {
		AbstractArchiveLoadAdapter.verifyRelative(modelObjectPath);
		return getLoadAdapter().containsModelObject(modelObjectPath);
	}

	@Override
	public Object getModelObject() throws ArchiveModelLoadException {
		return getModelObject(IArchive.EMPTY_MODEL_PATH);
	}

	@Override
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		AbstractArchiveLoadAdapter.verifyRelative(modelObjectPath);
		return getLoadAdapter().getModelObject(modelObjectPath);
	}

	@Override
	public boolean containsArchiveResource(IPath archiveRelativePath) {
		AbstractArchiveLoadAdapter.verifyRelative(archiveRelativePath);
		if (archiveFileIndex.containsFile(archiveRelativePath)) {
			return true;
		} else if (!archiveFileIndex.isFullyIndexed()) {
			return loadAdapter.containsArchiveResource(archiveRelativePath);
		}
		return false;
	}

	@Override
	public IArchive getNestedArchive(IArchiveResource archiveResource) throws ArchiveOpenFailureException {
		try {
			if (archiveResource.getArchive() != this) {
				throw new ArchiveOpenFailureException("Attempted to open nested IArchive " + archiveResource.getPath() + " using an IArchiveResource not contained in this IArchive."); //$NON-NLS-1$//$NON-NLS-2$
			}
			getArchiveResources(); // need to force the full index to be built now.
			IArchiveResource cachedArchiveResource = getArchiveResource(archiveResource.getPath());

			if (cachedArchiveResource.getType() == IArchiveResource.ARCHIVE_TYPE) {
				IArchive nestedArchive = (IArchive) cachedArchiveResource;
				if (!archiveFileIndex.getNestedArchives().contains(nestedArchive)) {
					archiveFileIndex.getNestedArchives().add(nestedArchive);
				}
				return nestedArchive;
			} else if (cachedArchiveResource.getType() == IArchiveResource.DIRECTORY_TYPE) {
				throw new ArchiveOpenFailureException("Attempted to open nested IArchive " + cachedArchiveResource.getPath() + " using a directory."); //$NON-NLS-1$//$NON-NLS-2$
			}
			IArchiveLoadAdapter nestedLoadAdapter = null;

			try {
				java.io.File tempFile = null;
				try {
					tempFile = ArchiveUtil.createTempFile(cachedArchiveResource.getPath().toString());
				} catch (IOException e) {
					ArchiveUtil.warn("Warning: Unable to create temp file for " + cachedArchiveResource.getPath() + ".  This will impact performance."); //$NON-NLS-1$//$NON-NLS-2$
				}
				if (tempFile != null) {
					InputStream in = cachedArchiveResource.getInputStream();
					OutputStream out = new FileOutputStream(tempFile);
					ArchiveUtil.copy(in, out);
					nestedLoadAdapter = new TempZipFileArchiveLoadAdapterImpl(tempFile);
				}
			} catch (IOException e) {
				throw new ArchiveOpenFailureException(e);
			}

			if (nestedLoadAdapter == null) {
				// TODO implement a ZipStream reader if necessary
			}

			ArchiveOptions nestedArchiveOptions = cloneUnknownOptions(archiveOptions);
			nestedArchiveOptions.setOption(ArchiveOptions.PARENT_ARCHIVE, this);
			nestedArchiveOptions.setOption(ArchiveOptions.LOAD_ADAPTER, nestedLoadAdapter);
			nestedArchiveOptions.setOption(ArchiveOptions.ARCHIVE_PATH, cachedArchiveResource.getPath());
			IArchive nestedArchive = archiveFactory.openArchive(nestedArchiveOptions);
			nestedArchive.setPath(cachedArchiveResource.getPath());
			nestedArchive.setArchive(this);
			return nestedArchive;

		} catch (FileNotFoundException e) {
			throw new ArchiveOpenFailureException(e);
		}
	}

	protected ArchiveOptions cloneUnknownOptions(ArchiveOptions archiveOptions){
		ArchiveOptions newOptions = new ArchiveOptions();
		Iterator iterator = archiveOptions.keySet().iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
			if(key == ArchiveOptions.ARCHIVE_PATH || key == ArchiveOptions.LOAD_ADAPTER || key == ArchiveOptions.SAVE_ADAPTER){
				continue;
			} else {
				newOptions.setOption(key, archiveOptions.getOption(key));
			}
		}
		return newOptions;
	}
	
	
	@Override
	public List<IArchive> getNestedArchives() {
		return Collections.unmodifiableList(archiveFileIndex.getNestedArchives());
	}

	/**
	 * Internal
	 * 
	 * @param archiveResource
	 */
	void addArchiveResourceInternal(IArchiveResource archiveResource) {
		archiveFileIndex.index.put(archiveResource.getPath(), archiveResource);
		if(archiveResource.getType() == ARCHIVE_TYPE){
			archiveFileIndex.getNestedArchives().add((IArchive)archiveResource);
		}
		archiveFileIndex.fullIndex = null;
	}
	
	protected IArchiveFactory archiveFactory;
	/**
	 * Internal; clients should not call.
	 * @param archiveFactory
	 */
	public void setArchiveFactory(IArchiveFactory archiveFactory){
		this.archiveFactory = archiveFactory;
	}

}