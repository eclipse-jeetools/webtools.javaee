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
package org.eclipse.jst.j2ee.internal.archive;

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
import org.eclipse.jst.jee.archive.AbstractArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchiveResource;

//TODO come back to and implement this class
public class ConnectorComponentNestedJARArchiveLoadAdapter extends AbstractArchiveLoadAdapter {

	private List files;

	private int sourceSegmentCount;

	private int outputSegmentCount;

	private Map<IPath, IFile> pathsToIFiles;

	/**
	 * Constructor for NestedJARLoadStrategyImpl.
	 */
	public ConnectorComponentNestedJARArchiveLoadAdapter(List <IArchiveResource> files, IContainer sourceContainer, IFolder javaOutputFolder) {
		super();
		this.files = files;
		sourceSegmentCount = sourceContainer.getProjectRelativePath().segmentCount();
		outputSegmentCount = javaOutputFolder.getProjectRelativePath().segmentCount();
	}

	public List getFiles() {
		pathsToIFiles = new HashMap<IPath, IFile>();
		List result = new ArrayList();
		int size = files.size();
		for (int i = 0; i < size; i++) {
			IFile iFile = (IFile) files.get(i);
			IPath relPath;
			if (JavaEEArchiveUtilities.isClass(iFile)) {
				relPath = getRelativePath(iFile, outputSegmentCount);
			} else {
				relPath = getRelativePath(iFile, sourceSegmentCount);
			}
			addFile(iFile, relPath, result);
		}
		return result;
	}

	protected void addFile(IFile iFile, IPath relPath, List result) {
		IArchiveResource cFile = createFile(iFile, relPath);
		result.add(cFile);
		pathsToIFiles.put(cFile.getPath(), iFile);
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	private IArchiveResource createFile(IFile iFile, IPath relPath) {
		IArchiveResource cFile = createFile(relPath);
		cFile.setLastModified(getLastModified(iFile));
		return cFile;
	}

	private IPath getRelativePath(IFile file, int parentSegmentCount) {
		return file.getProjectRelativePath().removeFirstSegments(parentSegmentCount);
	}

	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		if (null == pathsToIFiles) {
			getFiles();
		}
		IFile file = (IFile) pathsToIFiles.get(uri);
		if (file != null) {
			try {
				return file.getContents();
			} catch (CoreException core) {
				//TODO throw new ArchiveRuntimeException(uri, core);
			}
		}
		throw new FileNotFoundException(uri);
	}

	public boolean containsArchiveResource(IPath resourcePath) {
		return false;
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		return false;
	}

	public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IArchiveResource> getArchiveResources() {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		// TODO Auto-generated method stub
		return null;
	}

}
