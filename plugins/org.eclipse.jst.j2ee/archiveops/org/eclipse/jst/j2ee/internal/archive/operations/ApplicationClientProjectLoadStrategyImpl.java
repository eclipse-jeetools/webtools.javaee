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
package org.eclipse.jst.j2ee.internal.archive.operations;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverterImpl;

/**
 * Insert the type's description here. Creation date: (4/4/2001 11:12:19 AM)
 * 
 * @author: Administrator
 */
public class ApplicationClientProjectLoadStrategyImpl extends org.eclipse.jst.j2ee.internal.archive.operations.J2EELoadStrategyImpl {
	/**
	 * EARProjectSaveStrategyImpl constructor comment.
	 */
	public ApplicationClientProjectLoadStrategyImpl(IProject aProject) {
		super();
		project = aProject;
		filesList = new ArrayList();
	}

	/**
	 * getModuleFolderName method comment.
	 */
	public IContainer getModuleContainer() {
		try {
			ApplicationClientNatureRuntime enr = ApplicationClientNatureRuntime.getRuntime(project);
			return enr.getModuleServerRoot();
		} catch (Exception e) {
			throw new ArchiveRuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * getProjectURIConverter method comment.
	 */
	public WorkbenchURIConverter getProjectURIConverter() {

		ApplicationClientNatureRuntime anr = ApplicationClientNatureRuntime.getRuntime(project);
		projectURIConverter = new WorkbenchURIConverterImpl(anr.getModuleServerRoot());
		if (isExportSource()) {
			List localSourceFolders = ProjectUtilities.getSourceContainers(anr.getProject());
			for (int i = 0; i < localSourceFolders.size(); i++) {
				projectURIConverter.addInputContainer((IFolder) localSourceFolders.get(i));
			}
			return projectURIConverter;
		}
		return projectURIConverter;
	}

	/**
	 * getSourceFolderName method comment.
	 */
	public java.lang.String getSourceFolderName() throws java.lang.Exception {
		try {
			ApplicationClientNatureRuntime anr = ApplicationClientNatureRuntime.getRuntime(project);
			return anr.getSourceFolder().getName();
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_ProjectNature"), e);//$NON-NLS-1$
		}
	}

	/**
	 * save method comment.
	 */
	public List getSourceFoldersNames() throws Exception {
		try {
			ApplicationClientNatureRuntime anr = ApplicationClientNatureRuntime.getRuntime(project);
			List sourceFolderNames = new ArrayList();
			List localSourceFolders = ProjectUtilities.getSourceContainers(anr.getProject());
			for (int i = 0; i < localSourceFolders.size(); i++) {
				sourceFolderNames.add(((org.eclipse.core.resources.IFolder) localSourceFolders.get(i)).getName());
			}
			return sourceFolderNames;

		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.getString("ARCHIVE_OPERATION_ProjectNature"), e); //$NON-NLS-1$
		}

	}
}