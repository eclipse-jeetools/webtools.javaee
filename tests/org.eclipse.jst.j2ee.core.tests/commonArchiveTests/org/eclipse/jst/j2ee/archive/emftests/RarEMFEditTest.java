package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class RarEMFEditTest extends GeneralEMFEditingTest {
    RARFile rarFile;

    public RarEMFEditTest(String name) {
        super(name);
    }
    
    public RarEMFEditTest(String name, RendererFactory factory) {
    	super(name, factory);
    }
    
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.RarEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }

    public static junit.framework.Test suite(RendererFactory factory) {
        TestSuite suite = new TestSuite(RarEMFEditTest.class.getName());
        suite.addTest(new RarEMFEditTest("testRAREdit",factory));
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
}
