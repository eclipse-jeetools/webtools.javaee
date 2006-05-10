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
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategyImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;

public abstract class ComponentSaveStrategyImpl extends SaveStrategyImpl {

	protected IDataModel dataModel;
	protected IVirtualComponent vComponent;
	protected IOverwriteHandler overwriteHandler;
	protected IProgressMonitor progressMonitor;

	private String archiveComponentsDeployPath;
	private List archiveComponents;
	private Map archiveComponentURIMap;

	public ComponentSaveStrategyImpl(IVirtualComponent vComponent) {
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

	protected SaveStrategy createNestedSaveStrategy(Archive anArchive) throws IOException {
		return null;
	}

	protected OutputStream getOutputStreamForResource(Resource aResource) throws IOException {
		// this method has no references in the hirarchy
		return null;
	}

	protected void saveFiles() throws SaveFailureException {
		super.saveFiles();
		linkArchiveComponents();
	}

	public void save(File aFile, InputStream in) throws SaveFailureException {
		try {
			String displayString = EJBArchiveOpsResourceHandler.IMPORT_OPERATION_STRING;
			progressMonitor.subTask(displayString + aFile.getURI());
			IPath outputPath = getOutputPathForFile(aFile);
			if (aFile.isArchive()) {
				saveAsArchiveComponent((Archive) aFile, outputPath, in);
			} else if (!aFile.isDirectoryEntry()) {
				saveToOutputPath(outputPath, in);
			} else {
				createDirectory(outputPath);
			}
		} catch (OverwriteHandlerException ohe) {
			throw ohe;
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.ARCHIVE_OPERATION_SaveFile + aFile.getName();
			throw new SaveFailureException(errorString, e);
		}
	}

	protected IPath getOutputPathForFile(File aFile) {
		return new Path(aFile.getURI().toString());
	}

	protected void saveToWorkbenchPath(IPath workbenchPath, InputStream in) throws Exception {
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(workbenchPath);
		// IFile iFile = vComponent.getProject().getFile(workbenchPath);
		saveToIFile(iFile, in);
	}

	protected void createDirectory(IPath outputPath) throws CoreException {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualFolder vFolder = rootFolder.getFolder(outputPath);
		IFolder iFolder = (IFolder) vFolder.getUnderlyingFolder();
		if (!iFolder.exists()) {
			mkdirs(iFolder);
		}
	}

	protected void mkdirs(IFolder folder) throws CoreException {
		IContainer container = folder.getParent();
		if (!container.exists()) {
			mkdirs((IFolder) container);
		}
		folder.create(true, true, null);
	}

	protected void saveAsArchiveComponent(Archive archive, IPath outputPath, InputStream in) throws Exception {
		IFile iFile = saveToOutputPathIFile(outputPath, in);
		if (shouldLinkAsComponentRef(archive)) {
			IVirtualComponent archiveComponent = ComponentCore.createArchiveComponent(vComponent.getProject(), VirtualArchiveComponent.LIBARCHIVETYPE + iFile.getFullPath().toString());
			if (archiveComponents == null) {
				archiveComponents = new ArrayList();
				archiveComponentURIMap = new HashMap();
				archiveComponentsDeployPath = IPath.SEPARATOR + outputPath.removeLastSegments(1).toString();
			}
			archiveComponents.add(archiveComponent);
			archiveComponentURIMap.put(archiveComponent, iFile.getName());
		}
	}

	protected boolean shouldLinkAsComponentRef(Archive archive) {
		return true;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves to the specified output path. The workbench path is computed from the output path.
	 * Warning this method will be changed post 1.5 to return an IFile
	 * 
	 * @param outputPath
	 * @param in
	 * @throws Exception
	 */
	protected void saveToOutputPath(IPath outputPath, InputStream in) throws Exception {
		saveToOutputPathIFile(outputPath, in);
	}

	/**
	 * TODO refactor change the method above to return IFile
	 * {@link DoNotUseMeThisWillBeDeletedPost15}
	 */
	protected IFile saveToOutputPathIFile(IPath outputPath, InputStream in) throws Exception {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualFile vFile = rootFolder.getFile(outputPath);
		IFile iFile = vFile.getUnderlyingFile();
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

	public void save(ArchiveManifest aManifest) throws SaveFailureException {
	}
}
