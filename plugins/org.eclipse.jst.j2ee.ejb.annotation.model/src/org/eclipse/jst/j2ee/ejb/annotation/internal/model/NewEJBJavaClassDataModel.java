/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetHelper;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

public class NewEJBJavaClassDataModel extends NewJavaClassDataModel {

	protected WTPOperationDataModel parentDataModel = null;

	protected IFolder getDefaultJavaSourceFolder() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		if (!ServerTargetHelper.hasJavaNature(project))
			return null;
		IPath folderFullPath = JavaProjectUtilities.getSourceFolderOrFirst(
				project, "src").getFullPath();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = root.getFolder(folderFullPath);
		return folder;
	}

	public IPackageFragmentRoot getJavaPackageFragmentRoot() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		IJavaProject javaProject = JavaCore.create(project);
		if (javaProject != null) {
			IFolder sourcefolder = getJavaSourceFolder();
			if (sourcefolder != null)
				return javaProject.getPackageFragmentRoot(sourcefolder);
		}
		return null;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(SOURCE_FOLDER)) {
			// Get the project name from the source folder name
			String sourceFolder = (String) propertyValue;
			int index = sourceFolder.indexOf(File.separator);
			String projectName = sourceFolder;
			if (index == 0)
				projectName = sourceFolder.substring(1);
			index = projectName.indexOf(File.separator);
			if (index != -1) {
				projectName = projectName.substring(0, index);
				setProperty(PROJECT_NAME, projectName);
				if (this.parentDataModel != null)
					this.parentDataModel.setProperty(PROJECT_NAME, projectName);
			}
		}
		return super.doSetProperty(propertyName, propertyValue);
	}

	/**
	 * @return Returns the parentdataModel.
	 */
	public WTPOperationDataModel getParentDataModel() {
		return this.parentDataModel;
	}

	/**
	 * @param parentdataModel
	 *            The parentdataModel to set.
	 */
	public void setParentDataModel(WTPOperationDataModel parentDataModel) {
		this.parentDataModel = parentDataModel;
	}
}