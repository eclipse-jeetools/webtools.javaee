/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
/**
 * This dataModel is a common super class used for creation of WTP Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class WTPModuleCreationDataModel extends ArtifactEditOperationDataModel {
	/**
	 * An optional dataModel propertyName for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then a
	 * default deployment descriptor and supporting bindings files will be generated.
	 */
	public static final String CREATE_DEFAULT_FILES = "WTPModuleCreationDataModel.CREATE_DEFAULT_FILES"; //$NON-NLS-1$
	/**
	 * An optional dataModel propertyName for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then a
	 * default deployment descriptor and supporting bindings files will be generated.
	 */
	public static final String SHOULD_CREATE_PROJECT = "WTPModuleCreationDataModel.CREATE_DEFAULT_FILES"; //$NON-NLS-1$

	/**
	 * Optional, type String
	 */
	public static final String FINAL_PERSPECTIVE = "WTPModuleCreationDataModel.FINAL_PERSPECTIVE"; //$NON-NLS-1$

	/**
	 * An optonal dataModel propertyName for a <code>java.lang.String</code> type. Sets the local
	 * file system location for the described project. The path must be either an absolute file
	 * system path, or a relative path whose first segment is the name of a defined workspace path
	 * variable. The default value is the workspace's default location.
	 * 
	 * @see ProjectCreationDataModel.PROJECT_LOCATION
	 */
	//public static final String PROJECT_LOCATION = J2EEProjectCreationDataModelPROJECT_LOCATION;

	protected static final String IS_ENABLED = "WTPModuleCreationDataModel.IS_ENABLED"; //$NON-NLS-1$

	private static final String NESTED_MODEL_J2EE_PROJECT_CREATION = "WTPModuleCreationDataModel.NESTED_MODEL_J2EE_PROJECT_CREATION"; //$NON-NLS-1$
	private J2EEProjectCreationDataModel j2eeProjectDataModel;
	
	protected void initValidBaseProperties() {
		addValidBaseProperty(CREATE_DEFAULT_FILES);
		addValidBaseProperty(IS_ENABLED);
		addValidBaseProperty(FINAL_PERSPECTIVE);
		super.initValidBaseProperties();
	}
	protected void initNestedModels() {
		super.initNestedModels();
		initProjectModel();
		addNestedModel(NESTED_MODEL_J2EE_PROJECT_CREATION, j2eeProjectDataModel);
	}
	protected void initProjectModel() {
	    j2eeProjectDataModel = new J2EEProjectCreationDataModel();
	}
	/**
	 * @param projectDataModel
	 *            The projectDataModel to set.
	 */
	protected final void setProjectDataModel(J2EEProjectCreationDataModel projectDataModel) {
		j2eeProjectDataModel = projectDataModel;
	}

	public final J2EEProjectCreationDataModel getProjectDataModel() {
		return j2eeProjectDataModel;
	}
	protected Boolean basicIsEnabled(String propertyName) {
		return (Boolean) getProperty(IS_ENABLED);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG) {
			WTPOperationDataModel dm = event.getDataModel();
			if (dm == j2eeProjectDataModel) {
				String prop = event.getPropertyName();
				if (prop.equals(ProjectCreationDataModel.PROJECT_NAME) || prop.equals(ServerTargetDataModel.PROJECT_NAME)) {
					setProperty(PROJECT_NAME, event.getProperty()); //setting on outer will synch
					// all others
					return;
				}
			}
		}
		super.propertyChanged(event);
	}
	
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		super.doSetProperty(propertyName, propertyValue);
		if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName)) {
			j2eeProjectDataModel.getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NAME, propertyValue);
			j2eeProjectDataModel.getServerTargetDataModel().setProperty(ServerTargetDataModel.PROJECT_NAME, propertyValue);
		} else if (IS_ENABLED.equals(propertyName)) {
			notifyEnablementChange(PROJECT_NAME);
		}
		return true;
	}
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(PROJECT_NAME)) {
			IStatus status = j2eeProjectDataModel.getProjectDataModel().validateProperty(ProjectCreationDataModel.PROJECT_NAME);
			String projectName = getStringProperty(PROJECT_NAME);
			if (status.isOK()) {
				if (projectName.indexOf("#") != -1) { //$NON-NLS-1$
					String errorMessage = J2EECreationResourceHandler.getString("InvalidCharsError"); //$NON-NLS-1$
					return WTPCommonPlugin.createErrorStatus(errorMessage);
				}
			} else
				return status;

		} else if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
			return OK_STATUS;
		}
		return super.doValidateProperty(propertyName);
	}
	
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CREATE_DEFAULT_FILES) || propertyName.equals(IS_ENABLED)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	protected boolean isResultProperty(String propertyName) {
		if (propertyName.equals(FINAL_PERSPECTIVE))
			return true;
		return super.isResultProperty(propertyName);
	}
}
