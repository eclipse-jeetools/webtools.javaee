
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class EJBProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

	 /**
	 * @param name
	 */
	public EJBProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new TestSuite(EJBProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
    	return DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
    }
    
    public void testFindFilesUtility() {
    	IFile file = null;
    	try {
    		testDefaults();
    		IProject project = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.EJB)[0];
    		IVirtualComponent comp = ComponentCore.createComponent(project);
    		file = ComponentUtilities.findFile(comp, new Path("/META-INF/ejb-jar.xml")); //$NON-NLS-1$
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	Assert.assertTrue(file != null && file.exists());
    }
  
}
