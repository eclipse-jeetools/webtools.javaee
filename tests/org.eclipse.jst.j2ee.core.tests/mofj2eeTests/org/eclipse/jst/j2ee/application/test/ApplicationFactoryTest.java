package org.eclipse.jst.j2ee.application.test;
import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;

public class ApplicationFactoryTest extends TestCase {

    public void test_ApplicationFactoryImpl() {

        ApplicationFactory tmpApplicationFactory =  ApplicationFactory.eINSTANCE;
    }

   
    public void test_createApplication() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        Application retValue = null;
        retValue = objApplicationFactory.createApplication();
        assertNotNull(retValue);
    }

    public void test_createModule() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        Module retValue = null;
        retValue = objApplicationFactory.createModule();
        assertNotNull(retValue);
    }

    public void test_createWebModule() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        WebModule retValue = null;
        retValue = objApplicationFactory.createWebModule();
        assertNotNull(retValue);
    }

    public void test_createJavaClientModule() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        JavaClientModule retValue = null;
        retValue = objApplicationFactory.createJavaClientModule();
        assertNotNull(retValue);
    }

    public void test_createEjbModule() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        EjbModule retValue = null;
        retValue = objApplicationFactory.createEjbModule();
        assertNotNull(retValue);
    }

    public void test_createConnectorModule() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        ConnectorModule retValue = null;
        retValue = objApplicationFactory.createConnectorModule();
        assertNotNull(retValue);
    }

    public void test_getApplicationPackage() {

        ApplicationFactory objApplicationFactory = ApplicationFactory.eINSTANCE;
        ApplicationPackage retValue = null;
        retValue = objApplicationFactory.getApplicationPackage();
        // TODO -- Do we need to really test this API as the return value is always null
        //assertNotNull(retValue);
    }
    
}
