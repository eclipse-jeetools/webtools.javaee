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

import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class AppClientEMFTest extends GeneralEMFPopulationTest {
    EARFile earFile;
    protected ApplicationClientFile appClientFile;
	EARFile earFile14;
	ApplicationClientFile appClientFile14;

    public AppClientEMFTest(String name) {
        super(name);
    }
    
    public AppClientEMFTest(String name, RendererFactory factory) {
    	super(name, factory);
    }
    
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.AppClientEMFTest", "-noloading" };
        TestRunner.main(className);
    }

    public static junit.framework.Test suite(RendererFactory factory) {
        TestSuite suite = new TestSuite(AppClientEMFTest.class.getName());
        //[248158] suite.addTest(new AppClientEMFTest("testApplicationClientPopulation", factory));
        suite.addTest(new AppClientEMFTest("test14ApplicationClientPopulation", factory));
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
        String curDeploymentDesURI = curDir + "testOutput/TestAppEAR/fooAPP/META-INF/application-client.xml";
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
		String in = AutomatedBVT.baseDirectory + "testOutput/TestAppEAR14/fooAPP";
		appClientFile14 = getArchiveFactory().openApplicationClientFile(in);
	}
}
