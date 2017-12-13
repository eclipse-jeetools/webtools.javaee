/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class RarEMFTest extends GeneralEMFPopulationTest {
	EARFile earFile;
	protected RARFile rarFile;

	public RarEMFTest(String name) {
		super(name);
	}
	
    public RarEMFTest(String name, RendererFactory factory) {
    	super(name, factory);
    }

	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.RarEMFTest", "-noloading" };
		TestRunner.main(className);
	}

	public static junit.framework.Test suite(RendererFactory factory) {
		TestSuite suite = new TestSuite(RarEMFTest.class.getName());
		//[248158] suite.addTest(new RarEMFTest("testRARPopulation",factory));
		suite.addTest(new RarEMFTest("test14RARPopulation",factory));
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
