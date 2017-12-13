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
/*
 * Created on Aug 6, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResource;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


/**
 * @author dfholttp
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class WebServicesEMFTest extends GeneralEMFPopulationTest {
	public EARFile earFile;
	public EJBJarFile ejbFile;
	int currentVersion = J2EEVersionConstants.J2EE_1_3_ID;
	private int createdInterfaceMaps = 0;

	public WebServicesEMFTest(String name) {
		super(name);
	}
	
    public WebServicesEMFTest(String name, RendererFactory factory) {
    	super(name, factory);
    }

	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.WebServicesEMFTest", "-noloading" };
		TestRunner.main(className);
	}
	public static junit.framework.Test suite(RendererFactory factory) {
		TestSuite suite = new TestSuite(WebServicesEMFTest.class.getName());
		suite.addTest(new WebServicesEMFTest("test13WebServicesClientPopulation",factory));
		suite.addTest(new WebServicesEMFTest("test13WebServicesDDPopulation",factory));
		suite.addTest(new WebServicesEMFTest("test14WebServicesDDPopulation",factory));
		suite.addTest(new WebServicesEMFTest("test50WebServicesDDPopulation",factory));
		suite.addTest(new WebServicesEMFTest("testJaxRPCMapPopulation",factory));
		return suite;
	}
	
	public void test13WebServicesClientPopulation() throws Exception {
		currentVersion = J2EEVersionConstants.J2EE_1_3_ID;
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createEJB();
		

		WebServicesResource webserDD = (WebServicesResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservicesclient.xml"));
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_1_3);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}

	public void testJaxRPCMapPopulation() throws Exception {
		currentVersion = J2EEVersionConstants.J2EE_1_3_ID;
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createEJB();
		String mappingFilePathURI = "META-INF/testmap.xml";
		URI uri = URI.createURI(mappingFilePathURI);
		ResourceSet resSet = ejbFile.getResourceSet();
		J2EEResourceFactoryRegistry registry = (J2EEResourceFactoryRegistry) resSet.getResourceFactoryRegistry();
		registry.registerLastFileSegment(uri.lastSegment(), new JaxrpcmapResourceFactory(RendererFactory.getDefaultRendererFactory()));
		resSet = earFile.getResourceSet();
		registry = (J2EEResourceFactoryRegistry) resSet.getResourceFactoryRegistry();
		registry.registerLastFileSegment(uri.lastSegment(), new JaxrpcmapResourceFactory(RendererFactory.getDefaultRendererFactory()));

		JaxrpcmapResource jaxrpcmapRes = (JaxrpcmapResource) resSet.createResource(uri);
		
		jaxrpcmapRes.setVersionID(currentVersion);
		setVersion(VERSION_1_3);
		
		JavaWSDLMapping map = JaxrpcmapFactory.eINSTANCE.createJavaWSDLMapping();
		jaxrpcmapRes.getContents().add(map);
		populateRoot(jaxrpcmapRes.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}
	
	public void test13WebServicesDDPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		currentVersion = J2EEVersionConstants.J2EE_1_3_ID;
		createEAR();
		createEJB();

		WsddResource webserDD = (WsddResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservices.xml"));
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_1_3);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}
	public void test14WebServicesDDPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		currentVersion = J2EEVersionConstants.J2EE_1_4_ID;
		createEAR();
		createEJB();

		WsddResource webserDD = (WsddResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservices.xml"));
		webserDD.getContents().add(WsddFactory.eINSTANCE.createWebServices());
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_1_4);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}
	public void test50WebServicesDDPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		currentVersion = J2EEVersionConstants.JEE_5_0_ID;
		createEAR();
		createEJB();

		WsddResource webserDD = (WsddResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservices.xml"));
		webserDD.getContents().add(WsddFactory.eINSTANCE.createWebServices());
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_5_0);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}

	public void test60WebServicesDDPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		currentVersion = J2EEVersionConstants.JEE_6_0_ID;
		createEAR();
		createEJB();

		WsddResource webserDD = (WsddResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservices.xml"));
		webserDD.getContents().add(WsddFactory.eINSTANCE.createWebServices());
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_6_0);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}

	public void test70WebServicesDDPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		currentVersion = J2EEVersionConstants.JEE_7_0_ID;
		createEAR();
		createEJB();

		WsddResource webserDD = (WsddResource)ejbFile.getResourceSet().createResource(URI.createURI("META-INF/webservices.xml"));
		webserDD.getContents().add(WsddFactory.eINSTANCE.createWebServices());
		//TODO: individual test for each version
		webserDD.setVersionID(currentVersion);
		setVersion(VERSION_6_0);
		populateRoot(webserDD.getRootObject());
		
		String out = AutomatedBVT.baseDirectory +getProjectLocation();
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();

	}
	
	public EObject createInstance(EReference ref,EObject eObject) {

        if (JaxrpcmapPackage.eINSTANCE.getInterfaceMapping().equals(ref.getEType()))
            return createInterfaceMap();

        return super.createInstance(ref, eObject);
    }
	
    /* The web type is abstract.  Alternate between servlet-class
     * and jsp-file
     */
    public InterfaceMapping createInterfaceMap() {
        createdInterfaceMaps++;
        if ((createdInterfaceMaps & 1) == 0)
            return JaxrpcmapFactory.eINSTANCE.createServiceEndpointInterfaceMapping();
        else
            return JaxrpcmapFactory.eINSTANCE.createServiceEndpointInterfaceMapping();

    }
	
	public String getProjectLocation() {
		if (currentVersion == J2EEVersionConstants.J2EE_1_3_ID)
			return "testOutput/TestWebServices";
		if (currentVersion == J2EEVersionConstants.J2EE_1_4_ID)
			return "testOutput/TestWebServices14";
		else
			return "testOutput/TestWebServices50";
	}
	public void getEJB() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory +getProjectLocation() +"/fooWebServices";
		ejbFile = getArchiveFactory().openEJBJarFile(in);
		assertTrue(ejbFile.getDeploymentDescriptor() != null);
	}
	public void createEJB() throws DuplicateObjectException {
		ejbFile = getArchiveFactory().createEJBJarFileInitialized("fooWebServices");
		ejbFile = (EJBJarFile) earFile.addCopy(ejbFile);
		ejbFile.getDeploymentDescriptor().setDisplayName("fooWebServices");
		assertTrue(ejbFile.getDeploymentDescriptor() != null);
	}
	public void createEAR() {
		String earName = "Test.ear";
		earFile = getArchiveFactory().createEARFileInitialized(earName);
		assertTrue(earFile.getDeploymentDescriptor() != null);
	}
}
