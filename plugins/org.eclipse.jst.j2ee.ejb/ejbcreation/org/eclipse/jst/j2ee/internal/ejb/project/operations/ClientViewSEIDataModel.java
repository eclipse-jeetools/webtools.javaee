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
 * Created on May 7, 2004
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author sagarwal
 *  
 */
public class ClientViewSEIDataModel extends ClientViewModel {
	private final static String SERVICE = "Service"; //$NON-NLS-1$
	public static final String SHOULD_CREATE_NEW_SEI_INTERFACE = "ClientViewSEIDataModel.SHOULD_CREATE_NEW_SEI_INTERFACE"; //$NON-NLS-1$
	public static final String SHOULD_COPY_SEI_INTERFACE = "ClientViewSEIDataModel.SHOULD_COPY_SEI_INTERFACE"; //$NON-NLS-1$
	public static final String IS_SEI_SELECTED = "ClientViewSEIDataModel.IS_SEI_SELECTED"; //$NON-NLS-1$
	public static final String SEI_INTERFACE_EXISTING = "ClientViewSEIDataModel.SEI_INTERFACE_EXISTING"; //$NON-NLS-1$
	public static final String SEI_EXISTING_NAME = "ClientViewSEIDataModel.SEI_EXISTING_NAME"; //$NON-NLS-1$
	public static final String SEI_METHOD_COLLECTION = "ClientViewSEIDataModel.SEI_METHOD_COLLECTION"; //$NON-NLS-1$
	public static final String SHOULD_DELETE_SEI = "ClientViewSEIDataModel.SHOULD_DELETE_SEI"; //$NON-NLS-1$
	public static final String SHOULD_COPY_REMOTE_METHODS_TO_NEW = "ClientViewSEIDataModel.SHOULD_COPY_REMOTE_METHODS_TO_NEW"; //$NON-NLS-1$
	public static final String SHOULD_COPY_LOCAL_METHODS_TO_NEW = "ClientViewSEIDataModel.SHOULD_COPY_LOCAL_METHODS_TO_NEW"; //$NON-NLS-1$
	public static final String SHOULD_COPY_SEI_METHODS_TO_NEW = "ClientViewSEIDataModel.SHOULD_COPY_SEI_METHODS_TO_NEW"; //$NON-NLS-1$
	private static final String SEI_INTERFACE_TYPE = "java.rmi.Remote"; //$NON-NLS-1$

	/**
	 *  
	 */
	public ClientViewSEIDataModel() {
		super();
		setProperty(SEI_METHOD_COLLECTION, new ArrayList());
	}

