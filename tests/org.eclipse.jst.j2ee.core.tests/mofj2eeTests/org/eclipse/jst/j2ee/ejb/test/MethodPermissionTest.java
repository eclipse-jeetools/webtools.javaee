package org.eclipse.jst.j2ee.ejb.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;

public class MethodPermissionTest extends TestCase {

	private static final EjbFactory EJBFACTORY = EjbPackage.eINSTANCE.getEjbFactory();
	private static final CommonFactory COMMONFACTORY = CommonPackage.eINSTANCE.getCommonFactory();
	private MethodPermission getInstance() {
		return EJBFACTORY.createMethodPermission();
	}
	
	
    public void test_MethodPermission() {
        MethodPermission tmpMethodPermission = getInstance();
        assertNotNull(tmpMethodPermission);
    }

   /*public void test_getMethodElements() {

        MethodPermission objMethodPermission = getInstance();
        EnterpriseBean anEJB = null;
        List retValue = null;
        retValue = objMethodPermission.getMethodElements(anEJB);
    }*/

    public void test_getSecurityRole() {

        MethodPermission objMethodPermission = getInstance();
        SecurityRole securityRole= COMMONFACTORY.createSecurityRole();
		String securityRoleName = "testRole";
		securityRole.setRoleName(securityRoleName);
		objMethodPermission.getRoles().add(securityRole);
		
        SecurityRole retValue = null;
        retValue = objMethodPermission.getSecurityRole(securityRoleName);
        assertEquals(securityRole,retValue);
    }

    public void test_hasSecurityRole() {
        MethodPermission objMethodPermission = getInstance();
        SecurityRole securityRole= COMMONFACTORY.createSecurityRole();
		String securityRoleName = "testRole";
		securityRole.setRoleName(securityRoleName);
		objMethodPermission.getRoles().add(securityRole);
        boolean retValue = objMethodPermission.hasSecurityRole(securityRole);
        assertEquals(retValue, true);
        SecurityRole securityRole2= COMMONFACTORY.createSecurityRole();
        retValue = objMethodPermission.hasSecurityRole(securityRole2);
        assertEquals(retValue, false);
    }

    public void test_hasSecurityRole_2() {

    	MethodPermission objMethodPermission = getInstance();
        SecurityRole securityRole= COMMONFACTORY.createSecurityRole();
		String securityRoleName = "testRole";
		securityRole.setRoleName(securityRoleName);
		objMethodPermission.getRoles().add(securityRole);
        boolean retValue = objMethodPermission.hasSecurityRole(securityRoleName);
        assertEquals(retValue, true);
        String securityRoleNameDoesNotExist = "testRoleDoesNotExist";
        retValue = objMethodPermission.hasSecurityRole(securityRoleNameDoesNotExist);
        assertEquals(retValue, false);
    }

    public void test_isEquivalent() {

    	MethodPermission objMethodPermission1 = getInstance();
    	MethodPermission objMethodPermission2 = getInstance();
    	
        SecurityRole securityRole1= COMMONFACTORY.createSecurityRole();
        securityRole1.setRoleName("testRole1");
        SecurityRole securityRole2= COMMONFACTORY.createSecurityRole();
        securityRole2.setRoleName("testRole2");
        SecurityRole securityRole3= COMMONFACTORY.createSecurityRole();
        securityRole3.setRoleName("testRole3");
        
		
        objMethodPermission1.getRoles().add(securityRole1);
        objMethodPermission1.getRoles().add(securityRole2);
        
        objMethodPermission2.getRoles().add(securityRole1);
        objMethodPermission2.getRoles().add(securityRole2);
        
        
        boolean retValue = objMethodPermission1.isEquivalent(objMethodPermission2);
        assertEquals(retValue, true);
        
        objMethodPermission1.getRoles().add(securityRole3);
        retValue = objMethodPermission1.isEquivalent(objMethodPermission2);
        assertEquals(retValue, false);
        
        
    }

 /*  public void test_getDescription() {

        MethodPermission objMethodPermission = getInstance();
        String retValue = "";
        retValue = objMethodPermission.getDescription();
    }

    public void test_setDescription() {

        MethodPermission objMethodPermission = getInstance();
        String newDescription = "";
        objMethodPermission.setDescription(newDescription);
    }*/

  /* public void test_isUnchecked() {

        MethodPermission objMethodPermission = getInstance();
        boolean retValue = false;
        retValue = objMethodPermission.isUnchecked();
    }*/

   /* public void test_setUnchecked() {

        MethodPermission objMethodPermission = getInstance();
        boolean newUnchecked = false;
        objMethodPermission.setUnchecked(newUnchecked);
    }*/

   /* public void test_unsetUnchecked() {

        MethodPermission objMethodPermission = getInstance();
        objMethodPermission.unsetUnchecked();
    }*/

    /*public void test_isSetUnchecked() {

        MethodPermission objMethodPermission = getInstance();
        boolean retValue = false;
        retValue = objMethodPermission.isSetUnchecked();
    }*/

   /* public void test_getAssemblyDescriptor() {

        MethodPermission objMethodPermission = getInstance();
        AssemblyDescriptor retValue = null;
        retValue = objMethodPermission.getAssemblyDescriptor();
    }

    public void test_setAssemblyDescriptor() {

        MethodPermission objMethodPermission = getInstance();
        AssemblyDescriptor newAssemblyDescriptor = null;
        objMethodPermission.setAssemblyDescriptor(newAssemblyDescriptor);
    }*/

   /* public void test_getRoles() {

        MethodPermission objMethodPermission = getInstance();
        EList retValue = null;
        retValue = objMethodPermission.getRoles();
    }*/

   /* public void test_getMethodElements_2() {

        MethodPermission objMethodPermission = getInstance();
        EList retValue = null;
        retValue = objMethodPermission.getMethodElements();
    }*/

  /* public void test_getDescriptions() {

        MethodPermission objMethodPermission = getInstance();
        EList retValue = null;
        retValue = objMethodPermission.getDescriptions();
    }*/   
    
    public static Test suite() {
		return new TestSuite(MethodPermissionTest.class);
	}


}
