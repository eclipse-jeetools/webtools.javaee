/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public abstract class ComponentLoadStrategyImpl extends LoadStrategyImpl {

	protected IVirtualComponent vComponent;
	protected boolean exportSource;
	protected ArrayList filesList;
	protected Set visitedURIs;

	public ComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		this.vComponent = vComponent;
		filesList = new ArrayList();
	}
	
	public boolean contains(String uri){
		return vComponent.getFile(new Path(uri)).exists();
	}

	protected boolean primContains(String uri) {
		return false;
	}

	public List getFiles() {
		filesList.clear();
		try {
			IVirtualResource[] members = vComponent.members();
			filesList = getFiles(members);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filesList;
	}

	protected java.util.ArrayList getFiles(IVirtualResource[] virtualResources) throws CoreException {
		for (int i = 0; i < virtualResources.length; i++) {
			File cFile = null;
			if (virtualResources[i].getType() == IVirtualResource.FILE) {
				// We have to avoid duplicates between the source and output folders (non-java
				// resources)
				IPath runtimePath = virtualResources[i].getRuntimePath();
				String uri = runtimePath == null ? null : runtimePath.toString();
				if (uri == null)
					continue;
				if (!shouldInclude(uri)) //$NON-NLS-1$
					continue;
				if (getVisitedURIs().contains(uri))
					continue;
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(virtualResources[i].getUnderlyingResource()));
				getVisitedURIs().add(uri);
				filesList.add(cFile);
			} else if (shouldInclude((IVirtualContainer) virtualResources[i])) {
				getFiles(((IVirtualContainer) virtualResources[i]).members());
			}
		}
		return filesList;
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	public Set getVisitedURIs() {
		if (visitedURIs == null)
			visitedURIs = new HashSet();
		return visitedURIs;
	}

	public void setExportSource(boolean newExportSource) {
		exportSource = newExportSource;
	}

	public boolean isExportSource() {
		return exportSource;
	}

	protected boolean shouldInclude(IVirtualContainer vContainer) {
		return true;
	}

	protected boolean shouldInclude(String uri) {
		return isExportSource() || !isSource(uri);
	}

	protected boolean isSource(String uri) {
		if (uri == null)
			return false;
		return uri.endsWith(ArchiveUtil.DOT_JAVA) || uri.endsWith(ArchiveUtil.DOT_SQLJ);
	}

	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		String filePath = vComponent.findMember(uri).getUnderlyingResource().getLocation().toOSString();
		java.io.File file = new java.io.File(filePath);
	    InputStream inputStream = new FileInputStream(file);
	    return inputStream;
	}

	public boolean isClassLoaderNeeded() {
		return false;
	}

}
