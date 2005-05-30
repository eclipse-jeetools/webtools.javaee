/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperationEx;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetHelper;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.internal.FlexibleJavaProjectPreferenceUtil;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IModuleType;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;

public class JavaComponentCreationDataModelProvider extends ComponentCreationDataModelProvider implements IJavaComponentCreationDataModelProperties {
	
	
	public String[] getPropertyNames() {
		String[] props = new String[]{JAVASOURCE_FOLDER, MANIFEST_FOLDER, SERVER_TARGET_ID};
		return combineProperties(super.getPropertyNames(), props);
	}
	
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return  getComponentName();
		}else if (propertyName.equals(MANIFEST_FOLDER)) {
			return  getComponentName() +  "/" + J2EEConstants.META_INF; //$NON-NLS-1$
		}	
		return super.getDefaultProperty(propertyName);
	}
    
    public void init() {    
        super.init();
    }
	
    public boolean propertySet(String propertyName, Object propertyValue) {
        boolean status = super.propertySet(propertyName, propertyValue);
        if (PROJECT_NAME.equals(propertyName)) {
//			model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALUE_CHG);
//			IDataModel dm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
//            dm.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME, propertyValue);
        } else if(LOCATION.equals(propertyName)) {
			IDataModel dm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
            dm.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_LOCATION, propertyValue);
        } else if(SERVER_TARGET_ID.equals(propertyName)) {
			IDataModel dm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
            dm.setProperty(IFlexibleJavaProjectCreationDataModelProperties.SERVER_TARGET_ID, propertyValue);
        }
        return status;
    }
    
	public IStatus validate(String propertyName) {
        if (propertyName.equals(JAVASOURCE_FOLDER)) {
            IStatus status = OK_STATUS;
            String srcFolderName = model.getStringProperty(JAVASOURCE_FOLDER);
			if (srcFolderName == null || srcFolderName.length()==0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NAME_EMPTY);
				status =  WTPCommonPlugin.createErrorStatus(errorMessage); 
			}
			return status;

        } else if (propertyName.equals(MANIFEST_FOLDER)) {
            IStatus status = OK_STATUS;
            String srcFolderName = model.getStringProperty(MANIFEST_FOLDER);
			if (srcFolderName == null || srcFolderName.length()==0) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NAME_EMPTY);
				status =  WTPCommonPlugin.createErrorStatus(errorMessage); 
			}
			return status;
		} else if (propertyName.equals(SERVER_TARGET_ID)) {
			//if multiple modules are  supported, the  project is already been created, no need for validation here
            if(!FlexibleJavaProjectPreferenceUtil.getMultipleModulesPerProjectProp()){
	            IDataModel dm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
	            IStatus nestedValiation = dm.validate();
	            if(!nestedValiation.isOK())
	                return nestedValiation;
            }else
				return OK_STATUS;
        }
//		else if(propertyName.equals(PROJECT_NAME)){
//			IDataModel projectdm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
//			return projectdm.validateProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME);		
//        }
            
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

    protected String getComponentID() {
        return IModuleConstants.JST_UTILITY_MODULE;
    }

    protected String getComponentExtension() {
        return ".jar"; //$NON-NLS-1$
    }

    protected List getProperties() {
        return null;
    }
    
    public IDataModelOperation getDefaultOperation() {
        return new JavaUtilityComponentCreationOperationEx(model);
    }

    protected void initProjectCreationModel() {
        IDataModel dm = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
		model.addNestedModel(NESTED_PROJECT_CREATION_DM, dm);
        model.setProperty(LOCATION, dm.getProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_LOCATION));

    }
	
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(SERVER_TARGET_ID)) {
			//IDataModel projectdm = (IDataModel)model.getNestedModel(NESTED_PROJECT_CREATION_DM);
			//return projectdm.getValidPropertyDescriptors(IFlexibleJavaProjectCreationDataModelProperties.SERVER_TARGET_ID);
		}
		return null;
	}	


}
