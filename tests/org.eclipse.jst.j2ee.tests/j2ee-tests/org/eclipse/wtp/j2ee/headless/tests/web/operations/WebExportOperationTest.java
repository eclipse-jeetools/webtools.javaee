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
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentExportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebExportOperationTest extends ModuleExportOperationTest {	
	public WebExportOperationTest() {
		super("WebExportOperationTests");
	}
	
	public WebExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(WebExportOperationTest.class);
	}
	
	public void testWebExport22_Defaults() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("testWebExport", null, null, null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_Defaults() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("superWeb", null, null, null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_Defaults() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("crazyWeb", null, null, null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_Defaults() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("netoWeb", null, null, null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}	
	
	public void testWebExport22_ChangedContentDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("goodWeb", null, null, "myAwesomeContentDir", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContentDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("niceWeb", null, null, "contentHere", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContentDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("spiderWeb", null, null, "iLikeContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContentDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hotWeb", null, null, "fooContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("penWeb", null, null, null, "myJavaSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("pencilWeb", null, null, null, "fooSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("clipWeb", null, null, null, "barSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("markerWeb", null, null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("aWeb", null, null, "contentA", "srcHi", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("bWeb", null, null, "contentB", "srcBy", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("cWeb", null, null, "contentC", "srcKite", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("dWeb", null, null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_Defaults_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("testWebExport", "teatEAR", null, null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_Defaults_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("superWeb", "superEAR", null, null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_Defaults_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("crazyWeb", "crazyEAR", null, null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_Defaults_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("netoWeb", "netoEAR", null, null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("myWeb", "earMy", "superContextRoot", null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("yourWeb", "earYour", "contextRootFoo", null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("ourWeb", "earOur", "barContextRoot", null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("theirWeb", "earTheir", "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("goodWeb", "aEAR", null, "myAwesomeContentDir", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("niceWeb", "bEAR", null, "contentHere", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("spiderWeb", "cEAR", null, "iLikeContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hotWeb", "dEAR", null, "fooContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("penWeb", "fEAR", null, null, "myJavaSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("pencilWeb", "gEAR", null, null, "fooSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("clipWeb", "hEAR", null, null, "barSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("markerWeb", "iEAR", null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("redWeb", "jEAR", "superCR", "content", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("yellowWeb", "kEAR", "fooCR", "goContent", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("blackWeb", "lEAR", "barCR", "stopContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("whiteWeb", "mEAR", "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("farWeb", "nEAR", "ourCR", null, "superSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("closeWeb", "oEAR", "theirCR", null, "netoSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("neerWeb", "pEAR", "myCR", null, "niceSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("awayWeb", "qEAR", "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("aWeb", "aEAR", null, "contentA", "srcHi", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("bWeb", "bEAR", null, "contentB", "srcBy", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("cWeb", "cEAR", null, "contentC", "srcKite", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("dWeb", "dEAR", null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	public void testWebExport22_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("eWeb", "eEAR", "eRoot", "eContDir", "srcE", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport23_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("fWeb", "fEAR", "fRoot", "fContDir", "srcF", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport24_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("gWeb", "gEAR", "gRoot", "gContDir", "srcG", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	public void testWebExport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hWeb", "hEAR", "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
	
	
	public void testWebExport25_Defaults_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("netoWeb", null, null, null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("theirWeb", null, "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hotWeb", null, null, "fooContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("markerWeb", null, null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("whiteWeb", null, "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("awayWeb", null, "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("dWeb", null, null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hWeb", null, "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_Defaults_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("netoWeb", "netoEAR", null, null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("theirWeb", "earTheir", "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hotWeb", "dEAR", null, "fooContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("markerWeb", "iEAR", null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("whiteWeb", "mEAR", "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("awayWeb", "qEAR", "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("dWeb", "dEAR", null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}

	public void testWebExport25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = WebProjectCreationOperationTest.getWebDataModel("hWeb", "hEAR", "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runDataModel(dm);
		
		runExportTests_All(dm);
	}
	
    @Override
    protected String getModuleExtension() {
    	return ".war";
    }
	
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return getWebExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting, false);
	}
	
    /**
     * @param projectName name of the project to export
     * @param destination destination to export to
     * @param exportSource if TRUE export source files, else don't
     * @param runBuild if TRUE run a build before exporting, else don't
     * @param overwriteExisting if TRUE overwrite existing files, else don't
     * @param excludeCompileJSP indicates whether or not to export compiled JSP files
     * @return an EJBComponentExport data model with all of the given settings.
     */
    public static IDataModel getWebExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting, boolean excludeCompileJSP){
    	IDataModel exportModel = DataModelFactory.createDataModel(new WebComponentExportDataModelProvider());
    	
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, destination);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, exportSource);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwriteExisting);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, runBuild);
		
		exportModel.setProperty(IWebComponentExportDataModelProperties.EXCLUDE_COMPILE_JSP, excludeCompileJSP);
		
		return exportModel;
    }
    
    @Override
    protected void addJavaFilesToProject(String projectName, String[] classNames, String prackageName) throws Exception {
    	JavaFileTestingUtilities.addJavaFilesToWeb(projectName, classNames, prackageName);
    }
    
    @Override
    protected void verifyJavaFilesExported(String archiveName, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
    	JavaFileTestingUtilities.verifyJavaFilesInWAR(archiveName, classNames, packageName, withClassFiles, withSource);
    }
}
