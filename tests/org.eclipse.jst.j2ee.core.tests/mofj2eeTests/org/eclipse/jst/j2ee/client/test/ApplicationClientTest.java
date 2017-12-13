package org.eclipse.jst.j2ee.client.test;

import java.io.FileNotFoundException;

import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.archive.emftests.AppClientEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public class ApplicationClientTest extends AppClientEMFTest {

	/**
	 * @param name
	 */
	public ApplicationClientTest(String name) {
		super(name);
		
	}
	private void init() throws DuplicateObjectException, ResourceLoadException, FileNotFoundException {
		EMFAttributeFeatureGenerator.reset();
        createEAR();
        createAppClient();
	}
	private ApplicationClient getInstance() {
		return ClientPackage.eINSTANCE.getClientFactory().createApplicationClient();
	}
  
  
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ApplicationClientTest("test_getVersionID"));
		suite.addTest(new ApplicationClientTest("test_getJ2EEVersionID"));
		
		suite.addTest(new ApplicationClientTest("test_getCallbackHandlerClassName"));
		suite.addTest(new ApplicationClientTest("test_setCallbackHandlerClassName"));
		return suite;
	}


    public void test_getCallbackHandlerClassName() {
        ApplicationClient objApplicationClient = getInstance();
        String callBackHandlerName = "TestCallBackHandlerName" ;
        objApplicationClient.setCallbackHandlerClassName(callBackHandlerName);
        
        String retValue = "";
        retValue = objApplicationClient.getCallbackHandlerClassName();
        assertEquals(callBackHandlerName, retValue);
    }

    public void test_setCallbackHandlerClassName() {

    	ApplicationClient objApplicationClient = getInstance();
        String callBackHandlerName = "TestCallBackHandlerName" ;
        objApplicationClient.setCallbackHandlerClassName(callBackHandlerName);
        
        String retValue = "";
        retValue = objApplicationClient.getCallbackHandlerClassName();
        assertEquals(callBackHandlerName, retValue);
    }

    public void test_getVersionID() throws Exception {
    	init();
    	ApplicationClientResource DD = (ApplicationClientResource) appClientFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(APP_CLIENT);
		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getApplicationClient().getVersionID();
        assertEquals(retValue,J2EEVersionConstants.VERSION_1_4);
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		retValue = DD.getApplicationClient().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_3);
	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
		setVersion(VERSION_1_2);
		retValue = DD.getApplicationClient().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_2);
        
    }

    public void test_getJ2EEVersionID() throws Exception {
    	init();
    	ApplicationClientResource DD = (ApplicationClientResource) appClientFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(APP_CLIENT);
		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getApplicationClient().getJ2EEVersionID();
        assertEquals(retValue,J2EEVersionConstants.VERSION_1_4);
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		retValue = DD.getApplicationClient().getJ2EEVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_3);
	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
		setVersion(VERSION_1_2);
		retValue = DD.getApplicationClient().getJ2EEVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_2);
    }

 /*   public void test_getVersion() {

        ApplicationClient objApplicationClient = getInstance();
        String retValue = "";
        retValue = objApplicationClient.getVersion();
    }

    public void test_setVersion() {

        ApplicationClient objApplicationClient = getInstance();
        String newVersion = "";
        objApplicationClient.setVersion(newVersion);
    }*/

    /*public void test_getResourceRefs() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getResourceRefs();
    }
*/
   /* public void test_getEnvironmentProps() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getEnvironmentProps();
    }*/

  /*  public void test_getEjbReferences() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getEjbReferences();
    }*/

  /*  public void test_getResourceEnvRefs() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getResourceEnvRefs();
    }*/

   /* public void test_getCallbackHandler() {

        ApplicationClient objApplicationClient = getInstance();
        JavaClass retValue = null;
        retValue = objApplicationClient.getCallbackHandler();
    }*/

   /* public void test_setCallbackHandler() {

        ApplicationClient objApplicationClient = getInstance();
        JavaClass newCallbackHandler = null;
        objApplicationClient.setCallbackHandler(newCallbackHandler);
    }*/

  /*  public void test_getServiceRefs() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getServiceRefs();
    }

    public void test_getMessageDestinationRefs() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getMessageDestinationRefs();
    }

    public void test_getMessageDestinations() {

        ApplicationClient objApplicationClient = getInstance();
        EList retValue = null;
        retValue = objApplicationClient.getMessageDestinations();
    }*/

}
