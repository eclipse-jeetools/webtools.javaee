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
 * Created on Apr 7, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;


public class NewWebJavaClassDataModel extends NewJavaClassDataModel {

	protected WTPOperationDataModel parentDataModel = null;

	protected IFolder getDefaultJavaSourceFolder() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		try {
			// check for JavaSource folder
			J2EEWebNatureRuntime nature = (J2EEWebNatureRuntime) project.getNature(IWebNatureConstants.J2EE_NATURE_ID);
			if (nature == null)
				return null;
			IPath folderFullPath = nature.getSourceFolder().getFullPath();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFolder folder = root.getFolder(folderFullPath);
			return folder;
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public IPackageFragmentRoot getJavaPackageFragmentRoot() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		try {
			if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
				IJavaProject javaProject = ProjectUtilities.getJavaProject(project);
				if (javaProject != null) {
					IFolder sourcefolder = getJavaSourceFolder();
					if (sourcefolder != null)
						return javaProject.getPackageFragmentRoot(sourcefolder);
				}
			}
		} catch (CoreException ex) {
			//Do nothing
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
					this.parentDataModel.setProperty(EditModelOperationDataModel.PROJECT_NAME, projectName);
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