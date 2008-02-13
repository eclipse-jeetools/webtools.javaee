/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.utility.operations;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;

/**
 * @author itewk
 *
 */
public class UtilityExportOperationTest extends ModuleExportOperationTest {
	private static final String EAR_PROJECT_NAME = "testEAR";
	
	public UtilityExportOperationTest() {
		super("UtilityExportOperationTests");
	}
	
	public UtilityExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(UtilityExportOperationTest.class);
	}
	
	
	public void testUtilityExport_Defaults() throws Exception {
		IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("aJavaUtility", EAR_PROJECT_NAME);
		OperationTestCase.runAndVerify(dm);
		
		runExportTests_All(dm);
	}
	
    public void testUtilityExport_AddToExisitingEAR12() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel(EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.EAR_12, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("cJavaUtility", EAR_PROJECT_NAME);
    	OperationTestCase.runAndVerify(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testUtilityExport_AddToExisitingEAR13() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel(EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.EAR_13, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("dJavaUtility", EAR_PROJECT_NAME);
    	OperationTestCase.runAndVerify(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testUtilityExport_AddToExisitingEAR14() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel(EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.EAR_14, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("eJavaUtility", EAR_PROJECT_NAME);
    	OperationTestCase.runAndVerify(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testUtilityExport_AddToExisitingEAR5_WithoutDD() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel(EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.EAR_5, false);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("fJavaUtility", EAR_PROJECT_NAME);
    	OperationTestCase.runAndVerify(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testUtilityExport_AddToExisitingEAR5_WithDD() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel(EAR_PROJECT_NAME, null, null, null, JavaEEFacetConstants.EAR_5, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = UtilityProjectCreationOperationTest.getUtilityDataModel("gJavaUtility", EAR_PROJECT_NAME);
    	OperationTestCase.runAndVerify(dm);
    	
    	runExportTests_All(dm);
    }
	
	
	@Override
	protected void addJavaFilesToProject(String projectName,
			String[] classNames, String prackageName) throws Exception {
		
		//will be exporting the utility project within an EAR thus use utitilities for EARs to added files
		IProject earProject = JavaEEProjectUtilities.getProject(EAR_PROJECT_NAME);
		JavaFileTestingUtilities.addJavaFilesToAllProjectsInEAR(earProject);
	}

	@Override
	protected void verifyJavaFilesExported(String archivePath,
			String[] classNames, String packageName, boolean withClassFiles,
			boolean withSource) throws Exception {
		
		//project was exported within an EAR so need to verify files based on this
		JavaFileTestingUtilities.verifyAllJavaFilesExportedToProjectsInEAR(archivePath, withClassFiles, withSource);
	}

	@Override
	protected IDataModel getExportDataModel(String projectName,
			String destination, boolean exportSource, boolean runBuild,
			boolean overwriteExisting) {
		
		//only makes sence to test exporting the utility project within an EAR
		return EARExportOperationTest.getEARExportDataModel(EAR_PROJECT_NAME, destination, exportSource, runBuild, overwriteExisting);
	}

	@Override
	protected String getModuleExtension() {
		//using .ear and not .jar because we are testing exporting the utilities within EARs a
		return ".ear";
	}
}
