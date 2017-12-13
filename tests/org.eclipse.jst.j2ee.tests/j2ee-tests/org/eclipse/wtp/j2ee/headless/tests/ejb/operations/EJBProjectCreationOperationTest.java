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

package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class EJBProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

	public EJBProjectCreationOperationTest() {
		super("EJBProjectCreationOperationTest");
	}
	
	public EJBProjectCreationOperationTest(String name) {
		super(name);
	}
	
    public static Test suite() {
    	return new SimpleTestSuite(EJBProjectCreationOperationTest.class);
    }
    
    public void testEJB11_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("insaneEJB", null, null, null, JavaEEFacetConstants.EJB_11, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("fooEJB", null, null, null, JavaEEFacetConstants.EJB_2, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("barEJB", null, null, null, JavaEEFacetConstants.EJB_21, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("penEJB", null, null, null, JavaEEFacetConstants.EJB_3, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("blahEJB", null, null, null, JavaEEFacetConstants.EJB_31, false);
    	runAndVerify(dm);
    }
    
    
    public void testEJB11_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("testEJB", null, null, "testEAR", JavaEEFacetConstants.EJB_11, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("coolEJB", null, null, "booEAR", JavaEEFacetConstants.EJB_2, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("crazyEJB", null, null, "starEAR", JavaEEFacetConstants.EJB_21, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = getEJBDataModel("booEJB", null, null, "trymeEAR", JavaEEFacetConstants.EJB_31, false);
    	runAndVerify(dm);
    }    
    
    public void testEJB11_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = getEJBDataModel("testEJB", null, null, "testEAR", JavaEEFacetConstants.EJB_11, false, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = getEJBDataModel("coolEJB", null, null, "booEAR", JavaEEFacetConstants.EJB_2, false, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = getEJBDataModel("crazyEJB", null, null, "starEAR", JavaEEFacetConstants.EJB_21, false, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = getEJBDataModel("booEJB", null, null, "trymeEAR", JavaEEFacetConstants.EJB_31, false, false);
    	runAndVerify(dm);
    }   
    
    public void testEJB11_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = getEJBDataModel("fooBarEJB", "testEJBClient", null, "theirEAR", JavaEEFacetConstants.EJB_11, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = getEJBDataModel("saneEJB", "coolEJBClient", null, "yourEAR", JavaEEFacetConstants.EJB_2, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = getEJBDataModel("clipEJB", "crazyEJBClient", null, "ourEAR", JavaEEFacetConstants.EJB_21, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = getEJBDataModel("phoneEJB", "netoEJBClient", null, "waterEAR", JavaEEFacetConstants.EJB_3, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = getEJBDataModel("scaryEJB", "booEJBClient", null, "scaredEAR", JavaEEFacetConstants.EJB_31, false);
    	runAndVerify(dm);
    }
    
    public void testEJB11_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("cupEJB", null, "src", "openEAR", JavaEEFacetConstants.EJB_11, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("mouseEJB", null, "files", "closedEAR", JavaEEFacetConstants.EJB_2, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("pcEJB", null, "stuff", "batEAR", JavaEEFacetConstants.EJB_21, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("keyEJB", null, "foo", "keyEAR", JavaEEFacetConstants.EJB_3, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("goEJB", null, "boo", "goEAR", JavaEEFacetConstants.EJB_31, false);
    	runAndVerify(dm);
    }    
    
    public void testEJB11A_ddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("cupEJB", "superClient", "src", "openEAR", JavaEEFacetConstants.EJB_11, true);
    	runAndVerify(dm);
    }
    
    public void testEJB20_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("mouseEJB", "dudeClient", "files", "closedEAR", JavaEEFacetConstants.EJB_2, true);
    	runAndVerify(dm);
    }
    
    public void testEJB21_AddToEAR_ChangedEJB_ClientNameChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("pcEJB", "fireClient", "stuff", "batEAR", JavaEEFacetConstants.EJB_21, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEARChangedEJB_ClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("keyEJB", "wireClient", "foo", "keyEAR", JavaEEFacetConstants.EJB_3, false);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEARChangedEJB_ClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = getEJBDataModel("goEJB", "workClient", "boo", "goEAR", JavaEEFacetConstants.EJB_31, false);
    	runAndVerify(dm);
    }
    
    public void testEJB30_Defaults_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("tigerEJB", null, null, null, JavaEEFacetConstants.EJB_3, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_Defaults_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("pandaEJB", null, null, "roundEAR", JavaEEFacetConstants.EJB_3, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_NoClient_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_ChangedEJBClientName_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("snakeEJB", "client", null, "groundEAR", JavaEEFacetConstants.EJB_3, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("lionEJB", null, "barSrc", "pinEAR", JavaEEFacetConstants.EJB_3, true);
    	runAndVerify(dm);
    }
    
    public void testEJB30_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("monkeyEJB", "fooFooClient", "fooSrc", "outsideEAR", JavaEEFacetConstants.EJB_3, true);
    	runAndVerify(dm);
    }
    
    public void testEJB31_Defaults_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("spiderEJB", null, null, null, JavaEEFacetConstants.EJB_31, true);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_Defaults_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("yogiEJB", null, null, "bearEAR", JavaEEFacetConstants.EJB_31, true);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_NoClient_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("booEJB", null, null, "trymeEAR", JavaEEFacetConstants.EJB_31, false, true);
    	runAndVerify(dm);
    }   
    
    public void testEJB31_AddToEAR_ChangedEJBClientName_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("starEJB", "rockstarclient", null, "rockEAR", JavaEEFacetConstants.EJB_31, true);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("sharkEJB", null, "fishySrc", "fishEAR", JavaEEFacetConstants.EJB_31, true);
    	runAndVerify(dm);
    }
    
    public void testEJB31_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = getEJBDataModel("trainEJB", "booClient", "booSrc", "fastEAR", JavaEEFacetConstants.EJB_31, true);
    	runAndVerify(dm);
    }
    /**
     * Creates and returns an EJB Data Model with the given name and of the given version.
     * Can also set the clientName to be different then the default.
     * If earName is not null then AppClient will be added to the EAR with earName, and if appropriate
     * with or without a deployment descriptor.
     * 
     * @param projName name of the project to create
     * @param clientName name of client jar to create, if NULL or earName is NULL then don't create one
     * @param clientSourceFolder source folder for client, use default if value is NULL, ignored if clientName is NULL
     * @param earName name of the EAR to add the project too, if NULL then don't add to an EAR
     * @param version version of EJB to use
     * @param createDD only used if version is JEE5, if true then create DD else don't
     * @return an EJB Model with the appropriate properties set
     */
    public static IDataModel getEJBDataModel(String projName, String clientName, String clientSourceFolder, String earName, IProjectFacetVersion version, boolean createDD) {
    	IDataModel dm = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);

    	FacetDataModelMap facetMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
    	facetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, version);

    	if(earName != null) {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);

    		//only create client if given a client name, and is added to EAR
    		if(clientName != null) {
    			facetModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, true);
    			facetModel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME, clientName);

    			//use default source folder unless different name is given
    			if(clientSourceFolder != null) {
    				facetModel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER, clientSourceFolder);
    			}
    		}
    	} else {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
    	}

    	facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);

    	if(version.equals(JavaEEFacetConstants.EJB_31))
    	{
    		IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
	    	javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_6);
    	}
    	else{    
	    	IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
	    	javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_5);
    	}

        
    	return dm;
	}
    
    /**
     * Creates and returns an EJB Data Model with the given name and of the given version.
     * Can also set the clientName to be different then the default, or choose not to have a client.
     * If earName is not null then AppClient will be added to the EAR with earName, and if appropriate
     * with or without a deployment descriptor.
     * 
     * Created so EJB's could be created without clients.
     * 
     * @param projName name of the project to create
     * @param clientName name of client jar to create, if NULL or earName is NULL then don't create one
     * @param clientSourceFolder source folder for client, use default if value is NULL, ignored if clientName is NULL
     * @param earName name of the EAR to add the project too, if NULL then don't add to an EAR
     * @param version version of EJB to use
     * @param createClient if True and earName not NULL then create with client, else dont
     * @param createDD only used if version is JEE5, if true then create DD else don't
     * @return an EJB Model with the appropriate properties set
     */
    public static IDataModel getEJBDataModel(String projName, String clientName, String clientSourceFolder, String earName, IProjectFacetVersion version, boolean createCleint, boolean createDD) {
    	IDataModel dm = getEJBDataModel(projName, clientName, clientSourceFolder, earName, version, createDD);
    	
    	FacetDataModelMap facetMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
    	facetModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, createCleint);
        
    	return dm;
	} 

}
