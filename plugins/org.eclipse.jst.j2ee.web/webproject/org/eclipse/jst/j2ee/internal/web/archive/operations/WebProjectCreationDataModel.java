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
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.operations.AddModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.ProjectCreationDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WebProjectCreationDataModel extends J2EEModuleCreationDataModel {

	public WTPOperation getDefaultOperation() {
		return new WebProjectCreationOperation(this);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	private Object getDefaultJ2EEVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_3_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new Integer(J2EEVersionConstants.WEB_2_2_ID);
			default :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
		}
	}

	/**
	 * Type Integer
	 */
	public static final String SERVLET_VERSION = "WebProjectCreationDataModel.SERVLET_VERSION"; //$NON-NLS-1$
	/**
	 * Type Integer
	 */
	public static final String JSP_VERSION = "WebProjectCreationDataModel.JSP_VERSION"; //$NON-NLS-1$
	/**
	 * Type String
	 */
	public static final String CONTEXT_ROOT = AddWebModuleToEARDataModel.CONTEXT_ROOT;

	public static final String WEB_CONTENT = "WebProjectCreationDataModel.WEB_CONTENT"; //$NON-NLS-1$
	public static final String MIGRATE_WEB_SETTINGS = "WebProjectCreationDataModel.MIGRATE_WEB_SETTINGS"; //$NON-NLS-1$

	protected void init() {
		j2eeNatureID = IWebNatureConstants.J2EE_NATURE_ID;
		setProperty(EDIT_MODEL_ID, IWebNatureConstants.EDIT_MODEL_ID);
		serverTargetDataModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.WEB_APP_TYPE);
		getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{IWebNatureConstants.J2EE_NATURE_ID});
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{getDefaultJavaSourceFolderName()});
		updateOutputLocation();
		super.init();
	}



	/**
	 * @return
	 */
	private String getDefaultJavaSourceFolderName() {
		String javaSrcFolderPref = J2EEPlugin.getDefault().getJ2EEPreferences().getJavaSourceFolderName();
		if (javaSrcFolderPref == null || javaSrcFolderPref.length() == 0)
			javaSrcFolderPref = IWebNatureConstants.JAVA_SOURCE;
		return javaSrcFolderPref;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean retVal = super.doSetProperty(propertyName, propertyValue);
		if (WEB_CONTENT.equals(propertyName)) {
			updateOutputLocation();
		} else if (propertyName.equals(ADD_TO_EAR)) {
			Boolean value = (Boolean) propertyValue;
			if (value.booleanValue())
				earProjectCreationDataModel.enableValidation();
			else
				earProjectCreationDataModel.disableValidation();
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			notifyEnablementChange(J2EE_MODULE_VERSION);
		} else if (propertyName.equals(J2EE_MODULE_VERSION)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setProperty(USE_ANNOTATIONS, Boolean.FALSE);
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (propertyName.equals(CONTEXT_ROOT)) {
			addModuleToEARDataModel.setProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT, propertyValue);
		}
		return retVal;
	}

	private void updateOutputLocation() {
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, getOutputLocation());
	}

	private Object getOutputLocation() {
		StringBuffer buf = new StringBuffer(getStringProperty(WEB_CONTENT));
		buf.append(IPath.SEPARATOR);
		buf.append(IWebNatureConstants.INFO_DIRECTORY);
		buf.append(IPath.SEPARATOR);
		buf.append(IWebNatureConstants.CLASSES_DIRECTORY);
		return buf.toString();
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SERVLET_VERSION);
		addValidBaseProperty(JSP_VERSION);
		addValidBaseProperty(WEB_CONTENT);
		addValidBaseProperty(USE_ANNOTATIONS);
		addValidBaseProperty(MIGRATE_WEB_SETTINGS);
		addValidBaseProperty(CONTEXT_ROOT);
	}

	protected AddModuleToEARDataModel createModuleNestedModel() {
		return new AddWebModuleToEARDataModel();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MIGRATE_WEB_SETTINGS)) {
			return Boolean.TRUE;
		}

		if (propertyName.equals(WEB_CONTENT)) {
			String webContentFolderPref = J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName();
			if (webContentFolderPref == null || webContentFolderPref.length() == 0)
				webContentFolderPref = IWebNatureConstants.WEB_MODULE_DIRECTORY_;
			return webContentFolderPref;
		}
		if (propertyName.equals(CONTEXT_ROOT)) {
			return addModuleToEARDataModel.getProperty(CONTEXT_ROOT);
		}

		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getDefaultJ2EEVersion();
		}
		if (propertyName.equals(SERVLET_VERSION)) {
			int moduleVersion = getIntProperty(J2EE_MODULE_VERSION);
			int servletVersion = J2EEVersionConstants.SERVLET_2_2;
			switch (moduleVersion) {
				case J2EEVersionConstants.WEB_2_2_ID :
					servletVersion = J2EEVersionConstants.SERVLET_2_2;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
				case J2EEVersionConstants.WEB_2_4_ID :
					servletVersion = J2EEVersionConstants.SERVLET_2_3;
					break;
			}
			return new Integer(servletVersion);
		}
		if (propertyName.equals(JSP_VERSION)) {
			int moduleVersion = getIntProperty(J2EE_MODULE_VERSION);
			int jspVersion = J2EEVersionConstants.JSP_1_2_ID;
			switch (moduleVersion) {
				case J2EEVersionConstants.WEB_2_2_ID :
					jspVersion = J2EEVersionConstants.JSP_1_2_ID;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
				case J2EEVersionConstants.WEB_2_4_ID :
					jspVersion = J2EEVersionConstants.JSP_2_0_ID;
					break;
			}
			return new Integer(jspVersion);
		}
		return super.getDefaultProperty(propertyName);
	}

	protected String convertVersionIDtoLabel(int id) {
		switch (id) {
			case J2EEVersionConstants.WEB_2_2_ID :
				return J2EEVersionConstants.VERSION_2_2_TEXT;
			case J2EEVersionConstants.WEB_2_3_ID :
				return J2EEVersionConstants.VERSION_2_3_TEXT;
			case J2EEVersionConstants.WEB_2_4_ID :
				return J2EEVersionConstants.VERSION_2_4_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	protected Integer convertVersionLabeltoID(String label) {
		int version = -1;
		if (label.equals(J2EEVersionConstants.VERSION_2_2_TEXT)) {
			version = J2EEVersionConstants.WEB_2_2_ID;
		} else if (label.equals(J2EEVersionConstants.VERSION_2_3_TEXT)) {
			version = J2EEVersionConstants.WEB_2_3_ID;
		} else if (label.equals(J2EEVersionConstants.VERSION_2_4_TEXT)) {
			version = J2EEVersionConstants.WEB_2_4_ID;
		}
		return new Integer(version);
	}

	/**
	 * @return Return a String[] of the valid J2EE versions for the selected J2EE Preference Level.
	 */
	protected Object[] getValidJ2EEVersionLabels() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new String[]{J2EEVersionConstants.VERSION_2_2_TEXT, J2EEVersionConstants.VERSION_2_3_TEXT, J2EEVersionConstants.VERSION_2_4_TEXT};
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new String[]{J2EEVersionConstants.VERSION_2_2_TEXT, J2EEVersionConstants.VERSION_2_3_TEXT};
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new String[]{J2EEVersionConstants.VERSION_2_2_TEXT};
			default :
				return new String[]{J2EEVersionConstants.VERSION_2_2_TEXT, J2EEVersionConstants.VERSION_2_3_TEXT, J2EEVersionConstants.VERSION_2_4_TEXT};
		}
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		switch (moduleVersion) {
			case J2EEVersionConstants.WEB_2_2_ID :
				return J2EEVersionConstants.J2EE_1_2_ID;
			case J2EEVersionConstants.WEB_2_3_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.WEB_2_4_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
		}
		return -1;
	}

	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		switch (j2eeVersion.intValue()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return new Integer(J2EEVersionConstants.WEB_2_2_ID);
			case J2EEVersionConstants.J2EE_1_3_ID :
				return new Integer(J2EEVersionConstants.WEB_2_3_ID);
			case J2EEVersionConstants.J2EE_1_4_ID :
				return new Integer(J2EEVersionConstants.WEB_2_4_ID);
		}
		return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getWARFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".war"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(CONTEXT_ROOT)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				return addModuleToEARDataModel.validateProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT);
			}
			return OK_STATUS;

		}
		return super.doValidateProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == addModuleToEARDataModel && event.getPropertyName().equals(AddWebModuleToEARDataModel.CONTEXT_ROOT)) {
			setProperty(CONTEXT_ROOT, event.getNewValue());
		}
	}
}