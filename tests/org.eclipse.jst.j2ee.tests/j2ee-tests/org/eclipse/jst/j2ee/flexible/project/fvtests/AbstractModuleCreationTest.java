package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.TestCase;

import org.eclipse.jst.j2ee.application.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;

public abstract class AbstractModuleCreationTest extends TestCase {
	protected static final String ILLEGAL_PROJECT_NAME_MESSAGE = "Illegal project name: ";
	protected static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected exception";
	protected static final String TEST_FAILED_MESSAGE = "Test fails Exception should of been trown";
	protected static final String MANIFEST_CLASS_NAME = "Junit_Test_Dummy_Class";
	protected static final String MANIFEST_WRITE_ERROR = "Could not write to manifest test failed";
	protected static final String MANIFEST_LOCK_ERROR = "Manifest IO error - File could be locked";
	protected static final String MANIFEST_CORE_ERROR = "Java core error";

	
	public static final int APPLICATION_CLIENT_MODULE = 0;
	public static final int WEB_MODULE = 1;
	public static final int EJB_MODULE = 2;
	public static final int EAR_MODULE = 3;
//	public IProject ejbproject;
//	public IProject earproject;
	public static String DEFAULT_PROJECT_NAME = "Flexible";	
	
	public AbstractModuleCreationTest(String name) {
		super(name);
	}
	
	public AbstractModuleCreationTest() {
		super();
	}	

   public void createSimpleProject(String projectName) throws Exception {
		FlexibleJavaProjectCreationDataModel dataModel = getProjectCreationDataModel();
	    dataModel.setProperty(FlexibleJavaProjectCreationDataModel.PROJECT_NAME, projectName);
		setServerTargetProperty(dataModel);
		dataModel.getDefaultOperation().run(null);
   }
    
    public FlexibleJavaProjectCreationDataModel getProjectCreationDataModel(){
		return new FlexibleJavaProjectCreationDataModel();
    }
	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(FlexibleJavaProjectCreationDataModel dataModel) {
		dataModel.setProperty(FlexibleJavaProjectCreationDataModel.SERVER_TARGET_ID, AllTests.JONAS_TOMCAT_RUNTIME.getId());
	}
    
	public void runAll(){
		try {
			createSimpleProject(DEFAULT_PROJECT_NAME);
			setupWebModule();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setupWebModule() throws Exception {


		createWebModule(22, "FirstWebModule", "FirstWebModule.war", DEFAULT_PROJECT_NAME);
		createWebModule(23, "SecondWebModule", "SecondWebModule.war", DEFAULT_PROJECT_NAME);
		createWebModule(24, "ThirdWebModule", "ThirdWebModule.war", DEFAULT_PROJECT_NAME);
	
	}

	private void createWebModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		WebComponentCreationDataModel model = new WebComponentCreationDataModel();
		model.setProperty( WebComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(WebComponentCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		model.setProperty(WebComponentCreationDataModel.MODULE_NAME, aModuleName);		
		model.setProperty(WebComponentCreationDataModel.MODULE_DEPLOY_NAME, aModuleDeployName);
		try {
			runWebModuleCreationOperation(model);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private  void runWebModuleCreationOperation(WebComponentCreationDataModel model) throws Exception {
		
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.run(null);
	}

}
