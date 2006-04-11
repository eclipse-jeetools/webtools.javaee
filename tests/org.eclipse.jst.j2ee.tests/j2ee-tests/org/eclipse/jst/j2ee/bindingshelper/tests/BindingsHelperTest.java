/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.bindingshelper.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.core.internal.bindings.IJNDIBindingsHelper;
import org.eclipse.jst.j2ee.core.internal.bindings.JNDIBindingsHelperManager;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class BindingsHelperTest extends TestCase {

	public static String fileSep = System.getProperty("file.separator"); //$NON-NLS-1$

	public static String getFullTestDataPath(String dataPath) {
		try {
			String defectTestDataPath = "TestData" + fileSep + "BindingsHelperTests" + fileSep + dataPath; //$NON-NLS-1$ //$NON-NLS-2$
			HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
			if (plugin != null) {
				return ProjectUtility.getFullFileName(plugin, defectTestDataPath);
			}
			return System.getProperty("user.dir") + java.io.File.separatorChar + defectTestDataPath; //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //$NON-NLS-1$
	}

	private IProject earProject;
	private IVirtualComponent earComponent;

	protected void setUp() throws Exception {
		super.setUp();
		IDataModel model = importEar(getFullTestDataPath("BindingsTestEAR.ear")); //$NON-NLS-1$
		earProject = ProjectUtilities.getProject(model.getStringProperty(IEARComponentImportDataModelProperties.PROJECT_NAME));
		earComponent = ComponentCore.createComponent(earProject);
	}

	protected EARFile getEarFile() throws Exception {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			return (EARFile) edit.asArchive(true);
		} finally {
			if (null != edit) {
				edit.dispose();
			}
		}
	}

	public static IDataModel importEar(String earPath) throws Exception {
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earPath);
		model.getDefaultOperation().execute(null, null);
		return model;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		IProject[] projects = ProjectUtilities.getAllProjects();
		for (int i = 0; i < projects.length; i++) {
			projects[i].delete(true, null);
		}
	}

	protected String getSourceFileName() {
		return "test.txt"; //$NON-NLS-1$
	}

	public void testHelpers() throws Exception {
		// First check the default helper; i.e. no helpers enabled
		IJNDIBindingsHelper[] helpers = checkHelper(null);
		checkBindings(getEarFile(), helpers[0], ""); //$NON-NLS-1$

		// Now check fooHelper (barHelper disabled)
		IFile srcFile = earComponent.getRootFolder().getFile(getSourceFileName()).getUnderlyingFile();
		IFile fooFile = earComponent.getRootFolder().getFile(FooBindingsHelper.getFileName()).getUnderlyingFile();
		srcFile.copy(new Path(fooFile.getFullPath().lastSegment()), true, null);
		assertTrue(fooFile.exists());

		helpers = checkHelper(FooBindingsHelper.class);
		checkBindings(getEarFile(), helpers[0], FooBindingsHelper.getFileName());

		// Now check barHelper (fooHelper disabled)
		fooFile.delete(true, null);
		assertFalse(fooFile.exists());

		IFile barFile = earComponent.getRootFolder().getFile(BarBindingsHelper.getFileName()).getUnderlyingFile();
		srcFile.copy(new Path(barFile.getFullPath().lastSegment()), true, null);
		assertTrue(barFile.exists());

		helpers = checkHelper(BarBindingsHelper.class);
		checkBindings(getEarFile(), helpers[0], BarBindingsHelper.getFileName());

		// Now check both fooHelper and barHelper (both enabled)
		srcFile.copy(new Path(fooFile.getFullPath().lastSegment()), true, null);
		assertTrue(fooFile.exists());

		helpers = JNDIBindingsHelperManager.getInstance().getBindingsHelpers(earProject);
		assertEquals(2, helpers.length);
		int fooIndex = (helpers[0].getClass() == FooBindingsHelper.class) ? 0 : 1;
		int barIndex = (fooIndex == 0) ? 1 : 0;
		assertTrue(helpers[fooIndex].getClass() == FooBindingsHelper.class);
		assertTrue(helpers[barIndex].getClass() == BarBindingsHelper.class);
		EARFile earFile = getEarFile();
		IJNDIBindingsHelper[] tempHelpers = JNDIBindingsHelperManager.getInstance().getBindingsHelpers(earFile);
		assertEquals(2, tempHelpers.length);
		assertTrue(helpers[0] == tempHelpers[0]);
		assertTrue(helpers[1] == tempHelpers[1]);

		checkBindings(getEarFile(), helpers[fooIndex], FooBindingsHelper.getFileName());
		checkBindings(getEarFile(), helpers[barIndex], BarBindingsHelper.getFileName());
	}

	private IJNDIBindingsHelper[] checkHelper(Class clazz) throws Exception {
		IJNDIBindingsHelper[] helpers = JNDIBindingsHelperManager.getInstance().getBindingsHelpers(earProject);
		assertEquals(1, helpers.length);
		if (null != clazz) {
			assertTrue(helpers[0].getClass() == clazz);
		} else {
			assertTrue(helpers[0].getClass() != FooBindingsHelper.class);
			assertTrue(helpers[0].getClass() != BarBindingsHelper.class);
		}
		EARFile earFile = getEarFile();
		IJNDIBindingsHelper[] tempHelpers = JNDIBindingsHelperManager.getInstance().getBindingsHelpers(earFile);
		assertEquals(1, tempHelpers.length);
		assertTrue(helpers[0] == tempHelpers[0]);
		return helpers;
	}

	private void checkBindings(EARFile earFile, IJNDIBindingsHelper helper, String prefix) {
		List ejbJarFiles = earFile.getEJBJarFiles();
		EJBJarFile ejbJarFile = (EJBJarFile) ejbJarFiles.get(0);
		EJBJar ejbJar = ejbJarFile.getDeploymentDescriptor();

		List cmps = ejbJar.getContainerManagedBeans();
		ContainerManagedEntity cmp = (ContainerManagedEntity) cmps.get(0);
		String jndiName = helper.getJNDIName(cmp);
		String suffix = prefix.length() > 0 ? TestBindingsHelper.EJB : prefix;
		assertEquals(prefix + cmp.getName() + suffix, jndiName);

		jndiName = helper.getJNDINameForDefaultDataSource(cmp);
		suffix = prefix.length() > 0 ? TestBindingsHelper.CMP : prefix;
		assertEquals(prefix + ejbJar.getDisplayName() + suffix, jndiName);

		List sessions = ejbJar.getSessionBeans();

		Session session = (Session) sessions.get(0);
		// TODO
		// List ejbRefs = session.getEjbRefs();
		// EjbRef ejbRef = (EjbRef) ejbRefs.get(0);

		List resRefs = session.getResourceRefs();
		ResourceRef resRef = (ResourceRef) resRefs.get(0);
		jndiName = helper.getJNDINameForRef(session, resRef);
		suffix = prefix.length() > 0 ? TestBindingsHelper.EJB_RES_REF : prefix;
		assertEquals(prefix + resRef.getName() + suffix, jndiName);

		List resEnvRefs = session.getResourceEnvRefs();
		ResourceEnvRef resEnvRef = (ResourceEnvRef) resEnvRefs.get(0);
		jndiName = helper.getJNDINameForRef(session, resEnvRef);
		suffix = prefix.length() > 0 ? TestBindingsHelper.EJB_RES_ENV_REF : prefix;
		assertEquals(prefix + resEnvRef.getName() + suffix, jndiName);

		List securityRoleRefs = session.getSecurityRoleRefs();
		SecurityRoleRef securityRoleRef = (SecurityRoleRef) securityRoleRefs.get(0);
		jndiName = helper.getJNDINameForRef(session, securityRoleRef);
		suffix = prefix.length() > 0 ? TestBindingsHelper.EJB_SEC_ROLE_REF : prefix;
		assertEquals(prefix + securityRoleRef.getName() + suffix, jndiName);

		List warFiles = earFile.getWARFiles();
		WARFile warFile = (WARFile) warFiles.get(0);
		WebApp webApp = warFile.getDeploymentDescriptor();

		resRefs = webApp.getResourceRefs();
		resRef = (ResourceRef) resRefs.get(0);
		jndiName = helper.getJNDINameForRef(webApp, resRef);
		suffix = prefix.length() > 0 ? TestBindingsHelper.WEB_RES_REF : prefix;
		assertEquals(prefix + resRef.getName() + suffix, jndiName);

		resEnvRefs = session.getResourceEnvRefs();
		resEnvRef = (ResourceEnvRef) resEnvRefs.get(0);
		jndiName = helper.getJNDINameForRef(webApp, resEnvRef);
		suffix = prefix.length() > 0 ? TestBindingsHelper.WEB_RES_ENV_REF : prefix;
		assertEquals(prefix + resEnvRef.getName() + suffix, jndiName);

	}


}