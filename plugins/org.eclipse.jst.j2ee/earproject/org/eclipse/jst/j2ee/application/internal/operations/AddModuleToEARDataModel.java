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

package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 *
 */
public class AddModuleToEARDataModel extends AddArchiveToEARDataModel {
	
	/**
	 * 
	 */
	public static final String MODULE_PROJECT_LIST = "AddModuleToEARDataModel.MODULE_PROJECT_LIST"; //$NON-NLS-1$

	/**
	 * 
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MODULE_PROJECT_LIST);
	}
	
	/**
	 * 
	 * @param earModuleName
	 * @param module
	 * @return
	 */
	public static AddModuleToEARDataModel createAddToEARDataModel(String earModuleName, WorkbenchComponent module) {
		AddModuleToEARDataModel model = new AddModuleToEARDataModel();
		model.setProperty(ArtifactEditOperationDataModel.MODULE_NAME, earModuleName);
		model.setProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE, module);
		return model;
	}
	
	/**
	 * 
	 * @return
	 */
	public AddModuleToEARDataModel getAppropriateDataModel() {
		AddModuleToEARDataModel model = this;
		WorkbenchComponent wbComp = (WorkbenchComponent) getProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE);
		if (wbComp != null && wbComp.getComponentType().getComponentTypeId() == IModuleConstants.JST_WEB_MODULE) {
			//model = AddWebModuleToEARDataModel.createAddWebModuleToEARDataModel(getStringProperty(MODULE_NAME), wbComp);
			model.setProperty(AddArchiveToEARDataModel.ARCHIVE_URI, getProperty(ARCHIVE_URI));
		}
		return model;
	}

	/**
	 * 
	 */
	public WTPOperation getDefaultOperation() {
		return new AddArchiveProjectToEAROperation(this);
	}

	/**
	 * 
	 */
	protected String getDefaultArchiveURI() {
	    return getStringProperty(ARCHIVE_URI);
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

	/**
	 * 
	 */
	protected String getDefaultURIExtension() {
	    WorkbenchComponent wbComp = (WorkbenchComponent) getProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE);
		if (wbComp != null && wbComp.getComponentType().getComponentTypeId() == IModuleConstants.JST_WEB_MODULE) 
			return "war"; //$NON-NLS-1$
		if (wbComp != null && wbComp.getComponentType().getComponentTypeId() == IModuleConstants.JST_CONNECTOR_MODULE) 
			return "rar"; //$NON-NLS-1$
		return super.getDefaultURIExtension();
	}

	/**
	 * 
	 */
	public boolean isModuleArchive() {
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isWebModuleArchive() {
		return false;
	}
}