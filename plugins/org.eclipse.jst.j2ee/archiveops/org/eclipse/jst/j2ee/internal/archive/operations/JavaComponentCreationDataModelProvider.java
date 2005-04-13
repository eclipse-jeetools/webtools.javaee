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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperationEx;
import org.eclipse.jst.j2ee.archive.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public  class JavaComponentCreationDataModelProvider extends ComponentCreationDataModelProvider implements IJavaComponentCreationDataModelProperties {
	
	
	public String[] getPropertyNames() {
		String[] props = new String[]{JAVASOURCE_FOLDER, MANIFEST_FOLDER};
		return combineProperties(super.getPropertyNames(), props);
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

	/* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_UTILITY_MODULE;
    }

	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityComponentCreationOperationEx(model);
	}

	protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel#getVersion()
	 */
	protected String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel#getProperties()
	 */
	protected List getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return  getComponentName();
		}else if (propertyName.equals(MANIFEST_FOLDER)) {
			return  getComponentName() +  "/" + J2EEConstants.META_INF; //$NON-NLS-1$
		}	
		return super.getDefaultProperty(propertyName);
	}



	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModelProvider#getValidComponentVersionDescriptors()
	 */
	protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}	
	public IStatus validate(String propertyName) {
        if (propertyName.equals(JAVASOURCE_FOLDER)) {
            IStatus status = OK_STATUS;
            String srcFolderName = getDataModel().getStringProperty(JAVASOURCE_FOLDER);
			if (srcFolderName == null || srcFolderName.length()==0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NAME_EMPTY);
				status =  WTPCommonPlugin.createErrorStatus(errorMessage); 
			}
			return status;

        } else if (propertyName.equals(MANIFEST_FOLDER)) {
            IStatus status = OK_STATUS;
            String srcFolderName = getDataModel().getStringProperty(MANIFEST_FOLDER);
			if (srcFolderName == null || srcFolderName.length()==0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NAME_EMPTY);
				status =  WTPCommonPlugin.createErrorStatus(errorMessage); 
			}
			return status;
		} 
        return super.validate(propertyName);
	}	
}
