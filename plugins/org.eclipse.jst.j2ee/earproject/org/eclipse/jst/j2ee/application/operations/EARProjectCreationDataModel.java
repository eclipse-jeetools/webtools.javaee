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
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelListener;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class EARProjectCreationDataModel extends J2EEProjectCreationDataModel {
	/**
	 * type Integer
	 */
	public static final String EAR_VERSION = "EARProjectCreationDataModel.EAR_VERSION"; //$NON-NLS-1$

	/*
	 * A List containing IProjects
	 */
	public static final String MODULE_LIST = AddArchiveProjectsToEARDataModel.MODULE_LIST;
	/*
	 * UI only, type Boolean, default false.
	 */
	public static final String UI_SHOW_FIRST_PAGE_ONLY = "EARProjectCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY"; //$NON-NLS-1$
	private static final String NESTED_MODEL_ADD_ARCHIVE_TO_EAR = "EARProjectCreationDataModel.NESTED_MODEL_ADD_ARCHIVE_TO_EAR"; //$NON-NLS-1$

	/**
	 * Creates an EAR project with the specified name and version in the specified location.
	 * 
	 * @param projectName
	 *            The name of the EAR project to create.
	 * @param projectLocation
	 *            Sets the local file system location for the described project. The path must be
	 *            either an absolute file system path, or a relative path whose first segment is the
	 *            name of a defined workspace path variable. If <code>null</code> is specified,
	 *            the default location is used.
	 * @param j2eeVersion
	 *            Sets the J2EE Version for the descibed project. The version must be one of
	 *            <code>J2EEVersionConstants.J2EE_1_2_ID</code>,<code>J2EEVersionConstants.J2EE_1_3_ID</code>, or
	 *            <code>J2EEVersionConstants.J2EE_1_4_ID</code>.
	 * @since WTP 1.0
	 */
	public static void createProject(String projectName, IPath projectLocation, int j2eeVersion) {
		EARProjectCreationDataModel dataModel = new EARProjectCreationDataModel();
		dataModel.setProperty(EARProjectCreationDataModel.PROJECT_NAME, projectName);
		if (null != projectLocation) {
			dataModel.setProperty(EARProjectCreationDataModel.PROJECT_LOCATION, projectLocation.toOSString());
		}
		dataModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, j2eeVersion);
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}


	public WTPOperation getDefaultOperation() {
		return new EARProjectCreationOperation(this);
	}

	protected AddArchiveProjectsToEARDataModel addModulesToEARDataModel;

	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEARNatureConstants.EDIT_MODEL_ID);
		serverTargetDataModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APPLICATION_TYPE);
		projectDataModel.setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{IEARNatureConstants.NATURE_ID});
		setProperty(EAR_VERSION, getDefaultProperty(EAR_VERSION));
		//set it so it pushes it down to ServerTargeting
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EAR_VERSION);
		addValidBaseProperty(UI_SHOW_FIRST_PAGE_ONLY);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		addModulesToEARDataModel = new AddArchiveProjectsToEARDataModel();
		addNestedModel(NESTED_MODEL_ADD_ARCHIVE_TO_EAR, addModulesToEARDataModel);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(EAR_VERSION)) {
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

	public static String getEARVersionString(int earVersion) {
		switch (earVersion) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.VERSION_1_2_TEXT;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.VERSION_1_3_TEXT;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default:
				return J2EEVersionConstants.VERSION_1_4_TEXT;
		}
	}

	public ProjectCreationDataModel getProjectDataModel() {
		return projectDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(EAR_VERSION)) {
			return getValidJ2EEVersionDescriptors();
		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(EAR_VERSION)) {
			int version = getIntProperty(EAR_VERSION);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		super.doSetProperty(propertyName, propertyValue);
		if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName)) {
			addModulesToEARDataModel.setProperty(AddArchiveProjectsToEARDataModel.PROJECT_NAME, propertyValue);
		} else if (propertyName.equals(EAR_VERSION)) {
			serverTargetDataModel.setProperty(ServerTargetDataModel.J2EE_VERSION_ID, propertyValue);
		} else if (MODULE_LIST.equals(propertyName)) {
			addModulesToEARDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, propertyValue);
		}
		return true;
	}

	public AddArchiveProjectsToEARDataModel getAddModulesToEARDataModel() {
		return addModulesToEARDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEProjectCreationDataModel#propertyChanged(java.lang.String,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getFlag() == WTPOperationDataModelListener.PROPERTY_CHG) {
			if (AddArchiveProjectsToEARDataModel.PROJECT_NAME.equals(event.getPropertyName())) {
				setProperty(PROJECT_NAME, event.getNewValue());
				return;
			}
		}
		super.propertyChanged(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#enableValidation()
	 */
	public void enableValidation() {
		super.enableValidation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#disableValidation()
	 */
	public void disableValidation() {
		super.disableValidation();
	}
}