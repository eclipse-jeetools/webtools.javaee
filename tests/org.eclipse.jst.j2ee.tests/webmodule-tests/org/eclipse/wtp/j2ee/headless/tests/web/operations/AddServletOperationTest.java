/*
 * Created on May 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddServletOperationTest extends OperationTestCase {
    public static String WEB_PROJECT_NAME = "WebProject";
    public static String SERVLET_NAME = "Servlet1";

    private IDataModel webComponentDataModel;
	private NewServletClassDataModel servletDataModel;
    
    /**
	 * @param name
	 */
	public AddServletOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public AddServletOperationTest() {
		
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new SimpleTestSuite(AddServletOperationTest.class);
    }

    public void testAddServlet() throws Exception {
    	createWebProject(WEB_PROJECT_NAME);
    	WebApp webApp = getWebApp();
    	addServlet(WEB_PROJECT_NAME, SERVLET_NAME);
    	if (webApp != null){
    		Servlet servlet = webApp.getServletNamed(SERVLET_NAME);
			//TODO need to get the 
    		//addInitParams(servlet,WEB_PROJECT_NAME);
    	}
    }
    
    public void createWebProject(String projectName) throws Exception {
    	webComponentDataModel = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
    	webComponentDataModel.setProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        runAndVerify(webComponentDataModel);
    }
    /**
     * @param projectName
     * @param servletName
     */
    public void addServlet(String projectName, String servletName) throws Exception {
    	servletDataModel = new NewServletClassDataModel();
    	servletDataModel.setProperty(NewServletClassDataModel.PROJECT_NAME, projectName);
    	servletDataModel.setProperty(NewServletClassDataModel.DISPLAY_NAME, servletName);
		
        NewServletClassDataModel nestedModel = new NewServletClassDataModel();
        servletDataModel.addNestedModel("NewServletClassDataModel", nestedModel); //$NON-NLS-1$
		nestedModel.setProperty(NewServletClassDataModel.PROJECT_NAME, projectName);
		nestedModel.setProperty(NewServletClassDataModel.CLASS_NAME, servletName);
		nestedModel.setProperty(NewServletClassDataModel.SUPERCLASS, NewServletClassDataModel.SUPERCLASS);
		nestedModel.setProperty(NewServletClassDataModel.INTERFACES, 
				servletDataModel.getServletInterfaces());
        runAndVerify(servletDataModel);
    }
	
	
    
//    protected void addInitParams(Servlet servlet,String projectName) throws Exception {
//    	initParamDataModel = new AddServletInitParamDataModel();
//    	initParamDataModel.setProperty(AddServletInitParamDataModel.SERVLET, servlet);
//    	initParamDataModel.setProperty(AddServletInitParamDataModel.PROJECT_NAME,projectName);
//    	for (int i = 0; i < 10; i++) {
//        	String paramName = "param" + String.valueOf(i);
//        	String paramValue = "value" + String.valueOf(i);
//        	initParamDataModel.setProperty(AddServletInitParamDataModel.PARAMETER_NAME, paramName);
//        	initParamDataModel.setProperty(AddServletInitParamDataModel.PARAMETER_VALUE, paramValue);
//    	}
//        runAndVerify(initParamDataModel);
//    }
    
    protected WebApp getWebApp() {
    	IProject webProject = null;
    	if (servletDataModel != null){
    		webProject = servletDataModel.getTargetProject();
    	}
    	else{
    		webProject = ProjectUtility.getProject(WEB_PROJECT_NAME);
    	}
        J2EEWebNatureRuntime nature = (J2EEWebNatureRuntime)J2EENature.getRegisteredRuntime(webProject);

        if (nature != null)
        	return nature.getWebApp();
    	return null;
    }
}
