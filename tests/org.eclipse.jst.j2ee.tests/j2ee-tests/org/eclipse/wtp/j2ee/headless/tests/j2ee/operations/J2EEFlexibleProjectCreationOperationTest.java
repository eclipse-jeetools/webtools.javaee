package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.ConnectorProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

public class J2EEFlexibleProjectCreationOperationTest extends OperationTestCase {
    
    public static String DEFAULT_PROJECT_NAME = "SimpleFlexibleProject";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(J2EEFlexibleProjectCreationOperationTest.class);
        return suite;
    }
    
    public void testDefaults() throws Exception {
        createSimpleProject(DEFAULT_PROJECT_NAME);
    }

    public void createSimpleProject(String projectName) throws Exception {
        J2EEProjectCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
    
    public J2EEProjectCreationDataModel getProjectCreationDataModel(){
		return new J2EEJavaProjectCreationDataModel();
    }

}
