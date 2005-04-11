package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.TestCase;

import org.eclipse.jst.j2ee.application.internal.operations.EARComponentCreationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;

public abstract class AbstractModuleCreationTest extends TestCase {
	
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
			setupEARModule();
			setupjavaUtilComponent();
			setupWebModule();
			setupEJBModule();
			setupAppClientModule();
			setupConnectorModule();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
	public void setupjavaUtilComponent() throws Exception {
		createjavautilComponent(15, "javaUtil", "javaUtil.jar", DEFAULT_PROJECT_NAME);
	}

	private void createjavautilComponent(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		JavaComponentCreationDataModel model = new JavaComponentCreationDataModel();
		model.setProperty( JavaComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(JavaComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(JavaComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(JavaComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		try {
			runJavaUtilComponentCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runJavaUtilComponentCreationOperation(JavaComponentCreationDataModel model) throws Exception {
		
		JavaUtilityComponentCreationOperation webOp = new JavaUtilityComponentCreationOperation(model);
		webOp.run(null);
	}		
	
	
	
	public void setupEARModule() throws Exception {
		createEARModule(12, "FirstEARModule", "FirstEARModule.ear", DEFAULT_PROJECT_NAME);

	}
	
	private void createEARModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		EARComponentCreationDataModel model = new EARComponentCreationDataModel();
		model.setProperty( EARComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(EARComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		try {
			runEARComponenteCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runEARComponenteCreationOperation(EARComponentCreationDataModel model) throws Exception {
		
		EARComponentCreationOperation webOp = new EARComponentCreationOperation(model);
		webOp.run(null);
	}
	
	
	public void setupEJBModule() throws Exception {
		createEJBModule(11, "FirstEJBModule", "FirstEJBModule.jar", DEFAULT_PROJECT_NAME);
		createEJBModule(20, "SecondEJBModule", "SecondEJBModule.jar", DEFAULT_PROJECT_NAME);
		createEJBModule(21, "ThirdEJBModule", "ThirdEJBModule.jar", DEFAULT_PROJECT_NAME);
	}
	
	private void createEJBModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		EjbComponentCreationDataModel model = new EjbComponentCreationDataModel();
		model.setProperty( EjbComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(EjbComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(EjbComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(EjbComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		
		model.setBooleanProperty(EjbComponentCreationDataModel.ADD_TO_EAR, false);
		
		try {
			runEJBComponenteCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runEJBComponenteCreationOperation(EjbComponentCreationDataModel model) throws Exception {
		
		EjbComponentCreationOperation webOp = new EjbComponentCreationOperation(model);
		webOp.run(null);
	}
	
	
	public void setupWebModule() throws Exception {
		createWebModule(22, "FirstWebModule", "FirstWebModule.war", DEFAULT_PROJECT_NAME);
		createWebModule(23, "SecondWebModule", "SecondWebModule.war", DEFAULT_PROJECT_NAME);
		createWebModule(24, "ThirdWebModule", "ThirdWebModule.war", DEFAULT_PROJECT_NAME);
	}

	private void createWebModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		WebComponentCreationDataModel model = new WebComponentCreationDataModel();
		model.setProperty( WebComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(WebComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(WebComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(WebComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		try {
			runWebModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runWebModuleCreationOperation(WebComponentCreationDataModel model) throws Exception {
		
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.run(null);
	}
	
	
	public void setupAppClientModule() throws Exception {
		createAppClientModule(12, "FirstAppClient", "FirstAppClient.jar", DEFAULT_PROJECT_NAME);
	}

	private void createAppClientModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		AppClientComponentCreationDataModel model = new AppClientComponentCreationDataModel();
		model.setProperty( AppClientComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(AppClientComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(AppClientComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		try {
			runAppClientModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runAppClientModuleCreationOperation(AppClientComponentCreationDataModel model) throws Exception {
		
		AppClientComponentCreationOperation webOp = new AppClientComponentCreationOperation(model);
		webOp.run(null);
	}	
	
	public void setupConnectorModule() throws Exception {
		createConnectorModule(15, "FirstConnector", "FirstConnector.jar", DEFAULT_PROJECT_NAME);
	}

	private void createConnectorModule(int j2eeVersion, String aModuleName, String aModuleDeployName,String projectName){
		
		ConnectorComponentCreationDataModel model = new ConnectorComponentCreationDataModel();
		model.setProperty( ConnectorComponentCreationDataModel.PROJECT_NAME, projectName);
		model.setIntProperty(ConnectorComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(ConnectorComponentCreationDataModel.COMPONENT_NAME, aModuleName);		
		model.setProperty(ConnectorComponentCreationDataModel.COMPONENT_DEPLOY_NAME, aModuleDeployName);
		try {
			runConnectorModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runConnectorModuleCreationOperation(ConnectorComponentCreationDataModel model) throws Exception {
		
		ConnectorComponentCreationOperation webOp = new ConnectorComponentCreationOperation(model);
		webOp.run(null);
	}		

}
