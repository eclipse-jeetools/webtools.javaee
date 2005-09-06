/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AppClientComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IAppClientComponentCreationDataModelProperties {

	public AppClientComponentCreationDataModelProvider() {
		super();
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(CREATE_DEFAULT_MAIN_CLASS);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS)) {
			return getProperty(CREATE_DEFAULT_FILES);
		} else if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(MANIFEST_FOLDER)) {
			if (model.getBooleanProperty(SUPPORT_MULTIPLE_MODULES))
				return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + IPath.SEPARATOR + J2EEConstants.META_INF;
			else
				return IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + IPath.SEPARATOR + J2EEConstants.META_INF;
		}
		if (propertyName.equals(DD_FOLDER)) {
			if (model.getBooleanProperty(SUPPORT_MULTIPLE_MODULES))
				return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + IPath.SEPARATOR + J2EEConstants.META_INF;
			else
				return IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + IPath.SEPARATOR + J2EEConstants.META_INF;
		}
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			if (model.getBooleanProperty(SUPPORT_MULTIPLE_MODULES))
				return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER;
			else
				return IPath.SEPARATOR + CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		return new Integer(highestJ2EEPref);
	}

	protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		DataModelPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new DataModelPropertyDescriptor[1];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new DataModelPropertyDescriptor[2];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new DataModelPropertyDescriptor[3];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), J2EEVersionConstants.VERSION_1_4_TEXT);
				break;
		}
		return descriptors;
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		return moduleVersion;
	}

	protected EClass getComponentType() {
		return CommonarchiveFactoryImpl.getPackage().getApplicationClientFile();
	}

	protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
	 */
	protected String getComponentID() {
		return IModuleConstants.JST_APPCLIENT_MODULE;
	}

	public IDataModelOperation getDefaultOperation() {
		return new AppClientComponentCreationOperation(model);
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		return super.getValidPropertyDescriptors(propertyName);
	}
}
