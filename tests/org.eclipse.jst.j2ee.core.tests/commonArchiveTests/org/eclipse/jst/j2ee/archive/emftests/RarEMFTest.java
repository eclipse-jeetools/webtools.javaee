package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;


public class RarEMFTest extends GeneralEMFPopulationTest {
	EARFile earFile;
	protected RARFile rarFile;

	public RarEMFTest(String name) {
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
		String[] className = { "com.ibm.etools.archive.test.RarEMFTest", "-noloading" };
		TestRunner.main(className);
	}
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new RarEMFTest("testRARPopulation"));
		suite.addTest(new RarEMFTest("test14RARPopulation"));
		return suite;
	}

	public void testRARPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createRAR();

		ConnectorResource DD = (ConnectorResource) rarFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		setModuleType(CONNECTOR);
		populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory + "testOutput/TestRarEAR";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = curDir + "EMFTestNoID/ra.xml";
		String curDeploymentDesURI = curDir + "testOutput/TestRarEAR/fooRAR/META-INF/ra.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
	}
	public void test14RARPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createRAR();

		ConnectorResource DD = (ConnectorResource) rarFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(CONNECTOR);
		populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory + "testOutput/Test14RarEAR";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

		getRar();
		assertTrue("1.5".equals(rarFile.getDeploymentDescriptor().getSpecVersion()));
		out = AutomatedBVT.baseDirectory + "testOutput/Test14RarEAR2";
		rarFile.extractTo(out, Archive.EXPAND_ALL);
		rarFile.close();

		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = curDir + "testOutput/Test14RarEAR2/META-INF/ra.xml";
		String curDeploymentDesURI = curDir + "testOutput/Test14RarEAR/fooRAR/META-INF/ra.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
	}

	public void getRar() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory + "testOutput/Test14RarEAR/fooRAR";
		rarFile = getArchiveFactory().openRARFile(in);
		assertTrue(rarFile.getDeploymentDescriptor() != null);
	}

	public void createRAR() throws DuplicateObjectException {
		rarFile = getArchiveFactory().createRARFileInitialized("fooRAR");
		rarFile = (RARFile) earFile.addCopy(rarFile);
		rarFile.getDeploymentDescriptor().setDisplayName("fooRAR");
		assertTrue(rarFile.getDeploymentDescriptor() != null);
	}

	public void createEAR() {
		String earName = "Test.ear";
		earFile = getArchiveFactory().createEARFileInitialized(earName);
		assertTrue(earFile.getDeploymentDescriptor() != null);
	}

	public HashSet ignorableAttributes() {
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#createAttributeValue(org.eclipse.emf.ecore.EAttribute)
	 */
	protected Object createAttributeValue(EAttribute att, EObject eObject) {
		//TODO: delete this after the model is fixed
		if (att == JcaPackage.eINSTANCE.getConfigProperty_Type())
			return "java.lang.String";
		else if (att == JcaPackage.eINSTANCE.getAuthenticationMechanism_CredentialInterface())
			return "javax.resource.spi.security.PasswordCredential";
		else if (att == JcaPackage.eINSTANCE.getConnector_SpecVersion() && version == VERSION_1_3)
			return "1.0";
		else
			return super.createAttributeValue(att, eObject);
	}

}
