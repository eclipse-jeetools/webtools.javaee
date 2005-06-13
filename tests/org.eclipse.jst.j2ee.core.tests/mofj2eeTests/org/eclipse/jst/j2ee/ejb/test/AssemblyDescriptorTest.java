package org.eclipse.jst.j2ee.ejb.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;

public class AssemblyDescriptorTest extends EjbEMFTestBase {

    /**
	 * @param name
	 */
	public AssemblyDescriptorTest(String name) {
		super(name);
	}
	
		
	public void test_getMethodPermissionMethodElements() throws Exception {
		init();
		EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		List enterpriseBeans = DD.getEJBJar().getEnterpriseBeans();
		EnterpriseBean eb1 = (EnterpriseBean)enterpriseBeans.get(0);
		List methodPermissionList = assemblyDescriptor.getMethodPermissionMethodElements(eb1);
		
		
		List allMethodElements = new ArrayList();
		List permissions = assemblyDescriptor.getMethodPermissions();
		MethodPermission permission;
		for (int i = 0; i < permissions.size(); i++){
			permission = (MethodPermission) permissions.get(i);
			allMethodElements.addAll(permission.getMethodElements(eb1)); 
		}
		assertEquals(methodPermissionList,allMethodElements);
		
	}
	
	/**
	 * @param expected
	 * @param actual
	 */
	public static void assertEquals(List expected, List actual) {
		assertEquals(actual.size(), expected.size());
		for (int x=0; x< expected.size(); ++x) {
			Object o = expected.get(x);
			assertEquals(actual.contains(o), true);
		}

	}
	
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AssemblyDescriptorTest("test_getMethodPermissionMethodElements"));
		suite.addTest(new AssemblyDescriptorTest("test_getMethodTransactionMethodElements"));
		suite.addTest(new AssemblyDescriptorTest("test_getSecurityRoleNamed"));
		suite.addTest(new AssemblyDescriptorTest("test_renameSecurityRole"));
		suite.addTest(new AssemblyDescriptorTest("test_removeData"));
		
		
		return suite;
	}

	
  /*  public void test_getMethodPermission() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        List securityRolesList = null;
        MethodPermission retValue = null;
        retValue = objAssemblyDescriptor
                .getMethodPermission(securityRolesList);
    } */

 

    public void test_getMethodTransactionMethodElements() throws Exception {
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		List enterpriseBeans = DD.getEJBJar().getEnterpriseBeans();
		EnterpriseBean eb1 = (EnterpriseBean)enterpriseBeans.get(0);
		List methodTransactionList = assemblyDescriptor.getMethodTransactionMethodElements(eb1);
		
		
		List allMethodElements = new ArrayList();
		List transactions = assemblyDescriptor.getMethodTransactions();
		MethodTransaction transaction;
		for (int i = 0; i < transactions.size(); i++){
			transaction = (MethodTransaction) transactions.get(i);
			allMethodElements.addAll(transaction.getMethodElements(eb1)); 
		}
		assertEquals(methodTransactionList,allMethodElements);
    }



	public void test_getSecurityRoleNamed()throws Exception {
		init();
		EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		SecurityRole securityRole= CommonPackage.eINSTANCE.getCommonFactory().createSecurityRole();
		String securityRoleName = "testRole";
		securityRole.setRoleName(securityRoleName);
		assemblyDescriptor.getSecurityRoles().add(securityRole);
		SecurityRole retValue = assemblyDescriptor.getSecurityRoleNamed(securityRoleName) ;
		assertEquals(retValue,securityRole);
	}

    public void test_renameSecurityRole() throws Exception {
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		String securityRoleName = "testRole";
		
		SecurityRole securityRole= CommonPackage.eINSTANCE.getCommonFactory().createSecurityRole();
		
		securityRole.setRoleName(securityRoleName);
		assemblyDescriptor.getSecurityRoles().add(securityRole);
		String newSecurityRoleName = "newTestRole" ;
		SecurityRole retValue = assemblyDescriptor.getSecurityRoleNamed(newSecurityRoleName) ;
		assertNull(retValue);
		assemblyDescriptor.renameSecurityRole(securityRoleName,
					newSecurityRoleName);
		retValue = assemblyDescriptor.getSecurityRoleNamed(newSecurityRoleName) ;
		assertNotNull(retValue);
		
    }

   /* public void test_getMethodPermissions() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EList retValue = null;
        retValue = objAssemblyDescriptor.getMethodPermissions();
    }*/

   /* public void test_getMethodTransactions() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EList retValue = null;
        retValue = objAssemblyDescriptor.getMethodTransactions();
    } */

  /*  public void test_getEjbJar() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EJBJar retValue = null;
        retValue = objAssemblyDescriptor.getEjbJar();
    }

    public void test_setEjbJar() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EJBJar newEjbJar = null;
        objAssemblyDescriptor.setEjbJar(newEjbJar);
    } */

  /*  public void test_getSecurityRoles() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EList retValue = null;
        retValue = objAssemblyDescriptor.getSecurityRoles();
    }*/

 /*   public void test_getExcludeList() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        ExcludeList retValue = null;
        retValue = objAssemblyDescriptor.getExcludeList();
    } */

   

   /* public void test_setExcludeList() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        ExcludeList newExcludeList = null;
        objAssemblyDescriptor.setExcludeList(newExcludeList);
    } */

 /*   public void test_getMessageDestinations() {

        AssemblyDescriptor objAssemblyDescriptor = getInstance();
        EList retValue = null;
        retValue = objAssemblyDescriptor.getMessageDestinations();
    } */

    public void test_removeData() throws Exception {
    	init();
    	EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		AssemblyDescriptor assemblyDescriptor = DD.getEJBJar().getAssemblyDescriptor();
		assertNotNull(assemblyDescriptor);
		List enterpriseBeans = DD.getEJBJar().getEnterpriseBeans();
		EnterpriseBean eb1 = (EnterpriseBean)enterpriseBeans.get(0);
		List methodTransactionList = assemblyDescriptor.getMethodTransactionMethodElements(eb1);
		List methodPermissionList = assemblyDescriptor.getMethodPermissionMethodElements(eb1);
		assertEquals(methodTransactionList.size() > 0, true);
		assertEquals(methodPermissionList.size() > 0, true); 
		
		assemblyDescriptor.removeData(eb1);
		
		methodTransactionList = assemblyDescriptor.getMethodTransactionMethodElements(eb1);
		methodPermissionList = assemblyDescriptor.getMethodPermissionMethodElements(eb1);
		assertEquals(methodTransactionList.size() == 0, true);
		assertEquals(methodPermissionList.size() == 0, true); 
        
    	
    }

}
