/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.verifiers;

import junit.framework.Assert;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
    
    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier#verifyProjectCreationDataModel(com.ibm.etools.application.operations.J2EEProjectCreationDataModel)
     */
    public void verifyProjectCreationDataModel(IDataModel model) {
        Object key = new Object();
		WebArtifactEdit webEdit = null;
		
        try {
            ComponentHandle handle = ComponentHandle.create(ProjectUtilities.getProject(model.getStringProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME)), model.getStringProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME));
            Object dd = null;
			webEdit = (WebArtifactEdit) WebArtifactEdit.getWebArtifactEditForRead(handle);
       		if(webEdit != null) 
       			dd = (WebApp) webEdit.getDeploymentDescriptorRoot();
			Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
			if( webEdit != null )
				webEdit.dispose();
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
