package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class AppClientEMFEditTest extends GeneralEMFEditingTest {
    EARFile earFile;
    ApplicationClientFile appClientFile;

    public AppClientEMFEditTest(String name) {
        super(name);
    }
    
    public AppClientEMFEditTest(String name, RendererFactory factory) {
    	super(name, factory);
    }
    
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.AppClientEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }
    
    public static junit.framework.Test suite(RendererFactory factory) {
        TestSuite suite = new TestSuite(AppClientEMFEditTest.class.getName());
        suite.addTest(new AppClientEMFEditTest("testApplicationClientEdit", factory));
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
}
