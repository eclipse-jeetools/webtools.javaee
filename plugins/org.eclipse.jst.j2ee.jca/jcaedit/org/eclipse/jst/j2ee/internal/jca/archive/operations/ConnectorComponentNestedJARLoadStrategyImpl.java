/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;


public class ConnectorComponentNestedJARLoadStrategyImpl extends LoadStrategyImpl {

	private List files;
	private int sourceSegmentCount;
	private int outputSegmentCount;
	private Map urisToIFiles;

	/**
	 * Constructor for NestedJARLoadStrategyImpl.
	 */
	public ConnectorComponentNestedJARLoadStrategyImpl(List files, IContainer sourceContainer, IFolder javaOutputFolder) {
		super();
		this.files = files;
		sourceSegmentCount = sourceContainer.getProjectRelativePath().segmentCount();
		outputSegmentCount = javaOutputFolder.getProjectRelativePath().segmentCount();
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl#primContains(String)
	 */
	protected boolean primContains(String uri) {
		// Should only be used by discriminators, and we don't discriminate these archives
		return true;
	}

	/**
	 * @see com.ibm.etools.archive.impl.LoadStrategyImpl#getFiles()
	 */
	public List getFiles() {
		urisToIFiles = new HashMap();
		List result = new ArrayList();
		int size = files.size();
		for (int i = 0; i < size; i++) {
			IFile iFile = (IFile) files.get(i);
			IPath relPath;
			if(ConnectorComponentLoadStrategyImpl.isClass(iFile)){
				relPath = getRelativePath(iFile, outputSegmentCount);
			} else {
				relPath = getRelativePath(iFile, sourceSegmentCount);
			}
			addFile(iFile, relPath, result);
		}
		return result;
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

}
