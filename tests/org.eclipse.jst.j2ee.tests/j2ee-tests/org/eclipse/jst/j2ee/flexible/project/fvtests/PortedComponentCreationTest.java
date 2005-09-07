/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.jca.operations.IConnectorComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.tests.modulecore.AllTests;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public class PortedComponentCreationTest extends TestCase {

    public static String DEFAULT_PROJECT_NAME = "Flexible";

    public static Test suite() {
        return new TestSuite(PortedComponentCreationTest.class);
    }

    public PortedComponentCreationTest() {
        super();
    }

    public PortedComponentCreationTest(String name) {
        super(name);
    }

    /*
    public void testCreateFlexibleProject() throws Exception {
        IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleProjectCreationDataModelProvider());
        dataModel.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME, DEFAULT_PROJECT_NAME + "_");
        dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    */
    
    public void createFlexibleJavaProject() throws Exception {
        createFlexibleJavaProject(DEFAULT_PROJECT_NAME + "_Java");
    }

//    public void testCreateJavaUtiltyComponent() throws Exception {
//        //createFlexibleJavaProject(DEFAULT_PROJECT_NAME + "_JavaUtil");
//        createJavaUtilComponent(DEFAULT_PROJECT_NAME + "_JavaUtil", DEFAULT_PROJECT_NAME + "_JavaUtil");
//    }
//
//    public void testCreateConnectorComponent() throws Exception {
//       // createFlexibleJavaProject(DEFAULT_PROJECT_NAME + "_ConnectorProject");
//        createConnectorComponent(15, "TestConnector", DEFAULT_PROJECT_NAME + "_ConnectorProject");
//    }
//
//    
//    public void testCreateWebComponent() throws Exception {
//      //  createFlexibleJavaProject(DEFAULT_PROJECT_NAME+"_WebProject");
//        createWebComponent(24, "TestWeb", DEFAULT_PROJECT_NAME+"_WebProject");
//    }
//    
//    public void testCreateAppClientComponent() throws Exception {
//      //  createFlexibleJavaProject(DEFAULT_PROJECT_NAME+"_AppClientProject");
//        createAppClientComponent(14, "TestAppClient", DEFAULT_PROJECT_NAME+"_AppClientProject");
//    }
//    
//    public void testCreateEjbComponent() throws Exception {
//       // createFlexibleJavaProject(DEFAULT_PROJECT_NAME+"_EJBProject");
//        createEjbComponent(21, "TestEJB", DEFAULT_PROJECT_NAME+"_EJBProject");
//    }
//    
//    public void testCreateEARComponent() throws Exception {
//       // createFlexibleJavaProject(DEFAULT_PROJECT_NAME+"_EARProject");
//        createEARComponent(14, "TestEAR", DEFAULT_PROJECT_NAME+"_EARProject");
//    }
    
    private void createFlexibleJavaProject(String projectName) throws Exception {
        IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
        dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.PROJECT_NAME, projectName);
        dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.RUNTIME_TARGET_ID, AllTests.JONAS_TOMCAT_RUNTIME.getId());
        dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }

    private void createJavaUtilComponent(String aModuleName, String projectName) throws Exception {
        IDataModel model = DataModelFactory.createDataModel(new JavaComponentCreationDataModelProvider());
        model.setProperty(IJavaComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setProperty(IJavaComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createConnectorComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new ConnectorComponentCreationDataModelProvider());
        model.setProperty(IConnectorComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setIntProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IConnectorComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.setBooleanProperty(IConnectorComponentCreationDataModelProperties.ADD_TO_EAR, true);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createWebComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
        model.setProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.setBooleanProperty(IWebComponentCreationDataModelProperties.ADD_TO_EAR, true);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }

    private void createAppClientComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
        model.setProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setIntProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.setBooleanProperty(IAppClientComponentCreationDataModelProperties.ADD_TO_EAR, true);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createEjbComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
        model.setProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setIntProperty(IEjbComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IEjbComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.setBooleanProperty(IEjbComponentCreationDataModelProperties.ADD_TO_EAR, true);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createEARComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
        model.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, projectName);
        model.setIntProperty(IEarComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IEarComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    public void testCreateWebComponentWithUserDefinedFolders() throws Exception {
          IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
          model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, "TestWeb24");          
          model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, 24);
          model.setProperty(IWebComponentCreationDataModelProperties.JAVASOURCE_FOLDER, "src");
          model.setProperty(IWebComponentCreationDataModelProperties.WEBCONTENT_FOLDER, "web");          
          model.setBooleanProperty(IWebComponentCreationDataModelProperties.ADD_TO_EAR, false);
          model.getDefaultOperation().execute(new NullProgressMonitor(), null);
      }    
    
    public void testCreateAppClientComponentWithUserDefinedFolders() throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());

        model.setProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_NAME, "TestApp15");
        model.setProperty(IAppClientComponentCreationDataModelProperties.JAVASOURCE_FOLDER, "src");
        model.setIntProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_VERSION, 14);        
        model.setBooleanProperty(IAppClientComponentCreationDataModelProperties.ADD_TO_EAR, false);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }    
}
