package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


public class AppClientEMFTest extends GeneralEMFPopulationTest {
    EARFile earFile;
    ApplicationClientFile appClientFile;
	EARFile earFile14;
	ApplicationClientFile appClientFile14;

    public AppClientEMFTest(String name) {
        super(name);
    }

    public CommonarchiveFactory getArchiveFactory() {
        return CommonarchiveFactoryImpl.getActiveFactory();
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
        String[] className = { "com.ibm.etools.archive.test.AppClientEMFTest", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new AppClientEMFTest("testApplicationClientPopulation"));
        suite.addTest(new AppClientEMFTest("test14ApplicationClientPopulation"));
        return suite;
    }

    public void testApplicationClientPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
        createEAR();
        createAppClient();

        ApplicationClientResource DD = (ApplicationClientResource) appClientFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
        setVersion(VERSION_1_3);
        setModuleType(APP_CLIENT);
        populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestAppEAR";
        earFile.extractTo(out, Archive.EXPAND_ALL);
        earFile.close();

        //Compare
        String curDir = AutomatedBVT.baseDirectory;
        String exampleDeploymentDesURI = curDir + "EMFTestNoID/application-client.xml";
        String curDeploymentDesURI = curDir + "testOutput/TestAppEAR/fooApp/META-INF/application-client.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
    }

	public void test14ApplicationClientPopulation() throws Exception {
			EMFAttributeFeatureGenerator.reset();
			createEAR();
			createAppClient();

			ApplicationClientResource DD = (ApplicationClientResource) appClientFile.getDeploymentDescriptorResource();
			DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
			setVersion(VERSION_1_4);
			setModuleType(APP_CLIENT);
			populateRoot(DD.getRootObject());

			String out = AutomatedBVT.baseDirectory +"testOutput/TestAppEAR14";
			earFile.extractTo(out, Archive.EXPAND_ALL);
			earFile.close();

			getApp14Client();
			assertEquals("1.4", appClientFile14.getDeploymentDescriptor().getVersion());
			out = AutomatedBVT.baseDirectory +"testOutput/TestAppEAR14_2";
			appClientFile14.extractTo(out, Archive.EXPAND_ALL);
			appClientFile14.close();
			
			//Compare
			String curDir = AutomatedBVT.baseDirectory;
			String curDeploymentDesURI = curDir + "testOutput/TestAppEAR14/fooAPP/META-INF/application-client.xml";
			String secondDeploymentDesURI = out + "/META-INF/application-client.xml";
			setIgnoreAtt(ignorableAttributes());
			compareContentsIgnoreWhitespace(curDeploymentDesURI, secondDeploymentDesURI, null);
	}
	
    public void createAppClient() throws DuplicateObjectException {
        appClientFile = getArchiveFactory().createApplicationClientFileInitialized("fooAPP");
        appClientFile = (ApplicationClientFile) earFile.addCopy(appClientFile);
        appClientFile.getDeploymentDescriptor().setDisplayName("fooAPP");
        assertTrue(appClientFile.getDeploymentDescriptor() != null);
    }

    public void createEAR() {
        String earName = "Test.ear";
        earFile = getArchiveFactory().createEARFileInitialized(earName);
        assertTrue(earFile.getDeploymentDescriptor() != null);
    }
    
    
	public void getApp14Client() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory + "testOutput/TestAppEAR14/fooApp";
		appClientFile14 = getArchiveFactory().openApplicationClientFile(in);
	}
    
    public HashSet ignorableAttributes(){
    	HashSet set = new HashSet();
    	set.add("id");
    	return set;
    }
    
}
