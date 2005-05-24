/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

//TODO delete
/**
 * @deprecated
 *
 */
public class EJBClientComponentDataModel extends JavaComponentCreationDataModel {
	
	/**
	 * Required, type String
	 */		
	public static final String EJB_COMPONENT_NAME = "EJBClientComponentDataModel.EJB_MODULE_NAME"; //$NON-NLS-1$
	
	/**
	 * Required, type String
	 */		
	public static final String EJB_PROJECT_NAME = "EJBClientComponentDataModel.EJB_PROJECT_NAME"; //$NON-NLS-1$	
	
	/**
	 * Required, type String
	 */		
	public static final String EJB_COMPONENT_DEPLOY_NAME = "EJBClientComponentDataModel.EJB_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
	

	/**
	 * Required, type String
	 */
	public static final String CLIENT_COMPONENT_URI = "EJBClientComponentDataModel.CLIENT_COMPONENT_URI"; //$NON-NLS-1$

	/**
	 * Optional, type boolean
	 */
	public static final String DELETE_WHEN_FINISHED = "EJBClientComponentDataModel.DELETE_WHEN_FINISHED"; //$NON-NLS-1$
	
	/**
	 * type Boolean, default false
	 */
	public static final String ADD_TO_EAR = "EJBClientComponentDataModel.ADD_TO_EAR"; //$NON-NLS-1$

	/**
	 * type String
	 */
	
	public static final String EAR_MODULE_DEPLOY_NAME = "EJBClientComponentDataModel.EAR_MODULE_DEPLOY_NAME"; //$NON-NLS-1$
	
	/**
	 * type boolean
	 */
	
	public static final String CREATE_PROJECT = "EJBClientComponentDataModel.CREATE_PROJECT"; //$NON-NLS-1$
	
	
	/**
	 * This  needs to be set up to ensure that other j2ee component is properly added as dependent component of ear 
	 */
	private URI earComponentHandle;
	
	/**
	 *  
	 */
	public EJBClientComponentDataModel() {
		super();
	}

	/**
	 * 
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(EJB_COMPONENT_NAME);
		addValidBaseProperty(EJB_PROJECT_NAME);
		addValidBaseProperty(EJB_COMPONENT_DEPLOY_NAME);
		addValidBaseProperty(CLIENT_COMPONENT_URI);
		addValidBaseProperty(DELETE_WHEN_FINISHED);
		addValidBaseProperty(JAVASOURCE_FOLDER);
		addValidBaseProperty(ADD_TO_EAR);
		addValidBaseProperty(EAR_MODULE_DEPLOY_NAME);
		addValidBaseProperty(CREATE_PROJECT);
		super.initValidBaseProperties();
	}

	/**
	 * 
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean retVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(COMPONENT_NAME)) {
			if (!isSet(CLIENT_COMPONENT_URI))
				notifyDefaultChange(CLIENT_COMPONENT_URI);
		}
		return retVal;
	}

	/**
	 * 
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(COMPONENT_NAME)) {
			return getDefaultClientModuleName();
		} else if (propertyName.equals(CLIENT_COMPONENT_URI)) {
			return getStringProperty(COMPONENT_NAME).trim().replace(' ', '_') + ".jar"; //$NON-NLS-1$
		} else if (propertyName.equals(DELETE_WHEN_FINISHED)) {
			return Boolean.TRUE;
		} else if(propertyName.equals(COMPONENT_DEPLOY_NAME)){
			return getStringProperty(COMPONENT_NAME); //$NON-NLS-1$
		} else if( propertyName.equals(JAVASOURCE_FOLDER)){
			return getStringProperty(COMPONENT_NAME)+"/"+"ejbModule"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * 
	 * @return
	 */
	private String getDefaultClientModuleName() {
		String ejbModuleName = getStringProperty(EJB_COMPONENT_NAME);
		String moduleName = ejbModuleName + "Client"; //$NON-NLS-1$
		return moduleName;
	}

	/**
	 * 
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (NESTED_MODEL_VALIDATION_HOOK.equals(propertyName)) {
			return OK_STATUS;
		}
		IStatus status = super.doValidateProperty(propertyName);
		if (status.isOK()) {
			if ( propertyName.equals(COMPONENT_NAME)) {
				String ejbModuleName = getStringProperty(EJB_COMPONENT_NAME);
				String clientModuleName = getStringProperty(COMPONENT_NAME);
				if (ejbModuleName.equals(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				} else if (!CoreFileSystemLibrary.isCaseSensitive() && ejbModuleName.equalsIgnoreCase(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				}
			}
			if (propertyName.equals(CLIENT_COMPONENT_URI)) {
				status = validateClientJarUri();
			}
		}
		return status;
	}

	/**
	 * 
	 * @return
	 */
	public IStatus validateClientJarUri() {
		String clientJarURI = getStringProperty(CLIENT_COMPONENT_URI);
		if (clientJarURI == null || clientJarURI.trim().length() == 0)
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_JAR_URI_NOT_RESOLVE_UI_"), null); //$NON-NLS-1$
	
		return OK_STATUS;
	}

	/**
	 * 
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBClientComponentCreationOperation(this);
	}

	public boolean hasExistingClientJar(){
		return false;
	}

	protected EClass getComponentType() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Integer getDefaultComponentVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public void enableValidation() {
		super.enableValidation();
	}

	/**
	 * 
	 */
	public void disableValidation() {
		super.disableValidation();
	}
	
	public String getEARDeployName(){
		return getStringProperty(EAR_MODULE_DEPLOY_NAME);
	}
	public String getEJBComponentName(){
		return getStringProperty(EJB_COMPONENT_NAME);
	}
	
	public String getEJBDeployName(){
		return getStringProperty(EJB_COMPONENT_DEPLOY_NAME);
	}	
	public String getEJBProjectName(){
		return getStringProperty(EJB_PROJECT_NAME);
	}
	public URI getEarComponentHandle() {
		return earComponentHandle;
	}
	public void setEarComponentHandle(URI earComponentHandle) {
		this.earComponentHandle = earComponentHandle;
	}
}