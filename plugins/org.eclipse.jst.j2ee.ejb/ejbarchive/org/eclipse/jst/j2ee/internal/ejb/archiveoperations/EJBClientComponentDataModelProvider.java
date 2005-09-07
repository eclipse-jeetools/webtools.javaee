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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Set;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EJBClientComponentDataModelProvider extends JavaComponentCreationDataModelProvider implements IEJBClientComponentCreationDataModelProperties, IAnnotationsDataModel {

	public EJBClientComponentDataModelProvider() {
		super();
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EJB_COMPONENT_NAME);
		propertyNames.add(EJB_PROJECT_NAME);
		propertyNames.add(EJB_COMPONENT_DEPLOY_NAME);
		propertyNames.add(CLIENT_COMPONENT_URI);
		propertyNames.add(CREATE_PROJECT);
		propertyNames.add(EAR_COMPONENT_DEPLOY_NAME);
		propertyNames.add(IEjbComponentCreationDataModelProperties.EAR_COMPONENT_HANDLE);
		propertyNames.add(USE_ANNOTATIONS);
		return propertyNames;
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(COMPONENT_NAME)) {
			if (!model.isPropertySet(CLIENT_COMPONENT_URI))
				model.notifyPropertyChange(CLIENT_COMPONENT_URI, DataModelEvent.ENABLE_CHG);
		}else if (propertyName.equals(JAVASOURCE_FOLDER)){
			//unless MANIFEST folder is opened up, it is set as same as Java source folder
			setProperty(MANIFEST_FOLDER, getProperty(JAVASOURCE_FOLDER)+ "/" + J2EEConstants.META_INF);
		}		
		return status;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(COMPONENT_NAME)) {
			return getDefaultClientModuleName();
		} else if (propertyName.equals(CLIENT_COMPONENT_URI)) {
			return getStringProperty(COMPONENT_NAME).trim().replace(' ', '_') + ".jar"; //$NON-NLS-1$
		} else if (propertyName.equals(COMPONENT_DEPLOY_NAME)) {
			return getStringProperty(COMPONENT_NAME); //$NON-NLS-1$
		} else if (propertyName.equals(JAVASOURCE_FOLDER)) {
			if (model.getBooleanProperty(SUPPORT_MULTIPLE_MODULES))
				return getStringProperty(COMPONENT_NAME) + "/" + "ejbModule"; //$NON-NLS-1$ //$NON-NLS-2$
			else
				return "/" + "ejbModule";//$NON-NLS-2$
		} else if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		return super.getDefaultProperty(propertyName);
	}

	private String getDefaultClientModuleName() {
		String ejbModuleName = getStringProperty(EJB_COMPONENT_NAME);
		String moduleName = ejbModuleName + "Client"; //$NON-NLS-1$
		return moduleName;
	}

	public IStatus validate(String propertyName) {
		IStatus status = super.validate(propertyName);
		if (status.isOK()) {
			if (propertyName.equals(COMPONENT_NAME)) {
				String ejbModuleName = getStringProperty(EJB_COMPONENT_NAME);
				String clientModuleName = getStringProperty(COMPONENT_NAME);
				if (ejbModuleName.equals(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				} else if (!CoreFileSystemLibrary.isCaseSensitive() && ejbModuleName.equalsIgnoreCase(clientModuleName)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
				}
			}
			if (propertyName.equals(CLIENT_COMPONENT_URI)) {
				status = validateClientJarUri();
			}
		}
		return status;
	}

	public IStatus validateClientJarUri() {
		String clientJarURI = getStringProperty(CLIENT_COMPONENT_URI);
		if (clientJarURI == null || clientJarURI.trim().length() == 0)
			return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CLIENT_JAR_URI_NOT_RESOLVE_UI_"), null); //$NON-NLS-1$

		return OK_STATUS;
	}

	public IDataModelOperation getDefaultOperation() {
		return new EJBClientComponentCreationOperation(model);
	}

	public boolean hasExistingClientJar() {
		return false;
	}
}
