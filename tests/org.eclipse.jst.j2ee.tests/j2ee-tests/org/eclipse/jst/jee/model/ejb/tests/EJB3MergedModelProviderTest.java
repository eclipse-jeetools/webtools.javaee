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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestSuite;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.ejb.internal.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.ejb.internal.operations.StateType;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientJarCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientJarCreationDataModelProperties;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.model.internal.Ejb3ModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJB3MergedModelProviderTest extends AbstractAnnotationModelTest {

	private static final String EJB_PROJECT_NAME = EJB3MergedModelProviderTest.class.getSimpleName();
	private static final String EAR_PROJECT_NAME = EJB_PROJECT_NAME + "ear";
	private static final String CLIENT_NAME = EJB_PROJECT_NAME + "Client";

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(EJB3MergedModelProviderTest.class);
		setUpProject();
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		ProjectUtil.createEARProject(EAR_PROJECT_NAME);
		IProject project = ProjectUtil.createEJBProject(EJB_PROJECT_NAME, EAR_PROJECT_NAME, CLIENT_NAME,
				J2EEVersionConstants.EJB_3_0_ID, true);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		IProject clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(CLIENT_NAME);
		createProjectContent(facetedProject.getProject());
		createClientProjectContent(clientProject);
	}

	private static void createClientProjectContent(IProject clientProject) throws Exception {
		IJavaProject javaProject = JavaCore.create(clientProject);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		comFolder.getFolder("sap").create(true, true, null);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBeanLocal(fragment);
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(EJB3MergedModelProviderTest.class.getSimpleName());
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		comFolder.getFolder("sap").create(true, true, null);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBean(fragment);
	}

	// @Before
	public void setUp() throws Exception {
		super.setUp();
		if (facetedProject == null)
			return;
		fixture = ModelProviderManager.getModelProvider(facetedProject.getProject());
		clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(CLIENT_NAME);
	}

	// @After
	public void tearDown() throws Exception {
	}

	// @Test
	public void testAddBeanWithOperation() throws Exception {
		final String ejbProjectName = this.getClass().getSimpleName() + this.getName();
		final String clientName = ejbProjectName + "Client";
		IProject project = ProjectUtil.createEJBProject(ejbProjectName, ejbProjectName + "ear", clientName,
				J2EEVersionConstants.EJB_3_0_ID, true);
		IDataModelOperation operation = createBeanOperation("testAddBeanWithOperation", "com.sap", project.getName());
		IModelProvider provider = ModelProviderManager.getModelProvider(project);

		EJBJar result = (EJBJar) provider.getModelObject();
		assertEquals(new Integer(0), new Integer(result.getEnterpriseBeans().getSessionBeans().size()));
		executeAndWait(operation, provider);

		result = (EJBJar) provider.getModelObject();
		assertEquals(new Integer(1), new Integer(result.getEnterpriseBeans().getSessionBeans().size()));
		assertNotNull(TestUtils.getSessionBean(result, "testAddBeanWithOperation"));
	}

	// @Test
	public void testAddBeanWithOperationPreserveListeners() throws Exception {
		final String ejbProjectName = this.getClass().getSimpleName() + this.getName();
		IProject project = ProjectUtil.createEJBProject(ejbProjectName, null, J2EEVersionConstants.EJB_3_0_ID, true);
		IModelProvider provider = ModelProviderManager.getModelProvider(project);
		IDataModelOperation operation = createBeanOperation("testAddBeanWithOperationPreserveListeners1", "com.sap",
				project.getName());
		SynchronousModelChangedListener preservedListener = new SynchronousModelChangedListener(2);
		provider.addListener(preservedListener);
		executeAndWait(operation, provider);
		assertNotNull(TestUtils.getSessionBean((EJBJar) provider.getModelObject(),
				"testAddBeanWithOperationPreserveListeners1"));

		int oldReceivedEvents = preservedListener.getReceivedEvents().size();
		operation = createBeanOperation("testAddBeanWithOperationPreserveListeners2", "com.sap", project.getName());
		executeAndWait(operation, provider);
		assertNotNull(TestUtils.getSessionBean((EJBJar) provider.getModelObject(),
				"testAddBeanWithOperationPreserveListeners2"));
		assertTrue(preservedListener.getReceivedEvents().size() > oldReceivedEvents);
	}

	// /**
	// * Execute an operation on the model. Change the dd. The listeners should
	// * not be removed. https://bugs.eclipse.org/bugs/show_bug.cgi?id=241496
	// *
	// * @throws Exception
	// */
	// public void testPreserveListenersChangeDD() throws Exception {
	// final String ejbProjectName = this.getClass().getSimpleName() +
	// this.getName();
	// final IProject project = createEjbProjectWithoutClient(ejbProjectName,
	// ejbProjectName + "ear");
	// final IDataModel model = createModelEjbProjectWithClient(ejbProjectName);
	// SynchronousModelChangedListener preserveListener = new
	// SynchronousModelChangedListener(2);
	//
	// IModelProvider provider = ModelProviderManager.getModelProvider(project);
	// provider.addListener(preserveListener);
	// IFile ejbJarXml = project.getFile("ejbModule/META-INF/ejb-jar.xml");
	// String content = TestUtils.getFileContent(ejbJarXml);
	// executeAndWait(model.getDefaultOperation(), provider);
	// assertNotNull(((EJBJar) provider.getModelObject()).getEjbClientJar());
	//
	// int oldEventsSize = preserveListener.getReceivedEvents().size();
	// AbstractTest.saveFile(ejbJarXml, content);
	// assertTrue(preserveListener.waitForEvents());
	// assertTrue(preserveListener.getReceivedEvents().size() > oldEventsSize);
	// assertNull(((EJBJar) provider.getModelObject()).getEjbClientJar());
	// provider.removeListener(preserveListener);
	// }

	private IDataModel createModelEjbProjectWithClient(final String ejbProjectName) {
		final IDataModel model = DataModelFactory.createDataModel(new EjbClientJarCreationDataModelProvider());
		model.setProperty(IEjbClientJarCreationDataModelProperties.EJB_PROJECT_NAME, ejbProjectName);
		model.setProperty(IEjbClientJarCreationDataModelProperties.PROJECT_NAME, CLIENT_NAME + "newName");
		return model;
	}

	private IProject createEjbProjectWithoutClient(final String ejbProjectName, final String earProjectName)
			throws Exception {
		Map<String, Object> facetModelProperties = new HashMap<String, Object>();
		facetModelProperties.put(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, false);
		facetModelProperties.put(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		final IProject project = ProjectUtil.createEJBProject(ejbProjectName, earProjectName, facetModelProperties,
				J2EEVersionConstants.EJB_3_0_ID, true);
		return project;
	}

	public void testCreateClientProjectWithOperation() throws Exception {
		final String ejbProjectName = this.getClass().getSimpleName() + this.getName();
		final IProject project = createEjbProjectWithoutClient(ejbProjectName, ejbProjectName + "ear");
		final IModelProvider provider = new Ejb3ModelProvider(project);
		provider.getModelObject();
		final IDataModel model = createModelEjbProjectWithClient(ejbProjectName);

		executeAndWait(model.getDefaultOperation(), provider);

		EJBJar result = (EJBJar) provider.getModelObject();
		assertEquals(CLIENT_NAME + "newNameClient" + ".jar", result.getEjbClientJar());
	}

	private void executeAndWait(IDataModelOperation operation, IModelProvider provider) throws InterruptedException,
			ExecutionException {
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		operation.execute(null, null);
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);
	}

	// @Test
	// public void testChangeClientProject() throws Exception {
	// final String ejbProjectName = this.getClass().getSimpleName() +
	// this.getName();
	// final String clientName = ejbProjectName + "Client";
	// IProject project = ProjectUtil.createEJBProject(ejbProjectName,
	// ejbProjectName + "ear", clientName,
	// J2EEVersionConstants.EJB_3_0_ID, true);
	//
	// IFile dd =
	// facetedProject.getProject().getFile("ejbModule/META-INF/ejb-jar.xml");
	// final String ddWithClient = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	// +
	// "<ejb-jar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:ejb=\"http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" version=\"3.0\">"
	// + "<ejb-client-jar>" + clientName + ".jar</ejb-client-jar></ejb-jar>";
	// final String ddNoClient = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	// +
	// "<ejb-jar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:ejb=\"http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" version=\"3.0\">"
	// + "</ejb-jar>";
	// // load the model
	// IModelProvider provider = ModelProviderManager.getModelProvider(project);
	//
	// EJBJar result = (EJBJar) provider.getModelObject();
	// /*
	// * From now on the model provider should not listener for changes in
	// * the client project since it is not described in the deployment
	// * descriptor.
	// */
	// saveFileAndUpdate(dd, ddNoClient, provider);
	//
	// /*
	// * Change the interface. The model should not be updated.
	// */
	// final String remoteInterface = "package com.sap;" +
	// "import javax.ejb.Remote;"
	// + "@Remote public interface SessionBeanLocal {}";
	// IFile interfaceFile =
	// clientProject.getFile("ejbModule/com/sap/SessionBeanLocal.java");
	// SynchronousModelChangedListener listener = new
	// SynchronousModelChangedListener(1);
	// provider.addListener(listener);
	// AbstractTest.saveFile(interfaceFile, remoteInterface);
	// assertFalse(listener.waitForEvents());
	// provider.removeListener(listener);
	// result = (EJBJar) provider.getModelObject();
	// SessionBean bean = TestUtils.getSessionBean(result, "SessionBean");
	// assertEquals(new Integer(1), new
	// Integer(bean.getBusinessLocals().size()));
	// assertTrue(bean.getBusinessRemotes().isEmpty());
	//
	// /*
	// * The deployment descriptor is changed. The bean should now contain the
	// * interface in the remotes list
	// */
	// saveFileAndUpdate(dd, ddWithClient, provider);
	// bean = TestUtils.getSessionBean(result, "SessionBean");
	// assertEquals(new Integer(1), new
	// Integer(bean.getBusinessRemotes().size()));
	// assertTrue(bean.getBusinessLocals().isEmpty());
	//
	// // revet the changes
	// saveFileAndUpdate(facetedProject.getProject().getFile("ejbModule/com/sap/SessionBean.java"),
	// beanContent);
	// saveFileAndUpdate(interfaceFile, localInterfaceContent);
	// }

