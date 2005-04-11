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
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperationDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class AddArchiveToEARDataModel extends EARArtifactEditOperationDataModel {
	/**
	 * Required - This is the module that is being added to the EAR (designated by the
	 * PROJECT_NAME). type = WorkbenchComponent
	 */
	public static final String ARCHIVE_MODULE = "AddModuleToEARDataModel.ARCHIVE_PROJECT"; //$NON-NLS-1$
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
		addValidBaseProperty(ARCHIVE_MODULE);
		addValidBaseProperty(ARCHIVE_URI);
		addValidBaseProperty(SYNC_TARGET_RUNTIME);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (PROJECT_NAME.equals(propertyName)) {
			IProject project = getTargetProject();
			try {
				if (project == null || !project.exists() || !project.isOpen() || !project.hasNature(IEARNatureConstants.NATURE_ID)) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_NOT_EAR));
				}
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
		if (ARCHIVE_MODULE.equals(propertyName)) {
		    WorkbenchComponent module = (WorkbenchComponent) getProperty(propertyName);
			if (null == module) {
				return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_MODULE_MODULE_NULL));
			}
			IProject containingProj = getProjectForGivenComponent(module);
			if (!containingProj.isOpen()) {
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
				if (earEdit.uriExists(uri)) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.ADD_PROJECT_URI_EXISTS));
				}
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
		if (propertyName.equals(ARCHIVE_MODULE)) {
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
	    WorkbenchComponent module = (WorkbenchComponent) getProperty(ARCHIVE_MODULE);
		if (module != null) {
			StringBuffer b = new StringBuffer();
			b.append(module.getName().replace(' ', '_'));
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
	    WorkbenchComponent module = (WorkbenchComponent) getProperty(ARCHIVE_MODULE);
	    IProject modProject = getProjectForGivenComponent(module);
		if (modProject != null)
			model.setProperty(ServerTargetDataModel.PROJECT_NAME, modProject.getName());
		return model;
	}

	public IProject getProjectForGivenComponent(WorkbenchComponent wbComp) {
	    IProject modProject = null; 
		modProject = StructureEdit.getContainingProject(wbComp); 
	    return modProject;
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