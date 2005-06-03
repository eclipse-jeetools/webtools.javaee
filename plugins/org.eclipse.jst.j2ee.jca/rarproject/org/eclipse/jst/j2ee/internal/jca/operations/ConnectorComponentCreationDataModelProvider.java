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
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class ConnectorComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IConnectorComponentCreationDataModelProperties{

    public ConnectorComponentCreationDataModelProvider() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel#getDefaultJ2EEModuleVersion()
     */
    protected Integer getDefaultComponentVersion() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        switch (highestJ2EEPref) {
            case (J2EEVersionConstants.J2EE_1_4_ID) :
                return new Integer(J2EEVersionConstants.JCA_1_5_ID);
            case (J2EEVersionConstants.J2EE_1_3_ID) :
                return new Integer(J2EEVersionConstants.JCA_1_0_ID);
            case (J2EEVersionConstants.J2EE_1_2_ID) :
                return null;
            default :
                return new Integer(J2EEVersionConstants.JCA_1_5_ID);
        }
    }

    protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        DataModelPropertyDescriptor[] descriptors = null;
        switch (highestJ2EEPref) {
            case J2EEVersionConstants.J2EE_1_3_ID :
                descriptors = new DataModelPropertyDescriptor[1];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
                break;
            case J2EEVersionConstants.J2EE_1_4_ID :
            default :
                descriptors = new DataModelPropertyDescriptor[2];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
                descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_5_ID), J2EEVersionConstants.VERSION_1_5_TEXT);
                break;
        }
        return descriptors;
    }
    
    protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
        switch (moduleVersion) {
            case J2EEVersionConstants.JCA_1_0_ID :
                return J2EEVersionConstants.J2EE_1_3_ID;
            case J2EEVersionConstants.JCA_1_5_ID :
                return J2EEVersionConstants.J2EE_1_4_ID;
        }
        return 0;
    }
    
    protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
        switch (j2eeVersion.intValue()) {
            case J2EEVersionConstants.J2EE_1_3_ID :
                return new Integer(J2EEVersionConstants.JCA_1_0_ID);
            case J2EEVersionConstants.J2EE_1_4_ID :
                return new Integer(J2EEVersionConstants.JCA_1_5_ID);
        }
        return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
    }
    
    protected EClass getComponentType() {
        return CommonarchiveFactoryImpl.getPackage().getRARFile();
    }

    protected String getComponentExtension() {
        return ".rar"; //$NON-NLS-1$
    }

    public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
        if (propertyName.equals(COMPONENT_VERSION)) {
            Integer propertyValue = (Integer) getProperty(propertyName);
            String description = null;
            switch (propertyValue.intValue()) {
                case J2EEVersionConstants.JCA_1_0_ID :
                    description = J2EEVersionConstants.VERSION_1_0_TEXT;
                    break;
                case J2EEVersionConstants.JCA_1_5_ID :
                default :
                    description = J2EEVersionConstants.VERSION_1_5_TEXT;
                    break;
            }
            return new DataModelPropertyDescriptor(propertyValue, description);
        }
        return super.getPropertyDescriptor(propertyName);
    }


    public IDataModelOperation getDefaultOperation() {
        return new ConnectorComponentCreationOperation(model);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_CONNECTOR_MODULE;
    }
    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(MANIFEST_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "connectorModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
        } else if (propertyName.equals(ADD_TO_EAR)) {
            return Boolean.TRUE;
        } 
        if (propertyName.equals(DD_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "connectorModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
        }       
        if (propertyName.equals(JAVASOURCE_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "connectorModule";
        }       

        return super.getDefaultProperty(propertyName);
    }    

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName){
		return super.getValidPropertyDescriptors(propertyName);
	}	
}
