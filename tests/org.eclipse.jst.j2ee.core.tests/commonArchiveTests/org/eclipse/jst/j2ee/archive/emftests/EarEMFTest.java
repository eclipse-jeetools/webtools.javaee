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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class EarEMFTest extends GeneralEMFPopulationTest {
    protected EARFile earFile;
    int createdModules = 0;

    private int NUM_MODULES = 5;

    public EarEMFTest(String name) {
        super(name);
    }
    
    public EarEMFTest(String name, RendererFactory factory) {
    	super(name, factory);
    }
    
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.EarEMFTest", "-noloading" };
        TestRunner.main(className);
    }

    public static junit.framework.Test suite(RendererFactory factory) {
        TestSuite suite = new TestSuite(EarEMFTest.class.getName());
        //[248158] suite.addTest(new EarEMFTest("testEARPopulation", factory));
		suite.addTest(new EarEMFTest("test14EARPopulation", factory));
        return suite;
    }

    public void testEARPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
        createEAR();
        //createAppClient();

        ApplicationResource DD = (ApplicationResource) earFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		setModuleType(APPLICATION);
        populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestEAR";
        earFile.extractTo(out, Archive.EXPAND_ALL);
        earFile.close();

        //Compare work in progress
        String curDir = AutomatedBVT.baseDirectory;
        String exampleDeploymentDesURI = curDir + "EMFTestNoID/application.xml";
        String curDeploymentDesURI = curDir + "testOutput/TestEAR/META-INF/application.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
    }
    
	public void test14EARPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		//createAppClient();

		ApplicationResource DD = (ApplicationResource) earFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(APPLICATION);
		populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestEAR14";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

		getEAR();
		assertEquals("1.4", earFile.getDeploymentDescriptor().getVersion());
		out = AutomatedBVT.baseDirectory +"testOutput/TestEAR14_2";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		
		earFile.close();

		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = out + "/META-INF/application.xml";
		String curDeploymentDesURI = curDir + "testOutput/TestEAR14/META-INF/application.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
	}
    public void createEAR() {
        String earName = "Test.ear";
        earFile = getArchiveFactory().createEARFileInitialized(earName);
        assertTrue(earFile.getDeploymentDescriptor() != null);
    }

	public void getEAR() throws OpenFailureException {
		String in =AutomatedBVT.baseDirectory +"testOutput/TestEAR14";
		earFile = getArchiveFactory().openEARFile(in);
		assertTrue(earFile.getDeploymentDescriptor() != null);
	}

    public EObject createInstance(EReference ref, EObject eObject) {
		EClass eClassifier = (EClass)ref.getEType();
        if (eClassifier.getName().equals("Module")) {
            createdModules++;
            switch (createdModules) {
                case (1) :
                    return createJavaModuleInstance(eClassifier);
                case (2) :
                    return createEJBModuleInstance(eClassifier);
                case (3) :
                    return createWebModuleInstance(eClassifier);
                case (4) :
                    return createConnetorModuleInstance(eClassifier);
                default :
                    return createJavaModuleInstance(eClassifier);
            }
        }
        return super.createInstance(ref,eObject);
    }

    private EObject createConnetorModuleInstance(EClass eClassifier) {
        return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createConnectorModule();
    }

    private EObject createWebModuleInstance(EClass eClassifier) {
        return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createWebModule();
    }

    private EObject createEJBModuleInstance(EClass eClassifier) {
        return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createEjbModule();
    }

    private EObject createJavaModuleInstance(EClass eClassifier) {
        return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createJavaClientModule();
    }

    public Object getSharedObjectByType(EObject owner, EReference ref) {
        if (ref.getName().equals("module"))
            return createJavaModuleInstance((EClass) ref.getEType());
        return super.getSharedObjectByType(owner, ref);
    }

    public int getDepthForAttribute(EStructuralFeature ref) {
        if (ref.getName().equals("modules"))
            return NUM_MODULES;
        return super.getDepthForAttribute(ref);
    }
}