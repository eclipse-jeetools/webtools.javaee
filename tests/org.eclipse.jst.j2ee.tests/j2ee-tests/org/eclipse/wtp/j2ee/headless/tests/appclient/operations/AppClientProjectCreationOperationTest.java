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
/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
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

/**
 * @author jsholl
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 */
public class AppClientProjectCreationOperationTest extends ModuleProjectCreationOperationTest {	
	public AppClientProjectCreationOperationTest() {
		super("AppClientProjectCreationOperationTests");
	}
	
	public AppClientProjectCreationOperationTest(String name) {
		super(name);
	}
	
    public static Test suite() {
    	return new SimpleTestSuite(AppClientProjectCreationOperationTest.class);
    }
 
    public void testAC12_Defaults() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("randomApp", null, JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC13_Defaults() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("coolApp", null, JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC14_Defaults() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("awesomeApp", null, JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_Defaults() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("amazingApp", null, JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_Defaults() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("greatApp", null, JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testAC12_NoDefaultClass() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("randomApp", null, JavaEEFacetConstants.APP_CLIENT_12, false, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC13_NoDefaultClass() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("coolApp", null, JavaEEFacetConstants.APP_CLIENT_13, false, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC14_NoDefaultClass() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("awesomeApp", null, JavaEEFacetConstants.APP_CLIENT_14, false, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_NoDefaultClass() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("amazingApp", null, JavaEEFacetConstants.APP_CLIENT_5, false, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_NoDefaultClass() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("greatApp", null, JavaEEFacetConstants.APP_CLIENT_6, false, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testAC12_AddToEAR() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("fooAppToEar", "someEar", JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC13_AddToEAR() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("fooAppToEar", "coolEar", JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC14_AddToEAR() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("fooAppToEar", "sweetEar", JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_AddToEAR() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("fooAppToEar", "netoEar", JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_AddToEAR() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("fooAppToEar", "niceEar", JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testAC12_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("kd3(2k_djfD3", null, JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC13_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("a_dD3dj8)f7", null, JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC14_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("_Jid7dh)3a", null, JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("a_1B2c()3D4", null, JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("ASDbt_23()Gfr2", null, JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testAC12_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("kd(32k_djfD)3", "hFdf(8G_Fij))3", JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC13_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("adD__3dj8)df7", "(53_hdj(f8HD", JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC14_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("J_id7((dh3a_", "d_3Dk)j(f8", JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("a1B_2c)3D4", "4D_3c2)B1a", JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = getAppClientCreationDataModel("ASDbt_23()Gfr2", "23Sgsd)(_fg4", JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("insaneApp", null, JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_NoDefaultClass_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("insaneApp", null, JavaEEFacetConstants.APP_CLIENT_5, false, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_AddToEAR_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("appToEARwithDD", "bigEAR", JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC50_AddToEAR_InterestingName_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("D875)_DFj", "7D_3cF2)BaQ", JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("freshApp", null, JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_NoDefaultClass_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("freshApp", null, JavaEEFacetConstants.APP_CLIENT_6, false, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_AddToEAR_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("tryAddToEARWithDD", "tryEAR", JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testAC60_AddToEAR_InterestingName_WithDD() throws Exception {
    	IDataModel dm = getAppClientCreationDataModel("4vG_s(70)", "RAS_b46j)(a)1", JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    /**
     * Creates and returns an Application Client Data Model with the given name and of the given version.
     * If earName is not null then AppClient will be added to the EAR with earName, and if appropriate
     * with or without a deployment descriptor.
     * 
     * @param projName name of the project to create
     * @param earName name of the ear to add the project too, if NULL then don't add to an EAR
     * @param version version of Application Client to use
     * @param createDefaultMainClass if true then create default main class, else don't
     * @param createDD only used if version is JEE5, if true then create DD else don't
     * @return an Application Data Model with the appropriate properties set
     */
    public static IDataModel getAppClientCreationDataModel(String projName, String earName, IProjectFacetVersion version, boolean createDefaultMainClass, boolean createDD){
    	IDataModel dm = DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	
    	if(earName != null) {
        	dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
        	dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);
    	} else {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
    	}
    	
    	FacetDataModelMap facetMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.APPLICATION_CLIENT);
        facetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, version);
        facetModel.setProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS, createDefaultMainClass);
        
        
        facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);
        
        if(version.equals(JavaEEFacetConstants.APP_CLIENT_6))
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
}
