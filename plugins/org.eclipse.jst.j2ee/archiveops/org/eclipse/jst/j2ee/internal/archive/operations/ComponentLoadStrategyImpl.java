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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.impl.PlatformURLModuleConnection;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

public abstract class ComponentLoadStrategyImpl extends LoadStrategyImpl {

	protected IVirtualComponent vComponent;
	protected boolean exportSource;
	protected ArrayList filesList;
	protected Set visitedURIs;
	private int javaOutputFolderSegmentCount;
	private IFolder[] outputFolders;

	public ComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		this.vComponent = vComponent;
		filesList = new ArrayList();
	}

	public boolean contains(String uri) {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		return rootFolder.getFile(new Path(uri)).exists();
	}

	protected void initializeResourceSet() {
		resourceSet = WorkbenchResourceHelper.getResourceSet(vComponent.getProject());
	}

	protected boolean primContains(String uri) {
		return false;
	}

	public List getFiles() {
		filesList.clear();
		try {
			IVirtualFolder rootFolder = vComponent.getRootFolder();
			IVirtualResource[] members = rootFolder.members();
			filesList = getFiles(members);
			IPackageFragmentRoot[] roots = ComponentUtilities.getSourceContainers(vComponent);
			outputFolders = new IFolder[roots.length];
			IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			for (int i = 0; i < roots.length; i++) {
				IResource source;
				IPath outputPath = roots[i].getRawClasspathEntry().getOutputLocation();
				
				if( outputPath != null ){
					outputFolders[i] = ResourcesPlugin.getWorkspace().getRoot().getFolder(outputPath);
	
					IPath sourcePath = roots[i].getPath();
					
					if( sourcePath.segmentCount() > 1 )
						source = workspaceRoot.getFolder(sourcePath);
					else
						source = getComponent().getProject();
					IVirtualResource[] virtualSources = ComponentCore.createResources(source);
					IResource actualResource = virtualSources[0].getUnderlyingResource();
					int sourceOffset = actualResource.getProjectRelativePath().segmentCount() - sourcePath.segmentCount();
					int runtimeOffset = outputPath.segmentCount() - virtualSources[0].getRuntimePath().segmentCount();
	
					javaOutputFolderSegmentCount = sourceOffset + runtimeOffset;
	
					getFiles(new IResource[]{outputFolders[i]});
				}
			
			}


		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filesList;
	}

	protected ArrayList getFiles(IResource[] resources) throws CoreException {
		for (int i = 0; i < resources.length; i++) {
			File cFile = null;
			if (resources[i].getType() == IResource.FILE) {
				// We have to avoid duplicates between the source and output folders (non-java
				// resources)
				IPath runtimePath = resources[i].getProjectRelativePath().removeFirstSegments(javaOutputFolderSegmentCount);
				String uri = runtimePath == null ? null : runtimePath.toString();
				if (uri == null)
					continue;
				if (!shouldInclude(uri)) //$NON-NLS-1$
					continue;
				if (getVisitedURIs().contains(uri))
					continue;
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(resources[i]));
				getVisitedURIs().add(uri);
				getURIResourceMap().put(uri, resources[i]);
				filesList.add(cFile);
			} else if (shouldInclude((IContainer) resources[i])) {
				IResource[] nestedResources = ((IContainer) resources[i]).members();
				getFiles(nestedResources);
			}
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
				if (uri.charAt(0) == Path.SEPARATOR) {
					uri = uri.substring(1);
				}
				cFile = createFile(uri);
				cFile.setLastModified(getLastModified(virtualResources[i].getUnderlyingResource()));
				getVisitedURIs().add(uri);
				filesList.add(cFile);
			} else if (shouldInclude((IVirtualContainer) virtualResources[i])) {
				IVirtualResource[] nestedVirtualResources = ((IVirtualContainer) virtualResources[i]).members();
				getFiles(nestedVirtualResources);
			}
		}
		return filesList;
	}

	protected long getLastModified(IResource aResource) {
		return aResource.getLocation().toFile().lastModified();
	}

	private Map uriResourceMap;

	private Map getURIResourceMap() {
		if (uriResourceMap == null) {
			uriResourceMap = new HashMap();
		}
		return uriResourceMap;
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

	protected boolean shouldInclude(IContainer container) {
		return true;
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
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualResource vResource = rootFolder.findMember(uri);
		String filePath = null;
		if (null != vResource && vResource.exists()) {
			filePath = vResource.getUnderlyingResource().getLocation().toOSString();
		} else {
			IResource resource = (IResource) getURIResourceMap().get(uri);
			if (null == resource) {
				String eString = EARArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_FileNotFound");//$NON-NLS-1$
				throw new FileNotFoundException(eString);
			}
			filePath = resource.getLocation().toOSString();
		}
		java.io.File file = new java.io.File(filePath);
		InputStream inputStream = new FileInputStream(file);
		return inputStream;

	}

	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		try {
			URI compUri = ModuleURIUtil.fullyQualifyURI(vComponent.getComponentHandle());
			IPath requestPath = new Path(compUri.path()).append(new Path(uri));
			URI resourceURI = URI.createURI(PlatformURLModuleConnection.MODULE_PROTOCOL + requestPath.toString());
			return getResourceSet().getResource(resourceURI, true);
		} catch (WrappedException wrapEx) {
			if ((ExtendedEcoreUtil.getFileNotFoundDetector().isFileNotFound(wrapEx))) {
				FileNotFoundException fileNotFoundEx = ExtendedEcoreUtil.getInnerFileNotFoundException(wrapEx);
				throw fileNotFoundEx;
			}
			throwResourceLoadException(uri, wrapEx);
			return null; // never happens - compiler expects it though
		}

	}

	public boolean isClassLoaderNeeded() {
		return false;
	}

	public IVirtualComponent getComponent() {
		return vComponent;
	}

}
