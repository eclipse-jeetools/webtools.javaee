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
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;

import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.JCAProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;


/**
 * @author jsholl
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public class EARProjectCreationOperationTest extends JEEProjectCreationOperationTest {
	private static final String APP_CLIENT_PROJ_12 = "myAppClient_12";
	private static final String APP_CLIENT_PROJ_13 = "myAppClient_13";
	private static final String APP_CLIENT_PROJ_14 = "myAppClient_14";
	private static final String APP_CLIENT_PROJ_5 = "myAppClient_5";
	
	private static final String EJB_PROJ_11 = "myEJB_11";
	private static final String EJB_PROJ_2 = "myEJB_2";
	private static final String EJB_PROJ_21 = "myEJB_21";
	private static final String EJB_PROJ_3 = "myEJB_3";
	
	private static final String WEB_PROJ_22 = "myWeb_22";
	private static final String WEB_PROJ_23 = "myWeb_23";
	private static final String WEB_PROJ_24 = "myWeb_24";
	private static final String WEB_PROJ_25 = "myWeb_25";
	
	private static final String CONNECTOR_PROJ_1 = "myConnector_1";
	private static final String CONNECTOR_PROJ_15 = "myConnector_15";
	
	
    public static Test suite() {
    	return new SimpleTestSuite(EARProjectCreationOperationTest.class);
    }
    
    public void testEAR12_Defaults() throws Exception{
    	IDataModel dm = getEARDataModel("aEAR", null, null, null, JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR13_Defaults() throws Exception{
    	IDataModel dm = getEARDataModel("bEAR", null, null, null, JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR14_Defaults() throws Exception{
    	IDataModel dm = getEARDataModel("cEAR", null, null, null, JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_Defaults() throws Exception{
    	IDataModel dm = getEARDataModel("dEAR", null, null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR12_ChangedContentDir() throws Exception{
    	IDataModel dm = getEARDataModel("eEAR", "whosContent", null, null, JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR13_ChangedContentDir() throws Exception{
    	IDataModel dm = getEARDataModel("fEAR", "myContent", null, null, JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR14_ChangedContentDir() throws Exception{
    	IDataModel dm = getEARDataModel("gEAR", "yourContent", null, null, JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_ChangedContentDir() throws Exception{
    	IDataModel dm = getEARDataModel("hEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR12_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("zEAR", null, getJ2EEDependencyList_12(), getJavaDependencyList_12(), JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR13_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("yEAR", null, getJ2EEDependencyList_13(), getJavaDependencyList_13(), JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR14_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("xEAR", null, getJ2EEDependencyList_14(), getJavaDependencyList_14(), JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("wEAR", null, getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR12_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("zEAR", "myContent", getJ2EEDependencyList_12(), getJavaDependencyList_12(), JavaEEFacetConstants.EAR_12, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR13_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("yEAR", "ourContent", getJ2EEDependencyList_13(), getJavaDependencyList_13(), JavaEEFacetConstants.EAR_13, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR14_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("xEAR", "theirContent", getJ2EEDependencyList_14(), getJavaDependencyList_14(), JavaEEFacetConstants.EAR_14, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("wEAR", "yourContent", getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR50_Defaults_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("myEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_ChangedContentDir_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("yourEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("anEAR", null, getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_ChangedContentDir_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("theirEAR", "gotContent", getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    /**
     * Creates and returns an EAR Data Model with the given name and of the given version 
     * 
     * @param projName name of the project to create
     * @param version version of EAR to use
     * @param contentDir directory to store the content in, if NULL use default
     * @param dependenciesJ2EE list of J2EE IProjects that this EAR depends on, ignored if NULL
     * @param dependenciesJava list of Java IProjects that this EAR depends on, ignored if NULL
     * @param createDD only used if version is JEE5, if true then create DD else don't
     * @return an EAR Data Model with the appropriate properties set
     */
    public static IDataModel getEARDataModel(String projName, String contentDir, List dependenciesJ2EE, List dependenciesJava, IProjectFacetVersion version, boolean createDD) {
    	IDataModel dm = DataModelFactory.createDataModel(new EARFacetProjectCreationDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);

		FacetDataModelMap factMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = (IDataModel) factMap.get(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		facetModel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, version);
		
		
		if(contentDir != null) {
			facetModel.setStringProperty(IEarFacetInstallDataModelProperties.CONTENT_DIR,contentDir); 
		}
		
		if(dependenciesJ2EE != null) {
			facetModel.setProperty(IEarFacetInstallDataModelProperties.J2EE_PROJECTS_LIST, dependenciesJ2EE);
		}
		
		if(dependenciesJava != null) {
			facetModel.setProperty(IEarFacetInstallDataModelProperties.JAVA_PROJECT_LIST, dependenciesJava);
		}
		
        //this option only exists if JEE5
        if(version == JavaEEFacetConstants.EAR_5){
            facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);
        }
		
    	return dm;
    }
    
    
    
    public static List getJ2EEDependencyList_12() throws Exception {
    	List dependencies = new ArrayList();
    	List<IDataModel> models = new ArrayList<IDataModel>();
    	
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_12, null, JavaEEFacetConstants.APP_CLIENT_12, true, true));
    	
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_11, null, null, null, JavaEEFacetConstants.EJB_11, true));
    	
    	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_22, null, null, null, null, JavaEEFacetConstants.WEB_22, true));
    	
    	for(int i = 0; i < models.size(); i++) {
    		OperationTestCase.runDataModel(models.get(i));
    	}
    	
    	dependencies.addAll(Arrays.asList(JavaEEProjectUtilities.getAllProjects()));
    	
    	return dependencies;
    }
    
    public static List getJavaDependencyList_12() {
    	return Collections.emptyList();
    }
    
    
    public static List getJ2EEDependencyList_13() throws Exception {
    	getJ2EEDependencyList_12();
    	List dependencies = new ArrayList();
    	List<IDataModel> models = new ArrayList<IDataModel>();
    	
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_13, null, JavaEEFacetConstants.APP_CLIENT_13, true, true));
    	
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_2, null, null, null, JavaEEFacetConstants.EJB_2, true));
    	
    	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_23, null, null, null, null, JavaEEFacetConstants.WEB_23, true));
    	
    	models.add(JCAProjectCreationOperationTest.getConnectorDataModel(CONNECTOR_PROJ_1, null, null, JavaEEFacetConstants.CONNECTOR_1));
    	
    	for(int i = 0; i < models.size(); i++) {
    		OperationTestCase.runDataModel(models.get(i));
    	}
    	
    	dependencies.addAll(Arrays.asList(JavaEEProjectUtilities.getAllProjects()));
    	
    	return dependencies;
    }
    
    public static List getJavaDependencyList_13() {
    	return Collections.emptyList();
    }
    
    
    public static List getJ2EEDependencyList_14() throws Exception {
    	getJ2EEDependencyList_13();
    	List dependencies = new ArrayList();
    	
    	List<IDataModel> models = new ArrayList<IDataModel>();
    	
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_14, null, JavaEEFacetConstants.APP_CLIENT_14, true, true));

    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_21, null, null, null, JavaEEFacetConstants.EJB_21, true));

    	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_24, null, null, null, null, JavaEEFacetConstants.WEB_24, true));

    	for(int i = 0; i < models.size(); i++) {
    		OperationTestCase.runDataModel(models.get(i));
    	}
    	
    	dependencies.addAll(Arrays.asList(JavaEEProjectUtilities.getAllProjects()));
    	
    	return dependencies;
    }
    
    public static List getJavaDependencyList_14() {
    	return Collections.emptyList();
    }
    
    public static List getJ2EEDependencyList_5() throws Exception {
    	getJ2EEDependencyList_14();
    	List dependencies = new ArrayList();
    	
    	List<IDataModel> models = new ArrayList<IDataModel>();
    	
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_5, null, JavaEEFacetConstants.APP_CLIENT_5, true, false));
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_5 + "_WithDD", null, JavaEEFacetConstants.APP_CLIENT_5, true, true));
    	
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_3, null, null, null, JavaEEFacetConstants.EJB_3, false));
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_3 + "_WithDD", null, null, null, JavaEEFacetConstants.EJB_3, true));
    	
    	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_25, null, null, null, null, JavaEEFacetConstants.WEB_25, false));
      	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_25 + "_WithDD", null, null, null, null, JavaEEFacetConstants.WEB_25, true));
    	
    	for(int i = 0; i < models.size(); i++) {
    		OperationTestCase.runDataModel(models.get(i));
    	}
    	
    	dependencies.addAll(Arrays.asList(JavaEEProjectUtilities.getAllProjects()));
    	
    	return dependencies;
    }
    
    public static List getJavaDependencyList_5() {
    	return Collections.emptyList();
    }
}
