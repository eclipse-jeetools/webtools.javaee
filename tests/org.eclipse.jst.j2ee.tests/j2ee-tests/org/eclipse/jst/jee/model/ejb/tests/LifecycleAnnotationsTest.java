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
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.common.Result;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationFactoryTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class LifecycleAnnotationsTest extends AbstractAnnotationFactoryTest {

	private IFacetedProject facetedProject;
	
	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(LifecycleAnnotationsTest.class);
		return suite;
	}

	@Override
	protected void setUp() throws Exception {
		setUpProject();
		super.setUp();
		facetedProject =ProjectFacetsManager.create(ResourcesPlugin.getWorkspace().getRoot().getProject(this.getClass().getSimpleName())); 
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(LifecycleAnnotationsTest.class.getSimpleName());
		if (!project.exists())
		{
			project = ProjectUtil.createEJBProject(LifecycleAnnotationsTest.class.getSimpleName(), null,
				J2EEVersionConstants.EJB_3_0_ID, true);
			createProjectContent(project);
		}
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(LifecycleAnnotationsTest.class.getSimpleName());
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	// @Test
	public void testPostActivate() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateful public class testPostActivate implements SessionBeanLocal{"
				+ "@PostActivate public void postActivateMethod() {}"
				+ "@PostActivate protected void protectedMethod() {}" + "@PostActivate void defaultMethod() {}"
				+ "@PostActivate private void privateMethod() {}"
				+ "@PostActivate public String returnTypeMethod() {return null;}"
				+ "@PostActivate public void paramMethod(String param) {}"
				+ "@PostActivate public void exceptionMethod() throws Exception {}"
				+ "@PostActivate public final void finalMethod() {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testPostActivate.java");
		AbstractTest.saveFile(beanFile, beanContent);
		IType type = JavaCore.createCompilationUnitFrom(beanFile).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals("testPostActivate", sessionBean.getEjbName());
		LifecycleCallback postActivateMethod = TestUtils.findLifecycleMethod(sessionBean.getPostActivates(),
				"postActivateMethod");
		assertEquals("com.sap.testPostActivate", postActivateMethod.getLifecycleCallbackClass());
		LifecycleCallback protectedMethod = TestUtils.findLifecycleMethod(sessionBean.getPostActivates(),
				"protectedMethod");
		assertEquals("com.sap.testPostActivate", protectedMethod.getLifecycleCallbackClass());
		LifecycleCallback defaultMethod = TestUtils
				.findLifecycleMethod(sessionBean.getPostActivates(), "defaultMethod");
		assertEquals("com.sap.testPostActivate", defaultMethod.getLifecycleCallbackClass());
		LifecycleCallback privateMethod = TestUtils
				.findLifecycleMethod(sessionBean.getPostActivates(), "privateMethod");
		assertEquals("com.sap.testPostActivate", privateMethod.getLifecycleCallbackClass());
		assertEquals(new Integer(4), new Integer(sessionBean.getPostActivates().size()));

	}

	// @Test
	public void testPrePassivate() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateful public class testPrePassivate implements SessionBeanLocal{"
				+ "@PrePassivate public void prePassivateMethod() {}"
				+ "@PrePassivate protected void protectedMethod() {}" + "@PrePassivate void defaultMethod() {}"
				+ "@PrePassivate private void privateMethod() {}"
				+ "@PrePassivate public String returnTypeMethod() {return null;}"
				+ "@PrePassivate public void paramMethod(String param) {}"
				+ "@PrePassivate public void exceptionMethod() throws Exception {}"
				+ "@PrePassivate public final void finalMethod() {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testPrePassivate.java");
		AbstractTest.saveFile(beanFile, beanContent);
		IType type = JavaCore.createCompilationUnitFrom(beanFile).findPrimaryType();

		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		LifecycleCallback postActivateMethod = TestUtils.findLifecycleMethod(sessionBean.getPrePassivates(),
				"prePassivateMethod");
		assertEquals("com.sap.testPrePassivate", postActivateMethod.getLifecycleCallbackClass());
		LifecycleCallback protectedMethod = TestUtils.findLifecycleMethod(sessionBean.getPrePassivates(),
				"protectedMethod");
		assertEquals("com.sap.testPrePassivate", protectedMethod.getLifecycleCallbackClass());
		LifecycleCallback defaultMethod = TestUtils
				.findLifecycleMethod(sessionBean.getPrePassivates(), "defaultMethod");
		assertEquals("com.sap.testPrePassivate", defaultMethod.getLifecycleCallbackClass());
		LifecycleCallback privateMethod = TestUtils
				.findLifecycleMethod(sessionBean.getPrePassivates(), "privateMethod");
		assertEquals("com.sap.testPrePassivate", privateMethod.getLifecycleCallbackClass());
		assertEquals(new Integer(4), new Integer(sessionBean.getPrePassivates().size()));
	}

	// @Test
	public void testPostConstruct() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testPostConstruct implements SessionBeanLocal {"
				+ "@PostConstruct public void publicMethod() {}"
				+ "@PostConstruct public static void staticMethod() {}"
				+ "@PostConstruct public final void finalMethod(){}" + "@PostConstruct public String returnType() {}"
				+ "@PostConstruct public void paramMethod(String param) {}"
				+ "@PostConstruct public void throwExpception() throws Exception {};" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testPostConstruct.java");
		AbstractTest.saveFile(beanFile, beanContent);
		IType type = JavaCore.createCompilationUnitFrom(beanFile).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals("testPostConstruct", sessionBean.getEjbName());
		LifecycleCallback callback = TestUtils.findLifecycleMethod(sessionBean.getPostConstructs(), "publicMethod");
		assertEquals("com.sap.testPostConstruct", callback.getLifecycleCallbackClass());
		assertEquals(new Integer(1), new Integer(sessionBean.getPostConstructs().size()));

	}

	// @Test
	public void testPreDestroy() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testPreDestroy implements SessionBeanLocal {"
				+ "@PreDestroy public void publicMethod() {}" + "@PreDestroy public static void staticMethod() {}"
				+ "@PreDestroy public final void finalMethod(){}" + "@PreDestroy public String returnType() {}"
				+ "@PreDestroy public void paramMethod(String param) {}"
				+ "@PreDestroy public void throwExpception() throws Exception {};" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testPreDestroy.java");
		AbstractTest.saveFile(beanFile, beanContent);
		IType type = JavaCore.createCompilationUnitFrom(beanFile).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		LifecycleCallback callback = TestUtils.findLifecycleMethod(sessionBean.getPreDestroys(), "publicMethod");
		assertEquals("com.sap.testPreDestroy", callback.getLifecycleCallbackClass());
		assertEquals(new Integer(1), new Integer(sessionBean.getPreDestroys().size()));
	}

	// @Test
	public void testLifecycleOnMessageDriven() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@MessageDriven public class testLifecycleOnMessageDriven implements SessionBeanLocal {"
				+ "@PostConstruct public void postConstructMethod() {}"
				+ "@PreDestroy public void preDestroyMethod() {}" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testLifecycleOnMessageDriven.java");
		AbstractTest.saveFile(beanFile, beanContent);
		IType type = JavaCore.createCompilationUnitFrom(beanFile).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		MessageDrivenBean mdb = (MessageDrivenBean) result.getMainObject();
		assertNotNull(mdb);
		LifecycleCallback callback1 = TestUtils.findLifecycleMethod(mdb.getPostConstructs(), "postConstructMethod");
		assertNotNull(callback1);
		assertEquals("com.sap.testLifecycleOnMessageDriven", callback1.getLifecycleCallbackClass());
		LifecycleCallback callback2 = TestUtils.findLifecycleMethod(mdb.getPreDestroys(), "preDestroyMethod");
		assertNotNull(callback2);
		assertEquals("com.sap.testLifecycleOnMessageDriven", callback2.getLifecycleCallbackClass());
		assertEquals(new Integer(1), new Integer(mdb.getPreDestroys().size()));
		assertEquals(new Integer(1), new Integer(mdb.getPostConstructs().size()));
	}
}
