/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.ejb.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class SecurityRolesTest extends AbstractAnnotationModelTest {

	@BeforeClass
	public static void setUpProject() throws Exception {
		facetedProject = AbstractTest.createProject(SecurityRolesTest.class.getSimpleName());
		createProjectContent();
	}

	@AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(SecurityRolesTest.class.getSimpleName());
	}

	private static void createProjectContent() throws Exception {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	@Before
	public void setUp() throws Exception {
		fixture = new EJBAnnotationReader(facetedProject, clientProject);
	}

	@After
	public void tearDown() throws Exception {
		((AbstractAnnotationModelProvider)fixture).dispose();
	}

	@Test
	public void testDeclareRoles() throws Exception {
		final String beanContent = "package com.sap;" + "@DeclareRoles(value = {\"role1\", \"role2\"}) "
				+ "@Stateless public class testDeclareRoles implements SessionBeanLocal {}";

		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testDeclareRoles.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testDeclareRoles");
		SecurityRole role1 = TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1");
		SecurityRole role2 = TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role2");
		assertNotNull(role1);
		assertNotNull(role2);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "role1");
		assertNotNull(role1Ref);
		SecurityRoleRef role2Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "role2");
		assertNotNull(role2Ref);

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testDeclareRoles"));
		assertNull(TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1"));
		assertNull(TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role2"));
	}

	@Test
	public void testDeclareRoleOnManyBeans() throws Exception {
		final String bean1Content = "package com.sap;" + "@DeclareRoles(value = {\"role1\"}) "
				+ "@Stateless public class testDeclareRoleOnManyBeans1 implements SessionBeanLocal {}";
		final String bean2Content = "package com.sap;" + "@DeclareRoles(value = {\"role1\"}) "
				+ "@Stateless public class testDeclareRoleOnManyBeans2 implements SessionBeanLocal {}";

		IFile bean1File = facetedProject.getProject().getFile("ejbModule/com/sap/testDeclareRoleOnManyBeans1.java");
		IFile bean2File = facetedProject.getProject().getFile("ejbModule/com/sap/testDeclareRoleOnManyBeans2.java");
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testDeclareRoleOnManyBeans1");
		SecurityRole role1 = TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1");
		assertNotNull(role1);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "role1");
		assertNotNull(role1Ref);

		deleteFileAndUpdate(bean1File);
		assertNotNull(TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1"));
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1"));
	}

	@Test
	public void testRunAs() throws Exception {
		final String beanContent = "package com.sap;" + "@DeclareRoles(value = {\"role1\"}) @RunAs(value = \"role1\") "
				+ "@Stateless public class testRunAs implements SessionBeanLocal {}";

		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testRunAs.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testRunAs");
		SecurityRole role1 = TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1");
		assertNotNull(role1);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "role1");
		assertNotNull(role1Ref);
		SecurityIdentityType identity = (SecurityIdentityType) result.getSecurityIdentities();
		assertNotNull(identity);
		RunAs runAs = identity.getRunAs();
		assertNotNull(runAs);
		assertEquals("role1", runAs.getRoleName());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.findSecurityRole(getEJBJar().getAssemblyDescriptor().getSecurityRoles(), "role1"));
	}

	@Test
	public void testRunAsMessageBean() throws Exception {
		final String beanContent = "package com.sap;" + "@RunAs(value = \"role1\") "
				+ "@MessageDriven public class testRunAsMessageBean implements SessionBeanLocal {}";

		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testRunAsMessageBean.java");
		saveFileAndUpdate(beanFile, beanContent);
		MessageDrivenBean result = TestUtils.getMessageDrivenBean(getEJBJar(),
				"testRunAsMessageBean");
		SecurityIdentityType identity = (SecurityIdentityType) result.getSecurityIdentity();
		assertNotNull(identity);
		RunAs runAs = identity.getRunAs();
		assertNotNull(runAs);
		assertEquals("role1", runAs.getRoleName());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getMessageDrivenBean(getEJBJar(), "testRunAsMessageBean"));
	}

}
