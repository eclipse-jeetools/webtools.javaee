package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.TestCase;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.IConnectorComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public abstract class AbstractModuleCreationTest extends TestCase {
	
	public static String DEFAULT_PROJECT_NAME = "Flexible";	
	
	public AbstractModuleCreationTest(String name) {
		super(name);
	}
	
	public AbstractModuleCreationTest() {
		super();
	}	

   public void createSimpleProject(String projectName) throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
	    dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.PROJECT_NAME, projectName);
		setServerTargetProperty(dataModel);
		dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
   }
	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(IDataModel dataModel) {
		dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, AllTests.JONAS_TOMCAT_RUNTIME.getId());
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
		createjavautilComponent("javaUtil", DEFAULT_PROJECT_NAME);
	}

	private void createjavautilComponent(String aModuleName, String projectName){	
		IDataModel model = DataModelFactory.createDataModel(new JavaComponentCreationDataModelProvider());
		model.setProperty( IJavaComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setProperty(IJavaComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		try {
			runJavaUtilComponentCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runJavaUtilComponentCreationOperation(IDataModel model) throws Exception {	
        JavaUtilityComponentCreationOperation webOp = new JavaUtilityComponentCreationOperation(model);
		webOp.execute(new NullProgressMonitor(), null);
	}		
	
	
	
	public void setupEARModule() throws Exception {
		createEARModule(12, "FirstEARModule", DEFAULT_PROJECT_NAME);
	}
	
	private void createEARModule(int j2eeVersion, String aModuleName, String projectName){	
		IDataModel model = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		model.setProperty( IEarComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IEarComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IEarComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		try {
			runEARComponenteCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runEARComponenteCreationOperation(IDataModel model) throws Exception {	
		IDataModelOperation webOp = model.getDefaultOperation();
		webOp.execute(new NullProgressMonitor(), null);
	}
	
	
	public void setupEJBModule() throws Exception {
		createEJBModule(11, "FirstEJBModule", DEFAULT_PROJECT_NAME);
		createEJBModule(20, "SecondEJBModule", DEFAULT_PROJECT_NAME);
		createEJBModule(21, "ThirdEJBModule", DEFAULT_PROJECT_NAME);
	}
	
	private void createEJBModule(int j2eeVersion, String aModuleName, String projectName){	
		IDataModel model = DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
		model.setProperty( IEjbComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IEjbComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IEjbComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		
		model.setBooleanProperty(IEjbComponentCreationDataModelProperties.ADD_TO_EAR, false);
		
		try {
			runEJBComponenteCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runEJBComponenteCreationOperation(IDataModel model) throws Exception {	
		EjbComponentCreationOperation ejbOp = new EjbComponentCreationOperation(model);
        ejbOp.execute(new NullProgressMonitor(), null);
	}
	
	
	public void setupWebModule() throws Exception {
		createWebModule(22, "FirstWebModule", DEFAULT_PROJECT_NAME);
		createWebModule(23, "SecondWebModule", DEFAULT_PROJECT_NAME);
		createWebModule(24, "ThirdWebModule", DEFAULT_PROJECT_NAME);
	}

	private void createWebModule(int j2eeVersion, String aModuleName, String projectName){		
		IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
		model.setProperty( IWebComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		try {
			runWebModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runWebModuleCreationOperation(IDataModel model) throws Exception {	
		IDataModelOperation webOp = model.getDefaultOperation();
		webOp.execute(new NullProgressMonitor(), null);
	}
	
	
	public void setupAppClientModule() throws Exception {
		createAppClientModule(12, "FirstAppClient", DEFAULT_PROJECT_NAME);
	}

	private void createAppClientModule(int j2eeVersion, String aModuleName, String projectName){	
		IDataModel model = DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
		model.setProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		try {
			runAppClientModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runAppClientModuleCreationOperation(IDataModel model) throws Exception {	
		IDataModelOperation appOp = model.getDefaultOperation();
        appOp.execute(new NullProgressMonitor(),null);
	}	
	
	public void setupConnectorModule() throws Exception {
		createConnectorModule(15, "FirstConnector", DEFAULT_PROJECT_NAME);
	}

	private void createConnectorModule(int j2eeVersion, String aModuleName, String projectName){		
		IDataModel model = DataModelFactory.createDataModel(new ConnectorComponentCreationDataModelProvider());
		model.setProperty(IConnectorComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		try {
			runConnectorModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private  void runConnectorModuleCreationOperation(IDataModel model) throws Exception {
		ConnectorComponentCreationOperation webOp = new ConnectorComponentCreationOperation(model);
		webOp.execute(new NullProgressMonitor(),null);
	}		

}
