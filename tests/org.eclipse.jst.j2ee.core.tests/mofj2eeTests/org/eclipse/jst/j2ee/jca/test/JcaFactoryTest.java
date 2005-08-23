package org.eclipse.jst.j2ee.jca.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.MessageAdapter;
import org.eclipse.jst.j2ee.jca.MessageListener;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;

public class JcaFactoryTest extends TestCase {

	private JcaFactory getInstance() {
		return JcaPackage.eINSTANCE.getJcaFactory();
	}
    public void test_JcaFactory() {

        JcaFactory tmpJcaFactory = getInstance();
    }
    
    public static Test suite() {
		return new TestSuite(JcaFactoryTest.class);
	}

    public void test_createConnector() {

        JcaFactory objJcaFactory = getInstance();
        Connector retValue = null;
        retValue = objJcaFactory.createConnector();
        assertNotNull(retValue);
    }

    public void test_createLicense() {

        JcaFactory objJcaFactory = getInstance();
        License retValue = null;
        retValue = objJcaFactory.createLicense();
        assertNotNull(retValue);
    }

    public void test_createInboundResourceAdapter() {

        JcaFactory objJcaFactory = getInstance();
        InboundResourceAdapter retValue = null;
        retValue = objJcaFactory.createInboundResourceAdapter();
        assertNotNull(retValue);
    }

    public void test_createOutboundResourceAdapter() {

        JcaFactory objJcaFactory = getInstance();
        OutboundResourceAdapter retValue = null;
        retValue = objJcaFactory.createOutboundResourceAdapter();
        assertNotNull(retValue);
    }

    public void test_createMessageAdapter() {

        JcaFactory objJcaFactory = getInstance();
        MessageAdapter retValue = null;
        retValue = objJcaFactory.createMessageAdapter();
        assertNotNull(retValue);
    }

    public void test_createConnectionDefinition() {

        JcaFactory objJcaFactory = getInstance();
        ConnectionDefinition retValue = null;
        retValue = objJcaFactory.createConnectionDefinition();
        assertNotNull(retValue);
    }

    public void test_createAdminObject() {

        JcaFactory objJcaFactory = getInstance();
        AdminObject retValue = null;
        retValue = objJcaFactory.createAdminObject();
        assertNotNull(retValue);
    }

    public void test_createMessageListener() {

        JcaFactory objJcaFactory = getInstance();
        MessageListener retValue = null;
        retValue = objJcaFactory.createMessageListener();
        assertNotNull(retValue);
    }

    public void test_createActivationSpec() {

        JcaFactory objJcaFactory = getInstance();
        ActivationSpec retValue = null;
        retValue = objJcaFactory.createActivationSpec();
        assertNotNull(retValue);
    }

    public void test_createRequiredConfigPropertyType() {

        JcaFactory objJcaFactory = getInstance();
        RequiredConfigPropertyType retValue = null;
        retValue = objJcaFactory.createRequiredConfigPropertyType();
        assertNotNull(retValue);
    }

    public void test_createResourceAdapter() {

        JcaFactory objJcaFactory = getInstance();
        ResourceAdapter retValue = null;
        retValue = objJcaFactory.createResourceAdapter();
        assertNotNull(retValue);
    }

    public void test_createSecurityPermission() {

        JcaFactory objJcaFactory = getInstance();
        SecurityPermission retValue = null;
        retValue = objJcaFactory.createSecurityPermission();
        assertNotNull(retValue);
    }

    public void test_createAuthenticationMechanism() {

        JcaFactory objJcaFactory = getInstance();
        AuthenticationMechanism retValue = null;
        retValue = objJcaFactory.createAuthenticationMechanism();
        assertNotNull(retValue);
    }

    public void test_createConfigProperty() {

        JcaFactory objJcaFactory = getInstance();
        ConfigProperty retValue = null;
        retValue = objJcaFactory.createConfigProperty();
        assertNotNull(retValue);
    }

    public void test_getJcaPackage() {

        JcaFactory objJcaFactory = getInstance();
        JcaPackage retValue = null;
        retValue = objJcaFactory.getJcaPackage();
       
        assertNotNull(retValue);
    }

}
