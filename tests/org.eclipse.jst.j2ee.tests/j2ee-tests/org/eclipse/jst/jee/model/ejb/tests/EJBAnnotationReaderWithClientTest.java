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

import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;

/**
 * Test the cases for an EJBProject that has a client project connected with it.
 * The test is for EJBAnnotationReader.
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJBAnnotationReaderWithClientTest extends AbstractAnnotationModelTest {

	private static final String ejbProjectName = EJBAnnotationReaderWithClientTest.class.getSimpleName();
	private static final String earProjectName = ejbProjectName + "ear";
	private static final String clientName = ejbProjectName + "Client";

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(EJBAnnotationReaderWithClientTest.class);
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(ejbProjectName);
		if (!project.exists())
		{
			ProjectUtil.createEARProject(ejbProjectName + "ear", true);
			project = ProjectUtil.createEJBProject(ejbProjectName, earProjectName, clientName,
					J2EEVersionConstants.EJB_3_0_ID, true);
			IProject clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(clientName);
			createProjectContent(project);
			createClientProjectContent(clientProject);
		}
	}

	private static void createClientProjectContent(IProject clientProject) throws Exception {
		IJavaProject javaProject = JavaCore.create(clientProject);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		comFolder.getFolder("sap").create(true, true, null);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(EjbAnnotationReaderTest.class.getSimpleName());
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		comFolder.getFolder("sap").create(true, true, null);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	// @Before
	@Override
	protected void setUp() throws Exception {
		setUpProject();
		super.setUp();
		clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(clientName);
		fixture = new EJBAnnotationReader(facetedProject, clientProject);
	}

	// @After
	@Override
	protected void tearDown() throws Exception {
		((AbstractAnnotationModelProvider) fixture).dispose();
	}

	// @Test
	public void testLocalInterfaceInClient() throws Exception {
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testLocalInterfaceInClient.java");
		IFile interfaceFile = clientProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String beanContent = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testLocalInterfaceInClient implements InterfaceForAddedBean {}";
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";

		// add the bean
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testLocalInterfaceInClient");
		assertNotNull(result);
		assertEquals("InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testLocalInterfaceInClient");
		assertNotNull(result);
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// revert the change
		deleteFileAndUpdate(beanFile);
		// the bean is delete. Not update will occur on the annotation reader
		AbstractTest.deleteFile(interfaceFile);
	}

	/**
	 * The two beans are in the ejb project. The interface is in the client
	 * project. Change this interface to remote.
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testLocalToRemoteInterfaceInClient() throws Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testLocalToRemoteInterfaceInClient1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testLocalToRemoteInterfaceInClient2.java");
		IFile interfaceFile = clientProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testLocalToRemoteInterfaceInClient1 implements InterfaceForAddedBean {}";
		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testLocalToRemoteInterfaceInClient2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		result2 = TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient2");
		assertEquals("com.sap.InterfaceForAddedBean", result2.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());
		assertTrue(result2.getBusinessLocals().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testLocalToRemoteInterfaceInClient2"));
		AbstractTest.deleteFile(interfaceFile);
	}
}