	/**
	 * @param ejbBean
	 */
	public ClientViewSEIDataModel(EnterpriseBean ejbBean) {
		super(ejbBean);
		setProperty(SEI_METHOD_COLLECTION, new ArrayList());
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SHOULD_CREATE_NEW_SEI_INTERFACE);
		addValidBaseProperty(SHOULD_COPY_SEI_INTERFACE);
		addValidBaseProperty(IS_SEI_SELECTED);
		addValidBaseProperty(SEI_INTERFACE_EXISTING);
		addValidBaseProperty(SEI_EXISTING_NAME);
		addValidBaseProperty(SEI_METHOD_COLLECTION);
		addValidBaseProperty(SHOULD_COPY_SEI_METHODS_TO_NEW);
		addValidBaseProperty(SHOULD_DELETE_SEI);
		addValidBaseProperty(SHOULD_COPY_REMOTE_METHODS_TO_NEW);
		addValidBaseProperty(SHOULD_COPY_LOCAL_METHODS_TO_NEW);

	}

	public void setSEISelected(boolean selection) {
		setBooleanProperty(IS_SEI_SELECTED, selection);
	}// setSEISelected

	public boolean isSEISelected() {
		return getBooleanProperty(IS_SEI_SELECTED);
	}// isSEISelected

	/**
	 * Gets the getSEIExistingName.
	 * 
	 * @return Returns a String
	 */
	public String getSEIExistingName() {
		if (this.getProperty(SEI_EXISTING_NAME) == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(SERVICE); //$NON-NLS-1$
			this.setProperty(SEI_EXISTING_NAME, b.toString());
		}// if
		return (String) getProperty(SEI_EXISTING_NAME);
	}// getLocalHomeExistingName

	/**
	 * Sets the setSEIExistingName.
	 * 
	 * @param String
	 *            localHomeExistingName - The SEIExistingName to set
	 */
	public void setSEIExistingName(String seiExistingName) {
		this.setProperty(SEI_EXISTING_NAME, seiExistingName);
	}// setLocalHomeExistingName

	/**
	 * Gets the methodCollection.
	 * 
	 * @return List
	 */
	public List getSEIMethodCollection() {
		return (List) getProperty(SEI_METHOD_COLLECTION);
	}// getMethodCollection

	/**
	 * Sets the methodCollection.
	 * 
	 * @param List
	 *            methodCollection - The seiMethodCollection to set
	 */
	public void setSEIMethodCollection(List seiMethodCollection) {
		setProperty(SEI_METHOD_COLLECTION, seiMethodCollection);
	}// setMethodCollection

	/**
	 * Sets the setSEIExisting.
	 * 
	 * @param JavaClass
	 *            seiExisting - The getSEIExisting to get
	 */
	public void setSEIExisting(JavaClass seiExisting) {
		this.setProperty(SEI_INTERFACE_EXISTING, seiExisting);
	}// setSEIExisting

	/**
	 * Gets the getSEIExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getSEIExisting() {
		return (JavaClass) getProperty(SEI_INTERFACE_EXISTING);
	}// getSEIExisting

	/**
	 * Returns the shouldCreateSEICommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateSEICommand() {
		return this.getBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE);
	}

	/**
	 * Sets the setShouldCreateSEICommand.
	 * 
	 * @param setShouldCreateSEICommand
	 *            The shouldCreateSEICommand to set
	 */
	public void setShouldCreateSEICommand(boolean shouldCreateSEI) {
		this.setBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE, shouldCreateSEI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(SHOULD_DELETE_INTERFACE) || propertyName.equals(SHOULD_REMOVE_INTERFACE)) {
			return validateInterfaceDelete(getBooleanProperty(propertyName));
		} else if (propertyName.equals(SHOULD_DELETE_LOCAL) || propertyName.equals(SHOULD_DELETE_REMOTE) || propertyName.equals(SHOULD_DELETE_SEI)) {
			return validateDelete(getBooleanProperty(propertyName));
		} else if (propertyName.equals(SHOULD_CREATE_LOCAL_COMMAND) || propertyName.equals(SHOULD_CREATE_REMOTE_COMMAND) || propertyName.equals(SHOULD_CREATE_NEW_SEI_INTERFACE)) {
			return validateCreate(getBooleanProperty(propertyName));
		} else if (propertyName.equals(HOME_EXISTING_NAME)) {
			return validateHomeExistingName(getStringProperty(HOME_EXISTING_NAME));
		} else if (propertyName.equals(REMOTE_EXISTING_NAME)) {
			return validateRemoteExistingName(getStringProperty(REMOTE_EXISTING_NAME));
		} else if (propertyName.equals(LOCAL_HOME_EXISTING_NAME)) {
			return validateLocalHomeExistingName(getStringProperty(LOCAL_HOME_EXISTING_NAME));
		} else if (propertyName.equals(LOCAL_EXISTING_NAME)) {
			return validateLocalExistingName(getStringProperty(LOCAL_EXISTING_NAME));
		} else if (propertyName.equals(SEI_EXISTING_NAME)) {
			return validateSEIExistingName(getStringProperty(SEI_EXISTING_NAME));
		}
		return super.doValidateProperty(propertyName);
	}


	private IStatus validateDelete(boolean value) {
		if ((getBooleanProperty(SHOULD_DELETE_LOCAL) && getBooleanProperty(SHOULD_DELETE_REMOTE)) || (getBooleanProperty(SHOULD_DELETE_LOCAL) && !this.hasRemoteInterfaces()) || (getBooleanProperty(SHOULD_DELETE_REMOTE) && !this.hasLocalInterfaces())) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CANNOT_REMOVE_BOTH_LOCAL_AND_REMOTE")); //$NON-NLS-1$
		} else if (!getBooleanProperty(SHOULD_DELETE_LOCAL) && !getBooleanProperty(SHOULD_DELETE_REMOTE) && !getBooleanProperty(SHOULD_DELETE_SEI)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("SELECT_ONE_OR_MORE_VIEWS")); //$NON-NLS-1$
		}
		if (hasEjbSubTypes()) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_VIEW_ERR_DELETE_SUPERCLASS")); //$NON-NLS-1$


		} // if
		return WTPCommonPlugin.OK_STATUS;
	}

	/**
	 * @param b
	 * @return
	 */
	private IStatus validateCreate(boolean b) {
		if (!getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND) && !getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND) && !getBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("AN_OPERATION_MUST_BE_SELECTED")); //$NON-NLS-1$
		} else if ((getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND) && getBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE)) || (getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND) && getBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE))) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("ONLY_ONE_VIEW_CREATE")); //$NON-NLS-1$
		}
		return WTPCommonPlugin.OK_STATUS;

	}

	/**
	 * Gets the methodCollection.
	 * 
	 * @return List
	 */
	public List getMethodCollection() {
		List methodList = new ArrayList();
		if (this.shouldCopyLocal() && this.getEjbBean().getLocalInterface() != null && this.getEjbBean().getLocalInterface().getMethods() != null) {
			methodList.addAll(getEjbBean().getLocalInterface().getMethods());
		}
		if (this.shouldCopyRemote() && this.getEjbBean().getRemoteInterface() != null && this.getEjbBean().getRemoteInterface().getMethods() != null) {
			methodList.addAll(getEjbBean().getRemoteInterface().getMethods());
		}
		if (this.shouldCopySEI()) {
			Session sessionBean = (Session) getEjbBean();
			if (sessionBean.getServiceEndpoint() != null && sessionBean.getServiceEndpoint().getMethods() != null) {
				methodList.addAll(sessionBean.getServiceEndpoint().getMethods());
			}

		}
		return methodList;
	}// getMethodCollection


	/**
	 * Gets the methodCollection.
	 * 
	 * @return List
	 */
	public List getHomeMethodCollection() {
		List homeMethodList = new ArrayList();
		if (this.shouldCopyLocal() && this.getEjbBean().getLocalHomeInterface() != null && this.getEjbBean().getLocalHomeInterface().getMethods() != null) {
			homeMethodList.addAll(getEjbBean().getLocalHomeInterface().getMethods());
		}
		if (this.shouldCopyRemote() && this.getEjbBean().getHomeInterface() != null && this.getEjbBean().getHomeInterface().getMethods() != null) {
			homeMethodList.addAll(getEjbBean().getHomeInterface().getMethods());
		}
		return homeMethodList;
	}// getHomeMethodCollection


	/**
	 * @param b
	 */
	public void setShouldDeleteSEI(boolean b) {
		this.setBooleanProperty(SHOULD_DELETE_SEI, b);
	}

	/**
	 * Gets the shouldDeleteLocal.
	 * 
	 * @return boolean
	 */
	public boolean shouldDeleteSEI() {
		return this.getBooleanProperty(SHOULD_DELETE_SEI);
	}// shouldDeleteSEI

	public void setShouldCopyRemote(boolean b) {
		this.setLocalFromRemote(b);
		this.setBooleanProperty(SHOULD_COPY_REMOTE_METHODS_TO_NEW, b);
	}

	public boolean shouldCopyRemote() {
		return this.getBooleanProperty(SHOULD_COPY_REMOTE_METHODS_TO_NEW);
	}

	public void setShouldCopyLocal(boolean b) {
		this.setRemoteFromLocal(b);
		this.setBooleanProperty(SHOULD_COPY_LOCAL_METHODS_TO_NEW, b);
	}

	public boolean shouldCopyLocal() {
		return this.getBooleanProperty(SHOULD_COPY_LOCAL_METHODS_TO_NEW);
	}

	public void setShouldCopySEI(boolean b) {
		this.setBooleanProperty(SHOULD_COPY_SEI_METHODS_TO_NEW, b);
	}

	public boolean shouldCopySEI() {
		return this.getBooleanProperty(SHOULD_COPY_SEI_METHODS_TO_NEW);
	}

	protected IStatus validateSEIExistingName(String serviceEndpointInterface) {
		if (!getBooleanProperty(SHOULD_CREATE_NEW_SEI_INTERFACE))
			return OK_STATUS;
		if (serviceEndpointInterface == null || serviceEndpointInterface.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_service_endpoint_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(serviceEndpointInterface);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("ServiceEndpoint_interface_cannot_be_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(serviceEndpointInterface, "Service_endpoint_interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(serviceEndpointInterface, this.getCollisionList(SEI_EXISTING_NAME));
		if (!status.isOK())
			return status;
		return validateInterfaceType(serviceEndpointInterface, SEI_INTERFACE_TYPE);
	}

	protected String[] getCollisionList(String type) {
		String[] collisionList = null;
		if (type != null) {
			if (type.equals(HOME_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, LOCAL_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME, SEI_EXISTING_NAME};
			else if (type.equals(REMOTE_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, LOCAL_EXISTING_NAME, HOME_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME, SEI_EXISTING_NAME};
			else if (type.equals(LOCAL_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, HOME_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME, SEI_EXISTING_NAME};
			else if (type.equals(LOCAL_HOME_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, HOME_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_EXISTING_NAME, SEI_EXISTING_NAME};
			else if (type.equals(SEI_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, HOME_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME};
		}
		return collisionList;

	}

}