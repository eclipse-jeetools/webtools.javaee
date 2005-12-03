/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @deprecated 
 * @see EARFacetProjectCreationDataModelProvider
 */

public class EarComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IEarComponentCreationDataModelProperties {

    public IDataModelOperation getDefaultOperation() {
        //return new EARComponentCreationOperation(model);
    	return new EarComponentCreationFacetOperation(model);
    }

    /**
     * @return Returns the default J2EE spec level based on the Global J2EE
     *         Preference
     */
    protected Integer getDefaultComponentVersion() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        switch (highestJ2EEPref) {
        case (J2EEVersionConstants.J2EE_1_4_ID):
            return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
        case (J2EEVersionConstants.J2EE_1_3_ID):
            return new Integer(J2EEVersionConstants.J2EE_1_3_ID);
        case (J2EEVersionConstants.J2EE_1_2_ID):
            return new Integer(J2EEVersionConstants.J2EE_1_2_ID);
        default:
            return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
        }
    }

    public Set getPropertyNames(){
    	Set propertyNames = super.getPropertyNames();
		propertyNames.add(J2EE_PROJECTS_LIST);
		propertyNames.add(JAVA_PROJECT_LIST);
		return propertyNames;
    }
    
    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(DD_FOLDER)) {
        	return IPath.SEPARATOR + "META_INF"; //$NON-NLS-1$
        } else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
            return Boolean.FALSE;
        } else if (propertyName.equals(J2EE_PROJECTS_LIST)) {
            return Collections.EMPTY_LIST;
        }else if (propertyName.equals(JAVA_PROJECT_LIST)){
			return Collections.EMPTY_LIST;
        } else if(propertyName.equals(NESTED_EAR_COMPONENT_CREATION_DM)){
        	return model;
        }
        return super.getDefaultProperty(propertyName);
    }

    public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
        if (propertyName.equals(COMPONENT_VERSION)) {
            Integer propertyValue = (Integer) getProperty(propertyName);
            String description = null;
            switch (propertyValue.intValue()) {
            case J2EEVersionConstants.VERSION_1_2:
                description = J2EEVersionConstants.VERSION_1_2_TEXT;
                break;
            case J2EEVersionConstants.VERSION_1_3:
                description = J2EEVersionConstants.VERSION_1_3_TEXT;
                break;
            case J2EEVersionConstants.VERSION_1_4:
            default:
                description = J2EEVersionConstants.VERSION_1_4_TEXT;
                break;
            }
            return new DataModelPropertyDescriptor(propertyValue, description);
        }
        return super.getPropertyDescriptor(propertyName);
    }

    public DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        DataModelPropertyDescriptor[] descriptors = null;
        switch (highestJ2EEPref) {
        case J2EEVersionConstants.J2EE_1_2_ID:
            descriptors = new DataModelPropertyDescriptor[1];
            descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
            break;
        case J2EEVersionConstants.J2EE_1_3_ID:
            descriptors = new DataModelPropertyDescriptor[2];
            descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
            descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
            break;
        case J2EEVersionConstants.J2EE_1_4_ID:
        default:
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
     */
    protected EClass getComponentType() {
        return CommonarchiveFactoryImpl.getPackage().getEARFile();
    }

    protected String getComponentExtension() {
        return ".ear"; //$NON-NLS-1$
    }

    public IStatus validate(String propertyName) {
        if (propertyName.equals(PROJECT_NAME)) {
            // validate server target
			IStatus stat = super.validate(propertyName);
			if( stat.isOK()){
	            String projectName = getDataModel().getStringProperty(PROJECT_NAME);
	            if (projectName != null && projectName.length() != 0) {
	                IProject project = ProjectUtilities.getProject(projectName);
	                if (project != null) {
	                    IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
	                    if (runtime != null) {
	                        IRuntimeType type = runtime.getRuntimeType();
	                        String typeId = type.getId();
	                        if (typeId.startsWith("org.eclipse.jst.server.tomcat")) { //$NON-NLS-1$
	                            String msg = EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR;
	                            return WTPCommonPlugin.createErrorStatus(msg);
	                        }
	                    }
	                }
	            }
			}
        }else if(propertyName.equals(J2EE_PROJECTS_LIST)){
			return validateTargetComponentVersion((List)model.getProperty(J2EE_PROJECTS_LIST));
        }
        return super.validate(propertyName);
    }

	private IStatus validateTargetComponentVersion(List list) {

		Integer version = (Integer) model.getProperty(COMPONENT_VERSION);
		int earVersion = version.intValue();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			IProject handle = (IProject)iter.next();
			//IVirtualComponent comp = (IVirtualComponent) iter.next();
			IVirtualComponent comp = ComponentCore.createComponent(handle.getProject());
			int compVersion = J2EEVersionUtil.convertVersionStringToInt(comp);
			if (earVersion < compVersion) {
				String errorStatus = "The Module specification level of "+handle.getName()+", is incompatible with the containing EAR version"; //$NON-NLS-1$
				return J2EEPlugin.newErrorStatus(errorStatus, null);
			}
			
		}
		return OK_STATUS;
	}
	
	
    public boolean propertySet(String propertyName, Object propertyValue) {
        boolean returnValue = super.propertySet(propertyName, propertyValue);
        if (propertyName.equals(COMPONENT_NAME)) {
            setProperty(COMPONENT_DEPLOY_NAME, getDataModel().getStringProperty(COMPONENT_NAME));
        } else if(propertyName.equals(NESTED_EAR_COMPONENT_CREATION_DM)){
        	throw new RuntimeException();
        }
        return returnValue;
    }
	
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName){
		return super.getValidPropertyDescriptors(propertyName);
	}	
	
	protected boolean isCreatingEarComponent() {
		return true;
	}

	protected IProject getEARProject(){
		String earProjname = (String) model.getProperty(COMPONENT_NAME);
		
		IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);	
		earDM.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, earProjname);
		
		if( earProjname != null && !earProjname.equals("") && validate(COMPONENT_NAME).isOK())
			return ProjectUtilities.getProject(earProjname);
		else
			return null;
		
	}
	
	protected String getJ2EEProjectType() {
		return J2EEProjectUtilities.ENTERPRISE_APPLICATION;
	}

}
