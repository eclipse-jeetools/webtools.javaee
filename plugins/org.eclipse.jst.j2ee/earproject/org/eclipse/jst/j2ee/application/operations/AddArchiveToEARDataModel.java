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
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperationDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class AddArchiveToEARDataModel extends EARArtifactEditOperationDataModel {
	/**
	 * Required - This is the project that is being added to the EAR (designated by the
	 * PROJECT_NAME). type = IProject
	 */
	public static final String ARCHIVE_PROJECT = "AddModuleToEARDataModel.ARCHIVE_PROJECT"; //$NON-NLS-1$
	/**
	 * Optional - The URI used for the archive in the EAR project. type = String
	 */
	public static final String ARCHIVE_URI = "AddModuleToEARDataModel.URI"; //$NON-NLS-1$
	/**
	 * Required - This is the boolean value to sync the module target runtime to that of the EAR
	 * project default - false;
	 */
	public static final String SYNC_TARGET_RUNTIME = "AddModuleToEARDataModel.SYNC_TARGET_RUNTIME"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ARCHIVE_PROJECT);
		addValidBaseProperty(ARCHIVE_URI);
		addValidBaseProperty(SYNC_TARGET_RUNTIME);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (PROJECT_NAME.equals(propertyName)) {
			IProject project = getTargetProject();
			try {
				if (project == null || !project.exists() || !project.isOpen() || !project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_NOT_EAR));
				}
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
		if (ARCHIVE_PROJECT.equals(propertyName)) {
			IProject project = (IProject) getProperty(propertyName);
			if (null == project) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_NULL));
			}
			if (!project.isOpen()) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_CLOSED));
			}
		} else if (ARCHIVE_URI.equals(propertyName)) {
			String uri = getStringProperty(ARCHIVE_URI);
			if (uri == null || uri.trim().length() == 0) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_URI_EMPTY));
			}

			EARArtifactEdit earEdit = null;
			try {
				earEdit = getEARArtifactEditForRead();
				//TODO fix up uri check for existence
//				if (earEdit.uriExists(uri)) {
//					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_URI_EXISTS));
//				}
			} finally {
				if (earEdit != null) {
					earEdit.dispose();
				}
			}
		}
		return super.doValidateProperty(propertyName);
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean returnValue = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(ARCHIVE_PROJECT)) {
			notifyDefaultChange(ARCHIVE_URI);
		}
		return returnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ARCHIVE_URI))
			return getDefaultArchiveURI();
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	protected String getDefaultArchiveURI() {
		IProject proj = (IProject) getProperty(ARCHIVE_PROJECT);
		if (proj != null) {
			StringBuffer b = new StringBuffer();
			b.append(proj.getName().replace(' ', '_'));
			b.append('.');
			b.append(getDefaultURIExtension());
			return b.toString();
		}
		return ""; //$NON-NLS-1$
	}

	protected String getDefaultURIExtension() {
		return "jar"; //$NON-NLS-1$
	}

	public boolean isModuleArchive() {
		return false;
	}

	/**
	 * @return
	 */
	public ServerTargetDataModel getServerTargetDataModel() {
		ServerTargetDataModel model = new ServerTargetDataModel();
		IRuntime earRuntime = ServerCore.getProjectProperties(getTargetProject()).getRuntimeTarget();
		if (earRuntime != null)
			model.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, earRuntime.getId());
		else
			model.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, null);
		IProject modProject = (IProject) getProperty(ARCHIVE_PROJECT);
		if (modProject != null)
			model.setProperty(ServerTargetDataModel.PROJECT_NAME, modProject.getName());
		return model;
	}

	public AddArchiveToEARDataModel createArchiveModel() {
		AddArchiveToEARDataModel model = null;
//		switch (ModuleType) {
//			case XMLResource.WEB_APP_TYPE :
//				model = new AddWebModuleToEARDataModel();
//				break;
//			default :
//				model = new AddModuleToEARDataModel();
//				break;
//		}
//		model = new AddUtilityProjectToEARDataModel();
		return model;
	}
}