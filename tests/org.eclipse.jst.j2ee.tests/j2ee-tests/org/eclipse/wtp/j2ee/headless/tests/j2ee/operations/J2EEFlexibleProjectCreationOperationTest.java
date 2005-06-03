package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
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
		IDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.PROJECT_NAME, projectName);
		setServerTargetProperty(dataModel);
        runAndVerify(dataModel);
    }
    
    public IDataModel getProjectCreationDataModel(){
		return DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
    }
	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(IDataModel dataModel) {
		dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.SERVER_TARGET_ID, AllTests.JONAS_TOMCAT_RUNTIME.getId());
	}

}
