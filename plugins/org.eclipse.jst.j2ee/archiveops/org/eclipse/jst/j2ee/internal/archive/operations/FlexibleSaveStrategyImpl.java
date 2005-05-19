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
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;

public abstract class FlexibleSaveStrategyImpl extends SaveStrategyImpl {

	protected IVirtualComponent vComponent;
	protected IOverwriteHandler overwriteHandler;
	protected IProgressMonitor progressMonitor;

	public FlexibleSaveStrategyImpl(IVirtualComponent vComponent) {
		super();
		if(null == vComponent){
			throw new NullPointerException();
		}
		this.vComponent = vComponent;
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

	public void save(File aFile, InputStream in) throws SaveFailureException {
		try {
			String displayString = EJBArchiveOpsResourceHandler.getString("IMPORT_OPERATION_STRING"); //$NON-NLS-1$
			progressMonitor.subTask(displayString + aFile.getURI());
			IPath path = new Path(aFile.getURI().toString());
			saveToOutputPath(path, in);
		} catch (OverwriteHandlerException ohe) {
			throw ohe;
		} catch (Exception e) {
			String errorString = EJBArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_SaveFile") + aFile.getName(); //$NON-NLS-1$
			throw new SaveFailureException(errorString, e);
		}
	}

	protected void saveToWorkbenchPath(IPath workbenchPath, InputStream in) throws Exception {
		IFile iFile = vComponent.getProject().getFile(workbenchPath);
		saveToIFile(iFile, in);

	}

	/**
	 * Saves to the specified output path. The workbench path is computed from the output path.
	 * 
	 * @param outputPath
	 * @param in
	 * @throws Exception
	 */
	protected void saveToOutputPath(IPath outputPath, InputStream in) throws Exception {
		IVirtualFile vFile = vComponent.getFile(outputPath);
		IFile iFile = vFile.getUnderlyingFile();
		saveToIFile(iFile, in);
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
