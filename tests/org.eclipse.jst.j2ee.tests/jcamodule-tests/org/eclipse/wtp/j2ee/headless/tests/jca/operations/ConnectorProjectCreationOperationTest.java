
package org.eclipse.wtp.j2ee.headless.tests.jca.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class ConnectorProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
    public static Test suite() {
        return new TestSuite(ConnectorProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public J2EEModuleCreationDataModelOld getProjectCreationDataModel() {
        return new ConnectorModuleCreationDataModel();
    }
 
    
}
