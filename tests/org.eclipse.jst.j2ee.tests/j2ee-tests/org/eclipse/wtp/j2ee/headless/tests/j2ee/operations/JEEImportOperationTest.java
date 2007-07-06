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
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARImportOperationTest;

/**
 * @author itewk
 */
public abstract class JEEImportOperationTest extends OperationTestCase {
	protected static final String BASE_DATA_DIR = System.getProperty("user.dir") + java.io.File.separatorChar + "TestData" + java.io.File.separatorChar + "JEEImportOperationTests" + java.io.File.separatorChar;
	private static final String TEMP_EXPORT_DIR = System.getProperty("user.dir") + java.io.File.separatorChar + "JEEExportOperationTests" + java.io.File.separatorChar;
//	protected static TestResult result = null;
	
	
	public JEEImportOperationTest() {
		super("JEEImportOperationTests");
	}
		
	public JEEImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(ModuleImportOperationTest.suite());
        suite.addTest(EARImportOperationTest.suite());

        return suite;
    }
	
	protected String getArchivePath(String archiveName) {
		return BASE_DATA_DIR + this.getClass().getSimpleName() + java.io.File.separatorChar + archiveName;
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
    protected abstract IDataModel getImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose);
    
    protected abstract IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting);
    
	/**
	 * Should run all of the needed import tests for the child's type of import
	 */
	protected void runImportTests_All() throws Exception {
		runAndVerifyImport_ExportedDefaults();
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_ExportedWithSource();
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_ExportedWithDontRunBuild();
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild();
		OperationTestCase.deleteAllProjects();
	}
	
	protected abstract String getModuleExtension();
    
//	protected void runAndVerifyImport(String archivePath, String projectName) throws Exception {
//		verifyImportArchiveExists(archivePath);
//		
//		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
//		OperationTestCase.runAndVerify(importModel);
//	}
	
	protected void runAndVerifyImport_ExportedDefaults() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_Defaults" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedDefaults";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
		OperationTestCase.runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, false, true);
	}
	
	protected void runAndVerifyImport_ExportedWithSource() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
		runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	protected void runAndVerifyImport_ExportedWithDontRunBuild() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_DontRunBuild" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithDontRunBuild";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
		runAndVerify(importModel);
	}
	
	protected void runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource_DontRunBuild" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSourceAndDontRunBuild";
		
		verifyImportArchiveExists(archivePath);
		
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
		runAndVerify(importModel);
	}
	
	protected void runAndVerifyReExportation(IDataModel importModel, boolean exportSource, boolean runBuild) throws Exception {
		IArchive importedArchive = null;
		IArchive exportedArchive = null;
		
		String projectName = importModel.getStringProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME);
		String importedArchivePath = importModel.getStringProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME);
		
		String exportDestination = TEMP_EXPORT_DIR + projectName + getModuleExtension();
		
		try {
			IDataModel exportModel = getExportDataModel(projectName, exportDestination, exportSource, runBuild, true);

			OperationTestCase.runAndVerify(exportModel);
			
			importedArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(importedArchivePath));
			exportedArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(exportDestination));
			
			List<IArchiveResource> importedArchiveResources = importedArchive.getArchiveResources();
			List<IArchiveResource> exportedArchiveResources = exportedArchive.getArchiveResources();
			
			List<IPath> importedArchiveResourcePaths = new ArrayList<IPath>();
			List<IPath> exportedArchiveResourcePaths = new ArrayList<IPath>();
			
			for(IArchiveResource importedArchiveResource : importedArchiveResources) {
				importedArchiveResourcePaths.add(importedArchiveResource.getPath());
			}
			
			for(IArchiveResource exportedArchiveResource : exportedArchiveResources) {
				exportedArchiveResourcePaths.add(exportedArchiveResource.getPath());
			}
			
			if(exportedArchiveResourcePaths.contains(new Path("/"))){
				Assert.fail("Exported Archive should not contain a root entry '/'");
			}
			
			List<IPath> missingFromExport = new ArrayList<IPath>();
			for(IPath importedPath : importedArchiveResourcePaths){
				boolean exported = exportedArchiveResourcePaths.remove(importedPath);
				if(!exported){
					if(importedPath.lastSegment() != null){ //don't include root entries
						missingFromExport.add(importedPath);
					}
				}
			}
			List<IPath>missingFromImport = new ArrayList<IPath>();
			missingFromImport.addAll(exportedArchiveResourcePaths);
			
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
