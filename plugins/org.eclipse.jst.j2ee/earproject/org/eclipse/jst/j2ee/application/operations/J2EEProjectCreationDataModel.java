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

import org.eclipse.jst.j2ee.internal.servertarget.J2EEProjectServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

public class J2EEProjectCreationDataModel extends WTPOperationDataModel {
	/**
	 * An optional dataModel property for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then the
	 * server target specified by dataModel property <code>SERVER_TARGET_ID</code> will be set on
	 * the generated artifact.
	 * 
	 * @see SERVER_TARGET_ID
	 */
	public static final String ADD_SERVER_TARGET = "J2EEProjectCreationDataModel.ADD_SERVER_TARGET"; //$NON-NLS-1$

	/**
	 * An optional dataModel property for a <code>java.lang.String</code> type. This is used to
	 * specify the server target and is required if the <code>ADD_SERVER_TARGET</code> property is
	 * set to <code>Boolean.TRUE</code>.
	 * 
	 * @see ServerTargetDataModel.RUNTIME_TARGET_ID
	 */
	public static final String SERVER_TARGET_ID = J2EEProjectServerTargetDataModel.RUNTIME_TARGET_ID;

	private J2EEProjectServerTargetDataModel serverTargetDataModel;
	private static final String NESTED_MODEL_SERVER_TARGET = "J2EEProjectCreationDataModel.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$

	private ProjectCreationDataModel projectDataModel;
	public static final String PROJECT_NAME = "J2EEProjectCreationDataModel.PROJECT_NAME";
	public static final String PROJECT_LOCATION = "J2EEProjectCreationDataModel.PROJECT_LOCATION";
	private static final String NESTED_MODEL_PROJECT_CREATION = "J2EEProjectCreationDataModel.NESTED_MODEL_PROJECT_CREATION"; //$NON-NLS-1$
	
	protected void initValidBaseProperties() {
		addValidBaseProperty(ADD_SERVER_TARGET);
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(PROJECT_LOCATION);
		super.initValidBaseProperties();
	}
	
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_SERVER_TARGET)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	public final J2EEProjectServerTargetDataModel getServerTargetDataModel() {
		return serverTargetDataModel;
	}
	
	protected void initNestedModels() {
		super.initNestedModels();
		initProjectModel();
		addNestedModel(NESTED_MODEL_PROJECT_CREATION, projectDataModel);

		serverTargetDataModel = new J2EEProjectServerTargetDataModel();
		addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetDataModel);
	}
	
	protected void initProjectModel() {
		projectDataModel = new ProjectCreationDataModel();
	}
	
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		super.doSetProperty(propertyName, propertyValue);
		if (PROJECT_NAME.equals(propertyName)) {
		    projectDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, propertyValue);
			serverTargetDataModel.setProperty(J2EEProjectServerTargetDataModel.PROJECT_NAME, propertyValue);
		}
		return true;
	}
	
    public WTPOperation getDefaultOperation() {
        return new J2EEProjectCreationOperation(this);
    }
    
	protected final void setProjectDataModel(ProjectCreationDataModel projectDataModel) {
		this.projectDataModel = projectDataModel;
	}

	public final ProjectCreationDataModel getProjectDataModel() {
		return projectDataModel;
	}
}
