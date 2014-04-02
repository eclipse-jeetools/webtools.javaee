/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
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


public class WebProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
	public WebProjectCreationOperationTest() {
		super("WebProjectCreationOperationTests");
	}
	
	public WebProjectCreationOperationTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebProjectCreationOperationTest.class);
    }
	
	public void testWeb22_Defaults() throws Exception {
		IDataModel dm = getWebDataModel("testWeb", null, null, null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_Defaults() throws Exception {
		IDataModel dm = getWebDataModel("superWeb", null, null, null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_Defaults() throws Exception {
		IDataModel dm = getWebDataModel("crazyWeb", null, null, null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_Defaults() throws Exception {
		IDataModel dm = getWebDataModel("netoWeb", null, null, null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_Defaults() throws Exception {
		IDataModel dm = getWebDataModel("badWeb", null, null, null, null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	

	public void testWeb22_ChangedContentDir() throws Exception {
		IDataModel dm = getWebDataModel("goodWeb", null, null, "myAwesomeContentDir", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContentDir() throws Exception {
		IDataModel dm = getWebDataModel("niceWeb", null, null, "contentHere", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContentDir() throws Exception {
		IDataModel dm = getWebDataModel("spiderWeb", null, null, "iLikeContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContentDir() throws Exception {
		IDataModel dm = getWebDataModel("hotWeb", null, null, "fooContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir() throws Exception {
		IDataModel dm = getWebDataModel("madWeb", null, null, "madContent", null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb22_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("penWeb", null, null, null, "myJavaSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("pencilWeb", null, null, null, "fooSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("clipWeb", null, null, null, "barSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("markerWeb", null, null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("booWeb", null, null, null, "booSrc", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("aWeb", null, null, "contentA", "srcHi", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("bWeb", null, null, "contentB", "srcBy", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("cWeb", null, null, "contentC", "srcKite", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("dWeb", null, null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir() throws Exception {
		IDataModel dm = getWebDataModel("eeWeb", null, null, "contentEE", "srcTry", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("testWeb", "teatEAR", null, null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("superWeb", "superEAR", null, null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("crazyWeb", "crazyEAR", null, null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("netoWeb", "netoEAR", null, null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_Defaults_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("badWeb", "badEAR", null, null, null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb22_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("myWeb", "earMy", "superContextRoot", null, null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("yourWeb", "earYour", "contextRootFoo", null, null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("ourWeb", "earOur", "barContextRoot", null, null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("theirWeb", "earTheir", "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("herWeb", "earHer", "herContextRoot", null, null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("goodWeb", "aEAR", null, "myAwesomeContentDir", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("niceWeb", "bEAR", null, "contentHere", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("spiderWeb", "cEAR", null, "iLikeContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("hotWeb", "dEAR", null, "fooContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("madWeb", "eeEAR", null, "madContent", null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("penWeb", "fEAR", null, null, "myJavaSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("pencilWeb", "gEAR", null, null, "fooSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("clipWeb", "hEAR", null, null, "barSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("markerWeb", "iEAR", null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("booWeb", "jjEAR", null, null, "booSrc", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("redWeb", "jEAR", "superCR", "content", null, JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("yellowWeb", "kEAR", "fooCR", "goContent", null, JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("blackWeb", "lEAR", "barCR", "stopContent", null, JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("whiteWeb", "mEAR", "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("pinkWeb", "nnEAR", "pinkCR", "pinkContent", null, JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("farWeb", "nEAR", "ourCR", null, "superSrc", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("closeWeb", "oEAR", "theirCR", null, "netoSrc", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("neerWeb", "pEAR", "myCR", null, "niceSrc", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("awayWeb", "qEAR", "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("veryfarWeb", "rEAR", "herCR", null, "verybadSrc", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("aWeb", "aEAR", null, "contentA", "srcHi", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("bWeb", "bEAR", null, "contentB", "srcBy", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("cWeb", "cEAR", null, "contentC", "srcKite", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("dWeb", "dEAR", null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("eeWeb", "eeEAR", null, "contentEE", "srcTry", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb22_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("eWeb", "eEAR", "eRoot", "eContDir", "srcE", JavaEEFacetConstants.WEB_22, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb23_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("fWeb", "fEAR", "fRoot", "fContDir", "srcF", JavaEEFacetConstants.WEB_23, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb24_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("gWeb", "gEAR", "gRoot", "gContDir", "srcG", JavaEEFacetConstants.WEB_24, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("hWeb", "hEAR", "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR() throws Exception {
		IDataModel dm = getWebDataModel("iiWeb", "iiEAR", "iiRoot", "iiContDir", "srgII", JavaEEFacetConstants.WEB_30, false);
		OperationTestCase.runAndVerify(dm);
	}
	
	
	public void testWeb25_Defaults_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("netoWeb", null, null, null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("theirWeb", null, "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("hotWeb", null, null, "fooContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("markerWeb", null, null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("whiteWeb", null, "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("awayWeb", null, "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("dWeb", null, null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("hWeb", null, "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_Defaults_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("netoWeb", "netoEAR", null, null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("theirWeb", "earTheir", "theRootOfContext", null, null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("hotWeb", "dEAR", null, "fooContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("markerWeb", "iEAR", null, null, "srcOfCool", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("whiteWeb", "mEAR", "theROfC", "testContent", null, JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("awayWeb", "qEAR", "yourCR", null, "coolSrc", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("dWeb", "dEAR", null, "contentD", "srcBike", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}

	public void testWeb25_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("hWeb", "hEAR", "hRoot", "hContDir", "srgH", JavaEEFacetConstants.WEB_25, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_Defaults_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("badWeb", null, null, null, null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("herWeb", null, "herRootOfContext", null, null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("madWeb", null, null, "madContent", null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("pinkWeb", null, "pinkRC", "tryContent", null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("farawayWeb", null, "farawayCR", null, "farSrc", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("eeWeb", null, null, "contentE", "srcTry", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_Defaults_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("badWeb", "badEAR", null, null, null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("herWeb", "earHer", "herContextRoot", null, null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("madWeb", "eeEAR", null, "madContent", null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("booWeb", "jjEAR", null, null, "booSrc", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("pinkWeb", "nnEAR", "pinkCR", "pinkContent", null, JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("veryfarWeb", "rEAR", "herCR", null, "verybadSrc", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("eeWeb", "eeEAR", null, "contentEE", "srcTry", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	public void testWeb30_ChangedContextRoot_ChangedContentDir_ChangedJavaSrcDir_WithEAR_WithDD() throws Exception {
		IDataModel dm = getWebDataModel("iiWeb", "iiEAR", "iiRoot", "iiContDir", "srgII", JavaEEFacetConstants.WEB_30, true);
		OperationTestCase.runAndVerify(dm);
	}
	
	
    /**
     * Creates and returns a Web Data Model with the given name and of the given version.
     * Can also be used to specify none default context root, content directory, and/or
     * the java source directory.
     * If earName is not null then Web will be added to the EAR with earName, and if appropriate
     * with or without a deployment descriptor.
     * 
     * @param projName name of the project to create
     * @param earName name of the ear to add the project too, if NULL then don't add to an EAR
     * @param contextRoot the context root to use for this  project, use default if NULL
     * @param contentDir the content directory to use for this project, use default if NULL
     * @param javaSrcDir the java source directory to use for this project, use default if NULL
     * @param version version of Web to use
     * @param createDD only used if version is JEE5, if true then create DD else don't
     * @return a Web Data Model with the appropriate properties set
     */
    public static IDataModel getWebDataModel(String projName, String earName, String contextRoot, String contentDir, String javaSrcDir, IProjectFacetVersion version, boolean createDD){
    	IDataModel dm = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
    	dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	
    	if(earName != null) {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earName);
    	} else {
    		dm.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, false);
    	}
    	
    	FacetDataModelMap facetMap = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.DYNAMIC_WEB);
    	facetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, version);
    	
    	//if no contextRoot provided use default, contextRoot only matters if adding to EAR
    	if(contextRoot != null && earName != null) {
    		facetModel.setStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT, contextRoot);
    	}
    	
    	//if no contentDir provided use default
    	if(contentDir != null) {
    		facetModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER, contentDir);
    	}
    	
    	//if no javaSrcDir provided use default
    	if(javaSrcDir != null) {
    		facetModel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, javaSrcDir);
    	}
    	
    	facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, createDD);
    	
    	if (version.equals(JavaEEFacetConstants.WEB_31)){
    		IDataModel javaFacetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.JAVA);
	    	javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_7);   		
    	}
    	else if(version.equals(JavaEEFacetConstants.WEB_30))    	{
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
