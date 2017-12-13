package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author itewk
 */
public abstract class JEEImportOperationTest extends OperationTestCase {
	
	public JEEImportOperationTest() {
		super("JEEImportOperationTests");
	}
		
	public JEEImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        TestSuite suite = new TestSuite("All JEE Import Operation Tests");
        suite.addTest(ModuleImportOperationTest.suite());
        suite.addTest(EARImportOperationTest.suite());

        return suite;
    }
	
	protected String getTestDataDirectoryName() {
		return this.getClass().getSimpleName();
	}
	
	private static final String BASE_IMPORT_DIR = "TestData" + java.io.File.separatorChar + "JEEImportOperationTests" + java.io.File.separatorChar;
	
	protected String getArchivePath(String archiveName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = BASE_IMPORT_DIR + getTestDataDirectoryName() + java.io.File.separatorChar + archiveName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	private static final String TEMP_EXPORT_DIR = System.getProperty("user.dir") + java.io.File.separatorChar + "JEEExportOperationTests" + java.io.File.separatorChar;
	protected String getExportPath(String archiveName) throws Exception {
		return TEMP_EXPORT_DIR + archiveName;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param projectName
	 * @param overwriteHandler
	 * @param creationModel
	 * @param closeArchiveOnDispose
	 * @return
	 */
    protected abstract IDataModel getImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) throws Exception;
    
    protected abstract IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting);
    
	/**
	 * Should run all of the needed import tests for the child's type of import
	 */
	protected void runImportTests_All(String testName) throws Exception {
		String archiveName = null;
		
		archiveName = testName + "_Defaults" + getModuleExtension();
		runAndVerifyImport_ExportedDefaults(archiveName);
		OperationTestCase.deleteAllProjects();
		
		archiveName = testName + "_Source" + getModuleExtension();
		runAndVerifyImport_ExportedWithSource(archiveName);
		OperationTestCase.deleteAllProjects();
		
		archiveName = testName + "_NoBuild" + getModuleExtension();
		runAndVerifyImport_ExportedWithDontRunBuild(archiveName);
		OperationTestCase.deleteAllProjects();
		
		archiveName = testName + "_Source_NoBuild" + getModuleExtension();
		runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild(archiveName);
		OperationTestCase.deleteAllProjects();
	}
	
	protected abstract String getModuleExtension();
    
//	protected void runAndVerifyImport(String archivePath, String projectName) throws Exception {
//		verifyImportArchiveExists(archivePath);
//		
//		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
//		OperationTestCase.runAndVerify(importModel);
//	}
	
	protected void runAndVerifyImport_ExportedDefaults(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedDefaults";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
		OperationTestCase.runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	protected void runAndVerifyImport_ExportedWithSource(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
		runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	protected void runAndVerifyImport_ExportedWithDontRunBuild(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithDontRunBuild";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
		runAndVerify(importModel);
	}
	
	protected void runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSourceAndDontRunBuild";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
		runAndVerify(importModel);
	}
	
	protected void runAndVerifyReExportation(IDataModel importModel, boolean exportSource, boolean runBuild) throws Exception {
		IArchive importedArchive = null;
		IArchive exportedArchive = null;
		
		String projectName = importModel.getStringProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME);
		String importedArchivePath = importModel.getStringProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME);
		
		String exportDestination = getExportPath(projectName + getModuleExtension());
		
		try {
			IDataModel exportModel = getExportDataModel(projectName, exportDestination, exportSource, runBuild, true);

			//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
			runDataModel(exportModel);
			
			importedArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(importedArchivePath));
			exportedArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(exportDestination));
			
			List<IArchiveResource> importedArchiveResources = importedArchive.getArchiveResources();
			List<IArchiveResource> exportedArchiveResources = exportedArchive.getArchiveResources();
			
			List<IPath> importedArchiveFileResourcePaths = new ArrayList<IPath>();
			List<IPath> exportedArchiveFileResourcePaths = new ArrayList<IPath>();
			
			List<IPath> importedArchiveDirResourcePaths = new ArrayList<IPath>();
			List<IPath> exportedArchiveDirResourcePaths = new ArrayList<IPath>();
			
			for(IArchiveResource importedArchiveResource : importedArchiveResources) {
				if(importedArchiveResource.getType() == IArchiveResource.DIRECTORY_TYPE) {
					importedArchiveDirResourcePaths.add(importedArchiveResource.getPath());
				} else {
					importedArchiveFileResourcePaths.add(importedArchiveResource.getPath());
				}
			}
			
			for(IArchiveResource exportedArchiveResource : exportedArchiveResources) {
				if(exportedArchiveResource.getType() == IArchiveResource.DIRECTORY_TYPE) {
					exportedArchiveDirResourcePaths.add(exportedArchiveResource.getPath());
				} else {
					exportedArchiveFileResourcePaths.add(exportedArchiveResource.getPath());
				}
			}
			
			if(exportedArchiveFileResourcePaths.contains(new Path("/"))){
				Assert.fail("Exported Archive should not contain a root entry '/'");
			}
			
			List<IPath>missingFromImport = new ArrayList<IPath>();
			boolean exported;
			for(IPath importedPath : importedArchiveDirResourcePaths) {
				exported = exportedArchiveDirResourcePaths.contains(importedPath);
				if(!exported){
					if(importedPath.lastSegment() != null){ //don't include root entries
						missingFromImport.add(importedPath);
					}
				}
			}
			
			List<IPath> missingFromExport = new ArrayList<IPath>();
			for(IPath importedPath : importedArchiveFileResourcePaths){
				exported = exportedArchiveFileResourcePaths.remove(importedPath);
				if(!exported){
					if(importedPath.lastSegment() != null){ //don't include root entries
						missingFromExport.add(importedPath);
					}
				}
			}

			if(!missingFromExport.isEmpty() || !missingFromImport.isEmpty()){
				StringBuffer buffer = new StringBuffer();
				if(!missingFromExport.isEmpty()){
					buffer.append("The following are missing from the exported IArchive:\n");
					for(IPath path: missingFromExport){
						buffer.append(path+"\n");
					}
				}
				if(!missingFromImport.isEmpty()){
					buffer.append("The following are missing from the imported IArchive:\n");
					for(IPath path: missingFromImport){
						buffer.append(path+"\n");
					}
				}
				String str = buffer.toString();
				System.err.println(str);
				Assert.fail(str);
			}			
		} finally {
			if (null != importedArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(importedArchive);
			}
			
			if (null != exportedArchive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(exportedArchive);
			}
			
			File f = new File(exportDestination);
			f.delete();
		}
	}
	
	protected void verifyImportArchiveExists(String archivePath) {
		Assert.assertNotNull("The path to the archive to import can not be null", archivePath);
		File archive = new File(archivePath);
		Assert.assertTrue("The archive to import, " + archivePath + " does not exist", archive.exists());
	}
	
//    @Override
//    public void run(TestResult result) {
//    	if(this.result == null) {
//    		this.result = result;
//    	}
//    	
//    	super.run(result);
//    }
}
