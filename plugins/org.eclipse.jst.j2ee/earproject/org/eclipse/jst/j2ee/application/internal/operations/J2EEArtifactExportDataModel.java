/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is an common super class used to export J2EE components.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class J2EEArtifactExportDataModel extends WTPOperationDataModel {

	/**
	 * Required, type String
	 */
	public static final String PROJECT_NAME = "J2EEExportDataModel.PROJECT_NAME"; //$NON-NLS-1$
	/**
	 * Required, type String
	 *  
	 */
	public static final String ARCHIVE_DESTINATION = "J2EEExportDataModel.ARCHIVE_DESTINATION"; //$NON-NLS-1$
	/*
	 * Optional, type boolean
	 */
	public static final String EXPORT_SOURCE_FILES = "J2EEExportDataModel.EXPORT_SOURCE_FILES"; //$NON-NLS-1$
	/*
	 * Optional, type boolean
	 */
	public static final String OVERWRITE_EXISTING = "J2EEExportDataModel.OVERWRITE_EXISTING"; //$NON-NLS-1$

	/*
	 * Optional, type boolean, default true
	 */
	public static final String RUN_BUILD = "J2EEExportDataModel.RUN_BUILD"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getTargetProject()
	 */
	public IProject getTargetProject() {
		return ProjectCreationDataModel.getProjectHandleFromProjectName(getStringProperty(PROJECT_NAME));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(ARCHIVE_DESTINATION);
		addValidBaseProperty(EXPORT_SOURCE_FILES);
		addValidBaseProperty(OVERWRITE_EXISTING);
		addValidBaseProperty(RUN_BUILD);
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ARCHIVE_DESTINATION)) {
			return ""; //$NON-NLS-1$
		} else if (propertyName.equals(EXPORT_SOURCE_FILES)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(OVERWRITE_EXISTING)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(RUN_BUILD)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(PROJECT_NAME)) {
			return getValidProjectDescriptors();
		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (PROJECT_NAME.equals(propertyName)) {
			String projectName = (String) getProperty(PROJECT_NAME);
			IStatus status = ProjectCreationDataModel.validateProjectName(projectName);
			if (!status.isOK()) {
				return status;
			}
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if (!project.exists()) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EXISTS_ERROR, new Object[]{projectName}));
			}
			try {
				if (!project.hasNature(getNatureID())) {
					return WTPCommonPlugin.createErrorStatus(getWrongProjectTypeString(project.getName()));
				}
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}

		} else if (ARCHIVE_DESTINATION.equals(propertyName)) {
			String archiveLocation = (String) getProperty(ARCHIVE_DESTINATION);
			if (!isSet(ARCHIVE_DESTINATION) || archiveLocation.equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID)); //$NON-NLS-1$);
			} else if (isSet(ARCHIVE_DESTINATION) && !validateModuleType(archiveLocation)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_ARCHIVE_SHOULD_END_WITH, new Object[]{getModuleExtension()})); //$NON-NLS-1$);
			} else if (isSet(ARCHIVE_DESTINATION)) {
				IStatus tempStatus = validateLocation(archiveLocation);
				if (tempStatus != OK_STATUS)
					return tempStatus;
			}
		}
		if (ARCHIVE_DESTINATION.equals(propertyName) || OVERWRITE_EXISTING.equals(propertyName)) {
			String location = (String) getProperty(ARCHIVE_DESTINATION);
			if (checkForExistingFileResource(location)) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.RESOURCE_EXISTS_ERROR, new Object[]{location}));
			}
		}
		return super.doValidateProperty(propertyName);
	}
	
	protected abstract String getNatureID();

	protected abstract String getWrongProjectTypeString(String projectName);

	private IStatus validateLocation(String archiveLocation) {
		IPath path = null;
		try {
			path = new Path(archiveLocation);
		} catch (IllegalArgumentException ex) {
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus status = workspace.validateName(path.lastSegment(), IResource.FILE);
		if (!status.isOK()) {
			return status;
		}
		String device = path.getDevice();
		if (device == null)
			return OK_STATUS;
		if (path == null || device.length() == 1 && device.charAt(0) == Path.DEVICE_SEPARATOR)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));

		if (!path.toFile().canWrite()) {
			if (path.toFile().exists()) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.IS_READ_ONLY));
			}
			boolean OK = false;
			path = path.removeLastSegments(1);
			for (int i = 1; !OK && i < 20 && path.segmentCount() > 0; i++) {
				if (path.toFile().exists()) {
					OK = true;
				}
				status = workspace.validateName(path.lastSegment(), IResource.FOLDER);
				if (!status.isOK()) {
					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
				}
				path = path.removeLastSegments(1);
			}
		}

		return OK_STATUS;
	}

	private boolean checkForExistingFileResource(String fileName) {
		if (!getBooleanProperty(J2EEArtifactExportDataModel.OVERWRITE_EXISTING)) {
			java.io.File externalFile = new java.io.File(fileName);
			if (externalFile != null && externalFile.exists())
				return true;
		}
		return false;
	}

	/**
	 *  
	 */
	private boolean validateModuleType(String archive) {
		if ((archive.length() < 4) || (!(archive.substring(archive.length() - 4, archive.length()).equalsIgnoreCase(getModuleExtension())))) {
			return false;
		}
		return true;
	}

	/**
	 * Populate the resource name combo with connector projects that are not encrypted.
	 */
	private WTPPropertyDescriptor[] getValidProjectDescriptors() {
		List projects = Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		List projectsWithNature = new ArrayList();

		for (int i = 0; i < projects.size(); i++) {
			IProject project = (IProject) projects.get(i);
			if (J2EENature.hasRuntime(project, getNatureID()) && project.isOpen()) {
				projectsWithNature.add(project.getFullPath().toString());
			} // if
		} // for
		return WTPPropertyDescriptor.createDescriptors(ProjectUtilities.getProjectNamesWithoutForwardSlash((String[]) projectsWithNature.toArray(new String[projectsWithNature.size()])));
	}

	protected abstract String getModuleExtension();

}