/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.jca.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorEditModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JCAProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier#verifyProjectCreationDataModel(com.ibm.etools.application.operations.J2EEProjectCreationDataModel)
     */
    public void verifyProjectCreationDataModel(J2EEModuleCreationDataModelOld model) {
        ConnectorModuleCreationDataModel dataModel = (ConnectorModuleCreationDataModel) model;
        ProjectUtility.verifyProject(dataModel.getTargetProject().getName(), true);
        ConnectorEditModel editModel = null;
        Object key = new Object();
        try {
        	ConnectorNatureRuntime cRuntime = ConnectorNatureRuntime.getRuntime(dataModel.getTargetProject());
            //EMFWorkbenchContext emfWorkbenchContext = WorkbenchResourceHelper.createEMFContext(dataModel.getTargetProject(), null);
            editModel = (ConnectorEditModel) cRuntime.getEditModelForRead(dataModel.getStringProperty(EditModelOperationDataModel.EDIT_MODEL_ID), key);
            XMLResource dd = editModel.getDeploymentDescriptorResource();
            Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
            editModel.releaseAccess(key);
        }
        if (dataModel.getBooleanProperty(ConnectorModuleCreationDataModel.ADD_TO_EAR)) {
            IProject earProject = dataModel.getApplicationCreationDataModel().getTargetProject();
            EAREditModel ear = null;
            try {
                Assert.assertTrue("EAR doesn't exist:", earProject.exists());
                EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
                ear = (EAREditModel) runtime.getEditModelForRead(IEARNatureConstants.EDIT_MODEL_ID, key);
                ear.getModuleMapping(dataModel.getTargetProject());
                //TODO
            } finally {
                ear.releaseAccess(key);
            }
        }
    }

}
