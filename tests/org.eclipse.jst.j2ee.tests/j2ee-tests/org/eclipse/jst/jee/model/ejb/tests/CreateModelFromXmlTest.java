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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.EJBAnnotationReader;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class CreateModelFromXmlTest {

	private static IFacetedProject facetedProject;
	private EJBJar fixture;

	@BeforeClass
	public static void setUpProject() throws Exception {
		facetedProject = AbstractTest.createProject(CreateModelFromXmlTest.class.getSimpleName());
		createProjectContent();
	}

	@AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(CreateModelFromXmlTest.class.getSimpleName());
	}

	private static void createProjectContent() throws Exception {
		setEjbJarXmlContent();
	}

	private static void setEjbJarXmlContent() throws IOException, CoreException {
		IFile file = facetedProject.getProject().getFile(new Path("ejbModule/META-INF/ejb-jar.xml"));
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<ejb-jar version=\"3.0\" xmlns=\"http://java.sun.com/xml/ns/javaee\" "
				+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\">"
				+ "<enterprise-beans>" + "		<session>" + "			<ejb-name>com.sap.SessionBean</ejb-name>"
				+ "			<ejb-class>com.sap.SessionBean</ejb-class>" + "			<session-type>Stateless</session-type>"
				+ "		</session>" + "	</enterprise-beans>" + "</ejb-jar>";

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		stream.write(content.getBytes());
		file.setContents(new ByteArrayInputStream(stream.toByteArray()), IResource.FORCE, new NullProgressMonitor());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		fixture = (EJBJar) provider.getModelObject();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCerateReaderWithNullProject() {
		new EJBAnnotationReader(null, null);
	}

	@Test
	public void testGetBeans() {
		assertEquals(new Integer(1), new Integer(fixture.getEnterpriseBeans().getSessionBeans().size()));
	}

	@Test
	public void testSessionBean() {
		SessionBean bean = (SessionBean) fixture.getEnterpriseBeans().getSessionBeans().get(0);
		assertEquals("com.sap.SessionBean", bean.getEjbName());
		assertEquals("com.sap.SessionBean", bean.getEjbClass());
	}

}
