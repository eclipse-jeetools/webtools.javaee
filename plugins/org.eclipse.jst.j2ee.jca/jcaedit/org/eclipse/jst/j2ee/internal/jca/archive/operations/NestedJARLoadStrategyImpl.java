/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.LoadStrategyImpl;


public class NestedJARLoadStrategyImpl extends LoadStrategyImpl {

	private RARProjectLoadStrategyImpl rarStrategy;
	private List sourceIFiles;
	private int rootSegmentCount;
	private boolean exportSource = false;
	private Map urisToIFiles;

	/**
	 * Constructor for NestedJARLoadStrategyImpl.
	 */
	public NestedJARLoadStrategyImpl(RARProjectLoadStrategyImpl parent, List sourceFiles, IFolder root) {
		super();
		this.rarStrategy = parent;
		this.sourceIFiles = sourceFiles;
		rootSegmentCount = root.getProjectRelativePath().segmentCount();
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl#primContains(String)
	 */
	protected boolean primContains(String uri) {
		//Should only be used by discriminators, and we don't discriminate these archives
		return true;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl#getFiles()
	 */
	public List getFiles() {
		int outputLocSegmentCount = rarStrategy.getModuleRootSegmentCount();
		urisToIFiles = new HashMap();
		List result = new ArrayList();
		int size = sourceIFiles.size();
		for (int i = 0; i < size; i++) {
			IFile iFile = (IFile) sourceIFiles.get(i);
			IPath relPath = getRelativePath(iFile, rootSegmentCount);
			if (rarStrategy.isJava(iFile)) {
				if (exportSource)
					addFile(iFile, relPath, result);
				addClassFiles(outputLocSegmentCount, result, relPath);
			} else
				addFile(iFile, relPath, result);
		}
		return result;
	}

	private void addClassFiles(int outputLocSegmentCount, List result, IPath relPath) {
		List classFiles = getClassFiles(relPath);
		for (int j = 0; j < classFiles.size(); j++) {
			IFile classFile = (IFile) classFiles.get(j);
			if (classFile.exists())
				addFile(classFile, getRelativePath(classFile, outputLocSegmentCount), result);
		}
	}

	protected void addFile(IFile iFile, IPath relPath, List result) {
		File cFile = createFile(iFile, relPath);
		result.add(cFile);
		urisToIFiles.put(cFile.getURI(), iFile);
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	private File createFile(IFile iFile, IPath relPath) {
		File cFile = createFile(relPath.toString());
		cFile.setLastModified(getLastModified(iFile));
		return cFile;
	}

	private IPath getRelativePath(IFile file, int parentSegmentCount) {
		return file.getProjectRelativePath().removeFirstSegments(parentSegmentCount);
	}

	private List getClassFiles(IPath relativePath) {
		List result = new ArrayList(1);
		IPath path = relativePath.removeFileExtension();
		result.add(rarStrategy.getModuleRoot().getFile(path.addFileExtension("class"))); //$NON-NLS-1$
		List inners = rarStrategy.retrieveInnerClasses(path.toString());
		if (inners != null)
			result.addAll(inners);
		return result;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy#getInputStream(String)
	 */
	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		if (null == urisToIFiles) {
			getFiles();
		}
		IFile file = (IFile) urisToIFiles.get(uri);
		if (file != null) {
			try {
				return file.getContents();
			} catch (CoreException core) {
				throw new ArchiveRuntimeException(uri, core);
			}
		}
		throw new FileNotFoundException(uri);
	}

	/**
	 * Returns the exportSource.
	 * 
	 * @return boolean
	 */
	public boolean isExportSource() {
		return exportSource;
	}

	/**
	 * Sets the exportSource.
	 * 
	 * @param exportSource
	 *            The exportSource to set
	 */
	public void setExportSource(boolean includeSource) {
		this.exportSource = includeSource;
	}
}