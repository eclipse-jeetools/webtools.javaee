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
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientEditModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
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
        AppClientComponentCreationDataModel dataModel = (AppClientComponentCreationDataModel) model;
        Object key = new Object();
		AppClientArtifactEdit appClientEdit = null;
		
        try {
			ComponentHandle handle = ComponentHandle.create(dataModel.getProject(),dataModel.getStringProperty(WebComponentCreationDataModel.COMPONENT_NAME));
			Object dd = null;
			appClientEdit = (AppClientArtifactEdit) AppClientArtifactEdit.getAppClientArtifactEditForRead(handle);
       		if(appClientEdit != null) 
       			dd = (ApplicationClient) appClientEdit.getDeploymentDescriptorRoot();
			Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
			if( appClientEdit != null )
				appClientEdit.dispose();
		}
//        if (dataModel.getBooleanProperty(WebComponentCreationDataModel.ADD_TO_EAR)) {
//            IProject earProject = dataModel.getEarComponentCreationDataModel().getTargetProject();
//            EAREditModel ear = null;
//            try {
//                Assert.assertTrue("EAR doesn't exist:", earProject.exists());
//                EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
//                //EMFWorkbenchContext emfWorkbenchContext = WorkbenchResourceHelper.createEMFContext(earProject, null);
//                ear = (EAREditModel) runtime.getEditModelForRead(IEARNatureConstants.EDIT_MODEL_ID, key);
//                ear.getModuleMapping(dataModel.getTargetProject());
//                //TODO
//            } finally {
//                ear.releaseAccess(key);
//            }
//
//        }
    }
}
