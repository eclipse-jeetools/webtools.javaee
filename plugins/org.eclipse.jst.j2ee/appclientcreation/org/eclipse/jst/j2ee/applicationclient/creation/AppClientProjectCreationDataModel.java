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
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.jdt.internal.integration.JavaProjectCreationDataModel;


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


	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			Integer propertyValue = (Integer) getProperty(propertyName);
			String description = null;
			switch (propertyValue.intValue()) {
				case J2EEVersionConstants.J2EE_1_2_ID :
					description = J2EEVersionConstants.VERSION_1_2_TEXT;
					break;
				case J2EEVersionConstants.J2EE_1_3_ID :
					description = J2EEVersionConstants.VERSION_1_3_TEXT;
					break;
				case J2EEVersionConstants.J2EE_1_4_ID :
				default :
					description = J2EEVersionConstants.VERSION_1_4_TEXT;
					break;
			}
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}

	protected WTPPropertyDescriptor[] getValidJ2EEModuleVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), J2EEVersionConstants.VERSION_1_4_TEXT);
				break;
		}
		return descriptors;
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		return moduleVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getApplicationClientFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleExtension()
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