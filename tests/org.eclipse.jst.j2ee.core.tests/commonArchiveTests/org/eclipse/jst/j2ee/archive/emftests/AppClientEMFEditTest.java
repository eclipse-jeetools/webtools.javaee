package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;


public class AppClientEMFEditTest extends GeneralEMFEditingTest {
    EARFile earFile;
    ApplicationClientFile appClientFile;

    public AppClientEMFEditTest(String name) {
        super(name);
    }

    public CommonarchiveFactory getArchiveFactory() {
        return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
    }

    public EjbFactory getEjbFactory() {
        return EjbPackage.eINSTANCE.getEjbFactory();
    }

    public ApplicationFactory getApplicationFactory() {
        return ApplicationPackage.eINSTANCE.getApplicationFactory();
    }

    public WebapplicationFactory getWebAppFactory() {
        return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
    }

    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.AppClientEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new AppClientEMFEditTest("testApplicationClientEdit"));
        return suite;
    }

    public void testApplicationClientEdit() throws Exception {
        getAppClient();
        assertEquals("1.3", appClientFile.getDeploymentDescriptor().getVersion());

        ApplicationClientResource DD = (ApplicationClientResource) appClientFile.getDeploymentDescriptorResource();
		setVersion(VERSION_1_3);
		setModuleType(APP_CLIENT);
        editRoot(DD.getRootObject());
        String curDir = AutomatedBVT.baseDirectory;
        String out = curDir + "testOutput/EMFModelCreationTests/EditAppEAR";
        appClientFile.extractTo(out, Archive.EXPAND_ALL);
        appClientFile.close();

        //Compare
        
        String exampleDeploymentDesURI = curDir + "EMFTests/application-client.xml";
        String curDeploymentDesURI = curDir + out + "/AppClientfoo/META-INF/application-client.xml";
		setIgnoreAtt(ignorableAttributes());
        //compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }

    public void getAppClient() throws DuplicateObjectException, OpenFailureException {
        String in = AutomatedBVT.baseDirectory + "loose_module_workspace/LooseEARApp/fooAPP/";
        appClientFile = getArchiveFactory().openApplicationClientFile(in);
    }
    
	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}
}
