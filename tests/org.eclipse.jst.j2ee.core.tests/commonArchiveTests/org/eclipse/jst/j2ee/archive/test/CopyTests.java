package org.eclipse.jst.j2ee.archive.test;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * Insert the type's description here.
 * Creation date: (12/14/00 12:10:36 PM)
 * @author: Administrator
 */
public class CopyTests extends TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001."; //$NON-NLS-1$
	/**
	 * CopyTests constructor comment.
	 * @param name java.lang.String
	 */
	public CopyTests(String name) {
		super(name);
	}
	public CommonarchiveFactory getArchiveFactory() {
		return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
	}
	public EjbFactory getEJBFactory() {
		return EjbPackage.eINSTANCE.getEjbFactory();
	}

	public EjbPackage getEJBPackage() {
		return EjbPackage.eINSTANCE;
	}
	public WebapplicationFactory getWebFactory() {
		return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
	}
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.CopyTests", "-noloading" };
		TestRunner.main(className);
	}
	public static junit.framework.Test suite() {
		return new TestSuite(CopyTests.class);
	}
	/**
	 * Tests the addCopy(ReadOnlyDirectory) api on archive
	 */
	public void testAddCopyDirectory() throws Exception {

		WARFile aWarFile = (WARFile) getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "example2.war");

		ReadOnlyDirectory sampleDirectory = getArchiveFactory().openReadOnlyDirectory(AutomatedBVT.baseDirectory + "WarTestClasses");
		
		int initialSize = aWarFile.getFiles().size();

		aWarFile.addCopy(sampleDirectory);
		Iterator iter = aWarFile.getFiles().iterator();
		//For running inside eclipse
		while (iter.hasNext()) {
			String uri = ((File)iter.next()).getURI();
			if (uri.indexOf("CVS") >= 0) 
				iter.remove();
		}
		assertTrue("Wrong number of files ", aWarFile.getFiles().size() == initialSize + 7);
		aWarFile.saveAsNoReopen(AutomatedBVT.baseDirectory + "testOutput/CopyTests/addCopyDir.war");

	}

	public void testAddCopyModule() throws Exception {

		String in = AutomatedBVT.baseDirectory + "sample.ear";
		EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
		List modules = earFile.getDeploymentDescriptor().getModules();
		EJBJarFile ejbJarFile = null;

		for (int i = 0; i < modules.size(); i++) {
			Module aModule = (Module) modules.get(i);
			if (aModule.isEjbModule()) {
				ejbJarFile = (EJBJarFile) earFile.getFile(aModule.getUri());
				break;
			}
		}

		assertTrue("EJBJarFile should not be null", ejbJarFile != null);
		ejbJarFile.getDeploymentDescriptor();

		EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/CopyTests/newEarWithCopiedModule.ear");
		newEarFile.addCopy(ejbJarFile);
		EJBJarFile copied = (EJBJarFile) newEarFile.getModuleFiles().get(0);
		assertTrue(copied.isDeploymentDescriptorSet());
		assertTrue(copied.getDeploymentDescriptor() == copied.getDeploymentDescriptorResource().getContents().get(0));
		assertTrue(copied.getDeploymentDescriptor() != ejbJarFile.getDeploymentDescriptor());
		assertTrue(ejbJarFile.getFiles().size() == copied.getFiles().size());

		newEarFile.saveNoReopen();
	}

	/**
	 * Test per CMVC defect report 96197
	 * Create a new application, open ejb jar file, use addcopy to add the module, and save
	 * using original ear, get the ejb jar, and modify bindings
	 * save again
	 * Result:  bindings not updated
	 */
	public void testAddCopyModuleFilesToEAR() throws Exception {
		String uri = AutomatedBVT.baseDirectory + "testOutput/CopyTests/testAddCopyModuleFilesToEAR.ear";
		//Create a new ear file, add a standalone ejb jar file and standalone war file, and save
		EARFile earFile = getArchiveFactory().createEARFileInitialized(uri);
		earFile.setJ2EEVersion(J2EEVersionConstants.J2EE_1_3_ID);
		earFile.getDeploymentDescriptor().setDisplayName(uri);
		EJBJarFile ejbJarFile = getArchiveFactory().openEJBJarFile(AutomatedBVT.baseDirectory + "bankejbs.jar");
		earFile.addCopy(ejbJarFile);
		WARFile warFile = getArchiveFactory().openWARFile(AutomatedBVT.baseDirectory + "example2.war");
		earFile.addCopy(warFile);
		((WebModule) earFile.getDeploymentDescriptor().getFirstModule(warFile.getURI())).setContextRoot("/");
		earFile.save();

		ejbJarFile = (EJBJarFile) earFile.getEJBJarFiles().get(0);
		assertTrue("ejb dd resource not right", ejbJarFile.getDeploymentDescriptor().eResource() == ejbJarFile.getDeploymentDescriptorResource());

		

		warFile = (WARFile) earFile.getWARFiles().get(0);
		assertTrue("war dd resource not right", warFile.getDeploymentDescriptor().eResource() == warFile.getDeploymentDescriptorResource());

		
		earFile.saveNoReopen();

		
		earFile.close();

	}
	/**
	 * Tests opening a standalone module with an absolute path, and adding that to a new ear file, per defect report
	 */
	public void testAddCopyStandaloneModule() throws Exception {
		String fileName = AutomatedBVT.baseDirectory + "bankejbs.jar";

		EJBJarFile ejbJarFile = (EJBJarFile) getArchiveFactory().openArchive(fileName);
		ejbJarFile.getDeploymentDescriptor();

		EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/CopyTests/newEarWithStandAloneModule-out.ear");
		newEarFile.addCopy(ejbJarFile);
		EJBJarFile copied = (EJBJarFile) newEarFile.getModuleFiles().get(0);
		assertTrue(copied.isDeploymentDescriptorSet());
		assertTrue(copied.getDeploymentDescriptor() == copied.getDeploymentDescriptorResource().getContents().get(0));
		assertTrue(copied.getDeploymentDescriptor() != ejbJarFile.getDeploymentDescriptor());
		assertTrue(ejbJarFile.getFiles().size() == copied.getFiles().size());

		newEarFile.saveNoReopen();
	}

	public void testAddRenameAndDelete() throws Exception {

		String in = AutomatedBVT.baseDirectory + "sample.ear";
		EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
		List modules = earFile.getDeploymentDescriptor().getModules();
		EJBJarFile ejbJarFile = null;

		for (int i = 0; i < modules.size(); i++) {
			Module aModule = (Module) modules.get(i);
			if (aModule.isEjbModule()) {
				ejbJarFile = (EJBJarFile) earFile.getFile(aModule.getUri());
				break;
			}
		}

		assertTrue("EJBJarFile should not be null", ejbJarFile != null);
		ejbJarFile.getDeploymentDescriptor();

		EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/CopyTests/newEarWithCopiedModule.ear");
		EJBModuleRef addedCopy = (EJBModuleRef) newEarFile.addCopyRef(ejbJarFile);
		assertTrue("Module files should not be empty", !newEarFile.getModuleFiles().isEmpty());
		assertTrue("Modules should not be empty", !newEarFile.getDeploymentDescriptor().getModules().isEmpty());
		assertTrue("Module should be accessible and have the same uri as file", addedCopy.getModule().getUri().equals(addedCopy.getModuleFile().getURI()));

		String newURI = "temp/foobar.jar";
		addedCopy.setURI(newURI);
		assertTrue("Module should be accessible and have the new renamed uri as well as the file", addedCopy.getModule().getUri().equals(addedCopy.getModuleFile().getURI()) && addedCopy.getModule().getUri().equals(newURI));
		newEarFile.remove(addedCopy);
		assertTrue("Module files should be empty", newEarFile.getModuleFiles().isEmpty());
		assertTrue("Modules should be empty", newEarFile.getDeploymentDescriptor().getModules().isEmpty());

	}

	public void testAddRenameAndDeleteStandalone() throws Exception {

		String fileName = AutomatedBVT.baseDirectory + "bankejbs.jar";

		EJBJarFile ejbJarFile = (EJBJarFile) getArchiveFactory().openArchive(fileName);

		EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/CopyTests/newEarWithCopiedModule.ear");
		EJBModuleRef addedCopy = (EJBModuleRef) newEarFile.addCopyRef(ejbJarFile);
		assertTrue("Module files should not be empty", !newEarFile.getModuleFiles().isEmpty());
		assertTrue("Modules should not be empty", !newEarFile.getDeploymentDescriptor().getModules().isEmpty());
		assertTrue("Module should be accessible and have the same uri as file", addedCopy.getModule().getUri().equals(addedCopy.getModuleFile().getURI()));

		String newURI = "temp/foobar.jar";
		addedCopy.setURI(newURI);
		assertTrue("Module should be accessible and have the new renamed uri as well as the file", addedCopy.getModule().getUri().equals(addedCopy.getModuleFile().getURI()) && addedCopy.getModule().getUri().equals(newURI));
		newEarFile.remove(addedCopy);
		assertTrue("Module files should be empty", newEarFile.getModuleFiles().isEmpty());
		assertTrue("Modules should be empty", newEarFile.getDeploymentDescriptor().getModules().isEmpty());

	}
}