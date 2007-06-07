/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers;

import junit.framework.Assert;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EJBProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier#verifyProjectCreationDataModel(com.ibm.etools.application.operations.J2EEProjectCreationDataModel)
     */
    public void verifyProjectCreationDataModel(IDataModel model) {
        Object key = new Object();
        EnterpriseArtifactEdit ejbEdit = null;

        try {
            Object dd = null;
            ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(ProjectUtilities.getProject(model.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME)));
            if (ejbEdit != null)
                dd = (EJBJar) ejbEdit.getDeploymentDescriptorRoot();
            Assert.assertNotNull("Deployment Descriptor Null", dd);
        } finally {
            if (ejbEdit != null)
                ejbEdit.dispose();
        }
        // if
        // (dataModel.getBooleanProperty(WebComponentCreationDataModel.ADD_TO_EAR))
        // {
        // IProject earProject =
        // dataModel.getEarComponentCreationDataModel().getTargetProject();
        // EAREditModel ear = null;
        // try {
        // Assert.assertTrue("EAR doesn't exist:", earProject.exists());
        // EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
        // //EMFWorkbenchContext emfWorkbenchContext =
        // WorkbenchResourceHelper.createEMFContext(earProject, null);
        // ear = (EAREditModel)
        // runtime.getEditModelForRead(IEARNatureConstants.EDIT_MODEL_ID, key);
        // ear.getModuleMapping(dataModel.getTargetProject());
        // //TODO
        // } finally {
        // ear.releaseAccess(key);
        // }
        //
        // }
    }
}
