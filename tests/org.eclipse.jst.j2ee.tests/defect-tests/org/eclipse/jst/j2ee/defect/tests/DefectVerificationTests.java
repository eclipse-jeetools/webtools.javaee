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
package org.eclipse.jst.j2ee.defect.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class DefectVerificationTests extends OperationTestCase {

	public static String getFullTestDataPath(String dataPath) {
		try {
			String defectTestDataPath = "DefectTestData" + fileSep + dataPath;
			HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
			if (plugin != null) {
				return ProjectUtility.getFullFileName(plugin, defectTestDataPath);
			}
			return System.getProperty("user.dir") + java.io.File.separatorChar + defectTestDataPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=120018
	 */
	public void test120018() throws Exception {
		IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		model.setProperty(IWebFacetInstallDataModelProperties.FACET_PROJECT_NAME, "Test120018");
		model.getDefaultOperation().execute(null, null);

		IVirtualComponent component = ComponentUtilities.getComponent("Test120018");

		IVirtualFolder folder = component.getRootFolder().getFolder("imported_classes");
		folder.create(IResource.NONE, null);
		IPath folderPath = folder.getProjectRelativePath();

		final IVirtualFolder jsrc = component.getRootFolder().getFolder("/WEB-INF/classes");
		jsrc.createLink(folder.getProjectRelativePath(), 0, null);

		IJavaProject javaProject = JavaCore.create(component.getProject());
		IClasspathEntry[] entries = javaProject.getRawClasspath();
		boolean foundImportedClasses = false;
		for (int i = 0; i < entries.length && !foundImportedClasses; i++) {
			if (IClasspathEntry.CPE_CONTAINER == entries[i].getEntryKind()) {
				IClasspathContainer container = JavaCore.getClasspathContainer(entries[i].getPath(), javaProject);
				IClasspathEntry[] containerEntries = container.getClasspathEntries();
				for (int j = 0; j < containerEntries.length && !foundImportedClasses; j++) {
					IPath entryPath = containerEntries[j].getPath().removeFirstSegments(1);
					foundImportedClasses = folderPath.equals(entryPath);
				}
			}

		}
		Assert.assertTrue(foundImportedClasses);
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=105901
	 */
	public void test105901() throws Exception {
		String earFileName = getFullTestDataPath("Collision.ear");
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		runAndVerify(model);
		IVirtualComponent comp = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		IVirtualReference[] refs = comp.getReferences();
		assertEquals(3, refs.length);
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=109430
	 */
	public void test109430() throws Exception {
		String earFileName = getFullTestDataPath("EJBLocalAndRemoteRefEARWithClientJars.ear");
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);
		List moduleList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);

		List modulesNeeded = new ArrayList();
		for (int i = 0; i < moduleList.size(); i++) {
			IDataModel aModel = (IDataModel) moduleList.get(i);
			Object file = aModel.getProperty(IEARComponentImportDataModelProperties.FILE);

			if (file instanceof ModuleFile) {
				ModuleFile moduleFile = (ModuleFile) file;
				if (moduleFile.isEJBJarFile())
					modulesNeeded.add(aModel);
			}
		}
		runAndVerify(model);
		IVirtualComponent component = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		EARArtifactEdit artifactEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
		EARFile earFile = (EARFile) artifactEdit.asArchive(true);
		earFile.getEJBReferences(true, false);
		artifactEdit.dispose();
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=112636
	 */
	public void test112636() throws Exception {
		checkDeploy("BeenThere.ear");
	}

	/**
	 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=112835
	 */
	public void test112835() throws Exception {
		checkDeploy("sib.test.mediations.m5.JsMBR.ear");
	}

	public void test121158() throws Exception {
		String earFileName = getFullTestDataPath("EAR121158.ear"); //$NON-NLS-1$
		EARFile earFile = null;
		try {
			ArchiveOptions opts = new ArchiveOptions();
			opts.setIsReadOnly(true);
			earFile = CommonarchiveFactory.eINSTANCE.openEARFile(opts, earFileName);

			List moduleList = earFile.getModuleFiles();
			for (int i = 0; i < moduleList.size(); i++) {
				ModuleFile module = (ModuleFile) moduleList.get(i);
				RuntimeClasspathEntry[] entries = module.getFullRuntimeClassPath();
				assertEquals(2, entries.length);
				assertTrue(entries[0].toString().endsWith(module.getURI()));
				assertTrue(entries[1].toString().endsWith("EAR121158Util.jar")); //$NON-NLS-1$
			}

		} finally {
			if (earFile != null && earFile.isOpen()) {
				earFile.close();
				earFile = null;
			}
		}
	}

	protected void checkDeploy(String earName) throws Exception {
		String earFileName = getFullTestDataPath(earName);
		IDataModel model = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
		model.setProperty(IEARComponentImportDataModelProperties.FILE_NAME, earFileName);

		List moduleList = (List) model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
		for (int i = moduleList.size() - 1; i > -1; i--) {
			IDataModel aModel = (IDataModel) moduleList.get(i);
			Object file = aModel.getProperty(IEARComponentImportDataModelProperties.FILE);
			if (file instanceof ModuleFile) {
				ModuleFile moduleFile = (ModuleFile) file;
				if (moduleFile.isWARFile())
					moduleList.remove(aModel);
			}
		}

		runAndVerify(model);
		IVirtualComponent comp = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
		EARArtifactEdit earEdit = EARArtifactEdit.getEARArtifactEditForRead(comp);
		EARFile earFile = (EARFile) earEdit.asArchive(false);
		earFile.getEJBReferences(true, true);
		earFile.getEJBReferences(true, false);
		earFile.getEJBReferences(false, true);
		earFile.getEJBReferences(false, false);

		Thread.sleep(5000);

		String earOutputName = "d:\\temp\\Output" + System.currentTimeMillis() + ".ear";
		IDataModel export = DataModelFactory.createDataModel(new EARComponentExportDataModelProvider());
		export.setProperty(IEARComponentExportDataModelProperties.PROJECT_NAME, comp.getProject().getName());
		export.setProperty(IEARComponentExportDataModelProperties.ARCHIVE_DESTINATION, earOutputName);
		runAndVerify(export);

	}


}
