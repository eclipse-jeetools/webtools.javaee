package org.eclipse.jst.j2ee.application.test;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.archive.emftests.EarEMFTest;
import org.eclipse.jst.j2ee.common.SecurityRole;

public class ApplicationTest extends EarEMFTest {

    /**
	 * @param name
	 */
	public ApplicationTest(String name) {
		super(name);
	}

	public void test_containsSecurityRole() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        java.lang.String name = "";
        boolean retValue = false;
        retValue = objApplication.containsSecurityRole(name);
    }

    public void test_getModule() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String uri = "";
        Module retValue = null;
        retValue = objApplication.getModule(uri);
    }

    public void test_getModule_2() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String uri = "";
        String altDD = "";
        Module retValue = null;
        retValue = objApplication.getModule(uri, altDD);
    }

    public void test_getFirstModule() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String uri = "";
        Module retValue = null;
        retValue = objApplication.getFirstModule(uri);
    }

    public void test_getModuleHavingAltDD() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        java.lang.String uri = "";
        Module retValue = null;
        retValue = objApplication.getModuleHavingAltDD(uri);
    }

    public void test_getSecurityRoleNamed() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String roleName = "";
        SecurityRole retValue = null;
        retValue = objApplication.getSecurityRoleNamed(roleName);
    }

    public void test_isVersion1_2Descriptor() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        boolean retValue = false;
        retValue = objApplication.isVersion1_2Descriptor();
    }

    public void test_isVersion1_3Descriptor() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        boolean retValue = false;
        retValue = objApplication.isVersion1_3Descriptor();
    }

 /*   public void test_getVersion() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String retValue = "";
        retValue = objApplication.getVersion();
    }*/

    public void test_getVersionID() throws IllegalStateException {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        int retValue = 0;
        retValue = objApplication.getVersionID();
    }

    public void test_getJ2EEVersionID() throws IllegalStateException {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        int retValue = 0;
        retValue = objApplication.getJ2EEVersionID();
    }

  /*  public void test_setVersion() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        String newVersion = "";
        objApplication.setVersion(newVersion);
    }

    public void test_getSecurityRoles() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        EList retValue = null;
        retValue = objApplication.getSecurityRoles();
    }

    public void test_getModules() {

        Application objApplication = ApplicationFactory.eINSTANCE.createApplication();
        EList retValue = null;
        retValue = objApplication.getModules();
    }*/

}
