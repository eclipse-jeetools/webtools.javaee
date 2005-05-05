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

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEJBClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EjbComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IEjbComponentCreationDataModelProperties {

    public EjbComponentCreationDataModelProvider() {
        super();
    }
    public void init() {
        super.init();
        IDataModel ejbClientComponentDataModel = DataModelFactory.createDataModel(new EJBClientComponentDataModelProvider());
		//to do : after porting
        //ejbClientComponentDataModel.setProperty(IEJBClientComponentCreationDataModelProperties.EAR_COMPONENT_HANDLE, getEarComponentHandle());
        model.setProperty(NESTED_MODEL_EJB_CLIENT_CREATION, ejbClientComponentDataModel);
    }
    
    public IDataModelOperation getDefaultOperation() {
        return new EjbComponentCreationOp(model);
    }
    
    public String[] getPropertyNames() {
        String[] props = new String[]{CREATE_CLIENT, CREATE_DEFAULT_SESSION_BEAN, NESTED_MODEL_EJB_CLIENT_CREATION};
        return combineProperties(super.getPropertyNames(), props);
    }
    /**
     * @return Returns the default J2EE spec level based on the Global J2EE Preference
     */
    protected Integer getDefaultComponentVersion() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        switch (highestJ2EEPref) {
            case (J2EEVersionConstants.J2EE_1_4_ID) :
                return new Integer(J2EEVersionConstants.EJB_2_1_ID);
            case (J2EEVersionConstants.J2EE_1_3_ID) :
                return new Integer(J2EEVersionConstants.EJB_2_0_ID);
            case (J2EEVersionConstants.J2EE_1_2_ID) :
                return new Integer(J2EEVersionConstants.EJB_1_1_ID);
            default :
                return new Integer(J2EEVersionConstants.EJB_2_1_ID);
        }
    }

    public boolean propertySet(String propertyName, Object propertyValue) {
        boolean doSet = super.propertySet(propertyName, propertyValue);
        if (propertyName.equals(ADD_TO_EAR)) {
            if (!((Boolean) propertyValue).booleanValue()) {
                model.setProperty(CREATE_CLIENT, propertyValue);
            }
            model.notifyPropertyChange(CREATE_CLIENT, DataModelEvent.ENABLE_CHG);
        } else if (propertyName.equals(USE_ANNOTATIONS)) {
            model.notifyPropertyChange(COMPONENT_VERSION, DataModelEvent.ENABLE_CHG);
        } else if (propertyName.equals(COMPONENT_VERSION)) {
            if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
                model.setProperty(USE_ANNOTATIONS, Boolean.FALSE);
            model.notifyPropertyChange(USE_ANNOTATIONS, DataModelEvent.ENABLE_CHG);
        } 
        //else if (propertyName.equals(CREATE_CLIENT)) {
            //TODO: rework validation hooks
            //getNestedEJBClientComponentDataModel().setProperty(EJBClientComponentDataModel.CREATE_PROJECT, propertyValue);
//            if (getBooleanProperty(CREATE_CLIENT)) {
//                ((IDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION)).notifyPropertyChange();
//            } else {
//                ((IDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION)).disableValidation();
//            }
    //    } 
    else if (propertyName.equals(COMPONENT_NAME)) {
            IDataModel ejbClientComponentDataModel = (IDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION);
            ejbClientComponentDataModel.setProperty(IEJBClientComponentCreationDataModelProperties.EJB_COMPONENT_NAME, propertyValue);
            if (!ejbClientComponentDataModel.isPropertySet(COMPONENT_NAME))
                ejbClientComponentDataModel.notifyPropertyChange(COMPONENT_NAME, IDataModel.DEFAULT_CHG);
            if (!ejbClientComponentDataModel.isPropertySet(IEJBClientComponentCreationDataModelProperties.CLIENT_COMPONENT_URI))
                ejbClientComponentDataModel.notifyPropertyChange(IEJBClientComponentCreationDataModelProperties.CLIENT_COMPONENT_URI, IDataModel.DEFAULT_CHG);

            if( getBooleanProperty(CREATE_CLIENT)){
                ejbClientComponentDataModel.setProperty(IEJBClientComponentCreationDataModelProperties.CREATE_PROJECT, getProperty(CREATE_CLIENT));
                ejbClientComponentDataModel.setProperty(PROJECT_NAME, ejbClientComponentDataModel.getStringProperty(IEJBClientComponentCreationDataModelProperties.PROJECT_NAME));
            }   
        }
        
        if (getBooleanProperty(CREATE_CLIENT)) {
            IDataModel ejbClientComponentDataModel = (IDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION);
            if (propertyName.equals(CREATE_CLIENT) || propertyName.equals(PROJECT_NAME) || propertyName.equals(ADD_TO_EAR)
                        || propertyName.equals(COMPONENT_DEPLOY_NAME)) {
                ejbClientComponentDataModel.setProperty(IEJBClientComponentCreationDataModelProperties.EJB_PROJECT_NAME, getProperty(PROJECT_NAME));
                ejbClientComponentDataModel.setProperty(IEJBClientComponentCreationDataModelProperties.EJB_COMPONENT_DEPLOY_NAME, getProperty(COMPONENT_DEPLOY_NAME));
            }
        }
        return doSet;
    }

//  private Object updateAddToEar() {
        //IRuntime type = getServerTargetDataModel().getRuntimeTarget();
