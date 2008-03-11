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
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.ejb.internal.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJB3MergedModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJB3MergedModelProviderTest extends AbstractAnnotationModelTest {

	private static final String ejbProjectName = EJB3MergedModelProviderTest.class.getSimpleName();
	private static final String earProjectName = ejbProjectName + "ear";
	private static final String clientName = ejbProjectName + "Client";

	@BeforeClass
	public static void setUpProject() throws Exception {
		facetedProject = AbstractTest.createEjbProject(earProjectName, ejbProjectName, clientName);
		clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(clientName);
		createProjectContent();
		createClientProjectContent();
	}

	private static void createClientProjectContent() throws Exception {
		IJavaProject javaProject = JavaCore.create(clientProject);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBeanLocal(fragment);
	}

	@AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(EJB3MergedModelProviderTest.class.getSimpleName());
	}

	private static void createProjectContent() throws Exception {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBean(fragment);
	}

	@Before
	public void setUp() throws Exception {
		fixture = new EJB3MergedModelProvider(facetedProject.getProject());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChangeClientProject() throws Exception {
		IFile dd = facetedProject.getProject().getFile("ejbModule/META-INF/ejb-jar.xml");
		final String ddWithClient = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<ejb-jar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:ejb=\"http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" version=\"3.0\">"
				+ "<ejb-client-jar>" + clientName + ".jar</ejb-client-jar></ejb-jar>";
		final String ddNoClient = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<ejb-jar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:ejb=\"http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" version=\"3.0\">"
				+ "</ejb-jar>";
		// load the model
		fixture.getModelObject();
		/*
		 * From now one the model provider should not listener for changes in
		 * the client project since it is not described in the deployment
		 * descriptor.
		 */
		saveFileAndUpdate(dd, ddNoClient);
		/*
		 * Change the interface. The model should not be updated.
		 */
		final String remoteInterface = "package com.sap;" + "import javax.ejb.Remote;"
				+ "@Remote public interface SessionBeanLocal {}";
		IFile interfaceFile = clientProject.getFile("ejbModule/com/sap/SessionBeanLocal.java");
		AbstractTest.saveFile(interfaceFile, remoteInterface);

		SessionBean bean = TestUtils.getSessionBean(getEJBJar(), "SessionBean");
		assertEquals(new Integer(1), bean.getBusinessLocals().size());
		assertTrue(bean.getBusinessRemotes().isEmpty());

		/*
		 * The deployment descriptor is changed. The bean should now contain the
		 * interface in the remotes list
		 */
		saveFileAndUpdate(dd, ddWithClient);
		bean = TestUtils.getSessionBean(getEJBJar(), "SessionBean");
		assertEquals(new Integer(1), bean.getBusinessRemotes().size());
		assertTrue(bean.getBusinessLocals().isEmpty());

		// revet the changes
		saveFileAndUpdate(facetedProject.getProject().getFile("ejbModule/com/sap/SessionBean.java"), beanContent);
		saveFileAndUpdate(interfaceFile, localInterfaceContent);
	}

	@Test
	public void testAddBeanWithOperation() throws Exception {
		NewSessionBeanClassDataModelProvider provider = new NewSessionBeanClassDataModelProvider();
		IDataModel dataModel = DataModelFactory.createDataModel(provider);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.CLASS_NAME, "testAddBeanWithOperation");
		dataModel.setIntProperty(INewSessionBeanClassDataModelProperties.STATE_TYPE,
				NewSessionBeanClassDataModelProvider.STATE_TYPE_STATELESS_INDEX);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.EJB_NAME, "testAddBeanWithOperation");
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.JAVA_PACKAGE, "com.sap");
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.PROJECT_NAME, facetedProject.getProject()
				.getName());
		AddSessionBeanOperation operation = new AddSessionBeanOperation(dataModel);

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		getFixture().addListener(listener);
		operation.execute(null, null);
		listener.waitForEvents();
		getFixture().removeListener(listener);

		assertEquals(new Integer(2), getEJBJar().getEnterpriseBeans().getSessionBeans().size());
		assertNotNull(TestUtils.getSessionBean(getEJBJar(), "testAddBeanWithOperation"));
	}
}
