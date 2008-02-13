/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.utility.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

/**
 * @author itewk
 */
public class UtilityProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
	public UtilityProjectCreationOperationTest() {
		super("JavaUtilityProjectCreationOperationTests");
	}

	public UtilityProjectCreationOperationTest(String name) {
		super(name);
	}
	
    public static Test suite() {
    	return new SimpleTestSuite(UtilityProjectCreationOperationTest.class);
    }
    
    public void testUtility_Defaults() throws Exception{
    	IDataModel dm = getUtilityDataModel("aJavaUtility", null);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testUtility_AddToEAR() throws Exception{
    	IDataModel dm = getUtilityDataModel("bJavaUtility", "bEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testUtility_AddToExisitingEAR12() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel("cEAR", null, null, null, JavaEEFacetConstants.EAR_12, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = getUtilityDataModel("cJavaUtility", "cEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testUtility_AddToExisitingEAR13() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel("dEAR", null, null, null, JavaEEFacetConstants.EAR_13, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = getUtilityDataModel("dJavaUtility", "dEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testUtility_AddToExisitingEAR14() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel("eEAR", null, null, null, JavaEEFacetConstants.EAR_14, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = getUtilityDataModel("eJavaUtility", "eEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testUtility_AddToExisitingEAR5_WithoutDD() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel("fEAR", null, null, null, JavaEEFacetConstants.EAR_5, false);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = getUtilityDataModel("fJavaUtility", "fEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testUtility_AddToExisitingEAR5_WithDD() throws Exception {
    	IDataModel earModel = EARProjectCreationOperationTest.getEARDataModel("gEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	//IMPROVE PERFORMENCE: don't need to verify export model again here, its already bean done in other tests
		runDataModel(earModel);
    	
    	IDataModel dm = getUtilityDataModel("gJavaUtility", "gEAR");
    	OperationTestCase.runAndVerify(dm);
    }
    
	
    public static IDataModel getUtilityDataModel(String projName, String earName){
    	IDataModel model = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
    	
    	model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	
    	if(earName != null) {
        	model.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
        	model.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);
    	} else {
    		model.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
    	}
    	
    	return model;
    }
}