//	/**
//	 * Create an ejb project with a client project. Add a session bean to the
//	 * project. After creating the ejb project a client project is created. The
//	 * modify method of the provider is called passing a runnable for setting
//	 * the ejbClient. This is the current implementation of how a client is
//	 * create.
//	 * 
//	 * The test doing exactly this. Create an ear project. Create and ejb
//	 * project with a client project. Check that the model nows about the
//	 * ejbClient project. Then add a session bean to the ejb project. The
//	 * listener must be notified.
//	 * 
//	 * @throws Exception
//	 */
//	public void testAddSessionBean() throws Exception {
//		final String ejbProjectName = this.getClass().getSimpleName() + this.getName();
//
//		// create the project
//		ProjectUtil.createEARProject(ejbProjectName + "ear", true);
//		IProject project = ProjectUtil.createEJBProject(ejbProjectName, ejbProjectName + "ear", ejbProjectName
//				+ "client", J2EEVersionConstants.EJB_3_0_ID, true);
//		IFolder comFolder = project.getFolder("ejbModule/com");
//		comFolder.create(true, true, null);
//		comFolder.getFolder("sap").create(true, true, null);
//
//		// get the model provider
//		IModelProvider provider = ModelProviderManager.getModelProvider(project);
//		EJBJar result = (EJBJar) provider.getModelObject();
//		assertEquals(ejbProjectName + "Client.jar", result.getEjbClientJar());
//		IDataModelOperation operation = createBeanOperation("testAddSessionBeanClientExists", "com.sap", project
//				.getName());
//		executeAndWait(operation, provider);
//
//		result = (EJBJar) provider.getModelObject();
//		assertNotNull(TestUtils.getSessionBean(result, "testAddSessionBeanClientExists"));
//	}

	protected IDataModelOperation createBeanOperation(String className, String javaPackage, String projectName) {
		NewSessionBeanClassDataModelProvider dataProvider = new NewSessionBeanClassDataModelProvider();
		IDataModel dataModel = DataModelFactory.createDataModel(dataProvider);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.CLASS_NAME, className);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.STATE_TYPE, StateType.STATELESS.toString());
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.EJB_NAME, className);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.JAVA_PACKAGE, javaPackage);
		dataModel.setStringProperty(INewSessionBeanClassDataModelProperties.PROJECT_NAME, projectName);
		AddSessionBeanOperation operation = new AddSessionBeanOperation(dataModel);
		return operation;
	}
}
