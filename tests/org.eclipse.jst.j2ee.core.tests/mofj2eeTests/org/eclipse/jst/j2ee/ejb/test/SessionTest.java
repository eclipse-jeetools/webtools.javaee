package org.eclipse.jst.j2ee.ejb.test;

import junit.framework.TestCase;

import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Session;

public class SessionTest extends TestCase {

 
  /*  public void test_getTransactionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        TransactionType retValue = null;
        retValue = objSession.getTransactionType();
    }

    public void test_setTransactionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        TransactionType newTransactionType = null;
        objSession.setTransactionType(newTransactionType);
    }

    public void test_unsetTransactionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        objSession.unsetTransactionType();
    }

    public void test_isSetTransactionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        boolean retValue = false;
        retValue = objSession.isSetTransactionType();
    }*/

   /* public void test_getSessionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        SessionType retValue = null;
        retValue = objSession.getSessionType();
    }

    public void test_setSessionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        SessionType newSessionType = null;
        objSession.setSessionType(newSessionType);
    }

    public void test_unsetSessionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        objSession.unsetSessionType();
    }

    public void test_isSetSessionType() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        boolean retValue = false;
        retValue = objSession.isSetSessionType();
    }*/

  /*  public void test_getServiceEndpoint() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        JavaClass retValue = null;
        retValue = objSession.getServiceEndpoint();
    }

   
    public void test_setServiceEndpoint() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        JavaClass newServiceEndpoint = null;
        objSession.setServiceEndpoint(newServiceEndpoint);
    }*/
    
    public void test_setServiceEndpointName() {

        Session objSession =  EjbPackage.eINSTANCE.getEjbFactory().createSession();
        String serviceEndpointName = "SessionEndPoint1";
        objSession.setServiceEndpointName(serviceEndpointName);
        assertEquals(serviceEndpointName,objSession.getServiceEndpointName());
       
    }

    public void test_hasServiceEndpoint() {

        Session objSession =  EjbFactory.eINSTANCE.createSession();
        assertEquals(objSession.hasServiceEndpoint(),false);
        boolean retValue = false;
        String serviceEndpointName = "SessionEndPoint1";
        objSession.setServiceEndpointName(serviceEndpointName);
        retValue = objSession.hasServiceEndpoint();
        assertEquals(objSession.hasServiceEndpoint(),true);
        
    }

    public void test_getServiceEndpointName() {

	   Session objSession =  EjbPackage.eINSTANCE.getEjbFactory().createSession();
       String serviceEndpointName = "SessionEndPoint1";
       objSession.setServiceEndpointName(serviceEndpointName);
       assertEquals(serviceEndpointName,objSession.getServiceEndpointName());
     
    }

}
