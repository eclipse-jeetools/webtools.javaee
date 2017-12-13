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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jst.common.internal.modulecore.ClasspathContainerVirtualComponent;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddReferenceToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.classpath.tests.util.ClasspathDependencyTestUtil;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJavaUtilityProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IAddReferenceDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.IModuleFile;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.model.ModuleDelegate;
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
	private static final String APP_CLIENT_PROJ_6 = "myAppClient_6";
	
	private static final String EJB_PROJ_11 = "myEJB_11";
	private static final String EJB_PROJ_2 = "myEJB_2";
	private static final String EJB_PROJ_21 = "myEJB_21";
	private static final String EJB_PROJ_3 = "myEJB_3";
	private static final String EJB_PROJ_31 = "myEJB_31";
	
	private static final String WEB_PROJ_22 = "myWeb_22";
	private static final String WEB_PROJ_23 = "myWeb_23";
	private static final String WEB_PROJ_24 = "myWeb_24";
	private static final String WEB_PROJ_25 = "myWeb_25";
	private static final String WEB_PROJ_30 = "myWeb_30";
	
	private static final String CONNECTOR_PROJ_1 = "myConnector_1";
	private static final String CONNECTOR_PROJ_15 = "myConnector_15";

	public EARProjectCreationOperationTest(String name) {
		super(name);
	}

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
    
    public void testEAR60_Defaults() throws Exception{
    	IDataModel dm = getEARDataModel("eeEAR", null, null, null, JavaEEFacetConstants.EAR_6, false);
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
    
    public void testEAR60_ChangedContentDir() throws Exception{
    	IDataModel dm = getEARDataModel("iEAR", "herContent", null, null, JavaEEFacetConstants.EAR_6, false);
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
    
    public void testEAR60_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("whyEAR", null, getJ2EEDependencyList_6(), getJavaDependencyList_6(), JavaEEFacetConstants.EAR_6, false);
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
    
    public void testEAR60_ChangedContentDir_WithDependencies() throws Exception{
    	IDataModel dm = getEARDataModel("xyzEAR", "hisContent", getJ2EEDependencyList_6(), getJavaDependencyList_6(), JavaEEFacetConstants.EAR_6, false);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR50_Defaults_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("myEAR", null, null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR60_Defaults_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("mineEAR", null, null, null, JavaEEFacetConstants.EAR_6, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    
    public void testEAR50_ChangedContentDir_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("yourEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR60_ChangedContentDir_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("thatEAR", "thatContent", null, null, JavaEEFacetConstants.EAR_6, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
/*    
    public void testEAR50_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("anEAR", null, getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
    
    public void testEAR50_ChangedContentDir_WithDependencies_WithDD() throws Exception{
    	IDataModel dm = getEARDataModel("theirEAR", "gotContent", getJ2EEDependencyList_5(), getJavaDependencyList_5(), JavaEEFacetConstants.EAR_5, true);
    	OperationTestCase.runAndVerify(dm);
    }
*/
    
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
		
        
        facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);
        
		
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
    
    public static List getJ2EEDependencyList_6() throws Exception {
    	getJ2EEDependencyList_5();
    	List dependencies = new ArrayList();
    	
    	List<IDataModel> models = new ArrayList<IDataModel>();
    	
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_6, null, JavaEEFacetConstants.APP_CLIENT_6, true, false));
    	models.add(AppClientProjectCreationOperationTest.getAppClientCreationDataModel(APP_CLIENT_PROJ_6 + "_WithDD", null, JavaEEFacetConstants.APP_CLIENT_6, true, true));
    	
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_31, null, null, null, JavaEEFacetConstants.EJB_31, false));
    	models.add(EJBProjectCreationOperationTest.getEJBDataModel(EJB_PROJ_31 + "_WithDD", null, null, null, JavaEEFacetConstants.EJB_31, true));
    	
    	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_30, null, null, null, null, JavaEEFacetConstants.WEB_30, false));
      	models.add(WebProjectCreationOperationTest.getWebDataModel(WEB_PROJ_30 + "_WithDD", null, null, null, null, JavaEEFacetConstants.WEB_30, true));
    	
    	for(int i = 0; i < models.size(); i++) {
    		OperationTestCase.runDataModel(models.get(i));
    	}
    	
    	dependencies.addAll(Arrays.asList(JavaEEProjectUtilities.getAllProjects()));
    	
    	return dependencies;
    }
    
    public static List getJavaDependencyList_6() {
    	return Collections.emptyList();
    }

    
    public void testEAR50_WithVariableReference() throws Exception{
    	IDataModel dm = getEARDataModel("pEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    	IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject("pEAR");
    	if( p != null && p.exists()) {
    		try {
	    		IVirtualComponent vc = ComponentCore.createComponent(p);
	    		addArchiveComponent(vc);
	    		// now verify
	    		IModule module = ServerUtil.getModule(p);
	    		assertNotNull(module);
	    		ModuleDelegate md = (ModuleDelegate)module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
	    		IModuleResource[] resources = md.members();
	    		assertEquals(1, resources.length);
	    		assertEquals(1, ((IModuleFolder)resources[0]).members().length);
	    		assertTrue(((IModuleFolder)resources[0]).members()[0] instanceof IModuleFile);
	    		IModuleFile junitjar = (IModuleFile)((IModuleFolder)resources[0]).members()[0];
	    		assertEquals("junit.jar", junitjar.getName());
    		} catch( CoreException ce ) {
    			ce.printStackTrace();
    		}
    	}
    }
    
    public void testEAR50_NestedUtil_WithVariableReference() throws Exception{
    	IDataModel dm = getEARDataModel("zEAR", "zContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    	IProject earProj = ResourcesPlugin.getWorkspace().getRoot().getProject("zEAR");
    	
    	IDataModel dm2 = getUtilityProjectCreationDataModel("nestedUtil", "zEAR");
    	OperationTestCase.runAndVerify(dm2);
    	IProject utilProj = ResourcesPlugin.getWorkspace().getRoot().getProject("nestedUtil");

		IVirtualComponent vc = ComponentCore.createComponent(utilProj);
		addArchiveComponent(vc);

		IModule module = ServerUtil.getModule(utilProj);
		assertNotNull(module);
		ModuleDelegate md = (ModuleDelegate)module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
		IModuleResource[] resources = md.members();
		
		// ensure a 'lib' is found
		IModuleResource lib = null;
		for( int i = 0; i < resources.length; i++ ) {
			if( resources[i].getName().equals("lib")) {
				lib = resources[i];
				break;
			}
		}
		assertNotNull(lib);
		assertTrue(lib instanceof IModuleFolder);
		IModuleResource[] libs = ((IModuleFolder)lib).members();
		assertNotNull(libs);
		assertTrue(libs.length == 1);
		assertTrue(libs[0] instanceof IModuleFile);
		IModuleFile junitjar = (IModuleFile)libs[0];
		assertEquals("junit.jar", junitjar.getName());
    }
    
    /**
     * Creates and returns a utility project DM provider with the given name and of the given version.
     * If earName is not null then util project will be added to the EAR with earName
     * 
     * @param projName name of the project to create
     * @param earName name of the ear to add the project too, if NULL then don't add to an EAR
     * @param version version of Application Client to use
     * @return a Utility Project Data Model with the appropriate properties set
     */
    public static IDataModel getUtilityProjectCreationDataModel(String projName, String earName){
    	IDataModel dm = DataModelFactory.createDataModel(new JavaUtilityProjectCreationDataModelProvider());
    	dm.setProperty(IJavaUtilityProjectCreationDataModelProperties.PROJECT_NAME, projName);
    	dm.setProperty(IJavaUtilityProjectCreationDataModelProperties.SOURCE_FOLDER, "src");
    	if(earName != null) {
    		dm.setProperty(IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);
    	} 
    	return dm;
    }

    public void testEAR_HardDeploymentMapping() throws Exception {
    	IDataModel dm = getEARDataModel("hardEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    	IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject("hardEAR");
    	IFolder f = p.getFolder("test");
    	f.create(true, true, new NullProgressMonitor());
    	IFile file = f.getFile("silly.txt");
    	file.create(new ByteArrayInputStream("Silly String".getBytes()), true, new NullProgressMonitor());
    	
    	IVirtualComponent earComp = ComponentCore.createComponent(p);
    	IVirtualFolder rootFolder = earComp.getRootFolder();
    	IVirtualFile vfile = rootFolder.getFile(new Path("out/notsilly.txt"));
    	vfile.createLink(new Path("test/silly.txt"), 0, new NullProgressMonitor());
    	
		IModule module = ServerUtil.getModule(p);
		assertNotNull(module);
		ModuleDelegate md = (ModuleDelegate)module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
		IModuleResource[] resources = md.members();
    	assertTrue(resources.length == 1);
    	assertTrue(resources[0].getName().equals("out"));
    	IModuleFolder mf = (IModuleFolder)resources[0];
    	IModuleResource[] children = mf.members();
    	assertTrue(children.length == 1);
    	assertTrue(children[0].getName().equals("notsilly.txt"));
    }
    
    public void testEARWithJarInLibFolder() throws Exception {
    	IDataModel dm = getEARDataModel("qEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    	IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject("qEAR");
    	IFolder folder = p.getFolder("ourContent/lib");
    	folder.create(true, true, null);
    	IFile file = folder.getFile("test1.jar");
    	file.create(new FileInputStream(ClasspathDependencyTestUtil.TEST1_JAR_PATH.toFile()), 0, new NullProgressMonitor());
		IModule module = ServerUtil.getModule(p);
		ModuleDelegate md = (ModuleDelegate)module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
		IModuleResource[] resources = md.members();
		assertEquals(1, resources.length);
		assertEquals("lib", resources[0].getName());
		assertTrue(resources[0] instanceof IModuleFolder);
		IModuleResource[] children =((IModuleFolder)resources[0]).members(); 
		assertEquals(1, children.length);
		assertEquals("test1.jar", children[0].getName());
    }

    public void testEARWithClasspathContainerReference() throws Exception {
    	// Find the junit jar
		ClasspathContainerInitializer initializer= JavaCore.getClasspathContainerInitializer(JavaCore.USER_LIBRARY_CONTAINER_ID);
		IPath path = new Path("JUNIT_HOME/junit.jar"); //$NON-NLS-1$
		IPath resolvedPath = JavaCore.getResolvedVariablePath(path);
		
		// Make a new user library a999 referencing this jar
		IClasspathEntry junitEntry = JavaCore.newLibraryEntry(resolvedPath, null, null);
		JavaModelManager.getUserLibraryManager().setUserLibrary("a999", new IClasspathEntry[]{junitEntry}, false);
		String containerPath = JavaCore.USER_LIBRARY_CONTAINER_ID + "/a999";
		
		// Make an EAR project
    	IDataModel dm = getEARDataModel("rEAR", "ourContent", null, null, JavaEEFacetConstants.EAR_5, false);
    	OperationTestCase.runAndVerify(dm);
    	IProject rootProj = ResourcesPlugin.getWorkspace().getRoot().getProject("rEAR");
    	final IVirtualComponent rootComp = ComponentCore.createComponent(rootProj);
    	
    	// Add a classpath container reference
    	IVirtualComponent classpathContainerComp = new ClasspathContainerVirtualComponent(rootProj, rootComp, containerPath);
		final VirtualReference ref = new VirtualReference(rootComp, classpathContainerComp, new Path("/testFolder").makeAbsolute());
		ref.setDependencyType(IVirtualReference.DEPENDENCY_TYPE_CONSUMES);
		IWorkspaceRunnable runnable = new IWorkspaceRunnable(){
			public void run(IProgressMonitor monitor) throws CoreException{
				IDataModelProvider provider = new AddReferenceToEnterpriseApplicationDataModelProvider();
				IDataModel dm = DataModelFactory.createDataModel(provider);
				dm.setProperty(IAddReferenceDataModelProperties.SOURCE_COMPONENT, rootComp);
				dm.setProperty(IAddReferenceDataModelProperties.TARGET_REFERENCE_LIST, Arrays.asList(ref));
				
				IStatus stat = dm.validateProperty(IAddReferenceDataModelProperties.TARGET_REFERENCE_LIST);
				if (!stat.isOK())
					throw new CoreException(stat);
				try {
					dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
				} catch (ExecutionException e) {
					throw new CoreException(new Status(IStatus.ERROR, "blah", "error", e));
				}	
			}
		};
		try {
			ResourcesPlugin.getWorkspace().run(runnable, new NullProgressMonitor());
		} catch( CoreException e ) {
			e.printStackTrace();
			fail();
		}
		
		// Verify module stuff!
		IModule module = ServerUtil.getModule(rootProj);
		ModuleDelegate delegate = (ModuleDelegate)module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
		IModuleResource[] resources = delegate.members();
		assertTrue(resources.length == 1);
		assertTrue(resources[0] instanceof IModuleFolder);
		assertTrue(resources[0].getName().equals("testFolder"));
		assertTrue(((IModuleFolder)resources[0]).members().length == 1);
		assertTrue(((IModuleFolder)resources[0]).members()[0] instanceof IModuleFile);
		assertTrue(((IModuleFolder)resources[0]).members()[0].getName().equals("junit.jar"));
    }

    public void addArchiveComponent(IVirtualComponent component) throws CoreException {
		
		IPath path = new Path("JUNIT_HOME/junit.jar"); //$NON-NLS-1$
		IPath resolvedPath = JavaCore.getResolvedVariablePath(path);
//		java.io.File file = new java.io.File(resolvedPath.toOSString());
//		if( file.isFile() && file.exists()){
			String type = VirtualArchiveComponent.VARARCHIVETYPE + IPath.SEPARATOR;
			IVirtualComponent archive = ComponentCore.createArchiveComponent( component.getProject(), type +
					path.toString());
			IDataModelProvider provider = new AddComponentToEnterpriseApplicationDataModelProvider();
			IDataModel dm = DataModelFactory.createDataModel(provider);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, component);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(archive));
			Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
			uriMap.put(archive, "junit.jar");
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
	        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, "/lib");
			IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			if (!stat.isOK())
				throw new CoreException(stat);
			try {
				dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				throw new CoreException(new Status(IStatus.ERROR, "test", e.getMessage()));
			}	

//		}
	}
}
