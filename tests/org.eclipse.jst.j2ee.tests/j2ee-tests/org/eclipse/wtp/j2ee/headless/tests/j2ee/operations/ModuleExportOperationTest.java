/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.JCAExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.utility.operations.UtilityExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebExportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class ModuleExportOperationTest extends JEEExportOperationTest {
	protected static final String PACKAGE_NAME = "test";
	
	public ModuleExportOperationTest() {
		super("ModuleExportOperationTests");
	}
	
	public ModuleExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All Module ExportOperation Tests");
		suite.addTestSuite(JCAExportOperationTest.class);
		suite.addTestSuite(EJBExportOperationTest.class);
		suite.addTestSuite(WebExportOperationTest.class);
		suite.addTestSuite(AppClientExportOperationTest.class);
		suite.addTestSuite(UtilityExportOperationTest.class);
		
		return suite;
	}
	
	protected abstract void addJavaFilesToProject(String projectName, String[] classNames, String prackageName) throws Exception;
	
	protected abstract void verifyJavaFilesExported(String archivePath, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception;
	
	@Override
	protected void runExportTests_All(IDataModel creationModel) throws Exception {
		String projectName = creationModel.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
		String archiveName = null;
		String archivePath = null;
		
		addJavaFilesToProject(projectName, JAVA_FILE_NAMES, PACKAGE_NAME);
		
	    archiveName = runAndVerifyExport_Defaults(projectName);
	    archivePath = getDataPath(archiveName);
	    verifyJavaFilesExported(archivePath, JAVA_FILE_NAMES, PACKAGE_NAME, true, false);
		deleteExported(archivePath);
			
		archiveName = runAndVerifyExport_WithSource(projectName);
		archivePath = getDataPath(archiveName);
		verifyJavaFilesExported(archivePath, JAVA_FILE_NAMES, PACKAGE_NAME, true, true);
		deleteExported(archivePath);
		
		archiveName = runAndVerifyExport_DontRunBuild(projectName);
		archivePath = getDataPath(archiveName);
		deleteExported(archivePath);
			
		archiveName = runAndVerifyExport_WithSource_DontRunBuild(projectName);
		archivePath = getDataPath(archiveName);
		verifyJavaFilesExported(archivePath, JAVA_FILE_NAMES, PACKAGE_NAME, false, true);
		deleteExported(archivePath);
			
		runTest_AttemptToOverwriteButCant(projectName);
		runTest_AttemptToOverwriteSholdSucceed(projectName);
	}
}
