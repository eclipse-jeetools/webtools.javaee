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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IEJBCreateDeploymentFilesDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class GenerateDDTest extends TestCase {

	private static final String EJB_JAR_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<ejb-jar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
			+ "xmlns=\"http://java.sun.com/xml/ns/javaee\" "
			+ "xmlns:ejb=\"http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" "
			+ "xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee "
			+ "http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\" version=\"3.0\">" + "<enterprise-beans>"
			+ "		<session>" + "			<ejb-name>NMTOKEN</ejb-name>" + "		</session>" + "	</enterprise-beans></ejb-jar>";

	private IModelProvider fixture = null;

	private IProject project = null;

	public static Test suite() {
		TestSuite suite = new TestSuite(GenerateDDTest.class);
		return suite;
	}

	protected void setUp() throws Exception {
		super.setUp();
		project = ProjectUtil.createEJBProject(GenerateDDTest.class.getSimpleName(), null,
				J2EEVersionConstants.EJB_3_0_ID, true);
		fixture = ModelProviderManager.getModelProvider(project);
	}

	protected void tearDown() throws Exception {
		ProjectUtil.deleteProject(project);
		super.tearDown();
	}

	/**
	 * Generated the deployment descriptor.
	 * 
	 * @throws InterruptedException
	 */
	public void testGenerateDD() throws Exception {
		IFile file = project.getFile("ejbModule/META-INF/ejb-jar.xml");
		assertFalse(file.exists());
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		fixture.addListener(listener);

		IDataModel dataModel = DataModelFactory.createDataModel(IEJBCreateDeploymentFilesDataModelProperties.class);
		dataModel.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, project);
		dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);

		listener.waitForEvents();
		fixture.removeListener(listener);
		assertEquals(new Integer(1), new Integer(listener.getReceivedEvents().size()));
	}
}
