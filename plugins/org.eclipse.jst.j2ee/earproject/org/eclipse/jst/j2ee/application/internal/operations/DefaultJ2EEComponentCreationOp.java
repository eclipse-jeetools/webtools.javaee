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
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.IDefaultJ2EEComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.activities.WTPActivityBridge;

public class DefaultJ2EEComponentCreationOp extends AbstractDataModelOperation implements IDefaultJ2EEComponentCreationDataModelProperties {

    public static final String JCA_DEV_ACTIVITY_ID = "com.ibm.wtp.jca.development"; //$NON-NLS-1$

    public static final String WEB_DEV_ACTIVITY_ID = "com.ibm.wtp.web.development"; //$NON-NLS-1$

    public static final String ENTERPRISE_JAVA = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$

    public DefaultJ2EEComponentCreationOp(IDataModel model) {
        super(model);
    }

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        try {
            IDataModel model = getDataModel();
            if (model.getBooleanProperty(CREATE_EJB)) {
                IDataModel ejbModel = model.getNestedModel(NESTED_MODEL_EJB);
                ejbModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, model.getStringProperty(EJB_COMPONENT_NAME));
                ejbModel.setProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME, model.getStringProperty(EJB_COMPONENT_NAME));
                ejbModel.setProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, Boolean.FALSE);
                createEJBComponent(ejbModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_WEB)) {
                IDataModel webModel = model.getNestedModel(NESTED_MODEL_WEB);
                webModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, model.getStringProperty(WEB_COMPONENT_NAME));
                webModel.setProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME, model.getStringProperty(WEB_COMPONENT_NAME));
                webModel.setProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, Boolean.FALSE);
                createWebJ2EEComponent(webModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_CONNECTOR)) {
                IDataModel conModel = model.getNestedModel(NESTED_MODEL_JCA);
                conModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, model.getStringProperty(CONNECTOR_COMPONENT_NAME));
                conModel.setProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME, model.getStringProperty(CONNECTOR_COMPONENT_NAME));
                conModel.setProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, Boolean.FALSE);
                createRarJ2EEComponent(conModel, monitor);
            }
            if (model.getBooleanProperty(CREATE_APPCLIENT)) {
                IDataModel clientModel = model.getNestedModel(NESTED_MODEL_CLIENT);
                clientModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, model.getStringProperty(APPCLIENT_COMPONENT_NAME));
                clientModel.setProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME, model.getStringProperty(APPCLIENT_COMPONENT_NAME));
                clientModel.setProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, Boolean.FALSE);
                createAppClientComponent(clientModel, monitor);
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
