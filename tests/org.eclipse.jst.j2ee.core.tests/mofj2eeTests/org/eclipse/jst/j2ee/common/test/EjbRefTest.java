package org.eclipse.jst.j2ee.common.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;

public class EjbRefTest extends TestCase {

	private static final EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();
	private static final CommonFactory COMMONFACTORY = CommonPackage.eINSTANCE.getCommonFactory();
	
	private EjbRef getInstance() {
		return COMMONFACTORY.createEjbRef();
	}
	
    public void test_EjbRef() {
        EjbRef tmpEjbRef = getInstance();
        assertNotNull(tmpEjbRef);
    }

    public void test_getLinkedEjb() {
    	
    	EJBJar objEJBJar = EJBFACTORY.createEJBJar();
        AssemblyDescriptor descriptor = EJBFACTORY.createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	EjbRef ejbRef = COMMONFACTORY.createEjbRef();
    	Session session1 = EJBFACTORY.createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = EJBFACTORY.createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
    	ejbRef.setLink(name2);
    	ejbRef.setName("EjbRef_" + name2);
    	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
        EnterpriseBean retValue = null;
        retValue = ejbRef.getLinkedEjb(objEJBJar);
        assertEquals(session2,retValue);
    }

    public void test_isEquivalent() {

    	EJBJar objEJBJar = EJBFACTORY.createEJBJar();
        AssemblyDescriptor descriptor = EJBFACTORY.createAssemblyDescriptor();
        objEJBJar.setAssemblyDescriptor(descriptor);
         
    	EjbRef ejbRef1 = COMMONFACTORY.createEjbRef();
    	EjbRef ejbRef2 = COMMONFACTORY.createEjbRef();
    	Session session1 = EJBFACTORY.createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
    	
    	Session session2 = EJBFACTORY.createSession();
    	String name2 = "Name2";
    	session2.setName(name2);
    	
    	ejbRef1.setLink(name2);
    	ejbRef1.setName("EjbRef_" + name2);
    	
    	ejbRef2.setLink(name2);
    	ejbRef2.setName("EjbRef_" + name2);
    	
    	String home1 = "home1";
    	String home2 = "home2" ;
    	String remote1 = "remote1" ;
    	String remote2 = "remote2";
    	
    	ejbRef1.setHome(home1);
    	ejbRef2.setHome(home1);
    	
    	ejbRef1.setRemote(remote1);
    	ejbRef2.setRemote(remote1);
    	
    	objEJBJar.getEnterpriseBeans().add(session1);
    	objEJBJar.getEnterpriseBeans().add(session2);
    	
    	boolean retValue = false;
        retValue = ejbRef1.isEquivalent(ejbRef2);	
        assertEquals(retValue,true);
        
        ejbRef2.setRemote(remote2);
        retValue = ejbRef1.isEquivalent(ejbRef2);	
        assertEquals(retValue,false);
        
        ejbRef2.setRemote(remote1);
        ejbRef2.setHome(home2);
        retValue = ejbRef1.isEquivalent(ejbRef2);	
        assertEquals(retValue,false);
        
        ejbRef2.setHome(home1);
        ejbRef2.setLink(name1);
        retValue = ejbRef1.isEquivalent(ejbRef2);	
        assertEquals(retValue,false);
        
        ejbRef2.setLink(name2);
        ejbRef2.setName("EjbRef_" + name1);
        retValue = ejbRef1.isEquivalent(ejbRef2);	
        assertEquals(retValue,false);
        
    }

    public void test_isLinked() {

    	Session session1 = EJBFACTORY.createSession();
    	String name1 = "Name1";
    	session1.setName(name1);
        EjbRef objEjbRef = getInstance();
       
        boolean retValue = false;
        retValue = objEjbRef.isLinked(session1);
        assertEquals(retValue, false);
        
        objEjbRef.setLink(name1);
        retValue = objEjbRef.isLinked(session1);
        assertEquals(retValue, true);
        
        
    }

    public void test_isLocal() {

        EjbRef objEjbRef = getInstance();
        boolean retValue = false;
        retValue = objEjbRef.isLocal();
        assertEquals(retValue,false);
        EJBLocalRef objLocalRef = CommonPackage.eINSTANCE.getCommonFactory().createEJBLocalRef();
        retValue = objLocalRef.isLocal();
        assertEquals(retValue,true);
    }

   /* public void test_getName() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getName();
    }

    public void test_setName() {

        EjbRef objEjbRef = getInstance();
        String newName = "";
        objEjbRef.setName(newName);
    }

    public void test_getType() {

        EjbRef objEjbRef = getInstance();
        EjbRefType retValue = null;
        retValue = objEjbRef.getType();
    }

    public void test_setType() {

        EjbRef objEjbRef = getInstance();
        EjbRefType newType = null;
        objEjbRef.setType(newType);
    }

    public void test_unsetType() {

        EjbRef objEjbRef = getInstance();
        objEjbRef.unsetType();
    }

    public void test_isSetType() {

        EjbRef objEjbRef = getInstance();
        boolean retValue = false;
        retValue = objEjbRef.isSetType();
    }

    public void test_getHome() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getHome();
    }

    public void test_setHome() {

        EjbRef objEjbRef = getInstance();
        String newHome = "";
        objEjbRef.setHome(newHome);
    }

    public void test_getRemote() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getRemote();
    }

    public void test_setRemote() {

        EjbRef objEjbRef = getInstance();
        String newRemote = "";
        objEjbRef.setRemote(newRemote);
    }

    public void test_getLink() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getLink();
    }

    public void test_setLink() {

        EjbRef objEjbRef = getInstance();
        String newLink = "";
        objEjbRef.setLink(newLink);
    }

    public void test_setDescription() {

        EjbRef objEjbRef = getInstance();
        String newDescription = "";
        objEjbRef.setDescription(newDescription);
    }

    public void test_getDescription() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getDescription();
    }

    public void test_getDescriptions() {

        EjbRef objEjbRef = getInstance();
        EList retValue = null;
        retValue = objEjbRef.getDescriptions();
    }*/
    
    /**
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(EjbRefTest.class);
	}
}
