/*
 * Created on May 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddServletOperationTest extends OperationTestCase {
    public static String WEB_PROJECT_NAME = "WebProject"; //$NON-NLS-1$
    public static String SERVLET_NAME = "Servlet1"; //$NON-NLS-1$

    private IDataModel webComponentDataModel;
	private IDataModel servletDataModel;
    
    /**
	 * @param name
	 */
	public AddServletOperationTest(String name) {
		super(name);
	}

	/**
	 * Default constructor
	 */
	public AddServletOperationTest() {
		super();
	}

	public static Test suite() {
        return new TestSuite(AddServletOperationTest.class);
    }

    public void testAddServlet() throws Exception {
    	createWebProject(WEB_PROJECT_NAME);
    	WebArtifactEdit webEdit = null;
    	ComponentHandle handle = ComponentHandle.create(ProjectUtilities.getProject(WEB_PROJECT_NAME), WEB_PROJECT_NAME);
    	try {
    		webEdit = WebArtifactEdit.getWebArtifactEditForWrite(handle);
    		WebApp webApp = webEdit.getWebApp();
        	addServlet(WEB_PROJECT_NAME, SERVLET_NAME);
        	if (webApp != null){
        		Servlet servlet = webApp.getServletNamed(SERVLET_NAME);
        		assertNotNull(servlet);
    			//TODO need to get the 
        		//addInitParams(servlet,WEB_PROJECT_NAME);
        	}
    	} finally {
    		if (webEdit != null)
    			webEdit.dispose();
    	}
    	
    }
    
    public void createWebProject(String projectName) throws Exception {
    	webComponentDataModel = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
    	webComponentDataModel.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME, projectName);
    	webComponentDataModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, projectName);
    	runAndVerify(webComponentDataModel);
    }
    /**
     * @param projectName
     * @param servletName
     */
    public void addServlet(String projectName, String servletName) throws Exception {
    	servletDataModel = DataModelFactory.createDataModel(NewServletClassDataModelProvider.class);
    	servletDataModel.setProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME, projectName);
    	servletDataModel.setProperty(IArtifactEditOperationDataModelProperties.COMPONENT_NAME, projectName);
    	servletDataModel.setProperty(INewServletClassDataModelProperties.DISPLAY_NAME, servletName);
    	servletDataModel.setProperty(INewJavaClassDataModelProperties.CLASS_NAME, servletName);
    	servletDataModel.setBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS,false);
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
    
    
}
