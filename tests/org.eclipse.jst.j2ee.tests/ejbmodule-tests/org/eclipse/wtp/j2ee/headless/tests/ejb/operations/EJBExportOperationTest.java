/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleExportDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBExportOperationTest extends ModuleExportOperationTestCase {  
	
	public EJBExportOperationTest(String name) {
		super(name);
	}
	 
	public static Test suite() {
		return new TestSuite(EJBExportOperationTest.class);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getExportModel()
	 */
	protected J2EEModuleExportDataModel getModelInstance() {
		return new EJBModuleExportDataModel();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new EJBImportOperationTest("");
	}
	
	

}
