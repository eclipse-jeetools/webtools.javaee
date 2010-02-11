/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.webfragment.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFragmentFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;


public class WebFragmentProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
	public WebFragmentProjectCreationOperationTest() {
		super("WebProjectCreationOperationTests");
	}
	
	public WebFragmentProjectCreationOperationTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebFragmentProjectCreationOperationTest.class);
    }
	
	public void testWeb30_Defaults() throws Exception {
		IDataModel dm = getWebFragmentDataModel("badWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedContentDir() throws Exception {
		IDataModel dm = getWebFragmentDataModel("madWeb", "madContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebFragmentDataModel("booWeb", null, "booSrc", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebFragmentDataModel("eeWeb", "contentEE", "srcTry", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("badWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("herWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("madWeb", "madContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("booWeb", null, "booSrc", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("pinkWeb", "pinkContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("veryfarWeb", null, "verybadSrc", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("eeWeb", "contentEE", "srcTry", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebFragmentDataModel("iiWeb", "iiContDir", "srgII", JavaEEFacetConstants.WEBFRAGMENT_30, false);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb30_Defaults_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("badWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("herWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("madWeb", "madContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("pinkWeb", "tryContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("farawayWeb", null, "farSrc", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("eeWeb", "contentE", "srcTry", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_Defaults_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("badWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("herWeb", null, null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("madWeb", "madContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("booWeb", null, "booSrc", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("pinkWeb", "pinkContent", null, JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("veryfarWeb", null, "verybadSrc", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("eeWeb", "contentEE", "srcTry", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebFragmentDataModel("iiWeb", "iiContDir", "srgII", JavaEEFacetConstants.WEBFRAGMENT_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	
    /**
     * Creates and returns a Web Fragment Data Model with the given name and of the given version.
     * Can also be used to specify none default context root, content directory, and/or
     * the java source directory.
     * If earName is not null then Web will be added to the EAR with earName, and if appropriate
     * with or without a deployment descriptor.
     * 
     * @param projName name of the project to create
     * @param contentDir the content directory to use for this project, use default if NULL
     * @param javaSrcDir the java source directory to use for this project, use default if NULL
     * @param version version of Web to use
     * @param createDD - if true then create DD else don't
     * @return a Web Fragment Data Model with the appropriate properties set
     */
    public static IDataModel getWebFragmentDataModel(String projName, String contentDir, String javaSrcDir, IProjectFacetVersion version, boolean createDD){
    	IDataModel dm = DataModelFactory.createDataModel(new WebFragmentFacetInstallDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	dm.setProperty(IFacetDataModelProperties.FACET_VERSION, version);

    	//if no contentDir provided use default
    	if(contentDir != null) {
    		dm.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER, contentDir);
    	}
    	
    	//if no javaSrcDir provided use default
    	if(javaSrcDir != null) {
    		dm.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, javaSrcDir);
    	}
    	
    	dm.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);
    	
/* does the Java version get set correctly?
    	if(version.equals(JavaEEFacetConstants.WEBFRAGMENT_30))    	{
    		IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
	    	javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_6);
    	}
    	else{    		
            IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
            javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_5);
    	}
*/
    	
    	return dm;
    }
}
