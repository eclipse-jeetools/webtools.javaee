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


import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.ProjectCreationDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelListener;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EARProjectCreationDataModel extends J2EEProjectCreationDataModel implements J2EEVersionConstants {
	/**
	 * type Integer
	 */
	public static final String EAR_VERSION = "EARProjectCreationDataModel.EAR_VERSION"; //$NON-NLS-1$
	/**
	 * Used to provide a better display name for the j2ee version. The model will translate this
	 * property to the J2EE_VERSION property.
	 */
	public static final String EAR_VERSION_LBL = "EARProjectCreationDataModel.J2EE_VERSION_LBL"; //$NON-NLS-1$
	/**
	 * A List containing IProjects
	 */
	public static final String MODULE_LIST = AddArchiveProjectsToEARDataModel.MODULE_LIST;
	/**
	 * UI only, type Boolean, default false.
	 */
	public static final String UI_SHOW_FIRST_PAGE_ONLY = "EARProjectCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY"; //$NON-NLS-1$
	private static final String NESTED_MODEL_ADD_ARCHIVE_TO_EAR = "EARProjectCreationDataModel.NESTED_MODEL_ADD_ARCHIVE_TO_EAR"; //$NON-NLS-1$

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
		addValidBaseProperty(EAR_VERSION_LBL);
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
		} else if (propertyName.equals(EAR_VERSION_LBL)) {
			return convertVersionIDtoLabel(getIntProperty(EAR_VERSION));
		} else if (propertyName.equals(UI_SHOW_FIRST_PAGE_ONLY)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/**
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

	public static String convertEARVersionToLabel(int earVersion) {
		switch (earVersion) {
			case J2EE_1_2_ID :
				return VERSION_1_2_TEXT;
			case J2EE_1_3_ID :
				return VERSION_1_3_TEXT;
			case J2EE_1_4_ID :
				return VERSION_1_4_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * @param id
	 * @return
	 */
	private String convertVersionIDtoLabel(int id) {
		return convertEARVersionToLabel(id);
	}

	/**
	 * @param label
	 * @return
	 */
	private Integer convertVersionLabeltoID(String label) {
		int id = -1;
		if (label.equals(VERSION_1_2_TEXT))
			id = J2EE_1_2_ID;
		else if (label.equals(VERSION_1_3_TEXT))
			id = J2EE_1_3_ID;
		else if (label.equals(VERSION_1_4_TEXT))
			id = J2EE_1_4_ID;
		return new Integer(id);
	}

	public ProjectCreationDataModel getProjectDataModel() {
		return projectDataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(EAR_VERSION_LBL)) {
			return getValidJ2EEVersionLabels();
		}
		return super.doGetValidPropertyValues(propertyName);
	}

	/**
	 * @return Return a String[] of the valid J2EE versions for the selected J2EE Preference Level.
	 */
	protected Object[] getValidJ2EEVersionLabels() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new String[]{VERSION_1_2_TEXT, VERSION_1_3_TEXT, VERSION_1_4_TEXT};
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new String[]{VERSION_1_2_TEXT, VERSION_1_3_TEXT};
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new String[]{VERSION_1_2_TEXT};
			default :
				return new String[]{VERSION_1_2_TEXT, VERSION_1_3_TEXT, VERSION_1_4_TEXT};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(EAR_VERSION_LBL)) {
			Integer id = convertVersionLabeltoID((String) propertyValue);
			super.setProperty(EAR_VERSION, id);
			serverTargetDataModel.setProperty(ServerTargetDataModel.J2EE_VERSION_ID, id);
			return false;
		}
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
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel#propertyChanged(java.lang.String,
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
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#enableValidation()
	 */
	public void enableValidation() {
		super.enableValidation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#disableValidation()
	 */
	public void disableValidation() {
		super.disableValidation();
	}
}