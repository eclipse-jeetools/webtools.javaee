/***********************************************************************
 * Copyright (c) 2007 by SAP AG, Walldorf. http://www.sap.com All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of SAP AG,
 * Walldorf. You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered
 * into with SAP.
 * 
 * $Id: EjbAnnotationReaderTest.java,v 1.7 2010/11/23 14:01:47 jsholl Exp $
 ***********************************************************************/
package org.eclipse.jst.jee.model.ejb.tests;

import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.ProjectUtility;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EjbAnnotationReaderTest extends AbstractAnnotationModelTest {

	private static String BEAN_WITH_NAME = "package com.sap;" + "import javax.ejb.Stateless;"
			+ "@Stateless(name=\"%s\") public class BeanWithName implements SessionBeanLocal {}";

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(EjbAnnotationReaderTest.class);
		return suite;
	}

	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				EjbAnnotationReaderTest.class.getSimpleName());
		if (!project.exists()) {
			project = ProjectUtil.createEJBProject(EjbAnnotationReaderTest.class.getSimpleName(), null,
					J2EEVersionConstants.EJB_3_0_ID, true);
			createProjectContent(project);
		}
	}

	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(EjbAnnotationReaderTest.class.getSimpleName());
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBean(fragment);
		createSessionBeanLocal(fragment);
		createBeanWithName(fragment);
		createMessageDrivenBean(fragment);
		createInvalidCompilationUnit(fragment);
	}

	private static void createBeanWithName(IPackageFragment fragment) throws JavaModelException {
		String content = String.format(BEAN_WITH_NAME, "beanWithName");
		fragment.createCompilationUnit("BeanWithName.java", content, true, null);
	}

	/**
	 * The word "class" is missed and the compilation unit does not contain a
	 * valid type.
	 * 
	 * @param fragment
	 * @throws Exception
	 */
	private static void createInvalidCompilationUnit(IPackageFragment fragment) throws Exception {
		final String content = "package com.sap;"
				+ "@Stateless public InvalidCompilationUnit implements SessionBeanLocal {}";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path("InvalidCompilationUnit.java"));
		AbstractTest.saveFile(file, content);
	}

	private static void createMessageDrivenBean(IPackageFragment fragment) throws Exception {
		final String content = "package com.sap;" + "import javax.ejb.MessageDriven;"
				+ "@MessageDriven public class MessageDrivenBean {}";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path("MessageDrivenBean.java"));
		AbstractTest.saveFile(file, content);
	}

	@Override
	protected void setUp() throws Exception {
		ProjectUtility.deleteAllProjects();
		setUpProject();
		super.setUp();
		fixture = new EJBAnnotationReader(facetedProject, clientProject);
	}

	@Override
	protected void tearDown() throws Exception {
		((AbstractAnnotationModelProvider) fixture).dispose();
	}

	// @Test(expected = IllegalArgumentException.class)
	public void testCerateReaderWithNullProject() {
		try {
			new EJBAnnotationReader(null, null);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testLoadForEmptyProject() throws Exception {
		String projectName = EjbAnnotationReaderTest.class.getSimpleName() + "testLoadForEmptyProject";
		IProject emptyProject = ProjectUtil.createEJBProject(projectName, null, J2EEVersionConstants.EJB_3_0_ID, true);
		IModelProvider provider = new EJBAnnotationReader(ProjectFacetsManager.create(emptyProject), null);
		EJBJar ejbJar = (EJBJar) provider.getModelObject();
		assertNull(ejbJar.getEnterpriseBeans());
		AbstractTest.deleteProject(projectName);
	}

	public void testNullEnterpriseBeanAfterDelete() throws Exception {
		final String projectName = EjbAnnotationReaderTest.class.getSimpleName() + "testNullEnterpriseBeanAfterDelete";
		IProject oneBeanProject = ProjectUtil
				.createEJBProject(projectName, null, J2EEVersionConstants.EJB_3_0_ID, true);
		facetedProject = ProjectFacetsManager.create(oneBeanProject);
		fixture = new EJBAnnotationReader(facetedProject, clientProject);

		IFile beanFile = oneBeanProject.getFile("ejbModule/testNullEnterpriseBeanAfterDelete.java");
		assertFalse(beanFile.exists());
		final String content = "import javax.ejb.Stateless;"
				+ "@Stateless public class testNullEnterpriseBeanAfterDelete implements SessionBeanLocal {}";
		// add the file
		saveFileAndUpdate(beanFile, content);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testNullEnterpriseBeanAfterDelete");
		assertNotNull(result);
		// delete the file
		deleteFileAndUpdate(beanFile);
		assertNull(getEJBJar().getEnterpriseBeans());
		AbstractTest.deleteProject(projectName);
	}

	// @Test
	public void testGetSessionBeans() {
		EnterpriseBeans beans = getEJBJar().getEnterpriseBeans();
		assertNotNull(beans);
		assertEquals(new Integer(2), new Integer(beans.getSessionBeans().size()));
		assertEquals(new Integer(1), new Integer(getEJBJar().getEnterpriseBeans().getMessageDrivenBeans().size()));
	}

	// @Test
	public void testSessionBeanName() {
		List<SessionBean> beans = getEJBJar().getEnterpriseBeans().getSessionBeans();
		SessionBean bean = TestUtils.getSessionBean(getEJBJar(), "SessionBean");
		assertEquals("com.sap.SessionBean", bean.getEjbClass());
		assertEquals("SessionBean", bean.getEjbName());
	}

	// @Test
	public void testMessageDrivenBean() {
		MessageDrivenBean bean = TestUtils.getMessageDrivenBean(getEJBJar(), "MessageDrivenBean");
		assertNotNull(bean);
		assertEquals("com.sap.MessageDrivenBean", bean.getEjbClass());
		assertEquals("MessageDrivenBean", bean.getEjbName());
	}

	// @Test
	public void testBeanWithName() {
		SessionBean bean = TestUtils.getSessionBean(getEJBJar(), "beanWithName");
		assertNotNull(bean);
		assertEquals("com.sap.BeanWithName", bean.getEjbClass());
		assertEquals("beanWithName", bean.getEjbName());
	}

	// @Test
	public void testChange() throws Exception {
		IFile file = facetedProject.getProject().getFile("ejbModule/com/sap/BeanWithName.java");
		final String newContent = String.format(BEAN_WITH_NAME, "newBeanName");

		saveFileAndUpdate(file, newContent);

		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "newBeanName");
		assertNotNull(result);

		// revert the change
		final String oldContent = String.format(BEAN_WITH_NAME, "beanWithName");
		saveFileAndUpdate(file, oldContent);
		assertNotNull(TestUtils.getSessionBean(getEJBJar(), "beanWithName"));
	}

	// @Test
	public void testDeleteBean() throws Exception {
		IFile file = facetedProject.getProject().getFile("ejbModule/com/sap/BeanWithName.java");
		deleteFileAndUpdate(file);
		assertEquals(new Integer(1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "beanWithName"));

		// revert the change
		final String content = String.format(BEAN_WITH_NAME, "beanWithName");
		saveFileAndUpdate(file, content);
		assertNotNull(TestUtils.getSessionBean(getEJBJar(), "beanWithName"));
	}

	// @Test
	public void testAddDeleteSessionBean() throws Exception {
		int oldSize = getEJBJar().getEnterpriseBeans().getSessionBeans().size();
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testAddDeleteSessionBean.java");
		assertFalse(beanFile.exists());
		final String content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAddDeleteSessionBean implements SessionBeanLocal {}";
		// add the file
		saveFileAndUpdate(beanFile, content);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteSessionBean");
		assertNotNull(result);

		// delete the file
		deleteFileAndUpdate(beanFile);
		assertEquals(new Integer(oldSize), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteSessionBean"));
	}

	// @Test
	public void testEventsNumber1() throws Exception {
		final String content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testEventsNumber1 implements SessionBeanLocal {}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testEventsNumber1.java");
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		getFixture().addListener(listener);
		saveFileAndUpdate(beanFile, content);
		assertTrue(listener.waitForEvents());
		getFixture().removeListener(listener);
		assertEquals(new Integer(1), new Integer(listener.getReceivedEvents().size()));
	}

	// @Test
	public void testEventsNumber2() throws Exception {
		final String content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testEventsNumber2 implements SessionBeanLocal {}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testEventsNumber2.java");
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(2);
		getFixture().addListener(listener);
		saveFileAndUpdate(beanFile, content);
		assertFalse(listener.waitForEvents());
		getFixture().removeListener(listener);
		assertEquals(new Integer(1), new Integer(listener.getReceivedEvents().size()));

	}

	// @Test
	public void testDispose() throws Exception {
		AbstractAnnotationModelProvider reader = new EJBAnnotationReader(facetedProject, null);
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		reader.addListener(listener);
		reader.dispose();
		assertTrue(listener.waitForEvents());
	}

	// @Test
	public void testAddBeanThenInterface() throws Exception {
		int oldSize = getEJBJar().getEnterpriseBeans().getSessionBeans().size();
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testAddBeanThenInterface.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String beanContent = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAddBeanThenInterface implements InterfaceForAddedBean {}";
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";

		// add the bean
		saveFileAndUpdate(beanFile, beanContent);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterface");
		assertNotNull(result);
		assertEquals("InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface
		saveFileAndUpdate(interfaceFile, interfaceContent);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterface");
		assertNotNull(result);
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// revert the change
		deleteFileAndUpdate(beanFile);
		// the bean is delete. Not update will occur on the annotation reader
		AbstractTest.deleteFile(interfaceFile);
	}

	// @Test
	public void testAddBeanThenInterfaceThenRemote() throws Exception {
		int oldSize = getEJBJar().getEnterpriseBeans().getSessionBeans().size();
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAddBeanThenInterfaceThenRemote.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String beanContent = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAddBeanThenInterfaceThenRemote implements InterfaceForAddedBean {}";

		// add the bean. The interface is in the local list.
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterfaceThenRemote");
		assertNotNull(result);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		assertEquals("InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterfaceThenRemote");
		assertNotNull(result);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessLocals().get(0));
		assertTrue(result.getBusinessRemotes().isEmpty());

		// change the interface to remote
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterfaceThenRemote");
		assertNotNull(result);
		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getSessionBeans().size()));
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessRemotes().get(0));
		assertTrue(result.getBusinessLocals().isEmpty());

		// revert the change
		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenInterfaceThenRemote"));
		AbstractTest.deleteFile(interfaceFile);
	}

	// @Test
	public void testAddBeanThenRemoteThenLocal() throws Exception {
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testAddBeanThenRemoteThenLocal.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String beanContent = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAddBeanThenRemoteThenLocal implements InterfaceForAddedBean {}";

		// add the bean. The interface is in the local list.
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenRemoteThenLocal");
		assertNotNull(result);
		assertEquals("InterfaceForAddedBean", result.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to remote interfaces.
		final String interfaceContent = "package com.sap;" + "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenRemoteThenLocal");
		assertNotNull(result);
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessRemotes().get(0));
		assertTrue(result.getBusinessLocals().isEmpty());

		// change the interface to local
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Local public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenRemoteThenLocal");
		assertNotNull(result);
		assertEquals("com.sap.InterfaceForAddedBean", result.getBusinessLocals().get(0));
		assertTrue(result.getBusinessRemotes().isEmpty());

		// revert the change
		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddBeanThenRemoteThenLocal"));
		AbstractTest.deleteFile(interfaceFile);
	}

	/**
	 * Add a bean. Then add a local interface. The interface has a local
	 * annotation. Change the annotation of the interface to remote. The two
	 * beans should be rebuilded and the interface should now be in the remote
	 * business interfaces.
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	// @Test
	public void testAdd2BeansThenInterfaceThenRemote() throws InterruptedException, Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote2.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";
		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("com.sap.InterfaceForAddedBean", result2.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());
		assertTrue(result2.getBusinessLocals().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2"));
		AbstractTest.deleteFile(interfaceFile);
	}

	// @Test
	public void testBeanWithSourceAndBinaryInterface() throws Exception {
		final String beanContent = "package com.sap; "
				+ "@Stateless @Local({java.util.List.class, SessionBeanLocal.class}) public class testBeanWithSourceAndBinaryInterface {}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testBeanWithSourceAndBinaryInterface.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testBeanWithSourceAndBinaryInterface");
		assertNotNull(result);

		assertTrue(result.getBusinessLocals().contains("com.sap.SessionBeanLocal"));
		assertTrue(result.getBusinessLocals().contains("java.util.List"));

		// revert the change
		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testBeanWithSourceAndBinaryInterface"));
	}

	// @Test
	public void testAddDeleteMessageBean() throws Exception {
		int oldSize = getEJBJar().getEnterpriseBeans().getMessageDrivenBeans().size();
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		IFile file = facetedProject.getProject().getFile("ejbModule/com/sap/AddedMessageBean.java");
		assertFalse(file.exists());
		final String content = "package com.sap;" + "import javax.ejb.MessageDriven;"
				+ "@MessageDriven public class AddedMessageBean {}";
		getFixture().addListener(listener);

		// add the file
		AbstractTest.saveFile(file, content);
		assertTrue(listener.waitForEvents());
		getFixture().removeListener(listener);

		assertEquals(new Integer(oldSize + 1), new Integer(getEJBJar().getEnterpriseBeans().getMessageDrivenBeans()
				.size()));
		MessageDrivenBean result = TestUtils.getMessageDrivenBean(getEJBJar(), "AddedMessageBean");
		assertNotNull(result);

		// delete the file
		listener = new SynchronousModelChangedListener(1);
		getFixture().addListener(listener);
		AbstractTest.deleteFile(file);
		assertTrue(listener.waitForEvents());
		getFixture().removeListener(listener);

		assertEquals(new Integer(oldSize), new Integer(getEJBJar().getEnterpriseBeans().getMessageDrivenBeans().size()));
		assertNull(TestUtils.getMessageDrivenBean(getEJBJar(), "AddedMessageBean"));
	}

	// @Test
	public void testAddDeleteLocalHome() throws Exception {
		final String beanContent = "package com.sap; "
				+ "@Stateless @LocalHome(value = LocalHomeInterface.class) public class testAddDeleteLocalHome {}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testAddDeleteLocalHome.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteLocalHome");
		assertNotNull(result);
		assertEquals("LocalHomeInterface", result.getLocalHome());

		final String interfaceContent = "package com.sap; " + "public interface LocalHomeInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/LocalHomeInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteLocalHome");
		assertNotNull(result);
		assertEquals("com.sap.LocalHomeInterface", result.getLocalHome());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteLocalHome");
		assertNotNull(result);
		assertEquals("LocalHomeInterface", result.getLocalHome());
		deleteFileAndUpdate(beanFile);

		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteLocalHome"));
	}

	// @Test
	public void testAddDeleteHome() throws Exception {
		final String beanContent = "package com.sap; import javax.ejb.RemoteHome;"
				+ "@Stateless @RemoteHome(value = HomeInterface.class) public class testAddDeleteHome {}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testAddDeleteHome.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteHome");
		assertNotNull(result);
		assertEquals("HomeInterface", result.getHome());

		final String interfaceContent = "package com.sap; " + "public interface HomeInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/HomeInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteHome");
		assertNotNull(result);
		assertEquals("com.sap.HomeInterface", result.getHome());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteHome");
		assertNotNull(result);
		assertEquals("HomeInterface", result.getHome());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteHome"));
	}

	// @Test
	public void testRemoveMethod() throws Exception {
		final String beanContent = "package com.sap"
				+ "@Stateful public class testRemoveMethod implements SessionBeanLocal {"
				+ "@Remove public void removeMethod1() {}"
				+ "@Remove(retainIfException = true) public void removeMethod2() {}"
				+ "@Remove(retainIfException = true) public void removeMethodParam(java.lang.String str) {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testRemoveMethod.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testRemoveMethod");
		assertNotNull(result);
		RemoveMethodType method1 = TestUtils.findRemoveMethodByName(result, "removeMethod1");
		RemoveMethodType method2 = TestUtils.findRemoveMethodByName(result, "removeMethod2");
		assertNotNull(method1);
		assertNotNull(method2);
		assertFalse(method1.isRetainIfException());
		assertTrue(method2.isRetainIfException());
		assertEquals(new Integer(2), new Integer(result.getRemoveMethods().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testRemoveMethod"));
	}

	// @Test
	public void testRemoveMethodOnStateless() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testRemoveMethodOnStateless implements SessionBeanLocal {"
				+ "@Remove public void removeMethod1() {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testRemoveMethodOnStateless.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testRemoveMethodOnStateless");
		assertNotNull(result);
		assertTrue(result.getRemoveMethods().isEmpty());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testRemoveMethodOnStateless"));
	}

	// @Test
	public void testInitMetod() throws Exception {
		final String beanContent = "package com.sap"
				+ "@Stateful @RemoteHome(value = java.lang.Comparable.class) public class testInitMetod implements SessionBeanLocal {"
				+ "@Init public void createMethod1() {}" + "@Init public void createMethod2(String arg0) {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testInitMetod.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testInitMetod");
		assertNotNull(result);
		InitMethodType method1 = TestUtils.findInitMethodByName(result, "createMethod1");
		assertNotNull(method1);
		InitMethodType method2 = TestUtils.findInitMethodByName(result, "createMethod2");
		assertNotNull(method2);
		assertEquals(new Integer(1), new Integer(method2.getBeanMethod().getMethodParams().getMethodParams().size()));
		assertEquals("String", method2.getBeanMethod().getMethodParams().getMethodParams().get(0).toString());
		assertEquals(new Integer(2), new Integer(result.getInitMethods().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testInitMethod"));
	}

	// @Test
	public void testInitMetodStateless() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless @RemoteHome(value = java.lang.Comparable.class) public class testInitMetodStateless implements SessionBeanLocal {"
				+ "@Init public void createMethod1() {}" + "@Init public void createMethod2(String arg0) {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testInitMetodStateless.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testInitMetodStateless");
		assertNotNull(result);
		assertEquals(new Integer(0), new Integer(result.getInitMethods().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testInitMetodStateless"));
	}

	// @Test
	public void testActivationConfigProperty() throws Exception {
		final String beanContent = "package com.sap;" + "@MessageDriven(activationConfig = { "
				+ "@ActivationConfigProperty(propertyName = \"name1\", propertyValue = \"value1\"),"
				+ "@ActivationConfigProperty(propertyName = \"name2\", propertyValue = \"value2\") })"
				+ "public class testActivationConfigProperty {}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testActivationConfigProperty.java");
		saveFileAndUpdate(beanFile, beanContent);
		MessageDrivenBean result = TestUtils.getMessageDrivenBean(getEJBJar(), "testActivationConfigProperty");
		assertNotNull(result);
		ActivationConfigProperty property1 = TestUtils.findActivationConfigProperty(result, "name1");
		ActivationConfigProperty property2 = TestUtils.findActivationConfigProperty(result, "name2");
		assertEquals("value1", property1.getActivationConfigPropertyValue());
		assertEquals("value2", property2.getActivationConfigPropertyValue());
		assertEquals(new Integer(2), new Integer(result.getActivationConfig().getActivationConfigProperties().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testActivationConfigProperty"));
	}

	// @Test
	public void testMoveBeanFile() throws Exception {
		IFile file = facetedProject.getProject().getFile("ejbModule/com/sap/testMoveBeanFile.java");
		final String content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testMoveBeanFile implements		 SessionBeanLocal {}";

		// add the file
		saveFileAndUpdate(file, content);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testMoveBeanFile");
		assertNotNull(result);

		// move the file to a place out of the classpath
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		getFixture().addListener(listener);
		file.move(facetedProject.getProject().getFullPath().append("testMoveBeanFile.java"), true,
				new NullProgressMonitor());
		assertTrue(listener.waitForEvents());
		getFixture().removeListener(listener);

		assertNull(TestUtils.getSessionBean(getEJBJar(), "testMoveBeanFile"));

		// rever the change
		AbstractTest.deleteFile(facetedProject.getProject().getFile("testMoveBeanFile.java"));
	}

	// @Test
	public void testMoveBeanFileToMetainf() throws Exception {
		IFile file = facetedProject.getProject().getFile("ejbModule/com/sap/testMoveBeanFileToMetainf.java");
		final String content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testMoveBeanFileToMetainf implements SessionBeanLocal {}";

		// add the file
		saveFileAndUpdate(file, content);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testMoveBeanFileToMetainf");
		assertNotNull(result);

		// move the file to a place out of the classpath
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		getFixture().addListener(listener);
		IFolder metainfFolder = facetedProject.getProject().getFolder("ejbModule/META-INF");
		file
				.move(metainfFolder.getFullPath().append("testMoveBeanFileToMetainf.java"), true,
						new NullProgressMonitor());
		assertTrue(listener.waitForEvents());
		getFixture().removeListener(listener);

		assertNull(TestUtils.getSessionBean(getEJBJar(), "testMoveBeanFileToMetainf"));

		// revert the change
		AbstractTest.deleteFile(facetedProject.getProject()
				.getFile("ejbModule/META-INF/testMoveBeanFileToMetainf.java"));
	}

	/**
	 * Add a bean. Then add a local interface. The interface has a local
	 * annotation. Change the annotation of the interface to remote. The two
	 * beans should be rebuilded and the interface should now be in the remote
	 * business interfaces.
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	// @Test
	public void testAdd2BeansThenInterfaceThenRemoteThenOverrideWithLocal() throws InterruptedException, Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote2.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean1Content2 = "package com.sap;"
				+ "import javax.ejb.Stateless;"
				+ "@Stateless @Local({InterfaceForAddedBean.class}) public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("com.sap.InterfaceForAddedBean", result2.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());
		assertTrue(result2.getBusinessLocals().isEmpty());

		saveFileAndUpdate(bean1File, bean1Content2);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		assertTrue(result1.getBusinessRemotes().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2"));
		AbstractTest.deleteFile(interfaceFile);
	}

	/**
	 * Add a bean. Then add a local interface. The interface has a local
	 * annotation. Change the annotation of the interface to remote. The two
	 * beans should be rebuilded and the interface should now be in the remote
	 * business interfaces.
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	// @Test
	public void testAdd2BeansThenInterfaceThenLocalThenOverrideWithRemote() throws InterruptedException, Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote2.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean1Content2 = "package com.sap;"
				+ "import javax.ejb.Stateless;"
				+ "@Stateless @Remote({InterfaceForAddedBean.class}) public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote

		saveFileAndUpdate(bean1File, bean1Content2);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2"));
		AbstractTest.deleteFile(interfaceFile);
	}

	/**
	 * Add a bean. Then add a local interface. The interface has a local
	 * annotation. Change the annotation of the interface to remote. The two
	 * beans should be rebuilded and the interface should now be in the remote
	 * business interfaces.
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	// @Test
	public void testAdd2BeansThenInterfaceThenLocalThenOverrideWithLocal() throws InterruptedException, Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote2.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean1Content2 = "package com.sap;"
				+ "import javax.ejb.Stateless;"
				+ "@Stateless @Local({InterfaceForAddedBean.class}) public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote

		saveFileAndUpdate(bean1File, bean1Content2);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		assertTrue(result1.getBusinessRemotes().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2"));
		AbstractTest.deleteFile(interfaceFile);
	}

	/**
	 * Add a bean. Then add a local interface. The interface has a local
	 * annotation. Change the annotation of the interface to remote. The two
	 * beans should be rebuilded and the interface should now be in the remote
	 * business interfaces.
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	// @Test
	public void testAdd2BeansThenInterfaceThenRemoteThenOverrideWithRemote() throws InterruptedException, Exception {
		IFile bean1File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote1.java");
		IFile bean2File = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAdd2BeansThenInterfaceThenRemote2.java");
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InterfaceForAddedBean.java");

		final String bean1Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean1Content2 = "package com.sap;"
				+ "import javax.ejb.Stateless;"
				+ "@Stateless @Remote({InterfaceForAddedBean.class}) public class testAdd2BeansThenInterfaceThenRemote1 implements InterfaceForAddedBean {}";

		final String bean2Content = "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class testAdd2BeansThenInterfaceThenRemote2 implements InterfaceForAddedBean {}";
		// add the bean. The interface is in the local list.
		saveFileAndUpdate(bean1File, bean1Content);
		saveFileAndUpdate(bean2File, bean2Content);
		SessionBean result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("InterfaceForAddedBean", result1.getBusinessLocals().get(0));
		SessionBean result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("InterfaceForAddedBean", result2.getBusinessLocals().get(0));

		// add interface. The bean should now resolve the interface and add it
		// to local interfaces.
		final String interfaceContent = "package com.sap;" + "public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContent);

		// change the interface to remote
		final String interfaceContentRemote = "package com.sap;" + "import javax.ejb.Remote"
				+ "@Remote public interface InterfaceForAddedBean {} ";
		saveFileAndUpdate(interfaceFile, interfaceContentRemote);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		result2 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2");
		assertEquals("com.sap.InterfaceForAddedBean", result2.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());
		assertTrue(result2.getBusinessLocals().isEmpty());

		saveFileAndUpdate(bean1File, bean1Content2);
		result1 = TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1");
		assertEquals("com.sap.InterfaceForAddedBean", result1.getBusinessRemotes().get(0));
		assertTrue(result1.getBusinessLocals().isEmpty());

		// revert the change
		deleteFileAndUpdate(bean1File);
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote1"));
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAdd2BeansThenInterfaceThenRemote2"));
		AbstractTest.deleteFile(interfaceFile);
	}

}
