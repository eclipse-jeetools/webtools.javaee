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
 * Created on Nov 25, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AddModuleToEARDataModel extends AddArchiveProjectToEARDataModel {
	public static final String MODULE_PROJECT_LIST = "AddModuleToEARDataModel.MODULE_PROJECT_LIST"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MODULE_PROJECT_LIST);
	}

	public static AddModuleToEARDataModel createAddToEARDataModel(String earProjectName, IProject moduleProject) {
		AddModuleToEARDataModel model = new AddModuleToEARDataModel();
		model.setProperty(AddModuleToEARDataModel.PROJECT_NAME, earProjectName);
		model.setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, moduleProject);
		return model;
	}

	public AddModuleToEARDataModel getAppropriateDataModel() {
		AddModuleToEARDataModel model = this;
		IProject project = (IProject) getProperty(AddUtilityProjectToEARDataModel.ARCHIVE_PROJECT);
		J2EENature j2eeNature = J2EENature.getRegisteredRuntime(project);
		if (j2eeNature != null && XMLResource.WEB_APP_TYPE == j2eeNature.getDeploymentDescriptorType() && !model.isWebModuleArchive()) {
			model = AddWebModuleToEARDataModel.createAddWebModuleToEARDataModel(getStringProperty(PROJECT_NAME), project);
			model.setProperty(AddWebModuleToEARDataModel.ARCHIVE_URI, getProperty(ARCHIVE_URI));
		}
		return model;
	}

	public WTPOperation getDefaultOperation() {
		return new AddArchiveProjectToEAROperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultArchiveURI()
	 */
	protected String getDefaultArchiveURI() {
		IProject archiveProj = (IProject) getProperty(ARCHIVE_PROJECT);
		J2EENature j2eeNature = J2EENature.getRegisteredRuntime(archiveProj);
		if (j2eeNature != null) {
			J2EEModuleNature modNature = (J2EEModuleNature) j2eeNature;
			String existingURI = modNature.getModuleUriInFirstEAR();
			if (existingURI != null)
				return existingURI;
		}
		return super.getDefaultArchiveURI();
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (ARCHIVE_PROJECT.equals(propertyName)) {
			IProject project = (IProject) getProperty(propertyName);
			IStatus status = super.doValidateProperty(propertyName);
			if (!status.isOK()) {
				return status;
			}
			if (!isJ2EEModule(project)) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_TYPE));
			}
			EAREditModel editModel = null;
			Object key = new Object();
			try {
				editModel = (EAREditModel) getEditModelForRead(key);
				ModuleMapping mapping = editModel.getModuleMapping(project);
				if (mapping != null && mapping.getModule() != null) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_EXISTS));
				}
			} finally {
				if (null != editModel) {
					editModel.releaseAccess(key);
				}
			}
		}
		return super.doValidateProperty(propertyName);
	}

	protected boolean isJ2EEModule(IProject aProject) {
		try {
			return aProject.hasNature(EarModuleManager.getEJBModuleExtension().getNatureID()) || aProject.hasNature(EarModuleManager.getJCAModuleExtension().getNatureID()) || aProject.hasNature(EarModuleManager.getWebModuleExtension().getNatureID()) || ApplicationClientNatureRuntime.hasRuntime(aProject);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultURIExtension()
	 */
	protected String getDefaultURIExtension() {
		IProject archiveProj = (IProject) getProperty(ARCHIVE_PROJECT);
		J2EENature j2eeNature = J2EENature.getRegisteredRuntime(archiveProj);
		if (j2eeNature != null && j2eeNature.getDeploymentDescriptorType() == XMLResource.WEB_APP_TYPE)
			return "war"; //$NON-NLS-1$
		if (j2eeNature != null && j2eeNature.getDeploymentDescriptorType() == XMLResource.RAR_TYPE)
			return "rar"; //$NON-NLS-1$
		return super.getDefaultURIExtension();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#isModuleArchive()
	 */
	public boolean isModuleArchive() {
		return true;
	}

	public boolean isWebModuleArchive() {
		return false;
	}
}