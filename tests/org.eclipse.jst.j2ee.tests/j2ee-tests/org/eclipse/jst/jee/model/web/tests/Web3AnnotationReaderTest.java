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
package org.eclipse.jst.jee.model.web.tests;

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
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jee.model.internal.WebAnnotationReader;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class Web3AnnotationReaderTest extends AbstractAnnotationModelTest {

	private static final String TEST_URL_VALUE = "/testUrl";
	private static final String SERVLET_WITH_URL = "ServletWithUrl";
	private static final String SERVLET_NO_ARGUMENTS = "ServletNoArguments";
	private static final String SERVLET_ANNOTATED_NAME = "ServletNameChange";
	
	private static String BEAN_WITH_NAME = "package com.sap;" + "import javax.ejb.Stateless;"
			+ "@Stateless(name=\"%s\") public class BeanWithName implements SessionBeanLocal {}";

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(Web3AnnotationReaderTest.class);
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(Web3AnnotationReaderTest.class.getSimpleName());
		if (!project.exists())
		{
			IFacetedProject facetedProject = AbstractTest.createWebProject(Web3AnnotationReaderTest.class.getSimpleName(), IJ2EEFacetConstants.DYNAMIC_WEB_30, JavaFacetUtils.JAVA_60);
			createProjectContent(facetedProject.getProject());
		}
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(Web3AnnotationReaderTest.class.getSimpleName());
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("src/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createServlet3(fragment, SERVLET_NO_ARGUMENTS, null, null);
		createServlet3(fragment, SERVLET_ANNOTATED_NAME, "test", null);
		createServlet3(fragment, SERVLET_WITH_URL, null, TEST_URL_VALUE);
		createServletWithSecurity(fragment);
	}
	
	private static void createServletWithSecurity(IPackageFragment fragment) throws Exception {
		final String servletContent = "package com.sap;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "@DeclareRoles(value = {\"role1\", \"role2\"})  public class ServletWithSecurity extends HttpServlet {}";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path("ServletWithSecurity.java"));
		AbstractTest.saveFile(file, servletContent);
	}
	
	private static void createServlet3(IPackageFragment fragment, String className, String name, String urlPattern) throws Exception {
		String params = "";
		if (name != null){
			params="name=\""+name+"\"";
		}
		if (urlPattern != null){
			if (params.length() > 0){
				params+=", ";
			}
			params+="urlPatterns=\""+urlPattern+"\"";
		}
		if(params.length() > 0){
			params="(" + params+ ")";
		}
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;" + "@WebServlet"+params+" public class "+className+" extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};"
				+ "@Resource private Comparable comp2; @Resource public void setComparable2(Comparable comp){} }";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path(className+".java"));
		AbstractTest.saveFile(file, content);
	}

	private WebApp ddApp;

	// @Before
	@Override
	protected void setUp() throws Exception {
		setUpProject();
		super.setUp();
		ddApp = WebFactory.eINSTANCE.createWebApp();
		addServlet(ddApp, SERVLET_NO_ARGUMENTS, "com.sap.Servlet1");
		fixture = new WebAnnotationReader(facetedProject, ddApp);
	}

	// @After
	@Override
	protected void tearDown() throws Exception {
		((WebAnnotationReader) fixture).dispose();
	}

	// @Tets
	public void testAddDeleteEjbRef() throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;" + "@WebServlet public class testAddDeleteEjbRef extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testAddDeleteEjbRef.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getEjbLocalRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getEjbLocalRefs().size()));
	}

	/**
	 * The servlet is not described in the web.xml. It is not in the deployment
	 * descriptor model.
	 * 
	 * @throws Exception
	 */
	// @Tets
	public void testNotAServletAddDeleteEjbRef() throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "public class testNotAServletAddDeleteEjbRef {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testNotAServletAddDeleteEjbRef.java");
		AbstractTest.saveFile(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getEjbLocalRefs().size()));

		AbstractTest.deleteFile(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getEjbLocalRefs().size()));
	}

	// @Tets
	public void testAddDeleteResourceRef() throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "@WebServlet public class testAddDeleteResourceRef extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@Resource private Comparable comp2; @Resource public void setComparable2(Comparable comp){} }";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testAddDeleteResourceRef.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));
		deleteFileAndUpdate(file);

		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
	}

	// @Tets
	public void testListener() throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "@WebListener public class testListener {"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};"
				+ "@Resource private Comparable comp2; @Resource public void setComparable2(Comparable comp){} }";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testListener.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));
		assertEquals(new Integer(8), new Integer(result.getEjbLocalRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
		assertEquals(new Integer(6), new Integer(result.getEjbLocalRefs().size()));
	}

	// @Tets
	public void testFilter() throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "@WebFilter public class testFilter {"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};"
				+ "@Resource private Comparable comp2; @Resource public void setComparable2(Comparable comp){} }";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testFilter.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));
		assertEquals(new Integer(8), new Integer(result.getEjbLocalRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
		assertEquals(new Integer(6), new Integer(result.getEjbLocalRefs().size()));

	}

	// @Tets
	public void testResourcesServlet() throws Exception {
		final String content = "package com.sap;"
				+ "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "@Resources(value = {"
				+ "@Resource(name = \"withNotDefaults\", mappedName = \"mappedName\", "
				+ "	shareable = false, type = java.lang.Comparable.class, "
				+ "	authenticationType = AuthenticationType.APPLICATION, description = \"description\"),"
				+ "@Resource(name = \"withDefaults\", type = java.lang.Comparable.class), @Resource(name = \"invalidNoType\") })"
				+ "@WebServlet public class testResourcesServlet {}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testResourcesServlet.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
	}

	// @Tets
	public void testResourcesFilter() throws Exception {
		final String content = "package com.sap;"
				+ "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "@Resources(value = {"
				+ "@Resource(name = \"withNotDefaults\", mappedName = \"mappedName\", "
				+ "	shareable = false, type = java.lang.Comparable.class, "
				+ "	authenticationType = AuthenticationType.APPLICATION, description = \"description\"),"
				+ "@Resource(name = \"withDefaults\", type = java.lang.Comparable.class), @Resource(name = \"invalidNoType\") })"
				+ "@WebFilter public class testResourcesFilter {}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testResourcesFilter.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
	}

	// @Tets
	public void testResourcesOnListener() throws Exception {
		final String content = "package com.sap;"
				+ "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "@Resources(value = {"
				+ "@Resource(name = \"withNotDefaults\", mappedName = \"mappedName\", "
				+ "	shareable = false, type = java.lang.Comparable.class, "
				+ "	authenticationType = AuthenticationType.APPLICATION, description = \"description\"),"
				+ "@Resource(name = \"withDefaults\", type = java.lang.Comparable.class), @Resource(name = \"invalidNoType\") })"
				+ "@WebListener public class testResourcesOnListener {}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testResourcesOnListener.java");
		saveFileAndUpdate(file, content);

		WebApp result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(file);
		result = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(result.getResourceRefs().size()));
	}

	// @Tets
	public void testDeclareRoles() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@DeclareRoles(value = {\"testDeclareRoles1\", \"testDeclareRoles1\"}) "
				+ "@WebServlet public class testDeclareRoles extends HttpServlet {}";
		IFile servletFile = facetedProject.getProject().getFile("src/com/sap/testDeclareRoles.java");
		saveFileAndUpdate(servletFile, beanContent);

		WebApp app = (WebApp) fixture.getModelObject();
		Servlet result = TestUtils.findServletByName(app, "testDeclareRoles");
		assertNotNull(result);
		SecurityRole role1 = TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoles1");
		SecurityRole role2 = TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoles1");
		assertNotNull(role1);
		assertNotNull(role2);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "testDeclareRoles1");
		assertNotNull(role1Ref);
		SecurityRoleRef role2Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "testDeclareRoles1");
		assertNotNull(role2Ref);

		deleteFileAndUpdate(servletFile);
		app = (WebApp) fixture.getModelObject();
		assertNull(TestUtils.findServletByName(app, "testDeclareRoles"));
		assertNull(TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoles1"));
		assertNull(TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoles1"));
	}

	// @Tets
	public void testDeclareRoleOnManyServlets() throws Exception {
		final String servlet1Content = "package com.sap;"
				+ "@DeclareRoles(value = {\"testDeclareRoleOnManyBeansRole\"}) "
				+ "@WebServlet public class testDeclareRoleOnManyServlets1 implements SessionBeanLocal {}";
		final String servlet2Content = "package com.sap;"
				+ "@DeclareRoles(value = {\"testDeclareRoleOnManyBeansRole\"}) "
				+ "@WebServlet public class testDeclareRoleOnManyServlets2 implements SessionBeanLocal {}";
		IFile bean1File = facetedProject.getProject().getFile("src/com/sap/testDeclareRoleOnManyServlets1.java");
		IFile bean2File = facetedProject.getProject().getFile("src/com/sap/testDeclareRoleOnManyServlets2.java");
		saveFileAndUpdate(bean1File, servlet1Content);
		saveFileAndUpdate(bean2File, servlet2Content);
		WebApp app = (WebApp) fixture.getModelObject();
		Servlet result = TestUtils.findServletByName(app, "testDeclareRoleOnManyServlets1");
		SecurityRole role1 = TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoleOnManyBeansRole");
		assertNotNull(role1);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(),
				"testDeclareRoleOnManyBeansRole");
		assertNotNull(role1Ref);

		deleteFileAndUpdate(bean1File);
		assertNotNull(TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoleOnManyBeansRole"));
		deleteFileAndUpdate(bean2File);
		assertNull(TestUtils.findSecurityRole(app.getSecurityRoles(), "testDeclareRoleOnManyBeansRole"));
	}

	// @Tets
	public void testRunAs() throws Exception {
		final String servletContent = "package com.sap;"
				+ "@DeclareRoles(value = {\"testRunAsRole\"}) @RunAs(value = \"testRunAsRole\") "
				+ "@WebServlet public class testRunAs{}";

		IFile servletFile = facetedProject.getProject().getFile("src/com/sap/testRunAs.java");
		saveFileAndUpdate(servletFile, servletContent);
		WebApp app = (WebApp) fixture.getModelObject();
		Servlet result = TestUtils.findServletByName(app, "testRunAs");
		SecurityRole role1 = TestUtils.findSecurityRole(app.getSecurityRoles(), "testRunAsRole");
		assertNotNull(role1);
		SecurityRoleRef role1Ref = TestUtils.findSecurityRoleRef(result.getSecurityRoleRefs(), "testRunAsRole");
		assertNotNull(role1Ref);
		RunAs runAs = (RunAs) result.getRunAs();
		assertNotNull(runAs);
		assertEquals("testRunAsRole", runAs.getRoleName());

		deleteFileAndUpdate(servletFile);
		assertNull(TestUtils.findSecurityRole(((WebApp) fixture.getModelObject()).getSecurityRoles(), "testRunAsRole"));
	}

	// @Tets
	public void testAddEjbRefToServlet() throws Exception {
		final String content1 = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "@WebServlet public class testAddEjbRefToServlet extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;}";
		final String content2 = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "@WebServlet public class testAddEjbRefToServlet extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};}";
		IFile file = facetedProject.getProject().getFile("src/com/sap/testAddEjbRefToServlet.java");
		saveFileAndUpdate(file, content1);
		WebApp app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(app.getEjbLocalRefs().size()));

		saveFileAndUpdate(file, content2);
		app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(8), new Integer(app.getEjbLocalRefs().size()));

		deleteFileAndUpdate(file);
		app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(6), new Integer(app.getEjbLocalRefs().size()));
	}

	private static void addServlet(WebApp app, String servletName, String servletClass) {
		Servlet servlet = WebFactory.eINSTANCE.createServlet();
		servlet.setServletName(servletName);
		servlet.setServletClass(servletClass);
		app.getServlets().add(servlet);
	}

}
