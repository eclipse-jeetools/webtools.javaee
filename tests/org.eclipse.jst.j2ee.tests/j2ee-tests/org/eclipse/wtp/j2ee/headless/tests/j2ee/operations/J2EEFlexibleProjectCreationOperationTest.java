package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;
import org.eclipse.wtp.j2ee.headless.tests.plugin.AllPluginTests;

public class J2EEFlexibleProjectCreationOperationTest extends OperationTestCase {
    
    public static String DEFAULT_PROJECT_NAME = "SimpleFlexibleProject";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(J2EEFlexibleProjectCreationOperationTest.class);
        return suite;
    }
    
    public void testDefaults() throws Exception {
		AllPluginTests.createJONASRuntime();
        createSimpleProject(DEFAULT_PROJECT_NAME);
    }

    public void createSimpleProject(String projectName) throws Exception {
		FlexibleJavaProjectCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(FlexibleJavaProjectCreationDataModel.PROJECT_NAME, projectName);
		setServerTargetProperty(dataModel);
        runAndVerify(dataModel);
    }
    
    public FlexibleJavaProjectCreationDataModel getProjectCreationDataModel(){
		return new FlexibleJavaProjectCreationDataModel();
    }
	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(FlexibleJavaProjectCreationDataModel dataModel) {
		dataModel.setProperty(FlexibleJavaProjectCreationDataModel.SERVER_TARGET_ID, AllTests.JONAS_TOMCAT_RUNTIME.getId());
	}

}
