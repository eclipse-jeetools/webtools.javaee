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

import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
/**
 * Insert the type's description here.
 * Creation date: (1/17/2001 10:17:29 AM)
 * @author: Administrator
 */
public class JavaTests extends TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * JavaTests constructor comment.
 * @param name java.lang.String
 */
public JavaTests(String name) {
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
	String[] className = {"com.ibm.etools.archive.test.JavaTests", "-noloading"};
	TestRunner.main(className);
}
public static junit.framework.Test suite() {
	return new TestSuite(JavaTests.class);
}
/**
 * This method verifies that java reflection works on archives, and that
 * class loading and java reflection is dynamic with changes to the class
 * path of the archive
 */
public void testJavaReflection() throws Exception {
/*
	Archive anArchive = getArchiveFactory().primOpenArchive("bank35deployed.jar");
	JavaClass cls = (JavaClass)org.eclipse.jem.internal.java.impl.JavaClassImpl.reflect("com.ibm.ejb.bank.VapAccountBean", anArchive.getContext());
	//Ignore the constructor
	assertTrue("Should not have been able to reflect methods" ,cls.getMethods().size() == 1 && ((Method)cls.getMethods().get(0)).getName().equals(cls.getName()));

	anArchive.setExtraClasspath("ivjejb35.jar");

	assertTrue("Methods should have been reflected", cls.getMethods().size() > 1);
*/	
}
}
