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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebComponentToEARDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.datamodel.properties.IAddWebComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class WebComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IWebComponentCreationDataModelProperties {

    public WebComponentCreationDataModelProvider() {
        super();
    }

    public IDataModelOperation getDefaultOperation() {
        return new WebComponentCreationOperation(model);
    }
    
    public String[] getPropertyNames() {
        String[] props = new String[]{USE_ANNOTATIONS, CONTEXT_ROOT};
        return combineProperties(super.getPropertyNames(), props);
    }
    /**
     * @return Returns the default J2EE spec level based on the Global J2EE Preference
     */
    protected Integer getDefaultComponentVersion() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        switch (highestJ2EEPref) {
            case (J2EEVersionConstants.J2EE_1_4_ID) :
                return new Integer(J2EEVersionConstants.WEB_2_4_ID);
            case (J2EEVersionConstants.J2EE_1_3_ID) :
                return new Integer(J2EEVersionConstants.WEB_2_3_ID);
            case (J2EEVersionConstants.J2EE_1_2_ID) :
                return new Integer(J2EEVersionConstants.WEB_2_2_ID);
            default :
                return new Integer(J2EEVersionConstants.WEB_2_4_ID);
        }
    }


    public void init() {
        super.init();
        //setJ2EENatureID(IWebNatureConstants.J2EE_NATURE_ID);
        //setProperty(EDIT_MODEL_ID, IWebNatureConstants.EDIT_MODEL_ID);
        //getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{IModuleConstants.MODULE_NATURE_ID});
        //getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS, new String[]{getDefaultJavaSourceFolderName()});
		
        IDataModel dm = DataModelFactory.createDataModel(new AddWebComponentToEARDataModelProvider());
		model.setProperty(NESTED_ADD_COMPONENT_TO_EAR_DM, dm);

        updateOutputLocation();
    }

    public boolean propertySet(String propertyName, Object propertyValue) {
        boolean retVal = super.propertySet(propertyName, propertyValue);
//		if( propertyName.equals(COMPONENT_NAME)){
//	        IDataModel addtoEAR = (IDataModel) model.getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
//			addtoEAR.setProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, propertyValue);
//			
//		}else 
		if (propertyName.equals(USE_ANNOTATIONS)) {
            model.notifyPropertyChange(COMPONENT_VERSION, DataModelEvent.ENABLE_CHG);
        }
//		else if (propertyName.equals(COMPONENT_VERSION)) {
//            if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
//                setProperty(USE_ANNOTATIONS, Boolean.FALSE);
//            model.notifyPropertyChange(USE_ANNOTATIONS, DataModelEvent.ENABLE_CHG);
//        }
		else if (propertyName.equals(CONTEXT_ROOT)) {
            getAddComponentToEARDataModel().setProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, propertyValue);
        } else if (propertyName.equals(COMPONENT_NAME)) {
            if (!isPropertySet(CONTEXT_ROOT)) {
                model.notifyPropertyChange(CONTEXT_ROOT, DataModelEvent.VALUE_CHG);
                //((AddWebModuleToEARDataModel) getAddComponentToEARDataModel()).defaultContextRoot=(String)propertyValue;
				getAddComponentToEARDataModel().setProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, propertyValue );
                getAddComponentToEARDataModel().notifyPropertyChange(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT, IDataModel.DEFAULT_CHG);
            }
        }
        return retVal;
    }

    private void updateOutputLocation() {
//      getJavaProjectCreationDataModel().setProperty(JavaProjectCreationDataModel.OUTPUT_LOCATION, getOutputLocation());
    }

//  private Object getOutputLocation() {
//      StringBuffer buf = new StringBuffer(getStringProperty(WEB_CONTENT));
//      buf.append(IPath.SEPARATOR);
//      buf.append(IWebNatureConstants.INFO_DIRECTORY);
//      buf.append(IPath.SEPARATOR);
//      buf.append(IWebNatureConstants.CLASSES_DIRECTORY);
//      return buf.toString();
//  }

    private Object updateAddToEar() {
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
        return null;
    }

    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(ADD_TO_EAR)) {
            return updateAddToEar();
        }
//      if (propertyName.equals(WEB_CONTENT)) {
//          String webContentFolderPref = J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName();
//          if (webContentFolderPref == null || webContentFolderPref.length() == 0)
//              webContentFolderPref = IWebNatureConstants.WEB_MODULE_DIRECTORY_;
//          return webContentFolderPref;
//      }
        if (propertyName.equals(CONTEXT_ROOT)) {
            return getProperty(COMPONENT_NAME);
        }
		//To do: after porting
