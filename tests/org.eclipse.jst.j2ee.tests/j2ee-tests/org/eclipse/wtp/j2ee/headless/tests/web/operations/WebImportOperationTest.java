/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebImportOperationTest extends WebImportOperationBaseTest {

	public WebImportOperationTest() {
		super("WebImportOperationTests");
	}
	
	public WebImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(WebImportOperationTest.class);
	}
	
	public void testWebImport22_Defaults() throws Exception {
		runImportTests_All("Web22_Defaults");
	}
	
	public void testWebImport23_Defaults() throws Exception {
		runImportTests_All("Web23_Defaults");
	}
	
	public void testWebImport24_Defaults() throws Exception {
		runImportTests_All("Web24_Defaults");
	}
	
	public void testWebImport22_DiffContentDir() throws Exception {
		runImportTests_All("Web22_DiffContentDir");
	}
	
	public void testWebImport23_DiffContentDir() throws Exception {
		runImportTests_All("Web23_DiffContentDir");
	}
	
	public void testWebImport24_DiffContentDir() throws Exception {
		runImportTests_All("Web24_DiffContentDir");
	}
	
	public void testWebImport22_DiffSrcDir() throws Exception {
		runImportTests_All("Web22_DiffSrcDir");
	}
	
	public void testWebImport23_DiffSrcDir() throws Exception {
		runImportTests_All("Web23_DiffSrcDir");
	}
	
	public void testWebImport24_DiffSrcDir() throws Exception {
		runImportTests_All("Web24_DiffSrcDir");
	}
	
	public void testWebImport22_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web22_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport23_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web23_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport24_DiffContentDir_DiffSrcDir() throws Exception {
		runImportTests_All("Web24_DiffContentDir_DiffSrcDir");
	}
	
	public void testWebImport22_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web22_Defaults_WithEAR");
	}
	
	public void testWebImport23_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web23_Defaults_WithEAR");
	}
	
	public void testWebImport24_Defaults_WithEAR() throws Exception {
		runImportTests_All("Web24_Defaults_WithEAR");
	}
	
	public void testWebImport22_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_WithEAR");
	}
	
	public void testWebImport22_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContentDir_WithEAR");
	}
	
	public void testWebImport23_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContentDir_WithEAR");
	}
	
	public void testWebImport24_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContentDir_WithEAR");
	}
		
	public void testWebImport22_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport22_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffContentDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffContentDir_WithEAR");
	}
	
	public void testWebImport22_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport22_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport22_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web22_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport23_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web23_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
	
	public void testWebImport24_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR() throws Exception {
		runImportTests_All("Web24_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR");
	}
		
	public void testWebImport25_Defaults_WithDD() throws Exception {
		runImportTests_All("Web25_Defaults_WithDD");
	}

	public void testWebImport25_DiffContextRoot_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithDD");
	}

	public void testWebImport25_DiffContentDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithDD");
	}

	public void testWebImport25_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContentDir_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithDD");
	}

	public void testWebImport25_Defaults_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_Defaults_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContentDir_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContentDir_DiffSrcDir_WithEAR_WithDD");
	}

	public void testWebImport25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR_WithDD() throws Exception {
		runImportTests_All("Web25_DiffContextRoot_DiffContentDir_DiffSrcDir_WithEAR_WithDD");
	}	
}
