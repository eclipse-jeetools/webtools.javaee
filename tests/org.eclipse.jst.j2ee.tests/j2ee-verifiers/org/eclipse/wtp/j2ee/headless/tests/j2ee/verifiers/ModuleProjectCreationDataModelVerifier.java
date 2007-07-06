/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class ModuleProjectCreationDataModelVerifier extends JEEProjectCreationDataModelVerifier {
    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.J2EEProjectCreationDataModelVerifier#verify(org.eclipse.wtp.common.operation.WTPOperationDataModel)
     */
    public void verify(IDataModel model) throws Exception {
        super.verify(model);
        
        this.verifyProjectAddToEAR();
    }

    private void verifyProjectAddToEAR() throws Exception{
		boolean addToEAR = model.getBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR);
		if(addToEAR) {
			String earName = model.getStringProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME);
			IProject ear = ProjectUtilities.getProject(earName);
			Assert.assertTrue("The EAR should exist", ear.exists());
		
			IProject[] referencedProjs = ear.getReferencedProjects();
			boolean foundRef = false;
			for(int i = 0; i < referencedProjs.length && !foundRef; i++) {
				foundRef = referencedProjs[i].getName().equals(project.getName());
			}
			Assert.assertTrue("EAR did not have a reference to the project", foundRef);			
		}
	}
}
