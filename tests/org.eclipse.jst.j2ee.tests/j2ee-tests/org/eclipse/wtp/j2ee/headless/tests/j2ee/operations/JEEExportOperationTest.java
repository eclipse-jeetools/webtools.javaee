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
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.File;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARExportOperationTest;

/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 */
public abstract class JEEExportOperationTest extends OperationTestCase {
	protected static final String[] JAVA_FILE_NAMES = {"JavaOne","JavaTwo"};
	protected static final String BASE_DATA_DIR = System.getProperty("user.dir") + java.io.File.separatorChar + "JEEExportOperationTests" + java.io.File.separatorChar;
	
	public JEEExportOperationTest() {
		super("JEEProjectExportOperationTests");
	}
		
	public JEEExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        TestSuite suite = new TestSuite("All JEE Export Operation Tests");
        suite.addTest(ModuleExportOperationTest.suite());
        suite.addTest(EARExportOperationTest.suite());

        return suite;
    }
	
	/**
	 * used to get a string representing a path where a file with given suffix can be stored.
	 * 
	 * @param suffix the suffix to append to the BASE_DATA_DIR
	 * @return a string containing the BASE_DATA_DIR appended with the given suffix
	 */
	protected static String getDataPath(String suffix) {
		return BASE_DATA_DIR + suffix;
	}
	
	protected static void deleteExported(String archivePath) {
		File f = new File(archivePath);
		f.delete();
	}
	
    /**
     * @param projectName name of the project to export
     * @param destination destination to export to
     * @param exportSource if TRUE export source files, else don't
     * @param runBuild if TRUE run a build before exporting, else don't
     * @param overwriteExisting if TRUE overwrite existing files, else don't
     * @return an Export Data Model data model with all of the given settings.
     */
    protected abstract IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting);
    
	/**
	 * Should run all of the needed export tests for the child's type of export
	 * there needs to be at a project of projectType created in the workspace
	 * 
	 * @param creationModel the model used to create the project to export
	 */
	protected abstract void runExportTests_All(IDataModel creationModel) throws Exception;
	
	protected abstract String getModuleExtension();
    
    /**
     * @param projectName name of the project exported
     * @return the name of the archive exported
     * @throws Exception
     */
	protected String runAndVerifyExport_Defaults(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_Defaults" + getModuleExtension();
		String destination = getDataPath(archiveName);
		
		IDataModel exportModel = getExportDataModel(projectName, destination, false, true, false);
		runAndVerify(exportModel);
		
		return archiveName;
	}
	
    /**
     * @param projectName name of the project exported
     * @return the name of the archive exported
     * @throws Exception
     */
	protected String runAndVerifyExport_WithSource(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
		String destination = getDataPath(archiveName);

		IDataModel exportModel = getExportDataModel(projectName, destination, true, true, false);
		runAndVerify(exportModel);
		
		return archiveName;
	}
	
    /**
     * @param projectName name of the project exported
     * @return the name of the archive exported
     * @throws Exception
     */
	protected String runAndVerifyExport_DontRunBuild(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_DontRunBuild" + getModuleExtension();
		String destination = getDataPath(archiveName);
		
		IDataModel exportModel = getExportDataModel(projectName, destination, false, false, false);
		runAndVerify(exportModel);
		
		return archiveName;
	}
	
    /**
     * @param projectName name of the project exported
     * @return the name of the archive exported
     * @throws Exception
     */
	protected String runAndVerifyExport_WithSource_DontRunBuild(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource_DontRunBuild" + getModuleExtension();
		String destination = getDataPath(archiveName);
		
		IDataModel exportModel = getExportDataModel(projectName, destination, true, false, false);
		runAndVerify(exportModel);
		
		return archiveName;
	}
	
	/**
	 * @param projectName name of the project exported
	 * @throws Exception
	 */
	protected void runTest_AttemptToOverwriteButCant(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_AttemptToOverwriteButCant"+ getModuleExtension();
		String destination = getDataPath(archiveName);
		
		try {
			IDataModel exportModel = getExportDataModel(projectName, destination, false, true, false);
			//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
			runDataModel(exportModel);
		
			IDataModel exportModelOverwrite = getExportDataModel(projectName, destination, false, true, false);
			IStatus status = exportModelOverwrite.validate();
			int severity = status.getSeverity();
			Assert.assertEquals("Validate on the model should be returning an error because model should not be able overwrite existing archive", IStatus.ERROR, severity);
		} finally {
			deleteExported(archiveName);
		}
	}
	
	/**
	 * @param projectName name of the project exported
	 * @throws Exception
	 */
	protected void runTest_AttemptToOverwriteSholdSucceed(String projectName) throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_AttemptToOverwriteSholdSucceed" + getModuleExtension();
		String destination = getDataPath(archiveName);
		
		try {
			IDataModel exportModel = getExportDataModel(projectName, destination, false, true, true);
			//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
			runDataModel(exportModel);
			
			IDataModel exportModelOverwrite = getExportDataModel(projectName, destination, false, true, true);
			IStatus status = exportModelOverwrite.validate();
			int severity = status.getSeverity();
			Assert.assertEquals("Data model should be allowed to overwrite existing archive", IStatus.OK, severity);
		} finally {
			deleteExported(archiveName);
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		//clean up any exported files that got left behind if an error occurred
		String dataPath = getDataPath("");
		File dataFolder = new File(dataPath);
		File[] files = dataFolder.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			files[i].delete();
		}
	}
}
