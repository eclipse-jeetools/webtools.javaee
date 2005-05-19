/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

//TODO DELETE
/**
 * @deprecated
 */
public class EJBImportOperationTest extends ModuleImportOperationTestCase { 
	
	public static Test suite() {
		return new TestSuite(EJBImportOperationTest.class);
	} 

	public EJBImportOperationTest(String name) {
		super(name);
	}  	 

	public void testEJB20Import() throws Exception { 
		testImport("Test13EJB", getFullPathForEJBJar("Test13EJB.jar")); 
		
	} 
	
	public String getFullPathForEJBJar(String jarName) {
	try{
		return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(),TESTS_PATH + "\\" + jarName);
	} catch (Exception e) {
		e.printStackTrace();
	  }
	return jarName;
	}
	
	public void testEJB21Import() throws Exception { 
		testImport("Test14EJB", getFullPathForEJBJar("Test14EJB.jar")); 
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
