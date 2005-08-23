/*
 * Created on Aug 6, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
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
	EARFile earFile;
	EJBJarFile ejbFile;
	int currentVersion = J2EEVersionConstants.J2EE_1_3_ID;
	private int createdInterfaceMaps = 0;

	public WebServicesEMFTest(String name) {
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

	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.WebServicesEMFTest", "-noloading" };
		TestRunner.main(className);
	}
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new WebServicesEMFTest("test13WebServicesClientPopulation"));
		suite.addTest(new WebServicesEMFTest("test13WebServicesDDPopulation"));
		suite.addTest(new WebServicesEMFTest("test14WebServicesDDPopulation"));
		suite.addTest(new WebServicesEMFTest("testJaxRPCMapPopulation"));
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
	public String getProjectLocation() {
		if (currentVersion == J2EEVersionConstants.J2EE_1_3_ID)
			return "testOutput/TestWebServices";
		else
			return "testOutput/TestWebServices14";
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
    
	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}
}
