/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EJBClientProjectDataModel extends EditModelOperationDataModel {

	public static final String EJB_PROJECT_NAME = PROJECT_NAME;

	public static final String CLIENT_PROJECT_NAME = "EJBClientProjectDataModel.CLIENT_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * Required, type String
	 */
	public static final String CLIENT_PROJECT_URI = "EJBClientProjectDataModel.CLIENT_PROJECT_URI"; //$NON-NLS-1$
	/**
	 * Optional, type boolean
	 */
	public static final String DELETE_WHEN_FINISHED = "EJBClientProjectDataModel.DELETE_WHEN_FINISHED"; //$NON-NLS-1$

	private static final String NESTED_MODEL_JAVA_CREATION = "EJBClientProjectDataModel.NESTED_MODEL_JAVA_CREATION"; //$NON-NLS-1$

	public static final String USE_ANNOTATIONS = "EJBClientProjectDataModel.USE_ANNOTATIONS"; //$NON-NLS-1$

	private JavaProjectCreationDataModel nestedProjModel;

	/**
	 *  
	 */
	public EJBClientProjectDataModel() {
		super();
	}

	protected void init() {
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
		super.init();
	}

	protected void initNestedModels() {
		super.initNestedModels();
		nestedProjModel = new JavaProjectCreationDataModel();
		addNestedModel(NESTED_MODEL_JAVA_CREATION, nestedProjModel);
		nestedProjModel.setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{"ejbModule"}); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(CLIENT_PROJECT_NAME);
		addValidBaseProperty(CLIENT_PROJECT_URI);
		addValidBaseProperty(DELETE_WHEN_FINISHED);
		addValidBaseProperty(USE_ANNOTATIONS);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean hadDefaultProjectName = false;
		if (propertyName.equals(PROJECT_NAME)) {
			hadDefaultProjectName = getStringProperty(CLIENT_PROJECT_NAME).equals(getDefaultProperty(CLIENT_PROJECT_NAME));
		}
		boolean retVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(PROJECT_NAME)) {
			if (hadDefaultProjectName) {
				setProperty(CLIENT_PROJECT_NAME, getDefaultProperty(CLIENT_PROJECT_NAME));
			}
		} else if (CLIENT_PROJECT_NAME.equals(propertyName)) {
			nestedProjModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, propertyValue);
			notifyDefaultChange(CLIENT_PROJECT_URI);
		}
		return retVal;
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getDataModel() == nestedProjModel && event.getPropertyName().equals(ProjectCreationDataModel.PROJECT_NAME)) {
			setProperty(CLIENT_PROJECT_NAME, event.getProperty());
		}
		super.propertyChanged(event);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CLIENT_PROJECT_NAME)) {
			return getDefaultClientProjectName();
		}
		if (propertyName.equals(CLIENT_PROJECT_URI)) {
			return getStringProperty(CLIENT_PROJECT_NAME).trim().replace(' ', '_') + ".jar"; //$NON-NLS-1$
		} else if (propertyName.equals(DELETE_WHEN_FINISHED))
			return Boolean.TRUE;
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	public boolean hasExistingClientJar() {
		IProject ejbProject = getEJBProject();
		if (ejbProject != null) {
			EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(ejbProject);
			if (nature != null && nature.hasEJBClientJARProject())
				return true;
			return false;
		}
		return false;
	}

	protected IProject getEJBProject() {
		String projectName = getStringProperty(PROJECT_NAME);
		if (!ProjectCreationDataModel.validateProjectName(projectName).isOK()) {
			return null;
		}
		return getWorkspace().getRoot().getProject(projectName);
	}


	private IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	private String getDefaultClientProjectName() {
		String ejbProjectName = getStringProperty(PROJECT_NAME);
		String projectName = ejbProjectName + "Client"; //$NON-NLS-1$
		String baseName = projectName + "_"; //$NON-NLS-1$
		int index = 0;
		IProject project = ProjectCreationDataModel.getProjectHandleFromProjectName(projectName);
		if (null == project) {
			return "";//$NON-NLS-1$
		} else if (!project.exists()) {
			return projectName;
		}
		for (int i = 0; i < 10; i++) {
			projectName = baseName + index;
			project = ProjectCreationDataModel.getProjectHandleFromProjectName(projectName);
			if (null != project && !project.exists()) {
				return projectName;
			}
		}
		return "";//$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (NESTED_MODEL_VALIDATION_HOOK.equals(propertyName)) {
			return OK_STATUS;
		}
		IStatus status = super.doValidateProperty(propertyName);
		if (status.isOK()) {
			if (propertyName.equals(PROJECT_NAME) || propertyName.equals(CLIENT_PROJECT_NAME)) {
				String ejbProjectName = getStringProperty(EJB_PROJECT_NAME);
				String clientProjectName = getStringProperty(CLIENT_PROJECT_NAME);
				if (ejbProjectName.equals(clientProjectName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				} else if (!CoreFileSystemLibrary.isCaseSensitive() && ejbProjectName.equalsIgnoreCase(clientProjectName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				}
			}
			if (propertyName.equals(PROJECT_NAME)) {
				if (hasExistingClientJar())
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("EJB_JAR_already_has_client_Jar_"), null); //$NON-NLS-1$ 
				status = checkForValidProjectName(getStringProperty(PROJECT_NAME));
				if (status.isOK())
					status = validateClientJarUri();
			} else if (propertyName.equals(CLIENT_PROJECT_URI)) {
				status = validateClientJarUri();
			}
		}
		return status;
	}

	/**
	 * @param aProjectName
	 * @return
	 */
	private IStatus checkForValidProjectName(String aProjectName) {
		return ResourcesPlugin.getWorkspace().validateName(aProjectName, IResource.PROJECT);
	}

	public IStatus validateClientJarUri() {
		String clientJarURI = getStringProperty(CLIENT_PROJECT_URI);
		if (clientJarURI == null || clientJarURI.trim().length() == 0)
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_JAR_URI_NOT_RESOLVE_UI_"), null); //$NON-NLS-1$ 
		EARNatureRuntime[] natures = getReferencingEARNatures();
		EAREditModel editModel = null;
		IProject earProject = null;
		String ejbURI = null;
		String normalizedURI = null;
		if (natures != null) {
			for (int i = 0; i < natures.length; i++) {
				earProject = natures[i].getProject();
				editModel = natures[i].getEarEditModelForRead(this);
				try {
					ejbURI = getEJBModuleURI(natures[i]);
					if (ejbURI == null)
						continue;
					if (clientJarURI == null || clientJarURI.trim().length() == 0)
						normalizedURI = null;
					else
						normalizedURI = ArchiveUtil.deriveEARRelativeURI(clientJarURI, ejbURI);
					if (normalizedURI == null || normalizedURI.trim().length() == 0)
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_JAR_URI_NOT_RESOLVE_UI_"), null); //$NON-NLS-1$ 
					if (editModel.moduleMappingExists(normalizedURI.trim()) || editModel.utilJarMappingExists(normalizedURI))
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("EJB_Client_JAR_name_exists_"), null); //$NON-NLS-1$ 
					else if (earProject.findMember(normalizedURI.trim()) != null)
						return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("EJB_Client_JAR_name_exists_"), null); //$NON-NLS-1$ 
				} finally {
					if (editModel != null) {
						editModel.releaseAccess(this);
						editModel = null;
					}
				}
			}
		}
		return OK_STATUS;
	}

	private EARNatureRuntime[] getReferencingEARNatures() {
		IProject ejbProject = getEJBProject();
		if (ejbProject != null && ejbProject.exists()) {
			EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(ejbProject);
			if (nature != null)
				return nature.getReferencingEARProjects();
		} /*
		   * else { EARNatureRuntime earNature = getEARNature(); if (earNature != null) return new
		   * EARNatureRuntime[] {earNature}; }
		   */
		return null;
	}

	private String getEJBModuleURI(EARNatureRuntime nature) {
		IProject ejbProject = getEJBProject();
		if (ejbProject.exists())
			return nature.getJARUri(ejbProject);
		return ejbProject.getName() + ".jar"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBClientJarCreationOperation(this);
	}

	public JavaProjectCreationDataModel getNestedJavaProjectCreationDM() {
		return nestedProjModel;
	}

	public void enableValidation() {
		super.enableValidation();
	}

	public void disableValidation() {
		super.disableValidation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#notifyListeners(org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModelEvent)
	 */
	protected void notifyListeners(WTPOperationDataModelEvent event) {
		if (event.getDataModel() == nestedProjModel && !event.getPropertyName().equals(ProjectCreationDataModel.PROJECT_LOCATION)) {
			return;
		}
		super.notifyListeners(event);
	}
}