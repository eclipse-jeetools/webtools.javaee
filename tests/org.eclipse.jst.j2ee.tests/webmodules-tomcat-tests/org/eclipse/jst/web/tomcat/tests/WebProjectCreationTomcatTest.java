/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.web.tomcat.tests;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.web.operations.AddServletOperation;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.DataModelVerifierFactory;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wst.common.tests.TaskViewUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.AbstractProjectCreationTest;

/**
 * @author blancett
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebProjectCreationTomcatTest extends AbstractProjectCreationTest {
	protected String projectName = null;
	
	public static void createStandaloneWebProject(WebModuleCreationDataModel model) throws Exception {
		WebModuleCreationOperation webOp = new WebModuleCreationOperation(model);
		webOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}
	
	public static void createServlet(NewServletClassDataModel model) throws Exception {
		AddServletOperation op = new AddServletOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
		
	}
	
	public WebModuleCreationDataModel setupStandaloneWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebModuleCreationDataModel model = new WebModuleCreationDataModel();
		model.setProperty(WebModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setProperty(WebModuleCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		model.setProperty(WebModuleCreationDataModel.SERVER_TARGET_ID,AllTomcatTests.TOMCAT_RUNTIME.getId());
		createStandaloneWebProject(model);
		createServlet();
		return model;
	}
	
	public WebModuleCreationDataModel setupStandaloneAnnotatedWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebModuleCreationDataModel model = new WebModuleCreationDataModel();
		model.setProperty(WebModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setProperty(WebModuleCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		model.setProperty(WebModuleCreationDataModel.SERVER_TARGET_ID,AllTomcatTests.TOMCAT_RUNTIME.getId());
		createStandaloneWebProject(model);
		createAnnotatedServlet();
		return model;
	}

	/**
	 * @throws Exception
	 */
	private void createServlet() throws Exception {
		NewServletClassDataModel servletModel = setupServletCreationDataModel();
		createServlet(servletModel);
	}
	
	/**
	 * @throws Exception
	 */
	private void createAnnotatedServlet() throws Exception {
		NewServletClassDataModel servletModel = setupAnnotatedServletCreationDataModel();
		createServlet(servletModel);
	}
	
	public NewServletClassDataModel setupAnnotatedServletCreationDataModel() {
		NewServletClassDataModel servletDataModel = new NewServletClassDataModel();
		servletDataModel.setProperty(NewServletClassDataModel.CLASS_NAME, "FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.DISPLAY_NAME,"FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.USE_ANNOTATIONS,Boolean.TRUE);
		return servletDataModel;
	}
	
	public NewServletClassDataModel setupServletCreationDataModel() {
		NewServletClassDataModel servletDataModel = new NewServletClassDataModel();
		servletDataModel.setProperty(NewServletClassDataModel.CLASS_NAME, "FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.DISPLAY_NAME,"FooServlet");
		servletDataModel.setProperty(NewServletClassDataModel.USE_ANNOTATIONS,Boolean.FALSE);
		return servletDataModel;
	}
	
	public void createVaildProjectAndServletCreation(String projectName,int j2eeVersion) throws Exception {
				LogUtility.getInstance().resetLogging();
				J2EEArtifactCreationDataModel model = null;
				model = setupStandaloneWebProject(projectName, j2eeVersion);
				LogUtility.getInstance().verifyNoWarnings();
				checkValidDataModel(model);
			}
	
	public void createVaildAnnotatedProjectAndServletCreation(String projectName,int j2eeVersion) throws Exception {
		LogUtility.getInstance().resetLogging();
		J2EEArtifactCreationDataModel model = null;
		model = setupStandaloneAnnotatedWebProject(projectName, j2eeVersion);
		LogUtility.getInstance().verifyNoWarnings();
		checkValidDataModel(model);
	}

			/**
			 * @param model
			 */
	protected void checkValidDataModel(J2EEArtifactCreationDataModel model) {
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
	
	public void testVaild12AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildProjectAndServletCreation("FooAnnotatedTomcatWebProject12", J2EEVersionConstants.WEB_2_2_ID);
	}
	
	public void testVaild13WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("Foo1TomcatWebProject13", J2EEVersionConstants.WEB_2_3_ID);
	}
	
	public void testVaild13AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("Foo1TomcatWebProject13", J2EEVersionConstants.WEB_2_3_ID);
	}
	
	public void testVaild14WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildProjectAndServletCreation("Foo1TomcatWebProject14", J2EEVersionConstants.WEB_2_4_ID);
	}
	
	public void testVaild14AnnotatedWebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		createVaildAnnotatedProjectAndServletCreation("Foo1TomcatWebProject14", J2EEVersionConstants.WEB_2_4_ID);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(WebProjectCreationTomcatTest.class);
    }

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
	 */
	public J2EEModuleCreationDataModel getProjectCreationDataModel() {
		// TODO Auto-generated method stub
		return null;
	}
   
	

}
