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
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.internal.common.AbstractAnnotationModelProvider;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationModelTest;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.TestUtils;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EjbReferenceTest extends AbstractAnnotationModelTest {

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(EjbReferenceTest.class);
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(EjbReferenceTest.class.getSimpleName());
		if (!project.exists())
		{
			project = ProjectUtil.createEJBProject(EjbReferenceTest.class.getSimpleName(), null,
				J2EEVersionConstants.EJB_3_0_ID, true);
			createProjectContent(project);
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
		AbstractTest.deleteProject(EjbReferenceTest.class.getSimpleName());
	}

	// @Before
	@Override
	protected void setUp() throws Exception {
		setUpProject();
		super.setUp();
		fixture = new EJBAnnotationReader(facetedProject, clientProject);
	}

	// @After
	@Override
	protected void tearDown() throws Exception {
		((AbstractAnnotationModelProvider) fixture).dispose();
	}

	// @Test
	public void testAddDeleteInterfaceEjbOnFieldAnnotation() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless " + " public class AddedBean {"
				+ "@EJB(beanInterface = InjectionInterface.class) " + "private InjectionInterface bean1;" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/AddedBean.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "AddedBean");
		assertNotNull(result);
		assertEquals(new Integer(1), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("InjectionInterface", ref.getLocal());
		assertEquals(new Integer(1), new Integer(ref.getInjectionTargets().size()));
		InjectionTarget injectionTarget = (InjectionTarget) ref.getInjectionTargets().get(0);
		assertEquals("com.sap.AddedBean", injectionTarget.getInjectionTargetClass());
		assertEquals("bean1", injectionTarget.getInjectionTargetName());

		final String interfaceContent = "package com.sap;" + "public interface InjectionInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InjectionInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "AddedBean");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("com.sap.InjectionInterface", ref.getLocal());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "AddedBean");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("InjectionInterface", ref.getLocal());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "AddedBean"));
	}

	// @Test
	public void testManyEjbFields() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless " + " public class testManyEjbFields {"
				+ "@EJB(beanInterface = InjectionInterface.class) private InjectionInterface bean1;"
				+ "@EJB() private InjectionInterface bean2;"
				+ "@EJB(beanInterface = Collection.class) private String invalideBean;"
				+ "@EJB(beanInterface = Collection.class) private int simpelType;" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testManyEjbFields.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbFields");
		assertNotNull(result);
		assertEquals(new Integer(2), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testManyEjbFields/bean1");
		assertNotNull(bean1Reference);
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		EjbLocalRef bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testManyEjbFields/bean2");
		assertNotNull(bean2Reference);
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocal());

		// add the interface
		final String interfaceContent = "package com.sap;" + "public interface InjectionInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InjectionInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbFields");
		assertNotNull(result);
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbFields/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbFields/bean2");
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocal());
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("com.sap.InjectionInterface", bean2Reference.getLocalHome());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbFields");
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbFields/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbFields/bean2");
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testManyEjbFields"));
	}

	// @Test
	public void testAddDeleteInterfaceEjbOnMethodAnnotation() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless "
				+ " public class testAddDeleteInterfaceEjbOnMethodAnnotation {"
				+ "@EJB(beanInterface = InjectionInterface.class) public void setBean1(InjectionInterface inter) {}"
				+ "private InjectionInterface bean1;" + "}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAddDeleteInterfaceEjbOnMethodAnnotation.java");
		// add the bean file
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceEjbOnMethodAnnotation");
		assertNotNull(result);
		assertEquals(new Integer(1), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("InjectionInterface", ref.getLocal());
		assertEquals(new Integer(1), new Integer(ref.getInjectionTargets().size()));
		InjectionTarget injectionTarget = (InjectionTarget) ref.getInjectionTargets().get(0);
		assertEquals("com.sap.testAddDeleteInterfaceEjbOnMethodAnnotation", injectionTarget.getInjectionTargetClass());
		assertEquals("bean1", injectionTarget.getInjectionTargetName());

		// add interface
		final String interfaceContent = "package com.sap;" + "public interface InjectionInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InjectionInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceEjbOnMethodAnnotation");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("com.sap.InjectionInterface", ref.getLocal());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceEjbOnMethodAnnotation");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("InjectionInterface", ref.getLocal());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceEjbOnMethodAnnotation"));
	}

	// @Test
	public void testManyEjbMethods() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless " + " public class testManyEjbMethods {"
				+ "@EJB(beanInterface = InjectionInterface.class) public void setBean1(InjectionInterface bean1){};"
				+ "@EJB() public void setBean2(InjectionInterface bean2){};"
				+ "@EJB(beanInterface = Collection.class) public void setInvalideBean(String invalideBean) {};" + "}";
		IFile beanFile = facetedProject.getProject().getFile("ejbModule/com/sap/testManyEjbMethods.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbMethods");
		assertNotNull(result);
		assertEquals(new Integer(2), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testManyEjbMethods/bean1");
		assertNotNull(bean1Reference);
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		EjbLocalRef bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testManyEjbMethods/bean2");
		assertNotNull(bean2Reference);
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocal());

		// add the interface
		final String interfaceContent = "package com.sap;" + "public interface InjectionInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InjectionInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbMethods");
		assertNotNull(result);
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbMethods/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbMethods/bean2");
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocal());
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("com.sap.InjectionInterface", bean2Reference.getLocalHome());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testManyEjbMethods");
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbMethods/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "com.sap.testManyEjbMethods/bean2");
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testManyEjbMethods"));
	}

	// @Test
	public void testEjbOnMethodAndFieldsOfMessageBean() throws Exception {
		final String beanContent = "package com.sap;" + "@MessageDriven "
				+ " public class testEjbOnMethodAndFieldsOfMessageBean {"
				+ "@EJB(beanInterface = InjectionInterface.class) public void setBean1(InjectionInterface bean1){};"
				+ "@EJB() public InjectionInterface bean2;" + "}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testEjbOnMethodAndFieldsOfMessageBean.java");
		saveFileAndUpdate(beanFile, beanContent);
		MessageDrivenBean result = TestUtils.getMessageDrivenBean(getEJBJar(), "testEjbOnMethodAndFieldsOfMessageBean");
		assertNotNull(result);
		assertEquals(new Integer(2), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean1");
		assertNotNull(bean1Reference);
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		EjbLocalRef bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean2");
		assertNotNull(bean2Reference);
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocal());

		// add the interface
		final String interfaceContent = "package com.sap;" + "public interface InjectionInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/InjectionInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getMessageDrivenBean(getEJBJar(), "testEjbOnMethodAndFieldsOfMessageBean");
		assertNotNull(result);
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean2");
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocal());
		assertEquals("com.sap.InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("com.sap.InjectionInterface", bean2Reference.getLocalHome());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getMessageDrivenBean(getEJBJar(), "testEjbOnMethodAndFieldsOfMessageBean");
		bean1Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean1");
		bean2Reference = TestUtils.findLocalRefByName(result.getEjbLocalRefs(),
				"com.sap.testEjbOnMethodAndFieldsOfMessageBean/bean2");
		assertEquals("InjectionInterface", bean1Reference.getLocal());
		assertEquals("InjectionInterface", bean1Reference.getLocalHome());
		assertEquals("InjectionInterface", bean2Reference.getLocalHome());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testEjbOnMethodAndFieldsOfMessageBean"));
	}

	// @Test
	public void testAddDeleteBeanInterfaceOnEjbAnnotation() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless @EJB(name=\"refName\", beanInterface = BeanInterface.class, "
				+ "beanName=\"beanName\", mappedName=\"mappedName\")"
				+ " public class testAddDeleteBeanInterfaceOnEjbAnnotation {}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAddDeleteBeanInterfaceOnEjbAnnotation.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteBeanInterfaceOnEjbAnnotation");
		assertNotNull(result);
		assertEquals(new Integer(1), new Integer(result.getEjbLocalRefs().size()));
		EjbLocalRef ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("BeanInterface", ref.getLocal());

		final String interfaceContent = "package com.sap;" + "public interface BeanInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/BeanInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteBeanInterfaceOnEjbAnnotation");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("com.sap.BeanInterface", ref.getLocal());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteBeanInterfaceOnEjbAnnotation");
		ref = (EjbLocalRef) result.getEjbLocalRefs().get(0);
		assertEquals("BeanInterface", ref.getLocal());

		deleteFileAndUpdate(beanFile);
		assertFalse(beanFile.exists());
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteBeanInterfaceOnEjbAnnotation"));
	}

	// @Test
	public void testAddDeleteInterfaceOnEjbsAnnotation() throws Exception {
		final String beanContent = "package com.sap;" + "@Stateless " + "@EJBs(value = { "
				+ "@EJB(name=\"comparable\", beanInterface = java.lang.Comparable.class, beanName = \"beanName\"), "
				+ "@EJB(name=\"nonResolved\", beanInterface = BeanInterface.class) }) "
				+ " public class testAddDeleteInterfaceOnEjbsAnnotation {}";
		IFile beanFile = facetedProject.getProject().getFile(
				"ejbModule/com/sap/testAddDeleteInterfaceOnEjbsAnnotation.java");
		saveFileAndUpdate(beanFile, beanContent);
		SessionBean result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceOnEjbsAnnotation");
		assertNotNull(result);
		EjbLocalRef comparable = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "comparable");
		assertNotNull(comparable);
		assertEquals("java.lang.Comparable", comparable.getLocal());
		EjbLocalRef nonResolved = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "nonResolved");
		assertNotNull(nonResolved);
		assertEquals("BeanInterface", nonResolved.getLocal());
		assertEquals(new Integer(2), new Integer(result.getEjbLocalRefs().size()));

		final String interfaceContent = "package com.sap;" + "public interface BeanInterface {}";
		IFile interfaceFile = facetedProject.getProject().getFile("ejbModule/com/sap/BeanInterface.java");
		saveFileAndUpdate(interfaceFile, interfaceContent);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceOnEjbsAnnotation");
		nonResolved = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "nonResolved");
		assertEquals("com.sap.BeanInterface", nonResolved.getLocal());

		// revert the change
		deleteFileAndUpdate(interfaceFile);
		result = TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceOnEjbsAnnotation");
		nonResolved = TestUtils.findLocalRefByName(result.getEjbLocalRefs(), "nonResolved");
		assertEquals("BeanInterface", nonResolved.getLocal());

		deleteFileAndUpdate(beanFile);
		assertNull(TestUtils.getSessionBean(getEJBJar(), "testAddDeleteInterfaceOnEjbsAnnotation"));
	}

}
