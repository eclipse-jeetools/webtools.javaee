package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.ConnectorProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

public abstract class ModuleProjectCreationOperationTest extends OperationTestCase {
    
    public static String DEFAULT_PROJECT_NAME = "SimpleProject";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(AppClientProjectCreationOperationTest.class);
        suite.addTestSuite(EJBProjectCreationOperationTest.class);
        suite.addTestSuite(WebProjectCreationOperationTest.class); 
        suite.addTestSuite(ConnectorProjectCreationOperationTest.class); 
        return suite;
    }
    
    public void testDefaults() throws Exception {
        createSimpleModule(DEFAULT_PROJECT_NAME);
    }
    
    public void testCreateEAR() throws Exception {
    	J2EEComponentCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }

    public void testAddToEAR() throws Exception {
    	EARComponentCreationDataModel  dataModelEAR = new EARComponentCreationDataModel ();
        dataModelEAR.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
        EARProjectCreationOperationTest.runAndVerify(dataModelEAR);

        J2EEComponentCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, true);
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }
    
 
    
    public void testNoAddToEAR() throws Exception {
        J2EEComponentCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, false);
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }

    public void createSimpleModule(String projectName) throws Exception {
        J2EEComponentCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
    
    public abstract J2EEComponentCreationDataModel getProjectCreationDataModel();

}
