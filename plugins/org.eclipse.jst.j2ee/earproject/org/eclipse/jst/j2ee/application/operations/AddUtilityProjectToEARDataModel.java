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
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
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
public class AddUtilityProjectToEARDataModel extends AddArchiveProjectToEARDataModel {

	public static AddUtilityProjectToEARDataModel createAddToEARDataModel(String earProjectName, IProject moduleProject) {
		AddUtilityProjectToEARDataModel model = new AddUtilityProjectToEARDataModel();
		model.setProperty(AddModuleToEARDataModel.PROJECT_NAME, earProjectName);
		model.setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, moduleProject);
		return model;
	}

	private static final String NESTED_MODEL_MANIFEST = "AddUtilityProjectToEARDataModel.NESTED_MODEL_MANIFEST"; //$NON-NLS-1$
	private static final String NESTED_MODEL_SERVER_TARGET = "AddUtilityProjectToEARDataModel.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$

	private UpdateManifestDataModel manifestModel;
	private ServerTargetDataModel serverTargetModel;

	public WTPOperation getDefaultOperation() {
		return new AddArchiveProjectToEAROperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#initNestedModels()
	 */
	protected void initNestedModels() {
		super.initNestedModels();
		manifestModel = new UpdateManifestDataModel();
		addNestedModel(NESTED_MODEL_MANIFEST, manifestModel);
		serverTargetModel = new ServerTargetDataModel();
		addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetModel);
	}

	/**
	 *  
	 */
	private void updateServerTargetIDs() {
		EARNatureRuntime nature = EARNatureRuntime.getRuntime(getTargetProject());
		if (nature != null) {
			serverTargetModel.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, nature.getJ2EEVersion());
			IRuntime targetType = ServerCore.getProjectProperties(getTargetProject()).getRuntimeTarget();
			if (targetType != null)
				serverTargetModel.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, targetType.getId());
			//We need to use the EAR type for now.
			serverTargetModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APPLICATION_TYPE);
		}
	}

	public String getUtilityJARUriInFirstEAR(IProject utilProject) {
		Object key = new Object();
		EARNatureRuntime earNature = J2EEProjectUtilities.getFirstReferencingEARProject(utilProject);
		if (earNature != null) {
			EAREditModel model;
			UtilityJARMapping mapping;
			model = earNature.getEarEditModelForRead(key);
			try {
				mapping = model.getUtilityJARMapping(utilProject);
				if (mapping != null && mapping.getUri() != null && mapping.getUri().length() > 0)
					return mapping.getUri();
			} finally {
				model.releaseAccess(key);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean result = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(ARCHIVE_PROJECT)) {
			IProject proj = (IProject) propertyValue;
			updateManifestModel(proj);
			updateServerTargetModel(proj);
		} else if (propertyName.equals(PROJECT_NAME))
			updateServerTargetIDs();
		return result;
	}

	/**
	 * @param proj
	 */
	private void updateServerTargetModel(IProject proj) {
		if (proj != null) {
			serverTargetModel.setProperty(ServerTargetDataModel.PROJECT_NAME, proj.getName());
		}
	}

	private void updateManifestModel(IProject proj) {
		if (proj != null)
			manifestModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, proj.getName());
		else
			manifestModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.AddArchiveProjectToEARDataModel#getDefaultArchiveURI()
	 */
	protected String getDefaultArchiveURI() {
		IProject project = (IProject) getProperty(ARCHIVE_PROJECT);
		if (project != null) {
			String defaultURI = getUtilityJARUriInFirstEAR(project);
			if (defaultURI != null)
				return defaultURI;
		}
		return super.getDefaultArchiveURI();
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (ARCHIVE_PROJECT.equals(propertyName)) {
			IStatus status = super.doValidateProperty(propertyName);
			if (!status.isOK()) {
				return status;
			}
			Object key = new Object();
			EAREditModel editModel = null;
			try {
				editModel = (EAREditModel) getEditModelForRead(key);
				if (null != editModel.getUtilityJARMapping((IProject) getProperty(ARCHIVE_PROJECT))) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_UTIL_MAPPED));
				}
			} finally {
				if (null != editModel) {
					editModel.releaseAccess(key);
				}
			}
			IProject project = (IProject) getProperty(propertyName);
			if (isWebOrClientModule(project) || !isJavaProject(project)) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_NOT_JAVA));
			}
		} else if (ARCHIVE_URI.equals(propertyName)) {
			IStatus status = super.doValidateProperty(propertyName);
			if (!status.isOK()) {
				return status;
			}
			if (!getStringProperty(ARCHIVE_URI).endsWith(".jar")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_UTIL_URI));
			}
		}
		return super.doValidateProperty(propertyName);
	}

	private boolean isJavaProject(IProject project) {
		try {
			return project.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}
		return false;
	}

	private boolean isWebOrClientModule(IProject project) {
		try {
			return project.hasNature(EarModuleManager.getWebModuleExtension().getNatureID()) || ApplicationClientNatureRuntime.hasRuntime(project);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
			return false;
		}
	}

	/**
	 * @return Returns the manifestModel.
	 */
	public UpdateManifestDataModel getManifestModel() {
		return manifestModel;
	}

	/**
	 * @return Returns the serverTargetModel.
	 */
	public ServerTargetDataModel getServerTargetModel() {
		return serverTargetModel;
	}

}