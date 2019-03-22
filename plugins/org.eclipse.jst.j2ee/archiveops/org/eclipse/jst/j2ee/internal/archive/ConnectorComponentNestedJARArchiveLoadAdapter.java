/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.jee.archive.AbstractArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.osgi.util.NLS;

public class ConnectorComponentNestedJARArchiveLoadAdapter extends AbstractArchiveLoadAdapter {

	private List<IFile> iFiles;

	private int sourceSegmentCount;

	private int outputSegmentCount;

	private Map<IPath, IFile> pathsToIFiles;

	private Map<IPath, IArchiveResource> pathsToIArchiveResources;

	private IContainer sourceContainer;

	/**
	 * Constructor for NestedJARLoadStrategyImpl.
	 */
	public ConnectorComponentNestedJARArchiveLoadAdapter(List<IFile> iFiles, IContainer sourceContainer, IFolder javaOutputFolder) {
		super();
		this.iFiles = iFiles;
		this.sourceContainer = sourceContainer;
		sourceSegmentCount = sourceContainer.getProjectRelativePath().segmentCount();
		outputSegmentCount = javaOutputFolder.getProjectRelativePath().segmentCount();
	}

	@Override
	public String toString() {
		int packageLength = this.getClass().getPackage().getName().length() + 1;
		StringBuffer buffer = new StringBuffer(this.getClass().getName().substring(packageLength));
		buffer.append(", Source Container: "); //$NON-NLS-1$
		buffer.append(sourceContainer.getName());
		return buffer.toString();
	}

	private boolean indexed = false;

	private List<IArchiveResource> fullIndex = null;

	@Override
	public List<IArchiveResource> getArchiveResources() {
		if (!indexed) {
			indexed = true;
			pathsToIFiles = new HashMap<IPath, IFile>();
			pathsToIArchiveResources = new HashMap<IPath, IArchiveResource>();
			for (IFile iFile : iFiles) {
				IPath relPath;
				if (JavaEEArchiveUtilities.isClass(iFile)) {
					relPath = getRelativePath(iFile, outputSegmentCount);
				} else {
					relPath = getRelativePath(iFile, sourceSegmentCount);
				}
				addFile(iFile, relPath);
			}
			IPath manifestPath = new Path(J2EEConstants.MANIFEST_URI);
			if (!pathsToIArchiveResources.containsKey(manifestPath)) {
				IArchiveResource manifest = createManifest(manifestPath);
				pathsToIArchiveResources.put(manifest.getPath(), manifest);
			}
			List<IArchiveResource> list = new ArrayList();
			list.addAll(pathsToIArchiveResources.values());
			fullIndex = Collections.unmodifiableList(list);
		}
		return fullIndex;
	}

	protected void addFile(IFile iFile, IPath relPath) {
		IArchiveResource aFile = createFile(iFile, relPath);
		pathsToIArchiveResources.put(aFile.getPath(), aFile);
		pathsToIFiles.put(aFile.getPath(), iFile);
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

	@Override
	public boolean containsArchiveResource(IPath resourcePath) {
		if (!indexed) {
			getArchiveResources();
		}
		return pathsToIArchiveResources.containsKey(resourcePath);
	}

	@Override
	public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException {
		if (!indexed) {
			getArchiveResources();
		}
		return pathsToIArchiveResources.get(resourcePath);
	}

	@Override
	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException {
		IFile file = pathsToIFiles.get(archiveResource.getPath());
		if (file != null) {
			try {
				return file.getContents();
			} catch (CoreException core) {
				throw new IOException(NLS.bind(ArchiveMessages.ConnectorComponentNestedJARArchiveLoadAdapter_Unable_to_get_contents_from_0_mes_, new Object[] { file.getProjectRelativePath(), core.getLocalizedMessage() }));
			}
		}
		throw new FileNotFoundException(archiveResource.getPath().toString());
	}

	@Override
	public boolean containsModelObject(IPath modelObjectPath) {
		return false;
	}

	@Override
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return null; // no model objects here
	}

}
