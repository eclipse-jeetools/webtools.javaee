/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBImportOperationTest extends ModuleImportOperationTestCase { 
	
	public static Test suite() {
		return new TestSuite(EJBImportOperationTest.class);
	} 

	public EJBImportOperationTest(String name) {
		super(name);
	}  	 
	
	public void testAutoworld() throws Exception {
		testImport("Autoworld", "auto.jar");
	}

	public void testMinibank() throws Exception { 
		testImport("Minibank", "MinibankEJB.jar"); 
	} 
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getDirectory()
	 */
	protected String getDirectory() {
		return "EJBImportTests";
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getModelInstance()
	 */
	protected J2EEModuleImportDataModel getModelInstance() {
		return new EJBModuleImportDataModel();
	}
	
 
	
}