//      Boolean ret = Boolean.FALSE;
//      IRuntime type = getProjectDataModel().getServerTargetDataModel().getRuntimeTarget();
//      if (type == null)
//          return Boolean.TRUE;
//      IRuntimeType rType = type.getRuntimeType();
//      if (rType == null)
//          return Boolean.TRUE;
//      return ret;
        //return new Boolean(!rType.getVendor().equals(APACHE_VENDER_NAME));
//      return null;
//  }

    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(CREATE_CLIENT)) {
            return Boolean.TRUE;
        } else if (propertyName.equals(ADD_TO_EAR)) {
            return Boolean.TRUE;
        } else if (propertyName.equals(CREATE_DEFAULT_SESSION_BEAN)) {
            return Boolean.FALSE;
        } else if (propertyName.equals(DD_FOLDER)) {
            return IPath.SEPARATOR + getModuleName()+IPath.SEPARATOR + "ejbModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
        }else if (propertyName.equals(JAVASOURCE_FOLDER)) {
            return IPath.SEPARATOR + getModuleName()+IPath.SEPARATOR + "ejbModule";
        } else if (propertyName.equals(MANIFEST_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "ejbModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
        } else {
            return super.getDefaultProperty(propertyName);
        }   
    }

    public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
        if (propertyName.equals(COMPONENT_VERSION)) {
            Integer propertyValue = (Integer) getProperty(propertyName);
            String description = null;
            switch (propertyValue.intValue()) {
                case J2EEVersionConstants.EJB_1_1_ID :
                    description = J2EEVersionConstants.VERSION_1_1_TEXT;
                    break;
                case J2EEVersionConstants.EJB_2_0_ID :
                    description = J2EEVersionConstants.VERSION_2_0_TEXT;
                    break;
                case J2EEVersionConstants.EJB_2_1_ID :
                default :
                    description = J2EEVersionConstants.VERSION_2_1_TEXT;
                    break;
            }
            return new DataModelPropertyDescriptor(propertyValue, description);
        }
        return super.getPropertyDescriptor(propertyName);
    }
    
    public IStatus validate(String propertyName) {
        if (propertyName.equals(NESTED_MODEL_EJB_CLIENT_CREATION)) {
            if (getBooleanProperty(CREATE_CLIENT)) {
                EJBClientComponentDataModel ejbClientComponentDataModel = ((EJBClientComponentDataModel)model.getProperty(NESTED_MODEL_EJB_CLIENT_CREATION));              
                String clientName = ejbClientComponentDataModel.getStringProperty(ComponentCreationDataModel.COMPONENT_NAME);
                String moduleName = getStringProperty(PROJECT_NAME);
                if (clientName.equals(moduleName)) {
                    return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
                }
                if (!CoreFileSystemLibrary.isCaseSensitive()) {
                    if (clientName.toLowerCase().equals(moduleName.toLowerCase())) {
                        return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB));
                    }
                }

                if (getBooleanProperty(ADD_TO_EAR)) {
                    String earName = getStringProperty(EAR_COMPONENT_NAME);
                    if (clientName.equals(earName)) {
                        return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EAR));
                    }
                    if (!CoreFileSystemLibrary.isCaseSensitive()) {
                        if (clientName.toLowerCase().equals(earName.toLowerCase())) {
                            return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EAR));
                        }
                    }
                }
            }
        }
        return super.validate(propertyName);
    }

    protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        DataModelPropertyDescriptor[] descriptors = null;
        switch (highestJ2EEPref) {
            case J2EEVersionConstants.J2EE_1_2_ID :
                descriptors = new DataModelPropertyDescriptor[1];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
                break;
            case J2EEVersionConstants.J2EE_1_3_ID :
                descriptors = new DataModelPropertyDescriptor[2];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
                descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_0_ID), J2EEVersionConstants.VERSION_2_0_TEXT);
                break;
            case J2EEVersionConstants.J2EE_1_4_ID :
            default :
                descriptors = new DataModelPropertyDescriptor[3];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_1_1_ID), J2EEVersionConstants.VERSION_1_1_TEXT);
                descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_0_ID), J2EEVersionConstants.VERSION_2_0_TEXT);
                descriptors[2] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.EJB_2_1_ID), J2EEVersionConstants.VERSION_2_1_TEXT);
                break;
        }
        return descriptors;
    }

    protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
        switch (moduleVersion) {
            case J2EEVersionConstants.WEB_2_2_ID :
                return J2EEVersionConstants.J2EE_1_2_ID;
            case J2EEVersionConstants.WEB_2_3_ID :
                return J2EEVersionConstants.J2EE_1_3_ID;
            case J2EEVersionConstants.WEB_2_4_ID :
                return J2EEVersionConstants.J2EE_1_4_ID;
        }
        return -1;
    }

    protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
        switch (j2eeVersion.intValue()) {
            case J2EEVersionConstants.J2EE_1_2_ID :
                return new Integer(J2EEVersionConstants.WEB_2_2_ID);
            case J2EEVersionConstants.J2EE_1_3_ID :
                return new Integer(J2EEVersionConstants.WEB_2_3_ID);
            case J2EEVersionConstants.J2EE_1_4_ID :
                return new Integer(J2EEVersionConstants.WEB_2_4_ID);
        }
        return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
     */
    protected EClass getComponentType() {
        return CommonarchiveFactoryImpl.getPackage().getEJBJarFile();
    }

    protected String getComponentExtension() {
        return ".jar"; //$NON-NLS-1$
    }

    public boolean isPropertyEnabled(String propertyName) {
        if (USE_ANNOTATIONS.equals(propertyName)) {
            if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
                return false;
            return true;
        }
        return super.isPropertyEnabled(propertyName);
    }
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_EJB_MODULE;
    }

}
