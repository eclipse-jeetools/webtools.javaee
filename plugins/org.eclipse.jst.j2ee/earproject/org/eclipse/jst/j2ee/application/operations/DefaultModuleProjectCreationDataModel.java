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
 * Created on Nov 12, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.util.ArrayList;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.moduleextension.EjbModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.JcaModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.WebModuleExtension;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class DefaultModuleProjectCreationDataModel extends WTPOperationDataModel {
	private static final String CONNECTOR_SUFFIX = "Connector"; //$NON-NLS-1$
	private static final String CLIENT_SUFFIX = "Client"; //$NON-NLS-1$
	private static final String WEB_SUFFIX = "Web"; //$NON-NLS-1$
	private static final String EJB_SUFFIX = "EJB"; //$NON-NLS-1$
	/**
	 * Required - This is the name used to caculate the module project names. type String
	 */
	public static final String BASE_NAME = "DefaultModuleProjectCreationDataModel.BASE_NAME"; //$NON-NLS-1$
	/**
	 * Required - Set the J2EE version which each default module will be set to. type Integer
	 */
	public static final String J2EE_VERSION = "DefaultModuleProjectCreationDataModel.J2EE_VERSION"; //$NON-NLS-1$
	private static final int EJB = 0;
	private static final int WEB = 1;
	private static final int RAR = 2;
	private static final int CLIENT = 3;
	private static String CREATE_BASE = "DefaultModuleProjectCreationDataModel.CREATE_"; //$NON-NLS-1$
	private static String PROJECT_BASE = "DefaultModuleProjectCreationDataModel.PROJECT_NAME_"; //$NON-NLS-1$
	/**
	 * Default is true. type Boolean
	 */
	public static final String CREATE_EJB = CREATE_BASE + EJB;
	/**
	 * The EJB Project name to use. type String
	 */
	public static final String EJB_PROJECT_NAME = PROJECT_BASE + EJB;
	/**
	 * Default is true. type Boolean
	 */
	public static final String CREATE_WEB = CREATE_BASE + WEB;
	/**
	 * The WEB Project name to use. type String
	 */
	public static final String WEB_PROJECT_NAME = PROJECT_BASE + WEB;
	/**
	 * Default is true. type Boolean
	 */
	public static final String CREATE_APPCLIENT = CREATE_BASE + CLIENT;
	/**
	 * The EJB Project name to use. type String
	 */
	public static final String APPCLIENT_PROJECT_NAME = PROJECT_BASE + CLIENT;
	/**
	 * Default is true. type Boolean
	 */
	public static final String CREATE_CONNECTOR = CREATE_BASE + RAR;
	/**
	 * The EJB Project name to use. type String
	 */
	public static final String CONNECTOR_PROJECT_NAME = PROJECT_BASE + RAR;

	/**
	 * Used for validation only; validates no collsions between various module names. Do not set
	 * this value.
	 */
	public static final String MODULE_NAME_COLLISIONS_VALIDATION = "DefaultModuleProjectCreationDataModel.MODULE_NAME_COLLISIONS_VALIDATION"; //$NON-NLS-1$

	/**
	 * Default is true. type Boolean
	 */
	public static final String ENABLED = "DefaultModuleProjectCreationDataModel.ENABLED"; //$NON-NLS-1$

	private static final String NESTED_MODEL_EJB = "DefaultModuleProjectCreationDataModel.NESTED_MODEL_EJB"; //$NON-NLS-1$
	private static final String NESTED_MODEL_WEB = "DefaultModuleProjectCreationDataModel.NESTED_MODEL_WEB"; //$NON-NLS-1$
	private static final String NESTED_MODEL_JCA = "DefaultModuleProjectCreationDataModel.NESTED_MODEL_JCA"; //$NON-NLS-1$
	private static final String NESTED_MODEL_CLIENT = "DefaultModuleProjectCreationDataModel.NESTED_MODEL_CLIENT"; //$NON-NLS-1$

	private J2EEModuleCreationDataModel ejbModel;
	private J2EEModuleCreationDataModel webModel;
	private J2EEModuleCreationDataModel jcaModel;
	private AppClientProjectCreationDataModel clientModel;

	/**
	 *  
	 */
	public DefaultModuleProjectCreationDataModel() {
		super();
	}

	public WTPOperation getDefaultOperation() {
		return new DefaultModuleProjectCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(BASE_NAME);
		addValidBaseProperty(APPCLIENT_PROJECT_NAME);
		addValidBaseProperty(CONNECTOR_PROJECT_NAME);
		addValidBaseProperty(EJB_PROJECT_NAME);
		addValidBaseProperty(WEB_PROJECT_NAME);
		addValidBaseProperty(CREATE_CONNECTOR);
		addValidBaseProperty(CREATE_APPCLIENT);
		addValidBaseProperty(CREATE_EJB);
		addValidBaseProperty(CREATE_WEB);
		addValidBaseProperty(J2EE_VERSION);
		addValidBaseProperty(ENABLED);
		addValidBaseProperty(MODULE_NAME_COLLISIONS_VALIDATION);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#initNestedModels()
	 */
	protected void initNestedModels() {
		clientModel = new AppClientProjectCreationDataModel();
		addNestedModel(NESTED_MODEL_CLIENT, clientModel);
		EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
		if (ejbExt != null) {
			ejbModel = ejbExt.createProjectDataModel();
			if (ejbModel != null)
				addNestedModel(NESTED_MODEL_EJB, ejbModel);
		}
		WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
		if (webExt != null) {
			webModel = webExt.createProjectDataModel();
			if (webModel != null)
				addNestedModel(NESTED_MODEL_WEB, webModel);
		}
		JcaModuleExtension rarExt = EarModuleManager.getJCAModuleExtension();
		if (rarExt != null) {
			jcaModel = rarExt.createProjectDataModel();
			if (jcaModel != null)
				addNestedModel(NESTED_MODEL_JCA, jcaModel);
		}
	}

	private void addNestedModel(String modelName, J2EEModuleCreationDataModel moduleModel) {
		moduleModel.setProperty(J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION, Boolean.FALSE);
		super.addNestedModel(modelName, moduleModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.startsWith(CREATE_BASE))
			return getDefaultCreateValue(propertyName);
		if (propertyName.equals(ENABLED))
			return Boolean.TRUE;
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private Object getDefaultCreateValue(String propertyName) {
		if (propertyName.equals(CREATE_CONNECTOR)) {
			int version = getIntProperty(J2EE_VERSION);
			if (version < J2EEVersionConstants.J2EE_1_3_ID)
				return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	private int convertPropertyNameToInt(String propertyName) {
		int length = propertyName.length();
		String numString = propertyName.substring(length - 1, length);
		return Integer.parseInt(numString);
	}

	/**
	 * @param projectName
	 * @return
	 */
	private String ensureUniqueProjectName(String projectName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String newName = projectName;
		int index = 0;
		IProject proj = root.getProject(newName);
		while (proj.exists()) {
			index++;
			newName = projectName + index;
			proj = root.getProject(newName);
		}
		return newName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.startsWith(PROJECT_BASE)) {
			setNestedProjectName(convertPropertyNameToInt(propertyName), (String) propertyValue);
			return true;
		}
		if (propertyName.equals(J2EE_VERSION)) {
			updatedJ2EEVersion((Integer) propertyValue);
			return true;
		}
		if (propertyName.equals(BASE_NAME)) {
			setDefaultProjectNames((String) propertyValue);
			return true;
		}
		if (propertyName.startsWith(CREATE_BASE))
			notifyEnablement(convertPropertyNameToInt(propertyName));
		return notify;
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getPropertyName().equals(J2EEModuleCreationDataModel.PROJECT_NAME)) {
			Object source = event.getDataModel();
			String propertyName = null;
			if (ejbModel == source) {
				propertyName = EJB_PROJECT_NAME;
			} else if (webModel == source) {
				propertyName = WEB_PROJECT_NAME;
			} else if (jcaModel == source) {
				propertyName = CONNECTOR_PROJECT_NAME;
			} else if (clientModel == source) {
				propertyName = APPCLIENT_PROJECT_NAME;
			}
			if (null != propertyName) {
				setProperty(propertyName, event.getNewValue());
				return;
			}
		}
		super.propertyChanged(event);
	}

	/**
	 * @param flag
	 */
	private void notifyEnablement(int flag) {
		String propertyName = null;
		switch (flag) {
			case EJB :
				propertyName = EJB_PROJECT_NAME;
				break;
			case WEB :
				propertyName = WEB_PROJECT_NAME;
				break;
			case CLIENT :
				propertyName = APPCLIENT_PROJECT_NAME;
				break;
			case RAR :
				propertyName = CONNECTOR_PROJECT_NAME;
		}
		if (propertyName != null)
			notifyEnablementChange(propertyName);
	}

	private void updatedJ2EEVersion(Integer version) {
		setNestedJ2EEVersion(version);
		if (version.intValue() < J2EEVersionConstants.J2EE_1_3_ID && isSet(CREATE_CONNECTOR)) {
			setProperty(CREATE_CONNECTOR, Boolean.FALSE);
		}
	}

	public IStatus validateModuleNameCollisions() {
		if (getBooleanProperty(ENABLED)) {
			ArrayList list = new ArrayList();
			String projectName = null;
			String actualProjectName = null;
			boolean errorCollision = false;
			boolean errorNoSelection = true;
			if (getBooleanProperty(CREATE_APPCLIENT)) {
				actualProjectName = clientModel.getTargetProject().getName();
				projectName = CoreFileSystemLibrary.isCaseSensitive() ? actualProjectName : actualProjectName.toLowerCase();
				list.add(projectName);
				errorNoSelection = false;
			}
			if (getBooleanProperty(CREATE_EJB)) {
				actualProjectName = ejbModel.getTargetProject().getName();
				projectName = CoreFileSystemLibrary.isCaseSensitive() ? actualProjectName : actualProjectName.toLowerCase();
				if (!list.contains(projectName)) {
					list.add(projectName);
				} else {
					errorCollision = true;
				}
				errorNoSelection = false;
			}
			if (!errorCollision && getBooleanProperty(CREATE_WEB)) {
				actualProjectName = webModel.getTargetProject().getName();
				projectName = CoreFileSystemLibrary.isCaseSensitive() ? actualProjectName : actualProjectName.toLowerCase();
				if (!list.contains(projectName)) {
					list.add(projectName);
				} else {
					errorCollision = true;
				}
				errorNoSelection = false;
			}
			if (!errorCollision && getBooleanProperty(CREATE_CONNECTOR)) {
				actualProjectName = jcaModel.getTargetProject().getName();
				projectName = CoreFileSystemLibrary.isCaseSensitive() ? actualProjectName : actualProjectName.toLowerCase();
				if (!list.contains(projectName)) {
					list.add(projectName);
				} else {
					errorCollision = true;
				}
				errorNoSelection = false;
			}
			if (errorCollision) {
				return J2EEPlugin.newErrorStatus(EARCreationResourceHandler.getString("DuplicateModuleNames", new Object[]{actualProjectName}), null); //$NON-NLS-1$
			} else if (errorNoSelection) {
				return J2EEPlugin.newErrorStatus(EARCreationResourceHandler.getString("NoModulesSelected"), null); //$NON-NLS-1$
			}
		}

		return OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.startsWith(PROJECT_BASE)) {
			return validateNestedProjectName(convertPropertyNameToInt(propertyName));
		} else if (propertyName.equals(MODULE_NAME_COLLISIONS_VALIDATION)) {
			return validateModuleNameCollisions();
		} else if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
			return OK_STATUS;
		}
		return super.doValidateProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doGetProperty(java.lang.String)
	 */
	protected Object doGetProperty(String propertyName) {
		if (propertyName.startsWith(PROJECT_BASE))
			return getNestedProjectName(convertPropertyNameToInt(propertyName));
		return super.doGetProperty(propertyName);
	}

	private void setDefaultNestedProjectName(String name, int flag) {
		J2EEModuleCreationDataModel modModule = getNestedModel(flag);
		if (modModule != null) {
			String projName = ensureUniqueProjectName(name);
			modModule.setProperty(J2EEModuleCreationDataModel.PROJECT_NAME, projName);
		}
	}

	/**
	 * @param string
	 */
	private void setDefaultProjectNames(String base) {
		String projectName;
		if (base.endsWith(EJB_SUFFIX))
			projectName = base;
		else
			projectName = base + EJB_SUFFIX;
		setDefaultNestedProjectName(projectName, EJB);
		if (base.endsWith(WEB_SUFFIX))
			projectName = base;
		else
			projectName = base + WEB_SUFFIX;
		setDefaultNestedProjectName(projectName, WEB);
		if (base.endsWith(CLIENT_SUFFIX))
			projectName = base;
		else
			projectName = base + CLIENT_SUFFIX;
		setDefaultNestedProjectName(projectName, CLIENT);
		if (base.endsWith(CONNECTOR_SUFFIX))
			projectName = base;
		else
			projectName = base + CONNECTOR_SUFFIX;
		setDefaultNestedProjectName(projectName, RAR);
	}

	/**
	 * @param j2eeVersion
	 */
	private void setNestedJ2EEVersion(Object j2eeVersion) {
		if (ejbModel != null)
			ejbModel.setProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
		if (webModel != null)
			webModel.setProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
		if (jcaModel != null)
			jcaModel.setProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
		clientModel.setProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
	}

	/**
	 * @param flag
	 * @param projectName
	 */
	private void setNestedProjectName(int flag, String projectName) {
		J2EEModuleCreationDataModel model = getNestedModel(flag);
		if (model != null) {
			model.setProperty(J2EEModuleCreationDataModel.PROJECT_NAME, projectName);
		}
	}

	/**
	 * @param flag
	 */
	private IStatus validateNestedProjectName(int flag) {
		J2EEModuleCreationDataModel model = getNestedModel(flag);
		if (model != null) {
			String createProperty = null;
			switch (flag) {
				case EJB :
					createProperty = CREATE_EJB;
					break;
				case WEB :
					createProperty = CREATE_WEB;
					break;
				case CLIENT :
					createProperty = CREATE_APPCLIENT;
					break;
				case RAR :
					createProperty = CREATE_CONNECTOR;
					break;
			}
			if (null != createProperty && getBooleanProperty(createProperty)) {
				return model.validateProperty(J2EEModuleCreationDataModel.PROJECT_NAME);
			}
		}
		return J2EEPlugin.OK_STATUS;
	}

	private Object getNestedProjectName(int flag) {
		J2EEModuleCreationDataModel model = getNestedModel(flag);
		if (model != null)
			return model.getProperty(J2EEModuleCreationDataModel.PROJECT_NAME);
		return null;
	}

	private J2EEModuleCreationDataModel getNestedModel(int flag) {
		switch (flag) {
			case EJB :
				return ejbModel;
			case WEB :
				return webModel;
			case CLIENT :
				return clientModel;
			case RAR :
				return jcaModel;
		}
		return null;
	}

	/**
	 * @return Returns the clientModel.
	 */
	public AppClientProjectCreationDataModel getClientModel() {
		return clientModel;
	}

	/**
	 * @return Returns the ejbModel.
	 */
	public J2EEModuleCreationDataModel getEjbModel() {
		return ejbModel;
	}

	/**
	 * @return Returns the rarModel.
	 */
	public J2EEModuleCreationDataModel getJCAModel() {
		return jcaModel;
	}

	/**
	 * @return Returns the webModel.
	 */
	public J2EEModuleCreationDataModel getWebModel() {
		return webModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(CREATE_CONNECTOR) || propertyName.equals(CONNECTOR_PROJECT_NAME)) {
			int version = getIntProperty(J2EE_VERSION);
			boolean result = version > J2EEVersionConstants.J2EE_1_2_ID;
			if (result)
				return (Boolean) getProperty(CREATE_CONNECTOR);
			return new Boolean(result);
		}
		if (propertyName.equals(APPCLIENT_PROJECT_NAME))
			return (Boolean) getProperty(CREATE_APPCLIENT);
		if (propertyName.equals(EJB_PROJECT_NAME))
			return (Boolean) getProperty(CREATE_EJB);
		if (propertyName.equals(WEB_PROJECT_NAME))
			return (Boolean) getProperty(CREATE_WEB);
		return super.basicIsEnabled(propertyName);
	}

}