package org.eclipse.jst.j2ee.archive.test;

 /*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;

/**
 * Insert the type's description here.
 * Creation date: (12/15/00 2:26:04 PM)
 * @author: Administrator
 */
public class WARFileTests extends TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * WARFileTests constructor comment.
 * @param name java.lang.String
 */
public WARFileTests(String name) {
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
	String[] className = {"com.ibm.etools.archive.test.WARFileTests", "-noloading"};
	TestRunner.main(className);
}
public static junit.framework.Test suite() {
	return new TestSuite(WARFileTests.class);
}
public void testAddCopyClass() throws Exception {

	WARFile aWarFile = (WARFile)getArchiveFactory().openArchive(AutomatedBVT.baseDirectory +"example2.war");

	ReadOnlyDirectory sampleDirectory = getArchiveFactory().openReadOnlyDirectory(AutomatedBVT.baseDirectory +"WarTestClasses");

	int initialSize = aWarFile.getClasses().size();

	File aFile = sampleDirectory.getFileInSelfOrSubdirectory("com/ibm/etools/archive/test/WARFileTests.class");

	aWarFile.addCopyClass(aFile);
	assertTrue(aWarFile.getClasses().size() == initialSize+1);

	String classURI = ArchiveUtil.concatUri(ArchiveConstants.WEBAPP_CLASSES_URI, aFile.getURI(), '/');
	assertTrue(aWarFile.containsFile(classURI));
	//System.out.println(aWarFile.getURI()+" contains class "+classURI);

	aWarFile.saveAsNoReopen(AutomatedBVT.baseDirectory +"testOutput/WarTests/addCopyClass.war");

}
	
public void testAddCopyLib() throws Exception {

	WARFile aWarFile = (WARFile)getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "example2.war");

	Archive lib = getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "foo.jar");

	int initialSize = aWarFile.getLibs().size();

	aWarFile.addCopyLib(lib);
	assertTrue(aWarFile.getLibs().size() == initialSize+1);

	String libURI = ArchiveUtil.concatUri(ArchiveConstants.WEBAPP_LIB_URI, lib.getURI(), '/');
	assertTrue(aWarFile.containsFile(libURI));
	//System.out.println(aWarFile.getURI()+" contains lib "+libURI);

	aWarFile.saveAsNoReopen("testOutput/WarTests/addCopyLib.war");

}
	
public void testListClassesAndLibs() throws Exception {

	WARFile aWarFile = (WARFile)getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "example2.war");

	List libs = aWarFile.getLibs();
	List classes = aWarFile.getClasses();
	List resources = aWarFile.getResources();
//	System.out.println("WAR spec version "+aWarFile.getSpecVersion()+" detected.");
//	System.out.println("example2.war contains "+classes.size()+ " files in classes");
//	System.out.println("example2.war contains "+libs.size()+ " libs");
//	System.out.println("example2.war contains "+resources.size()+ " resources");

}
	
}
