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
 * Created on Apr 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.internal.adapters.jdom.JDOMSearchHelper;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

import com.ibm.wtp.emf.workbench.ProjectUtilities;


/**
 * @author sagarwal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */

public class ClientViewModel extends J2EEModelModifierOperationDataModel {


	/**
	 * Required - The name of the filter mapping pattern.
	 * 
	 * @see String
	 */
	private final static String LOCAL = "Local"; //$NON-NLS-1$
	private final static String HOME = "Home"; //$NON-NLS-1$


	public static final String HOME_METHOD_COLLECTION = "ClientViewDataModel.HOME_METHOD_COLLECTION"; //$NON-NLS-1$
	public static final String METHOD_COLLECTION = "ClientViewDataModel.METHOD_COLLECTION"; //$NON-NLS-1$
	public static final String DELETE_INTERFACE_COLLECTION = "ClientViewDataModel.DELETE_INTERFACE_COLLECTION"; //$NON-NLS-1$
	public static final String SHOULD_DELETE = "ClientViewDataModel.SHOULD_DELETE"; //$NON-NLS-1$
	public static final String SHOULD_DELETE_REMOTE = "ClientViewDataModel.SHOULD_DELETE_REMOTE"; //$NON-NLS-1$
	public static final String SHOULD_DELETE_LOCAL = "ClientViewDataModel.SHOULD_DELETE_LOCAL"; //$NON-NLS-1$
	public static final String IS_REMOTE_SELECTED = "ClientViewDataModel.IS_REMOTE_SELECTED"; //$NON-NLS-1$
	public static final String IS_LOCAL_SELECTED = "ClientViewDataModel.IS_LOCAL_SELECTED"; //$NON-NLS-1$
	public static final String SHOULD_CREATE_REMOTE_COMMAND = "ClientViewDataModel.SHOULD_CREATE_REMOTE_COMMAND"; //$NON-NLS-1$
	public static final String SHOULD_CREATE_LOCAL_COMMAND = "ClientViewDataModel.SHOULD_CREATE_LOCAL_COMMAND"; //$NON-NLS-1$
	public static final String SHOULD_DELETE_INTERFACE = "ClientViewDataModel.SHOULD_DELETE_INTERFACE"; //$NON-NLS-1$
	public static final String SHOULD_REMOVE_INTERFACE = "ClientViewDataModel.SHOULD_REMOVE_INTERFACE"; //$NON-NLS-1$
	public static final String HOME_EXISTING_NAME = "ClientViewDataModel.HOME_EXISTING_NAME"; //$NON-NLS-1$
	public static final String REMOTE_EXISTING_NAME = "ClientViewDataModel.REMOTE_EXISTING_NAME"; //$NON-NLS-1$
	public static final String LOCAL_HOME_EXISTING_NAME = "ClientViewDataModel.LOCAL_HOME_EXISTING_NAME"; //$NON-NLS-1$
	public static final String LOCAL_EXISTING_NAME = "ClientViewDataModel.LOCAL_EXISTING_NAME"; //$NON-NLS-1$
	public static final String PACKAGE_NAME = "ClientViewDataModel.PACKAGE_NAME"; //$NON-NLS-1$
	public static final String LOCAL_SUFFIX = "ClientViewDataModel.LOCAL_SUFFIX"; //$NON-NLS-1$
	public static final String HOME_INTERFACE_EXISTING = "ClientViewDataModel.HOME_INTERFACE_EXISTING"; //$NON-NLS-1$
	public static final String REMOTE_INTERFACE_EXISTING = "ClientViewDataModel.REMOTE_INTERFACE_EXISTING"; //$NON-NLS-1$
	public static final String LOCAL_INTERFACE_EXISTING = "ClientViewDataModel.LOCAL_INTERFACE_EXISTING"; //$NON-NLS-1$
	public static final String LOCAL_HOME_INTERFACE_EXISTING = "ClientViewDataModel.LOCAL_HOME_INTERFACE_EXISTING"; //$NON-NLS-1$
	public static final String EJB_BEAN = "ClientViewDataModel.EJB_BEAN"; //$NON-NLS-1$
	public static final String REMOTE_FROM_LOCAL = "ClientViewDataModel.REMOTE_FROM_LOCAL"; //$NON-NLS-1$
	public static final String LOCAL_FROM_REMOTE = "ClientViewDataModel.LOCAL_FROM_REMOTE"; //$NON-NLS-1$

