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
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
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
    
    private void createConnectorComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new ConnectorFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel jcaModel = map.getFacetDataModel(J2EEProjectUtilities.JCA);
        jcaModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createWebComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel webModel = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
        webModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }

    private void createAppClientComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel appClientModel = map.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT);
        appClientModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createEjbComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel ejbModel = map.getFacetDataModel(J2EEProjectUtilities.EJB);
        ejbModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    private void createEARComponent(int j2eeVersion, String aModuleName, String projectName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new EARFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel earModel = map.getFacetDataModel(J2EEProjectUtilities.ENTERPRISE_APPLICATION);
        earModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }
    
    public void testCreateWebComponentWithUserDefinedFolders() throws Exception {
          IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
          model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, "TestWeb24"); //$NON-NLS-1$   
          FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
          IDataModel webModel = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
          webModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,24);
          webModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
          webModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web"); //$NON-NLS-1$
          webModel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src"); //$NON-NLS-1$
          model.getDefaultOperation().execute(new NullProgressMonitor(), null);
      }    
    
    public void testCreateAppClientComponentWithUserDefinedFolders() throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, "TestApp15"); //$NON-NLS-1$
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel appClientModel = map.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT);
        appClientModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,14); 
        appClientModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER, "src"); //$NON-NLS-1$
        appClientModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
    }    
}
