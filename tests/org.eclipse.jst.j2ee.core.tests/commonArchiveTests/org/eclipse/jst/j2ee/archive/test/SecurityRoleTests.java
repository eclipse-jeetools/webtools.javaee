package org.eclipse.jst.j2ee.archive.test;

 /*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;

/**
 * Insert the type's description here.
 * Creation date: (02/09/01 12:05:18 AM)
 * @author: Administrator
 */
public class SecurityRoleTests extends AbstractArchiveTest {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * SecurityRoleTests constructor comment.
 * @param name java.lang.String
 */
public SecurityRoleTests(String name) {
	super(name);
}
public CommonFactory getCommonFactory() {
	return CommonPackage.eINSTANCE.getCommonFactory();
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {
	String[] className = {"com.ibm.etools.archive.test.SecurityRoleTests", "-noloading"};
	TestRunner.main(className);
}
public static junit.framework.Test suite() {
	return new TestSuite(SecurityRoleTests.class);
}
public void testPushDownCopy() throws Exception {

	EARFile earFile = getArchiveFactory().openEARFile(AutomatedBVT.baseDirectory + "sample.ear");

	WARFile warFile = (WARFile)earFile.getWARFiles().get(0);

	EJBJarFile ejbJarFile = (EJBJarFile) earFile.getEJBJarFiles().get(0);

	SecurityRole aRole = getCommonFactory().createSecurityRole();
	aRole.setRoleName("administrator");
	aRole.setDescription("The all powerful");
	earFile.getDeploymentDescriptor().getSecurityRoles().add(aRole);
	earFile.pushDownRole(aRole);

	assertTrue("Push down failed for ejb jar", ejbJarFile.getDeploymentDescriptor().getAssemblyDescriptor().getSecurityRoleNamed("administrator") != null);
	assertTrue("Push down failed for war", warFile.getDeploymentDescriptor().getSecurityRoleNamed("administrator") != null);

	earFile.close();

}
public void testRollupAddAndPushDown() throws Exception {

	EARFile earFile = getArchiveFactory().openEARFile(AutomatedBVT.baseDirectory +"sample.ear");

	WebModuleRef webRef = (WebModuleRef)earFile.getWebModuleRefs().get(0);

	EJBModuleRef ejbModRef = (EJBModuleRef) earFile.getEJBModuleRefs().get(0);

	SecurityRole aRole = getCommonFactory().createSecurityRole();
	aRole.setRoleName("Joe");
	aRole.setDescription("New description");
	webRef.getWebApp().getSecurityRoles().add(aRole);

	aRole = getCommonFactory().createSecurityRole();
	aRole.setRoleName("administrator");
	aRole.setDescription("The all powerful");
	
	webRef.getWebApp().getSecurityRoles().add(aRole);

	//Test the rollup
	earFile.rollUpRoles();
	//We cheated here because we already knew there were two roles in the ejb jar
	//one of which is named Joe, and zero roles in the war file
	assertTrue("Roll up not right", earFile.getDeploymentDescriptor().getSecurityRoles().size() == 5);

	aRole = getCommonFactory().createSecurityRole();
	aRole.setRoleName("manager");
	aRole.setDescription("the manager");
	earFile.addCopy(aRole, webRef.getModule());

	//Test the add and rollup
	assertTrue("Add copy not right", earFile.getDeploymentDescriptor().getSecurityRoles().size() == 6 
		&& earFile.getDeploymentDescriptor().containsSecurityRole(aRole.getRoleName())
		&& webRef.getWebApp().containsSecurityRole(aRole.getRoleName()));

	//Test the push down
	earFile.renameSecurityRole("Joe", "guest");

	assertTrue("Push down not right", !earFile.getDeploymentDescriptor().containsSecurityRole("Joe")
		&& earFile.getDeploymentDescriptor().containsSecurityRole("guest")
		&& !webRef.getWebApp().containsSecurityRole("Joe")
		&& webRef.getWebApp().containsSecurityRole("guest")
		&& !ejbModRef.getEJBJar().containsSecurityRole("Joe")
		&& ejbModRef.getEJBJar().containsSecurityRole("guest"));

	earFile.saveAsNoReopen(AutomatedBVT.baseDirectory +"testOutput/SecurityRoleTests/newSample.ear");
	
}
}
