/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * This dataModel is used for to create Enterprise Applications.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EnterpriseApplicationCreationDataModelOld extends J2EEArtifactCreationDataModelOld {
	/**
	 * An optional dataModel propertyName for an Integer type. It is used to specify the J2EE
	 * Version for the new Enterprise Application. The default value is computed based on the
	 * highest J2EE version specified in the J2EE Properties.
	 * 
	 * @since WTP 1.0
	 */
	public static final String APPLICATION_VERSION = "J2EEApplicationCreationDataModel.APPLICATION_VERSION"; //$NON-NLS-1$

	/*
	 * A List containing IProjects
	 */
	public static final String MODULE_LIST = AddArchiveProjectsToEARDataModel.MODULE_LIST;
	/*
	 * UI only, type Boolean, default false.
	 */
	public static final String UI_SHOW_FIRST_PAGE_ONLY = "J2EEApplicationCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY"; //$NON-NLS-1$

	private static final String NESTED_MODEL_ADD_ARCHIVE_TO_EAR = "J2EEApplicationCreationDataModel.NESTED_MODEL_ADD_ARCHIVE_TO_EAR"; //$NON-NLS-1$
	private AddArchiveProjectsToEARDataModel addModulesToEARDataModel;

	/**
	 * Creates an Enterprise Application project with the specified name and version in the
	 * specified location.
	 * 
	 * @param projectName
	 *            The name of the Enterprise Application project to create.
	 * @param projectLocation
	 *            Sets the local file system location for the described project. The path must be
	 *            either an absolute file system path, or a relative path whose first segment is the
	 *            name of a defined workspace path variable. If <code>null</code> is specified,
	 *            the default location is used.
	 * @param applicationVersion
	 *            Sets the J2EE Version for the descibed project. The version must be one of
	 *            <code>J2EEVersionConstants.J2EE_1_2_ID</code>,
	 *            <code>J2EEVersionConstants.J2EE_1_3_ID</code>, or
	 *            <code>J2EEVersionConstants.J2EE_1_4_ID</code>.
	 * @since WTP 1.0
	 */
	public static void createProject(String projectName, IPath projectLocation, int applicationVersion) {
		EnterpriseApplicationCreationDataModelOld dataModel = new EnterpriseApplicationCreationDataModelOld();
		dataModel.setProperty(PROJECT_NAME, projectName);
		if (null != projectLocation) {
			dataModel.setProperty(PROJECT_LOCATION, projectLocation.toOSString());
		}
		dataModel.setIntProperty(APPLICATION_VERSION, applicationVersion);
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}


	public WTPOperation getDefaultOperation() {
		return new EnterpriseApplicationCreationOperationOld(this);
	}

	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEARNatureConstants.EDIT_MODEL_ID);
		getServerTargetDataModel().setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APPLICATION_TYPE);
		getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{IEARNatureConstants.NATURE_ID});
		setProperty(APPLICATION_VERSION, getDefaultProperty(APPLICATION_VERSION));
		//set it so it pushes it down to ServerTargeting
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(APPLICATION_VERSION);
		addValidBaseProperty(UI_SHOW_FIRST_PAGE_ONLY);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		addModulesToEARDataModel = new AddArchiveProjectsToEARDataModel();
		addNestedModel(NESTED_MODEL_ADD_ARCHIVE_TO_EAR, addModulesToEARDataModel);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(APPLICATION_VERSION)) {
			return getDefaultJ2EEVersion();
		} else if (propertyName.equals(UI_SHOW_FIRST_PAGE_ONLY)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	private Object getDefaultJ2EEVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_3_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_2_ID);
			default :
				return new Integer(J2EEVersionConstants.J2EE_1_4_TEXT);
		}
	}

	/**
	 * Returns the String representation of <code>J2EEVersionConstants.J2EE_1_2_ID</code>,
	 * <code>J2EEVersionConstants.J2EE_1_3_ID</code>, or
	 * <code>J2EEVersionConstants.J2EE_1_4_ID</code>.
	 * 
	 * @param applicationVersion
	 * @return The String representation of <code>applicationVersion</code>
	 */
	public final static String getVersionString(int applicationVersion) {
		switch (applicationVersion) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.VERSION_1_2_TEXT;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.VERSION_1_3_TEXT;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return J2EEVersionConstants.VERSION_1_4_TEXT;
		}
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(APPLICATION_VERSION)) {
			return getValidJ2EEVersionDescriptors();
		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(APPLICATION_VERSION)) {
			int version = getIntProperty(APPLICATION_VERSION);
			WTPPropertyDescriptor descriptor = null;
			switch (version) {
				case (J2EEVersionConstants.J2EE_1_2_ID) :
					descriptor = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
					break;
				case (J2EEVersionConstants.J2EE_1_3_ID) :
					descriptor = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
					break;
				case (J2EEVersionConstants.J2EE_1_4_ID) :
				default :
					descriptor = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), J2EEVersionConstants.VERSION_1_4_TEXT);
					break;

			}
			return descriptor;
		}
		return super.doGetPropertyDescriptor(propertyName);
	}

	/**
	 * @return Return a String[] of the valid J2EE versions for the selected J2EE Preference Level.
	 */
	protected WTPPropertyDescriptor[] getValidJ2EEVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case (J2EEVersionConstants.J2EE_1_4_ID) :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), J2EEVersionConstants.VERSION_1_4_TEXT);
		}
		return descriptors;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		super.doSetProperty(propertyName, propertyValue);
		if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName)) {
			addModulesToEARDataModel.setProperty(AddArchiveProjectsToEARDataModel.PROJECT_NAME, propertyValue);
		} else if (propertyName.equals(APPLICATION_VERSION)) {
			getServerTargetDataModel().setProperty(ServerTargetDataModel.J2EE_VERSION_ID, propertyValue);
		} else if (MODULE_LIST.equals(propertyName)) {
			addModulesToEARDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, propertyValue);
		}
		return true;
	}

	public AddArchiveProjectsToEARDataModel getAddModulesToEARDataModel() {
		return addModulesToEARDataModel;
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG) {
			if (AddArchiveProjectsToEARDataModel.PROJECT_NAME.equals(event.getPropertyName())) {
				setProperty(PROJECT_NAME, event.getProperty());
				return;
			}
		}
		super.propertyChanged(event);
	}

	public void enableValidation() {
		super.enableValidation();
	}

	public void disableValidation() {
		super.disableValidation();
	}
}