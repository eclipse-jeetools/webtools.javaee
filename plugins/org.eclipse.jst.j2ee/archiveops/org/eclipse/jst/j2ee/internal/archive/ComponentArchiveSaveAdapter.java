/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.archive.operations.EJBArchiveOpsResourceHandler;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.jst.j2ee.internal.archive.operations.OverwriteHandlerException;
import org.eclipse.jst.jee.archive.AbstractArchiveSaveAdapter;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

// hari: make abstract
public class ComponentArchiveSaveAdapter extends AbstractArchiveSaveAdapter {

	protected IDataModel dataModel;

	protected IVirtualComponent vComponent;

	protected IOverwriteHandler overwriteHandler;

	protected IProgressMonitor progressMonitor;

	private String archiveComponentsDeployPath;

	private List archiveComponents;

	private Map archiveComponentURIMap;

	public ComponentArchiveSaveAdapter(IVirtualComponent vComponent) {
		super();
		if (null == vComponent) {
			throw new NullPointerException();
		}
		this.vComponent = vComponent;
	}

	public void setDataModel(IDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public void setOverwriteHandler(IOverwriteHandler newOverwriteHandler) {
		overwriteHandler = newOverwriteHandler;
	}

	public void setProgressMonitor(IProgressMonitor newProgressMonitor) {
		progressMonitor = newProgressMonitor;
	}

	protected void validateEdit(IFile aFile) {
		if (overwriteHandler == null)
			return;
		if (!(aFile.exists() && aFile.isReadOnly()))
			return;

		overwriteHandler.validateEdit(aFile);
	}

	protected boolean shouldOverwrite(String uri) {
		if (overwriteHandler.isOverwriteNone())
			return false;
		return (overwriteHandler.isOverwriteResources() || overwriteHandler.isOverwriteAll() || overwriteHandler.shouldOverwrite(uri));
	}

	protected OutputStream getOutputStreamForResource(Resource aResource) throws IOException {
		// this method has no references in the hirarchy
		return null;
	}

	@Override
	public void save(IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int SUPER_TICKS = 1000;
		final int LOCAL_TICKS = 10;
		final int TOTAL_TICKS = SUPER_TICKS + LOCAL_TICKS;
		try {
			monitor.beginTask(NLS.bind(ArchiveMessages.ComponentArchiveSaveAdapter_Importing_0_, vComponent.getName()), TOTAL_TICKS);
			super.save(new SubProgressMonitor(monitor, SUPER_TICKS));
			linkArchiveComponents();
			monitor.worked(LOCAL_TICKS);
		} finally {
			monitor.done();
		}
	}

	@Override
	public void save(IArchiveResource aFile) throws ArchiveSaveFailureException {

		try {
			if (progressMonitor == null)
				progressMonitor = new NullProgressMonitor();
			progressMonitor.subTask(aFile.getPath().toString());
			InputStream in = aFile.getInputStream();
			
			IPath projectRelativePath = getProjectRelativePath(aFile);
			if (aFile.getType() == IArchiveResource.ARCHIVE_TYPE) {
				saveAsArchiveComponent((IArchive) aFile, projectRelativePath, in);
			} else if (aFile.getType() != IArchiveResource.DIRECTORY_TYPE) {
				saveToOutputPath(projectRelativePath, in);
			} else {
				createDirectory(projectRelativePath);
			}
		} catch (OverwriteHandlerException ohe) {
			throw ohe;
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.ARCHIVE_OPERATION_SaveFile + aFile.getPath();
			throw new ArchiveSaveFailureException(errorString);
		} finally {
			if(null != progressMonitor){
				progressMonitor.worked(1);
			}
		}
	}

	/**
	 * Returns the project relative path for where the specified file should be
	 * saved.
	 * 
	 * @param aFile
	 * @return
	 */
	protected IPath getProjectRelativePath(IArchiveResource aFile) {
		IPath path = aFile.getPath();
		IFile iFile = null;
		if (path.segmentCount() > 0 && path.segment(0).equals(IModuleConstants.DOT_SETTINGS)) {
			iFile = vComponent.getProject().getFile(path);
		} else {
			IVirtualFolder rootFolder = vComponent.getRootFolder();
			IVirtualFile vFile = rootFolder.getFile(path);
			iFile = vFile.getUnderlyingFile();
		}
		return iFile.getProjectRelativePath();
	}

	/**
	 * Creates the IFolder specified by the project relative path.
	 * 
	 * @param projectRelativePath
	 * @throws CoreException
	 */
	protected void createDirectory(IPath projectRelativePath) throws CoreException {
		IFolder iFolder = vComponent.getProject().getFolder(projectRelativePath);
		if (!iFolder.exists()) {
			mkdirs(iFolder);
		}
	}

	/**
	 * Creates the specified IFolder
	 * 
	 * @param folder
	 * @throws CoreException
	 */
	protected void mkdirs(IFolder folder) throws CoreException {
		IContainer container = folder.getParent();
		if (!container.exists()) {
			mkdirs((IFolder) container);
		}
		folder.create(true, true, null);
	}

	/**
	 * Save the specified Archive to the specified project relative path using
	 * the passed input stream.
	 * 
	 * @param archive
	 * @param projectRelativePath
	 * @param in
	 * @throws Exception
	 */
	protected void saveAsArchiveComponent(IArchive archive, IPath projectRelativePath, InputStream in) throws Exception {
		IFile iFile = saveToOutputPath(projectRelativePath, in);
		// TODO investigate removing this block and related variables and
		// linkArchiveComponents(); see bugzilla 159160
		if (shouldLinkAsComponentRef(archive)) {
			IVirtualComponent archiveComponent = ComponentCore.createArchiveComponent(vComponent.getProject(), VirtualArchiveComponent.LIBARCHIVETYPE + iFile.getFullPath().toString());
			if (archiveComponents == null) {
				archiveComponents = new ArrayList();
				archiveComponentURIMap = new HashMap();
				archiveComponentsDeployPath = IPath.SEPARATOR + projectRelativePath.removeLastSegments(1).toString();
			}
			archiveComponents.add(archiveComponent);
			archiveComponentURIMap.put(archiveComponent, iFile.getName());
		}
	}

	protected boolean shouldLinkAsComponentRef(IArchive archive) {
		return false;
	}

	protected void linkArchiveComponents() {
		if (archiveComponents != null && archiveComponents.size() > 0) {
			IDataModel createReferencesDataModel = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
			createReferencesDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, vComponent);
			createReferencesDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, archiveComponentsDeployPath);
			createReferencesDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, archiveComponents);
			createReferencesDataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, archiveComponentURIMap);
			try {
				createReferencesDataModel.getDefaultOperation().execute(null, null);
			} catch (ExecutionException e) {
				J2EEPlugin.logError(e);
			}
		}
	}

	/**
	 * Saves to the specified project relative output path. Warning this method
	 * will be changed post 1.5 to return an IFile
	 * 
	 * @param projectRelativePath
	 * @param in
	 * @throws Exception
	 */
	protected IFile saveToOutputPath(IPath projectRelativePath, InputStream in) throws Exception {
		IFile iFile = vComponent.getProject().getFile(projectRelativePath);
		saveToIFile(iFile, in);
		return iFile;
	}

	protected void saveToIFile(IFile iFile, InputStream in) throws Exception {
		validateEdit(iFile);
		if (iFile.exists())
			iFile.setContents(in, true, true, null);
		else {
			mkdirs(iFile.getFullPath().removeLastSegments(1), ResourcesPlugin.getWorkspace().getRoot());
			iFile.create(in, true, null);
		}
	}

	protected void mkdirs(IPath path, IWorkspaceRoot root) throws CoreException {
		if (path.segmentCount() <= 1)
			return;
		IFolder folder = root.getFolder(path);
		if (!folder.exists()) {
			mkdirs(path.removeLastSegments(1), root);
			folder.create(true, true, null);
		}
	}

}
