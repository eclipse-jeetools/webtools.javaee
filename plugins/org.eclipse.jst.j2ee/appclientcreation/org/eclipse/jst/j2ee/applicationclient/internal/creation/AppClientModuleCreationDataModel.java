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
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;

/**
 * This dataModel is used for to create Application Client Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class AppClientModuleCreationDataModel extends J2EEModuleCreationDataModelOld {

	/**
	 * Boolean, default=true. If this is true and CREATE_DEFAULT_FILES is true, then a default main
	 * class will be generated during project creation.
	 */
	public static final String CREATE_DEFAULT_MAIN_CLASS = "AppClientModuleCreationDataModel.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$

	/**
	 * Creates an Application Client project with the specified name and version in the specified
	 * location.
	 * 
	 * @param projectName
	 *            The name of the Application Client project to create.
	 * @param projectLocation
	 *            Sets the local file system location for the described project. The path must be
	 *            either an absolute file system path, or a relative path whose first segment is the
	 *            name of a defined workspace path variable. If <code>null</code> is specified,
	 *            the default location is used.
	 * @param applicationClientVersion
	 *            Sets the Application Client Module Version for the descibed project. The version
	 *            must be one of <code>J2EEVersionConstants.J2EE_1_2_ID</code>,
	 *            <code>J2EEVersionConstants.J2EE_1_3_ID</code>, or
	 *            <code>J2EEVersionConstants.J2EE_1_4_ID</code>.
	 * @since WTP 1.0
	 */
	public static void createProject(String projectName, IPath projectLocation, int applicationClientVersion) {
		AppClientModuleCreationDataModel dataModel = new AppClientModuleCreationDataModel();
		dataModel.setProperty(PROJECT_NAME, projectName);
		if (null != projectLocation) {
			dataModel.setProperty(PROJECT_LOCATION, projectLocation.toOSString());
		}
		dataModel.setIntProperty(J2EE_MODULE_VERSION, applicationClientVersion);
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}


	public WTPOperation getDefaultOperation() {
		return new AppClientModuleCreationOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CREATE_DEFAULT_MAIN_CLASS);
	}

	protected void init() {
		setJ2EENatureID(IApplicationClientNatureConstants.NATURE_ID);
		super.init();
		setProperty(EDIT_MODEL_ID, IApplicationClientNatureConstants.EDIT_MODEL_ID);
		getServerTargetDataModel().setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APP_CLIENT_TYPE);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, IApplicationClientNatureConstants.DEFAULT_SOURCE_PATH);
		getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{IApplicationClientNatureConstants.DEFAULT_SOURCE_PATH});
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS)) {
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
	protected Integer getDefaultJ2EEModuleVersion() {
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