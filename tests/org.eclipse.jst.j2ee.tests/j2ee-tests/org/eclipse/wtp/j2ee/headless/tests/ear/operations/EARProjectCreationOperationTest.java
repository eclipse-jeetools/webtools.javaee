/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.EnterpriseApplicationCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public class EARProjectCreationOperationTest extends OperationTestCase {
    public static Test suite() {
        return new SimpleTestSuite(EARProjectCreationOperationTest.class);
    }

    public void testDefaults() throws Exception {
        createSimpleEAR("SimpleEAR");
    }
    public void testLocation() throws Exception {
        EnterpriseApplicationCreationDataModel dataModel = new EnterpriseApplicationCreationDataModel();
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, "SimpleEAR");
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION, "c:/temp/");
        runAndVerify(dataModel);
    }

    public void testVersion12() throws Exception {
    	EnterpriseApplicationCreationDataModel dataModel = new EnterpriseApplicationCreationDataModel();
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, "SimpleEAR");
        dataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_2_ID);
        runAndVerify(dataModel);
    }

    public void testVersion13() throws Exception {
    	EnterpriseApplicationCreationDataModel dataModel = new EnterpriseApplicationCreationDataModel();
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, "SimpleEAR");
        dataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
        runAndVerify(dataModel);
    }

    public void testVersion14() throws Exception {
    	EnterpriseApplicationCreationDataModel dataModel = new EnterpriseApplicationCreationDataModel();
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, "SimpleEAR");
        dataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_4_ID);
        runAndVerify(dataModel);
    }

    public static void createSimpleEAR(String earName) throws Exception {
    	EnterpriseApplicationCreationDataModel dataModel = new EnterpriseApplicationCreationDataModel();
        dataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, earName);
        runAndVerify(dataModel);
    }

}
