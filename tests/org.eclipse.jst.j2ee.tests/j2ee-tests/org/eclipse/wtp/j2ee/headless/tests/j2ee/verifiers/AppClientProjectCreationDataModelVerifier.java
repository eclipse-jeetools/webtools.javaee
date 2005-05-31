/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import junit.framework.Assert;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

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
	public void verifyProjectCreationDataModel(IDataModel model) {
        Object key = new Object();
		AppClientArtifactEdit appClientEdit = null;
		
        try {
			ComponentHandle handle = ComponentHandle.create(ProjectUtilities.getProject(model.getStringProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME)),model.getStringProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_NAME));
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