	public static final String BEAN_EXISTING_NAME = "ClientViewDataModel.BEAN_EXISTING_NAME"; //$NON-NLS-1$


	private String EMPTY_STRING = ""; //$NON-NLS-1$
	private EJBNatureRuntime ejbNature;
	private Map typeCache;
	private IJavaProject javaProject;
	private static final String HOME_INTERFACE_TYPE = "javax.ejb.EJBHome"; //$NON-NLS-1$
	private static final String REMOTE_INTERFACE_TYPE = "javax.ejb.EJBObject"; //$NON-NLS-1$
	private static final String LOCAL_HOME_INTERFACE_TYPE = "javax.ejb.EJBLocalHome"; //$NON-NLS-1$
	private static final String LOCAL_INTERFACE_TYPE = "javax.ejb.EJBLocalObject"; //$NON-NLS-1$

	///////////////////////////////////////////////////////////////////////////////////

	/**
	 * Constructor for ClientViewModel.
	 */
	public ClientViewModel() {
		super();
		setProperty(METHOD_COLLECTION, new ArrayList());

	}// ClientViewModel

	/**
	 * Constructor for ClientViewModel.
	 */
	public ClientViewModel(EnterpriseBean ejbBean) {
		super();
		this.setEjbBean(ejbBean);
		setProperty(METHOD_COLLECTION, new ArrayList());
		if (ejbBean != null)
			setProperty(BEAN_EXISTING_NAME, ejbBean.getEjbClass().getQualifiedName());
	}// ClientViewModel

