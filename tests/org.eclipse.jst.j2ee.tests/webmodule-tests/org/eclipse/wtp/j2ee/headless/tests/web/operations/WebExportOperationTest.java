/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleExportDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebExportOperationTest extends ModuleExportOperationTestCase {
	 
	protected boolean excludeCompileJsp = false;
	
	public WebExportOperationTest(String name) {
		super(name);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(); 
		suite.addTestSuite(WebExportOperationTest.class); 
		return suite;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception { 
		super.setUp(); 
		excludeCompileJsp = false;
	}

	public void testExcludeCompileJspOn() throws Exception {  
		excludeCompileJsp = true;
		testAllExportTestCases();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getModelInstance()
	 */
	protected J2EEModuleExportDataModel getModelInstance() {
		WebModuleExportDataModel dataModel = new WebModuleExportDataModel(); 
		dataModel.setBooleanProperty(WebModuleExportDataModel.EXCLUDE_COMPILE_JSP, excludeCompileJsp);
		return dataModel;		
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new WebImportOperationTest("");
	}
	
	/**
	 * @return
	 */
	public String getModuleExportFileExt() {
		return ".war";
	}

}
