/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Oct 31, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is a common super class used to create J2EE Components.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class J2EEArtifactCreationDataModel extends EditModelOperationDataModel {
	/**
	 * An optional dataModel propertyName for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then a
	 * default deployment descriptor and supporting bindings files will be generated.
	 */
	public static final String CREATE_DEFAULT_FILES = "J2EEArtifactCreationDataModel.CREATE_DEFAULT_FILES"; //$NON-NLS-1$

	/**
	 * An optional dataModel property for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then the
	 * server target specified by dataModel property <code>SERVER_TARGET_ID</code> will be set on
	 * the generated artifact.
	 * 
	 * @see SERVER_TARGET_ID
	 */
	public static final String ADD_SERVER_TARGET = "J2EEArtifactCreationDataModel.ADD_SERVER_TARGET"; //$NON-NLS-1$

	/**
	 * Optional, type String
	 */
	public static final String FINAL_PERSPECTIVE = "J2EEArtifactCreationDataModel.FINAL_PERSPECTIVE"; //$NON-NLS-1$

	/**
	 * An optonal dataModel propertyName for a <code>java.lang.String</code> type. Sets the local
	 * file system location for the described project. The path must be either an absolute file
	 * system path, or a relative path whose first segment is the name of a defined workspace path
	 * variable. The default value is the workspace's default location.
	 * 
	 * @see ProjectCreationDataModel.PROJECT_LOCATION
	 */
	public static final String PROJECT_LOCATION = ProjectCreationDataModel.PROJECT_LOCATION;

	/**
	 * An optional dataModel property for a <code>java.lang.String</code> type. This is used to
	 * specify the server target and is required if the <code>ADD_SERVER_TARGET</code> property is
	 * set to <code>Boolean.TRUE</code>.
	 * 
	 * @see ServerTargetDataModel.RUNTIME_TARGET_ID
	 */
	public static final String SERVER_TARGET_ID = ServerTargetDataModel.RUNTIME_TARGET_ID;
	public static final String CLASSPATH_ENTRIES = JavaProjectCreationDataModel.CLASSPATH_ENTRIES;

	protected static final String IS_ENABLED = "J2EEArtifactCreationDataModel.IS_ENABLED"; //$NON-NLS-1$

	private static final String NESTED_MODEL_PROJECT_CREATION = "J2EEArtifactCreationDataModel.NESTED_MODEL_PROJECT_CREATION"; //$NON-NLS-1$
	private static final String NESTED_MODEL_SERVER_TARGET = "J2EEArtifactCreationDataModel.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$

	private ProjectCreationDataModel projectDataModel;
	private ServerTargetDataModel serverTargetDataModel;

	protected void initValidBaseProperties() {
		addValidBaseProperty(CREATE_DEFAULT_FILES);
		addValidBaseProperty(ADD_SERVER_TARGET);
		addValidBaseProperty(IS_ENABLED);
		addValidBaseProperty(FINAL_PERSPECTIVE);
		super.initValidBaseProperties();
	}

	protected void initNestedModels() {
		super.initNestedModels();
		initProjectModel();
		addNestedModel(NESTED_MODEL_PROJECT_CREATION, projectDataModel);

		serverTargetDataModel = new ServerTargetDataModel();
		addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetDataModel);
	}

	protected void initProjectModel() {
		projectDataModel = new ProjectCreationDataModel();
	}

	/**
	 * @param projectDataModel
	 *            The projectDataModel to set.
	 */
	protected final void setProjectDataModel(ProjectCreationDataModel projectDataModel) {
		this.projectDataModel = projectDataModel;
	}

	public final ProjectCreationDataModel getProjectDataModel() {
		return projectDataModel;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		super.doSetProperty(propertyName, propertyValue);
		if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName)) {
			projectDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, propertyValue);
			serverTargetDataModel.setProperty(ServerTargetDataModel.PROJECT_NAME, propertyValue);
		} else if (IS_ENABLED.equals(propertyName)) {
			notifyEnablementChange(SERVER_TARGET_ID);
			notifyEnablementChange(PROJECT_NAME);
		}
		return true;
	}

	public final ServerTargetDataModel getServerTargetDataModel() {
		return serverTargetDataModel;
	}

	protected Boolean basicIsEnabled(String propertyName) {
		return (Boolean) getProperty(IS_ENABLED);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getFlag() == PROPERTY_CHG) {
			WTPOperationDataModel dm = event.getDataModel();
			if (dm == projectDataModel || dm == serverTargetDataModel) {
				String prop = event.getPropertyName();
				if (prop.equals(ProjectCreationDataModel.PROJECT_NAME) || prop.equals(ServerTargetDataModel.PROJECT_NAME)) {
					setProperty(PROJECT_NAME, event.getNewValue()); //setting on outer will synch
					// all others
					return;
				}
			}
		}
		super.propertyChanged(event);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(PROJECT_NAME)) {
			IStatus status = projectDataModel.validateProperty(ProjectCreationDataModel.PROJECT_NAME);
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
		if (propertyName.equals(CREATE_DEFAULT_FILES) || propertyName.equals(ADD_SERVER_TARGET) || propertyName.equals(IS_ENABLED)) {
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