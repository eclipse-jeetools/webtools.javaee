package org.eclipse.jst.j2ee.application.test;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;

public class ApplicationFactoryTest extends TestCase {

	 public static Test suite(){
        return new TestSuite(ApplicationFactoryTest.class);
    }
	  
    public ApplicationFactory getInstance() {
        return ApplicationPackage.eINSTANCE.getApplicationFactory();
    }

   
    public void test_createApplication() {

        ApplicationFactory objApplicationFactory = getInstance();
        Application retValue = null;
        retValue = objApplicationFactory.createApplication();
        assertNotNull(retValue);
    }

    public void test_createModule() {

        ApplicationFactory objApplicationFactory = getInstance();
        Module retValue = null;
        retValue = objApplicationFactory.createModule();
        assertNotNull(retValue);
    }

    public void test_createWebModule() {

        ApplicationFactory objApplicationFactory = getInstance();
        WebModule retValue = null;
        retValue = objApplicationFactory.createWebModule();
        assertNotNull(retValue);
    }

    public void test_createJavaClientModule() {

        ApplicationFactory objApplicationFactory = getInstance();
        JavaClientModule retValue = null;
        retValue = objApplicationFactory.createJavaClientModule();
        assertNotNull(retValue);
    }

    public void test_createEjbModule() {

        ApplicationFactory objApplicationFactory = getInstance();
        EjbModule retValue = null;
        retValue = objApplicationFactory.createEjbModule();
        assertNotNull(retValue);
    }

    public void test_createConnectorModule() {

        ApplicationFactory objApplicationFactory = getInstance();
        ConnectorModule retValue = null;
        retValue = objApplicationFactory.createConnectorModule();
        assertNotNull(retValue);
    }

    public void test_getApplicationPackage() {

        ApplicationFactory objApplicationFactory = getInstance();
        ApplicationPackage retValue = null;
        retValue = objApplicationFactory.getApplicationPackage();
        assertNotNull(retValue);
    }
    
}
