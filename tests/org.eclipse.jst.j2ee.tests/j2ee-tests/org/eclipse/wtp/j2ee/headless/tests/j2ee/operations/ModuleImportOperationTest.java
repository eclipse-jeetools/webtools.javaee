/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
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

import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.JCAImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.utility.operations.UtilityImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebImportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class ModuleImportOperationTest extends JEEImportOperationTest {

	
	public ModuleImportOperationTest() {
		super("ModuleImportOperationTests");
	}
	
	public ModuleImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All Module Import Operation Tests");
		suite.addTestSuite(JCAImportOperationTest.class);
		suite.addTestSuite(EJBImportOperationTest.class);
		suite.addTestSuite(WebImportOperationTest.class);
		suite.addTestSuite(AppClientImportOperationTest.class);
		suite.addTestSuite(UtilityImportOperationTest.class);
		return suite;
	}
	
//	@Override
//	protected void runImportTests_All() throws Exception {	
//		runAndVerifyImport_ExportedDefaults();
//		OperationTestCase.deleteAllProjects();
//		
//		runAndVerifyImport_ExportedWithSource();
//		OperationTestCase.deleteAllProjects();
//		
//		runAndVerifyImport_ExportedWithDontRunBuild();
//		OperationTestCase.deleteAllProjects();
//		
//		runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild();
//		OperationTestCase.deleteAllProjects();
//	}
	
//	protected void runSubTests() {
//		final String partialPath = getArchivePath("") + this.getClass().getSimpleName() + "_";
//		
//    	TestCase defaults = new TestCase(this.getName() + "_Defaults") {
//    		@Override
//            public void runTest() throws Exception{
//    			String archivePath = partialPath + this.getName() + getModuleExtension();
//    			String projectName = "exportedDefaults";
//    			
//        		runAndVerifyImport(archivePath, projectName);
//        		runAndVerifyReExportation(archivePath, projectName, false, true);
//        		
//        		OperationTestCase.deleteAllProjects();
//            }
//    	};
//    	TestCase withSource = new TestCase(this.getName() + "_WithSource") {
//    		@Override
//            public void runTest() throws Exception{
//    			String archivePath = partialPath + this.getName() + getModuleExtension();
//        		String projectName = "exportedWithSource";
//        		
//        		runAndVerifyImport(archivePath, projectName);
//        		runAndVerifyReExportation(archivePath, projectName, true, true);
//        		
//        		OperationTestCase.deleteAllProjects();
//            }
//    	};
//    	TestCase dontRunBuild = new TestCase(this.getName() + "_DontRunBuild") {
//    		@Override
//            public void runTest() throws Exception{
//    			String archivePath = partialPath + this.getName() + getModuleExtension();
//    			String projectName = "exportedWithDontRunBuild";
//    			
//        		runAndVerifyImport(archivePath, projectName);
//        		
//        		OperationTestCase.deleteAllProjects();
//            }
//    	};
//    	TestCase withSourceDontRunBuild = new TestCase(this.getName() + "_WithSource_DontRunBuild") {
//    		@Override
//            public void runTest() throws Exception{
//    			String archiveName = partialPath + this.getName() + getModuleExtension();
//    			String projectName = "exportedWithSourceAndDontRunBuild";
//    			
//        		runAndVerifyImport(archiveName, projectName);
//        		
//        		OperationTestCase.deleteAllProjects();
//            }
//    	};
//    	
//    	TestSuite suite = new TestSuite();
//		suite.addTest(defaults);
//		suite.addTest(withSource);
//		suite.addTest(dontRunBuild);
//		suite.addTest(withSourceDontRunBuild);
//		suite.setName(this.getClass().getName());
//    	suite.run(result);
//	}
}
