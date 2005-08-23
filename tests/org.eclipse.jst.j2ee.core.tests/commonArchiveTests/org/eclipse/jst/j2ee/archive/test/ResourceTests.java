/*
 * Created on Mar 13, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveInit;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * @author schacher
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ResourceTests extends AbstractArchiveTest {

	/**
	 * @param name
	 */
	public ResourceTests(String name) {
		super(name);
	}
	
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		String[] className = {"com.ibm.etools.archive.test.ResourceTests", "-noloading"};
		TestRunner.main(className);
	}
	public static junit.framework.Test suite() {
		return new TestSuite(ResourceTests.class);
	}

	protected List findOccurrences(List resources, String uri) {
		List result = new ArrayList(1);
		for (int i = 0; i < resources.size(); i++) {
			Resource aResource = (Resource) resources.get(i);
			if (uri.equals(aResource.getURI().toString()))
				result.add(aResource);
		}
		return result;
	}

	public void testResourceCreation() throws Exception {
		String uri = "META-INF/client-resource.xmi";
		ApplicationClientFile appClientFile = getArchiveFactory().createApplicationClientFileInitialized("test.jar");
		Resource res = null;
		boolean exceptionCaught = false;
		try {
			res = appClientFile.getMofResource(uri);
		} catch (java.io.FileNotFoundException e) {
			exceptionCaught = true;
			List foundResources = findOccurrences(appClientFile.getResourceSet().getResources(), uri);
			assertTrue("There should exist exactly one resource", foundResources.size() == 1);
			Resource foundResource = (Resource)foundResources.get(0);
			assertFalse("The resource should be unloaded", foundResource.isLoaded());
			res = appClientFile.makeMofResource(uri);
			assertTrue("Resource should not be null", res != null);
			foundResources = findOccurrences(appClientFile.getResourceSet().getResources(), uri);
			assertTrue("There should exist exactly one resource", foundResources.size() == 1);
			foundResource = (Resource)foundResources.get(0);
			assertTrue("The resource should exist", foundResource != null);
			assertFalse("The resource should be unloaded", foundResource.isLoaded());		
		}
		assertTrue("Exception should have been caught", exceptionCaught);
		Collection loadedResources = appClientFile.getLoadedMofResources();
		assertTrue("Loaded resources should be size 1", loadedResources.size() == 1);
		EjbRef aRef = CommonPackage.eINSTANCE.getCommonFactory().createEjbRef();
		res.getContents().add(aRef);
		loadedResources = appClientFile.getLoadedMofResources();
		assertTrue("Loaded resources should be size 2", loadedResources.size() == 2);
	}
	
	public void testResourceDirty() throws Exception {
		EJBJarFile jar = getArchiveFactory().openEJB11JarFile(AutomatedBVT.baseDirectory + "bankejbs.jar");
		EnterpriseBean bean = (EnterpriseBean)jar.getDeploymentDescriptor().getEnterpriseBeans().get(0);
		
		EARFile newEar = getArchiveFactory().createEARFileInitialized("test");
		EJBJarFile copyJar = (EJBJarFile) newEar.addCopy(jar);
		
		assertFalse("dd should not be dirty", copyJar.getDeploymentDescriptorResource().isModified());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ArchiveInit.init();
	}

}
