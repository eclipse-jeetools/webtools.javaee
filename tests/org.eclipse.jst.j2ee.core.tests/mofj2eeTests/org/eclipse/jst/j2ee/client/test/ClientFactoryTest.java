package org.eclipse.jst.j2ee.client.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.internal.client.ClientPackage;

public class ClientFactoryTest extends TestCase {

	private ClientFactory getInstance() {
		return ClientPackage.eINSTANCE.getClientFactory();
	}
    public void test_ClientFactory() {
        ClientFactory tmpClientFactory = getInstance();
        assertNotNull(tmpClientFactory);
    }

   
    public void test_createApplicationClient() {

        ClientFactory objClientFactory = getInstance();
        ApplicationClient retValue = null;
        retValue = objClientFactory.createApplicationClient();
        assertNotNull(retValue);
    }

    public void test_getClientPackage() {
        ClientFactory objClientFactory = getInstance();
        ClientPackage retValue = null;
        retValue = objClientFactory.getClientPackage();
        assertNotNull(retValue);
    }
	/**
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(ClientFactoryTest.class);
	}

}
