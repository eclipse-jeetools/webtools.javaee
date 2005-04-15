/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public class EARProjectCreationOperationTest extends org.eclipse.wst.common.tests.OperationTestCase {
    public EARProjectCreationOperationTest(String string) {
		super(string);
	}

	public static Test suite() {
        return new SimpleTestSuite(EARProjectCreationOperationTest.class);
    }

//    public void testDefaults() throws Exception {
//        createSimpleEAR("SimpleEAR");
//    }
//    public void testLocation() throws Exception {
//        EARComponentCreationDataModel dataModel = new EARComponentCreationDataModel();
//        dataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
//       // dataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, "c:/temp/");
//        runAndVerify(dataModel);
//    }
//
//    public void testVersion12() throws Exception {
//    	EARComponentCreationDataModel dataModel = new EARComponentCreationDataModel();
//        dataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
//        dataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_2_ID);
//        runAndVerify(dataModel);
//    }
//
//    public void testVersion13() throws Exception {
//    	EARComponentCreationDataModel dataModel = new EARComponentCreationDataModel();
//        dataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
//        dataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//        runAndVerify(dataModel);
//    }
//
//    public void testVersion14() throws Exception {
//    	EARComponentCreationDataModel dataModel = new EARComponentCreationDataModel();
//        dataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, "SimpleEAR");
//        dataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_4_ID);
//        runAndVerify(dataModel);
//    }
//
//    public static void createSimpleEAR(String earName) throws Exception {
//    	EARComponentCreationDataModel dataModel = new EARComponentCreationDataModel();
//        dataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, earName);
//        runAndVerify(dataModel);
//    }

}
