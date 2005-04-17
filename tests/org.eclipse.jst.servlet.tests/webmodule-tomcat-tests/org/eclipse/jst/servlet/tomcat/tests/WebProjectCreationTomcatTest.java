/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;
import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wst.common.tests.TaskViewUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierFactory;

/**
 * @author blancett
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebProjectCreationTomcatTest extends TestCase {
	protected String projectName = null;
	
	 public void createSimpleProject(String projectName) throws Exception {
		FlexibleJavaProjectCreationDataModel dataModel = getFlexibleProjectCreationDataModel();
	    dataModel.setProperty(FlexibleJavaProjectCreationDataModel.PROJECT_NAME, projectName);
		setServerTargetProperty(dataModel);
		dataModel.getDefaultOperation().run(null);
   }
    
    public FlexibleJavaProjectCreationDataModel getFlexibleProjectCreationDataModel(){
		return new FlexibleJavaProjectCreationDataModel();
    }
	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(FlexibleJavaProjectCreationDataModel dataModel) {
		dataModel.setProperty(FlexibleJavaProjectCreationDataModel.SERVER_TARGET_ID, AllTomcatTests.TOMCAT_RUNTIME.getId());
	}
	
	public static void createStandaloneWebProject(WebComponentCreationDataModel model) throws Exception {
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.run(null);
		//ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}
	
	public static void createServlet(NewServletClassDataModel model) throws Exception {
		// TODO fix up the create servlet operation
		/*AddServletOperation op = new AddServletOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();*/
		
	}
	
	public WebComponentCreationDataModel setupStandaloneWebProject(String aProjectName,  int j2eeVersion) throws Exception {
		projectName = aProjectName;
		createSimpleProject(projectName);
		WebComponentCreationDataModel model = getWebComponentCreateionDataModel(projectName,j2eeVersion);
		createStandaloneWebProject(model);
		createServlet(projectName);
		return model;
	}
	
	private WebComponentCreationDataModel getWebComponentCreateionDataModel(String aProjectName, int j2eeVersion) {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(aProjectName);
		String moduleName = aProjectName + "WebModule" ;
		String moduleDeployName = moduleName + ".war" ;
		WebComponentCreationDataModel model = new WebComponentCreationDataModel();
		model.setProperty( WebComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setIntProperty(WebComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(WebComponentCreationDataModel.COMPONENT_NAME, moduleName);		
		model.setProperty(WebComponentCreationDataModel.COMPONENT_DEPLOY_NAME, moduleDeployName);
		return model;
	}
	

	public WebComponentCreationDataModel setupStandaloneAnnotatedWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		createSimpleProject(projectName);
		
		WebComponentCreationDataModel model = getWebComponentCreateionDataModel(projectName,j2eeVersion);
		createStandaloneWebProject(model);
		createAnnotatedServlet(projectName);
		return model;
	}

	/**
	 * @throws Exception
	 */
	private void createServlet(String projectName) throws Exception {
		NewServletClassDataModel servletModel = setupServletCreationDataModel(projectName);
		createServlet(servletModel);
	}
	
	/**
	 * @throws Exception
	 */
	private void createAnnotatedServlet(String projectName) throws Exception {
		NewServletClassDataModel servletModel = setupAnnotatedServletCreationDataModel(projectName);
		createServlet(servletModel);
	}
	
	public NewServletClassDataModel setupAnnotatedServletCreationDataModel(String projectName) {
		NewServletClassDataModel servletDataModel = new NewServletClassDataModel();
		servletDataModel.setProperty(NewServletClassDataModel.PROJECT_NAME, projectName);
		servletDataModel.setProperty(NewServletClassDataModel.CLASS_NAME, "FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.DISPLAY_NAME,"FooServlet");
		servletDataModel.setBooleanProperty(NewServletClassDataModel.USE_ANNOTATIONS,true);
		return servletDataModel;
	}
	
	public NewServletClassDataModel setupServletCreationDataModel(String projectName) {
		NewServletClassDataModel servletDataModel = new NewServletClassDataModel();
		servletDataModel.setProperty(NewServletClassDataModel.PROJECT_NAME, projectName);
		servletDataModel.setProperty(NewServletClassDataModel.CLASS_NAME, "FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.DISPLAY_NAME,"FooServlet");
		servletDataModel.setBooleanProperty(NewServletClassDataModel.USE_ANNOTATIONS,false);
		return servletDataModel;
	}
	
	public void createVaildProjectAndServletCreation(String projectName,int j2eeVersion) throws Exception {
				LogUtility.getInstance().resetLogging();
				J2EEComponentCreationDataModel model = null;
				model = setupStandaloneWebProject(projectName, j2eeVersion);
				LogUtility.getInstance().verifyNoWarnings();
				checkValidDataModel(model);
			}
	
	public void createVaildAnnotatedProjectAndServletCreation(String projectName,int j2eeVersion) throws Exception {
		LogUtility.getInstance().resetLogging();
		J2EEComponentCreationDataModel model = null;
		model = setupStandaloneAnnotatedWebProject(projectName, j2eeVersion);
		LogUtility.getInstance().verifyNoWarnings();
		checkValidDataModel(model);
	}

			/**
			 * @param model
			 */
	protected void checkValidDataModel(J2EEComponentCreationDataModel model) {
			DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(model);
			try {
				verifier.verify(model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	public void testVaild12WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildProjectAndServletCreation("FooTomcatWebProject12", J2EEVersionConstants.WEB_2_2_ID);
	}
	
	/* TODO Uncomment this method when Annotation support for servlets is enabled
	 * public void testVaild12AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("FooAnnotatedTomcatWebProject12", J2EEVersionConstants.WEB_2_2_ID);
	}*/
	
	public void testVaild13WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildProjectAndServletCreation("Foo1TomcatWebProject13", J2EEVersionConstants.WEB_2_3_ID);
	}
	
	/* TODO Uncomment this method when Annotation support for servlets is enabled
	 * public void testVaild13AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("Foo1TomcatWebProject13", J2EEVersionConstants.WEB_2_3_ID);
	}*/
	
	public void testVaild14WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildProjectAndServletCreation("Foo1TomcatWebProject14", J2EEVersionConstants.WEB_2_4_ID);
	}
	
	/* TODO Uncomment this method when Annotation support for servlets is enabled
	 * public void testVaild14AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("Foo1TomcatWebProject14", J2EEVersionConstants.WEB_2_4_ID);
	}*/
	
	
    public static Test suite() {
        return new SimpleTestSuite(WebProjectCreationTomcatTest.class);
    }

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
	 */
	public J2EEComponentCreationDataModel getProjectCreationDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
   
	

}