	public WTPOperation getDefaultOperation() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SHOULD_DELETE);
		addValidBaseProperty(SHOULD_DELETE_REMOTE);
		addValidBaseProperty(SHOULD_DELETE_LOCAL);
		addValidBaseProperty(IS_REMOTE_SELECTED);
		addValidBaseProperty(IS_LOCAL_SELECTED);
		addValidBaseProperty(SHOULD_CREATE_REMOTE_COMMAND);
		addValidBaseProperty(SHOULD_CREATE_LOCAL_COMMAND);
		addValidBaseProperty(HOME_EXISTING_NAME);
		addValidBaseProperty(REMOTE_EXISTING_NAME);
		addValidBaseProperty(LOCAL_HOME_EXISTING_NAME);
		addValidBaseProperty(LOCAL_EXISTING_NAME);
		addValidBaseProperty(LOCAL_SUFFIX);
		addValidBaseProperty(PACKAGE_NAME);
		addValidBaseProperty(HOME_INTERFACE_EXISTING);
		addValidBaseProperty(REMOTE_INTERFACE_EXISTING);
		addValidBaseProperty(LOCAL_INTERFACE_EXISTING);
		addValidBaseProperty(LOCAL_HOME_INTERFACE_EXISTING);
		addValidBaseProperty(EJB_BEAN);
		addValidBaseProperty(SHOULD_DELETE_INTERFACE);
		addValidBaseProperty(SHOULD_REMOVE_INTERFACE);
		addValidBaseProperty(HOME_METHOD_COLLECTION);
		addValidBaseProperty(METHOD_COLLECTION);
		addValidBaseProperty(DELETE_INTERFACE_COLLECTION);
		addValidBaseProperty(REMOTE_FROM_LOCAL);
		addValidBaseProperty(LOCAL_FROM_REMOTE);
		addValidBaseProperty(BEAN_EXISTING_NAME);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(SHOULD_DELETE_INTERFACE) || propertyName.equals(SHOULD_REMOVE_INTERFACE)) {
			return validateInterfaceDelete(getBooleanProperty(propertyName));
		} else if (propertyName.equals(SHOULD_DELETE_LOCAL) || propertyName.equals(SHOULD_DELETE_REMOTE)) {
			return validateDelete(getBooleanProperty(propertyName));
		} else if (propertyName.equals(SHOULD_CREATE_LOCAL_COMMAND) || propertyName.equals(SHOULD_CREATE_REMOTE_COMMAND)) {
			return validateCreate(getBooleanProperty(propertyName));
		} else if (propertyName.equals(HOME_EXISTING_NAME)) {
			return validateHomeExistingName(getStringProperty(HOME_EXISTING_NAME));
		} else if (propertyName.equals(REMOTE_EXISTING_NAME)) {
			return validateRemoteExistingName(getStringProperty(REMOTE_EXISTING_NAME));
		} else if (propertyName.equals(LOCAL_HOME_EXISTING_NAME)) {
			return validateLocalHomeExistingName(getStringProperty(LOCAL_HOME_EXISTING_NAME));
		} else if (propertyName.equals(LOCAL_EXISTING_NAME)) {
			return validateLocalExistingName(getStringProperty(LOCAL_EXISTING_NAME));
		}
		return super.doValidateProperty(propertyName);
	}


	/**
	 * Checks if the field if valid,
	 * 
	 * @param Text
	 *            field - The current text field.
	 * @return boolean
	 */
	public boolean isInValidField(String name) {
		return (name == null || name.equals("") || name.equals(" ")); //$NON-NLS-1$ //$NON-NLS-2$
	}// isValidField


	/**
	 * @param b
	 * @return
	 */
	private IStatus validateCreate(boolean b) {
		if (!getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND) && !getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("AN_OPERATION_MUST_BE_SELECTED")); //$NON-NLS-1$
		}
		return WTPCommonPlugin.OK_STATUS;

	}

	protected IStatus validateInterfaceDelete(boolean value) {
		if (!getBooleanProperty(SHOULD_DELETE_INTERFACE) && !getBooleanProperty(SHOULD_REMOVE_INTERFACE)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("AN_OPERATION_MUST_BE_SELECTED")); //$NON-NLS-1$
		}
		if (hasEjbSubTypes()) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_VIEW_ERR_DELETE_SUPERCLASS")); //$NON-NLS-1$

		} // if
		return WTPCommonPlugin.OK_STATUS;
	}

	protected boolean hasEjbSubTypes() {
		EjbModuleExtensionHelper modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
		if (modHelper == null)
			return false;

		List subtypes = modHelper.getSubtypes(getEjbBean());
		return subtypes != null && !subtypes.isEmpty();
	} // validateControls


	private IStatus validateDelete(boolean value) {
		if ((getBooleanProperty(SHOULD_DELETE_LOCAL) && getBooleanProperty(SHOULD_DELETE_REMOTE)) || (getBooleanProperty(SHOULD_DELETE_LOCAL) && !this.hasRemoteInterfaces()) || (getBooleanProperty(SHOULD_DELETE_REMOTE) && !this.hasLocalInterfaces())) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CANNOT_REMOVE_BOTH_LOCAL_AND_REMOTE")); //$NON-NLS-1$
		} else if (!getBooleanProperty(SHOULD_DELETE_LOCAL) && !getBooleanProperty(SHOULD_DELETE_REMOTE)) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("SELECT_ONE_OR_MORE_VIEWS")); //$NON-NLS-1$
		}
		if (hasEjbSubTypes()) {
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_VIEW_ERR_DELETE_SUPERCLASS")); //$NON-NLS-1$

		} // if
		return WTPCommonPlugin.OK_STATUS;
	}


	/**
	 * Sets if remote interfaces have been selected.
	 * 
	 * @param boolean
	 *            selection - The current selection.
	 */
	public void setRemoteSelected(boolean selection) {
		setBooleanProperty(IS_REMOTE_SELECTED, selection);
	}// setRemoteSelected

	/**
	 * Sets if local interfaces have been selected.
	 * 
	 * @param boolean
	 *            selection - The current selection.
	 */
	public void setLocalSelected(boolean selection) {
		setBooleanProperty(IS_LOCAL_SELECTED, selection);
	}// setLocalSelected

	/**
	 * Says if remote interfaces have been selected.
	 * 
	 * @return boolean
	 */
	public boolean isRemoteSelected() {
		return getBooleanProperty(IS_REMOTE_SELECTED);
	}// isRemoteSelected


	/**
	 * Says if local interfaces have been selected.
	 * 
	 * @return boolean
	 */
	public boolean isLocalSelected() {
		return this.getBooleanProperty(IS_LOCAL_SELECTED);
	}// isLocalSelected

	/**
	 * Has remote interfaces.
	 * 
	 * @return boolean
	 */
	public boolean hasRemoteInterfaces() {
		return (this.getEjbBean().getRemoteInterface() != null || this.getEjbBean().getHomeInterface() != null);
	}// hasRemoteInterfaces

	/**
	 * Has local interfaces.
	 * 
	 * @return boolean
	 */
	public boolean hasLocalInterfaces() {
		return (this.getEjbBean().getLocalInterface() != null || this.getEjbBean().getLocalHomeInterface() != null);
	}// hasRemoteInterfaces

	public boolean hasSEIInterface() {
		return (((Session) getEjbBean()).getServiceEndpoint() != null);
	}

	/**
	 * Gets the ejbBean.
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjbBean() {
		return (EnterpriseBean) getProperty(EJB_BEAN);
	}// getEjbBean

	/**
	 * Sets the ejbBean .
	 * 
	 * @param EnterpriseBean -
	 *            The ejbBean to set
	 */
	public void setEjbBean(EnterpriseBean ejbBean) {
		this.setProperty(EJB_BEAN, ejbBean);
	}// setEjbBean

	/**
	 * Gets the homeExistingName.
	 * 
	 * @return String
	 */
	public String getHomeExistingName() {
		if (this.getProperty(HOME_EXISTING_NAME) == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(HOME); //$NON-NLS-1$
			this.setProperty(HOME_EXISTING_NAME, b.toString());
		}// if
		return (String) getProperty(HOME_EXISTING_NAME);
	}// getHomeExistingName

	/**
	 * Sets the homeExistingName.
	 * 
	 * @param String
	 *            homeExistingName - The homeExistingName to set
	 */
	public void setHomeExistingName(String homeExistingName) {
		this.setProperty(HOME_EXISTING_NAME, homeExistingName);
	}// setHomeExistingName

	/**
	 * Gets the remoteExistingName.
	 * 
	 * @return String
	 */
	public String getRemoteExistingName() {
		if (this.getProperty(REMOTE_EXISTING_NAME) == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()); //$NON-NLS-1$
			this.setProperty(REMOTE_EXISTING_NAME, b.toString()); //$NON-NLS-1$
		}// if
		return (String) getProperty(REMOTE_EXISTING_NAME);
	}// getRemoteExistingName

	/**
	 * Sets the remoteExistingName.
	 * 
	 * @param String
	 *            remoteExistingName - The remoteExistingName to set
	 */
	public void setRemoteExistingName(String remoteExistingName) {
		this.setProperty(REMOTE_EXISTING_NAME, remoteExistingName);
	}// setRemoteExistingName

	/**
	 * Gets the localHomeExistingName.
	 * 
	 * @return Returns a String
	 */
	public String getLocalHomeExistingName() {
		if (this.getProperty(LOCAL_HOME_EXISTING_NAME) == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(getLocalSuffix()).append(HOME); //$NON-NLS-1$
			this.setProperty(LOCAL_HOME_EXISTING_NAME, b.toString());
		}// if
		return (String) getProperty(LOCAL_HOME_EXISTING_NAME);
	}// getLocalHomeExistingName

	/**
	 * Sets the localHomeExistingName.
	 * 
	 * @param String
	 *            localHomeExistingName - The localHomeExistingName to set
	 */
	public void setLocalHomeExistingName(String localHomeExistingName) {
		this.setProperty(LOCAL_HOME_EXISTING_NAME, localHomeExistingName);
	}// setLocalHomeExistingName


	/**
	 * Gets the localExistingName.
	 * 
	 * @return String
	 */
	public String getLocalExistingName() {
		if (this.getProperty(LOCAL_EXISTING_NAME) == null) {
			StringBuffer b = new StringBuffer();
			b.append(getPackageName()).append(".").append(getEjbBean().getName()).append(getLocalSuffix()); //$NON-NLS-1$
			this.setProperty(LOCAL_EXISTING_NAME, b.toString());
		}// if
		return (String) getProperty(LOCAL_EXISTING_NAME);
	}// getLocalExistingName

	/**
	 * Sets the localExistingName.
	 * 
	 * @param String
	 *            localExistingName - The localExistingName to set
	 */
	public void setLocalExistingName(String localExistingName) {
		this.setProperty(LOCAL_EXISTING_NAME, localExistingName);
	}// setLocalExistingName

	/**
	 * Gets the methodCollection.
	 * 
	 * @return List
	 */
	public List getMethodCollection() {
		return (List) getProperty(METHOD_COLLECTION);
	}// getMethodCollection

	/**
	 * Sets the methodCollection.
	 * 
	 * @param List
	 *            methodCollection - The methodCollection to set
	 */
	public void setMethodCollection(List methodCollection) {
		setProperty(METHOD_COLLECTION, methodCollection);
	}// setMethodCollection



	/**
	 * Sets the package name.
	 * 
	 * @param String
	 *            packageName - The name of package.
	 */
	public void setPackageName(String packageName) {
		this.setProperty(PACKAGE_NAME, packageName);
	}// setPackageName

	/**
	 * Gets the package name.
	 * 
	 * @return String
	 */
	public String getPackageName() {
		if (this.getProperty(PACKAGE_NAME) == null) {
			return Signature.getQualifier(this.getEjbBean().getEjbClassName());
		}
		return (String) getProperty(PACKAGE_NAME);
	}// getPackageName

	/**
	 * Gets the homeMethodCollection.
	 * 
	 * @return List
	 */
	public List getHomeMethodCollection() {
		return (List) getProperty(HOME_METHOD_COLLECTION);
	}// getHomeMethodColleaction

	/**
	 * Sets the homeMethodCollection.
	 * 
	 * @param List
	 *            homeMethodCollection - The homeMethodCollection to set
	 */
	public void setHomeMethodCollection(List homeMethodCollection) {
		this.setProperty(HOME_METHOD_COLLECTION, homeMethodCollection);
	}// setHomeMethodColleaction

	/**
	 * Gets the shouldDelete.
	 * 
	 * @return boolean
	 */
	public boolean shouldDelete() {
		return this.getBooleanProperty(SHOULD_DELETE);
	}// shouldDelete

	/**
	 * Sets the shouldDelete.
	 * 
	 * @param boolean
	 *            shouldDelete - The shouldDelete to set
	 */
	public void setShouldDelete(boolean shouldDelete) {
		this.setBooleanProperty(SHOULD_DELETE, shouldDelete);
	}// setShouldDelete

	public void setLocalFromRemote(boolean localFromRemote) {
		if (localFromRemote && getEjbBean().getHomeInterface().getMethods() != null && getEjbBean().getRemoteInterface().getMethods() != null) {
			setHomeMethodCollection(getEjbBean().getHomeInterface().getMethods());
			setMethodCollection(getEjbBean().getRemoteInterface().getMethods());
		} else {
			setMethodListEmpty();
		}// if
		this.setBooleanProperty(LOCAL_FROM_REMOTE, localFromRemote);
	}

	public boolean localFromRemote() {
		return this.getBooleanProperty(LOCAL_FROM_REMOTE);
	}

	/**
	 * Set method lists empty.
	 */
	protected void setMethodListEmpty() {
		setHomeMethodCollection(java.util.Collections.EMPTY_LIST);
		setMethodCollection(java.util.Collections.EMPTY_LIST);
	}// setMethodListEmpty


	public void setRemoteFromLocal(boolean remoteFromLocal) {
		if (remoteFromLocal && getEjbBean().getLocalInterface().getMethods() != null && getEjbBean().getLocalHomeInterface().getMethods() != null) {
			setMethodCollection(getEjbBean().getLocalInterface().getMethods());
			setHomeMethodCollection(getEjbBean().getLocalHomeInterface().getMethods());
		} else {
			setMethodListEmpty();
		}// if
		this.setBooleanProperty(REMOTE_FROM_LOCAL, remoteFromLocal);
	}

	public boolean remoteFromLocal() {
		return this.getBooleanProperty(REMOTE_FROM_LOCAL);
	}

	public boolean shouldDeleteInterface() {
		return getBooleanProperty(SHOULD_DELETE_INTERFACE);
	}

	public void setShouldDeleteInterface(boolean shouldDeleteInterface) {
		this.setBooleanProperty(SHOULD_DELETE_INTERFACE, shouldDeleteInterface);
	}

	public boolean shouldRemoveInterface() {
		return getBooleanProperty(SHOULD_REMOVE_INTERFACE);
	}

	public void setShouldRemoveInterface(boolean shouldRemoveInterface) {
		this.setBooleanProperty(SHOULD_REMOVE_INTERFACE, shouldRemoveInterface);
	}

	/**
	 * Gets the shouldDeleteRemote.
	 * 
	 * @return boolean
	 */
	public boolean shouldDeleteRemote() {
		return this.getBooleanProperty(SHOULD_DELETE_REMOTE);
	}// shouldDeleteRemote

	/**
	 * Sets the shouldDeleteRemote.
	 * 
	 * @param boolean
	 *            shouldDeleteRemote - The shouldDeleteRemote to set
	 */
	public void setShouldDeleteRemote(boolean shouldDeleteRemote) {
		this.setBooleanProperty(SHOULD_DELETE_REMOTE, shouldDeleteRemote);
	}// setShouldDeleteRemote

	/**
	 * Gets the shouldDeleteLocal.
	 * 
	 * @return boolean
	 */
	public boolean shouldDeleteLocal() {
		return this.getBooleanProperty(SHOULD_DELETE_LOCAL);
	}// shouldDeleteLocal

	/**
	 * Sets the shouldDeleteLocal.
	 * 
	 * @param boolean
	 *            shouldDeleteLocal - The shouldDeleteLocal to set
	 */
	public void setShouldDeleteLocal(boolean shouldDeleteLocal) {
		this.setBooleanProperty(SHOULD_DELETE_LOCAL, shouldDeleteLocal);
	}// setShouldDeleteLocal

	/**
	 * Gets the homeInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getHomeInterfaceExisting() {
		return (JavaClass) getProperty(HOME_INTERFACE_EXISTING);
	}// getHomeInterfaceExisting

	/**
	 * Sets the homeInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            homeInterfaceExisting - The homeInterfaceExisting to set
	 */
	public void setHomeInterfaceExisting(JavaClass homeInterfaceExisting) {
		this.setProperty(HOME_INTERFACE_EXISTING, homeInterfaceExisting);
	}// setHomeInterfaceExisting

	/**
	 * Gets the remoteInterfaceExisiting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getRemoteInterfaceExisting() {
		return (JavaClass) getProperty(REMOTE_INTERFACE_EXISTING);
	}// getRemoteInterfaceExisting

	/**
	 * Sets the remoteInterfaceExisiting.
	 * 
	 * @param JavaClass
	 *            remoteInterfaceExisiting - The remoteInterfaceExisiting to set
	 */
	public void setRemoteInterfaceExisting(JavaClass remoteInterfaceExisting) {
		this.setProperty(REMOTE_INTERFACE_EXISTING, remoteInterfaceExisting);
	}// setRemoteInterfaceExisting

	/**
	 * Gets the localInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getLocalInterfaceExisting() {
		return (JavaClass) getProperty(LOCAL_INTERFACE_EXISTING);
	}// getLocalInterfaceExisting

	/**
	 * Sets the localInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            localInterfaceExisting - The localInterfaceExisting to set
	 */
	public void setLocalInterfaceExisting(JavaClass localInterfaceExisting) {
		this.setProperty(LOCAL_INTERFACE_EXISTING, localInterfaceExisting);
	}// setLocalInterfaceExisting

	/**
	 * Gets the localHomeInterfaceExisting.
	 * 
	 * @return JavaClass
	 */
	public JavaClass getLocalHomeInterfaceExisting() {
		return (JavaClass) getProperty(LOCAL_HOME_INTERFACE_EXISTING);
	}// getLocalHomeInterfaceExisting

	/**
	 * Sets the localHomeInterfaceExisting.
	 * 
	 * @param JavaClass
	 *            localHomeInterfaceExisting - The localHomeInterfaceExisting to set
	 */
	public void setLocalHomeInterfaceExisting(JavaClass localHomeInterfaceExisting) {
		this.setProperty(LOCAL_HOME_INTERFACE_EXISTING, localHomeInterfaceExisting);
	}// setLocalHomeInterfaceExisting



	/**
	 * Returns the shouldCreateLocalCommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateLocalCommand() {
		return this.getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND);
	}

	/**
	 * Sets the shouldCreateLocalCommand.
	 * 
	 * @param shouldCreateLocalCommand
	 *            The shouldCreateLocalCommand to set
	 */
	public void setShouldCreateLocalCommand(boolean shouldCreateLocalCommand) {
		this.setBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND, shouldCreateLocalCommand);
	}

	/**
	 * Returns the shouldCreateRemoteCommand.
	 * 
	 * @return boolean
	 */
	public boolean shouldCreateRemoteCommand() {
		return this.getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND);
	}

	/**
	 * Sets the shouldCreateRemoteCommand.
	 * 
	 * @param shouldCreateRemoteCommand
	 *            The shouldCreateRemoteCommand to set
	 */
	public void setShouldCreateRemoteCommand(boolean shouldCreateRemoteCommand) {
		this.setBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND, shouldCreateRemoteCommand);
	}



	/**
	 * Returns the localSuffix.
	 * 
	 * @return String
	 */
	public String getLocalSuffix() {
		if (this.getProperty(LOCAL_SUFFIX) == null)
			setProperty(LOCAL_SUFFIX, LOCAL);
		return (String) getProperty(LOCAL_SUFFIX);
	}

	/**
	 * Sets the localSuffix.
	 * 
	 * @param localSuffix
	 *            The localSuffix to set
	 */
	public void setLocalSuffix(String localSuffix) {
		setProperty(LOCAL_SUFFIX, localSuffix);
	}

	public void resetDerivedAttributes() {
		setProperty(HOME_EXISTING_NAME, null);
		setProperty(REMOTE_EXISTING_NAME, null);
		setProperty(LOCAL_HOME_EXISTING_NAME, null);
		setProperty(LOCAL_EXISTING_NAME, null);
		setProperty(PACKAGE_NAME, null);
	}

	public boolean shouldDoDeleteOperation() {
		return this.shouldDeleteLocal() || this.shouldDeleteRemote();
	}// shouldDeleteOperation

	protected void init() {
		super.init();
		setBooleanProperty(SHOULD_DELETE, false);
		setBooleanProperty(SHOULD_DELETE_REMOTE, false);
		setBooleanProperty(SHOULD_DELETE_LOCAL, false);
		setBooleanProperty(IS_REMOTE_SELECTED, false);
		setBooleanProperty(IS_LOCAL_SELECTED, false);
		setBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND, false);
		setBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND, false);
	}



	/**
	 * @param fullClassName
	 * @return
	 */
	protected String getClassName(String fullClassName) {
		if (fullClassName != null)
			return Signature.getSimpleName(fullClassName);
		return EMPTY_STRING;
	}

	protected String getPackageName(String fullClassName) {
		if (fullClassName != null)
			return Signature.getQualifier(fullClassName);
		return EMPTY_STRING;

	}

	protected IStatus validateHomeExistingName(String remoteHome) {
		if (!getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND))
			return OK_STATUS;
		if (remoteHome == null || remoteHome.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Message_Enter_home_interface_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(remoteHome);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Home_interface_cannot_be_i_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(remoteHome, "Message_Home_Interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(remoteHome, this.getCollisionList(HOME_EXISTING_NAME));
		if (!status.isOK())
			return status;
		return validateInterfaceType(remoteHome, HOME_INTERFACE_TYPE);
	}


	protected IStatus validateRemoteExistingName(String remoteInterface) {
		if (!getBooleanProperty(SHOULD_CREATE_REMOTE_COMMAND))
			return OK_STATUS;
		if (remoteInterface == null || remoteInterface.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Message_Enter_remote_interface_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(remoteInterface);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Remote_interface_cannot_be_i_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(remoteInterface, "Message_Remote_Interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(remoteInterface, this.getCollisionList(REMOTE_EXISTING_NAME));
		if (!status.isOK())
			return status;
		return validateInterfaceType(remoteInterface, REMOTE_INTERFACE_TYPE);
	}

	/**
	 * @return
	 */
	protected IStatus validateLocalHomeExistingName(String localHome) {
		if (!getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND))
			return OK_STATUS;

		if (localHome == null || localHome.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_local_home_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(localHome);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Local_Home_not_in_a_default_pkg_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(localHome, "Local_home_interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(localHome, this.getCollisionList(LOCAL_HOME_EXISTING_NAME));
		if (!status.isOK())
			return status;
		return validateInterfaceType(localHome, LOCAL_HOME_INTERFACE_TYPE);
	}

	/**
	 * @return
	 */
	protected IStatus validateLocalExistingName(String localInterface) {
		if (!getBooleanProperty(SHOULD_CREATE_LOCAL_COMMAND))
			return OK_STATUS;

		if (localInterface == null || localInterface.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Enter_local_int_name_UI_"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(localInterface);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Local_not_in_a_default_pkg_UI_"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus status = validateJavaTypeName(localInterface, "Local_interface_UI_"); //$NON-NLS-1$
		if (!status.isOK())
			return status;
		status = validateNoCollisions(localInterface, this.getCollisionList(LOCAL_EXISTING_NAME));
		if (!status.isOK())
			return status;
		return validateInterfaceType(localInterface, LOCAL_INTERFACE_TYPE);
	}

	/**
	 * @param typeName
	 * @param strings
	 * @return
	 */
	protected IStatus validateNoCollisions(String typeName, String[] propertyNames) {
		String compareName = null;
		for (int i = 0; i < propertyNames.length; i++) {
			compareName = (String) getProperty(propertyNames[i]);
			if (compareName != null && compareName.equals(typeName)) {
				String msg_pattern = EJBCreationResourceHandler.getString("Name_collision_pattern"); //$NON-NLS-1$
				return EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[]{typeName}), null);
			}
		}
		return OK_STATUS;
	}

	public boolean isVersion2xOrGreater() {
		EJBNatureRuntime nature = getEJBNature();
		if (nature != null)
			return nature.getModuleVersion() >= J2EEVersionConstants.EJB_2_0_ID;
		return true;
	}

	protected EJBNatureRuntime getEJBNature() {
		if (ejbNature == null)
			ejbNature = EJBNatureRuntime.getRuntime(getTargetProject());
		return ejbNature;
	}

	protected IStatus validateJavaTypeName(String javaName, String messagePrefixKey) {
		IStatus javaStatus = JavaConventions.validateJavaTypeName(javaName);
		if (javaStatus.getSeverity() == IStatus.ERROR)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString(messagePrefixKey) + " " //$NON-NLS-1$
						+ javaStatus.getMessage(), null);
		else if (javaStatus.getSeverity() == IStatus.WARNING)
			return EjbPlugin.createStatus(IStatus.WARNING, -1, EJBCreationResourceHandler.getString(messagePrefixKey) + " " + javaStatus.getMessage(), null); //$NON-NLS-1$
		return OK_STATUS;
	}

	/**
	 * @param remoteHome
	 * @return
	 */
	protected IStatus validateInterfaceType(String interfaceName, String implementsInterfaceName) {
		IType type = findType(interfaceName);
		if (type != null) {
			if (!implementsInterface(type, implementsInterfaceName)) {
				String msg_pattern = EJBCreationResourceHandler.getString("Interface_extends"); //$NON-NLS-1$
				return EjbPlugin.createErrorStatus(MessageFormat.format(msg_pattern, new String[]{interfaceName, implementsInterfaceName}), null);
			}
		}
		return OK_STATUS;
	}

	protected IType findType(String qualifiedName) {
		if (qualifiedName != null && qualifiedName.length() > 0) {
			if (typeCache != null) {
				Object cache = typeCache.get(qualifiedName);
				if (cache != null)
					return cache instanceof IType ? (IType) cache : null;
			} else {
				typeCache = new HashMap();
			}
			IType type = JDOMSearchHelper.findType(qualifiedName, false, getJavaProject(), null);
			if (type == null)
				typeCache.put(qualifiedName, qualifiedName);
			else
				typeCache.put(qualifiedName, type);
			return type;
		}
		return null;
	}

	protected boolean implementsInterface(IType type, String interfaceName) {
		if (type == null)
			return false;
		String[] typeImplements = null;
		try {
			typeImplements = type.getSuperInterfaceNames();
			if (typeImplements != null && typeImplements.length > 0) {
				for (int i = 0; i < typeImplements.length; i++) {
					typeImplements[i] = JDOMSearchHelper.resolveSimpleTypeName(type, typeImplements[i]);
					if (typeImplements[i].equals(interfaceName))
						return true;
				}
			}
		} catch (JavaModelException e) {
		}
		IType sc = null;
		//iterate over implements
		boolean implementTypeImplements = false;
		for (int j = 0; j < typeImplements.length; j++) {
			sc = findType(typeImplements[j]);
			if (sc != null) {
				implementTypeImplements = implementsInterface(sc, interfaceName);
				if (implementTypeImplements)
					return true;
			}
		}
		//Need to check super,
		String superClazz = null;
		try {
			superClazz = type.getSuperclassName();
		} catch (JavaModelException e) {
		}
		if (superClazz != null && superClazz.length() > 0) {
			try {
				superClazz = JDOMSearchHelper.resolveSimpleTypeName(type, superClazz);
			} catch (JavaModelException e) {
			}
			sc = findType(superClazz);
			if (sc != null)
				return implementsInterface(sc, interfaceName);
		}
		return false;
	}

	protected IJavaProject getJavaProject() {
		if (javaProject == null) {
			EnterpriseBean bean = this.getEjbBean();
			IProject project = ProjectUtilities.getProject(bean);
			javaProject = JavaCore.create(project);
		}
		return javaProject;
	}

	protected String[] getCollisionList(String type) {
		String[] collisionList = null;
		if (type != null) {
			if (type.equals(HOME_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, LOCAL_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME};
			else if (type.equals(REMOTE_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, LOCAL_EXISTING_NAME, HOME_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME};
			else if (type.equals(LOCAL_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, HOME_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_HOME_EXISTING_NAME};
			else if (type.equals(LOCAL_HOME_EXISTING_NAME))
				collisionList = new String[]{BEAN_EXISTING_NAME, HOME_EXISTING_NAME, REMOTE_EXISTING_NAME, LOCAL_EXISTING_NAME};
		}
		return collisionList;

	}
}