/*
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.earcreation.DefaultJ2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleExtension;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.internal.moduleextension.JcaModuleExtension;
import org.eclipse.wst.common.frameworks.internal.activities.WTPActivityBridge;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class DefaultJ2EEComponentCreationOperation extends WTPOperation {

    /**
     * This activity no longer exists use ENTERPRISE_JAVA instead
     * 
     * @deprecated
     */
    public static final String APP_CLIENT_DEV_ACTIVITY_ID = "org.eclipse.jst.j2ee.application.client.development"; //$NON-NLS-1$

    /**
     * This activity no longer exists use ENTERPRISE_JAVA instead
     * 
     * @deprecated
     */
    public static final String EJB_DEV_ACTIVITY_ID = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$

    /**
     * This activity no longer exists use ENTERPRISE_JAVA instead
     * 
     * @deprecated
     */
    public static final String JCA_DEV_ACTIVITY_ID = "com.ibm.wtp.jca.development"; //$NON-NLS-1$

    public static final String WEB_DEV_ACTIVITY_ID = "com.ibm.wtp.web.development"; //$NON-NLS-1$

    public static final String ENTERPRISE_JAVA = "com.ibm.wtp.ejb.development"; //$NON-NLS-1$

    /**
     * @param operationDataModel
     */
    public DefaultJ2EEComponentCreationOperation(DefaultJ2EEComponentCreationDataModel operationDataModel) {
        super(operationDataModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		DefaultJ2EEComponentCreationDataModel model = (DefaultJ2EEComponentCreationDataModel) operationDataModel;
		if (model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.CREATE_EJB)){
            J2EEComponentCreationDataModel ejbModel = model.getEjbModel();
            ejbModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.PROJECT_NAME));
            ejbModel.setProperty(J2EEComponentCreationDataModel.COMPONENT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EJB_COMPONENT_NAME));
            ejbModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            ejbModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_DEPLOY_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            ejbModel.setProperty(J2EEComponentCreationDataModel.ADD_TO_EAR,
                    Boolean.TRUE);
			createEJBComponent(ejbModel, monitor);         
        } 
        if (model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.CREATE_WEB)){
            J2EEComponentCreationDataModel webModel = model.getWebModel();
            webModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.PROJECT_NAME));
            webModel.setProperty(J2EEComponentCreationDataModel.COMPONENT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.WEB_COMPONENT_NAME));
            webModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            webModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_DEPLOY_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            webModel.setProperty(J2EEComponentCreationDataModel.ADD_TO_EAR,
                    Boolean.TRUE);
			createWebJ2EEComponent(webModel, monitor);
        }
		if (model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.CREATE_CONNECTOR)){
            J2EEComponentCreationDataModel conModel = model.getJCAModel();
            conModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.PROJECT_NAME));
            conModel.setProperty(J2EEComponentCreationDataModel.COMPONENT_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.CONNECTOR_COMPONENT_NAME));
            conModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            conModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_DEPLOY_NAME,
                    model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
            conModel.setProperty(J2EEComponentCreationDataModel.ADD_TO_EAR,
                    Boolean.TRUE);
			createRarJ2EEComponent(conModel, monitor);
        }
		if (model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.CREATE_APPCLIENT)) {
			AppClientComponentCreationDataModel clientModel = model.getClientModel();
			clientModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME,
					model.getStringProperty(DefaultJ2EEComponentCreationDataModel.PROJECT_NAME));
			clientModel.setProperty(J2EEComponentCreationDataModel.COMPONENT_NAME,
					model.getStringProperty(DefaultJ2EEComponentCreationDataModel.APPCLIENT_COMPONENT_NAME));
			clientModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME,
					model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
			clientModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_DEPLOY_NAME,
					model.getStringProperty(DefaultJ2EEComponentCreationDataModel.EAR_COMPONENT_NAME));
			clientModel.setProperty(J2EEComponentCreationDataModel.ADD_TO_EAR,
					Boolean.TRUE);
			createAppClientComponent(clientModel, monitor);
		}
	}
    /**
     * @param model
     */
    private void createEJBComponent(J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        EarModuleExtension ext = EarModuleManager.getEJBModuleExtension();
        runModuleExtensionOperation(ext, model, monitor);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
    }

    /**
     * @param model
     */
    private void createWebJ2EEComponent(J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        EarModuleExtension ext = EarModuleManager.getWebModuleExtension();
        runModuleExtensionOperation(ext, model, monitor);
        WTPActivityBridge.getInstance().enableActivity(WEB_DEV_ACTIVITY_ID, true);
    }

    /**
     * @param model
     */
    private void createRarJ2EEComponent(J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        JcaModuleExtension jcaExt = EarModuleManager.getJCAModuleExtension();
        runModuleExtensionOperation(jcaExt, model, monitor);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);
    }

    private void runModuleExtensionOperation(EarModuleExtension extension, J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
  /*      if (extension != null) {
            J2EEComponentCreationOp op = extension.createProjectCreationOperation(model);
            if (op != null)
                op.doRun(monitor);
        }*/
    }

    /**
     * @param model
     */
    private void createAppClientComponent(J2EEComponentCreationDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        AppClientComponentCreationOperation op = new AppClientComponentCreationOperation((AppClientComponentCreationDataModel) model);
        op.doRun(monitor);
        WTPActivityBridge.getInstance().enableActivity(ENTERPRISE_JAVA, true);

    }
}