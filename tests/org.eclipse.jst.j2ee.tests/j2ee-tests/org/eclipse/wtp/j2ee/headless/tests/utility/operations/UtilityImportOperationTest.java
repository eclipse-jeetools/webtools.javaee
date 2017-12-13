/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.utility.operations;

import java.util.Collections;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

/**
 * @author itewk
 *
 */
public class UtilityImportOperationTest extends ModuleImportOperationTest {
	private IArchive archive;
	
	public UtilityImportOperationTest() {
		super("UtilityImportOperationTests");
		
		archive = null;
	}
	
	public UtilityImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(UtilityImportOperationTest.class);
	}	
	
    public void testUtilityImport_Defaults() throws Exception{
    	runImportTests_All("Utility_Defaults");
    }
	
    public void testUtilityImport_AddToExisitingEAR12() throws Exception {
    	runImportTests_All("Utility_AddToExisitingEAR12");
    }
    
    public void testUtilityImport_AddToExisitingEAR13() throws Exception {
    	runImportTests_All("Utility_AddToExisitingEAR13");
    }
    
    public void testUtilityImport_AddToExisitingEAR14() throws Exception {
    	runImportTests_All("Utility_AddToExisitingEAR14");
    }
    
    /*
    public void testUtilityImport_AddToExisitingEAR5_WithoutDD() throws Exception {
    	runImportTests_All("Utility_AddToExisitingEAR5_WithoutDD");
    }
    */
    
    public void testUtilityImport_AddToExisitingEAR5_WithDD() throws Exception {
    	runImportTests_All("Utility_AddToExisitingEAR5_WithDD");
    }
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEImportOperationTest#getExportDataModel(java.lang.String, java.lang.String, boolean, boolean, boolean)
	 */
	@Override
	protected IDataModel getExportDataModel(String projectName,
			String destination, boolean exportSource, boolean runBuild,
			boolean overwriteExisting) {
		
		//only makes since to test utility projects exported/imported within an EAR
		return EARExportOperationTest.getEARExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEImportOperationTest#getImportDataModel(java.lang.String, java.lang.String, org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler, org.eclipse.wst.common.frameworks.datamodel.IDataModel, boolean)
	 */
	@Override
	protected IDataModel getImportDataModel(String filePath,
			String projectName, 
			IDataModel creationModel, boolean closeArchiveOnDispose) throws Exception {
		
		//only makes sense to test utility projects exported/imported within an EAR
		IDataModel earImportModel = EARImportOperationTest.getEARImportDataModel(filePath, projectName, creationModel, closeArchiveOnDispose);

		archive = null;
		List <ArchiveWrapper> utilityArchiveWrappers = Collections.EMPTY_LIST;

		archive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(filePath));
		ArchiveWrapper wrappedArchive = new ArchiveWrapper(archive);

		utilityArchiveWrappers = wrappedArchive.getEARUtilitiesAndWebLibs();
		if (utilityArchiveWrappers.size() > 0){
			filterEJBClientJars(utilityArchiveWrappers, wrappedArchive);
		}

		
		earImportModel = EARImportOperationTest.setExtendedEARImportDataModelProperties(earImportModel, null, utilityArchiveWrappers, null, null, null, null);
		return earImportModel;
	}
	/**
	 * Should run all of the needed import tests for the child's type of import
	 */
	@Override
	protected void runImportTests_All(String testName) throws Exception {
		String archiveName = null;
		
		//need to keep the archive open for as long as the ImportDataModel is alive, otherwise it crashes because the
		// nested Utility archives get closed

		try {
			archiveName = testName + "_Defaults" + getModuleExtension();
			runAndVerifyImport_ExportedDefaults(archiveName);
			OperationTestCase.deleteAllProjects();
		} finally {
			if (null != archive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
		
		try {
			archiveName = testName + "_Source" + getModuleExtension();
			runAndVerifyImport_ExportedWithSource(archiveName);
			OperationTestCase.deleteAllProjects();
		} finally {
			if (null != archive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
		
		try {
			archiveName = testName + "_NoBuild" + getModuleExtension();
			runAndVerifyImport_ExportedWithDontRunBuild(archiveName);
			OperationTestCase.deleteAllProjects();
		} finally {
			if (null != archive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
		
		try {
			archiveName = testName + "_Source_NoBuild" + getModuleExtension();
			runAndVerifyImport_ExportedWithSrouce_ExportedWithDontRunBuild(archiveName);
			OperationTestCase.deleteAllProjects();
		} finally {
			if (null != archive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEImportOperationTest#getModuleExtension()
	 */
	@Override
	protected String getModuleExtension() {
		//using .ear and not .jar because we are testing exporting the utilities within EARs a
		return ".ear";
	}
	
	private void filterEJBClientJars(List <ArchiveWrapper> utilities, ArchiveWrapper earWrapper) {
		List <ArchiveWrapper> modules = earWrapper.getEarModules();
		for(ArchiveWrapper module : modules){
			if(module.isEJBJarFile()){
				ArchiveWrapper clientWrapper = earWrapper.getEJBClientArchiveWrapper(module);
				if(null != clientWrapper){
					boolean removed = false;
					for(int i=0;i<utilities.size() && !removed; i++){
						if(clientWrapper.getUnderLyingArchive() == utilities.get(i).getUnderLyingArchive()){
							utilities.remove(i);
							removed = true;
						}
					}
				}
			}
		}
	}
}
