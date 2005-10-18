/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class JavaComponentCreationDataModelProvider extends ComponentCreationDataModelProvider implements IJavaComponentCreationDataModelProperties {

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(JAVASOURCE_FOLDER);
		propertyNames.add(MANIFEST_FOLDER);
		propertyNames.add(RUNTIME_TARGET_ID);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return getComponentName();
		} else if (propertyName.equals(MANIFEST_FOLDER)) {
			return "/" + J2EEConstants.META_INF; //$NON-NLS-1$
		}
		return super.getDefaultProperty(propertyName);
	}

	public void init() {
		super.init();
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		if (PROJECT_NAME.equals(propertyName)) {
			// model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALUE_CHG);
			// IDataModel dm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			// dm.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME,
			// propertyValue);
		} else if (LOCATION.equals(propertyName)) {
			IDataModel dm = model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			dm.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_LOCATION, propertyValue);
		} else if (RUNTIME_TARGET_ID.equals(propertyName)) {
			IDataModel dm = model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			dm.setProperty(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, propertyValue);
		}
//		else if (propertyName.equals(JAVASOURCE_FOLDER)){
//			//unless MANIFEST folder is opened up, it is set as same as Java source folder
//			setProperty(MANIFEST_FOLDER, getProperty(JAVASOURCE_FOLDER)+ "/" + J2EEConstants.META_INF);
//		}
		return status;
	}

	public IStatus validate(String propertyName) {
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			IStatus status = OK_STATUS;
			String srcFolderName = model.getStringProperty(JAVASOURCE_FOLDER);
			if (srcFolderName == null || srcFolderName.length() == 0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SOURCEFOLDER_EMPTY);
				status = WTPCommonPlugin.createErrorStatus(errorMessage);
			}
			return status;

		} else if (propertyName.equals(MANIFEST_FOLDER)) {
			IStatus status = OK_STATUS;
			String srcFolderName = model.getStringProperty(MANIFEST_FOLDER);
			if (srcFolderName == null || srcFolderName.length() == 0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NAME_EMPTY);
				status = WTPCommonPlugin.createErrorStatus(errorMessage);
			}
			return status;
		} else if (propertyName.equals(RUNTIME_TARGET_ID)) {
			
			IDataModel dm = model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			IStatus nestedValiation = dm.validate();
			if (!nestedValiation.isOK())
				return nestedValiation;			
		}
		// else if(propertyName.equals(PROJECT_NAME)){
		// IDataModel projectdm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
		// return
		// projectdm.validateProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME);
		// }

		return super.validate(propertyName);
	}

	public JavaComponentCreationDataModelProvider() {
		super();
	}

	protected EClass getComponentType() {
		return CommonarchivePackage.eINSTANCE.getModuleFile();
	}

	protected Integer getDefaultComponentVersion() {
		Integer version = new Integer("10");
		return version;
	}

	protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	protected List getProperties() {
		return null;
	}

	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityComponentCreationOperation(model);
	}

	protected void initProjectCreationModel() {
		IDataModel dm = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
		model.addNestedModel(NESTED_PROJECT_CREATION_DM, dm);
		model.setProperty(LOCATION, dm.getProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_LOCATION));

	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID)) {
			// IDataModel projectdm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			// return
			// projectdm.getValidPropertyDescriptors(IFlexibleJavaProjectCreationDataModelProperties.SERVER_TARGET_ID);
		}
		return null;
	}


}
