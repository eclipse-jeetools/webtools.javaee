package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;


public class RarEMFEditTest extends GeneralEMFEditingTest {
    RARFile rarFile;

    public RarEMFEditTest(String name) {
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
        String[] className = { "com.ibm.etools.archive.test.RarEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new RarEMFEditTest("testRAREdit"));
        return suite;
    }

    public void testRAREdit() throws Exception {
        getRar();
		assertEquals("1.0", rarFile.getDeploymentDescriptor().getSpecVersion());

		ConnectorResource DD = (ConnectorResource) rarFile.getDeploymentDescriptorResource();
       	setVersion(VERSION_1_3);
       	setModuleType(CONNECTOR);       
        editRoot(DD.getRootObject());
        String curDir = AutomatedBVT.baseDirectory;
        
        String out = curDir + "testOutput/EMFModelCreationTests/EditRarEAR";
        rarFile.extractTo(out, Archive.EXPAND_ALL);
        rarFile.close();

        //Compare work in progress
        
        String exampleDeploymentDesURI = curDir + "EMFTests/ra.xml";
        String curDeploymentDesURI = curDir + out + "/fooRAR/META-INF/ra.xml";
        //compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }

    public void getRar() throws DuplicateObjectException, OpenFailureException {
        String in = AutomatedBVT.baseDirectory + "loose_module_workspace/LooseConnector/fooRAR/";
        rarFile = getArchiveFactory().openRARFile(in);
        assertTrue(rarFile.getDeploymentDescriptor() != null);
    }
	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}
}
