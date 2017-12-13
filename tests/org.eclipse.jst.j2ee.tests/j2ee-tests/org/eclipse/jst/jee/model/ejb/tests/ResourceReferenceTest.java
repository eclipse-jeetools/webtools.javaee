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
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.ResAuthType;
import org.eclipse.jst.javaee.core.ResSharingScopeType;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class ResourceReferenceTest extends AbstractAnnotationModelTest {

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(ResourceReferenceTest.class);
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(ResourceReferenceTest.class.getSimpleName());
		if (!project.exists())
		{
			IFacetedProject facetedProject = AbstractTest.createEjbProject(ResourceReferenceTest.class.getSimpleName());
			createProjectContent(facetedProject.getProject());
		}
	}

	private static void createProjectContent(IProject project) throws Exception {
		IJavaProject javaProject = JavaCore.create(project);
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
//		AbstractTest.deleteProject(facetedProject.getProject().getName());
	}

	// @Before
	@Override
	protected void setUp() throws Exception {
		setUpProject();
		super.setUp();
		fixture = new EJBAnnotationReader(facetedProject, clientProject);
	}

	// @After
	public void tearDown() throws Exception {
		((AbstractAnnotationModelProvider) fixture).dispose();
	}

	//@Test
	public void testResource() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ "@Resource(name =\"withNotDefaults\", mappedName = \"mappedName\", shareable = false, "
				+ "type = java.lang.Comparable.class, "
				+ "authenticationType = AuthenticationType.CONTAINER, description = \"description\")"
				+ "public class testResource implements SessionBeanLocal{}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResource.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResource");
		assertNotNull(result);
		ResourceRef ref = TestUtils.findResourceRefByName(result.getResourceRefs(), "withNotDefaults");
		assertNotNull(ref);
		assertEquals("description", ((Description) ref.getDescriptions().get(0)).getValue());
		assertEquals("mappedName", ref.getMappedName());
		assertEquals(ResSharingScopeType.UNSHAREABLE_LITERAL, ref.getResSharingScope());
		assertEquals(ResAuthType.CONTAINER_LITERAL, ref.getResAuth());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResource"));
	}

	//@Test
	public void testResourcesDefaults() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ "@Resource(name =\"withDefaults\", mappedName = \"mappedName\","
				+ "type = java.lang.Comparable.class, " + "description = \"description\")"
				+ "public class testResourcesDefaults implements SessionBeanLocal{}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResourcesDefaults.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResourcesDefaults");
		assertNotNull(result);
		ResourceRef ref = TestUtils.findResourceRefByName(result.getResourceRefs(), "withDefaults");
		assertNotNull(ref);
		assertEquals("description", ((Description) ref.getDescriptions().get(0)).getValue());
		assertEquals("mappedName", ref.getMappedName());
		assertEquals("java.lang.Comparable", ref.getResType());
		assertEquals(ResSharingScopeType.SHAREABLE_LITERAL, ref.getResSharingScope());
		assertEquals(ResAuthType.APPLICATION_LITERAL, ref.getResAuth());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResourcesDefaults"));
	}

	//@Test
	public void testResources() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless "
				+ "@Resources(value = {"
				+ "@Resource(name = \"withNotDefaults\", mappedName = \"mappedName\", "
				+ "	shareable = false, type = java.lang.Comparable.class, "
				+ "	authenticationType = AuthenticationType.APPLICATION, description = \"description\"),"
				+ "@Resource(name = \"withDefaults\", type = java.lang.Comparable.class), @Resource(name = \"invalidNoType\") })"
				+ "public class testResources implements SessionBeanLocal{}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResources.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResources");
		assertNotNull(result);
		assertNotNull(TestUtils.findResourceRefByName(result.getResourceRefs(), "withNotDefaults"));
		assertNotNull(TestUtils.findResourceRefByName(result.getResourceRefs(), "withDefaults"));
		assertEquals(new Integer(2), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResources"));
	}

	//@Test
	public void testResourceUnresolved() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless " + "@Resource(name =\"withNotDefaults\","
				+ "type = ResourceInterface.class)"
				+ "public class testResourceUnresolved implements SessionBeanLocal{}";
		// add the bean.
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResourceUnresolved.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResourceUnresolved");
		assertNotNull(result);
		ResourceRef ref = TestUtils.findResourceRefByName(result.getResourceRefs(), "withNotDefaults");
		assertNotNull(ref);
		assertEquals("ResourceInterface", ref.getResType());

		// add the interface. The name should be resolved
		final String interfaceContent = "package com.sap;" + "public interface ResourceInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/ResourceInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testResourceUnresolved");
		assertNotNull(result);
		ref = TestUtils.findResourceRefByName(result.getResourceRefs(), "withNotDefaults");
		assertNotNull(ref);
		assertEquals("com.sap.ResourceInterface", ref.getResType());

		// delete the file. The name is now not resolved.
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testResourceUnresolved");
		assertNotNull(result);
		ref = TestUtils.findResourceRefByName(result.getResourceRefs(), "withNotDefaults");
		assertNotNull(ref);
		assertEquals("ResourceInterface", ref.getResType());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResourceUnresolved"));
	}

	//@Test
	public void testResourceOnField() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ "public class testResourceOnField implements SessionBeanLocal{"
				+ "@Resource(type = java.lang.Comparable.class) private java.lang.Comparable valid;"
				+ "@Resource(type = java.lang.Comparable.class) private int simpleType;"
				+ "@Resource private java.lang.Comparable validButWithoutType;}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResourceOnField.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResourceOnField");
		assertNotNull(result);
		ResourceRef ref1 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnField/valid");
		assertNotNull(ref1);
		assertEquals("java.lang.Comparable", ref1.getResType());
		ResourceRef ref2 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnField/validButWithoutType");
		assertNotNull(ref2);
		assertEquals("java.lang.Comparable", ref2.getResType());
		assertEquals(new Integer(2), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResourceOnField"));
	}

	//@Test
	public void testResourceOnMethod() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ "public class testResourceOnMethod implements SessionBeanLocal{"
				+ "@Resource(type = java.lang.Comparable.class) public void validMethod(Comparable arg0){};"
				+ "@Resource(type = java.lang.Comparable.class) private void simpleType(int arg0) {};"
				+ "@Resource public void validButWithoutType(Comparable arg0){};"
				+ "@Resource public void twoParams(Comparable arg0, Comparable arg1} {};}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testResourceOnMethod.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethod");
		assertNotNull(result);
		ResourceRef ref1 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethod/validMethod");
		assertNotNull(ref1);
		ResourceRef ref2 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethod/validButWithoutType");
		assertNotNull(ref2);
		assertEquals(new Integer(2), new Integer(result.getResourceRefs().size()));

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethod"));
	}

	//@Test
	public void testResourceOnMethodAndFieldUnresolved() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ "public class testResourceOnMethodAndFieldUnresolved implements SessionBeanLocal{"
				+ "@Resource(type = ResourceInterface.class) public void validMethod(ResourceInterface arg0){};"
				+ "@Resource private ResourceInterface valid;}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testResourceOnMethodAndFieldUnresolved.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethodAndFieldUnresolved");
		assertNotNull(result);
		ResourceRef ref1 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/validMethod");
		assertEquals("ResourceInterface", ref1.getResType());
		ResourceRef ref2 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/valid");
		assertEquals("ResourceInterface", ref2.getResType());

		// add the interface. The name should be resolved
		final String interfaceContent = "package com.sap;" + "public interface ResourceInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/ResourceInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethodAndFieldUnresolved");
		assertNotNull(result);
		ref1 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/validMethod");
		ref2 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/valid");
		assertEquals("com.sap.ResourceInterface", ref1.getResType());
		assertEquals("com.sap.ResourceInterface", ref1.getResType());

		// delete the file. The name is now not resolved.
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethodAndFieldUnresolved");
		ref1 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/validMethod");
		ref2 = TestUtils.findResourceRefByName(result.getResourceRefs(),
				"com.sap.testResourceOnMethodAndFieldUnresolved/valid");
		assertEquals("ResourceInterface", ref1.getResType());
		assertEquals("ResourceInterface", ref2.getResType());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testResourceOnMethodAndFieldUnresolved"));
	}

}
