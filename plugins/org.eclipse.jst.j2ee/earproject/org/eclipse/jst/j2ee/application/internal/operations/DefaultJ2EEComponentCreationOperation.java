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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.IDefaultJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.activities.WTPActivityBridge;

public class DefaultJ2EEComponentCreationOperation extends AbstractDataModelOperation implements IDefaultJ2EEComponentCreationDataModelProperties {

    public static final String JCA_DEV_ACTIVITY_ID = "com.ibm.wtp.jca.development"; //$NON-NLS-1$

    public static final String WEB_DEV_ACTIVITY_ID = "com.ibm.wtp.web.development"; //$NON-NLS-1$

    public static final String ENTERPRISE_JAVA = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$

    public DefaultJ2EEComponentCreationOperation(IDataModel model) {
        super(model);
    }

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        try {
            IDataModel model = getDataModel();
            if (model.getBooleanProperty(CREATE_EJB)) {
                IDataModel projectModel = model.getNestedModel(NESTED_MODEL_EJB);
                
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(EJB_COMPONENT_NAME));
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, model.getProperty(FACET_RUNTIME));
                
                FacetDataModelMap map = (FacetDataModelMap) projectModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
                IDataModel ejbModel = map.getFacetDataModel(J2EEProjectUtilities.EJB );
                int nVer = model.getIntProperty(J2EE_VERSION);
                ejbModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, J2EEVersionUtil.getEJBTextVersion(J2EEVersionUtil.convertJ2EEVersionIDToEJBVersionID(nVer)));
                ejbModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
                
                createEJBComponent(projectModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_WEB)) {
                IDataModel projectModel = model.getNestedModel(NESTED_MODEL_WEB);
                
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(WEB_COMPONENT_NAME));
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, model.getProperty(FACET_RUNTIME));
                
                FacetDataModelMap map = (FacetDataModelMap) projectModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
                IDataModel webtModel = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB );
                int nVer = model.getIntProperty(J2EE_VERSION);
                webtModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, J2EEVersionUtil.getServletTextVersion(J2EEVersionUtil.convertJ2EEVersionIDToWebVersionID(nVer)));
                webtModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
                
                createWebJ2EEComponent(projectModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_CONNECTOR)) {
                IDataModel projectModel = model.getNestedModel(NESTED_MODEL_JCA);

                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(CONNECTOR_COMPONENT_NAME));
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, model.getProperty(FACET_RUNTIME));
                
                FacetDataModelMap map = (FacetDataModelMap) projectModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
                IDataModel conModel = map.getFacetDataModel(J2EEProjectUtilities.JCA );
                int nVer = model.getIntProperty(J2EE_VERSION);
                conModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, J2EEVersionUtil.getJCATextVersion(J2EEVersionUtil.convertJ2EEVersionIDToConnectorVersionID(nVer)));
                conModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
                
                createRarJ2EEComponent(projectModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_APPCLIENT)) {
                IDataModel projectModel = model.getNestedModel(NESTED_MODEL_CLIENT);
                
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(APPCLIENT_COMPONENT_NAME));
                projectModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, model.getProperty(FACET_RUNTIME));
                
                FacetDataModelMap map = (FacetDataModelMap) projectModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
                IDataModel clientModel = map.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT);
                int nVer = model.getIntProperty(J2EE_VERSION);
                clientModel.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, J2EEVersionUtil.getJ2EETextVersion(nVer));
                clientModel.setBooleanProperty(IAppClientFacetInstallDataModelProperties.ADD_TO_EAR, false);

                createAppClientComponent(projectModel, monitor);
            }
        } catch (Exception e) {
            Logger.getLogger().log(e.getMessage());
        }
        return OK_STATUS;
    }

    /**
     * @param model
     */
    private void createEJBComponent(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException, ExecutionException {
        model.getDefaultOperation().execute(monitor, null);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
    }

    /**
     * @param model
     */
    private void createWebJ2EEComponent(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException, ExecutionException {
        model.getDefaultOperation().execute(monitor, null);
        WTPActivityBridge.getInstance().enableActivity(WEB_DEV_ACTIVITY_ID, true);
    }

    /**
     * @param model
     */
    private void createRarJ2EEComponent(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException, ExecutionException {
        model.getDefaultOperation().execute(monitor, null);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
    }

    /**
     * @param model
     */
    private void createAppClientComponent(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException, ExecutionException {
        model.getDefaultOperation().execute(monitor, null);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);

    }

    public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        return null;
    }

    public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        return null;
    }

}
