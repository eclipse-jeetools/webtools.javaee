/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author dfholt
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class EJBClientJarFileMoveOperation extends WTPOperation {
	IProject clientProject;
	IProject ejbProject;
	IWorkspace workspace;
	EJBClientJarFileMoveDataModel ejbClientMoveDataModel;
	IProgressMonitor monitor;
	IProgressMonitor moveResourceMonitor;
	Map javaFilesToMove;

	public EJBClientJarFileMoveOperation(EJBClientJarFileMoveDataModel operationDataModel) {
		super(operationDataModel);
		ejbClientMoveDataModel = operationDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		monitor = aMonitor;
		initialize();
		moveRequiredFiles();
	}

	protected void initialize() {
		ejbProject = getEJBProject(ejbClientMoveDataModel.getStringProperty(EJBClientJarFileMoveDataModel.EJB_PROJECT_NAME));
		if (ejbProject == null)
			return;
		workspace = ejbProject.getWorkspace();
		clientProject = workspace.getRoot().getProject(ejbClientMoveDataModel.getStringProperty(EJBClientJarFileMoveDataModel.CLIENT_PROJECT_NAME));
		javaFilesToMove = (Map) ejbClientMoveDataModel.getProperty(EJBClientJarFileMoveDataModel.FILES_TO_MOVE_MAP);
	}

	private void moveRequiredFiles() throws CoreException {
		moveResourceMonitor = createSubProgressMonitor(1);
		Iterator iter = javaFilesToMove.entrySet().iterator();
		IPackageFragmentRoot packageFragmentRoot;
		Set types;
		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			packageFragmentRoot = (IPackageFragmentRoot) entry.getKey();
			types = (Set) entry.getValue();
			try {
				if (packageFragmentRoot.getKind() == IPackageFragmentRoot.K_SOURCE)
					moveFiles(packageFragmentRoot, types);
				else
					moveClassFiles(packageFragmentRoot, types);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
		IJavaProject javaProject = ProjectUtilities.getJavaProject(clientProject);
		//Refresh problems
		javaProject.close();
		javaProject.open(null);
	}


	private void moveFiles(IPackageFragmentRoot packageFragmentRoot, Set types) throws CoreException {
		IFile file = null;
		int leadSegmentsToTruncate = -1;
		IFolder sourceSourceFolder = getSourceFolder(packageFragmentRoot);
		if (sourceSourceFolder == null)
			return;
		IFolder targetSourceFolder = getOrCreateTargetSourceFolder(sourceSourceFolder);
		if (targetSourceFolder == null)
			return;
		IPath sourceFolderPath = targetSourceFolder.getFullPath();
		try {
			leadSegmentsToTruncate = packageFragmentRoot.getUnderlyingResource().getFullPath().segmentCount();
		} catch (JavaModelException ex) {
			Logger.getLogger().logError(ex);
			return;
		}
		for (Iterator iter = types.iterator(); iter.hasNext();) {
			file = (IFile) iter.next();
			IPath relPath = file.getFullPath().removeFirstSegments(leadSegmentsToTruncate);
			IPath newPath = sourceFolderPath.append(relPath);
			mkdirs(newPath, workspace.getRoot());
			IFile existing = workspace.getRoot().getFile(newPath);
			if (!existing.exists())
				file.move(newPath, true, moveResourceMonitor);
		}
	}

	/**
	 * @param sourceSourceFolder
	 * @return
	 */
	private IFolder getOrCreateTargetSourceFolder(IFolder sourceSourceFolder) throws CoreException {
		IFolder targetFolder = clientProject.getFolder(sourceSourceFolder.getProjectRelativePath());
		if (targetFolder == null || !targetFolder.exists()) {
			mkFolders(targetFolder.getFullPath(), workspace.getRoot());
			ProjectUtilities.appendJavaClassPath(clientProject, JavaCore.newSourceEntry(targetFolder.getFullPath()));
		}
		return targetFolder;
	}

	protected void mkFolders(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 1)
			return;
		IFolder folder = root.getFolder(newPath);
		IPath parentPath = newPath.removeLastSegments(1);
		if (!folder.exists()) {
			mkFolders(parentPath, root);
			folder.create(true, true, null);
		}
	}

	/**
	 * @param packageFragmentRoot
	 * @return
	 */
	private IFolder getSourceFolder(IPackageFragmentRoot packageFragmentRoot) {
		IResource res = null;
		try {
			res = packageFragmentRoot.getUnderlyingResource();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		if (res != null && res instanceof IFolder)
			return (IFolder) res;
		return null;
	}

	private void moveClassFiles(IPackageFragmentRoot packageFragmentRoot, Set types) throws CoreException {
		IFolder folder = clientProject.getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);
		if (!folder.exists())
			folder.create(true, true, null);
		try {
			ProjectUtilities.appendJavaClassPath(clientProject, JavaCore.newLibraryEntry(folder.getFullPath(), null, null, true));
		} catch (CoreException ex) {
			Logger.getLogger().logError(ex);
		}
		moveFiles(packageFragmentRoot, types);
	}

	protected void mkdirs(IPath newPath, IWorkspaceRoot root) throws CoreException {
		if (newPath.segmentCount() <= 2)
			return;
		IPath parentPath = newPath.removeLastSegments(1);
		IFolder folder = root.getFolder(parentPath);
		if (!folder.exists()) {
			mkdirs(parentPath, root);
			folder.create(true, true, null);
		}
	}

	/**
	 * @param clientProjectName
	 */
	protected IProject getEJBProject(String aProjectName) {
		return getWorkspace().getRoot().getProject(aProjectName);
	}

	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}
}