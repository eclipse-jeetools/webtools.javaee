/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientEditModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AppClientProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
 /* (non-Javadoc)
 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.J2EEProjectCreationDataModelVerifier#verifyProjectCreationDataModel(com.ibm.etools.application.operations.J2EEProjectCreationDataModel)
 */
public void verifyProjectCreationDataModel(J2EEComponentCreationDataModel model) {
    	AppClientComponentCreationDataModel dataModel = (AppClientComponentCreationDataModel)model;
        ProjectUtility.verifyProject(dataModel.getTargetProject().getName(), true);
        AppClientEditModel editModel = null;
        Object key = new Object();
        try {
        	ApplicationClientNatureRuntime aRuntime = ApplicationClientNatureRuntime.getRuntime(dataModel.getTargetProject());
            //EMFWorkbenchContext emfWorkbenchContext = WorkbenchResourceHelper.createEMFContext(dataModel.getTargetProject(), null);
            editModel = (AppClientEditModel)aRuntime.getEditModelForRead(dataModel.getStringProperty(EditModelOperationDataModel.EDIT_MODEL_ID), key);
            XMLResource dd = editModel.getDeploymentDescriptorResource();
            Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
            editModel.releaseAccess(key);
        }
        if (dataModel.getBooleanProperty(AppClientComponentCreationDataModel.ADD_TO_EAR)) {
            IProject earProject = dataModel.getEarComponentCreationDataModel().getTargetProject();
            EAREditModel ear = null;
            try {
                Assert.assertTrue("EAR doesn't exist:", earProject.exists());
                EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
                //EMFWorkbenchContext emfWorkbenchContext = WorkbenchResourceHelper.createEMFContext(earProject, null);
                ear = (EAREditModel)runtime.getEditModelForRead(IEARNatureConstants.EDIT_MODEL_ID, key);
                ear.getModuleMapping(dataModel.getTargetProject());
                //TODO
            } finally {
                ear.releaseAccess(key);
            }
        }
	}
}
