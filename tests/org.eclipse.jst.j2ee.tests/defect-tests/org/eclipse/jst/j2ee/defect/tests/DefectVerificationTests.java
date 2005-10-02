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

import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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
		IVirtualComponent component = (IVirtualComponent) model.getProperty(IEARComponentImportDataModelProperties.COMPONENT);
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
		EARArtifactEdit artifactEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
		EARFile earFile = (EARFile) artifactEdit.asArchive(true);
		earFile.getEJBReferences(true, false);
		artifactEdit.dispose();
	}
	
}
