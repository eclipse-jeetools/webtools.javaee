/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier#verifyProjectCreationDataModel(com.ibm.etools.application.operations.J2EEProjectCreationDataModel)
     */
    public void verifyProjectCreationDataModel(J2EEModuleCreationDataModelOld model) {
        EJBModuleCreationDataModel dataModel = (EJBModuleCreationDataModel)model;
        ProjectUtility.verifyProject(dataModel.getTargetProject().getName(), true);
        EJBEditModel editModel = null;
        Object key = new Object();
        EJBNatureRuntime ejbRuntime = EJBNatureRuntime.getRuntime(dataModel.getTargetProject());
        try {
            editModel = (EJBEditModel)ejbRuntime.getEditModelForRead(dataModel.getStringProperty(EditModelOperationDataModel.EDIT_MODEL_ID), key);
            XMLResource dd = editModel.getDeploymentDescriptorResource();
            Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
            editModel.releaseAccess(key);
        }
        if (dataModel.getBooleanProperty(EJBModuleCreationDataModel.ADD_TO_EAR)) {
            EARNatureRuntime[] earRuntimes = ejbRuntime.getReferencingEARProjects();
            IProject earProject = earRuntimes[0].getProject();
            EAREditModel earEditModel = null;
            try {
                Assert.assertTrue("EAR doesn't exist:", earProject.exists());
                EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
                earEditModel = (EAREditModel) earRuntimes[0].getEarEditModelForRead(key);
                earEditModel.getModuleMapping(dataModel.getTargetProject());
                //TODO
            } finally {
            if(earEditModel != null)
            	earEditModel.releaseAccess(key);
            }
        }
    }

}
