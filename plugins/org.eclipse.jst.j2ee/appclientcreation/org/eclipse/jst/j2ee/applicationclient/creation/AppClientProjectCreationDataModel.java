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
 * Created on Nov 5, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.jst.j2ee.applicationclient.creation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppClientProjectCreationDataModel extends J2EEModuleCreationDataModel {

	/**
	 * Boolean, default=true. If this is true and CREATE_DEFAULT_FILES is true, then a default main
	 * class will be generated during project creation.
	 */
	public static final String CREATE_DEFAULT_MAIN_CLASS = "AppClientProjectCreationDataModel.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$

	public WTPOperation getDefaultOperation() {
		return new AppClientProjectCreationOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CREATE_DEFAULT_MAIN_CLASS);
	}

	protected void init() {
		j2eeNatureID = IApplicationClientNatureConstants.NATURE_ID;
		super.init();
		setProperty(EDIT_MODEL_ID, IApplicationClientNatureConstants.EDIT_MODEL_ID);
		serverTargetDataModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APP_CLIENT_TYPE);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, IApplicationClientNatureConstants.DEFAULT_SOURCE_PATH);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{IApplicationClientNatureConstants.DEFAULT_SOURCE_PATH});
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getDefaultJ2EEVersion();
		} else if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected String convertVersionIDtoLabel(int id) {
		switch (id) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.VERSION_1_2_TEXT;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.VERSION_1_3_TEXT;
			case J2EEVersionConstants.J2EE_1_4_ID :
				return J2EEVersionConstants.VERSION_1_4_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	protected Integer convertVersionLabeltoID(String label) {
		int version = -1;
		if (label.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
			version = J2EEVersionConstants.J2EE_1_2_ID;
		} else if (label.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
			version = J2EEVersionConstants.J2EE_1_3_ID;
		} else if (label.equals(J2EEVersionConstants.VERSION_1_4_TEXT)) {
			version = J2EEVersionConstants.J2EE_1_4_ID;
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
				return new String[]{J2EEVersionConstants.VERSION_1_2_TEXT, J2EEVersionConstants.VERSION_1_3_TEXT, J2EEVersionConstants.VERSION_1_4_TEXT};
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new String[]{J2EEVersionConstants.VERSION_1_2_TEXT, J2EEVersionConstants.VERSION_1_3_TEXT};
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new String[]{J2EEVersionConstants.VERSION_1_2_TEXT};
			default :
				return new String[]{J2EEVersionConstants.VERSION_1_2_TEXT, J2EEVersionConstants.VERSION_1_3_TEXT, J2EEVersionConstants.VERSION_1_4_TEXT};
		}
	}


	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		return moduleVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getApplicationClientFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".jar"; //$NON-NLS-1$
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
				return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
		}
	}

}