//        if (propertyName.equals(SERVLET_VERSION)) {
//            int moduleVersion = getIntProperty(COMPONENT_VERSION);
//            int servletVersion = J2EEVersionConstants.SERVLET_2_2;
//            switch (moduleVersion) {
//                case J2EEVersionConstants.WEB_2_2_ID :
//                    servletVersion = J2EEVersionConstants.SERVLET_2_2;
//                    break;
//                case J2EEVersionConstants.WEB_2_3_ID :
//                case J2EEVersionConstants.WEB_2_4_ID :
//                    servletVersion = J2EEVersionConstants.SERVLET_2_3;
//                    break;
//            }
//            return new Integer(servletVersion);
//        }
//        if (propertyName.equals(JSP_VERSION)) {
//            int moduleVersion = getIntProperty(COMPONENT_VERSION);
//            int jspVersion = J2EEVersionConstants.JSP_1_2_ID;
//            switch (moduleVersion) {
//                case J2EEVersionConstants.WEB_2_2_ID :
//                    jspVersion = J2EEVersionConstants.JSP_1_2_ID;
//                    break;
//                case J2EEVersionConstants.WEB_2_3_ID :
//                case J2EEVersionConstants.WEB_2_4_ID :
//                    jspVersion = J2EEVersionConstants.JSP_2_0_ID;
//                    break;
//            }
//            return new Integer(jspVersion);
//        }
        if (propertyName.equals(DD_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "WebContent"+IPath.SEPARATOR + J2EEConstants.WEB_INF;
        }       
        if (propertyName.equals(JAVASOURCE_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "JavaSource";
        }       
        if (propertyName.equals(MANIFEST_FOLDER)) {
            return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "WebContent"+IPath.SEPARATOR + J2EEConstants.META_INF;
        }           
        return super.getDefaultProperty(propertyName);
    }

    public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
        if (propertyName.equals(COMPONENT_VERSION)) {
            Integer propertyValue = (Integer) getProperty(propertyName);
            String description = null;
            switch (propertyValue.intValue()) {
                case J2EEVersionConstants.WEB_2_2_ID :
                    description = J2EEVersionConstants.VERSION_2_2_TEXT;
                    break;
                case J2EEVersionConstants.WEB_2_3_ID :
                    description = J2EEVersionConstants.VERSION_2_3_TEXT;
                    break;
                case J2EEVersionConstants.WEB_2_4_ID :
                default :
                    description = J2EEVersionConstants.VERSION_2_4_TEXT;
                    break;
            }
            return new DataModelPropertyDescriptor(propertyValue, description);
        }
        return super.getPropertyDescriptor(propertyName);
    }

    protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
        int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
        DataModelPropertyDescriptor[] descriptors = null;
        switch (highestJ2EEPref) {
            case J2EEVersionConstants.J2EE_1_2_ID :
                descriptors = new DataModelPropertyDescriptor[1];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
                break;
            case J2EEVersionConstants.J2EE_1_3_ID :
                descriptors = new DataModelPropertyDescriptor[2];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
                descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
                break;
            case J2EEVersionConstants.J2EE_1_4_ID :
            default :
                descriptors = new DataModelPropertyDescriptor[3];
                descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_2_ID), J2EEVersionConstants.VERSION_2_2_TEXT);
                descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_3_ID), J2EEVersionConstants.VERSION_2_3_TEXT);
                descriptors[2] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.WEB_2_4_ID), J2EEVersionConstants.VERSION_2_4_TEXT);
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
        return CommonarchiveFactoryImpl.getPackage().getWARFile();
    }

    protected String getComponentExtension() {
        return ".war"; //$NON-NLS-1$
    }

    public boolean isPropertyEnabled(String propertyName) {
        if (USE_ANNOTATIONS.equals(propertyName)) {
            if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
                return false;
            return true;
        }
        return super.isPropertyEnabled(propertyName);
    }

    public IStatus validate(String propertyName) {
        if (propertyName.equals(CONTEXT_ROOT)) {
            if (getBooleanProperty(ADD_TO_EAR)) {
//                //return getAddComponentToEARDataModel().validateProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT);
				return getAddComponentToEARDataModel().validateProperty(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT);
            }
            return OK_STATUS;

        }
        return super.validate(propertyName);
    }

    public void propertyChanged(DataModelEvent event) {
        super.propertyChanged(event);
        if (event.getDataModel() == getAddComponentToEARDataModel() && event.getPropertyName().equals(IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT) && event.getDataModel().isPropertySet(AddWebModuleToEARDataModel.CONTEXT_ROOT)) {
            setProperty(CONTEXT_ROOT, event.getProperty());
        } 
		//else if (event.getDataModel() == getServerTargetDataModel() && event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID) && event.getDataModel().isSet(ServerTargetDataModel.RUNTIME_TARGET_ID))
            //setProperty(ADD_TO_EAR, updateAddToEar());
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_WEB_MODULE;
    }
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName){
		return super.getValidPropertyDescriptors(propertyName);
	}	

}
