/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Collection;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EJBClientComponentDataModel extends WTPOperationDataModel {
	
	/**
	 * Required, type String
	 */		
	public static final String PROJECT_NAME = "EJBClientComponentDataModel.PROJECT_NAME"; 
	/**
	 * Required, type String
	 */		

	public static final String EJB_MODULE_NAME = "EJBClientComponentDataModel.EJB_MODULE_NAME"; //$NON-NLS-1$
	/**
	 * Optional, type String
	 */		

	public static final String CLIENT_MODULE_NAME = "EJBClientComponentDataModel.CLIENT_MODULE_NAME"; //$NON-NLS-1$
	
	/**
	 * Required, type String
	 */
	public static final String CLIENT_MODULE_URI = "EJBClientComponentDataModel.CLIENT_MODULE_URI"; //$NON-NLS-1$	
	
	/**
	 * Required, type String
	 */	
	public static final String MODULE_DEPLOY_NAME = "EJBClientComponentDataModel.MODULE_DEPLOY_NAME";//$NON-NLS-1$
	
	public static final String J2EE_MODULE_VERSION = "EJBClientComponentDataModel.J2EE_MODULE_VERSION";	//$NON-NLS-1$


	/**
	 * Optional, type boolean
	 */
	public static final String DELETE_WHEN_FINISHED = "EJBClientComponentDataModel.DELETE_WHEN_FINISHED"; //$NON-NLS-1$

	private static final String NESTED_MODEL_JAVA_CREATION = "EJBClientComponentDataModel.NESTED_MODEL_JAVA_CREATION"; //$NON-NLS-1$

	public static final String USE_ANNOTATIONS = "EJBClientComponentDataModel.USE_ANNOTATIONS"; //$NON-NLS-1$
	
	/**
	 * type String
	 */
	public static final String JAVASOURCE_FOLDER = "J2EEComponentCreationDataModel.JAVASOURCE_FOLDER"; //$NON-NLS-1$
	

	//private JavaProjectCreationDataModel nestedProjModel;

	/**
	 *  
	 */
	public EJBClientComponentDataModel() {
		super();
	}

	protected void init() {
		//setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
		super.init();
	}

	protected void initNestedModels() {
		super.initNestedModels();
		//nestedProjModel = new JavaProjectCreationDataModel();
		//addNestedModel(NESTED_MODEL_JAVA_CREATION, nestedProjModel);
		//nestedProjModel.setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{"ejbModule"}); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(EJB_MODULE_NAME);
		addValidBaseProperty(CLIENT_MODULE_NAME);
		addValidBaseProperty(CLIENT_MODULE_URI);
		addValidBaseProperty(MODULE_DEPLOY_NAME);
		addValidBaseProperty(J2EE_MODULE_VERSION);
		addValidBaseProperty(DELETE_WHEN_FINISHED);
		addValidBaseProperty(USE_ANNOTATIONS);
		addValidBaseProperty(JAVASOURCE_FOLDER);
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
		if (propertyName.equals(CLIENT_MODULE_NAME)) {
			setProperty(CLIENT_MODULE_NAME, propertyValue );
		}
		boolean retVal = super.doSetProperty(propertyName, propertyValue);
		return retVal;
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
//		if (event.getDataModel() == nestedProjModel && event.getPropertyName().equals(ProjectCreationDataModel.PROJECT_NAME)) {
//			setProperty(CLIENT_MODULE_NAME, event.getProperty());
//		}
		super.propertyChanged(event);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CLIENT_MODULE_NAME)) {
			return getDefaultClientModuleName();
		}
		if (propertyName.equals(CLIENT_MODULE_URI)) {
			return getStringProperty(CLIENT_MODULE_NAME).trim().replace(' ', '_') + ".jar"; //$NON-NLS-1$
		} else if (propertyName.equals(DELETE_WHEN_FINISHED))
			return Boolean.TRUE;
		
		if(propertyName.equals(MODULE_DEPLOY_NAME)){
			return getStringProperty(CLIENT_MODULE_NAME)+".jar";
		}
		if( propertyName.equals(JAVASOURCE_FOLDER)){
			return getStringProperty(CLIENT_MODULE_NAME)+"/"+"ejbModule"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return super.getDefaultProperty(propertyName);
	}

	private IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	private String getDefaultClientModuleName() {
		String ejbModuleName = getStringProperty(EJB_MODULE_NAME);
		String moduleName = ejbModuleName + "Client"; //$NON-NLS-1$
		return moduleName;
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
			if ( propertyName.equals(CLIENT_MODULE_NAME)) {
				String ejbModuleName = getStringProperty(EJB_MODULE_NAME);
				String clientModuleName = getStringProperty(CLIENT_MODULE_NAME);
				if (ejbModuleName.equals(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				} else if (!CoreFileSystemLibrary.isCaseSensitive() && ejbModuleName.equalsIgnoreCase(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				}
			}
			if (propertyName.equals(CLIENT_MODULE_URI)) {
				status = validateClientJarUri();
			}
		}
		return status;
	}


	public IStatus validateClientJarUri() {
		String clientJarURI = getStringProperty(CLIENT_MODULE_URI);
		if (clientJarURI == null || clientJarURI.trim().length() == 0)
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_JAR_URI_NOT_RESOLVE_UI_"), null); //$NON-NLS-1$
	
		return OK_STATUS;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBClientComponentCreationOperation(this);
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
		super.notifyListeners(event);
	}

	public boolean hasExistingClientJar(){
		return false;
	}
}