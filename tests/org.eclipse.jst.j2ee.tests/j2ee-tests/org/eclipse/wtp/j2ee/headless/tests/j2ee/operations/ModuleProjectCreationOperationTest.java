package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.ConnectorProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

public abstract class ModuleProjectCreationOperationTest extends OperationTestCase {
    
    public static String DEFAULT_PROJECT_NAME = "SimpleProject";
	public static String DEFAULT_COMPONENT_NAME = "SimpleComponent";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(AppClientProjectCreationOperationTest.class);
        suite.addTestSuite(EJBProjectCreationOperationTest.class);
        suite.addTestSuite(WebProjectCreationOperationTest.class); 
        suite.addTestSuite(ConnectorProjectCreationOperationTest.class); 
        return suite;
    }
    
    public void testDefaults() throws Exception {
		createSimpleFlexProject(DEFAULT_PROJECT_NAME);
        createSimpleModule(DEFAULT_COMPONENT_NAME,DEFAULT_PROJECT_NAME);
    }
    
    public void testCreateEAR() throws Exception {
    	J2EEComponentCreationDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }

    public void testAddToEAR() throws Exception {
    	EARComponentCreationDataModel  dataModelEAR = new EARComponentCreationDataModel ();
        dataModelEAR.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
        EARProjectCreationOperationTest.runAndVerify(dataModelEAR);

        J2EEComponentCreationDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, true);
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }
    
 
    
    public void testNoAddToEAR() throws Exception {
        J2EEComponentCreationDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, "SimpleEJB");
        dataModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, false);
        dataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, "SimpleEAR");
        runAndVerify(dataModel);
    }

    public void createSimpleFlexProject(String projectName) throws Exception {
        FlexibleProjectCreationDataModel dataModel = getFlexProjectDataModel();
        dataModel.setProperty(FlexibleProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
	public void createSimpleModule(String componentName,String projectName) throws Exception {
        J2EEComponentCreationDataModel dataModel = getComponentCreationDataModel();
		dataModel.setProperty(J2EEComponentCreationDataModel.PROJECT_NAME, projectName);
		runAndVerify(dataModel);
        dataModel.setProperty(J2EEComponentCreationDataModel.COMPONENT_NAME, componentName);
        runAndVerify(dataModel);
    }
    
    private FlexibleProjectCreationDataModel getFlexProjectDataModel() {
		return new FlexibleJavaProjectCreationDataModel();
	}

	public abstract J2EEComponentCreationDataModel getComponentCreationDataModel();

}
