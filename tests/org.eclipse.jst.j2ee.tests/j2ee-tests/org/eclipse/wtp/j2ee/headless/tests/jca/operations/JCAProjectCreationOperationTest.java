/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.wtp.j2ee.headless.tests.jca.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.jca.project.facet.IConnectorFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class JCAProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
	public JCAProjectCreationOperationTest() {
		super("ConnectorProjectCreationOperationTests");
	}

	public JCAProjectCreationOperationTest(String name) {
		super(name);
	}

    public static Test suite() {
    	return new SimpleTestSuite(JCAProjectCreationOperationTest.class);
    }
    
    public void testConnector10_Defaults() throws Exception{
    	IDataModel dm = getConnectorDataModel("aConnector", null, null, JavaEEFacetConstants.CONNECTOR_1);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector15_Defaults() throws Exception{
    	IDataModel dm = getConnectorDataModel("bConnector", null, null, JavaEEFacetConstants.CONNECTOR_15);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector16_Defaults() throws Exception{
    	IDataModel dm = getConnectorDataModel("iConnector", null, null, JavaEEFacetConstants.CONNECTOR_16);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector10_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("cConnector", "myEAR", null, JavaEEFacetConstants.CONNECTOR_1);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector15_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("dConnector", "yourEAR", null, JavaEEFacetConstants.CONNECTOR_15);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector16_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("jConnector", "herEAR", null, JavaEEFacetConstants.CONNECTOR_16);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector10_ChangedSrouceFolder() throws Exception{
    	IDataModel dm = getConnectorDataModel("eConnector",null, "mySrc", JavaEEFacetConstants.CONNECTOR_1);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector15_ChangedSrouceFolder() throws Exception{
    	IDataModel dm = getConnectorDataModel("fConnector", null, "ourSrc", JavaEEFacetConstants.CONNECTOR_15);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector16_ChangedSrouceFolder() throws Exception{
    	IDataModel dm = getConnectorDataModel("kConnector", null, "herSrc", JavaEEFacetConstants.CONNECTOR_16);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector10_ChangedSrouceFolder_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("gConnector", "coolEAR", "theirSrc", JavaEEFacetConstants.CONNECTOR_1);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector15_ChangedSrouceFolder_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("hConnector", "netoEAR", "weSrc", JavaEEFacetConstants.CONNECTOR_15);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testConnector16_ChangedSrouceFolder_WithEAR() throws Exception{
    	IDataModel dm = getConnectorDataModel("lConnector", "niceEAR", "hisSrc", JavaEEFacetConstants.CONNECTOR_16);
    	OperationTestCase.runAndVerify(dm);
    }

    /**
     * Creates and returns an Connector Data Model with the given name and of the given version.
     * If earName is not null then Connector will be added to the EAR with earName.
     * Can also specify none default source folder
     * 
     * @param projName name of the project to create
     * @param earName name of the EAR to add the project too, if NULL then don't add to an EAR
     * @param sourceFolder name of the source folder to use, if NULL then use default
     * @param version version of Application Client to use
     * @return a Connector Model with the appropriate properties set
     */
    public static IDataModel getConnectorDataModel(String projName, String earName, String sourceFolder, IProjectFacetVersion version){
    	IDataModel dm = DataModelFactory.createDataModel(new ConnectorFacetProjectCreationDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	
    	if(earName != null) {
        	dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
        	dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);
    	} else {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
    	}
    	
    	FacetDataModelMap facetMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JCA);
        facetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, version);
        
        if(sourceFolder != null) {
        	facetModel.setProperty(IConnectorFacetInstallDataModelProperties.CONFIG_FOLDER, sourceFolder);
        }
        
        //be sure to use Java5 with JEE5
        if(version == JavaEEFacetConstants.CONNECTOR_15){
            IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
            javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_5);
        }
        else if(version == JavaEEFacetConstants.CONNECTOR_16) {
            IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
            javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_6);
        }
        
    	return dm;
    }

//    public IDataModel getComponentCreationDataModel() {
//        return DataModelFactory.createDataModel(IConnectorFacetInstallDataModelProperties.class);
//    }
//    
//    public IDataModel getComponentCreationDataModelWithEar() {
//        IDataModel model =  getComponentCreationDataModel();
//        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
//        IDataModel facetDM = map.getFacetDataModel(IConnectorFacetInstallDataModelProperties.JCA);
//        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
//        return model;
//    }   
    
}
