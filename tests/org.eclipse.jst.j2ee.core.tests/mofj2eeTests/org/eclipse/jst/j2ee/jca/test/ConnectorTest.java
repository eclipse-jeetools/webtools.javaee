package org.eclipse.jst.j2ee.jca.test;

import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.archive.emftests.RarEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.ConnectorResource;

public class ConnectorTest extends RarEMFTest {

    /**
	 * @param name
	 */
	public ConnectorTest(String name) {
		super(name);
	}

	public Connector getInstance() {
    	return JcaPackage.eINSTANCE.getJcaFactory().createConnector();
    }

    public void test_getVersionID() throws Exception {
    	EMFAttributeFeatureGenerator.reset();
		createEAR();
		createRAR();

		ConnectorResource DD = (ConnectorResource) rarFile.getDeploymentDescriptorResource();
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		int versionID = DD.getConnector().getVersionID();
		assertEquals(versionID,J2EEVersionConstants.JCA_1_5_ID);
		
    }
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ConnectorTest("test_getVersionID"));
		return suite;
	}


}
