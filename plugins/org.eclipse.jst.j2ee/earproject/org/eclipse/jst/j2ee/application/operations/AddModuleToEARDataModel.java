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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AddModuleToEARDataModel extends AddArchiveToEARDataModel {
	public static final String MODULE_PROJECT_LIST = "AddModuleToEARDataModel.MODULE_PROJECT_LIST"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MODULE_PROJECT_LIST);
	}
	/**
	 * (non-Javadoc)
	 *  * @deprecated - This method is deprecated module must be passed
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultArchiveURI()
	 */
	public static AddModuleToEARDataModel createAddToEARDataModel(String earProjectName, IProject moduleProject) {
		AddModuleToEARDataModel model = new AddModuleToEARDataModel();
	//	model.setProperty(AddModuleToEARDataModel.PROJECT_NAME, earProjectName);
	//	model.setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, moduleProject);
		return model;
	}
	public static AddModuleToEARDataModel createAddToEARDataModel(String earModuleName, WorkbenchComponent module) {
		AddModuleToEARDataModel model = new AddModuleToEARDataModel();
		model.setProperty(AddModuleToEARDataModel.MODULE_NAME, earModuleName);
		model.setProperty(AddModuleToEARDataModel.ARCHIVE_MODULE, module);
		return model;
	}
	public AddModuleToEARDataModel getAppropriateDataModel() {
		AddModuleToEARDataModel model = this;
		WorkbenchComponent wbComp = (WorkbenchComponent) getProperty(AddUtilityProjectToEARDataModel.ARCHIVE_MODULE);
		if (wbComp != null && wbComp.getComponentType().getModuleTypeId() == IModuleConstants.JST_WEB_MODULE) {
			model = AddWebModuleToEARDataModel.createAddWebModuleToEARDataModel(getStringProperty(PROJECT_NAME), getProjectForGivenComponent(wbComp));
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
	    return getStringProperty(MODULE_NAME);
	    //TODO: reimplement 
//		IProject archiveProj = (IProject) getProperty(ARCHIVE_PROJECT);
//		J2EENature j2eeNature = J2EENature.getRegisteredRuntime(archiveProj);
//		if (j2eeNature != null) {
//			J2EEModuleNature modNature = (J2EEModuleNature) j2eeNature;
//			String existingURI = modNature.getModuleUriInFirstEAR();
//			if (existingURI != null)
//				return existingURI;
//		}
//		return super.getDefaultArchiveURI();
	}

	protected IStatus doValidateProperty(String propertyName) {
//		if (ARCHIVE_PROJECT.equals(propertyName)) {
//			IProject project = (IProject) getProperty(propertyName);
//			IStatus status = super.doValidateProperty(propertyName);
//			if (!status.isOK()) {
//				return status;
//			}
//			if (!isJ2EEModule(project)) {
//				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_TYPE));
//			}
//		}
		return super.doValidateProperty(propertyName);
	}

//	protected boolean isJ2EEModule(IProject aProject) {
//		try {
//			return aProject.hasNature(EarModuleManager.getEJBModuleExtension().getNatureID()) || aProject.hasNature(EarModuleManager.getJCAModuleExtension().getNatureID()) || aProject.hasNature(EarModuleManager.getWebModuleExtension().getNatureID()) || ApplicationClientNatureRuntime.hasRuntime(aProject);
//		} catch (CoreException e) {
//			Logger.getLogger().logError(e);
//			return false;
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultURIExtension()
	 */
	protected String getDefaultURIExtension() {
	    WorkbenchComponent wbComp = (WorkbenchComponent) getProperty(AddUtilityProjectToEARDataModel.ARCHIVE_MODULE);
		if (wbComp != null && wbComp.getComponentType().getModuleTypeId() == IModuleConstants.JST_WEB_MODULE) 
			return "war"; //$NON-NLS-1$
		if (wbComp != null && wbComp.getComponentType().getModuleTypeId() == IModuleConstants.JST_CONNECTOR_MODULE) 
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