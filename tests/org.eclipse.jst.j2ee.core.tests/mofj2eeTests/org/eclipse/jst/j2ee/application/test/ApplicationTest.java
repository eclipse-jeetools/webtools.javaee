package org.eclipse.jst.j2ee.application.test;
import java.io.FileNotFoundException;

import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.archive.emftests.EarEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;

public class ApplicationTest extends EarEMFTest {

	 private static final CommonFactory COMMONFACTORY = CommonPackage.eINSTANCE.getCommonFactory();
	 public ApplicationFactory getInstance() {
        return ApplicationPackage.eINSTANCE.getApplicationFactory();
    }
    /**
	 * @param name
	 */
	public ApplicationTest(String name) {
		super(name);
	}
	
	private void init() throws DuplicateObjectException, ResourceLoadException, FileNotFoundException {
		EMFAttributeFeatureGenerator.reset();
        createEAR();
	}

	public void test_containsSecurityRole() {
		
        Application objApplication = getInstance().createApplication();
        String name ="role1" ;
        String name2 ="role2" ;
        boolean retValue = false;
        retValue = objApplication.containsSecurityRole(name);
        assertEquals(retValue, false) ;
        SecurityRole role = COMMONFACTORY.createSecurityRole();
		role.setRoleName(name);
        objApplication.getSecurityRoles().add(role);
        retValue = objApplication.containsSecurityRole(name);
        assertEquals(retValue, true) ;
        retValue = objApplication.containsSecurityRole(name2);
        assertEquals(retValue, false) ;
       
    }

  /* public void test_getModule() {

        Application objApplication = getInstance().createApplication();
        String uri = "";
        Module retValue = null;
        retValue = objApplication.getModule(uri);
    }*/

    public void test_getModule_2() {

        Application objApplication = getInstance().createApplication();
        Module module = getInstance().createModule();
        String uri = "uri1";
        String altDD = "altDD1";
        module.setUri(uri);
        module.setAltDD(altDD);
        objApplication.getModules().add(module);
        Module retValue = null;
        retValue = objApplication.getModule(uri, altDD);
        assertEquals(module,retValue);
    }

    public void test_getFirstModule() {

    	 Application objApplication = getInstance().createApplication();
         Module module = getInstance().createModule();
         Module module2 = getInstance().createModule();
         Module module3 = getInstance().createModule();
         String uri = "uri1";
         String uri2 = "uri2" ;
         String altDD = "altDD1";
         String altDD2 = "altDD2" ;
         
         module.setUri(uri);
         module.setAltDD(altDD);
         
         module2.setUri(uri2);
         module2.setAltDD(altDD2);
         
         module3.setUri(uri2);
         module3.setAltDD(altDD);
         
         objApplication.getModules().add(module);
         objApplication.getModules().add(module2);
         objApplication.getModules().add(module3);
         Module retValue = null;
         retValue = objApplication.getFirstModule(uri2);
         assertEquals(module2,retValue);
         
    }

    public void test_getModuleHavingAltDD() {

    	 Application objApplication = getInstance().createApplication();
         Module module = getInstance().createModule();
         String uri = "uri1";
         String altDD = "altDD1";
         module.setUri(uri);
         module.setAltDD(altDD);
         objApplication.getModules().add(module);
         Module retValue = null;
         retValue = objApplication.getModuleHavingAltDD(altDD);
         assertEquals(module,retValue);
    }

    public void test_getSecurityRoleNamed() {

    	 Application objApplication = getInstance().createApplication();
         String name ="role1" ;
         String name2 ="role2" ;
         SecurityRole retValue = null;
         retValue = objApplication.getSecurityRoleNamed(name);
         assertNull(retValue);
         SecurityRole role = COMMONFACTORY.createSecurityRole();
 		 role.setRoleName(name);
         objApplication.getSecurityRoles().add(role);
         retValue = objApplication.getSecurityRoleNamed(name);
         assertEquals(retValue, role) ;
         retValue = objApplication.getSecurityRoleNamed(name2);
         assertNull(retValue);
        
    }

  /*  public void test_isVersion1_2Descriptor() {

        Application objApplication = getInstance().createApplication();
        boolean retValue = false;
        retValue = objApplication.isVersion1_2Descriptor();
    }

    public void test_isVersion1_3Descriptor() {

        Application objApplication = getInstance().createApplication();
        boolean retValue = false;
        retValue = objApplication.isVersion1_3Descriptor();
    }*/

 /*   public void test_getVersion() {

        Application objApplication = getInstance().createApplication();
        String retValue = "";
        retValue = objApplication.getVersion();
    }*/

    public void test_getVersionID() throws Exception {
        init();
    	ApplicationResource DD = (ApplicationResource) earFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(APP_CLIENT);
		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getApplication().getVersionID();
        assertEquals(retValue,J2EEVersionConstants.VERSION_1_4);
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		retValue = DD.getApplication().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_3);
	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
		setVersion(VERSION_1_2);
		retValue = DD.getApplication().getVersionID();
	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_2);  
    }

    public void test_getJ2EEVersionID() throws IllegalStateException, ResourceLoadException, DuplicateObjectException, FileNotFoundException {

    	init();
     	ApplicationResource DD = (ApplicationResource) earFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
 		setVersion(VERSION_1_4);
 		setModuleType(APP_CLIENT);
 		populateRoot(DD.getRootObject());
        int retValue = 0;
        retValue = DD.getApplication().getJ2EEVersionID();
        assertEquals(retValue,J2EEVersionConstants.VERSION_1_4);
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
 		setVersion(VERSION_1_3);
 		retValue = DD.getApplication().getJ2EEVersionID();
 	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_3);
 	    DD.setVersionID(J2EEVersionConstants.J2EE_1_2_ID);
 		setVersion(VERSION_1_2);
 		retValue = DD.getApplication().getJ2EEVersionID();
 	    assertEquals(retValue,J2EEVersionConstants.VERSION_1_2);  
 	    
        
    }

  /*  public void test_setVersion() {

        Application objApplication = getInstance().createApplication();
        String newVersion = "";
        objApplication.setVersion(newVersion);
    }

    public void test_getSecurityRoles() {

        Application objApplication = getInstance().createApplication();
        EList retValue = null;
        retValue = objApplication.getSecurityRoles();
    }

    public void test_getModules() {

        Application objApplication = getInstance().createApplication();
        EList retValue = null;
        retValue = objApplication.getModules();
    }*/
    
    public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ApplicationTest("test_getVersionID"));
		suite.addTest(new ApplicationTest("test_getJ2EEVersionID"));
		
		suite.addTest(new ApplicationTest("test_getSecurityRoleNamed"));
		suite.addTest(new ApplicationTest("test_getModuleHavingAltDD"));
		suite.addTest(new ApplicationTest("test_containsSecurityRole"));
		suite.addTest(new ApplicationTest("test_getModule_2"));
		suite.addTest(new ApplicationTest("test_getFirstModule"));
		return suite;
	}

}
