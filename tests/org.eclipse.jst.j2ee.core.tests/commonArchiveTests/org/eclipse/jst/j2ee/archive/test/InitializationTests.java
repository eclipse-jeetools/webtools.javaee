package org.eclipse.jst.j2ee.archive.test;

 /*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */


import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
/**
 * Insert the type's description here.
 * Creation date: (12/18/00 7:41:39 PM)
 * @author: Administrator
 */
public class InitializationTests extends TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * InitializationTests constructor comment.
 * @param name java.lang.String
 */
public InitializationTests(String name) {
	super(name);
}
public CommonarchiveFactory getArchiveFactory() {
	return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {
	String[] className = {"com.ibm.etools.archive.test.InitializationTests", "-noloading"};
	TestRunner.main(className);
}
public static junit.framework.Test suite() {
	return new TestSuite(InitializationTests.class);
}
public void testNewArchives() throws Exception {

//        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
	ApplicationClientFile clientFile = getArchiveFactory().createApplicationClientFileInitialized("foo");
	assertTrue(clientFile.getDeploymentDescriptor() != null);
	

	clientFile = getArchiveFactory().createApplicationClientFileInitialized("foo");

	EJBJarFile ejbJarFile = getArchiveFactory().createEJBJarFileInitialized("foo");
	assertTrue(ejbJarFile.getDeploymentDescriptor() != null);
	
	//The next piece is to verify no exceptions occur
	ejbJarFile = getArchiveFactory().createEJBJarFileInitialized("foo");
	EJBModuleRef ejbModuleRef = getArchiveFactory().createEJBModuleRef();
	ejbModuleRef.setModuleFile(ejbJarFile);
	

	EARFile earFile = getArchiveFactory().createEARFileInitialized("foo");
	
	assertTrue(earFile.getDeploymentDescriptor() != null);
	

	WARFile warFile = getArchiveFactory().createWARFileInitialized("foo");
	
	assertTrue(warFile.getDeploymentDescriptor() != null);
	

	warFile = getArchiveFactory().createWARFileInitialized("foo");
	WebModuleRef webModuleRef = getArchiveFactory().createWebModuleRef();
	webModuleRef.setModuleFile(warFile);
	

}
	
	


	
}
